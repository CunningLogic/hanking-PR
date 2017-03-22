
jQuery(document).ready(function() {

    $('.page-container form').submit(function(){
        var username = $(this).find('.username').val();
        var password = $(this).find('.password').val();
        var captcha = $(this).find('.Captcha').val();
        var errmsg = $(this).find('.errmsg');
        if(username == '') {
        	errmsg.html('用户名不能为空！');
            $(this).find('.errbox').fadeOut('fast', function(){
                $(this).css('top', '27px');
            });
            $(this).find('.errbox').fadeIn('fast', function(){
                $(this).parent().find('.username').focus();
            });
            return false;
        }
		
        if(password == '') {
        	errmsg.html('密码不能为空！');
            $(this).find('.errbox').fadeOut('fast', function(){
                $(this).css('top', '96px');
            });
            $(this).find('.errbox').fadeIn('fast', function(){
                $(this).parent().find('.password').focus();
            });
            return false;
        }
        
        if(captcha == '') {
        	errmsg.html('请填写验证码！');
        	$(this).find('.errbox').fadeOut('fast', function(){
        		$(this).css('top', '158px');
        	});
        	$(this).find('.errbox').fadeIn('fast', function(){
        		$(this).parent().find('.Captcha').focus();
        	});
        	return false;
        }
        
//        var captcharight=false;
//        $.ajax({
//			async : false,
//			cache:false,
//			type: 'POST',
//			dataType : "json",
//			data : {
//				"randCode" : captcha
//			},
//			url: 'im/auth/randCodeCheck',//请求的action路径
//			success:function(data){
//				if(data&&data.success){
//					captcharight=true;
//				}
//			}
//		});
//        if(!captcharight){
//        	errmsg.html('验证码错误！')
//        	$(this).find('.errbox').fadeOut('fast', function(){
//        		$(this).css('top', '165px');
//        	});
//        	$(this).find('.errbox').fadeIn('fast', function(){
//        		$(this).parent().find('.Captcha').focus();
//        	});
//        	return false;
//        }
    });

    $('.page-container form .username, .page-container form .password,.page-container form .Captcha').keyup(function(){
        $(this).parent().find('.errbox').fadeOut('fast');
    });

    
});
function refresh(){
	var imgSrc = $("#checkcode");  
    var src = imgSrc.attr("src");  
    imgSrc.attr("src",chgUrl(src));
}
function chgUrl(url){  
    var timestamp = (new Date()).valueOf();  
    url = url.substring(0,16);  
    if((url.indexOf("&")>=0)){  
        url = url + "×tamp=" + timestamp;  
    }else{  
        url = url + "?timestamp=" + timestamp;  
    }  
    return url;  
}  