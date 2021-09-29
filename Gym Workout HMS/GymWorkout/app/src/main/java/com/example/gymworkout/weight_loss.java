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

public class weight_loss extends AppCompatActivity {
    ListView listView;
    String mTitle[] = {"6:30 A.M","8:00 A.M.","12:00 P.M.","2:00 P.M.","2:10 P.M.","4:00 P.M.","5:30 P.M.","8:50 P.M.","9:00 P.M."};
    String mDescription[] = {"Cucumber Detox Water(1 glass)","Curd (1.5 katori) Mixed Vegetable Stuffed Roti (2 pieces)"
    ,"Skimmed Milk Paneer (100 grams)","Mixed Vegetable Salad (1 katori)","Dal & Rice with Roti (1 roti/chapati) & Sabzi",
    "Cut Fruits (apple,orange,banana,papaya) Buttermilk (1 glass)","Coffee with Milk and Less Sugar (0.5 tea cup)",
    "Mixed Salad (1 katori)","Dal & Rice with Roti (1 roti/chapati) & Mixsabzi"};
    int images[] = {R.drawable.cucumber_detox_water,R.drawable.curd_stuffed_roti, R.drawable.skimmed_milk_paneer, R.drawable.mixed_vegetable_salad,
            R.drawable.dal_rice_with_sabzi_roti,R.drawable.coffee_with_milk,R.drawable.cut_fruits,R.drawable.mixed_salad,R.drawable.dal_rice_sabzi};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_loss);
        listView = findViewById(R.id.weight_loss_listView);
        Weight_Loss_Adaptor adaptor = new Weight_Loss_Adaptor(this, mTitle, mDescription, images);
        listView.setAdapter(adaptor);
        listView.setOnItemClickListener((adapterView, view, position, l) -> {
            if (position == 0)
                Toast.makeText(weight_loss.this, "Cucumber Detox Water(1 glass)", Toast.LENGTH_SHORT).show();
            if (position == 1)
                Toast.makeText(weight_loss.this, "Curd (1.5 katori) Mixed Vegetable Stuffed Roti (2 pieces)", Toast.LENGTH_SHORT).show();
            if (position == 2)
                Toast.makeText(weight_loss.this, "Skimmed Milk Paneer (100 grams)", Toast.LENGTH_SHORT).show();
            if (position == 3)
                Toast.makeText(weight_loss.this, "Mixed Vegetable Salad (1 katori)", Toast.LENGTH_SHORT).show();
            if (position == 4)
                Toast.makeText(weight_loss.this, "Dal & Rice with Roti (1 roti/chapati) & Mixsabzi", Toast.LENGTH_SHORT).show();
            if (position == 5)
                Toast.makeText(weight_loss.this, "Cut Fruits (apple,orange,banana,papaya) Buttermilk (1 glass)", Toast.LENGTH_SHORT).show();
            if (position == 6)
                Toast.makeText(weight_loss.this, "Coffee with Milk and Less Sugar (0.5 tea cup)", Toast.LENGTH_SHORT).show();
            if (position == 7)
                Toast.makeText(weight_loss.this, "Mixed Vegetable Salad (1 katori)", Toast.LENGTH_SHORT).show();
            if (position == 8)
                Toast.makeText(weight_loss.this, "Dal & Rice with Roti (1 roti/chapati) & Mixsabzi", Toast.LENGTH_SHORT).show();
        });
    }
}
class Weight_Loss_Adaptor extends ArrayAdapter<String> {
    Context context;
    String rTitle[];
    String rDesription[];
    int rImage[];
    Weight_Loss_Adaptor (Context c,String title[],String description[],int images[]){
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
        View weight_loss_cells=layoutInflater.inflate(R.layout.diet_cells,parent,false);
        ImageView images=weight_loss_cells.findViewById(R.id.diet_image);
        TextView myTitle=weight_loss_cells.findViewById(R.id.diet_heading);
        TextView myDescription=weight_loss_cells.findViewById(R.id.diet_desc);
        images.setImageResource(rImage[position]);
        myTitle.setText(rTitle[position]);
        myDescription.setText(rDesription[position]);
        return weight_loss_cells;
    }
}
