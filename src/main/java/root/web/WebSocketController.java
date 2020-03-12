package root.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import root.service.ProductsService;
import root.web.dto.UserActionRequestDto;

@Controller
public class WebSocketController {

    private Logger LOGGER = LoggerFactory.getLogger(WebSocketController.class);

    @MessageMapping("/action")
    @SendTo("/queue/message") /* User */
    public UserActionRequestDto info(@Payload UserActionRequestDto userAction) {
        LOGGER.error("\n" + userAction + "\n");
        return userAction;
    }

}
