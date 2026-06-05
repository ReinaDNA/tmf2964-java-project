package interfaces;
// Interface for Gamification Element 
// Created by: Seng Zhi Jie (106256)
// Tested by: Mohamad Ikhmal Iskandar bin Mohd Ibrahim  (105016)
public interface InterfaceGameEngine {
    // This interface will hold all elements related to the Game engine
    // Timer methods
    public void startTimer();
    public void stopTimer();
    public int getElaspedTime();
    public void resetTimer();

    // Health System methods
    public void takeDamage(int amount);
    public void heal(int amount);
    public int getCurrentHealth();
    public boolean isAlive();
    public String getHealthString();

}