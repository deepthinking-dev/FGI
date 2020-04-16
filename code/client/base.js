(function () {
    let escType =true 
    var fullscreen ;
    let btn = document.getElementById('btn');
    let fullarea = document.getElementById('topo_canvas')
    btn.addEventListener('click',function(){  
        
        if (fullarea.requestFullscreen) {
            fullarea.requestFullscreen();
          } else if (fullarea.webkitRequestFullScreen) {
            fullarea.webkitRequestFullScreen();
          } else if (fullarea.mozRequestFullScreen) {
            fullarea.mozRequestFullScreen();
          } else if (fullarea.msRequestFullscreen) {
            // IE11
            fullarea.msRequestFullscreen();
          }
          $('#topo_canvas').css({
              "background-color":"#fff",
              "background-image": "url('./static/lkr.png')",
              "background-size": "100% 100%",
              "background-repeat": "no-repeat",
              "z-index":'99'
        })
      escType = false
      fullscreen = true;
    })

    $(document).keydown(function(event){
　　　　if(event.keyCode == 27){
　　　　　　alert('你按下了ESC'); 
　　　　}
　　});


})();



