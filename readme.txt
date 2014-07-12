

问题集：
1.启动报错，找不到{servlet-name}-servlet.xml文件
org.springframework.beans.factory.BeanDefinitionStoreException: IOException parsing XML document from ServletContext resource [/WEB-INF/91date-servlet.xml]; nested exception is java.io.FileNotFoundException: Could not open ServletContext resource [/WEB-INF/91date-servlet.xml]
解决方法：参照web.xml中注释说明

2.在jettyRun启动时，请求404
No mapping found for HTTP request with URI [/mydate/user/showUser.do] in DispatcherServlet with name 'mydate'
解决办法：
在spring-servlet.xml中需要单独加载针对Controller的扫描
<context:component-scan base-package="com.mytime.controller" />

3.tomcat启动报错
Failed to convert property value of type 'java.lang.String' to required type 'javax.sql.DataSource' for property 'dataSource'
解决方法：
错误的把ref="dataSource"写成了value="dataSource"，改程ref就OK了
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"/>
</bean>

4、java.lang.ClassNotFoundException: org.apache.velocity.tools.view.ToolboxManager
解决方法：
引入"org.apache.velocity:velocity-tools:2.0"


5、 SQL异常
解决办法：mybatis中的SQL写法与ibatis有区别，参考下面文章
http://blog.csdn.net/techbirds_bao/article/details/9233599


6、 XMLToolboxManager has been deprecated
XMLToolboxManager has been deprecated. Please use org.apache.velocity.tools.ToolboxFactory instead.
ServletToolboxManager has been deprecated. Please use org.apache.velocity.tools.ToolboxFactory instead.
解决方法：
spring不支持velocity-tool-2.0 需要重载VelocityToolboxView的createVelocityContext方法

7、velocity-tool.xml中定义的date工具在页面上不起作用
解决办法：
同6