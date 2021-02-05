package com.pmaxsoft.routes;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

import com.pmaxsoft.dto.Cliente;

public class Routes extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// Expongo servicio para que Aura envie sus datos
		from("platform-http:/cliente/insertar?httpMethodRestrict=POST").unmarshal()
				.json(JsonLibrary.Jackson, Cliente.class)
				.log(LoggingLevel.ERROR, "AURA->EIP", "${body}")
				.process(new TransformarObjeto())
				.end()
				.marshal()
				.json()
				.to("direct:test");
		
		//Consumo el servicio de Odoo para enviar los datos de Aura a su BD
		from("direct:test")
		.log(LoggingLevel.ERROR, "EIP->ODOO", "${body}")
		.removeHeaders("CamelHttp*")
		.setHeader(Exchange.HTTP_METHOD, constant("POST"))
		.to("http://190.155.31.122:8090/insertar");
	}
}
