let productionPORT = window.location.port,
env = {
   // development:'http://127.0.0.1:11000',
    development:'http://192.168.0.111:11000',
    production:'http://'+window.location.hostname +":"+productionPORT
}
  
let currentEnv = 'production';

var urlConfig = {
    host:`${env[currentEnv]}`,
    // ws:`${env[currentEnv]}`.replace('https://','').replace('http://',''),
    // initGis:env[currentEnv] + ":8090/iserver/services/map-haituWS/rest/maps/haituMap"
}


// var urlConfig = {
//     host:'http://192.168.0.111:11000'
// }