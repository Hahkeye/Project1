package project1;
import java.io.*;
import java.util.*;
public class IdServer implements Serializable{
    private static final long serialVersionUID = 1L;
    private static IdServer server;
    private int cID;
    private int pID;
    private int sID;

    private IdServer(){
        this.cID=1;
        this.pID=1;
        this.sID=1;
    }
    public static IdServer instance(){
        if(server==null){
            return(server=new IdServer());
        }else{
            return server;
        }
    }
    public int getcid() {
        return this.cID++;
    }
    public int getpid() {
        //System.out.println("new product id:"+this.pID);
        return this.pID++;
    }
    public int getsid(){
       // System.out.println("new supplier Id:"+this.sID);
        return this.sID++;        
    }
    public static void retrieve(ObjectInputStream input) {
        try {
            server = (IdServer) input.readObject();
        } catch(IOException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    private void writeObject(java.io.ObjectOutputStream output) throws IOException {
        try {
            output.defaultWriteObject();
            output.writeObject(server);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    private void readObject(java.io.ObjectInputStream input) throws IOException, ClassNotFoundException {
        try {
            input.defaultReadObject();
            if (server == null) {
                server = (IdServer) input.readObject();
            } else {
                input.readObject();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}