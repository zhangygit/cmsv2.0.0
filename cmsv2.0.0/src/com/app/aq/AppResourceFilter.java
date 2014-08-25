package com.app.aq;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.cms.web.FrontUtils;
/**
 * 资源过滤器，主要获取当前的资源路径
 * @author zy
 *
 */
public class AppResourceFilter extends OncePerRequestFilter{
	private static Log logger = LogFactory.getLog(AppResourceFilter.class);
	//key=应用id,value=放在request中的属性名称
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		setCmsBaseUrl(request);
		filterChain.doFilter(request, response);
	}
	
	private void setCmsBaseUrl(HttpServletRequest request) {
		request.setAttribute(FrontUtils.SITE_SERVER, request.getServerName());
		request.setAttribute(FrontUtils.BASE_HREF, request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/" );
		request.setAttribute(FrontUtils.BASE, request.getContextPath() );
	}

	
	
}
