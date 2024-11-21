package com.example.homos_cristian_cosmin_1101;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView patientListView;
    private ArrayList<Patient> patients;
    private ArrayAdapter<Patient> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        patientListView = findViewById(R.id.patientListView);  //ListView
        patients = new ArrayList<>();

        // Inițializăm adapter-ul pentru ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, patients);
        patientListView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add) {
            Intent intent = new Intent(MainActivity.this, AddPatientActivity.class);
            startActivityForResult(intent, 1);
            return true;
        } else if (item.getItemId() == R.id.menu_details) {
            // Preluăm textul de pe Pastebin și afișăm Toast-ul
            fetchPastebinText("https://pastebin.com/raw/H8wVH8DY");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void fetchPastebinText(String urlString) {
        new Thread(() -> {
            try {
                // Creăm o conexiune către URL
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Afișăm textul preluat într-un Toast pe thread-ul UI
                    String finalResult = result.toString();
                    runOnUiThread(() -> Toast.makeText(MainActivity.this, finalResult, Toast.LENGTH_LONG).show());

                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Eroare la preluarea datelor!", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Patient newPatient = (Patient) data.getSerializableExtra("newPatient");
            if (newPatient != null) {
                patients.add(newPatient);
                adapter.notifyDataSetChanged(); // Actualizăm ListView-ul
            }
        }
    }
}