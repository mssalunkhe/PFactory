package com.example.trial;

import java.util.*;
import java.lang.*;
/*A terminal will accept orders and serve them through its stock*/
public class Terminal {

    private int terminal_id;
    private int orderId=1;

    private Stock myStock = new Stock();
    private Menu myMenu = new Menu();

    //private List<Order> orderList= new ArrayList<>();
    public Terminal(int id) {
        this.terminal_id=id;
    }
    public int getTerminalId(){
        return this.terminal_id;
    }
    public int getNextOrderId() {
        return orderId++;
    }
    public Menu getMenu(){
        return this.myMenu;
    }
    public void showMenu()  {

        myMenu.display();

    }
    public void setStock()  {
        //Menu myMenu = new Menu();
        myStock.setMyStock();
        myStock.display();

    }

    public Stock getStock(){
        return this.myStock;
    }

    public void setMenu()  {

        myMenu.setMyMenu();


    }
    /*Collect next order from a customer. An order consists an array of items. Each item is either a pizza with a topping
    (in multiple quantity)or a sides(in multiple quantity).*/
    public Order nextOrder(){
        // A pizza item in the array has sidesId=0 and a sides item has pizzaId=crustId=toppingsId=0
        int choice=0,pizzaId=0,toppingsId=0,crustId=0,quantity=0,sidesId=0;
        int cont=1;

        ArrayList<orderItem> ordItemList = new ArrayList<>();
        Order ord= new Order(getNextOrderId(),getTerminalId(),ordItemList);


            Scanner sc = new Scanner(System.in);
            while (cont != 0) {
                try {
                    System.out.println("Please enter 1 for Pizza , 2 for Sides :");
                    choice = sc.nextInt();
                    if (choice == 1) {
                        System.out.println("Please enter the pizza id (single) (1-18):");
                        pizzaId = sc.nextInt();
                        System.out.println("Please enter the crust type (1/2/3/4) :");
                        crustId = sc.nextInt();
                        System.out.println("Please enter the Toppings id you want (single)(19-27) :");
                        toppingsId = sc.nextInt();
                        System.out.println("Please enter the quantity :");
                        quantity = sc.nextInt();
                        orderItem item = new orderItem(pizzaId, crustId, toppingsId, sidesId, quantity);
                        ord.addItem(item);


                    } else if (choice == 2) {
                        System.out.println("Please enter the sides id (single)(28-29) :");
                        sidesId = sc.nextInt();
                        System.out.println("Please enter the quantity :");
                        quantity = sc.nextInt();
                        orderItem item = new orderItem(pizzaId, crustId, toppingsId, sidesId, quantity);
                        ord.addItem(item);
                    } else {
                        System.out.println("Please enter a correct choice :");
                        continue;
                    }
                    //reset the values
                    pizzaId = crustId = toppingsId = sidesId = 0;
                    System.out.println("Would like to have some more pizzas or sides? ( 0 (zero): no)");
                    cont = sc.nextInt();

                } catch (InputMismatchException ex) {
                    System.out.println("Please enter a numeric value");
                    return null;
                }
            }
        return ord;

    }
    //Get confirmation from the customer by showing him the order and amount to pay
    public int confirmOrder(Order ord )   {
        int amount,sr=0;
        Scanner sc= new Scanner(System.in);

        ord.display();

        amount= myMenu.amountOf(ord);
        System.out.println("The amount to be paid for the order  :"+ amount);

        System.out.println("Please confirm the order by pressing 1 or reject by pressing 0");
        sr = sc.nextInt();

        if(sr!=1) {
            //orderList.remove(ord);
            System.out.println("Your order is cancelled");
        }
        return sr;
        }

    /*public void addOrder(Order ord){
        this.orderList.add(ord);
        System.out.println("Your order is placed. Please wait for a while.");

    }*/
    //deduce the order quantity from the stock
    public boolean processOrder(Order ord) {
            //see if there is sufficient stock available
            if(!myStock.sufficientFor(ord)) {
                System.out.println("Sorry. We have insufficient stock.");
                return false;
            }   else {
                myStock.commitOrder(ord);// deduce the order from the stock
                myStock.display();
            }
            return true;
    }
    public static void main(String args[])   {

       Terminal term = new Terminal(1);// created  a terminal object

        BusinessRules br= new BusinessRules();
        Stock stock;
        Menu menu;
        //set stock at this terminal locally
        term.setStock();


        //add 50 to the qunatity of itemid=5
        stock=term.getStock();
        stock.addItemQuantity(5,50);

        term.setMenu();
        menu=term.getMenu();
        Order ord;
        br.basedOnMenu(menu);
        //wait for a customer to place an order
        while (true) {
            term.showMenu();


            ord = term.nextOrder();//Wait for a customer to place an order at the terminal
            // if customer does not give roper input
            if(ord==null) continue;
            if (0!= term.confirmOrder(ord)) { //verify the order from the customer
                if(br.followedBy( ord)){
                    //term.addOrder(ord);// add the order in the list
                    if(term.processOrder(ord)) //deduce the order from the stock. Implemented locally.
                        System.out.println("Thank you for visiting.");
                }else {
                    System.out.println("Please change the order.");
                }


            }

        }
    }
}


