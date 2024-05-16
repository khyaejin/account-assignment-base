package com.example.account.member.service;

import com.example.account.domain.Member;
import com.example.account.member.dto.LogInDto;
import com.example.account.member.dto.MemberCreateDto;
import com.example.account.member.repository.MemberRepository;
import com.example.account.util.response.CustomApiResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Builder
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    //회원가입
    @Override
    public ResponseEntity<CustomApiResponse<?>> createMember(MemberCreateDto.Req req) {
        Member member = req.toEntity();

        Member savedMember = memberRepository.save(member);

        //생성된 게시글의 정보를 포함한 응답 반환
        //MemberCreateDto.CreateMember createPostResponse = new MemberCreateDto.CreateMember(savedMember.getId(), savedMember.getUpdateAt());
        CustomApiResponse<MemberCreateDto.CreateMember> res = CustomApiResponse.createSucsess(HttpStatus.CREATED.value(), null, "회원가입에 성공하셨습니다.");
        return ResponseEntity.status(HttpStatus.CREATED).body(res);

    }

    //로그인
    @Override
    public ResponseEntity<CustomApiResponse<?>> logIn(LogInDto.Req req) {
        Optional<Member> optionalMember = memberRepository.findByUserId(req.getUserId());
        //계정이 없는 경우
        if (optionalMember.isEmpty()) {
            CustomApiResponse<?> res = CustomApiResponse.createFailWithoutData(HttpStatus.NOT_FOUND.value(), "존재하지 않는 회원입니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        Member member = optionalMember.get();
        // 비밀번호가 일치하지 않는 경우
        if (!member.getPassword().equals(req.getPassword())) {
            CustomApiResponse<Object> responseBody = CustomApiResponse.createFailWithoutData(HttpStatus.UNAUTHORIZED.value(), "비밀번호가 일치하지 않습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
        }

        //성공한 경우
        CustomApiResponse<Object> res = CustomApiResponse.createSucsess(HttpStatus.OK.value(), null, "로그인에 성공하였습니다.");
        return ResponseEntity.status(HttpStatus.OK).body(res);

    }

    //회원탈퇴
    @Override
    public ResponseEntity<CustomApiResponse<?>> withdraw(String userId) {
        Optional<Member> optionalMember = memberRepository.findByUserId(userId);

        //계정이 없는 경우
        if (optionalMember.isEmpty()) {
            String msg = "id가" + userId + "인 회원은 존재하지 않습니다.";
            CustomApiResponse<?> res = CustomApiResponse.createFailWithoutData(HttpStatus.NOT_FOUND.value(), msg);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        //성공한 경우

        Member member = optionalMember.get();
        memberRepository.delete(member);
        CustomApiResponse<?> res = CustomApiResponse.createSucsess(HttpStatus.OK.value(), null, "회원이 정상적으로 탈퇴되었습니다.");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
