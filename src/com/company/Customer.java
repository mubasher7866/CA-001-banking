// Name : Mubasher Zeb Khan
// Student Number : 21694

package com.company;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;
    private String pin;
    private String accountNumber;


    public Customer(String firstName, String lastName, String email, String pin, String accountNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.pin = pin;
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "Name: " + firstName +" "+ lastName+"\nEmail: " + email + "\nAccount No: " + accountNumber ;
    }






}

