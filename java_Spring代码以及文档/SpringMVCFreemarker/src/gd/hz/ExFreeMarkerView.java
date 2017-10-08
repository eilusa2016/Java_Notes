package gd.hz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 扩展FreeMarkerView, 使我们可以控制是否生成静态页面以及生成的静态页面存放的位置.
 * 这个的请求优先
 * 但是在视图  ModelAndView中配置的视图  在相关的模版中找不到 
 * 那么它会通过其他的视图解析器来做
 * Title:ExFreeMarkerView
 * @date 2016年10月23日
 */
public class ExFreeMarkerView extends FreeMarkerView {
	
	
	@Override
	protected void doRender(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		exposeModelAsRequestAttributes(model, request);
		SimpleHash fmModel = buildTemplateModel(model, request, response);
		if (logger.isDebugEnabled()) {
			logger.debug("Rendering FreeMarker template [" + getUrl()
					+ "] in FreeMarkerView '" + getBeanName() + "'");
		}
		Locale locale = RequestContextUtils.getLocale(request);
		/*
		 * 在这里我们默认生成静态文件,当ModelAndView有指定STATIC_HTML = false时,就不会输出HTML文件
		 * 例如：ModelAndView modelAndView = new ModelAndView("htmlTest");
		 * modelAndView.addObject("STATICHTML", false);
		 */
		if (Boolean.FALSE.equals(model.get("STATIC_HTML"))) {
			processTemplate(getTemplate(locale), fmModel, response);
		} else {
			createHTML(getTemplate(locale), fmModel, request, response);
		}
		//super.doRender(model, request, response);
	}
	/**
	 * 
	 * @param template
	 * @param model
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws TemplateException
	 * @throws ServletException
	 */
	public void createHTML(Template template, SimpleHash model,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, TemplateException, ServletException {
		// 站点根目录的绝对路径
		String basePath = request.getSession().getServletContext()
				.getRealPath("/");
		String requestHTML = this.getRequestHTML(request);
		// 静态页面绝对路径
		String htmlPath = basePath + requestHTML;
		File htmlFile = new File(htmlPath);
		if (!htmlFile.getParentFile().exists()) {
			htmlFile.getParentFile().mkdirs();
		}

		/**
		 * 如果静态页面已经存在,就不再创建静态页面.
		 */
		if (!htmlFile.exists()) {
			htmlFile.createNewFile();
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(htmlFile), "UTF-8"));
			// 处理模版
			template.process(model, out);
			out.flush();
			out.close();
		}
		/* 将请求转发到生成的htm文件 */
		response.sendRedirect(requestHTML);
		//request.getRequestDispatcher(requestHTML).forward(request, response);
	}

	/**
	 * 生成静态页面的相对路径,
	 * 这里我们可以灵活处理,
	 * 可以决定静态页面的存放路径
	 * @param request
	 * @return
	 */
	private String getRequestHTML(HttpServletRequest request) {
		// web应用名称,部署在ROOT目录时为空
		String contextPath = request.getContextPath();
		// web应用/目录/文件.do
		String requestURI = request.getRequestURI();
		// basePath里面已经有了web应用名称，所以直接把它replace掉，以免重复
		requestURI = requestURI.replaceFirst(contextPath, "");
		String httpPath=request.getRequestURL().toString();
		httpPath=httpPath.replace(requestURI, "");
		// 将.do改为.htm,稍后将请求转发到此htm文件
		requestURI = requestURI.substring(0, requestURI.indexOf(".")) + ".html";
//		requestURI = requestURI + ".html";
		return httpPath+requestURI;
	}
}
