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
        
        // private String password_check; -> 삭제
        // 비밀번호와 비밀번호 확인 한의 일치 여부는 프론트에서 확인 후 백엔드로는 비밀번호 값만 넘어옴
        // 유효성 검사는 대부분 프론트에서 한 후 백엔드로 넘어옴. 이게 비용적 측면에서 유리하기 때문

        public Member toEntity(){
            return Member.builder()
                    .email(email)
                    .phone(phone)
                    .userId(userId)
                    .password(password)
                    //패스워드 확인? -> 프론트측에서 진행
                    .build();
        }
    }
}
