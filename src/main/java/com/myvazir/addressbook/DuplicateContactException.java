package com.myvazir.addressbook;

public class DuplicateContactException extends AddressBookException {
    public DuplicateContactException(String message) {
        super(message);
    }
}
