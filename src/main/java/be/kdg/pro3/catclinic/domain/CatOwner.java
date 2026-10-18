package be.kdg.pro3.catclinic.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CatOwner{
    private UUID ownerId;
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

    private CatOwner(){

    }

    public static CatOwner registerCatOwner(int amountOfKids, String familyName, double livingSpaceSquareMeters, String name,
                                     String ownerPersonsPhone, String photoOfOwnersIdCard,
                                     PreferredCommunicationLanguage preferredCommunicationLanguage,
                                     boolean smokesAtHome){
        CatOwner catOwner = new CatOwner();
        catOwner.amountOfKids = amountOfKids;
        catOwner.familyName = familyName;
        catOwner.livingSpaceSquareMeters = livingSpaceSquareMeters;
        catOwner.name = name;
        catOwner.ownerId = UUID.randomUUID();
        catOwner.ownerPersonsPhone = ownerPersonsPhone;
        catOwner.photoOfOwnersIdCard = photoOfOwnersIdCard;
        catOwner.preferredCommunicationLanguage = preferredCommunicationLanguage;
        catOwner.registrationDate = LocalDate.now();
        catOwner.smokesAtHome = smokesAtHome;
        catOwner.cats = new ArrayList<>();
        return catOwner;
    }

    public static CatOwner retrievedCatOwner(int amountOfKids, String familyName, double livingSpaceSquareMeters, String name,
                                      UUID ownerId, String ownerPersonsPhone, String photoOfOwnersIdCard,
                                      PreferredCommunicationLanguage preferredCommunicationLanguage,
                                      LocalDate registrationDate, boolean smokesAtHome){
        CatOwner catOwner = new CatOwner();
        catOwner.amountOfKids = amountOfKids;
        catOwner.familyName = familyName;
        catOwner.livingSpaceSquareMeters = livingSpaceSquareMeters;
        catOwner.name = name;
        catOwner.ownerId = ownerId;
        catOwner.ownerPersonsPhone = ownerPersonsPhone;
        catOwner.photoOfOwnersIdCard = photoOfOwnersIdCard;
        catOwner.preferredCommunicationLanguage = preferredCommunicationLanguage;
        catOwner.registrationDate = registrationDate;
        catOwner.smokesAtHome = smokesAtHome;
        catOwner.cats = new ArrayList<>();
        return catOwner;
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

    public UUID getOwnerId(){
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
                "\n%-20s | %-15s | %-8.2f | %-6d | %-15s | %-10s | %-20s | %-15s",
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
        return Double.compare(livingSpaceSquareMeters, catOwner.livingSpaceSquareMeters) == 0 && amountOfKids == catOwner.amountOfKids && smokesAtHome == catOwner.smokesAtHome && Objects.equals(ownerId, catOwner.ownerId) && Objects.equals(name, catOwner.name) && Objects.equals(familyName, catOwner.familyName) && Objects.equals(ownerPersonsPhone, catOwner.ownerPersonsPhone) && Objects.equals(photoOfOwnersIdCard, catOwner.photoOfOwnersIdCard) && Objects.equals(registrationDate, catOwner.registrationDate) && preferredCommunicationLanguage == catOwner.preferredCommunicationLanguage;
    }

    @Override
    public int hashCode(){
        return Objects.hash(ownerId, name, familyName, livingSpaceSquareMeters, amountOfKids, ownerPersonsPhone, photoOfOwnersIdCard, registrationDate, smokesAtHome, preferredCommunicationLanguage);
    }
}