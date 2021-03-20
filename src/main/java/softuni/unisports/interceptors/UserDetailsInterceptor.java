package softuni.unisports.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.SmartView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class UserDetailsInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(UserDetailsInterceptor.class);

//    @Override
//    public boolean preHandle(HttpServletRequest request,
//                             HttpServletResponse response, Object object) throws Exception {
//        if (isUserLogged()) {
//            addToModelUserDetails(request.getSession());
//        }
//        return true;
//    }

    @Override
    public void postHandle(
            HttpServletRequest req,
            HttpServletResponse res,
            Object o,
            ModelAndView model) throws Exception {

        if (model != null && !isRedirectView(model)) {
//            if (isUserLogged()) {
                addToModelUserDetails(model);
//            }
        }
    }


    public static boolean isUserLogged() {
        //throws NullPointerException
        try {
            return !SecurityContextHolder.getContext().getAuthentication()
                    .getName().equals("anonymousUser");
        } catch (Exception e) {
            return false;
        }

    }

    public static boolean isRedirectView(ModelAndView mv) {
        String viewName = mv.getViewName();
        if (viewName.startsWith("redirect:/")) {
            return true;
        }
        View view = mv.getView();
        return (view != null && view instanceof SmartView
                && ((SmartView) view).isRedirectView());
    }

    private void addToModelUserDetails(ModelAndView model) {
        log.info("=============== addToModel =========================");


        if (isUserLogged()) {
            String loggedUsername = SecurityContextHolder.getContext()
                    .getAuthentication().getName();
            model.addObject("loggedUsername", loggedUsername);
        } else {
            model.addObject("loggedUsername", "Anonymous");
        }



        log.trace("session : " + model.getModel());
        log.info("=============== addToModel =========================");
    }


//    private void addToModelUserDetails(HttpSession session) {
//        log.info("=============== addToModelUserDetails =========================");
//
//        String loggedUsername
//                = SecurityContextHolder.getContext().getAuthentication().getName();
//        session.setAttribute("username", loggedUsername);
//
//        log.info("user(" + loggedUsername + ") session : " + session);
//        log.info("=============== addToModelUserDetails =========================");
//    }
}
