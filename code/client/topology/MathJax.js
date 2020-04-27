
    function FrameClose(){
        if(window.bigData.formulaType=="add" || window.bigData.formulaType=="edit"){
            parent.$("#dicDiv").show()
        }else{
            parent.$("#dicDiv").hide()
        }
        parent.$(".Frame").attr("style","display:none;");
     
    }
    function LogicClose(){
        parent.$(".Logic").attr("style","display:none;");
    }
    //选择算法类型
    function selectFormula(){
        var formulatype = document.getElementById('formulaType').value
        if(formulatype == 1){
            parent.$(".Logic").attr("style","display:none;");
            parent.$(".Frame").attr("style","display:block;");
            parent.$("#suanfaType").css('display', "none");
        }
        if(formulatype == 2){
            parent.$(".Frame").attr("style","display:none;");
            parent.$(".Logic").attr("style","display:block;");
            parent.$("#suanfaType").css('display', "none");
       }
    }
    function fieldsClose(){
        parent.$("#fields").attr("style","display:none;");
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
                   let str =``
                    data.map(item=>{
                        str +=`<tr id="${item.id}" moduleId="${item.moduleid}">
                                    <td class="fieldname">${item.fieldname}</td>
                                    <td>${item.fieldtype}</td>
                                    <td>${item.remark}</td>
                                    remark
                                </tr>`
                    })
                    parent.$(".fieldsList").html(str)
                }
            })
        }
    })
    //提交算法信息及公式编辑
    function ConfirmFrame(){
        if($('#groupGs').val() == ""){
            $('.noticeList').append(`<li>${getTime()} 【算法】分组不能为空！</li>`)
            toastr.info(`【算法】分组不能为空！` )
            $("#flex_props1_home").scrollTop($("#flex_props1_home")[0].scrollHeight);
            return false;
        }
        if($('#AlgorithmnameY').val() == ""){
            parent.$('.noticeList').append(`<li>${parent.getTime()} 【算法】请填写算法名称！</li>`)
            parent.toastr.info(`【算法】请填写算法名称！` )
            parent.$("#flex_props1_home").scrollTop(parent.$("#flex_props1_home")[0].scrollHeight);
            return false;
        }
        if($('#MathInput').val() == ""){
            parent.$('.noticeList').append(`<li>${parent.getTime()} 【算法】请填写公式！</li>`)
            parent.toastr.info(`【算法】请填写公式！` )
            parent.$("#flex_props1_home").scrollTop(parent.$("#flex_props1_home")[0].scrollHeight);
            return false;
        }
        let tableAl ={
            algorithmauthor:$('#gsName').val(),
            algorithmfun:sessionStorage.latex,
            algorithmgroup:$('#groupGs').val(),
            algorithmname:$('#AlgorithmnameY').val(),
            algorithmtype:2,
            des:$('#gsDes').val(),
            ispublic:0,
            moduleid:0,
            remark:$('#companyGs').val(),
            id:$("#AlgorithmnameY").attr("tablealgorithmid"),
            remark2:sessionStorage.src,
            status:"",
            userid:0
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
                    obj.id = Number(MathJaxParamLength.eq(i).attr('formulaid')) ;
                    obj.algorithmid = Number($("#updateSzId").val());
                    obj.inorout = Number(MathJaxParamLength.eq(i).attr('inorout')) ;;
                }else{
                    obj.inorout = 0;
                }
                obj.moduleid =0;
                obj.remark = MathJaxParamLength.eq(i).find('.MathJaxInput4').val()
                obj.varname = MathJaxParamLength.eq(i).find('.MathJaxInput1').val()
                obj.vartype = MathJaxParamLength.eq(i).find('.MathJaxInput2').val()
               
                obj.parametername = MathJaxParamLength.eq(i).find('.MathJaxInputCs').val();
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
            tableAl.status =  $("#gsStatus").val();
        }
        let param = {
            tableAlgorithm:tableAl,
            tableFuncs:tableF,
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
                        parent.$(".Frame").hide();
                        parent.$('.noticeList').append(`<li>${parent.getTime()} 【算法】${data.msg}！</li>`)
                        parent.toastr.success(`【算法】${data.msg}！` )
                        parent.$("#flex_props1_home").scrollTop(parent.$("#flex_props1_home")[0].scrollHeight);
                        // Topology.init();
                        dictionary()
                        $("#dicDiv").show()

                    } else {
                        parent.$('.noticeList').append(`<li>${parent.getTime()} 【算法】${data.msg}！</li>`)
                        parent.toastr.info(`【算法】${data.msg}！` )
                        parent.$("#flex_props1_home").scrollTop(parent.$("#flex_props1_home")[0].scrollHeight);
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
                        // Topology.init();
                        parent.$('.noticeList').append(`<li>${parent.getTime()}【算法】保存成功！</li>`)
                        parent.toastr.success(`【算法】保存成功！` )
                        parent.$("#flex_props1_home").scrollTop(parent.$("#flex_props1_home")[0].scrollHeight);
                    } else {
                        parent.$('.noticeList').append(`<li>${parent.getTime()}【算法】 ${data.msg}！</li>`)
                        parent.toastr.info(`【算法】${data.msg}！` )
                        parent.$("#flex_props1_home").scrollTop(parent.$("#flex_props1_home")[0].scrollHeight);
                    }
                }
            })
        }
        sfWinList();
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

//提交算法信息及逻辑运算
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
        parent.$('.noticeList').append(`<li>${parent.getTime()}【算法】请填写逻辑运算名称!</li>`)
        parent.toastr.info(`【算法】请填写逻辑运算名称！` )
        parent.$("#flex_props1_home").scrollTop(parent.$("#flex_props1_home")[0].scrollHeight);
        return false;
    }
    if(logicLi.length > 0){
        for(let i=0;i<logicLi.length;i++){

            let obj = logicLi.eq(i).find('.Logic-form-field option:selected').text()+logicLi.eq(i).find('.Logic-form-label option:selected').text()+ logicLi.eq(i).find('.Logic-form-value').val();
            formula += obj+ " and ";
        }
    }else{
        parent.$('.noticeList').append(`<li>${parent.getTime()}【算法】请至少填写一个</li>`)
        parent.toastr.info(`【算法】请至少填写一个！` )
        parent.$("#flex_props1_home").scrollTop(parent.$("#flex_props1_home")[0].scrollHeight);
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
                    parent.$('.noticeList').append(`<li>${parent.getTime()}【算法】保存成功！</li>`)
                    parent.toastr.success(`【算法】保存成功！` )
                    parent.$("#flex_props1_home").scrollTop(parent.$("#flex_props1_home")[0].scrollHeight);
                    parent.$(".Logic").attr("style","display:none;");
                    // Topology.init();
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
                    parent.$('.noticeList').append(`<li>${parent.getTime()}【算法】保存成功！</li>`)
                    parent.toastr.success(`【算法】保存成功！` )
                    parent.$("#flex_props1_home").scrollTop(parent.$("#flex_props1_home")[0].scrollHeight);
                    parent.$(".Logic").attr("style","display:none;");
                    // Topology.init();
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
    if($(".inputfile")[0].files[0] == undefined){
        $('.noticeList').append(`<li>${getTime()}【规则】文件不能为空！</li>`)
        toastr.info(`【规则】文件不能为空！` )
        return false;
    }
    var formData = new FormData();
    formData.append("file",$(".inputfile")[0].files[0]);
    $.ajax({
        url:urlConfig.host+'/algorithmRule/readAlgorithmRuleFromFile',
        type:"POST",
        data: formData,
        cache: false,
　　　　 processData: false,
　　　　 contentType: false,
        success(data) {
            parent.$("#fileupload").hide();
            parent.$('.noticeList').append(`<li>${parent.getTime()} 导入成功！</li>`)
            parent.toastr.success(`导入成功！` )
            parent.$("#flex_props1_home").scrollTop(parent.$("#flex_props1_home")[0].scrollHeight);
        },
        error(data){
            parent.$('.noticeList').append(`<li>${parent.getTime()} 导入失败!</li>`)
            parent.toastr.info(`导入失败!` )
            parent.$("#flex_props1_home").scrollTop(parent.$("#flex_props1_home")[0].scrollHeight);
        }
    })
}

//选择关系确定按钮
function selectSure(){
    let str = $('.selectRelation option:selected').text();
    $('#'+window.currentId).text(str)
    parent.$("#selectRela").hide();

}
//选择关系关闭按钮
function RelateClose(){
    $("#selectRela").hide();
}
//规则弹框
function ruleOpen(){
    $("#sureRule").show();
    $.ajax({
        url:urlConfig.host+ '/group/findAllGroupMessagesByType',
        data:{type:3},
        success(res) {
            if(res.length == 0){
                parent.$('.noticeList').append(`<li>${parent.getTime()} 请先添加【规则】分组！</li>`)
                parent.toastr.info(`请先添加【规则】分组!` )
                parent.$("#flex_props1_home").scrollTop(parent.$("#flex_props1_home")[0].scrollHeight);
                return
            }
        }
    })
}
window.ruleOpen = ruleOpen
//关闭规则弹框
function RuleClose(){
    $("#sureRule").hide();
}
//保存规则（一起新增）
function ruleSure(){
    if($("#gzGroupName").val() == ""){
        $('.noticeList').append(`<li>${parent.getTime()} 分组不能为空！ </li>`)
        toastr.info(`分组不能为空！` )
        $("#flex_props1_home").scrollTop($("#flex_props1_home")[0].scrollHeight);
        return false
    }
     //动作
    if($("#ruleName").val() == ""){
        parent.$('.noticeList').append(`<li>${parent.getTime()} 请填写规则名称！ </li>`)
        parent.toastr.info(`请填写规则名称！` )
        parent.$("#flex_props1_home").scrollTop(parent.$("#flex_props1_home")[0].scrollHeight);
        return false
     }
    editGzType = false;
   parent.$("#currentGzName").text($("#ruleName").val());
   parent.$("#currentGzName").attr("title",$("#ruleName").val())
   parent.$("#currentGzDes").text($("#ruleRemark").val());
   parent.$("#currentGzDes").attr("title",$("#ruleRemark").val());
    let algorithmRuleDataList = [] 

    //参数借口
    let operatorInterfaceDataModels = [] 
    let childList ,coordinate;
    debugger
    if(window.canvasNowId == "canvas0"){
        childList = Object.values(window.Topology.tools)
        coordinate =JSON.stringify(canvas.data)
        window.globalActionDatas.map(item=>{
            let obj ={
                actionRelation:item.dataIn.interfaceRoleDataModels.actionRelation,
                preActionRelation:item.dataOut.interfaceRoleDataModels.preActionRelation,
                des:'',
                id:0,
                interfaceID: item.dataIn.interfaceRoleDataModels.interfaceID,
                parametersID:item.dataIn.interfaceRoleDataModels.parametersID,
                preInterfaceID:item.dataIn.interfaceRoleDataModels.preInterfaceID,
                preParametersID:item.dataIn.interfaceRoleDataModels.preParametersID,
                remark:"",
                roleid:0,
            }
            obj.algorithmconditions =  item.dataIn.interfaceRoleDataModels.algorithmconditions.concat(item.dataOut.interfaceRoleDataModels.algorithmconditions)
            algorithmRuleDataList.push(obj)
        })
   }else{
        childList = Object.values(window.frames[canvasNowId].contentWindow.Topology.tools);
        coordinate = JSON.stringify(window.frames[canvasNowId].contentWindow.canvas.data)
        window.frames[canvasNowId].contentWindow.globalActionDatas.map(item=>{
            let objs ={
                actionRelation:item.dataIn.interfaceRoleDataModels.actionRelation,
                preActionRelation:item.dataOut.interfaceRoleDataModels.preActionRelation,
                des:'',
                id:0,
                interfaceID: item.dataIn.interfaceRoleDataModels.interfaceID,
                parametersID:item.dataIn.interfaceRoleDataModels.parametersID,
                preInterfaceID:item.dataIn.interfaceRoleDataModels.preInterfaceID,
                preParametersID:item.dataIn.interfaceRoleDataModels.preParametersID,
                remark:"",
                roleid:0,
            }
            objs.algorithmconditions =  item.dataIn.interfaceRoleDataModels.algorithmconditions.concat(item.dataOut.interfaceRoleDataModels.algorithmconditions)
            algorithmRuleDataList.push(objs)
        })
   }
   childList.map(item=>{
        // let bigList = []
        let objF = {
            algorithmID:item.id,
            id:item.uuid,
            interfaceName:item.name,
            roleID:0,
            tableInterfaceparametersList:[]
        }
       operatorInterfaceDataModels.push(objF)
        
        item.children.map(index=>{
           
            let CsObj = {
                id:index.uuid.substr((index.uuid.indexOf('---')-36),36),
                inorout:index.inorout,
                interfaceid:item.uuid,
                parametersname:index.varname,
                parameterssources:index.id
            }
            objF.tableInterfaceparametersList.push(CsObj)
        })
    })
    //规则本身信息
    let tableRole={   
        coordinate:coordinate,
        des:$("#ruleRemark").val(),
        entrancenote:$("#ruleDes").attr("data")?$("#ruleDes").attr("data"):"",
        id:0,
        remark:'',
        rolename:$("#ruleName").val(),
        uuserid:0,
        remark2:'',
        remark3:'',
        status:"",
        rolegroup:$("#gzGroupName").val(),
    }
    let algorithmRuleSaveDataModel ={
        interfaceRoleDataModels :algorithmRuleDataList,
        operatorInterfaceDataModels:operatorInterfaceDataModels,
        tableRole:tableRole
      }
    let ruleType
    if(window.canvasNowId == "canvas0"){
        ruleType =window.bigData.ruleType

    }else{
        ruleType =window.frames[canvasNowId].contentWindow.bigData.ruleType
    }
    if(ruleType == "edit"){
        if(window.canvasNowId == "canvas0"){
            tableRole.id = window.bigData.editRuleId
        }else{
            tableRole.id = window.frames[canvasNowId].contentWindow.bigData.editRuleId
        }
        $.ajax({
            type:"post",
            dataType: "json",
            url:urlConfig.host+'/algorithmRule/modAlgorithmRuleBase',
            contentType: "application/json;charset=UTF-8",
            data:JSON.stringify(tableRole),
            success: function(data) {
                parent.$("#sureRule").fadeToggle(500)
                parent.$('.noticeList').append(`<li>${parent.getTime()}【规则】修改成功！ </li>`)
                parent.toastr.success(`【规则】修改成功！` )
                parent.$("#flex_props1_home").scrollTop(parent.$("#flex_props1_home")[0].scrollHeight);
            }
        })
    }else{
        $.ajax({
            type:"post",
            dataType: "json",
            url:urlConfig.host+'/algorithmRule/saveAlgorithmRule',
            contentType: "application/json;charset=UTF-8",
            data:JSON.stringify(algorithmRuleSaveDataModel),
            success: function(data) {
                parent.$("#sureRule").hide()
                parent.$('.noticeList').append(`<li>${parent.getTime()}【规则】保存成功！ </li>`)
                parent.toastr.success(`【规则】保存成功！` )
                parent.$("#flex_props1_home").scrollTop(parent.$("#flex_props1_home")[0].scrollHeight);
                let isExportButton
                if(window.canvasNowId == "canvas0"){
                    window.bigData.isExportId = data.tableRole.id
                    isExportButton = window.bigData.isExportButton
                }else{
                    window.frames[canvasNowId].contentWindow.bigData.isExportId = data.tableRole.id
                    isExportButton = window.frames[canvasNowId].contentWindow.bigData.isExportButton
                }

                if(isExportButton){
                    location.href= urlConfig.host+'/algorithmRule/saveAlgorithmRule2File?id=' + data.tableRole.id
                    if(window.canvasNowId == "canvas0"){
                        window.bigData.isExportButton = false
                    }else{
                        window.frames[canvasNowId].contentWindow.bigData.isExportButton = false
                    }

                }
              
            }
        })
    }
    $.ajax({
        url: urlConfig.host + '/algorithmRule/getAlgorithmRuleById',
        type:"get",
        data: {Id:window.bigData.editRuleId},
        success(data) {
            responseActionDatas = data.interfaceRoleDataModels
        }
    })
}
window.ruleSure = ruleSure
//删除算法
function ConfirmDelAlgorithm(){
    $.ajax({
        url:urlConfig.host+'/operatorMaintenance/delAlgorithmById',
        data:{algthId:window.bigData.delAlgorithmId},
        success: function(data) {
            if(data.status == 1){
                window.bigData.delAlgorithmId = ''
                parent.$('#lkrAlgorithm').fadeToggle(500)
                window.getAllData('/operatorMaintenance/getAllAlgorithm',{id:'id',Tname:'tableAlgorithm',name:'algorithmname'},'tableAlgorithm',{username:null})
            }
            if(data.status == 2){
                parent. $('.noticeList').append(`<li>${parent.getTime()}【算法】${data.msg} </li>`)
                parent.toastr.info(`【算法】${data.msg}` )
                parent.$("#flex_props1_home").scrollTop(parent.$("#flex_props1_home")[0].scrollHeight);
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
                parent.$('.noticeList').append(`<li>${parent.getTime()}【算法】删除成功 </li>`)
                parent.toastr.success(`【算法】删除成功` )
                parent.$("#flex_props1_home").scrollTop(parent.$("#flex_props1_home")[0].scrollHeight);
                canvas.data.nodes = [];
                canvas.data.lines = [];               
                window.bigData.ruleType = "add"
                window.bigData.isExportId = ''
                window.Topology.tools = {}
                $("#currentGzName").text("").attr({title:""})
                $("#currentGzDes").text("").attr({title:""})
                $("#ruleDeleteDiv").hide()
                $('#gzDiv').show()
                canvas.render();
                getGzList()
                ruleGroupShow()
                freshClick("gzWinTree")
                freshClick("ruleTree")
            }
        }
    })
}
//取消删除规则
function ruleDelClose(){
    $('#ruleDeleteDiv').hide()
}

//动作确定
function ActionSure(){
    //双击的大模块数据
    let data,test,childList;
    if(window.canvasNowId == "canvas0"){
         data = JSON.parse(JSON.stringify(window.Topology.dblclickNode))
        //拷贝大模块数据变成小接口数据
         test = JSON.parse(JSON.stringify(window.Topology.dblclickNode)),num = {}
         childList  =window.Topology.tools[data.id].children
    }else{
        cwin = window.frames[canvasNowId].contentWindow;
         data = JSON.parse(JSON.stringify(cwin.Topology.dblclickNode))
        //拷贝大模块数据变成小接口数据
         test = JSON.parse(JSON.stringify(cwin.Topology.dblclickNode)),num = {}
         childList =cwin.Topology.tools[data.id].children
    }


    // let data = JSON.parse(JSON.stringify(window.Topology.dblclickNode))
    // //拷贝大模块数据变成小接口数据
    // let test = JSON.parse(JSON.stringify(window.Topology.dblclickNode)),num = {}
    //接口参数的个数
    let actionInfoNum = $('.ruleContentDiv .actionInfo')
    //当前大模块的小接口节点数据
    let nowList =[]

    let currId = data.data.sid;
    if(actionInfoNum.length  > data.data.inNum){
        for(let i =0;i< actionInfoNum.length ;i++){
            let varName =  parent.$('.ruleContentDiv .actionInfo').eq(i).find('.varNameInput1 option:selected').val()
            if(varName =="请选择"){
                parent.$('.noticeList').append(`<li>${parent.getTime()} 请选择【算法】参数名称！ </li>`)
                parent.toastr.info(`请选择【算法】参数名称！` )
                parent.$("#flex_props1_home").scrollTop(parent.$("#flex_props1_home")[0].scrollHeight);
                return false;
            }
            //有uuid说明这个小接口已存在
            let uuid = parent.$('.ruleContentDiv .actionInfo').eq(i).attr("data-uuid")
              //没得uuid，说明要新增小接口
            if(!uuid){

                let xinguid = guid()
                let typeIn  =""
                parent.$('.ruleContentDiv .actionInfo').eq(i).attr("data-uuid",xinguid)
                let widths = 20
                let heights = 10               
               
                if(parent.$('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected1').val() == 0){
                    data.data.inNum ++
                    num = {
                        x:-widths,
                        y:(heights*data.data.inNum)+10*(data.data.inNum)
                    }
                    typeIn = parent.$('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected2').val();
                    if(typeIn== "基本类型"){
                        typeIn =parent.$('.ruleContentDiv .actionInfo').eq(i).find('#varTypeInput').val();
                    }
                    test.id = xinguid+ "---" +typeIn;
                    
                }else{
                    if(window.canvasNowId == "canvas0"){
                        window.Topology.dblclickNode.data.outNum ++
                       
                    }else{
                        window.frames[canvasNowId].contentWindow.Topology.dblclickNode.data.outNum++   
                    }
                    num = {
                        x:data.rect.width,
                        y:(heights*data.data.outNum)+10*(data.data.outNum-1)
                    }
                    typeIn = parent.$('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected2').val();
                    if(typeIn== "基本类型"){
                        typeIn =parent.$('.ruleContentDiv .actionInfo').eq(i).find('#varTypeInput').val();
                    }
                    test.id = xinguid+"---" +typeIn;
                }
                switch (typeIn) {
                    case '常量':
                        fillStyle = '#0eff23';
                        break;
                    case '对象':
                        fillStyle = '#ff00e7';
                        break;
                    case 'int':
                        fillStyle = 'red';
                        break;
                    case 'byte':
                        fillStyle = '#ff7749';
                        break;
                    case 'long':
                        fillStyle = '#a4ff59';
                        break;
                    case 'short':
                        fillStyle = '#fb61ff';
                        break;
                    case 'float':
                        fillStyle = 'blue';
                        break;
                    case 'double':
                        fillStyle = 'green';
                        break;
                    case 'boolean':
                        fillStyle = 'aqua';
                        break;
                    case 'number':
                        fillStyle = 'orange';
                        break;
                    case 'char':
                        fillStyle = '#7cc6ff';
                        break;
                    case 'date':
                        fillStyle = '#ff4286';
                        break;
                    case 'string':
                        fillStyle = '#00c1ff';
                        break;
                    case 'blob':
                        fillStyle = '#6a46ff';
                        break;
                    case 'array':
                        fillStyle = '#ffe964';
                        break;
                }
                test.rect.x = data.rect.x + num.x
                test.rect.y = data.rect.y + num.y
                test.rect.width = widths
                test.rect.height = heights
                test.bkType = 0
                test.fillStyle = fillStyle
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
                test.paddingTop = -4
                test.fullTextRect.x = data.fullTextRect.x + num.x
                test.fullTextRect.y = data.fullTextRect.y + num.y
                test.iconRect.x = data.iconRect.x + num.x
                test.iconRect.y = data.iconRect.y + num.y
                test.fullIconRect.x = data.fullIconRect.x + num.x
                test.fullIconRect.y = data.fullIconRect.y + num.y
                test.textMaxLine = 1
                test.childStand = {
                    type:"OUT",
                    wz:num,
                    bb:{
                        x:data.rect.x,
                        y:data.rect.y,
                        ex:data.rect.ex,
                        ey:data.rect.ey
                    },
                    text:parent.$('.ruleContentDiv .actionInfo').eq(i).find('#varTypeInput').val(),
                    fid:data.data.sid,
                    fUUid: data.id,
                    canshuId: parent.$('.ruleContentDiv .actionInfo').eq(i).attr("Funcs-id")
                }
                if(parent.$('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected1').val() == 0){
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
                test.text = parent.$('.ruleContentDiv .actionInfo').eq(i).find('.varNameInput1').val();
                let flag 
                if(window.canvasNowId == "canvas0"){
                    window.Topology.dblclickNode.data.outNum ++
                    flag = canvas.addNode(test)
                    canvas.lockNodes([test], true)
                   
                }else{
                    window.frames[canvasNowId].contentWindow.Topology.dblclickNode.data.outNum++
                    flag = window.frames[canvasNowId].contentWindow.canvas.addNode(test)
                    window.frames[canvasNowId].contentWindow.canvas.lockNodes([test], true)

                }

                if(flag){
                    if(data.data.outNum > data.data.inNum){
                        if(window.canvasNowId == "canvas0"){
                            if( window.Topology.dblclickNode.rect.height < (heights*(data.data.outNum+1) +10*(data.data.outNum+1))){
                                window.Topology.dblclickNode.rect.ey = window.Topology.dblclickNode.rect.ey + heights+15
                                window.Topology.dblclickNode.rect.height = window.Topology.dblclickNode.rect.height + heights+15
                            }
                        }else{
                            if( window.frames[canvasNowId].contentWindow.Topology.dblclickNode.rect.height < (heights*(data.data.outNum+1) +10*(data.data.outNum+1))){
                                window.frames[canvasNowId].contentWindow.Topology.dblclickNode.rect.ey = window.frames[canvasNowId].contentWindow.Topology.dblclickNode.rect.ey + heights+15
                                window.frames[canvasNowId].contentWindow.Topology.dblclickNode.rect.height = window.frames[canvasNowId].contentWindow.Topology.dblclickNode.rect.height + heights+15
                            }
                        }
                        
                    }
                }


            }
        }

    }
    // let isFlag = false
    let nowNodesList =[]
    let lsList = []
    //数据处理成需要的格式
    for(let i =0;i< actionInfoNum.length ;i++){
        let id = parent.$('.ruleContentDiv .actionInfo').eq(i).attr("Funcs-id")
        if(id){
            id =id
        }else{
            id =""
        }
        let typeIn = parent.$('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected2').val();
        if(typeIn== "基本类型"){
            typeIn =parent.$('.ruleContentDiv .actionInfo').eq(i).find('#varTypeInput').val();
        }
        let varName = parent.$('.ruleContentDiv .actionInfo').eq(i).find('.varNameInput').val()
        if(varName){
            varName = varName
        }else{
            varName = parent.$('.ruleContentDiv .actionInfo').eq(i).find('.varNameInput1 option:selected').val()
        }
        test.text = parent.$('.ruleContentDiv .actionInfo').eq(i).attr('data-parametername');
        canvas.render();
        let uuid = parent.$('.ruleContentDiv .actionInfo').eq(i).attr("data-uuid")
        if(uuid.indexOf('---') == -1){
            uuid= parent.$('.ruleContentDiv .actionInfo').eq(i).attr("data-uuid")+"---"+typeIn
        }else{
            uuid = uuid
        }
        let inorout = parent.$('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected1').val()
        if(inorout){
            inorout =inorout
        }else{
            inorout =parent.$('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected1 option:selected').val()
        }
          
        if(inorout == "输入" || inorout== "0"){
            inorout = 0
        }else{
            inorout = 1
        }
        obj = {
           id:id,
           uuid:uuid,
           algorithmid:currId,
           varname:parent.$('.ruleContentDiv .actionInfo').eq(i).attr("data-name"),
           vartype:parent.$('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected2').val(),
           valvalue:parent.$('.ruleContentDiv .actionInfo').eq(i).find('#varTypeInput').val(),
           inorout:inorout,
           remark:parent.$('.ruleContentDiv .actionInfo').eq(i).attr("data-title"),
           parametername:varName
       }
       lsList.push(obj)
   }
   nowNodesList = lsList
   let UPdataList = []
   let AddList = []
   let DelList = []

    //判断修改，新增，删除数据
    for(let i =0;i< actionInfoNum.length ;i++){
        let UPFlag = false
        for(var c = 0;c<childList.length;c++){
            let clyId = parent.$('.ruleContentDiv .actionInfo').eq(i).attr("data-uuid")
            if(clyId.indexOf('---') != -1){
                clyId = clyId.substr((clyId.indexOf('---')-36),36)
            } 
            let childUUid = childList[c].uuid.substr((childList[c].uuid.indexOf('---')-36),36)
            console.log(clyId,childUUid,"5555555555555555555555555555555555555555555")
            if(childUUid == clyId){
                UPdataList.push(childList[c])
                UPFlag = true
                break;
            }
        
            
        }
        if(!UPFlag){
            let id = parent.$('.ruleContentDiv .actionInfo').eq(i).attr("Funcs-id")
            if(id){
                id =id
            }else{
                id =""
            }
            let typeIn = parent.$('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected2').val();
            if(typeIn== "基本类型"){
                typeIn =parent.$('.ruleContentDiv .actionInfo').eq(i).find('#varTypeInput').val();
            }
            let varName = parent.$('.ruleContentDiv .actionInfo').eq(i).find('.varNameInput').val()
            if(varName){
                varName = varName
            }else{
                varName = parent.$('.ruleContentDiv .actionInfo').eq(i).find('.varNameInput1 option:selected').val()
            }
            let uuid = ''
            if(id){
                uuid=parent.$('.ruleContentDiv .actionInfo').eq(i).attr("data-uuid")
            }else{
                uuid=parent.$('.ruleContentDiv .actionInfo').eq(i).attr("data-uuid")+"---"+typeIn
            }
            let inorout = parent.$('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected1').val()
            if(inorout){
                inorout =inorout
            }else{
                inorout =parent.$('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected1 option:selected').val()
            }
            
            if(inorout == "输入"){
                inorout = 0
            }else{
                inorout = 1
            }
            obj = {
                id:id,
                uuid:uuid,
                algorithmid:currId,
                varname:parent.$('.ruleContentDiv .actionInfo').eq(i).attr("data-name"),
                vartype:parent.$('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected2').val(),
                valvalue:parent.$('.ruleContentDiv .actionInfo').eq(i).find('#varTypeInput').val(),
                inorout:inorout,
                remark:parent.$('.ruleContentDiv .actionInfo').eq(i).attr("data-title"),
                parametername:varName
            }
            AddList.push(obj)
        }
    }


    for(let m=0;m<childList.length;m++){
        let delFlag = false
        for(let n=0;n<UPdataList.length;n++){
      
            if(childList[m].uuid == UPdataList[n].uuid){
                delFlag = true
                break;    
            }
            
        }
        if(!delFlag){
            DelList.push(childList[m])
        }
        
    }
    var canvasData
    if(window.canvasNowId == "canvas0"){
        canvasData = canvas.data.nodes
        //修改本地缓存数据
        window.Topology.tools[data.id].children =nowNodesList
    }else{
        canvasData= window.frames[canvasNowId].contentWindow.canvas.data.nodes
        window.frames[canvasNowId].contentWindow.Topology.tools[data.id].children =nowNodesList
    }
    canvasData.map(now=>{
        if(now.id.includes(data.id)){
            nowList.push(now)
        }
    })

    lsList = UPdataList.concat(AddList)
    console.log(DelList,"777777777777")
    DelList.map(item=>{
        let Del1UUid = item.uuid.split('---')[0]
        for(let i = nowList.length - 1;i >=0 ;i--){
            let nowUUid =nowList[i].id.substr((nowList[i].id.indexOf('---')-36),36)
            if(nowUUid ==Del1UUid){
                nowList.splice(i,1);
            }
        }
        canvasData.map((item1,i) => {
            if(item1.childStand){
                let Del2UUid = item1.id.substr((item1.id.indexOf('---')-36),36)
                let in_num = -1 
                let out_num = -1 
                if(Del1UUid == Del2UUid){                       
                    if(item.inorout == 0){
                        canvasData.splice(i,1); 
                        parent.$('.noticeList').append(`<li>${parent.getTime()}【算法参数】删除成功！ </li>`)
                        parent.toastr.success(`【算法参数】删除成功！` )
                        parent.$("#flex_props1_home").scrollTop(parent.$("#flex_props1_home")[0].scrollHeight);
                        if(window.canvasNowId == "canvas0"){
                            window.Topology.dblclickNode.data.inNum --
                        }else{
                            window.frames[canvasNowId].contentWindow.Topology.dblclickNode.data.inNum --
                        }
                       
                        
                        nowList.map((test,R)=>{
                            
                            if(test.childStand){
                                if(test.id.indexOf("IN") !=-1){   
                                in_num ++                                       
                                test.rect.width = 20
                                test.rect.height = 10  
                                test.rect.x = data.rect.x-test.rect.width
                                test.rect.y = data.rect.y + in_num*20 + 10                            
                                test.rect.ex = test.rect.x  + test.rect.width
                                test.rect.ey = test.rect.y + test.rect.height
                                test.rect.center.x = test.rect.x+ test.rect.width/2
                                test.rect.center.y =test.rect.y  + test.rect.height/2
                                test.textRect.x = test.rect.x - 5
                                test.textRect.y =  test.rect.y
                                test.textRect.width = 10
                                test.textRect.height = 5
                                test.paddingTopNum = 0
                                test.paddingTop = 0
                                test.fullIconRect.height = 4
                                test.fullTextRect.x = test.rect.ex - test.textRect.height - 5
                                test.fullTextRect.y =  test.rect.y  -test.fullIconRect.height
                                test.iconRect.x = test.rect.ex - test.textRect.height- 5
                                test.iconRect.y = test.rect.y  -test.fullIconRect.height
                                test.fullIconRect.x =  test.rect.ex - test.textRect.height- 5
                                test.fullIconRect.y = test.rect.y  -test.fullIconRect.height
                                test.textMaxLine = 1
                                test.anchors[0].x = test.rect.x
                                test.anchors[0].y =test.rect.center.y
                                test.anchors[1].x =0
                                test.anchors[1].y = 0   
                                test.anchors[2].x = 0
                                test.anchors[2].y = 0                                                                                           
                                test.anchors[3].x =0
                                test.anchors[3].y = 0
                                test.rotatedAnchors[0].x = test.rect.x
                                test.rotatedAnchors[0].y =test.rect.center.y
                                test.rotatedAnchors[1].x = 0
                                test.rotatedAnchors[1].y =0
                                test.rotatedAnchors[2].x = 0
                                test.rotatedAnchors[2].y = 0
                                test.rotatedAnchors[3].x =0
                                test.rotatedAnchors[3].y =0
                                }
                                
                            }
                        })
                        if(window.canvasNowId == "canvas0"){
                            if( window.Topology.dblclickNode.rect.height > 100){
                            
                                window.Topology.dblclickNode.rect.ey = window.Topology.dblclickNode.rect.ey -15
                                window.Topology.dblclickNode.rect.height = window.Topology.dblclickNode.rect.height - 15
                            }else{
                                window.Topology.dblclickNode.rect.height = 100
                            }
                        }else{
                            window.frames[canvasNowId].contentWindow.Topology.dblclickNode.data.inNum --
                            if( window.frames[canvasNowId].contentWindow.Topology.dblclickNode.rect.height > 100){
                            
                                window.frames[canvasNowId].contentWindow.Topology.dblclickNode.rect.ey = window.frames[canvasNowId].contentWindow.Topology.dblclickNode.rect.ey -15
                                window.frames[canvasNowId].contentWindow.Topology.dblclickNode.rect.height = window.frames[canvasNowId].contentWindow.Topology.dblclickNode.rect.height - 15
                            }else{
                                window.frames[canvasNowId].contentWindow.Topology.dblclickNode.rect.height = 100
                            }
                        }
                                                                        
                    }else{
                        if(window.canvasNowId == "canvas0"){
                            window.Topology.dblclickNode.data.outNum --
                        }else{
                            window.frames[canvasNowId].contentWindow.Topology.dblclickNode.data.outNum --
                        }
                       
                        canvas.data.nodes.splice(i,1); 
                        parent.$('.noticeList').append(`<li>${parent.getTime()}【算法参数】删除成功！ </li>`)
                        parent.toastr.success(`【算法参数】删除成功！` )
                        parent.$("#flex_props1_home").scrollTop(parent.$("#flex_props1_home")[0].scrollHeight);

                        nowList.map((test,R)=>{
                            
                            if(test.childStand){
                                if(test.id.indexOf("OUT") !=-1){
                                out_num ++
                                test.rect.x = data.rect.ex 
                                test.rect.y = data.rect.y + out_num*20 + 10
                                test.rect.width = 20
                                test.rect.height = 10                                
                                test.rect.ex = test.rect.x  + test.rect.width
                                test.rect.ey = test.rect.y + test.rect.height
                                test.rect.center.x = test.rect.x+ test.rect.width/2
                                test.rect.center.y =test.rect.y  + test.rect.height/2
                                test.textRect.x = test.rect.x- 5
                                test.textRect.y =  test.rect.y
                                test.textRect.width = 10
                                test.textRect.height = 5
                                test.paddingTopNum = 0
                                test.paddingTop = 0
                                test.fullIconRect.height = 4
                                test.fullTextRect.x = test.rect.ex - test.textRect.height- 5
                                test.fullTextRect.y =  test.rect.y  -test.fullIconRect.height
                                test.iconRect.x = test.rect.ex - test.textRect.height- 5
                                test.iconRect.y = test.rect.y  -test.fullIconRect.height
                                test.fullIconRect.x =  test.rect.ex - test.textRect.height- 5
                                test.fullIconRect.y = test.rect.y  -test.fullIconRect.height
                                test.textMaxLine = 1
                                test.anchors[0].x = 0
                                test.anchors[0].y =0
                                test.anchors[1].x =0
                                test.anchors[1].y = 0   
                                test.anchors[2].x = test.rect.ex
                                test.anchors[2].y = test.rect.center.y                                                                                            
                                test.anchors[3].x =0
                                test.anchors[3].y = 0
                                test.rotatedAnchors[0].x = 0
                                test.rotatedAnchors[0].y =0

                                test.rotatedAnchors[1].x = 0
                                test.rotatedAnchors[1].y =0

                                test.rotatedAnchors[2].x = test.rect.ex
                                test.rotatedAnchors[2].y = test.rect.center.y

                                test.rotatedAnchors[3].x =0
                                test.rotatedAnchors[3].y =0
                                }
                            }
                            
                        })
                        if(window.canvasNowId == "canvas0"){
                            if( window.Topology.dblclickNode.rect.height > 100){
                            
                                window.Topology.dblclickNode.rect.ey = window.Topology.dblclickNode.rect.ey -15
                                window.Topology.dblclickNode.rect.height = window.Topology.dblclickNode.rect.height - 15
                            }else{
                                window.Topology.dblclickNode.rect.height = 100
                            }
                        }else{
                            window.frames[canvasNowId].contentWindow.Topology.dblclickNode.data.inNum --
                            if( window.frames[canvasNowId].contentWindow.Topology.dblclickNode.rect.height > 100){
                            
                                window.frames[canvasNowId].contentWindow.Topology.dblclickNode.rect.ey = window.frames[canvasNowId].contentWindow.Topology.dblclickNode.rect.ey -15
                                window.frames[canvasNowId].contentWindow.Topology.dblclickNode.rect.height = window.frames[canvasNowId].contentWindow.Topology.dblclickNode.rect.height - 15
                            }else{
                                window.frames[canvasNowId].contentWindow.Topology.dblclickNode.rect.height = 100
                            }
                        }
                    }     
                }
            }
        })
    })
    
    //修改小接口显示的内容
    nowList.map(item =>{
        if(item.childStand){
            let update_UUid = item.id.substr((item.id.indexOf('---')-36),36)
            nowNodesList.map(index=>{
                let index_UUid = index.uuid.substr((index.uuid.indexOf('---')-36),36)
                if(update_UUid == index_UUid){
                    item.text = index.parametername
                    canvas.render();
                }
            })
        }
        
    })
    //修改接口参数，从数据库修改删除
    let ruleType
    if(window.canvasNowId == "canvas0"){
        ruleType =window.bigData.ruleType
    }else{
        ruleType =window.frames[canvasNowId].contentWindow.bigData.ruleType
    }
    if(ruleType== "edit"){
        let interfaceName,roleID,childList
        if(window.canvasNowId == "canvas0"){
            interfaceName =window.Topology.dblclickNode.text
            roleID= window.bigData.editRuleId
            childList = window.Topology.tools[window.Topology.dblclickNode.id]
        }else{
            interfaceName =window.frames[canvasNowId].contentWindow.Topology.dblclickNode.text
            roleID= window.frames[canvasNowId].contentWindow.bigData.editRuleId
            childList = window.frames[canvasNowId].contentWindow.Topology.tools[window.frames[canvasNowId].contentWindow.Topology.dblclickNode.id]
        }

        let operatorInterfaceDataModel ={
            algorithmID:data.data.sid,
            id:data.id ,
            interfaceName:interfaceName,
            roleID:roleID,
            tableInterfaceparametersList:[]
        }

        childList.children.map(index=>{
            let uuid
            if(index.uuid.indexOf('---') !=-1){
                uuid = index.uuid.substr((index.uuid.indexOf('---')-36),36)
            }else{
                uuid = index.uuid
            }
            let CsObj = {
                id:uuid,
                inorout:index.inorout,
                interfaceid:data.id,
                parametersname:index.varname,
                parameterssources:index.id
            }
            operatorInterfaceDataModel.tableInterfaceparametersList.push(CsObj)
        })
        console.log(operatorInterfaceDataModel,"99999999999")
        $.ajax({
            type:"post",
            dataType: "json",
            url:urlConfig.host+'/algorithmRule/modInterfaceRole',
            contentType: "application/json;charset=UTF-8",
            data:JSON.stringify(operatorInterfaceDataModel),
            success: function(data) {
                parent.$('.noticeList').append(`<li>${parent.getTime()}【算法参数】修改成功！ </li>`)
                parent.toastr.success(`【算法参数】修改成功！` )
                parent.$("#flex_props1_home").scrollTop(parent.$("#flex_props1_home")[0].scrollHeight);
            }
        })
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
    $('#ruleAct').fadeToggle(500)
}

//加
function ruleAddButtonS(){
    let data;
    if(window.canvasNowId == "canvas0"){
         data = JSON.parse(JSON.stringify(window.Topology.dblclickNode))
    }else{
        cwin = window.frames[canvasNowId].contentWindow;
        data = JSON.parse(JSON.stringify(cwin.Topology.dblclickNode))
    }
    $.ajax({
        url:urlConfig.host+'/operatorMaintenance/getAlgorithmById',
        data:{algthId:data.data.sid},
        success: function(data) {
            let str =`<div class="actionInfo" data-title="xin">
                    <input value="输出" class="actionSelected1" disabled>  
                    <input value="" class="varNameInput" style="display: none;">   
                    <select class="varNameInput1">
                    </select>
                    <input value="" class="actionSelected2" disabled style="margin-left: 6px;">   
                    <input value="" id="varTypeInput" disabled style="margin-left: 6px;">                                                 
                    <button type="button" onclick="reduceButton(event)">x</button> 
                    </div>` 
                    $('.ruleContentDiv').append(str);
                    let actionInfoNum = $('.ruleContentDiv .actionInfo').length-1
                    let lstr1=`<option>请选择</option>`
                    data.tableFuncs.map(item => {
                       lstr1 += `<option>${item.parametername}</option>`
                    })
                    parent.$('.ruleContentDiv .actionInfo').eq(actionInfoNum).find(".varNameInput1").html(lstr1)
                  $('body').off("change").on('change','.varNameInput1',(e) => {
                    data.tableFuncs.map(item => {
                       if($(e.target).val()== item.parametername){
                            if(item.vartype == "1"){
                                $(e.target).parent().children('.actionSelected2').val("基本类型")
                                $(e.target).parent().children('#varTypeInput').val(item.valvalue)
                                $(e.target).parent().children('.varNameInput').val($(e.target).val())
                                $(e.target).parent().attr("Funcs-id",item.id)
                                $(e.target).parent().attr("data-name",item.varname)
                            }
                            
                            if(item.vartype == "2"){
                                $(e.target).parent().children('.actionSelected2').val("常量")
                                $(e.target).parent().children('#varTypeInput').val(item.valvalue)
                                $(e.target).parent().children('.varNameInput').val($(e.target).val())
                                $(e.target).parent().attr("Funcs-id",item.id)
                                $(e.target).parent().attr("data-name",item.varname)
                            }
                            if(item.vartype == "3"){
                                $(e.target).parent().children('.actionSelected2').val("对象")
                                $(e.target).parent().children('#varTypeInput').val(item.valvalue)
                                $(e.target).parent().children('.varNameInput').val($(e.target).val())
                                $(e.target).parent().attr("Funcs-id",item.id)
                                $(e.target).parent().attr("data-name",item.varname)
                            }
                            if(item.vartype == ""){
                                $(e.target).parent().children('.actionSelected2').val("")
                                $(e.target).parent().children('#varTypeInput').val(item.valvalue)
                                $(e.target).parent().children('.varNameInput').val($(e.target).val())
                                $(e.target).parent().attr("Funcs-id",item.id)
                                $(e.target).parent().attr("data-name",item.varname)
                            }             
                       }else if($(e.target).val()== "请选择"){
                            $(e.target).parent().children('.actionSelected2').val("")
                            $(e.target).parent().children('#varTypeInput').val("")
                            $(e.target).parent().children('.varNameInput').val("")
                            $(e.target).parent().attr("")
                            $(e.target).parent().attr("")
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