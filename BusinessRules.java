
package com.example.trial;

import java.util.*;



  class BusinessRules {
    private Menu menu;
    BusinessRules(){

    }
//These business rules are for the arument menu
    public void basedOnMenu(Menu menu){
        this.menu=menu;
    }

    //Check whether business rules are followed by an order
    public boolean followedBy(Order ord){


        if(!vegPizzaNonVegTop(ord) && ! nonVegPizzaPaneerTop(ord)) {

                return true;
        }
        return false;

    }


    public boolean vegPizzaNonVegTop( Order ord){

        Set<Integer> vegPizzas = menu.getVegPizzaIds();
        Set<Integer> nonVegToppings = menu.getNonVegToppingIds();

        for (orderItem item: ord.getOrderItems()

        ) {

            int id =item.getId(),toppingId= item.getToppingsId();
            if (vegPizzas.contains(id) && nonVegToppings.contains(toppingId) ) {
                System.out.println("Sorry.You cannot order a veg pizza with a non-veg toppings.");
                return true;
            }
        }


        return false;
    }

    public boolean nonVegPizzaPaneerTop( Order ord){
        Set<Integer> nonVegPizzas = menu.getNonVegPizzaIds();
        int paneerToppings = menu.getPaneerToppingId();
        for (orderItem item: ord.getOrderItems()

        ) {

            int id =item.getId(),toppingId= item.getToppingsId();
            if (nonVegPizzas.contains(id) && paneerToppings==toppingId ) {
                System.out.println("Sorry.You cannot order a Nonveg pizza with a paneer toppings.");
                return true;
            }
        }


        return false;
    }



}
