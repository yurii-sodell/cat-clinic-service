package be.kdg.pro3.catclinic.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class CatOwner{
    private CatOwnerId ownerId;
    private String name;
    private String familyName;
    private double livingSpaceSquareMeters;
    private int amountOfKids;
    private String ownerPersonsPhone;
    private String photoOfOwnersIdCard;
    private LocalDate registrationDate;
    private boolean smokesAtHome;
    private PreferredCommunicationLanguage preferredCommunicationLanguage;
    private List<CatId> cats;

    public CatOwner(int amountOfKids, String familyName, double livingSpaceSquareMeters, String name,
                    CatOwnerId ownerId, String ownerPersonsPhone, String photoOfOwnersIdCard,
                                             PreferredCommunicationLanguage preferredCommunicationLanguage,
                                             LocalDate registrationDate, boolean smokesAtHome, List<CatId> cats){

        this.amountOfKids = amountOfKids;
        this.familyName = familyName;
        this.livingSpaceSquareMeters = livingSpaceSquareMeters;
        this.name = name;
        this.ownerId = ownerId;
        this.ownerPersonsPhone = ownerPersonsPhone;
        this.photoOfOwnersIdCard = photoOfOwnersIdCard;
        this.preferredCommunicationLanguage = preferredCommunicationLanguage;
        this.registrationDate = registrationDate;
        this.smokesAtHome = smokesAtHome;
        this.cats = cats;
    }

    public int getAmountOfKids(){
        return amountOfKids;
    }

    public String getFamilyName(){
        return familyName;
    }

    public String getConcatenatedName(){
        return name + " " + familyName;
    }

    public String getName(){
        return name;
    }

    public CatOwnerId getOwnerId(){
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

    public List<CatId> getCats(){
        return cats;
    }

    @Override
    public String toString(){
        DateTimeFormatter registrationFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return String.format(
                "%n%-20s | %-15s | %-8.2f | %-6d | %-15s | %-10s | %-20s | %-15s",
                this.name,
                this.familyName,
                this.livingSpaceSquareMeters,
                this.amountOfKids,
                this.ownerPersonsPhone,
                this.preferredCommunicationLanguage.toString(),
                registrationFormat.format(this.registrationDate),
                this.smokesAtHome ? "Smokes at home" : "No smoking"
        );
    }

    @Override
    public boolean equals(Object o){
        if(o == null || getClass() != o.getClass()) return false;
        CatOwner catOwner = (CatOwner) o;
        return Objects.equals(ownerId, catOwner.ownerId);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(ownerId);
    }
}