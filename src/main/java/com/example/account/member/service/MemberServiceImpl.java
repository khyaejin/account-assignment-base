package com.example.account.member.service;

import com.example.account.domain.Member;
import com.example.account.member.dto.MemberCreateDto;
import com.example.account.member.repository.MemberRepository;
import com.example.account.util.response.CustomApiResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Builder
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    //회원가입
    @Override
    public ResponseEntity<CustomApiResponse<?>> createMember(MemberCreateDto.Req req){
        Member member = req.toEntity();

        Member savedMember = memberRepository.save(member);

        //생성된 게시글의 정보를 포함한 응답 반환
        //MemberCreateDto.CreateMember createPostResponse = new MemberCreateDto.CreateMember(savedMember.getId(), savedMember.getUpdateAt());
        CustomApiResponse<MemberCreateDto.CreateMember> res = CustomApiResponse.createSucsess(HttpStatus.OK.value(), null, "회원가입에 성공하셨습니다.");
        return ResponseEntity.ok(res);
    }
}
