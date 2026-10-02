package be.kdg.pro3.catclinic;
import be.kdg.pro3.catclinic.controller.ConsoleView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CatClinicApp {
    static void main(String[] args){
        ConfigurableApplicationContext context = SpringApplication.run(CatClinicApp.class);
        context.close();
    }
}