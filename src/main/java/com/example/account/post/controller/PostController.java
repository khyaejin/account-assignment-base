package com.example.account.post.controller;

import com.example.account.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/member")
@RequiredArgsConstructor
public class PostController {

    //회원가입
    @PostMapping("/sign-up")
    public ResponseEntity<CustomApiResponse<?>> signUp(){
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<CustomApiResponse<?>> login(){
    }

    //로그인
    @DeleteMapping("/withdraw/{userId}")
    public ResponseEntity<CustomApiResponse<?>> login(){
    }
}
