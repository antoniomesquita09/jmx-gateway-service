package org.jmx.gateway.service.com.service;

import org.jmx.gateway.service.com.entity.CpuInfo;
import org.jmx.gateway.service.com.entity.MemoryInfo;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.management.*;
import javax.management.openmbean.CompositeData;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class JmxService {
    private final JMXServiceURL jmxUrl;
    private final Map<String, Object> environment;
    private final ThreadLocal<JMXConnector> jmxConnectorThreadLocal;
    private final ThreadLocal<MBeanServerConnection> mBeanServerConnectionThreadLocal;


    public JmxService() throws MalformedURLException {
        // Initialize JMX URL and environment
        String hostname = "localhost";
        int port = 2578;

        this.jmxUrl = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + hostname + ":" + port + "/jmxrmi");
        this.environment = new HashMap<>();
        this.jmxConnectorThreadLocal = new ThreadLocal<>();
        this.mBeanServerConnectionThreadLocal = new ThreadLocal<>();
    }

    public synchronized MemoryInfo getMemoryMetric() throws IOException, MalformedObjectNameException, ReflectionException, InstanceNotFoundException {
        JMXConnector connector = jmxConnectorThreadLocal.get();
        MBeanServerConnection connection = mBeanServerConnectionThreadLocal.get();

        if (connector == null || connection == null) {
            // Establish a new connection to JMX server
            connector = JMXConnectorFactory.connect(jmxUrl, environment);
            connection = connector.getMBeanServerConnection();
            jmxConnectorThreadLocal.set(connector);
            mBeanServerConnectionThreadLocal.set(connection);
        }

        ObjectName objectName = new ObjectName("java.lang:type=Memory");
        AttributeList attributes = connection.getAttributes(objectName, new String[]{"HeapMemoryUsage"});
        Attribute heapMemoryUsageAttribute = (Attribute) attributes.get(0);
        CompositeData heapMemoryUsage = (CompositeData) heapMemoryUsageAttribute.getValue();

        // Extract and return the metric value
        long usedMemory = (long) heapMemoryUsage.get("used");
        long committedMemory = (long) heapMemoryUsage.get("committed");
        long maxMemory = (long) heapMemoryUsage.get("max");

        return new MemoryInfo(usedMemory, committedMemory, maxMemory);
    }

    public synchronized CpuInfo getCpuMetric() throws IOException, MalformedObjectNameException, ReflectionException, InstanceNotFoundException, AttributeNotFoundException, MBeanException {
        JMXConnector connector = jmxConnectorThreadLocal.get();
        MBeanServerConnection connection = mBeanServerConnectionThreadLocal.get();

        if (connector == null || connection == null) {
            // Establish a new connection to JMX server
            connector = JMXConnectorFactory.connect(jmxUrl, environment);
            connection = connector.getMBeanServerConnection();
            jmxConnectorThreadLocal.set(connector);
            mBeanServerConnectionThreadLocal.set(connection);
        }
        // Construct the ObjectName for the operating system MBean
        ObjectName osBeanName = new ObjectName("java.lang:type=OperatingSystem");

        // Query the attribute value using MBeanServerConnection
        Double cpuUsage = (Double) connection.getAttribute(osBeanName, "ProcessCpuLoad");

        return new CpuInfo(cpuUsage);
    }
}
