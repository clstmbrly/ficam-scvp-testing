package gov.treas.pki.vss.rest;

import java.io.IOException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import gov.treas.pki.vss.rest.json.TransactionResult;
import gov.treas.pki.vss.rest.json.VSSResponse;

@Provider
public class ThrowableMapper implements ExceptionMapper<Throwable> {

	/**
	 * Field LOG.
	 */
	private final Logger LOG = LogManager.getLogger(ThrowableMapper.class);

	@Override
	public Response toResponse(Throwable exception) {

		/*
		 * Create the result.
		 */
		VSSResponse result = new VSSResponse();
		TransactionResult tResult = new TransactionResult();
		tResult.transactionResultToken = "SERVICEFAIL";
		tResult.transactionResultText = exception.getMessage();
		result.transactionResult = tResult;
		ObjectMapper mapper = new ObjectMapper();
		/*
		 * Log the result.
		 */
		try {
			String output = mapper.writeValueAsString(result);
			LOG.info("{\"ValidationResponse\":" + output + "}");
		} catch (JsonGenerationException e) {
			LOG.error("Error converting POJO to JSON", e);
		} catch (JsonMappingException e) {
			LOG.error("Error converting POJO to JSON", e);
		} catch (IOException e) {
			LOG.error("Error converting POJO to JSON", e);
		}
		/*
		 * Log, don't swallow, the Stack Trace, since this
		 * is our catch-all
		 */
		LOG.error("Throwable Caught: " + exception.getMessage(), exception);
		/*
		 * Send the result.
		 */
		return Response.ok(result, MediaType.APPLICATION_JSON).build();
	}

}
