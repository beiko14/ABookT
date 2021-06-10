package com.example.abookt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";

    private ImageView imageView;
    private Button buttonAddToCurrentlyReading, buttonAddToWantToRead, buttonAddToAlreadyRead, buttonAddToFavorites;
    private TextView textBookName2, textAuthor2, textPages2, textShortDescription2, textLongDescription2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();

        //TODO: get data from recycler view here
//        BookModel book = new BookModel(0, "Harry Potter", "J. K. Rowling", 336,
//                "https://assets.thalia.media/img/artikel/63be06f8134196a5fca3e5320017be22650d4f64-00-00.jpeg",
//                "Zaubererbuch", "Dies ist ein Buch über einen Zauberer-Jungen");

        Intent intent = getIntent();
        if(intent != null){
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if(bookId != -1){
                BookModel incomingBook = Utils.getInstance().getBookById(bookId);
                if(incomingBook != null){
                    setData(incomingBook);
                }
            }
        }
    }

    private void setData(BookModel book){
        textBookName2.setText(book.getName());
        textAuthor2.setText(book.getAuthor());
        textPages2.setText(String.valueOf(book.getPages()));
        textShortDescription2.setText(book.getShortDesc());
        textLongDescription2.setText(book.getLongDesc());

        Glide.with(this)
                .asBitmap().load(book.getImageUrl())
                .into(imageView);
    }

    private void initViews() {
        imageView = findViewById(R.id.imageView);

        buttonAddToCurrentlyReading = findViewById(R.id.buttonAddToCurrentlyReading);
        buttonAddToWantToRead = findViewById(R.id.buttonAddToWantToRead);
        buttonAddToAlreadyRead = findViewById(R.id.buttonAddToAlreadyRead);
        buttonAddToFavorites = findViewById(R.id.buttonAddToFavorites);

        textBookName2 = findViewById(R.id.textBookName2);
        textAuthor2 = findViewById(R.id.textAuthor2);
        textPages2 = findViewById(R.id.textPages2);
        textShortDescription2 = findViewById(R.id.textShortDescription2);
        textLongDescription2 = findViewById(R.id.textLongDescription2);
    }
}