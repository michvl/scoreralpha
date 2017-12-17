package com.example.michal.scoreralpha;

import android.content.res.Resources;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnAddPlayer;
    private Button btnAddMatch;
    private ListView lvRanking;


    private DBAdapter dbAdapter;
    private List<Player> players;
    private Cursor playerCursor;
    private RankingArrayAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvRanking = (ListView) findViewById(R.id.lvRanking);

        initSpinner();
        initPlayerBtn();
        initMatchBtn();
        initListView();

    }



    private void initSpinner(){
        Spinner spinner = (Spinner) findViewById(R.id.scroll);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }


    private void initPlayerBtn(){
        btnAddPlayer = (Button) findViewById(R.id.addPlayer);
        btnAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_addplayer, null);
                final EditText mName = (EditText) mView.findViewById(R.id.etName);
                Button mAdd = (Button) mView.findViewById(R.id.btnAdd);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                mAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!mName.getText().toString().isEmpty()){
                            String playerName = mName.getText().toString();
                            dbAdapter.insertPlayer(playerName);
                            mName.setText("");
                            Toast.makeText(MainActivity.this, "Dodano gracza", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Wpisz nazwę gracza!", Toast.LENGTH_SHORT).show();
                        }
                        updateListViewData();
                    }
                });
            }
        });
    }

    private void initMatchBtn(){




////        btnAddMatch = (Button) findViewById(R.id.addMatch);
////        btnAddMatch.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
////                View mView = getLayoutInflater().inflate(R.layout.dialog_addmatch, null);
////                Button mAdd = (Button) mView.findViewById(R.id.btnAddMatch);
////                mBuilder.setView(mView);
////                final AlertDialog dialog = mBuilder.create();
////                dialog.show();
//
//            }
//        });
    }

    private void initListView(){
        fillListViewData();
    }

    private void fillListViewData(){
        dbAdapter = new DBAdapter(getApplicationContext());
        dbAdapter.open();
        getAllPlayers();
        listAdapter = new RankingArrayAdapter(this, players);
        lvRanking.setAdapter(listAdapter);

    }

    private void getAllPlayers(){
        players = new ArrayList<Player>();
        playerCursor = getAllEntriesFromDb();
        updatePlayerList();

    }
    private Cursor getAllEntriesFromDb() {
        playerCursor = dbAdapter.getAllPlayers();
        if(playerCursor != null) {
            startManagingCursor(playerCursor);
            playerCursor.moveToFirst();
        }
        return playerCursor;
    }

    private void updatePlayerList() {
        if(playerCursor != null && playerCursor.moveToFirst()) {
            do {
                long id = playerCursor.getLong(dbAdapter.ID_COLUMN);
                String name = playerCursor.getString(dbAdapter.NAME_COLUMN);
                players.add(new Player(id, name));
            } while(playerCursor.moveToNext());
        }
    }

    private void updateListViewData(){
        playerCursor.requery();
        players.clear();
        updatePlayerList();
        listAdapter.notifyDataSetChanged();
    }

}

