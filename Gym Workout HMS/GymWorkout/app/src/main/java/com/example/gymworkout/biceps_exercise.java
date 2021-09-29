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

public class biceps_exercise extends AppCompatActivity {
    ListView listView;
    String mTitle[]={"Alternating Incline Dumbbell Curl","Standing Cable Curl","Decline Dumbbell Curl","Concentration Curl","Cable Flex Curl",
    "Standing Barbell Curl","Standing Reverse Barbell Curl","Zottman Curl"};
    String mDescription[]={
            "Beginner\nSets:2-3 , Reps:8-10\nIntermediate\nSets:3-4 , Reps:10-12\nAdvance\nSets:4-5 , Reps:12-15",
            "Beginner\nSets:2-3 , Reps:8-10\nIntermediate\nSets:3-4 , Reps:10-12\nAdvance\nSets:4-5 , Reps:12-15",
            "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12",
            "Beginner\nSets:2-3 , Reps:8-10\nIntermediate\nSets:3-4 , Reps:10-12\nAdvance\nSets:4-5 , Reps:12-15",
            "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12",
            "Beginner\nSets:2-3 , Reps:8-10\nIntermediate\nSets:3-4 , Reps:10-12\nAdvance\nSets:4-5 , Reps:12-15",
            "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12",
            "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12"};
    int images[] = {R.drawable.alternating_incline_dumbbell_curl,R.drawable.standing_cable_curl,R.drawable.decline_dumbbell_curl,
            R.drawable.concentration_curl,R.drawable.cable_flex_curl,R.drawable.standing_barbell_curl,R.drawable.standing_reverse_barbell_curl,R.drawable.zottman_curl};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biceps_exercise);
        listView=findViewById(R.id.biceps_exercise_listView);
        biceps_exerciseAdaptor adaptor=new biceps_exerciseAdaptor(this,mTitle,mDescription,images);
        listView.setAdapter(adaptor);
        listView.setOnItemClickListener((adapterView, view, position, l) -> {
            if (position == 0)
                Toast.makeText(biceps_exercise.this, "Alternating Incline Dumbbell Curl", Toast.LENGTH_SHORT).show();
            if (position == 1)
                Toast.makeText(biceps_exercise.this, "Standing Cable Curl", Toast.LENGTH_SHORT).show();
            if (position == 2)
                Toast.makeText(biceps_exercise.this, "Decline Dumbbell Curl", Toast.LENGTH_SHORT).show();
            if (position == 3)
                Toast.makeText(biceps_exercise.this, "Concentration Curl", Toast.LENGTH_SHORT).show();
            if (position == 4)
                Toast.makeText(biceps_exercise.this, "Cable Flex Curl", Toast.LENGTH_SHORT).show();
            if (position == 5)
                Toast.makeText(biceps_exercise.this, "Standing Barbell Curl", Toast.LENGTH_SHORT).show();
            if (position == 6)
                Toast.makeText(biceps_exercise.this, "Standing Reverse Barbell Curl", Toast.LENGTH_SHORT).show();
            if (position == 7)
                Toast.makeText(biceps_exercise.this, "Zottman Curl", Toast.LENGTH_SHORT).show();
        });
    }
}
class biceps_exerciseAdaptor extends ArrayAdapter<String> {
    Context context;
    String rTitle[];
    String rDesription[];
    int rImage[];

    biceps_exerciseAdaptor(Context c, String title[], String description[], int images[]) {
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