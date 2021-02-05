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
		// Expongo servicio para que Aura envie el Cliente
		from("platform-http:/cliente/insertar?httpMethodRestrict=POST").unmarshal()
				.json(JsonLibrary.Jackson, Cliente.class).process(new TransformarObjeto()).end().marshal().json().end();

		/*
		 * from("direct:test").removeHeaders("CamelHttp*").setHeader(Exchange.
		 * HTTP_METHOD,constant("GET")).
		 * to("http://jsonplaceholder.typicode.com/posts").log("respuesta: ${body}");
		 */

		/*
		 * from("platform-http:/cliente/insertar?httpMethodRestrict=POST"). unmarshal()
		 * .json(JsonLibrary.Jackson, Cliente.class).process().body(Cliente.class,
		 * clientes::add).setBody() .constant(clientes).end().marshal().json();
		 */

	}
}
