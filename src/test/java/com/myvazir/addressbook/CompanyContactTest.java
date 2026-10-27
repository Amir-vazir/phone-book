package com.myvazir.addressbook;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("CompanyContact: construction and equality")
class CompanyContactTest {

    @Test
    @DisplayName("Stores the company name passed to the constructor")
    void constructor_storesCompanyName () {
        CompanyContact companyContact = new CompanyContact("JJ", "Amir", "123456");

        assertEquals("JJ", companyContact.getCompanyName());
    }

    @Test
    @DisplayName("Initializes the inherited name and phone fields")
    void constructor_initializesInheritedFields () {
        CompanyContact companyContact = new CompanyContact("JJ", "Amir", "123456");

        assertEquals("Amir", companyContact.getName());
        assertEquals("123456", companyContact.getPhone());
    }

    @Test
    @DisplayName("A company contact is not equal to a plain contact with the same name")
    void equals_returnsFalse_whenComparedToPlainContactWithSameName () {
        Contact plain = new Contact("Amir", "111111");
        CompanyContact company = new CompanyContact("JJ", "Amir", "222222");

        assertNotEquals(plain, company);
        assertNotEquals(company, plain);

    }

    @Test
    @DisplayName("Two company contacts with the same name are equal, even if the companies differ")
    void equals_returnsTrue_whenCompanyContactsShareNameRegardlessOfCompany () {
        CompanyContact a = new CompanyContact("JH", "AMIR", "111111");
        CompanyContact b = new CompanyContact("JJ", "Amir", "222222");

        assertEquals(a,b);
    }

    @Test
    @DisplayName("Equal company contacts produce the same hash code")
    void hashCode_isConsistentWithEquals () {
        CompanyContact a = new CompanyContact("JH", "AMIR", "111111");
        CompanyContact b = new CompanyContact("JJ", "Amir", "222222");

        assertEquals(a,b);
        assertEquals(a.hashCode(),b.hashCode());
    }




}
