<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.it61.minecraft.bean.*"%>
<%@page import="com.it61.minecraft.service.*"%>
<%@page import="com.it61.minecraft.service.impl.*"%>
<%@page import="com.alibaba.fastjson.*" %>
<%@page import="com.alibaba.fastjson.serializer.*" %>

<%
/* 应用路径 */
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

/* 当前用户 */
User user = (User)session.getAttribute("user");

/* 获取校友录，也就是当前所有用户 */
UserService service = new UserServiceImpl();
List<User> schoolmates = service.getAllUsers();
//将对象序列化为string
String schoolmatesString = JSON.toJSONString(schoolmates);
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
	
 	</head>
	<body>
		<!-- 将jsp中的变量转成JavaScript变量，以便在其他js文件中可以引用 -->
		<script type="text/javascript">
			/* 应用路径 */
			var basePath = "<%=basePath%>";
			
			/* 校友录 */
			//将string转成JavaScript中的对象
			var schoolmatesObj = JSON.parse('<%=schoolmatesString%>');
			
		</script>
	
		<!-- 用户登录部分 -->
		<%
			String username = user.getUserName();
			String logoutURL = basePath+"servlet/LogoutServlet";
		%>
		<div>欢迎&nbsp;<b><%=username %></b>&nbsp;<a href="<%=logoutURL%>">注销</a></div>


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
						<div class="remarks">
							<!-- 作者信息 -->
							<div class="remarks-author">
								<img src="imgs/head1.png"/>
								<div class="author-name">
									Chindy
								</div>
								<div class="author-class"></div>
							</div>
							<!-- 说说内容 -->
							<div class="remarks-content">
								<div class="content-text">
									谁说我白，瘦，漂亮，我就跟他做好朋友。
								</div>
								<div class="content-time">
									今天8：15
								</div>
								<img src="imgs/show1.jpg"/>
								<div class="remarks-comments">
									<div class="comment-like">
										<span> </span>
										<img src="imgs/zan1.png">
										<img src="imgs/zan2.png">
									</div>
									<div class="comment-text">
										<span> </span>
										<textarea> </textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="remarks">
							<!-- 作者信息 -->
							<div class="remarks-author">
								<img src="imgs/head1.png"/>
								<div class="author-name">
									Chindy
								</div>
								<div class="author-class"></div>
							</div>
							<!-- 说说内容 -->
							<div class="remarks-content">
								<div class="content-text">
									谁说我白，瘦，漂亮，我就跟他做好朋友。
								</div>
								<div class="content-time">
									今天8：15
								</div>
								<img src="imgs/show1.jpg"/>
								<div class="remarks-comments">
									<div class="comment-like">
										<span> </span>
										<img src="imgs/zan1.png">
										<img src="imgs/zan2.png">
									</div>
									<div class="comment-text">
										<span> </span>
										<textarea> </textarea>
									</div>
								</div>
							</div>
						</div>											
					</div>
					<div id="album" class="showcontent">
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
		                    <div class="friendee" id="friendd1">
		                        <div class="gfriendphoto"></div>
		                        <div class="gfriendname">Cindy</div>
		                        <div class="gfriendchat">聊天</div>
		                    </div>
		                    <div class="friendee" id="friendd2">
		                        <div class="gfriendphoto"></div>
		                        <div class="gfriendname">Jackson</div>
		                        <div class="gfriendchat">聊天</div>
		                    </div>		                
		                	<div class="multichat" onclick="multichat()"></div>
		                </div>
					</div>
					<div id="classname" class="showcontent">
		                <div class="classmask"></div>
		                <div class="classbgdecoration"></div>
		                <div class="classmate" id="classmate">
							<div class="classmatee" id="classmate1">
		                        <div class="jiangpai"></div>
		                        <div class="classmatephoto"></div>
		                        <div class="classmatename">Martha</div>
		                        <div class="classmatecaozuo">
		                            <a href="javascript:void(0)"></a>
		                            <a href="javascript:void(0)"></a>
		                        </div>
		                    </div>
		                    <div class="classmatee" id="classmate2">
		                        <div class="jiangpai"></div>
		                        <div class="classmatephoto"></div>
		                        <div class="classmatename">Fabian</div>
		                        <div class="classmatecaozuo">
		                            <a href="javascript:void(0)"></a>
		                            <a href="javascript:void(0)"></a>
		                        </div>
		                    </div>
		                    <div class="classmatee" id="classmate3">
		                        <div class="jiangpai"></div>
		                        <div class="classmatephoto"></div>
		                        <div class="classmatename">Chindy</div>
		                        <div class="classmatecaozuo">
		                            <a href="javascript:void(0)"></a>
		                            <a href="javascript:void(0)"></a>
		                        </div>
		                    </div>
		                    <div class="classmatee" id="classmate4">
		                        <div class="jiangpai"></div>
		                        <div class="classmatephoto"></div>
		                        <div class="classmatename">Jackson</div>
		                        <div class="classmatecaozuo">
		                            <a href="javascript:void(0)"></a>
		                            <a href="javascript:void(0)"></a>
		                        </div>
		                    </div>
		                    <div class="classmatee" id="classmate5">
		                        <div class="jiangpai"></div>
		                        <div class="classmatephoto"></div>
		                        <div class="classmatename">Jackson</div>
		                        <div class="classmatecaozuo">
		                            <a href="javascript:void(0)"></a>
		                            <a href="javascript:void(0)"></a>
		                        </div>
		                    </div>		                
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
							</div>
							<div > 
								<img id="userlevel"/>
							</div>
						</div>
						<div id="edit">
							<div id="nick">
								<span> </span>
								<input type="text" name="nickname" value=""/>
							</div>
							<div id="age">
								<span> </span>
								<input type="number" min="1" name="age" value=""/>
							</div>
							<div id="birthday">
								<span>  </span>
								<input type="date" name="birthday" value=""/>
							</div>
							<div id="star">
								<span> </span>
								<select name="star" id="star-select">
								</select>
							</div>
							<div id="mail">
								<span> </span>
								<input type="text" name="email" value=""/>
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
								<input type="text" value="" name="phonenumber"/>
							</div>
							<div id="sex">
								<span>  </span>
								<input type="radio" name="gender"  value="male"/>
								<b>男</b>
								<input type="radio" name="gender" value="female"/>
								<b>女</b>
							</div>
							<div id="oldpass">
								<span> </span>
								<input  type="password" value=""/>
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
			
	</body>
	
	<!-- 引入kalendae日历插件 -->
	<script type="text/javascript" src="<%=basePath %>plugin/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>plugin/kalendae/js/kalendae.js"></script>
	<!-- JS引入 -->
	<script src="<%=basePath %>js/elements.js"></script>
	<script src="<%=basePath %>js/common.js"></script>
	<script src="<%=basePath %>js/bubble.js"></script>
	<script src="<%=basePath %>js/music.js"></script>
	<script src="<%=basePath %>js/slider.js"></script>
	<script src="<%=basePath %>js/main.js"></script>
	<script src="<%=basePath %>js/schoolmates.js"></script>
	
	<script type="text/javascript">
		//音乐播放
		player.init();	  
		//显示校友录
		showSchoolmates();
	</script>
	
</html>
