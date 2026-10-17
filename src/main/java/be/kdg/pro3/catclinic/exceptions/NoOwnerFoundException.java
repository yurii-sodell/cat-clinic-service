package be.kdg.pro3.catclinic.exceptions;

public class NoOwnerFoundException extends RuntimeException{
    public NoOwnerFoundException(String message){
        super(message);
    }
}
