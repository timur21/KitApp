package com.learn2crack;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.learn2crack.model.Book;

import java.util.List;

import static com.learn2crack.R.id.imageView;
import static com.learn2crack.R.id.title;

/**
 * Created by timur on 10-Jul-17.
 */

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.ViewHolder> {







    private List<Book> books;
    private Context context;

    public CatalogAdapter(List<Book> books, Context context) {
        this.books = books;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item,parent,false);

        return new ViewHolder(v);
    }

    public void getBooks(){

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book book = books.get(position);
        holder.b_title.setText(book.getTitle());
        holder.b_author.setText(book.getAuthor());
        holder.b_lang.setText(book.getLang());
        holder.b_genre.setText(book.getGenre());
        holder.b_price.setText(book.getPrice());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView b_title;
        private TextView b_author;
        private TextView b_genre;
        private TextView b_lang;
        private TextView b_price;


        public ViewHolder(View itemView) {
            super(itemView);

            b_title = (TextView) itemView.findViewById(R.id.b_title);
            b_author = (TextView) itemView.findViewById(R.id.b_author);
            b_genre = (TextView) itemView.findViewById(R.id.b_genre);
            b_lang = (TextView) itemView.findViewById(R.id.b_lang);
            b_price = (TextView) itemView.findViewById(R.id.b_price);

        }
    }






}
