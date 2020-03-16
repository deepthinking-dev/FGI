
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
        
    }else{
        alert("请填写信息")
        return false;
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
    if(($('#LogicName').val()).trim() == ''){
        alert("请填写逻辑运算名称")
        return false;
    }
    if(logicLi.length > 0){
        for(let i=0;i<logicLi.length;i++){
        
            let obj = logicLi.eq(i).find('.Logic-form-field option:selected').text()+logicLi.eq(i).find('.Logic-form-label option:selected').text()+ logicLi.eq(i).find('.Logic-form-value').val();
            formula += obj+ " and ";
        }
    }else{
        alert("请至少填写一个")
        return false;
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
    if(window.bigData.formulaType == 'edit'){
        tableAl.id = $('#LogicName').attr("tableAlgorithmid");
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
    let filename = 'F:\MyDownloads\FGI\算法规则导出.txt'
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

//选择关系确定按钮
function selectSure(){
    let str = $('.selectRelation option:selected').text();
    console.log(str)
    $('#'+window.currentId).text(str)
    $("#selectRela").hide();

}
//选择关系关闭按钮
function RelateClose(){
    $("#selectRela").hide();
}

//保存规则（一起新增）
function ruleSure(){
    debugger
    let algorithmRuleDataList = []
    let spanId = $('#topo_canvas div span')
    let coordinate = ''
    if(spanId.length> 0){
        for(let i=0;i<spanId.length-1;i++){
            debugger
            let  id=spanId.eq(i).attr('id')
            let str = id.split("_")
            let obj={
                algorithmModel:null,
                algorithmid:str[0].substring(0,str[0].length-2),
                des:'',
                id:'',
                preaAlgorithmModel:null,
                prealgorithmid:str[2].substring(0,str[2].length-2),
                remark:null,
                roleId:null,
                tableAlgorithmcondition:{
                    algorithmroleid:'',
                    id:'',
                    logicrelation:spanId.eq(i).text(),
                    logicvalue:'',
                    remark:''
                }
            }
            algorithmRuleDataList.push(obj)
        }
       
    }
    let node =window.Topology.nodeData
    node.map(item=>{
        let obj = (item.id).substring(0,item.length-2)+"\\"+item.fullIconRect.x+"\\"+item.fullIconRect.y
        coordinate += obj +","
        console.log(obj)
    })

     console.log(algorithmRuleDataList)
     if($("#ruleName").val() == ''){
         alert('请输入规则名称')
         return false;
     }
    let algorithmRuleSaveDataModel ={
        algorithmRuleDataModelList:algorithmRuleDataList,
        coordinateinfo:coordinate,
        tableRole:{
            des:'',
            id:'',
            remark:$("#ruleRemark").val(),
            rolename:$("#ruleName").val(),
            tno:''
        }
        
    }
    $.ajax({
        type:"post",   
        dataType: "json",
        url:urlConfig.host+'/algorithmRule/saveAlgorithmRule',
        contentType: "application/json;charset=UTF-8",
        data:JSON.stringify(algorithmRuleSaveDataModel),
        success: function(data) {
            if(data == true){
               
            }
        }
    }) 
}
//删除算子
function ConfirmDelAlgorithm(){
    $.ajax({
        url:urlConfig.host+'/operatorMaintenance/delAlgorithmById',
        data:{algthId:window.bigData.delAlgorithmId},
        success: function(data) {
            if(data){
                window.bigData.delAlgorithmId = ''
                $('#lkrAlgorithm').fadeToggle(500)
                window.getAllData('/operatorMaintenance/getAllAlgorithm',{id:'id',name:'algorithmname'},'算子',{username:null})
            }
        }
    })
}
function AlgorithmeDelClose(){
    $('#lkrAlgorithm').fadeToggle(500)
}
// 确定删除规则
function ConfirmDelRule(){
    $.ajax({
        type:"get",   
        dataType: "json",
        url:urlConfig.host+'/algorithmRule/delAlgorithmRuleById',
        contentType: "application/json;charset=UTF-8",
        data:{
            id:window.bigData.delRuleId
        },
        success: function(data) {
            if(data == true){
                window.bigData.delRuleId = ''
                $('#lkrRule').fadeToggle(500)
                window.getAllData('/algorithmRule/getAllAlgorithmRule',{id:'id',name:'rolename',ruleType:'规则'},'规则',{username:null})
            }
        }
    })
}
//取消删除规则
function ruleDelClose(){
    $('#lkrRule').fadeToggle(500)
}