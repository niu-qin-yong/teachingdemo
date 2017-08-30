/**
 * 显示好友界面
 * 好友对象的json
 * {
        "friId": 5,
        "friName": "Marry",
        "id": 1,
        "owerId": 4
    }
 */
function showFriends(){
	for(var i=0;i<allFriendsObj.length;i++){
		var fri = allFriendsObj[i];
		
		var friendee=document.createElement("div");
		friendee.setAttribute("class","friendee");
		friendee.setAttribute("id","fri"+fri.friId);
		
		var gfriendphoto=document.createElement("div");
		gfriendphoto.setAttribute("class","gfriendphoto");
		//先显示默认头像，后面再显示真实头像
		var photoURL = basePath+"servlet/ShowPicServlet?id="+fri.friId+"&type=user";
		gfriendphoto.style.backgroundImage="url("+photoURL+")";	
		friendee.appendChild(gfriendphoto);
		
		var gfriendname=document.createElement("div");
		gfriendname.setAttribute("class","gfriendname");
		gfriendname.innerHTML = fri.friName;
		friendee.appendChild(gfriendname);
		
		var gfriendchat=document.createElement("div");
		gfriendchat.setAttribute("class","gfriendchat");
		gfriendchat.innerHTML = "聊天";
		console.log(chat);
		gfriendchat.setAttribute("onclick","chat("+fri.friId+",'"+fri.friName+"')");
		friendee.appendChild(gfriendchat);
		
		var parent = document.getElementById("friendcontent");
		parent.appendChild(friendee);
	}
}

/**
*在线好友泡泡的随机left值
**/
function getRandomLeft(index){
	var rand = Math.random();
	var left;
	if(index%2 != 0){
		//1,3,5
		left = (Math.pow(rand,3)*20)+"px";
	}else{
		//2,4,6
		left = (Math.pow(rand,3)*80)+"px";
	}
	return left;
}

/* 显示在线好友 */
function showOnlineFriends(){
	for(var i=0;i<onlineFriendsObj.length;i++){
		var friend = onlineFriendsObj[i];
		
		var friendOn=document.createElement("div");
		friendOn.setAttribute("class","friendOn");
		friendOn.setAttribute("title",friend.friName);
		var photoURL = basePath+"servlet/ShowPicServlet?type=user&id="+friend.friId;
		friendOn.style.backgroundImage="url("+photoURL+"),url(/minecraft/imgs/pop.jpg)";	
		friendOn.style.left=getRandomLeft(i);
		friendOn.style.top=(i*80)+"px";
		friendOn.setAttribute("data-toUserId",friend.friId);
		friendOn.setAttribute("data-toUserName",friend.friName);
		
		var parent = document.getElementById("friendsOnline");
		parent.appendChild(friendOn);
	}
}