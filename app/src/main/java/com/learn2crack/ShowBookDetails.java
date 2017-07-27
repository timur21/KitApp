package com.learn2crack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowBookDetails extends AppCompatActivity {

    private TextView tvBookTitle;
    private TextView tvBookAuthor;
    private TextView tvBookGenre;
    private TextView tvBookLang;
    private TextView tvBookPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_book_details);

        tvBookTitle = (TextView) findViewById(R.id.tvBookTitle);
        tvBookAuthor = (TextView) findViewById(R.id.tvBookAuthor);
        tvBookGenre = (TextView) findViewById(R.id.tvBookGenre);
        tvBookLang = (TextView) findViewById(R.id.tvBookLang);
        tvBookPrice = (TextView) findViewById(R.id.tvBookPrice);

        Intent intent = getIntent();

        tvBookTitle.setText(intent.getStringExtra(CatalogFragment.KEY_BOOK_TITLE));
        tvBookAuthor.setText(intent.getStringExtra(CatalogFragment.KEY_BOOK_AUTHOR));
        tvBookGenre.setText(intent.getStringExtra(CatalogFragment.KEY_BOOK_GENRE));
        tvBookLang.setText(intent.getStringExtra(CatalogFragment.KEY_BOOK_LANG));
        tvBookPrice.setText(intent.getStringExtra(CatalogFragment.KEY_BOOK_PRICE));


    }
}
