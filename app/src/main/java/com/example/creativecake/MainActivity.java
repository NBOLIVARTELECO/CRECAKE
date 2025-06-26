package com.example.creativecake;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    
    private Button iniciaSesionCliente, registro, iniciaSesionDom, iniciaSesionNeg, iniciaSesionAdmin;
    private EditText etTelefono, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initializeViews();
        setupClickListeners();
        checkExistingSession();
    }

    private void initializeViews() {
        iniciaSesionCliente = findViewById(R.id.iniciaSesionCliente);
        registro = findViewById(R.id.idBotonRegistro);
        iniciaSesionDom = findViewById(R.id.iniciaSesionDomiciliario);
        iniciaSesionNeg = findViewById(R.id.iniciaSesionNegocio);
        iniciaSesionAdmin = findViewById(R.id.iniciaSesionAdmin);

        etTelefono = findViewById(R.id.idTelefonoMain);
        etPassword = findViewById(R.id.idPasswordMain);
    }

    private void setupClickListeners() {
        iniciaSesionCliente.setOnClickListener(v -> loginCliente());
        registro.setOnClickListener(v -> {
            Intent registro = new Intent(getApplicationContext(), Registro.class);
            startActivity(registro);
        });
        iniciaSesionDom.setOnClickListener(v -> loginDomiciliario());
        iniciaSesionNeg.setOnClickListener(v -> loginStore());
        iniciaSesionAdmin.setOnClickListener(v -> loginDeveloper());
    }

    private void checkExistingSession() {
        if (SharedPreferences_Util.isUserLoggedIn(this)) {
            String userType = SharedPreferences_Util.getType_SP(this);
            navigateToUserActivity(userType);
        }
    }

    private void navigateToUserActivity(String userType) {
        Intent intent = null;
        switch (userType) {
            case "User":
                intent = new Intent(getApplicationContext(), MainCliente.class);
                break;
            case "Tienda":
                intent = new Intent(getApplicationContext(), Inicio_tienda.class);
                break;
            case "Domiciliario":
                intent = new Intent(getApplicationContext(), MainDomiciliario.class);
                break;
            case "Admin":
                intent = new Intent(getApplicationContext(), MainAdministrador.class);
                break;
        }
        
        if (intent != null) {
            startActivity(intent);
            finish();
        }
    }

    private boolean validateInputs() {
        if (!validateTelefono()) {
            return false;
        }
        if (!validatePassword()) {
            return false;
        }
        return true;
    }

    private boolean validateTelefono() {
        String telefono = etTelefono.getText().toString().trim();
        if (telefono.isEmpty()) {
            etTelefono.setError("El teléfono no puede estar vacío");
            etTelefono.requestFocus();
            return false;
        }
        if (telefono.length() < 7) {
            etTelefono.setError("El teléfono debe tener al menos 7 dígitos");
            etTelefono.requestFocus();
            return false;
        }
        etTelefono.setError(null);
        return true;
    }

    private boolean validatePassword() {
        String password = etPassword.getText().toString().trim();
        if (password.isEmpty()) {
            etPassword.setError("La contraseña no puede estar vacía");
            etPassword.requestFocus();
            return false;
        }
        if (password.length() < 4) {
            etPassword.setError("La contraseña debe tener al menos 4 caracteres");
            etPassword.requestFocus();
            return false;
        }
        etPassword.setError(null);
        return true;
    }

    public void loginCliente() {
        if (!validateInputs()) {
            return;
        }
        authenticateUser("usuarioCliente", "User");
    }

    public void loginDomiciliario() {
        if (!validateInputs()) {
            return;
        }
        authenticateUser("usuarioDomiciliario", "Domiciliario");
    }

    public void loginStore() {
        if (!validateInputs()) {
            return;
        }
        authenticateUser("usuarioNegocio", "Tienda");
    }

    public void loginDeveloper() {
        if (!validateInputs()) {
            return;
        }
        authenticateUser("usuarioDev", "Admin");
    }

    private void authenticateUser(String databasePath, String userType) {
        final String numeroIngresado = etTelefono.getText().toString().trim();
        final String passwordIngresado = etPassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(databasePath);
        Query checkUsuario = reference.orderByChild("telefono").equalTo(numeroIngresado);

        checkUsuario.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    etTelefono.setError(null);
                    String passwordFromDB = snapshot.child(numeroIngresado).child("password").getValue(String.class);

                    if (passwordFromDB != null && passwordFromDB.equals(passwordIngresado)) {
                        // Guardar datos de sesión
                        SharedPreferences_Util.savePhone_SP(numeroIngresado, getApplicationContext());
                        SharedPreferences_Util.savePassword_SP(passwordIngresado, getApplicationContext());
                        SharedPreferences_Util.saveType_SP(userType, getApplicationContext());

                        // Navegar a la actividad correspondiente
                        navigateToUserActivity(userType);
                    } else {
                        etPassword.setError("Contraseña incorrecta");
                        etPassword.requestFocus();
                        Toast.makeText(MainActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    etTelefono.setError("Usuario no encontrado");
                    etTelefono.requestFocus();
                    Toast.makeText(MainActivity.this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Database error: " + error.getMessage());
                Toast.makeText(MainActivity.this, "Error de conexión. Intente nuevamente.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}