//package project1;
import java.util.*;
import java.text.*;
import java.io.*;
public class ClerkState extends State{
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Warehouse warehouse;
    private WarehouseContext context;
    private static ClerkState instance;
    private static final int EXIT = 0;
    private static final int ADD=1;
    private static final int PANDP=2;
    private static final int CLIENTS=3;
    private static final int OUSTANDING=4;
    private static final int MIMIC=5;
    private static final int WAITLISTS=6;

    
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
    public boolean tOrf(String query){
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
                if(value >= EXIT){
                    return value;
                }
            }catch(NumberFormatException e){
                    System.out.println(e);
            }
        }while(true);
    }
    public void userMenu(){
        String userID = getResponse("Please input the user id: ");
        //if(WarehouseContext.instance().s)
        //check if they exists
    }
    public void logout(){
        WarehouseContext.instance().changeState(0);
    }
    public void menu(){
        System.out.println("Clerk Menu:");
        System.out.println(EXIT + " to Exit.");
        System.out.println(ADD + ": Add Client");
        System.out.println(PANDP + ": Show products");
        System.out.println(CLIENTS + ": Show Clients");
        System.out.println(OUSTANDING + ": Oustanding Clients");
        System.out.println(MIMIC + ": Become a Client");
        System.out.println(WAITLISTS + ": Waitlist for a product");
    }
    public void process(){
        int command;
        menu();
        while((command = getCommand())!= EXIT){
            switch(command){
                case ADD:

                break;
                case PANDP:
                    
                break;
                case CLIENTS:
                    
                break;
                case OUSTANDING:
                    
                break;
                case MIMIC:
                    
                break;
                case WAITLISTS:
                    
                break;
            }
        }
    }


    public void run(){
        process();
    }
}