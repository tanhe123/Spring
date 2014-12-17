package net.xiayule.spring;


import com.google.common.base.Splitter;
import net.xiayule.spring.beans.factory.support.BeanDefinition;
import net.xiayule.spring.core.io.ClassPathResource;
import net.xiayule.spring.util.StringUtils;
import org.junit.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.stylesheets.LinkStyle;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tan on 14-12-15.
 */
public class Test {
    public static void main(String[] args) throws Exception {
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

                    // 提取别名
                    // spirng中别名是用 [,; ] 分割的
                    List<String> aliases = new ArrayList<String>();
                    if (StringUtils.hasLength(nameAttr)) {
                        String[] nameArr = StringUtils.tokenizeToStringArray(nameAttr, ",; ");
                        aliases.addAll(Arrays.asList(nameArr));

                        System.out.println(aliases);
                    }

                    String beanName = id;
//                    todo: 判断 beanname，不能为空
//                    ...

                    //todo: 检测是否已经重复定义了 beanName
//                    ...

                    // spring 中的parent指的是继承父类中的一些属性
                    // 无需在子类中注入
                    String className = null;
                    if (ele.hasAttribute("class")) {
                        className = ele.getAttribute("class").trim();
                    }

                    String parent = null;
                    if (ele.hasAttribute("parent")) {
                        parent = ele.getAttribute("parent");
                    }

                    BeanDefinition bd = createBeanDefinition(className, parent);

                    parseBeanDefinitionElement(ele, bd);
                }

                System.out.println(ele);
            }
        }
    }

    /**
     * 根据给定的 class name 和 parent name 创建一个 BeanDefinition
     * @param className bean 的 class name
     * @param parentName bean 的 parent 的名字
     * @return 新创建的 bean definition
     */
    public static BeanDefinition createBeanDefinition(String className, String parentName) throws ClassNotFoundException {
        BeanDefinition bd = new BeanDefinition();
        bd.setParentName(parentName);

        if (className != null) {
            //todo: 加载类，如果加载失败


            bd.setBeanClassName(className);
        }
    }

    /**
     * 解析给定的bean element的属性到beandefinition
     * @param ele bean declaration element
     * @param bd bean name
     * @throws Exception
     */
    public static void parseBeanDefinitionElement(Element ele, BeanDefinition bd) throws Exception {
        // 解析 bean 的其他属性
        if (ele.hasAttribute("scope")) {
            // Spring 2.x "scope" attribute
            bd.setScope(ele.getAttribute("scope"));

            if (ele.hasAttribute("singleton")) {
                throw new Exception("Specify either 'scope' or 'singleton', not both");
            }
        } else if (ele.hasAttribute("singleton")) {
            // Spring 1.x "singleton" attribute
            bd.setScope("true".equals(ele.getAttribute("singleton")) ?
                    "scope" : "prototype");
        } /*else if () {
                       //todo: 继承 containingBean 的 scope 属性
                    }*/

        // todo: abstrac 属性

        // todo:  lazy-init 属性

        // todo: autowrire 属性

        // todo: dependency-check 属性

        // todo: depends-on 属性

        // todo: autowire-candidate 属性

        // todo: primary 属性

        // todo: init-method 属性

        // todo: destroy-method 属性

        // todo: factory-method 属性

        // todo: factory-bean 属性


    }
}
