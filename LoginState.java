import java.util.*;

import project1.Warehouse;

import java.io.*;

public class LoginState extends State{
    public static final int CLOGIN = 0;
    private static final int ULOGIN =1;
    private static final int ALOGIN = 2;
    private static final int EXIT = 3;
    private BufferedReader reader = new BuffreredReader(new InputStreamReader(System.in));
    private WarehouseContext context;
    private static LoginState instance;
    private LoginState(){
        super();
    }

    public  static LoginState instance(){
        if(instance == null){
            instance = new LoginState();
        }
        return instance;
    }
    public String getResponse(String query){
        do{
            try {
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
                if(value <= EXIT && value >= CLOGIN){
                    return value;
                }
            }catch(NumberFormatException e){
                    System.out.println(e);
            }
        }while(true);
    }
    private void clerk(){
        WarehouseContext.instance().setLogin(WarehouseContext.CLOGIN);
        WarehouseContext.instance().changeState(0);
    }
    private void user(){
        String userID =  getResponse("Enter the userID: ");
        if(Warehouse.instance().getClient(cID)!=null){
            WarehouseContext.instance().setLogin(WarehouseContext.UULOGIN);
            WarehouseContext.instance().setUser(userID);
            WarehouseContext.instance().changeState(1);
        }else{
            System.out.println("Bad uid");
        }
    }
    private void admin(){
        
    }

    public void process(){
        int command;
        System.out.println("\tLogin menu"+
        "1: User login\n"+"2: Clerk\n3: Admin");
        while((command= getCommand())!= EXIT){
            switch(command){
                case CLOGIN:
                    clerk();
                break;
                case ULOGIN:
                    user();
                break;
                default:
                    System.out.println("broke;");
            }
        }
    }

    public void run(){
        process();
    }
}
