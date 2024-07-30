package com.team10.services;

import com.team10.entity.RefreshToken;
import com.team10.entity.User;

public interface IRefreshTokenService {

	public RefreshToken getRefreshTokenByUser(User user);
	public RefreshToken getRefreshTokenByToken(String token);
	public void saveRefreshToken(String token,User user);
	public void deleteRefreshToken(User user);
	
}
