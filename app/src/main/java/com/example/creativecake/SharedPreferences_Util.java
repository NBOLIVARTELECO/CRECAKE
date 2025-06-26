package com.example.creativecake;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class SharedPreferences_Util {

    private static final String TAG = "SharedPreferences_Util";

    public SharedPreferences_Util(){
    }

    /**
     * Guarda el número de teléfono en SharedPreferences.
     * @param phone Número de teléfono a guardar
     * @param context Contexto de la aplicación
     * @return true si se guardó correctamente
     */
    public static boolean savePhone_SP(String phone, Context context){
        if (context == null) {
            Log.e(TAG, "Context is null");
            return false;
        }
        
        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor prefsEditor = prefs.edit();
            prefsEditor.putString(SharedPreferences_Constantes.KEY_PHONE, phone);
            prefsEditor.apply();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving phone: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene el número de teléfono almacenado en SharedPreferences.
     * @param context Contexto de la aplicación
     * @return Número de teléfono almacenado o null si no existe
     */
    public static String getPhone_SP(Context context){
        if (context == null) {
            Log.e(TAG, "Context is null");
            return null;
        }
        
        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            return prefs.getString(SharedPreferences_Constantes.KEY_PHONE, null);
        } catch (Exception e) {
            Log.e(TAG, "Error getting phone: " + e.getMessage());
            return null;
        }
    }

    /**
     * Guarda la contraseña en SharedPreferences.
     * @param password Contraseña a guardar
     * @param context Contexto de la aplicación
     * @return true si se guardó correctamente
     */
    public static boolean savePassword_SP(String password, Context context){
        if (context == null) {
            Log.e(TAG, "Context is null");
            return false;
        }
        
        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor prefsEditor = prefs.edit();
            prefsEditor.putString(SharedPreferences_Constantes.KEY_PASSWORD, password);
            prefsEditor.apply();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving password: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene la contraseña almacenada en SharedPreferences.
     * @param context Contexto de la aplicación
     * @return Contraseña almacenada o null si no existe
     */
    public static String getPassword_SP(Context context){
        if (context == null) {
            Log.e(TAG, "Context is null");
            return null;
        }
        
        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            return prefs.getString(SharedPreferences_Constantes.KEY_PASSWORD, null);
        } catch (Exception e) {
            Log.e(TAG, "Error getting password: " + e.getMessage());
            return null;
        }
    }

    /**
     * Guarda el tipo de usuario en SharedPreferences.
     * @param type Tipo de usuario a guardar
     * @param context Contexto de la aplicación
     * @return true si se guardó correctamente
     */
    public static boolean saveType_SP(String type, Context context){
        if (context == null) {
            Log.e(TAG, "Context is null");
            return false;
        }
        
        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor prefsEditor = prefs.edit();
            prefsEditor.putString(SharedPreferences_Constantes.KEY_TYPE, type);
            prefsEditor.apply();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving type: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene el tipo de usuario almacenado en SharedPreferences.
     * @param context Contexto de la aplicación
     * @return Tipo de usuario almacenado o null si no existe
     */
    public static String getType_SP(Context context){
        if (context == null) {
            Log.e(TAG, "Context is null");
            return null;
        }
        
        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            return prefs.getString(SharedPreferences_Constantes.KEY_TYPE, null);
        } catch (Exception e) {
            Log.e(TAG, "Error getting type: " + e.getMessage());
            return null;
        }
    }

    /**
     * Limpia todas las preferencias guardadas (logout).
     * @param context Contexto de la aplicación
     * @return true si se limpiaron correctamente
     */
    public static boolean clearAllPreferences(Context context) {
        if (context == null) {
            Log.e(TAG, "Context is null");
            return false;
        }
        
        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor prefsEditor = prefs.edit();
            prefsEditor.clear();
            prefsEditor.apply();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error clearing preferences: " + e.getMessage());
            return false;
        }
    }

    /**
     * Verifica si el usuario está logueado.
     * @param context Contexto de la aplicación
     * @return true si el usuario está logueado
     */
    public static boolean isUserLoggedIn(Context context) {
        String phone = getPhone_SP(context);
        String type = getType_SP(context);
        return phone != null && !phone.isEmpty() && type != null && !type.isEmpty();
    }
}
