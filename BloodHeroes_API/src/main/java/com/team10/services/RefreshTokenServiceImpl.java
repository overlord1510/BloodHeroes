package com.team10.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.team10.entity.RefreshToken;
import com.team10.entity.User;
import com.team10.repository.RefreshTokenRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements IRefreshTokenService {
	private final RefreshTokenRepository refreshTokenRepository;

	@Value("${cookie.expiration}")
	private long EXPIRATION;

	@Override
	public RefreshToken getRefreshTokenByUser(User user) {
		Optional<RefreshToken> byUser_id = refreshTokenRepository.findByUser_id(user.getId());
		return byUser_id.isPresent() ? byUser_id.get() : null;
	}

	@Override
	public RefreshToken getRefreshTokenByToken(String token) {
		Optional<RefreshToken> byToken = refreshTokenRepository.findByToken(token);
		return byToken.isPresent() ? byToken.get() : null;
	}

	@Transactional
	@Override
	public void saveRefreshToken(String token, User user) {
		//@formatter:off
		refreshTokenRepository.save(RefreshToken.builder()
				.user(user)
				.token(token)
				.expiration(new Date(System.currentTimeMillis()+EXPIRATION*1000))
				.build());
		//@formatter:on
	}

	@Transactional
	@Override
	public void deleteRefreshToken(User user) {
		refreshTokenRepository.deleteByUser_id(user.getId());
	}
}
