package be.kdg.pro3.catclinic.domain;
public enum MedicationType {
    PILLS("pills-lang"),
    OINTMENT("ointment-lang"),
    DROPS("drops-lang"),
    SYRUP("syrup-lang"),
    POWDER("powder-lang"),
    INJECTION("injection-lang");

    private final String displayName;

    MedicationType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
