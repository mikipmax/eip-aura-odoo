package com.pmaxsoft.routes;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

import com.pmaxsoft.dto.Cliente;

public class Routes extends RouteBuilder {
	private final Set<Cliente> clientes = Collections.synchronizedSet(new LinkedHashSet<>());

	@Override
	public void configure() throws Exception {
		
		/*from("platform-http:/cliente/insertar?httpMethodRestrict=POST").
		unmarshal()
				.json(JsonLibrary.Jackson, Cliente.class).process().body(Cliente.class, clientes::add).setBody()
				.constant(clientes).to("direct:test").end().marshal().json(); 
		//Revisar para consumir rest-- descomentar y reparar error
		//con to lanzo otro proceso.
		from("direct:test").setHeader(Exchange.HTTP_METHOD,constant("GET")).
		to("http://jsonplaceholder.typicode.com/posts?bridgeEndpoint=true").
		process(ex->ex.getIn().getBody()).end(); */		
		
		
		
		from("platform-http:/cliente/insertar?httpMethodRestrict=POST").
		unmarshal()
				.json(JsonLibrary.Jackson, Cliente.class).process().body(Cliente.class, clientes::add).setBody()
				.constant(clientes).end().marshal().json(); 
	
		
	}
}
