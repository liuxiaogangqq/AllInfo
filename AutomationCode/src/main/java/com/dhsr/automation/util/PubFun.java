package com.dhsr.automation.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Map.Entry;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;





public class PubFun {
	public static String weburl = "";
	public static String weburls = "";
	//public static String wxurl = "";//加密
	public static String wxurlend = "&response_type=code&scope=snsapi_base&state=dhsrscw2#wechat_redirect";
	 public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";  
	 public static final String LETTERCHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";  
	 public static final String NUMBERCHAR = "0123456789";  
	 protected final Log logger = LogFactory.getLog(this.getClass());
	// private static final String ERROR_LOG_ENTRY_SEPARATOR = ", ";
	 private static final String EMPTY_STRING = new String();
/*	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
			"classpath:applicationContext.xml");*/

	// jdk代理
	//private static BaseService baseService = (BaseService) context.getBean("baseServiceImpl");
	public PubFun() {
	}
	
	/*public static void setwxurl(String AppID){
		wxurl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+AppID+"&redirect_uri=";
		
	}*/
	/**
	 * 得到当前系统日期
	 * 
	 * @return 当前日期的格式字符串,日期格式为"yyyy-MM-dd"
	 */
	public static String getCurrentDate() {
		// String pattern = "yyyy-MM-dd";
		// SimpleDateFormat df = new SimpleDateFormat(pattern);
		// Date today = new Date();
		// String tString = df.format(today);
		// return tString;
		// 运用下面这个方法可以使效率提高
		GregorianCalendar tGCalendar = new GregorianCalendar();
		int sYears = tGCalendar.get(Calendar.YEAR);
		int sMonths = tGCalendar.get(Calendar.MONTH) + 1;
		int sDays = tGCalendar.get(Calendar.DAY_OF_MONTH);
		StringBuffer tStringBuffer = new StringBuffer(10);
		tStringBuffer.append(sYears);
		tStringBuffer.append('-');
		if (sMonths < 10)
			tStringBuffer.append('0');
		tStringBuffer.append(sMonths);
		tStringBuffer.append('-');
		if (sDays < 10)
			tStringBuffer.append('0');
		tStringBuffer.append(sDays);
		return tStringBuffer.toString();
		// 运用下面这个方法可以使效率提高
	}

	/**
	 * 得到当前系统时间
	 * 
	 * @return 当前时间的格式字符串，时间格式为"HH:mm:ss"
	 */
	public static String getCurrentTime() {
		// String pattern = "HH:mm:ss";
		// SimpleDateFormat df = new SimpleDateFormat(pattern);
		// Date today = new Date();
		// String tString = df.format(today);
		// return tString;
		// 运用下面这个方法可以使效率提高
		GregorianCalendar tGCalendar = new GregorianCalendar();
		int sHOUR = tGCalendar.get(Calendar.HOUR_OF_DAY);
		int sMINUTE = tGCalendar.get(Calendar.MINUTE);
		int sSECOND = tGCalendar.get(Calendar.SECOND);
		StringBuffer tStringBuffer = new StringBuffer(8);
		if (sHOUR < 10)
			tStringBuffer.append('0');
		tStringBuffer.append(sHOUR);
		tStringBuffer.append(':');
		if (sMINUTE < 10)
			tStringBuffer.append('0');
		tStringBuffer.append(sMINUTE);
		tStringBuffer.append(':');
		if (sSECOND < 10)
			tStringBuffer.append('0');
		tStringBuffer.append(sSECOND);
		return tStringBuffer.toString();
		// 运用下面这个方法可以使效率提高
	}
	public  static Date getTimeAll(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = getCurrentDate()+" "+getCurrentTime();
		Date d = null;
		try {
			d = sdf.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(d.toLocaleString());
		return d;
	}
	public static Date getDate() {
		java.sql.Date dat = new java.sql.Date(new java.util.Date().getTime());
		return dat;
	}

	public static String getTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");// 只有时分秒
		return sdf.format(date);
	}

	public static String getIp() {
		Enumeration<?> allNetInterfaces;
		InetAddress ip = null;
		String myip = null;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();

			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces
						.nextElement();
				Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					ip = (InetAddress) addresses.nextElement();
					if (ip != null && ip instanceof Inet4Address) {
						myip = ip.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myip;
	}

	public static Date getEndDate(Date d) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		Date endDate = null;
		date.setTime(d);
		date.set(Calendar.DATE, date.get(Calendar.DATE) + 1);
		try {
			endDate = dft.parse(dft.format(date.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return endDate;
	}
	
//	郭   05922355613
	
	public static String getTypeDate(Date d,String type) {
		SimpleDateFormat dft = new SimpleDateFormat(type);
		String dateStr = "";
		try {
			dateStr = dft.format(d);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateStr;
	}

	 public static String getStackTrace(Throwable t)
	    {
	        if (t == null)
	        {
	            return EMPTY_STRING;
	        }
	        try
	        {
	            StringWriter localSw = new StringWriter();
	            PrintWriter localPw = new PrintWriter(localSw);
	            t.printStackTrace(localPw);
	           // StringBuffer msg = new StringBuffer(localSw.toString());
	            return localSw.toString();
	        }
	        catch (Throwable u)
	        {
	            return EMPTY_STRING;
	        }
	    }
	
	public static void methodFile(String file, String conent) {
		BufferedWriter out = null;
		try {
		out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
		out.write(conent+"\r\n");
		} catch (Exception e) {
		e.printStackTrace();
		} finally {
		try {
		 out.close();
		} catch (IOException e) {
		e.printStackTrace();
	  }
	 }
	}
	
	
	
	
	/*
	 * Java文件操作 获取文件扩展名
	 * 
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	
	public static void main(String[] args) {

		//PubFun tPunFun = new PubFun();

		//System.out.println(tPunFun.getCurrentDate());
		//System.out.println(tPunFun.getCurrentTime());

	}
	/**
	 * 生成前缀编号方法
	 * 
	 *//*
	public static String setNumber(String type,String prefix){
		
		Ldmax entity = (Ldmax)baseService.getObjectById(Ldmax.class, type);
		Integer count = entity.getFieldMax();
		Integer length = entity.getFieldLength();
		
		entity.setFieldMax(count+1);
		baseService.saveOrUpdateObject(entity);
		
		if((length - ("" + count).length())>0){
			for (int j = 0; j < (length - ("" + count).length()); j++) {
				prefix += "0";
			}
		}
		return prefix + count;
	}
	*//**
	 * 生成无前缀编号方法
	 * 
	 *//*
	public static String setNumber(String type){
		String number = "";
		Ldmax entity = (Ldmax)baseService.getObjectById(Ldmax.class, type);
		Integer count = entity.getFieldMax();
		Integer length = entity.getFieldLength();
		
		entity.setFieldMax(count+1);
		baseService.saveOrUpdateObject(entity);
		
		if((length - ("" + count).length())>0){
			for (int j = 0; j < (length - ("" + count).length()); j++){
				number += "0";
			}
		}
		return number + count;
	}*/

	/**
	 * 获取类文件名的方法
	 * 
	 */
	public static String getClassFileName(Object o)
    {
        String fileName = o.getClass().getName();
        fileName = fileName.substring(fileName.lastIndexOf(".") + 1);
        return fileName;
    }
	public static String getUrlEncoder(String url){
    	String newurl = "";
    	try {
    		newurl = URLEncoder.encode(url,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return newurl;
    }
	/** 
     * 返回一个定长的随机字符串(只包含大小写字母、数字) 
     *  
     * @param length 
     *            随机字符串长度 
     * @return 随机字符串 
     */  
    public static String generateString(int length) {  
        StringBuffer sb = new StringBuffer();  
        Random random = new Random();  
        for (int i = 0; i < length; i++) {  
           // sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));  
            sb.append(NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length())));  
        }  
        return sb.toString();  
    }  

  //----------------------------------------------------------微信支付公共方法-------------------------------------------------//
  	/**
  	 * 字典排序
  	 * @param paraMap
  	 * @param urlencode
  	 * @return
  	 * @throws Exception
  	 */
  	public static String FormatBizQueryParaMap(Map<String, String> paraMap,
              boolean urlencode) throws Exception {
          String buff = "";
          try {
              List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(
                      paraMap.entrySet());
              Collections.sort(infoIds,
                      new Comparator<Map.Entry<String, String>>() {
                          public int compare(Map.Entry<String, String> o1,
                                  Map.Entry<String, String> o2) {
                              return (o1.getKey()).toString().compareTo(
                                      o2.getKey());
                          }
                      });
              for (int i = 0; i < infoIds.size(); i++) {
                  Map.Entry<String, String> item = infoIds.get(i);
                  //System.out.println(item.getKey());
                  if (item.getKey() != "") {
                      String key = item.getKey();
                      String val = item.getValue();
                      if (urlencode) {
                          val = URLEncoder.encode(val, "utf-8");
                      }
                      buff += key + "=" + val + "&";
                  }
              }
              if (buff.isEmpty() == false) {
                  buff = buff.substring(0, buff.length() - 1);
              }
          } catch (Exception e) {
              throw new Exception(e.getMessage());
          }
          return buff;
      }
  	/**
  	 * MD5加密
  	 * @param content
  	 * @param key
  	 * @return
  	 */
  	public static String sign(String content, String key){
  		
  		String signStr = "";
          signStr = content + "&key=" + key;
          
          return DigestUtils.md5Hex(signStr);
  	}
  	/**
       * 获取支付所需签名
       * @param ticket
       * @param timeStamp
       * @param card_id
       * @param code
       * @return
       * @throws Exception
       */
      public static String getPayCustomSign(Map<String, String> bizObj,String key) throws Exception {
          String bizString = FormatBizQueryParaMap(bizObj,
                  false);
          //logger.info(bizString);
          return sign(bizString, key);
      }
      /**
       * 转化微信支付所需要的参数传递格式（XML）
       * @param arr
       * @return
       */
      public static String ArrayToXml(Map<String, String> arr) {
          String xml = "<xml>";
          Iterator<Entry<String, String>> iter = arr.entrySet().iterator();
          while (iter.hasNext()) {
              Entry<String, String> entry = iter.next();
              String key = entry.getKey();
              String val = entry.getValue();
              if (IsNumeric(val)) {
                  xml += "<" + key + ">" + val + "</" + key + ">";
              } else
                  xml += "<" + key + "><![CDATA[" + val + "]]></" + key + ">";
          }
          xml += "</xml>";
          return xml;
      }


      public static boolean IsNumeric(String str) {
          if (str.matches("\\d *")) {
              return true;
          } else {
              return false;
          }
      }
      /**
       * 获取指定属性值
       * @param pagam
       * @return
       */
      public static String getPagam(String pagam){
      	String result = "";
      	try {
  			Properties prop = new Properties(); 
  	        InputStream in = PubFun.class.getResourceAsStream("/jdbc.properties");
  	        prop.load(in);     ///加载属性列表
  	        result = prop.getProperty(pagam);
  		} catch (Exception e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		};
  		return result;
      } 
      /**
       * 随机订单号
       * @return
       */
      public static  String orderNum(){
          String chars = "0123456789";
          String order = System.currentTimeMillis()+"";
          String res = "";
          for (int i = 0; i < 19; i++) {
              Random rd = new Random();
              res += chars.charAt(rd.nextInt(chars.length() - 1));
          }
          order+=res;
          return order;
      }
    
    
}
