package com.myvazir.addressbook;

import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("AddressBook: add, search, edit, delete, and sort")
class AddressBookTest {

    private AddressBook book;

    @BeforeEach
    void setUp () {
        book = new AddressBook();
    }

    private void addContacts (Contact... contacts) throws DuplicateContactException {
        for (Contact c : contacts){
            book.addContact(c);
        }
    }

    // ---------------------------------------------
    // addContact
    // ---------------------------------------------
    @Nested
    @DisplayName("addContact")
    class AddContact {

        @Test
        @DisplayName("Adds the contact and makes it retrievable by name")
        void addContact_makesContactFindable_whenValid () throws DuplicateContactException {
            Contact Amir = new Contact("Amir", "123456");
            book.addContact(Amir);

            assertEquals(1, book.count());
            assertFalse(book.isEmpty());
            assertTrue(book.findExactContact("Amir").isPresent());
        }

        @Test
        @DisplayName("Throws DuplicateContactException when the name already exists")
        void addContact_throwsDuplicate_whenNameAlreadyExists () throws DuplicateContactException {
            book.addContact(new Contact("Amir", "111"));

            DuplicateContactException ex = assertThrows(DuplicateContactException.class,
            () -> book.addContact(new Contact("Amir", "222")));

            assertTrue(ex.getMessage().contains("Amir"));
        }

        @Test
        @DisplayName("Duplicate detection is case-insensitive")
        void addContact_throwsDuplicate_whenNameDiffersOnlyByCase () throws DuplicateContactException {
            book.addContact(new Contact("Amir", "111"));

            assertThrows(DuplicateContactException.class,
                    () -> book.addContact(new Contact("AMIR", "222")));

        }

        @Test
        @DisplayName("Rejects null with NullPointerException")
        void addContact_throwsNullPointerException_whenContactIsNull () throws DuplicateContactException {
            assertThrows(NullPointerException.class, () -> book.addContact(null));
        }
    }

    // ---------------------------------------------
    // isEmpty / count
    // ---------------------------------------------

    @Nested
    @DisplayName("isEmpty and count")
    class EmptyAndCount {

        @Test
        @DisplayName("A new book is empty and has count 0")
        void newBook_isEmptyWithZeroCount () {
            assertTrue(book.isEmpty());
            assertEquals(0, book.count());
        }

        @Test
        @DisplayName("Adding a contact makes the book non-empty")
        void isEmpty_returnsFalse_afterAddingContact () throws DuplicateContactException {
            book.addContact(new Contact("Amir", "123"));
            assertFalse(book.isEmpty());
        }

        @Test
        @DisplayName("Count reflects the number of added contacts")
        void count_returnsNumberOfContacts () throws DuplicateContactException {
            addContacts(
            new Contact("Amir", "1"),
            new Contact("Reza", "2"),
            new Contact("Piruz", "3")
            );
            assertEquals(3, book.count());
        }
    }

    // ---------------------------------------------
    // findExactContact
    // ---------------------------------------------

    @Nested
    @DisplayName("findExactContact")
    class FindExactContact {

        @Test
        @DisplayName("Returns the matching contact when the name exists")
        void findExactContact_returnsContact_whenNameExists () throws DuplicateContactException {
            book.addContact(new Contact("Amir", "123"));
            Optional<Contact> found = book.findExactContact("Amir");

            assertTrue(found.isPresent());
            assertEquals("Amir", found.get().getName());
        }

        @Test
        @DisplayName("Lookup is case-insensitive")
        void findExactContact_isCaseInsensitive () throws DuplicateContactException {
            book.addContact(new Contact("Amir", "123"));

            assertTrue(book.findExactContact("AMIR").isPresent());
            assertTrue(book.findExactContact("amir").isPresent());
        }

        @Test
        @DisplayName("Returns empty when the name is absent")
        void findExactContact_returnsEmpty_whenNameIsAbsent () {
            assertTrue(book.findExactContact("Ghost").isEmpty());
        }

        @Test
        @DisplayName("Requires an exact name, not a substring")
        void findExactContact_returnsEmpty_whenOnlyPartOfNameMatches () throws DuplicateContactException {
            book.addContact(new Contact("Ali Reza", "123456"));
            assertTrue(book.findExactContact("Ali").isEmpty());
        }
    }

    // ---------------------------------------------
    // searchContacts(String keyword)
    // ---------------------------------------------

    @Nested
    @DisplayName("searchContacts by keyword")
    class SearchByKeyword {

        @Test
        @DisplayName("Returns contacts whose names contain the keyword")
        void searchContacts_returnsMatches_whenKeywordIsSubstring () throws DuplicateContactException {
            addContacts(
              new Contact("Ali Reza","111"),
              new Contact("Amir Ali","222"),
              new Contact("Piruz","333")
            );
            List<Contact> result = book.searchContacts("Ali");
            assertEquals(2, result.size());
        }

        @Test
        @DisplayName("Keyword matching is case-insensitive")
        void searchContacts_isCaseInsensitive () throws DuplicateContactException {
            book.addContact(new Contact("Amir", "123"));

            assertEquals(1, book.searchContacts("AMIR").size());
            assertEquals(1, book.searchContacts("amir").size());
        }

        @Test
        @DisplayName("Returns an empty list when nothing matches")
        void searchContacts_returnsEmptyList_whenNoMatch () throws DuplicateContactException {
            book.addContact(new Contact("Amir", "123"));

            assertTrue(book.searchContacts("Ali").isEmpty());
        }
    }

    // ---------------------------------------------
    // searchContacts(Predicate)
    // ---------------------------------------------

    @Nested
    @DisplayName("searchContacts by predicate")
    class SearchByPredicate {

        @Test
        @DisplayName("Returns contacts that satisfy a custom predicate")
        void searchContactsWithPredicate_returnsMatches_whenPredicateMatches () throws DuplicateContactException {
            addContacts(
                    new Contact("Piruz", "123"),
                    new Contact("Parsa", "456")
            );
            List<Contact> result = book.searchContacts( (contact) -> contact.getPhone().startsWith("12"));
            assertEquals(1, result.size());
            assertEquals("Piruz", result.get(0).getName());
        }

        @Test
        @DisplayName("Returns every contact when the predicate always returns true")
        void searchContactsWithPredicate_returnsAll_whenPredicateAlwaysTrue () throws DuplicateContactException {
            addContacts(
                    new Contact("Piruz", "123"),
                    new Contact("Parsa", "456")
            );

            assertEquals(2, book.searchContacts( contact -> true).size());
        }

        @Test
        @DisplayName("Returns every contact when the predicate always returns false")
        void searchContactsWith () throws DuplicateContactException {
            addContacts(
                    new Contact("Piruz", "123"),
                    new Contact("Parsa", "456")
            );

            assertTrue(book.searchContacts( contact -> false).isEmpty());
        }
    }

    // ---------------------------------------------
    // editContact
    // ---------------------------------------------

    @Nested
    @DisplayName("editContact")
    class EditContact {

        @Test
        @DisplayName("Replaces the stored contact when the new name is not taken")
        void editContact_replacesContact_whenNewNameIsUnique () throws DuplicateContactException, ContactNotFoundException {
            book.addContact(new Contact("Piruz", "111"));
            book.editContact("Piruz", new Contact("Parsa", "222"));

            assertTrue(book.findExactContact("Piruz").isEmpty());
            assertTrue(book.findExactContact("Parsa").isPresent());
        }

        @Test
        @DisplayName("Updates fields while keeping the same name")
        void editContact_updatesFields_whenNameIsUnchanged () throws DuplicateContactException, ContactNotFoundException {
            book.addContact(new Contact("Piruz", "111"));
            book.editContact("Piruz", new Contact("Piruz", "222"));

            assertEquals("222", book.findExactContact("Piruz").orElseThrow().getPhone());
        }

        @Test
        @DisplayName("Locates the old contact regardless of letter case")
        void editContact_isCaseInsensitive_whenLookingUpOldName () throws DuplicateContactException, ContactNotFoundException {
            book.addContact(new Contact("Parsa","111"));
            book.editContact("PARSA", new Contact("Piruz", "222"));

            assertTrue(book.findExactContact("Piruz").isPresent());
        }

        @Test
        @DisplayName("Throws ContactNotFoundException when the old name is absent")
        void editContact_throwsContactNotFound_whenOldNameIsAbsent () {
            assertThrows( ContactNotFoundException.class,
                    () -> book.editContact("Ghost", new Contact("Any", "111"))
            );
        }

        @Test
        @DisplayName("Throws DuplicateContactException when the new name already belongs to another contact")
        void editContact_throwsDuplicate_whenNewNameBelongsToAnotherContact () throws DuplicateContactException {
            addContacts(
                    new Contact("Amir", "111"),
                    new Contact("Parsa", "222")
            );

            assertThrows(DuplicateContactException.class,
                    () -> book.editContact("Amir", new Contact("Parsa", "333"))
            );
        }
    }

    // ---------------------------------------------
    // deleteContact
    // ---------------------------------------------

    @Nested
    @DisplayName("deleteContact")
    class DeleteContact {

        @Test
        @DisplayName("Removes the matching contact from the book")
        void deleteContact_removesContact_whenNameExists () throws ContactNotFoundException, DuplicateContactException {
            book.addContact(new Contact("Piruz", "111"));
            book.deleteContact("Piruz");

            assertTrue(book.isEmpty());
            assertTrue(book.findExactContact("Piruz").isEmpty());
        }

        @Test
        @DisplayName("Deletion is case-insensitive")
        void deleteContact_isCaseInsensitive () throws DuplicateContactException, ContactNotFoundException {
            book.addContact(new Contact("Amir", "123"));
            book.deleteContact("AMIR");

            assertTrue(book.isEmpty());
        }

        @Test
        @DisplayName("Leaves other contacts untouched")
        void deleteContact_keepsOtherContacts_whenDeletingOne () throws DuplicateContactException, ContactNotFoundException {
            addContacts(
                    new Contact("Amir", "111"),
                    new Contact("Ali", "222")
            );
            book.deleteContact("Amir");

            assertEquals(1, book.count());
            assertTrue(book.findExactContact("Ali").isPresent());
        }

        @Test
        @DisplayName("g")
        void deleteContact_ () {
            assertThrows( ContactNotFoundException.class,
                    () -> book.deleteContact("Ghost")
            );
        }
    }

    // ---------------------------------------------
    // getContactSortedByName
    // ---------------------------------------------

    @Nested
    @DisplayName("getContactSortedByName")
    class SortedByName {

        @Test
        @DisplayName("Returns contacts in alphabetical order")
        void getContactSortedByName_returnsAlphabeticalOrder () throws DuplicateContactException {
            addContacts(
                    new Contact("Maryam", "111"),
                    new Contact("Amir", "222"),
                    new Contact("Reza", "333")
            );
            List<Contact> sorted = book.getContactSortedByName();

            assertEquals("Amir", sorted.get(0).getName());
            assertEquals("Maryam", sorted.get(1).getName());
            assertEquals("Reza", sorted.get(2).getName());
        }

        @Test
        @DisplayName("Sorting ignores letter case")
        void getContactSortedByName_isCaseInsensitive () throws DuplicateContactException {
            addContacts(
                    new Contact("Ziba", "111"),
                    new Contact("amir", "222")
            );
            List<Contact> sorted = book.getContactSortedByName();

            assertEquals("amir", sorted.get(0).getName());
            assertEquals("Ziba", sorted.get(1).getName());
        }

        @Test
        @DisplayName("Returns an empty list when the book is empty")
        void getContactSortedByName_returnsEmptyList_whenBookIsEmpty () {
            assertTrue(book.getContactSortedByName().isEmpty());
        }

        @Test
        @DisplayName("Modifying the returned list does not affect the book")
        void getContactSortedByName_returnsIndependentList () throws DuplicateContactException {
            addContacts(
                    new Contact("Amir", "111"),
                    new Contact("Parsa", "222")
            );
            List<Contact> sorted = book.getContactSortedByName();
            sorted.clear();
            assertEquals(2, book.count());
        }
    }
}
