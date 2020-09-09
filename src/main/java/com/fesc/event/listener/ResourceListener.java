package com.fesc.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fesc.event.ResourceEvent;

public class ResourceListener implements ApplicationListener<ResourceEvent> {

	@Override
	public void onApplicationEvent(ResourceEvent event) {
		HttpServletResponse response = event.getResponse();
		Long codigo = event.getCodigo();
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(codigo).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
	}

}
