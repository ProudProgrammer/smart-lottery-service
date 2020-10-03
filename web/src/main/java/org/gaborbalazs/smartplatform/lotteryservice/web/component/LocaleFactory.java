package org.gaborbalazs.smartplatform.lotteryservice.web.component;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocaleFactory {

    private static final String PATTERN_LANGUAGE_COUNTRY = "^[a-z]{2}[_-][A-Z]{2}$";
    private static final String PATTERN_LANGUAGE = "^[a-z]{2}$";
    private static final String PATTERN_SPLIT = "[_-]";
    private static final Locale LOCALE_DEFAULT = Locale.US;

    private final Logger logger;

    LocaleFactory(Logger logger) {
        this.logger = logger;
    }

    public Locale create(String localeAsString) {
        if (StringUtils.isBlank(localeAsString)) {
            return getLocaleDefault();
        } else if (localeAsString.matches(PATTERN_LANGUAGE_COUNTRY)) {
            String[] localeParts = localeAsString.split(PATTERN_SPLIT);
            return getLocale(localeParts[0], localeParts[1]);
        } else if (localeAsString.matches(PATTERN_LANGUAGE)) {
            return getLocale(localeAsString, null);
        } else {
            return getLocaleDefault();
        }
    }

    private Locale getLocaleDefault() {
        logger.debug("Locale is not specified or not appropriate. Default locale will be used: " + LOCALE_DEFAULT.toString());
        return LOCALE_DEFAULT;
    }

    private Locale getLocale(String language, String country) {
        Locale.Builder localeBuilder = new Locale.Builder().setLanguage(language);
        if (country != null) {
            localeBuilder.setRegion(country);
        }
        Locale locale = localeBuilder.build();
        logger.debug("Locale is specified and appropriate: " + locale.toString());
        return locale;
    }
}
