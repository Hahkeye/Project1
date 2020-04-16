import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.io.*;
public class ClerkButton extends JButton implements ActionListener{
    public ClerkButton(){
        super("Clerk");
        this.setListener();
    }
    public void setListener(){
        this.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        WarehouseContext.instance().setLogin(WarehouseContext.isClerk);
        LoginState.instance().clear();
        //WarehouseContext.instance().changeState(0);
    }
}