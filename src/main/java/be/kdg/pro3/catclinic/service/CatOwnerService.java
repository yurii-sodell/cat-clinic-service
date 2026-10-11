package be.kdg.pro3.catclinic.service;
import be.kdg.pro3.catclinic.domain.CatOwner;
import be.kdg.pro3.catclinic.domain.PreferredCommunicationLanguage;

import java.time.LocalDate;
import java.util.List;

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
}
