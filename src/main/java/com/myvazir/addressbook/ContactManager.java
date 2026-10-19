package com.myvazir.addressbook;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface ContactManager {

    void addContact(Contact contact) throws DuplicateContactException;

    void displayAll();

    boolean isEmpty();

    List<Contact> searchContacts(String keyWord);
    List<Contact> searchContacts(Predicate<Contact> predicate);

    Optional<Contact> findExactContact (String name);

    void editContact(String oldName, Contact newContact) throws ContactNotFoundException, DuplicateContactException;

    void deleteContact(String nameDelete) throws ContactNotFoundException;

     List<Contact> getContactSortedByName();

     long count();
}