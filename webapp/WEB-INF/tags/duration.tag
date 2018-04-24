<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@tag import="java.util.Date"%>
<%@attribute name="startTime" type="java.util.Date" required="true"%>
<%@attribute name="endTime" type="java.util.Date" required="true"%>
<%
  Date startTime = (Date) jspContext.getAttribute("startTime");
  Date endTime = (Date) jspContext.getAttribute("endTime");

  try {
	    long duration = (endTime.getTime() - startTime.getTime()) / 1000;
	    long day = duration / (24 * 60 * 60);
	    long hour = duration / (60 * 60) % 24;
	    long minute = duration / 60 % 60;
	    long second = duration % 60;

	    // StringBuilder buff = new StringBuilder();
	    String str="";
	   if (day > 0) {
	      //buff.append(day).append("天");
	      str+=day;
	      str+="天";
	    }
	    if (hour > 0) {
	      //buff.append(hour).append("时");
	      str+=hour;
	      str+="时";
	    }
	    if (minute > 0) {
	      //buff.append(minute).append("分");
	      str+=minute;
	      str+="分";
	    }
	    if (second > 0) {
	      //buff.append(second).append("秒");
	      str+=second;
	      str+="秒";
	    } 
	    if (str.length() > 0) {
	      out.print(str);
	    } else {
	      out.print("-");
	    }
  } catch(Exception ex) {
    System.out.println(ex.toString());
  }
%>
