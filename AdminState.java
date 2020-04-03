//package project1;
import java.util.*;
import java.text.*;
import java.io.*;
public class AdminState extends State{
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Warehouse warehouse;
    private WarehouseContext context;
    private static AdminState instance;
    private static final int EXIT = 0;
    private static final int ADDPRODUCT=1;
    private static final int ADDSUPPIIER=2;
    private static final int SUPPLIERLIST=3;
    private static final int SUPPLIERSFORPRODUCT=4;
    private static final int PRODUCTSFORSUPPLIERS=5;
    private static final int ADDSUPPLIERTOPRODUCT=6;
    private static final int MIMIC=7;
    
    private AdminState(){
        super();
        warehouse = Warehouse.instance();
    }
    public static AdminState instance(){
        if(instance == null){
            instance = new AdminState();
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
    }
    public void logout(){
        WarehouseContext.instance().changeState(0);
    }
    public void addProduct(){
        do{
            String name = getResponse("Enter product name:");
            int count = Integer.valueOf(getResponse("Enter Product Stock count: "));
            double price = Double.valueOf(getResponse("Enter product price: "));
            int sid = Integer.valueOf(getResponse("Enter Supplier ID:"));
            warehouse.addProduct(name, count, price,sid);
            if(!tOrf("Do you want to add another product? y/n?")){
                break;
            }
        }while(true);
    }

    public void menu(){
        System.out.println("Admin Menu:");
        System.out.println(EXIT + " to Exit.");
        System.out.println(ADDPRODUCT + ": Add Product");
        System.out.println(ADDSUPPIIER + ": Add Suplier");
        System.out.println(SUPPLIERLIST + ": Supplier List");
        System.out.println(SUPPLIERSFORPRODUCT + ": Products with assoiated suppliers");
        System.out.println(PRODUCTSFORSUPPLIERS + ": Suppliers with associated products");
        System.out.println(ADDSUPPLIERTOPRODUCT + ": Add supplier to product");
        System.out.println(MIMIC + ": Login as clerk");
    }
    public void process(){
        int command;
        menu();
        while((command = getCommand())!= EXIT){
            switch(command){
                case ADDPRODUCT:

                break;
                case ADDSUPPIIER:
                    
                break;
                case SUPPLIERLIST:
                    
                break;
                case SUPPLIERSFORPRODUCT:
                    
                break;
                case PRODUCTSFORSUPPLIERS:
                    
                break;
                case ADDSUPPLIERTOPRODUCT:
                    
                break;
                case MIMIC:
                    
                break;

            }
        }
    }


    public void run(){
        process();
    }
    
}