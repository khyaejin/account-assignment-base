package com.example.account.member.controller;

import com.example.account.member.dto.LogInDto;
import com.example.account.member.dto.MemberCreateDto;
import com.example.account.member.service.MemberService;
import com.example.account.util.response.CustomApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    //회원가입
    @PostMapping("/sign-up")
    public ResponseEntity<CustomApiResponse<?>> signUp(
            @Valid @RequestBody MemberCreateDto.Req req) {
            ResponseEntity<CustomApiResponse<?>> result = memberService.createMember(req);
            return result;
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<CustomApiResponse<?>> logIn(@RequestBody LogInDto.Req req) {
        return memberService.logIn(req);
    }


    //회원탈퇴
    @DeleteMapping("/withdraw/{userId}")
    public ResponseEntity<CustomApiResponse<?>> deleteMember(@PathVariable("userId") String userId) {
        return memberService.withdraw(userId);
    }

}
