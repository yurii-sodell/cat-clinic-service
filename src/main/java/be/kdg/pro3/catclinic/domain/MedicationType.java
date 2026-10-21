package be.kdg.pro3.catclinic.domain;

public enum MedicationType{
    PILLS("Pills"),
    OINTMENT("Ointment"),
    DROPS("Drops"),
    SYRUP("Syrup"),
    POWDER("Powder"),
    INJECTION("Injection");

    private String displayName;

    MedicationType(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return displayName;
    }
}
