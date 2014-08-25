package com.app.aq;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.app.common.context.AppVersion;

/**
 * 处理用户信息及用户的授权信息
 */
public class AppSecurityFilter extends OncePerRequestFilter{

		public AppSecurityFilter() {
			super();
		}

		@Override
		protected void doFilterInternal(HttpServletRequest request,
				HttpServletResponse response, FilterChain filterChain)
				throws ServletException, IOException {
			try {
				//把title放到request中
				request.setAttribute("title", null);
				
				

				request.setAttribute("VERSION", AppVersion.get());
				//当前服务器日期，页面可以直接获取${CURRENT_DATE}
				request.setAttribute("CURRENT_DATE", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				//当前服务器时间，页面可以直接获取${CURRENT_TIME}
				request.setAttribute("CURRENT_TIME", new SimpleDateFormat("HH:mm:ss").format(new Date()));
				filterChain.doFilter(request, response);
			} finally {
			}
		}

		

		
		
		
}
