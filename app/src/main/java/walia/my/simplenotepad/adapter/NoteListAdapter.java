/* Copyright 2014 Braden Farmer
 * Copyright 2015 Sean93Park
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package walia.my.simplenotepad.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import walia.my.simplenotepad.R;
import walia.my.simplenotepad.util.NoteListItem;

import java.util.ArrayList;

import androidx.core.content.ContextCompat;

public class NoteListAdapter extends ArrayAdapter<NoteListItem> {
    public NoteListAdapter(Context context, ArrayList<NoteListItem> notes) {
        super(context, R.layout.row_layout, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        NoteListItem item = getItem(position);
        String note = item.getNote();

        // Check if an existing view is being reused, otherwise inflate the view
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_layout, parent, false);

        // Lookup view for data population
        TextView noteTitle = convertView.findViewById(R.id.noteTitle);

        // Populate the data into the template view using the data object
        noteTitle.setText(note);

        // Apply theme
        SharedPreferences pref = getContext().getSharedPreferences(getContext().getPackageName() + "_preferences", Context.MODE_PRIVATE);
        String theme = pref.getString("theme", "light-sans");

        if(theme.contains("light"))
            noteTitle.setTextColor(ContextCompat.getColor(getContext(), R.color.text_color_primary));

        if(theme.contains("dark"))
            noteTitle.setTextColor(ContextCompat.getColor(getContext(), R.color.text_color_primary_dark));

        if(theme.contains("sans"))
            noteTitle.setTypeface(Typeface.SANS_SERIF);

        if(theme.contains("serif"))
            noteTitle.setTypeface(Typeface.SERIF);

        if(theme.contains("monospace"))
            noteTitle.setTypeface(Typeface.MONOSPACE);

        switch(pref.getString("font_size", "normal")) {
            case "smallest":
                noteTitle.setTextSize(12);
                break;
            case "small":
                noteTitle.setTextSize(14);
                break;
            case "normal":
                noteTitle.setTextSize(16);
                break;
            case "large":
                noteTitle.setTextSize(18);
                break;
            case "largest":
                noteTitle.setTextSize(20);
                break;
        }

        // Return the completed view to render on screen
        return convertView;
    }
}