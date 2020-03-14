package root.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserLogDto { // 간단하게 2개만 해보자! html 에서 보내는 데이터 포맷
    String type; // click,order,shoplist
    String productName; // 상품이름
}
