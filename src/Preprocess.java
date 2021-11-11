import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Preprocess {

    static String[] yekans = new String[]{"صفر", "یک", "دو", "سه", "چهار", "پنج", "شش", "هفت", "هشت", "نه",
            "ده", "یازده", "دوازده", "سیزده", "چهارده", "پانزده", "شانزده", "هفده", "هجده", "نوزده"};
    static String[] dahgans = new String[]{"دهدهدهدهدهدهده", "بیست", "سی", "چهل", "پنجاه", "شصت", "هفتاد", "هشتاد", "نود"};
    static String[] sadgans = new String[]{"صد", "دویست", "سیصد", "چهارصد", "پانصد", "ششصد", "هفتصد", "هشتصد", "نهصد"};

    static String[] other_decimal_units = new String[]{"هزار", "میلیون", "میلیارد"};
    static String[] money_units = new String[]{"ریال", "تومان"};

    static String nim_faseleh = "\u200c";

    static String[] hybrid_numerals = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "۰", "۱", "۲", "۳", "۴",
            "۵", "۶", "۷", "۸", "۹"};
    static HashMap<String, String[][]> arabic2farsi_plurals = new HashMap<>() {
        {
            put("arabic", new String[][]{
                    new String[]{"جوایز", "جوائز", "هدایا", "هدایای"}
                    , new String[]{"اماکن", "مراکز", "دفاتر"}
                    , new String[]{"خدمات"}
                    , new String[]{"پیشنهادات", "پیشنهادی"}
                    , new String[]{"سوالات", "سؤالات"}
                    , new String[]{"نظرات"}
                    , new String[]{"اضافات"}
                    , new String[]{"مکالمات"}
                    , new String[]{"گزارشات"}
                    , new String[]{"الزامات", "اجبارات"}
                    , new String[]{"اقسام", "انواع"}
                    , new String[]{"اجناس"}
                    , new String[]{"قبوض"}
                    , new String[]{"مبالغ"}
                    , new String[]{"اعداد"}
                    , new String[]{"مخارج"}
                    , new String[]{"مناطق", "نواحی"}
                    , new String[]{"مطالبات"}
                    , new String[]{"سوابق"}
                    , new String[]{"مکالماتی"}
                    , new String[]{"احوال"}
            });
            put("farsi", new String[][]{
                    new String[]{"جایزه ها"}
                    , new String[]{"دفتر ها"}
                    , new String[]{"خدمت"}
                    , new String[]{"پیشنهاد ها"}
                    , new String[]{"سوال ها"}
                    , new String[]{"نظر ها"}
                    , new String[]{"اضافه ها"}
                    , new String[]{"مکالمه ها"}
                    , new String[]{"گزارش ها"}
                    , new String[]{"الزام ها"}
                    , new String[]{"نوع ها"}
                    , new String[]{"جنس ها"}
                    , new String[]{"قبض ها"}
                    , new String[]{"مبلغ ها"}
                    , new String[]{"عدد ها"}
                    , new String[]{"خرج ها"}
                    , new String[]{"منطقه ها"}
                    , new String[]{"طلب ها"}
                    , new String[]{"سابقه ها"}
                    , new String[]{"مکالمه ای"}
                    , new String[]{"حالت ها"}
            });
        }
    };

    static HashMap<String, String[][]> vip_keywords = new HashMap<>() {
        {
            //############# FINAL_TERM
            put("FINAL_TERM", new String[][]{new String[]{"صورتحساب", "پایان", "دوره"}
                    , new String[]{"صورتحساب", "آخر", "ماه"}
                    , new String[]{"صورتحساب", "پایان", "ماه"}});
            //############# MID_TERM
            put("MID_TERM", new String[][]{new String[]{"صورتحساب", "میان", "دوره"}});
            //############# INSTALLMENT
            put("INSTALLMENT", new String[][]{new String[]{"قسط"}, new String[]{"تقسیط"}});
            //############# INCREASE_CREDIT
            put("INCREASE_CREDIT", new String[][]{new String[]{"بستانکار"}
                    , new String[]{"افزایش", "بدهی "}
                    , new String[]{"کاهش", "بدهی "}
                    , new String[]{"افزایش", "بدهیااا"}
                    , new String[]{"کاهش", "بدهیااا"}
                    , new String[]{"افزایش", "طلب"}
                    , new String[]{"کاهش", "طلب"}});
            //############# BILL_HISTORY
            put("BILL_HISTORY", new String[][]{new String[]{"تاریخچه", "صورتحساب"}
                    , new String[]{"پرداخت", "قبل"}
                    , new String[]{"صورتحساب", "قبل"}
                    , new String[]{"صورتحساب", "پیشین"}
                    , new String[]{"پرداخت", "لیست"}
                    , new String[]{"صورتحساب", "فهرست"}
                    , new String[]{"صورتحساب", "لیست"}
                    , new String[]{"پرداخت", "فهرست"}
                    , new String[]{"پرداخت", "پیشین"}
                    , new String[]{"پرداخت", "گذشته"}
                    , new String[]{"تاریخچه", "پرداخت"}});
            //############# EMERGENCY_UMBRELLA
            put("EMERGENCY_UMBRELLA", new String[][]{
                    new String[]{"چتر"}
                    , new String[]{"اضطراری"}
                    , new String[]{"تماس", "درخواست"}
                    , new String[]{"شارژ", "فوری"}
                    , new String[]{"تماس", "فوری"}
            });
            //############# CHARGE_HISTORY
            put("CHARGE_HISTORY", new String[][]{
                    new String[]{"تاریخچه", "شارژ"}
                    , new String[]{"سابقه", "شارژ"}
                    , new String[]{"خرید", "شارژ", "ها", "شده"}
                    , new String[]{"شارژ", "قبلی"}
                    , new String[]{"شارژ", "خلاصه"}
                    , new String[]{"شارژ", "اخیر"}
            });
            //############# FIROOZEI
            put("FIROOZEI", new String[][]{
                    new String[]{"مدار"}
                    , new String[]{"فیروزه", "مدار"}
                    , new String[]{"فیروزه", "باشگاه"}
            });
            //############# GAME
            put("Game", new String[][]{
                    new String[]{"بازی"}
                    , new String[]{"سرگرمی"}
                    , new String[]{"فان"}
                    , new String[]{"فهرست", "برندگان"}
                    , new String[]{"لیست", "برندگان"}
            });
            //############# CHARITY
            put("Charity", new String[][]{
                    new String[]{"نیکوکاری"}
                    , new String[]{"خیریه"}
                    , new String[]{"کار خیر"}
                    , new String[]{"طرح", "مشارکت", "خیر"}
                    , new String[]{"طرح", "شرکت", "خیر"}
            });
            //############# CLUB_REQUEST_SCORE
            put("CLUB_REQUEST_SCORE", new String[][]{
                    new String[]{"امتیازگیری"}
                    , new String[]{"امتیاز گیری"}
                    , new String[]{"امتیاز", "دریافت"}
                    , new String[]{"افزایش", "امتیاز"}
                    , new String[]{"زیاد", "امتیاز"}
                    , new String[]{"بالا", "امتیاز"}
                    , new String[]{"باشگاه", "امتیاز"}
                    , new String[]{"اضافه", "امتیاز"}
            });
            //############# INCENTIVE_PLAN
            put("INCENTIVE_PLAN", new String[][]{
                    new String[]{"تشویق", "طرح"}
                    , new String[]{"رایگان"}
                    , new String[]{"مجانی"}
                    , new String[]{"هدیه"}
                    , new String[]{"کادو"}
                    , new String[]{"جایزه"}
                    , new String[]{"شتاب در پرداخت"}
                    , new String[]{"پرداخت", "به موقع"}
                    , new String[]{"فیروزه", "باشگاه", "امتیاز"}
                    , new String[]{" تولد"}
                    , new String[]{"مشوق", "طرح"}
            });
            //############# BUY_NET_PACKAGE
            put("BUY_NET_PACKAGE", new String[][]{
                    new String[]{"نوترینو"}
                    , new String[]{"صبحانت", "صبحانه"}
                    , new String[]{"آلفا"}
            });
            //############# BUY_DESIRED_PACKAGE
            put("BUY_DESIRED_PACKAGE", new String[][]{
                    new String[]{"دلخواه", "بسته"}
                    , new String[]{"بسته", "ترکیبی"}
                    , new String[]{"باندل"}
                    , new String[]{"خرید", "ترکیبی"}
                    , new String[]{"خرید", "دلخواه"}
            });
            //############# SPEED_TEST
            put("SPEED_TEST", new String[][]{
                    new String[]{"سرعتسنج"}
                    , new String[]{"سرعت", "اینترنت"}
                    , new String[]{"سرعت", "دانلود"}
                    , new String[]{"سرعت", "آپلود"}
                    , new String[]{"نرخ", "دانلود"}
                    , new String[]{"نرخ", "آپلود"}
                    , new String[]{"اسپید"}
                    , new String[]{"اینترنت", "تست"}
                    , new String[]{"اینترنت", "سنجش"}
                    , new String[]{"آزمون", "اینترنت"}
                    , new String[]{" نت", "تست"}
                    , new String[]{"آزمون", " نت"}
                    , new String[]{"سنجش", " نت"}
            });
            //############# RBT
            put("RBT", new String[][]{
                    new String[]{"انتظار", "آوا"}
                    , new String[]{"آهنگ"}
                    , new String[]{"موزیک"}
                    , new String[]{"موسیقی"}
                    , new String[]{"پیشواز"}
                    , new String[]{"پیش واز"}
                    , new String[]{"انتظار", "آهنگ"}
                    , new String[]{"پخش", "کن"}
                    , new String[]{"پخش", "بشه"}
                    , new String[]{"پخش", "بشود"}
                    , new String[]{"قرآن"}
            });
            //############# BULK_SMS
            put("BULK_SMS", new String[][]{
                    new String[]{"پیامک انبوه"}
                    , new String[]{"تبلیغ", "پیامک"}
                    , new String[]{"رسانی", "اطلاع", "پیامک"}
                    , new String[]{"تبلیغ", "پیام"}
                    , new String[]{"تبلیغ", "حذف"}
                    , new String[]{"غیرفعال", "پیامک"}
                    , new String[]{"حذف", "پیامک"}
            });
            //############# MCA
            put("MCA", new String[][]{
                    new String[]{"تماسبان"}
                    , new String[]{"تماس", "از", "دست", "رفته"}
                    , new String[]{"میسکال"}
                    , new String[]{"شده", "میس", "تماس"}
                    , new String[]{"شده", "میس", "پیامک"}
                    , new String[]{"غیرفعال", "پیامک"}
                    , new String[]{"پیامک", "از", "دست", "رفته"}
            });
            //############# ROAMING
            put("ROAMING", new String[][]{
                    new String[]{"رومینگ"}
                    , new String[]{"رمینگ"}
            });
            //############# VOICEMAIL
            put("VOICEMAIL", new String[][]{
                    new String[]{"پیامگیر", "صوتی"}
                    , new String[]{"صوتی", "پیام"}
                    , new String[]{"صندوق", "صوتی"}
                    , new String[]{"صندوق", "پیام"}
                    , new String[]{"تلفن", "منشی"}
                    , new String[]{"وضعیت", "پیامگیر"}
                    , new String[]{"پیامگیر"}
            });
            //############# VAS
            put("VAS", new String[][]{
                    new String[]{"ارزش افزوده"}
                    , new String[]{"خدمت", "محتوا"}
                    , new String[]{"محتوا", "سرویس"}
                    , new String[]{"اااوسااا"}
                    , new String[]{" وسااا"}
                    , new String[]{"اااوس "}
                    , new String[]{" وس "}
            });
            //############# CODES
            put("CODES", new String[][]{
                    new String[]{"کد ", "خرید"}
                    , new String[]{"کد ", "صورتحساب"}
                    , new String[]{"خرید", " کد"}
                    , new String[]{"صورتحساب", " کد"}
                    , new String[]{"کد", "دستوری"}
                    , new String[]{"بسته", " کد"}
                    , new String[]{" بسته", "کد"}
                    , new String[]{"شارژ", " کد"}
                    , new String[]{" شارژ", "کد"}
            });
            //############# CALL_RESTRICTION
            put("CALL_RESTRICTION", new String[][]{
                    new String[]{"مکالمه", "محدود"}
                    , new String[]{"خارجی", "تماس"}
                    , new String[]{"تماس", "محدود"}
            });
            //############# SIM_STATUS
            put("SIM_STATUS", new String[][]{
                    new String[]{"وضعیت", "سیمکارت"}
                    , new String[]{"مدیریت", "سیمکارت"}
                    , new String[]{"وضعیت", "سیم"}
                    , new String[]{"مدیریت", "سیم"}
                    , new String[]{"وضعیت", "خط"}
                    , new String[]{"من", "سیمکارت"}
                    , new String[]{"سیمکارتم"}
                    , new String[]{"قطع", "سیمکارت"}
                    , new String[]{"وصل", "سیمکارت"}
                    , new String[]{"قطع", "سیم"}
                    , new String[]{"وصل", "سیم"}
                    , new String[]{"قطع", "خط"}
                    , new String[]{"وصل", "خط"}
            });
            //############# PRE_TO_POST
            put("PRE_TO_POST", new String[][]{
                    new String[]{"سیمکارت", "تبدیل"}
                    , new String[]{"اعتباری", "دائمی"}
                    , new String[]{"اعتباری", "تبدیل"}
                    , new String[]{"دائمی", "تبدیل"}
                    , new String[]{"دائمی", "تغییر"}
                    , new String[]{"اعتباری", "تغییر", "سیمکارت"}
            });
            //############# FREE_TARIFF
            put("FREE_TARIFF", new String[][]{
                    new String[]{"تعرفه", "آزاد"}
                    , new String[]{"اینترنت", "آزاد"}
                    , new String[]{"نت", "آزاد"}
            });
            //############# OFFICE
            put("OFFICE", new String[][]{
                    new String[]{"خدمت", "دفتر"}
                    , new String[]{"اطراف", "دفتر"}
                    , new String[]{"همراه", "دفتر"}
                    , new String[]{"خدمت", "مرکز"}
                    , new String[]{"اطراف", "مرکز"}
                    , new String[]{"همراه", "مرکز"}
                    , new String[]{"نزدیک", "دفتر"}
                    , new String[]{"نزدیک", "مرکز"}
                    , new String[]{"خدمت", "کجا"}
                    , new String[]{"مرکز", "کجا"}
            });
            //############# SUPPORT_NETWORK_AREA
            put("SUPPORT_NETWORK_AREA", new String[][]{
                    new String[]{"اینترنت", "پوشش"}
                    , new String[]{"اینترنت", "ساپورت"}
                    , new String[]{"پوشش", "محدوده"}
                    , new String[]{"ساپورت", "محدوده"}
                    , new String[]{"پوشش", "ناحیه"}
                    , new String[]{"ساپورت", "ناحیه"}
                    , new String[]{"اینترنت", "منطقه"}
                    , new String[]{"پوشش", "منطقه"}
                    , new String[]{"اینترنت", "ناحیه"}
                    , new String[]{"اینترنت", "محدوده"}
                    , new String[]{"اینترنت", "منطقه"}
                    , new String[]{"آنتن"}
                    , new String[]{"پوشش", "کجا"}
                    , new String[]{"ساپورت", "کجا"}
                    , new String[]{"کییفیت", "منطقه"}
                    , new String[]{"کییفیت", "ناحیه"}
            });
            put("TRANSACTION", new String[][]{new String[]{"تراکنش", "کیف"},
                    new String[]{"تراکنش", "ولت"},
                    new String[]{"تراکنش", "والت"},
                    new String[]{"تراکنش", "لیست"},
                    new String[]{"تراکنش", "فهرست"}
            });
            put("MY_WALLET", new String[][]{new String[]{"کیف "},
                    new String[]{" ولت "},
                    new String[]{" ولتااا"},
                    new String[]{"اااولت "},
                    new String[]{"والت"},
                    new String[]{"پول", "کیف"},
                    new String[]{"شارژ", "کیف"},
                    new String[]{"موجودی", "کیف"}
            });

            put("TRANSFER_CREDIT", new String[][]{new String[]{"اعتبار", "انتقال"},
                    new String[]{"شارژ", "انتقال"},
                    new String[]{"اعتبار", "ارسال"},
                    new String[]{"شارژ", "ارسال"},
                    new String[]{"اعتبار", "فرست"},
                    new String[]{"پول", "کیف"},
                    new String[]{"شارژ", "کیف"},
                    new String[]{"شارژ", "فرست"}
            });
            put("SURVEY", new String[][]{new String[]{"نظرسنجی"},
                    new String[]{"پرسشنامه"}
            });
            put("SUGGESTIONS", new String[][]{new String[]{"پیشنهادات"},
                    new String[]{"انتقادات"},
                    new String[]{"ایده"},
                    new String[]{"نظرات"},
                    new String[]{"نظر"},
                    new String[]{"پیشنهاد", "دارم"},
                    new String[]{"پیشنهاد", "بدم"},
                    new String[]{"انتقاد"},
                    new String[]{"فیدبک"}
            });

            put("QA", new String[][]{new String[]{"پشتیبان"},
                    new String[]{"کمک"},
                    new String[]{"سوال"},
                    new String[]{"مشکل"},
                    new String[]{"راهنمایی"},
                    new String[]{"چگونه", "شارژ"},
                    new String[]{"چگونه", "بسته"},
                    new String[]{"چگونه", "صورتحساب"},
                    new String[]{"چجوری", "شارژ"},
                    new String[]{"چجوری", "بسته"},
                    new String[]{"چجوری", "صورتحساب"}
            });
            put("BLACK_LIST", new String[][]{new String[]{"فهرست", "سیاه"},
                    new String[]{"لیست", "سیاه"},
                    new String[]{"فهرست", "ممنوعه"},
                    new String[]{"لیست", "ممنوعه"},
                    new String[]{"بلک لیست"},
                    new String[]{"بلاک"}
            });

        }
    };

    public static void add_y_to_arabic() {
        for (int i = 0; i < arabic2farsi_plurals.get("arabic").length; i++) {
            ArrayList<String> my_list = new ArrayList<>(Arrays.asList(arabic2farsi_plurals.get("arabic")[i]));
            ArrayList<String> res = new ArrayList<>(my_list);
            for (String s : my_list) {
                boolean contains = my_list.contains(s + "ی");
                if (!contains) {
                    res.add(s + "ی");
                }
            }
            arabic2farsi_plurals.get("arabic")[i] = res.toArray(new String[0]);
        }
    }

    public static HashMap<String, ArrayList<Double>> sub_wordak = new HashMap<>();

    public static void read_wordak() {
        String text = "";
        try {
            text = new String(Files.readAllBytes(Paths.get("sub_wordak.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] tokens = text.split("\\),");
        for (String token : tokens) {
            String key = token.split(":")[0];
            key = key.substring(2, key.length() - 1);
            token = token.substring(token.indexOf('[') + 1, token.indexOf(']'));
            String[] numbers = token.split(",(\\s|\\n)");
            ArrayList<Double> vector = new ArrayList<>();
            for (String number : numbers) {
                vector.add(Double.parseDouble(number));
            }
            sub_wordak.put(key, vector);
        }

    }

    public static String replace_by_legal_subword(String sentence) {
        HashSet<String> legal_words = new HashSet<>(sub_wordak.keySet());
        String[] words = sentence.split(" ");
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (legal_words.contains(words[i])) {
                int curr_max = 0;
                String replacement = "[نامعلوم]";
                for (String legalWord : legal_words) {
                    if (words[i].contains(legalWord) && legalWord.length() > curr_max) {
                        replacement = legalWord;
                        curr_max = legalWord.length();
                    }
                }
                if ((words[i].length() - replacement.length()) / (words[i].length() * 1.0) <= 0.5) {
                    words[i] = replacement;
                } else {
                    words[i] = "[نامعلوم]";
                }
                res.append(words[i]).append(" ");
            }
        }
        return res.toString().trim();

    }

    public static void main(String[] args) {
        read_wordak();
        System.out.println(sub_wordak.get("آئینی"));
        System.out.println(replace_by_legal_subword("سلام خخ میخوام"));
//        System.out.println("hey");
//        System.out.println("a\u200cb");
//        System.out.println(vip_keywords.get("INCREASE_CREDIT")[0][0]);
//        add_y_to_arabic();
//        System.out.println(Arrays.toString(arabic2farsi_plurals.get("arabic")[0]));

    }

    public static String nim_fasele_removal(String sentence) {
        return sentence.replace("\u200c", " ");
    }


    public static String arabic2farsi_plural_replacement(String sentence) {
        for (int i = 0; i < arabic2farsi_plurals.get("arabic").length; i++) {
            for (String s : arabic2farsi_plurals.get("arabic")[i]) {
                sentence = sentence.replace(s, arabic2farsi_plurals.get("farsi")[i][0]);
            }
        }
        return sentence;
    }

    public static String word_concatenation(String sentence) {


        HashMap<String, String> good_bad = new HashMap<>();
        good_bad.put("دیگه", "دیگر");
        good_bad.put("یه کم", "کمی");
        good_bad.put("یه مقدار", "کمی");
        good_bad.put("یه کمی", "کمی");
        good_bad.put("یه مقداری", "کمی");
        good_bad.put("سیم کارت", "سیمکارت");
        good_bad.put("صورت حساب", "صورتحساب");
        good_bad.put("تماس بان", "تماسبان");
        good_bad.put("کیفیت", "کییفیت");
        good_bad.put("دایمی", "دائمی");
        good_bad.put("پیغام گیر", "پیامگیر");
        good_bad.put("پیام گی", "'پیامگیر");
        good_bad.put("قبض", "صورتحساب");
        good_bad.put("پرسش نامه", "پرسشنامه");
        good_bad.put("نظر سنجی", "نظرسنجی");
        good_bad.put("غیر فعال", "غیرفعال");
        good_bad.put("اس ام اس", "پیامک");
        good_bad.put("میس کال", "میسکال");
        good_bad.put("نیکو کاری", "نیکوکاری");
        good_bad.put("پیغام", "پیام");
        good_bad.put("تاریخ چه", "تاریخچه");
        good_bad.put("چه میزان", "چقدر");
        good_bad.put("چه مقدار", "چقدر");
        good_bad.put("چه قدر", "چقدر");
        good_bad.put("می خوام", "میخوام");
        good_bad.put("سرعت سنج", "سرعتسنج");
        good_bad.put("پکیج", "بسته");
        good_bad.put("بدهکار", "بستانکار");
        good_bad.put("طلب کار", "بستانکار");
        good_bad.put("طلبکار", "بستانکار");
        good_bad.put("بستان کاری", "بستانکاری");
        good_bad.put("نشون", "نشان");
        for (String s : good_bad.keySet()) {
            sentence = sentence.replace(s, good_bad.get(s));
        }
        return sentence;

    }

    public static String ha_hay_removal(String sentence) {
        ArrayList<String> ok_dictionary = new ArrayList<>();
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
        String[] words = sentence.split(" ");
        StringBuilder res = new StringBuilder();
        for (String word : words) {
            if (((word.endsWith("ها") && word.length() != 2) || (word.endsWith("های") && word.length() != 3) ||
                    (word.endsWith("هائی") && word.length() != 4) ||
                    (word.endsWith("هایی") && word.length() != 4)) && !ok_dictionary.contains(word)) {
                if (word.endsWith("ها")) {
                    res.append(word, 0, word.length() - 2).append(" ").append("ها").append(" ");
                }
                if (word.endsWith("های")) {
                    res.append(word, 0, word.length() - 3).append(" ").append("های").append(" ");
                }
                if (word.endsWith("هایی") || word.endsWith("هائی")) {
                    res.append(word, 0, word.length() - 4).append(" ").append("هایی").append(" ");
                }
            } else {
                res.append(word).append(" ");
            }
        }
        return res.toString().strip();
    }
}
