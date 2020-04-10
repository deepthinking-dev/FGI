
$(function(){
    $("#selectAll").click((e) => {
        $("input:checkbox[name='item']").each((i,v) => {
            $(v).prop('checked',$("#selectAll").prop('checked'))
        })
    })
    $('body').on('click','.dicDivClose',(e) => {
        $("#dicDiv").hide();
    })
    $('body').on('click','.editDicClose',(e) => {
        $("#editDic").hide();
        // $("#dicDiv").show();
        // dictionary()
    })
    $("#selectOutIn").change(()=> {
        $("#actionInDiv").empty();
        $("#actionOutDiv").empty();
        if ($("#selectOutIn").val() == "1") {
            $("#actionInDiv").show();
            $("#actionOutDiv").hide();
            globalActionDatas.map(s=>{
                if(s.id == $("#addActionButton").attr("out_small") + "AND" +$("#addActionButton").attr("in_small")){
                    try{
                        var lineDatas = s.dataIn.interfaceRoleDataModels[0].algorithmconditions;
                        lineDatas.map(t=>{
                            $("#actionInDiv").append(`
                              <div style="margin: 10px 0">
                                   <span>行为值来源</span><select class="xwzly_in" disabled></select>
                                   <span>行为</span><select class="xwSelect_in">
                                   <option value=">">></option>
                                   <option value="<"><</option>
                                   <option value="=">=</option>
                                   <option value=">=">>=</option>
                                   <option value="<="><=</option>
                                   <option value="!=">!=</option>
                                   <option value="assignment">赋值</option>
                               </select>
                                   <span>表达式</span><input type="text" value=${t.expression} class="bds_in">
                                   <button class="deleteActionData" type="button"  style="background: #f56c6c;color: #fff;margin-left: 20px;height: 20px;border: none">X</button>
                              </div>
                            `)
                        })
                        lineDatas.map((t,i)=>{
                            $('#actionInDiv .xwSelect_in').eq(i).val(t.behavior)
                        })
                    }catch (e) {

                    }
                }
            })
        } else {
            $("#actionInDiv").hide();
            $("#actionOutDiv").show();
            $.ajax({
                url:urlConfig.host+'/operatorMaintenance/getAlgorithmById',
                data:{algthId:$("#addActionButton").attr("id_out")},
                success(res) {
                    let optionx = "";
                    res.tableFuncs.map(s=>{
                        optionx += `<option value=${s.id}>${s.varname}</option>`
                    })
                    globalActionDatas.map(s=>{
                        if(s.id == $("#addActionButton").attr("out_small") + "AND" +$("#addActionButton").attr("in_small")){
                            try {
                                var lineDatas = s.dataOut.interfaceRoleDataModels[0].algorithmconditions;
                                lineDatas.map(t=>{
                                    $("#actionOutDiv").append(`
                                      <div style="margin: 10px 0">
                                           <span>行为值来源</span><select class="xwzly_out">${optionx}</select>
                                           <span>行为</span><select class="xwSelect_out">
                                           <option value=">">></option>
                                           <option value="<"><</option>
                                           <option value="=">=</option>
                                           <option value=">=">>=</option>
                                           <option value="<="><=</option>
                                           <option value="!=">!=</option>
                                           <option value="assignment">赋值</option>
                                       </select>
                                           <span>表达式</span><input type="text" value=${t.expression} class="bds_out">
                                           <button class="deleteActionData" type="button"  style="background: #f56c6c;color: #fff;margin-left: 20px;height: 20px;border: none">X</button>
                                      </div>
                                `)
                                })
                                lineDatas.map((t,i)=>{
                                    $('#actionOutDiv .xwzly_out').eq(i).val(t.valuesources);
                                    $('#actionOutDiv .xwSelect_out').eq(i).val(t.behavior);
                                })
                            }catch (e) {

                            }
                        }
                    })
                }
            })
        }
    })
    $('body').on('click','.deleteActionData',(e) => {
        $(e.target).parent().remove()
    })
    $('body').on('click','#addActionButton',(e) => {
        if($("#selectOutIn").val() == "1"){
                $("#actionInDiv").append(`
                      <div style="margin: 10px 0">
                           <span>行为值来源</span><select class="xwzly_in" disabled></select>
                           <span>行为</span><select class="xwSelect_in">
                           <option value=">">></option>
                           <option value="<"><</option>
                           <option value="=">=</option>
                           <option value=">=">>=</option>
                           <option value="<="><=</option>
                           <option value="!=">!=</option>
                           <option value="assignment">赋值</option>
                       </select>
                           <span>表达式</span><input type="text" value="" class="bds_in">
                           <button class="deleteActionData" type="button"  style="background: #f56c6c;color: #fff;margin-left: 20px;height: 20px;border: none">X</button>
                      </div>
                    `)
        } else {
            $.ajax({
                url:urlConfig.host+'/operatorMaintenance/getAlgorithmById',
                data:{algthId:$("#addActionButton").attr("id_out")},
                success(res) {
                    let optionx = "";
                    res.tableFuncs.map(s=>{
                        optionx += `<option value=${s.id} type=${s.vartype} valvalue=${s.valvalue}>${s.varname}</option>`
                    })
                    $("#actionOutDiv").append(`
                          <div style="margin: 10px 0">
                               <span>行为值来源</span><select class="xwzly_out">${optionx}</select>
                               <span>行为</span><select class="xwSelect_out">
                               <option value=">">></option>
                               <option value="<"><</option>
                               <option value="=">=</option>
                               <option value=">=">>=</option>
                               <option value="<="><=</option>
                               <option value="!=">!=</option>
                               <option value="assignment">赋值</option>
                           </select>
                               <span>表达式</span><input type="text" value="" class="bds_out">
                               <button class="deleteActionData" type="button"  style="background: #f56c6c;color: #fff;margin-left: 20px;height: 20px;border: none">X</button>
                          </div>
                        `)
                }
            })
        }
    })
    $('body').on('dblclick','.bds_out',(e) => {
        if($(e.target).parent().children('.xwzly_out').find("option:selected").attr('type') == "3" && $(e.target).parent().children('.xwSelect_out').val() == "assignment"){//对象&&赋值
            $.ajax({
                url: urlConfig.host + '/module/findTableModuleByName',
                data: {name : $(e.target).parent().children('.xwzly_out').find("option:selected").attr('valvalue')},
                success(res) {
                    console.log(res);
                }
            })
        }
    })
    $('body').on('click','#addAction',(e) => {
        $("#actionDiv").hide();
        $("#actionOutDiv").hide()
        if($("#selectOutIn").val() == "1"){//输入
            var dataArr = [];
            $('#actionInDiv div').each(function () {
                let obj = {
                    "behavior": $(this).find(".xwSelect_in").val(),
                    "expression": $(this).find(".bds_in").val(),
                    "id": 0,
                    "interfaceparametersid":$("#addActionButton").attr("in_small"),
                    "interfaceroleid": 0,
                    "remark": "",
                    "valuesources": 0
                };
                dataArr.push(obj)
            })
           var data = {
                "interfaceRoleDataModels": [
                    {
                        "algorithmconditions": dataArr,
                        "des": "",
                        "id": 0,
                        "interfaceID": $("#addActionButton").attr("in_big"),
                        "parametersID": $("#addActionButton").attr("in_small"),
                        "preInterfaceID": $("#addActionButton").attr("out_big"),
                        "preParametersID": $("#addActionButton").attr("out_small"),
                        "remark": "",
                        "roleid": 0,
                    }
                ],
            }
            var flag = true;
            globalActionDatas.map(s=>{
                if(s.id == $("#addActionButton").attr("out_small") + "AND" + $("#addActionButton").attr("in_small")){
                    s.dataIn = data;
                    flag = false
                }
            })
            if(flag){
                globalActionDatas.push({
                    id:$("#addActionButton").attr("out_small") + "AND" + $("#addActionButton").attr("in_small"),
                    dataIn:data,
                    dataOut:""
                })
            }
        } else {
            var arrOut = [];
            $('#actionOutDiv div').each(function () {
                let obj = {
                    "behavior": $(this).find(".xwSelect_out").val(),
                    "expression": $(this).find(".bds_out").val(),
                    "id": 0,
                    "interfaceparametersid":$("#addActionButton").attr("out_small"),
                    "interfaceroleid": 0,
                    "remark": "",
                    "valuesources": $(this).find(".xwzly_out").val()
                };
                arrOut.push(obj)
            })
            var dataOut = {
                "interfaceRoleDataModels": [
                    {
                        "algorithmconditions": arrOut,
                        "des": "",
                        "id": 0,
                        "interfaceID": $("#addActionButton").attr("in_big"),
                        "parametersID": $("#addActionButton").attr("in_small"),
                        "preInterfaceID": $("#addActionButton").attr("out_big"),
                        "preParametersID": $("#addActionButton").attr("out_small"),
                        "remark": "",
                        "roleid": 0,
                    }
                ],
            }
            var flag = true;
            globalActionDatas.map(s=>{
                if(s.id == $("#addActionButton").attr("out_small") + "AND" + $("#addActionButton").attr("in_small")){
                    s.dataOut = dataOut;
                    flag = false
                }
            })
            if(flag){
                globalActionDatas.push({
                    id:$("#addActionButton").attr("out_small") + "AND" + $("#addActionButton").attr("in_small"),
                    dataIn:"",
                    dataOut:dataOut
                })
            }
        }
    })
    $('body').on('click','.addDicClose',(e) => {
        $("#editDic").hide();
        $("#dicDiv").show();
        dictionary()
    })
    $('body').on('click','#dicYes',(e) => {
        $("#dicDiv").hide();
    })
    $('body').on('click','#addDic',(e) => {
        $("#editDic").show();
        $("#editDicYes").show();
        $("#editDicTitle").text("新增字典");
        $("#zdcsList").html(``)
        $("#editDicYes").attr("editId","")
        $("#editDicName").val("");
        $("#editDicDes").val("");
        $("#editAuthor").val("");
        $("#dicDiv").hide();
        $("#addZdcs").show();
        $(".editDicClose").attr("class","addDicClose");
        $("#editAuthor").attr("disabled",false);
        $("#editDicName").attr("disabled",false);
        $("#editDicDes").attr("disabled",false);
    })
    $('body').on('click','.dicEdit',(e) => {
        $("#editDicTitle").text("修改字典")
    })
    $('body').on('click','#editDicYes',(e) => {
        let name = $("#editDicName").val();
        var flag = true;
        if(name == ""){
            toastr.info('请填写算法名称！')
            return false;
        }
        let des =  $("#editDicDes").val();
        let dataAll = {
            "tableAlgorithm": {
                "algorithmauthor": $("#editAuthor").val(),
                "algorithmfun": "",
                "algorithmname": name,
                "algorithmtype": 1,
                "des": des,
                "id": 0,
                "ispublic": 0,
                "moduleid":0,
                "remark": ""
            },
            "tableFuncs": [

            ],
            "tableModuleuserrelation": {
                "id": 0,
                "moduleid": 0,
                "remark": "string",
                "username": "string"
            }
        }
        if($(".gsDiv").css("display") == "block"){
            dataAll.tableAlgorithm.algorithmtype = 2
        }
        var tables = [];
        $(".zdcsDiv").each((i,s)=>{
            let obj =   {
                "algorithmid": 0,
                "id": 0,
                "remark": ""
            };
            obj.varname = $(s).find('.zdcsCsmc').val() //参数名称
            if(obj.varname == ""){
                flag = false;
                toastr.info('请填写参数名称！')
            }
            obj.inorout = $(s).find('.zdcsExport').val()//输入输出
            if(obj.inorout == ""){
                flag = false;
                toastr.info('请填写输入输出！')
            }
            obj.vartype = $(s).find('.zdcsSelect').val()//变量类型
            if(obj.vartype == "2" || obj.vartype == "3"){
                obj.valvalue = $(s).find('.zdcsText').val()//变量类型值
                if(obj.valvalue == ""){
                    flag = false;
                    toastr.info('请填写取值！')
                }
            } else {
                obj.valvalue = $(s).find('.zdcsTypeSelect').val()//下拉框类型值
            }
            if($(s).attr("divid")){
                obj.id = Number($(s).attr("divid"))
                obj.algorithmid = Number($("#editDicYes").attr("editId"))
            }
            tables.push(obj)
        })
        dataAll.tableFuncs = tables;
        if(!flag) return false;
        console.log(dataAll);
        if( $("#editDicTitle").text() == "新增字典"){
            $.ajax({
                url:urlConfig.host + "/operatorMaintenance/addAlgorithm",
                data:JSON.stringify(dataAll),
                type:"POST",
                dataType: "json",
                contentType:"application/json",
                success(data) {
                    console.log(data);
                    toastr.success('保存成功！');
                    $("#editDic").hide()
                    dictionary();
                    Topology.init();
                    $("#dicDiv").show()
                }
            })
        } else {
            let jbxx = {
                "algorithmauthor": $("#editAuthor").val(),
                "algorithmfun": "",
                "algorithmname": name,
                "algorithmtype": 1,
                "des": des,
                "id": $("#editDicYes").attr("editId"),
                "ispublic": 0,
                "moduleid": 0,
                "remark": ""
            }
            dataAll.tableAlgorithm.id = $("#editDicYes").attr("editId");
            $.ajax({
                url:urlConfig.host + "/operatorMaintenance/modAlgorithmBaseInfoById",
                data:JSON.stringify(jbxx),
                type:"POST",
                dataType: "json",
                contentType:"application/json",
                success(data) {
                    console.log(data);
                    toastr.success('保存成功！');
                    $("#editDic").hide()
                }
            })
            $.ajax({
                url:urlConfig.host + "/operatorMaintenance/modAlgorithmFuncsById",
                data:JSON.stringify(dataAll),
                type:"POST",
                dataType: "json",
                contentType:"application/json",
                success(data) {
                    console.log(data);
                    dictionary()
                    Topology.init();
                    $("#dicDiv").show()
                }
            })
        }
    })
    $('body').on('click','#addZdcs',(e) => {
        $("#zdcsList").append(`
             <div class="zdcsDiv" style="margin-bottom: 15px">
                <p style="margin-top: 5px">
                    <span style="color:#fff;margin-right: 20px;">参数名称</span>
                    <input class="zdcsCsmc" type="text" value="">
                </p>
                <p>
                    <span style="color:#fff;margin-right: 20px">类型</span>
                    <select class="zdcsSelect">
                        <option value="2">常量</option>
                        <option value="3">对象</option>
                        <option value="1">基本类型</option>
                    </select>
                </p>
                <p>
                    <span style="color:#fff;;margin-right: 20px">取值</span>
                    <input type="text" value="" class="zdcsText">
                </p>
                <p>
                    <span style="color:#fff;margin-right: 20px">输入输出</span>
                    <select class="zdcsExport">
                        <option value="0">输入</option>
                        <option value="1">输出</option>
                    </select>
                     <button class="deleteZdcs" style="float: right;margin-right: 20px;height: 30px;line-height: 30px;background: #f56c6c;border: none;color: #fff;">删除</button>
                </p>
            </div>
        `)
    })
    $('body').on('change','.zdcsSelect',(e) => {
        if(e.target.value == "2" || e.target.value == "3"){
            $(e.target).parent().next().find('input').remove();
            $(e.target).parent().next().find('select').remove();
            $(e.target).parent().next().find('span').text("取值")
            $(e.target).parent().next().append(`<input type="text" value=""  class="zdcsText">`)
        } else {
            $(e.target).parent().next().find('input').remove();
            $(e.target).parent().next().find('select').remove();
            $(e.target).parent().next().find('span').text("数据项")
            $(e.target).parent().next().append(`
                    <select class="zdcsTypeSelect">
                        <option>int</option>
                        <option>long</option>
                        <option>byte</option>
                        <option>short</option>
                        <option>float</option>
                        <option>double</option>
                        <option>boolean</option>
                        <option>char</option>
                        <option>date</option>
                        <option>string</option>
                        <option>BLOB</option>
                        <option>boolean</option>
                        <option>array</option>
                    </select>`)
        }
    })
    $('body').on('click','.deleteZdcs',(e) => {
        $(e.target).parent().parent().remove()
    })

    $('body').on('click','#getAllSz',(e) => {
        $("#ruleMde").hide();
        $("#algorithmPage").show();
        getAllData('/operatorMaintenance/getAllAlgorithm',{id:'id',Tname:'tableAlgorithm',name:'algorithmname'},'算子',{username:null})
    })
    $('body').on('click','#getAllGzgz',(e) => {
        getAllData('/algorithmRule/getAllAlgorithmRule',{id:'id',Tname:'rolename'},'规则',{username:null})
        $("#algorithmPage").hide();
        $("#ruleMde").show();
    })
    function getAllData(url,datas,type,param){
        $.ajax({
            url:urlConfig.host+url,
            data:param,
            success: function(data) {
                $(".left-list").remove()
                if(type=="算子"){
                    data.map(item => {
                        window.addAlgorithm({
                            name: 'rectangle',
                            icon: 'icon-rectangle',
                            id:item[datas.Tname][datas.id],
                            type:type,
                            data: {
                                id:item[datas.Tname][datas.id]+type,
                                text: item[datas.Tname][datas.name],
                                rect: {
                                    width: 200,
                                    height: 100
                                },
                                font: {
                                    fontFamily: 'Arial',
                                    color: 'aqua',
                                    textBaseline: 'top'
                                },
                                data:{
                                    inNum:item.inNum,
                                    outNum:item.outNum,
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
                            data: {
                                id:item[datas.id]+type,
                                text: item[datas.Tname],
                                rect: {
                                    width: 200,
                                    height: 100
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
    //点击删除模型
    $('body').on('click','.lkr-list-del',(e) => {
        window.bigData.delmoduleId = $(e.target).data('id')
        $('#lkrFrameDel').fadeToggle(500)
    })
  
    // 点击删除算子
    $('body').on('click','.lkr-list-delAlgorithm',(e) => {
        window.bigData.delAlgorithmId = $(e.target).data('id')
        $('#lkrAlgorithm').fadeToggle(500)
    })
    function showMsg(){
        $.ajax({
            url:urlConfig.host+'/operatorMaintenance/getAlgorithmById',
            data:{
                algthId:window.bigData.editAlgorithmId
            },
            success: function(data) {
                console.log(data)
                if(algorithmtype==2){
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
                                            <select class="MathJaxInput2 inputButton">
                                                <option value="">请选择</option>
                                                <option value="2">常量</option>
                                                <option value="1">基本类型</option> 
                                            </select>
                                        </div>
                                        <div class="width-50 isShow1"> 
                                                <span>取值</span >
                                                <input type="text" class="MathJaxInput3 inputButton">
                                        </div>
                                        <div class="width-50 isShow2">
                                            <span>基本类型</span>
                                            <select class="MathJaxSelect  MathJaxInput1 inputButton">
                                                <option>byte</option>
                                                <option>short</option>
                                                <option>int</option>
                                                <option>long</option>
                                                <option>float</option>
                                                <option>double</option>
                                                <option>boolean</option>
                                                <option>char</option>
                                                <option>date</option>
                                                <option>string</option>
                                                <option>BLOB</option>
                                                <option>boolean</option>
                                                <option>array</option>
                                            </select>
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
                                }
                            }
                        }
                    }
                    $('.Frame').fadeToggle(500)
                }else if(algorithmtype==3){
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
                                            <button class="removeLogic" type="button" onclick="remov eLogic(event)">删除</button>
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
    }
      // 点击编辑算子


    $('body').on('click','.inputFields',(e) => {
        window.filed.inputFieldsTarget = $(e.target)
        $('#fields').fadeToggle(500)
    })
     // 点击删除算子
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
            toastr.info("至少勾选一个规则！");
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

    $('body').on('click','button.delTab',(e) => {
        $(e.target).parents('tr').remove();
    })
    $("#showAllmag").on("click",()=>{
        showMsg(window.selectId)
    })
    function showMsg(AlgorithmId){
        $.ajax({
            url:urlConfig.host +"/operatorMaintenance/getAlgorithmById",
            data:{algthId:AlgorithmId} ,
            type:"get",
            success(data) {
                console.log(data);
                $("#editDicTitle").text("字典详情")
                $("#editDicYes").hide()
                if(data.tableAlgorithm.algorithmtype == 1){
                    $("#editAuthor").val(data.tableAlgorithm.algorithmauthor).attr({"disabled":"disabled"});
                    $("#editDicName").val(data.tableAlgorithm.algorithmname).attr({"disabled":"disabled"});
                    $("#editDicDes").val(data.tableAlgorithm.des).attr({"disabled":"disabled"});
                    $("#zdcsList").empty();
                    $("#addZdcs").hide();
                    $("#editDic").show();
                    data.tableFuncs.map(t=>{
                        $("#zdcsList").append(`
                         <div divId="${t.id}" class="zdcsDiv" style="margin-bottom: 15px">
                            <p style="margin-top: 5px">
                                <span style="color:#fff;margin-right: 20px;">参数名称</span>
                                <input class="zdcsCsmc" disabled type="text" value="${t.varname}">
                            </p>
                            <p>
                                <span style="color:#fff;;margin-right: 20px">类型</span>
                                <select class="zdcsSelect" disabled>
                                    <option value="2">常量</option>
                                    <option value="3">对象</option>
                                    <option value="1">基本类型</option>
                                </select>
                            </p>
                             <p>
                                <span style="color:#fff;;margin-right: 20px">取值</span>
                                <input type="text" value="" class="zdcsText" disabled>
                            </p>
                            <p>
                                <span style="color:#fff;margin-right: 20px">输入输出</span>
                                <select class="zdcsExport" disabled>
                                    <option value="0">输入</option>
                                    <option value="1">输出</option>
                                </select>
                            </p>
                        </div>
                    `)
                    })
                    for(var i=0;i<data.tableFuncs.length;i++){
                        $("#editDic .zdcsSelect").eq(i).val(data.tableFuncs[i].vartype)
                        $("#editDic .zdcsExport").eq(i).val(data.tableFuncs[i].inorout)
                        if(data.tableFuncs[i].vartype == 2 || data.tableFuncs[i].vartype == 3){
                            $("#editDic .zdcsText").eq(i).val(data.tableFuncs[i].valvalue)
                        } else {
                            $("#editDic .zdcsText").eq(i).hide();
                            let select= $(`
                            <select class="zdcsTypeSelect" disabled>
                                <option>int</option>
                                <option>long</option>
                                <option>byte</option>
                                <option>short</option>
                                <option>float</option>
                                <option>double</option>
                                <option>boolean</option>
                                <option>char</option>
                                <option>date</option>
                                <option>string</option>
                                <option>BLOB</option>
                                <option>boolean</option>
                                <option>array</option>
                            </select>`)
                            select.val(data.tableFuncs[i].valvalue)
                            $("#editDic .zdcsSelect").eq(i).parent().next().find("span").text("数据项")
                            $("#editDic .zdcsSelect").eq(i).parent().next().append(select)
                        }
                    }
                } else if(data.tableAlgorithm.algorithmtype == 2){
                    $("#gsName").val(data.tableAlgorithm.algorithmauthor).attr({"disabled":"disabled"});
                    $("#gsDes").val(data.tableAlgorithm.des).attr({"disabled":"disabled"});
                    $('#AlgorithmnameY').val(data.tableAlgorithm.algorithmname).attr({"bleAlgorithmid":data.tableAlgorithm.id,"tableAlmoduleid":data.tableAlgorithm.moduleid,"disabled":"disabled"})
                    $('#MathInput').attr("disabled","disabled");
                    $('.closeGsButton').hide();
                    window.bigData.editFormula = data.tableAlgorithm.algorithmfun
                    window.changeBds(data.tableAlgorithm.algorithmfun);
                    window.bigData.formulaType = 'edit';
                    if(data.tableFuncs.length>0){
                        let str =``
                        data.tableFuncs.map((item)=>{
                            str +=`<div class="MathJaxParam" formulaid="${item.id}" formulaModuleId="${item.algorithmid}">
                                        <div class="width-50">
                                            <span>变量</span>
                                            <input type="text" readonly="readonly" value="${item.varname}" class="MathJaxInput1 inputButton">
                                        </div>
                                        <div class="width-50 width-select">
                                            <span>类型</span>
                                            <select  class="MathJaxInput2 inputButton" disabled="disabled">
                                                <option value="2">常量</option>
                                                <option value="1">基本类型</option>
                                            </select>
                                        </div>
                                        <div class="width-50 isShow1">
                                                <span>取值</span >
                                                <input type="text" readonly="readonly" class="MathJaxInput3 inputButton">
                                        </div>
                                        <div class="width-50 isShow2">
                                            <span>基本类型</span>
                                            <select class="MathJaxSelect" disabled="disabled">
                                                <option>byte</option>
                                                <option>short</option>
                                                <option>int</option>
                                                <option>long</option>
                                                <option>float</option>
                                                <option>double</option>
                                                <option>boolean</option>
                                                <option>char</option>
                                                <option>date</option>
                                                <option>string</option>
                                                <option>BLOB</option>
                                                <option>boolean</option>
                                                <option>array</option>
                                            </select>
                                        </div>
                                        <div class="width-50">
                                                <span>描述</span>
                                                <input type="text" readonly="readonly" class="MathJaxInput4 inputButton" value="${item.remark}">
                                        </div>
                                    </div>`
                        })
                        $(".MathJaxEdit").html(str)
                        for(let j=0;j<data.tableFuncs.length;j++){
                            for(k=0;k<$('.MathJaxParam').length;k++){
                                let vType = data.tableFuncs[j].vartype
                                if(j == k){
                                    $('.MathJaxParam').eq(k).find(".MathJaxInput2").find("option[value='"+vType+"']").attr("selected",true);
                                    if(vType == "2"){
                                        $('.MathJaxParam').eq(k).find('.isShow2').attr("style","display:none;");
                                        $('.MathJaxParam').eq(k).find(".isShow1").attr("style","display:block;");
                                        $('.MathJaxParam').eq(k).find(".isShow1").find('input').attr("value",data.tableFuncs[j].valvalue)
                                    }
                                    if(vType == "1"){
                                        $('.MathJaxParam').eq(k).find('.isShow1').attr("style","display:none;");
                                        $('.MathJaxParam').eq(k).find(".isShow2").attr("style","display:block;");
                                        $('.MathJaxParam').eq(k).find(".isShow2").find('input').attr("value",data.tableFuncs[j].valvalue)
                                        $('.MathJaxParam').eq(k).find(".MathJaxSelect").val(data.tableFuncs[j].valvalue)
                                    }
                                }
                            }
                        }
                    }
                    $('.Frame').fadeToggle(500)
                }else if(data.tableAlgorithm.algorithmtype == 3){
                    $('.addButton').hide();
                    $(".closeLjButton").hide();
                    window.bigData.formulaType = 'edit';
                    $('#LogicName').val(data.tableAlgorithm.algorithmname).attr({"tableAlgorithmid":data.tableAlgorithm.id,"tableAlmoduleid":data.tableAlgorithm.moduleid,"disabled":"disabled"});
                    $("#ljName").val(data.tableAlgorithm.algorithmauthor).attr("disabled","disabled");
                    $('#ljDes').val(data.tableAlgorithm.des).attr("disabled","disabled");
                    let algorithmfun = data.tableAlgorithm.algorithmfun;
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
                                                <select disabled name="" class="Logic-form-field inputButton">
                                                    <option value="">${obj[0]}</option>
                                                </select>
                                                <select disabled name="" class="Logic-form-label inputButton">
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
                                                <input disabled type="text" class="Logic-form-value inputButton" value="${obj[1]}">
                                                <button disabled class="removeLogic" type="button" onclick="removeLogic(event)">删除</button>
                                            </li>`
                                    $('.logicLi').eq(i).find(".Logic-form-field").find("option[value='"+obj[0]+"']").attr("selected",true);
                                }
                                if(algorithmfun[i].indexOf('或') !=-1){
                                    let obj = algorithmfun[i].split("或")
                                    str+=` <li class="logicLi">
                                                <select disabled name="" class="Logic-form-field inputButton">
                                                    <option value="">${obj[0]}</option>
                                                </select>
                                                <select disabled name="" class="Logic-form-label inputButton">
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
                                                <input disabled type="text" class="Logic-form-value inputButton" value="${obj[1]}">
                                                <button disabled class="removeLogic" type="button" onclick="removeLogic(event)">删除</button>
                                            </li>`
                                    $('.logicLi').eq(i).find(".Logic-form-field").find("option[value='"+obj[0]+"']").attr("selected",true);
                                }
                                if(algorithmfun[i].indexOf('非') !=-1){
                                    let obj = algorithmfun[i].split("非")
                                    str+=` <li class="logicLi">
                                            <select disabled name="" class="Logic-form-field inputButton">
                                                <option value="">${obj[0]}</option>
                                            </select>
                                            <select disabled name="" class="Logic-form-label inputButton">
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
                                            <input disabled type="text" class="Logic-form-value inputButton" value="${obj[1]}">
                                            <button disabled class="removeLogic" type="button" onclick="removeLogic(event)">删除</button>
                                        </li>`
                                    $('.logicLi').eq(i).find(".Logic-form-field").find("option[value='"+obj[0]+"']").attr("selected",true);
                                }
                                if((algorithmfun[i].indexOf('<') !=-1) && (algorithmfun[i].indexOf('<=') == -1)){
                                    let obj = algorithmfun[i].split("<")
                                    str+=` <li class="logicLi">
                                                <select disabled name="" class="Logic-form-field inputButton">
                                                    <option value="">${obj[0]}</option>
                                                </select>
                                                <select disabled name="" class="Logic-form-label inputButton">
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
                                                <input disabled type="text" class="Logic-form-value inputButton" value="${obj[1]}">
                                                <button disabled class="removeLogic" type="button" onclick="removeLogic(event)">删除</button>
                                            </li>`
                                    $('.logicLi').eq(i).find(".Logic-form-field").find("option[value='"+obj[0]+"']").attr("selected",true);
                                }
                                if((algorithmfun[i].indexOf('<=') !=-1) && (algorithmfun[i].indexOf('<') !=-1)  && (algorithmfun[i].indexOf('=') !=-1)){
                                    let obj = algorithmfun[i].split("<=")
                                    str+=` <li class="logicLi">
                                                <select disabled name="" class="Logic-form-field inputButton">
                                                    <option value="">${obj[0]}</option>
                                                </select>
                                                <select disabled name="" class="Logic-form-label inputButton">
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
                                                <input disabled type="text" class="Logic-form-value inputButton" value="${obj[1]}">
                                                <button disabled class="removeLogic" type="button" onclick="removeLogic(event)">删除</button>
                                            </li>`
                                    $('.logicLi').eq(i).find(".Logic-form-field").find("option[value='"+obj[0]+"']").attr("selected",true);
                                }
                                if((algorithmfun[i].indexOf('>') !=-1) && (algorithmfun[i].indexOf('>=') == -1)){
                                    let obj = algorithmfun[i].split(">")
                                    str+=` <li class="logicLi">
                                            <select disabled name="" class="Logic-form-field inputButton">
                                                <option value="">${obj[0]}</option>
                                            </select>
                                            <select disabled name="" class="Logic-form-label inputButton">
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
                                            <input disabled type="text" class="Logic-form-value inputButton" value="${obj[1]}">
                                            <button disabled class="removeLogic" type="button" onclick="removeLogic(event)">删除</button>
                                        </li>`
                                    $('.logicLi').eq(i).find(".Logic-form-field").find("option[value='"+obj[0]+"']").attr("selected",true);
                                }
                                if((algorithmfun[i].indexOf('=') !=-1) && (algorithmfun[i].indexOf('>') !=-1) && (algorithmfun[i].indexOf('>=') !=-1)){
                                    let obj = algorithmfun[i].split(">=")
                                    str+=` <li class="logicLi">
                                                <select disabled name="" class="Logic-form-field inputButton">
                                                    <option value="">${obj[0]}</option>
                                                </select>
                                                <select disabled name="" class="Logic-form-label inputButton">
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
                                                <input disabled type="text" class="Logic-form-value inputButton" value="${obj[1]}">
                                                <button disabled class="removeLogic" type="button" onclick="removeLogic(event)">删除</button>
                                            </li>`
                                    $('.logicLi').eq(i).find(".Logic-form-field").find("option[value='"+obj[0]+"']").attr("selected",true);
                                }
                                if((algorithmfun[i].indexOf('=') !=-1) && (algorithmfun[i].indexOf('>') ==-1) && (algorithmfun[i].indexOf('<') == -1)){
                                    let obj = algorithmfun[i].split("=")
                                    str+=` <li class="logicLi">
                                            <select disabled name="" class="Logic-form-field inputButton">
                                                <option value="">${obj[0]}</option>
                                            </select>
                                            <select disabled name="" class="Logic-form-label inputButton">
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
                                            <input disabled type="text" class="Logic-form-value inputButton" value="${obj[1]}">
                                            <button disabled class="removeLogic" type="button" onclick="removeLogic(event)">删除</button>
                                        </li>`
                                    $('.logicLi').eq(i).find(".Logic-form-field").find("option[value='"+obj[0]+"']").attr("selected",true);
                                }


                            }
                            $('.logicUl').html(str)

                        }
                    })

                    $(".Logic").fadeToggle(500)
                }
            }
        })
    }
    $('body').on('dblclick','.dbclickAlgorithm',(e) => {
        if($('#dictordySpan').hasClass('addDicClose')) {
            $(".addDicClose").attr("class","editDicClose");
        }
        $("#dataModulePage").hide();
        $("#lkrFrame").hide();
        let AlgorithmId= $(e.target).attr('algorithmid')
        showMsg(AlgorithmId)
    })
})


