package com.example.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class TestsActivity extends AppCompatActivity {

    private ImageView testD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);

        testD = findViewById(R.id.testD);
        testD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestsActivity.this, com.example.loginregister.DepressionActivity.class);
                TestsActivity.this.startActivity(intent);
                TestsActivity.this.finish();
            }
        });
    }
}