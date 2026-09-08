public class Contact implements Displayable {
  private String name;
  private String phone;

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


}

