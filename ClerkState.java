import java.util.*;

import project1.Warehouse;

import java.text.*;
import java.io.*;
public class ClerkState extends State{
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Warehouse warehouse;
    private WarehouseContext context;
    private static ClerkState instance;
    private static final int EXIT = 0;
    private static final int COMMAND1=1;
    private static final int COMMAND2=2;
    private static final int COMMAND3=3;
    private static final int COMMAND4=4;
    private static final int COMMAND5=5;
    private static final int COMMAND6=6;
    private static final int COMMAND7=7;
    private static final int COMMAND8=8;
    private static final int COMMAND9=9;
    private static final int USERSWAP=10;
    
    private ClerkState(){
        super();
        warehouse = Warehouse.instance();
    }
    public static ClerkState instance(){
        if(instance == null){
            instance = new ClerkState();
        }
        return instance;
    }

    public static String getResponse(String query){
        do{
            try {
                //System.out.println(query);
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
    public static boolean tOrf(String query){
        String answer = getResponse(query);
        if(answer.charAt(0)=='y'||answer.charAt(0)=='Y'){
            return true;
        }
        return false;
    }
    public int getNumber(String prompt) {
        do {
          try {
            String item = getResponse(prompt);
            Integer num = Integer.valueOf(item);
            return num.intValue();
          } catch (NumberFormatException e) {
            System.out.println("Please input a number ");
          }
        } while (true);
      }
    public int getCommand(){
        do{
            try{
                int value = Integer.parseInt(getResponse("Enter Command: "));
                if(value <= EXIT && value >= CLOGIN){
                    return value;
                }
            }catch(NumberFormatException e){
                    System.out.println(e);
            }
        }while(true);
    }
    public void userMenu(){
        String userID = getResponse("Please input the user id: ");
        if(WarehouseContext.instance().s)
        //check if they exists
    }
    public void logout(){
        WarehouseContext.instance().changeState(0);
    }
    public void menu(){
        System.out.println("Clerk Menu:");
        System.out.println(EXIT + " to Exit.");
        System.out.println(COMMAND1 + " for command 1.");
        System.out.println(COMMAND2 + " for command 2.");
        System.out.println(COMMAND3 + " for command 3.");
        System.out.println(COMMAND4 + " for command 4.");
        System.out.println(COMMAND5 + " for command 5.");
        System.out.println(COMMAND6 + " for command 6.");
        System.out.println(COMMAND7 + " for command 7.");
        System.out.println(COMMAND8 + " for command 8.");
        
    }
    public void process(){
        int command;
        menu();
        while((command = getCommand())!= EXIT){
            switch(command){
                case COMMAND1:

                break;
                case COMMAND2:
                    
                break;
                case COMMAND3:
                    
                break;
                case COMMAND4:
                    
                break;
                case COMMAND5:
                    
                break;
                case COMMAND6:
                    
                break;
                case COMMAND7:
                    
                break;
                case COMMAND8:
                    
                break;

            }
        }
    }


    public void run(){
        process();
    }
}