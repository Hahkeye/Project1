package project1;

import java.util.*;
import java.io.*;

public class ProductDirectory implements Serializable{
    private static final long serialVersionUID = 1L;
    private List directory = new LinkedList();
    private static ProductDirectory pDirectory ;

    private ProductDirectory(){}
    public static ProductDirectory instance(){
        if(pDirectory == null){
            return (pDirectory= new ProductDirectory());
        }
        return pDirectory;
    }
    public Product contains(int pID){
        Iterator it = directory.iterator();
        while(it.hasNext()){
            Product temp = (Product)it.next();
            if(temp.getID()==pID){
                return temp;
            }
        }
        return null;
    }

    public boolean insert(Product p){
        this.directory.add(p);
        return true;
    }
    public boolean remove(int pid){
        if(contains(pid)!=null){
            directory.remove(contains(pid));
            return true;
        }
        return false;
    }
    public Iterator getProducts(){
        return this.directory.iterator();
    }
    public int getCount(){return this.directory.size();}
    
    private void writeObject(java.io.ObjectOutputStream output){
        try{
            output.defaultWriteObject();
            output.writeObject(pDirectory);
        } catch(IOException e){
            System.out.println(e);
        }
        
    }
    private void readObject(java.io.ObjectInputStream input){
        try{
            if(pDirectory!=null){
                return;
            }else{
                input.defaultReadObject();
                if(pDirectory == null){
                    pDirectory= (ProductDirectory) input.readObject();
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
        return this.directory.toString();
    }


}