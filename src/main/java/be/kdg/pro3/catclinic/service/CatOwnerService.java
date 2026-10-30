package be.kdg.pro3.catclinic.service;
import be.kdg.pro3.catclinic.domain.CatOwner;
import be.kdg.pro3.catclinic.domain.CatOwnerId;
import be.kdg.pro3.catclinic.domain.PreferredCommunicationLanguage;
import be.kdg.pro3.catclinic.exceptions.OwnerNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CatOwnerService{
    List<CatOwner> getAllOwners();
    List<CatOwner> getCatOwnerByCatsName(String name);
    List<CatOwner> getCatOwnerByItsName(String name);
    List<CatOwner> getCatOwnerByItsLanguage(PreferredCommunicationLanguage language);
    List<CatOwner> getOwnersByRegistrationDate(LocalDate registrationDate);
    void registerNewOwner(String name, String familyName,
                          double livingSpaceSquareMeters, int amountOfKids,
                          String ownerPersonsPhone, String photoOfOwnersIdCard,
                          PreferredCommunicationLanguage preferredCommunicationLanguage,
                          boolean smokesAtHome);
    public CatOwner getCatOwnerById(CatOwnerId id);
}
