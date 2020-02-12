package Project1;

import java.util.*;
import java.io.*;

public class ClientDirectory implements Serializable{
    private List<Client> directory = new LinkedList<Client>();
    private static ClientDirectory cDirectory;

    private ClientDirectory(){}

    public static ClientDirectory instance(){
        if(cDirectory == null){
            return (cDirectory= new ClientDirectory());
        }
        return cDirectory;
    }

    public boolean insertClient(Client c){
        this.directory.add(c);
        return true;
    }
    public boolean removeClient(Client c){
        this.directory.remove(c);
        return true;
    }
    public Iterator getClients(){
        return directory.iterator();
    }
    private void writeObject(java.io.ObjectOutputStream output){
        try{
            output.defaultWriteObject();
            output.writeObject(cDirectory);
        } catch(IOException e){
            System.out.println(e);
        }
        
    }
    private void readObject(java.io.ObjectInputStream input){
        try{
            if(cDirectory!=null){
                return;
            }else{
                input.defaultReadObject();
                if(cDirectory == null){
                    cDirectory= (ClientDirectory) input.readObject();
                }else{
                    input.readObject();
                }
            }
        }catch(IOException e){
            System.out.println("problem in product direcoty "+ e);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public String toString(){
        return directory.toString();
    }


}