package be.kdg.pro3.catclinic.model.domain;

import be.kdg.pro3.catclinic.PreferredCommunicationLanguage;

import java.time.LocalDate;
import java.util.List;

public class CatOwner{
    private String ownerId;
    private double livingSpaceSquareMeters;
    private int amountOfKids;
    private String ownerPersonsPhone;
    private String photoOfOwnersIdCard;
    private LocalDate registrationDate;
    private boolean smokesAtHome;
    private PreferredCommunicationLanguage preferredCommunicationLanguage;
    private List<Cat> cats;

    public int getAmountOfKids(){
        return amountOfKids;
    }

    public String getOwnerId(){
        return ownerId;
    }

    public double getLivingSpaceSquareMeters(){
        return livingSpaceSquareMeters;
    }

    public String getOwnerPersonsPhone(){
        return ownerPersonsPhone;
    }

    public String getPhotoOfOwnersIdCard(){
        return photoOfOwnersIdCard;
    }

    public PreferredCommunicationLanguage getPreferredCommunicationLanguage(){
        return preferredCommunicationLanguage;
    }

    public LocalDate getRegistrationDate(){
        return registrationDate;
    }

    public boolean isSmokesAtHome(){
        return smokesAtHome;
    }

    public List<Cat> getCats(){
        return cats;
    }
}
