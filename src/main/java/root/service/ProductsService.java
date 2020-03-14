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
import java.util.Arrays;
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

//    @Autowired
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

    ProductXmlParse[] productArray = {
            new ProductXmlParse(0,22900L,"고당도 청송사과 5kg/10kg 아삭아삭한 부사","http://www.11st.co.kr/product/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=115849999"),
            new ProductXmlParse(1,22900L,"[만족도최고]주왕산 털보네 하늘사과 흠과 9kg내외","http://www.11st.co.kr/product/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=1418504992"),
            new ProductXmlParse(2,10900L,"하루한봉! 농협이 만든 세척사과 2.5kg","http://www.11st.co.kr/product/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=1830465505"),
            new ProductXmlParse(3,6900L,"늘품 햇 꿀맛 부사 사과 5kg, 10kg","http://www.11st.co.kr/product/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=2607791441"),
            new ProductXmlParse(4,28000L,"청송사과(가을사과 부사)가정용10kg(22과-40과)","http://www.11st.co.kr/product/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=1397500779"),
            new ProductXmlParse(5,15900L,"햇살가득 고랭지 세척사과 5kg","http://www.11st.co.kr/product/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=2561674911"),
    };
    List<ProductXmlParse> productList = Arrays.asList(productArray);//null;
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

    String key = "2d032fa306d841860118fa99d9959d5b";
    @Transactional
    public void/*List<ProductsResponseDto>*/ list(String keyword) throws IOException, SAXException, ParserConfigurationException { // 상품 api 요청 기능
        productList = new ArrayList<>();
//        String key = "2d032fa306d841860118fa99d9959d5b";
        String url = "http://openapi.11st.co.kr/openapi/OpenApiService.tmall?key="+key+"&apiCode=ProductSearch&keyword="+ keyword + "&pageSize=10";
//        WebClient client = WebClient.create(url);

        LOGGER.warn("\n"+url+"\n");
        String xmlString = restTemplate.getForObject(url, String.class);
        LOGGER.warn("\n"+xmlString+"\n");
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
