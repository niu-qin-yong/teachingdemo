<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.it61.minecraft.bean.*"%>
<%@page import="com.it61.minecraft.service.*"%>
<%@page import="com.it61.minecraft.service.impl.*"%>
<%@page import="com.alibaba.fastjson.*" %>
<%@page import="com.alibaba.fastjson.serializer.*" %>
<%@page import="com.it61.minecraft.common.*"%>
<%@page import="java.sql.*" %>

<%
/* 应用路径 */
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

/* 当前用户 */
User user = (User)session.getAttribute("user");
String userString = JSON.toJSONString(user);

/* 获取校友录，也就是当前所有用户 */
UserService userService = new UserServiceImpl();
List<User> schoolmates = userService.getAllUsers();
//将对象序列化为string
String schoolmatesString = JSON.toJSONString(schoolmates);

/* 获取好友 */
FriendService friService = new FriendServiceImpl();
List<Friend> allFriends = friService.getAllFriends(user);
//将好友List序列化为string
String allFriendsString = JSON.toJSONString(allFriends);

/* 获取班级同学 */
List<User> classmates = userService.getClassmates(user);
String classmatesString = JSON.toJSONString(classmates);

/* 获取在线好友 */
//所有在线用户
ArrayList<User> onlineUsers = (ArrayList<User>)getServletContext().getAttribute("online_users");
//在线好友
List<Friend> onlineFriends = new ArrayList<Friend>();
for(Friend fri : allFriends){
	for(User u : onlineUsers){
		if(fri.getFriId() == u.getId()){
			onlineFriends.add(fri);
		}
	}
}
String onlineFriendsString = JSON.toJSONString(onlineFriends); 

/* 获取所有动态 */
//获取自己及好友的id
List<Integer> senderIds = new ArrayList<Integer>();
senderIds.add(user.getId());
for(Friend fri : allFriends){
	senderIds.add(fri.getFriId());
}

MomentService momentService = new MomentServiceImpl();
//获取所有动态
//List<Moment> moments = momentService.getMoments(senderIds);

//分页获取动态，获取首次显示的
Timestamp time = new Timestamp(System.currentTimeMillis());
List<Moment> moments = momentService.getMomentsPaging(senderIds,time.toString(),3);

//获取动态的点赞
FavorService favorService = new FavorServiceImpl();
for(Moment m : moments){
	m.setFavors(favorService.getAllFavorsByMomentId(m.getId()));
}

//获取动态的留言
CommentService commentService = new CommentServiceImpl();
for(Moment m : moments){
	m.setComments(commentService.getAllCommentsByMomentId(m.getId()));
}

PropertyFilter filter = new PropertyFilter(){
	
	@Override
	public boolean apply(Object obj, String name, Object value) {
		//返回false表示过滤
		if(name.equals("picture")){
			return false;
		}
		return true;
	}  
	
};
String momentsString = JSON.toJSONString(moments,filter,SerializerFeature.WriteDateUseDateFormat); 

//获取所有相册
AlbumService service = new AlbumServiceImpl();
List<Album> allAlbums = service.getAllAlbums(user.getId());

//获取相册包含的图片
PictureService picService = new PictureServiceImpl();
for(int i=0;i<allAlbums.size();i++){
	Album album = allAlbums.get(i);
	List<Picture> pics = picService.getPictures(album);
	album.setPics(pics);
}

JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss"; 
String allAlbumsJsonString = JSON.toJSONString(allAlbums,SerializerFeature.WriteDateUseDateFormat);

//获取轮播图图片，规则：先从第一个相册取，如果不够，继续下一个，直到取够或者所有相册都取完
AlbumService albumService = new AlbumServiceImpl();
int count = 5;
List<Picture> bannerPics = albumService.getBannerPics(user.getId(), count);
String bannerPicsString = JSON.toJSONString(bannerPics);
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>我的主页完整布局</title>
		<link rel="stylesheet" href="<%=basePath %>css/index.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>plugin/kalendae/css/kalendae.css">
		<link rel="stylesheet" href="<%=basePath %>css/classroom.css">
		<link rel="stylesheet" href="<%=basePath %>css/schoolfellow.css">
		<link rel="stylesheet" href="<%=basePath %>css/friend.css">
		<link rel="stylesheet" href="<%=basePath %>css/music.css">
		<link rel="stylesheet" href="<%=basePath %>css/album.css">
		<link rel="stylesheet" href="<%=basePath %>css/chat.css">
		<link rel="stylesheet" href="<%=basePath %>plugin/viewer/viewer.css">
	
 	</head>
	<body>
		<!-- 将jsp中的变量转成JavaScript变量，以便在其他js文件中可以引用 -->
		<script type="text/javascript">
			/* 应用路径 */
			var basePath = "<%=basePath%>";
			
			/* 当前用户 */
			var user = JSON.parse('<%=userString%>');
			
			/* 校友录 */
			//将string转成JavaScript中的对象
			var schoolmatesObj = JSON.parse('<%=schoolmatesString%>');
			
			/* 我的好友 */
			var allFriendsObj = JSON.parse('<%=allFriendsString%>');
			
			/* 班级同学 */
			var classmatesObj = JSON.parse('<%=classmatesString%>');
			
			/* 在线好友 */
			var onlineFriendsObj = JSON.parse('<%=onlineFriendsString%>');
			
			/* 所有动态 */
			var momentsObj = JSON.parse('<%=momentsString%>');
			
			/* 所有相册 */
			var albumArrayObj = JSON.parse('<%=allAlbumsJsonString%>');
			
			/* 轮播图 */
			var bannerPicsObj = JSON.parse('<%=bannerPicsString%>');
			var bannerPhotoes = [];
			for(var i=0;i<bannerPicsObj.length;i++){
				var picObj = bannerPicsObj[i];
				var picUrl = basePath+"pictures/"+picObj.userId+"/"+picObj.albumId+"/"+picObj.name;
				var photo = {"i":i+1,"img":picUrl};
				bannerPhotoes.push(photo);
			}
			
			
		</script>
	
		<!-- 用户登录部分 -->
		<%
			String username = user.getUserName();
			String logoutURL = basePath+"servlet/LogoutServlet";
			String encodeLogoutURL = response.encodeURL(logoutURL);
		%>
		<div>欢迎&nbsp;<b><%=username %></b>&nbsp;<a href="<%=encodeLogoutURL%>">注销</a></div>

		<!--外部框架-->
		<div id="base">
		<!--头部部分-->
			<header>
				<!--logo部分-->
				<div id="banner">
					<img src="<%=basePath %>imgs/logo.gif" alt="logo">
				</div>
				<!--顶部导航栏部分-->
				<nav>
					<div id="friendzone0" class="navct">
						<a href="#"> </a>
					</div>
					<div id="photo1" class="navct">
						<a href="#"> </a>
					</div>
					<div id="dairy2" class="navct">
						<a href="#"> </a>
					</div>
					<div id="plan3" class="navct">
						<a href="#"> </a>
					</div>
					<div id="mgame4" class="navct">
						<a href="#"> </a>
					</div>
				</nav>
			</header>
			<!--左边快速导航栏-->
			<aside id="left-side">
				<div id="signIn" onclick="sign()"> </div>
				<ul id="lnav">
					<li id="friends5" class="lnavc">
						<a href="#" > </a>
					</li>
					<li id="classname6" class="lnavc">
						<a href="#" > </a>
					</li>
					<li id="schoolfellow7" class="lnavc">
						<a href="#" > </a>
					</li>
					<li id="music8" class="lnavc">
						<a href="#" > </a>
					</li>
					<li id="setting9" class="lnavc">
						<a href="#" > </a>
					</li>
				</ul>
			</aside>
			<!--主体内容部分-->
			<section id="contentView">
				<div id="content">
					<div id="friendzone" class="showcontent">
						<div id="send" style="margin-bottom: 10px">
							<form onsubmit="return sendMoment()" id="momentform">
							     <textarea name="moment-text" id="moment" cols="80" rows="3" placeholder="说点什么吧"></textarea>
							     <input type="file" name="moment-pic" id="moment-pic" accept="image/png,image/jpeg"/><br/>
                    			 <input type="submit" value="发表"/>
                    		</form>
						</div>
						<div id="loadmore">
							<button onclick="loadMoreMoments()">点击加载更多</button>
						</div>											
					</div>
					<div id="album" class="showcontent">
						<div class="album-header">
							<button onclick="album.showAlbumCreate()">创建相册</button>
						</div>		
									
						<div id="slider">
						</div>
						
						<!-- 相册内容区 -->
						<div class="album-content" id="album-content">
						</div>
					</div>
					<div id="dairy" class="showcontent">
						<div id="dairyleft">
							<div id="operation">
								<div id="opr-left">
									<span> </span><span> </span>
								</div>
								<div id="opr-right">
									<span> </span><span>草稿箱</span>
									<span> </span><span>回收站</span>
								</div>
							</div>
							<div id="dairycontent">
								<table>
									<tr>
										<td><span>[顶]</span><a href="#">我的新年大计划</a></td>
										<td><a href="#">编辑</a><span> </span></td>
									</tr>
									<tr>
										<td><a href="#">女生为什么那么喜欢星座</a></td>
										<td><a href="#">编辑</a><span> </span></td>
									</tr>
									<tr>
										<td><a href="#">科技馆奇妙夜</a></td>
										<td><a href="#">编辑</a><span> </span></td>
									</tr>
									<tr>
										<td><a href="#">门外的班主任</a></td>
										<td><a href="#">编辑</a><span> </span></td>
									</tr>
									<tr>
										<td><a href="#">阿里巴巴的秘密</a></td>
										<td><a href="#">编辑</a><span> </span></td>
									</tr>
									<tr>
										<td><a href="#">12星座谁最具有内在美</a></td>
										<td><a href="#">编辑</a><span> </span></td>
									</tr>
									<tr>
										<td><a href="#">12月10日</a></td>
										<td><a href="#">编辑</a><span> </span></td>
									</tr>
									<tr>
										<td><span>[转]</span><a href="#">我们怎么才能远离雾霾</a></td>
										<td><a href="#">编辑</a><span> </span></td>
									</tr>
									<tr>
										<td><a href="#">编程十法&nbsp;</a></td>
										<td><a href="#">编辑</a><span> </span></td>
									</tr>
									<tr>
										<td><a href="#">00后八大派 你是哪一派？&nbsp;</a></td>
										<td><a href="#">编辑</a><span> </span></td>
									</tr>
									<tr>
										<td><span>[转]</span><a href="#">龙的九个儿子！大家知道几个！</a></td>
										<td><a href="#">编辑</a><span> </span></td>
									</tr>
								</table>
							</div>
						</div>
						<div id="dairyright">
							<!-- 顶部功能区 搜索+日历 -->
							<div id="newshead">
								<input type="text" value="搜索">
								<div id="calendar">
									
								</div>
							</div>
							<!-- 底部热门消息区 -->
							<div id="newslist">
								<div>
									<img src="imgs/d16.png"/>
									<span>惊吓！在喜马拉雅山上驾车时是一种什么体验</span>
									<br/>
									<p>
										<span> </span>
										<a href="#">感兴趣</a>
										<a href="#">121</a>
									</p>
								</div>
								<div>
									<img src="imgs/d17.png"/>
									<span>气象局停发霾预警？回应：正协商联合发布机制</span>
									<br/>
									<p>
										<span> </span>
										<a href="#">感兴趣</a>
										<a href="#">169</a>
									</p>
								</div>
								<div>
									<img src="imgs/d18.png"/>
									<span>亲情是最浓的年味,联合15所高校奏响家庭欢乐颂</span>
									<br/>
									<p>
										<span> </span>
										<a href="#">感兴趣</a>
										<a href="#">234</a>
									</p>
								</div>
							</div>
						</div>
					</div>
					<div id="studyplan" class="showcontent">
						学习计划
					</div>
					<div id="mgame" class="showcontent">
						<div class="userinfo">
                    		<div class="userphoto"> </div>
                    		<div class="username">Chindy</div>
		                    <div class="userlevel"> </div>
		                    <div id="recentgame" class="unitgame">最近游戏</div>
		                    <div id="gamecenter" class="unitgame">游戏大厅</div>
		                </div>
						<div id="gamelist">
		                    <div class="gamelistitem" id="gameitem1">
		                        <div class="gamenum"> </div>
		                        <div class="gamephoto"> </div>
		                        <div class="gamename"> </div>
		                    </div>
		                    <div class="gamelistitem" id="gameitem2">
		                        <div class="gamenum"> </div>
		                        <div class="gamephoto"> </div>
		                        <div class="gamename"> </div>
		                    </div>
		                    <div class="gamelistitem" id="gameitem3">
		                        <div class="gamenum"> </div>
		                        <div class="gamephoto"> </div>
		                        <div class="gamename"> </div>
		                    </div>
		                    <div class="gamelistitem" id="gameitem4">
		                        <div class="gamenum"> </div>
		                        <div class="gamephoto"> </div>
		                        <div class="gamename"> </div>
		                     </div>
		                    <div class="gamelistitem" id="gameitem5">
		                        <div class="gamenum"> </div>
		                        <div class="gamephoto"> </div>
		                        <div class="gamename"> </div>
		                    </div>
		                    <div class="gamelistitem" id="gameitem6">
		                        <div class="gamenum"> </div>
		                        <div class="gamephoto"> </div>
		                        <div class="gamename"> </div>
		                    </div>
		                </div>
					</div>
					<div id="friendd6" class="showcontent">
						<div class="classmask"></div>
		                <div class="friendbgdecoration"></div>
		                <div class="friendcontent" id="friendcontent">
		                	<div class="multichat" onclick="multichat()"></div>
		                </div>
					</div>
					<div id="classname" class="showcontent">
		                <div class="classmask"></div>
		                <div class="classbgdecoration"></div>
		                <div class="classmate" id="classmate">
							       
		                </div>				
					</div>
					<div id="schoolfellows8" class="showcontent">
		                <div class="classmask"></div>
		                <div class="schoolbgdecoration"></div>
		                <div class="schoolfellowcontent" id="schoolfees">
		                </div>						
					</div>
					<div id="music" class="showcontent">
						<div id="music-head">
							<div id="myMusic" onclick="player.showAllMusic()" title="显示全部音乐"></div>
							<div>
								<input type="text" placeholder="输入单曲或歌手,按回车键搜索" id="music_search_input">
							</div>
						</div>
						<div id="music-content">
							<div class="music-list" data-key="1">
								<span></span>
								<!-- 海报 -->
								<div class="music-poster">
									<!-- 相框 -->
									<div class="photo-frame"></div>
								</div>
								<div class="music-share"></div>
								<div class="music-like"></div>
								<div class="music-control"></div>							
							</div>
							<div class="music-list" data-key="2">
								<span></span>
								<!-- 海报 -->
								<div class="music-poster">
									<!-- 相框 -->
									<div class="photo-frame"></div>
								</div>
								<div class="music-share"></div>
								<div class="music-like"></div>
								<div class="music-control"></div>
							</div>						
						</div>
						<div id="music-search">
						</div>
						<audio id="player" autoplay="autoplay" loop="true"></audio>
					</div>
					<form id="settingform" onsubmit="return ajax_submit_setting()">
					<div id="setting" class="showcontent">
						<div class="userinfo">
							
							<div class="userphoto" id="userphoto">
								<a href="javascript:;">
									<input type="file" name="photo" accept="image/png,image/jpeg" onchange="preView(this,$('#userphoto')[0])"/>
								</a>
							</div>
						
							<div class="username">
								<%=user.getUserName() %>
							</div>
							<div > 
								<img id="userlevel"/>
							</div>
						</div>
						<div id="edit">
							<div id="nick">
								<span> </span>
								<input type="text" name="nickname" value="<%=user.getNickName()%>"/>
							</div>
							<div id="age">
								<span> </span>
								<input type="number" min="1" name="age" value="<%=user.getAge()%>"/>
							</div>
							<div id="birthday">
								<span>  </span>
								<input type="date" name="birthday" value="<%=user.getBirth()%>"/>
							</div>
							<div id="star">
								<span> </span>
								<select name="star" id="star-select">
									<option value="<%=Stars.BYZ%>"><%=Stars.BYZ%></option>
									<option value="<%=Stars.JNZ%>"><%=Stars.JNZ%></option>
									<option value="<%=Stars.SHUANGZZ%>"><%=Stars.SHUANGZZ%></option>
									<option value="<%=Stars.JXZ%>"><%=Stars.JXZ%></option>
									<option value="<%=Stars.SHIZZ%>"><%=Stars.SHIZZ%></option>
									<option value="<%=Stars.TCZ%>"><%=Stars.TCZ%></option>
									<option value="<%=Stars.TXZ%>"><%=Stars.TXZ%></option>
									<option value="<%=Stars.SSZ%>"><%=Stars.SSZ%></option>
									<option value="<%=Stars.MJZ%>"><%=Stars.MJZ%></option>
									<option value="<%=Stars.SPZ%>"><%=Stars.SPZ%></option>
									<option value="<%=Stars.SYZ%>"><%=Stars.SYZ%></option>								
								</select>
							</div>
							<div id="mail">
								<span> </span>
								<input type="text" name="email" value="<%=user.getEmail()%>"/>
							</div>
							<div id="myclass">
								<span> </span>
								<select name="grade" id="grade">
									<option value="1">1年级</option>
									<option value="2">2年级</option>
									<option value="3">3年级</option>
									<option value="4">4年级</option>
									<option value="5">5年级</option>
									<option value="6">6年级</option>
									<option value="7">7年级</option>
									<option value="8">8年级</option>
									<option value="9">9年级</option>
								</select>
								<select name="banji" id="banji">
									<option value="1">1班</option>
									<option value="2">2班</option>
									<option value="3">3班</option>
									<option value="4">4班</option>
									<option value="5">5班</option>
									<option value="6">6班</option>
									<option value="7">7班</option>
								</select>
							</div>
							<div id="phone">
								<span> </span>
								<input type="text" value="<%=user.getPhoneNumber()%>" name="phonenumber"/>
							</div>
							<div id="sex">
								<span>  </span>
								<input type="radio" name="gender" <%="male".equals(user.getGender())?"checked":""%> value="male"/>
								<b>男</b>
								<input type="radio" name="gender"  <%="female".equals(user.getGender())?"checked":""%> value="female"/>
								<b>女</b>
							</div>
							<div id="oldpass">
								<span> </span>
								<input  type="password" value="<%=user.getPassword()%>"/>
							</div>
							<div id="newpass" >
								<span> </span>
								<input  type="password" name="newpwd"/>
							</div>
							<div id="sub">
								<input type="submit" value="" />
							</div>
						</div>
						<div id="flower"> </div>
					</div>
					</form>
				</div>
			</section>
			<!--右边在线好友栏-->
			<aside id="right-side">
				<!--泡泡部分-->
				<div id="friendsOnline">
<!-- 					<div class="friendOn" id="friend1"> </div>
					<div class="friendOn" id="friend2"> </div>
					<div class="friendOn" id="friend3"> </div>
					<div class="friendOn" id="friend4"> </div>
					<div class="friendOn" id="friend5"> </div>
					<div class="friendOn" id="friend6"> </div>
					<div class="friendOn" id="friend7"> </div> -->
				</div>
				<div id="right-down">
					<img id="onlinefriend"src="<%=basePath %>imgs/onlinefriend.gif">
				</div>
			</aside>
		</div>
		
		<!-- 音乐点赞数 -->
		<span id="like-count"></span>
		
		<!-- 相册创建对话框 -->
		<div id="album-create">
			<div id="ac-header">
				<div id="ac-header-title">
					创建相册
				</div>
			</div>
			<div id="ac-body">
				<input id="album-create-name" placeholder="相册名称"/>
				<input id="album-create-des" placeholder="相册描述" />
			</div>
			<div id="ac-footer">
				<button id="al-create" onclick="album.hideAlbumCreate()">
					关闭
				</button>
				<button id="al-close" onclick="album.createAlbum()">
					创建
				</button>
			</div>
		</div>		
		<!-- 相册浏览对话框 -->
		<div id="album-browser">
			<div id="ab-header">
				<div id="ab-header-title">
					浏览相册
				</div>
			</div>
			<div class="scroll_wrap">
				<div id="ab-body">
					<ul class="album-pictures" id="pictures-container">
	              	</ul>
				</div>
			</div>
			<div id="ab-footer">
				<button id="ab-close" onclick="album.hideAlbumBrowser()">
					关闭
				</button>
			</div>
		</div>		
		<!-- 聊天对话框 -->
		<div id="chatbox">
			<div id="chatmain">
				<div id="bar">
					与xxx聊天中
				</div>
				<div id="chatborard">
				</div>
				<div>
					<input type="text" id="inputcontent" placeholder="点击按钮或者按回车键发送  /  按ESC键关闭聊天"/>
					<button id="chatsend" onclick="onSend()">
						发送
					</button>
				</div>
			</div>
		</div>			
	</body>
	
	<!-- 引入kalendae日历插件 -->
	<script type="text/javascript" src="<%=basePath %>plugin/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>plugin/kalendae/js/kalendae.js"></script>
	<!-- Viewer -->
	<script type="text/javascript" src="<%=basePath %>plugin/viewer/viewer.js"></script>
	<!-- JS引入 -->
	<script src="<%=basePath %>js/elements.js"></script>
	<script src="<%=basePath %>js/common.js"></script>
	<script src="<%=basePath %>js/bubble.js"></script>
	<script src="<%=basePath %>js/music.js"></script>
	<script src="<%=basePath %>js/slider.js"></script>
	<script src="<%=basePath %>js/main.js"></script>
	<script src="<%=basePath %>js/schoolmates.js"></script>
	<script src="<%=basePath %>js/friends.js"></script>
	<script src="<%=basePath %>js/classmates.js"></script>
	<script src="<%=basePath %>js/setting.js"></script>
	<script src="<%=basePath %>js/friendzone.js"></script>
	<script src="<%=basePath %>js/album.js"></script>
	<script src="<%=basePath %>js/chat.js"></script>
	
	<script type="text/javascript">
		//音乐播放
		player.init();	  
		//显示校友录
		showSchoolmates();
		//显示好友
		showFriends();
		//显示班级同学
		showClassmates();
		//设置界面初始化
		setting.init();
		//显示在线好友
		showOnlineFriends();
		//显示朋友圈动态
		showMoments();
		//相册
		album.initAlbum(); 
		//图片轮播配置
		sliderConfig();
		//聊天
		Chat.initialize();
	</script>
	
</html>
