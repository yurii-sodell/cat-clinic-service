package be.kdg.pro3.catclinic.service;

import be.kdg.pro3.catclinic.domain.CatOwner;
import be.kdg.pro3.catclinic.domain.PreferredCommunicationLanguage;
import be.kdg.pro3.catclinic.repository.CatOwnerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class DefaultCatOwnerService implements CatOwnerService{
    private final CatOwnerRepository catOwnerRepository;

    public DefaultCatOwnerService(CatOwnerRepository catOwnerRepository){
        this.catOwnerRepository = catOwnerRepository;
    }

    @Override
    public List<CatOwner> getAllOwners(){
        return catOwnerRepository.getCatOwners();
    }

    @Override
    public List<CatOwner> getCatOwnerByCatsName(String name){
        return catOwnerRepository.getCatOwnerByCatsName(name);
    }

    @Override
    public List<CatOwner> getCatOwnerByItsName(String name){
        return catOwnerRepository.getCatOwnerByItsName(name);
    }

    @Override
    public List<CatOwner> getCatOwnerByItsLanguage(PreferredCommunicationLanguage language){
        return catOwnerRepository.getCatOwnerByItsLanguage(language);
    }

    @Override
    public List<CatOwner> getOwnersByRegistrationDate(LocalDate registrationDate){
        return catOwnerRepository.getOwnersByRegistrationDate(registrationDate);
    }

    ;
}
