//package project1;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.io.*;
public class ClerkState extends State implements ActionListener{
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Warehouse warehouse;
    private WarehouseContext context;
    private static JPanel panel;
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
    private static final int PROCESS=9;
    private AbstractButton logoutButton, addButton, pandpButton, clientButton, outstandingButton, mimicButton, waitlistButton, shipmentButton, paymentButton, processButton;
    
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
    public boolean tOrf(String query,String title){
        String answer = JOptionPane.showInputDialog(panel, query, title, 3);
        if(answer.charAt(0)=='y'||answer.charAt(0)=='Y'){
            return true;
        }
        return false;
    }

    public void userMenu(){
        int userID = Integer.valueOf(getResponse("Please input the user id: "));
        if(warehouse.getClient(userID)!=null){
            WarehouseContext.instance().changeState(1);
        }
    }
    public void add(){
        do{
            String name = getResponse("Enter name:");
            warehouse.addClient(name);
            if(!tOrf("Do you want to add another Client? y/n?","Multi-Client prompt")){
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
                if(tOrf("Do you want to fill the waitlist?y/n?","Waitlist prompt")){
                    warehouse.recieve(pid, count);
                }else{
                    warehouse.adjustProduct(pid, count);
                }
            }
            if(warehouse.existsP(pid)){
                warehouse.adjustProduct(pid, count);
            }
        }while(tOrf("Would you like to enter another product?","Multi-product prompt"));
    }
    public void payment(){
        int cid = Integer.valueOf(getResponse("Enter client id:"));
        Double price = Double.valueOf(getResponse("Enter amount being payed: "));
        System.out.println("the payment was "+warehouse.pay(cid, price));
    }
    public void processOrder(){
        int cid = Integer.valueOf(getResponse("Enter client ID: "));
        System.out.println(warehouse.processOrder(cid));
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
        if(WarehouseContext.instance().getLogin()==WarehouseContext.isAdmin){
            WarehouseContext.instance().changeState(3);
        }else{
            WarehouseContext.instance().changeState(0);
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
        addButton = new JButton("Add Client");
        pandpButton = new JButton("Show Products");
        clientButton = new JButton("Show Clients");
        outstandingButton = new JButton("Oustanding Clients");
        mimicButton = new JButton("Become a Client");
        waitlistButton = new JButton("Waitlist for a product");
        shipmentButton =  new JButton("Recive a shipment");
        paymentButton = new JButton("Record a payment");
        processButton = new JButton("Process Order");
        logoutButton = new JButton("Logout");
        panel.add(addButton);
        panel.add(pandpButton);
        panel.add(clientButton);
        panel.add(outstandingButton);
        panel.add(mimicButton);
        panel.add(waitlistButton);
        panel.add(shipmentButton);
        panel.add(paymentButton);
        panel.add(processButton);
        panel.add(logoutButton);
        addButton.addActionListener(this);
        pandpButton.addActionListener(this);
        clientButton.addActionListener(this);
        outstandingButton.addActionListener(this);
        mimicButton.addActionListener(this);
        waitlistButton.addActionListener(this);
        shipmentButton.addActionListener(this);
        paymentButton.addActionListener(this);
        processButton.addActionListener(this);
        logoutButton.addActionListener(this);
        panel.setVisible(true);
        panel.paint(panel.getGraphics());
        WarehouseContext.instance().getFrame().add(panel);
        WarehouseContext.instance().getFrame().validate();
    }
}
