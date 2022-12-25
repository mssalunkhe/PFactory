
package com.example.trial;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class stockItem {
    private int id, quantity;

    stockItem(int id, int quantity) {
        this.id = id;

        this.quantity = quantity;
    }

    int getId() {
        return this.id;
    }
    int getQuantity() {
        return this.quantity;
    }

    void setQuantity(int q) {
        this.quantity=q;

    }


    void show() {
        System.out.println(this.id + " " + this.quantity);
    }
}
public class Stock {
    private ArrayList<stockItem> myStockList = new ArrayList<>();

    public void Stock() {


    }
    public void setMyStock()  {

        myStockList.add(new stockItem(1, 10));
        myStockList.add(new stockItem(2, 10));
        myStockList.add(new stockItem(3, 10));
        myStockList.add(new stockItem(4, 10));
        myStockList.add(new stockItem(5, 10));
        myStockList.add(new stockItem(6, 10));
        myStockList.add(new stockItem(7, 10));
        myStockList.add(new stockItem(8, 10));
        myStockList.add(new stockItem(9, 10));
        myStockList.add(new stockItem(10, 10));
        myStockList.add(new stockItem(11, 10));
        myStockList.add(new stockItem(12, 10));
        myStockList.add(new stockItem(13, 10));
        myStockList.add(new stockItem(14, 10));
        myStockList.add(new stockItem(15, 10));
        myStockList.add(new stockItem(16, 10));
        myStockList.add(new stockItem(17, 10));
        myStockList.add(new stockItem(18, 10));
        myStockList.add(new stockItem(19, 10));
        myStockList.add(new stockItem(20, 10));
        myStockList.add(new stockItem(21, 10));
        myStockList.add(new stockItem(22, 10));
        myStockList.add(new stockItem(23, 10));
        myStockList.add(new stockItem(24, 10));
        myStockList.add(new stockItem(25, 10));
        myStockList.add(new stockItem(26, 10));
        myStockList.add(new stockItem(27, 10));
        myStockList.add(new stockItem(28, 10));
        myStockList.add(new stockItem(29, 10));

    }

    public void addItem(stockItem item) {


        this.myStockList.add(item);
    }

    public boolean deleteItem(stockItem item)   {
        this.myStockList.remove( item);
        return true;
    }
//adds to the current quantity of a stock item
    public void addItemQuantity(int stockItemId, int quantity) {


        int k=0,len = myStockList.size(),currQuantity=0;
        stockItem i;
        while ( k < len) {
            i = myStockList.get(k);
            if(i.getId()==stockItemId)  {
                currQuantity=i.getQuantity();
                i.setQuantity(currQuantity+quantity);
            }

            k = k + 1;
        }
    }
    //returns a set of ids of items in the stock
    public Set<Integer> getStockItemIds() {
        List<Integer> StkItemIds= new ArrayList<>() ;

        for (stockItem item :myStockList
        ) {
            int id =item.getId();
            StkItemIds.add(id);

        }
        Set<Integer> StkItemIdSet= new HashSet<Integer>(StkItemIds) ;

        return StkItemIdSet;
    }

    public void display() {


        //create an iterator
        Integer k=0;

        System.out.println("Stock:: ");
        stockItem i;
        int len= myStockList.size();
        if(len>1) {
            while (k < len) {
                i = myStockList.get(k);
                i.show();
                k = k + 1;
            }
        }else {
            System.out.println("Sorry!No stock data available");
        }

    }
    public boolean sufficientFor(Order ord){
        int ordItemId, ordQuantity,ordToppingsId,ordSidesId;
        int stkItemId;
        int itemQuantity;

        int stkLen = myStockList.size(), ordLen =ord.getOrderItems().size();
        int i=0,j=0;
        Set<Integer> OrdItemIds;
        Set<Integer> StkItemIds;

        OrdItemIds=ord.getOrderItemIds();
        StkItemIds= getStockItemIds();


        //check if all order item ids are there in the stock ids
        if(!StkItemIds.containsAll(OrdItemIds))   {
            System.out.println("Sorry.You ordered something we don't offer at all!");
            return false;
        }
        while(j< ordLen) {
            ordItemId= ord.getOrderItems().get(j).getId();
            ordQuantity= ord.getOrderItems().get(j).getQuantity();
            ordToppingsId= ord.getOrderItems().get(j).getToppingsId();
            ordSidesId= ord.getOrderItems().get(j).getSidesId();

            while (i < stkLen) {
                stkItemId = myStockList.get(i).getId();

                itemQuantity = myStockList.get(i).getQuantity();
                if (stkItemId == ordItemId || stkItemId == ordToppingsId|| stkItemId == ordSidesId) {
                    if (myStockList.get(i).getQuantity() < ordQuantity)
                        return false;
                }
                i++;

            }

            i=0; j++;
        }
        return true;
    }

    //actually deduce the order from the stock
    public boolean commitOrder( Order ord) {

        int ordItemId, ordQuantity,ordToppingsId,ordSidesId;
        int stkLen = myStockList.size(), ordLen =ord.getOrderItems().size();
        int i=0,j=0;


        while(j< ordLen) {
            ordItemId= ord.getOrderItems().get(j).getId(); ordQuantity= ord.getOrderItems().get(j).getQuantity();
            ordToppingsId= ord.getOrderItems().get(j).getToppingsId();ordSidesId= ord.getOrderItems().get(j).getSidesId();

            while (i < stkLen) {
                int stkItemId = myStockList.get(i).getId();
                int itemQuantity = myStockList.get(i).getQuantity();
                if (stkItemId == ordItemId || stkItemId == ordToppingsId|| stkItemId == ordSidesId) {
                        myStockList.get(i).setQuantity(itemQuantity- ordQuantity);
                }
                i++;

            }
            i=0; j++;
        }
        return true;

    }
    }
