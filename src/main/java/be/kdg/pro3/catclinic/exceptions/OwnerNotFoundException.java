package be.kdg.pro3.catclinic.exceptions;

public class OwnerNotFoundException extends RuntimeException{
    public OwnerNotFoundException(){
        super("Owner not found!");
    }
}
