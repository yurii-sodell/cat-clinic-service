package be.kdg.pro3.catclinic.presistence;

import be.kdg.pro3.catclinic.domain.CatId;
import be.kdg.pro3.catclinic.domain.CatOwner;
import be.kdg.pro3.catclinic.domain.CatOwnerId;
import be.kdg.pro3.catclinic.domain.PreferredCommunicationLanguage;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CatOwnerRepository{
    void saveOwner(CatOwner catOwner);
    void saveOwners(List<CatOwner> owners);
    List<CatOwner> getCatOwners();
    List<CatOwner> getCatOwnerByItsName(String name);
    List<CatOwner> getCatOwnerByItsLanguage(PreferredCommunicationLanguage language);
    List<CatOwner> getOwnersByRegistrationDate(LocalDate registrationDate);
    List<CatOwner> getCatOwnerByCatsId(CatId id);
    Optional<CatOwner> getCatOwnerById(CatOwnerId id);
}
