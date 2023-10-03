package com.alejandrosanchez.thegameofpig;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Class MainActivity
 *
 * This class is used to create a MainActivity object
 *
 * @version 1.0
 * @since 2021-03-01
 * @author Alejandro Sánchez PInto
 * @GitHub <a href="https://github.com/Minealex2001/PMDM-DAM2">...</a>
 */

public class MainActivity extends AppCompatActivity {

    //Attributes
    private Button hold, newGame, roll;

    private final Dice dice = new Dice();

    private TextView winner;

    /**
     * This method is used to create a MainActivity object
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Views
        winner = findViewById(R.id.player_winner);
        winner.setVisibility(View.INVISIBLE);
        //Buttons
        roll = findViewById(R.id.roll_button);
        newGame = findViewById(R.id.new_game_button);
        hold = findViewById(R.id.hold_button);
        //TextViews
        setDiceRoll();
        resetGame(newGame);
        setGameHold();
        }

        /**
         * This method is used to set the hold button
         */
        private void setGameHold(){
        hold.setOnClickListener(view -> {
            //Check if the hold button is pressed
            if (dice.invertedHold()){
                //Add the score to the player
                dice.addDice("p1", dice.getScoreRound());
                //Set the score to the player
                TextView viewPlayer1 = findViewById(R.id.p1_score);
                viewPlayer1.setText(String.valueOf(dice.getPoints("p1")));
                //Set the actual player
                TextView actualPlayer = findViewById(R.id.actualPlayer);
                actualPlayer.setText(R.string.player2);
            }else {
                //Add the score to the player
                dice.addDice("p2", dice.getScoreRound());
                //Set the score to the player
                TextView viewPlayer2 = findViewById(R.id.p2_score);
                viewPlayer2.setText(String.valueOf(dice.getPoints("p2")));
                //Set the actual player
                TextView actualPlayer = findViewById(R.id.actualPlayer);
                actualPlayer.setText(R.string.player1);
            }
            //Reset the score
            dice.setScoreRound(0);
            //Reload the score
            TextView scoreView = findViewById(R.id.turn_score);
            scoreView.setText(String.valueOf(Dice.getScoreRound()));
            //Check if the player has won
            winnerCheck();
            //Change the hold button
            dice.setHold(dice.invertedHold());
        });
        }

        /**
         * This method is used to reset the game
         */
        private void resetGame(Button newGame){
        newGame.setOnClickListener(view -> {
            //Reset the game
            dice.deleteGame();
            //Reload the score
            TextView viewPlayer1 = findViewById(R.id.p1_score);
            viewPlayer1.setText(String.valueOf(dice.getPoints("p1")));
            TextView viewPlayer2 = findViewById(R.id.p2_score);
            viewPlayer2.setText(String.valueOf(dice.getPoints("p2")));
            //Set winner to invisible
            winner.setVisibility(View.INVISIBLE);
        });
        }
        /**
         * This method is used to set the dice roll
         */
        private void setDiceRoll(){
        this.roll.setOnClickListener(view -> {
            //Roll the dice
            dice.play();
            //Reload the score
            TextView scoreView = findViewById(R.id.turn_score);
            scoreView.setText(String.valueOf(Dice.getScoreRound()));
            //Check if the player has won
            winnerCheck();
        });
        }

        /**
         * This method is used to check if the player has won
         */
        private void winnerCheck(){
            //Check if the player 1 has won
        if (dice.winner().equalsIgnoreCase("P1")){
            //Set the winner to player 1
            winner.setText(R.string.player1_wins);
            winner.setVisibility(View.VISIBLE);
            //Check if the player 2 has won
        }else if(dice.winner().equalsIgnoreCase("P2")){
            //Set the winner to player 2
            winner.setText(R.string.player2_wins);
            winner.setVisibility(View.VISIBLE);
        }
        }
    }
