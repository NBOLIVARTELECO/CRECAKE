package com.example.creativecake;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Clase de utilidades para funciones comunes en la aplicación
 */
public class Utils {

    /**
     * Valida si un email tiene formato válido
     * @param email email a validar
     * @return true si el email es válido
     */
    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * Valida si un teléfono tiene formato válido
     * @param phone teléfono a validar
     * @return true si el teléfono es válido
     */
    public static boolean isValidPhone(String phone) {
        return !TextUtils.isEmpty(phone) && phone.length() >= 7 && phone.matches("\\d+");
    }

    /**
     * Valida si una contraseña cumple con los requisitos mínimos
     * @param password contraseña a validar
     * @return true si la contraseña es válida
     */
    public static boolean isValidPassword(String password) {
        return !TextUtils.isEmpty(password) && password.length() >= 4;
    }

    /**
     * Valida si un precio es válido
     * @param price precio a validar
     * @return true si el precio es válido
     */
    public static boolean isValidPrice(String price) {
        try {
            double precio = Double.parseDouble(price);
            return precio > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Valida si una cantidad es válida
     * @param quantity cantidad a validar
     * @return true si la cantidad es válida
     */
    public static boolean isValidQuantity(String quantity) {
        try {
            int cantidad = Integer.parseInt(quantity);
            return cantidad > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Formatea un precio para mostrar
     * @param price precio a formatear
     * @return precio formateado
     */
    public static String formatPrice(String price) {
        try {
            double precio = Double.parseDouble(price);
            return String.format(Locale.getDefault(), "$%.0f", precio);
        } catch (NumberFormatException e) {
            return "$0";
        }
    }

    /**
     * Formatea un precio con descuento
     * @param price precio original
     * @param discount porcentaje de descuento
     * @return precio con descuento formateado
     */
    public static String formatPriceWithDiscount(String price, String discount) {
        try {
            double precio = Double.parseDouble(price);
            double descuento = Double.parseDouble(discount);
            double precioFinal = precio * (1 - descuento / 100);
            return String.format(Locale.getDefault(), "$%.0f", precioFinal);
        } catch (NumberFormatException e) {
            return formatPrice(price);
        }
    }

    /**
     * Obtiene la fecha actual formateada
     * @return fecha actual en formato dd/MM/yyyy
     */
    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(new Date());
    }

    /**
     * Obtiene la hora actual formateada
     * @return hora actual en formato HH:mm
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return sdf.format(new Date());
    }

    /**
     * Muestra un Toast con mensaje corto
     * @param context contexto de la aplicación
     * @param message mensaje a mostrar
     */
    public static void showShortToast(Context context, String message) {
        if (context != null && !TextUtils.isEmpty(message)) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Muestra un Toast con mensaje largo
     * @param context contexto de la aplicación
     * @param message mensaje a mostrar
     */
    public static void showLongToast(Context context, String message) {
        if (context != null && !TextUtils.isEmpty(message)) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Capitaliza la primera letra de cada palabra
     * @param text texto a capitalizar
     * @return texto capitalizado
     */
    public static String capitalizeWords(String text) {
        if (TextUtils.isEmpty(text)) {
            return text;
        }
        
        String[] words = text.split("\\s+");
        StringBuilder result = new StringBuilder();
        
        for (String word : words) {
            if (word.length() > 0) {
                result.append(Character.toUpperCase(word.charAt(0)))
                      .append(word.substring(1).toLowerCase())
                      .append(" ");
            }
        }
        
        return result.toString().trim();
    }

    /**
     * Limpia un texto eliminando espacios extra
     * @param text texto a limpiar
     * @return texto limpio
     */
    public static String cleanText(String text) {
        if (TextUtils.isEmpty(text)) {
            return text;
        }
        return text.trim().replaceAll("\\s+", " ");
    }

    /**
     * Verifica si una cadena contiene solo números
     * @param text texto a verificar
     * @return true si contiene solo números
     */
    public static boolean isNumeric(String text) {
        return !TextUtils.isEmpty(text) && text.matches("\\d+");
    }

    /**
     * Convierte un string a entero de forma segura
     * @param text texto a convertir
     * @param defaultValue valor por defecto si falla la conversión
     * @return entero convertido o valor por defecto
     */
    public static int safeParseInt(String text, int defaultValue) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Convierte un string a double de forma segura
     * @param text texto a convertir
     * @param defaultValue valor por defecto si falla la conversión
     * @return double convertido o valor por defecto
     */
    public static double safeParseDouble(String text, double defaultValue) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
} 