package com.example.michal.scoreralpha;

/**
 * Created by Michal on 10.12.2017.
 */

public class Match {
    private Player Player1;
    private Player Player2;
    private int ScorePlayer1;
    private int ScorePlayer2;

    public Match(Player player1, Player player2, int scorePlayer1, int scorePlayer2){
        this.Player1 = player1;
        this.Player2 = player2;
        this.ScorePlayer1 = scorePlayer1;
        this.ScorePlayer2 = scorePlayer2;
        if(scorePlayer1>scorePlayer2){
            Player1.wins++;
            Player2.loses++;
        }
        else{
            Player2.wins++;
            Player1.loses++;
        }
        Player1.goalsLost += scorePlayer2;
        Player2.goalsLost += scorePlayer1;
        Player1.goalsShot += scorePlayer1;
        Player2.goalsShot += scorePlayer2;
        Player1.matches++;
        Player2.matches++;
    }

}
