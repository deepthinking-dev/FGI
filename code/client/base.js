(function () {
    if(window.canvasNowId == "canvas0" || !window.canvasNowId){
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
        })
    }else{
        let btn = window.parent.document.getElementById('btn');
        let fullarea = parent.$('#'+window.top.canvasNowId).contents().find("#topo_canvas")
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
        })
    }


})();



