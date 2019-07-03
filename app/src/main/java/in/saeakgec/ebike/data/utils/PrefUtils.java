package in.saeakgec.ebike.data.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtils {
    public PrefUtils() {
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("Token", Context.MODE_PRIVATE);
    }

    public static void storeToken(Context context, String apiKey) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString("Token", apiKey);
        editor.apply();
        editor.commit();
    }

    public static String getToken(Context context) {
        return getSharedPreferences(context).getString("Token", "");
    }
}
