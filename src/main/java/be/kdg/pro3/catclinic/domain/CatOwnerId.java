package be.kdg.pro3.catclinic.domain;

import java.util.Objects;
import java.util.UUID;

public record CatOwnerId(UUID id){
    public CatOwnerId{
        Objects.requireNonNull(id);
    }
}
