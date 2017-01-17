package lang;

import java.util.HashMap;

/**
 * This class is created by Ahmad Asadi on 1/10/17.
 */
public abstract class LanguageDictionary {
    private static LanguageDictionary languageDictionary = null;

    public abstract String getString(String label) ;

    public static LanguageDictionary getInstance(String language){
        if(languageDictionary == null)
            createNewLanguageDictionary(language) ;
        return languageDictionary ;
    }

    public abstract String[] getStrings() ;

    public abstract String getKey(String value) ;

    private static void createNewLanguageDictionary(String language) {
        if(language.toLowerCase().equals("en"))
            languageDictionary = new EnglishLanguageDictionary() ;
        else if (language.toLowerCase().equals("fa"))
            languageDictionary = new PersianLanguageDictionary() ;
    }
}
