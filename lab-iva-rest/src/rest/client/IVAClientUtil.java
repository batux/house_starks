package rest.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * 
 * @author Batuhan Duzgun (batux) Computer Engineer / Industrial Engineer
 *
 */

public class IVAClientUtil {

	private static Client client = null;
	private static IVAClient ivaClient = null;
	private static ObjectMapper objectMapper = null;
	private static SimpleDateFormat dateFormat = null;
	
	public static void createIvaClient(String baseUrl) {
		if(ivaClient == null) {
			ivaClient = new IVAClient();
			ivaClient.setBaseUrl(baseUrl);
		}
	}
	
	public static IVAClient getIvaClient() {
		return ivaClient;
	}
	
	public static WebResource getWebResource(String url) {
		if(client == null) { client = Client.create(); }
		return client.resource(url);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getResponse(String json, Class<?> responseType) {
		if(objectMapper == null) { objectMapper = new ObjectMapper(); }
		try {
			if(responseType == String.class) { return (T) json; }
			return (T) objectMapper.readValue(json.getBytes(), responseType);
		}
		catch (JsonParseException e) { e.printStackTrace(); } 
		catch (JsonMappingException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); }
		return null;
	}
	
	public static String convertToIvaDateFormat(Date date) {
		if(dateFormat == null) { dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH.mm.ss.SSS"); }
		return dateFormat.format(date);
	}
}
