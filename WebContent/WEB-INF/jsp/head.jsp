<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<base href="<%=basePath%>">
<script type="text/javascript" src="jQuery/jquery-1.10.2.js" ></script>
<link rel="stylesheet" type="text/css" href="easyui/css/default.css" />
<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css" />
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery.Jcrop.min.js"></script>
<script type="text/javascript">
	String.prototype.startWith=function(str){
		if(str==null||str==""||this.length==0||str.length>this.length)
		  return false;
		if(this.substr(0,str.length)==str)
		  return true;
		else
		  return false;
		return true;
	}
	String.prototype.trim=function(){
		return this.replace(/(^\s*)|(\s*$)/g, "");
	}
	String.prototype.ltrim=function(){
		return this.replace(/(^\s*)/g,"");
	}
	String.prototype.rtrim=function(){
		return this.replace(/(\s*$)/g,"");
	}
	Date.prototype.format = function (format) {  
	    var o = {  
	        "M+": this.getMonth() + 1,  
	        "d+": this.getDate(),  
	        "h+": this.getHours(),  
	        "m+": this.getMinutes(),  
	        "s+": this.getSeconds(),  
	        "q+": Math.floor((this.getMonth() + 3) / 3),  
	        "S": this.getMilliseconds()  
	    }  
	    if (/(y+)/.test(format)) {  
	        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
	    }  
	    for (var k in o) {  
	        if (new RegExp("(" + k + ")").test(format)) {  
	            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
	        }  
	    }  
	    return format;  
	}  
	/**   
	*转换日期对象为日期字符串   
	* @param date 日期对象   
	* @param isFull 是否为完整的日期数据,   
	*               为true时, 格式如"2000-03-05 01:05:04"   
	*               为false时, 格式如 "2000-03-05"   
	* @return 符合要求的日期字符串   
	*/    
	function getSmpFormatDate(date, isFull) {  
	    var pattern = "";  
	    if (isFull == true || isFull == undefined) {  
	        pattern = "yyyy-MM-dd hh:mm:ss";  
	    } else {  
	        pattern = "yyyy-MM-dd";  
	    }  
	    return getFormatDate(date, pattern);  
	}  
	/**   
	*转换当前日期对象为日期字符串   
	* @param date 日期对象   
	* @param isFull 是否为完整的日期数据,   
	*               为true时, 格式如"2000-03-05 01:05:04"   
	*               为false时, 格式如 "2000-03-05"   
	* @return 符合要求的日期字符串   
	*/    
	
	function getSmpFormatNowDate(isFull) {  
	    return getSmpFormatDate(new Date(), isFull);  
	}  
	/**   
	*转换long值为日期字符串   
	* @param l long值   
	* @param isFull 是否为完整的日期数据,   
	*               为true时, 格式如"2000-03-05 01:05:04"   
	*               为false时, 格式如 "2000-03-05"   
	* @return 符合要求的日期字符串   
	*/    
	
	function getSmpFormatDateByLong(l, isFull) {  
	    return getSmpFormatDate(new Date(l), isFull);  
	}  
	/**   
	*转换long值为日期字符串   
	* @param l long值   
	* @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss   
	* @return 符合要求的日期字符串   
	*/    
	
	function getFormatDateByLong(l, pattern) {  
	    return getFormatDate(new Date(l), pattern);  
	}  
	/**   
	*转换日期对象为日期字符串   
	* @param l long值   
	* @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss   
	* @return 符合要求的日期字符串   
	*/    
	function getFormatDate(date, pattern) {  
	    if (date == undefined) {  
	        date = new Date();  
	    }  
	    if (pattern == undefined) {  
	        pattern = "yyyy-MM-dd hh:mm:ss";  
	    }  
	    return date.format(pattern);  
	}  
	$(function(){
		var t=$('.enterPress');
		$.each(t,function(i,item){
			$(item).textbox('textbox').bind('keydown', function(e){
				if (e.keyCode == 13){	
					$(item).textbox('setValue', $(this).val());
					query();
				}
			})
		});
	})
	
</script>

<%
  if(session.getAttribute("loginuser")==null){
  %>
  <script language="JavaScript" type="text/JavaScript">
	    $(function () {
	    	$("body").eq(0).html("");
	        $.messager.alert('温馨提醒', 'session超时,请重新登录!', "info", function () {
	            window.top.location.href="<%=basePath%>";
	        });
	      
	    });
  </script>
<%}%>