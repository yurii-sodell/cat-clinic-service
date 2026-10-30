package be.kdg.pro3.catclinic.presentation.web;

import be.kdg.pro3.catclinic.domain.MedicationType;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
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
}
