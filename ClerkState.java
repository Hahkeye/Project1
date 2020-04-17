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
    private JTextArea text;
    private JScrollPane scroll;
    private static ClerkState instance;
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

    public boolean tOrf(String query,String title){
        String answer = JOptionPane.showInputDialog(panel, query, title, 3);
        if(answer.charAt(0)=='y'||answer.charAt(0)=='Y'){
            return true;
        }
        return false;
    }
    public void add(){
        do{
            String name = JOptionPane.showInputDialog(panel, "Enter name: ");
            warehouse.addClient(name);
            if(!tOrf("Do you want to add another Client? y/n?","Multi-Client prompt")){
                break;
            }
        }while(true);
    }
    public void pandp(){
        String tempS = "";
        Iterator it = warehouse.getProducts();
        while(it.hasNext()){
            Product temp = (Product) it.next();
            tempS+=(temp+"\n"); 
        }
        text.setText(tempS);
    }
    public void clients(){
        String tempS = "";
        Iterator it = warehouse.getClients();
        while(it.hasNext()){
            Client temp = (Client) it.next();
            tempS+=(temp+"\n"); 
        }
        text.setText(tempS);
    }
    public void outstanding(){
        if(warehouse.getClientsWithBalance().length()>0){
            text.setText(warehouse.getClientsWithBalance());
        }
        text.setText("No outstanding clients");
    }
    public void mimic(){
        int cid = Integer.valueOf(JOptionPane.showInputDialog(panel, "Enter Client ID", "Client Login",3));
        if(warehouse.getClient(cid)!=null){
            panel.setVisible(false);;
            WarehouseContext.instance().setUser(cid);
            WarehouseContext.instance().changeState(1);

        }else{
            System.out.println("Bad ID");
        }
    }
    public void shipment(){
        do{
            int pid = Integer.valueOf(JOptionPane.showInputDialog(panel, "What product ID is being recieved?", "Recieve Shipment", 3));
            int count = Integer.valueOf(JOptionPane.showInputDialog(panel, "How man are being recieved?", "Recieve Shipment",3));
            if(warehouse.checkWaitList(pid,count)){
                if(tOrf("Do you want to fill the waitlist?y/n?","Waitlist prompt")){
                    warehouse.recieve(pid, count);
                }else{
                    warehouse.adjustProduct(pid, count);
                }
            }else{
                warehouse.adjustProduct(pid, count);
            }
        }while(tOrf("Would you like to enter another product?","Multi-product prompt"));
    }
    public void payment(){
        int cid = Integer.valueOf(JOptionPane.showInputDialog(panel, "Enter Client ID", "Payment",3));
        Double price = Double.valueOf(JOptionPane.showInputDialog(panel, "Enter payment amount.", "Payment",3));
        JOptionPane.showMessageDialog(panel, "the payment was "+warehouse.pay(cid, price));
        //System.out.println("the payment was "+warehouse.pay(cid, price));
    }
    public void processOrder(){
        int cid = Integer.valueOf(JOptionPane.showInputDialog(panel, "Enter Client ID", "Payment",3));
        if(warehouse.processOrder(cid)){
            JOptionPane.showMessageDialog(panel,"Order processed succesfully");
        }else{
            JOptionPane.showMessageDialog(panel,"Order failed to process");
        }
        
    }
    public void waitlist(){
        int pid = Integer.valueOf(JOptionPane.showInputDialog(panel, "What is the product ID?", "Waitlist", 3));
        int waiting = warehouse.waiting(pid);
        if(waiting!=0){
            text.setText("Are waiting on "+waiting);
        }else{
            text.setText("Not waiting on any");
        }
    }
    public void logout(){
        if(WarehouseContext.instance().getLogin()==WarehouseContext.isAdmin){
            WarehouseContext.instance().changeState(3);
        }else{
            WarehouseContext.instance().changeState(0);
        }      
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.logoutButton)){
            panel.setVisible(false);
            logout();
        }
        else if(e.getSource().equals(this.addButton)){
            add();
        }
        else if(e.getSource().equals(this.pandpButton)){
            pandp();
        }
        else if(e.getSource().equals(this.clientButton)){
            clients();
        }
        else if(e.getSource().equals(this.outstandingButton)){
            outstanding();
        }
        else if(e.getSource().equals(this.mimicButton)){
            mimic();
        }
        else if(e.getSource().equals(this.waitlistButton)){
            waitlist();
        }
        else if(e.getSource().equals(this.shipmentButton)){
            shipment();
        }
        else if(e.getSource().equals(this.processButton)){
            processOrder();
        }
        else if(e.getSource().equals(this.paymentButton)){
            payment();
        }

    }


    public void run(){
        panel = new JPanel();
        text = new JTextArea();
        scroll = new JScrollPane(text);
        panel.add(scroll);
        text.setEditable(false);
        text.setLineWrap(true);
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
