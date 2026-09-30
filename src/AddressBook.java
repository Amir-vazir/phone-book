import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AddressBook implements ContactManager {
    private final ArrayList<Contact> contacts;

    public AddressBook (){
        contacts = new ArrayList<>();
    }


    @Override
    public void addContact( Contact contact) throws DuplicateContactException{
        Objects.requireNonNull(contact, "Contact can not be null");
        if (findExactContact(contact.getName()).isPresent() ){
            throw new DuplicateContactException("Duplicate contact: " + contact.getName());
        }
     contacts.add(contact);
    }

    @Override
    public void displayAll (){
        IntStream.range(0, contacts.size()).forEach(i -> {System.out.println((i + 1) + ". ");
               contacts.get(i).display();});
    }

    @Override
    public boolean isEmpty(){
        return contacts.isEmpty();
    }


    @Override
    public List<Contact> getContactSortedByName(){
     return  contacts.stream().sorted(Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    @Override
    public long count(){
        return contacts.size();
    }

    @Override
    public List<Contact> searchContacts(String keyWord) {
     return   searchContacts(Contact.nameContains(keyWord));
    }

    @Override
    public List<Contact> searchContacts(Predicate<Contact> predicate){
      return   contacts.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public Optional<Contact> findExactContact (String name){
       return contacts.stream().filter(Contact.nameEquals(name)).findFirst();
    }

    @Override
    public void  editContact ( String oldName, Contact newContact) throws ContactNotFoundException{
     int index =  IntStream.range(0,contacts.size()).filter(i -> contacts.get(i).getName().equalsIgnoreCase(oldName))
               .findFirst().orElseThrow( () -> new ContactNotFoundException("Contact not found: " + oldName));
     contacts.set(index, newContact);
    }

    @Override
    public void deleteContact(String nameDelete) throws ContactNotFoundException{
    boolean  removed  =  contacts.removeIf(Contact.nameEquals(nameDelete));

    if (!removed){
        throw new ContactNotFoundException("Contact not found: " + nameDelete );
        }

    }


}

