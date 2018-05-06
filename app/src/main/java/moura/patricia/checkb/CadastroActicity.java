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

public class CadastroActicity extends AppCompatActivity {

    private EditText confSenha;
    private EditText cadSenha;
    private EditText cadEmail;
    private Button btnCadastrar;
    private ProgressBar cadProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        confSenha = findViewById(R.id.conf_senha);
        cadSenha = findViewById(R.id.cad_senha);
        cadEmail = findViewById(R.id.cad_email);
        btnCadastrar = findViewById(R.id.btn_cad);
        cadProgressBar = findViewById(R.id.cad_progress);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = cadEmail.getText().toString();
                String senha = cadSenha.getText().toString();
                String conf_senha = confSenha.getText().toString();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(senha) & !TextUtils.isEmpty(conf_senha)){
                    if (senha.equals(conf_senha)){

                        cadProgressBar.setVisibility(View.VISIBLE);

                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){

                                    Intent perfilIntent = new Intent(CadastroActicity.this, PerfilActivity.class);
                                    startActivity(perfilIntent);
                                    finish();

                                } else {

                                    String errorMessage = task.getException().getMessage();
                                    Toast.makeText(CadastroActicity.this, "Error : " + errorMessage, Toast.LENGTH_LONG).show();

                                }

                                cadProgressBar.setVisibility(View.INVISIBLE);

                            }
                        });
                    } else {

                        Toast.makeText(CadastroActicity.this, "Senha diferente", Toast.LENGTH_LONG);
                    }

                }
            }
        });
    }
}
