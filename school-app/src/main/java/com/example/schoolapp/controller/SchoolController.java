package com.example.schoolapp.controller;

import com.example.schoolapp.dto.RequestWrapper;
import com.example.schoolapp.dto.SchoolRequest;
import com.example.schoolapp.dto.SchoolResponse;
import com.example.schoolapp.manager.SchoolManager;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerResponse;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.util.Map;

/**
 * @Author Nitesh Poudel
 */
@Slf4j
@RestController
@RequestMapping("school")
public class SchoolController {

    @Autowired
    private SchoolManager schoolManager;

    @PostMapping(value = "admission", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> admit(@RequestBody SchoolRequest schoolRequest){

        log.info("Request from student app, {} " + schoolRequest.toString());

        SchoolResponse schoolResponse = schoolManager.decryptRequest(schoolRequest);

        return ResponseEntity.ok(schoolResponse);
    }


}
