public class CompanyContact extends Contact {

    private String companyName;

    public CompanyContact(String companyName, String name, String phone){
        super(name, phone);
        this.companyName = companyName;
    }

    public String getCompanyName(){
        return companyName;
    }

    @Override
    public void display(){
        System.out.println(getName() + " - " + getPhone() +
        "( " + BundleManager.getMessage("company.label") + " " + companyName+ " )");
    }

}
