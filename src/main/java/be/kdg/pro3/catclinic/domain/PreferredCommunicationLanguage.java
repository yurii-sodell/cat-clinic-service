package be.kdg.pro3.catclinic.domain;

public enum PreferredCommunicationLanguage{
    NL("Dutch"),
    FR("French"),
    DE("German"),
    EN("English"),
    OTHER("Other");

    private String languageName;

    PreferredCommunicationLanguage(String languageName){
        this.languageName = languageName;
    }

    public String getLanguageName(){
        return languageName;
    }

}
