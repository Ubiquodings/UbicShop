package root.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Setter
@XmlRootElement(name="ProductSearchResponse")
public class ProductListResponseDto {
    private List<Product> productList;

    @XmlElementWrapper(name="Products")
    @XmlElement(name="item")
    public List<Product> getProductList(){
        return productList;
    }

    @Setter
    @Getter
    @XmlRootElement(name="Product")
    public static class Product{
//        @XmlElement(name="ProductName")
        private String productName;

//        @XmlElement(name="ProductPrice")
        private Long productPrice;

//        @XmlElement(name="DetailPageUrl")
        private String detailPageUrl;
    }
}
