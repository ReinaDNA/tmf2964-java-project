// Class to display info to the user
// Created by:Thiveya

public class Info implements InterfaceInfo{
    public Info(){

    }   

    public void displayInfo(){
        System.Out.println("Welcome To Math Quiz !");
    }
    public void displayInfo(int page){
        System.Out.println("First ODE" + page + "/10");
    }
    public void displayInfo(double score){
        System.Out.println("Score" + score + "%");
        if score (>=80) System.Out.println("Spectacular !");
        else if score (>=60) System.Out.pritnln("That's Good !");
        else if score (>=40) System.Out.println("Great Effort !");
        else if score (>=20) System.Out.println("Lets's elavate next time !");
        else System.Out.println("Don't Give Up !")
    }
}
