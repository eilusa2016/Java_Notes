package gd.hz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	@RequestMapping("index.htm")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("content", "网站标题");

		// 当设置false时不生成静态页面
		modelAndView.addObject("STATIC_HTML", false);

		return modelAndView;
	}

	@RequestMapping("html/index.htm")
	public ModelAndView htmlIndex() {
		ModelAndView modelAndView = new ModelAndView("index");
		// 默认是生成静态页面的
		modelAndView.addObject("content", "网站标题");
		return modelAndView;
	}

	// jsp测试
	@RequestMapping("jsp/index.htm")
	public ModelAndView jspindex() {
		ModelAndView modelAndView = new ModelAndView("test");
		
		modelAndView.addObject("content", "网站标题");
		return modelAndView;
	}
}
