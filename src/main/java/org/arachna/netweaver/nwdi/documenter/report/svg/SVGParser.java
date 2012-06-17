/**
 * 
 */
package org.arachna.netweaver.nwdi.documenter.report.svg;

import java.io.IOException;
import java.io.Reader;

import org.apache.commons.digester3.Digester;
import org.arachna.xml.AbstractDefaultHandler;
import org.arachna.xml.NullEntityResolver;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * Parser for SVG documents. Extracts general properties of an SVG {@see
 * SVGProperties}.
 * 
 * @author Dirk Weigenand
 */
public final class SVGParser extends AbstractDefaultHandler {
    /**
     * Parse the given SVG and extract general properties (width, height).
     * 
     * @param reader
     *            the reader containing the SVG document.
     * @return {@link SVGProperties} read from the SVG document.
     */
    public SVGProperties parse(final Reader reader) {
        try {
            final Digester digester = new Digester(XMLReaderFactory.createXMLReader());
            digester.setEntityResolver(new NullEntityResolver());
            digester.addObjectCreate("svg", SVGProperties.class);

            digester.addCallMethod("svg", "setWidth", 1);
            digester.addCallParam("svg", 0, "width");
            digester.addCallMethod("svg", "setHeight", 1);
            digester.addCallParam("svg", 0, "height");

            return (SVGProperties)digester.parse(reader);
        }
        catch (final SAXException e) {
            throw new RuntimeException(e);
        }
        catch (final IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                reader.close();
            }
            catch (final IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}