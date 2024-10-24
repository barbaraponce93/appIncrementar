package com.example.appincrementar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.appincrementar.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private IncrementarViewModel incrementarViewModel;
    private int res=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        incrementarViewModel=new ViewModelProvider(this).get(IncrementarViewModel.class);
        Log.d("TAG1", "onCreate()");


        binding.tvResCon.setText("Con ViewModel: "+incrementarViewModel.getResultado());
        binding.tvResSin.setText("Sin ViewModel"+res);

        tarea();

    }

    public void tarea(){
        binding.btIncrementarsin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //actualiza solo la varible local, no se actualiza tras la rotacion
                res=Incrementar.incrementar(res);
                binding.tvResSin.setText("Sin ViewModel: "+res);
            }
        });
        binding.btIncrementarCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Incrementa el valor almacenado en el viewmodel(se mantiene tras la rotación)
               incrementarViewModel.setResultado(Incrementar.incrementar(incrementarViewModel.getResultado()));
                binding.tvResCon.setText("Con ViewModel: "+incrementarViewModel.getResultado());
            }
        });
    }
}