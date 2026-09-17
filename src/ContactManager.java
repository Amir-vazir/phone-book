import java.util.ArrayList;
public interface ContactManager {

    void addContact(Contact contact) throws DuplicateContactException;

    void displayAll();

    boolean isEmpty();

    ArrayList<Contact> searchContacts(String keyWord);

    Contact findExactContact(String name);

    void editContact(String oldName, Contact newContact) throws ContactNotFoundException;

    void deleteContact(String nameDelete) throws ContactNotFoundException;

}