package root.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;
import root.domain.Products;
import root.service.ProductsService;
import root.web.dto.ProductListResponseDto;
import root.web.dto.ProductsResponseDto;
import root.web.dto.ProductsSaveRequestDto;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductsApiController {

    private final ProductsService productsService;

    // home
//    @GetMapping("/") // index controller 에 이미 있다
//    public String home(){
//        return "home";
//    }

    // create
    @PutMapping("/api/v1/products")
    public Long save(@RequestBody ProductsSaveRequestDto requestDto){
        return productsService.save(requestDto); // return Id
        // ProductsSaveRequestDto 에 @Builder 가 없으면 동작 안하나 ? 아닌듯
    }

    // get
//    @GetMapping("/api/v1/products/{id}") // 우리 서비스에선.. 조회가 아니라 끌고오기 ?
//    public ProductsResponseDto findById(@PathVariable Long id){
//        return productsService.findById(id);
//    }

    /*"api/v1/products?keyword={keyword}"*/
//    http://localhost:8080/api/v1/products?keyword=%EC%88%98%EA%B1%B4
    @GetMapping("/api/v1/products/{keyword}")
    public String/*List<ProductsResponseDto>*/ list(@PathVariable String keyword) throws ParserConfigurationException, SAXException, IOException { // 키워드 받아와야하는데
        productsService.list(keyword);
        return "";
    }

    @PostMapping("/api/v1/products/cart")
    public Long save_cart(@RequestBody ProductsSaveRequestDto requestDto){
        return productsService.save(requestDto); // return Id
    }

}
