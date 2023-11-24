package com.example.demo.controller;


import com.example.demo.dto.common.ResponseDto;
import com.example.demo.dto.user.request.UserCreateRequestDto;
import com.example.demo.dto.user.response.UserCreateResponseDto;
import com.example.demo.dto.user.response.UserSignInRequestDto;
import com.example.demo.dto.user.response.UserTokenResponseDto;
import com.example.demo.entity.User;
import com.example.demo.filter.TokenProvider;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final TokenProvider tokenProvider;

    @PostMapping("")
    public ResponseEntity<ResponseDto<UserCreateResponseDto>> createUser(@RequestBody UserCreateRequestDto userCreateRequestDto){
        try{
            User savedUser = userService.createUser(userCreateRequestDto);
            UserCreateResponseDto result = UserCreateResponseDto.of(savedUser);
            return new ResponseEntity<>(new ResponseDto<>(true, "success", result), HttpStatus.OK);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/signIn")
    public ResponseEntity<ResponseDto<UserTokenResponseDto>> authenticate(@RequestBody UserSignInRequestDto userSignInRequestDto){
        try{
            User signInUser = userService.signIn(userSignInRequestDto, passwordEncoder);
            final String token = tokenProvider.create(signInUser);
            final UserTokenResponseDto result = UserTokenResponseDto.of(token, signInUser.getId(), signInUser.getEmail());
            return new ResponseEntity<>(new ResponseDto<>(true, "success", result), HttpStatus.OK);
        }catch (Exception ex){
            throw ex;
        }
    }
}
