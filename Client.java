package Project1;
import java.io.*;

public class Client implements Serializable{
    private int ID;
    private String address;
    private String name;

    public Client(String name,int id){
        this.name=name;
        this.ID=id;
    }

    public int getID(){return this.ID;}
    public String getName(){return this.name;}
    public String getAddress(){return this.address;}
}