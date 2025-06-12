package com.borangalleri.controller;
import com.borangalleri.dto.AuthRequest;
import com.borangalleri.dto.AuthResponse;
import com.borangalleri.dto.DtoUser;
import com.borangalleri.dto.RefreshTokenRequest;

public interface IREstAuthenticationController {
    public RootEntity<DtoUser> register(AuthRequest input);

    public RootEntity<AuthResponse> authenticate(AuthRequest input);

    public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);
}
