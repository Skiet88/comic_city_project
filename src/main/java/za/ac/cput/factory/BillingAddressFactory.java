package za.ac.cput.factory;

//Joshua Mokwebo
//Student No 222191562
//GitHubRepository:My_commic_city_project

import za.ac.cput.domain.BillingAdress;
import za.ac.cput.util.Helper;



public  class BillingAddressFactory {


    public BillingAdress buildBillingAddress(String paymentMethod, String invoiceNotes, String street, String suburb, String postalCode, String city) {
        if (Helper.isStringNullorEmpty(street) || Helper.isStringNullorEmpty(suburb) ||
                Helper.isStringNullorEmpty(city) || Helper.isStringNullorEmpty(paymentMethod) ||
                Helper.isStringNullorEmpty(invoiceNotes))
            return null;
        BillingAdress billingAdress = (BillingAdress) new BillingAdress.BillingAdressBuilder().
                setPaymentMethod(paymentMethod).
                setInvoiceNotes(invoiceNotes).
                setStreet(street).
                setSuburb(suburb).
                setPostalCode(postalCode).
                setCity(city).
                build();

        // BillingAdress  address = new BillingAdress.BillingAdressBuilder().copy(billingAdress).setInvoiceNotes(invoiceNotes).setPaymentMethod(paymentMethod).build();
        return billingAdress;


    }




}
