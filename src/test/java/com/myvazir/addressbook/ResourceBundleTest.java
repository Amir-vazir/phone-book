package com.myvazir.addressbook;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Resource bundles load correctly for both locales")
class ResourceBundleTest {

    @Test
    @DisplayName("English bundle provides a non-empty add.name.prompt")
    void englishBundle_hasAddNamePrompt () {
        ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.ENGLISH, new UTF8Control());
        String prompt = bundle.getString("add.name.prompt");

        assertNotNull(prompt);
        assertFalse(prompt.isBlank());
    }

    @Test
    @DisplayName("Persian bundle is loaded through UTFControl")
    void persianBundle_loadsThroughUtf8Control () {
        ResourceBundle bundle = ResourceBundle.getBundle("messages", new Locale("fa"), new UTF8Control());
        String prompt = bundle.getString("add.name.prompt");
        assertNotNull(prompt);
        assertTrue(prompt.matches(".*[\\u0600-\\u06FF].*"), "Expected Persian characters in: " + prompt);
    }

    @Test
    @DisplayName("Both locales define the same set of keys")
    void bothLocales_defineSameKeys () {
        ResourceBundle english = ResourceBundle.getBundle("messages", Locale.ENGLISH, new UTF8Control());
        ResourceBundle persian = ResourceBundle.getBundle("messages", new Locale("fa"), new UTF8Control());

        assertTrue(english.keySet().containsAll(persian.keySet()));
        assertTrue(persian.keySet().containsAll(english.keySet()));
    }
}
