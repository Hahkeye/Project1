package project1;

import java.io.*;

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
        Client c1= new Client("jeff",1);
        Order o1 = new Order(c1,10.00);
        if(question("Do you want to load? y/n?")){
            try {
                FileInputStream file1 = new FileInputStream("ProdData");
                // FileInputStream file2 = new FileInputStream("ClientData");
                ObjectInputStream input = new ObjectInputStream(file1);
                input.readObject(); 
                // input = new ObjectInputStream(file2);
                // input.readObject();
                input.close();
            } catch(IOException ioe) {
                ioe.printStackTrace();
                 
            } catch(ClassNotFoundException cnfe) {
                cnfe.printStackTrace();
                 
            }
        }
        ClientDirectory clients= ClientDirectory.instance();
        ProductDirectory products= ProductDirectory.instance();
        System.out.println(products.getProductCount());
        System.out.println(clients.getClientCount());
        // System.out.println(clients.getClientCount());
        products.insertProduct(p1);
        clients.insertClient(c1);
        // clients.insertClient(c1);
        System.out.println(products.getProductCount());
        System.out.println(clients.getClientCount());
        // System.out.println(clients.getClientCount());





        Boolean answer2 = question("Do you want to save? y/n?");
        if(answer2){
            try {
                FileOutputStream file = new FileOutputStream("ProdData");
                // FileOutputStream file2 = new FileOutputStream("ClientData");
                ObjectOutputStream output = new ObjectOutputStream(file);
                // ObjectOutputStream output2 = new ObjectOutputStream(file2);
                output.writeObject(products);
                // output2.writeObject(clients);
                output.close();
            } catch(IOException ioe) {
                ioe.printStackTrace();
            } 
        }
    }
}