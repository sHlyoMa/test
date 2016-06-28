package com.enya.trainig.web.rest;

import com.enya.trainig.ejb.services.IUserService;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.function.Supplier;

@Path("/hello")
public class HelloWorld {

    @Inject
    private IUserService iUserService;

    @GET
    @Produces("text/plain")
    public String getClichedMessage() throws NamingException {
        Runnable r = () -> System.out.println("Java 8");
        r.run();
        Supplier<String> s = () -> "Java 8";
        return String.format("Hello World EJB = %s and lambda = %s commons-lang test = %s", iUserService.run(), s.get(), StringUtils.isNotBlank("test"));
    }
}
