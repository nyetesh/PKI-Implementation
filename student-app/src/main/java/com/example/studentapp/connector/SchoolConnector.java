package com.example.studentapp.connector;

import com.example.studentapp.dto.RequestWrapper;
import com.example.studentapp.dto.RestTemplateResponse;
import com.example.studentapp.dto.SchoolResponse;
import com.example.studentapp.dto.ServerResponse;
import com.example.studentapp.http.RestTemplateHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.net.URL;

/**
 * @Author Nitesh Poudel
 */
@Slf4j
@Component
public class SchoolConnector extends RestTemplateHelper {

    public <T> ServerResponse request(T requestData, ParameterizedTypeReference typeReference) {

        String schoolURL = "http://localhost:8080/school/admission";
        ServerResponse serverResponse = new ServerResponse();

        log.info("Socket notification url : {} with body : {}", requestData);

        RestTemplateResponse restTemplateResponse = doPost(
                schoolURL,
                buildEntity(requestData)
                , typeReference);

        if (restTemplateResponse.isObtained()) {

            SchoolResponse schoolResponse = (SchoolResponse) restTemplateResponse.getObj();
            serverResponse.setSuccess(true);
            serverResponse.setObject(schoolResponse);
            serverResponse.setCode("0");

        } else {
            //exception case
            serverResponse.setSuccess(false);
            serverResponse.setCode("2");
            serverResponse.setMessage(restTemplateResponse.getErrorMessage());
        }
        return serverResponse;
    }



}
