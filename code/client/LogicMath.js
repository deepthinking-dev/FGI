$(function(){
    var form1=document.querySelector("#LogicOne");
    var form2=document.querySelector("#LogicTwo")
    var form3=document.querySelector("#LogicThree");
    function linkage(form1,form2,form3,data){
        for(var i=0;i<data.provinces.length;i++){
            var opc=document.createElement("option");
            opc.innerHTML=data.provinces[i].title;
            opc.value=data.provinces[i].name;
            form1.appendChild(opc);
        }
    form1.onchange=function(){
        form2.innerHTML=" <option>城市</option>"
        for(var j=0;j<data.cities[this.value].length;j++){
            var opc1=document.createElement("option");
            opc1.innerHTML=data.cities[this.value][j].title;opc1.value=data.cities[this.value][j].name;
            form2.appendChild(opc1)
            for(var k=0;k<data.areas[opc1.value].length;k++){
                var opc2=document.createElement("option");
                opc2.innerHTML=data.areas[opc1.value][k].title;
                opc2.value=data.areas[opc1.value][k].name;
                form3.appendChild(opc2)
            }
        }
    }
    form2.onchange=function(){
        form3.innerHTML=" <option>区域</option>";
        for(var a=0;a<data.areas[this.value].length;a++){
            var opc3=document.createElement("option");
            opc3.innerHTML=data.areas[this.value][a].title;
            opc3.value=data.areas[this.value][a].name;form3.appendChild(opc3)
        }
    }
    btn.onclick=function(){var obj={};console.log(form1.options[form1.selectedIndex].text)
    obj.provinces={name:form1.options[form1.selectedIndex].value,title:form1.options[form1.selectedIndex].text,};obj.cities={name:form2.options[form2.selectedIndex].value,title:form2.options[form2.selectedIndex].text,};obj.areas={name:form3.options[form3.selectedIndex].value,title:form3.options[form3.selectedIndex].text,};obj.detail={name:"detail",title:text.value}
    console.log(obj)}}
    linkage(form1,form2,form3,text,btn,data);
})