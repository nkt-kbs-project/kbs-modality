package one.modality.ecommerce.payment.gateway.impl.square;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bruno Salmon
 */
final class SquareRestApiOneTimeHtmlResponsesCache {

    final static Map<String, String> ONE_TIME_HTML_RESPONSES = new HashMap<>();

    static void registerOneTimeHtmlResponse(String key, String value) {
        ONE_TIME_HTML_RESPONSES.put(key, value);
    }

    static String getOneTimeHtmlResponse(String key) {
        return ONE_TIME_HTML_RESPONSES.remove(key);
    }
}
