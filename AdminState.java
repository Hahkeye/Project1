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
    private JTextArea text;
    private JScrollPane scroll;
    private static AdminState instance;
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
    public boolean tOrf(String query,String title){
        String answer = JOptionPane.showInputDialog(panel, query, title, 3);
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
            String name = JOptionPane.showInputDialog(panel, "Enter the name of the product.", "New Product", 3);
            int count = Integer.valueOf(JOptionPane.showInputDialog(panel, "Enter the stock count.", "New Product", 3));
            double price = Double.valueOf(JOptionPane.showInputDialog(panel, "Enter the price.", "New Product", 3));
            int sid = Integer.valueOf(JOptionPane.showInputDialog(panel, "Enter the supplier id.", "New Product", 3));
            warehouse.addProduct(name, count, price,sid);
            if(!tOrf("Do you want to add another product? y/n?","Multiple Products")){
                break;
            }
        }while(true);
    }
    public void addSupplier(){
        do{
            String name = JOptionPane.showInputDialog(panel, "Enter the name of the supplier.", "New Supplier", 3);
            warehouse.addSupplier(name);
            if(!tOrf("Do you want to add another supplier? y/n?","Multiple Suppliers")){
                break;
            }
        }while(true);
    }
    public void supplierList(){
        Iterator it = warehouse.getSuppliers();
        String temp = "";
        while(it.hasNext()){
            Supplier tempS = (Supplier) it.next();
            temp+=("Name: "+tempS.getName()+"\nID: "+tempS.getID()+"\n\n");
        }
        text.setText(temp);
    }
    public void sandp(){
        text.setText(warehouse.getSuppliersAndProducts());
    }
    public void mimic(){
        panel.setVisible(false);;
        WarehouseContext.instance().changeState(2);
    }
    public void modify(){
        do{
            int pid = Integer.valueOf(JOptionPane.showInputDialog(panel, "What is the product ID?", "Supplier Change", 3));
            int sid = Integer.valueOf(JOptionPane.showInputDialog(panel, "What is the supplier ID?", "Supplier Change", 3));
            Product tempP=warehouse.getProduct(pid);
            Supplier tempS=warehouse.getSupplier(sid);
            if(tempP!=null && tempS!=null){
                tempP.setSupplier(tempS);
            }   
            if(!tOrf("Do you want to change another product? y/n?","Multiple modifys")){
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
        else if(e.getSource().equals(this.addPButton)){
            addProduct();
        }
        else if(e.getSource().equals(this.addSButton)){
            addSupplier();
        }
        else if(e.getSource().equals(this.supplierButton)){
            supplierList();
        }
        else if(e.getSource().equals(this.addstopButton)){
            modify();
        }
        else if(e.getSource().equals(this.sandpButton)){
            sandp();
        }
        else if(e.getSource().equals(this.mimicButton)){
            mimic();
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