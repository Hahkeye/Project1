//package project1;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.io.*;
public class ClientState extends State implements ActionListener{
    private static ClientState clientState;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Warehouse warehouse;
    private JPanel panel;
    private JTextArea text;
    private JScrollPane scroll;
    private static final int EXIT = 0;
    private static final int DETAILS=1;
    private static final int PANDP=2;
    private static final int TRANSACTIONS=3;
    private static final int EDITCART=4;
    private static final int WAITLIST=5;
    private static final int ADD = 6;
    private AbstractButton detailButton, pnpButton, transactionButton, editButton, waitButton, addButton, logoutButton;

    private ClientState(){
        warehouse=warehouse.instance();
    }

    public static ClientState instance(){
        if(clientState == null){
            return clientState = new ClientState();
        }
        return clientState;
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
    public void add(){
        do{
            int cid = WarehouseContext.instance().getUser();
            int pid = Integer.valueOf(JOptionPane.showInputDialog(panel, "Enter product number? ", "Producting Adding", 3));
            int quanity = Integer.valueOf(JOptionPane.showInputDialog(panel, "Enter Desired quanity? ", "Producting Adding", 3));
            if(warehouse.addToOrder(cid,pid,quanity)){
                JOptionPane.showMessageDialog(panel, "Item added to cart.", "Product Adding", 1);
                //System.out.println("Successfully added to shopping cart.");
            }else{
                JOptionPane.showMessageDialog(panel, "Item failed to add to cart.", "Product Adding", 2);
                //System.out.println("Failed to add to shopping cart");
            }
            if(JOptionPane.showConfirmDialog(panel, "Would you like to add another?", "Multiple Items", JOptionPane.YES_NO_OPTION)!=0){
                break;
            }
            // if(!tOrf("Do you want to add another item to shopping cart? y/n?","Multi-item prompt")){
            //     break;
            // }
        }while(true);
    }
    public void editCart(){
        int cid = WarehouseContext.instance().getUser();
        String question = warehouse.displayCart(cid);
        //int size = warehouse.cartSize(cid);
        //int[] options = java.util.stream.IntStream.rangeClosed(0, size).toArray();
        //int item = Integer.valueOf(JOptionPane.showOptionDialog(panel, question, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, options, null,"Edit Cart",0));
        int item = Integer.valueOf(JOptionPane.showInputDialog(panel, question+"\n Which item id would you like to edit?", "Edit Cart", 3));
        int adjust = Integer.valueOf(JOptionPane.showInputDialog(panel, "Adjust quanity by(0 for none):", "Edit Cart",3));
        System.out.println(adjust);
        if(warehouse.editCart(cid, item, adjust)){
            JOptionPane.showMessageDialog(panel, "Item editted successfully.", "Editing", 1);
        }else{
            JOptionPane.showMessageDialog(panel, "Item editted failed.", "Editing", 2);
        }
    }
    public void waiting(){
        warehouse.waitlist(WarehouseContext.instance().getUser());
    }
    public void transactions(){
        text.setText(warehouse.getTransactions(WarehouseContext.instance().getUser()));
    }
    //productrs and prices
    public void pandp(){
        String tempS="";
        Iterator it = warehouse.getProducts();
        while(it.hasNext()){
            Product temp = (Product) it.next();
            tempS+=temp.toString();
        }
        text.setText(tempS);
    }

    public void details(){
        System.out.println("printing details");
        text.setText(warehouse.getClientData(WarehouseContext.instance().getUser()));
        //System.out.println("Pogggers");
        //panel.validate();
    }
    public void logout(){
        //adjust as nessacary
        System.out.println("Exiting");
        if(WarehouseContext.instance().getLogin() == WarehouseContext.isClerk){
            System.out.println("Exiting to clerk");
            WarehouseContext.instance().changeState(2);
        }
        else if(WarehouseContext.instance().getLogin() == WarehouseContext.isUser){
            System.out.println("Exiting to login");
            WarehouseContext.instance().changeState(0);
        }else{
            System.out.println("Exiting to admin");
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
        else if(e.getSource().equals(this.detailButton)){
            details();
        }
        else if(e.getSource().equals(this.pnpButton)){
            pandp();
        }
        else if(e.getSource().equals(this.transactionButton)){
            transactions();
        }
        else if(e.getSource().equals(this.editButton)){
            editCart();
        }
        else if(e.getSource().equals(this.waitButton)){
            waiting();
        }
        else if(e.getSource().equals(this.addButton)){
            add();
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
        detailButton = new JButton("Details");
        pnpButton = new JButton("Products and Prices");
        transactionButton = new JButton("Transction History");
        editButton = new JButton("Edit Cart");
        waitButton = new JButton("Wait list");
        addButton = new JButton("Add item");
        logoutButton = new JButton("Logout");
        panel.add(detailButton);
        panel.add(pnpButton);
        panel.add(transactionButton);
        panel.add(editButton);
        panel.add(waitButton);
        panel.add(addButton);
        panel.add(logoutButton);
        detailButton.addActionListener(this);
        pnpButton.addActionListener(this);
        transactionButton.addActionListener(this);
        editButton.addActionListener(this);
        waitButton.addActionListener(this);
        addButton.addActionListener(this);
        logoutButton.addActionListener(this);
        panel.setVisible(true);
        panel.paint(panel.getGraphics());
        WarehouseContext.instance().getFrame().add(panel);
        WarehouseContext.instance().getFrame().validate();
    }
}