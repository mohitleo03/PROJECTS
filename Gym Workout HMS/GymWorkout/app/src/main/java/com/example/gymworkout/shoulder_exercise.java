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

public class shoulder_exercise extends AppCompatActivity {
    ListView listView;
    String mTitle[]={"Bent Over Dumbbell Lateral Raise","Reverse Pec Deck Fly","Barbell Overhead Shoulder Press","Seated Dumbbell Shoulder Press",
            "Front Raise with Rectangular Road","Standing Barbell Shrugs","Reverse Cable Crossover","One-Arm Cable Lateral Raise"};
    String mDescription[]={
            "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12",
            "Beginner\nSets:2-3 , Reps:8-10\nIntermediate\nSets:3-4 , Reps:10-12\nAdvance\nSets:4-5 , Reps:12-15",
            "Beginner\nSets:2-3 , Reps:8-10\nIntermediate\nSets:3-4 , Reps:10-12\nAdvance\nSets:4-5 , Reps:12-15",
            "Beginner\nSets:2-3 , Reps:8-10\nIntermediate\nSets:3-4 , Reps:10-12\nAdvance\nSets:4-5 , Reps:12-15",
            "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12",
            "Beginner\nSets:2-3 , Reps:8-10\nIntermediate\nSets:3-4 , Reps:10-12\nAdvance\nSets:4-5 , Reps:12-15",
            "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12",
            "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12"};
    int images[] = {R.drawable.bent_over_dumbbell_ateral_raise,R.drawable.reverse_pec_deck_fly,R.drawable.barbell_overhead_shoulder_press,
            R.drawable.seated_dumbbell_houlder_ress,R.drawable.front_raise_with_rectangula_road,R.drawable.standing_barbell_shrugs,
            R.drawable.reverse_cable_crossover,R.drawable.one_arm_cable_lateral_raise};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoulder_exercise);
        listView=findViewById(R.id.shoulder_exercise_listView);
        shoulder_exerciseAdaptor adaptor=new shoulder_exerciseAdaptor(this,mTitle,mDescription,images);
        listView.setAdapter(adaptor);
        listView.setOnItemClickListener((adapterView, view, position, l) -> {
            if (position == 0)
                Toast.makeText(shoulder_exercise.this, "Bent Over Dumbbell Lateral Raise", Toast.LENGTH_SHORT).show();
            if (position == 1)
                Toast.makeText(shoulder_exercise.this, "Reverse Pec Deck Fly", Toast.LENGTH_SHORT).show();
            if (position == 2)
                Toast.makeText(shoulder_exercise.this, "Barbell Overhead Shoulder Press", Toast.LENGTH_SHORT).show();
            if (position == 3)
                Toast.makeText(shoulder_exercise.this, "Seated Dumbbell Shoulder Press", Toast.LENGTH_SHORT).show();
            if (position == 4)
                Toast.makeText(shoulder_exercise.this, "Front Raise with Rectangular Road", Toast.LENGTH_SHORT).show();
            if (position == 5)
                Toast.makeText(shoulder_exercise.this, "Standing Barbell Shrugs", Toast.LENGTH_SHORT).show();
            if (position == 6)
                Toast.makeText(shoulder_exercise.this, "Reverse Cable Crossover", Toast.LENGTH_SHORT).show();
            if (position == 7)
                Toast.makeText(shoulder_exercise.this, "One-Arm Cable Lateral Raise", Toast.LENGTH_SHORT).show();
        });
    }
}
class shoulder_exerciseAdaptor extends ArrayAdapter<String> {
    Context context;
    String rTitle[];
    String rDesription[];
    int rImage[];

    shoulder_exerciseAdaptor(Context c, String title[], String description[], int images[]) {
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