package be.kdg.pro3.catclinic.model.domain;

import be.kdg.pro3.catclinic.PreferredCommunicationLanguage;

import java.time.LocalDate;
import java.util.List;

public class OwningFamily{
    private String familyId;
    private double livingSpaceSquareMeters;
    private int amountOfKids;
    private String ownerPersonsPhone;
    private String photoOfPersonsId;
    private LocalDate registrationDate;
    private boolean smokesAtHome;
    private PreferredCommunicationLanguage preferredCommunicationLanguage;
    private List<Cat> cats;

    public int getAmountOfKids(){
        return amountOfKids;
    }

    public String getFamilyId(){
        return familyId;
    }

    public double getLivingSpaceSquareMeters(){
        return livingSpaceSquareMeters;
    }

    public String getOwnerPersonsPhone(){
        return ownerPersonsPhone;
    }

    public String getPhotoOfPersonsId(){
        return photoOfPersonsId;
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
