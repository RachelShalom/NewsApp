package com.example.rachel.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rachel on 01/08/2017.
 */

/**
 * A {@link StoryAdapter} knows how to create a list item layout for each story
 * in the data source (a list of {@link Story} objects).
 * <p>
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class StoryAdapter extends ArrayAdapter<Story> {

    /**
     * Constructs a new {@link StoryAdapter}.
     *
     * @param context of the app
     * @param stories is the list of stories, which is the data source of the adapter
     */
    public StoryAdapter(Context context, List<Story> stories) {
        super(context, 0, stories);
    }

    /**
     * Returns a list item view that displays information about the story at the given position
     * in the list of stories.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        // get the the story at a given position in the list of stories
        Story currentStory = getItem(position);

        // Find the TextView with view ID stiry_title
        TextView TitleView = (TextView) listItemView.findViewById(R.id.story_title);
        //display the title of the current story in that view
        TitleView.setText(currentStory.getTitle());

        //Find the TextView with the view ID Story_section
        TextView SectionView = (TextView) listItemView.findViewById(R.id.story_section);
        //display the section of the current story in that view
        SectionView.setText(currentStory.getSection());

        //Find the TextView with the ID story_date

        TextView DateView = (TextView) listItemView.findViewById(R.id.story_date);
        String Current_date = currentStory.getDate();
        String[] Short_date = Current_date.split("T");
        //dis[lay the date of the current story in that view
        DateView.setText(Short_date[0]);

        return listItemView;
    }
}
