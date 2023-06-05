package com.sakuno.unisync;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Todolist extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Todo> list;
    DatabaseReference databaseReference;
    MyAdapter adapter;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Todolist.this,MainActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);

        recyclerView = findViewById(R.id.recycleview);
        databaseReference = FirebaseDatabase.getInstance("https://unisync-e609d-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("todos");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this,list);
        recyclerView.setAdapter(adapter);

        databaseReference.orderByChild("date").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Todo todo = dataSnapshot.getValue(Todo.class);
                    list.add(todo);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}