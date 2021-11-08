
import java.util.ArrayList;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
//        for (int i = 0; i < arabic2farsi_plurals.size; i++) {
//
//
//        }
        System.out.println(ha_hay_removal("کتابهای من ها را بده"));
    }
    public static String nim_fasele_removal(String sentence){
        return sentence.replace("\u200c"," ");
    }

//    public static String arabic2farsi_plural_replacement(String sentence){
//        for (int i = 0; i<arabic2farsi_plurals.size();i++){
//            for (int j=0 ; j<arabic2farsi_plurals.getValue(i).length;j++){
//                sentence = sentence.replace(arabic2farsi_plurals.get(i).get(j),arabic2farsi_plurals.get(i).get(j))
//            }
//        }
//        return sentence;
//    }
    public static String word_concatenation(String sentence){


        HashMap<String ,String> good_bad = new HashMap<>();
        good_bad.put("دیگه" , "دیگر");
        good_bad.put("یه کم" , "کمی");
        good_bad.put("یه مقدار" , "کمی");
        good_bad.put("یه کمی" , "کمی");
        good_bad.put("یه مقداری" , "کمی");
        good_bad.put("سیم کارت" , "سیمکارت");
        good_bad.put("صورت حساب" , "صورتحساب");
        good_bad.put("تماس بان" , "تماسبان");
        good_bad.put("کیفیت" , "کییفیت");
        good_bad.put("دایمی" , "دائمی");
        good_bad.put("پیغام گیر" , "پیامگیر");
        good_bad.put("پیام گی" , "'پیامگیر");
        good_bad.put("قبض" , "صورتحساب");
        good_bad.put("پرسش نامه" , "پرسشنامه");
        good_bad.put("نظر سنجی" , "نظرسنجی");
        good_bad.put("غیر فعال" , "غیرفعال");
        good_bad.put("اس ام اس" , "پیامک");
        good_bad.put("میس کال" , "میسکال");
        good_bad.put("نیکو کاری" , "نیکوکاری");
        good_bad.put("پیغام" , "پیام");
        good_bad.put("تاریخ چه" , "تاریخچه");
        good_bad.put("چه میزان" , "چقدر");
        good_bad.put("چه مقدار" , "چقدر");
        good_bad.put("چه قدر" , "چقدر");
        good_bad.put("می خوام" , "میخوام");
        good_bad.put("سرعت سنج" , "سرعتسنج");
        good_bad.put("پکیج" , "بسته");
        good_bad.put("بدهکار" , "بستانکار");
        good_bad.put("طلب کار" , "بستانکار");
        good_bad.put("طلبکار" , "بستانکار");
        good_bad.put("بستان کاری" , "بستانکاری");
        good_bad.put("نشون" , "نشان");
        for (String s : good_bad.keySet()) {
            sentence = sentence.replace(s,good_bad.get(s));
        }
        return sentence;

    }
    public static String ha_hay_removal(String sentence){
        ArrayList<String>ok_dictionary = new ArrayList<>();
        ok_dictionary.add("بها");
        ok_dictionary.add("بهای");
        ok_dictionary.add("بهایی");
        ok_dictionary.add("بهائی");

        ok_dictionary.add("تنها");
        ok_dictionary.add("تنهای");
        ok_dictionary.add("تنهایی");
        ok_dictionary.add("تنهائی");

        ok_dictionary.add("رها");
        ok_dictionary.add("رهای");
        ok_dictionary.add("رهایی");
        ok_dictionary.add("رهائی");
        String [] words = sentence.split(" ");
        StringBuilder res = new StringBuilder();
        for (String word : words) {
            if ((word.endsWith("ها") && word.length()!=2) ||(word.endsWith("های") && word.length()!=3) ||
                    (word.endsWith("هائی") && word.length()!=4)||
                    (word.endsWith("هایی") && word.length()!=4)){
                if (word.endsWith("ها")){
                    res.append(word, 0, word.length() - 2).append(" ").append("ها").append(" ");
                }
                if (word.endsWith("های")){
                    res.append(word, 0, word.length() - 3).append(" ").append("های").append(" ");
                }
                if (word.endsWith("هایی") || word.endsWith("هائی")){
                    res.append(word, 0, word.length() - 4).append(" ").append("هایی").append(" ");
                }
            }
            else {
                res.append(word).append(" ");
            }
        }
        return res.toString().strip();
    }
}
