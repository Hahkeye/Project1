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
    private static final int SHIPMENT=7;
    private static final int PAYMENT=8;
    
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
    public void add(){
        do{
            String name = getResponse("Enter name:");
            warehouse.addClient(name);
            if(!tOrf("Do you want to add another Client? y/n?")){
                break;
            }
        }while(true);
    }
    public void pandp(){
        Iterator it = warehouse.getProducts();
        while(it.hasNext()){
            Product temp = (Product) it.next();
            System.out.println(temp); 
        }
    }
    public void clients(){
        Iterator it = warehouse.getClients();
        while(it.hasNext()){
            Client temp = (Client) it.next();
            System.out.println(temp); 
        }
    }
    public void outstanding(){
        warehouse.getClientsWithBalance();
    }
    public void mimic(){
        int cid = Integer.valueOf(getResponse("Enter UserID: "));
        if(warehouse.getClient(cid)!=null){
            WarehouseContext.instance().setUser(cid);
            WarehouseContext.instance().changeState(1);

        }else{
            System.out.println("Bad ID");
        }
    }
    public void shipment(){
        do{
            int pid = Integer.valueOf(getResponse("Enter the product ID: "));
            int count = Integer.valueOf(getResponse("Enter count coming in: "));
            if(warehouse.checkWaitList(pid,count)){
                if(tOrf("Do you want to fill the waitlist?y/n?")){
                    warehouse.recieve(pid, count);
                }else{
                    warehouse.adjustProduct(pid, count);
                }
            }
            if(warehouse.existsP(pid)){
                warehouse.adjustProduct(pid, count);
            }
        }while(tOrf("Would you like to enter another product?"));
    }
    public void payment(){
        int cid = Integer.valueOf(getResponse("Enter client id:"));
        Double price = Double.valueOf(getResponse("Enter amount being payed: "));
        System.out.println("the payment was "+warehouse.pay(cid, price));
    }
    public void waitlist(){
        int pid =  Integer.valueOf(getResponse("Enter product id: "));
        int waiting = warehouse.waiting(pid);
        if(waiting!=0){
            System.out.println("Are waiting on "+waiting);
        }else{
            System.out.println("Not waiting on any");
        }
    }
    public void logout(){
        WarehouseContext.instance().changeState(0);
    }
    public void menu(){
        System.out.println("Clerk Menu:");
        System.out.println(EXIT + " to Exit/Logout.");
        System.out.println(ADD + ": Add Client");
        System.out.println(PANDP + ": Show products");
        System.out.println(CLIENTS + ": Show Clients");
        System.out.println(OUSTANDING + ": Oustanding Clients");
        System.out.println(MIMIC + ": Become a Client");
        System.out.println(WAITLISTS + ": Waitlist for a product");
        System.out.println(SHIPMENT + ": Recive a shipment");
        System.out.println(PAYMENT + ": Record a payment");

    }
    public void process(){
        int command;
        menu();
        while((command = getCommand())!= EXIT){
            switch(command){
                case ADD:
                    add();
                break;
                case PANDP:
                    pandp();
                break;
                case CLIENTS:
                    clients();
                break;
                case OUSTANDING:
                    outstanding();
                break;
                case MIMIC:
                    mimic();
                break;
                case WAITLISTS:
                    waitlist();
                break;
                case SHIPMENT:
                    shipment();
                break;
                case PAYMENT:
                    payment();
                break;
            }
        }
    }


    public void run(){
        process();
    }
}