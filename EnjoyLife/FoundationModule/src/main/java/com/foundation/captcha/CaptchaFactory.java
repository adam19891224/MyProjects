package com.foundation.captcha;

import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.word.RandomWordFactory;

import java.awt.*;
import java.util.Random;

public class CaptchaFactory {

	private static ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
    
	private static Random random = new Random();
    
	static {
        cs.setColorFactory(x -> {
            int[] c = new int[3];
            int i = random.nextInt(c.length);
            for (int fi = 0; fi < c.length; fi++) {
                if (fi == i) {
                    c[fi] = random.nextInt(71);
                } else {
                    c[fi] = random.nextInt(256);
                }
            }
            return new Color(c[0], c[1], c[2]);
        });
        RandomWordFactory wf = new RandomWordFactory();
        wf.setCharacters("1234567890ABCDEFGHIGKLMNPQRSTUVWXYZ");
        wf.setMaxLength(4);
        wf.setMinLength(4);
        cs.setWordFactory(wf);
    }
	
	public static ConfigurableCaptchaService getInstance(){
		return cs;
	}
}
