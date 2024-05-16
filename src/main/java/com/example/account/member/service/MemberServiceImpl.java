package com.example.account.member.service;

import com.example.account.domain.Member;
import com.example.account.member.dto.LogInDto;
import com.example.account.member.dto.MemberCreateDto;
import com.example.account.member.repository.MemberRepository;
import com.example.account.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public ResponseEntity<CustomApiResponse<?>> createMember(MemberCreateDto.Req req) {
        Member member = req.toEntity();
        Member savedMember = memberRepository.save(member);
        CustomApiResponse<MemberCreateDto.CreateMember> responseBody = CustomApiResponse.createSucsess(HttpStatus.CREATED.value(), null, "회원가입에 성공하셨습니다.");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @Override
    public ResponseEntity<CustomApiResponse<?>> logIn(LogInDto.Req req) {
        Optional<Member> optionalMember = memberRepository.findByUserId(req.getUserId());
        if (optionalMember.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CustomApiResponse.createFailWithoutData(HttpStatus.NOT_FOUND.value(), "존재하지 않는 회원입니다."));
        }

        Member member = optionalMember.get();
        if (!member.getPassword().equals(req.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CustomApiResponse.createFailWithoutData(HttpStatus.UNAUTHORIZED.value(), "비밀번호가 일치하지 않습니다."));
        }

        return ResponseEntity.status(HttpStatus.OK).body(CustomApiResponse.createSucsess(HttpStatus.OK.value(), null, "로그인에 성공하였습니다."));
    }

    @Override
    public ResponseEntity<CustomApiResponse<?>> withdraw(String userId) {
        Optional<Member> optionalMember = memberRepository.findByUserId(userId);
        if (optionalMember.isEmpty()) {
            String msg = "id가" + userId + "인 회원은 존재하지 않습니다.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CustomApiResponse.createFailWithoutData(HttpStatus.NOT_FOUND.value(), msg));
        }

        Member member = optionalMember.get();
        memberRepository.delete(member);
        return ResponseEntity.status(HttpStatus.OK).body(CustomApiResponse.createSucsess(HttpStatus.OK.value(), null, "회원이 정상적으로 탈퇴되었습니다."));
    }
}
