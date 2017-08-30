/**
 * 
 */

var album = {
		//显示创建相册弹出框
		showAlbumCreate : function(){
			$("#album-create").animate({top:'100px'},300,function(){
			
			});
		},
		//隐藏创建相册弹出框
		hideAlbumCreate : function(){
			$("#album-create").animate({top:'-300px'},300,function(){
				//隐藏后将原有输入数据清空
				$("#album-create-name").val("");
				$("#album-create-des").val("");
			});	
		},		
		//创建相册
		createAlbum : function(){
			
			var self = this;
			//数据
			var name = $("#album-create-name").val();
			var des = $("#album-create-des").val();
			
			if(name == undefined || name == ""){
				alert("亲，相册名不能为空");
				return;
			}
			
			//点击创建后隐藏弹出框
			album.hideAlbumCreate();
			
			$.post(
				basePath+"servlet/CreateAlbumServlet",
				{"name":name,"des":des},
				function(data,status){
					if(status != "success"){
						alert("对不起，创建相册失败！");
						return;
					}
					
					var albumObj = JSON.parse(data);
					self.createEleNode(albumObj);	
				}
			);

		},	
		//创建一个相册对应的DOM
		createEleNode : function(albumObj){
			var container = $("#album-content");
			
			var list = $("<div></div>");
			list.attr("id",albumObj.id);
			list.attr("class","album-list");
			list.attr("title","浏览相册"); 
			//图片路径  pictures/1-2-xx.png  userid-albumid-picname.png
			if(albumObj.pics != null && albumObj.pics != undefined && albumObj.pics.length != 0){
				//取第一张图片做封面
				var cover = basePath+"pictures/"+albumObj.userId+"/"+albumObj.id+"/thumb/"+albumObj.pics[0].name;
				list.css("backgroundImage","url("+cover+")");
			}else{
				//显示默认封面
				var coverURL = basePath + "imgs/default-photo.png";
				list.css("backgroundImage","url("+coverURL+")");
			}
			//设置鼠标悬停效果
			list.on("mouseover",function(event){
				//显示选项
				var opts = $(".opt"+albumObj.id);
				for(var i=0;i < opts.length;i++){
					$(opts[i]).css("display","block");
				}
				
				//隐藏相册名
				$("#name"+albumObj.id).css("display","none");		
			});
			list.on("mouseout",function(event){
				var opts = $(".opt"+albumObj.id);
				for(var i=0;i < opts.length;i++){
					$(opts[i]).css("display","none");
				}
				
				//显示相册名
				$("#name"+albumObj.id).css("display","block");		
			});
			container.append(list);
			
			var uploadOpt = $("<div></div>");
			uploadOpt.attr("class","album-upload"+" opt"+albumObj.id);
			uploadOpt.css("display","none");
			uploadOpt.html("上传图片");
			list.append(uploadOpt);
			
			var input = $("<input />");
			input.attr("name","album-pictures");
			input.attr("type","file");
			input.attr("accept","image/png,image/jpeg");
			input.attr("multiple","multiple");
			//当input元素内容变化后(也即用户选择图像后)的回调函数
			input.on("change",function(event){
				// 实例化一个表单数据对象
				var formData = new FormData();
				
				//哪个用户的哪个相册
				formData.append("userId",user.id);
				formData.append("albumId",albumObj.id);
				formData.append("albumName",albumObj.name);
				
				//获取选择的图片
				var files = $(this)[0].files;
				for(var i = 0;i < files.length;i++){
					var file = files[i];
					formData.append($(this).attr("name"),file);
				}
				
				//AJAX异步上传图片
				$.ajax({
				         url:basePath+"servlet/AlbumUploadServet",
				         type:"post",
				         async: false,//同步 ，true 异步，默认异步
				         data : formData,
				         // 告诉jQuery不要去处理发送的数据
				         processData : false,
				         // 告诉jQuery不要去设置Content-Type请求头
				         contentType : false,
				         success:function(data,status){
				        	console.log("上传图片提交后，服务端返回的状态："+status+"\n数据:"+data+status);
				        	
				         	var obj = JSON.parse(data);
				         	 
							//更新封面
							//取第一张图片做封面
							var cover = basePath+"pictures/"+obj.userId+"/"+obj.id+"/thumb/"+obj.pics[0].name;
							$("#"+obj.id).css("backgroundImage","url("+cover+")");
							
							//浏览相册时看到的就是最新的上传后的图片了
							albumObj = obj;

							alert("恭喜，上传成功!");
				         },
				         error:function(data,status){
				        	 console.log("上传图片失败\nstatus "+status+"\ndata "+JSON.stringify(data));
				             alert("上传图片失败");
				         }
				    })
				
			});
			uploadOpt.append(input);
			
			var browerOpt = $("<span></span>");
			browerOpt.attr("class","album-brower");
			browerOpt.addClass("opt"+albumObj.id);
			browerOpt.css("display","none");
			browerOpt.html("浏览相册");
			//点击浏览相册对应的回调事件
			browerOpt.on("click",function(event){
				if(albumObj.pics == undefined || albumObj.pics == null || albumObj.pics.length == 0){
					alert("没有照片可以浏览！\n亲，请上传你的玉照吧！");
					return;
				}
				
				album.createBrowserNodeEleAndShow(albumObj);
			});
			list.append(browerOpt);
			
			var nameSpan = $("<span></span>");
			nameSpan.attr("id","name"+albumObj.id);
			nameSpan.attr("class","album-name");
			nameSpan.html(albumObj.name);
			list.append(nameSpan);
		},
		//创建相册浏览需要的DOM，并显示弹出框
		createBrowserNodeEleAndShow : function(obj){
			console.log("createBrowserNodeEleAndShow "+JSON.stringify(obj));
			
			var ul = $("#pictures-container");
			//先清除之前的DOM
			ul.empty();
					
			//创建新的
			var picsArray = obj.pics;
			for(var i=0;i < obj.pics.length;i++){
				var picObj = obj.pics[i];
				var li = $("<li></li>");
				ul.append(li);
				var img = $("<img />");
				li.append(img);
				//原图
				img.attr("data-original",basePath+"pictures/"+picObj.userId+"/"+picObj.albumId+"/"+picObj.name);
				//缩略图
				img.attr("src",basePath+"pictures/"+picObj.userId+"/"+picObj.albumId+"/thumb/"+picObj.name);
				img.attr("alt",picObj.name);
			}
			
			//显示弹出框
			$("#album-browser").animate({top:'60px'},500,function(){
				//填充了图片后再创建Viewer对象才有效
				album.initViewer();
			});
			
		},	
		//隐藏相册浏览弹出框
		hideAlbumBrowser : function(){
			$("#album-browser").animate({top:'-1000px'},500,function(){
				//弹出框隐藏后销毁Viewer
				album.destroyViewer();
			});			
		},
		//初始化Viewer
		initViewer : function(){
			var self = this;
			var options = {
				url: 'data-original'
			};
			
			var galley = document.getElementById('pictures-container');
			self.viewer = new Viewer(galley,options);	
		},
		//销毁viewer对象
		destroyViewer : function(){
			var self = this;
			self.viewer.destroy();
		},	
		//初始化相册
		initAlbum : function(){
			for(var i=0;i < albumArrayObj.length;i++){
				var albumObj = albumArrayObj[i];
				this.createEleNode(albumObj);	
			}
			
		}	
}