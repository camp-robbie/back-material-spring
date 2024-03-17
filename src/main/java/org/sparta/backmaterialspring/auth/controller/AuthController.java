package org.sparta.backmaterialspring.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.sparta.backmaterialspring.auth.dto.CreateUserDto;
import org.sparta.backmaterialspring.auth.dto.SignupResponseDto;
import org.sparta.backmaterialspring.auth.entity.TokenType;
import org.sparta.backmaterialspring.auth.jwt.JwtProvider;
import org.sparta.backmaterialspring.auth.service.AuthService;
import org.sparta.backmaterialspring.auth.service.TokenBlackListService;
import org.sparta.backmaterialspring.auth.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final JwtProvider jwtProvider;
    private final AuthService authService;
    private final UserService userService;
    private final TokenBlackListService tokenBlackListService;

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody CreateUserDto createUserDto) {
        SignupResponseDto responseDto = userService.signup(createUserDto);
        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "로그아웃")
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        tokenBlackListService.addToBlacklist(
                jwtProvider.getJwtFromHeader(request, TokenType.ACCESS),
                jwtProvider.getJwtFromHeader(request, TokenType.REFRESH)
        );
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
