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

public class back_exercise extends AppCompatActivity {
    ListView listView;
    String mTitle[]={"Single-Arm Dumbbell Row","Decline Bench Dumbbell Pull-Over","Face Pull (low)",
            "Pull Down","Standing T-Bar Row","Barbell Deadlift","Bent-Over Barbell Row","Wide Grip Pull Up"};
    String mDescription[]=
            {       "Beginner\nSets:2-3 , Reps:8-10\nIntermediate\nSets:3-4 , Reps:10-12\nAdvance\nSets:4-5 , Reps:12-15",
                    "Beginner\nSets:2-3 , Reps:8-10\nIntermediate\nSets:3-4 , Reps:10-12\nAdvance\nSets:4-5 , Reps:12-15",
                    "Beginner\nSets:2-3 , Reps:8-10\nIntermediate\nSets:3-4 , Reps:10-12\nAdvance\nSets:4-5 , Reps:12-15",
                    "Beginner\nSets:2-3 , Reps:8-10\nIntermediate\nSets:3-4 , Reps:10-12\nAdvance\nSets:4-5 , Reps:12-15",
                    "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12",
                    "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12",
                    "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12",
                    "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12"};
    int images[] = {R.drawable.single_arm_dumbbell_row, R.drawable.barbell_benchpress,R.drawable.face_pull_low, R.drawable.pull_down,
            R.drawable.standing_t_bar_row,R.drawable.barbell_benchpress,R.drawable.bent_over_barbell_row,R.drawable.wide_grip_pull_up};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_exercise);
        listView=findViewById(R.id.back_exercise_listView);
        back_exerciseAdaptor adaptor=new back_exerciseAdaptor(this,mTitle,mDescription,images);
        listView.setAdapter(adaptor);
        listView.setOnItemClickListener((adapterView, view, position, l) -> {
            if (position == 0)
                Toast.makeText(back_exercise.this, "Single-Arm Dumbbell Row", Toast.LENGTH_SHORT).show();
            if (position == 1)
                Toast.makeText(back_exercise.this, "Decline Bench Dumbbell Pull-Over", Toast.LENGTH_SHORT).show();
            if (position == 2)
                Toast.makeText(back_exercise.this, "Face Pull (low)", Toast.LENGTH_SHORT).show();
            if (position == 3)
                Toast.makeText(back_exercise.this, "Pull Down", Toast.LENGTH_SHORT).show();
            if (position == 4)
                Toast.makeText(back_exercise.this, "Standing T-Bar Row", Toast.LENGTH_SHORT).show();
            if (position == 5)
                Toast.makeText(back_exercise.this, "Barbell Deadlift", Toast.LENGTH_SHORT).show();
            if (position == 6)
                Toast.makeText(back_exercise.this, "Bent-Over Barbell Row", Toast.LENGTH_SHORT).show();
            if (position == 7)
                Toast.makeText(back_exercise.this, "Wide Grip Pull Up", Toast.LENGTH_SHORT).show();
        });
    }
}
class back_exerciseAdaptor extends ArrayAdapter<String> {
    Context context;
    String rTitle[];
    String rDesription[];
    int rImage[];

    back_exerciseAdaptor(Context c, String title[], String description[], int images[]) {
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