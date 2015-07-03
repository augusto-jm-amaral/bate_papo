package org.projeto.util.gson;

import javax.enterprise.inject.Produces;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonProducers {

	@Produces
	public GsonBuilder gsonBuilderProducer() {
		System.out.println("[DEBUG] GsonProducers gsonBuilderProducer()");
		return new GsonBuilder();
	}

	@Produces
	public Gson gsonProducer(GsonBuilder builder) {
		System.out.println("[DEBUG] GsonProducers gsonProducer()");
		return builder.create();
	}

}
