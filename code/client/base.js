(function () {
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
        // Topology.init();
      escType = false
      fullscreen = true;
    })

})();



