package Project1;

import java.util.*;
import java.io.*;

public class ProductDirectory implements Serializable{
    private List<Product> directory = new LinkedList<Product>();
    private static ProductDirectory pDirectory ;

    private ProductDirectory(){}
    public static ProductDirectory instance(){
        if(pDirectory == null){
            return (pDirectory= new ProductDirectory());
        }
        return pDirectory;
    }

    public boolean insertProduct(Product p){
        this.directory.add(p);
        return true;
    }
    public Iterator getProducts(){
        return directory.iterator();
    }
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
        return directory.toString();
    }


}