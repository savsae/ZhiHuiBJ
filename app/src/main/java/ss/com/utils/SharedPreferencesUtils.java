package ss.com.utils;


import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {

    private static final String SHARED_NAME = "config";

    public static boolean getBoolean(Context cx,String key,boolean defaultValue){
        SharedPreferences sharedPreferences = cx.getSharedPreferences(SHARED_NAME, cx.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key,defaultValue);
    }

    public static void setBoolean(Context cx,String key,boolean value){
        SharedPreferences sharedPreferences = cx.getSharedPreferences("config",cx.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }

}
