
//package project1;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class LoginState extends State implements ActionListener{
    public static final int CLOGIN = 2;
    private static final int ULOGIN = 1;
    private static final int ALOGIN = 3;
    private static final int EXIT = 0;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private WarehouseContext context;
    private static JPanel panel;
    private static LoginState instance;
    private AbstractButton clientButton, clerkButton, adminButton, exitButton;
    
    private LoginState(){
        super();
    }

    public static LoginState instance(){
        if(instance == null){
            instance = new LoginState();
        }
        return instance;
    }

    public void actionPerformed(ActionEvent e){
        panel.setVisible(false);
        if(e.getSource().equals(this.clientButton)){            
            this.user();
        }
        else if(e.getSource().equals(this.clerkButton)){
            this.clerk();
        }
        else if(e.getSource().equals(this.adminButton)){
            this.admin();
        }
        else{
           WarehouseContext.instance().changeState(0);
        }
    }

    public void clear(){
        panel.removeAll();
        panel.paint(panel.getGraphics());
    }

    
    private void clerk(){
        WarehouseContext.instance().setLogin(WarehouseContext.isClerk);
        WarehouseContext.instance().changeState(2);
    }
    private void user(){
        int cid = Integer.valueOf(JOptionPane.showInputDialog(panel, "Enter Client ID", "Client Login",3));
        //int cID =  Integer.valueOf(getResponse("Enter the userID: "));
        if(Warehouse.instance().getClient(cid)!=null){
            WarehouseContext.instance().setLogin(WarehouseContext.isUser);
            WarehouseContext.instance().setUser(cid);
            WarehouseContext.instance().changeState(1);
        }else{
            System.out.println("Bad uid");
        }
    }
    private void admin(){
        System.out.println("admingslected");
        WarehouseContext.instance().setLogin(WarehouseContext.isAdmin);
        WarehouseContext.instance().changeState(3);
    }



    public void run(){
        //WarehouseContext.instance().getFrame().setVisible(true);
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        clientButton = new JButton("Client");
        clerkButton = new JButton("Clerk");
        adminButton = new JButton("Admin");
        exitButton = new JButton("Exit");
        panel.add(clientButton);
        panel.add(clerkButton);
        panel.add(adminButton);
        panel.add(exitButton);
        clientButton.addActionListener(this);
        clerkButton.addActionListener(this);
        adminButton.addActionListener(this);
        exitButton.addActionListener(this);
        panel.setVisible(true);
        panel.paint(panel.getGraphics());
        WarehouseContext.instance().getFrame().add(panel);

    }
}
