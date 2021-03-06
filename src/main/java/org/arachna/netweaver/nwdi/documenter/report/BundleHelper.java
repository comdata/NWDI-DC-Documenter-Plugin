/**
 * 
 */
package org.arachna.netweaver.nwdi.documenter.report;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Helper class for {@link ResourceBundle}s in conjunction with velocity
 * template internationalization.
 * 
 * @author Dirk Weigenand
 */
public final class BundleHelper {
    /**
     * The resource bundle to work with.
     */
    private final ResourceBundle bundle;

    /**
     * Create an instance of the BundleHelper using the given resource bundle an
     * locale.
     * 
     * @param bundle
     *            bundle to get messages from.
     */
    public BundleHelper(final ResourceBundle bundle) {
        this.bundle = bundle;
    }

    /**
     * Render the message referred to via the given key with the supplied
     * string.
     * 
     * @param key
     *            message key.
     * @param substitute
     *            substitution argument.
     * @return the message with the substitute replaced in the original string.
     */
    public String render(final String key, final String substitute) {
        String message = key;

        try {
            message = bundle.getString(key);
        }
        catch (final MissingResourceException mre) {
            // use key as message format...
        }

        return String.format(bundle.getLocale(), message, substitute);
    }

    /**
     * Helper method to test an object for <code>null</code>.
     * 
     * @param value
     *            the value to test for <code>null</code>.
     * @return <code>true</code>, when the given value is <code>null</code>,
     *         <code>false</code> otherwise.
     */
    public boolean isNull(final Object value) {
        return value == null;
    }
}
