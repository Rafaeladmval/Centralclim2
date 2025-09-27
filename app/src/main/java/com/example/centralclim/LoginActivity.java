package com.example.centralclim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
// import android.content.Intent;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView forgotPasswordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // dbHelper = new DatabaseHelper(this); // Inicialize o helper do banco de dados

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        forgotPasswordTextView = findViewById(R.id.forgotPasswordTextView);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });

        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria um Intent para iniciar a ForgotPasswordActivity
                Intent intent = new Intent(LoginActivity.this, RecuperarSenhaActivity.class);
                startActivity(intent);
                // Não chame finish() aqui, a menos que você queira fechar a LoginActivity
                // quando o usuário for para a tela de recuperação de senha.
            }
        });
    }

    private void performLogin() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();


        if (email.isEmpty()) {
            emailEditText.setError("O campo de e-mail é obrigatório.");
            emailEditText.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Por favor, insira um e-mail válido.");
            emailEditText.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordEditText.setError("O campo de senha é obrigatório.");
            passwordEditText.requestFocus();
            return;
        }

        // Autenticação com o banco de dados
        authenticateUser(email, password);
    }

    private void authenticateUser(String email, String password) {
        Toast.makeText(this, "Verificando credenciais...", Toast.LENGTH_SHORT).show();

        // **Lógica de autenticação com SQLite (Exemplo)**
        // Substitua esta lógica pela sua implementação real do SQLite
        /*
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                // Colunas que você quer retornar
                DatabaseHelper.COLUMN_ID // Exemplo
        };
        String selection = DatabaseHelper.COLUMN_EMAIL + " = ? AND " + DatabaseHelper.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = { email, password }; // Idealmente, a senha deve ser hasheada

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_USERS, // Nome da sua tabela de usuários
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            // Usuário encontrado e senha corresponde
            Toast.makeText(LoginActivity.this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show();
            // Iniciar a próxima Activity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class); // Substitua MainActivity pela sua Activity principal
            startActivity(intent);
            finish(); // Finaliza a LoginActivity para que o usuário não possa voltar para ela pressionando "voltar"
            cursor.close();
        } else {
            // Usuário não encontrado ou senha incorreta
            Toast.makeText(LoginActivity.this, "E-mail ou senha inválidos.", Toast.LENGTH_SHORT).show();
            if (cursor != null) {
                cursor.close();
            }
        }
        db.close();
        */


    }

    // Você precisará de uma classe DatabaseHelper como esta (simplificada):
    /*
    public class DatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "users.db";
        private static final int DATABASE_VERSION = 1;

        public static final String TABLE_USERS = "users";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASSWORD = "password"; // Armazene senhas hasheadas!

        private static final String TABLE_CREATE =
                "CREATE TABLE " + TABLE_USERS + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_EMAIL + " TEXT UNIQUE NOT NULL, " +
                        COLUMN_PASSWORD + " TEXT NOT NULL);";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            onCreate(db);
        }
    }
    */
}