package Project1;
public class test{
    public static void main(String args[]){
        Product p1 = new Product("test product","test description",34,4,32.32,2234);
        Client c1= new Client("jeff",1);
        Order o1 = new Order(c1,10.00);
        System.out.println(p1);
        System.out.println(c1);
        System.out.println(c1.adjustBalance(10));
        System.out.println(c1);
        System.out.println(c1.adjustBalance(-10));
        System.out.println(c1);
        c1.adjustBalance(-5);
        System.out.println(c1);

    }
}