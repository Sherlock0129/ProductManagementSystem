package service;
import tool.DoubleLinkedList;
import entity.Products;

import java.io.Serializable;

public class Sort implements Serializable {

    private DoubleLinkedList<Products> productsList;

    public Sort(DoubleLinkedList<Products> productsList) {
        this.productsList = productsList;
    }

    public DoubleLinkedList<Products> sortProductsWithPrice() {
        if (productsList.size() > 1) {
            quickSort(productsList, 0, productsList.size() - 1);
        }
        return productsList;
    }

    private void quickSort(DoubleLinkedList<Products> list, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(list, low, high);

            quickSort(list, low, partitionIndex - 1);
            quickSort(list, partitionIndex + 1, high);

        }
    }

    private int partition(DoubleLinkedList<Products> list, int low, int high) {
        Products pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compareProduct(list.get(j), pivot) <= 0) {
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, high);
        return i + 1;
    }

    private void swap(DoubleLinkedList<Products> list, int i, int j) {
        Products temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    // Compare two products with price
    private int compareProduct(Products obj1, Products obj2) {
        double price1 = obj1.getPrice();
        double price2 = obj2.getPrice();

        if (price1 < price2) {
            return -1; // The price of obj1 is less than the price of obj2
        } else if (price1 > price2) {
            return 1; // The price of OBJ1 is greater than that of OBJ2
        } else {
            return 0; // The price is equal
        }

    }

}
