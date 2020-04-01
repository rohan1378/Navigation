package com.example.navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.navigation.R;

public class MainActivity2 extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int s = extras.getInt("source");
        int d = extras.getInt("dest");
        //int s = intent.getIntExtra(MainActivity.s2, 0);
        //int d = intent.getIntExtra(MainActivity.d2, 1);
        //String s = intent.getStringExtra(String.valueOf(MainActivity.s2));
        Button source2 = getBtn(s);
        Button dest2 = getBtn(d);

        source2.setBackgroundResource(R.drawable.circle_shape_selected);
        dest2.setBackgroundResource(R.drawable.circle_shape_selected);

/*
        btn = findViewById(R.id.btn10);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btn.setBackgroundResource(R.drawable.circle_shape_selected);
            }
        });


 */
    }

    public Button getBtn(int n) {
        if(n == 1)
            return findViewById(R.id.btn1);
        else if(n == 2)
            return findViewById(R.id.btn2);
        else if(n == 3)
            return findViewById(R.id.btn3);
        else if(n == 4)
            return findViewById(R.id.btn4);
        else if(n == 5)
            return findViewById(R.id.btn5);
        else if(n == 6)
            return findViewById(R.id.btn6);
        else if(n == 7)
            return findViewById(R.id.btn7);
        else if(n == 8)
            return findViewById(R.id.btn8);
        else if(n == 9)
            return findViewById(R.id.btn9);
        else if(n == 10)
            return findViewById(R.id.btn10);
        else if(n == 11)
            return findViewById(R.id.btn11);
        else if(n == 12)
            return findViewById(R.id.btn12);
        else if(n == 13)
            return findViewById(R.id.btn13);
        else if(n == 14)
            return findViewById(R.id.btn14);
        else if(n == 15)
            return findViewById(R.id.btn15);
        else if(n == 16)
            return findViewById(R.id.btn16);
        else if(n == 17)
            return findViewById(R.id.btn17);
        else if(n == 18)
            return findViewById(R.id.btn18);
        else if(n == 19)
            return findViewById(R.id.btn19);
        else if(n == 20)
            return findViewById(R.id.btn20);
        else
            return findViewById(R.id.btn1);
    }





}

