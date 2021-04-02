package softuni.unisports.web;


import org.dom4j.rule.Mode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.unisports.exception.NewsNotFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {NewsNotFoundException.class})
    @GetMapping("/error")
    public ModelAndView newsNotFoundExceptionHandler(HttpServletRequest req, NewsNotFoundException ex) {
        ModelAndView mav = new ModelAndView("404");
        mav.addObject("message", ex.getMessage());

        return mav;
    }

    @ExceptionHandler(Exception.class)
    @GetMapping("/error")
    public ModelAndView unexpectedExceptionHandler(HttpServletRequest req, Exception ex) {
        ModelAndView mav = new ModelAndView("500");
        mav.addObject("message", ex.getMessage());

        return mav;
    }

    @ExceptionHandler(RuntimeException.class)
    @GetMapping("/error")
    public ModelAndView runtimeException(HttpServletRequest req, RuntimeException ex) {
        ModelAndView mav = new ModelAndView("500");
        mav.addObject("message", ex.getMessage());

        return mav;
    }


}
