package com.asim.nolimt.myretrofit;

import android.graphics.Picture;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by NO Limt on 25.07.2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private List<MyFlawers> list;

    MyAdapter(List<MyFlawers> list)
    {
        this.list=list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.name.setText(list.get(position).getName());
        holder.id.setText( Integer.toString(list.get(position).getProductId()));
        holder.catogray.setText(list.get(position).getCategory());
        holder.price.setText(Float.toString( list.get(position).getPrice()));
        Picasso.with(holder.imageView.getContext())
                .load("http://services.hanselandpetal.com/photos/"+list.get(position).getPhoto())
        .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name, price,id,catogray;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.tv_name);
            price= itemView.findViewById(R.id.tv_prise);
            id= itemView.findViewById(R.id.tv_id);
            catogray= itemView.findViewById(R.id.tv_catogray);
            imageView=itemView.findViewById(R.id.imageView);
        }
    }
}

