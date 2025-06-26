# CREATIVE CAKE

CREATIVE CAKE es una aplicación móvil Android para la gestión de pedidos, cotizaciones y ventas de productos de pastelería, conectando clientes, tiendas y domiciliarios en un solo ecosistema digital.

## Tabla de Contenidos
- [Características](#características)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Flujo de Usuario](#flujo-de-usuario)
- [Dependencias](#dependencias)
- [Configuración y Ejecución](#configuración-y-ejecución)
- [Mejoras Implementadas](#mejoras-implementadas)
- [Clases Utilitarias](#clases-utilitarias)
- [Internacionalización](#internacionalización)
- [Licencia](#licencia)

## Características
- Registro y autenticación de usuarios (Cliente, Tienda, Domiciliario, Administrador).
- Catálogo de productos de pastelería con imágenes y ofertas.
- Carrito de compras y proceso de pago.
- Cotización personalizada de productos.
- Sistema de domicilios con seguimiento en tiempo real.
- Gestión de inventario para tiendas.
- Panel de administración.
- Notificaciones push.
- Integración con Google Maps para ubicación.
- Sistema de calificaciones y reseñas.

## Estructura del Proyecto

```
CRECAKE2/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/creativecake/
│   │   │   ├── Activities/           # Actividades principales
│   │   │   ├── Fragments/            # Fragmentos de UI
│   │   │   ├── Adapters/             # Adaptadores para RecyclerViews
│   │   │   ├── Dialogs/              # Diálogos personalizados
│   │   │   ├── Helpers/              # Clases helper para datos
│   │   │   ├── Utils/                # Clases utilitarias
│   │   │   └── Constants/            # Constantes de la aplicación
│   │   ├── res/
│   │   │   ├── layout/               # Archivos de layout XML
│   │   │   ├── values/               # Recursos de strings, colores, etc.
│   │   │   ├── drawable/             # Imágenes y drawables
│   │   │   └── navigation/           # Grafos de navegación
│   │   └── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
└── README.md
```

## Flujo de Usuario

### Cliente
1. **Registro/Login**: Crear cuenta o iniciar sesión
2. **Explorar Catálogo**: Ver productos disponibles con filtros
3. **Agregar al Carrito**: Seleccionar productos y cantidades
4. **Proceso de Compra**: Confirmar pedido y método de pago
5. **Seguimiento**: Monitorear estado del pedido
6. **Cotizaciones**: Solicitar cotizaciones personalizadas

### Tienda
1. **Gestión de Productos**: Agregar, editar, eliminar productos
2. **Inventario**: Control de stock y precios
3. **Pedidos**: Revisar y gestionar pedidos entrantes
4. **Cotizaciones**: Responder solicitudes de cotización
5. **Ventas**: Reportes y estadísticas de ventas

### Domiciliario
1. **Pedidos Disponibles**: Ver pedidos pendientes de entrega
2. **Aceptar Pedidos**: Tomar pedidos para entrega
3. **Navegación**: Usar GPS para llegar al destino
4. **Confirmar Entrega**: Marcar pedidos como entregados
5. **Ganancias**: Ver comisiones por entregas

## Dependencias

### Dependencias Principales Actualizadas
```gradle
// AndroidX Core Libraries
implementation 'androidx.core:core:1.12.0'
implementation 'androidx.activity:activity:1.8.2'
implementation 'androidx.fragment:fragment:1.6.2'
implementation 'androidx.appcompat:appcompat:1.6.1'
implementation 'androidx.constraintlayout:constraintlayout:2.1.4'

// Material Design
implementation 'com.google.android.material:material:1.11.0'

// Navigation Components
implementation 'androidx.navigation:navigation-fragment:2.7.6'
implementation 'androidx.navigation:navigation-ui:2.7.6'

// Firebase
implementation 'com.google.firebase:firebase-core:21.1.1'
implementation 'com.google.firebase:firebase-auth:22.3.1'
implementation 'com.google.firebase:firebase-database:20.3.0'
implementation 'com.google.firebase:firebase-storage:20.3.0'

// Google Services
implementation 'com.google.android.gms:play-services-location:21.0.1'
implementation 'com.google.android.gms:play-services-maps:18.2.0'
implementation 'com.google.android.gms:play-services-auth:20.7.0'

// Image Loading
implementation 'com.squareup.picasso:picasso:2.8'
implementation 'id.zelory:compressor:2.1.1'
```

### Configuración de Build
- **compileSdkVersion**: 34
- **targetSdkVersion**: 34
- **minSdkVersion**: 21
- **Gradle Plugin**: 8.2.0
- **Google Services Plugin**: 4.4.0

## Configuración y Ejecución

### Prerrequisitos
- Android Studio Arctic Fox o superior
- JDK 8 o superior
- Dispositivo Android con API 21+ o emulador
- Cuenta de Firebase

### Pasos de Instalación

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/tu-usuario/creative-cake.git
   cd creative-cake
   ```

2. **Configurar Firebase**
   - Crear proyecto en [Firebase Console](https://console.firebase.google.com/)
   - Descargar `google-services.json` y colocarlo en `app/`
   - Habilitar Authentication, Realtime Database y Storage

3. **Configurar Google Maps**
   - Obtener API Key de [Google Cloud Console](https://console.cloud.google.com/)
   - Reemplazar en `app/src/debug/res/values/google_maps_api.xml`

4. **Sincronizar proyecto**
   ```bash
   ./gradlew clean build
   ```

5. **Ejecutar aplicación**
   - Conectar dispositivo Android o iniciar emulador
   - Ejecutar desde Android Studio o `./gradlew installDebug`

## Mejoras Implementadas

### 🔧 Actualizaciones de Dependencias
- **AndroidX**: Actualizado a las últimas versiones estables
- **Firebase**: Migrado a las versiones más recientes y seguras
- **Google Services**: Actualizado para compatibilidad con Android 14
- **Material Design**: Mejorado para mejor experiencia de usuario

### 🛡️ Seguridad y Validaciones
- **Validaciones robustas**: Implementadas en todos los formularios
- **Manejo de errores centralizado**: Clase `ErrorHandler` para gestión uniforme
- **Validación de entrada**: Sanitización y validación de datos de usuario
- **Manejo seguro de sesiones**: Mejoras en `SharedPreferences_Util`

### 🏗️ Arquitectura y Código
- **Código refactorizado**: Mejor organización y legibilidad
- **Clases utilitarias**: `Utils`, `Constants`, `NetworkUtils` para reutilización
- **Manejo de errores mejorado**: Try-catch y validaciones en operaciones críticas
- **Documentación**: Comentarios Javadoc en métodos principales

### 📱 Experiencia de Usuario
- **Validaciones en tiempo real**: Feedback inmediato al usuario
- **Mensajes de error claros**: Información específica sobre problemas
- **Mejor navegación**: Flujos de usuario optimizados
- **Gestión de sesión mejorada**: Verificación automática de login

## Clases Utilitarias

### Constants.java
Centraliza todas las constantes de la aplicación:
- Tipos de usuario y estados
- Rutas de Firebase
- Configuraciones de UI y red
- Mensajes de error y éxito
- Patrones de validación

### Utils.java
Métodos de utilidad para:
- Validación de datos (email, teléfono, contraseña)
- Formateo de precios y fechas
- Manejo de Toasts
- Conversiones seguras de tipos
- Limpieza y capitalización de texto

### NetworkUtils.java
Gestión de conectividad:
- Verificación de conexión a internet
- Detección de tipo de conexión (WiFi, móvil)
- Validación de estabilidad de red
- Estimación de velocidad de conexión

### ErrorHandler.java
Manejo centralizado de errores:
- Errores de Firebase Database y Storage
- Errores de red y autenticación
- Errores de validación y permisos
- Logging y reporte de errores

### ProductHelperClass.java
Modelo mejorado para productos:
- Validaciones de datos
- Cálculo de precios con descuento
- Verificación de stock
- Formateo de información

## Internacionalización

La aplicación está preparada para múltiples idiomas con archivos de recursos en:
- `res/values/strings.xml` (Español - por defecto)
- `res/values-en/strings.xml` (Inglés)
- `res/values-fr/strings.xml` (Francés)

### Agregar Nuevo Idioma
1. Crear carpeta `res/values-[código_idioma]/`
2. Copiar `strings.xml` y traducir valores
3. Agregar idioma en configuración del dispositivo

## Características Técnicas

### Arquitectura
- **Patrón MVC**: Separación clara de responsabilidades
- **Fragments**: UI modular y reutilizable
- **Navigation Component**: Navegación declarativa
- **RecyclerView**: Listas eficientes y optimizadas

### Base de Datos
- **Firebase Realtime Database**: Sincronización en tiempo real
- **Firebase Storage**: Almacenamiento de imágenes
- **Firebase Authentication**: Autenticación segura

### Seguridad
- **Validación de entrada**: Sanitización de datos
- **Manejo de sesiones**: Gestión segura de login
- **Permisos**: Control granular de acceso
- **Encriptación**: Protección de datos sensibles

### Performance
- **Lazy Loading**: Carga eficiente de imágenes
- **Paginación**: Carga progresiva de datos
- **Cache**: Almacenamiento local inteligente
- **Compresión**: Optimización de imágenes

## Contribución

1. Fork el proyecto
2. Crear rama para feature (`git checkout -b feature/AmazingFeature`)
3. Commit cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abrir Pull Request

## Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## Contacto

- **Desarrollador**: [Tu Nombre]
- **Email**: [tu-email@ejemplo.com]
- **Proyecto**: [https://github.com/tu-usuario/creative-cake](https://github.com/tu-usuario/creative-cake)

---

**Nota**: Este proyecto es una aplicación educativa y de demostración. Para uso en producción, se recomienda implementar medidas de seguridad adicionales y realizar pruebas exhaustivas. 