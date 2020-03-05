function formula (data){
   var tools= ['alpha','beta','gamma','delta','epsilon','varepsilon','zeta','eta','theta','vartheta','iota','kappa',
    'lambda','mu','nu','xi','pi','varpi','rho','varrho','sigma','varsigma','tau','upsilon','phi','varphi','chi','psi','omega']
    var formulaArr =['left','right','rm','sideset','bigotimes','frac','over','sqrt','ldots','cdots','vec','int','log','lg','ln','sin','cos',
     'tan','cot','sec','csc','prod','sum','cdot','prod']
   let dataArr = []
   let xilaArr = []
   let bianliangArr = []
    for(var i=0;i<data.length;i++){
        let flag = true 
        for(var j=0;j<tools.length;j++){     
              
            if(data[i] == tools[j]){
               flag = false
               break;
            }
           
        }
        if(flag){
            dataArr.push(data[i])
        }
        if(!flag){
            xilaArr.push(data[i])
        }

    }
    for(var m=0 ;m<dataArr.length;m++){
        let flag1= true 
        for(var n =0;n <formulaArr.length;n++ ){
            if(dataArr[m] == formulaArr[n]){
                flag1 = false
                break;
            } 
        }
        if(flag1){
            bianliangArr.push(dataArr[m])
        }
    }
    return data =bianliangArr.concat(xilaArr)
}
window.formula = formula