package com.example.creativecake;

import android.text.TextUtils;

/**
 * Clase helper para gestionar productos de pastelería
 * Incluye validaciones y métodos útiles para el manejo de productos
 */
public class ProductHelperClass {
    private String nombre;
    private String descripcion;
    private String precio;
    private String cantidad;
    private String tipo;
    private String oferta;
    private String rating;
    private String imagenUrl;
    private String tienda;
    private String fechaCreacion;

    public ProductHelperClass() {
        // Constructor vacío requerido para Firebase
    }

    public ProductHelperClass(String nombre, String descripcion, String precio, String cantidad,
                             String tipo, String oferta, String rating, String imagenUrl, String tienda) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.oferta = oferta;
        this.rating = rating;
        this.imagenUrl = imagenUrl;
        this.tienda = tienda;
        this.fechaCreacion = String.valueOf(System.currentTimeMillis());
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOferta() {
        return oferta;
    }

    public void setOferta(String oferta) {
        this.oferta = oferta;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Valida si el producto tiene todos los campos requeridos
     * @return true si el producto es válido
     */
    public boolean isValid() {
        return !TextUtils.isEmpty(nombre) &&
               !TextUtils.isEmpty(precio) &&
               !TextUtils.isEmpty(tipo) &&
               !TextUtils.isEmpty(tienda);
    }

    /**
     * Calcula el precio con descuento aplicado
     * @return precio con descuento o precio original si no hay oferta
     */
    public double getPrecioConDescuento() {
        try {
            double precioOriginal = Double.parseDouble(precio);
            if (!TextUtils.isEmpty(oferta)) {
                double porcentajeDescuento = Double.parseDouble(oferta);
                return precioOriginal * (1 - porcentajeDescuento / 100);
            }
            return precioOriginal;
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    /**
     * Verifica si el producto está en oferta
     * @return true si tiene descuento
     */
    public boolean tieneOferta() {
        return !TextUtils.isEmpty(oferta) && !oferta.equals("0");
    }

    /**
     * Obtiene el rating como float para mostrar en RatingBar
     * @return rating como float
     */
    public float getRatingFloat() {
        try {
            return Float.parseFloat(rating);
        } catch (NumberFormatException e) {
            return 0.0f;
        }
    }

    /**
     * Verifica si hay stock disponible
     * @return true si hay cantidad disponible
     */
    public boolean tieneStock() {
        try {
            int cantidadInt = Integer.parseInt(cantidad);
            return cantidadInt > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Obtiene el precio formateado como string
     * @return precio formateado con símbolo de moneda
     */
    public String getPrecioFormateado() {
        try {
            double precioDouble = Double.parseDouble(precio);
            return String.format("$%.0f", precioDouble);
        } catch (NumberFormatException e) {
            return "$0";
        }
    }

    /**
     * Obtiene el precio con descuento formateado
     * @return precio con descuento formateado
     */
    public String getPrecioConDescuentoFormateado() {
        double precioConDescuento = getPrecioConDescuento();
        return String.format("$%.0f", precioConDescuento);
    }

    @Override
    public String toString() {
        return "ProductHelperClass{" +
                "nombre='" + nombre + '\'' +
                ", precio='" + precio + '\'' +
                ", tipo='" + tipo + '\'' +
                ", tienda='" + tienda + '\'' +
                '}';
    }
}
