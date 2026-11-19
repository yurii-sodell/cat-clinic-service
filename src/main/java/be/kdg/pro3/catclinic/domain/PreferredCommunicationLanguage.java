package be.kdg.pro3.catclinic.domain;

public enum PreferredCommunicationLanguage{
    NL("dutch-lang"),
    FR("french-lang"),
    DE("german-lang"),
    EN("english-lang"),
    OTHER("other-lang");

    private String languageName;

    PreferredCommunicationLanguage(String languageName){
        this.languageName = languageName;
    }

    public String getLanguageName(){
        return languageName;
    }

}
