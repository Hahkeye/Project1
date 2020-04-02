import java.util.*;

import jdk.internal.jline.internal.InputStreamReader;

import java.text.*;
import java.io.*;
public class ClientState extends State{
    private static ClientState clientState;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Warehouse warehouse;
    private static final int EXIT = 0;
    private static final int DETAILS=1;
    private static final int PANDP=2;
    private static final int TRANSACTIONS=3;
    private static final int EDITCART=4;
    private static final int WAITLIST=5;
    private static final int LOGOUT=6;

    private ClientState(){
        warehouse.instance();
    }

    public static ClientState instance(){
        if(clientState == null){
            return clientState = new ClientState());
        }
        return clientState;
    }
    public static String getResponse(String query){
        do{
            try {
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
    public void editCart(){

    }
    public void waiting(){

    }
    public void transactions(){

    }

    public void pandp(){

    }

    public void details(){
        warehouse.getClient(Integer.valueOf(WarehouseContext.instance().getUser())).toString();
    }
    public void menu(){
        System.out.println("Client Menu:");
        System.out.println(EXIT + " to Exit.");
        System.out.println(DETAILS + ": Show Details");
        System.out.println(PANDP + ": Products and Prices");
        System.out.println(TRANSACTIONS + ": Transactions");
        System.out.println(EDITCART + ": Edit Cart");
        System.out.println(WAITLIST + ": Display Waitlist");
        System.out.println(LOGOUT + ": Logout");

    }
    public void logout(){
        //adjust as nessacary
        if(WarehouseContext.instance().getLogin() == WarehouseContext.isClerk){
            WarehouseContext.instance().changeState(1);
        }
        else if(WarehouseContext.instance().getLogin() == WarehouseContext.isUser){
            WarehouseContext.instance().changeState(0);
        }else{
            WarehouseContext.instance().changeState(2);
        }
    }
    public void process(){
        int command;
        menu();
        while((command = getCommand())!= EXIT){
            switch(command){
                case DETAILS:
                    details();
                break;
                case PANDP:
                    pandp();
                break;
                case TRANSACTIONS:
                    transactions();
                break;
                case EDITCART:
                    editCart();
                break;
                case WAITLIST:
                    waiting();
                break;
                case LOGOUT:
                    logout();
                break;

            }
        }
        logout();
    }


    public void run(){
        process();
    }

}