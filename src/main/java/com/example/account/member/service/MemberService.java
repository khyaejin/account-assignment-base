package com.example.account.member.service;

import com.example.account.member.dto.MemberCreateDto;
import com.example.account.util.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    ResponseEntity<CustomApiResponse<?>> createMember(MemberCreateDto.Req req);
    ResponseEntity<CustomApiResponse<?>> login(Long userId, String password);
    ResponseEntity<CustomApiResponse<?>> withdraw(Long userId);

}
