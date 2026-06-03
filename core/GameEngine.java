// Class for the gamification elements
// Created by: Seng Zhi Jie (106256)
package core;

import java.util.Timer;
import java.util.TimerTask;

import interfaces.InterfaceGameEngine;

public class GameEngine implements InterfaceGameEngine{
    int healthBar = 5; // Each player has an initial health of 5

    // Timer related methods
    Timer timer = new Timer();
    int seconds = 60; // Each question is given 60 seconds
    TimerTask task = new TimerTask() {
    @Override
    public void run(){
        System.out.println(seconds);
        seconds--; // Countdown
        // When timer is up, stop the timer.
        if(seconds <= 0){
            System.out.println("Time's up!");
            timer.cancel();
            }
        }
    };

    public void startTimer(){
        timer.schedule(task, 1000, 1000);
    }

    public void stopTimer(){
        timer.cancel();
    }
    
    public int getElaspedTime(){
        return 60 - seconds;
    }

    public void resetTimer(){
        seconds = 60;
    }

    // Health bar related methods
    public void takeDamage(int amount){
        this.healthBar -= amount;
    }

    public void heal(int amount){
        this.healthBar += amount;
    }

    public int getCurrentHealth(){
        return this.healthBar;
    }

    public boolean isAlive(){
        if(this.healthBar >= 0){
            return true;
        }else{
            return false;
        }
    }
}