package com.example.account.member.dto;

import com.example.account.domain.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;

public class MemberCreateDto {

    @Getter
    @Setter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor
    @Builder
    public static class Req{
        @Email(message = "이메일 형식을 맞춰주세요.")
        @NotBlank(message = "이메일은 비어있을 수 없습니다")
        private String email;

        @Pattern(regexp = "^010\\d{3,4}\\d{4}$", message = "전화번호 형식을 맞춰주세요.")
        @NotBlank(message = "전화번호는 비어있을 수 없습니다")
        private String phone;

        @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "아이디는 영문자와 숫자만 포함할 수 있습니다.")
        @NotBlank(message = "아이디는 비어있을 수 없습니다")
        private String userId;
        
        private String password;
        
        private String password_check;

        public Member toEntity(){
            return Member.builder()
                    .email(email)
                    .phone(phone)
                    .userId(userId)
                    .password(password)
                    //패스워드 확인?
                    .build();
        }
    }
}
