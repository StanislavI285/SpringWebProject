package softuni.unisports.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import softuni.unisports.model.service.NewsGetServiceModel;
import softuni.unisports.service.NewsService;

@Aspect
@Component
public class NewsViewAspect {

    private final NewsService newsService;


    public NewsViewAspect(NewsService newsService) {
        this.newsService = newsService;
    }

    @Pointcut("execution(* softuni.unisports.web.NewsController.showNewsArticle(..))")
    public void newsViewPointcut() {
    }

    @Before("newsViewPointcut()")
    public void newsViewAdvice(JoinPoint joinpoint) {

        Object[] args = joinpoint.getArgs();
        String newsId = (String) args[0];
        this.newsService.incrementViews(newsId);
    }
}
