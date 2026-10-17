package be.kdg.pro3.catclinic.web;

import be.kdg.pro3.catclinic.domain.Cat;
import be.kdg.pro3.catclinic.domain.Gender;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class CatEditForm{

    @NotBlank
    @NotNull
    private String name;

    @NotNull
    @PastOrPresent
    private LocalDate dateOfBirth;

    @NotNull
    private Gender gender;

    @DecimalMin(value = "0.0", inclusive = false)
    private double weightInKilos;

    private String photo;

    public CatEditForm(Cat cat){
        this.name = cat.getName();
        this.dateOfBirth = cat.getDateOfBirth();
        this.gender = cat.getGender();
        this.weightInKilos = cat.getWeightInKilos();
        this.photo = cat.getPhoto();
    }

    public LocalDate getDateOfBirth(){
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender(){
        return gender;
    }

    public void setGender(Gender gender){
        this.gender = gender;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPhoto(){
        return photo;
    }

    public void setPhoto(String photo){
        this.photo = photo;
    }

    public double getWeightInKilos(){
        return weightInKilos;
    }

    public void setWeightInKilos(double weightInKilos){
        this.weightInKilos = weightInKilos;
    }
}
