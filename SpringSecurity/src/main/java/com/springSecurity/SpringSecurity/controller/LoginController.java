package com.springSecurity.SpringSecurity.controller;

import com.springSecurity.SpringSecurity.model.Customer;
import com.springSecurity.SpringSecurity.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){
        Customer savedCustomer=null;
        ResponseEntity response=null;
        try{
            String encodedPwd=bCryptPasswordEncoder.encode(customer.getPwd());
            customer.setPwd(encodedPwd);
            savedCustomer=customerRepository.save(customer);
            if(savedCustomer.getId()>0){
                response=ResponseEntity.status(HttpStatus.CREATED).body("Given user details are successfully registered");
            }
        }catch(Exception ex){
            response=ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An exception occured due to"+ex.getMessage());
        }
        return response;
    }
}
