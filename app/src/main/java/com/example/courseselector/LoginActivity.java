package com.example.courseselector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText name,userName,password;
    Button signIn,clear;

    ArrayList<Student> students = new ArrayList<Student>();
    public static String sName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        data();
        name = findViewById(R.id.name);
        userName = findViewById(R.id.uName);
        password = findViewById(R.id.pass);
        signIn = findViewById(R.id.login);
        clear = findViewById(R.id.cls);

        signIn.setOnClickListener(this);
        clear.setOnClickListener(this);

    }

    public void data(){
        students.add(new Student("Student","student1","123456"));
        students.add(new Student("Almas","At101","12345"));
        students.add(new Student("Jack","Jk102","qwerty"));
        students.add(new Student("Marcus","Ms103","001100"));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login){
            String check = userChecker(name.getText().toString(),userName.getText().toString(),password.getText().toString());
            if (check.isEmpty()){
                Toast.makeText(getApplicationContext(),"Invalid Username or password",Toast.LENGTH_LONG).show();
            }
            else{
                sName = check;
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
        }
        if(v.getId() == R.id.cls){
            name.setText("");
            userName.setText("");
            password.setText("");
        }
    }

    public String userChecker(String n, String uN, String pass){
        for(int i=0;i<students.size();i++){
            if(n.equals(students.get(i).getName()))
                if(uN.equals(students.get(i).getUsername()))
                    if(pass.equals(students.get(i).getPassword()))
                        return students.get(i).getName();
        }
        return "";
    }
}