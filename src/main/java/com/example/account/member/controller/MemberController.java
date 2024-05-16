package com.example.account.member.controller;

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
    public ResponseEntity<CustomApiResponse<?>> login(@RequestParam Long userId, @RequestParam String password) {
        ResponseEntity<CustomApiResponse<?>> result = memberService.login(userId, password);
        return result;
    }


    //회원탈퇴
    @DeleteMapping("/withdraw/{userId}")
    public ResponseEntity<CustomApiResponse<?>> withdraw(@PathVariable Long userId) {
        ResponseEntity<CustomApiResponse<?>> result = memberService.withdraw(userId);
        return result;
    }

}
