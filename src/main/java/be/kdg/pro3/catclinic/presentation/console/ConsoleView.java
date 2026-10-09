package be.kdg.pro3.catclinic.presentation.console;

import be.kdg.pro3.catclinic.domain.Cat;
import be.kdg.pro3.catclinic.domain.CatOwner;
import be.kdg.pro3.catclinic.domain.Medication;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class ConsoleView{
    private final Scanner sc;
    private static final String MAIN_MENU;
    private static final String CAT_MENU;
    private static final String MEDICATION_MENU;
    private static final String OWNER_MENU;
    private static final String UNKNOWN_CHOICE_MESSAGE;
    private static final String NO_RESULTS_MESSAGE;

    public ConsoleView(){
        this.sc = new Scanner(System.in);
    }

    static {
        MAIN_MENU = "\n   What are we working with?\n1 - Cats\n2 - Owners\n3 - Medications\n4 - Exit\n";
        CAT_MENU = "\n   Cats\n1 - Show all cats\n2 - Filter by age and weight\n3 - Filter by prescripted medications\n4 - Back\n";
        MEDICATION_MENU = "\n   Medications\n1 - Show all medications\n2 - is prescription needed\n3 - Price\n4 - Register new medication\n5 - Back\n";
        OWNER_MENU = "\n   Owners\n1 - Show all owners\n2 - Find owner by name\n3 - Language\n4 - Find by cats\n5 - Find by registration date\n6 - Back\n";
        UNKNOWN_CHOICE_MESSAGE = "I don't get you\n";
        NO_RESULTS_MESSAGE = "None satisfied the provided filters\n";
    }


    public void showCatMenu() {
        System.out.println(CAT_MENU);
    }

    public void showMainMenu() {
        System.out.println(MAIN_MENU);
    }

    public void showMedicationMenu() {
        System.out.println(MEDICATION_MENU);
    }

    public void showNoResultsMessage() {
        System.out.println(NO_RESULTS_MESSAGE);
    }

    public void showOwnerMenu() {
        System.out.println(OWNER_MENU);
    }

    public void showUnknownChoiceMessage() {
        System.out.println(UNKNOWN_CHOICE_MESSAGE);
    }

    void show(String message) {
        if(message != null) System.out.printf(message);
    }

    void show(Cat cat) {
        if(cat != null) System.out.println(cat);
    }

    void show(CatOwner owner) {
        if(owner != null) System.out.println(owner);
    }

    void show(Medication medication) {
        if(medication != null) System.out.println(medication);
    }

    public int getIntInput() throws InputMismatchException{
        try {
            int value = sc.nextInt();
            sc.nextLine();
            return value;
        } catch (InputMismatchException e) {
            sc.nextLine();
            throw new InputMismatchException("Try a number instead\n");
        }
    }

    public int getIntInput(int defaultValue) throws InputMismatchException {
        String input = sc.nextLine().trim();
        if (input.isEmpty()) return defaultValue;

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Try a number instead\n");
        }
    }

    public double getDoubleInput(double defaultValue) throws InputMismatchException {
        String input = sc.nextLine().trim();
        if (input.isEmpty()) return defaultValue;

        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Try a number instead\n");
        }
    }

    public String inputString(){
        return sc.nextLine();
    }
}
