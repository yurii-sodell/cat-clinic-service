package be.kdg.pro3.catclinic.service;

import be.kdg.pro3.catclinic.domain.Cat;
import be.kdg.pro3.catclinic.exceptions.CatNotFoundException;
import be.kdg.pro3.catclinic.repository.CatRepository;
import be.kdg.pro3.catclinic.web.CatEditForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public List<Cat> getCatBySearchRequest(String catName, Integer minAge, Integer maxAge, Integer minWeight, Integer maxWeight){
        return catRepository.getCatsBySearchRequest(catName, minAge, maxAge, minWeight, maxWeight);
    }

    @Override
    public Cat getCatById(UUID uuid) throws CatNotFoundException{
        Optional<Cat> found = catRepository.getCatById(uuid);
        if(found.isEmpty()) throw new CatNotFoundException("No cat found with id: " + uuid);
        return found.get();
    }

    @Override
    public void updateCat(UUID catsId, CatEditForm catEditForm) throws CatNotFoundException, IllegalArgumentException{
        Optional<Cat> found = catRepository.getCatById(catsId);
        if(found.isEmpty()) throw new CatNotFoundException("No cat found with id: " + catsId);

        Cat theCat = found.get();
        try{
            theCat.setGender(catEditForm.getKeptGender());
            theCat.setDateOfBirth(catEditForm.getKeptDateOfBirth());
            theCat.setName(catEditForm.getKeptName());
            theCat.setPhoto(catEditForm.getKeptPhoto());
            theCat.setWeightInKilos(catEditForm.getKeptWeightInKilos().doubleValue());
        }catch (IllegalArgumentException e) {
            throw e;
        }
        catRepository.saveCat(theCat);
    }

}
