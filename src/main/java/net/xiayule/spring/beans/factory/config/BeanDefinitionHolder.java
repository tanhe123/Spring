package net.xiayule.spring.beans.factory.config;

import net.xiayule.spring.beans.factory.support.BeanDefinition;
import org.junit.Assert;

/**
 * Created by tan on 14-12-18.
 */
public class BeanDefinitionHolder {
    private final BeanDefinition beanDefinition;
    private final String beanName;
    private final String[] aliases;

    /**
     * 创建一个新的 BeanDefinition
     * @param beanDefinition 要wrap的BeanDefinition
     * @param beanName bean 的 name 属性
     */
    public BeanDefinitionHolder(BeanDefinition beanDefinition, String beanName) {
        this(beanDefinition, beanName, null);
    }

    /**
     * 创建一个新的 BeanDefinition
     * @param beanDefinition 要wrap的BeanDefinition
     * @param beanName bean 的 name 属性
     * @param aliases bean 的别名(alias), 可能为 <code>null</code>
     */
    public BeanDefinitionHolder(BeanDefinition beanDefinition, String beanName, String[] aliases) {
        Assert.assertNotNull("BeanDefinition 不能为 NULL", beanDefinition);
        Assert.assertNotNull("Bean name 不能为 NULL");
        this.beanDefinition = beanDefinition;
        this.aliases = aliases;
        this.beanName = beanName;
    }

    public BeanDefinitionHolder(BeanDefinitionHolder beanDefinitionHolder) {
        Assert.assertNotNull("BeanDefinition 不能为 NULL");
        this.beanDefinition = beanDefinitionHolder.beanDefinition;
        this.beanName = beanDefinitionHolder.beanName;
        this.aliases = beanDefinitionHolder.getAliases();
    }


    // set and get methods

    public String[] getAliases() {
        return aliases;
    }

    public BeanDefinition getBeanDefinition() {
        return beanDefinition;
    }

    public String getBeanName() {
        return beanName;
    }
}
