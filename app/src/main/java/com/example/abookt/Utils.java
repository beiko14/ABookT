package com.example.abookt;

import java.util.ArrayList;
import java.util.List;

//TODO: replace with a DB (sqlite?)
public class Utils {

    private static Utils instance;

    private static List<BookModel> allBooks;
    private static List<BookModel> currentlyReading;
    private static List<BookModel> alreadyReadBooks;
    private static List<BookModel> readList;
    private static List<BookModel> favoriteBooks;

    private Utils() {
        if(null == allBooks){
            allBooks = new ArrayList<>();
            initData();
        }

        if(null == currentlyReading){
            currentlyReading = new ArrayList<>();
        }

        if(null == alreadyReadBooks){
            alreadyReadBooks = new ArrayList<>();
        }

        if(null == readList){
            readList = new ArrayList<>();
        }

        if(null == favoriteBooks){
            favoriteBooks = new ArrayList<>();
        }
    }

    private void initData() {

        allBooks.add(new BookModel(0, "Harry Potter", "J. K. Rowling", 336,
                "https://assets.thalia.media/img/artikel/63be06f8134196a5fca3e5320017be22650d4f64-00-00.jpeg",
                "Zaubererbuch", "Dies ist ein Buch über einen Zauberer-Jungen"));

        allBooks.add(new BookModel(1, "The Lord of the Rings", "J. R. R. Tolkien", 777,
                "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcT1KbNKHi6we3eF90RGyLf1sgxvgZ_ynIJbxKh97F1fZU3TRO0T",
                "Fantasy", "Eins der besten Fantasy-Bücker aller Zeiten"));
    }

    public static Utils getInstance() {
        if(null != instance){
            return instance;
        } else {
            instance = new Utils();
            return instance;
        }
    }

    public static List<BookModel> getAllBooks() {
        return allBooks;
    }

    public static List<BookModel> getCurrentlyReading() {
        return currentlyReading;
    }

    public static List<BookModel> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static List<BookModel> getReadList() {
        return readList;
    }

    public static List<BookModel> getFavoriteBooks() {
        return favoriteBooks;
    }

    public BookModel getBookById(int id){
        for (BookModel b: allBooks){
            if(b.getId() == id){
                return b;
            }
        }

        return null;
    }
}
