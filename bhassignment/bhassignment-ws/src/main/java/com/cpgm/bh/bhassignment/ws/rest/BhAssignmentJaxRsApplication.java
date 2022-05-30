package com.cpgm.bh.bhassignment.ws.rest;

import java.awt.FontFormatException;
import java.io.IOException;

import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class BhAssignmentJaxRsApplication extends ResourceConfig {
	
	public BhAssignmentJaxRsApplication() throws FontFormatException, IOException {
		
		super(MultiPartFeature.class);
		packages("com.cpgm.bh.bhassignment.ws.rest");
		
		register(LoggingFeature.class);
	}
}
