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

public class legs_exercise extends AppCompatActivity {
    ListView listView;
    String mTitle[]={"Lunges With Weight","Jump Squat","Leg Extension","Hamstring Curl","Leg-Press","Squats With Weight"
            ,"Seated Calf Raises","Wall Sit"};
    String mDescription[]={
            "Beginner\nSets:2-3 , Reps:8-10\nIntermediate\nSets:3-4 , Reps:10-12\nAdvance\nSets:4-5 , Reps:12-15",
            "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12",
            "Beginner\nSets:2-3 , Reps:8-10\nIntermediate\nSets:3-4 , Reps:10-12\nAdvance\nSets:4-5 , Reps:12-15",
            "Beginner\nSets:2-3 , Reps:8-10\nIntermediate\nSets:3-4 , Reps:10-12\nAdvance\nSets:4-5 , Reps:12-15",
            "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12",
            "Beginner\nSets:1-2 , Reps:6-8 \nIntermediate\nSets:2-3 , Reps:8-10 \nAdvance\nSets:3-4 , Reps:10-12",
            "Beginner\nSets:2-3 , Reps:8-10\nIntermediate\nSets:3-4 , Reps:10-12\nAdvance\nSets:4-5 , Reps:12-15",
            "Beginner\nSets:2-3 , Reps:8-10\nIntermediate\nSets:3-4 , Reps:10-12\nAdvance\nSets:4-5 , Reps:12-15"};
    int images[] = {R.drawable.lunges_with_weight,R.drawable.jump_squat,R.drawable.leg_extension,R.drawable.hamstring_curl,
            R.drawable.leg_press,R.drawable.squats_with_weight,R.drawable.seated_calf_raises,R.drawable.wall_sit};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legs_exercise);
        listView=findViewById(R.id.legs_exercise_listView);
        legs_exerciseAdaptor adaptor=new legs_exerciseAdaptor(this,mTitle,mDescription,images);
        listView.setAdapter(adaptor);
        listView.setOnItemClickListener((adapterView, view, position, l) -> {
            if (position == 0)
                Toast.makeText(legs_exercise.this, "Lunges With Weight", Toast.LENGTH_SHORT).show();
            if (position == 1)
                Toast.makeText(legs_exercise.this, "Jump Squat", Toast.LENGTH_SHORT).show();
            if (position == 2)
                Toast.makeText(legs_exercise.this, "Leg Extension", Toast.LENGTH_SHORT).show();
            if (position == 3)
                Toast.makeText(legs_exercise.this, "Hamstring Curl", Toast.LENGTH_SHORT).show();
            if (position == 4)
                Toast.makeText(legs_exercise.this, "Leg-Press", Toast.LENGTH_SHORT).show();
            if (position == 5)
                Toast.makeText(legs_exercise.this, "Squats With Weight", Toast.LENGTH_SHORT).show();
            if (position == 6)
                Toast.makeText(legs_exercise.this, "Seated Calf Raises", Toast.LENGTH_SHORT).show();
            if (position == 7)
                Toast.makeText(legs_exercise.this, "Wall Sit", Toast.LENGTH_SHORT).show();
        });
    }
}
class legs_exerciseAdaptor extends ArrayAdapter<String> {
    Context context;
    String rTitle[];
    String rDesription[];
    int rImage[];

    legs_exerciseAdaptor(Context c, String title[], String description[], int images[]) {
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