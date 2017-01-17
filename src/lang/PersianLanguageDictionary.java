package lang;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is created by Ahmad Asadi on 1/10/17.
 */
public class PersianLanguageDictionary extends LanguageDictionary{

    private HashMap<String, String> stringMap ;

    public PersianLanguageDictionary(){
        stringMap = new HashMap<>() ;
        stringMap.put("applicationTitle" , "شاخص های اقلیم آسایش حرارتی") ;
        stringMap.put("Evanz" , "شاخص اوانز") ;
        stringMap.put("Olgyay" , "شاخص اولگی") ;
        stringMap.put("Becker" , "شاخص بیکر") ;
        stringMap.put("ke(Wind cool Index)" , "شاخص برودت باد") ;
        stringMap.put("Penwarden" , "شاخص پن واردن") ;
        stringMap.put("Tek(Equivalent temperature)" , "شاخص تعادل گرمایی") ;
        stringMap.put("DI (Term hygrometric Index)" , "شاخص ترموهیگرومتریک") ;
        stringMap.put("Terjung " , "شاخص ترجونگ") ;
        stringMap.put("(WCT) Wind chill Temperature", "شاخص خنکی باد");
        stringMap.put("(WCI) Wind chill index ", "شاخص خنک‌کنندگی باد");
        stringMap.put("SET(New Standard Effective Temperature)" , "شاخص دمای موثر ") ;
        stringMap.put("NET(net effective temperature)" , "شاخص دمای موثر NET") ;
        stringMap.put("SSI (Summer Simmer Index)" , "شاخص داغی تابستان") ;
        stringMap.put("WBGT(Wet Bulb Globe Temperature)" , "شاخص دمای تر") ;
        stringMap.put("AT(Apparent Temperature)" , "شاخص دمای ظاهری") ;
        stringMap.put("Thi1(Temperature -Humidity Index)" , "شاخص دما-رطوبت") ;
        stringMap.put("Thi2(Thom index)" , "شاخص رطوبتی حرارتی(تام)") ;
        stringMap.put("H&K0(wind-chill index)" , "شاخص سرماباد") ;
        stringMap.put("Sb(Bodman's weather severity index)" , "شاخص سختی هوا") ;
        stringMap.put("D(sultry intensity index)" , "شاخص شدت شرجی") ;
        stringMap.put("Di(Discomfort Index)" , "شاخص ضریب ناراحتی انسان") ;
        stringMap.put("Rsi(Relative Strain Index)" , "شاخص فشار نسبی") ;
        stringMap.put("CI(neurotic pressure index)" , "شاخص فشار عصبی") ;
        stringMap.put("Cpi(Cooling Power Index)" , "شاخص قدرت خنک‌کنندگی") ;
        stringMap.put("Givoni" , "نمودار زیست‌ اقلیمی-ساختمانی گیونی") ;
        stringMap.put("Mahoney" , "شاخص ماهانی") ;
        stringMap.put("Humidex" , "شاخص هیومیدکس") ;
        stringMap.put("HI(Heat Index)" , "شاخص گرما ") ;
        stringMap.put("EET (Equivalent effective temperature)" , "شاخص درجه حرارت موثر معادل ") ;
        stringMap.put("ET (Equivalent temperature)" , "شاخص دمای معادل ") ;
        stringMap.put("pphs(Approximated physiological strain index)" , "شاخص فشار فیزیولوژیکی Pphs") ;
        stringMap.put("TS(Thermal Sensation)" , "شاخص احساس حرارتی") ;
        stringMap.put("ASV(Actual Sensation Vote)" , "شاخص آرای احساس حقیقی") ;
        stringMap.put("PMV (predicted mean vote)" , "شاخص میانگین نظرسنجی پیش‌بینی شده") ;
        stringMap.put("PET(Physiological Equivalent Temperature)" , "شاخص دمای معادل فیزیولوژیک") ;
        stringMap.put("UTCI(Universal Thermal Climate Index)" , "شاخص جهانی اقلیم حرارتی");
        stringMap.put("HSI(heat stress index)" , " شاخص استرس گرمایی");
        stringMap.put("PHS(Physiological strain index)" , "شاخص تنش فیزیولوژیکی");
        stringMap.put("STI(Subjective temperature index)" , "شاخص جهانی اقلیم حرارتی");
        stringMap.put("SST(Still shade temperature)" , "SSTشاخص  ");
        stringMap.put("PST(Physiological subjective temperature)" , "PST شاخص ");

        stringMap.put("indexLabel" , "انتخاب شاخص: ");
    }

    @Override
    public String getString(String label) {
        return stringMap.get(label);
    }

    @Override
    public String[] getStrings() {
        return stringMap.values().toArray(new String[0]);
    }

    @Override
    public String getKey(String value)
    {
        for (Map.Entry<String, String> e : stringMap.entrySet()) {
            String key = e.getKey();
            String currValue = e.getValue();
            if(currValue.toLowerCase().equals(value.toLowerCase()))
                return key ;
        }
        return null;
    }
}
