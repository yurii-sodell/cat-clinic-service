package be.kdg.pro3.catclinic.configuration;

import be.kdg.pro3.catclinic.domain.SessionHistory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SessionHistoryPrehandler implements HandlerInterceptor{
    private SessionHistory history;

    public SessionHistoryPrehandler(SessionHistory history){
        this.history = history;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object Handler){
        history.addLog(request.getRequestURL().toString());
        return true;
    }
}
