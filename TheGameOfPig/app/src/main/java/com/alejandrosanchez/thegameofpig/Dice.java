package com.alejandrosanchez.thegameofpig;

import java.util.HashMap;
/**
 * Class Dice
 *
 * This class is used to create a dice object
 *
 * @version 1.0
 * @since 2021-03-01
 * @author Alejandro Sánchez PInto
 * @GitHub <a href="https://github.com/Minealex2001/PMDM-DAM2">...</a>
 */
public class Dice {
    /**
     * Attributes
     */
    private final HashMap<String, Integer> players = new HashMap<>();
    private boolean hold = false;
    private static int scoreRound = 0;

/**
     * Constructor
     */
    public Dice() {
        players.put("p1", 0);
        players.put("p2", 0);
    }

    //Methods

    /**
     * This method is used to roll the dice
     *
     * @return int
     */
    public static int roll() {
        return (int) (Math.random() * 6) + 1;
    }

    /**
     * This method is used to play the game
     */
    public void play() {
        int number = 0;

        if (!((number = roll()) == 1)) {
            scoreRound += number;
        } else {
            this.setHold(this.invertedHold());
            scoreRound = 0;
        }

    }
    /**
     * This method is used to check if the player has won
     *
     * @return String
     */
    public String winner(){
        String winner = "";
        if (players.get("p1") >= 100) {
            winner = "P1";
        } else if (players.get("p2") >= 100) {
            winner = "P2";
        }
        return winner;
    }
    /**
     * This method is used reset the game
     */
    public void deleteGame(){
        players.put("p1", 0);
        players.put("p2", 0);
        scoreRound = 0;
    }
    /**
     * This method is used to get the score of the round
     *
     * @return int
     */
    public static int getScoreRound() {
        return scoreRound;
    }
    /**
     * This method is used to get the points
     *
     * @return int
     */
    public int getPoints(String player){
        Integer points = players.get(player);
        return points != null ? points : 0;
    }
    /**
     * This method is used to sum the points
     */
    public void addDice(String player, int roll){
        players.merge(player, roll, Integer::sum);
    }
    /**
     * This method is used to check if the player has selected hold
     */
    public void setHold(boolean hold) {
        this.hold = hold;
    }

    public void setScoreRound(int currentRound) {
        scoreRound = currentRound;
    }

    /**
     * This method is used to change the hold value
     *
     * @return boolean
     */
    public boolean invertedHold() {
        return !hold;
    }

}