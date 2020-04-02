import java.util.*;

import jdk.internal.jline.internal.InputStreamReader;

import java.text.*;
import java.io.*;
public class UserState extends State{
    private static UserState userState;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Warehouse warehouse;
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

    private UserState(){
        warehouse.instance();
    }

    public static UserState instance(){
        if(userState == null){
            return userState = new UserState();
        }
        return userState;
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
}