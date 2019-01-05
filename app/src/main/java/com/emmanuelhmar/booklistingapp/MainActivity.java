package com.emmanuelhmar.booklistingapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String JSON_URL = "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.list_item);



    }

    private static class BookAsyncTask extends AsyncTask<String, Void, List<Book>> {
        @Override
        protected List<Book> doInBackground(String... strings) {

            return QueryUtils.fetchBookData(strings[0]);
        }
    }

}
