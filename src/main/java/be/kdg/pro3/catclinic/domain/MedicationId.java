package be.kdg.pro3.catclinic.domain;

import java.util.Objects;
import java.util.UUID;

public record MedicationId(UUID uuid){
    public MedicationId{
        Objects.requireNonNull(uuid, "UUID cannot be empty");
    }

    public static MedicationId random(){
        return new MedicationId(UUID.randomUUID());
    }

    public static MedicationId parse(String id){
        return new MedicationId(UUID.fromString(id));
    }

}
