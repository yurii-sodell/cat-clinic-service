package be.kdg.pro3.catclinic.service;

import be.kdg.pro3.catclinic.domain.Cat;
import be.kdg.pro3.catclinic.domain.CatId;
import be.kdg.pro3.catclinic.domain.CatOwnerId;
import be.kdg.pro3.catclinic.domain.Medication;
import be.kdg.pro3.catclinic.exceptions.CatNotFoundException;
import be.kdg.pro3.catclinic.exceptions.MedicationNotFoundException;
import be.kdg.pro3.catclinic.repository.CatRepository;
import be.kdg.pro3.catclinic.presentation.web.CatEditForm;
import be.kdg.pro3.catclinic.repository.MedicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultCatService implements CatService{
    private final CatRepository catRepository;
    private final MedicationRepository medicationRepository;

    public DefaultCatService(CatRepository catRepository, MedicationRepository medicationRepository){
        this.catRepository = catRepository;
        this.medicationRepository = medicationRepository;
    }

    public List<Cat> getAllCats(){
        return catRepository.getCats();
    }

    @Override
    public List<Cat> getCatsFilteredByAgeAndByWeight(int minAge, int maxAge, int minWeight, int maxWeight){
        return catRepository.getCatsFilteredByAgeAndByWeight(minAge, maxAge, minWeight, maxWeight);
    }

    @Override
    public List<Cat> getCatsFilteredByMedicationName(String medicationName) throws MedicationNotFoundException{
        Optional<Medication> medication = medicationRepository.getMedicationIdByName(medicationName);
        if(medication.isEmpty()) throw new MedicationNotFoundException();
        return catRepository.getCatsFilteredByMedicationId(medication.get().getProductId());
    }

    @Override
    public List<Cat> getCatBySearchRequest(String catName, Integer minAge, Integer maxAge, Integer minWeight, Integer maxWeight){
        return catRepository.getCatsBySearchRequest(catName, minAge, maxAge, minWeight, maxWeight);
    }

    @Override
    public Cat getCatById(CatId uuid) throws CatNotFoundException{
        Optional<Cat> found = catRepository.getCatById(uuid);
        if(found.isEmpty()) throw new CatNotFoundException();
        return found.get();
    }

    @Override
    public List<Cat> getCatsForOwner(CatOwnerId catOwnerId){
        return catRepository.getCatByIdOfOwner(catOwnerId);
    }

    @Override
    public void updateCat(CatId catsId, CatEditForm catEditForm) throws CatNotFoundException, IllegalArgumentException{
        Optional<Cat> found = catRepository.getCatById(catsId);
        if(found.isEmpty()) throw new CatNotFoundException();

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
