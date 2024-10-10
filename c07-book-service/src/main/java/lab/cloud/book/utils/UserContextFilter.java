package lab.cloud.book.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserContextFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(UserContextFilter.class);

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) servletRequest;

		UserContextHolder.getContext()
			.setCorrelationId(req.getHeader(UserContext.CORRELATION_ID));
		UserContextHolder.getContext()
			.setUserId(req.getHeader(UserContext.USER_ID));
		UserContextHolder.getContext()
			.setAuthToken(req.getHeader(UserContext.AUTH_TOKEN));
		UserContextHolder.getContext()
			.setAuthorId(req.getHeader(UserContext.AUTHOR_ID));

        logger.debug("UserContextFilter Correlation id: {}", 
        		UserContextHolder.getContext().getCorrelationId());

		filterChain.doFilter(req, servletResponse);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
