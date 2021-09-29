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

public class weight_gain extends AppCompatActivity {
    ListView listView;
    String mTitle[] = {"6:30 A.M","8:00 A.M.","12:00 P.M.","2:00 P.M.","2:10 P.M.","4:00 P.M.","5:30 P.M.","8:50 P.M.","9:00 P.M."};
    String mDescription[] = {"Peanut Butter on Wholegrain Toast","Curd (1.5 katori) Mixed Vegetable Stuffed Roti (2 pieces)"
            ,"Fruit Juice or Shakes","Mixed Vegetable Salad (1 katori)","Dal & Rice with Roti (2 roti/chapati) & Sabzi",
            "Boiled Eggs(2-3)","Whole Wheat Bread with Juice",
            "Mixed Salad (1 katori)","Mixsabzi Roti with Chicken"};
    int images[] = {R.drawable.peanut_butter_on_wholegrain_toast,R.drawable.curd_stuffed_roti, R.drawable.fruit_juice_and_shake, R.drawable.mixed_vegetable_salad,
            R.drawable.dal_rice_with_sabzi_roti,R.drawable.boiled_eggs,R.drawable.whole_wheat_bread_with_juice,R.drawable.mixed_salad,R.drawable.mixsabji_roti_with_chicken};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_gain);
        listView = findViewById(R.id.weight_gain_listView);
        Weight_gain_Adaptor adaptor = new Weight_gain_Adaptor(this, mTitle, mDescription, images);
        listView.setAdapter(adaptor);
        listView.setOnItemClickListener((adapterView, view, position, l) -> {
            if (position == 0)
                Toast.makeText(weight_gain.this, "Peanut Butter on Wholegrain Toast", Toast.LENGTH_SHORT).show();
            if (position == 1)
                Toast.makeText(weight_gain.this, "Curd (1.5 katori) Mixed Vegetable Stuffed Roti (2 pieces)", Toast.LENGTH_SHORT).show();
            if (position == 2)
                Toast.makeText(weight_gain.this, "Fruit Juice or Shakes", Toast.LENGTH_SHORT).show();
            if (position == 3)
                Toast.makeText(weight_gain.this, "Mixed Vegetable Salad (1 katori)", Toast.LENGTH_SHORT).show();
            if (position == 4)
                Toast.makeText(weight_gain.this, "Dal & Rice with Roti (1 roti/chapati) & Mixsabzi", Toast.LENGTH_SHORT).show();
            if (position == 5)
                Toast.makeText(weight_gain.this, "Boiled Eggs(2-3)", Toast.LENGTH_SHORT).show();
            if (position == 6)
                Toast.makeText(weight_gain.this, "Whole Wheat Bread with Juice", Toast.LENGTH_SHORT).show();
            if (position == 7)
                Toast.makeText(weight_gain.this, "Mixed Salad (1 katori)", Toast.LENGTH_SHORT).show();
            if (position == 8)
                Toast.makeText(weight_gain.this, "Mixsabzi Roti with Chicken", Toast.LENGTH_SHORT).show();
        });
    }
}
class Weight_gain_Adaptor extends ArrayAdapter<String> {
    Context context;
    String rTitle[];
    String rDesription[];
    int rImage[];
    Weight_gain_Adaptor (Context c,String title[],String description[],int images[]){
        super(c,R.layout.diet_cells,R.id.diet_heading,title);
        this.context=c;
        this.rTitle=title;
        this.rImage=images;
        this.rDesription=description;
    }
    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater=(LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View weight_gain_cells=layoutInflater.inflate(R.layout.diet_cells,parent,false);
        ImageView images=weight_gain_cells.findViewById(R.id.diet_image);
        TextView myTitle=weight_gain_cells.findViewById(R.id.diet_heading);
        TextView myDescription=weight_gain_cells.findViewById(R.id.diet_desc);
        images.setImageResource(rImage[position]);
        myTitle.setText(rTitle[position]);
        myDescription.setText(rDesription[position]);
        return weight_gain_cells;
    }
}
