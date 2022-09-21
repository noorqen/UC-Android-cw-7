package com.example.class5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //$1. identify and name the interactable components from the details xml file
        TextView nametext = findViewById(R.id.textViewnamed);
        TextView pricetext = findViewById(R.id.textViewpriced);
        ImageView img = findViewById(R.id.imageViewdetails);

        //$2. call the Bundle [bundleName] = getIntent().getExtras()
        Bundle bundle = getIntent().getExtras();
        //$3. we are accepting object from bundle to save: className name it = (className) bundle.getSerializable("key given in ItemAdapter")
        //save from bundle:  itemsClass [nameIt] = (itemsClass) bundle.getSerializable("same name from prev Class(key name)")
        Phones sendphone = (Phones) bundle.getSerializable("phone");

        //$4. display the output from saved bundle input to id^ by iddetails.setText([nameIt].get[from Class]())
        nametext.setText(sendphone.getPhoneName());
        pricetext.setText(sendphone.getPhonePrice() + " KD");
        //#--img.setImageResource(sendphone.getPhoneImg());
        //#1. add: Picasso.with([context of this page]).load(...).into(...); & fill from above line
        Picasso.with(this).load(sendphone.getPhoneImg()).into(img);
    }
}