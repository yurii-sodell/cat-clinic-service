package be.kdg.pro3.catclinic.configuration.converter;

import be.kdg.pro3.catclinic.configuration.SessionHistoryPrehandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    private SessionHistoryPrehandler prehandler;

    public WebConfig(SessionHistoryPrehandler prehandler){
        this.prehandler = prehandler;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(prehandler)
                .addPathPatterns("/owners/**",
                        "/cats/**",
                        "/medications/**",
                        "/history/**",
                        "/prescriptions/**");
    }

    @Override
    public void addFormatters(FormatterRegistry registry){
        registry.addConverter(new CatIdFromStringConverter());
        registry.addConverter(new OwnerIdFromStringConverter());
        registry.addConverter(new MedicationIdFromStringConverter());
    }

}
