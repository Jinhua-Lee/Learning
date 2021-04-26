package cn.designpattern.chain;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 职责链模式 -> 过滤器案例
 *
 * @author Jinhua
 * @date 2021/3/10下午11:18
 */
public class EncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setCharacterEncoding(Charset.defaultCharset().name());

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setContentType("text/html; charset=UTF-8");

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
