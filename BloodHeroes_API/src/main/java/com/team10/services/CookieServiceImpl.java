package com.team10.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.Cookie.SameSite;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
@Service
public class CookieServiceImpl implements ICookieService {

	@Value("${cookie.expiration}")
	private long EXPIRATION;

	@Override
	public void createCookie(String refreshToken, HttpServletResponse response) {
		//@formatter:off
		 ResponseCookie cookie = ResponseCookie.from("rToken", refreshToken)
				.httpOnly(true)
				.sameSite(SameSite.NONE.toString())
				.secure(true)
				.path("/")
				.maxAge(EXPIRATION)
				.build();
		//@formatter:on
		response.addHeader("Set-Cookie", cookie.toString());
	}

	@Override
	public void deleteCookie(HttpServletResponse response) {
		//@formatter:off
		 ResponseCookie cookie = ResponseCookie.from("rToken", null)
				.httpOnly(true)
				.sameSite(SameSite.NONE.toString())
				.secure(true)
				.path("/")
				.maxAge(0)
				.build();
		//@formatter:on
		response.addHeader("Set-Cookie", cookie.toString());
	}

}
