package com.example.creativecake;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroCliente extends AppCompatActivity
{
    private static final String TAG = "RegistroCliente";

    private FirebaseDatabase database;
    private DatabaseReference reference;
    private EditText  etNombre, etCorreo, etPassword, etTelefono, etDireccion, etEdad;
    private Button botonSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cliente);

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        etNombre = findViewById(R.id.idNombre);
        etCorreo = findViewById(R.id.idCorreo);
        etPassword = findViewById(R.id.idPassword);
        etTelefono = findViewById(R.id.idTelefono);
        etDireccion = findViewById(R.id.idDireccion);
        etEdad = findViewById(R.id.idEdad);
        botonSiguiente = findViewById(R.id.botonSiguiente);
    }

    private void setupClickListeners() {
        botonSiguiente.setOnClickListener(v -> {
            if (validateInputs()) {
                registerUser();
            }
        });
    }

    private boolean validateInputs() {
        // Validar nombre
        String nombre = etNombre.getText().toString().trim();
        if (TextUtils.isEmpty(nombre)) {
            etNombre.setError("El nombre es requerido");
            etNombre.requestFocus();
            return false;
        }
        if (nombre.length() < 2) {
            etNombre.setError("El nombre debe tener al menos 2 caracteres");
            etNombre.requestFocus();
            return false;
        }

        // Validar correo
        String correo = etCorreo.getText().toString().trim();
        if (TextUtils.isEmpty(correo)) {
            etCorreo.setError("El correo es requerido");
            etCorreo.requestFocus();
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            etCorreo.setError("Ingrese un correo válido");
            etCorreo.requestFocus();
            return false;
        }

        // Validar contraseña
        String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("La contraseña es requerida");
            etPassword.requestFocus();
            return false;
        }
        if (password.length() < 4) {
            etPassword.setError("La contraseña debe tener al menos 4 caracteres");
            etPassword.requestFocus();
            return false;
        }

        // Validar teléfono
        String telefono = etTelefono.getText().toString().trim();
        if (TextUtils.isEmpty(telefono)) {
            etTelefono.setError("El teléfono es requerido");
            etTelefono.requestFocus();
            return false;
        }
        if (telefono.length() < 7) {
            etTelefono.setError("El teléfono debe tener al menos 7 dígitos");
            etTelefono.requestFocus();
            return false;
        }

        // Validar dirección
        String direccion = etDireccion.getText().toString().trim();
        if (TextUtils.isEmpty(direccion)) {
            etDireccion.setError("La dirección es requerida");
            etDireccion.requestFocus();
            return false;
        }

        // Validar edad
        String edad = etEdad.getText().toString().trim();
        if (TextUtils.isEmpty(edad)) {
            etEdad.setError("La edad es requerida");
            etEdad.requestFocus();
            return false;
        }
        try {
            int edadInt = Integer.parseInt(edad);
            if (edadInt < 13 || edadInt > 120) {
                etEdad.setError("La edad debe estar entre 13 y 120 años");
                etEdad.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            etEdad.setError("Ingrese una edad válida");
            etEdad.requestFocus();
            return false;
        }

        return true;
    }

    private void registerUser() {
        String nombre = etNombre.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String telefono = etTelefono.getText().toString().trim();
        String direccion = etDireccion.getText().toString().trim();
        String edad = etEdad.getText().toString().trim();

        // Crear objeto de usuario
        UserHelperClass helperClass = new UserHelperClass(nombre, correo, password, telefono, direccion, edad);

        // Inicializar Firebase
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("usuarioCliente");

        // Verificar si el usuario ya existe
        reference.child(telefono).get().addOnCompleteListener(new OnCompleteListener<com.google.firebase.database.DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<com.google.firebase.database.DataSnapshot> task) {
                if (task.isSuccessful()) {
                    com.google.firebase.database.DataSnapshot snapshot = task.getResult();
                    if (snapshot.exists()) {
                        Toast.makeText(RegistroCliente.this, "El teléfono ya está registrado", Toast.LENGTH_SHORT).show();
                        etTelefono.setError("Teléfono ya registrado");
                        etTelefono.requestFocus();
                    } else {
                        // Registrar usuario
                        saveUserToDatabase(helperClass, telefono);
                    }
                } else {
                    Log.e(TAG, "Error checking user existence: " + task.getException());
                    Toast.makeText(RegistroCliente.this, "Error al verificar usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveUserToDatabase(UserHelperClass helperClass, String telefono) {
        // Guardar usuario principal
        reference.child(telefono).setValue(helperClass)
                .addOnSuccessListener(aVoid -> {
                    // Crear estructuras adicionales para el usuario
                    createUserStructures(telefono);
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Error saving user: " + e.getMessage());
                    Toast.makeText(RegistroCliente.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                });
    }

    private void createUserStructures(String telefono) {
        DatabaseReference carritoRef = database.getReference("carrito").child(telefono);
        DatabaseReference chatRef = database.getReference("chat").child(telefono);
        DatabaseReference pagoCarritoRef = database.getReference("pagoCarrito").child(telefono);
        DatabaseReference cotizacionesRef = database.getReference("cotizaciones").child(telefono);

        // Crear estructuras iniciales
        carritoRef.child("1").setValue(" ");
        chatRef.setValue(" ");
        pagoCarritoRef.child("1").setValue(" ");
        cotizacionesRef.setValue(" ")
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(RegistroCliente.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                    navigateToMainActivity();
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Error creating user structures: " + e.getMessage());
                    Toast.makeText(RegistroCliente.this, "Error al crear estructuras de usuario", Toast.LENGTH_SHORT).show();
                });
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}