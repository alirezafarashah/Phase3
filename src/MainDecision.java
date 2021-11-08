import java.util.HashMap;

public class MainDecision {
    public boolean all_kw_match(String sentence, String[] kw_list) {
        boolean res = true;
        for (String s : kw_list) {
            if (!sentence.contains(s)) {
                res = false;
                break;
            }
        }
        return res;
    }

    public boolean any_kw_match(String sentence, String[] kw_list) {
        boolean res = false;
        for (String s : kw_list) {
            if (!sentence.contains(s)) {
                res = true;
                break;
            }
        }
        return res;
    }

    int vip_score = 20;
    int special_score = 5;
    float epsilon = (float) (1.0 / 48);

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

        }


        return rule_based_probs;

    }

}
