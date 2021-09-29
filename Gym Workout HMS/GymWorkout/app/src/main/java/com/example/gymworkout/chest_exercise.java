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
public class chest_exercise extends AppCompatActivity {
    ListView listView;
    String mTitle[]={"Butterfly","Normal or Incline Dumbbell Flye","Barbell Bench Press","Low Incline Bench Press","Dumbbell Bench Press"
            ,"Cable Crossover","Low Cable Crossover","Crossover Pushup Main"};
    String mDescription[]={ "Beginner\nSets:2-3 , Reps:8-10\nIntermediate\nSets:3-4 , Reps:10-12\nAdvance\nSets:4-5 , Reps:12-15",
                            "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12",
                            "Beginner\nSets:2-3 , Reps:8-10\nIntermediate\nSets:3-4 , Reps:10-12\nAdvance\nSets:4-5 , Reps:12-15",
                            "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12",
                            "Beginner\nSets:2-3 , Reps:8-10\nIntermediate\nSets:3-4 , Reps:10-12\nAdvance\nSets:4-5 , Reps:12-15",
                            "Beginner\nSets:2-3 , Reps:8-10\nIntermediate\nSets:3-4 , Reps:10-12\nAdvance\nSets:4-5 , Reps:12-15",
                            "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12",
                            "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12"};
    int images[] = {R.drawable.butterfly,R.drawable.normal_or_incline_dumbbell_flye,R.drawable.barbell_benchpress,R.drawable.low_incline_press
            ,R.drawable.dumbbell_bench_press,R.drawable.cablecrossover,R.drawable.low_cable_crossover,R.drawable.crossover_pushup_main};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest_exercise);
        listView=findViewById(R.id.chest_exercise_listView);
        chest_exerciseAdaptor adaptor=new chest_exerciseAdaptor(this,mTitle,mDescription,images);
        listView.setAdapter(adaptor);
        listView.setOnItemClickListener((adapterView, view, position, l) -> {
            if (position == 0)
                Toast.makeText(chest_exercise.this, "Butterfly", Toast.LENGTH_SHORT).show();
            if (position == 1)
                Toast.makeText(chest_exercise.this, "Normal or Incline Dumbbell Flye", Toast.LENGTH_SHORT).show();
            if (position == 2)
                Toast.makeText(chest_exercise.this, "Barbell Bench Press", Toast.LENGTH_SHORT).show();
            if (position == 3)
                Toast.makeText(chest_exercise.this, "Low Incline Bench Press", Toast.LENGTH_SHORT).show();
            if (position == 4)
                Toast.makeText(chest_exercise.this, "Dumbbell Bench Press", Toast.LENGTH_SHORT).show();
            if (position == 5)
                Toast.makeText(chest_exercise.this, "Cable Crossover", Toast.LENGTH_SHORT).show();
            if (position == 6)
                Toast.makeText(chest_exercise.this, "Low Cable Crossover", Toast.LENGTH_SHORT).show();
            if (position == 7)
                Toast.makeText(chest_exercise.this, "Crossover Pushup Main", Toast.LENGTH_SHORT).show();
        });
    }
}
class chest_exerciseAdaptor extends ArrayAdapter<String> {
    Context context;
    String rTitle[];
    String rDesription[];
    int rImage[];

    chest_exerciseAdaptor(Context c, String title[], String description[], int images[]) {
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