package com.team10.services;

import jakarta.servlet.http.HttpServletResponse;

public interface ICookieService {

	public void createCookie(String refreshToken,HttpServletResponse response);
	public void deleteCookie(HttpServletResponse response);
	
}
