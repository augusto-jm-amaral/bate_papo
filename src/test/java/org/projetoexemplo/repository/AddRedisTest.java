package org.projetoexemplo.repository;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.projeto.model.Usuario;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class AddRedisTest {

	private static JedisPool jedisPool;
	private static Jedis jedis;

	@Before
	public void init() {
		AddRedisTest.jedis = jedisPool.getResource();
	}

	@BeforeClass
	public static void initClass() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(10);
		AddRedisTest.jedisPool = new JedisPool(poolConfig, "127.0.0.1", 9999, 2000, "root");
	}

	@After
	public void destroy() {
		AddRedisTest.jedisPool.returnResourceObject(AddRedisTest.jedis);
	}

	@AfterClass
	public static void destroyClass() {
		AddRedisTest.jedisPool.destroy();
	}

	@Test
	public void addUsuario() {

		Usuario usuario = new Usuario();
		usuario.setId(1);
		usuario.setNome("Jao Jose Joelma");
		usuario.setEmail("jao@email.com");
		usuario.setSenha("123");

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(usuario);

		AddRedisTest.jedis.set("usuario:" + usuario.getEmail(), json);
	}
}
