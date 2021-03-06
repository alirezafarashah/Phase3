import java.util.*;

public class MainDecision {
    public static void main(String[] args) {
        ArrayList<Boolean> temp = new ArrayList<>();
        temp.add(true);
        System.out.println(temp.contains(false));
    }

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
    static double exponent = 3;

    static HashMap<String, String> subcat2cat = new HashMap<>() {{

        put("FINAL_TERM", "BILL");
        put("MID_TERM", "BILL");
        put("INSTALLMENT", "BILL");
        put("INCREASE_CREDIT", "BILL");
        put("BILL_HISTORY", "BILL");
        put("CHARGE_REMAINS", "CHARGE");
        put("BUY_CHARGE", "CHARGE");
        put("EMERGENCY_UMBRELLA", "CHARGE");
        put("CHARGE_HISTORY", "CHARGE");
        put("FIROOZEI", "CLUB");
        put("GAME", "CLUB");
        put("CHARITY", "CLUB");
        put("CUSTOMER_CLUB", "CLUB");
        put("CLUB_REQUEST_SCORE", "CLUB");
        put("CLUB_GUIDE", "CLUB");
        put("CLUB_REPORTS", "CLUB");
        put("CLUB_INVITE", "CLUB");
        put("CLUB_GIFTS", "CLUB");
        put("INCENTIVE_PLAN", "GIFT");
        put("BUY_PACKAGE", "PACKAGE");
        put("BUY_NET_PACKAGE", "PACKAGE");
        put("BUY_SMS_PACKAGE", "PACKAGE");
        put("BUY_VOICE_PACKAGE", "PACKAGE");
        put("BUY_DESIRED_PACKAGE", "PACKAGE");
        put("ACTIVE_PACKAGE", "PACKAGE");
        put("SPEED_TEST", "SERVICE");
        put("RBT", "SERVICE");
        put("BULK_SMS", "SERVICE");
        put("MCA", "SERVICE");
        put("ROAMING", "SERVICE");
        put("VOICEMAIL", "SERVICE");
        put("VAS", "SERVICE");
        put("CODES", "SERVICE");
        put("CALL_RESTRICTION", "SERVICE");
        put("SIM_STATUS", "SERVICE");
        put("PRE_TO_POST", "SERVICE");
        put("FREE_TARIFF", "SERVICE");
        put("OFFICE", "SERVICE");
        put("SUPPORT_NETWORK_AREA", "SERVICE");
        put("BLACK_LIST", "SERVICE");
        put("QA", "SUPPORT");
        put("SUGGESTIONS", "SUPPORT");
        put("SURVEY", "SUPPORT");
        put("TRANSFER_CREDIT", "TRANSFER");
        put("MY_WALLET", "WALLET");
        put("CHARGE_WALLET", "WALLET");
        put("TRANSACTION", "WALLET");
        put("OTHER", "OTHER");
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
            put("BILL", new String[]{"FINAL_TERM", "MID_TERM", "INSTALLMENT", "INCREASE_CREDIT", "BILL_HISTORY"});
            put("CHARGE", new String[]{"CHARGE_REMAINS", "BUY_CHARGE", "EMERGENCY_UMBRELLA", "CHARGE_HISTORY"});
            put("CLUB", new String[]{"FIROOZEI", "GAME", "CHARITY", "CUSTOMER_CLUB", "CLUB_REQUEST_SCORE", "CLUB_GUIDE", "CLUB_REPORTS", "CLUB_INVITE", "CLUB_GIFTS"});
            put("GIFT", new String[]{"INCENTIVE_PLAN"});
            put("PACKAGE", new String[]{"ACTIVE_PACKAGE", "BUY_PACKAGE", "BUY_NET_PACKAGE", "BUY_SMS_PACKAGE", "BUY_VOICE_PACKAGE", "BUY_DESIRED_PACKAGE"});
            put("SERVICE", new String[]{"SPEED_TEST", "RBT", "BULK_SMS", "MCA", "ROAMING", "VOICEMAIL", "VAS", "CODES", "CALL_RESTRICTION", "SIM_STATUS", "PRE_TO_POST", "FREE_TARIFF", "OFFICE", "SUPPORT_NETWORK_AREA", "BLACK_LIST"});
            put("SUPPORT", new String[]{"QA", "SUGGESTIONS", "SURVEY"});
            put("TRANSFER", new String[]{"TRANSFER_CREDIT"});
            put("WALLET", new String[]{"MY_WALLET", "CHARGE_WALLET", "TRANSACTION"});
            put("OTHER", new String[]{"OTHER"});
        }
    };

    public static HashMap<String, Double> decisions_probs(String sentence) {
        HashMap<String, Double> rule_based_probs = new HashMap<>();
        for (String s : subcats) {
            rule_based_probs.put(s, 0.0);
        }
        sentence = Preprocess.nim_fasele_removal(sentence);
        sentence = Preprocess.arabic2farsi_plural_replacement(sentence);
        sentence = Preprocess.word_concatenation(sentence);
        sentence = Preprocess.ha_hay_removal(sentence);
        sentence = "??????" + sentence + "??????";

        boolean is_there_conflict = false;
        boolean vip_detected = false;

        for (String s : Preprocess.vip_keywords.keySet()) {
            ArrayList<Boolean> match_result = new ArrayList<>();
            for (String[] strings : Preprocess.vip_keywords.get(s)) {
                boolean temp = all_kw_match(sentence, strings);
                match_result.add(temp);
            }
            if (match_result.contains(true)) {
                rule_based_probs.replace(s, rule_based_probs.get(s) + vip_score);
                if (!vip_detected) {
                    vip_detected = true;
                } else {
                    is_there_conflict = true;
                }
            }
        }
        HashMap<String, String[]> action_type_keywords = new HashMap<>();
        HashMap<String, String[]> comm_type_keywords = new HashMap<>();

        action_type_keywords.put("demand", new String[]{"????????????", "??????", "????????", "??????????", "????????",
                "??????????????", "????????????", "????????", "????????????", "????????"});
        action_type_keywords.put("status", new String[]{"????????", "????????????", "????????", " ???? ", "????????", "??????????",
                "??????????????????", "??????????", "?????????? ", " ???? ", "??????????", "??????", "????????", "?????? "});
        action_type_keywords.put("price", new String[]{"????????", "??????????", "????????"});

        comm_type_keywords.put("call", new String[]{"????????", "????????????"});
        comm_type_keywords.put("sms", new String[]{"??????????"});
        comm_type_keywords.put("internet", new String[]{"??????????????", "?????????? ", " ??????????", " ???? ", "??????"});

        HashMap<String, Integer> comm_type_flag = new HashMap<>();
        for (String s : comm_type_keywords.keySet()) {
            comm_type_flag.put(s, 0);
        }

        HashMap<String, Integer> action_type_flag = new HashMap<>();
        for (String s : action_type_keywords.keySet()) {
            action_type_flag.put(s, 0);
        }
        for (String s : action_type_keywords.keySet()) {
            if (any_kw_match(sentence, action_type_keywords.get(s))) {
                action_type_flag.replace(s, 1);
            }
        }
        for (String s : comm_type_keywords.keySet()) {
            if (any_kw_match(sentence, comm_type_keywords.get(s))) {
                comm_type_flag.replace(s, 1);
            }
        }

/////////////////////////////////
        if (rule_based_probs.get("SUPPORT_NETWORK_AREA") != 0 && rule_based_probs.get("BUY_NET_PACKAGE") != 0) {
            rule_based_probs.replace("BUY_NET_PACKAGE", 0.0);
        }
        if (rule_based_probs.get("SURVEY") != 0 && rule_based_probs.get("SUGGESTIONS") != 0) {
            rule_based_probs.replace("SUGGESTIONS", 0.0);
        }
        if (rule_based_probs.get("SIM_STATUS") != 0 && any_kw_match(sentence, new String[]{"????????", "????????"})) {
            rule_based_probs.replace("SIM_STATUS", 0.0);
        }


////////////////////////////
        if (any_kw_match(sentence, new String[]{"??????????", "????????????"}) && action_type_flag.get("price") == 1) {
            rule_based_probs.replace("TRANSFER_CREDIT", rule_based_probs.get("TRANSFER_CREDIT") + vip_score);
        }

        ArrayList<Boolean> temp = new ArrayList<>();
        for (String kw : new String[]{"????????????", "????????????"}) {
            if (sentence.contains(kw)) {
                temp.add(true);
            } else {
                temp.add(false);
            }
        }
        if (temp.contains(true) && (action_type_flag.get("price") == 1 || action_type_flag.get("status") == 1)) {
            rule_based_probs.replace("CLUB_REQUEST_SCORE", rule_based_probs.get("CLUB_REQUEST_SCORE") + vip_score);
        }

        temp = new ArrayList<>();
        for (String kw : new String[]{"????????????", "????????", "????????"}) {
            if (sentence.contains(kw)) {
                temp.add(true);
            } else {
                temp.add(false);
            }
        }
        if (rule_based_probs.get("MY_WALLET") == vip_score && (temp.contains(true))) {
            rule_based_probs.replace("MY_WALLET", 0.0);
            rule_based_probs.replace("CHARGE_WALLET", rule_based_probs.get("CHARGE_WALLET") + vip_score);
        }

        temp = new ArrayList<>();
        for (String kw : new String[]{"??????????", "????????????"}) {
            if (sentence.contains(kw)) {
                temp.add(true);
            } else {
                temp.add(false);
            }
        }
        if (rule_based_probs.get("MY_WALLET") == vip_score && (temp.contains(true))) {
            rule_based_probs.replace("MY_WALLET", 0.0);
            rule_based_probs.replace("TRANSACTION", rule_based_probs.get("TRANSACTION") + vip_score);
        }
        ArrayList<String> non_zeros = new ArrayList<>();
        for (String s : rule_based_probs.keySet()) {
            if (rule_based_probs.get(s) != 0) {
                non_zeros.add(s);
            }
        }
        int conflict_level = non_zeros.size();
        if (conflict_level != 0) {
            conflict_level = -1;
        }

        if (conflict_level > 0) {
            ArrayList<String> selected_vip_sc = non_zeros;
            String[] no_price_list = new String[]{"SIM_STATUS", "VOICEMAIL"};
            if (action_type_flag.get("price") == 1) {
                for (String k : no_price_list) {
                    rule_based_probs.replace(k, 0.0);
                }
            }

            if (selected_vip_sc.contains("RBT") && selected_vip_sc.contains("VOICEMAIL")) {
                rule_based_probs.replace("RBT", 0.0);
            }

        }


        ///////////////////////////////////////////////////////
        //////////////// Special Keyword stage ////////////////
        ///////////////////////////////////////////////////////

        String[] special_kw_farsi = new String[]{"????????????????", "????????", "????????????", "????????"};
        String[] special_category = new String[]{"BILL", "CHARGE", "CLUB", "PACKAGE"};

        TreeSet<String> curr_sc_1 = new TreeSet<String>();
        TreeSet<String> curr_sc_2 = new TreeSet<String>();
        for (int i = 0; i < special_kw_farsi.length; i++) {
            if (sentence.contains(special_kw_farsi[i])) {
                for (String subcat : cat2subcat.get(special_category[i])) {
                    curr_sc_1.add(subcat);
                }
                if (special_kw_farsi[i].equals("????????")) {
                    boolean charge_local_flag = false;
                    if (any_kw_match(sentence, new String[]{"????????", "??????????", "????????????"})) {
                        curr_sc_2.add("TRANSFER_CREDIT");
                        curr_sc_1.add("TRANSFER_CREDIT");
                        charge_local_flag = true;
                    }
                    if (action_type_flag.get("demand") == 1 || action_type_flag.get("price") == 1 && !charge_local_flag) {
                        curr_sc_2.add("BUY_CHARGE");
                        charge_local_flag = true;
                    }
                    if (action_type_flag.get("status") == 1 && !charge_local_flag) {
                        curr_sc_2.add("CHARGE_REMAINS");
                        charge_local_flag = true;
                    }
                }
                if (special_kw_farsi[i].equals("????????")) {
                    rule_based_probs.put("BUY_PACKAGE", rule_based_probs.get("BUY_PACKAGE") + 0.25 * special_score);
                    if ((action_type_flag.get("status") == 1 && sum(action_type_flag.values()) == 1) || sentence.contains("????") || sentence.contains("????????")) {
                        curr_sc_2.add("ACTIVE_PACKAGE");
                    }
                    if ((sum(comm_type_flag.values()) == 0 && (action_type_flag.get("demand") == 1) || action_type_flag.get("price") == 1)) {
                        curr_sc_2.add("BUY_CHARGE");
                    }
                    if (comm_type_flag.get("internet") == 1 && sum(comm_type_flag.values()) == 1) {
                        curr_sc_2.add("BUY_NET_PACKAGE");
                    }
                    if (comm_type_flag.get("call") == 1 && sum(comm_type_flag.values()) == 1) {
                        curr_sc_2.add("BUY_VOICE_PACKAGE");
                    }
                    if (comm_type_flag.get("sms") == 1 && sum(comm_type_flag.values()) == 1) {
                        curr_sc_2.add("BUY_SMS_PACKAGE");
                    }
                    if (sum(comm_type_flag.values()) > 1) {
                        curr_sc_2.add("BUY_DESIRED_PACKAGE");
                    }
                }
                if (special_kw_farsi[i].equals("????????????????")) {
                    if (action_type_flag.get("status") == 1)
                        curr_sc_2.add("FINAL_TERM");
                }
                if (special_kw_farsi[i].equals("????????????")) {
                    if (any_kw_match(sentence, new String[]{"????????", "????????", "??????"})) {
                        curr_sc_2.add("CLUB_INVITE");
                    }
                    if (any_kw_match(sentence, new String[]{"??????????"})) {
                        curr_sc_2.add("CLUB_REPORTS");
                    }
                }

            }
        }
        for (String key : curr_sc_1) {
            rule_based_probs.put(key, rule_based_probs.get(key) + special_score);
        }
        for (String key : curr_sc_2) {
            rule_based_probs.put(key, rule_based_probs.get(key) + special_score);
        }

        ///////////////////////////////////////////////////////
        ///////////////// No VIP, No Special //////////////////
        ///////////////////////////////////////////////////////
        if (sumDouble(rule_based_probs.values()) == 0) {
            if (comm_type_flag.get("internet") == 1 || any_kw_match(sentence, new String[]{"????????", "??????", "????????"})) {
                rule_based_probs.put("ACTIVE_PACKAGE", rule_based_probs.get("ACTIVE_PACKAGE") + special_score);
            }
        }
        ///////////////////////////////////////////////////////
        ///////////////////// Finalization/////////////////////
        ///////////////////////////////////////////////////////
        for (String key : rule_based_probs.keySet()) {
            rule_based_probs.put(key, rule_based_probs.get(key) + epsilon);
            rule_based_probs.put(key, Math.pow(rule_based_probs.get(key), exponent));
        }
        double temp_sum = sumDouble(rule_based_probs.values());
        for (String key : rule_based_probs.keySet()) {
            rule_based_probs.put(key, rule_based_probs.get(key) / temp_sum);
        }
        ///////////////////////////////////////////////////////
        //////////////// here comes the OTHER!/////////////////
        ///////////////////////////////////////////////////////
        if (Collections.max(rule_based_probs.values()) <= 5 * epsilon) {
            rule_based_probs.put("OTHER", 48.0);
            for (String key : rule_based_probs.keySet()) {
                if (!key.equals("OTHER")) {
                    rule_based_probs.put(key, 1.0);
                }
            }
            rule_based_probs.replaceAll((k, v) -> Math.pow(rule_based_probs.get(k), exponent));
            temp_sum = sumDouble(rule_based_probs.values());
            for (String key : rule_based_probs.keySet()) {
                rule_based_probs.put(key, rule_based_probs.get(key) / temp_sum);
            }
        }

        return rule_based_probs;

    }

    private static int sum(Collection<Integer> values) {
        int sum = 0;
        for (Integer value : values) {
            sum += value;
        }
        return sum;
    }

    private static double sumDouble(Collection<Double> values) {
        double sum = 0;
        for (Double value : values) {
            sum += value;
        }
        return sum;
    }

}
