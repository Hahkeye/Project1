package project1;

import java.util.*;
import java.io.*;

public class SupplierDirectory implements Serializable{
    private static final long serialVersionUID = 1L;
    private List directory = new LinkedList();
    private static SupplierDirectory sDirectory ;

    private SupplierDirectory(){}

    public static SupplierDirectory instance(){
        if(sDirectory == null){
            return (sDirectory= new SupplierDirectory());
        }
        return sDirectory;
    }

    public boolean insert(Supplier s){
        this.directory.add(s);
        return true;
    }
    public Iterator getSuppliers(){
        return directory.iterator();
    }
    public Supplier exists(int sID){
        Iterator it = directory.iterator();
        while(it.hasNext()){
            Supplier temp = (Supplier)it.next();
            if(temp.getID()==sID){
                return temp;
            }
        }
        return null;
    }
    private void writeObject(java.io.ObjectOutputStream output){
        try{
            output.defaultWriteObject();
            output.writeObject(sDirectory);
        } catch(IOException e){
            System.out.println(e);
        }
        
    }
    private void readObject(java.io.ObjectInputStream input){
        try{
            if(sDirectory!=null){
                return;
            }else{
                input.defaultReadObject();
                if(sDirectory == null){
                    sDirectory= (SupplierDirectory) input.readObject();
                }else{
                    input.readObject();
                }
            }
        }catch(IOException e){
            System.out.println("problem in supplier direcoty "+ e);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public String toString(){
        return directory.toString();
    }


}