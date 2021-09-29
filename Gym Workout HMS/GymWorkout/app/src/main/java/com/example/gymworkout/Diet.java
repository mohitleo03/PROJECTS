package com.example.gymworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class Diet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);
        Button weight_loss=findViewById(R.id.weight_loss);
        weight_loss.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Weight Loss", Toast.LENGTH_SHORT).show();
            Intent Weight_Loss = new Intent(Diet.this,weight_loss.class);
            startActivity(Weight_Loss);
        });
        Button weight_gain=findViewById(R.id.weight_gain);
        weight_gain.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Weight Gian", Toast.LENGTH_SHORT).show();
            Intent Weight_Gain = new Intent(Diet.this,weight_gain.class);
            startActivity(Weight_Gain);
        });
        Button muscle_booster=findViewById(R.id.muscle_booster);
        muscle_booster.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Muscle Booster", Toast.LENGTH_SHORT).show();
            Intent Muscle_Booster = new Intent(Diet.this,muscle_booster.class);
            startActivity(Muscle_Booster);
        });
        Button whey_protein=findViewById(R.id.whey_protein);
        whey_protein.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Home Made Whey Protein", Toast.LENGTH_SHORT).show();
            Intent protein = new Intent(Diet.this,home_made_whey_protein.class);
            startActivity(protein);
        });
    }
}