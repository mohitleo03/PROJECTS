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

public class abs_exercise extends AppCompatActivity {
    ListView listView;
    String mTitle[]={"Dumbbell Crunch","Bicycle Crunches","Modified V Sit","Seated Russian Twist","Hanging Leg Raise"
            ,"Hanging Knee Raise Twist","Truck Crunch","Decline Plank Foot Touch"};
    String mDescription[]={
            "Beginner\nSets:2-3 , Reps:8-10\nIntermediate\nSets:3-4 , Reps:10-12\nAdvance\nSets:4-5 , Reps:12-15",
            "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12",
            "Beginner\nSets:2-3 , Reps:8-10\nIntermediate\nSets:3-4 , Reps:10-12\nAdvance\nSets:4-5 , Reps:12-15",
            "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12",
            "Beginner\nSets:2-3 , Reps:8-10\nIntermediate\nSets:3-4 , Reps:10-12\nAdvance\nSets:4-5 , Reps:12-15",
            "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12",
            "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12",
            "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12"};
    int images[] = {R.drawable.dumbbell_crunch,R.drawable.bicycle_crunches,R.drawable.modified_v_sit,R.drawable.seated_russian_twist,
            R.drawable.hanging_leg_raise,R.drawable.hanging_knee_raise_twist,R.drawable.truck_crunch,R.drawable.decline_plank_foot_touch};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs_exercise);
        listView=findViewById(R.id.abs_exercise_listView);
        abs_exerciseAdaptor adaptor=new abs_exerciseAdaptor(this,mTitle,mDescription,images);
        listView.setAdapter(adaptor);
        listView.setOnItemClickListener((adapterView, view, position, l) -> {
            if (position == 0)
                Toast.makeText(abs_exercise.this, "Dumbbell Crunch", Toast.LENGTH_SHORT).show();
            if (position == 1)
                Toast.makeText(abs_exercise.this, "Bicycle Crunches", Toast.LENGTH_SHORT).show();
            if (position == 2)
                Toast.makeText(abs_exercise.this, "Modified V Sit", Toast.LENGTH_SHORT).show();
            if (position == 3)
                Toast.makeText(abs_exercise.this, "Seated Russian Twist", Toast.LENGTH_SHORT).show();
            if (position == 4)
                Toast.makeText(abs_exercise.this, "Hanging Leg Raise", Toast.LENGTH_SHORT).show();
            if (position == 5)
                Toast.makeText(abs_exercise.this, "Hanging Knee Raise Twist", Toast.LENGTH_SHORT).show();
            if (position == 6)
                Toast.makeText(abs_exercise.this, "Truck Crunch", Toast.LENGTH_SHORT).show();
            if (position == 7)
                Toast.makeText(abs_exercise.this, "Decline Plank Foot Touch", Toast.LENGTH_SHORT).show();
        });
    }
}
class abs_exerciseAdaptor extends ArrayAdapter<String> {
    Context context;
    String rTitle[];
    String rDesription[];
    int rImage[];

    abs_exerciseAdaptor(Context c, String title[], String description[], int images[]) {
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