package com.hanking.pr.common;

import java.net.URLEncoder;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacSha1Signature {
	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

	private static String toHexString(byte[] bytes) {
		@SuppressWarnings("resource")
		Formatter formatter = new Formatter();

		for (byte b : bytes) {
			formatter.format("%02x", b);
		}

		return formatter.toString();
	}

	public static String calculateRFC2104HMAC(String data, String key)
			throws Exception {
		SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(),
				HMAC_SHA1_ALGORITHM);
		Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
		mac.init(signingKey);
		return toHexString(mac.doFinal(data.getBytes()));
	}
	
	//9495d21d1c261e8dd8ea7cc40909a846c74a4988   130722FOY-WMSLPI 
	//130722FOY-WMSLPI
	//135987985522
	//13036371369   3loadTelphoneQuery3DdsService3  3loadTrackNumberQuery3DdsService3  track   130722FOY-WMSLPI
	/*	public static void main(String[] args) throws Exception {
		String key = "Dds$Remote*Sync";
		 String dateInfo = "2365";

		String code=HmacSha1Signature.calculateRFC2104HMAC("03Z0787040",key).toUpperCase(Locale.US);
		System.out.println(code+"/////////////");
	}*/
	
	public static void main(String[] args) throws Exception {
		String key = "searchMemberMisService";
		@SuppressWarnings("deprecation")
		String code=HmacSha1Signature.calculateRFC2104HMAC("account=" + URLEncoder.encode(""), key);
		System.out.println(code);
	}
}
