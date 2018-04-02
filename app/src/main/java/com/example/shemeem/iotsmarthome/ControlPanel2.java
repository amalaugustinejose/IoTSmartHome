package com.example.shemeem.iotsmarthome;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ControlPanel2 extends Activity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private TextView textViewUserEmail;
    private Button buttonLogout;

    private DatabaseReference databaseReference;

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button10;

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private TextView tv8;
    private TextView tv9;
    private TextView tv10;

    private Button masterOn;
    private Button masterOff;

    private buttonState bslocal = new buttonState();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_panel2);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LogInActivity.class));
        }
        FirebaseUser user = mAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        try {
            databaseReference.child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    saveData(dataSnapshot);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } catch (Exception e) {
            Log.e("TestLog1234", "Inside Firebase Read");
            Log.e("Read Error", "Database Error");
        }


        textViewUserEmail = (TextView) findViewById(R.id.tv_useremail);
        textViewUserEmail.setText(user.getEmail());

        button1 = (Button) findViewById(R.id.bt1);
        button2 = (Button) findViewById(R.id.bt2);
        button3 = (Button) findViewById(R.id.bt3);
        button4 = (Button) findViewById(R.id.bt4);
        button5 = (Button) findViewById(R.id.bt5);
        button6 = (Button) findViewById(R.id.bt6);
        button7 = (Button) findViewById(R.id.bt7);
        button8 = (Button) findViewById(R.id.bt8);
        button9 = (Button) findViewById(R.id.bt9);
        button10 = (Button) findViewById(R.id.bt10);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
        tv6 = (TextView) findViewById(R.id.tv6);
        tv7 = (TextView) findViewById(R.id.tv7);
        tv8 = (TextView) findViewById(R.id.tv8);
        tv9 = (TextView) findViewById(R.id.tv9);
        tv10 = (TextView) findViewById(R.id.tv10);


        masterOn = (Button) findViewById(R.id.btMasterOn);
        masterOff = (Button) findViewById(R.id.btMasterOff);

        buttonLogout = (Button) findViewById(R.id.bt_logOut);
        buttonLogout.setOnClickListener(this);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button10.setOnClickListener(this);

        masterOn.setOnClickListener(this);
        masterOff.setOnClickListener(this);

        bslocal = new buttonState(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

    }

    private void setButtonStatus (buttonState bs) {
        if (bs.getB1()==1){
            tv1.setText("ON");
            tv1.setBackgroundColor(Color.parseColor("#4CAF50"));
        } else {
            tv1.setText("OFF");
            tv1.setBackgroundColor(Color.parseColor("#F44336"));
        }
        if (bs.getB2()==1){
            tv2.setText("ON");
            tv2.setBackgroundColor(Color.parseColor("#4CAF50"));
        } else {
            tv2.setText("OFF");
            tv2.setBackgroundColor(Color.parseColor("#F44336"));
        }
        if (bs.getB3()==1){
            tv3.setText("ON");
            tv3.setBackgroundColor(Color.parseColor("#4CAF50"));
        } else {
            tv3.setText("OFF");
            tv3.setBackgroundColor(Color.parseColor("#F44336"));
        }
        if (bs.getB4()==1){
            tv4.setText("ON");
            tv4.setBackgroundColor(Color.parseColor("#4CAF50"));
        } else {
            tv4.setText("OFF");
            tv4.setBackgroundColor(Color.parseColor("#F44336"));
        }
        if (bs.getB5()==1){
            tv5.setText("ON");
            tv5.setBackgroundColor(Color.parseColor("#4CAF50"));
        } else {
            tv5.setText("OFF");
            tv5.setBackgroundColor(Color.parseColor("#F44336"));
        }
        if (bs.getB6()==1){
            tv6.setText("ON");
            tv6.setBackgroundColor(Color.parseColor("#4CAF50"));
        } else {
            tv6.setText("OFF");
            tv6.setBackgroundColor(Color.parseColor("#F44336"));
        }
        if (bs.getB7()==1){
            tv7.setText("ON");
            tv7.setBackgroundColor(Color.parseColor("#4CAF50"));
        } else {
            tv7.setText("OFF");
            tv7.setBackgroundColor(Color.parseColor("#F44336"));
        }
        if (bs.getB8()==1){
            tv8.setText("ON");
            tv8.setBackgroundColor(Color.parseColor("#4CAF50"));
        } else {
            tv8.setText("OFF");
            tv8.setBackgroundColor(Color.parseColor("#F44336"));
        }
        if (bs.getB9()==1){
            tv9.setText("ON");
            tv9.setBackgroundColor(Color.parseColor("#4CAF50"));
        } else {
            tv9.setText("OFF");
            tv9.setBackgroundColor(Color.parseColor("#F44336"));
        }
        if (bs.getB10()==1){
            tv10.setText("ON");
            tv10.setBackgroundColor(Color.parseColor("#4CAF50"));
        } else {
            tv10.setText("OFF");
            tv10.setBackgroundColor(Color.parseColor("#F44336"));
        }
    }

    private void saveData(DataSnapshot dataSnapshot) {
        buttonState bs = new buttonState();
        bslocal = new buttonState(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        if (dataSnapshot == null) {
            Log.e("Firebase Read", "Null snapshot");
        }
        try {
            bs = dataSnapshot.getValue(buttonState.class);
            setButtonStatus(bs);
        } catch (Exception e) {
            Log.e("TestLogSaveData", "When snapshot not empty");
        }
        bslocal = bs;
        try {
            databaseReference.child(mAuth.getCurrentUser().getUid()).setValue(bslocal);
        } catch (Exception e) {
            bslocal = new buttonState(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }
    }

    private void saveButtonState(String button) {
        if (bslocal == null) {
            bslocal = new buttonState(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }
        if (button == "b1") {

            if (bslocal.getB1() == 1) {
                bslocal.setB1(0);
            } else {
                bslocal.setB1(1);
            }
            if (tv1.getText().equals("ON")) {
                tv1.setText("OFF");
                tv1.setBackgroundColor(Color.parseColor("#F44336"));
            } else {
                tv1.setText("ON");
                tv1.setBackgroundColor(Color.parseColor("#4CAF50"));
            }
        }
        if (button == "b2") {
            if (bslocal.getB2() == 1) {
                bslocal.setB2(0);
            } else {
                bslocal.setB2(1);
            }
            if (tv2.getText().equals("ON")) {
                tv2.setText("OFF");
                tv2.setBackgroundColor(Color.parseColor("#F44336"));
            } else {
                tv2.setText("ON");
                tv2.setBackgroundColor(Color.parseColor("#4CAF50"));
            }
        }
        if (button == "b3") {
            if (bslocal.getB3() == 1) {
                bslocal.setB3(0);
            } else {
                bslocal.setB3(1);
            }
            if (tv3.getText().equals("ON")) {
                tv3.setText("OFF");
                tv3.setBackgroundColor(Color.parseColor("#F44336"));
            } else {
                tv3.setText("ON");
                tv3.setBackgroundColor(Color.parseColor("#4CAF50"));
            }
        }
        if (button == "b4") {
            if (bslocal.getB4() == 1) {
                bslocal.setB4(0);
            } else {
                bslocal.setB4(1);
            }
            if (tv4.getText().equals("ON")) {
                tv4.setText("OFF");
                tv4.setBackgroundColor(Color.parseColor("#F44336"));
            } else {
                tv4.setText("ON");
                tv4.setBackgroundColor(Color.parseColor("#4CAF50"));
            }
        }
        if (button == "b5") {
            if (bslocal.getB5() == 1) {
                bslocal.setB5(0);
            } else {
                bslocal.setB5(1);
            }
            if (tv5.getText().equals("ON")) {
                tv5.setText("OFF");
                tv5.setBackgroundColor(Color.parseColor("#F44336"));
            } else {
                tv5.setText("ON");
                tv5.setBackgroundColor(Color.parseColor("#4CAF50"));
            }
        }
        if (button == "b6") {
            if (bslocal.getB6() == 1) {
                bslocal.setB6(0);
            } else {
                bslocal.setB6(1);
            }
            if (tv6.getText().equals("ON")) {
                tv6.setText("OFF");
                tv6.setBackgroundColor(Color.parseColor("#F44336"));
            } else {
                tv6.setText("ON");
                tv6.setBackgroundColor(Color.parseColor("#4CAF50"));
            }
        }
        if (button == "b7") {
            if (bslocal.getB7() == 1) {
                bslocal.setB7(0);
            } else {
                bslocal.setB7(1);
            }
            if (tv7.getText().equals("ON")) {
                tv7.setText("OFF");
                tv7.setBackgroundColor(Color.parseColor("#F44336"));
            } else {
                tv7.setText("ON");
                tv7.setBackgroundColor(Color.parseColor("#4CAF50"));
            }
        }
        if (button == "b8") {
            if (bslocal.getB8() == 1) {
                bslocal.setB8(0);
            } else {
                bslocal.setB8(1);
            }
            if (tv8.getText().equals("ON")) {
                tv8.setText("OFF");
                tv8.setBackgroundColor(Color.parseColor("#F44336"));
            } else {
                tv8.setText("ON");
                tv8.setBackgroundColor(Color.parseColor("#4CAF50"));
            }
        }
        if (button == "b9") {
            if (bslocal.getB9() == 1) {
                bslocal.setB9(0);
            } else {
                bslocal.setB9(1);
            }
            if (tv9.getText().equals("ON")) {
                tv9.setText("OFF");
                tv9.setBackgroundColor(Color.parseColor("#F44336"));
            } else {
                tv9.setText("ON");
                tv9.setBackgroundColor(Color.parseColor("#4CAF50"));
            }
        }
        if (button == "b10") {
            if (bslocal.getB10() == 1) {
                bslocal.setB10(0);
            } else {
                bslocal.setB10(1);
            }
            if (tv10.getText().equals("ON")) {
                tv10.setText("OFF");
                tv10.setBackgroundColor(Color.parseColor("#F44336"));
            } else {
                tv10.setText("ON");
                tv10.setBackgroundColor(Color.parseColor("#4CAF50"));
            }
        }
        if (button == "masterOn") {
            bslocal = new buttonState(1);
            setButtonStatus(bslocal);
        }
        if (button == "masterOff") {
            bslocal = new buttonState(0);
            setButtonStatus(bslocal);
        }
        updateUserDetails(bslocal);
    }

    private void updateUserDetails(buttonState bslocal) {
        FirebaseUser user = mAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(bslocal);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonLogout) {
            Log.d("saveButtonState", "logout pressed");
            mAuth.signOut();
            finish();
            startActivity(new Intent(this, LogInActivity.class));
        }
        if (v == button1) {
            Log.d("saveButtonState", "b1 pressed");
            saveButtonState("b1");
        }
        if (v == button2) {
            saveButtonState("b2");
        }
        if (v == button3) {
            saveButtonState("b3");
        }
        if (v == button4) {
            saveButtonState("b4");
        }
        if (v == button5) {
            saveButtonState("b5");
        }
        if (v == button6) {
            saveButtonState("b6");
        }
        if (v == button7) {
            saveButtonState("b7");
        }
        if (v == button8) {
            saveButtonState("b8");
        }
        if (v == button9) {
            saveButtonState("b9");
        }
        if (v == button10) {
            saveButtonState("b10");
        }
        if (v == masterOn) {
            saveButtonState("masterOn");
        }
        if (v == masterOff) {
            saveButtonState("masterOff");
        }
    }

    public void onBackPressed() {
        finish();
    }
}
