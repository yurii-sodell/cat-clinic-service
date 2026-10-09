package be.kdg.pro3.catclinic.configuration;

import be.kdg.pro3.catclinic.presentation.console.ConsoleView;
import be.kdg.pro3.catclinic.presentation.console.Presenter;
import be.kdg.pro3.catclinic.repository.CatOwnerRepository;
import be.kdg.pro3.catclinic.repository.CatRepository;
import be.kdg.pro3.catclinic.repository.MedicationRepository;
import be.kdg.pro3.catclinic.service.CatOwnerService;
import be.kdg.pro3.catclinic.service.CatService;
import be.kdg.pro3.catclinic.service.MedicationService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration{

    @Bean
    @ConditionalOnProperty(name = "db.inmem", havingValue ="true")
    public DataFactory dataFactory(CatOwnerRepository catOwnerRepository, MedicationRepository medicationRepository, CatRepository catRepository){
        IO.println("The database seeder is called");
        return new DataFactory(catOwnerRepository, medicationRepository, catRepository);
    }

    @Bean
    @ConditionalOnProperty(name="control.cons", havingValue="true")
    public Presenter presenter(ConsoleView view, CatOwnerService catOwnerService, CatService catService, MedicationService medicationService){
        return new Presenter(view, catOwnerService, catService, medicationService);
    }

}
