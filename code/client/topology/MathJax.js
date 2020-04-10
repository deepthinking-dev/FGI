
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
        if($('#AlgorithmnameY').val() == ""){
            toastr.info('请填写算子名称！')
            return false;
        }
        if($('#MathInput').val() == ""){
            toastr.info('请填写公式！')
            return false;
        }
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
                        toastr.success(data.msg);
                        Topology.init();
                        dictionary()
                    }
                    if(data.status == 2){
                        toastr.info(data.msg)
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
                        dictionary()
                        Topology.init();
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
        toastr.info("请填写逻辑运算名称")
        return false;
    }
    if(logicLi.length > 0){
        for(let i=0;i<logicLi.length;i++){

            let obj = logicLi.eq(i).find('.Logic-form-field option:selected').text()+logicLi.eq(i).find('.Logic-form-label option:selected').text()+ logicLi.eq(i).find('.Logic-form-value').val();
            formula += obj+ " and ";
        }
    }else{
        toastr.info("请至少填写一个")
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
                    dictionary()
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
                    Topology.init();
                    dictionary()
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
    // let filename = 'F:\MyDownloads\FGI\算法规则导出.txt'
    $.ajax({
        type:"get",
        dataType: "json",
        url:urlConfig.host+'/algorithmRule/readAlgorithmRuleFromFile',
        contentType: "application/json;charset=UTF-8",
        data:{
           filename: $(".inputfile")[0].files[0].name
            // filename:filename
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
//规则弹框
function ruleOpen(){
    $("#sureRule").fadeToggle(500);
}
//关闭规则弹框
function RuleClose(){
    $("#sureRule").fadeToggle(500);
}
//保存规则（一起新增）
function ruleSure(){
    let algorithmRuleDataList = [{
        algorithmconditions:[],
        des:'',
        id:0,
        interfaceID:0,
        parametersID:0,
        preInterfaceID:0,
        preParametersID:0,
        remark:"",
        roleid:'',
        table_InterfaceRole:{
            des:'',
            id:0,
            interfaceid:0,
            parametersid:0,
            preinterfaceid:0,
            preparametersid:0,
            remark:"",
            roleid:0
        }
    }]
    canvas.data.nodes.map(item => {
        if(item.childStand){
            if(item.childStand.type == data[0].id+'的弟弟'){
                console.log(item,'45454545')  
                let obj = {
                    behavior:'',
                    expression:'',
                    id:'',
                    interfaceparametersid:0,
                    interfaceroleid:0,
                    remark:'',
                    valuesources:''
                } 
                algorithmRuleDataList.algorithmconditions.push(obj)                         
            }
        }
    })


    let operatorInterfaceDataModels = [
        {
            algorithmID:0,
            id:0 ,
            interfaceName:"",
            roleID:0,
            tableInterfaceparametersList:[
                {
                    id:0,
                    inorout:0,
                    interfaceid:0,
                    parametersname:"",
                    parameterssources:''
                }
            ]
        }
    ]
    let tableRole={
        coordinate:'',
        des:'',
        entrancenote:'',
        id:0,
        remark:'',
        rolename:'',
        uuserid:0
    }
    let algorithmRuleSaveDataModel ={
        interfaceRoleDataModels :algorithmRuleDataList,
        operatorInterfaceDataModels:operatorInterfaceDataModels,
        tableRole:tableRole
      }
    $.ajax({
        type:"post",
        dataType: "json",
        url:urlConfig.host+'/algorithmRule/saveAlgorithmRule',
        contentType: "application/json;charset=UTF-8",
        data:JSON.stringify(algorithmRuleSaveDataModel),
        success: function(data) {
            if(data == true){
                $("#sureRule").fadeToggle(500)
                toastr.success('保存成功！');
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
                toastr.success('删除成功！');
                window.getAllData('/algorithmRule/getAllAlgorithmRule',{id:'id',Tname:'rolename'},'规则',{username:null})
            }
        }
    })
}
//取消删除规则
function ruleDelClose(){
    $('#lkrRule').fadeToggle(500)
}

//动作确定
function ActionSure(){
    let data = JSON.parse(JSON.stringify(window.Topology.dblclickNode))
    let test = JSON.parse(JSON.stringify(window.Topology.dblclickNode)),num = {}
    let actionInfoNum = $('.ruleContentDiv .actionInfo')
   
    let saveList ={
        id :data.id,
        children:[]
    }
    let tableAlgorithmIndex = data.id.indexOf("tableAlgorithm");
    let currId = data.id.slice(0,tableAlgorithmIndex);
    if(actionInfoNum.length  > data.data.inNum){
        for(let i =0;i< actionInfoNum.length ;i++){
            let uuid = $('.ruleContentDiv .actionInfo').eq(i).attr("data-uuid")           
            if(!uuid){

                let xinguid = guid()
                $('.ruleContentDiv .actionInfo').eq(i).attr("data-uuid",xinguid)
                let widths = 20
                let heights = 10
                console.log(data.data,'444444444444',widths,heights) 
                console.log(num)
            
                
                if($('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected1').val() == 0){
                    data.data.inNum ++
                    num = {
                        x:-widths,
                        y:(heights*data.data.inNum)+10*(data.data.inNum + 1)
                    }
                    let typeIn = $('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected2').val();
                    if(typeIn== "基本类型"){
                        typeIn =$('.ruleContentDiv .actionInfo').eq(i).find('#varTypeInput').val();
                    }
                    window.bigData.isAddInOutType = "in";
                    test.id = data.id +"IN"+ "_" +xinguid+ "---" +typeIn;
                    test.text = "in"+ data.data.inNum;
                    
                }else{
                    debugger
                    data.data.outNum ++
                    num = {
                        x:data.rect.width,
                        y:(heights*data.data.outNum)+10*data.data.outNum +10
                    }
                    window.bigData.isAddInOutType = "out"
                    let typeIn = $('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected2').val();
                    if(typeIn== "基本类型"){
                        typeIn =$('.ruleContentDiv .actionInfo').eq(i).find('#varTypeInput').val();
                    }
                    test.id = data.id +"OUT"+ "_" +xinguid+"---" +typeIn;
                    test.text = "out"+data.data.outNum
                }

                test.rect.x = data.rect.x + num.x
                test.rect.y = data.rect.y + num.y
                test.rect.width = widths
                test.rect.height = heights

                test.rect.ex = data.rect.ex + num.x
                test.rect.ey = data.rect.ey + num.y
                test.rect.center.x = data.rect.center.x + num.x
                test.rect.center.y = data.rect.center.y + num.y
                test.fullTextRect.x = 0
                test.fullTextRect.y = 0
                test.textRect.x = test.rect.x
                test.textRect.y =  test.rect.y
                test.textRect.width = 0
                test.textRect.height = 0
                test.paddingTopNum = 0
                test.paddingTop = 0
                test.fullTextRect.x = data.fullTextRect.x + num.x
                test.fullTextRect.y = data.fullTextRect.y + num.y
                test.iconRect.x = data.iconRect.x + num.x
                test.iconRect.y = data.iconRect.y + num.y
                test.fullIconRect.x = data.fullIconRect.x + num.x
                test.fullIconRect.y = data.fullIconRect.y + num.y
                test.childStand = {
                    type:data.id+'的弟弟',
                    wz:num,
                    bb:{
                        x:data.rect.x,
                        y:data.rect.y,
                        ex:data.rect.ex,
                        ey:data.rect.ey
                    }
                }
                if($('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected1').val() == 0){
                    test.anchors.map((obj,i) => {
                        obj.x = data.anchors[i].x-185 + num.x
                        obj.y = data.anchors[i].y-85 + num.y
                    })
                    test.rotatedAnchors.map((obj,i) => {
                        obj.x = data.rotatedAnchors[i].x-185 + num.x
                        obj.y = data.rotatedAnchors[i].y-85 + num.y
                    })
                }else{
                    test.anchors.map((obj,i) => {
                        obj.x = data.anchors[i].x+218 + num.x
                        obj.y = data.anchors[i].y+115 + num.y
                    })
                    test.rotatedAnchors.map((obj,i) => {
                        obj.x = data.rotatedAnchors[i].x+220 + num.x
                        obj.y = data.rotatedAnchors[i].y-115 + num.y
                    })
                }
                test.text = $('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected2').val();
                window.bigData.isAddInOut = true;

                let flag = canvas.addNode(test)
                canvas.lockNodes([test], true)
                if(flag){
                    if($('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected1').val() == 0){
                        if(data.data.inNum  > data.data.outNum){
                            if( window.Topology.dblclickNode.rect.height < (heights*(data.data.inNum+1)+10*(data.data.inNum+1))){
                                window.Topology.dblclickNode.rect.ey = window.Topology.dblclickNode.rect.ey + heights+15
                                window.Topology.dblclickNode.rect.height = window.Topology.dblclickNode.rect.height + heights+15
                            }
                            console.log( window.Topology.dblclickNode.rect.height , (heights*(data.data.inNum+1) +10*(data.data.inNum+1)))
                        }
                    }else{
                        if(data.data.outNum > data.data.inNum){
                            if( window.Topology.dblclickNode.rect.height < (heights*(data.data.outNum+1) +10*(data.data.outNum+1))){
                                window.Topology.dblclickNode.rect.ey = window.Topology.dblclickNode.rect.ey + heights+15
                                window.Topology.dblclickNode.rect.height = window.Topology.dblclickNode.rect.height + heights+15
                            }
                            console.log( window.Topology.dblclickNode.rect.height , (heights*(data.data.outNum+1) +10*(data.data.outNum+1)))
                        }
                    }
                }else{
                    window.bigData.isAddInOut = false
                }


            }
        }

    }
    let isFlag = false
    let lsList = []
    for(let i =0;i< actionInfoNum.length ;i++){
        let id = $('.ruleContentDiv .actionInfo').eq(i).attr("Funcs-id")
        if(id){
            id =id
        }else{
            id =""
        }
        let varName = $('.ruleContentDiv .actionInfo').eq(i).find('.varNameInput').val()
        if(varName){
            varName = varName
        }else{
            varName = $('.ruleContentDiv .actionInfo').find('.varNameInput option:selected').val()
        }
        obj = {
           id:id,
           uuid:$('.ruleContentDiv .actionInfo').eq(i).attr("data-uuid"),
           algorithmid:currId,
           varname:varName,
           vartype:$('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected2').val(),
           valvalue:$('.ruleContentDiv .actionInfo').eq(i).find('#varTypeInput').val(),
           inorout:$('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected1 option:selected').val(),
           remark:$('.ruleContentDiv .actionInfo').eq(i).attr("data-title")
       }
       lsList.push(obj)
   }
    window.Topology.tools.map(isCZdata=>{
        if(isCZdata.id == data.id){
            isFlag =true
            isCZdata.children = lsList
        }
    })
    if(!isFlag){
        saveList.children = lsList
        window.Topology.tools.push(saveList)
    }
    canvas.render();
    $('#ruleAct').fadeToggle(500)
}

function guid() {
	return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
		var r = Math.random() * 16 | 0,
			v = c == 'x' ? r : (r & 0x3 | 0x8);
		return v.toString(16);
	});
}
//动作取消
function ActionClose(){
    let data = JSON.parse(JSON.stringify(window.Topology.dblclickNode))
    let actionInfoNum = $('.ruleContentDiv .actionInfo')
    let tableAlgorithmIndex = data.id.indexOf("tableAlgorithm");
    let currId = data.id.slice(0,tableAlgorithmIndex);
    let saveList ={
        id :data.id,
        children:[]
    }
    let isFlag = false
    let lsList = []
    for(let i =0;i< actionInfoNum.length ;i++){
        let id = $('.ruleContentDiv .actionInfo').eq(i).attr("Funcs-id")
        if(id){
            id =id
        }else{
            id =""
        }
        let varName = $('.ruleContentDiv .actionInfo').eq(i).find('.varNameInput').val()
        if(varName){
            varName = varName
        }else{
            varName = $('.ruleContentDiv .actionInfo').find('.varNameInput option:selected').val()
        }
        obj = {
           id:id,
           uuid:$('.ruleContentDiv .actionInfo').eq(i).attr("data-uuid"),
           algorithmid:currId,
           varname:varName,
           vartype:$('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected2').val(),
           valvalue:$('.ruleContentDiv .actionInfo').eq(i).find('#varTypeInput').val(),
           inorout:$('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected1').val(),
           remark:$('.ruleContentDiv .actionInfo').eq(i).attr("data-title")
       }
       lsList.push(obj)
   }
    window.Topology.tools.map(isCZdata=>{
        if(isCZdata.id == data.id){
            isFlag =true
            isCZdata.children = lsList
        }
    })
    if(!isFlag){
        saveList.children = lsList
        window.Topology.tools.push(saveList)
    }
    $('#ruleAct').fadeToggle(500)
}

//加
function ruleAddButtonS(){
    let data = JSON.parse(JSON.stringify(window.Topology.dblclickNode))
    let tableAlgorithmIndex = data.id.indexOf("tableAlgorithm");
    let currId = data.id.slice(0,tableAlgorithmIndex);
    $.ajax({
        url:urlConfig.host+'/operatorMaintenance/getAlgorithmById',
        data:{algthId:currId},
        success: function(data) {
            let str =`<div class="actionInfo">
                    <select class="actionSelected1">
                        <option value="1">输出</option>
                        <option value="0">输入</option>
                    </select>
                    <input value="" class="varNameInput" style="display: none;">   
                    <select class="varNameInput1">
                    </select>
                    <input value="" class="actionSelected2" disabled>   
                    <input value="" id="varTypeInput" disabled>                                                 
                    <button type="button" onclick="reduceButton(event)">x</button> 
                    </div>` 
                    $('.ruleContentDiv').append(str);
                    let actionInfoNum = $('.ruleContentDiv .actionInfo').length-1
                    let lstr1=`<option>请选择</option>`
                    data.tableFuncs.map(item => {
                       lstr1 += `<option>${item.varname}</option>`
                    })
                  $('.ruleContentDiv .actionInfo').eq(actionInfoNum).find(".varNameInput1").html(lstr1)
                  $('body').off("change").on('change','.varNameInput1',(e) => {
                    //   debugger
                    data.tableFuncs.map(item => {
                       if($(e.target).val()== item.varname){
                            if(item.vartype == "1"){
                                $(e.target).parent().children('.actionSelected2').val("基本类型")
                                $(e.target).parent().children('#varTypeInput').val(item.valvalue)
                                $(e.target).parent().children('.varNameInput').val($(e.target).val())
                            }
                            
                            if(item.vartype == "2"){
                                $(e.target).parent().children('.actionSelected2').val("常量")
                                $(e.target).parent().children('#varTypeInput').val(item.valvalue)
                                $(e.target).parent().children('.varNameInput').val($(e.target).val())
                            }
                            if(item.vartype == "3"){
                                $(e.target).parent().children('.actionSelected2').val("对象")
                                $(e.target).parent().children('#varTypeInput').val(item.valvalue)
                                $(e.target).parent().children('.varNameInput').val($(e.target).val())
                            }                
                       }else if($(e.target).val()== "请选择"){
                            $(e.target).parent().children('.actionSelected2').val("")
                            $(e.target).parent().children('#varTypeInput').val("")
                            $(e.target).parent().children('.varNameInput').val("")
                       }
                    })
                 })

        }
    })
}
//减
function reduceButton(e){
    $(e.target).parent().remove()
}