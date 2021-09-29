package com.example.gymworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class Workouts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);
        Button chest_exercise =findViewById(R.id.chest_exercise);
        chest_exercise.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Chest Exercise", Toast.LENGTH_SHORT).show();
            Intent chest = new Intent(Workouts.this,chest_exercise.class);
            startActivity(chest);
        });
        Button back_exercise =findViewById(R.id.back_exercise);
        back_exercise.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Back Exercise", Toast.LENGTH_SHORT).show();
            Intent back = new Intent(Workouts.this,back_exercise.class);
            startActivity(back);
        });
        Button biceps_exercise =findViewById(R.id.biceps_exercise);
        biceps_exercise.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Biceps Exercise", Toast.LENGTH_SHORT).show();
            Intent biceps = new Intent(Workouts.this,biceps_exercise.class);
            startActivity(biceps);
        });
        Button triceps_exercise =findViewById(R.id.triceps_exercise);
        triceps_exercise.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Triceps Exercise", Toast.LENGTH_SHORT).show();
            Intent triceps = new Intent(Workouts.this,triceps_exercise.class);
            startActivity(triceps);
        });
        Button shoulder_exercise =findViewById(R.id.shoulder_exercise);
        shoulder_exercise.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Shoulder Exercise", Toast.LENGTH_SHORT).show();
            Intent shoulder = new Intent(Workouts.this,shoulder_exercise.class);
            startActivity(shoulder);
        });
        Button abs_exercise =findViewById(R.id.abs_exercise);
        abs_exercise.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Abs Exercise", Toast.LENGTH_SHORT).show();
            Intent abs = new Intent(Workouts.this,abs_exercise.class);
            startActivity(abs);
        });
        Button legs_exercise =findViewById(R.id.leg_exercise);
        legs_exercise.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Legs Exercise", Toast.LENGTH_SHORT).show();
            Intent legs = new Intent(Workouts.this,legs_exercise.class);
            startActivity(legs);
        });
    }
}