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

public class muscle_booster extends AppCompatActivity {
    ListView listView;
    String mTitle[] = {"6:30 A.M","9:00 A.M.","2:00 P.M.","4:00 P.M.","9:00 P.M.","10: P.M."};
    String mDescription[] = {"Scrambled Eggs with Veggies","Protein shake with 30g whey protein powder and 200ml semi-skimmed milk 1 small banana"
            ,"Chicken with Salad & Veggies","Dry-fruits(almonds,cashew nuts)","Chicken with MixSabji & Roti",
            "30g whey protein powder mixed with 150g low-fat Greek yogurt and 100g frozen berries"};
    int images[] = {R.drawable.scrambled_eggs_with_veggies,R.drawable.protein_shake, R.drawable.chicken_salad_veggies, R.drawable.dryfruits,
            R.drawable.chicken_vegetable_roti,R.drawable.whey_protein_yogurt_frozen_berries};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_booster);
        listView = findViewById(R.id.muscle_booster_listView);
        muscle_boosters_Adaptor adaptor = new muscle_boosters_Adaptor(this, mTitle, mDescription, images);
        listView.setAdapter(adaptor);
        listView.setOnItemClickListener((adapterView, view, position, l) -> {
            if (position == 0)
                Toast.makeText(muscle_booster.this, "Scrambled Eggs with Veggies", Toast.LENGTH_SHORT).show();
            if (position == 1)
                Toast.makeText(muscle_booster.this, "Protein shake with 30g whey protein powder and 200ml semi-skimmed milk 1 small banana", Toast.LENGTH_SHORT).show();
            if (position == 2)
                Toast.makeText(muscle_booster.this, "Chicken with Salad & Veggies", Toast.LENGTH_SHORT).show();
            if (position == 3)
                Toast.makeText(muscle_booster.this, "Dry-fruits(beef jerky,almonds,cashew nuts)", Toast.LENGTH_SHORT).show();
            if (position == 4)
                Toast.makeText(muscle_booster.this, "Chicken with MixSabji & Roti", Toast.LENGTH_SHORT).show();
            if (position == 5)
                Toast.makeText(muscle_booster.this, "30g whey protein powder mixed with 150g low-fat Greek yogurt and 100g frozen berries", Toast.LENGTH_SHORT).show();
        });
    }
}
class muscle_boosters_Adaptor extends ArrayAdapter<String> {
    Context context;
    String rTitle[];
    String rDesription[];
    int rImage[];
    muscle_boosters_Adaptor (Context c,String title[],String description[],int images[]){
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
        View muscle_booster_cells=layoutInflater.inflate(R.layout.diet_cells,parent,false);
        ImageView images=muscle_booster_cells.findViewById(R.id.diet_image);
        TextView myTitle=muscle_booster_cells.findViewById(R.id.diet_heading);
        TextView myDescription=muscle_booster_cells.findViewById(R.id.diet_desc);
        images.setImageResource(rImage[position]);
        myTitle.setText(rTitle[position]);
        myDescription.setText(rDesription[position]);
        return muscle_booster_cells;
    }
}
