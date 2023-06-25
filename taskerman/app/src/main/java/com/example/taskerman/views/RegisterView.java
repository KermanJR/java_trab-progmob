package com.example.taskerman.views;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.taskerman.R;

import com.example.taskerman.database.LocalDatabase;
import com.example.taskerman.databinding.ActivityRegisterViewBinding;
import com.example.taskerman.entities.RegularUser;

public class RegisterView extends AppCompatActivity {
    private EditText nameRegister, emailRegister, passwordRegister;

    private Button btnRegister;
    private ActivityRegisterViewBinding binding;
    private LocalDatabase db;

    private RegularUser dbUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = LocalDatabase.getDatabase(getApplicationContext());

        nameRegister = findViewById(R.id.usernameEditTextRegister);
        emailRegister = findViewById(R.id.emailEditTextRegister);
        passwordRegister = findViewById(R.id.passwordEditTextRegister);
        btnRegister = findViewById(R.id.cadButton);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarUsuario();
            }
        });
    }

    public void cadastrarUsuario(){
        String nameRegister = binding.usernameEditTextRegister.getText().toString();
        String emailRegister = binding.emailEditTextRegister.getText().toString();
        String passwordRegister = binding.passwordEditTextRegister.getText().toString();

        if(nameRegister.equals("")){
            Toast.makeText(this, "O nome do usuário é obrigatório", Toast.LENGTH_SHORT).show();
            return;
        }

        if(emailRegister.equals("")){
            Toast.makeText(this, "O e-mail do usuário é obrigatório", Toast.LENGTH_SHORT).show();
            return;
        }

        if(passwordRegister.equals("")){
            Toast.makeText(this, "A senha do usuário é obrigatória", Toast.LENGTH_SHORT).show();
            return;
        }

        RegularUser newUser = new RegularUser();
        newUser.setUsername(nameRegister);
        newUser.setPassword(passwordRegister);
        newUser.setEmail(emailRegister);

        db.regularUserModel().insertRegularUser(newUser);
        Toast.makeText(this, "usuário cadastrado com sucesso.", Toast.LENGTH_SHORT).show();
        finish();

    }

    public void voltar(View view){
        finish();
    }
}
