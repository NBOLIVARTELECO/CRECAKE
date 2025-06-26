package com.example.creativecake;

/**
 * Clase que contiene todas las constantes de la aplicación
 */
public class Constants {

    // Tipos de usuario
    public static final String USER_TYPE_CLIENT = "User";
    public static final String USER_TYPE_STORE = "Tienda";
    public static final String USER_TYPE_DELIVERY = "Domiciliario";
    public static final String USER_TYPE_ADMIN = "Admin";

    // Estados de pago
    public static final String PAYMENT_STATUS_PENDING = "PENDIENTE";
    public static final String PAYMENT_STATUS_ACCEPTED = "ACEPTADO";
    public static final String PAYMENT_STATUS_DENIED = "DENEGADO";
    public static final String PAYMENT_STATUS_IN_PROGRESS = "EN PROGRESO";

    // Estados de pedido
    public static final String ORDER_STATUS_PENDING = "PENDIENTE";
    public static final String ORDER_STATUS_ACCEPTED = "ACEPTADO";
    public static final String ORDER_STATUS_IN_PROGRESS = "EN PROGRESO";
    public static final String ORDER_STATUS_DELIVERED = "ENTREGADO";
    public static final String ORDER_STATUS_CANCELLED = "CANCELADO";

    // Tipos de producto
    public static final String PRODUCT_TYPE_CAKE = "Torta";
    public static final String PRODUCT_TYPE_DESSERT = "Postre";
    public static final String PRODUCT_TYPE_PASTRY = "Hojaldre";
    public static final String PRODUCT_TYPE_OTHER = "Otro";

    // Rutas de Firebase
    public static final String FIREBASE_USERS_CLIENT = "usuarioCliente";
    public static final String FIREBASE_USERS_STORE = "usuarioNegocio";
    public static final String FIREBASE_USERS_DELIVERY = "usuarioDomiciliario";
    public static final String FIREBASE_USERS_ADMIN = "usuarioDev";
    public static final String FIREBASE_PRODUCTS = "productoTienda";
    public static final String FIREBASE_CART = "carrito";
    public static final String FIREBASE_PAYMENTS = "pagoCarrito";
    public static final String FIREBASE_QUOTES = "cotizaciones";
    public static final String FIREBASE_CHAT = "chat";
    public static final String FIREBASE_SALES = "Ventas";
    public static final String FIREBASE_DELIVERIES = "domicilios";
    public static final String FIREBASE_DELIVERY_ORDERS = "pedidoAgregadoDomiciliario";
    public static final String FIREBASE_STORE_QUOTES = "cotiTienda";
    public static final String FIREBASE_DELIVERED_ORDERS = "entregasDomiciliario";

    // Configuración de la aplicación
    public static final int MIN_PASSWORD_LENGTH = 4;
    public static final int MIN_PHONE_LENGTH = 7;
    public static final int MIN_NAME_LENGTH = 2;
    public static final int MIN_AGE = 13;
    public static final int MAX_AGE = 120;
    public static final double COMMISSION_PERCENTAGE = 5.0;

    // Códigos de respuesta
    public static final int REQUEST_CODE_IMAGE_PICK = 1;
    public static final int REQUEST_CODE_LOCATION_PERMISSION = 2;

    // Tamaños de imagen
    public static final int IMAGE_SIZE_SMALL = 150;
    public static final int IMAGE_SIZE_MEDIUM = 300;
    public static final int IMAGE_SIZE_LARGE = 500;

    // Tiempos de espera (en milisegundos)
    public static final long SPLASH_DELAY = 2000;
    public static final long TOAST_DURATION_SHORT = 2000;
    public static final long TOAST_DURATION_LONG = 3500;

    // Colores de la aplicación
    public static final String COLOR_PRIMARY = "#BF5A7F";
    public static final String COLOR_SECONDARY = "#8C1C3A";
    public static final String COLOR_ACCENT = "#F2F2F2";

    // Mensajes de error
    public static final String ERROR_NETWORK = "Error de conexión. Intente nuevamente.";
    public static final String ERROR_INVALID_EMAIL = "Ingrese un correo válido";
    public static final String ERROR_INVALID_PHONE = "Ingrese un teléfono válido";
    public static final String ERROR_INVALID_PASSWORD = "La contraseña debe tener al menos 4 caracteres";
    public static final String ERROR_INVALID_PRICE = "Ingrese un precio válido";
    public static final String ERROR_INVALID_QUANTITY = "Ingrese una cantidad válida";
    public static final String ERROR_USER_NOT_FOUND = "Usuario no encontrado";
    public static final String ERROR_WRONG_PASSWORD = "Contraseña incorrecta";
    public static final String ERROR_USER_ALREADY_EXISTS = "El usuario ya está registrado";
    public static final String ERROR_REQUIRED_FIELD = "Este campo es requerido";

    // Mensajes de éxito
    public static final String SUCCESS_USER_REGISTERED = "Usuario registrado exitosamente";
    public static final String SUCCESS_LOGIN = "Inicio de sesión exitoso";
    public static final String SUCCESS_LOGOUT = "Sesión cerrada exitosamente";
    public static final String SUCCESS_PRODUCT_ADDED = "Producto agregado exitosamente";
    public static final String SUCCESS_PRODUCT_UPDATED = "Producto actualizado exitosamente";
    public static final String SUCCESS_PRODUCT_DELETED = "Producto eliminado exitosamente";
    public static final String SUCCESS_ORDER_PLACED = "Pedido realizado exitosamente";
    public static final String SUCCESS_PAYMENT_CONFIRMED = "Pago confirmado exitosamente";
    public static final String SUCCESS_QUOTE_SENT = "Cotización enviada exitosamente";

    // Nombres de archivos
    public static final String FILE_FACTURA_PREFIX = "Factura_";
    public static final String FILE_FACTURA_EXTENSION = ".pdf";
    public static final String FOLDER_FACTURAS = "Facturas Creative Cake";

    // Configuración de Google Maps
    public static final String GOOGLE_MAPS_API_KEY = "AIzaSyBhG2xNtDxtu3nV69Zw1v9u1C_si-Tf67Y";
    public static final double DEFAULT_LATITUDE = 4.710989;
    public static final double DEFAULT_LONGITUDE = -74.072092;
    public static final int DEFAULT_ZOOM = 15;

    // Configuración de Firebase Storage
    public static final String STORAGE_IMAGES_PATH = "Imagen Producto";
    public static final String STORAGE_PROFILE_IMAGES_PATH = "Imagenes Perfil";

    // Configuración de paginación
    public static final int ITEMS_PER_PAGE = 20;
    public static final int MAX_ITEMS_LOAD = 100;

    // Configuración de cache
    public static final long CACHE_DURATION = 24 * 60 * 60 * 1000; // 24 horas en milisegundos
    public static final int CACHE_SIZE = 10 * 1024 * 1024; // 10 MB

    // Configuración de notificaciones
    public static final String NOTIFICATION_CHANNEL_ID = "creative_cake_channel";
    public static final String NOTIFICATION_CHANNEL_NAME = "Creative Cake Notifications";
    public static final String NOTIFICATION_CHANNEL_DESCRIPTION = "Notificaciones de la aplicación Creative Cake";

    // Configuración de encriptación
    public static final String ENCRYPTION_ALGORITHM = "AES";
    public static final String ENCRYPTION_TRANSFORMATION = "AES/CBC/PKCS5Padding";
    public static final int ENCRYPTION_KEY_SIZE = 256;

    // Configuración de validación
    public static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String PHONE_PATTERN = "^[0-9]{7,15}$";
    public static final String PRICE_PATTERN = "^[0-9]+(\\.[0-9]{1,2})?$";
    public static final String QUANTITY_PATTERN = "^[0-9]+$";

    // Configuración de formato de fecha
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String TIME_FORMAT = "HH:mm";
    public static final String DATETIME_FORMAT = "dd/MM/yyyy HH:mm";

    // Configuración de moneda
    public static final String CURRENCY_SYMBOL = "$";
    public static final String CURRENCY_CODE = "COP";
    public static final String CURRENCY_NAME = "Peso Colombiano";

    // Configuración de idioma
    public static final String DEFAULT_LANGUAGE = "es";
    public static final String DEFAULT_COUNTRY = "CO";
    public static final String DEFAULT_LOCALE = "es_CO";

    // Configuración de zona horaria
    public static final String DEFAULT_TIMEZONE = "America/Bogota";

    // Configuración de red
    public static final int CONNECTION_TIMEOUT = 30000; // 30 segundos
    public static final int READ_TIMEOUT = 30000; // 30 segundos
    public static final int WRITE_TIMEOUT = 30000; // 30 segundos

    // Configuración de animaciones
    public static final int ANIMATION_DURATION_SHORT = 200;
    public static final int ANIMATION_DURATION_MEDIUM = 300;
    public static final int ANIMATION_DURATION_LONG = 500;

    // Configuración de UI
    public static final int CORNER_RADIUS = 8;
    public static final int ELEVATION = 4;
    public static final int MARGIN_SMALL = 8;
    public static final int MARGIN_MEDIUM = 16;
    public static final int MARGIN_LARGE = 24;
    public static final int PADDING_SMALL = 8;
    public static final int PADDING_MEDIUM = 16;
    public static final int PADDING_LARGE = 24;
} 