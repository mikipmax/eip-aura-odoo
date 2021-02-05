package com.pmaxsoft.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.pmaxsoft.dto.Cliente;
import com.pmaxsoft.dto.Persona;

public class TransformarObjeto implements Processor {

	@Override
	public void process(Exchange e) throws Exception {

		Cliente cli = e.getIn().getBody(Cliente.class);
		Persona pers = null;
		if (cli != null) {
			
			String nombre = cli.nombre;
			String direccion = "Test de transformación: " + String.valueOf(cli.edad);
			pers = new Persona( nombre, direccion);
		}
		e.getOut().setBody(pers); 
		

	}

}
