package com.sakuno.unisync;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    EditText editSubject,editTime,editTodo;
    CalendarView calendarView;
    Button addbtn,showbtn;
    DatabaseReference databaseTodos;
    TextView textView;
    private String selectedDate;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addbtn = findViewById(R.id.addbtn);
        showbtn = findViewById(R.id.viewbtn);

        calendarView = findViewById(R.id.calendarView1);
        imageView = findViewById(R.id.imageView);
        editSubject = findViewById(R.id.editSub);
        editTime = findViewById(R.id.editDate);
        editTodo = findViewById(R.id.editTodo);
        textView = findViewById(R.id.dayname);

        databaseTodos = FirebaseDatabase.getInstance("https://unisync-e609d-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        showbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Todolist.class));
                finish();
            }
        });
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = findDay(month,dayOfMonth,year);
                updateView(selectedDate);
            }
        });

    }

    public static String findDay(int month, int day, int year) {
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");

        Date date = new GregorianCalendar(year, month , day).getTime();
        String dayText = simpleDateformat.format(date);

        return dayText.toUpperCase();
    }

    private void insertData() {
        String sub = editSubject.getText().toString();
        editSubject.getText().clear();
        String date = editTime.getText().toString();
        editTime.getText().clear();
        String todo = editTodo.getText().toString();
        editTodo.getText().clear();
        String id =  databaseTodos.push().getKey();
        if(sub.isEmpty() && date.isEmpty() && todo.isEmpty()){
            Toast.makeText(this, "Insert Data", Toast.LENGTH_SHORT).show();
        }else{
            Todo todo1 = new Todo(sub,date,todo);
            databaseTodos.child("todos").child(id).setValue(todo1).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "ADDED TODO", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    public void updateView(String txt){
        textView.setText(txt);
        System.out.println(txt);
        if(txt.equals("SUNDAY")){
            imageView.setImageResource(R.drawable.sunday);
            textView.setText(txt);
        }else if(txt.equals("MONDAY")){
            imageView.setImageResource(R.drawable.meme_project);
            textView.setText(txt);
        }
        else if(txt.equals("TUESDAY")){
            imageView.setImageResource(R.drawable.tuesday);
            textView.setText(txt);
        }
        else if(txt.equals("WEDNESDAY")){
            imageView.setImageResource(R.drawable.wednesday);
            textView.setText(txt);
        }
        else if(txt.equals("THURSDAY")){
            imageView.setImageResource(R.drawable.meme_project);
            textView.setText(txt);
        }
        else if(txt.equals("FRIDAY")){
            imageView.setImageResource(R.drawable.meme_project);
            textView.setText(txt);
        }
        else if(txt.equals("SATURDAY")){
            imageView.setImageResource(R.drawable.saturday);
            textView.setText(txt);
        }
    }
}