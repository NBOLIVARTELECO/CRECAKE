package com.example.creativecake;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Clase de utilidades para manejar la conectividad de red
 */
public class NetworkUtils {

    private static final String TAG = "NetworkUtils";

    /**
     * Verifica si hay conectividad de red disponible
     * @param context contexto de la aplicación
     * @return true si hay conexión a internet
     */
    public static boolean isNetworkAvailable(Context context) {
        if (context == null) {
            return false;
        }

        ConnectivityManager connectivityManager = (ConnectivityManager) 
            context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network network = connectivityManager.getActiveNetwork();
            if (network == null) {
                return false;
            }

            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
            return capabilities != null && 
                   (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET));
        } else {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
    }

    /**
     * Verifica si hay conexión WiFi
     * @param context contexto de la aplicación
     * @return true si hay conexión WiFi
     */
    public static boolean isWifiConnected(Context context) {
        if (context == null) {
            return false;
        }

        ConnectivityManager connectivityManager = (ConnectivityManager) 
            context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network network = connectivityManager.getActiveNetwork();
            if (network == null) {
                return false;
            }

            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
            return capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
        } else {
            NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            return wifiInfo != null && wifiInfo.isConnected();
        }
    }

    /**
     * Verifica si hay conexión móvil
     * @param context contexto de la aplicación
     * @return true si hay conexión móvil
     */
    public static boolean isMobileConnected(Context context) {
        if (context == null) {
            return false;
        }

        ConnectivityManager connectivityManager = (ConnectivityManager) 
            context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network network = connectivityManager.getActiveNetwork();
            if (network == null) {
                return false;
            }

            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
            return capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);
        } else {
            NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            return mobileInfo != null && mobileInfo.isConnected();
        }
    }

    /**
     * Verifica si hay conexión a internet real (no solo conectividad)
     * @return true si hay conexión real a internet
     */
    public static boolean hasInternetConnection() {
        try {
            URL url = new URL("https://www.google.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(Constants.CONNECTION_TIMEOUT);
            connection.setReadTimeout(Constants.READ_TIMEOUT);
            connection.connect();
            
            int responseCode = connection.getResponseCode();
            connection.disconnect();
            
            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (IOException e) {
            Log.e(TAG, "Error checking internet connection: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene el tipo de conexión actual
     * @param context contexto de la aplicación
     * @return tipo de conexión (WIFI, MOBILE, ETHERNET, NONE)
     */
    public static String getConnectionType(Context context) {
        if (context == null) {
            return "NONE";
        }

        ConnectivityManager connectivityManager = (ConnectivityManager) 
            context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return "NONE";
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network network = connectivityManager.getActiveNetwork();
            if (network == null) {
                return "NONE";
            }

            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
            if (capabilities == null) {
                return "NONE";
            }

            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return "WIFI";
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return "MOBILE";
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                return "ETHERNET";
            } else {
                return "OTHER";
            }
        } else {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            if (activeNetwork == null) {
                return "NONE";
            }

            switch (activeNetwork.getType()) {
                case ConnectivityManager.TYPE_WIFI:
                    return "WIFI";
                case ConnectivityManager.TYPE_MOBILE:
                    return "MOBILE";
                case ConnectivityManager.TYPE_ETHERNET:
                    return "ETHERNET";
                default:
                    return "OTHER";
            }
        }
    }

    /**
     * Verifica si la conexión es estable
     * @param context contexto de la aplicación
     * @return true si la conexión es estable
     */
    public static boolean isConnectionStable(Context context) {
        if (!isNetworkAvailable(context)) {
            return false;
        }

        // Verificar múltiples veces para asegurar estabilidad
        for (int i = 0; i < 3; i++) {
            if (!hasInternetConnection()) {
                return false;
            }
            try {
                Thread.sleep(1000); // Esperar 1 segundo entre verificaciones
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return true;
    }

    /**
     * Obtiene la velocidad de conexión estimada
     * @param context contexto de la aplicación
     * @return velocidad estimada en Mbps, -1 si no se puede determinar
     */
    public static double getEstimatedConnectionSpeed(Context context) {
        String connectionType = getConnectionType(context);
        
        switch (connectionType) {
            case "WIFI":
                return 50.0; // WiFi típico
            case "MOBILE":
                return 10.0; // 4G típico
            case "ETHERNET":
                return 100.0; // Ethernet típico
            default:
                return -1.0; // No determinado
        }
    }

    /**
     * Verifica si la conexión es lo suficientemente rápida para la aplicación
     * @param context contexto de la aplicación
     * @return true si la conexión es adecuada
     */
    public static boolean isConnectionAdequate(Context context) {
        double speed = getEstimatedConnectionSpeed(context);
        return speed > 1.0; // Mínimo 1 Mbps
    }

    /**
     * Obtiene información detallada de la conexión
     * @param context contexto de la aplicación
     * @return información de la conexión
     */
    public static String getConnectionInfo(Context context) {
        if (!isNetworkAvailable(context)) {
            return "Sin conexión a internet";
        }

        String connectionType = getConnectionType(context);
        double speed = getEstimatedConnectionSpeed(context);
        boolean isStable = isConnectionStable(context);

        StringBuilder info = new StringBuilder();
        info.append("Tipo: ").append(connectionType);
        
        if (speed > 0) {
            info.append(", Velocidad estimada: ").append(String.format("%.1f Mbps", speed));
        }
        
        info.append(", Estable: ").append(isStable ? "Sí" : "No");
        
        return info.toString();
    }
} 