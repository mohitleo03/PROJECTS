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

public class WarmUp extends AppCompatActivity {
    ListView listView;
    String mTitle[]={"Running","Cross Trainer","Jumping Jacks","Squats","PushUps"};
    String mDescription[]={"A smart running warmup gives your muscles, bones, and joints a chance to loosen up \n \n" +
            "Time Duration = 5-10 min","Boost your stamina and cardio capacity,Put less stress on your joints\n\n" +
            "Time Duration = 2-5 min","offer cardiovascular benefits,balances out your heart rate,increases blood circulation," +
            "controls & maintains blood pressure\n\n" +
            "Time Duration = 1-2 min","Strengthens your core,Crushes calories,Strengthens the muscles of your lower body.\n\n" +
            "Repetitions = 15-25","Traditional PushUps are beneficial for building upper body strength. They work the triceps," +
            " pectoral muscles, and shoulders.if its too easy for you try burpees\n\n" +
            "Repetitions = 15-30"};
    int images[] = {R.drawable.running,R.drawable.cross_trainer,R.drawable.jumping_jacks,R.drawable.squats,R.drawable.pushups};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warm_up);
        listView=findViewById(R.id.WarmUp_listView);
        WarmUpAdaptor adaptor=new WarmUpAdaptor(this,mTitle,mDescription,images);
        listView.setAdapter(adaptor);
        listView.setOnItemClickListener((adapterView, view, position, l) -> {
            if (position == 0)
                Toast.makeText(WarmUp.this, "Running", Toast.LENGTH_SHORT).show();
            if (position == 1)
                Toast.makeText(WarmUp.this, "Cross Trainer", Toast.LENGTH_SHORT).show();
            if (position == 2)
                Toast.makeText(WarmUp.this, "Jumping Jacks", Toast.LENGTH_SHORT).show();
            if (position == 3)
                Toast.makeText(WarmUp.this, "Squats", Toast.LENGTH_SHORT).show();
            if (position == 4)
                Toast.makeText(WarmUp.this, "PushUps", Toast.LENGTH_SHORT).show();
        });
    }
}
class WarmUpAdaptor extends ArrayAdapter<String> {
    Context context;
    String rTitle[];
    String rDesription[];
    int rImage[];

    WarmUpAdaptor(Context c, String title[], String description[], int images[]) {
        super(c, R.layout.warmup_cells, R.id.warmup_heading, title);
        this.context = c;
        this.rTitle = title;
        this.rImage = images;
        this.rDesription = description;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.warmup_cells, parent, false);
        ImageView images = row.findViewById(R.id.warmup_image);
        TextView myTitle = row.findViewById(R.id.warmup_heading);
        TextView myDescription = row.findViewById(R.id.warmup_desc);
        images.setImageResource(rImage[position]);
        myTitle.setText(rTitle[position]);
        myDescription.setText(rDesription[position]);
        return row;
    }
}



