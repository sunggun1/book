package com.example.demo.service;

import com.example.demo.constant.RoleType;
import com.example.demo.dto.user.request.UserCreateRequestDto;
import com.example.demo.dto.user.response.UserCreateResponseDto;
import com.example.demo.dto.user.response.UserSignInRequestDto;
import com.example.demo.dto.user.response.UserTokenResponseDto;
import com.example.demo.entity.User;
import com.example.demo.global.customException.UserCustomException;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(UserCreateRequestDto userCreateRequestDto){
        if(userCreateRequestDto == null || userCreateRequestDto.getPassword() == null){
            throw UserCustomException.NOT_FOUND_PASSWORD;
        }

        if(userRepository.existsUserByEmail(userCreateRequestDto.getEmail())){
            throw UserCustomException.ALEADY_EMAIL_EXIST;
        }

        User user = User.builder()
                .email(userCreateRequestDto.getEmail())
                .password(passwordEncoder.encode(userCreateRequestDto.getPassword()))
                .type(RoleType.USER)
                .build();

        return userRepository.save(user);
    }

    public User createAdmin(UserCreateRequestDto userCreateRequestDto){
        if(userCreateRequestDto == null || userCreateRequestDto.getPassword() == null){
            throw UserCustomException.NOT_FOUND_PASSWORD;
        }

        if(userRepository.existsUserByEmail(userCreateRequestDto.getEmail())){
            throw UserCustomException.ALEADY_EMAIL_EXIST;
        }

        User user = User.builder()
                .email(userCreateRequestDto.getEmail())
                .password(passwordEncoder.encode(userCreateRequestDto.getPassword()))
                .type(RoleType.ADMIN)
                .build();

        return userRepository.save(user);
    }

    public User signIn(UserSignInRequestDto userSignInRequestDto, PasswordEncoder passwordEncoder){
        User user = userRepository.findUserByEmail(userSignInRequestDto.getEmail()).orElseThrow(() -> UserCustomException.NOT_FOUND_USER);
        if(passwordEncoder.matches(userSignInRequestDto.getPassword(), user.getPassword())){
            return user;
        }else{
            throw UserCustomException.NOT_MATCH_PASSWORD;
        }
    }
}
