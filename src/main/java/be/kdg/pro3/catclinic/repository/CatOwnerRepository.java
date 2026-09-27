package be.kdg.pro3.catclinic.repository;

import be.kdg.pro3.catclinic.domain.CatOwner;

import java.util.List;

public interface CatOwnerRepository{
    void saveOwner(CatOwner catOwner);
    void saveOwners(List<CatOwner> owners);
    List<CatOwner> getCatOwners();
}
