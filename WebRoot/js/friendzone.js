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
        	  
        	  alert(data);
              
              /* 显示自己发的这条动态在最上面 */
//              createMomentElement(JSON.parse(data),true);
              
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
