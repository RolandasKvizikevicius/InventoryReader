/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rolandas K.
 */
public class InventoryReader {

    public static void main(String[] args) {
        //find file with item data
        try {
            File inventoryFile = new File("inventory.csv");
            List<Item> itemList = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(inventoryFile));
            String line = "";
            //read from file
            while ((line = br.readLine()) != null) {
                // split on comma(',')  
                String[] itemCsv = line.split(",");
                // create item object from file data  
                Item itemObj = new Item();
                // add values from csv to object  
                itemObj.setName(itemCsv[0]);
                itemObj.setCode(Long.parseLong(itemCsv[1]));
                itemObj.setQuantity(Integer.parseInt(itemCsv[2]));
                itemObj.setExpirationDate(LocalDate.parse(itemCsv[3]));
                // adding objects to a list  
                itemList.add(itemObj);
            }
            br.close();

            //sort items by name
            Collections.sort(itemList, (Item i1, Item i2) -> i1.getName().compareTo(i2.getName()));

            //search for duplicates within object list and turn them into a single item, after adding quantity
            for (int i = 0; i < itemList.size(); i++) {
                for (int j = i + 1; j < itemList.size(); j++) {
                    if (itemList.get(i).getName().equals(itemList.get(j).getName()) && itemList.get(i).getCode() == itemList.get(j).getCode()
                            && itemList.get(i).getExpirationDate().equals(itemList.get(j).getExpirationDate())) {
                        itemList.get(i).setQuantity(itemList.get(i).getQuantity() + itemList.get(j).getQuantity());
                        itemList.remove(j);
                        j--;
                    }
                }
            }
            
            Scanner sc = new Scanner(System.in);
            System.out.println("Welcome! Selections are as follows:");
            System.out.println("1 - Print all inventory");
            System.out.println("2 - Find items by entered quantity (or lower than)");
            System.out.println("3 - Find items by entered expiration date(or lower than)");
            System.out.println("Please enter your selection below:");
            int selection = sc.nextInt();
            if (selection == 1) {
                for (int i = 0; i < itemList.size(); i++) {
                    System.out.println(itemList.get(i));
                }
            } else if (selection == 2) {
                System.out.println("Enter quantity below:");
                long scQuantity = sc.nextLong();
                if (scQuantity >= 0) {
                    for (int i = 0; i < itemList.size(); i++) {
                        if (itemList.get(i).getQuantity() <= scQuantity) {
                            System.out.println(itemList.get(i));
                        }
                    }
                }
            } else if (selection == 3) {
                System.out.println("Enter date below in this format: 'yyyy-MM-dd'");
                String scDate = sc.next();
                LocalDate userDate = LocalDate.parse(scDate);
                for (int i = 0; i < itemList.size(); i++) {
                    if (itemList.get(i).getExpirationDate().compareTo(userDate) < 0 || itemList.get(i).getExpirationDate().compareTo(userDate) == 0) {
                        System.out.println(itemList.get(i));
                    }
                }
            } else {
                System.out.println("Invalid selection!");
            }
            sc.close();
        } catch (DateTimeParseException ex) {
            System.out.println("Wrong date format!");
            Logger.getLogger(InventoryReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InventoryReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
