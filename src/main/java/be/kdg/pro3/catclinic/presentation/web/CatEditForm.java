package be.kdg.pro3.catclinic.presentation.web;

import be.kdg.pro3.catclinic.domain.Gender;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Setter
@Getter
public class CatEditForm{

    @NotBlank(message = "Name cannot contains only blanks")
    @NotNull(message = "Name cannot be empty")
    @Size(max = 100, message = "Cat name cannot be longer than 100 characters")
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
}
