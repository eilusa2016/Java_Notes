package zxn.ws.service;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path(value = "/")
public interface RestfulRegeditService {
    
    @POST
    @Path("/regedit")
    @Consumes(MediaType.APPLICATION_JSON)
    public String regedit(String username, String password) throws IOException;
    
}
