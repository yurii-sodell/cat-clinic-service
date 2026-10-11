package be.kdg.pro3.catclinic.web;

import be.kdg.pro3.catclinic.domain.PreferredCommunicationLanguage;

public class CatOwnerRegistrationForm{
    private String name;
    private String familyName;
    private double livingSpaceSquareMeters;
    private int amountOfKids;
    private String ownerPersonsPhone;
    private String photoOfOwnersIdCard;
    private boolean smokesAtHome;
    private PreferredCommunicationLanguage preferredCommunicationLanguage;

    public CatOwnerRegistrationForm(){

    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getFamilyName(){
        return familyName;
    }

    public void setFamilyName(String familyName){
        this.familyName = familyName;
    }

    public double getLivingSpaceSquareMeters(){
        return livingSpaceSquareMeters;
    }

    public void setLivingSpaceSquareMeters(double livingSpaceSquareMeters){
        this.livingSpaceSquareMeters = livingSpaceSquareMeters;
    }

    public int getAmountOfKids(){
        return amountOfKids;
    }

    public void setAmountOfKids(int amountOfKids){
        this.amountOfKids = amountOfKids;
    }

    public String getOwnerPersonsPhone(){
        return ownerPersonsPhone;
    }

    public void setOwnerPersonsPhone(String ownerPersonsPhone){
        this.ownerPersonsPhone = ownerPersonsPhone;
    }

    public String getPhotoOfOwnersIdCard(){
        return photoOfOwnersIdCard;
    }

    public void setPhotoOfOwnersIdCard(String photoOfOwnersIdCard){
        this.photoOfOwnersIdCard = photoOfOwnersIdCard;
    }

    public boolean isSmokesAtHome(){
        return smokesAtHome;
    }

    public void smokesAtHome(boolean smokesAtHome){
        this.smokesAtHome = smokesAtHome;
    }

    public PreferredCommunicationLanguage getPreferredCommunicationLanguage(){
        return preferredCommunicationLanguage;
    }

    public void setPreferredCommunicationLanguage(PreferredCommunicationLanguage preferredCommunicationLanguage){
        this.preferredCommunicationLanguage = preferredCommunicationLanguage;
    }
}