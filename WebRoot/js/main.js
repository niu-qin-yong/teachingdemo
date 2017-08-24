window.onload = function(){
		recordPosition();
		move();
		binding();
		bindingNav();
        kalendae();
}

function kalendae(){
  var k = new Kalendae(document.getElementById('calendar'),{
    selected:Kalendae.moment()   //显示今天的时间
  });
  k.subscribe('change',function(){
    console.log(this.getSelected());
  });
}
