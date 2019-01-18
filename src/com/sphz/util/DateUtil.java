package com.sphz.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 
 * 说明：日期处理
 * @author DK
 * @version
 */
public class DateUtil {
	
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
	private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat sdfTimes = new SimpleDateFormat("yyyyMMddHHmmss");
	private final static SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy年MM月dd日");

	/**
	 * 获取YYYY格式
	 * @return
	 */
	public static String getSdfTimes() {
		return sdfTimes.format(new Date());
	}
	
	/**
	 * 获取YYYY格式
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}
	
	/**
	 * 获取YYYYMMDD格式
	 * @return
	 */
	public static String getDays(){
		return sdfDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	/**
	* @Title: compareDate
	* @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	* @param s
	* @param e
	* @return boolean  
	* @throws
	* @author fh
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	
	/**
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getDiffYear(String startTime,String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			//long aa=0;
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}
	 
	/**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long 
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate = null;
        java.util.Date endDate = null;
        
            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
      
        return day;
    }
    
    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    
    /**
     * 得到n天之后是周几
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
    	int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        return dateStr;
    }
    
    /**
     * 获取当月第一天日期
     */
	public static String getBeginDataOfMonth() {
		  Calendar ca=Calendar.getInstance();
		  int minmum=ca.getActualMinimum(Calendar.DAY_OF_MONTH);
		  int day=ca.get(Calendar.DAY_OF_MONTH);
		  Calendar cal=(Calendar)ca.clone();
		  cal.add(Calendar.DATE, minmum-day);
		  Date dateX=cal.getTime();
		  String strX=sdfDay.format(dateX);
		  return strX;
	}
	
	/**
     * 获取当月最后一天日期
     */
	public static String getEndDataOfMonth() {
		  Calendar ca=Calendar.getInstance();
		  int maximum = ca.getActualMaximum(Calendar.DAY_OF_MONTH);
		  int day=ca.get(Calendar.DAY_OF_MONTH);
		  Calendar cal=(Calendar)ca.clone();
		  cal.add(Calendar.DATE, maximum-day);
		  Date dateD=cal.getTime();
		  String strD=sdfDay.format(dateD);
		  return strD;
	}
	
	/**
     * 获取前一个月第一天日期
     */
	public static String getBeginDataOfLastMonth() {
	 	Calendar cal=Calendar.getInstance();//获取当前日期 
	    cal.add(Calendar.MONTH, -1);
	    cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
	    String firstDay = sdfDay.format(cal.getTime());
	    return firstDay;
	}
	
	/**
     * 获取前一个月最后一天日期
     */
	public static String getEndDataOfLastMonth() {
	    Calendar cale = Calendar.getInstance();   
	    cale.set(Calendar.DAY_OF_MONTH,0); 
	    String lastDay = sdfDay.format(cale.getTime());
	    return lastDay;
	}
	
	/**
     * 获取前几个月的第一天日期
     */
	public static String getBeginDataOfMonthByInterval(int interval) {
	 	Calendar cal=Calendar.getInstance();//获取当前日期 
	    cal.add(Calendar.MONTH, -interval);
	    cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
	    String firstDay = sdfDay.format(cal.getTime());
	    return firstDay;
	}
	
	
	/**
     * 获取前几个月的最后一天日期
     */
	public static String getEndDataOfLastMonthByInterval(int interval) {
	    Calendar cale = Calendar.getInstance(); 
	    cale.add(Calendar.MONTH, -interval+1);
	    cale.set(Calendar.DAY_OF_MONTH,0); 
	    String lastDay = sdfDay.format(cale.getTime());
	    return lastDay;
	}
	
	/**
     * 根据年和月获取第一天日期
     */
	public static String getBeginDataOfMonthAndYear(int year,int month) {
		Calendar ca=Calendar.getInstance();
		ca.clear();
		ca.set(Calendar.YEAR, year);
		ca.set(Calendar.MONTH, month-1);
		ca.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		String strX=sdfDay.format(ca.getTime());
		return strX;
	}
	
	/**
     * 根据年和月获取最后一天日期
     */
	public static String getEndDataOfMonthAndYear(int year,int month) {
		Calendar ca=Calendar.getInstance();
		ca.clear();
		ca.set(Calendar.YEAR, year);
		ca.set(Calendar.MONTH, month);
		ca.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天 
		String strX=sdfDay.format(ca.getTime());
		return strX;
	}
	
	/**
     * 获取当年第一天日期
     */
	public static String getBeginDataOfYear() {
		  Calendar ca=Calendar.getInstance();
		  ca.set(Calendar.MONTH, 0);
		  ca.set(Calendar.DATE, 1);
		  String strX=sdfDay.format(ca.getTime());
		  return strX;
	}
	
	/**
     * 获取当年最后一天日期
     */
	public static String getEndDataOfYear() {
		  Calendar ca=Calendar.getInstance();
		  ca.set(Calendar.MONTH, 11);
		  ca.set(Calendar.DATE, 31);
		  String strX=sdfDay.format(ca.getTime());
		  return strX;
	}
	
	/**
     * 获取前几年第一天日期
     */
	public static String getBeginDataOfYearByInterval(int interval) {
		  Calendar ca=Calendar.getInstance();
		  ca.add(Calendar.YEAR, -interval);
		  ca.set(Calendar.MONTH, 0);
		  ca.set(Calendar.DATE, 1);
		  String strX=sdfDay.format(ca.getTime());
		  return strX;
	}
	
	/**
     * 获取当几年最后一天日期
     */
	public static String getEndDataOfYearByInterval(int interval) {
		  Calendar ca=Calendar.getInstance();
		  ca.add(Calendar.YEAR,-interval);
		  ca.set(Calendar.MONTH, 11);
		  ca.set(Calendar.DATE, 31);
		  String strX=sdfDay.format(ca.getTime());
		  return strX;
	}
	
	/**
     * 获取当年月2018年8月
     */
	public static String getMonthOfYear() {
		  Calendar ca=Calendar.getInstance();
		  int year=ca.get(Calendar.YEAR);
		  int month=ca.get(Calendar.MONTH)+1;
		  String strX=year+"年"+month+"月";
		  return strX;
	}
	
	/**
     * 获取n月前年月
     */
	public static String getMonthOfYearByInterval(int interval) {
		  Calendar ca=Calendar.getInstance();
		  ca.add(Calendar.MONTH, -interval);
		  int year=ca.get(Calendar.YEAR);
		  int month=ca.get(Calendar.MONTH)+1;
		  String strX=year+"年"+month+"月";
		  return strX;
	}
	
	/**
     * 获取某年某月××年××月
     */
	public static String formatMonthOfYear(int year,int month) {
		  String strX=year+"年"+month+"月";
		  return strX;
	}
	
	/**
     * 获取n月前月份
     */
	public static String getMonthByInterval(int interval) {
		  Calendar ca=Calendar.getInstance();
		  ca.add(Calendar.MONTH, -interval);
		  int month=ca.get(Calendar.MONTH)+1;
		  return month+"月";
	}
	
	/**
     * 获取当年第一天日期（XXXX年XX月XX日）
     */
	public static String getBeginDataCnOfYear() {
		  Calendar ca=Calendar.getInstance();
		  ca.set(Calendar.MONTH, 0);
		  ca.set(Calendar.DATE, 1);
		  String strX=sdfdate.format(ca.getTime());
		  return strX;
	}
	/**
     * 获取前n年当前日期（XXXX-XX-XX）
     */
	public static String getDataByIntervalYear(int interval) {
		  Calendar ca=Calendar.getInstance();
		  ca.add(Calendar.YEAR, -interval);
		  String strX=sdfDay.format(ca.getTime());
		  return strX;
	}
	
	/**
     * 获取前n年年份
     */
	public static String getYearByInterval(int interval) {
		  Calendar ca=Calendar.getInstance();
		  int year=ca.get(Calendar.YEAR)-interval;
		  return String.valueOf(year);
	}
	/**
     * 获取某年第一天日期
     */
	public static String getYearFirst(int year) {
		Calendar ca=Calendar.getInstance();
		ca.clear();
		ca.set(Calendar.YEAR, year);
		String strX=sdfDay.format(ca.getTime());
		return strX;
	}
	/**
     * 获取某年最后一天日期
     */
	public static String getYearLast(int year) {
		Calendar ca=Calendar.getInstance();
		ca.clear();
		ca.set(Calendar.YEAR, year);
		ca.roll(Calendar.DAY_OF_YEAR, -1);
		String strX=sdfDay.format(ca.getTime());
		return strX;
	}
	
	/**
     * string类型yyyy-MM-dd HH:mm:ss转yyyy-MM-dd字符串
     */
	public static String string2yyyyMMdd(String d) {
		if("".equals(d)){
			return "";
		}if(d.contains(" ")){
			String[] split = d.split(" ");
			return String.valueOf(split[0]);
		}
    	return d;
	}
    
    public static void main(String[] args) {
    	System.out.println(getBeginDataOfMonthAndYear(2018, 12));
    	System.out.println(getEndDataOfMonthAndYear(2018, 12));
    }

}
