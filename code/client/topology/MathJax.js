
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
    var formData = new FormData();
    formData.append("file",$(".inputfile")[0].files[0]);
    $.ajax({
        url:urlConfig.host+'/algorithmRule/readAlgorithmRuleFromFile',
        type:"POST",
        data: formData,
        cache: false,
　　　　processData: false, 
　　　　contentType: false,   
        success: function(data) {
            if(data == true){

            }
        }
    })
}

//选择关系确定按钮
function selectSure(){
    let str = $('.selectRelation option:selected').text();
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
     //动作
    editGzType = false;
    $("#currentGzName").text($("#ruleName").val());
    $("#currentGzDes").text($("#ruleRemark").val());
    let algorithmRuleDataList = [] 
    window.globalActionDatas.map(item=>{
       let obj ={
            des:'',
            id:0,
            interfaceID: item.dataIn.interfaceRoleDataModels.interfaceID,
            parametersID:item.dataIn.interfaceRoleDataModels.parametersID,
            preInterfaceID:item.dataIn.interfaceRoleDataModels.preInterfaceID,
            preParametersID:item.dataIn.interfaceRoleDataModels.preParametersID,
            remark:"",
            roleid:0,
            algorithmconditions:[]

       }
       obj.algorithmconditions =  item.dataIn.interfaceRoleDataModels.algorithmconditions.concat(item.dataOut.interfaceRoleDataModels.algorithmconditions)
      algorithmRuleDataList.push(obj)
   })
    //参数借口
    let operatorInterfaceDataModels = [] 
    window.Topology.tools.map(item=>{
        // let bigList = []
        let objF = {
            algorithmID:item.id.slice(0,item.id.indexOf("tableAlgorithm")),
            id:window.idStoreData[item.id] ,
            interfaceName:item.name,
            roleID:0,
            tableInterfaceparametersList:[]
        }
       operatorInterfaceDataModels.push(objF)
        
        item.children.map(index=>{
            let id =""
            if(index.uuid.indexOf("---") == -1){
                id= index.uuid
            }else{
                index.uuid.slice(0,index.uuid.indexOf("---"))
            }
            let CsObj = {
                id:id,
                inorout:index.inorout,
                interfaceid:window.idStoreData[index.algorithmid+"tableAlgorithm"],
                parametersname:index.varname,
                parameterssources:index.id
            }
            console.log(CsObj,"333333333333333333333333333333")
            objF.tableInterfaceparametersList.push(CsObj)
        })
        // operatorInterfaceDataModels.push(objF)
        // operatorInterfaceDataModels.push(bigList)
    })
    //规则本身信息
    let tableRole={   
        coordinate:JSON.stringify(canvas.data),
        des:$("#ruleRemark").val(),
        entrancenote:$("#ruleDes").attr("data")?$("#ruleDes").attr("data"):"",
        id:0,
        remark:'',
        rolename:$("#ruleName").val(),
        uuserid:0
    }
    let algorithmRuleSaveDataModel ={
        interfaceRoleDataModels :algorithmRuleDataList,
        operatorInterfaceDataModels:operatorInterfaceDataModels,
        tableRole:tableRole
      }
      console.log(algorithmRuleSaveDataModel)
    if(window.bigData.ruleType == "edit"){
        tableRole.id = window.bigData.editRuleId
        $.ajax({
            type:"post",
            dataType: "json",
            url:urlConfig.host+'/algorithmRule/modAlgorithmRuleBase',
            contentType: "application/json;charset=UTF-8",
            data:JSON.stringify(tableRole),
            success: function(data) {
                $("#sureRule").fadeToggle(500)
                toastr.success('修改成功！');
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
                $("#sureRule").fadeToggle(500)
                toastr.success('保存成功！');
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
//删除算子
function ConfirmDelAlgorithm(){
    $.ajax({
        url:urlConfig.host+'/operatorMaintenance/delAlgorithmById',
        data:{algthId:window.bigData.delAlgorithmId},
        success: function(data) {
            if(data.status == 1){
                window.bigData.delAlgorithmId = ''
                $('#lkrAlgorithm').fadeToggle(500)
                window.getAllData('/operatorMaintenance/getAllAlgorithm',{id:'id',Tname:'tableAlgorithm',name:'algorithmname'},'tableAlgorithm',{username:null})
            }
            if(data.status == 2){
                toastr.info(data.msg);
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
    debugger
    let test = JSON.parse(JSON.stringify(window.Topology.dblclickNode)),num = {}
    let actionInfoNum = $('.ruleContentDiv .actionInfo')
   
    let saveList ={
        id :data.id,
        name:data.text,
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
                let varName =  $('.ruleContentDiv .actionInfo').eq(i).find('.varNameInput1 option:selected').val()
                if(varName =="请选择"){
                    toastr.success('请选择变量名称！');
                    return false;
                }
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
                    data.data.outNum ++
                    num = {
                        x:data.rect.width,
                        y:(heights*data.data.outNum)+10*(data.data.outNum-1)
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
                test.text = $('.ruleContentDiv .actionInfo').eq(i).find('.varNameInput').val();
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
                        }
                    }else{
                        if(data.data.outNum > data.data.inNum){
                            if( window.Topology.dblclickNode.rect.height < (heights*(data.data.outNum+1) +10*(data.data.outNum+1))){
                                window.Topology.dblclickNode.rect.ey = window.Topology.dblclickNode.rect.ey + heights+15
                                window.Topology.dblclickNode.rect.height = window.Topology.dblclickNode.rect.height + heights+15
                            }
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
        let typeIn = $('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected2').val();
        if(typeIn== "基本类型"){
            typeIn =$('.ruleContentDiv .actionInfo').eq(i).find('#varTypeInput').val();
        }
        let varName = $('.ruleContentDiv .actionInfo').eq(i).find('.varNameInput').val()
        if(varName){
            varName = varName
        }else{
            varName =  $('.ruleContentDiv .actionInfo').eq(i).find('.varNameInput1 option:selected').val()
        }
        let uuid = $('.ruleContentDiv .actionInfo').eq(i).attr("data-uuid")
        if(uuid.indexOf('---') == -1){
            uuid=$('.ruleContentDiv .actionInfo').eq(i).attr("data-uuid")+"---"+typeIn
        }else{
           
        }
        let inorout = $('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected1').val()
        if(inorout){
            inorout =inorout
        }else{
            inorout =$('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected1 option:selected').val()
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
           varname:varName,
           vartype:$('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected2').val(),
           valvalue:$('.ruleContentDiv .actionInfo').eq(i).find('#varTypeInput').val(),
           inorout:inorout,
           remark:$('.ruleContentDiv .actionInfo').eq(i).attr("data-title")
       }
       lsList.push(obj)
       console.log(obj,"1111111111111111111111111111")
   }
   let UPdataList = []
   let AddList = []
   let DelList = []
    window.Topology.tools.map(isCZdata=>{
        if(isCZdata.id == data.id){
            isFlag =true
            for(let i =0;i< actionInfoNum.length ;i++){
                let UPFlag = false
                for(let j=0; j<isCZdata.children.length;j++){
                    let uuID  =''
                    if(isCZdata.children[j].uuid.indexOf('---') == -1){
                        uuID = isCZdata.children[j].uuid
                    }else{
                        let sy = isCZdata.children[j].uuid.indexOf('---')
                        uuID = isCZdata.children[j].uuid.slice(0,sy)
                    }                     
                    let clyId = $('.ruleContentDiv .actionInfo').eq(i).attr("data-uuid")
                        clyId = clyId.split('---')[0]
                    if(uuID == clyId){
                        UPdataList.push(isCZdata.children[j])
                        UPFlag = true
                        break;
                    }
                }
                if(!UPFlag){
                    let id = $('.ruleContentDiv .actionInfo').eq(i).attr("Funcs-id")
                    if(id){
                        id =id
                    }else{
                        id =""
                    }
                    let typeIn = $('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected2').val();
                    if(typeIn== "基本类型"){
                        typeIn =$('.ruleContentDiv .actionInfo').eq(i).find('#varTypeInput').val();
                    }
                    let varName = $('.ruleContentDiv .actionInfo').eq(i).find('.varNameInput').val()
                    if(varName){
                        varName = varName
                    }else{
                        varName =  $('.ruleContentDiv .actionInfo').eq(i).find('.varNameInput1 option:selected').val()
                    }
                    let uuid = ''
                    if(id){
                        uuid=$('.ruleContentDiv .actionInfo').eq(i).attr("data-uuid")
                    }else{
                        uuid=$('.ruleContentDiv .actionInfo').eq(i).attr("data-uuid")+"---"+typeIn
                    }
                    let inorout = $('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected1').val()
                    if(inorout){
                        inorout =inorout
                    }else{
                        inorout =$('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected1 option:selected').val()
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
                        varname:varName,
                        vartype:$('.ruleContentDiv .actionInfo').eq(i).find('.actionSelected2').val(),
                        valvalue:$('.ruleContentDiv .actionInfo').eq(i).find('#varTypeInput').val(),
                        inorout:inorout,
                        remark:$('.ruleContentDiv .actionInfo').eq(i).attr("data-title")
                    }
                    AddList.push(obj)
                }
            }


            for(let m=0; m<isCZdata.children.length;m++){
                let delFlag = false
                for(let n=0;n<UPdataList.length;n++){
                    if(isCZdata.children[m].uuid == UPdataList[n].uuid){
                        delFlag = true
                        break;    
                    }
                }
                if(!delFlag){
                    DelList.push(isCZdata.children[m])
                }
            }
            lsList = UPdataList.concat(AddList)
            isCZdata.children = lsList
            console.log(DelList)
            if(DelList.length > 0){
                DelList.map(item=>{
                    let Del1UUid = item.uuid.split('---')[0]
                    canvas.data.nodes.map((item1,i) => {
                        if(item1.childStand){
                            let Del2UUid = item1.id.substr((item1.id.indexOf('---')-36),36)
                            if(Del1UUid == Del2UUid){                             
                                if( window.bigData.ruleType == "edit"){
                                    if(window.bigData.editRuleId){
                                        $.ajax({
                                            type:"get",
                                            dataType: "json",
                                            url:urlConfig.host+'/algorithmRule/delTableOperatorinterface',
                                            contentType: "application/json;charset=UTF-8",
                                            data:{
                                                operatorinterfaceId:Del1UUid
                                            },
                                            success: function(data) {
                                                if(item.inorout == 0){
                                                    window.Topology.dblclickNode.data.inNum --   
                                                    if( window.Topology.dblclickNode.rect.height > 100){
                                                        window.Topology.dblclickNode.rect.ey = window.Topology.dblclickNode.rect.ey - 15
                                                        window.Topology.dblclickNode.rect.height = window.Topology.dblclickNode.rect.height - 15
                                                    }                                                   
                                                }else{
                                                    window.Topology.dblclickNode.data.outNum --
                                                    if( window.Topology.dblclickNode.rect.height > 100){
                                                        window.Topology.dblclickNode.rect.ey = window.Topology.dblclickNode.rect.ey - 15
                                                        window.Topology.dblclickNode.rect.height = window.Topology.dblclickNode.rect.height - 15
                                                    }
                                                }
                                                canvas.data.nodes.splice(i,1);  
                                                if(data == true){
                                                    toastr.success('删除成功！');
                                                }
                                            }
                                        })
                                    }
                                }else{
                                    if(item.inorout == 0){
                                        window.Topology.dblclickNode.data.inNum --     
                                        if( window.Topology.dblclickNode.rect.height > 100){
                                           
                                            window.Topology.dblclickNode.rect.ey = window.Topology.dblclickNode.rect.ey -15
                                            window.Topology.dblclickNode.rect.height = window.Topology.dblclickNode.rect.height - 15
                                        }                                                 
                                    }else{
                                        window.Topology.dblclickNode.data.outNum --
                                        canvas.data.nodes.splice(i,1); 
                                        toastr.success('删除成功！');

                                        canvas.data.nodes.map((test,k)=>{
                                            if(test.childStand){
                                               if(test.id.indexOf("OUT") !=-1){
                                                test.rect.x = data.rect.ex 
                                                test.rect.y = data.rect.y + k*10 + 10
                                                test.rect.width = 20
                                                test.rect.height = 10
                                
                                                test.rect.ex = test.rect.x  + test.rect.width
                                                test.rect.ey = test.rect.y + test.rect.height
                                                test.rect.center.x = test.rect.x+ test.rect.height
                                                test.rect.center.y =test.rect.y  + test.rect.height/2
                                                console.log( test.rect.x,test.rect.y,k)
                                               }
                                                
                                            }
                                        })
                                        if( window.Topology.dblclickNode.rect.height > 100){
                                            
                                            window.Topology.dblclickNode.rect.ey = window.Topology.dblclickNode.rect.ey - 15
                                            window.Topology.dblclickNode.rect.height = window.Topology.dblclickNode.rect.height -15
                                        }else{
                                            window.Topology.dblclickNode.rect.height = 100
                                        }
                                    }
                                    
                                }       
                            }
                        }
                    })
                })
            }
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
            let str =`<div class="actionInfo" data-title="xin">
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
                    data.tableFuncs.map(item => {
                       if($(e.target).val()== item.varname){
                            if(item.vartype == "1"){
                                $(e.target).parent().children('.actionSelected2').val("基本类型")
                                $(e.target).parent().children('#varTypeInput').val(item.valvalue)
                                $(e.target).parent().children('.varNameInput').val($(e.target).val())
                                $(e.target).parent().attr("Funcs-id",item.id)
                            }
                            
                            if(item.vartype == "2"){
                                $(e.target).parent().children('.actionSelected2').val("常量")
                                $(e.target).parent().children('#varTypeInput').val(item.valvalue)
                                $(e.target).parent().children('.varNameInput').val($(e.target).val())
                                $(e.target).parent().attr("Funcs-id",item.id)
                            }
                            if(item.vartype == "3"){
                                $(e.target).parent().children('.actionSelected2').val("对象")
                                $(e.target).parent().children('#varTypeInput').val(item.valvalue)
                                $(e.target).parent().children('.varNameInput').val($(e.target).val())
                                $(e.target).parent().attr("Funcs-id",item.id)
                            }                
                       }else if($(e.target).val()== "请选择"){
                            $(e.target).parent().children('.actionSelected2').val("")
                            $(e.target).parent().children('#varTypeInput').val("")
                            $(e.target).parent().children('.varNameInput').val("")
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