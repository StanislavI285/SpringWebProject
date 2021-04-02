package softuni.unisports.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class LogInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory
            .getLogger(LogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
//        //TODO uncomment
//        long startTime = System.currentTimeMillis();
//        logger.info("Request URL::" + request.getRequestURL().toString()
//                + ":: Start Time=" + System.currentTimeMillis());
//        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    //@Override
//    public void postHandle(HttpServletRequest request,
//                           HttpServletResponse response, Object handler,
//                           ModelAndView modelAndView) {
//      logger.info("Request URL::" + request.getRequestURL().toString()
//                + " Sent to Handler :: Current Time=" + System.currentTimeMillis());
//    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex) {
//        //TODO uncomment
//        long startTime = (Long) request.getAttribute("startTime");
//        logger.info("Request URL::" + request.getRequestURL().toString()
//                + ":: End Time=" + System.currentTimeMillis());
//        logger.info("Request URL::" + request.getRequestURL().toString()
//                + ":: Time Taken=" + (System.currentTimeMillis() - startTime));
    }
}
