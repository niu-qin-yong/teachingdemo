var player = {
	  curMusic : null,
	  paused : false,
	  list : [],
	  audio : document.getElementById('player'),
	  init : function(){
	    var self = this;
	    self.list = systemMusicObj;
	    
	    //初始化音乐馆
	    self.createMusicNodeEle(self.list,"music-content","music-list");
	    //更新歌曲信息
	    self.updateView(self.list,"music-list");
	    
	    //初始化我的音乐   
	    self.createMusicNodeEle(mineMusicObj,"music-mine","music-mine-list");
	    self.updateView(mineMusicObj,"music-mine-list");       
	    
	    //初始化好友音乐   
	    self.createMusicNodeEle(friendMusicObj,"music-friend","music-friend-list");
	    self.updateView(friendMusicObj,"music-friend-list");       
	    
	    //监听搜索框
		$("#music_search_input")[0].onkeydown = function(event) {
			if (event.keyCode == 13) {
				self.searchMusic();
			}
		};
	
	  },
	  /*更新界面*/
	  updateView : function(source,subDivClassName){
	    var self = this;
	    var elements = document.querySelectorAll('.'+subDivClassName);
	    for(var i = 0;i < elements.length;i++){
	    
	      var msg = self.find(source,elements[i].dataset.id);
	      if(msg != null && msg != undefined){
		      elements[i].children[0].innerHTML=msg.title;
		      elements[i].children[1].style.backgroundImage = "url("+msg.poster+")"; 
		      //绑定播放按钮的事件
		      elements[i].children[4].onclick = function(){
		        self.bind(this);
		        if(self.curMusic == null || self.curMusic != this){
		          self.audio.src = basePath+self.find(source,this.parentNode.dataset.id).music;
		        }else{
		          self.musicControl();
		        }
		        self.curMusic = this;
		      }
	      }
	    }
	  },
	  //音乐的播放和暂停控制
	  musicControl : function(){
	    var self = this;
	    if(self.paused){
	      self.paused = false;
	      self.audio.play();
	    }else{
	      self.paused = true;
	      self.audio.pause();
	    }
	  },
	  //根据key值查找对应的音乐信息
	  find : function(source,id){
	    for(var i = 0;i < source.length;i++){
	      if(source[i].id == id){
	        return source[i];
	      }
	    }
	  },
	  bind : function(ele){
	    var self = this;
	    //重新绑定事件之前将所有的按钮重置
	    var elements = document.querySelectorAll('.music-control');
	    for(var i = 0;i < elements.length;i++){
	      elements[i].className = "music-control";
	      elements[i].parentNode.children[1].className = "music-poster";
	    }
	    //audio的play和pause事件
	    self.audio.onplay = function(){
	      ele.className = "music-control music-play";
	      ele.parentNode.children[1].className = "music-poster poster-rotate";
	    }
	    self.audio.onpause = function(){
	      ele.className = "music-control";
	      ele.parentNode.children[1].className = "music-poster";
	    }
	  },
	  //参数jsons：要显示的音乐数据，json格式
	  //参数containerId：DOM将要添加到的父元素ID，music-content或者music-search
	  //参数subClassName：子div的class名称，music-list或者search-list
	  createMusicNodeEle : function(jsons,containerId,subClassName){ //创建一首音乐对应的DOM
			for(var i = 0;i < jsons.length;i++){
				var musicJson = jsons[i];
				
				var content = $("#"+containerId);
				
				var item = $("<div></div>");
				item.attr("class",subClassName);
				item.attr("data-id",musicJson.id);
				item.attr("data-music-name",musicJson.title);
				content.append(item);
		
				var span = $("<span></span>");
				item.append(span);
				
				var poster = $("<div></div>");
				poster.attr("class","music-poster");
				item.append(poster);
				
				var share = $("<div></div>");
				share.attr("class","music-share");
				share.attr("id","share"+musicJson.id);
				share.attr("onmouseover","player.showRelay(this)");
				share.attr("onmouseout","player.hideRelay()");
				item.append(share);
				
				var like = $("<div></div>");
				like.attr("class","music-like");
				like.attr("data-like-value",musicJson.likeCount+"喜欢");
				//给点赞按钮设置鼠标滑入划出事件
				like.attr("onmouseover",'player.showLike(this)');
				like.attr("onmouseout",'player.hideLike()');
				like.attr("onclick",'player.addLike(this)');
				item.append(like);
				
				var control = $("<div></div>");
				control.attr("class","music-control");
				item.append(control);
			}
			
		},
		//显示ID是conatinerId的div，并隐藏其他div
		showMusics : function(conatinerId){
			var divs = $(".music-container");
			for(var i=0;i < divs.length;i++){
				var container = $(divs[i]);
				if(container.attr("id") == conatinerId){
					container.css("display","block");
				}else{
					container.css("display","none");
				}
			}
		},
		/*搜索音乐*/
		searchMusic : function(){
			//AJAX获取搜索的音乐
			var self = this;
			var key = $("#music_search_input").val();
			//console.log("searchMusic key:"+key);
			var url = basePath+"servlet/MusicSearchServlet?key="+key;
			$.get(url,function(data,state){
				//console.log("searchMusic data:"+data);
				var musics = JSON.parse(data);
				if(musics.length == 0){
					alert("对不起，没有找到您要的歌曲，试试其他关键字搜索吧！");
					return;
				}
			
				//把之前搜索界面的DOM移除
				$('#music-search').empty();
				//创建新的DOM
				self.createMusicNodeEle(musics,"music-search","search-list");
				//更新界面
				self.updateView(musics,"search-list");
				//显示搜索界面
				self.showMusics("music-search");	
			});
		}
}