package Project1;

import java.util.*;
import java.io.*;

public class ProductDirectory implements Serializable{
    private List<Product> directory = new LinkedList<Product>();
    private static ProductDirectory productDirectory ;

    private ProductDirectory(){}
    public static ProductDirectory instance(){
        if(productDirectory == null){
            return (productDirectory= new ProductDirectory());
        }
        return productDirectory;
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
            output.writeObject(productDirectory);
        } catch(IOException e){
            System.out.println(e);
        }
        
    }
    private void readObject(java.io.ObjectInputStream input){
        try{
            if(productDirectory!=null){
                return;
            }else{
                input.defaultReadObject();
                if(productDirectory == null){
                    productDirectory= (ProductDirectory) input.readObject();
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