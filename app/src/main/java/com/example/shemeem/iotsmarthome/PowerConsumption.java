package com.example.shemeem.iotsmarthome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class PowerConsumption extends Activity {

    private FirebaseAuth mAuth;

    private DatabaseReference databaseReference;
    private DatabaseReference userReference;
    private DatabaseReference buttonReference;
    private DatabaseReference timeReference;
    private DatabaseReference powerRating;

    private usageData udlocal;
    private powerRating prlocal;

    private TextView tv_d1;
    private TextView tv_d2;
    private TextView tv_d3;
    private TextView tv_d4;
    private TextView tv_d5;
    private TextView tv_d6;

    private TextView tv_totalUsage;

    private Double total = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_consumption);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LogInActivity.class));
        }
        FirebaseUser user = mAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        userReference = databaseReference.child(mAuth.getCurrentUser().getUid());
        buttonReference = userReference.child("buttonState");
        timeReference = userReference.child("usageData");
        powerRating = userReference.child("powerRating");

        timeReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                saveUsageData(dataSnapshot);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        powerRating.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                savePowerRating(dataSnapshot);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        tv_d1 = (TextView) findViewById(R.id.tv_d1);
        tv_d2 = (TextView) findViewById(R.id.tv_d2);
        tv_d3 = (TextView) findViewById(R.id.tv_d3);
        tv_d4 = (TextView) findViewById(R.id.tv_d4);
        tv_d5 = (TextView) findViewById(R.id.tv_d5);
        tv_d6 = (TextView) findViewById(R.id.tv_d6);

        tv_totalUsage = (TextView) findViewById(R.id.tv_totalUsage);

        //displayUsage();
    }

    private void displayUsage() {
        total = (udlocal.getB1u() * prlocal.getBt1()) + (udlocal.getB2u() * prlocal.getBt2()) + (udlocal.getB3u() * prlocal.getBt3()) + (udlocal.getB4u() * prlocal.getBt4()) + (udlocal.getB5u() * prlocal.getBt5()) + (udlocal.getB6u() * prlocal.getBt6());
        tv_totalUsage.setText(Double.toString(total));

        tv_d1.setText(Double.toString (udlocal.getB1u() * prlocal.getBt1()));
        tv_d2.setText(Double.toString (udlocal.getB2u() * prlocal.getBt2()));
        tv_d3.setText(Double.toString (udlocal.getB3u() * prlocal.getBt3()));
        tv_d4.setText(Double.toString (udlocal.getB4u() * prlocal.getBt4()));
        tv_d5.setText(Double.toString (udlocal.getB5u() * prlocal.getBt5()));
        tv_d6.setText(Double.toString (udlocal.getB6u() * prlocal.getBt6()));
    }

    private void saveUsageData(DataSnapshot dataSnapshot) {
        usageData ud = new usageData();
        try {
            ud = dataSnapshot.getValue(usageData.class);
            Log.e("saveUsageData", "Read Success" + ud.getB1u());

        } catch (Exception e) {
            Log.e("saveUsageData", "Read Failed");
        }
        udlocal = ud;
    }

    private void savePowerRating(DataSnapshot dataSnapshot) {
        powerRating pr = new powerRating();
        try {
            pr = dataSnapshot.getValue(powerRating.class);
            Log.e("savePowerRating", "Read Success");

        } catch (Exception e) {
            Log.e("savePowerRating", "Read Failed");
        }
        prlocal = pr;

        displayUsage();
    }
}
