package za.ac.cput.factory;

import za.ac.cput.domain.*;
import za.ac.cput.util.Helper;


public class CustomerFactory {
    public static Customer buildCustomer(String firstName,String middleName, String lastName, String password, Contact contact){
        if( Helper.isStringNullorEmpty(firstName)|| Helper.isStringNullorEmpty(middleName)
                || Helper.isStringNullorEmpty(lastName) || Helper.isStringNullorEmpty(password) ||
                contact == null
        ){

            return null;
        }
        Name name = new Name.NameBuilder().setFirstName(firstName)
                .setLastName(lastName)
                .setMiddleName(middleName)
                .build();
        return new Customer.CustomerBuilder()

                .setName(name)
                .setPassword(password)
                .setContact(contact)
                .build();
    }
    public static Customer buildCustomer( String firstName, String lastName, String password, Contact contact){
        if(Helper.isStringNullorEmpty(firstName)
                || Helper.isStringNullorEmpty(lastName) || Helper.isStringNullorEmpty(password) ||
                contact == null
        ){

            return null;
        }
        Name name = new Name.NameBuilder().setFirstName(firstName)
                .setLastName(lastName)
                .build();
        return new Customer.CustomerBuilder()

                .setName(name)
                .setPassword(password)
                .setContact(contact)
                .build();
    }
}

