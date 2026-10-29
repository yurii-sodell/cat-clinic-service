package be.kdg.pro3.catclinic.domain;

import java.util.Objects;
import java.util.UUID;

public record CatOwnerId(UUID id){
    public CatOwnerId{
        Objects.requireNonNull(id);
    }

    public static CatOwnerId of(String id){
        return new CatOwnerId(UUID.fromString(id));
    }

    public static CatOwnerId random(){
        return new CatOwnerId(UUID.randomUUID());
    }

}
