package com.example.android.mycardview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/*
  Making calls to findViewById() can be slow in practice,
  and if your adapter has to call it for each View in your row for every single row
   then you can often run into performance issues. What the ViewHolder class does is cache the call to findViewById().
   Once your ListView has reached the max amount of rows it can display on a screen,
    Android is smart enough to begin recycling those row Views
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    ArrayList<Country> arrayList = new ArrayList<>();

    //A ViewHolder describes an item view and metadata about its place within the RecyclerView.
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView c_flag;
        TextView c_name;


        public MyViewHolder(View itemView) {
            super(itemView);
            c_flag =(ImageView) itemView.findViewById(R.id.flag);
            c_name = (TextView) itemView.findViewById(R.id.name);
        }
    }

    RecyclerAdapter(ArrayList<Country> arrayList){
        this.arrayList = arrayList;
    }

 /*  onBindViewHolder(VH holder, int position)
    Called by RecyclerView to display the data at the specified position.

    onCreateViewHolder(ViewGroup parent, int viewType)
    Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.


    getItemCount()
    Returns the total number of items in the data set held by the adapter.

*/
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate a view for row
        //as same as:
        // LayoutInflater inflater = LayoutInflater.from(getContext()); (obtains the layoutinflater from given context)
        // View view= inflater.inflate(R.layout.row_layout, parent, false) (Inflate a new view hierarchy from the specified xml resource)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_lyout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    //set resources for textview and imageview.

    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.c_flag.setImageResource(arrayList.get(position).getFlag_id());
        holder.c_name.setText(arrayList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public void setFilter(ArrayList<Country> newList)
    {
        arrayList = new ArrayList<>();
        arrayList.addAll(newList);
        //Notify any registered observers that the data set has changed. or refresh the adapter.
        notifyDataSetChanged();
    }
}
