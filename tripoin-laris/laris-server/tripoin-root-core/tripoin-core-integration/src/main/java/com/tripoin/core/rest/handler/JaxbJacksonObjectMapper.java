package com.tripoin.core.rest.handler;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class JaxbJacksonObjectMapper extends ObjectMapper {

	/**
	 * Annotation introspector to use for serialization process 
	 * is configured separately for serialization and deserialization purposes
	 */
	public JaxbJacksonObjectMapper() {
		  final AnnotationIntrospector introspector
		      = new JacksonAnnotationIntrospector();
		  super.getDeserializationConfig()
		       .withAnnotationIntrospector(introspector);
		  super.getSerializationConfig()
		       .withAnnotationIntrospector(introspector);
		  
	}
}