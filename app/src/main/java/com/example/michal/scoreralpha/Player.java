package com.example.michal.scoreralpha;

/**
 * Created by Michal on 09.12.2017.
 */

public class Player {
    public long id;
    public String name;
    public int wins;
    public int loses;
    public int points;
    public int form;
    public int matches;
    public int goalsShot;
    public int goalsLost;

    public Player(long id, String name){
        this.name = name;
        this.id = id;
        this.loses=0;
        this.wins=0;
        this.points=0;
        this.form=0;
        this.matches=0;
    }



}
