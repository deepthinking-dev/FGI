
    function FrameClose(){
        $(".Frame").attr("style","display:none;");
    }
    function LogicClose(){
        $(".Logic").attr("style","display:none;");
    }
    //选择算子类型
    function selectFormula(){
        var formulatype = document.getElementById('formulaType').value
        if(formulatype == 1){
            $(".Logic").attr("style","display:none;");
            $(".Frame").attr("style","display:block;");          
            $("#suanfaType").css('display', "none");
        }
        if(formulatype == 2){
            $(".Frame").attr("style","display:none;");
            $(".Logic").attr("style","display:block;");
            $("#suanfaType").css('display', "none");
       }
    }
    function fieldsClose(){
        $("#fields").attr("style","display:none;");
    }
    //选择变量类型
    $('body').on('change','.MathJaxInput2',(e) => {
        var objS = $(e.target).val()
        if(objS == "2"){
            $(e.target).parent('.width-select').nextAll('.isShow2').attr("style","display:none;");
            $(e.target).parent('.width-select').nextAll('.isShow3').attr("style","display:none;");
            $(e.target).parent('.width-select').nextAll('.isShow1').attr("style","display:block;");
        }
        if(objS == "1"){
            $(e.target).parent('.width-select').nextAll('.isShow1').attr("style","display:none;");
            $(e.target).parent('.width-select').nextAll('.isShow3').attr("style","display:none;");
            $(e.target).parent('.width-select').nextAll('.isShow2').attr("style","display:block;");
            $.ajax({
                url:urlConfig.host+'/module/getModuleColumns',
                data:{moduleId:11},
                success: function(data) {
                   console.log(data)
                   let str =``
                    data.map(item=>{
                        str +=`<tr id="${item.id}" moduleId="${item.moduleid}">
                                    <td class="fieldname">${item.fieldname}</td>
                                    <td>${item.fieldtype}</td>
                                    <td>${item.remark}</td>
                                    remark
                                </tr>`
                    })
                    $(".fieldsList").html(str) 
                }
            })
        }
    })
    //提交算子信息及公式编辑
    function ConfirmFrame(){
        let tableAl ={
            algorithmauthor:$('#gsName').val(),
            algorithmfun:$('#MathInput').val(),
            algorithmname:$('#AlgorithmnameY').val(),
            algorithmtype:2,
            des:$('#gsDes').val(),
            ispublic:0,
            moduleid:0,
            remark:"",
            id:$("#AlgorithmnameY").attr("tablealgorithmid")
        }
        let tableF=[]
        let tableModule={
            moduleid:0,
            remark:"",
            username:"",
            id:$("#AlgorithmnameY").attr("tablealgorithmid")
        }
        let MathJaxParamLength = $('.MathJaxParam')
        if(MathJaxParamLength.length > 0){
            for(let i=0;i<MathJaxParamLength.length;i++){
                let obj ={}
                if(window.bigData.formulaType == 'edit'){
                    obj.id = MathJaxParamLength.eq(i).attr('formulaid');
                    obj.algorithmid = MathJaxParamLength.eq(i).attr('formulaModuleId');
                }
                obj.moduleid =0;
                obj.remark = MathJaxParamLength.eq(i).find('.MathJaxInput4').val()
                obj.varname = MathJaxParamLength.eq(i).find('.MathJaxInput1').val()
                obj.vartype = MathJaxParamLength.eq(i).find('.MathJaxInput2').val()
                obj.inorout = 0;
                if(MathJaxParamLength.eq(i).find('.isShow1').css('display') == "block"){
                    obj.valvalue = MathJaxParamLength.eq(i).find('.MathJaxInput3').val()
                } else {
                    obj.valvalue = MathJaxParamLength.eq(i).find('.MathJaxSelect').val()
                }
                tableF.push(obj)
            }
        }
        if(window.bigData.formulaType == 'edit'){
            tableAl.algorithmauthor = $('#gsName').val();
            tableAl.des = $('#gsDes').val();
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
                    if(data.status == 1){
                        $(".Frame").hide();
                        toastr.success('保存成功！');
                        Topology.init();
                    }
                }
            })
        }
        if(window.bigData.formulaType == 'edit'){
            $.ajax({
                type:"post",
                dataType: "json",
                url:urlConfig.host+'/operatorMaintenance/modAlgorithmBaseInfoById',
                contentType: "application/json;charset=UTF-8",
                data:JSON.stringify(tableAl),
                success: function(data) {
                    console.log(data);
                }
            })
            $.ajax({
                type:"post",
                dataType: "json",
                url:urlConfig.host+'/operatorMaintenance/modAlgorithmFuncsById',
                contentType: "application/json;charset=UTF-8",
                data:JSON.stringify(param),
                success: function(data) {
                    if(data.status == 1){
                        $(".Frame").hide();
                        toastr.success('保存成功！');
                    }
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

//提交算子信息及逻辑运算
function ConfirmLogic(){
    let tableAl ={
        algorithmauthor:$("#ljName").val(),
        algorithmfun:'',
        algorithmname:$('#LogicName').val(),
        algorithmtype:3,
        des:$("#ljDes").val(),
        ispublic:0,
        moduleid:0,
        remark:"",
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
        moduleid:0,
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

    if(window.bigData.formulaType == 'add'){
        $.ajax({
            type:"post",
            dataType: "json",
            url:urlConfig.host+'/operatorMaintenance/addAlgorithm',
            contentType: "application/json;charset=UTF-8",
            data:JSON.stringify(param),
            success: function(data) {
                if(data.status == 1){
                    toastr.success('保存成功！');
                    $(".Logic").attr("style","display:none;");
                    Topology.init();
                }
            }
        })
    }
    if(window.bigData.formulaType == 'edit'){
        $.ajax({
            type:"post",
            dataType: "json",
            url:urlConfig.host+'/operatorMaintenance/modAlgorithmBaseInfoById',
            contentType: "application/json;charset=UTF-8",
            data:JSON.stringify(tableAl),
            success: function(data) {
                if(data.status == 1){
                    toastr.success('保存成功！');
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
    let algorithmRuleDataList = []
    let spanId = $('#topo_canvas div span')
    let coordinate = ''
    if(spanId.length> 0){
        for(let i=0;i<spanId.length-1;i++){
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
    // let node =window.Topology.nodeData
    // node.map(item=>{
    //     let obj = (item.id).substring(0,(item.id).length-2)+"\\"+item.fullIconRect.x+"\\"+item.fullIconRect.y
    //     coordinate += obj +","
    // })
    console.log(coordinate)
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
                window.getAllData('/operatorMaintenance/getAllAlgorithm',{id:'id',Tname:'tableAlgorithm',name:'algorithmname'},'算子',{username:null})
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
                window.getAllData('/algorithmRule/getAllAlgorithmRule',{id:'id',Tname:'rolename'},'规则',{username:null})
            }
        }
    })
}
//取消删除规则
function ruleDelClose(){
    $('#lkrRule').fadeToggle(500)
}