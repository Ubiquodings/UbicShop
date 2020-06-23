package com.ubic.shop.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubic.shop.dto.UserActionKafkaRequestDto;
import com.ubic.shop.es_repository.UserAction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
@Slf4j
public class UserActionConsumer {

    private final ObjectMapper objectMapper;
    private final ElasticsearchRestTemplate esTemplate; // sb version up 2.2.x 로 새로 등장 ?


    @KafkaListener(topics = {"ubic-shop-test"}, containerFactory = "defaultKafkaListenerContainerFactory")
    public void onUserAction(ConsumerRecord<String, String> consumerRecord) throws JsonProcessingException {

        log.info("\nubic-shop-test :: ConsumerRecord : {} ", consumerRecord.value()); //ok
        // value 는 String >> json ?
        UserActionKafkaRequestDto received = objectMapper.readValue(consumerRecord.value(), UserActionKafkaRequestDto.class);
        log.info("\nubic-shop-test :: ObjectMapper : {} ", received ); //ok
        String actionType = received.getActionType();

        long score = 0;
        switch (actionType){
            case "click":
                score = 1;
                break;
            case "cart":
                score = 3;
                break;
            case "order":
                score = 5;
                break;
        }

        // 기존 es 객체 가져와야 한다 TODO null 체크해야 한다! ?  또한 만약 새로운 고객이면 .. ?
        String jsonMapValue = esTemplate.queryForObject(
                        GetQuery.getById(received.getUserid().toString()),
                        String.class);
        Map<Long,Long> map = objectMapper.readValue(jsonMapValue, new TypeReference<Map<Long,Long>>(){});

        // score update
        map.put(received.getCategoryId(), map.get(received.getCategoryId())+score);


        // 엘라스틱 서치에 저장 : Map to json
        String updatedJsonValue = objectMapper.writeValueAsString(map);

        // 인덱스는 직접 생성했다

        // 문서 추가
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(received.getUserid().toString()) // _id
                .withObject(updatedJsonValue) // list string
                .build();
        log.info("\n" + esTemplate.index(indexQuery) + "\n");

    }

}
