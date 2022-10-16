package com.example.studentapp.controller;

import com.example.studentapp.dto.AdmissionRequest;
import com.example.studentapp.dto.ServerResponse;
import com.example.studentapp.manager.StudentManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @Author Nitesh Poudel
 */
@Slf4j
@RestController
@RequestMapping(value = "student")
public class StudentController {

    @Autowired
    private StudentManager studentManager;

    @PostMapping(value = "admit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> admission(@RequestBody AdmissionRequest admissionRequest) {

        String referenceNumber = studentManager.admitStudent(admissionRequest);
        System.out.println("Admission Success. Your reference number is : " + referenceNumber);
        return ResponseEntity.ok(referenceNumber);
    }

    @GetMapping(value="get")
    public String get() {
        return "running";
    }
}
