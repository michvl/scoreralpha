package com.example.michal.scoreralpha;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView list;
    private Button btnAddPlayer;
    private Button btnAddMatch;
    private ListView lvRanking;
    private String[] languages;
    private String[] helloPhrases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddPlayer = (Button) findViewById(R.id.addPlayer);
        btnAddMatch = (Button) findViewById(R.id.addMatch);
        lvRanking = (ListView) findViewById(R.id.lvRanking);
        initResources();
        initLanguagesListView();


        Spinner spinner = (Spinner) findViewById(R.id.scroll);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

    }

    private void initResources(){
        Resources res = getResources();
        languages = res.getStringArray(R.array.languages);
        helloPhrases = res.getStringArray(R.array.hello_phrases);
    }

    private void initLanguagesListView(){
        RankingArrayAdapter adapter = new RankingArrayAdapter(this, languages);
        lvRanking.setAdapter(adapter);

        lvRanking.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int pos,   long id) {
                Toast.makeText(getApplicationContext(),
                        helloPhrases[pos],
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void initBtnOnClickPlayer(){
//        btnAddPlayer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                )
//            }
//        });
//    }

}
