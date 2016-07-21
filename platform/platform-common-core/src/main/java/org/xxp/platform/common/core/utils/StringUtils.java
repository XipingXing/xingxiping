package org.xxp.platform.common.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * String 字符串工具类
 * 
 * @author xiping xing
 *
 *         Created on 2016年7月20日
 *
 */
public class StringUtils {
  
  /**
   * x表示空格，i标识 : 号，a标识-
   */
  public enum DateFormat{
    
    YYYYaMMaDDxHHimmiss("YYYY-MM-DD HH:mm:ss"),
    
    YYYYaMMaDD("YYYY-MM-DD"),
    
    YYYYMMDDxHHimmiss("YYYYMMDD HH:mm:ss"),
    
    YYYYMMDD("YYYYMMDD");
    
    private String dateFormat;
    
    private DateFormat(String dateFormat){
      this.dateFormat = dateFormat;
    }

    public String getDateFormat() {
      return dateFormat;
    }
  }

  /**
   * StringUtils.isBlank(null)    true
   * StringUtils.isBlank("")    true
   * StringUtils.isBlank("  ")    true
   * StringUtils.isBlank("bbb")    false
   * StringUtils.isBlank(" bbb ")    false
   * 
   * @param str
   * @return
   */
  public static boolean isBlank(String str) {
    int strLen = 0;
    if(str == null || (strLen = str.length()) == 0){
      return true;
    }
    for(int i = 0; i < strLen; i++){
      if(Character.isWhitespace(str.charAt(i)) == false){
        return false;
      }
    }
    return true;
  }

  /**
   * StringUtils.isBlank(null)    false
   * StringUtils.isBlank("")    false
   * StringUtils.isBlank("  ")    false
   * StringUtils.isBlank("bbb")    true
   * StringUtils.isBlank(" bbb ")    true
   * 
   * @param str
   * @return
   */
  public static boolean isNotBlank(String str) {
    return !StringUtils.isBlank(str);
  }
  
  /**
   * StringUtils.isBlank(null)    true
   * StringUtils.isBlank("")    true
   * StringUtils.isBlank("  ")    false
   * StringUtils.isBlank("bbb")    false
   * StringUtils.isBlank(" bbb ")    false
   * 
   * @param str
   * @return
   */
  public static boolean isEmpty(String str){
    return str == null || str.length() == 0;
  }
  
  /**
   * StringUtils.isBlank(null)    false
   * StringUtils.isBlank("")    false
   * StringUtils.isBlank("  ")    true
   * StringUtils.isBlank("bbb")    true
   * StringUtils.isBlank(" bbb ")    true
   * 
   * @param str
   * @return
   */
  public static boolean isNotEmpty(String str){
    return !StringUtils.isEmpty(str);
  }
  
  /**
   * StringUtils.trimToEmpty(null)   ""
   * StringUtils.trimToEmpty("  ")   ""
   * StringUtils.trimToEmpty("bbb")   "bbb"
   * StringUtils.trimToEmpty(" bbb ")   "bbb"
   * 
   * @param str
   * @return
   */
  public static String trimToEmpty(String str){
    return str == null ? "" : str.trim();
  }
  
  /**
   * parse date to string
   * 
   * @param date
   * @param dateFormat
   * @return
   */
  public static String parseDateToString(Date date, DateFormat dateFormat){
    return date == null ? null : new SimpleDateFormat(dateFormat.getDateFormat()).format(date);
  }
  
  /**
   * parse string to date
   * 
   * @param dateStr
   * @param dateFormat
   * @return
   * @throws ParseException
   */
  public static Date parseStringToDate(String dateStr, DateFormat dateFormat) throws ParseException{
    return StringUtils.isBlank(dateStr) ? null : new SimpleDateFormat(dateFormat.getDateFormat()).parse(dateStr);
  }
  
}
