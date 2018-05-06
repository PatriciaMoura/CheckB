package moura.patricia.checkb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button btnLendo;
    private Button btnDesejo;
    private Button btnEmprestado;
    private Button btnNaoLi;
    private Button btnAnotacao;
    private Button btnVoucher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLendo = findViewById(R.id.btn_lendo);
        btnDesejo = findViewById(R.id.btn_desejo);
        btnEmprestado = findViewById(R.id.btn_emprestado);
        btnNaoLi = findViewById(R.id.btn_nao_lido);
        btnAnotacao = findViewById(R.id.btn_anotacao);
        btnVoucher = findViewById(R.id.btn_voucher);

        btnLendo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnDesejo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnEmprestado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnNaoLi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnAnotacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Verifica se usuário está logado
        FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
        //Se não estiver logado
        if (usuario == null){

            //Chama a tela de login
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            // comando para validar que o usuário não volte a essa tela
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.btn_sair:

                logOut();

                return true;

                default:
                    return false;


        }
    }

    private void logOut(){

        FirebaseAuth.getInstance().signOut();

        //Chama a tela de login
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        // comando para validar que o usuário não volte a essa tela
        finish();
    }
}
