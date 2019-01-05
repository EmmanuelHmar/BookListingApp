package com.emmanuelhmar.booklistingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BookAdapter extends ArrayAdapter<Book> {
    @BindView(R.id.title) TextView textTitle;
    @BindView(R.id.authors) TextView textAuthors;
    @BindView(R.id.date) TextView textDate;
    @BindView(R.id.price) TextView textPrice;

    public BookAdapter(@NonNull Context context, @NonNull ArrayList<Book> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.book_list_item, parent, false);
        }

        Book book = getItem(position);

        assert book != null;
        setupBookView(listItemView,book);

        return listItemView;
    }

    private void setupBookView(View view, Book book) {

        String title = book.getTitle();
        List<String> authors = book.getAuthors();
        int publishedDate = book.getPublishedDate();
        double listedPrice = book.getListedPrice();

        ButterKnife.bind(this, view);

        textTitle.setText(title);
        textAuthors.setText(authors.toString());
        textDate.setText(String.valueOf(publishedDate));
        textPrice.setText(String.valueOf(listedPrice));
    }
}
