//TO DO
// make buttons look nice
// make search bar update with current url

package com.example.webbrowser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create history linked list
        final History history = new History();

        // main layout
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        // webview
        LinearLayout webLayout = new LinearLayout(this);
        webLayout.setOrientation(LinearLayout.VERTICAL);
        final WebView webView = new WebView(this);
        webView.setWebViewClient(new WebViewClient());
        webLayout.addView(webView);

        // navigation row - back button, forward button, edit text, go button
        LinearLayout navigationRow = new LinearLayout(this);
        LinearLayout.LayoutParams navRow = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,100);
        navigationRow.setLayoutParams(navRow);


        // edit text bar
        final AppCompatEditText searchBar = new AppCompatEditText(this);

        // layout of search bar
        LinearLayout.LayoutParams searchBarParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        searchBarParams.weight = 1;
        searchBar.setLayoutParams(searchBarParams);

        // button params
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                100,
                100);

        // back button

        // set up
        AppCompatButton backButton = new AppCompatButton(this);
        backButton.setText("B");

        // formatting
        backButton.setLayoutParams(buttonParams); //

        // on click
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history.getBack();
                String url = history.getUrl();
                searchBar.setText(url);
                webView.loadUrl(url);

            }
        });

        // forward button
        AppCompatButton forwardButton = new AppCompatButton(this);
        forwardButton.setText("F");

        // formatting
        forwardButton.setLayoutParams(buttonParams); //

        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history.getForward();
                String url = history.getUrl();
                searchBar.setText(url);
                webView.loadUrl(url);

            }
        });

        // clear button

        // setup
        AppCompatButton clearButton = new AppCompatButton(this);
        clearButton.setText("X");

        // formatting
        clearButton.setLayoutParams(buttonParams);

        // on click
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchBar.setText("");
            }
        });

        // go button

        // setup
        AppCompatButton goButton = new AppCompatButton(this);
        goButton.setText("G");

        // formatting
        goButton.setLayoutParams(buttonParams); //

        // on click
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = searchBar.getText().toString(); // find searchBar input

                if(!url.equals("")){ // checks if address bar is empty or not

                    if (!url.startsWith("https://")){ // if url doesnt start with https:// add it
                        url = "https://" + url;
                    }

                    webView.loadUrl(url); // go to that url
                    history.add(url); // add url to history
                }
            }
        });

        // add layouts to navigation row
        navigationRow.addView(backButton);
        navigationRow.addView(forwardButton);
        navigationRow.addView(searchBar);
        navigationRow.addView(clearButton);
        navigationRow.addView(goButton);

        //add layouts to main layout
        mainLayout.addView(navigationRow);
        mainLayout.addView(webLayout);
        setContentView(mainLayout);
    }
}