package com.web.controller;

import com.foundation.utils.ConUtils;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.FilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.*;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import com.foundation.captcha.CaptchaFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

	@RequestMapping("/getImage.jpg")
	public void getImage(HttpServletRequest request, HttpServletResponse response) {
		try {
			cs.setFilterFactory(factories.get(new Random().nextInt(5)));
			setResponseHeaders(response);

			HttpSession session = super.getSession(request);
			String token = EncoderHelper.getChallangeAndWriteImage(cs, "png", response.getOutputStream());
			session.setAttribute("code", token);

			logger.info("当前的SessionID = " + session.getId() + "，  验证码 = " + token);

		} catch (IOException e) {
			logger.error("获取图片验证码发生异常");
			logger.error(e);
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
