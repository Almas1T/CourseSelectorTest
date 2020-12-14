package com.example.courseselector;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    TextView msg,courseFee,courseHrs,totalFees,totalHrs;
    RadioButton graduated,unGraduated;
    Spinner sp;
    Button add;
    CheckBox medical, accommodation;

    ArrayList<Course> courses = new ArrayList<Course>();
    ArrayList<String> cName = new ArrayList<String>();

    double tfees = 0;
    int thrs = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cData();
        msg = findViewById(R.id.intro);
        courseFee = findViewById(R.id.cFee);
        courseHrs = findViewById(R.id.cHrs);
        totalFees = findViewById(R.id.tFees);
        totalHrs = findViewById(R.id.tHrs);
        graduated = findViewById(R.id.grad);
        unGraduated = findViewById(R.id.unGrad);
        sp = findViewById(R.id.spinner);
        add = findViewById(R.id.button);
        medical = findViewById(R.id.mi);
        accommodation = findViewById(R.id.Ac);

        msg.setText("Welcome " + LoginActivity.sName);

        ArrayAdapter c = new ArrayAdapter(this, android.R.layout.simple_spinner_item,cName);
        sp.setAdapter(c);
        sp.setOnItemSelectedListener(this);

        add.setOnClickListener(this);

        medical.setOnCheckedChangeListener(this);
        accommodation.setOnCheckedChangeListener(this);

        graduated.setOnClickListener(this);
        unGraduated.setOnClickListener(this);

    }

    public void cData(){
        courses.add(new Course("Java",1300,6));
        courses.add(new Course("Swift",1500,5));
        courses.add(new Course("iOS",1350,5));
        courses.add(new Course("Android",1400,7));
        courses.add(new Course("Database",1000,4));

        for(int i=0;i<courses.size();i++)
            cName.add(courses.get(i).getcName());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        courseFee.setText(String.valueOf(courses.get(position).getcFees()));
        courseHrs.setText(String.valueOf(courses.get(position).getcHrs()));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.grad){
            totalFees.setText("0");
            totalHrs.setText("0");
            thrs = 0;
            tfees = 0;
        }
        if (v.getId() == R.id.unGrad){
            totalFees.setText("0");
            totalHrs.setText("0");
            thrs = 0;
            tfees = 0;
        }
        if (v.getId() == R.id.button) {
            if (graduated.isChecked() || unGraduated.isChecked()) {
                thrs += Integer.parseInt(courseHrs.getText().toString());
                if (graduated.isChecked() && thrs <= 21) {
                    tfees += Double.parseDouble(courseFee.getText().toString());
                    totalFees.setText(String.valueOf(tfees));
                    totalHrs.setText(String.valueOf(thrs));
                } else if (unGraduated.isChecked() && thrs <= 19) {
                    tfees += Double.parseDouble(courseFee.getText().toString());
                    totalFees.setText(String.valueOf(tfees));
                    totalHrs.setText(String.valueOf(thrs));
                } else {
                    Toast.makeText(getApplicationContext(), "Maximum Hours Reached! Cannot add any more Course", Toast.LENGTH_LONG).show();
                }
            }
            else{
                Toast.makeText(getApplicationContext(),"Select Either of graduated or ungraduated",Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        double tempfees = Double.parseDouble(totalFees.getText().toString());
        if(buttonView.getId() == R.id.mi){
            if (medical.isChecked())
                tempfees += 700;
            else
                tempfees -= 700;
        }

        if(buttonView.getId() ==R.id.Ac){
            if (accommodation.isChecked())
                tempfees += 1000;
            else
                tempfees -= 1000;
        }

        totalFees.setText(String.valueOf(tempfees));
    }
}