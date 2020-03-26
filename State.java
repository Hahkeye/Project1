public abstract class State{
    protected static WarehouseContext contex;
    protected WarehouseState(){

    }
    public abstract void run();
}