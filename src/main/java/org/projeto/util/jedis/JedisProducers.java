package org.projeto.util.jedis;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@ApplicationScoped
public class JedisProducers {

	@Produces
	private JedisPool jedisPool;

	@PostConstruct
	public void init() {

		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(RedisDBConfig.MAXTOTAL);
		this.jedisPool = new JedisPool(poolConfig, RedisDBConfig.HOST,
				RedisDBConfig.PORT, RedisDBConfig.TIMEOUT,
				RedisDBConfig.PASSWORD);

		System.out.println("[DEBUG] JEDISPOOL INICIADO");

	}

	@Produces
	@RequestScoped
	public Jedis getJedis(JedisPool jedisPool) {
		System.out.println("[DEBUG] getJedis() INVOCADO");
		return jedisPool.getResource();
	}

	public void closeJedis(@Disposes Jedis jedis, JedisPool jedisPool) {
		System.out.println("[DEBUG] closeJedis() INVOCADO");
		jedisPool.returnResourceObject(jedis);
	}

	@PreDestroy
	public void destroy() {
		System.out.println("[DEBUG] destroy() JEDISPRODUCER INVOCADO");
		this.jedisPool.destroy();
	}

}
