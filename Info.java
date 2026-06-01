// Class to display info to the user
// Created by:Thiveya

public class Info implements InterfaceInfo{
    public Info(){

    }   

    public void displayInfo(){
        System.Out.pritnln("Welcome To Math Quiz !");
    }
    public void displayInfo(int page){
        System.Out.pritnln("First ODE" + page + "/10");
    }
    public void displayInfo(double score){
        System.Out.pritnln("Score" + score + "%");
        if score (>=80) System.Out.pritnln("Spectacular !");
        else if score (>=60) System.Out.pritnln("That's Good !");
        else if score (>=40) System.Out.pritnln("Great Effort !");
        else if score (>=20) System.Out.pritnln("Lets's elavate next time !");
        else System.Out.println("Don't Give Up !")
    }
}
