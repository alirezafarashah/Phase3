import java.util.HashMap;

public class MainDecision {
    public static boolean all_kw_match(String sentence, String[] kw_list) {
        boolean res = true;
        for (String s : kw_list) {
            if (!sentence.contains(s)) {
                res = false;
                break;
            }
        }
        return res;
    }

    public static boolean any_kw_match(String sentence, String[] kw_list) {
        boolean res = false;
        for (String s : kw_list) {
            if (!sentence.contains(s)) {
                res = true;
                break;
            }
        }
        return res;
    }

    static int vip_score = 20;
    static int special_score = 5;
    static float epsilon = (float) (1.0 / 48);

    static HashMap<String, String> subcat2cat = new HashMap<>() {{

        subcat2cat.put("FINAL_TERM", "BILL");
        subcat2cat.put("MID_TERM", "BILL");
        subcat2cat.put("INSTALLMENT", "BILL");
        subcat2cat.put("INCREASE_CREDIT", "BILL");
        subcat2cat.put("BILL_HISTORY", "BILL");
        subcat2cat.put("CHARGE_REMAINS", "CHARGE");
        subcat2cat.put("BUY_CHARGE", "CHARGE");
        subcat2cat.put("EMERGENCY_UMBRELLA", "CHARGE");
        subcat2cat.put("CHARGE_HISTORY", "CHARGE");
        subcat2cat.put("FIROOZEI", "CLUB");
        subcat2cat.put("GAME", "CLUB");
        subcat2cat.put("CHARITY", "CLUB");
        subcat2cat.put("CUSTOMER_CLUB", "CLUB");
        subcat2cat.put("CLUB_REQUEST_SCORE", "CLUB");
        subcat2cat.put("CLUB_GUIDE", "CLUB");
        subcat2cat.put("CLUB_REPORTS", "CLUB");
        subcat2cat.put("CLUB_INVITE", "CLUB");
        subcat2cat.put("CLUB_GIFTS", "CLUB");
        subcat2cat.put("INCENTIVE_PLAN", "GIFT");
        subcat2cat.put("BUY_PACKAGE", "PACKAGE");
        subcat2cat.put("BUY_NET_PACKAGE", "PACKAGE");
        subcat2cat.put("BUY_SMS_PACKAGE", "PACKAGE");
        subcat2cat.put("BUY_VOICE_PACKAGE", "PACKAGE");
        subcat2cat.put("BUY_DESIRED_PACKAGE", "PACKAGE");
        subcat2cat.put("ACTIVE_PACKAGE", "PACKAGE");
        subcat2cat.put("SPEED_TEST", "SERVICE");
        subcat2cat.put("RBT", "SERVICE");
        subcat2cat.put("BULK_SMS", "SERVICE");
        subcat2cat.put("MCA", "SERVICE");
        subcat2cat.put("ROAMING", "SERVICE");
        subcat2cat.put("VOICEMAIL", "SERVICE");
        subcat2cat.put("VAS", "SERVICE");
        subcat2cat.put("CODES", "SERVICE");
        subcat2cat.put("CALL_RESTRICTION", "SERVICE");
        subcat2cat.put("SIM_STATUS", "SERVICE");
        subcat2cat.put("PRE_TO_POST", "SERVICE");
        subcat2cat.put("FREE_TARIFF", "SERVICE");
        subcat2cat.put("OFFICE", "SERVICE");
        subcat2cat.put("SUPPORT_NETWORK_AREA", "SERVICE");
        subcat2cat.put("BLACK_LIST", "SERVICE");
        subcat2cat.put("QA", "SUPPORT");
        subcat2cat.put("SUGGESTIONS", "SUPPORT");
        subcat2cat.put("SURVEY", "SUPPORT");
        subcat2cat.put("TRANSFER_CREDIT", "TRANSFER");
        subcat2cat.put("MY_WALLET", "WALLET");
        subcat2cat.put("CHARGE_WALLET", "WALLET");
        subcat2cat.put("TRANSACTION", "WALLET");
        subcat2cat.put("OTHER", "OTHER");
    }};
    static String[] subcats = {"FINAL_TERM",
            "MID_TERM",
            "INSTALLMENT",
            "INCREASE_CREDIT",
            "BILL_HISTORY",
            "CHARGE_REMAINS",
            "BUY_CHARGE",
            "EMERGENCY_UMBRELLA",
            "CHARGE_HISTORY",
            "FIROOZEI",
            "GAME",
            "CHARITY",
            "CUSTOMER_CLUB",
            "CLUB_REQUEST_SCORE",
            "CLUB_GUIDE",
            "CLUB_REPORTS",
            "CLUB_INVITE",
            "CLUB_GIFTS",
            "INCENTIVE_PLAN",
            "ACTIVE_PACKAGE",
            "BUY_PACKAGE",
            "BUY_NET_PACKAGE",
            "BUY_SMS_PACKAGE",
            "BUY_VOICE_PACKAGE",
            "BUY_DESIRED_PACKAGE",
            "SPEED_TEST",
            "RBT",
            "BULK_SMS",
            "MCA",
            "ROAMING",
            "VOICEMAIL",
            "VAS",
            "CODES",
            "CALL_RESTRICTION",
            "SIM_STATUS",
            "PRE_TO_POST",
            "FREE_TARIFF",
            "OFFICE",
            "SUPPORT_NETWORK_AREA",
            "BLACK_LIST",
            "QA",
            "SUGGESTIONS",
            "SURVEY",
            "TRANSFER_CREDIT",
            "MY_WALLET",
            "CHARGE_WALLET",
            "TRANSACTION",
            "OTHER"};
    static HashMap<String, String[]> cat2subcat = new HashMap<>() {
        {
            cat2subcat.put("BILL", new String[]{"FINAL_TERM", "MID_TERM", "INSTALLMENT", "INCREASE_CREDIT", "BILL_HISTORY"});
            cat2subcat.put("CHARGE", new String[]{"CHARGE_REMAINS", "BUY_CHARGE", "EMERGENCY_UMBRELLA", "CHARGE_HISTORY"});
            cat2subcat.put("CLUB", new String[]{"FIROOZEI", "GAME", "CHARITY", "CUSTOMER_CLUB", "CLUB_REQUEST_SCORE", "CLUB_GUIDE", "CLUB_REPORTS", "CLUB_INVITE", "CLUB_GIFTS"});
            cat2subcat.put("GIFT", new String[]{"INCENTIVE_PLAN"});
            cat2subcat.put("PACKAGE", new String[]{"ACTIVE_PACKAGE", "BUY_PACKAGE", "BUY_NET_PACKAGE", "BUY_SMS_PACKAGE", "BUY_VOICE_PACKAGE", "BUY_DESIRED_PACKAGE"});
            cat2subcat.put("SERVICE", new String[]{"SPEED_TEST", "RBT", "BULK_SMS", "MCA", "ROAMING", "VOICEMAIL", "VAS", "CODES", "CALL_RESTRICTION", "SIM_STATUS", "PRE_TO_POST", "FREE_TARIFF", "OFFICE", "SUPPORT_NETWORK_AREA", "BLACK_LIST"});
            cat2subcat.put("SUPPORT", new String[]{"QA", "SUGGESTIONS", "SURVEY"});
            cat2subcat.put("TRANSFER", new String[]{"TRANSFER_CREDIT"});
            cat2subcat.put("WALLET", new String[]{"MY_WALLET", "CHARGE_WALLET", "TRANSACTION"});
            cat2subcat.put("OTHER", new String[]{"OTHER"});
        }
    };

    public static HashMap<String, Float> decisions_probs(String sentence) {
        HashMap<String, Float> rule_based_probs = new HashMap<>();
        for (String s : subcats) {
            rule_based_probs.put(s, (float) 0.0);
        }
        sentence = Preprocess.nim_fasele_removal(sentence);
        sentence = Preprocess.arabic2farsi_plural_replacement(sentence);
        sentence = Preprocess.word_concatenation(sentence);
        sentence = Preprocess.ha_hay_removal(sentence);
        sentence = "ااا" + sentence + "ااا";

        boolean is_there_conflict = false;
        boolean vip_detected = false;

        for (String s : Preprocess.vip_keywords.keySet()) {
            ArrayList<Boolean> match_result = new ArrayList<>();
            for (String[] strings : Preprocess.vip_keywords.get(s)) {
                boolean temp = all_kw_match(sentence,strings);
                match_result.add(temp);
            }
            if (match_result.contains(true)){
                rule_based_probs.replace(s,rule_based_probs.get(s)+vip_score);
                if (!vip_detected) {
                    vip_detected = true;
                }
                else {
                    is_there_conflict = true;
                }
            }
        }
        HashMap<String,String []> action_type_keywords = new HashMap<>();
        HashMap<String,String []> comm_type_keywords = new HashMap<>();

        action_type_keywords.put("demand", new String[]{"میخوام","بخر","خرید","تقاضا","بگیر",
                "خواستار","افزایش","تهیه","دریافت","کاهش"});
        action_type_keywords.put("status", new String[]{"ببین","مشاهده","دیدن"," چک ","چقدر","وضعیت",
                "باقیمانده","مانده","اااچه "," چه ","نمایش","چند","نشان","آیا "});
        action_type_keywords.put("price", new String[]{"ریال","تومان","تومن"});

        comm_type_keywords.put("call", new String[]{"تماس","مکالمه"});
        comm_type_keywords.put("sms", new String[]{"پیامک"});
        comm_type_keywords.put("internet", new String[]{"اینترنت","ااانت "," نتااا"," نت ","حجم"});

        HashMap<String,Integer> comm_type_flag = new HashMap<>();
        for (String s : comm_type_keywords.keySet()) {
            comm_type_flag.put(s,0);
        }

        HashMap<String,Integer> action_type_flag = new HashMap<>();
        for (String s : action_type_keywords.keySet()) {
            action_type_flag.put(s,0);
        }
        for (String s : action_type_keywords.keySet()) {
            if (any_kw_match(sentence,action_type_keywords.get(s))){
                action_type_flag.replace(s,1);
            }
        }
        for (String s : comm_type_keywords.keySet()) {
            if (any_kw_match(sentence,comm_type_keywords.get(s))){
                comm_type_flag.replace(s,1);
            }
        }

/////////////////////////////////
        if (rule_based_probs.get("SUPPORT_NETWORK_AREA") != 0 && rule_based_probs.get("BUY_NET_PACKAGE") != 0){
            rule_based_probs.replace("BUY_NET_PACKAGE", 0.0F);
        }
        if (rule_based_probs.get("SURVEY") != 0 && rule_based_probs.get("SUGGESTIONS") != 0){
            rule_based_probs.replace("SUGGESTIONS", 0.0F);
        }
        if (rule_based_probs.get("SIM_STATUS") != 0 && any_kw_match(sentence,new String[]{"شارژ","مصرف"})){
            rule_based_probs.replace("SIM_STATUS", 0.0F);
        }


////////////////////////////
        if (any_kw_match(sentence, new String[]{"منتقل","انتقال"})&& action_type_flag.get("price") == 1){
            rule_based_probs.replace("TRANSFER_CREDIT",rule_based_probs.get("TRANSFER_CREDIT")+vip_score);
        }

        ArrayList<Boolean> temp= new ArrayList<>();
        for (String kw : new String[]{"باشگاه", "امتیاز"}) {
            if (sentence.contains(kw)){
                temp.add(true);
            }
            else {
                temp.add(false);
            }
        }
        if (temp.contains(true) && (action_type_flag.get("price") == 1 ||action_type_flag.get("status")==1)){
            rule_based_probs.replace("CLUB_REQUEST_SCORE",rule_based_probs.get("CLUB_REQUEST_SCORE")+vip_score);
        }




        ///////////////////////////////////////////////////////
        //////////////// Special Keyword stage ////////////////
        ///////////////////////////////////////////////////////

        String[] special_kw_farsi = new String[]{"صورتحساب", "شارژ", "باشگاه", "بسته"};
        String[] special_category = new String[]{"BILL", "CHARGE", "CLUB", "PACKAGE"};

        TreeSet<String> curr_sc_1 = new TreeSet<String>();
        TreeSet<String> curr_sc_2 = new TreeSet<String>();
        for (int i = 0; i < special_kw_farsi.length; i++) {
            if (sentence.contains(special_kw_farsi[i])) {
                for (String subcat : cat2subcat.get(special_category[i])) {
                    curr_sc_1.add(subcat);
                }
                if (special_kw_farsi[i].equals("شارژ")) {
                    boolean charge_local_flag = false;
                    if (any_kw_match(sentence, new String[] {"فرست", "منتقل", "انتقال"}) && !charge_local_flag) {
                        curr_sc_2.add("TRANSFER_CREDIT");
                        curr_sc_1.add("TRANSFER_CREDIT");
                        charge_local_flag = true;
                    }

                }
            }
        }
            return rule_based_probs;

    }

}
