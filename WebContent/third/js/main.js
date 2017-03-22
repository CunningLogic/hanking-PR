$(function(){
	tabClose();
	tabCloseEven();
	InitLeftMenu();
	clockon();
})
//初始化左侧
var _menus=new Array();
function InitLeftMenu() {
	//获取菜单
	$.ajaxSettings.async = false;

	var users="{\"children\":[{\"text\":\"管理\",\"iconCls\":\"users\",\"children\":[{\"text\":\"用户\",\"iconCls\":\"users\",\"url\":\"manage/user\"},{\"text\":\"服务/联络\",\"iconCls\":\"service\",\"url\":\"manage/service\"}," +
			"{\"text\":\"领导\",\"iconCls\":\"leader\",\"url\":\"manage/leader\"}," +
			"{\"text\":\"配置\",\"iconCls\":\"config\",\"url\":\"manage/config\"},{\"text\":\"群组\",\"iconCls\":\"group\",\"url\":\"manage/groups\"},{\"text\":\"公告\",\"iconCls\":\"group\",\"url\":\"manage/announcement\"}," +
			"{\"text\":\"登录信息\",\"iconCls\":\"group\",\"url\":\"manage/loginInfo\"},{\"text\":\"在线用户\",\"iconCls\":\"group\",\"url\":\"manage/onlineUsers\"},{\"text\":\"红包管理\",\"iconCls\":\"group\",\"url\":\"manage/redbag\"}," +
			"{\"text\":\"限制设置\",\"iconCls\":\"limit\",\"url\":\"manage/limit\"}," +
			"{\"text\":\"应用管理\",\"iconCls\":\"application\",\"url\":\"manage/application\"}," +
			"{\"text\":\"公众号管理\",\"iconCls\":\"publicNumber\",\"url\":\"manage/publicNumber\"}," +
			"{\"text\":\"公众号数据\",\"iconCls\":\"publicdata\",\"url\":\"manage/publicdata\"}," +
			"{\"text\":\"公众号消息\",\"iconCls\":\"publicmsg\",\"url\":\"manage/publicmsg\"}," +
			"{\"text\":\"分享\",\"iconCls\":\"share\",\"url\":\"manage/share\"}" +
			"]}]}"

	_menus=jQuery.parseJSON(users);
	var tmp='';
	if(_menus)
	{
		$.each(_menus.children, function(i, n) {//$.each 遍历_menu中的元素
			var menulist ='<ul class="easyui-tree" lines="true" id="ttttt'+i+'">';
			menulist += '</ul>';
			$('#aa').accordion('add', {
	            title: n.text,
	            content: menulist,
	            iconCls: 'icon ' + n.iconCls
	        });
	    });
	}
	if(_menus)
	{
		$.each(_menus.children, function(i, n) {//$.each 遍历_menu中的元素
			$('#ttttt'+i).tree({data:n.children,
    			onClick: function(node){
					var tabTitle = node.text;//获取超链里span中的内容作为新打开tab的标题
					var url = node.url;
					var menuid = node.url;//获取超链接属性中ref中的内容
					addTab(tabTitle,url,'icon '+menuid);//增加tab
    			}
    		});
	    });
	}
	$.ajaxSettings.async = true;
	$('.easyui-accordion li a').click(function(){//当单击菜单某个选项时，在右边出现对用的内容
		var tabTitle = $(this).children('.nav').text();//获取超链里span中的内容作为新打开tab的标题
		var url = $(this).attr("rel");
		var menuid = $(this).attr("ref");//获取超链接属性中ref中的内容
		addTab(tabTitle,url,'icon '+menuid);//增加tab
		$('.easyui-accordion li div').removeClass("tree-node-selected");
		$(this).parent().parent().addClass("tree-node-selected");
	}).hover(function(){
		$(this).parent().addClass("hover");
	},function(){
		$(this).parent().removeClass("hover");
	});
	//选中第一个
	var panels = $('#aa').accordion('panels');
	if(panels[0])
	{
		var t = panels[0].panel('options').title;
    	$('#aa').accordion('select', t);
	}
}

function addTab(subtitle,url,icon){
	if(!$('#tabs').tabs('exists',subtitle)){
		$('#tabs').tabs('add',{
			title:subtitle,
			content:createFrame(url),
			closable:true,
			icon:icon
		});
	}else{
		$('#tabs').tabs('select',subtitle);
		$('#mm-tabupdate').click();
	}
	tabClose();
}

function createFrame(url)
{
	var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return s;
}

function tabClose()
{
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	})
	/*为选项卡绑定右键*/
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab",subtitle);
		$('#tabs').tabs('select',subtitle);
		return false;
	});
}
//绑定右键菜单事件
function tabCloseEven()
{
	//刷新
	$('#mm-tabupdate').click(function(){
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		if(url)
		{
			$('#tabs').tabs('update',{
				tab:currTab,
				options:{
					content:createFrame(url)
				}
			})
		}
	})
	//关闭当前
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	})
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			if(t!='我的桌面')
			{
				$('#tabs').tabs('close',t);
			}
		});
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		$('#mm-tabcloseright').click();
		$('#mm-tabcloseleft').click();
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			msgShow('系统提示','后面没有啦~~','error');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			if(t!='我的桌面')
			{
				$('#tabs').tabs('close',t);
			}
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			msgShow('系统提示','前面没有啦~~','error');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			if(t!='我的桌面')
			{
				$('#tabs').tabs('close',t);
			}
		});
		return false;
	});

	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	})
}

// 本地时钟
function clockon() {
	var now = new Date();
	var year = now.getFullYear(); // getFullYear getYear
	var month = now.getMonth();
	var date = now.getDate();
	var day = now.getDay();
	var hour = now.getHours();
	var minu = now.getMinutes();
	var sec = now.getSeconds();
	var week;
	month = month + 1;
	if (month < 10)
		month = "0" + month;
	if (date < 10)
		date = "0" + date;
	if (hour < 10)
		hour = "0" + hour;
	if (minu < 10)
		minu = "0" + minu;
	if (sec < 10)
		sec = "0" + sec;
	var arr_week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
	week = arr_week[day];
	var time = "";
	time = year + "年" + month + "月" + date + "日" + " " + hour + ":" + minu
			+ ":" + sec + " " + week;

	$("#bgclock").html(time);

	var timer = setTimeout("clockon()", 200);
}

function photoUpload(){
	var file = $("#userPhoto").val(); 
         if(file==""){ 
             sysMsgShow("请选择上传的头像"); 
             return; 
         } 
         else{ 
             //判断上传的文件的格式是否正确  
             var fileType = file.substring(file.lastIndexOf(".")+1); 
             if(fileType!="png"&&fileType!="jpg"){ 
                 sysMsgShow("上传文件格式错误"); 
                 return; 
             } 
             else{ 
                 var url = "sys/uploadPhoto.action"; 
                 $.ajaxFileUpload({
                     url:url, 
                     secureuri:false, 
                     fileElementId:"userPhoto",        //file的id  
                     dataType:"text",                  //返回数据类型为文本  
                     success:function(data,status){ 
                         if(data=="1"){ 
                             sysMsgShow("请上传文件大小不大于2M的图片"); 
                         }
                         else{
                         	 var path=data.substring(data.indexOf(">")==-1?0:(data.indexOf(">")+1),data.lastIndexOf("<")==-1?data.length:data.lastIndexOf("<")); 
	                     	 $("#srcImgDiv").html('<img id="srcImg"/>');
	                         $("#srcImg").attr("src",path);
	                         $("#srcImg").Jcrop({
								aspectRatio: 1, //选中区域宽高比为1，即选中区域为正方形
								bgColor:"#ccc", //裁剪时背景颜色设为灰色
								bgOpacity:0.1, //透明度设为0.1
								allowResize:true, //不允许改变选中区域的大小
								setSelect:[0,0,100,100], //初始化选中区域
								onChange:showCoords, //当选择区域变化的时候，执行对应的回调函数
								onSelect:showCoords //当选中区域的时候，执行对应的回调函数 
							}); 
                         } 
                     } 
                 }) 
             } 
         } 
}
function showCoords(c) {
	$("#photoX1").val(c.x); //得到选中区域左上角横坐标
	$("#photoY1").val(c.y); //得到选中区域左上角纵坐标
	$("#photoX2").val(c.x2); //得到选中区域右下角横坐标
	$("#photoY2").val(c.y2); //得到选中区域右下角纵坐标
	$("#photoWidth").val(c.w); //得到选中区域的宽度
	$("#photoHeight").val(c.h); //得到选中区域的高度
}
function updatePhoto(){
	//确定更改头像
	if(!$("#srcImg").attr("src"))
	{
		sysMsgShow('请先上传图片!');
		return;
	}
	$.ajax({
		async : false,
		cache:false,
		type: 'POST',
		dataType : "json",
		data : {
			"userPhotoFileName" : $("#srcImg").attr("src"),
			"photoX1" : $("#photoX1").val(),
			"photoY1" : $("#photoY1").val(),
			"photoX2" : $("#photoX2").val(),
			"photoY2" : $("#photoY2").val(),
			"photoWidth" : $("#photoWidth").val(),
			"photoHeight" : $("#photoHeight").val()
		},
		url: 'sys/updatePhoto.action',//请求的action路径
		error: function () {//请求失败处理函数
			sysErrorShow('请求失败!');
		},success:function(data){
			var messgage = "修改成功!";
			if(data.success==true){//未返回任何消息表示添加成功
				$("#photo").attr("src",data.msg);
			}else{//返回异常信息
				messgage = data.msg;
			}
			sysMsgShow(messgage);
		}
	});
}