
function FrameClose(){
    $(".Frame").attr("style","display:none;");
}
function LogicClose(){
    $(".Logic").attr("style","display:none;");
}
//选择算子类型
function selectFormula(){
    let formulatype = document.getElementById('formulaType').value
    if(formulatype == 1){
        $(".Logic").attr("style","display:none;");
        $(".Frame").attr("style","display:block;");          
        $("#suanfaType").css('display', "none");

    }
    if(formulatype == 2){
        $(".Frame").attr("style","display:none;");
        $(".Logic").attr("style","display:block;");
        $("#suanfaType").css('display', "none");
        $.ajax({
            url:urlConfig.host+'/module/getModuleColumns',
            data:{moduleId:window.bigData.formulaModuleId},
            success: function(data) {
                console.log(data)
                let str =``
                if(data.length>0){
                    data.map(item => {
                        str += `<option value="${item.fieldname}">${item.fieldname}</option>`
                    })
                
                }
                $('.Logic-form-field').html(str)
                
            }
        })
    }
}
function fieldsClose(){
    $("#fields").attr("style","display:none;");
}
//选择变量类型
$('body').on('change','.MathJaxInput2',(e) => {

    var objS = $(e.target).val()
    if(objS == "常量"){           
        $(e.target).parent('.width-select').nextAll('.isShow2').attr("style","display:none;");
        $(e.target).parent('.width-select').nextAll('.isShow3').attr("style","display:none;");
        $(e.target).parent('.width-select').nextAll('.isShow1').attr("style","display:block;");
    }
    if(objS == "数据项"){
        $(e.target).parent('.width-select').nextAll('.isShow1').attr("style","display:none;");
        $(e.target).parent('.width-select').nextAll('.isShow3').attr("style","display:none;");
        $(e.target).parent('.width-select').nextAll('.isShow2').attr("style","display:block;");
    }
    if(objS == "其他模块计算结果"){

        $(e.target).parent('.width-select').nextAll('.isShow1').attr("style","display:none;");
        $(e.target).parent('.width-select').nextAll('.isShow2').attr("style","display:none;");
        $(e.target).parent('.width-select').nextAll('.isShow3').attr("style","display:block;");
    }
})

//确定选择字段信息确定按钮
function ConfirmFields(){
    $(window.filed.inputFieldsTarget).attr("value",window.filed.fieldname)
    $('#fields').fadeToggle(500)
}
    //确定选择算子信息确定按钮
function ConfirmotherFormula(){
    $(window.filed.inputFieldsTarget).attr("value",window.filed.fieldname)
    $('#otherFormula').fadeToggle(500)
}

//逻辑运算新增按钮
function addLogic(){
    $(".logicUl").append($(".logicLi:first").clone());
}

//逻辑运算删除按钮
function removeLogic(event){
    if($('.logicLi').length >1){
        $(event.target).parent().remove()
    }
    
}

//提交算子信息及公式编辑
function ConfirmFrame(){
    let tableAl ={
        algorithmauthor:"",
        algorithmfun:$('#MathInput').val(),
        algorithmname:$('#AlgorithmnameY').val(),
        algorithmtype:0,
        des:"",
        ispublic:0,
        moduleid:'',
        remark:"",
        tno:""
    }
    let tableF=[]
    let tableModule={
        moduleid:window.bigData.formulaModuleId,
        remark:"",
        username:""
    }
    let MathJaxParamLength = $('.MathJaxParam')
    if(MathJaxParamLength.length > 0){
        for(let i=0;i<MathJaxParamLength.length;i++){
            let obj ={}
            if(window.bigData.formulaType == 'edit'){
                obj.id = MathJaxParamLength.eq(i).attr("formulaid")
                obj.moduleid =MathJaxParamLength.eq(i).attr("formulaModuleId")
            }
            obj.vartype = MathJaxParamLength.eq(i).find('.MathJaxInput2 option:selected').text()
            if( obj.vartype == '数据项'){
                obj.valvalue = MathJaxParamLength.eq(i).find('.inputFields').attr("value")
            }else if(obj.vartype == '其他模块计算结果'){
                obj.valvalue = MathJaxParamLength.eq(i).find('.otherFormula').attr("value")
            }else{
                obj.valvalue = MathJaxParamLength.eq(i).find('.MathJaxInput3').val()
            }             
            obj.remark = MathJaxParamLength.eq(i).find('.MathJaxInput4').val()
            obj.varname = MathJaxParamLength.eq(i).find('.MathJaxInput1').val()
            
            tableF.push(obj)
        }
        
    }
    if(window.bigData.formulaType == 'edit'){
        tableAl.moduleid = $('#AlgorithmnameY').attr("tableAlmoduleid")
        tableAl.id =  $('#AlgorithmnameY').attr("tableAlgorithmid")
        tableModule.moduleid = $('#AlgorithmnameY').attr("tableAlmoduleid")
    }else{
        tableAl.moduleid = window.bigData.formulaModuleId
    }
    let param = {
        tableAlgorithm:tableAl,
        tableFuncs:tableF,
        tableModuleuserrelation:tableModule
    }
    console.log(param)
    if(window.bigData.formulaType == 'add'){
        $.ajax({
            type:"post",   
            dataType: "json",
            url:urlConfig.host+'/operatorMaintenance/addAlgorithm',
            contentType: "application/json;charset=UTF-8",
            data:JSON.stringify(param),  
            success: function(data) {
                if(data == true){
                    $(".Frame").attr("style","display:none;");
                }
            }
        }) 
    }
    if(window.bigData.formulaType == 'edit'){
        $.ajax({
            type:"post",   
            dataType: "json",
            url:urlConfig.host+'/operatorMaintenance/modAlgorithmById',
            contentType: "application/json;charset=UTF-8",
            data:JSON.stringify(param),
            success: function(data) {
                if(data == true){
                    $(".Frame").attr("style","display:none;");
                }
            }
        }) 
    }
    
}
//提交算子信息及逻辑运算
function ConfirmLogic(){
    let tableAl ={
        algorithmauthor:"",
        algorithmfun:'',
        algorithmname:$('#LogicName').val(),
        algorithmtype:1,
        des:"",
        ispublic:0,
        moduleid:window.bigData.formulaModuleId,
        remark:"",
        tno:""
    }
    let formula=""
    let logicLi = $('.logicLi')
    for(let i=0;i<logicLi.length;i++){
        
        let obj = logicLi.eq(i).find('.Logic-form-field option:selected').text()+logicLi.eq(i).find('.Logic-form-label option:selected').text()+ logicLi.eq(i).find('.Logic-form-value').val();
        formula += obj+ " and ";
    }
    if (formula.length > 0) {
        formula = formula.substr(0, formula.length - 5);
        tableAl.algorithmfun = formula
    }
    
    let tableModule={
        moduleid:window.bigData.formulaModuleId,
        remark:"",
        username:""
    }
    let param = {
        tableAlgorithm:tableAl,
        tableFuncs:[],
        tableModuleuserrelation:tableModule
    }
    console.log(param)
    if(window.bigData.formulaType == 'add'){
        $.ajax({
            type:"post",   
            dataType: "json",
            url:urlConfig.host+'/operatorMaintenance/addAlgorithm',
            contentType: "application/json;charset=UTF-8",
            data:JSON.stringify(param),  
            success: function(data) {
                if(data == true){
                    $(".Logic").attr("style","display:none;");
                }
            }
        }) 
    }
    if(window.bigData.formulaType == 'edit'){
        $.ajax({
            type:"post",   
            dataType: "json",
            url:urlConfig.host+'/operatorMaintenance/modAlgorithmById',
            contentType: "application/json;charset=UTF-8",
            data:JSON.stringify(param),
            success: function(data) {
                if(data == true){
                    $(".Logic").attr("style","display:none;");
                }
            }
        }) 
    }
    
}
//关闭上传弹框
function uploadClose(){
   $("#fileupload").hide();
}
//确定导入文件按钮
function uploadSure(){
    let filename = $(".inputfile").val()
    debugger
    $.ajax({
        type:"get",   
        dataType: "json",
        url:urlConfig.host+'/algorithmRule/readAlgorithmRuleFromFile',
        contentType: "application/json;charset=UTF-8",
        data:{
            // filename: $(".inputfile")[0].files[0].name
            filename:filename
        },
        success: function(data) {
            if(data == true){
               
            }
        }
    }) 
}