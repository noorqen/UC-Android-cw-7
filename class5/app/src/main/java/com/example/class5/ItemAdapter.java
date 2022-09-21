package com.example.class5;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//1. to import stuff from other classes [extends (RecyclerView).Adapter]
//+ [implement methods] from error bulb
public class ItemAdapter extends RecyclerView.Adapter{

    //2. add two attributes: re-identify the array list & add context
    ArrayList<Phones> PhoneList=new ArrayList<>();
    Context context;

    //3. generate a Constructor with all the attributes
    public ItemAdapter(ArrayList<Phones> phoneList, Context context) {
        PhoneList = phoneList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //8. to use an external xml, id View = LayoutInflater.from --- layout.[row_item], parent, false);
        //id a view holder as the above name and return it
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    //$4. [Suppress: Add @SuppressLint("RecyclerView") annotation] from error bulb
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //9. add the setText and setImg
        //((inner class name)holder form above line).what you named them.setText ---
        //(listname.get(position form above line).get[PhoneName()]) | (+ "") for int and double
        ((ViewHolder)holder).textname.setText(PhoneList.get(position).getPhoneName());
        ((ViewHolder)holder).textprice.setText(PhoneList.get(position).getPhonePrice() + " KD");
        //#--((ViewHolder)holder).img.setImageResource(PhoneList.get(position).getPhoneImg());
        //#1?. add: Picasso.with([context of this page]).load(...).into(...); & fill from above line
        Picasso.with(context).load(PhoneList.get(position).getPhoneImg()).into(((ViewHolder)holder).img);
        //$1. add setOnClickListener for item details to show on another page when label pressed
        ((ViewHolder)holder).v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //$2. make a new intent with this page's context defined above to detailsActivity
                //Intent [intentname] = new Intent(context defined above, DetailsActivity.class);
                //create Intent [intentname] = new Intent(thisClass.this, theClasstogoto.class)
                Intent intent = new Intent(context, DetailsActivity.class);
                //$3. what to transfer: [intentname].putExtra(key name, listname.get(position))
                intent.putExtra("phone",PhoneList.get(position));
                //$5. context of this class.startActivity(intentname)
                context.startActivity(intent);
            }
        });

        //$8. add setOnClickListener for delete
        ((ViewHolder)holder).delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //$16. save the deleted item temporarily: ClassofItems rmvItm = listname.get(position);
                Phones removedphone = PhoneList.get(position);
                //$20. save save the deleted item position temporarily
                int x = position;

                //$9. to set the confirmation delete message:
                //AlertDialog.Builder [alertname] = new AlertDialog.Builder(context of this page)
                //.setTitle("TitleName")
                //.setMessage("delete msg")
                //.setPositiveButton("Pos. Btn Name", new DialogInterface.OnClickListener() {
                AlertDialog.Builder alert = new AlertDialog.Builder(context)
                        .setTitle("Attention")
                        .setMessage("Are you sure you want to delete?")
                        .setPositiveButton("delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //$10. delete the object position: listname.remove(position)
                                PhoneList.remove(position);
                                //$11. notify the data of changes made
                                notifyDataSetChanged();

                                //$15. use Snackbar function to undo deletion action
                                //Snackbar.make(context of this page, view of OnClick, "msg shown", milli sec)
                                // .setAction("undo", new View.OnClickListener() {
                                Snackbar.make(context, view, "1 item deleted", 3000)
                                        .setAction("undo", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                //$17. add the removed item back to list in its position:
                                                //listname.add(position, rmvItm)
                                                PhoneList.add(position, removedphone);
                                                //$18. notify the data of changes made
                                                notifyDataSetChanged();

                                            }
                                            //$19. at the end of Snackbar code: .show();
                                        }).show();
                            }
                        })
                        //$12. setNegativeButton("Neg. Btn Name", new DialogInterface.OnClickListener() {
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //$13. dismiss the delete msg
                                dialogInterface.dismiss();
                            }
                        });
                //$14. show the alert msg: [alertname].show()
                alert.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        //10. return the list size
        return PhoneList.size();
    }
    //4. new inner class named ViewHolder + [create constructor matching super] from error bulb
    public class ViewHolder extends RecyclerView.ViewHolder{

        //5. identify the components
        //can either do this or directly write it before the id statement attachments
        //$6. identify the delete component
        ImageView img, delete;
        TextView textname, textprice;
        //6. cannot directly id the comps, so use view
        View v;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //7. name it=above view.find--(--id of comp from row_item)
            v=itemView;
            img = v.findViewById(R.id.imageView);
            textname = v.findViewById(R.id.textViewname);
            textprice = v.findViewById(R.id.textViewprice);
            //$7. name it=above view.find--(--id of comp from row_item) for delete
            delete = v.findViewById(R.id.imagedelete);
        }
    }
}
