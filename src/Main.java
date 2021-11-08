import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, String[][]> vip_keywords = new HashMap<>();
        vip_keywords.put("TRANSACTION", new String[][]{new String[]{"تراکنش", "کیف"},
                new String[]{"تراکنش", "ولت"},
                new String[]{"تراکنش", "والت"},
                new String[]{"تراکنش", "لیست"},
                new String[]{"تراکنش", "فهرست"}
        });
        vip_keywords.put("MY_WALLET", new String[][]{new String[]{"کیف "},
                new String[]{" ولت "},
                new String[]{" ولتااا"},
                new String[]{"اااولت "},
                new String[]{"والت"},
                new String[]{"پول", "کیف"},
                new String[]{"شارژ", "کیف"},
                new String[]{"موجودی", "کیف"}
        });
        vip_keywords.put("TRANSFER_CREDIT", new String[][]{new String[]{"اعتبار", "انتقال"},
                new String[]{"شارژ", "انتقال"},
                new String[]{"اعتبار", "ارسال"},
                new String[]{"شارژ", "ارسال"},
                new String[]{"اعتبار", "فرست"},
                new String[]{"پول", "کیف"},
                new String[]{"شارژ", "کیف"},
                new String[]{"شارژ", "فرست"}
        });
        vip_keywords.put("SURVEY", new String[][]{new String[]{"نظرسنجی"},
                new String[]{"پرسشنامه"}
        });
        vip_keywords.put("SUGGESTIONS", new String[][]{new String[]{"پیشنهادات"},
                new String[]{"انتقادات"},
                new String[]{"ایده"},
                new String[]{"نظرات"},
                new String[]{"نظر"},
                new String[]{"پیشنهاد", "دارم"},
                new String[]{"پیشنهاد", "بدم"},
                new String[]{"انتقاد"},
                new String[]{"فیدبک"}
        });

        vip_keywords.put("QA", new String[][]{new String[]{"پشتیبان"},
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
        vip_keywords.put("BLACK_LIST", new String[][]{new String[]{"فهرست", "سیاه"},
                new String[]{"لیست", "سیاه"},
                new String[]{"فهرست", "ممنوعه"},
                new String[]{"لیست", "ممنوعه"},
                new String[]{"بلک لیست"},
                new String[]{"بلاک"}
        });

        vip_keywords.put("SUPPORT_NETWORK_AREA", new String[][]{new String[]{"اینترنت", "پوشش"},
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
}