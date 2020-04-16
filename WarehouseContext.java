//package project1;
import java.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.io.*;
public class WarehouseContext{
    private int state;
    private static Warehouse warehouse;
    private static WarehouseContext context;
    private int currentUser;
    private int userID;
    private static JFrame warehouseFrame;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final int isClerk=0;
    public static final int isUser=1;
    public static final int isAdmin=2;
    private State[] states;
    private int[][] nextState;

    public String getResponse(String query){
        do{
            try {
                System.out.println(query);
                String line = reader.readLine();
                StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
                if (tokenizer.hasMoreTokens()) {
                    return tokenizer.nextToken();
                }
            } 
            catch (IOException ioe) {
                System.exit(0);
            }
        } while(true);
    }
    public boolean tOrf(String query,String title){
        String answer = JOptionPane.showInputDialog(warehouseFrame, query, title, 3);
        if(answer.charAt(0)=='y'||answer.charAt(0)=='Y'){
            return true;
        }
        return false;
    }
    private void retrieve() {
        try {
            Warehouse tWarehouse = Warehouse.retrieve();
            if (tWarehouse != null) {
                System.out.println(" The warehouse has been successfully retrieved from the file WarehouseData \n" );
                warehouse = tWarehouse;
            }else{
                System.out.println("File doesnt exist; creating new library" );
                warehouse = Warehouse.instance();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void setLogin(int code){this.currentUser=code;}
    public void setUser(int uID){this.userID=uID;}
    public int getLogin(){return this.currentUser;}
    public int getUser(){return this.userID;}
    public JFrame getFrame(){return this.warehouseFrame;}

    private WarehouseContext(){
        //System.out.println("new construct");
        if (tOrf("Use saved data? y/n?","Saved Data")) {
            //System.out.println("finding save");
            retrieve();
        }else{
            //System.out.println("creating new");
            warehouse = Warehouse.instance();
        }
        states = new State[4];
        states[0] = LoginState.instance();
        states[1] = ClientState.instance();
        states[2] = ClerkState.instance();
        states[3] = AdminState.instance();
        nextState = new int[4][4];
        //Login
        nextState[0][0] = -1;nextState[0][1] = 1;nextState[0][2] = 2;nextState[0][3] = 3;
        //Client
        nextState[1][0] = 0;nextState[1][1] = -1;nextState[1][2] = 2;nextState[1][3] = 3;
        //Clerk
        nextState[2][0] = 0;nextState[2][1] = 1;nextState[2][2] = -1;nextState[2][3] = 3;
        //Admin
        nextState[3][0] = 0;nextState[3][1] = -2;nextState[3][2] = 2;nextState[3][3] = -2;
        state = 0;
        warehouseFrame = new JFrame("Warehouse inventory");
        warehouseFrame.addWindowListener(new WindowAdapter()
        {public void windowClosing(WindowEvent e){System.exit(0);}});
        warehouseFrame.setSize(400,400);
        warehouseFrame.setLocation(400,400);
    }  
    public void changeState(int transition){
        //System.out.println("\nTransition: "+transition+" State: "+state);
        state = nextState[state][transition];
        if(state == -2){
            System.out.println("exiting");
            terminate();

        }
        if(state == -1){
            terminate();
            
        }
        System.out.println("going to state : "+state);
        states[state].run();
    }
    private void terminate(){
        if(tOrf("Would you like to save?", "Save Dialog")){
            if(warehouse.save()){
                System.out.println("Saved to library");
            }else{
                System.out.println("broke");
            }
        }
        System.out.println("Exiting");
        System.exit(0);
    }

    public static WarehouseContext instance(){
        if(context == null){
            //System.out.println("constructorpog");
            context = new WarehouseContext();
            warehouse.addClient("client1");
            warehouse.addClient("client2");
            warehouse.addClient("client3");
            warehouse.addClient("client4");
            warehouse.addClient("client5");
            warehouse.addSupplier("Supplier1");
            warehouse.addSupplier("Supplier2");
            warehouse.addSupplier("Supplier3");
            warehouse.addSupplier("Supplier4");
            warehouse.addSupplier("Supplier5");
            warehouse.addProduct("Product1",4,4.0,1);
            warehouse.addProduct("Product2",7,2.0,2);
            warehouse.addProduct("Product3",9,1.0,3);
            warehouse.addProduct("Product4",8,5.0,4);
            warehouse.addProduct("Product5",1,7.0,1);
            warehouse.addProduct("Product6",1,7.0,2);
            warehouse.addProduct("Product7",1,7.0,3);
            warehouse.addProduct("Product8",1,7.0,4);
        }
        return context;
    }
    
    public void process(){
        //System.out.println("proccessing");
        states[state].run();
    }

    public static void main(String[] args){
        WarehouseContext.instance().process();
        WarehouseContext.instance().getFrame().setVisible(true);
        //states[state].run();
    }

}