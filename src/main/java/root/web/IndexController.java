package root.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import root.service.ProductsService;
import root.web.dto.ProductXmlParse;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final ProductsService productsService;

    @GetMapping("/")
    public String index(Model model) {

        // TODO; service 에서 list 가져오기
        if (productsService.isExistProductList()) {
            model.addAttribute("productList", productsService.getProductList());
        }

        return "index";
    }

    @GetMapping("/products/{id}")
    public String detail(Model model, @PathVariable int id){
        ProductXmlParse product = productsService.getProductList().get(id);
        model.addAttribute("product", product);
        return "detail";
    }

//    @PostMapping("/")
//    public String list() {
//        // TODO: 상품 api 요청 코드 작성
//
//
//        return "index";
//    }

}
