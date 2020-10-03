package org.gaborbalazs.smartplatform.lotteryservice.web.component;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LocaleFactoryTest {

    private static final Locale LOCALE_DEFAULT = Locale.US;

    @InjectMocks
    private LocaleFactory underTest;

    @Mock
    private Logger logger;

    @Test
    void testShouldReturnLocaleUSWhenInputIsNull() {
        // GIVEN
        String locale = null;

        // WHEN
        Locale result = underTest.create(locale);

        // THEN
        verify(logger).debug(anyString());
        assertEquals(LOCALE_DEFAULT, result);
    }

    @Test
    void testShouldReturnLocaleWithLanguageAndCountryWhenInputIsProperLanguageCodeIdentifierWithUnderscore() {
        // GIVEN
        String locale = "pt_BR";
        Locale expectedLocale = new Locale("pt", "BR");

        // WHEN
        Locale result = underTest.create(locale);

        // THEN
        verify(logger).debug(anyString());
        assertEquals(expectedLocale, result);
    }

    @Test
    void testShouldReturnLocaleWithLanguageAndCountryWhenInputIsProperLanguageCodeIdentifierWithHyphen() {
        // GIVEN
        String locale = "pt-BR";
        Locale expectedLocale = new Locale("pt", "BR");

        // WHEN
        Locale result = underTest.create(locale);

        // THEN
        verify(logger).debug(anyString());
        assertEquals(expectedLocale, result);
    }

    @Test
    void testShouldReturnLocaleWithLanguageWhenInputIsProperLanguageCodeWithoutCountry() {
        // GIVEN
        String locale = "es";
        Locale expectedLocale = new Locale(locale);

        // WHEN
        Locale result = underTest.create(locale);

        // THEN
        verify(logger).debug(anyString());
        assertEquals(expectedLocale, result);
    }

    @Test
    void testShouldReturnLocaleUSWhenInputIsNotProperTooLong() {
        // GIVEN
        String locale = "French";

        // WHEN
        Locale result = underTest.create(locale);

        // THEN
        verify(logger).debug(anyString());
        assertEquals(LOCALE_DEFAULT, result);
    }

    @Test
    void testShouldReturnLocaleUSWhenInputIsNotProperLanguageIsNotLowerCase() {
        // GIVEN
        String locale = "ES";

        // WHEN
        Locale result = underTest.create(locale);

        // THEN
        verify(logger).debug(anyString());
        assertEquals(LOCALE_DEFAULT, result);
    }

    @Test
    void testShouldReturnLocaleUSWhenInputIsNotProperLanguageAndCountryAreSwapped() {
        // GIVEN
        String locale = "BR_pt";

        // WHEN
        Locale result = underTest.create(locale);

        // THEN
        verify(logger).debug(anyString());
        assertEquals(LOCALE_DEFAULT, result);
    }
}
