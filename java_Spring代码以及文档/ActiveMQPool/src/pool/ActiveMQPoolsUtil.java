package pool;

import javax.jms.JMSException;

import org.apache.activemq.pool.PooledConnection;
import org.apache.activemq.ActiveMQConnectionFactory;  
import org.apache.activemq.pool.PooledConnectionFactory; 
/**
 * 
 * <p>Title:ActiveMQPoolsUtil</p>
 * @date  2016年9月30日
 */
public class ActiveMQPoolsUtil {
	 /** 
     * 连接 
     */ 
    private static PooledConnection connection;  
    
    private ActiveMQPoolsUtil() {  
   
    }  
    // 初始化连接池等工作  
    static {  
    	//链接的地址
        String url = "failover:(tcp://127.0.0.1:61616)?initialReconnectDelay=1000";  
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();  
        activeMQConnectionFactory.setUserName("system");  
        activeMQConnectionFactory.setPassword("manage");  
        activeMQConnectionFactory.setBrokerURL(url);  
        try {  
   
            PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory(  
                    activeMQConnectionFactory);  
   
            // session数  
            int maximumActive = 200;
            pooledConnectionFactory.setMaximumActiveSessionPerConnection(maximumActive);  
            pooledConnectionFactory.setIdleTimeout(120);  
            pooledConnectionFactory.setMaxConnections(5);  
            pooledConnectionFactory.setBlockIfSessionPoolIsFull(true);  
            connection = (PooledConnection) pooledConnectionFactory.createConnection();  
            // 必须start，否则无法接收消息  
            connection.start();  
        } catch (JMSException e) {  
   
            e.printStackTrace();  
        }  
    }  
   
    /** 
     * 关闭连接 
     */ 
    public static void close() {  
        try {  
            if (connection != null) {  
                connection.close();  
            }  
        } catch (JMSException e) {  
   
            e.printStackTrace();  
        }  
    }  
   
    /** 
     * 获取一个连接  
     */ 
    public static PooledConnection getConnection() {
        return connection;  
    }  
   
    /** 
     * 设置连接 
     */ 
    public static void setConnection(PooledConnection connection) {
        ActiveMQPoolsUtil.connection = connection;  
    }  
}
