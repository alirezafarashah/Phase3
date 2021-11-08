import java.util.ArrayList;
import java.util.HashMap;

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
                    new String[]{"جوایز","جوائز","هدایا","هدایای"}
                    , new String[]{"اماکن","مراکز","دفاتر"}
                    , new String[]{"خدمات"}
                    , new String[]{"پیشنهادات","پیشنهادی"}
                    , new String[]{"سوالات","سؤالات"}
                    , new String[]{"نظرات"}
                    , new String[]{"اضافات"}
                    , new String[]{"مکالمات"}
                    , new String[]{"گزارشات"}
                    , new String[]{"الزامات","اجبارات"}
                    , new String[]{"اقسام","انواع"}
                    , new String[]{"اجناس"}
                    , new String[]{"قبوض"}
                    , new String[]{"مبالغ"}
                    , new String[]{"اعداد"}
                    , new String[]{"مخارج"}
                    , new String[]{"مناطق","نواحی"}
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
                    , new String[]{"فهرست","برندگان"}
                    , new String[]{"لیست","برندگان"}
            });
            //############# CHARITY
            put("Charity", new String[][]{
                    new String[]{"نیکوکاری"}
                    , new String[]{"خیریه"}
                    , new String[]{"کار خیر"}
                    , new String[]{"طرح","مشارکت","خیر"}
                    , new String[]{"طرح","شرکت","خیر"}
            });
            //############# CLUB_REQUEST_SCORE
            put("CLUB_REQUEST_SCORE", new String[][]{
                    new String[]{"امتیازگیری"}
                    , new String[]{"امتیاز گیری"}
                    , new String[]{"امتیاز","دریافت"}
                    , new String[]{"افزایش","امتیاز"}
                    , new String[]{"زیاد","امتیاز"}
                    , new String[]{"بالا","امتیاز"}
                    , new String[]{"باشگاه","امتیاز"}
                    , new String[]{"اضافه","امتیاز"}
            });
            //############# INCENTIVE_PLAN
            put("INCENTIVE_PLAN", new String[][]{
                    new String[]{"تشویق","طرح"}
                    , new String[]{"رایگان"}
                    , new String[]{"مجانی"}
                    , new String[]{"هدیه"}
                    , new String[]{"کادو"}
                    , new String[]{"جایزه"}
                    , new String[]{"شتاب در پرداخت"}
                    , new String[]{"پرداخت","به موقع"}
                    , new String[]{"فیروزه","باشگاه","امتیاز"}
                    , new String[]{" تولد"}
                    , new String[]{"مشوق","طرح"}
            });
            //############# BUY_NET_PACKAGE
            put("BUY_NET_PACKAGE", new String[][]{
                    new String[]{"نوترینو"}
                    , new String[]{"صبحانت","صبحانه"}
                    , new String[]{"آلفا"}
            });
            //############# BUY_DESIRED_PACKAGE
            put("BUY_DESIRED_PACKAGE", new String[][]{
                    new String[]{"دلخواه","بسته"}
                    , new String[]{"بسته","ترکیبی"}
                    , new String[]{"باندل"}
                    , new String[]{"خرید","ترکیبی"}
                    , new String[]{"خرید","دلخواه"}
            });
            //############# SPEED_TEST
            put("SPEED_TEST", new String[][]{
                    new String[]{"سرعتسنج"}
                    , new String[]{"سرعت","اینترنت"}
                    , new String[]{"سرعت","دانلود"}
                    , new String[]{"سرعت","آپلود"}
                    , new String[]{"نرخ","دانلود"}
                    , new String[]{"نرخ","آپلود"}
                    , new String[]{"اسپید"}
                    , new String[]{"اینترنت","تست"}
                    , new String[]{"اینترنت","سنجش"}
                    , new String[]{"آزمون","اینترنت"}
                    , new String[]{" نت","تست"}
                    , new String[]{"آزمون"," نت"}
                    , new String[]{"سنجش"," نت"}
            });
            //############# RBT
            put("RBT", new String[][]{
                    new String[]{"انتظار","آوا"}
                    , new String[]{"آهنگ"}
                    , new String[]{"موزیک"}
                    , new String[]{"موسیقی"}
                    , new String[]{"پیشواز"}
                    , new String[]{"پیش واز"}
                    , new String[]{"انتظار","آهنگ"}
                    , new String[]{"پخش","کن"}
                    , new String[]{"پخش","بشه"}
                    , new String[]{"پخش","بشود"}
                    , new String[]{"قرآن"}
            });
            //############# BULK_SMS
            put("BULK_SMS", new String[][]{
                    new String[]{"پیامک انبوه"}
                    , new String[]{"تبلیغ","پیامک"}
                    , new String[]{"رسانی","اطلاع","پیامک"}
                    , new String[]{"تبلیغ","پیام"}
                    , new String[]{"تبلیغ","حذف"}
                    , new String[]{"غیرفعال","پیامک"}
                    , new String[]{"حذف","پیامک"}
            });
            //############# MCA
            put("MCA", new String[][]{
                    new String[]{"تماسبان"}
                    , new String[]{"تماس","از","دست","رفته"}
                    , new String[]{"میسکال"}
                    , new String[]{"شده","میس","تماس"}
                    , new String[]{"شده","میس","پیامک"}
                    , new String[]{"غیرفعال","پیامک"}
                    , new String[]{"پیامک","از","دست","رفته"}
            });
            //############# ROAMING
            put("ROAMING", new String[][]{
                    new String[]{"رومینگ"}
                    , new String[]{"رمینگ"}
            });
            //############# VOICEMAIL
            put("VOICEMAIL", new String[][]{
                    new String[]{"پیامگیر","صوتی"}
                    , new String[]{"صوتی","پیام"}
                    , new String[]{"صندوق","صوتی"}
                    , new String[]{"صندوق","پیام"}
                    , new String[]{"تلفن","منشی"}
                    , new String[]{"وضعیت","پیامگیر"}
                    , new String[]{"پیامگیر"}
            });
            //############# VAS
            put("VAS", new String[][]{
                    new String[]{"ارزش افزوده"}
                    , new String[]{"خدمت","محتوا"}
                    , new String[]{"محتوا","سرویس"}
                    , new String[]{"اااوسااا"}
                    , new String[]{" وسااا"}
                    , new String[]{"اااوس "}
                    , new String[]{" وس "}
            });
            //############# CODES
            put("CODES", new String[][]{
                    new String[]{"کد ","خرید"}
                    , new String[]{"کد ","صورتحساب"}
                    , new String[]{"خرید"," کد"}
                    , new String[]{"صورتحساب"," کد"}
                    , new String[]{"کد","دستوری"}
                    , new String[]{"بسته"," کد"}
                    , new String[]{" بسته","کد"}
                    , new String[]{"شارژ"," کد"}
                    , new String[]{" شارژ","کد"}
            });
            //############# CALL_RESTRICTION
            put("CALL_RESTRICTION", new String[][]{
                    new String[]{"مکالمه","محدود"}
                    , new String[]{"خارجی","تماس"}
                    , new String[]{"تماس","محدود"}
            });
            //############# SIM_STATUS
            put("SIM_STATUS", new String[][]{
                    new String[]{"وضعیت","سیمکارت"}
                    , new String[]{"مدیریت","سیمکارت"}
                    , new String[]{"وضعیت","سیم"}
                    , new String[]{"مدیریت","سیم"}
                    , new String[]{"وضعیت","خط"}
                    , new String[]{"من","سیمکارت"}
                    , new String[]{"سیمکارتم"}
                    , new String[]{"قطع","سیمکارت"}
                    , new String[]{"وصل","سیمکارت"}
                    , new String[]{"قطع","سیم"}
                    , new String[]{"وصل","سیم"}
                    , new String[]{"قطع","خط"}
                    , new String[]{"وصل","خط"}
            });
            //############# PRE_TO_POST
            put("PRE_TO_POST", new String[][]{
                    new String[]{"سیمکارت","تبدیل"}
                    , new String[]{"اعتباری","دائمی"}
                    , new String[]{"اعتباری","تبدیل"}
                    , new String[]{"دائمی","تبدیل"}
                    , new String[]{"دائمی","تغییر"}
                    , new String[]{"اعتباری","تغییر","سیمکارت"}
            });
            //############# FREE_TARIFF
            put("FREE_TARIFF", new String[][]{
                    new String[]{"تعرفه","آزاد"}
                    , new String[]{"اینترنت","آزاد"}
                    , new String[]{"نت","آزاد"}
            });
            //############# OFFICE
            put("OFFICE", new String[][]{
                    new String[]{"خدمت","دفتر"}
                    , new String[]{"اطراف","دفتر"}
                    , new String[]{"همراه","دفتر"}
                    , new String[]{"خدمت","مرکز"}
                    , new String[]{"اطراف","مرکز"}
                    , new String[]{"همراه","مرکز"}
                    , new String[]{"نزدیک","دفتر"}
                    , new String[]{"نزدیک","مرکز"}
                    , new String[]{"خدمت","کجا"}
                    , new String[]{"مرکز","کجا"}
            });
            //############# SUPPORT_NETWORK_AREA
            put("SUPPORT_NETWORK_AREA", new String[][]{
                    new String[]{"اینترنت","پوشش"}
                    , new String[]{"اینترنت","ساپورت"}
                    , new String[]{"پوشش","محدوده"}
                    , new String[]{"ساپورت","محدوده"}
                    , new String[]{"پوشش","ناحیه"}
                    , new String[]{"ساپورت","ناحیه"}
                    , new String[]{"اینترنت","منطقه"}
                    , new String[]{"پوشش","منطقه"}
                    , new String[]{"اینترنت","ناحیه"}
                    , new String[]{"اینترنت","محدوده"}
                    , new String[]{"اینترنت","منطقه"}
                    , new String[]{"آنتن"}
                    , new String[]{"پوشش","کجا"}
                    , new String[]{"ساپورت","کجا"}
                    , new String[]{"کییفیت","منطقه"}
                    , new String[]{"کییفیت","ناحیه"}
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
            put("SUPPORT_NETWORK_AREA", new String[][]{new String[]{"اینترنت", "پوشش"},
                    new String[]{"اینترنت", "ساپورت"},
                    new String[]{"پوشش", "محدوده"},
                    new String[]{"پوشش", "ناحیه"},
                    new String[]{"ساپورت", "ناحیه"},
                    new String[]{"اینترنت", "منطقه"},
                    new String[]{"پوشش", "منطقه"},
                    new String[]{"اینترنت", "ناحیه"},
                    new String[]{"اینترنت", "محدوده"},
                    new String[]{"اینترنت", "منطقه"},
                    new String[]{"آنتن"},
                    new String[]{"پوشش", "کجا"},
                    new String[]{"آنتن"},
                    new String[]{"ساپورت", "کجا"},
                    new String[]{"کییفیت", "منطقه"},
                    new String[]{"کییفیت", "ناحیه"}
            });

        }
    };
/*

###########################################################################################################
        ###########################################################################################################
        ###########################################################################################################
        ###########################################################################################################
        ###########################################################################################################
        ###########################################################################################################
        ###########################################################################################################

    arabic2farsi_plurals <- list(arabic = list(), farsi = character(0))

    arabic2farsi_plurals$arabic[[1]] <- c("جوایز","جوائز","هدایا","هدایای")
    arabic2farsi_plurals$farsi [[1]] <- c("جایزه ها")

    arabic2farsi_plurals$arabic[[2]] <- c("اماکن","مراکز","دفاتر")
    arabic2farsi_plurals$farsi [[2]] <- c("دفتر ها")

    arabic2farsi_plurals$arabic[[3]] <- c("خدمات")
    arabic2farsi_plurals$farsi [[3]] <- c("خدمت")

    arabic2farsi_plurals$arabic[[4]] <- c("پیشنهادات","پیشنهادی")
    arabic2farsi_plurals$farsi [[4]] <- c("پیشنهاد ها")

    arabic2farsi_plurals$arabic[[5]] <- c("سوالات","سؤالات")
    arabic2farsi_plurals$farsi [[5]] <- c("سوال ها")

    arabic2farsi_plurals$arabic[[6]] <- c("نظرات")
    arabic2farsi_plurals$farsi [[6]] <- c("نظر ها")

    arabic2farsi_plurals$arabic[[7]] <- c("اضافات")
    arabic2farsi_plurals$farsi [[7]] <- c("اضافه ها")

    arabic2farsi_plurals$arabic[[8]] <- c("مکالمات")
    arabic2farsi_plurals$farsi [[8]] <- c("مکالمه ها")

    arabic2farsi_plurals$arabic[[9]] <- c("گزارشات")
    arabic2farsi_plurals$farsi [[9]] <- c("گزارش ها")

    arabic2farsi_plurals$arabic[[10]] <- c("الزامات","اجبارات")
    arabic2farsi_plurals$farsi [[10]] <- c("الزام ها")

    arabic2farsi_plurals$arabic[[11]] <- c("اقسام","انواع")
    arabic2farsi_plurals$farsi [[11]] <- c("نوع ها")

    arabic2farsi_plurals$arabic[[12]] <- c("اجناس")
    arabic2farsi_plurals$farsi [[12]] <- c("جنس ها")

    arabic2farsi_plurals$arabic[[13]] <- c("قبوض")
    arabic2farsi_plurals$farsi [[13]] <- c("قبض ها")

    arabic2farsi_plurals$arabic[[14]] <- c("مبالغ")
    arabic2farsi_plurals$farsi [[14]] <- c("مبلغ ها")

    arabic2farsi_plurals$arabic[[15]] <- c("اعداد")
    arabic2farsi_plurals$farsi [[15]] <- c("عدد ها")

    arabic2farsi_plurals$arabic[[16]] <- c("مخارج")
    arabic2farsi_plurals$farsi [[16]] <- c("خرج ها")

    arabic2farsi_plurals$arabic[[17]] <- c("مناطق","نواحی")
    arabic2farsi_plurals$farsi [[17]] <- c("منطقه ها")

    arabic2farsi_plurals$arabic[[18]] <- c("مطالبات")
    arabic2farsi_plurals$farsi [[18]] <- c("طلب ها")

    arabic2farsi_plurals$arabic[[19]] <- c("سوابق")
    arabic2farsi_plurals$farsi [[19]] <- c("سابقه ها")

    arabic2farsi_plurals$arabic[[20]] <- c("مکالماتی")
    arabic2farsi_plurals$farsi [[20]] <- c("مکالمه ای")

    arabic2farsi_plurals$arabic[[21]] <- c("احوال")
    arabic2farsi_plurals$farsi [[21]] <- c("حالت ها")*/

    public static void main(String[] args) {
        System.out.println("hey");
        System.out.println("a\u200cb");
        System.out.println(vip_keywords.get("INCREASE_CREDIT")[0][0]);
    }
    public static String nim_fasele_removal(String sentence){
        return sentence.replace("\u200c"," ");
    }

    public static String arabic2farsi_plural_replacement(String sentence){
        //TODO
        return sentence;
    }
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