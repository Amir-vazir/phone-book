import java.util.function.Predicate;

public class Contact implements Displayable {
  private final String name;
  private final String phone;

  public Contact(String name, String phone){
      this.name = name;
      this.phone = phone;
  }

  public String getName (){
      return name;
  }

  public String getPhone (){
      return phone;
  }

  @Override
    public void display(){
      System.out.println(name + " - " + phone);
  }

  public static Predicate<Contact> nameContains (String keyword){
      String lower = keyword.toLowerCase();
      return contact -> contact.getName().toLowerCase().contains(lower);
  }

  public static Predicate<Contact> nameEquals (String name){
      return contact -> contact.getName().equalsIgnoreCase(name);
  }


}

