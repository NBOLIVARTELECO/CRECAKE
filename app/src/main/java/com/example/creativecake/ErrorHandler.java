package com.example.creativecake;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabaseException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Clase para manejar errores de forma centralizada en la aplicación
 */
public class ErrorHandler {

    private static final String TAG = "ErrorHandler";

    /**
     * Maneja errores de Firebase Database
     * @param context contexto de la aplicación
     * @param error error de Firebase
     */
    public static void handleFirebaseError(Context context, DatabaseError error) {
        if (context == null || error == null) {
            return;
        }

        Log.e(TAG, "Firebase Database Error: " + error.getMessage());

        switch (error.getCode()) {
            case DatabaseError.PERMISSION_DENIED:
                Utils.showShortToast(context, "No tienes permisos para realizar esta acción");
                break;
            case DatabaseError.DISCONNECTED:
                Utils.showShortToast(context, "Error de conexión con la base de datos");
                break;
            case DatabaseError.OPERATION_FAILED:
                Utils.showShortToast(context, "La operación falló. Intente nuevamente");
                break;
            case DatabaseError.QUOTA_EXCEEDED:
                Utils.showShortToast(context, "Se ha excedido el límite de la base de datos");
                break;
            case DatabaseError.USER_CODE_EXCEPTION:
                Utils.showShortToast(context, "Error en el código de usuario");
                break;
            case DatabaseError.INVALID_TOKEN:
                Utils.showShortToast(context, "Token de autenticación inválido");
                break;
            case DatabaseError.MAX_RETRIES:
                Utils.showShortToast(context, "Se han agotado los intentos de conexión");
                break;
            case DatabaseError.OVERRIDDEN_BY_SET:
                Utils.showShortToast(context, "Los datos han sido sobrescritos");
                break;
            case DatabaseError.UNAVAILABLE:
                Utils.showShortToast(context, "Servicio no disponible");
                break;
            case DatabaseError.WRITE_CANCELED:
                Utils.showShortToast(context, "Escritura cancelada");
                break;
            case DatabaseError.INVALID_ARGUMENT:
                Utils.showShortToast(context, "Argumento inválido");
                break;
            case DatabaseError.DATA_STALE:
                Utils.showShortToast(context, "Los datos están desactualizados");
                break;
            case DatabaseError.KEY_NOT_FOUND:
                Utils.showShortToast(context, "Clave no encontrada");
                break;
            case DatabaseError.INTERNAL_ERROR:
                Utils.showShortToast(context, "Error interno del servidor");
                break;
            case DatabaseError.NETWORK_ERROR:
                Utils.showShortToast(context, Constants.ERROR_NETWORK);
                break;
            default:
                Utils.showShortToast(context, "Error desconocido: " + error.getMessage());
                break;
        }
    }

    /**
     * Maneja errores de Firebase Storage
     * @param context contexto de la aplicación
     * @param exception excepción de Firebase Storage
     */
    public static void handleFirebaseStorageError(Context context, Exception exception) {
        if (context == null || exception == null) {
            return;
        }

        Log.e(TAG, "Firebase Storage Error: " + exception.getMessage());

        if (exception instanceof FirebaseDatabaseException) {
            handleFirebaseError(context, new DatabaseError(0, exception.getMessage()));
        } else {
            Utils.showShortToast(context, "Error al subir archivo: " + exception.getMessage());
        }
    }

    /**
     * Maneja errores de red
     * @param context contexto de la aplicación
     * @param exception excepción de red
     */
    public static void handleNetworkError(Context context, Exception exception) {
        if (context == null || exception == null) {
            return;
        }

        Log.e(TAG, "Network Error: " + exception.getMessage());

        if (exception instanceof UnknownHostException) {
            Utils.showShortToast(context, "No se puede conectar al servidor");
        } else if (exception instanceof SocketTimeoutException) {
            Utils.showShortToast(context, "Tiempo de conexión agotado");
        } else if (exception instanceof IOException) {
            Utils.showShortToast(context, "Error de entrada/salida");
        } else {
            Utils.showShortToast(context, Constants.ERROR_NETWORK);
        }
    }

    /**
     * Maneja errores de validación
     * @param context contexto de la aplicación
     * @param fieldName nombre del campo con error
     * @param errorType tipo de error
     */
    public static void handleValidationError(Context context, String fieldName, String errorType) {
        if (context == null) {
            return;
        }

        String message;
        switch (errorType) {
            case "EMPTY":
                message = "El campo " + fieldName + " no puede estar vacío";
                break;
            case "INVALID_EMAIL":
                message = Constants.ERROR_INVALID_EMAIL;
                break;
            case "INVALID_PHONE":
                message = Constants.ERROR_INVALID_PHONE;
                break;
            case "INVALID_PASSWORD":
                message = Constants.ERROR_INVALID_PASSWORD;
                break;
            case "INVALID_PRICE":
                message = Constants.ERROR_INVALID_PRICE;
                break;
            case "INVALID_QUANTITY":
                message = Constants.ERROR_INVALID_QUANTITY;
                break;
            case "MIN_LENGTH":
                message = "El campo " + fieldName + " es demasiado corto";
                break;
            case "MAX_LENGTH":
                message = "El campo " + fieldName + " es demasiado largo";
                break;
            case "ALREADY_EXISTS":
                message = "El " + fieldName + " ya está registrado";
                break;
            case "NOT_FOUND":
                message = fieldName + " no encontrado";
                break;
            default:
                message = "Error de validación en " + fieldName;
                break;
        }

        Utils.showShortToast(context, message);
        Log.w(TAG, "Validation Error: " + message);
    }

    /**
     * Maneja errores de autenticación
     * @param context contexto de la aplicación
     * @param errorCode código de error
     */
    public static void handleAuthError(Context context, String errorCode) {
        if (context == null) {
            return;
        }

        String message;
        switch (errorCode) {
            case "USER_NOT_FOUND":
                message = Constants.ERROR_USER_NOT_FOUND;
                break;
            case "WRONG_PASSWORD":
                message = Constants.ERROR_WRONG_PASSWORD;
                break;
            case "USER_ALREADY_EXISTS":
                message = Constants.ERROR_USER_ALREADY_EXISTS;
                break;
            case "INVALID_CREDENTIALS":
                message = "Credenciales inválidas";
                break;
            case "ACCOUNT_DISABLED":
                message = "Cuenta deshabilitada";
                break;
            case "TOO_MANY_ATTEMPTS":
                message = "Demasiados intentos fallidos. Intente más tarde";
                break;
            case "WEAK_PASSWORD":
                message = "La contraseña es demasiado débil";
                break;
            case "INVALID_EMAIL":
                message = Constants.ERROR_INVALID_EMAIL;
                break;
            default:
                message = "Error de autenticación: " + errorCode;
                break;
        }

        Utils.showShortToast(context, message);
        Log.w(TAG, "Auth Error: " + message);
    }

    /**
     * Maneja errores de permisos
     * @param context contexto de la aplicación
     * @param permission permiso denegado
     */
    public static void handlePermissionError(Context context, String permission) {
        if (context == null) {
            return;
        }

        String message;
        switch (permission) {
            case "CAMERA":
                message = "Se requiere permiso de cámara para tomar fotos";
                break;
            case "STORAGE":
                message = "Se requiere permiso de almacenamiento para guardar archivos";
                break;
            case "LOCATION":
                message = "Se requiere permiso de ubicación para mostrar tu posición";
                break;
            case "INTERNET":
                message = "Se requiere conexión a internet";
                break;
            default:
                message = "Se requiere permiso: " + permission;
                break;
        }

        Utils.showShortToast(context, message);
        Log.w(TAG, "Permission Error: " + message);
    }

    /**
     * Maneja errores generales
     * @param context contexto de la aplicación
     * @param exception excepción general
     */
    public static void handleGeneralError(Context context, Exception exception) {
        if (context == null || exception == null) {
            return;
        }

        Log.e(TAG, "General Error: " + exception.getMessage(), exception);
        Utils.showShortToast(context, "Ha ocurrido un error inesperado");
    }

    /**
     * Maneja errores de imagen
     * @param context contexto de la aplicación
     * @param errorType tipo de error de imagen
     */
    public static void handleImageError(Context context, String errorType) {
        if (context == null) {
            return;
        }

        String message;
        switch (errorType) {
            case "LOAD_FAILED":
                message = "No se pudo cargar la imagen";
                break;
            case "UPLOAD_FAILED":
                message = "No se pudo subir la imagen";
                break;
            case "INVALID_FORMAT":
                message = "Formato de imagen no válido";
                break;
            case "FILE_TOO_LARGE":
                message = "El archivo es demasiado grande";
                break;
            case "COMPRESSION_FAILED":
                message = "No se pudo comprimir la imagen";
                break;
            default:
                message = "Error con la imagen: " + errorType;
                break;
        }

        Utils.showShortToast(context, message);
        Log.w(TAG, "Image Error: " + message);
    }

    /**
     * Registra un error para análisis
     * @param errorType tipo de error
     * @param errorMessage mensaje de error
     * @param exception excepción (opcional)
     */
    public static void logError(String errorType, String errorMessage, Exception exception) {
        if (exception != null) {
            Log.e(TAG, errorType + ": " + errorMessage, exception);
        } else {
            Log.e(TAG, errorType + ": " + errorMessage);
        }
        
        // Aquí se podría enviar el error a un servicio de análisis como Crashlytics
        // FirebaseCrashlytics.getInstance().recordException(exception);
    }
} 