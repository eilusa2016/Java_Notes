package zxn.ws.service.impl;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import zxn.ws.service.RestfulRegeditService;

@Path(value = "/")
public class RestfulRegeditServiceImpl implements RestfulRegeditService {

	/**
	 * @POST �C������ܴ���POST ����
	 * @Path �C web�����URL·����ץȡURL Url <base_url>/bookservice/getbook/{name} ,
	 *       ����:<base_url>/bookservice/addbook
	 * @Produces �C ָʾ��Ӧ��MIME���ͣ��ڰ������� application/xml �� application/json.
	 * @Consumes �C ������������ѵ������MIME����
	 */
	@Context
	private UriInfo uriInfo;
	@Context
	private Request request;

	@POST
	@Path("/regedit")
	@Produces(MediaType.APPLICATION_JSON)
	public String regedit(String username, String password) throws IOException {
		return username + ";--:" + password;
	}

	@GET
	@Path("/say")
	public String helloWord() {
		return "Justin @ LW";
	}
}
