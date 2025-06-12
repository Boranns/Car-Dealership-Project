package com.borangalleri.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.borangalleri.dto.AuthRequest;
import com.borangalleri.dto.AuthResponse;
import com.borangalleri.dto.DtoUser;
import com.borangalleri.exception.BaseException;
import com.borangalleri.exception.ErrorMessage;
import com.borangalleri.exception.MessageType;
import com.borangalleri.jwt.JWTservice;
import com.borangalleri.model.RefreshToken;
import com.borangalleri.model.User;
import com.borangalleri.repository.RefreshtokenRepository;
import com.borangalleri.repository.UserRepository;
import com.borangalleri.service.IAuthenticationService;


@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JWTservice jwtService;

    @Autowired
    private RefreshtokenRepository refreshtokenRepository;

    private User createUser(AuthRequest input) {
        User user = new User();
        user.setCreateTime(LocalDateTime.now());
        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        return user;
    }

    private RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setCreateTime(LocalDateTime.now());

        
        Date expiredDate = Date.from(LocalDateTime.now()
                .plusHours(4)
                .atZone(ZoneId.systemDefault())
                .toInstant());

        refreshToken.setExpiredDate(expiredDate);
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        return refreshToken;
    }

    @Override
    public DtoUser register(AuthRequest input) {
        User savedUser = userRepository.save(createUser(input));
        DtoUser dtoUser = new DtoUser();
        BeanUtils.copyProperties(savedUser, dtoUser);
        return dtoUser;
    }

    @Override
    public AuthResponse authenticate(AuthRequest input) {
        try {
            Optional<User> optUser = userRepository.findByUsername(input.getUsername());

            if (optUser.isEmpty()) {
                throw new BaseException(new ErrorMessage(
                        MessageType.INVALID_USERNAME_OR_PASSWORD, "User not found"));
            }

            User user = optUser.get();

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword());

            authenticationProvider.authenticate(authenticationToken);

            String accessToken = jwtService.generateToken(user);

            RefreshToken savedRefreshToken = refreshtokenRepository.save(createRefreshToken(user));

            return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());

        } catch (AuthenticationException e) {
            throw new BaseException(new ErrorMessage(
                    MessageType.INVALID_USERNAME_OR_PASSWORD, "Invalid credentials"));
        }
    }

    public boolean isValidRefreshToken(Date expireDate){
        return new Date().before(expireDate);

    }

    @Override
    public AuthResponse refreshToken(com.borangalleri.dto.RefreshTokenRequest input) {
        Optional<RefreshToken> optRefreshToken = refreshtokenRepository.findByRefreshToken(input.getRefreshToken());
        if (optRefreshToken.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, 
                                                                input.getRefreshToken()));
        }

        if (!isValidRefreshToken(optRefreshToken.get().getExpiredDate())) {
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED, 
                                                                input.getRefreshToken()));
        }

        User user = optRefreshToken.get().getUser();
        String accessToken = jwtService.generateToken(user);
        RefreshToken savedRefreshToken = refreshtokenRepository.save(createRefreshToken(user));
       
        return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
    }
}
