package com.example.abookt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class AllBooksActivity extends AppCompatActivity {

    private RecyclerView booksRecView;
    public BookRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        adapter = new BookRecViewAdapter(this);
        booksRecView = findViewById(R.id.booksRecView);

        booksRecView.setAdapter(adapter);
        booksRecView.setLayoutManager(new LinearLayoutManager(this));
        // booksRecView.setLayoutManager(new GridLayoutManager(this, 2));

        List<BookModel> books = new ArrayList<>();
        books.add(new BookModel(0, "Harry Potter", "J. K. Rowling", 336,
                "https://assets.thalia.media/img/artikel/63be06f8134196a5fca3e5320017be22650d4f64-00-00.jpeg",
                "Zaubererbuch", "Dies ist ein Buch über einen Zauberer-Jungen"));

        books.add(new BookModel(0, "The Lord of the Rings", "J. R. R. Tolkien", 777,
                "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcT1KbNKHi6we3eF90RGyLf1sgxvgZ_ynIJbxKh97F1fZU3TRO0T",
                "Fantasy", "Eins der besten Fantasy-Bücker aller Zeiten"));

        adapter.setBooks(books);

    }
}