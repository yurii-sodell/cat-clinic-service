package be.kdg.pro3.catclinic.model.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CatOwner{
    private String ownerId;
    private String name;
    private String familyName;
    private double livingSpaceSquareMeters;
    private int amountOfKids;
    private String ownerPersonsPhone;
    private String photoOfOwnersIdCard;
    private LocalDate registrationDate;
    private boolean smokesAtHome;
    private PreferredCommunicationLanguage preferredCommunicationLanguage;
    private List<Cat> cats;

    public CatOwner(int amountOfKids, String familyName, double livingSpaceSquareMeters, String name,
                    String ownerId, String ownerPersonsPhone, String photoOfOwnersIdCard,
                    PreferredCommunicationLanguage preferredCommunicationLanguage,
                    LocalDate registrationDate, boolean smokesAtHome){
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
        this.cats = new ArrayList<>();
    }

    public CatOwner(){

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

    @Override
    public String toString() {
        DateTimeFormatter registrationFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return String.format(
                "\n%-20s| %-15s| %-10s| %-8.2f| %-6d| %-15s| %-10s| %-20s| %-15s",
                this.name,
                this.familyName,
                this.ownerId,
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
        return Double.compare(livingSpaceSquareMeters, catOwner.livingSpaceSquareMeters) == 0 && amountOfKids == catOwner.amountOfKids && smokesAtHome == catOwner.smokesAtHome && Objects.equals(ownerId, catOwner.ownerId) && Objects.equals(name, catOwner.name) && Objects.equals(familyName, catOwner.familyName) && Objects.equals(ownerPersonsPhone, catOwner.ownerPersonsPhone) && Objects.equals(photoOfOwnersIdCard, catOwner.photoOfOwnersIdCard) && Objects.equals(registrationDate, catOwner.registrationDate) && preferredCommunicationLanguage == catOwner.preferredCommunicationLanguage;
    }

    @Override
    public int hashCode(){
        return Objects.hash(ownerId, name, familyName, livingSpaceSquareMeters, amountOfKids, ownerPersonsPhone, photoOfOwnersIdCard, registrationDate, smokesAtHome, preferredCommunicationLanguage);
    }
}