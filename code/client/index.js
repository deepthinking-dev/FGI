
$(function(){
    $("#selectAll").click((e) => {
        $("input:checkbox[name='item']").each((i,v) => {
            $(v).prop('checked',$("#selectAll").prop('checked'))
        })
    })
    $('body').on('click','#flex_tools>.lkr-pic_list .active-taps',(e) => {
        if($(e.target).html() == '模板管理'){
            $("#mouldPage").show()
            $("#algorithmPage").hide()
            $("#regulationPage").hide()
            $(".rule-flex").hide();
        }else if($(e.target).html() == '算子管理'){
            $("#algorithmPage").show()
            getAllData('/module/GetAllModule',{id:'id',name:'modulename'},'模板','')
            $("#mouldPage").hide()
            $("#regulationPage").hide()
            $(".rule-flex").hide();
        }else if($(e.target).html() == '规则管理'){
            $("#algorithmPage").hide()
            $(".rule-flex").show();
            getAllData('/operatorMaintenance/getAllAlgorithm',{id:'id',name:'algorithmname'},'规则',{username:null})
            $("#mouldPage").hide()
            $(".ruleTitle").hide();
            $("#ruleMde").hide();
            $("#regulationPage").show()
            $("#formulaMde").show();
           
        }
    })
    $('body').on('click','#getAllMb',(e) => {
        getAllData('/module/GetAllModule',{id:'id',name:'modulename'},'模板','')
    })
    $('body').on('click','#getAllSz',(e) => {
        getAllData('/operatorMaintenance/getAllAlgorithm',{id:'id',name:'algorithmname'},'算子',{username:null})
    })
    $('body').on('click','#getAllSzgz',(e) => {
        getAllData('/operatorMaintenance/getAllAlgorithm',{id:'id',name:'algorithmname'},'规则',{username:null})
        $(".ruleTitle").hide();
        $("#ruleMde").hide();
        $("#formulaMde").show();
        
    })
    $('body').on('click','#getAllGzgz',(e) => {
        getAllData('/algorithmRule/getAllAlgorithmRule',{id:'id',name:'rolename',ruleType:'规则'},'规则',{username:null})
        $("#formulaMde").hide();
        $("#ruleMde").show();
        $(".ruleTitle").show();
    })
    function getAllData(url,datas,type,param,ruleType){
        $.ajax({
            url:urlConfig.host+url,
            data:param,
            success: function(data) {
                $(".left-list").remove()
                if(type == '算子'){
                    data.map(item => {
                        window.addAlgorithm({
                            name: 'rectangle',
                            icon: 'icon-rectangle',
                            id:item[datas.id],
                            moduleid:item.moduleid,
                            type:type,
                            algorithmtype:item.algorithmtype,
                            data: {
                                id:item[datas.id]+type,
                                text: item[datas.name],
                                rect: {
                                    width: 200,
                                    height: 50
                                },
                                font: {
                                    fontFamily: 'Arial',
                                    color: 'aqua',
                                
                                    textBaseline: 'top'
                                },
                                paddingLeft: 10,
                                paddingRight: 10,
                                paddingTop: 10,
                                paddingBottom: 10,
                                borderRadius: 0.1,
                                name: 'rectangle',
                                fillStyle:'rgba(4,44,98,0.58)',
                                strokeStyle: '#4295ec',
                                // image:'./static/mum1.png'
                            }
                        })
                    })
                }else{
                    data.map(item => {
                        window.addAlgorithm({
                            name: 'rectangle',
                            icon: 'icon-rectangle',
                            id:item[datas.id],
                            moduleid:item.moduleid,
                            type:type,
                            ruleType:datas.ruleType,
                            data: {
                                id:item[datas.id]+type,
                                text: item[datas.name],
                                rect: {
                                    width: 200,
                                    height: 50
                                },
                                font: {
                                    fontFamily: 'Arial',
                                    color: 'aqua',
                                
                                    textBaseline: 'top'
                                },
                                paddingLeft: 10,
                                paddingRight: 10,
                                paddingTop: 10,
                                paddingBottom: 10,
                                borderRadius: 0.1,
                                name: 'rectangle',
                                fillStyle:'rgba(4,44,98,0.58)',
                                strokeStyle: '#4295ec',
                                // image:'./static/mum1.png'
                            }
                        })
                    })
                }
                
            }
        })
    }
    window.getAllData = getAllData
    $('body').on('click','#mouldPage .active-taps',(e) => {
        $('.active-taps').each((i,v) => {
            $(v).css('color','#ffffff')
    })
        window.bigData.actionTab = $(e.target).data('name')
        $(e.target).css('color','rgb(255, 217, 0)')
        window.onRender()
        $.ajax({
            url:urlConfig.host+'/module/GetModuleByGroupName',
            data:{moduleGroupName:$(e.target).data('name')},
            success: function(data) {
                $(".left-list").remove()
                data.map(item => {
                    window.addModel({
                        name: 'rectangle',
                        icon: 'icon-rectangle',
                        id:item.id,
                        data: {
                            id:item.id,
                            text: item.modulename,
                            rect: {
                                width: 200,
                                height: 50
                            },
                            font: {
                                fontFamily: 'Arial',
                                color: 'aqua',
                            
                                textBaseline: 'top'
                            },
                            paddingLeft: 10,
                            paddingRight: 10,
                            paddingTop: 10,
                            paddingBottom: 10,
                            borderRadius: 0.1,
                            name: 'rectangle',
                            fillStyle:'rgba(4,44,98,0.58)',
                            strokeStyle: '#4295ec',
                            // image:'./static/mum1.png'
                        }
                    })
                })
            }
        })
    })
    $('body').on('click','.lkr-list-del',(e) => {
        window.bigData.delmoduleId = $(e.target).data('id')
        $('#lkrFrameDel').fadeToggle(500)
    })
    // 点击删除算子
    $('body').on('click','.lkr-list-delAlgorithm',(e) => {
        window.bigData.delAlgorithmId = $(e.target).data('id')
        $('#lkrAlgorithm').fadeToggle(500)
    })
      // 点击编辑算子
    $('body').on('click','.lkr-list-editAlgorithm',(e) => {
        window.bigData.editAlgorithmId = $(e.target).data('id');
        let algorithmtype = $(e.target).attr('data-type');
        window.bigData.formulaType = 'edit'
        window.bigData.formulaModuleId = $(e.target).attr('data-moduleid');
        $.ajax({
            url:urlConfig.host+'/operatorMaintenance/getAlgorithmById',
            data:{
                algthId:window.bigData.editAlgorithmId
            },
            success: function(data) {
                console.log(data)
                if(algorithmtype==0){
                    $('#AlgorithmnameY').attr({"value":data.tableAlgorithm.algorithmname,"tableAlgorithmid":data.tableAlgorithm.id,"tableAlmoduleid":data.tableAlgorithm.moduleid});
                    window.bigData.editFormula = data.tableAlgorithm.algorithmfun
                    window.changeBds(data.tableAlgorithm.algorithmfun);
                    if(data.tableFuncs.length>0){
                        let str =``
                        data.tableFuncs.map((item)=>{
                            str +=`<div class="MathJaxParam" formulaid="${item.id}" formulaModuleId="${item.moduleid}">
                                        <div class="width-50">
                                            <span>变量</span>
                                            <input type="text" readonly="readonly" value="${item.varname}" class="MathJaxInput1 inputButton">
                                        </div>
                                        <div class="width-50 width-select">
                                            <span>类型</span>
                                            <select  class="MathJaxInput2 inputButton">
                                                <option value="">请选择</option>
                                                <option value="常量">常量</option>
                                                <option value="数据项">数据项</option>   
                                                <option value="其他模块计算结果">其他模块计算结果</option>
                                            </select>
                                        </div>
                                        <div class="width-50 isShow1"> 
                                                <span>取值</span >
                                                <input type="text" class="MathJaxInput3 inputButton">
                                        </div>
                                        <div class="width-50 isShow2"> 
                                            <span>数据项</span>
                                            <input type="text" class="MathJaxInput3 inputFields inputButton"  readonly="readonly">
                                        </div>
                                        <div class="width-50 isShow3"> 
                                                <span>其他公式</span>
                                                <input type="text" class="MathJaxInput3 otherFormula inputButton"  readonly="readonly">
                                        </div>
                                        <div class="width-50"> 
                                                <span>描述</span>
                                                <input type="text" class="MathJaxInput4 inputButton" value="${item.remark}">
                                        </div>
                                    </div>`  
                            })
                        $(".MathJaxEdit").html(str)   
                       for(let j=0;j<data.tableFuncs.length;j++){
                           for(k=0;k<$('.MathJaxParam').length;k++){
                               let vType = data.tableFuncs[j].vartype
                               if(j == k){
                                $('.MathJaxParam').eq(k).find(".MathJaxInput2").find("option[value='"+vType+"']").attr("selected",true);
                                if(vType == "常量"){
                                    $('.MathJaxParam').eq(k).find('.isShow2').attr("style","display:none;");
                                    $('.MathJaxParam').eq(k).find('.isShow3').attr("style","display:none;");
                                    $('.MathJaxParam').eq(k).find(".isShow1").attr("style","display:block;");
                                    $('.MathJaxParam').eq(k).find(".isShow1").find('input').attr("value",data.tableFuncs[j].valvalue)
                                }
                                if(vType == "数据项"){
                                    $('.MathJaxParam').eq(k).find('.isShow1').attr("style","display:none;");
                                    $('.MathJaxParam').eq(k).find('.isShow3').attr("style","display:none;");
                                    $('.MathJaxParam').eq(k).find(".isShow2").attr("style","display:block;");
                                    $('.MathJaxParam').eq(k).find(".isShow2").find('input').attr("value",data.tableFuncs[j].valvalue)
                                }
                                if(vType == "其他模块计算结果"){
                                    $('.MathJaxParam').eq(k).find('.isShow1').attr("style","display:none;");
                                    $('.MathJaxParam').eq(k).find('.isShow2').attr("style","display:none;");
                                    $('.MathJaxParam').eq(k).find(".isShow3").attr("style","display:block;");
                                    $('.MathJaxParam').eq(k).find(".isShow3").find('input').attr("value",data.tableFuncs[j].valvalue)
                                }
                               }                     
                           }                      
                       }
                    }
                    
                    $('.Frame').fadeToggle(500)
                }else if(algorithmtype==1){
                    $('#LogicName').attr({"value":data.tableAlgorithm.algorithmname,"tableAlgorithmid":data.tableAlgorithm.id,"tableAlmoduleid":data.tableAlgorithm.moduleid});
                    let algorithmfun = data.tableAlgorithm.algorithmfun
                    $.ajax({
                        url:urlConfig.host+'/module/getModuleColumns',
                        data:{moduleId:window.bigData.formulaModuleId},
                        success: function(data) {
                         
                           let str1 =``
                           if(data.length>0){
                                data.map(item => {
                                    str1 += `<option value="${item.fieldname}">${item.fieldname}</option>`
                                })
                                $('.Logic-form-field').html(str1)
                           }                          
                           
                            algorithmfun = algorithmfun.split(" and ")
                            let str =``
                            for(let i=0;i<algorithmfun.length;i++){                      
                                if(algorithmfun[i].indexOf('与') !=-1){
                                    let obj = algorithmfun[i].split("与")                         
                                    str+=` <li class="logicLi">
                                                <select name="" class="Logic-form-field inputButton">
                                                    <option value="">${obj[0]}</option>
                                                </select> 
                                                <select name="" class="Logic-form-label inputButton">
                                                    <option value="" selected>与</option>
                                                    <option value="">或</option>
                                                    <option value="">非</option>
                                                    <option value="">&lt;</option>
                                                    <option value="">&lt;=</option>
                                                    <option value="">&gt;</option>
                                                    <option value="">&gt;=</option>
                                                    <option value="">=</option>
                                                </select>
                                                <!-- <select name="" class="Logic-form-value inputButton">
                                                    <option value="">请选择取值</option>
                                                </select>  -->
                                                <input type="text" class="Logic-form-value inputButton" value="${obj[1]}"> 
                                                <button class="removeLogic" type="button" onclick="removeLogic(event)">删除</button>
                                            </li>` 
                                $('.logicLi').eq(i).find(".Logic-form-field").find("option[value='"+obj[0]+"']").attr("selected",true);
                                }
                                if(algorithmfun[i].indexOf('或') !=-1){
                                    let obj = algorithmfun[i].split("或")
                                    str+=` <li class="logicLi">
                                                <select name="" class="Logic-form-field inputButton">
                                                    <option value="">${obj[0]}</option>
                                                </select> 
                                                <select name="" class="Logic-form-label inputButton">
                                                    <option value="">与</option>
                                                    <option value="" selected>或</option>
                                                    <option value="">非</option>
                                                    <option value="">&lt;</option>
                                                    <option value="">&lt;=</option>
                                                    <option value="">&gt;</option>
                                                    <option value="">&gt;=</option>
                                                    <option value="">=</option>
                                                </select>
                                                <!-- <select name="" class="Logic-form-value inputButton">
                                                    <option value="">请选择取值</option>
                                                </select>  -->
                                                <input type="text" class="Logic-form-value inputButton" value="${obj[1]}"> 
                                                <button class="removeLogic" type="button" onclick="removeLogic(event)">删除</button>
                                            </li>`
                                    $('.logicLi').eq(i).find(".Logic-form-field").find("option[value='"+obj[0]+"']").attr("selected",true);       
                                } 
                                if(algorithmfun[i].indexOf('非') !=-1){
                                    let obj = algorithmfun[i].split("非")
                                    str+=` <li class="logicLi">
                                            <select name="" class="Logic-form-field inputButton">
                                                <option value="">${obj[0]}</option>
                                            </select> 
                                            <select name="" class="Logic-form-label inputButton">
                                                <option value="">与</option>
                                                <option value="">或</option>
                                                <option value="" selected>非</option>
                                                <option value="">&lt;</option>
                                                <option value="">&lt;=</option>
                                                <option value="">&gt;</option>
                                                <option value="">&gt;=</option>
                                                <option value="">=</option>
                                            </select>
                                            <!-- <select name="" class="Logic-form-value inputButton">
                                                <option value="">请选择取值</option>
                                            </select>  -->
                                            <input type="text" class="Logic-form-value inputButton" value="${obj[1]}"> 
                                            <button class="removeLogic" type="button" onclick="removeLogic(event)">删除</button>
                                        </li>`
                                        $('.logicLi').eq(i).find(".Logic-form-field").find("option[value='"+obj[0]+"']").attr("selected",true);
                                }
                                if((algorithmfun[i].indexOf('<') !=-1) && (algorithmfun[i].indexOf('<=') == -1)){
                                    let obj = algorithmfun[i].split("<")
                                    str+=` <li class="logicLi">
                                                <select name="" class="Logic-form-field inputButton">
                                                    <option value="">${obj[0]}</option>
                                                </select> 
                                                <select name="" class="Logic-form-label inputButton">
                                                    <option value="">与</option>
                                                    <option value="">或</option>
                                                    <option value="">非</option>
                                                    <option value="" selected>&lt;</option>
                                                    <option value="">&lt;=</option>
                                                    <option value="">&gt;</option>
                                                    <option value="">&gt;=</option>
                                                    <option value="">=</option>
                                                </select>
                                                <!-- <select name="" class="Logic-form-value inputButton">
                                                    <option value="">请选择取值</option>
                                                </select>  -->
                                                <input type="text" class="Logic-form-value inputButton" value="${obj[1]}"> 
                                                <button class="removeLogic" type="button" onclick="removeLogic(event)">删除</button>
                                            </li>`
                                        $('.logicLi').eq(i).find(".Logic-form-field").find("option[value='"+obj[0]+"']").attr("selected",true);
                                }  
                                if((algorithmfun[i].indexOf('<=') !=-1) && (algorithmfun[i].indexOf('<') !=-1)  && (algorithmfun[i].indexOf('=') !=-1)){
                                    let obj = algorithmfun[i].split("<=")
                                        str+=` <li class="logicLi">
                                                <select name="" class="Logic-form-field inputButton">
                                                    <option value="">${obj[0]}</option>
                                                </select> 
                                                <select name="" class="Logic-form-label inputButton">
                                                    <option value="">与</option>
                                                    <option value="">或</option>
                                                    <option value="">非</option>
                                                    <option value="">&lt;</option>
                                                    <option value="" selected>&lt;=</option>
                                                    <option value="">&gt;</option>
                                                    <option value="">&gt;=</option>
                                                    <option value="">=</option>
                                                </select>
                                                <!-- <select name="" class="Logic-form-value inputButton">
                                                    <option value="">请选择取值</option>
                                                </select>  -->
                                                <input type="text" class="Logic-form-value inputButton" value="${obj[1]}"> 
                                                <button class="removeLogic" type="button" onclick="removeLogic(event)">删除</button>
                                            </li>`
                                            $('.logicLi').eq(i).find(".Logic-form-field").find("option[value='"+obj[0]+"']").attr("selected",true);
                                }                      
                                if((algorithmfun[i].indexOf('>') !=-1) && (algorithmfun[i].indexOf('>=') == -1)){
                                    let obj = algorithmfun[i].split(">")
                                    str+=` <li class="logicLi">
                                            <select name="" class="Logic-form-field inputButton">
                                                <option value="">${obj[0]}</option>
                                            </select> 
                                            <select name="" class="Logic-form-label inputButton">
                                                <option value="">与</option>
                                                <option value="">或</option>
                                                <option value="">非</option>
                                                <option value="">&lt;</option>
                                                <option value="">&lt;=</option>
                                                <option value="" selected>&gt;</option>
                                                <option value="">  &gt;=</option>
                                                <option value="">=</option>
                                            </select>
                                            <!-- <select name="" class="Logic-form-value inputButton">
                                                <option value="">请选择取值</option>
                                            </select>  -->
                                            <input type="text" class="Logic-form-value inputButton" value="${obj[1]}"> 
                                            <button class="removeLogic" type="button" onclick="removeLogic(event)">删除</button>
                                        </li>`
                                        $('.logicLi').eq(i).find(".Logic-form-field").find("option[value='"+obj[0]+"']").attr("selected",true);
                                }
                                if((algorithmfun[i].indexOf('=') !=-1) && (algorithmfun[i].indexOf('>') !=-1) && (algorithmfun[i].indexOf('>=') !=-1)){
                                    let obj = algorithmfun[i].split(">=")
                                        str+=` <li class="logicLi">
                                                <select name="" class="Logic-form-field inputButton">
                                                    <option value="">${obj[0]}</option>
                                                </select> 
                                                <select name="" class="Logic-form-label inputButton">
                                                    <option value="">与</option>
                                                    <option value="">或</option>
                                                    <option value="">非</option>
                                                    <option value="">&lt;</option>
                                                    <option value="">&lt;=</option>
                                                    <option value="">&gt;</option>
                                                    <option value="" selected>&gt;=</option>
                                                    <option value="">=</option>
                                                </select>
                                                <!-- <select name="" class="Logic-form-value inputButton">
                                                    <option value="">请选择取值</option>
                                                </select>  -->
                                                <input type="text" class="Logic-form-value inputButton" value="${obj[1]}"> 
                                                <button class="removeLogic" type="button" onclick="removeLogic(event)">删除</button>
                                            </li>`
                                            $('.logicLi').eq(i).find(".Logic-form-field").find("option[value='"+obj[0]+"']").attr("selected",true);
                                }
                                if((algorithmfun[i].indexOf('=') !=-1) && (algorithmfun[i].indexOf('>') ==-1) && (algorithmfun[i].indexOf('<') == -1)){
                                    let obj = algorithmfun[i].split("=")
                                    str+=` <li class="logicLi">
                                            <select name="" class="Logic-form-field inputButton">
                                                <option value="">${obj[0]}</option>
                                            </select> 
                                            <select name="" class="Logic-form-label inputButton">
                                                <option value="">与</option>
                                                <option value="">或</option>
                                                <option value="">非</option>
                                                <option value="">&lt;</option>
                                                <option value="">&lt;=</option>
                                                <option value="">&gt;</option>
                                                <option value="">&gt;=</option>
                                                <option value="" selected>=</option>
                                            </select>
                                            <!-- <select name="" class="Logic-form-value inputButton">
                                                <option value="">请选择取值</option>
                                            </select>  -->
                                            <input type="text" class="Logic-form-value inputButton" value="${obj[1]}"> 
                                            <button class="removeLogic" type="button" onclick="removeLogic(event)">删除</button>
                                        </li>`
                                    $('.logicLi').eq(i).find(".Logic-form-field").find("option[value='"+obj[0]+"']").attr("selected",true);
                                }

                            
                            }
                            $('.logicUl').html(str)
                            $('.Logic').fadeToggle(500)
                        }
                    })
                 
                    
                   
                }
              
            }
        })
       
    })
    //点击数据项

    $('body').on('click','.inputFields',(e) => {
        window.filed.inputFieldsTarget = $(e.target);
        $.ajax({
            url:urlConfig.host+'/module/getModuleColumns',
            data:{moduleId:window.bigData.formulaModuleId},
            success: function(data) {
               console.log(data)
               let str =``
               if(data.length>0){
                    data.map(item=>{
                        str +=`<tr id="${item.id}" moduleId="${item.moduleid}">
                                    <td class="fieldname">${item.fieldname}</td>
                                    <td>${item.fieldtype}</td>
                                    <td>${item.remark}</td>
                                    remark
                                </tr>`
                    })
               }else{
                   str+= `<div style="text-align: center;">暂无数据</div>`
               }
               
                
                $(".fieldsList").html(str) 
            }
        })
        $('#fields').fadeToggle(500)
    })
     // 点击选择数据项字段信息
    $('body').on('click','.fieldsList tr',(e) => {
        window.filed.fieldname = $(e.target).parent('tr').children('.fieldname').text();
        $(e.target).parent('tr').addClass("backcolor").siblings("tr").removeClass("backcolor"); 
    })

    //点击其他模块
    $('body').on('click','.otherFormula',(e) => {
        window.filed.inputFieldsTarget = $(e.target);
        $.ajax({
            url:urlConfig.host+'/operatorMaintenance/getAllAlgorithm',
            data:{
                username:null
            },
            success: function(data) {
               console.log(data)
               let str =``
               if(data.length>0){
                    data.map(item=>{
                        str +=`<tr id="${item.id}" moduleId="${item.moduleid}">
                                    <td class="algorithmname">${item.algorithmname}</td>
                                    <td>${item.algorithmtype}</td>
                                    <td>${item.algorithmfun}</td>
                                    <td>${item.algorithmauthor}</td>
                                    
                                    remark
                                </tr>`
                    })
               }else{
                   str+= `<div style="text-align: center;">暂无数据</div>`
               }
               
                
                $(".otherFormulaList").html(str) 
            }
        })
        $('#otherFormula').fadeToggle(500)
        
    })
      // 点击选择算子信息
    $('body').on('click','.otherFormulaList tr',(e) => {
        window.filed.fieldname = $(e.target).parent('tr').children('.algorithmname').text();
        $(e.target).parent('tr').addClass("backcolor").siblings("tr").removeClass("backcolor"); 
    })
   //点击导入
   $('body').on('click','#Import',(e) => {
        $("#fileupload").show();
    })
    // 上传选择文件  
    $('input.inputfile').on('change',function(){
        // console.log(e)
        console.log($(this).val());
        var file = this.files[0];
        if (window.FileReader) {    
           debugger
            var reader = new FileReader();    
            reader.readAsDataURL(file);    
            //监听文件读取结束后事件            
            reader.onloadend = function (e) {
                console.log(e.target.result)
            };    
         } 
    
    })
    $('body').on('click','.ruleCheckbox',(e) => {
        if($(e.target).parent('.left-list').parent('#ruleMde').children('.left-list').children("input[class='ruleCheckbox']:checked").length  > 1){
            let str = $(e.target).parent('.left-list').parent('#ruleMde').children('.left-list')
            for(let i=0;i<str.length;i++){
                $(str[i]).children("input[class='ruleCheckbox']").prop("checked",false);
            }
            $(e.target).prop('checked', true);  
        }
    })
    //点击导出
    $('body').on('click','#export',(e) => {
        if($("input[class='ruleCheckbox']:checked").length == 0){
            alert("至少勾选一个规则！");
            return false;
       } 
       let id = $('input:checkbox:checked').attr("data-id");
       location.href= urlConfig.host+'/algorithmRule/saveAlgorithmRule2File?id=' + id
       
        // $.ajax({
        //     type:"get",   
        //     dataType: "json",
        //     url:urlConfig.host+'/algorithmRule/saveAlgorithmRule2File?id=' + id,
        //     contentType: "application/json;charset=UTF-8",
        //     success: function(data) {
        //         if(data == true){
                   
        //         }
        //     }
        // }) 
    })
       // 点击删除规则
    $('body').on('click','.lkr-list-delRule',(e) => {
        window.bigData.delRuleId = $(e.target).data('id')
        $('#lkrRule').fadeToggle(500)
    })
})


