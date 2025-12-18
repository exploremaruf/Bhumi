package com.maruf.landmanagementsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText lengthA;
    EditText lengthB;
    EditText widthA;
    EditText widthB;

    TextView tvdisplay;

    Button btndelt, btnadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //****************************************************************************************

        lengthA = findViewById(R.id.lengthA);
        lengthB = findViewById(R.id.lengthB);
        widthA = findViewById(R.id.widthA);
        widthB = findViewById(R.id.widthB);
        tvdisplay = findViewById(R.id.tvdisplay);
        btnadd = findViewById(R.id.btnadd);
        btndelt = findViewById(R.id.btndelt);

        tvdisplay.setVisibility(TextView.GONE);

        ///************************************************************************************
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean hasError = false;

                if (lengthA.getText().toString().trim().isEmpty()) {
                    lengthA.setError("Please input number");
                    hasError = true;
                }

                if (lengthB.getText().toString().trim().isEmpty()) {
                    lengthB.setError("Please input number");
                    hasError = true;
                }

                if (widthA.getText().toString().trim().isEmpty()) {
                    widthA.setError("Please input number");
                    hasError = true;
                }

                if (widthB.getText().toString().trim().isEmpty()) {
                    widthB.setError("Please input number");
                    hasError = true;
                }

                if (hasError) return;

                float etlengthA = Float.parseFloat(lengthA.getText().toString().trim());
                float etlengthB = Float.parseFloat(lengthB.getText().toString().trim());
                float etwidthA = Float.parseFloat(widthA.getText().toString().trim());
                float etwidthB = Float.parseFloat(widthB.getText().toString().trim());

                if (etlengthA <= 0) {
                    lengthA.setError("Number must be greater than 0");
                    hasError = true;
                }
                if (etlengthB <= 0) {
                    lengthB.setError("Number must be greater than 0");
                    hasError = true;
                }
                if (etwidthA <= 0) {
                    widthA.setError("Number must be greater than 0");
                    hasError = true;
                }
                if (etwidthB <= 0) {
                    widthB.setError("Number must be greater than 0");
                    hasError = true;
                }

                if (hasError) return;

                if (etlengthA > 0 && etlengthB > 0 && etwidthA > 0 && etwidthB > 0) {

                    float avaragel = (etlengthA + etlengthB) / 2.0f;
                    float avaragew = (etwidthA + etwidthB) / 2.0f;
                    float squearefeet = (avaragel * avaragew);
                    float landparcentange = (float) (squearefeet / 435.6);

                    tvdisplay.setVisibility(View.VISIBLE);
                    tvdisplay.setText("গড় দৈর্ঘ্য: " + avaragel + " ফুট" + "\n" + "গড় প্রস্থ: " + avaragew + " ফুট" + "\n" + "মোট ক্ষেত্রফল: " + squearefeet + " বর্গফুট" + "\n" + "মোট জমি: " + landparcentange + " শতক");

                }
            }
        });

        btndelt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvdisplay.setVisibility(View.VISIBLE);
                tvdisplay.setText("");
                tvdisplay.setVisibility(View.GONE);
                lengthA.setText("");
                lengthB.setText("");
                widthA.setText("");
                widthB.setText("");
                lengthA.setError(null);
                lengthB.setError(null);
                widthA.setError(null);
                widthB.setError(null);
            }
        });

    }
}