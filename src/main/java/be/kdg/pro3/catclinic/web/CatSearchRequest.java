package be.kdg.pro3.catclinic.web;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatSearchRequest{

    @Size(max = 100, message = "Cat name cannot be longer than 100 characters")
    @Nullable
    private String catName;

    @PositiveOrZero(message = "Min age cannot be negative")
    @Nullable
    private Integer minAge;

    @PositiveOrZero(message = "Max age cannot be negative")
    @Nullable
    private Integer maxAge;

    @PositiveOrZero(message = "Min weight cannot be negative")
    @Nullable
    private Integer minWeight;

    @PositiveOrZero(message = "Max weight cannot be negative")
    @Nullable
    private Integer maxWeight;

    public CatSearchRequest(@Nullable String catName,
                            @Nullable Integer minAge,
                            @Nullable Integer maxAge,
                            @Nullable Integer minWeight,
                            @Nullable Integer maxWeight) throws IllegalArgumentException{
        if(minAge != null && maxAge != null && minAge >= maxAge) throw new IllegalArgumentException("Min age must be lower than max age");
        if(minWeight != null && maxWeight != null && minWeight >= maxWeight) throw new IllegalArgumentException("Min weight must be lower than max weight");

        this.catName = catName;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
    }

    @Override
    public String toString(){
        return "CatSearchRequest{" +
                "catName=" + catName +
                ", minAge=" + minAge +
                ", maxAge=" + maxAge +
                ", minWeight=" + minWeight +
                ", maxWeight=" + maxWeight +
                '}';
    }
}