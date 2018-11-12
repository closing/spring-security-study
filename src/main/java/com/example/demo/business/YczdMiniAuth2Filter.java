package com.example.demo.business;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

public class YczdMiniAuth2Filter extends AbstractAuthenticationProcessingFilter {
	// ~ Static fields/initializers
	// =====================================================================================

	public static final String YCZD_AUTH2_MINI = "code";

	private String codeId = YCZD_AUTH2_MINI;
	private boolean postOnly = true;

	//
	// ~ Constructors
	// ===================================================================================================

	public YczdMiniAuth2Filter() {
		super(new AntPathRequestMatcher("/login", "POST"));
	}

	@Override
	protected boolean requiresAuthentication(HttpServletRequest request,
			HttpServletResponse response) {

		boolean result = super.requiresAuthentication(request, response);
		if (result) {
			if (null != request.getParameter(codeId)) {
				return true;
			}
		}
		return false;

	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: " + request.getMethod());
		}

		String codeId = obtainCodeId(request);

		if (codeId == null) {
			codeId = "";
		}

		codeId = codeId.trim();

		YczdMiniAuth2Token authRequest = new YczdMiniAuth2Token(
				codeId, null);

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);

	}

	protected String obtainCodeId(HttpServletRequest request) {
		return request.getParameter(codeId);
	}

	protected void setDetails(HttpServletRequest request,
			YczdMiniAuth2Token authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

	public void setCodeIdParameter(String codeIdParameter) {
		Assert.hasText(codeIdParameter, "CodeId parameter must not be empty or null");
		this.codeId = codeIdParameter;
	}

	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public final String getCodeIdParameter() {
		return codeId;
	}

}
