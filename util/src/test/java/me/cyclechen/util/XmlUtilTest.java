package me.cyclechen.util;

import me.cyclechen.java.util.XmlUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class XmlUtilTest {

    private Document getTestDocument() throws DocumentException {
        InputStream inputStream = getClass().getResourceAsStream("/test.xml");
        SAXReader reader = new SAXReader();
        return reader.read(inputStream);
    }

    @Test
    void testParseXml() throws Exception {
        String xml = "<root><element/><element>text</element></root>";
        Document document = XmlUtil.updateElementValue(xml);
        assertNotNull(document);
        assertEquals("root", document.getRootElement().getName());
    }

    @Test
    void testGetRootElement() throws Exception {
        Document document = getTestDocument();
        assertNotNull(document);
        assertEquals("bookstore", document.getRootElement().getName());
    }

    @Test
    void testGetElementValue() throws Exception {
        Document document = getTestDocument();
        assertNotNull(document);

        String title = XmlUtil.getElementValue(document, "/bookstore/book[1]/title");
        assertEquals("Harry Potter", title);

        String price = XmlUtil.getElementValue(document, "/bookstore/book[2]/price");
        assertEquals("39.95", price);
    }

}