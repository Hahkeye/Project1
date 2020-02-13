package project1;

import java.util.*;
import java.io.*;

public class ClientDirectory implements Serializable{
    private static final long serialVersionUID = 1L;
    private List directory = new LinkedList();
    private static ClientDirectory cDirectory;

    private ClientDirectory(){}

    public static ClientDirectory instance(){
        if(cDirectory == null){
            return (cDirectory= new ClientDirectory());
        }
        return cDirectory;
    }

    public boolean insert(Client c){
        this.directory.add(c);
        return true;
    }
    public boolean remove(Client c){
        this.directory.remove(c);
        return true;
    }
    public Iterator getClients(){
        return directory.iterator();
    }

    public int getCount(){return this.directory.size();}
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
            System.out.println("problem in client direcoty "+ e);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public String toString(){
        return directory.toString();
    }


}