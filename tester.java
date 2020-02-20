package project1;

import java.io.*;
import java.util.*;
import javax.lang.model.element.QualifiedNameable;

public class tester{
    public static boolean question(String question){
        try{
            System.out.println(question);
            BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
            String answer = reader.readLine();
            if(answer.charAt(0)=='y' || answer.charAt(0)=='Y'){
                return true;
            }
            return false;
        }catch(IOException e){
            System.out.println("borke");
        }
        return false;
        
    }
    public static void main(String args[]){
        

        //SupplierDirectory suppiers = SupplierDirectory.instance();
        Product p1 = new Product("test product","test description",34,4,32.32,2234);
        Client c1= new Client("jeff");
        Order o1 = new Order(c1,10.00);
        ClientDirectory clients= ClientDirectory.instance();
        ProductDirectory products= ProductDirectory.instance();
        products.insert(p1);
        clients.insert(c1);
        o1.add(p1, 4);
        c1.addProduct(p1, 2);
        System.out.println(c1.toString( ));
        System.out.println(o1.toString());
        System.out.println(o1.contains(p1));
        o1.remove(p1);
        System.out.println(o1.contains(p1));
        System.out.println(o1.toString());



        Boolean answer2 = question("Do you want to save? y/n?");
       
    }
}