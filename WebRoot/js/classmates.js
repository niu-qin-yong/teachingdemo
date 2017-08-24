/**
 * 显示班级同学
 * 
 * User对象的结构
 *     {
        "age": 0,
        "banji": 1,
        "experience": 30,
        "grade": 1,
        "id": 8,
        "password": "111",
        "phoneNumber": "18622223333",
        "registerTime": 1503460794000,
        "userName": "Jerry"
    }
 */
function showClassmates(){
	
	for(var i=0;i<classmatesObj.length;i++){
		var mate = classmatesObj[i];
	
		/* 创建代表一个好友的div */
		var classmatee=document.createElement("div");
		classmatee.setAttribute("class","classmatee");
		
		//不同等级显示不同图片
		var rank = i;
		var jiangpai=document.createElement("div");
		jiangpai.setAttribute("class","jiangpai");
		if(rank==0){
			//gold
			jiangpai.style.backgroundImage="url("+basePath+"imgs/golden.png)";
		}else if(rank==1){
			//silver
			jiangpai.style.backgroundImage="url("+basePath+"imgs/sliver.png)";
		}else if(rank==2){
			//copper
			jiangpai.style.backgroundImage="url("+basePath+"imgs/tong.png)";
		}
		
		var classmatephoto=document.createElement("div");
		classmatephoto.setAttribute("class","classmatephoto");
		//先使用默认图像
		var defaultPhoto = basePath+"imgs/f1.gif";
		classmatephoto.style.backgroundImage="url("+defaultPhoto+")";	
		
		var classmatename=document.createElement("div");
		classmatename.setAttribute("class","classmatename");
		classmatename.innerHTML = mate.userName;
		
		var classmatecaozuo=document.createElement("div");
		classmatecaozuo.setAttribute("class","classmatecaozuo");
		
		//在自己的条目后面不显示添加好友的按钮
		if(user.id != mate.id){
			var addfriend=document.createElement("a");
			addfriend.setAttribute("href","javascript:;");
			
			if(isFriend(mate.id)){
				//如果是好友，取消好友
				addfriend.setAttribute("title","取消好友");
				addfriend.setAttribute("data-friId",mate.id);
				addfriend.style.backgroundImage="url("+basePath+"imgs/friends-each-other.png)";
				addfriend.setAttribute("onclick","removeFriend(this)");
			}else{
				//如果不是好友，添加好友
				addfriend.setAttribute("title","添加好友");
				addfriend.setAttribute("data-friId",mate.id);
				addfriend.setAttribute("data-friName",mate.userName);
				addfriend.setAttribute("onclick","addFriend(this)");
			}
			classmatecaozuo.appendChild(addfriend);
		}
		
		classmatee.appendChild(jiangpai);
		classmatee.appendChild(classmatephoto);
		classmatee.appendChild(classmatename);
		classmatee.appendChild(classmatecaozuo);
		
		/* 将好友div添加到父元素中 */
		var parent = document.getElementById("classmate");
		parent.appendChild(classmatee);
		
	}
}

/**
*判断是否相互是好友
*
*好友对象的json
 * {
        "friId": 5,
        "friName": "Marry",
        "id": 1,
        "owerId": 4
    }
**/
function isFriend(friendId){
	for(var i=0;i<allFriendsObj.length;i++){
		var fri = allFriendsObj[i];
		if(friendId == fri.friId){
			return true;
		}
	}
	return false;
}

/**
*添加好友，friendId是好友id
**/
function addFriend(a){
	var friId = a.getAttribute("data-friId");
	var friName = a.getAttribute("data-friName");
	
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function(){
       if(xmlhttp.readyState==4 && xmlhttp.status == 200){
    	   if(xmlhttp.responseText == "add_friend_ok"){
     	    a.style.backgroundImage="url("+basePath+"imgs/friends-each-other.png)";
			a.setAttribute("title","移除好友");
			a.setAttribute("data-friId",a.getAttribute("data-friId"));
			a.setAttribute("onclick",'removeFriend(this)');  
		
			//更新好友界面
			//addEleFromFriends(a.getAttribute("data-friId"),a.getAttribute("data-friName"));
    	   }
       }
    }
    xmlhttp.open("post",basePath+"servlet/AddFriendServlet",true);
    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    xmlhttp.send("friendId="+friId+"&friendName="+friName);
}

/**
*移除好友关系
**/
function removeFriend(a){
	var owerId = user.id;
	var friId = a.getAttribute("data-friId");
	
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function(){
       if(xmlhttp.readyState==4 && xmlhttp.status == 200){
    	   if(xmlhttp.responseText == "remove_friend_ok"){
     	    a.style.backgroundImage="url("+basePath+"imgs/jiafriend.png)";
	a.setAttribute("title","添加好友");
	a.setAttribute("onclick",'addFriend(this)'); 
	
	//更新好友界面
	removeEleFromFriends(a.getAttribute("data-friId"));
    	   }
       }
    }
    xmlhttp.open("post",basePath+"servlet/RemoveFriendServlet",true);
    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    xmlhttp.send("owerId="+owerId+"&friId="+friId);			
}