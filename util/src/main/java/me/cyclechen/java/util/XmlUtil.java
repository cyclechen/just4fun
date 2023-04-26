package me.cyclechen.java.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class XmlUtil {

    public static void updateElementValue(String filePath, String xpath, String newValue) throws IOException, DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(filePath));
        Element element = (Element) document.selectSingleNode(xpath);
        if (element != null) {
            element.setText(newValue);
        }
        writeDocument(document, filePath);
    }

    private static void writeDocument(Document document, String filePath) throws IOException {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding(StandardCharsets.UTF_8.name());
        XMLWriter writer = new XMLWriter(Files.newBufferedWriter(Paths.get(filePath), StandardCharsets.UTF_8), format);
        try {
            writer.write(document);
        } finally {
            writer.close();
        }
    }

    public static String getElementValue(String filePath, String xpath) throws DocumentException, MalformedURLException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(filePath));
        Element element = (Element) document.selectSingleNode(xpath);
        return element != null ? element.getText() : null;
    }

    public static void main(String[] args) throws IOException, DocumentException {
        String filePath = "/path/to/your/xml/file.xml";
        String xpath = "//your/xpath/expression";
        String newValue = "new value";
        updateElementValue(filePath, xpath, newValue);
        String elementValue = getElementValue(filePath, xpath);
        System.out.println("Element value: " + elementValue);
    }

}