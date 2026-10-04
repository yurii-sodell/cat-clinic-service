package be.kdg.pro3.catclinic.repository;

import be.kdg.pro3.catclinic.domain.CatOwner;
import be.kdg.pro3.catclinic.domain.PreferredCommunicationLanguage;

import java.time.LocalDate;
import java.util.List;

public interface CatOwnerRepository{
    void saveOwner(CatOwner catOwner);
    void saveOwners(List<CatOwner> owners);
    List<CatOwner> getCatOwners();
    List<CatOwner> getCatOwnerByCatsName(String name);
    List<CatOwner> getCatOwnerByItsName(String name);
    List<CatOwner> getCatOwnerByItsLanguage(PreferredCommunicationLanguage language);
    List<CatOwner> getOwnersByRegistrationDate(LocalDate registrationDate);
}
