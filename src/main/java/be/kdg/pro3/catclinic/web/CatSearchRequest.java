package be.kdg.pro3.catclinic.web;

public class CatSearchRequest{
    private String catName;
    private Integer minAge;
    private Integer maxAge;
    private Integer mixWeight;
    private Integer maxWeight;

    public CatSearchRequest(String catName, Integer minAge, Integer maxAge, Integer minWeight, Integer maxWeight){
        if(minAge != null && maxAge != null && minWeight >= maxAge) throw new IllegalArgumentException("Min age must be higher then max age");
        if(minWeight != null && maxWeight != null && minWeight >= maxWeight) throw new IllegalArgumentException("Min weight must be higher then max weight");
        this.catName = catName;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.mixWeight = minWeight;
        this.maxWeight = maxWeight;
    }

    public Integer getMaxWeight(){
        return maxWeight;
    }

    public void getMaxWeight(Integer maxWeight){
        this.maxWeight = maxWeight;
    }

    public Integer getMaxAge(){
        return maxAge;
    }

    public void setMaxAge(Integer maxAge){
        this.maxAge = maxAge;
    }

    public Integer getMinAge(){
        return minAge;
    }

    public void setMinAge(Integer minAge){
        this.minAge = minAge;
    }

    public Integer getMinWeight(){
        return mixWeight;
    }

    public void setMixWeight(Integer mixWeight){
        this.mixWeight = mixWeight;
    }

    public String getCatName(){
        return catName;
    }

    public void setName(String catName){
        this.catName = catName;
    }

    @Override
    public String toString(){
        return "CatSearchRequest{" +
                "catName=" + catName +
                ", minAge=" + minAge +
                ", maxAge=" + maxAge +
                ", mixWeight=" + mixWeight +
                ", maxWeight=" + maxWeight +
                '}';
    }
}
