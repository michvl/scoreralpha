package com.example.michal.scoreralpha;

/**
 * Created by Michal on 09.12.2017.
 */

public class Player {
    public int id;
    public String name;
    public int wins;
    public int loses;
    public int points;
    public int form;
    public int matches;
    public int goalsShot;
    public int goalsLost;

    public Player(String name){
        this.name = name;
        this.id = 1;
        this.loses=0;
        this.wins=0;
        this.points=0;
        this.form=0;
        this.matches=0;
    }



}
