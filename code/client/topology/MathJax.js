
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
    let algorithmRuleDataList = []
    let spanId = $('#topo_canvas div span')
    let coordinate = []
    console.log(window.Topology)
    if(spanId.length > 0){
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
    let node =window.Topology.saveNode
    if(node.length ==0){
        alert('请建立规则')
        return false;
    }else{     
        node.map(item=>{
            let obj ={
                id:(item.id).substring(0,(item.id).length-2),
                x:item.rect.x,
                y:item.rect.y,
                width:item.rect.width,
                height:item.rect.height,
                ex:item.rect.ex,
                ey:item.rect.ey,
            }
            coordinate.push(obj)
        })
    }
    
    console.log(coordinate)
     console.log(algorithmRuleDataList)
     if($("#ruleName").val() == ''){
         toastr.info('请输入规则名称')
         return false;
     }
    let algorithmRuleSaveDataModel ={
        algorithmRuleDataModelList:algorithmRuleDataList,
        coordinateinfo:JSON.stringify(coordinate),
        tableRole:{
            des:$("#ruleDes").val(),
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
function ActionSure(event){
    // let obj ={
    //     action:$('.actionSelected1').val(),
    //     actionValue:$('.actionSelected2').val()
    // }

    let data = JSON.parse(JSON.stringify(window.Topology.dblclickNode))
    let test = JSON.parse(JSON.stringify(window.Topology.dblclickNode)),num = {}
                        
    let widths = data.rect.width/10
    let heights = data.rect.height/10
    console.log(data.data,'444444444444',widths,heights) 

    console.log(num)
    if($('.actionSelected1').val() == 0){
        if(!data.data.inNum){
            data.data.inNum = 1
            num = {
                x:-widths,
                y:heights+5
            }
        }else{
            num = {
                x:-widths,
                y:(heights*data.data.inNum)+5*data.data.inNum
            }
        }
        window.bigData.isAddInOutType = "in";
        test.id = data.id +"IN"+ data.data.inNum + "---" + $("#varTypeInput").val();
        test.text = "in"+ data.data.inNum;
   }else{
    if(!data.data.outNum){
        data.data.outNum = 1
        num = {
            x:data.rect.width,
            y:heights+5
        }
    }else{

        num = {
            x:data.rect.width,
            y:(heights*data.data.outNum)+5*data.data.outNum
        }
    }
       window.bigData.isAddInOutType = "out"
       test.id = data.id +"OUT"+data.data.outNum + "---" + $("#varTypeInput").val();
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
    test.textRect.x = 0
    test.textRect.y = 0
    test.textRect.width = 0
    test.textRect.height = 0
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
    if($('.actionSelected1').val() == 0){
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
            obj.x = data.rotatedAnchors[i].x+218 + num.x
            obj.y = data.rotatedAnchors[i].y-115 + num.y
        })
    }
    test.text = $('.actionSelected2 option:selected').text();


    
    // canvas.render();

    window.bigData.isAddInOut = true;

    let flag = canvas.addNode(test)
    canvas.lockNodes([test], true)
    if(flag){
        data.data++
    }else{
        window.bigData.isAddInOut = false
    }

    $('#ruleAct').fadeToggle(500)

    canvas.render();
    // canvas.updateProps(canvas.data.nodes)

//     $('#ruleAct').fadeToggle(500)
//     let num = 40
//     let node = window.Topology.dblclickNode.node

//     let countIn = 0;
//     let countOut = 0;
//     let data = {}
//     let inOut =""
//     let textValue = ''
    
//     if(node.children.length > 0){
//          data =  JSON.parse(JSON.stringify(window.Topology.dblclickNode.node.children[1])) 
//          data.parentId = window.Topology.dblclickNode.node.children[0].id
//          if($('.actionSelected1').val() == 0){
//             console.log(data,'454545')

            
//             inOut = data.id+"in" + $('.actionSelected2').eq(0).val();
//             textValue = "in" + $('.actionSelected2').eq(0).val();
//             canvas.data.nodes.map((s,i)=>{
//                 if(s.id.includes(window.Topology.dblclickNode.node.id)){
//                     countIn++; 
//                 }
//             })
//             data.rect.x = data.rect.x;
//             cLength = window.Topology.dblclickNode.node.children.length
//             data.rect.y = data.rect.y + 20*(cLength -1);
           
//         }
//         if($('.actionSelected1').val() == 1){
//             inOut = data.id+"out" + $('.actionSelected2').eq(0).val();
//             textValue = "out" + $('.actionSelected2').eq(0).val();
//             canvas.data.nodes.map((s,i)=>{
//                 if(s.id.includes(window.Topology.dblclickNode.node.id)){
//                     countOut++; 
//                 }
//             })
//             data.rect.x = data.rect.x +200;
//             data.rect.y = data.rect.y+ 20*countOut;
        
//         }
//     }else{
//          data = JSON.parse(JSON.stringify(window.Topology.dblclickNode.node)) 
//          data.parentId = window.Topology.dblclickNode.node.id
//          if($('.actionSelected1').val() == 0){
//             inOut = data.id+"in" + $('.actionSelected2').eq(0).val();
//             textValue = "in" + $('.actionSelected2').eq(0).val();
//             canvas.data.nodes.map((s,i)=>{
//                 if(s.id.includes(window.Topology.dblclickNode.node.id)){
//                     countIn++; 
//                 }
//             })
//             data.rect.x = data.rect.x -15;
//             cLength = window.Topology.dblclickNode.node.children.length
//             data.rect.y = data.rect.y + 20*(cLength -1);
           
//         }
//         if($('.actionSelected1').val() == 1){
//             inOut = data.id+"out" + $('.actionSelected2').eq(0).val();
//             textValue = "out" + $('.actionSelected2').eq(0).val();
//             canvas.data.nodes.map((s,i)=>{
//                 if(s.id.includes(window.Topology.dblclickNode.node.id)){
//                     countOut++; 
//                 }
//             })
//             data.rect.x = data.rect.x +230;
//             data.rect.y = data.rect.y+ 20*countOut;
        
//         }
//     }
    
   

//     // debugger
    
   
//     data.rect.width = 15
//     data.rect.height = 15
//     data.text =textValue
//     data.rect.ex = data.rect.ex + num
//     data.rect.ey = data.rect.ey + num
//     data.rect.center.x = data.rect.center.x + num
//     data.rect.center.y = data.rect.center.y
//     data.textRect.x =data.textRect.x + num
//     data.textRect.y = data.textRect.y
//     data.fullTextRect.x = data.fullTextRect.x + num
//     data.fullTextRect.y = data.fullTextRect.y
//     data.iconRect.x = data.iconRect.x + num
//     data.iconRect.y = data.iconRect.y
//     data.fullIconRect.x = data.fullIconRect.x + num
//     data.fullIconRect.y = data.fullIconRect.y
//     data.FGIID = data.id
//     data.id = inOut
//     data.rectInParent = {
//        x:0,
//        y:0,
//        width: 15,
//        height:15
//    }
//     console.log("-----------------------------------------------------",data.rect)
//     // canvas.parse();
//     // window.Topology.dblclickNode.node.children[0].setChild([data])
  
//     canvas.render();
//     console.log(data)
//     canvas.addNode(data)
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