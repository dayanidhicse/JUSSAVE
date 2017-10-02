package com.dayanidhid.jussave.Adapters;

/**
 * Created by HP on 10/2/2017.
 */

public class NotesAdapter {
    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    String Type;
    public NotesAdapter(String ti,String type)
    {
        this.title=ti;
        this.Type=type;
    }
}
