package com.emmanuelhmar.booklistingapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>>{
    private final static String TAG = MainActivity.class.getName();
//    private final String JSON_URL = "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=1";
    public static String JSON_URL = "https://www.googleapis.com/books/v1/volumes?q="; //android&maxResults=1";
    public static BookAdapter bookAdapter;
    private static String searchTerm;

    @BindView(R.id.list_item)
    ListView listView;

    @BindView(R.id.search_view)
    SearchView searchView;

    @BindView(R.id.emptyText)
    TextView emptyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        bookAdapter = new BookAdapter(this, new ArrayList<Book>());

        listView.setAdapter(bookAdapter);

//     Set the text visible if the adapter is empty
        listView.setEmptyView(emptyText);

        emptyText.setText("Enter Keyword and hit GO! Top search results will display here");

    }

    @OnClick(R.id.button)
    public void clickMe() {
        searchTerm = String.valueOf(searchView.getQuery());

        Toast.makeText(getApplicationContext(), searchTerm, Toast.LENGTH_SHORT).show();

        JSON_URL = "https://www.googleapis.com/books/v1/volumes?q=" + searchTerm + "&maxResults=10";

        getSupportLoaderManager().initLoader((int) Math.floor(Math.random()), null, this);

    }

    @NonNull
    @Override
    public Loader<List<Book>> onCreateLoader(int id, @Nullable Bundle args) {
        return new BookLoader(MainActivity.this, JSON_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Book>> loader, List<Book> data) {
        Log.i(TAG, "onLoadFinished: ");
        bookAdapter.clear();

        emptyText.setText("There are no results found");

        if (data != null && !data.isEmpty()) {
            bookAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Book>> loader) {
        Log.i(TAG, "onLoaderReset: " + bookAdapter);
        bookAdapter.clear();

    }

}
