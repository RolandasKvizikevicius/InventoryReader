/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import java.time.LocalDate;

/**
 *
 * @author Rolandas K.
 */
public class Item {

    private String name;
    private long code;
    private int quantity;
    private LocalDate expirationDate;

    public Item() {
    }

    public Item(String name, int code, int quantity, LocalDate expirationDate) {
        this.name = name;
        this.code = code;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Item: " + name + ", code: " + code + ", quantity: " + quantity + ", expirationDate: " + expirationDate;
    }

}
