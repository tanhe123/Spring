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

        // 解析XML的時候，如果將Namespace打开，则在解析生成document的時候，会查里面是否有Schema，查询xmln。
        // 如果沒有还好，如果有的話，但是链接失效了，那么，就会返回很糟糕的数据。
        factory.setNamespaceAware(false);

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(classPathResource.getInputStream());

        Element root = doc.getDocumentElement();

        NodeList nl = root.getChildNodes();
        for (int i=0; i<nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;


                // 这里locaName啥意思呢?
                // 如果有命名空间比如:
                // xmlns:baidu="http://www.baidu.com"
                /*<websites
                xmlns:sina="http://www.sina.com"
                xmlns:baidu="http://www.baidu.com">

                <sina:website sina:blog="blog.sina.com">新浪</sina:website>
                <baidu:website baidu:blog="hi.baidu.com">百度</baidu:website>
                </websites>*/
                // 那么 sina:blog中, blog就是localName
                if (ele.getNodeName().equals("bean") || ele.getLocalName().equals("bean")) {
                    System.out.println("检测到 bean 元素");

                    String id = ele.getAttribute("id");
                    String nameAttr = ele.getAttribute("name");


                }

                System.out.println(ele);
            }
        }
    }
}
