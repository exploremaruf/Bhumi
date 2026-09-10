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

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Locale;

public class MapActivity extends AppCompatActivity {
    private EditText lengthA, lengthB, widthA, widthB;
    private TextInputLayout tilLengthA, tilLengthB, tilWidthA, tilWidthB;
    private TextView tvdisplay;
    private MaterialCardView resultCard;
    private Button btnadd, btndelt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_map);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Views
        lengthA = findViewById(R.id.lengthA);
        lengthB = findViewById(R.id.lengthB);
        widthA = findViewById(R.id.widthA);
        widthB = findViewById(R.id.widthB);
        
        tilLengthA = findViewById(R.id.tilLengthA);
        tilLengthB = findViewById(R.id.tilLengthB);
        tilWidthA = findViewById(R.id.tilWidthA);
        tilWidthB = findViewById(R.id.tilWidthB);
        
        tvdisplay = findViewById(R.id.tvdisplay);
        resultCard = findViewById(R.id.resultCard);
        btnadd = findViewById(R.id.btnadd);
        btndelt = findViewById(R.id.btndelt);

        // Calculate Button Listener
        btnadd.setOnClickListener(v -> calculateLand());

        // Clear Button Listener
        btndelt.setOnClickListener(v -> clearFields());
    }

    private void calculateLand() {
        boolean hasError = false;

        // Reset errors
        tilLengthA.setError(null);
        tilLengthB.setError(null);
        tilWidthA.setError(null);
        tilWidthB.setError(null);

        String strLA = lengthA.getText().toString().trim();
        String strLB = lengthB.getText().toString().trim();
        String strWA = widthA.getText().toString().trim();
        String strWB = widthB.getText().toString().trim();

        if (strLA.isEmpty()) {
            tilLengthA.setError("মাপ লিখুন");
            hasError = true;
        }
        if (strLB.isEmpty()) {
            tilLengthB.setError("মাপ লিখুন");
            hasError = true;
        }
        if (strWA.isEmpty()) {
            tilWidthA.setError("মাপ লিখুন");
            hasError = true;
        }
        if (strWB.isEmpty()) {
            tilWidthB.setError("মাপ লিখুন");
            hasError = true;
        }

        if (hasError) return;

        try {
            float etlengthA = Float.parseFloat(strLA);
            float etlengthB = Float.parseFloat(strLB);
            float etwidthA = Float.parseFloat(strWA);
            float etwidthB = Float.parseFloat(strWB);

            if (etlengthA <= 0) {
                tilLengthA.setError("০ এর বেশি হতে হবে");
                hasError = true;
            }
            if (etlengthB <= 0) {
                tilLengthB.setError("০ এর বেশি হতে হবে");
                hasError = true;
            }
            if (etwidthA <= 0) {
                tilWidthA.setError("০ এর বেশি হতে হবে");
                hasError = true;
            }
            if (etwidthB <= 0) {
                tilWidthB.setError("০ এর বেশি হতে হবে");
                hasError = true;
            }

            if (hasError) return;

            float averageL = (etlengthA + etlengthB) / 2.0f;
            float averageW = (etwidthA + etwidthB) / 2.0f;
            float squareFeet = (averageL * averageW);
            float landPercentage = (float) (squareFeet / 435.6);

            String result = String.format(Locale.getDefault(),
                    "গড় দৈর্ঘ্য: %.2f ফুট\nগড় প্রস্থ: %.2f ফুট\nমোট ক্ষেত্রফল: %.2f বর্গফুট\nমোট জমি: %.3f শতক",
                    averageL, averageW, squareFeet, landPercentage);

            tvdisplay.setText(result);
            resultCard.setVisibility(View.VISIBLE);

        } catch (NumberFormatException e) {
            // Handle parsing error if necessary
        }
    }

    private void clearFields() {
        lengthA.setText("");
        lengthB.setText("");
        widthA.setText("");
        widthB.setText("");
        
        tilLengthA.setError(null);
        tilLengthB.setError(null);
        tilWidthA.setError(null);
        tilWidthB.setError(null);
        
        resultCard.setVisibility(View.GONE);
        tvdisplay.setText("");
    }
}
