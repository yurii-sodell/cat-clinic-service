package be.kdg.pro3.catclinic.repository;

import be.kdg.pro3.catclinic.domain.Cat;
import be.kdg.pro3.catclinic.domain.CatId;
import be.kdg.pro3.catclinic.domain.CatOwnerId;
import be.kdg.pro3.catclinic.domain.MedicationId;

import java.util.List;
import java.util.Optional;

public interface CatRepository{
    void save(Cat cat);
    void saveCats(List<Cat> cats);
    List<Cat> getCats();
    List<Cat> getCatsFilteredByAgeAndByWeight(int minAge, int maxAge, int minWeight, int maxWeight);
    List<Cat> getCatsFilteredByMedicationId(MedicationId medicationId);
    List<Cat> getCatsBySearchRequest(String catName, Integer minAge, Integer maxAge, Integer minWeight, Integer maxWeight);
    Optional<Cat> getCatById(CatId id);
    List<Cat> getCatByName(String name);
    List<Cat> getCatByIdOfOwner(CatOwnerId id);
}
