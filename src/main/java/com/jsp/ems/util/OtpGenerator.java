package com.jsp.ems.util;

import java.util.Random;

public class OtpGenerator {

	public static String generatOtp() {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		return String.valueOf(otp);
	}

}
