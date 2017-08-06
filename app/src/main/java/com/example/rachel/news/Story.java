package com.example.rachel.news;

/**
 * Created by Rachel on 31/07/2017.
 */

public class Story {

    //Title  of the story
    private String mTitle;

    // Section of the story
    private String mSection;

    // date of the story
    private String mDate;

    // name of the author of the story
    private String mAuthor;

    // link-url of the story
    private String mLink;

/**
 * Constructs a new {@link Story} object.
 *
 * @param title is the title of the story
 * @param section is to which section the story belong
 * @param date is when the story was published
 * @param author is the writer of the story
 * @param link is the website URL to read the complete story
  */

    public Story(String title,String section,String date,String author,String link){
        mTitle = title;
        mSection= section;
        mDate = date;
        mAuthor = author;
        mLink = link;
    }
    /**
     * Returns the title ofthe story
     */
     public String getTitle(){
         return mTitle;
     }
    /**
     * Returns the section of the story
     */
    public String getSection(){
        return mSection;
    }
    /**
     * Returns the date of the story
     */
    public String getDate(){
        return mDate;
    }
    /**
     * Returns the author of the story
     */
    public String getAuthor(){
        return mAuthor;
    }
    /**
     * Returns the link to website of the story
     */
    public String getLink(){
        return mLink;
    }
}
