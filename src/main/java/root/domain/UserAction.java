package root.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserAction {

    /*User user;*/
    String actionType;
    String item; // 임시로 String, 상품 이름
    String time; // 임시로. 받는 시간 string. 아마 js 랑 java 랑 time 형식이 다를거다
}
