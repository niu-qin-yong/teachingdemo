var player = {
  curMusic : null,
  paused : false,
  list : [],
  audio : document.getElementById('player'),
  init : function(){
    var self = this;
    self.list = [
      {key:"1",music:"audio/凉凉.mp3",poster:"poster/ll.jpg",title:"凉凉"},
      {key:"2",music:"audio/Faded.mp3",poster:"poster/faded.jpg",title:"Faded"},
      {key:"3",music:"audio/告白气球.mp3",poster:"poster/gbqq.jpg",title:"告白气球"},
      {key:"4",music:"audio/成都.mp3",poster:"poster/cd.jpg",title:"成都"},
      {key:"5",music:"audio/小幸运.mp3",poster:"poster/xxy.jpg",title:"小幸运"},
      {key:"6",music:"audio/演员.mp3",poster:"poster/yy.jpg",title:"演员"}
    ]
    self.updateView();          //更新歌曲信息
  },
  updateView : function(){
    var self = this;
    var elements = document.querySelectorAll('.music-list');
    for(var i = 0;i<elements.length;i++){
      var msg = self.find(elements[i].dataset.key);
      elements[i].children[0].innerHTML=msg.title;
      elements[i].children[1].style.backgroundImage = "url("+msg.poster+")";
      //绑定播放按钮的事件
      elements[i].children[4].onclick = function(){
        self.bind(this);
        if(self.curMusic == null || self.curMusic != this){
          self.audio.src = self.find(this.parentNode.dataset.key).music;
        }else{
          self.musicControl();
        }
        self.curMusic = this;
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
  find : function(key){
    var self = this;
    for(var i = 0;i<self.list.length;i++){
      if(self.list[i].key == key){
        return self.list[i];
      }
    }
  },
  bind : function(ele){
    var self = this;
    //重新绑定事件之前将所有的按钮重置
    var elements = document.querySelectorAll('.music-control');
    for(var i = 0;i<elements.length;i++){
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
  }
}
/*
 知识点：1.查找子节点父节点 2.多个class属性 3.audio多媒体的play和pause方法 4.audio事件监听（onplay和onpause）
 --------- 
 DOM事件委托的知识点未使用
 */
