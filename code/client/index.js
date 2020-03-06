
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
        }else if($(e.target).html() == '算子管理'){
            $("#algorithmPage").show()
            getAllData('/module/GetAllModule',{id:'id',name:'modulename'},'模板','')
            $("#mouldPage").hide()
        }
    })
    $('body').on('click','#getAllMb',(e) => {
        getAllData('/module/GetAllModule',{id:'id',name:'modulename'},'模板','')
    })
    $('body').on('click','#getAllSz',(e) => {
        getAllData('/operatorMaintenance/getAllAlgorithm',{id:'id',name:'algorithmname'},'算子',{username:null})
    })
    function getAllData(url,datas,type,param){
        debugger
        $.ajax({
            url:urlConfig.host+url,
            data:param,
            success: function(data) {
                $(".left-list").remove()
                data.map(item => {
                    window.addAlgorithm({
                        name: 'rectangle',
                        icon: 'icon-rectangle',
                        id:item[datas.id],
                        moduleid:item.moduleid,
                        type:type,
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
        })
    }
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
        debugger
        window.bigData.editAlgorithmId = $(e.target).data('id');
        window.bigData.formulaType = 'edit'
        window.bigData.formulaModuleId = $(e.target).attr('data-moduleid');
        $.ajax({
            url:urlConfig.host+'/operatorMaintenance/getAlgorithmById',
            data:{
                algthId:window.bigData.editAlgorithmId
            },
            success: function(data) {
                console.log(data)
                $('#AlgorithmnameY').attr({"value":data.tableAlgorithm.algorithmname,"tableAlgorithmid":data.tableAlgorithm.id,"tableAlmoduleid":data.tableAlgorithm.moduleid});
                window.bigData.editFormula = data.tableAlgorithm.algorithmfun
                window.changeBds(data.tableAlgorithm.algorithmfun);
                if(data.tableFuncs.length>0){

                    let str =``
                    data.tableFuncs.map((item)=>{
                        str +=`<div class="MathJaxParam" formulaid="${item.id}" formulaModuleId="${item.moduleid}">
                                    <div class="width-30">
                                        <span>变量</span>
                                        <input type="text" readonly="readonly" value="${item.varname}" class="MathJaxInput1">
                                    </div>
                                    <div class="width-30 width-select">
                                        <span>类型</span>
                                        <select  class="MathJaxInput2">
                                            <option value="">请选择</option>
                                            <option value="常量">常量</option>
                                            <option value="数据项">数据项</option>   
                                            <option value="其他模块计算结果">其他模块计算结果</option>
                                        </select>
                                    </div>
                                    <div class="width-30 isShow1"> 
                                            <span>取值</span >
                                            <input type="text" class="MathJaxInput3">
                                    </div>
                                    <div class="width-30 isShow2"> 
                                        <span>数据项</span>
                                        <input type="text" class="MathJaxInput3 inputFields"  readonly="readonly">
                                    </div>
                                    <div class="width-30 isShow3"> 
                                            <span>其他公式</span>
                                            <input type="text" class="MathJaxInput3 otherFormula"  readonly="readonly">
                                    </div>
                                    <div class="width-100"> 
                                            <span>描述</span>
                                            <input type="text" class="MathJaxInput4" value="${item.remark}">
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
            }
        })
       
    })
    //点击数据项

    $('body').on('click','.inputFields',(e) => {
        window.filed.inputFieldsTarget = $(e.target);
        debugger
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


    //逻辑运算删除按钮
    $('body').on('click','.removeLogic tr',(e) => {
        debugger
        console.log(e)
    })
 
})


