package be.kdg.pro3.catclinic.web;

import be.kdg.pro3.catclinic.domain.Cat;
import be.kdg.pro3.catclinic.domain.Gender;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class CatEditForm{

    @NotBlank(message = "Name cannot contains only blanks")
    @NotNull(message = "Name cannot be empty")
    private String keptName;

    @NotNull(message = "Date cannot be empty")
    @PastOrPresent(message = "Date must be in present or past")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate keptDateOfBirth;

    @NotNull(message = "Name cannot be empty")
    private Gender keptGender;

    @NotNull(message = "Wight cannot be empty")
    @DecimalMin(value = "0.0", inclusive = false, message = "Weight cannot be negative")
    @DecimalMax(value="30.0", inclusive = false, message = "Weight cannot be more them 30")
    private Double keptWeightInKilos;

    private String keptPhoto;

    public CatEditForm(String name, LocalDate dateOfBirth, Gender gender, Double weight, String photo){
        this.keptName = name;
        this.keptDateOfBirth = dateOfBirth;
        this.keptGender = gender;
        this.keptWeightInKilos = weight;
        this.keptPhoto = photo;
    }

    public LocalDate getKeptDateOfBirth(){
        return keptDateOfBirth;
    }

    public void setKeptDateOfBirth(LocalDate keptDateOfBirth){
        this.keptDateOfBirth = keptDateOfBirth;
    }

    public Gender getKeptGender(){
        return keptGender;
    }

    public void setKeptGender(Gender keptGender){
        this.keptGender = keptGender;
    }

    public String getKeptName(){
        return keptName;
    }

    public void setKeptName(String keptName){
        this.keptName = keptName;
    }

    public String getKeptPhoto(){
        return keptPhoto;
    }

    public void setKeptPhoto(String keptPhoto){
        this.keptPhoto = keptPhoto;
    }

    public Double getKeptWeightInKilos(){
        return keptWeightInKilos;
    }

    public void setKeptWeightInKilos(double keptWeightInKilos){
        this.keptWeightInKilos = keptWeightInKilos;
    }
}
