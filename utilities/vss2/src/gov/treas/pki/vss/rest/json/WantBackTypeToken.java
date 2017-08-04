package gov.treas.pki.vss.rest.json;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * This class is a Java representation of the JSON Object wantBackTypeToken.
 * 
 * @author tejohnson
 * 
 * @version $Revision: 1.0 $
 */
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class WantBackTypeToken {

	/**
	 * Field wantBackTypeToken
	 */
	@JsonProperty("wantBackTypeToken")
	public String wantBackTypeToken;

}
