package com.juniorrodrigues.progressbar_estudo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private int contador = 0;

    private ProgressBar progressBar;
    private ProgressBar progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        progressBar2 = findViewById(R.id.progressBar2);

        progressBar2.setVisibility(View.GONE);
    }

    public void carregarProgresso(View view){
        progressBar2.setVisibility(View.VISIBLE);

//        this.contador += 10;
//        this.progressBar.setProgress(this.contador);


        //criando uma thread (o resultado nao consegue atualizar automaticamente a interace)
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<= 100; i++){
//                    progressBar.setProgress(i);
                    final int progress = i; // com não posso jogar o i diretamente , crio uma final para esse proposito.

                    //Thread responsavel por atualizar a interface
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progress);
                            if(progress==100){
                                progressBar2.setVisibility(View.GONE);
                            }
                        }
                    });
                    try {
                        Thread.sleep(100); //Definindo Delay
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();//Start da thread

    }
}
