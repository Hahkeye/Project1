package project1;
import java.io.*;

public class Client implements Serializable{
    private int ID;
    private String address;
    private String name;
    private double balance;

    public Client(String name,int id){
        this.name=name;
        this.ID=id;
        this.balance=0.0;
    }

    public int getID(){return this.ID;}
    public String getName(){return this.name;}
    public String getAddress(){return this.address;}
    public double getBalance(){return this.balance;}

    public double adjustBalance(double adjustNumber){
        return(this.balance+=adjustNumber);
    }

    public String toString(){
        return this.ID+"|"+this.name+"|$"+this.balance;
    }
}