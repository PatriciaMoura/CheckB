package moura.patricia.checkb;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEmailText;
    private EditText loginSenhaText;
    private Button btnLogin;
    private Button btnLoginCad;
    private ProgressBar loginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmailText = findViewById(R.id.cad_email);
        loginSenhaText = findViewById(R.id.cad_senha);
        btnLogin = findViewById(R.id.btn_login);
        btnLoginCad = findViewById(R.id.btn_login_cad);
        loginProgress = findViewById(R.id.login_progress);

        btnLoginCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent cadastroIntent = new Intent(LoginActivity.this, CadastroActicity.class);
                startActivity(cadastroIntent);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String loginEmail = loginEmailText.getText().toString();
                String loginSenha = loginSenhaText.getText().toString();

                if(!TextUtils.isEmpty(loginEmail) && !TextUtils.isEmpty(loginSenha)){
                    loginProgress.setVisibility(View.VISIBLE);

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(loginEmail,loginSenha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){

                                //Chama a tela de login
                                Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(mainIntent);
                                // comando para validar que o usuário não volte a essa tela
                                finish();

                            } else {

                                String erro = task.getException().getMessage();
                                Toast.makeText(LoginActivity.this, "Erro:" + erro, Toast.LENGTH_LONG).show();
                            }

                            loginProgress.setVisibility(View.INVISIBLE);
                        }
                    });

                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        // Verifica se usuário está logado
        FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
        //Se não estiver logado
        if (usuario != null){

            //Chama a tela de login
            Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(mainIntent);
            // comando para validar que o usuário não volte a essa tela
            finish();
        }
    }
}
