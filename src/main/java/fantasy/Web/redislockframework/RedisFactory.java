package fantasy.Web.redislockframework;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RedisFactory {

	public static JedisPoolConfig getPoolConfig() throws IOException{
		Properties properties = new Properties();
		
		InputStream in = RedisFactory.class.getClassLoader().getResourceAsStream("redis.properties");
		
		try {
			properties.load(in);
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle", "100")));
			config.setMinIdle(Integer.parseInt(properties.getProperty("minIdle", "1")));
			config.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal", "1000")));
            System.err.println(properties.getProperty("maxTotal", "1000"));
            return config;
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static RedisClient getDefaultClient() throws IOException {
		JedisPool pool = new JedisPool(getPoolConfig(),"127.0.0.1", 6379,10000,"123456");
		RedisClient client = new RedisClient(pool);
		return client;
	}
}
