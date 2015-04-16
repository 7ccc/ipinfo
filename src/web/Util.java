package web;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import sun.misc.BASE64Encoder;

public class Util {
	/**
	 * 获取外网IP
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
        if (null == request) {
            return null;
        }
        String proxs[] = { "X-Forwarded-For", "Proxy-Client-IP",
                "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR" };
 
        String ip = null;
 
        for (String prox : proxs) {
            ip = request.getHeader(prox);
            if (isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                continue;
            } else {
                break;
            }
        }
 
        if (isBlank(ip)) {
            return request.getRemoteAddr();
        }
        
        return ip;
    }
	/**
	 * 判断字符串是否为空
	 * @param arg
	 * @return
	 */
	private static boolean isBlank(String arg){
		if(arg == null || "".equals(arg)){
			return true;
		}
		return false;
	}
	/**
	 * 对字节数组进行base64编码
	 * @param img
	 * @return
	 */
	public static String bytesToBase64(byte[] img){
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(img);// 返回Base64编码过的字节数组字符串
	}
	/**
	 * 从"ip-api.com"获取IP信息
	 * @param ip
	 * @return
	 */
	public static JsonEntity getIpInfo(String ip){
		String line = "";
		String json = "";
		try {
			// 生成一个URL对象
			URL url = new URL("http://ip-api.com/json/"+ip);
			// 打开URL
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			// 得到输入流，即获得了网页的内容
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream()));
			// 读取输入流的数据，并显示
			while ((line = reader.readLine()) != null) {
				json += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonEntity je = new JsonEntity();
		String as = json.substring(json.indexOf("as")+5,json.indexOf("city")-3);
		String city = json.substring(json.indexOf("city")+7, json.indexOf("country")-3);
		String region = json.substring(json.indexOf("regionName")+13,json.indexOf("status")-3);
		region += " " + json.substring(json.indexOf("region")+9,json.indexOf("regionName")-3);
		String country = json.substring(json.indexOf("countryCode")+14,json.indexOf("isp")-3);
		String loc = json.substring(json.indexOf("lat")+5,json.indexOf("lon")-3);
		loc += "," + json.substring(json.indexOf("lon")+5,json.indexOf("org")-3);
		String org = json.substring(json.indexOf("org")+6, json.indexOf("query")-3);
		je.setOrg(org).setLoc(loc).setCountry(country).setRegion(region).setCity(city).setIp(ip).setAs(as);
		/*
		 * 对要呈现到网页上的信息格式化
		 */
		StringBuffer sb = new StringBuffer(json);
		sb = sb.insert(sb.indexOf("{"),"\n");
		sb = sb.insert(sb.indexOf("\"as"), "\n\t");
		sb = sb.insert(sb.indexOf("\"city"), "\n\t");
		sb = sb.insert(sb.indexOf("\"country"), "\n\t");
		sb = sb.insert(sb.indexOf("\"countryCode"), "\n\t");
		sb = sb.insert(sb.indexOf("\"isp"), "\n\t");
		sb = sb.insert(sb.indexOf("\"lat"), "\n\t");
		sb = sb.insert(sb.indexOf("\"lon"), "\n\t");
		sb = sb.insert(sb.indexOf("\"org"), "\n\t");
		sb = sb.insert(sb.indexOf("\"query"), "\n\t");
		sb = sb.insert(sb.indexOf("\"region"), "\n\t");
		sb = sb.insert(sb.indexOf("\"regionName"), "\n\t");
		sb = sb.insert(sb.indexOf("\"status"), "\n\t");
		sb = sb.insert(sb.indexOf("\"timezone"), "\n\t");
		sb = sb.insert(sb.indexOf("\"zip"), "\n\t");
		sb = sb.insert(sb.length()-1, "\n");
		je.setJson(sb.toString());
		return je;
	}
	/**
	 * 根据经纬度获取位置图片(获取图片必须能访问谷歌,不能翻墙的话百度'helloDNS')
	 * @param loc
	 * @return 图片的byte数组
	 */
	public static byte[] getLocImg(String loc){  
		InputStream inStream = null;
		try {
	        //new一个URL对象  
	        URL url = new URL("https://maps.googleapis.com/maps/api/staticmap?"
	        		+ "center="+loc+"&zoom=9&size=640x200&sensor=false");  
	        //打开链接  
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
	        //设置请求方式为"GET"  
	        conn.setRequestMethod("GET");  
	        //超时响应时间为10秒  
	        conn.setConnectTimeout(10 * 1000);  
	        //通过输入流获取图片数据  
	        inStream = conn.getInputStream();  
	        //得到图片的二进制数据，以二进制封装得到数据，具有通用性  
		} catch (Exception e) {
			e.printStackTrace();
		}
        return readInputStream(inStream);  
    }
	/**
	 * 将输入流转化为字节数组
	 * @param inStream
	 * @return 
	 */
	public static byte[] readInputStream(InputStream inStream){ 
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        try {
        	 //创建一个Buffer字符串  
            byte[] buffer = new byte[1024];  
            //每次读取的字符串长度，如果为-1，代表全部读取完毕  
            int len = 0;  
            //使用一个输入流从buffer里把数据读取出来  
            while( (len=inStream.read(buffer)) != -1 ){  
                //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
                outStream.write(buffer, 0, len);  
            }  
            //关闭输入流  
            inStream.close();  
            //把outStream里的数据写入内存  
		} catch (Exception e) {
			e.printStackTrace();
		}
        return outStream.toByteArray();  
    }
}
