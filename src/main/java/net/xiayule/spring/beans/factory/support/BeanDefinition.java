package net.xiayule.spring.beans.factory.support;

/**
 * Created by tan on 14-12-17.
 */
public class BeanDefinition {

    /**
     * 默认的 scope "", 等价于 单例(singleton)
     */
    public static final String SCOPE_DEFAULT = "";

    public static final String SCOPE_SINGLETON = "singleton";

    public static final String SCOPE_PROTOTYPE = "prototype";

    private String parentName;

    /**
     * 如果加载 beanClass 失败，则会传来 class 的字符串名
     * 如果加载成功，则会传来该类的 Class
     */
    private volatile Object beanClass;

    private boolean singleton = true;

    private boolean prototype = false;

    private boolean lazyInit = false;

    private String scope = SCOPE_DEFAULT;


    // set and get methods


    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClass = beanClassName;
    }

    public String getBeanClassName() {
        Object beanClassObject = this.beanClass;
        if (beanClassObject instanceof Class) {
            return (((Class) beanClassObject).getName());
        } else {
            return (String) beanClassObject;
        }
    }

    /**
     * 设置 bean 的 scope 属性
     * <p>默认是单例（singleton）
     * bean 的scope可以从 parent 那里继承
     * 默认的 scope 为 空串, 代表单例(singleton)
     * @see #SCOPE_PROTOTYPE
     * @see #SCOPE_SINGLETON
     */
    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope) || SCOPE_DEFAULT.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    /**
     * 返回 scope 的字符串表示
     * @return
     */
    public String getScope() {
        return this.scope;
    }
}
