package be.kdg.pro3.catclinic.controller;

import org.springframework.stereotype.Component;

@Component
public class ConsoleView{
    private String MAIN_MENU;
    private String CAT_MENU;
    private String MEDICATION_MENU;
    private String OWNER_MENU;
    private String UNKNOWN_CHOICE_MESSAGE;
    private String NO_RESULTS_MESSAGE;

    public ConsoleView(){
        initStrings();
    }

    private void initStrings(){
        MAIN_MENU = "\n   What are we working with?\n1 - Cats\n2 - Owners\n3 - Medications\n4 - Exit\n";
        CAT_MENU = "\n   Cats\n1 - Show all cats\n2 - Filter by age and weight\n3 - Filter by prescripted medications\n4 - Back\n";
        MEDICATION_MENU = "\n   Medications\n1 - Show all medications\n2 - is prescription needed\n3 - Price\n4 - Back\n";
        OWNER_MENU = "\n   Owners\n1 - Show all owners\n2 - Find owner by name\n3 - Language\n4 - Find by cats\n5 - Back\n";
        UNKNOWN_CHOICE_MESSAGE = "I don't get you\n";
        NO_RESULTS_MESSAGE = "None satisfied the provided filters\n";
    }

    String getCatMenu(){
        return CAT_MENU;
    }

    String getMainMenu(){
        return MAIN_MENU;
    }

    String getMedicationMenu(){
        return MEDICATION_MENU;
    }

    String getNoResultsMessage(){
        return NO_RESULTS_MESSAGE;
    }

    String getOwnerMenu(){
        return OWNER_MENU;
    }

    String getUnknownChoiceMessage(){
        return UNKNOWN_CHOICE_MESSAGE;
    }
}
