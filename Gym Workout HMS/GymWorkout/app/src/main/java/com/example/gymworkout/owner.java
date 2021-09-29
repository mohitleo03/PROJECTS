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

public class owner extends AppCompatActivity {
    ListView listView;
    String mTitle[]={"Mohit Malik"};
    String mDescription[]={"APP CREATED BY MOHIT MALIK WITH ASSISTANCE OF MANISH KUMAR SHARMA \n"
    };
    int images[] = {R.drawable.mohit_malik};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        listView=findViewById(R.id.info_listView);
        ownerAdaptor adaptor=new ownerAdaptor(this,mTitle,mDescription,images);
        listView.setAdapter(adaptor);
        listView.setOnItemClickListener((adapterView, view, position, l) -> {
            if (position == 0)
                Toast.makeText(owner.this, "Mohit Malik", Toast.LENGTH_SHORT).show();
        });
    }
}
class ownerAdaptor extends ArrayAdapter<String> {
    Context context;
    String rTitle[];
    String rDesription[];
    int rImage[];

    ownerAdaptor(Context c, String title[], String description[], int images[]) {
        super(c, R.layout.info_cells, R.id.info_heading, title);
        this.context = c;
        this.rTitle = title;
        this.rImage = images;
        this.rDesription = description;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.info_cells, parent, false);
        ImageView images = row.findViewById(R.id.info_image);
        TextView myTitle = row.findViewById(R.id.info_heading);
        TextView myDescription = row.findViewById(R.id.info_desc);
        images.setImageResource(rImage[position]);
        myTitle.setText(rTitle[position]);
        myDescription.setText(rDesription[position]);
        return row;
    }
}



