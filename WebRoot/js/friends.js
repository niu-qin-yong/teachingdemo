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
		gfriendchat.setAttribute("onclick","javascript:chat('<%=fri.getFriId()%>','<%=fri.getFriName()%>')");
		friendee.appendChild(gfriendchat);
		
		var parent = document.getElementById("friendcontent");
		parent.appendChild(friendee);
	}
}