package be.kdg.pro3.catclinic.domain;

import java.util.Objects;
import java.util.UUID;

public record CatId(UUID id){
    public CatId{
        Objects.requireNonNull(id, "Cat id cannot be null");
    }

    public static CatId random(){
        return new CatId(UUID.randomUUID());
    }
}
