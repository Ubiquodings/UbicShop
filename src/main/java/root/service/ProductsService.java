package root.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import root.domain.ProductsRepository;
import root.web.dto.ProductXmlParse;
import root.web.dto.ProductsSaveRequestDto;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.SAXException;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

@RequiredArgsConstructor
@Service
public class ProductsService {

    private Logger LOGGER = LoggerFactory.getLogger(ProductsService.class);

    private final ProductsRepository productsRepository;

    @Autowired
    RestTemplate restTemplate;

    // TODO: 어디서 검색하지 ? list 하면 검색해야지!
    // TODO: item id 가 1부터 시작하는데 정상 맞나? 0부터 시작해야 하는거 아니고 ?
//    private Map<Long, Products> productsList; // 검색하면 초기화:new

    @Transactional
    public Long save(ProductsSaveRequestDto requestDto) {
        return productsRepository.save(requestDto.toEntity()).getId();
    }

//    public ProductsResponseDto findById(Long id) {
        // TODO: 당장은 무조건 있겠지만 Optional 로 유무 확인해야한다
//        Products entity = productsList.get(id);

//        return new ProductsResponseDto(entity);
//    }

    public List<ProductXmlParse> getProductList() {
        return productList;
    }

    List<ProductXmlParse> productList = null;
    // DB 나 Session 에 저장을 해야 하나 ?

    public boolean isExistProductList() {
//        productList = new ArrayList<>();
        if(productList != null){
            LOGGER.error('\n'+"list is not not null"+'\n');
            return true;
        }
        LOGGER.error('\n'+"list is null"+'\n');
        return false;
    }

    @Transactional
    public void/*List<ProductsResponseDto>*/ list(String keyword) throws IOException, SAXException, ParserConfigurationException { // 상품 api 요청 기능
        productList = new ArrayList<>();
        String url = "http://openapi.11st.co.kr/openapi/OpenApiService.tmall?key=20724c2228aa0d17d51b4b236fcf635d&apiCode=ProductSearch&keyword="
                + keyword + "&pageSize=10";
//        WebClient client = WebClient.create(url);

        String xmlString = restTemplate.getForObject(url, String.class);
        parseXmlHttpResponse(xmlString);

    }

    private void parseXmlHttpResponse(String xmlString) throws ParserConfigurationException, SAXException, IOException {
        // Xml String 파싱
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        InputSource is = new InputSource(new StringReader(xmlString));
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(is);

        // root 구하기
        Element root = document.getDocumentElement();

        // 자식 노드 목록
        NodeList childeren = root.getChildNodes(); // root 에서 가져온거니까
        for (int i = 0; i < childeren.getLength(); i++) {
            Node node = childeren.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) { // 왜 쓰는 거지 ?
                if (node.getNodeName().equals("Products")) {
                    // xml
                    parseXmlProductList(node);
                }
            }
        }
    }

    private void parseXmlProductList(Node node) {
        LOGGER.error("\n" + node.getNodeName() + "\n"); // Products

        NodeList productsNodeChilderen = node.getChildNodes();
        NodeList productContents;
        Node tmp, contentNode;
        for (int i = 0; i < productsNodeChilderen.getLength(); i++) {
            tmp = productsNodeChilderen.item(i);
//            productId = i;
//            LOGGER.error("\n" + productId + "\n");

            parseProductContents(tmp, i);
        }
    }

    private void parseProductContents(Node productNode, int productId) {
        NodeList productContents;
        Node contentNode;
        String productName = null, productPrice = null, productPageUrl = null;
//        int productId=-1;
        if (productNode.getNodeName().equals("Product")) {
            productContents = productNode.getChildNodes(); // product content
            for (int j = 0; j < productContents.getLength(); j++) {

                contentNode = productContents.item(j);
                if (contentNode.getNodeName().equals("ProductName")) { // ProductName

                    productName = contentNode.getTextContent();
                    LOGGER.error("\n" + productName + "\n");

                } else if (contentNode.getNodeName().equals("ProductPrice")) { // ProductPrice

                    productPrice = contentNode.getTextContent();
                    LOGGER.error("\n" + productPrice + "\n");

                } else if (contentNode.getNodeName().equals("DetailPageUrl")) { // DetailPageUrl

                    productPageUrl = contentNode.getTextContent();
                    LOGGER.error("\n" + productPageUrl + "\n");

                }
            }

            productList.add(
                    ProductXmlParse.builder()
                            .id(productId)
                            .productName(productName)
                            .productPrice(Long.parseLong(productPrice))
                            .detailPageUrl(productPageUrl)
                            .build()
            );
            LOGGER.error('\n'+"리스트 크기: "+productList.size()+'\n');
        }
    }

}
