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

    public static CatId parse(String value){
        return new CatId(UUID.fromString(value));
    }

    @Override
    public String toString(){
        return id.toString();
    }
}
