// Class to display info to the user
// Created by:

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
    }
}
