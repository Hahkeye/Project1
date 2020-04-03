import java.io.InputStreamReader;

import project1.Warehouse;

public class WarehouseContext{
    private int state;
    private static Warehouse warehouse;
    private static WarehouseContext context;
    private int currentUser;
    private int userID;
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
    public void setUser(int uID){this.userID=uID;}
    public int getLogin(){return this.currentUser;}
    public int getUser(){return this.userID;}

    private WarehouseContext(){
        if(tOrf(getResponse("Use saved data? y/n?"))){
            retrieve();
        }else{
            warehouse = Warehouse.instance();
        }
        states = new State[4];
        states[0] = ClerkState.instance();
        states[1] = ClientState.instance();
        states[2] = LoginState.instance();
        states[3] = AdminState.instance();
        nState = new int[4][3];
        nextState[0][0] = 2;nextState[0][1] = 1;nextState[0][2] = -2;
        nextState[1][0] = 2;nextState[1][1] = 0;nextState[1][2] = -2;
        nextState[2][0] = 2;nextState[2][1] = 0;nextState[2][2] = -2;
        nextState[3][0] = 0;nextState[3][1] = 1;nextState[3][2] = -1;
        currentState = 2;
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
            terminate();\
            states[state].run();
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

    public void process(){
        states[state].run();
    }

    public static void main(String[] args){
        WarehouseContext.instance().process();
    }

}