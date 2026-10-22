package be.kdg.pro3.catclinic.web;

import be.kdg.pro3.catclinic.domain.MedicationType;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MedicationRegistrationForm{

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotNull(message = "Price cannot be empty")
    @Positive(message = "Price must be greater than zero")
    @Digits(integer = 10, fraction = 2, message = "Invalid price format")
    private BigDecimal price;

    @PositiveOrZero(message = "Weeks after birth cannot be negative")
    private int weeksAfterBirthToStartMedication;

    @NotNull(message = "Market release date cannot be empty")
    @PastOrPresent(message = "Market release date cannot be in the future")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate marketReleaseDate;

    @NotNull(message = "Medication type cannot be empty")
    private MedicationType medicationType;

    private boolean prescriptionNeeded;
    private boolean canBeGiftedOnCatsBirthday;
    private String photo;

    public MedicationRegistrationForm(){

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

    public BigDecimal getPrice(){
        return price;
    }

    public void setPrice(BigDecimal price){
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
