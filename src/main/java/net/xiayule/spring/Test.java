package net.xiayule.spring;


import net.xiayule.spring.core.io.ClassPathResource;
import org.junit.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.lang.annotation.ElementType;

/**
 * Created by tan on 14-12-15.
 */
public class Test {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        ClassPathResource classPathResource = new ClassPathResource("spring-config.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(classPathResource.getInputStream());

        Element root = doc.getDocumentElement();

        NodeList nl = root.getChildNodes();
        for (int i=0; i<nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                System.out.println(ele);
            }
        }
    }
}
