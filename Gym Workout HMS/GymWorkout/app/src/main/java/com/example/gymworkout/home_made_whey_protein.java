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

public class home_made_whey_protein extends AppCompatActivity {
    ListView listView;
    String mTitle[] = {"Badam - 100gm", "Akhrot - 100 gm", "Milk Powder - 2 tea spoon (50 gm)", "Oats - 50 gm" ,"Chea Seeds - 2 Tea Spoon"
            ,"Peanuts(roasted) - 100 gm","Pumkin Seed - 100 gm", "2 ilaichi", "Flax Seeds - 1 Tea Spoon", "Ashwagandha - 1 Tea Spoon",
            "Mix all the above items in mixer this will be enough for one week depending on your usage"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_made_whey_protein);
        listView = findViewById(R.id.home_made_whey_protein_listView);
        protein_Adaptor adaptor = new protein_Adaptor(this, mTitle);
        listView.setAdapter(adaptor);

    }
}
class protein_Adaptor extends ArrayAdapter<String> {
    Context context;
    String rTitle[];
    int rImage[];
    protein_Adaptor (Context c,String title[]){
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
