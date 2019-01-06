package com.emmanuelhmar.booklistingapp;

import android.content.Context;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import static com.emmanuelhmar.booklistingapp.MainActivity.JSON_URL;

public class BookLoader extends AsyncTaskLoader<List<Book>> {
    private String url;

    public BookLoader(@NonNull Context context, String url) {
        super(context);
        this.url = url;
    }

//    @Override
//    protected List<Book> doInBackground(String... strings) {
//        List<Book> books = QueryUtils.fetchBookData(strings[0]);
//
//        return books;
//    }
//
//    @Override
//    protected void onPostExecute(List<Book> books) {
//        bookAdapter.clear();
//
//        Log.i("LOG", "Books: " + books);
//
//        if (books != null && !books.isEmpty()) {
//            bookAdapter.addAll(books);
//        }



    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public List<Book> loadInBackground() {
        List<Book> books = QueryUtils.fetchBookData(JSON_URL);

        return books;
    }
}


