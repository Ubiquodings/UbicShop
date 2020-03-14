package root.web;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import root.config.auth.LoginUser;
import root.config.auth.dto.SessionUser;
import root.service.ProductsService;
import root.web.dto.ProductXmlParse;

import javax.servlet.http.HttpSession;

//@NoArgsConstructor
@RequiredArgsConstructor
@Controller
public class IndexController {

    @Autowired
    private final ProductsService productsService;
//    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {

        // TODO; service 에서 list 가져오기
        if (productsService.isExistProductList()) {
            model.addAttribute("productList", productsService.getProductList());
        }

//        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null){
            model.addAttribute("userName", user.getName());
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
