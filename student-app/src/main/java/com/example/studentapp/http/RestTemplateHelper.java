package com.example.studentapp.http;

import com.example.studentapp.dto.RestTemplateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @Author Nitesh Poudel
 */
@Slf4j
public class RestTemplateHelper {

    @Autowired
    private RestTemplate restTemplate;

    public <T> RestTemplateResponse doPost(String url, HttpEntity httpEntity, ParameterizedTypeReference<T> responseType) {
        log.info("Triggering URL : {} with body : {}", url, httpEntity.getBody());
        RestTemplateResponse restTemplateResponse = new RestTemplateResponse<>();

        try {
            ResponseEntity response = restTemplate
                    .exchange(
                            url,
                            HttpMethod.POST,
                            httpEntity,
                            responseType);

            log.debug("Http response code : " + response.getStatusCode().name()+" ,Response : "+response.getBody());

            restTemplateResponse.setHttpStatus(response.getStatusCode());
            restTemplateResponse.setObtained(true);
            restTemplateResponse.setObj(response.getBody());

            return restTemplateResponse;
        } catch (Exception e) {
            log.error("error : {}", e.getMessage());
            restTemplateResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            restTemplateResponse.setErrorMessage("Exception");
            return restTemplateResponse;
        }
    }

    public static <T> HttpEntity<T> buildEntity(T obj) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity<>(obj, headers);
        return entity;
    }


}
