package com.example.class5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
//class 5 no. | class 6 $no. | class 7 #no.
public class MainActivity extends AppCompatActivity {

    //1. array list of <[class name]> then what you want to name it
    ArrayList<Phones> PhoneList=new ArrayList<>();

    //#1. read and get database data from here "URL changes w proj" before adding items
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://phones-a2a53-default-rtdb.firebaseio.com/");
    DatabaseReference dbRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2. identify the phones by ClassName phone# = new ClassName("name of obj", int/double, the image id)
        //#2. products list not needed
        //#.Phones phone1 = new Phones("Samsung Galaxy S22 Ultra" , 464 , R.drawable.s22ultra);
        //#.Phones phone2 = new Phones("Samsung Galaxy Z Fold" , 574 , R.drawable.zflod3);
        //#.Phones phone3 = new Phones("Apple iPhone 13 Pro" , 390 , R.drawable.iphone13promax);
        //#.Phones phone4 = new Phones("Apple iPhone 12 Pro" , 330 , R.drawable.iphone12promax);
        //#.Phones phone5 = new Phones("HUAWEI P50 Pocket" , 429.90 , R.drawable.p50pocket);
        //#.Phones phone6 = new Phones("Samsung Galaxy S21" , 250 , R.drawable.s21);

        //3. name of list.add the identified phones
        //#3. list not needed
        //#.PhoneList.add(phone1);
        //#.PhoneList.add(phone2);
        //#.PhoneList.add(phone3);
        //#.PhoneList.add(phone4);
        //#.PhoneList.add(phone5);
        //#.PhoneList.add(phone6);

        //4. identified the recycler
        //set the recycler size as fixed
        //RecyclerView.LayoutManager [name of manager] = new LinearLayoutManager([this context]);
        //rope the recycler with the manager above
        RecyclerView recycler = findViewById(R.id.rv);
        recycler.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);

        //5. after ItemAdapter class, [class name] [name it]= ---(listname, context of this class this)
        //recycler.setAdapter(name it^);
        ItemAdapter adapter = new ItemAdapter(PhoneList, this);
        recycler.setAdapter(adapter);

        //#4. to read and show obj in list from firebase after adding the objects:
        //final Query [nameIT] = [dbRef from above].child("Phone name of 1ST key")
        final Query myphone = dbRef.child("Phone");

        //OnClick listener for notif of new data: [nameIT^].addValueEventListener(new ValueEventListener()
        myphone.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //#5. add for each: for(DataSnapshot [named a]: snapshot.getChildren()){
                for(DataSnapshot a: snapshot.getChildren()){
                    //#6. get each object to save in list
                    Phones phone = a.getValue(Phones.class);
                    //#7. add the objects to list where it goes thru all and saves each in orig Arraylist above
                    PhoneList.add(phone);
                    //#8. notify it of changes: [adapterName from 5.].notifyDataSetChanged()
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

//XML FILES:
//internet: add the line code: <uses-permission android:name="android.permission.INTERNET"/>
//in AndroidManifest.xml