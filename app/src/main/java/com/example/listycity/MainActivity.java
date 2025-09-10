package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    private int selectedIndex = -1;

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

        ListView cityList   = findViewById(R.id.list_city);
        EditText cityInput  = findViewById(R.id.city_input);
        Button confirmBtn   = findViewById(R.id.confirm_button);
        Button deleteBtn    = findViewById(R.id.delete_city);
        TextView emptyView  = findViewById(R.id.empty_view);
        Button addBtn = findViewById(R.id.add_city);

        dataList = new ArrayList<>();

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);
        cityList.setEmptyView(emptyView);

        // Select item on tap
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedIndex = position;
            }
        });

        // Add button also adds text to list
        addBtn.setOnClickListener(v -> {
            String name = cityInput.getText().toString().trim();
            if (name.isEmpty()) {
                Toast.makeText(this, "Type a city name", Toast.LENGTH_SHORT).show();
                return;
            }
            dataList.add(name);
            cityAdapter.notifyDataSetChanged();
            cityInput.setText("");
        });

        // Confirm adds text to list
        confirmBtn.setOnClickListener(v -> {
            String name = cityInput.getText().toString().trim();
            if (name.isEmpty()) {
                Toast.makeText(this, "Type a city name", Toast.LENGTH_SHORT).show();
                return;
            }
            dataList.add(name);
            cityAdapter.notifyDataSetChanged();
            cityInput.setText("");
        });

        // Delete removes selected item
        deleteBtn.setOnClickListener(v -> {
            if (selectedIndex == -1) {
                Toast.makeText(this, "Tap a city to select", Toast.LENGTH_SHORT).show();
                return;
            }
            dataList.remove(selectedIndex);
            cityAdapter.notifyDataSetChanged();
            selectedIndex = -1;
        });
    }
}