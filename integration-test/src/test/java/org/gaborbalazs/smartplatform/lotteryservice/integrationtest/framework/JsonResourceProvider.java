package org.gaborbalazs.smartplatform.lotteryservice.integrationtest.framework;

import com.google.common.io.Resources;
import org.junit.jupiter.api.Assertions;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
class JsonResourceProvider {

    private static final String UNKNOWN_RESOURCE = "Unknown resource: ";

    /**
     * Get json file content.
     *
     * @param resourceFilePath the resource file path
     * @return the file content
     */
    String getJsonContent(final String resourceFilePath) {
        String result;
        try {
            result = Resources.toString(Resources.getResource(resourceFilePath), StandardCharsets.UTF_8);
        } catch (IOException | IllegalArgumentException e) {
            Assertions.fail(UNKNOWN_RESOURCE + resourceFilePath);
            throw new IllegalStateException(UNKNOWN_RESOURCE + resourceFilePath, e);
        }
        return result;
    }
}
