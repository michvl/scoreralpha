package com.example.michal.scoreralpha;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Michal on 14.12.2017.
 */

public class RankingArrayAdapter extends ArrayAdapter<String> {
    private Activity context;
    private String[] languages;
    public RankingArrayAdapter(Activity context, String[] languages){
        super(context, R.layout.custom_list_item, languages);
        this.context = context;
        this.languages = languages;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowView = layoutInflater.inflate(R.layout.custom_list_item, null, true);

        TextView tvName = (TextView) rowView.findViewById(R.id.tvName);
        TextView tvNumber = (TextView) rowView.findViewById(R.id.tvNumber);

        tvNumber.setText(Integer.toString(position));
        tvName.setText(languages[position]);
        return rowView;
    }
}
