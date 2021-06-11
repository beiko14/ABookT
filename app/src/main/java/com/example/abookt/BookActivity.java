package com.example.abookt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.List;

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
        if (intent != null) {
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (bookId != -1) {
                BookModel incomingBook = Utils.getInstance().getBookById(bookId);
                if (incomingBook != null) {
                    setData(incomingBook);

                    handleAlreadyRead(incomingBook);
                    handleReadList(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                    handleFavoriteBooks(incomingBook);

                }
            }
        }
    }

    private void handleFavoriteBooks(BookModel incomingBook) {
        List<BookModel> favoriteBooks = Utils.getInstance().getFavoriteBooks();

        boolean existInfavoriteBooks = false;

        for (BookModel b : favoriteBooks) {
            if (b.getId() == incomingBook.getId()) {
                existInfavoriteBooks = true;
            }
        }

        if (existInfavoriteBooks) {
            buttonAddToFavorites.setEnabled(false);
        } else {
            buttonAddToFavorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToFavorite(incomingBook)) {
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();

                        //navigate user
                        Intent intent = new Intent(BookActivity.this, FavoriteBooksActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "FAILED", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleCurrentlyReadingBooks(BookModel incomingBook) {
        List<BookModel> currentlyReadingBooks = Utils.getInstance().getCurrentlyReading();

        boolean existInCurrentlyReadingBooks = false;

        for (BookModel b : currentlyReadingBooks) {
            if (b.getId() == incomingBook.getId()) {
                existInCurrentlyReadingBooks = true;
            }
        }

        if (existInCurrentlyReadingBooks) {
            buttonAddToCurrentlyReading.setEnabled(false);
        } else {
            buttonAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToCurrentlyReading(incomingBook)) {
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();

                        //navigate user
                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "FAILED", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleReadList(BookModel incomingBook) {
        List<BookModel> readList = Utils.getInstance().getReadList();

        boolean existInReadList = false;

        for (BookModel b : readList) {
            if (b.getId() == incomingBook.getId()) {
                existInReadList = true;
            }
        }

        if (existInReadList) {
            buttonAddToWantToRead.setEnabled(false);
        } else {
            buttonAddToWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToReadList(incomingBook)) {
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();

                        //navigate user
                        Intent intent = new Intent(BookActivity.this, ReadListActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "FAILED", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    // Enable and disable button
    // add book to already read book List
    private void handleAlreadyRead(BookModel incomingBook) {
        List<BookModel> alreadyReadBooks = Utils.getInstance().getAlreadyReadBooks();

        boolean existInAlreadyReadBooks = false;

        for (BookModel b : alreadyReadBooks) {
            if (b.getId() == incomingBook.getId()) {
                existInAlreadyReadBooks = true;
            }
        }

        if (existInAlreadyReadBooks) {
            buttonAddToAlreadyRead.setEnabled(false);
        } else {
            buttonAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToAlreadyRead(incomingBook)) {
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();

                        //navigate user
                        Intent intent = new Intent(BookActivity.this, AlreadyReadBooksActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "FAILED", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setData(BookModel book) {
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