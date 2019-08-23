/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.BankCustomer;

/**
 *
 * @author willi
 */
public class CustomerDTO {
    
    private long customerID;
    private String fullName; 
    private String accountNumber;
    private double balance;

    public CustomerDTO(BankCustomer bc) {
        customerID = bc.getId();
        fullName = bc.getFirstName() + " " + bc.getLastName();
        accountNumber = bc.getAccountNumber();
        balance = bc.getBalance();
    }

    public long getCustomerID() {
        return customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }
    
    

    
}
