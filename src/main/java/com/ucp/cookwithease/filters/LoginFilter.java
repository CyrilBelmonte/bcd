package com.ucp.cookwithease.filters;

import com.ucp.cookwithease.servlets.References;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;


public class LoginFilter implements Filter {
    private static final LinkedList<String> excludedPages = new LinkedList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludedPages.addLast(References.PATH_CSS);
        excludedPages.addLast(References.PATH_IMG);
        excludedPages.addLast(References.PATH_JS);

        excludedPages.addLast(References.VIEW_INDEX);
        excludedPages.addLast(References.VIEW_LOGIN);
        excludedPages.addLast(References.VIEW_REGISTER);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession();

        if (session.getAttribute("userSession") != null) {
            chain.doFilter(request, response);
            return;
        }

        int contextLength = request.getContextPath().length();
        String path = request.getRequestURI().substring(contextLength);

        boolean isExcluded = false;

        for (String page : excludedPages)
            isExcluded = isExcluded || path.startsWith(page);

        if (!isExcluded)
            response.sendRedirect(request.getContextPath() + References.VIEW_LOGIN);

        else
            chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
