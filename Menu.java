
package com.example.trial;

import java.lang.*;
import java.util.*;

class menuItem {
    private int id;
    private String type;//pizza, toppings, sides
    private String subType;//veg, nonveg
    private String name;
    private int price;
    private char size;//L/M/S/N

    public menuItem(int id, String type, String subType, String name, int price, char size) {
        this.id = id;
        this.type = type;
        this.subType = subType;
        this.name = name;
        this.price = price;
        this.size = size;
    }
    public int getId()  {
        return this.id;
    }

    public String getSubType()  {
        return subType;
    }

    public String getType()  {
        return type;
    }
    public void show()   {

        System.out.println(this.id+" "+this.type+" "+this.subType+" "+this.name+" "+this.price+" "+this.size);
    }
    public int getPrice() {

        return this.price;
    }

    public String getName() {

        return this.name;
    }
    public char getSize() {

        return this.size;
    }

}
public class Menu {
    private ArrayList<menuItem> myMenu = new ArrayList<menuItem>();

    public void Menu() {


    }

    public void setMyMenu() {
        myMenu.add(new menuItem(1, "Pizza", "veg", "Deluxe Veggie", 150, 'R'));
        myMenu.add(new menuItem(2, "Pizza", "veg", "Deluxe Veggie", 200, 'M'));
        myMenu.add(new menuItem(3, "Pizza", "veg", "Deluxe Veggie", 325, 'L'));

        myMenu.add(new menuItem(4, "Pizza", "veg", "Cheese and Corn", 175, 'R'));
        myMenu.add(new menuItem(5, "Pizza", "veg", "Cheese and Corn", 375, 'M'));
        myMenu.add(new menuItem(6, "Pizza", "veg", "Cheese and Corn", 475, 'L'));

        myMenu.add(new menuItem(7, "Pizza", "veg", "Paneer Tikka", 160, 'R'));
        myMenu.add(new menuItem(8, "Pizza", "veg", "Paneer Tikka", 260, 'M'));
        myMenu.add(new menuItem(9, "Pizza", "veg", "Paneer Tikka", 340, 'L'));

        myMenu.add(new menuItem(10, "Pizza", "Non-veg", "Non-veg Supreme", 110, 'R'));
        myMenu.add(new menuItem(11, "Pizza", "Non-veg", "Non-veg Supreme", 230, 'M'));
        myMenu.add(new menuItem(12, "Pizza", "Non-veg", "Non-veg Supreme", 350, 'L'));

        myMenu.add(new menuItem(13, "Pizza", "Non-veg", "Chicken Tikka", 100, 'R'));
        myMenu.add(new menuItem(14, "Pizza", "Non-veg", "Chicken Tikka", 200, 'M'));
        myMenu.add(new menuItem(15, "Pizza", "Non-veg", "Chicken Tikka", 300, 'L'));

        myMenu.add(new menuItem(16, "Pizza", "Non-veg", "Pepper Barbeque Chicken", 140, 'R'));
        myMenu.add(new menuItem(17, "Pizza", "Non-veg", "Pepper Barbeque Chicken", 250, 'M'));
        myMenu.add(new menuItem(18, "Pizza", "Non-veg", "Pepper Barbeque Chicken", 370, 'L'));


        myMenu.add(new menuItem(19, "Toppings", "veg", "Black Olive", 20, 'N'));
        myMenu.add(new menuItem(20, "Toppings", "veg", "Capsicum", 30, 'N'));
        myMenu.add(new menuItem(21, "Toppings", "veg", "Paneer", 40, 'N'));
        myMenu.add(new menuItem(22, "Toppings", "veg", "Mushroom", 50, 'N'));
        myMenu.add(new menuItem(23, "Toppings", "veg", "Fresh Tomato", 60, 'N'));

        myMenu.add(new menuItem(24, "Toppings", "Non-veg", "Chiken-tikka", 30, 'N'));
        myMenu.add(new menuItem(25, "Toppings", "Non-veg", "Barbeque Chiken", 40, 'N'));
        myMenu.add(new menuItem(26, "Toppings", "Non-veg", "Grilled Chiken", 50, 'N'));
        myMenu.add(new menuItem(27, "Toppings", "NA", "Extra Cheese", 30, 'N'));

        myMenu.add(new menuItem(28, "Sides", "NA", "Cold Drink", 50, 'N'));
        myMenu.add(new menuItem(29, "Sides", "NA", "Mousse Cake", 90, 'N'));
    }

 //calculate amount of t he ordder
    public int amountOf(Order ord) {
        int amount = 0;

        int menuLen = myMenu.size(), ordLen =ord.getOrderItems().size();

        int i=0,j=0;
        menuItem item;
        //
        System.out.println("OrdLen "+ordLen);
        while(j< ordLen) {
            int ordItemId= ord.getOrderItems().get(j).getId(), ordQuantity= ord.getOrderItems().get(j).getQuantity();
            int toppingsId = ord.getOrderItems().get(j).getToppingsId();
            int sidesId = ord.getOrderItems().get(j).getSidesId();
            int idPrice=0, toppingsPrice=0,sidesPrice=0;
            char PizzaSize='R';



            while (i < menuLen) {
                int menuItemId = myMenu.get(i).getId();


                if (menuItemId == ordItemId) {

                    idPrice = myMenu.get(i).getPrice();
                    PizzaSize = myMenu.get(i).getSize();
                    amount = amount + idPrice * ordQuantity;
                }
                    else if(menuItemId==toppingsId ) {
                        //check if its large size pizza with toppings. In that case don't add price of the toppings.
                    if( PizzaSize!='L' ) {
                        toppingsPrice = myMenu.get(i).getPrice();
                        amount = amount + toppingsPrice * ordQuantity;
                        PizzaSize='R';
                    }else System.out.println("We offer free toppings on large size pizza!");
                }
                else if (menuItemId==sidesId) {
                    sidesPrice = myMenu.get(i).getPrice();
                    amount = amount + sidesPrice * ordQuantity;
                }
                i++;



            }
            i=0;
            j++;
        }
        return amount;
        }






    public void addItem(menuItem item) {


        this.myMenu.add(item);
    }

    public void display() {



        int k=0;
        //menuItem=this.myMenu.get(0);
        System.out.printf("Welcome to PizzaFactory!");
        System.out.println("Please have a look at the Menu card and place your order.");
        System.out.println("Menu:: ");
        menuItem i;
        int len=myMenu.size();
        if(len>1) {
            while (k < len) {
                i = myMenu.get(k);
                i.show();
                k = k + 1;
            }
        }else {
            System.out.println("Sorry!No menu present");
        }

    }

//et rice of an item in the menu
    public int getPrice(int id) {
        menuItem i;
        int len=myMenu.size(),k=0,price=0;
        if(len>1) {
            while (k < len) {
                i = myMenu.get(k);
                if(id== i.getId()) {
                    price = i.getPrice();
                    return price;
                }
                k = k + 1;
            }
        }
        return price;
    }
    public Set<Integer> getVegPizzaIds(){
        menuItem i;
        int len=myMenu.size(),k=0;
        String itemType=new String("");
        String pizzatype=new String("");
        Set<Integer> vegPizzaIds= new HashSet<>();

        if(len>0) {
                while (k < len) {
                    i = myMenu.get(k);
                    itemType=i.getType();
                    pizzatype=i.getSubType();
                    //System.out.println(itemType+" "+pizzatype);

                    if( "Pizza".equals(itemType) && "veg".equals(pizzatype))
                        vegPizzaIds.add(i.getId());

                    k = k + 1;
                }
        }


        return vegPizzaIds;
        }

    public Set<Integer> getNonVegPizzaIds(){
        menuItem i;
        int len=myMenu.size(),k=0;
        Set<Integer> nonVegPizzaIds= new HashSet<>();

        if(len>0) {
                while (k < len) {
                    i = myMenu.get(k);
                    if("Pizza".equals(i.getType()) && "Non-veg".equals(i.getSubType()))
                        nonVegPizzaIds.add(i.getId());
                    k = k + 1;

            }
        }
        return nonVegPizzaIds;
    }

    public Set<Integer> getNonVegToppingIds(){
        menuItem i;
        int len=myMenu.size(),k=0;
        String itemType;
        String toppingType;
        Set<Integer> nonVegToppingIds= new HashSet<>();

        if(len>0) {
            while (k < len) {
                i = myMenu.get(k);
                itemType=i.getType();
                toppingType=i.getSubType();
                if("Toppings".equals(itemType) && "Non-veg".equals(toppingType))
                    nonVegToppingIds.add(i.getId());
                k = k + 1;

            }
        }


        return nonVegToppingIds;
    }
    public int getPaneerToppingId(){
        menuItem i;
        int len=myMenu.size(),k=0;
        String itemType;
        String toppingType;

        if(len>0) {
            while (k < len) {
                i = myMenu.get(k);
                itemType=i.getType();
                toppingType=i.getSubType();
                String name=i.getName();
                if("Toppings".equals(itemType) && "veg".equals(toppingType) && "Paneer".equals(name))
                    return i.getId();

                k++;
            }
        }
        return 0;
    }


}
