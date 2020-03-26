import java.io.InputStreamReader;

import project1.Warehouse;

public class WarehouseContext{
    private int state;
    private static Warehouse warehouse;
    private static WarehouseContext context;
    private int currentUser;
    private String userID;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final int isClerk=0;
    public static final int isUser=1;
    private State[] states;
    private int[][] nState;

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
    public void setUser(String uID){this.userID=uID;}
    public int getLogin(){return this.currentUser;}
    public String getUser(){return this.userID;}

    private WarehouseContext(){
        if(tOrf(getResponse("Use saved data? y/n?"))){
            retrieve();
        }else{
            warehouse = Warehouse.instance();
        }
        states = new State[3];
        states[0] = 
    }
    public static WarehouseContext instance(){
        if(context == null){
            context = new WarehouseContext();
        }
        return context;
    }
    public void changeState(int transition){
        state = nState[state][transition];
        if(state == -2){
            System.out.println("Something broke");

        }
        if(state == -1){
            t
        }

    }
    private void terminate(){
        if(tOrf("Would you like to save the data?")){
            if(warehouse.save()){
                System.out.println("Saved to library");
            }else{
                System.out.println("broke");
            }
        }
        System.out.println("Exiting");
        System.exit(0);
    }

}