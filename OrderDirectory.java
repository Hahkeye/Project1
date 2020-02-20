package project1;

import java.util.*;
import java.io.*;

public class OrderDirectory implements Serializable{
    private static final long serialVersionUID = 1L;
    private List directory = new LinkedList();
    private static OrderDirectory oDirectory;

    private OrderDirectory(){}

    public static OrderDirectory instance(){
        if(oDirectory == null){
            return (oDirectory= new OrderDirectory());
        }
        return oDirectory;
    }

    public Order contains(int oID){
        Iterator it = directory.iterator();
        while(it.hasNext()){
            Order temp = (Order)it.next();
            if(temp.getID()==oID){
                return temp;
            }
        }
        return null;
    }
    public Order getOrder(int oID){
        if(contains(oID)){
            Iterator it = directory.iterator();
            while(it.hasNext()){
                Order temp = (Order)it.next();
                if(temp.getID()==oID){
                    return temp;
                }
            }
        }
        return null;
    }
    public boolean insert(Order o){
        this.directory.add(o);
        return true;
    }
    public boolean remove(Order o){
        this.directory.remove(o);
        return true;
    }
    public Iterator getOrders(){
        return directory.iterator();
    }

    public int getCount(){return this.directory.size();}
    private void writeObject(java.io.ObjectOutputStream output){
        try{
            output.defaultWriteObject();
            output.writeObject(oDirectory);
        } catch(IOException e){
            System.out.println(e);
        }
        
    }
    private void readObject(java.io.ObjectInputStream input){
        try{
            if(oDirectory!=null){
                return;
            }else{
                input.defaultReadObject();
                if(oDirectory == null){
                    oDirectory= (OrderDirectory) input.readObject();
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