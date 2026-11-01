package be.kdg.pro3.catclinic.presentation.web;

import be.kdg.pro3.catclinic.domain.MedicationId;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

public class PrescriptionCreationRequest{
    @Getter
    @Setter
    @Pattern(regexp = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}",
            message = "Wrong id format")
    @NotNull
    private MedicationId medicationId;
}
