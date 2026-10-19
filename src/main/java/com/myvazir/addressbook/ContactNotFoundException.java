package com.myvazir.addressbook;

public class ContactNotFoundException extends AddressBookException {
    public ContactNotFoundException(String message) {
        super(message);
    }
}
