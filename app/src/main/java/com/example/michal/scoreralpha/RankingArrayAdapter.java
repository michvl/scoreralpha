package com.example.michal.scoreralpha;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Michal on 14.12.2017.
 */

public class RankingArrayAdapter extends ArrayAdapter<Player> {
    private Activity context;
    private List<Player> players;
    public RankingArrayAdapter(Activity context, List<Player> players) {
        super(context, R.layout.custom_list_item, players);
        this.context = context;
        this.players = players;
    }

    static class ViewHolder{
        public TextView tvNumber;
        public TextView tvName;
        public TextView tvPoints;
        public TextView tvWins;
        public TextView tvLoses;
        public TextView tvForm;
        public TextView tvMatches;
        public TextView tvGoalsShot;
        public TextView tvGoalsLost;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder;
        View rowView = convertView;
        if(rowView == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            rowView = layoutInflater.inflate(R.layout.custom_list_item, null, true);
            viewHolder = new ViewHolder();
            viewHolder.tvNumber = (TextView) rowView.findViewById(R.id.tvNumber);
            viewHolder.tvName = (TextView) rowView.findViewById(R.id.tvName);
            viewHolder.tvPoints = (TextView) rowView.findViewById(R.id.tvPoints);
            rowView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder) rowView.getTag();
        }
        Player player = players.get(position);
        viewHolder.tvName.setText(player.name);
        //viewHolder.tvNumber.setText((int)player.id);

        return rowView;
    }

}
