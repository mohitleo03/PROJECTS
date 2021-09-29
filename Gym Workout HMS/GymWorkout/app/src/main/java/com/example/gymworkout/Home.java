package com.example.gymworkout;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Home extends AppCompatActivity{
    TextView welcome_msg;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent welcome=getIntent();
        String wel_msg=welcome.getStringExtra("name");
        welcome_msg=(TextView)findViewById(R.id.welcome);
        welcome_msg.setText("Welcome "+wel_msg);
        Button warmup = findViewById(R.id.warmUp);
        warmup.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "WarmUp", Toast.LENGTH_SHORT).show();
            Intent WarmUp = new Intent(Home.this, WarmUp.class);
            startActivity(WarmUp);
        });

        Button workout = findViewById(R.id.workout);
        workout.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Workouts", Toast.LENGTH_SHORT).show();
            Intent Workouts = new Intent(Home.this,Workouts.class);
            startActivity(Workouts);

        });

        Button diet = findViewById(R.id.diet);
        diet.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Diet Plan", Toast.LENGTH_SHORT).show();
            Intent Diet = new Intent(Home.this, Diet.class);
            startActivity(Diet);
        });

        Button tips = findViewById(R.id.tips);
        tips.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Tips", Toast.LENGTH_SHORT).show();
            Intent Tips = new Intent(Home.this, Tips.class);
            startActivity(Tips);
        });

    }
}

