package be.kdg.pro3.catclinic.configuration;

import be.kdg.pro3.catclinic.repository.CatOwnerRepository;
import be.kdg.pro3.catclinic.repository.CatRepository;
import be.kdg.pro3.catclinic.repository.MedicationRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration{

    @Bean
    @ConditionalOnProperty(name = "db.seeded", havingValue ="true")
    public DataFactory dataFactory(CatOwnerRepository catOwnerRepository, MedicationRepository medicationRepository, CatRepository catRepository){
        System.out.println("Seeder is called");
        return new DataFactory(catOwnerRepository, medicationRepository, catRepository);
    }

}
