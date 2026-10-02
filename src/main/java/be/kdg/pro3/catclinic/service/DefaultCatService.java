package be.kdg.pro3.catclinic.service;

import be.kdg.pro3.catclinic.domain.Cat;
import be.kdg.pro3.catclinic.repository.CatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCatService implements CatService{
    private final CatRepository catRepository;

    public DefaultCatService(CatRepository catRepository){
        this.catRepository = catRepository;
    }

    public List<Cat> getAllCats(){
        return catRepository.getCats();
    }

    @Override
    public List<Cat> getCatsFilteredByAgeAndByWeight(int minAge, int maxAge, int minWeight, int maxWeight){
        return catRepository.getCatsFilteredByAgeAndByWeight(minAge, maxAge, minWeight, maxWeight);
    }

    @Override
    public List<Cat> getCatsFilteredByMedicationName(String medicationName){
        return catRepository.getCatsFilteredByMedicationName(medicationName);
    }
}
