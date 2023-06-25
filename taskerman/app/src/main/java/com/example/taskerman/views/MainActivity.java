package com.example.taskerman.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taskerman.R;
import com.example.taskerman.database.LocalDatabase;
import com.example.taskerman.databinding.ActivityMainBinding;
import com.example.taskerman.entities.RegularUser;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView registerButton;
    private LocalDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.btnRegister);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                db = LocalDatabase.getDatabase(getApplicationContext());
                RegularUser regularUser = db.regularUserModel().authenticate(username, password);
                if (regularUser != null) {
                    Intent regularUserIntent = new Intent(MainActivity.this, TaskListView.class);
                    startActivity(regularUserIntent);
                    // Usuário regular autenticado com sucesso
                    Toast.makeText(MainActivity.this, "Login bem-sucedido", Toast.LENGTH_SHORT).show();
                    finish();
                    // Faça a ação necessária para o usuário regular
                }/* else if (username.equals(adminUser.getUsername()) && password.equals(adminUser.getPassword())) {
                    Intent adminIntent = new Intent(MainActivity.this, AdminActivity.class);
                    // Usuário administrador autenticado com sucesso
                    Toast.makeText(MainActivity.this, "Login de administrador bem-sucedido", Toast.LENGTH_SHORT).show();
                    // Faça a ação necessária para o usuário administrador
                    finish();
                } */else {
                    // Credenciais inválidas
                    Toast.makeText(MainActivity.this, "Credenciais inválidas", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerActivityIntent = new Intent(MainActivity.this, RegisterView.class);
                startActivity(registerActivityIntent);
                finish();
            }
        });
    }

}