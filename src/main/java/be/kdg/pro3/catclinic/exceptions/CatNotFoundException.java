package be.kdg.pro3.catclinic.exceptions;

public class CatNotFoundException extends RuntimeException{
    public CatNotFoundException(String message){
        super(message);
    }
}
