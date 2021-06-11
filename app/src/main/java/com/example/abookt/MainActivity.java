package com.example.abookt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonAllBooks, buttonCurrentlyReading,
            buttonAllreadyRead, buttonWantToReadBooks, buttonFavorite, buttonAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        // initialize the Lists to avoid possible null-pointer exceptions if user clicks AlreadyReadBooks first
        Utils.getInstance();

        buttonAllBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
                startActivity(intent);
            }
        });

        buttonAllreadyRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlreadyReadBooksActivity.class);
                startActivity(intent);
            }
        });

        buttonWantToReadBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReadListActivity.class);
                startActivity(intent);
            }
        });

        buttonFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavoriteBooksActivity.class);
                startActivity(intent);
            }
        });

        buttonCurrentlyReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CurrentlyReadingActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initViews(){
        buttonAllBooks = findViewById(R.id.buttonAllBooks);
        buttonCurrentlyReading = findViewById(R.id.buttonCurrentlyReading);
        buttonAllreadyRead = findViewById(R.id.buttonAllreadyRead);
        buttonWantToReadBooks = findViewById(R.id.buttonWantToReadBooks);
        buttonFavorite = findViewById(R.id.buttonFavorite);
        buttonAbout = findViewById(R.id.buttonAbout);
    }

}