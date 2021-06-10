package com.example.abookt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BookActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button buttonAddToCurrentlyReading, buttonAddToWantToRead, buttonAddToAlreadyRead, buttonAddToFavorites;
    private TextView textBookName2, textAuthor2, textPages2, textShortDescription2, textLongDescription2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();

        //TODO: get data from recycler view here
        BookModel book = new BookModel(0, "Harry Potter", "J. K. Rowling", 336,
                "https://assets.thalia.media/img/artikel/63be06f8134196a5fca3e5320017be22650d4f64-00-00.jpeg",
                "Zaubererbuch", "Dies ist ein Buch über einen Zauberer-Jungen sjdflkas jdfljasoi fsaoi jfsaj foijsaoi fjsoiaj foisajf oijsiof jsaoij foisaj foijsf oijs foijsoi fjsoi fjosij f" +
                "iasjd fdoiasj foisaj fiojsaoif jasoi fdjaoisjf ojsafdoi jsoif jsoid fjoisj foisj foisj foi js foijsoi fjsoi fjsoi fjosij fois jfoisaj f oi sjfio jsf sj fd iojsio dfjsa fd" +
                "oisj dfoisaj foiasj foi jsf oijsoif jsoif jsio fjios fjios jdfiosj fiosj foij sfoij sfiojsoif jsaio fjiosaj fdiosa jfoisajf oisjf iosajf oijsafoi sai fdasfd j" +
                "aosid jfoiasj fdoisj foiasj foiasj foisj foiasj foisjf ois fois fdjoias dfjois dfoisaj dfoi sadfoi sad foisdf oisadoif jsoidf jsoiadf joisadjf oias fdoi asdf" +
                "osiad fjoisa djfioasd jfoisa jfoisa jfoias fjdioasj foias jfioas jfdoisa fdjiosa fiosadf jiosad jfoisa dfdiosad fjiosadf ioasd foiasd jfioas dfiosa dfioas dfio asf" +
                "sjd foiasj dfiosaiof siao jfioas j foijsaoi fjsaoi fjios jfoisj foiasfoi saof ijiosj fois jf iosj f iosjafio j soif jsiof jiosj foisa f io jsiof sio fios fois jfoi sajdf" +
                "oias jdfoisa jfio jsafio jsoif jsoif jios fjois fjois fjoisfj oisj fois jfoisaj foisa jfoisa jfoisaj foisa jfoisa foisa foisa foisa jfoi sfio safiosjaf io safiosaf");

        setData(book);
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