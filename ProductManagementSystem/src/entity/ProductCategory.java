package entity;
import java.io.Serializable;

import tool.*;


public class ProductCategory implements Serializable{
    private String name;

    private DoubleLinkedList<Products> productsList;

    public ProductCategory(String name) {
        this.name = name;
        this.productsList = new DoubleLinkedList<>();
    }

    public String getName() { return name; }
    public String setName(String name) {
        return this.name = name;
    }



    public DoubleLinkedList<Products> getProductsList() { return productsList; }
    public DoubleLinkedList<Products> setProductsList(DoubleLinkedList<Products> productsList) {
        return this.productsList = productsList;
    }

    public void showInfo() {
        System.out.printf("|%-20s|\n", name);
    }
    public void showSelfProducts() {
        System.out.printf("|%-20s|\n", name);
        System.out.println("--------------------------------------------------");
        System.out.printf("|%-20s|%-20s|%-20s|\n", "Name", "Price", "Quantity");
        System.out.println("--------------------------------------------------");
        for (int i = 0; i < productsList.size(); i++) {
            productsList.get(i).showInfo();
        }
        System.out.println("--------------------------------------------------");
    }

}
