package root.web.dto;

import lombok.Builder;

public class ProductXmlParse {

    private int id;
    private String productName;
    private Long productPrice;
    private String detailPageUrl;

    @Builder
    public ProductXmlParse(
            int id,
            /*Long productCode,*/Long productPrice, String productName,
//            String productImage,
//            String seller,Long rate,
                                 String detailPageUrl
//            Long reviewCount,
//            Long buySatisfy
    ){
//        this.productCode=productCode;
        this.id=id;
        this.productPrice=productPrice;
        this.productName=productName;
//        this.productImage=productImage;
//        this.seller=seller;
//        this.rate=rate;
        this.detailPageUrl=detailPageUrl;
//        this.reviewCount=reviewCount;
//        this.buySatisfy=buySatisfy;
    }

}
