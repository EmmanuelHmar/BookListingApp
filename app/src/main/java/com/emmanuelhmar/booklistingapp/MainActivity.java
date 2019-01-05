package com.emmanuelhmar.booklistingapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private final static String LOG_TAG = MainActivity.class.getName();
//    private final String JSON_URL = "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=1";
    private final String JSON_URL = "https://www.googleapis.com/books/v1/volumes?q="; //android&maxResults=1";
    private static BookAdapter bookAdapter;
    private static String searchTerm;

    @BindView(R.id.list_item)
    ListView listView;

    @BindView(R.id.search_view)
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        bookAdapter = new BookAdapter(this, new ArrayList<Book>());

        listView.setAdapter(bookAdapter);


    }

    @OnClick(R.id.button)
    public void clickMe() {
        searchTerm = String.valueOf(searchView.getQuery());

        Toast.makeText(getApplicationContext(), searchTerm, Toast.LENGTH_SHORT).show();

        new BookAsyncTask().execute(JSON_URL + searchTerm + "&maxResults=2");
    }

    private static class BookAsyncTask extends AsyncTask<String, Void, List<Book>> {
        @Override
        protected List<Book> doInBackground(String... strings) {

            return QueryUtils.fetchBookData(strings[0]);
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            bookAdapter.clear();

            Log.i(LOG_TAG, "Books: " + books);

            if (books != null && !books.isEmpty()) {
                bookAdapter.addAll(books);
            }

        }
    }

}
