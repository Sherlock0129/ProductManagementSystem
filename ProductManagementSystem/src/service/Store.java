package service;

import entity.ProductCategory;
import tool.*;

import java.io.*;

public class Store implements Serializable{
//use hashTable to store the ProductCategory
    private HashTable<String,ProductCategory> productCategoryTable;

    public Store(){
        productCategoryTable = new HashTable<>();
    }

    public HashTable<String, ProductCategory> getProductCategoryTable() {
        return productCategoryTable;
    }

    public void setProductCategoryTable(HashTable<String, ProductCategory> productCategoryTable) {
        this.productCategoryTable = productCategoryTable;
    }

    public void addProductCategory(ProductCategory productCategory){
        productCategoryTable.put(productCategory.getName(),productCategory);
    }

    public void removeProductCategory(String name){
        productCategoryTable.remove(name);
    }

    public void editProductCategory(String name, ProductCategory productCategory){
        productCategoryTable.put(name,productCategory);
    }

    public void serialize() {
        try {
            // Open the file 'store.txt' for serialization
            FileOutputStream fileOut = new FileOutputStream("store.txt");
            // Create an object output stream
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            // Writes the current object to the output stream
            out.writeObject(this);
            // Close the output stream
            out.close();
            // Close the file output stream
            fileOut.close();
            // Print a successful message
            System.out.println("The data was saved successfully");
        } catch (IOException e) {
            System.err.println("Failed to save data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deserialize() {
        try {
            // Open the file 'store.txt' to deserialize
            FileInputStream fileIn = new FileInputStream("store.txt");
            // Create an object input stream
            ObjectInputStream in = new ObjectInputStream(fileIn);
            // Objects are read from the input stream and type-converted
            Store obj = (Store) in.readObject();
            // Close the input stream
            in.close();
            // Close the file input stream
            fileIn.close();
            // Sets the deserialized data to the current object
            this.productCategoryTable = obj.productCategoryTable;

            // Print a successful message
            System.out.println("The data was read successfully");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Data read failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
