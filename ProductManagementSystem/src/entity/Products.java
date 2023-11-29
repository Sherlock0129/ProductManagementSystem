package entity;
import java.io.Serializable;
import java.util.Hashtable;

public class Products implements Serializable{
    private String name;
    private String category;
    private double price;
    private int quantity;


    private Hashtable<String, Integer> purchaseDateTable ;
    private Hashtable<String, Integer> saleDateTable ;

    public Products() {
    }

    public Products(String name, double price) {
        this.name = name;
        this.price = price;
        purchaseDateTable = new Hashtable<>();
        saleDateTable = new Hashtable<>();

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
         this.name = name;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Hashtable<String, Integer> getPurchaseDateTable() {
        return purchaseDateTable;
    }
    public void setPurchaseDateTable(Hashtable<String, Integer> purchaseDateTable) {
        this.purchaseDateTable = purchaseDateTable;
    }

    public Hashtable<String, Integer> getSaleDateTable() {
        return saleDateTable;
    }
    public void setSaleDateTable(Hashtable<String, Integer> saleDateTable) {
        this.saleDateTable = saleDateTable;
    }

    public void addPurchaseDate(String date, int quantity) {
        purchaseDateTable.put(date, quantity);
    }
    public void addSaleDate(String date, int quantity) {
        saleDateTable.put(date, quantity);
    }


    public void showInfo() {
        System.out.printf("|%-20s|%-20s|%10d|\n", name, price, quantity);
    }


}
