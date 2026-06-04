package core;
// Class for the gamification elements
// Created by: Seng Zhi Jie (106256)

import java.awt.event.ActionListener;
import javax.swing.Timer; 
import interfaces.InterfaceGameEngine;

public class GameEngine implements InterfaceGameEngine {
    private static final int MAX_HEALTH = 5;
    private static final int MAX_TIME = 60;

    private int healthBar = MAX_HEALTH;
    private ActionListener tickAction;

    // Timer related
    private Timer timer;
    private int seconds = MAX_TIME;
    private ActionListener timeUpAction; // To tell the UI when time is up

    public GameEngine() {
        // Swing Timer ticks every 1000ms (1 second)
        timer = new Timer(1000, e -> {
            if(tickAction != null){
                tickAction.actionPerformed(e);
            }
            seconds--;
            if (seconds <= 0) {
                stopTimer();
                // Notify the UI that time is up
                if (timeUpAction != null) {
                    timeUpAction.actionPerformed(e);
                }
            }
        });
    }

    // Allows the UI to update timer every second
    public void setTickAction(ActionListener action){
        this.tickAction = action;
    }

    // Allow the QuizScreen to pass an action for when time runs out
    public void setTimeUpAction(ActionListener action) {
        this.timeUpAction = action;
    }

    public void startTimer() {
        seconds = MAX_TIME; // Always reset time when starting
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }
    
    // Fixed typo from getElaspedTime
    public int getElaspedTime() { 
        return MAX_TIME - seconds;
    }

    public int getRemainingTime() {
        return seconds;
    }

    public void resetTimer() {
        seconds = MAX_TIME;
    }

    // Health bar related methods
    public void takeDamage(int amount) {
        this.healthBar -= amount;
        if (this.healthBar < 0) this.healthBar = 0; // Prevent negative health
    }

    public void heal(int amount) {
        this.healthBar += amount;
        if (this.healthBar > MAX_HEALTH) this.healthBar = MAX_HEALTH; // Prevent overhealing
    }

    public int getCurrentHealth() {
        return this.healthBar;
    }

    public boolean isAlive() {
        return this.healthBar > 0; // Dead at 0
    }
    
    public String getHealthString(){
        String hearts = "";
        for(int i=0; i < this.healthBar; i++){
            hearts += "❤️";
        }
        return hearts;
    }

    public void resetHealth() {
        this.healthBar = MAX_HEALTH;
    }
    
    // Full reset for the whole engine when playing again
    public void resetAll() {
        resetTimer();
        stopTimer();
        resetHealth();
    }
}