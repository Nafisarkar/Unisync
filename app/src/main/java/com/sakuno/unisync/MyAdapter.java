package com.sakuno.unisync;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<Todo> list;

    public MyAdapter(Context context, ArrayList<Todo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.todoentry,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Todo todo = list.get(position);
        holder.sub.setText(todo.getSubject());
        holder.date.setText(todo.getDate());
        holder.todo.setText(todo.getTodo());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView sub;
        TextView date;
        TextView todo;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sub = itemView.findViewById(R.id.txtsubject);
            date = itemView.findViewById(R.id.txtdate);
            todo = itemView.findViewById(R.id.txttodo);
        }
    }
}
