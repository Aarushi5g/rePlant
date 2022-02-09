package com.android.replant;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class PurchaseActivity extends AppCompatActivity {

    private EditText treeName_TV;
    private TextView treeLocation_TV;
    private TextView treeType_TV;
    private String treeLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        //change colour of status bar
        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.main));

        //set views to variables
        treeType_TV = findViewById(R.id.treetype);
        treeName_TV = findViewById(R.id.treeName);
        treeLocation_TV = findViewById(R.id.treeLocation_TV);
        treeName_TV.setInputType(InputType.TYPE_CLASS_TEXT);

        // Get the location of tree selected from MapsActivity
        Intent i = getIntent();
        treeLocation = i.getStringExtra("treeLocation");
        treeLocation_TV.setText("Tree location: Ticknock Forest");

        Button button = findViewById(R.id.buy);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PurchaseActivity.this, CheckoutActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}


