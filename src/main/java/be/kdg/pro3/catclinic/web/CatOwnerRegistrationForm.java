package be.kdg.pro3.catclinic.web;

import be.kdg.pro3.catclinic.domain.PreferredCommunicationLanguage;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CatOwnerRegistrationForm{

    @NotBlank(message = "Name cannot contain only blanks")
    @NotNull(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Family name cannot contain only blanks")
    @NotNull(message = "Family name cannot be empty")
    private String familyName;

    @NotNull(message = "Living space cannot be empty")
    @DecimalMin(value = "0.0", inclusive = false, message = "Living space must be greater than 0")
    private Double livingSpaceSquareMeters;

    @NotNull(message = "Amount of kids cannot be empty")
    @PositiveOrZero(message = "Amount of kids cannot be negative")
    private Integer amountOfKids;

    @NotBlank(message = "Phone cannot be empty")
    @Pattern(regexp = "^\\+?[0-9 /.-]{8,15}$", message = "Phone number is not valid")
    private String ownerPersonsPhone;

    @NotBlank(message = "Photo of ID card is required")
    private String photoOfOwnersIdCard;

    private boolean smokesAtHome;

    @NotNull(message = "Preferred communication language cannot be empty")
    private PreferredCommunicationLanguage preferredCommunicationLanguage;

    public CatOwnerRegistrationForm(){

    }

    @Override
    public String toString(){
        return "CatOwnerRegistrationForm{" +
                "amountOfKids=" + amountOfKids +
                ", name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", livingSpaceSquareMeters=" + livingSpaceSquareMeters +
                ", ownerPersonsPhone='" + ownerPersonsPhone + '\'' +
                ", photoOfOwnersIdCard='" + photoOfOwnersIdCard + '\'' +
                ", smokesAtHome=" + smokesAtHome +
                ", preferredCommunicationLanguage=" + preferredCommunicationLanguage +
                '}';
    }
}