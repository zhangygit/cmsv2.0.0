package com.app.common.security.rememberme;

@SuppressWarnings("serial")
public class InvalidCookieException extends RememberMeAuthenticationException {
	public InvalidCookieException() {
	}

	public InvalidCookieException(String msg) {
		super(msg);
	}
}
