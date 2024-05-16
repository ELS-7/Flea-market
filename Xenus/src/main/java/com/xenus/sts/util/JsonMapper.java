package com.xenus.sts.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonMapper {

	public JsonMapper(){
		encodingType = JsonEncoding.UTF8;
	}
	
	private JsonEncoding encodingType;
	public void setEncodingType(String encodingType) {
		if (encodingType.equalsIgnoreCase("UTF-8")){
			this.encodingType = JsonEncoding.UTF8;
		}
	}

	public JsonEncoding getEncodingType() {
		return encodingType;
	}	 
	
    private ObjectMapper m = new ObjectMapper();
    private JsonFactory jf = new JsonFactory();

    public <T> Object fromJson(String jsonAsString, Class<T> pojoClass)
    throws JsonMappingException, JsonParseException, IOException {
        return m.readValue(jsonAsString, pojoClass);
    }

    public <T> Object fromJson(FileReader fr, Class<T> pojoClass)
    throws JsonParseException, IOException
    {
        return m.readValue(fr, pojoClass);
    }
    
    public <T> Object fromJson(InputStream is, Class<T> pojoClass)
    throws JsonMappingException, JsonGenerationException, IOException {
    	 return m.readValue(is, pojoClass);
    }
    
    public <T> Object fromJson(BufferedReader br, Class<T> pojoClass)
    throws JsonMappingException, JsonGenerationException, IOException {
    	 return m.readValue(br, pojoClass);
    }
    
    public String toJson(Object pojo, boolean prettyPrint)
    throws JsonMappingException, JsonGenerationException, IOException {
        StringWriter sw = new StringWriter();
        JsonGenerator jg = jf.createJsonGenerator(sw);
        if (prettyPrint) {
            jg.useDefaultPrettyPrinter();
        }
        m.writeValue(jg, pojo);
        return sw.toString();
    }

    public void toJson(Object pojo, FileWriter fw, boolean prettyPrint)
    throws JsonMappingException, JsonGenerationException, IOException {
        JsonGenerator jg = jf.createJsonGenerator(fw);
        if (prettyPrint) {
            jg.useDefaultPrettyPrinter();
        }
        m.writeValue(jg, pojo);
    }
    
    public void toJson(Object pojo, ByteArrayOutputStream os, boolean prettyPrint)
    throws JsonMappingException, JsonGenerationException, IOException {
        JsonGenerator jg = jf.createJsonGenerator(os, JsonEncoding.UTF8);
        if (prettyPrint) {
            jg.useDefaultPrettyPrinter();
        }
        m.writeValue(jg, pojo);
    }
}

