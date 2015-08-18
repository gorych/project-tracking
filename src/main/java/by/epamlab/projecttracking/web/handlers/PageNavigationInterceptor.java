package by.epamlab.projecttracking.web.handlers;

import by.epamlab.projecttracking.web.AttributeConstants;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PageNavigationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        String pageQuery = request.getRequestURI() + "?" + request.getQueryString();

        HttpSession session = request.getSession();
        session.setAttribute(AttributeConstants.PREVIOUS_PAGE, pageQuery);

        super.postHandle(request, response, handler, modelAndView);
    }
}
