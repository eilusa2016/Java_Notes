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
	 * @POST –服务接受处理POST 请求
	 * @Path – web服务的URL路径，抓取URL Url <base_url>/bookservice/getbook/{name} ,
	 *       增加:<base_url>/bookservice/addbook
	 * @Produces – 指示响应的MIME类型，在案例中是 application/xml 和 application/json.
	 * @Consumes – 这个服务能消费的请求的MIME类型
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
