package com.emmanuelhmar.booklistingapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class QueryUtils {

    public static List<Book> fetchBookData(String jsonUrl) {
        URL url = null;

        try {
            url = new URL(jsonUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        assert url != null;
        String jsonResponse = makeHttp(url);

        return extractFeaturesFromBook(jsonResponse);



    }

//    Get a connection to the HTTP
    private static String makeHttp(URL url) {
        String jsonResponse = null;

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

//        Try and connect to the URL using HttpUrlConnection
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000); //milliseconds
            urlConnection.setConnectTimeout(15000); //milliseconds
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            Log.i("LOG: ", urlConnection.getResponseCode() + " CODE");
//            If the responsecode is 200, then proceed to stream the data
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonResponse;
    }


//    Create a function to read our stream, parse it and return it as a string
    private static String readFromStream(InputStream stream) throws IOException {
//        Create a StringBuilder to create our String file
        StringBuilder stringBuilder = new StringBuilder();

//        If the stream is not null then parse thru the stream
        if (stream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(stream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();

            while (line != null) {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }
        }
        return stringBuilder.toString();
    }

    private static List<Book> extractFeaturesFromBook(String jsonResponse) {

//        Check if the json parameter is empty
        if (TextUtils.isEmpty(jsonResponse)) {
            return null;
        }

        List<Book> books = new ArrayList<>();

       try {
//           Create a JSONobject from the JSON string
           JSONObject baseJsonResponse = new JSONObject(jsonResponse);

//           Extract the JSONArray associated with the key "items"
           JSONArray bookArray = baseJsonResponse.optJSONArray("items");

           for (int i = 0; i < bookArray.length(); i++) {
//               Get an earthquake at position i
               JSONObject currentBook = bookArray.optJSONObject(i);

//               Get the volumeInfo from the book
               JSONObject volumeInfo = currentBook.optJSONObject("volumeInfo");

//               Get the title of the book
               String title = volumeInfo.optString("title");
               Log.i("LOG", " authors: " + title);

//               Get the authors of the book
               ArrayList<String> authors = new ArrayList<>();

//               Create and get the authors
               JSONArray authorArrays = volumeInfo.optJSONArray("authors");
               Log.i("LOG", " authors: " + authorArrays);

               for (int j = 0; j < authorArrays.length(); j++) {
                   authors.add(authorArrays.optString(j));
               }

               String publishedDate = volumeInfo.optString("publishedDate");
               Log.i("LOG", " authors: " + publishedDate);

//               Switch to the saleInfo object and get the sales price
               JSONObject saleInfo = currentBook.optJSONObject("saleInfo");

               String saleability = saleInfo.optString("saleability");

               String listedPrice = "Not for Sale";

               if (saleability.equals("FOR_SALE")) {
                   JSONObject listPrice = saleInfo.optJSONObject("listPrice");
                   listedPrice = "$" + listPrice.optString("amount");
               }



               Log.i("LOG", "extractFeaturesFromBook: LOG" + books);
               Log.i("LOG", "test: " + listedPrice);

               books.add(new Book(title, authors, publishedDate, listedPrice));

           }

       } catch (JSONException e) {
           e.printStackTrace();
       }

        Log.i(TAG, "extractFeaturesFromBook: " + books + " LOG");

        return books;

    }
}
