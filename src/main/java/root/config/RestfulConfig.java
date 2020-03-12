package root.config;


//import lombok.Value;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestfulConfig {

//    @Value("${restTemplate.factory.readTimeout}")
//    private int READ_TIMEOUT;
//
//    @Value("${restTemplate.factory.connectTimeout}")
//    private int CONNECT_TIMEOUT;
//
//    @Value("${restTemplate.httpClient.maxConnTotal}")
//    private int MAX_CONN_TOTAL;
//
//    @Value("${restTemplate.httpClient.maxConnPerRoute}")
//    private int MAX_CONN_PER_ROUTE;

    @Bean
    public RestTemplate restTemplate(){
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        factory.setReadTimeout(READ_TIMEOUT);
//        factory.setConnectTimeout(CONNECT_TIMEOUT);
//
//        HttpClient httpClient = HttpClientBuilder.create()
//                .setMaxConnTotal(MAX_CONN_TOTAL)
//                .setMaxConnPerRoute(MAX_CONN_PER_ROUTE)
//                .build();



//        factory.setHttpClient(httpClient);
//
//        RestTemplate restTemplate = new RestTemplate(factory);


        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(200)
                .setMaxConnPerRoute(20)
                .build();
        httpRequestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(5))
                .setReadTimeout(Duration.ofSeconds(5))
                .requestFactory(() -> httpRequestFactory)
                .build();


        return restTemplate;
    }
}
