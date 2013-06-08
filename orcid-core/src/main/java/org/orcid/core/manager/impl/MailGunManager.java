package org.orcid.core.manager.impl;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class MailGunManager {

    /*
     * From mailguns home page! Yey!
     * 
     * curl -s -k --user api:key-3ax6xnjp29jd6fds4gc373sgvjxteol0 \
     *   https://api.mailgun.net/v2/samples.mailgun.org/messages \
     *    -F from='Excited User <me@samples.mailgun.org>' \
     *    -F to='Dude <dude@mailgun.net>'\
     *    -F to=devs@mailgun.net \
     *    -F subject='Hello' \
     *    -F text='Testing some Mailgun awesomeness!'
     *    
      */
    
    @Value("${com.mailgun.apiKey:key-3ax6xnjp29jd6fds4gc373sgvjxteol0}")
    private String apiKey;
    
    @Value("${com.mailgun.apiUrl:https://api.mailgun.net/v2/samples.mailgun.org/messages}")
    private String apiUrl;
    
    @Value("${com.mailgun.apiUrl:yes}")
    private String testmode;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MailGunManager.class);

    public ClientResponse sendSimpleVerfiyEmail(String from,String to, String subject, String text) {
        
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api",
                getApiKey()));
        WebResource webResource =
                client.resource(getApiUrl());
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", from);
        formData.add("to", to);
        formData.add("subject", subject);
        formData.add("text", text);
        formData.add("o:testmode", testmode);
        LOGGER.debug("Email form data: \n" + formData.toString());
        return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
                post(ClientResponse.class, formData);
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

}
