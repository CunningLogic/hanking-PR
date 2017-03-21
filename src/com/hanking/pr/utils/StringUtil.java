package com.hanking.pr.utils;
import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
//import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Clob;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import sun.misc.BASE64Encoder;

/**
 * String Utils
 * 
 * @author Alex Yu
 * 
 */
public final class StringUtil {

	public static final String REGEX_EMAIL = "\\w+.\\w+@(\\w+.)+[a-z]{2,3}";
//	public static final String REGEX_EMAIL = "\\w+@(\\w+.)+[a-z]{2,3}";

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0 || "".equals(str) || "".equals(str.trim());
	}
	
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static String toParamName(String str) {

		return str.replaceAll("\\.", "");

	}

	public static boolean isEqual(String s1, String s2) {
		if (s1 == null && s2 == null) {
			return true;
		}

		if (s1 == null && s2 != null) {
			return false;
		}

		if (s1 != null && s2 == null) {
			return false;
		}

		return s1.equalsIgnoreCase(s2);
	}

	public static String padSuffix(String s, int length) {
		return padSuffix(s, ' ', length);
	}

	public static String padSuffix(String s, char pad, int length) {
		int strLen = s.length();
		if (strLen >= length) {
			return s;
		}
		for (int i = 0; i < length - strLen; i++) {
			s = s + pad;
		}

		return s;
	}

	public static String padPrefix(String s, int length) {
		return padPrefix(s, ' ', length);
	}

	public static String padPrefix(String s, char pad, int length) {
		int strLen = s.length();
		if (strLen >= length) {
			return s;
		}
		for (int i = 0; i < length - strLen; i++) {
			s = pad + s;
		}

		return s;
	}

	public static String getCarrierCode(String flightNumber) {
		if (flightNumber == null || flightNumber.length() < 2) {
			return "";
		}

		return flightNumber.substring(0, 2);
	}

	public static String getFlightNumber(String flightNumber) {
		if (flightNumber == null || flightNumber.length() < 2) {
			return "";
		}

		return flightNumber.substring(2);
	}

	public static String formatNumber(int number, int digit) {
		String s = Integer.toString(number);
		if (s.length() == digit) {
			return s;
		}
		return padPrefix(s, '0', digit);
	}

	@SuppressWarnings("rawtypes")
	public static String serializeList(List list, String delim) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			Object obj = list.get(i);
			if (obj != null) {
				if (i > 0) {
					buffer.append(delim);
				}
				buffer.append(obj.toString());
			}
		}
		return buffer.toString();
	}

	@SuppressWarnings("rawtypes")
	public static String serializeList(List list, String delim, String newline) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			Object obj = list.get(i);
			if (obj != null) {
				if (i > 0) {
					buffer.append(delim);
				}
				buffer.append(obj.toString());
				buffer.append(newline);
			}
		}
		return buffer.toString();
	}

	public static String convertClob(Clob clob) {
		if (clob == null) {
			return "";
		}

		StringBuffer strOut = new StringBuffer();
		String aux = "";
		try {
			BufferedReader br = new BufferedReader(clob.getCharacterStream());
			while ((aux = br.readLine()) != null) {
				strOut.append(aux + "\r\n");
			}
		} catch (Exception ex) {

			return "";
		}
		return strOut.toString();
	}

	@SuppressWarnings("rawtypes")
	public static String getMapValue(Map map, String key) {
		if (map.get(key) == null) {
			return null;
		}
		return map.get(key).toString();
	}

	public static boolean isNumericString(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public static boolean isAlphanumeric(String s) {
		if (isEmpty(s))
			return false;
		for (int i = 0, n = s.length(); i < n; i++) {
			if (!(isNumericChar(s.charAt(i)) || isEnglishChar(s.charAt(i))))
				return false;
		}
		return true;
	}

	public static boolean isNumeric(String s) {
		if (isEmpty(s))
			return false;
		for (int i = 0, n = s.length(); i < n; i++) {
			if (!isNumericChar(s.charAt(i)))
				return false;
		}
		return true;
	}

	public static boolean isAlphabets(String s) {
		if (isEmpty(s))
			return false;
		for (int i = 0, n = s.length(); i < n; i++) {
			if (!isEnglishChar(s.charAt(i)))
				return false;
		}
		return true;
	}

	public static boolean isNumericChar(char c) {
		return (c >= 48 && c <= 57);
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isEnglishChar(char c) {

		return (c >= 65 && c <= 90) || (c >= 97 && c <= 122);

	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmailAddress(String email) {
		if (isEmpty(email))
			return false;
		Pattern p = Pattern.compile(REGEX_EMAIL);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * 利用MD5进行加密
	 * 
	 * @param str
	 *            待加密的字符串
	 * @return 加密后的字符串
	 * @throws NoSuchAlgorithmException
	 *             没有这种产生消息摘要的算法
	 * @throws UnsupportedEncodingException
	 */
	public static String EncoderByMd5(String str)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		if (isEmpty(str))
			return "";
		// 确定计算方法
		/*MessageDigest md5 = MessageDigest.getInstance("MD5");
		BASE64Encoder base64en = new BASE64Encoder();
		// 加密后的字符串
		return base64en.encode(md5.digest(str.getBytes("utf-8")));*/
		
		return "Not yet implemented";
	}
	
	/**
	 * 首字母转小写
	 * 
	 * @param s
	 * @return
	 */
    public static String toLowerCaseFirstOne(String s){
    	
    	StringBuffer sb = new StringBuffer(s);
    	sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
    	return sb.toString();
    	
        /*if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();*/
    }
    
    /**
     * 首字母转大写
     * 
     * @param s
     * @return
     */
    public static String toUpperCaseFirstOne(String s){
    	
    	StringBuffer sb = new StringBuffer(s);
    	sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
    	return sb.toString();
    	
        /*if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();*/
    }
    
    /**
     * 将价格调整比例(adj_proportion)转化成数字
     * @param adj_proportion  价格调整比例
     * @param unitPrice		      单价
     * @return 
     */
    public static double adjProportionToNumber(String adj_proportion,double unitPrice){
    	if(adj_proportion != null){
    		double proportion = 0;//单价乘以比例得出的结果
    		int index = adj_proportion.indexOf("/");
    		int index_1 = adj_proportion.indexOf("%");
    		if(index > 0){
    			int prefixNum = Integer.parseInt(adj_proportion.substring(0, index));//分号前面的数字
    			int SuffixNum = Integer.parseInt(adj_proportion.substring(index+1, adj_proportion.length()));//分号后面的数字
    			proportion = ((prefixNum / SuffixNum) * unitPrice) - unitPrice;
    		}
    		if(index_1 > 0){
    			int prefixNum = Integer.parseInt(adj_proportion.substring(0, index_1));//百分号前面的数字
    			proportion = ((prefixNum / 100) * unitPrice) - unitPrice;
    		}
    		return proportion;
    	}else{
    		return 0;
    	}
    }
	
	public static void main(String[] args){
		String email = "xxx@dji.com";
		System.out.println(StringUtil.isEmailAddress(email));
		
		System.out.println(StringUtil.toUpperCaseFirstOne("xasdfasdf"));
		System.out.println(StringUtil.toLowerCaseFirstOne("ASDFASDFas"));
		System.out.println(10 / 0);
	}

}
