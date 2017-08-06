package com.example.rachel.news;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Story>> {
    EditText searchEdit;
    String searchValue;
    public static String DEFAULT_STORIES_REQUEST;
    public static String storiesRequest;


    private static final String LOG_TAG = MainActivity.class.getName();
    /**
     * Constant value for the story loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int STORY_LOADER_ID = 1;

    /**
     * Adapter for the list of earthquakes
     */
    public StoryAdapter mAdapter;

    /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DEFAULT_STORIES_REQUEST = "http://content.guardianapis.com/search?q=debates&api-key=1935bd38-513c-4b76-8f12-9aa2352800ce";
       Button SearchButton = (Button) findViewById(R.id.button);
        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEdit = (EditText) findViewById(R.id.search_editor);
                searchValue = searchEdit.getText().toString();
                Log.e(LOG_TAG, "you typed" + searchValue);
                storiesRequest = "http://content.guardianapis.com/search?q=" + searchValue + "&api-key=1935bd38-513c-4b76-8f12-9aa2352800ce";
               /* Intent myIntent = new Intent(MainActivity.this,
                        StoryActivity.class);
                startActivity(myIntent);*/
                getLoaderManager().restartLoader(STORY_LOADER_ID,null,MainActivity.this);
            }

        });
        // Find a reference to the {@link ListView} in the layout
        ListView storyListView = (ListView) findViewById(R.id.list);
        //Create a new adapter that takes an empty list of stories as input
        mAdapter = new StoryAdapter(this, new ArrayList<Story>());
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        storyListView.setAdapter(mAdapter);
        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected earthquake.
        storyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current story that was clicked on
                Story currentStory = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri storyUri = Uri.parse(currentStory.getLink());

                // Create a new intent to view the story URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, storyUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
        reload();

    }

    private void reload() {
        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(STORY_LOADER_ID, null, this);
        Log.e(LOG_TAG, "Loader is initiated 1");
    }

    @Override
    public Loader<List<Story>> onCreateLoader(int id, Bundle args) {
        if (storiesRequest == null) {
            return new StoryLoader(this, DEFAULT_STORIES_REQUEST);
        } else {
            return new StoryLoader(this, storiesRequest);
        }


    }

    @Override
    public void onLoadFinished(Loader<List<Story>> loader, List<Story> stories) {
        // Clear the adapter of previous earthquake data
        mAdapter.clear();

        // If there is a valid list of {@link Story}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (stories != null && !stories.isEmpty()) {
            mAdapter.addAll(stories);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Story>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();

    }
}





