package com.example.account.member.dto;

import com.example.account.domain.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

public class MemberCreateDto {

    @Getter
    @Setter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor
    @Builder
    public static class Req{
        @NotBlank(message = "이메일은 비어있을 수 없습니다")
        private String email;

        @NotBlank(message = "전화번호는 비어있을 수 없습니다")
        private String phone;

        @NotBlank(message = "아이디는 비어있을 수 없습니다")
        private String user_id;
        
        private String password;
        
        private String password_check;

        public Member toEntity(){
            return Member.builder()
                    .email(email)
                    .phone(phone)
                    .userId(user_id)
                    .password(password)
                    //패스워드 확인?
                    .build();
        }
    }
    //게시글 작성 :memberId, updateAt
    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreateMember {
        private Long memberId;
        private LocalDateTime updateAt;

        public CreateMember(Long memberId, LocalDateTime updateAt) {
            this.memberId = memberId;
            this.updateAt = updateAt;
        }
    }
}
