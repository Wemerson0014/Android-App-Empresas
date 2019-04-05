package br.com.desafio.appempresas.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import br.com.desafio.appempresas.R;
import br.com.desafio.appempresas.network.RetrofitConfig;
import br.com.desafio.appempresas.network.model.Header;

import br.com.desafio.appempresas.ui.home.HomeActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static br.com.desafio.appempresas.ui.home.HomeActivity.EXTRA_HEADER;

public class LoginActivity extends AppCompatActivity {

    Button buttonLogin;
    EditText editEmail;
    EditText editPassword;

    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validatorLogin()) {
                    login();
                } else {
                    Toast.makeText(LoginActivity.this, "Preencha os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void login() {
        Call call = new RetrofitConfig().service().login(email, password);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    String uid = response.headers().get("uid");
                    String client = response.headers().get("client");
                    String accessToken = response.headers().get("access-token");
                    Header header = new Header(uid, accessToken, client);

                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra(EXTRA_HEADER, header);

                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Erro ao conectar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Erro inesperado.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validatorLogin() {
        email = editEmail.getText().toString();
        password = editPassword.getText().toString();
        return !email.isEmpty() || !password.isEmpty();
    }
}


