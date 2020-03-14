package root.web.dto;

import lombok.*;

@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class UserActionRequestDto {

    String user; // 임시로 세션 id 값 string.
    String actionType;
    String item; // 임시로 String, 상품 이름
//    String time; // 임시로. 받는 시간 string. 아마 js 랑 java 랑 time 형식이 다를거다


//    public UserActionRequestDto(String actionType,
//                                String item,
//                                String time){
//        this.actionType = actionType;
//        this.item = item;
//        this.time = time;
//    }

}
