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

public class Tips extends AppCompatActivity {
    ListView listView;
    String mTitle[] = {"At least 30 minutes of moderate physical activity every day",
    "Eat the Right Foods and Portion Each Meal",
    "Keep Track of Calories and Food Intake Per Day",
    "It's important to get enough sleep. Sleep helps keep your mind and body healthy.",
    "Do a Warmup and Cool-Down for better results in workout",
    "body will experience a great training effect if you gradually increase your time,intensity,reps",
    "Use Equipment Wisely Don't use excess of heavy weights ",
    "Train With a Partner this will help you to stay motivated and feels competitive",
    "Training too hard or fast is a common cause of sports-related injuries.",
    "Consult with your gym instructor, coach for instruction on how to exercise safely.",
    "Potentially harmful exercises include bouncing while stretching,toe-touches,squats,etc",
    "Wear appropriate protective gear and make sure your sporting equipment is well maintained.",
    "Stop exercise immediately if you are injured until you get fit."};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        listView = findViewById(R.id.tips_listView);
        tips_Adaptor adaptor = new tips_Adaptor(this, mTitle);
        listView.setAdapter(adaptor);

    }
}
class tips_Adaptor extends ArrayAdapter<String> {
    Context context;
    String rTitle[];
    int rImage[];
    tips_Adaptor (Context c,String title[]){
        super(c,R.layout.tips_cells,R.id.tips_heading,title);
        this.context=c;
        this.rTitle=title;

    }
    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater=(LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View tips_cells=layoutInflater.inflate(R.layout.tips_cells,parent,false);

        TextView myTitle=tips_cells.findViewById(R.id.tips_heading);


        myTitle.setText(rTitle[position]);

        return tips_cells;
    }
}
