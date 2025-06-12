package com.borangalleri.service;

import com.borangalleri.dto.AuthRequest;
import com.borangalleri.dto.AuthResponse;
import com.borangalleri.dto.DtoUser;
import com.borangalleri.dto.RefreshTokenRequest;

public interface IAuthenticationService {
    DtoUser register(AuthRequest input);

    public AuthResponse authenticate(AuthRequest input);

    public AuthResponse refreshToken(RefreshTokenRequest input);
}
