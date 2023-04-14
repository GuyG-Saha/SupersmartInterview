package com.example.supersmart.controller;

import com.example.supersmart.dto.CustomResponse;
import com.example.supersmart.dto.Transaction;
import com.example.supersmart.entity.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class SupersmartController {
    @Autowired
    private Validator validator;

    @PostMapping("/validate")
    public ResponseEntity<CustomResponse> validate(@RequestBody Transaction transaction) {
        validator.setItems(transaction.getItems());
        return new ResponseEntity<>(new CustomResponse(validator.validate()), HttpStatus.OK);
    }

}
