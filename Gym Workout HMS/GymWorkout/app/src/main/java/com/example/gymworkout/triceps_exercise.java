package com.example.gymworkout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class triceps_exercise extends AppCompatActivity {
    ListView listView;
    String mTitle[]={"Cable Rope Tricep Pushdown","Close Grip Bench Press","45 Degree Incline Dumbbell Chest Press",
            "Lying Triceps Extension","One-Arm Overhead Extension","Bench Dip","Diamond Push-Ups","Tricep Dips"};
    String mDescription[]={
            "Beginner\nSets:2-3 , Reps:8-10\nIntermediate\nSets:3-4 , Reps:10-12\nAdvance\nSets:4-5 , Reps:12-15",
            "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12",
            "Beginner\nSets:2-3 , Reps:8-10\nIntermediate\nSets:3-4 , Reps:10-12\nAdvance\nSets:4-5 , Reps:12-15",
            "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12",
            "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12",
            "Beginner\nSets:2-3 , Reps:8-10\nIntermediate\nSets:3-4 , Reps:10-12\nAdvance\nSets:4-5 , Reps:12-15",
            "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12",
            "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12"};
    int images[] = {R.drawable.cable_rope_tricep_pushdown,R.drawable.close_grip_bench_press,R.drawable.degree_incline_dumbbell_chest_press,
            R.drawable.lying_triceps_extension,R.drawable.one_arm_overhead_extension,R.drawable.bench_dip,R.drawable.diamond_push_ups,R.drawable.tricep_dips};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triceps_exercise);
        listView=findViewById(R.id.triceps_exercise_listView);
        triceps_exerciseAdaptor adaptor=new triceps_exerciseAdaptor(this,mTitle,mDescription,images);
        listView.setAdapter(adaptor);
        listView.setOnItemClickListener((adapterView, view, position, l) -> {
            if (position == 0)
                Toast.makeText(triceps_exercise.this, "Cable Rope Tricep Pushdown", Toast.LENGTH_SHORT).show();
            if (position == 1)
                Toast.makeText(triceps_exercise.this, "Close Grip Bench Press", Toast.LENGTH_SHORT).show();
            if (position == 2)
                Toast.makeText(triceps_exercise.this, "45 Degree Incline Dumbbell Chest Press", Toast.LENGTH_SHORT).show();
            if (position == 3)
                Toast.makeText(triceps_exercise.this, "Lying Triceps Extension", Toast.LENGTH_SHORT).show();
            if (position == 4)
                Toast.makeText(triceps_exercise.this, "One-Arm Overhead Extension", Toast.LENGTH_SHORT).show();
            if (position == 5)
                Toast.makeText(triceps_exercise.this, "Bench Dip", Toast.LENGTH_SHORT).show();
            if (position == 6)
                Toast.makeText(triceps_exercise.this, "Diamond Push-Ups", Toast.LENGTH_SHORT).show();
            if (position == 7)
                Toast.makeText(triceps_exercise.this, "Tricep Dips", Toast.LENGTH_SHORT).show();
        });
    }
}
class triceps_exerciseAdaptor extends ArrayAdapter<String> {
    Context context;
    String rTitle[];
    String rDesription[];
    int rImage[];

    triceps_exerciseAdaptor(Context c, String title[], String description[], int images[]) {
        super(c, R.layout.exercise_cells, R.id.exercise_heading, title);
        this.context = c;
        this.rTitle = title;
        this.rImage = images;
        this.rDesription = description;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.exercise_cells, parent, false);
        ImageView images = row.findViewById(R.id.exercise_image);
        TextView myTitle = row.findViewById(R.id.exercise_heading);
        TextView myDescription = row.findViewById(R.id.exercise_desc);
        images.setImageResource(rImage[position]);
        myTitle.setText(rTitle[position]);
        myDescription.setText(rDesription[position]);
        return row;
    }
}