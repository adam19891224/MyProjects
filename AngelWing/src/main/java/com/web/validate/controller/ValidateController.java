package com.web.validate.controller;

import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.FilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.*;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import com.web.validate.factory.CaptchaFactory;
import com.tools.utils.ConUtils;
import com.web.base.controller.BaseController;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/validate")
public class ValidateController extends BaseController {

	private static ConfigurableCaptchaService cs = CaptchaFactory.getInstance();

	private static List<FilterFactory> factories;

	static{
		factories  = ConUtils.arraylist();
		factories.add(new CurvesRippleFilterFactory(cs.getColorFactory()));
		factories.add(new MarbleRippleFilterFactory());
		factories.add(new DoubleRippleFilterFactory());
		factories.add(new WobbleRippleFilterFactory());
		factories.add(new DiffuseRippleFilterFactory());
	}

	@RequestMapping("/getImage")
	public void getImage(HttpServletRequest request, HttpServletResponse response) {

		cs.setFilterFactory(factories.get(new Random().nextInt(5)));

		setResponseHeaders(response);

		try {

			String token = EncoderHelper.getChallangeAndWriteImage(cs, "png", response.getOutputStream());

			Session session = super.getSession();

			session.setAttribute("code", token);

			logger.info("当前的SessionID = " + session.getId() + "，  验证码 = " + token);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setResponseHeaders(HttpServletResponse response) {
		response.setContentType("image/png");
		response.setHeader("Cache-Control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		long time = System.currentTimeMillis();
		response.setDateHeader("Last-Modified", time);
		response.setDateHeader("Date", time);
		response.setDateHeader("Expires", time);
	}
}
