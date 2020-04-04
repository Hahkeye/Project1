//package project1;
import java.util.*;
import java.io.*;

public class LoginState extends State{
    public static final int CLOGIN = 2;
    private static final int ULOGIN = 1;
    private static final int ALOGIN = 3;
    private static final int EXIT = 0;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private WarehouseContext context;
    private static LoginState instance;
    private LoginState(){
        super();
    }

    public static LoginState instance(){
        if(instance == null){
            instance = new LoginState();
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
    private void clerk(){
        WarehouseContext.instance().setLogin(WarehouseContext.isClerk);
        WarehouseContext.instance().changeState(2);
    }
    private void user(){
        int cID =  Integer.valueOf(getResponse("Enter the userID: "));
        if(Warehouse.instance().getClient(cID)!=null){
            WarehouseContext.instance().setLogin(WarehouseContext.isUser);
            WarehouseContext.instance().setUser(cID);
            WarehouseContext.instance().changeState(1);
        }else{
            System.out.println("Bad uid");
        }
    }
    private void admin(){
        System.out.println("admingslected");
        WarehouseContext.instance().setLogin(WarehouseContext.isAdmin);
        WarehouseContext.instance().changeState(3);
    }

    public void process(){
        int command;
        System.out.println("\tLogin menu"+
        "\n1: User login\n"+"2: Clerk\n3: Admin\n0: Exit");
        while((command= getCommand())!= EXIT){
            switch(command){
                case CLOGIN:
                    clerk();
                break;
                case ULOGIN:
                    user();
                break;
                case ALOGIN:
                    admin();
                break;
                default:
                    System.out.println("broke;");
            }
        }
        WarehouseContext.instance().changeState(0);
    }

    public void run(){
        process();
    }
}
