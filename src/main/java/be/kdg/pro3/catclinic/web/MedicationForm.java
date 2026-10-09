package be.kdg.pro3.catclinic.web;

import be.kdg.pro3.catclinic.domain.MedicationType;

import java.time.LocalDate;
import java.util.UUID;

public class MedicationForm{
    private String title;
    private double price;
    private int weeksAfterBirthToStartMedication;
    private LocalDate marketReleaseDate;
    private MedicationType medicationType;
    private boolean prescriptionNeeded;
    private boolean canBeGiftedOnCatsBirthday;
    private String photo;

    public MedicationForm(){

    }

    public boolean isCanBeGiftedOnCatsBirthday(){
        return canBeGiftedOnCatsBirthday;
    }

    public void canBeGiftedOnCatsBirthday(boolean canBeGiftedOnCatsBirthday){
        this.canBeGiftedOnCatsBirthday = canBeGiftedOnCatsBirthday;
    }

    public boolean isPrescriptionNeeded(){
        return prescriptionNeeded;
    }

    public void setPrescriptionNeeded(boolean prescriptionNeeded){
        this.prescriptionNeeded = prescriptionNeeded;
    }

    public LocalDate getMarketReleaseDate(){
        return marketReleaseDate;
    }

    public void setMarketReleaseDate(LocalDate marketReleaseDate){
        this.marketReleaseDate = marketReleaseDate;
    }

    public MedicationType getMedicationType(){
        return medicationType;
    }

    public void setMedicationType(MedicationType medicationType){
        this.medicationType = medicationType;
    }

    public String getPhoto(){
        return photo;
    }

    public void setPhoto(String photo){
        this.photo = photo;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public int getWeeksAfterBirthToStartMedication(){
        return weeksAfterBirthToStartMedication;
    }

    public void setWeeksAfterBirthToStartMedication(int weeksAfterBirthToStartMedication){
        this.weeksAfterBirthToStartMedication = weeksAfterBirthToStartMedication;
    }
}
