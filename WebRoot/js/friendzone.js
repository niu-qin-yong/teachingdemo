
/*
 *   {
        "content": "还让不让人说话了",
        "id": 5,
        "sendTime": "2017-08-25 15:08:26",
        "senderExperience": 100,
        "senderId": 4,
        "senderName": "Tom"
    }
 */

/**
*发表朋友圈动态
**/		
function sendMoment(){
     var content = $('#moment').val();
     var file = $('#moment-pic')[0];
     
     /* 检测图片大小 */
     if(file.files[0].size > 65536){
    	 $('#moment-pic').val("");
    	 alert("您上传的图片过大，请上传小于64k的图片");
    	 return false;
     }
     
     var formData = new FormData();
     formData.append("moment",content);
     formData.append("pic",file.files[0]);
     
     /* 发送到服务端保存 */
     $.ajax({
          url:basePath+"servlet/MomentServlet",
          type:"post",
          data : formData,
          //发送请求前执行，如果返回false取消请求
          beforeSend:function(xhr){
          },
          // 告诉jQuery不要去处理发送的数据
          processData : false,
          // 告诉jQuery不要去设置Content-Type请求头
          contentType : false,
          success:function(data,status){
        	  
              /* 显示自己发的这条动态在最上面 */
              createMomentElement(JSON.parse(data),true);
              
              /* 清除文本输入框等的内容 */
              $('#moment').val("");
              $('#moment-pic').val("");
          },
          error:function(data,status){
              alert("sendMoment()\n"+data+"========"+status);
          }
     })
     
     
	return false;
}

/*显示首页朋友圈 */
function showMoments(){
	for(var i=0;i<momentsObj.length;i++){
		var moment = momentsObj[i];
		createMomentElement(moment,false);
	}
}

/**
*创建动态的DOM，并将其添加到父元素中
**/
function createMomentElement(moment,top){
	var container = $("<div></div>");
	container.attr("class","remarks");
	container.attr("data-daytime",moment.sendTime);
	
	/* 发布者信息 */
	var author = $("<div></div>");
	author.attr("class","remarks-author");
	//发布者头像
	var photo = $("<img/>");
	photo[0].src = basePath+"servlet/ShowPicServlet?type=user&id="+moment.senderId;
	//发布者名字
	var authorName = $("<div></div>");
	authorName.attr("class","author-name");
	authorName.html(moment.senderName);
	//等级
	var levelImg = $("<img />");
	var level = moment.senderExperience;
	var levelUrl = basePath+"imgs/star-level-1.png";
	if(Math.floor(level/30) == 2){
		levelUrl = basePath+"imgs/star-level-2.png";
	}else if(Math.floor(level/30) == 3){
		levelUrl = basePath+"imgs/star-level-3.png";
	}
	levelImg.attr("src",levelUrl);
	
	var authorClass = $("<div></div>");
	authorClass.attr("class","author-class");
	authorClass.append(levelImg);
	//把头像、名称、等级放到外层 div
	author.append(photo,authorName,authorClass);
	
	/* 动态内容 */
	var content = $("<div></div>");
	content.attr("class","remarks-content");
	//内容
	var text = $("<div></div>");
	text.attr("class","content-text");
	text.html(moment.content);
	//发布时间
	var time = $("<div></div>");
	time.attr("class","content-time");
	time.html(moment.sendTime);
	//动态的图片
	var img = $("<img/>");
	img[0].src=basePath+"servlet/ShowPicServlet?type=moment&id="+moment.id;
	
	/*点赞和留言*/
	var remarksComments = $("<div></div>");
	remarksComments.attr("class","remarks-comments");
	
	/* 点赞 */
	var favor = $("<div></div>");
	
	/* 留言  */
	var comments = $("<div></div>");
	
	remarksComments.append(favor);
	remarksComments.append(comments);
	
	content.append(text,time,img,remarksComments);
	
	container.append(author,content);
	
	//$("#friendzone").append(container);
	
	if(top){
		/* 如果top是true，将动态显示在发送动态框的下面*/
		$('#send').after(container);
	}else{
		/* 显示在加载更多框的上面 */
		$("#loadmore").before(container);			
	}
}

/**
*加载下一组动态
**/
var hasMore = true;
function loadMoreMoments(){
	if(!hasMore){
		alert("亲,没有了！");
		return;
	}
	
	var lastMomentStamp = $('#loadmore').prev().attr("data-daytime");
	var url = basePath+"servlet/LoadMoreMomentServlet?stamp="+lastMomentStamp;
	$.get(url,function(data,status){
		var moreMoments = JSON.parse(data);
		
		if(moreMoments.length == 0){
			hasMore = false;
			alert("亲,没有了！");
			return;
		}
		
		for(var i=0;i < moreMoments.length;i++){
		 	createMomentElement(moreMoments[i],false);
		}
	});
}
