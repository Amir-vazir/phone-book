
import java.util.List;

public interface ContactManager {

    void addContact(Contact contact) throws DuplicateContactException;

    void displayAll();

    boolean isEmpty();

    List<Contact> searchContacts(String keyWord);

    void editContact(String oldName, Contact newContact) throws ContactNotFoundException;

    void deleteContact(String nameDelete) throws ContactNotFoundException;

}