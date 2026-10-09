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
    private boolean isPrescriptionNeeded;
    private boolean canBeGiftedOnCatsBirthday;
    private String photo;

    public MedicationForm(){

    }

    public void setCanBeGiftedOnCatsBirthday(boolean canBeGiftedOnCatsBirthday){
        this.canBeGiftedOnCatsBirthday = canBeGiftedOnCatsBirthday;
    }

    public void setPrescriptionNeeded(boolean prescriptionNeeded){
        isPrescriptionNeeded = prescriptionNeeded;
    }

    public void setMarketReleaseDate(LocalDate marketReleaseDate){
        this.marketReleaseDate = marketReleaseDate;
    }

    public void setMedicationType(MedicationType medicationType){
        this.medicationType = medicationType;
    }

    public void setPhoto(String photo){
        this.photo = photo;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setWeeksAfterBirthToStartMedication(int weeksAfterBirthToStartMedication){
        this.weeksAfterBirthToStartMedication = weeksAfterBirthToStartMedication;
    }

    public boolean isCanBeGiftedOnCatsBirthday(){
        return canBeGiftedOnCatsBirthday;
    }

    public boolean isPrescriptionNeeded(){
        return isPrescriptionNeeded;
    }

    public LocalDate getMarketReleaseDate(){
        return marketReleaseDate;
    }

    public MedicationType getMedicationType(){
        return medicationType;
    }

    public String getPhoto(){
        return photo;
    }

    public double getPrice(){
        return price;
    }

    public String getTitle(){
        return title;
    }

    public int getWeeksAfterBirthToStartMedication(){
        return weeksAfterBirthToStartMedication;
    }
}
