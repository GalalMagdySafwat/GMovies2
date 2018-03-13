package com.example.android.gmovie;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.android.gmovie.MovieUtils.QueryUtils;

import java.util.ArrayList;
import java.util.List;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<Movie>> {
    private static final String LOG_TAG = MainActivity.class.getName();
    private static final String TMDB_REQUEST_URL =
            "https://api.themoviedb.org/3/discover/movie?api_key=*****&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1";
    private static final int EARTHQUAKE_LOADER_ID = 1;
    private MovieAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView MovieListView = (ListView) findViewById(R.id.list);

        mAdapter = new MovieAdapter(this, new ArrayList<Movie>());


        MovieListView.setAdapter(mAdapter);
        LoaderManager loaderManager=getLoaderManager();
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID,null,this);

    }
    public Loader<List<Movie>> onCreateLoader(int i, Bundle bundle) {
        return new MovieLoader(this, TMDB_REQUEST_URL);
    }

    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> movies) {
        mAdapter.clear();
        if (movies != null && !movies.isEmpty()) {
            mAdapter.addAll(movies);
        }
    }

    public void onLoaderReset(Loader<List<Movie>> loader) {
        mAdapter.clear();


    }
}
