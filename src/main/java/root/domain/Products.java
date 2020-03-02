package root.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Products {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

//    @Column
//    private Long productCode;

    @Column
    private Long productPrice;

    @Column
    private String productName;

//    @Column
//    private String productImage;
//
//    @Column
//    private String seller;
//
//    @Column
//    private Long rate;
//
    @Column
    private String detailPageUrl;
//
//    @Column
//    private Long reviewCount;
//
//    @Column
//    private Long buySatisfy;

    @Builder
    public Products(
//            Long productCode,
            Long productPrice, String productName,
//            String productImage,
//            String seller,Long rate,
            String detailPageUrl
//            Long reviewCount,
//            Long buySatisfy
    ){
//        this.productCode=productCode;
        this.productPrice=productPrice;
        this.productName=productName;
        this.detailPageUrl=detailPageUrl;
//        this.productImage=productImage;
//        this.seller=seller;
//        this.rate=rate;
//        this.detailPageUrl=detailPageUrl;
//        this.reviewCount=reviewCount;
//        this.buySatisfy=buySatisfy;
    }
}
