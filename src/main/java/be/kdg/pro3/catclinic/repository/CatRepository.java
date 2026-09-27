package be.kdg.pro3.catclinic.repository;

import be.kdg.pro3.catclinic.domain.Cat;
import be.kdg.pro3.catclinic.domain.CatOwner;

import java.util.List;

public interface CatRepository{
    void saveCat(Cat cat);
    void saveCats(List<Cat> cats);
    List<Cat> getCats();
}
