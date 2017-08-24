/**
*显示校友录
**/		
function showSchoolmates(){
	
	for(var i=0;i<schoolmatesObj.length;i++){
		var smate = schoolmatesObj[i];
	
		var schoolfee=document.createElement("div");
		schoolfee.setAttribute("class","schoolfee");
		
		var schoolephoto=document.createElement("div");
		schoolephoto.setAttribute("class","schoolephoto");
		//显示用户头像
		var photoURL = basePath+"servlet/ShowPicServlet?id="+smate.id+"&type=user";
		schoolephoto.style.backgroundImage="url("+photoURL+")";	
		schoolfee.appendChild(schoolephoto);
		
		var schoolename=document.createElement("div");
		schoolename.setAttribute("class","schoolename");
		schoolename.innerHTML = smate.userName;
		schoolfee.appendChild(schoolename);
		
		var schoolenclass=document.createElement("div");
		schoolenclass.setAttribute("class","schoolenclass");
		schoolenclass.innerHTML = smate.grade+"年"+smate.banji+"班";
		schoolfee.appendChild(schoolenclass);
		
		var parent = document.getElementById("schoolfees");
		parent.appendChild(schoolfee);
	}
}