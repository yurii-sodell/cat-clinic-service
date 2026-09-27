package be.kdg.pro3.catclinic.service;

import be.kdg.pro3.catclinic.domain.CatOwner;
import be.kdg.pro3.catclinic.repository.CatOwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatOwnerService{
    private final CatOwnerRepository catOwnerRepository;

    public CatOwnerService(CatOwnerRepository catOwnerRepository){
        this.catOwnerRepository = catOwnerRepository;
    }

    public List<CatOwner> getAllOwners(){
        return catOwnerRepository.getCatOwners();
    }

}
