import java.util.Scanner;
import java.io.Serializable;

import service.*;
import tool.*;
import entity.*;

public class ProductManagementSystem implements Serializable{
    private static Store store = new Store();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Product Category");
            System.out.println("2. Add Product ");
            System.out.println("3. Purchase products");
            System.out.println("4. Sale products");
            System.out.println("5. List products");
            System.out.println("6. Save");
            System.out.println("7. Load");
            System.out.println("8. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            try {
                switch (choice) {
                    case 1:
                        addProductCategory(scanner);
                        break;
                    case 2:
                        addProduct(scanner);
                        break;
                    case 3:
                        purchaseProducts(scanner);
                        break;
                    case 4:
                        saleProducts(scanner);
                        break;
                    case 5:
                        listProductHistory(scanner);
                        break;
                    case 6:
                        saveDate();
                        break;
                    case 7:
                        loadDate();
                        break;
                    case 8:
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void addProductCategory(Scanner scanner) {
        System.out.println("Please enter the name of the product category");
        String name = scanner.nextLine();
        ProductCategory productCategory = new ProductCategory(name);
        store.addProductCategory(productCategory);
    }

    public static void addProduct(Scanner scanner) {
        listProductCategory();
        System.out.println("Please select a name of the product category");
        String name = scanner.nextLine();
        //Determine if the category exists
        if (!store.getProductCategoryTable().containsKey(name)) {
            System.out.println("The product category does not exist");
            return;
        }
        System.out.println("Please enter the name of the product");
        String productName = scanner.nextLine();

        System.out.println("Please enter the price of the product");
        double price = scanner.nextDouble();
        Products product = new Products(productName, price);
        ProductCategory category = store.getProductCategoryTable().get(name);
        if (category != null) {
            if (category.getProductsList() != null) {
                category.getProductsList().add(product);
                System.out.println("Product added successfully to the category: " + name);
            } else {
                System.out.println("Product list is null for category: " + name);
            }
        } else {
            System.out.println("Category is null: " + name);
        }
    }

    //list all productCategory
    public static void listProductCategory(){
        System.out.println("Product Category List");
        System.out.printf("|%-20s|\n", "Name");
        for (String s : store.getProductCategoryTable().keySet()) {
            store.getProductCategoryTable().get(s).showInfo();
        }
    }

    //list all productCategory with their products
    public static void listProductWithCategory() {
        System.out.println("Product List");
        for (String s : store.getProductCategoryTable().keySet()) {
            System.out.println("-----------------------------------------------------");
            store.getProductCategoryTable().get(s).showInfo();
            System.out.printf("|%-20s|%-20s|%10s|\n", "Name", "Price", "Quantity");

            // Retrieve products list for the current category
            DoubleLinkedList<Products> productsList = store.getProductCategoryTable().get(s).getProductsList();

            // Sort products within the category using Sort class
            Sort sorter = new Sort(productsList);
            productsList = sorter.sortProductsWithPrice();

            double totalPrice = 0;
            for (int i = 0; i < productsList.size(); i++) {
                // Display sorted products
                productsList.get(i).showInfo();
                totalPrice += productsList.get(i).getPrice() * productsList.get(i).getQuantity();
            }
            System.out.println("Total Price : " + totalPrice);
            System.out.println("-----------------------------------------------------");
        }
    }


    //Select one of the existing products to display historical purchase and sale dates and quantities
    public static void listProductHistory(Scanner scanner){
        listProductWithCategory();
        System.out.println("Please select a name of the product ");
        String productName = scanner.nextLine();
        //Iterate through all product categories to find the product
        for (String s : store.getProductCategoryTable().keySet()) {
            for (int i = 0; i < store.getProductCategoryTable().get(s).getProductsList().size(); i++) {
                if (store.getProductCategoryTable().get(s).getProductsList().get(i).getName().equals(productName)){
                    System.out.println("Purchase History");
                    System.out.printf("|%-20s|%-20s|\n", "Date", "Quantity");
                    for (String s1 : store.getProductCategoryTable().get(s).getProductsList().get(i).getPurchaseDateTable().keySet()) {
                        System.out.printf("|%-20s|%10d|\n", s1, store.getProductCategoryTable().get(s).getProductsList().get(i).getPurchaseDateTable().get(s1));
                    }
                    System.out.println("Sale History");
                    System.out.printf("|%-20s|%-20s|\n", "Date", "Quantity");
                    for (String s1 : store.getProductCategoryTable().get(s).getProductsList().get(i).getSaleDateTable().keySet()) {
                        System.out.printf("|%-20s|%10d|\n", s1, store.getProductCategoryTable().get(s).getProductsList().get(i).getSaleDateTable().get(s1));
                    }
                }
            }
        }
    }


    public static void purchaseProducts(Scanner scanner){
       //Select one of the existing products to add the incoming quantity and date
        listProductWithCategory();
        System.out.println("Please select a name of the product ");
        String productName = scanner.nextLine();
        //Iterate through all product categories to find the product
        for (String s : store.getProductCategoryTable().keySet()) {
            for (int i = 0; i < store.getProductCategoryTable().get(s).getProductsList().size(); i++) {
                if (store.getProductCategoryTable().get(s).getProductsList().get(i).getName().equals(productName)){
                    //Ask if you want to add it in a loop
                    boolean isContinueAdd = true;
                    while (isContinueAdd){
                        System.out.println("Please enter the purchase quantity");
                        int quantity = scanner.nextInt();
                        scanner.nextLine();
                        //Decrease the quantity from the current product
                        Products products = store.getProductCategoryTable().get(s).getProductsList().get(i);
                        products.setQuantity(products.getQuantity() + quantity);
                        System.out.println("Please enter the purchase date");
                        String purchaseDate = scanner.nextLine();
                        store.getProductCategoryTable().get(s).getProductsList().get(i).addPurchaseDate(purchaseDate, quantity);
                        System.out.println("Product purchase success");
                        System.out.println("Do you want to continue to purchase? (Y/N)");
                        String isContinue = scanner.nextLine();
                        if (isContinue.equals("N")){
                            isContinueAdd = false;
                        }
                    }
                }
            }
        }
        listProductWithCategory();
    }

    public static void saleProducts(Scanner scanner){
        //Select one of the existing products to add the incoming quantity and date
        listProductWithCategory();
        System.out.println("Please select a name of the product ");
        String productName = scanner.nextLine();
        //Iterate through all product categories to find the product
        for (String s : store.getProductCategoryTable().keySet()) {
            for (int i = 0; i < store.getProductCategoryTable().get(s).getProductsList().size(); i++) {
                if (store.getProductCategoryTable().get(s).getProductsList().get(i).getName().equals(productName)){
                    //Ask if you want to add it in a loop
                    boolean isContinueAdd = true;
                    while (isContinueAdd){
                        System.out.println("Please enter the sale quantity");
                        int quantity = scanner.nextInt();
                        scanner.nextLine();
                        //Add quantity to the current quantity of product
                        Products products = store.getProductCategoryTable().get(s).getProductsList().get(i);
                        products.setQuantity(products.getQuantity() - quantity);
                        System.out.println("Please enter the sale date");
                        String saleDate = scanner.nextLine();
                        store.getProductCategoryTable().get(s).getProductsList().get(i).addSaleDate(saleDate, quantity);
                        System.out.println("Product sale success");
                        System.out.println("Do you want to continue to sale? (Y/N)");
                        String isContinue = scanner.nextLine();
                        if (isContinue.equals("N")){
                            isContinueAdd = false;
                        }
                    }
                }
            }
        }
        listProductWithCategory();
    }




    private static void saveDate(){
        store.serialize();
    }

    private static void loadDate(){
        store.deserialize();
    }
}