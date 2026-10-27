package com.myvazir.addressbook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Contact: construction, predicate, equality, and hashing")
class ContactTest {

    @Test
    @DisplayName("Stores the name and phone passed to the constructor ")
    void constructor_storesNameAndPhone (){

        Contact c = new Contact("jak", "935353");

        assertEquals("jak",c.getName());
        assertEquals("935353",c.getPhone());

    }

  @Nested
  @DisplayName("nameContains predicate")
  class NameContains {

        @Test
        @DisplayName("Matches when the keyword appears anywhere in the name")
        void nameContains_returnsTrue_whenKeywordIsSubstringOfName () {

            Contact contact = new Contact("Ali Reza", "123456");

            assertTrue(Contact.nameContains("Ali").test(contact));
            assertTrue(Contact.nameContains("Reza").test(contact));
            assertTrue(Contact.nameContains("li Re").test(contact));


        }


      @Test
      @DisplayName("Ignores letter case in both keyword and name")
      void nameContains_isCaseInsensitive () {


          Contact contact = new Contact("Ali", "123456");

          assertTrue(Contact.nameContains("Ali").test(contact));
          assertTrue(Contact.nameContains("ali").test(contact));
          assertTrue(Contact.nameContains("ALI").test(contact));


      }


      @Test
      @DisplayName("Returns false when the keyword is not in the name")
      void nameContains_returnsFalse_whenKeywordIsAbsent () {


          Contact contact = new Contact("Ali", "123456");

          assertFalse(Contact.nameContains("Amir").test(contact));
          assertFalse(Contact.nameContains("xyz").test(contact));



      }
  }

  @Nested
  @DisplayName("nameEquals predicate")
  class NameEquals {

        @Test
        @DisplayName("Returns true when the whole name matches")
        void nameEquals_returnsTrue_whenNameMatchExactly () {
            Contact contact = new Contact("Ali", "123456");

            assertTrue(Contact.nameEquals("Ali").test(contact));

        }

       @Test
       @DisplayName("Ignores letter case")
       void nameEquals_isCaseInsensitive () {
           Contact contact = new Contact("Ali", "123456");

           assertTrue(Contact.nameEquals("ali").test(contact));
           assertTrue(Contact.nameEquals("ALI").test(contact));

       }

       @Test
       @DisplayName("Returns false when the names are different")
       void nameEquals_returnsFalse_whenNamesDiffer () {
           Contact contact = new Contact("Ali", "123456");

           assertFalse(Contact.nameEquals("Amir").test(contact));

       }


       @Test
       @DisplayName("Rejects partial matches, unlike nameContains")
       void nameEquals_returnsFalse_whenOnlyPartOfNameMatches () {
           Contact contact = new Contact("Ali Reza", "123456");

           assertFalse(Contact.nameEquals("Ali").test(contact));
           assertFalse(Contact.nameEquals("Reza").test(contact));

       }

  }


  @Nested
  @DisplayName("ho")
  class Equals {

        @Test
        @DisplayName("Two contacts are equal when their names match, even with different phones")
        void equals_returnsTrue_whenNamesMatchRegardlessOfPhone (){
            Contact a = new Contact("Ali", "123");
            Contact b = new Contact("Ali", "456");

            assertEquals(a,b);

        }

        @Test
        @DisplayName("Names are compared case-insensitively")
        void equals_returnsTrue_whenNamesDifferOnlyByCase (){
           Contact a = new Contact("ali", "123");
           Contact b = new Contact("ALI", "123");

           assertEquals(a,b);

        }

        @Test
        @DisplayName("Contacts with different names are not equal")
        void equals_returnsFalse_whenNamesDiffer (){
           Contact a = new Contact("Ali", "123");
           Contact b = new Contact("Amir", "123");

           assertNotEquals(a,b);

        }

        @Test
        @DisplayName("A contact is equal to itself")
        void equals_returnsTrue_whenComparedWithItself (){
           Contact a = new Contact("Ali", "123");
           assertTrue(a.equals(a));
        }

        @Test
        @DisplayName("A contact is never equal to null")
        void equals_returnsFalse_whenComparedWithNull (){
           Contact a = new Contact("Ali", "123");
           assertFalse(a.equals(null));
        }

        @Test
        @DisplayName("A contact is not equal to an unrelated object")
        void equals_returnsFalse_whenComparedWithDifferentType (){
           Contact a = new Contact("Ali", "123");
           assertFalse(a.equals("Ali"));
           assertFalse(a.equals(1));
        }
  }


  @Nested
  @DisplayName("hashCode")
  class HashCode {

        @Test
        @DisplayName("hg")
        void hashcodeT () {
            Contact a = new Contact("Ali", "123");
            Contact b = new Contact("ALI", "111");

            assertEquals(a,b);
            assertEquals(a.hashCode(), b.hashCode());
        }
  }



}
