package be.kdg.pro3.catclinic.repository;

import be.kdg.pro3.catclinic.domain.Cat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CatRepository{
    void saveCat(Cat cat);
    void saveCats(List<Cat> cats);
    List<Cat> getCats();
    List<Cat> getCatsFilteredByAgeAndByWeight(int minAge, int maxAge, int minWeight, int maxWeight);
    List<Cat> getCatsFilteredByMedicationName(String name);
    List<Cat> getCatsBySearchRequest(String catName, Integer minAge, Integer maxAge, Integer minWeight, Integer maxWeight);
    Optional<Cat> getCatById(UUID uuid);
}
