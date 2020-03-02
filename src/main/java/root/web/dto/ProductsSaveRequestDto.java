package root.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import root.domain.Products;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
public class ProductsSaveRequestDto {

//    private Long productCode;

    private String productName;
    private Long productPrice;
    private String detailPageUrl;


//    private String productImage;
//
//    private String seller;
//
//    private Long rate;
//
//
//    private Long reviewCount;
//
//    private Long buySatisfy;

    @Builder
    public ProductsSaveRequestDto(
            /*Long productCode,*/Long productPrice, String productName,
//            String productImage,
//            String seller,Long rate,
            String detailPageUrl
//            Long reviewCount,
//            Long buySatisfy
    ){
//        this.productCode=productCode;
        this.productPrice=productPrice;
        this.productName=productName;
//        this.productImage=productImage;
//        this.seller=seller;
//        this.rate=rate;
        this.detailPageUrl=detailPageUrl;
//        this.reviewCount=reviewCount;
//        this.buySatisfy=buySatisfy;
    }


    public Products toEntity(){
        return Products.builder()

//                .buySatisfy(this.buySatisfy)
                .detailPageUrl(this.detailPageUrl)
//                .productCode(this.productCode)
//                .productImage(this.productImage)

                .productPrice(this.productPrice)
                .productName(this.productName)
//                .rate(this.rate)
//                .reviewCount(this.reviewCount)
//                .seller(this.seller)

                .build();
    }
}
