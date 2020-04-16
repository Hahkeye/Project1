//package project1;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.io.*;
public class AdminState extends State implements ActionListener{
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Warehouse warehouse;
    private WarehouseContext context;
    private JPanel panel;
    private static AdminState instance;
    // private static final int EXIT = 0;
    // private static final int ADDPRODUCT=1;
    // private static final int ADDSUPPIIER=2;
    // private static final int SUPPLIERLIST=3;
    // private static final int SUPPLIERSFORPRODUCT=4;
    // private static final int PRODUCTSFORSUPPLIERS=5;
    // private static final int ADDSUPPLIERTOPRODUCT=6;
    // private static final int MIMIC=7;
    private AbstractButton logoutButton, addPButton, addSButton, supplierButton, sandpButton, pandsButton, addstopButton, mimicButton;
    
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
    // public void userMenu(){
    //     String userID = getResponse("Please input the user id: ");
    // }
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
    public void addSupplier(){
        do{
            String name = getResponse("Enter Supplier name:");
            warehouse.addSupplier(name);
            if(!tOrf("Do you want to add another supplier? y/n?")){
                break;
            }
        }while(true);
    }
    public void supplierList(){
        Iterator it = warehouse.getSuppliers();
        while(it.hasNext()){
            Supplier tempS = (Supplier) it.next();
            System.out.println(tempS);
        }
    }
    public void sandp(){
        warehouse.getSuppliersAndProducts();
    }
    public void mimic(){
        WarehouseContext.instance().changeState(2);
    }
    public void modify(){
        do{
            int pid = Integer.valueOf(getResponse("Enter ProductID: "));
            int sid = Integer.valueOf(getResponse("Enter Supplier ID:"));
            Product tempP=warehouse.getProduct(pid);
            Supplier tempS=warehouse.getSupplier(sid);
            if(tempP!=null && tempS!=null){
                tempP.setSupplier(tempS);
            }   
            if(!tOrf("Do you want to change another product? y/n?")){
                break;
            }
        }while(true);
    }


    public void logout(){
        if(WarehouseContext.instance().getLogin()==WarehouseContext.isAdmin){
            WarehouseContext.instance().changeState(0);
        }else{
            WarehouseContext.instance().changeState(3);
        }
        //WarehouseContext.instance().getFrame().removeAll();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.logoutButton)){
            panel.setVisible(false);
            logout();
        }

    }


    public void run(){
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        addPButton = new JButton("Add Product");
        addSButton = new JButton("Add Supplier");
        supplierButton = new JButton("Supplier list");
        sandpButton = new JButton("Suppliers and products");
        addstopButton = new JButton("Add supplier to product");
        mimicButton = new JButton("Login as clerk");
        logoutButton = new JButton("Logout");
        panel.add(addPButton);
        panel.add(addSButton);
        panel.add(supplierButton);
        panel.add(sandpButton);
        panel.add(addstopButton);
        panel.add(mimicButton);
        panel.add(logoutButton);
        addPButton.addActionListener(this);
        addSButton.addActionListener(this);
        supplierButton.addActionListener(this);
        sandpButton.addActionListener(this);
        addstopButton.addActionListener(this);
        mimicButton.addActionListener(this);
        logoutButton.addActionListener(this);
        panel.setVisible(true);
        panel.paint(panel.getGraphics());
        WarehouseContext.instance().getFrame().add(panel);
        WarehouseContext.instance().getFrame().validate();
    }
}

//private AbstractButton exitButton, addPButton, addSButton, supplierButton, sandpButton, pandsButton, addstopButton, mimicButton;