
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
    })
    $('body').on('click','.addLjgx',(e) => {
        var text =  $(e.target).parent().children("i").text();
        if($("#selectOutIn").val() == 1){
            var val = $("#actionMsgIn").val();
            $("#actionMsgIn").val(val + " " + text)
        } else {
            var val = $("#actionMsgOut").val();
            $("#actionMsgOut").val(val + " " + text)
        }

    })
    $('body').on('click','.addLjgxType',(e) => {
        var type =  $("#addLjSelect").val();
        if($("#selectOutIn").val() == 1){
            var val = $("#actionMsgIn").val();
            $("#actionMsgIn").val(val + " " + type)
        } else {
            var val = $("#actionMsgOut").val();
            $("#actionMsgOut").val(val + " " + type)
        }
    })

    $("#selectOutIn").change(()=> {
        if ($("#selectOutIn").val() == "1") {
            $("#actionInDiv").show();
            $("#actionOutDiv").hide();
            $("#actionMsgIn").show();
            $("#actionMsgOut").hide();
                if ($("#addActionButton").attr("resData")) {//后台返回数据
                    $("#actionInDiv").empty();
                    $("#actionMsgIn").val($("#addActionButton").attr("actionRelation"))
                    var from_name = $("#addActionButton").attr("from_name");
                    var from_id = $("#addActionButton").attr("from_id");
                    resCurrentLineData.dataIn.interfaceRoleDataModels.algorithmconditions.map((t,i) => {
                        $("#actionInDiv").append(`
                              <div style="margin: 10px 0">
                                   <i>${i+1}</i>
                                   <span>行为值来源</span><input class="xwzly_in" disabled value="${from_name}" resource="${from_id}">
                                   <span>行为</span><select class="xwSelect_in">
                                   <option value=">">></option>
                                   <option value="<"><</option>
                                   <option value="=">=</option>
                                   <option value=">=">>=</option>
                                   <option value="<="><=</option>
                                   <option value="!=">!=</option>
                                   <option value="assignment">赋值</option>
                               </select>
                                   <span>表达式</span><input type="text" value="${t.expression}" class="bds_in">
                                   <button class="addLjgx" type="button"  style="background: #409eff;color: #fff;margin-left: 5px;height: 20px;border: none;width: 22px">+</button>
                                   <button class="deleteActionData" type="button"  style="background: #f56c6c;color: #fff;margin-left: 10px;height: 20px;border: none;width: 22px">x</button>
                              </div>
                         `)
                    })
                    resCurrentLineData.dataIn.interfaceRoleDataModels.algorithmconditions.map((t, i) => {
                        $('#actionInDiv .xwSelect_in').eq(i).val(t.behavior)
                    })
                } else {
                    var from_name = $("#addActionButton").attr("from_name");
                    var from_id = $("#addActionButton").attr("from_id");
                    globalActionDatas.map(s => {
                        if (s.id == $("#addActionButton").attr("out_small") + "AND" + $("#addActionButton").attr("in_small")) {
                            try {
                                var lineDatas = s.dataIn.interfaceRoleDataModels.algorithmconditions;
                                $("#actionInDiv").empty();
                                lineDatas.map((t,i) => {
                                    $("#actionInDiv").append(`
                                      <div style="margin: 10px 0">
                                           <i>${i+1}</i>
                                           <span>行为值来源</span><input class="xwzly_in" disabled value="${from_name}" resource="${from_id}">
                                           <span>行为</span><select class="xwSelect_in">
                                           <option value=">">></option>
                                           <option value="<"><</option>
                                           <option value="=">=</option>
                                           <option value=">=">>=</option>
                                           <option value="<="><=</option>
                                           <option value="!=">!=</option>
                                           <option value="assignment">赋值</option>
                                       </select>
                                           <span>表达式</span><input type="text" value="${t.expression}" class="bds_in">
                                           <button class="addLjgx" type="button"  style="background: #409eff;color: #fff;margin-left: 5px;height: 20px;border: none;width: 22px">+</button>
                                           <button class="deleteActionData" type="button"  style="background: #f56c6c;color: #fff;margin-left: 10px;height: 20px;border: none">x</button>
                                      </div>
                                    `)
                                })
                                lineDatas.map((t, i) => {
                                    $('#actionInDiv .xwSelect_in').eq(i).val(t.behavior)
                                })
                            } catch (e) {

                            }
                        }
                    })
                }
        } else {
            $("#actionInDiv").hide();
            $("#actionOutDiv").show();
            $("#actionMsgIn").hide();
            $("#actionMsgOut").show();
            if($("#addActionButton").attr("resData")){//后台数据
                $("#actionMsgOut").val($("#addActionButton").attr("preActionRelation"))
                    if($("#addActionButton").attr("resData")){
                        $.ajax({
                            url:urlConfig.host+'/operatorMaintenance/getAlgorithmById',
                            data:{algthId:$("#addActionButton").attr("id_out")},
                            success(res) {
                                let optionx = "";
                                res.tableFuncs.map(s => {
                                    optionx += `<option value=${s.id} type=${s.vartype} valvalue=${s.valvalue}>${s.varname}</option>`
                                })
                                $("#actionOutDiv").empty();
                                resCurrentLineData.dataOut.interfaceRoleDataModels.algorithmconditions.map((t,i)=>{
                                    $("#actionOutDiv").append(`
                                          <div style="margin: 10px 0">
                                               <i>${i+1}</i>
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
                                               <span>表达式</span><input type="text" value="${t.expression}" class="bds_out">
                                               <button class="addLjgx" type="button"  style="background: #409eff;color: #fff;margin-left: 5px;height: 20px;border: none;width: 22px">+</button>
                                               <button class="deleteActionData" type="button"  style="background: #f56c6c;color: #fff;margin-left: 10px;height: 20px;border: none">X</button>
                                          </div>
                                 `)
                                })
                                resCurrentLineData.dataOut.interfaceRoleDataModels.algorithmconditions.map((t,i)=>{
                                    $('#actionOutDiv .xwSelect_out').eq(i).val(t.behavior)
                                })
                            }
                        })
                    }
            } else {
                $.ajax({
                    url:urlConfig.host+'/operatorMaintenance/getAlgorithmById',
                    data:{algthId:$("#addActionButton").attr("id_out")},
                    success(res) {
                        let optionx = "";
                        res.tableFuncs.map(s=>{
                            optionx += `<option value=${s.id} type=${s.vartype} valvalue=${s.valvalue}>${s.varname}</option>`
                        })
                        $("#actionOutDiv").empty();
                        globalActionDatas.map(s=>{
                            if(s.id == $("#addActionButton").attr("out_small") + "AND" +$("#addActionButton").attr("in_small")){
                                try {
                                    var lineDatas = s.dataOut.interfaceRoleDataModels.algorithmconditions;
                                    lineDatas.map((t,i)=>{
                                        $("#actionOutDiv").append(`
                                               <div style="margin: 10px 0">
                                                   <i>${i+1}</i>
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
                                                   <span>表达式</span><input type="text" value="${t.expression}" class="bds_out">
                                                    <button class="addLjgx" type="button"  style="background: #409eff;color: #fff;margin-left: 5px;height: 20px;border: none;width: 22px">+</button>
                                                   <button class="deleteActionData" type="button"  style="background: #f56c6c;color: #fff;margin-left: 10px;height: 20px;border: none">X</button>
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
        }
    })
    $('body').on('click','.deleteActionData',(e) => {
        if($("#selectOutIn").val() == 1){
            $("#actionMsgIn").val("");
        } else {
            $("#actionMsgOut").val("");
        }
        var divBig =  $(e.target).parent().parent()
        $(e.target).parent().remove();
        divBig.find('i').each((i,s)=>{
            $(s).text(i+1)
        })
    })
    $('body').on('click','#addActionButton',(e) => {
        if($("#selectOutIn").val() == "1"){
            $("#actionMsgIn").val("");
            var num = $("#actionInDiv div").length;
            var from_name = $('#addActionButton').attr("from_name");
            var from_id = $('#addActionButton').attr("from_id");
                $("#actionInDiv").append(`
                      <div style="margin: 10px 0">
                           <i>${num+1}</i>
                           <span>行为值来源</span><input class="xwzly_in" disabled value="${from_name}" resource="${from_id}">
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
                           <button class="addLjgx" type="button"  style="background: #409eff;color: #fff;margin-left: 5px;height: 20px;border: none;width: 22px">+</button>
                           <button class="deleteActionData" type="button"  style="background: #f56c6c;color: #fff;margin-left: 10px;height: 20px;border: none;width: 22px">x</button>
                      </div>
                    `)
        } else {
            $("#actionMsgOut").val("");
            $.ajax({
                url:urlConfig.host+'/operatorMaintenance/getAlgorithmById',
                data:{algthId:$("#addActionButton").attr("id_out")},
                success(res) {
                    let optionx = "";
                    res.tableFuncs.map(s=>{
                        optionx += `<option value=${s.id} type=${s.vartype} valvalue=${s.valvalue}>${s.varname}</option>`
                    })
                    var num = $("#actionOutDiv div").length;
                    $("#actionOutDiv").append(`
                          <div style="margin: 10px 0">
                               <i>${num+1}</i>
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
                                <button class="addLjgx" type="button"  style="background: #409eff;color: #fff;margin-left: 5px;height: 20px;border: none;width: 22px">+</button>
                               <button class="deleteActionData" type="button"  style="background: #f56c6c;color: #fff;margin-left: 10px;height: 20px;border: none;width: 22px">x</button>
                          </div>
                        `)
                }
            })
        }
    })
    $('body').on('click','.bds_out',(e) => {
        if($(e.target).parent().children('.xwSelect_out').val() == "assignment"){
            if($(e.target).parent().children('.xwzly_out').find("option:selected").attr('type') != "3"){
                $('.noticeList').append(`<li>${getTime()}【算法】行为值来源为对象才能赋值！ </li>`)
                toastr.info(`【算法】行为值来源为对象才能赋值！` )
                $("#flex_props1_home").scrollTop($("#flex_props1_home")[0].scrollHeight);
                return false
            }
        }
        if($(e.target).parent().children('.xwzly_out').find("option:selected").attr('type') == "3" && $(e.target).parent().children('.xwSelect_out').val() == "assignment"){//对象&&赋值
            currentActionInput = $(e.target)
            $.ajax({
                url: urlConfig.host + '/module/findTableModuleByName',
                data: {name : $(e.target).parent().children('.xwzly_out').find("option:selected").attr('valvalue')},
                success(res) {
                    if(res == ""){
                        $('.noticeList').append(`<li>${getTime()}【算法】非本系统模型，无选择参数！ </li>`)
                        toastr.info(`【算法】非本系统模型，无选择参数！` )
                        $("#flex_props1_home").scrollTop($("#flex_props1_home")[0].scrollHeight);
                        return false;
                    }
                    $("#actionSelectDiv").show();
                    $("#actionSelectParma").empty();
                    res.modulefields.map(t=>{
                        $("#actionSelectParma").append(`
                            <option value=${t.fieldname}>${t.fieldname}</option>
                        `)
                    })
                    if(currentActionInput.val()){
                        var val = currentActionInput.val();
                        var start = val.indexOf(".");
                        var end = val.indexOf("=");
                        $("#actionSelectParma").val(val.slice(start+1,end))
                    }
                }
            })
            $.ajax({
                url:urlConfig.host+'/operatorMaintenance/getAlgorithmById',
                data:{algthId:$("#addActionButton").attr("id_out")},
                success(res) {
                    let optionx = "";
                    res.tableFuncs.map(s=>{
                        optionx += `<option value=${s.varname}>${s.varname}</option>`
                    })
                    $("#actionSelectName").html(`${optionx}`)
                    if(currentActionInput.val()){
                        var v = currentActionInput.val();
                        var s = v.indexOf("[");
                        var e = v.indexOf("]");
                        $("#actionSelectName").val(v.slice(s+1,e))
                    }
                }
            })
        }
    })
    $('body').on('click','#actionSelectButton',(e) => {
        var selectName = currentActionInput.parent().children('.xwzly_out').find("option:selected").attr('valvalue');
        var fieldname = $("#actionSelectParma").val();
        var fromName = $("#actionSelectName").val();
        $("#actionSelectDiv").hide();
        currentActionInput.val(selectName + "." + fieldname + "=" + "[" + fromName+ "]")
    })
    $('body').on('click','#addAction',(e) => {
        $("#actionDiv").hide();
        $("#actionOutDiv").hide()
        if($("#addActionButton").attr("resdata")){//修改动作
            var sendDataIn = [];
            var sendDataOut = [];
            $('#actionInDiv div').each(function (i,v) {
                let obj = {
                    "behavior": $(this).find(".xwSelect_in").val(),
                    "expression": $(this).find(".bds_in").val(),
                    "id":$(v).attr("actionId"),
                    "interfaceparametersid":$("#addActionButton").attr("in_small"),
                    "interfaceroleid": resCurrentLineData.dataIn.interfaceRoleDataModels.id,//线id
                    "remark": "",
                    "valuesources":Number($(this).find(".xwzly_in").attr("resource")),
                    "xh":$(this).find("i").text()
                };
                sendDataIn.push(obj)
            })
            var sendDataAll = {
                algorithmconditions:sendDataIn,
                interfaceParametersID:$("#addActionButton").attr("in_small"),
                interfaceRoleId:resCurrentLineData.dataIn.interfaceRoleDataModels.id,
                actionRelation:$("#actionMsgIn").val()
            }
            $.ajax({
                url:urlConfig.host+'/algorithmRule/saveFunAction',
                data:JSON.stringify(sendDataAll),
                type:"POST",
                dataType: "json",
                contentType:"application/json",
                success(res) {}
            })
            $('#actionOutDiv div').each(function (i,v) {
                let obj = {
                    "behavior": $(this).find(".xwSelect_out").val(),
                    "expression": $(this).find(".bds_out").val(),
                    "id":$(v).attr("actionId"),
                    "interfaceparametersid":$("#addActionButton").attr("out_small"),
                    "interfaceroleid": resCurrentLineData.dataIn.interfaceRoleDataModels.id,//线id
                    "remark": "",
                    "valuesources":Number($(this).find(".xwzly_out").val()),
                    "xh":$(this).find("i").text()
                };
                sendDataOut.push(obj)
            })
            var sendDataAllOut = {
                algorithmconditions:sendDataOut,
                interfaceParametersID:$("#addActionButton").attr("out_small"),
                interfaceRoleId:resCurrentLineData.dataIn.interfaceRoleDataModels.id,
                actionRelation:$("#actionMsgOut").val()
            }
            $.ajax({
                url:urlConfig.host+'/algorithmRule/saveFunAction',
                data:JSON.stringify(sendDataAllOut),
                type:"POST",
                dataType: "json",
                contentType:"application/json",
                success(res) {}
            })
        } else{//新增动作
                var dataArrIn = [];
                $('#actionInDiv div').each(function () {
                    let obj = {
                        "behavior": $(this).find(".xwSelect_in").val(),
                        "expression": $(this).find(".bds_in").val(),
                        "id": 0,
                        "interfaceparametersid":$("#addActionButton").attr("in_small"),
                        "interfaceroleid": 0,
                        "remark": "",
                        "valuesources": Number($(this).find(".xwzly_in").attr("resource")),
                        "xh":$(this).find("i").text()
                    };
                    dataArrIn.push(obj)
                })
                globalActionDatas.map(s=>{
                    if(s.id == $("#addActionButton").attr("out_small") + "AND" + $("#addActionButton").attr("in_small")){
                        s.dataIn.interfaceRoleDataModels.algorithmconditions = dataArrIn;
                        s.dataIn.interfaceRoleDataModels.actionRelation = $("#actionMsgIn").val();
                    }
                })
                var dataArrOut = [];
                $('#actionOutDiv div').each(function () {
                    let obj = {
                        "behavior": $(this).find(".xwSelect_out").val(),
                        "expression": $(this).find(".bds_out").val(),
                        "id": 0,
                        "interfaceparametersid":$("#addActionButton").attr("out_small"),
                        "interfaceroleid": 0,
                        "remark": "",
                        "valuesources":Number($(this).find(".xwzly_out").val()),
                        "xh":$(this).find("i").text()
                    };
                    dataArrOut.push(obj)
                })
                globalActionDatas.map(s=>{
                    if(s.id == $("#addActionButton").attr("out_small") + "AND" + $("#addActionButton").attr("in_small")){
                        s.dataOut.interfaceRoleDataModels.algorithmconditions = dataArrOut;
                        s.dataOut.interfaceRoleDataModels.preActionRelation = $("#actionMsgOut").val();
                    }
                })
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
        $("#editDicTitle").text("新增算法");
        $("#zdcsList").html(``)
        $("#editDicYes").attr("editId","")
        $("#editDicName").val("");
        $("#editDicDes").val("");
        $("#editAuthor").val("");
        $("#company").val("");
        $("#dicDiv").hide();
        $("#addZdcs").show();
        $(".editDicClose").attr("class","addDicClose");
        $("#editAuthor").attr("disabled",false);
        $("#editDicName").attr("disabled",false);
        $("#editDicDes").attr("disabled",false);
        $("#group").attr("disabled",false)
        $("#company").attr("disabled",false)
        $.ajax({
            url: urlConfig.host + '/group/findAllGroupMessagesByType',
            type:"get",
            data: {type:2},
            success(data){
                if(data.length == 0){
                    $('.noticeList').append(`<li>${getTime()}请先添加【算法】分组！</li>`)
                    toastr.info(`请先添加【算法】分组！` )
                    $("#flex_props1_home").scrollTop($("#flex_props1_home")[0].scrollHeight);
                    return
                }
                $("#group").empty()
                data.map(s=>{
                    $("#group").append(`<option value="${s.groupname}">${s.groupname}</option>`)
                })
            }
        })
    })
    $('body').on('click','.dicEdit',(e) => {
        $("#editDicTitle").text("修改算法")
    }) 
    $('body').on('click','#editDicYes',(e) => {
        let name = $("#editDicName").val();
        var flag = true;
        if(name == ""){
            $('.noticeList').append(`<li>${getTime()}【算法】请填写算法名称！ </li>`)
            toastr.info(`【算法】请填写算法名称！` )
            $("#flex_props1_home").scrollTop($("#flex_props1_home")[0].scrollHeight);
            return false;
        }
        let des =  $("#editDicDes").val();
        let dataAll = {
            "tableAlgorithm": {
                "algorithmauthor": $("#editAuthor").val(),
                "algorithmfun": "",
                "algorithmgroup":$('#group').val(),
                "algorithmname": name,
                "algorithmtype": 1,
                "des": des,
                "id": 0,
                "ispublic": 0,
                "moduleid":0,
                "remark": $('#company').val(),
                "remark2":"",
                "status":""
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
            obj.parametername = $(s).find('.zdcsCsmc').val() //中文名称
            if(obj.parametername == ""){
                flag = false;
                $('.noticeList').append(`<li>${getTime()}【算法字典】请填写中文名！ </li>`)
                toastr.info(`【算法字典】请填写中文名！` )
                $("#flex_props1_home").scrollTop($("#flex_props1_home")[0].scrollHeight);
            }
            obj.varname = $(s).find('.variable').val()//输入输出
            if(obj.varname == ""){
                flag = false;
                $('.noticeList').append(`<li>${getTime()}【算法字典】请填写英文名！ </li>`)
                toastr.info(`【算法字典】请填写英文名！` )
                $("#flex_props1_home").scrollTop($("#flex_props1_home")[0].scrollHeight);
            }
            obj.inorout = $(s).find('.zdcsExport').val()//输入输出
            if(obj.inorout == ""){
                flag = false;
                $('.noticeList').append(`<li>${getTime()}【算法字典】请填写输入输出！ </li>`)
                toastr.info(`【算法字典】请填写输入输出！` )
                $("#flex_props1_home").scrollTop($("#flex_props1_home")[0].scrollHeight);
            }
            obj.vartype = $(s).find('.zdcsSelect').val()//变量类型
            if(obj.vartype == "2"){
                obj.valvalue = $(s).find('.zdcsText').val()//变量类型值
                if(obj.valvalue == ""){
                    flag = false;
                    $('.noticeList').append(`<li>${getTime()}【算法字典】请填写取值！ </li>`)
                    toastr.info(`【算法字典】请填写取值！` )
                    $("#flex_props1_home").scrollTop($("#flex_props1_home")[0].scrollHeight);
                }
            } else if(obj.vartype == "1") {
                obj.valvalue = $(s).find('.zdcsTypeSelect').val()//下拉框类型值
            } else {
                obj.valvalue = $(s).find('.moduleTypeSelect').val()//下拉框模型值
            }
            if($(s).attr("divid")){
                obj.id = Number($(s).attr("divid"))
            }
            if($("#editDicYes").attr("editId")){
                obj.algorithmid = Number($("#editDicYes").attr("editId"))
            }
            tables.push(obj)
        })
        dataAll.tableFuncs = tables;
        if(!flag) return false;
        if( $("#editDicTitle").text() == "新增算法"){
            $.ajax({
                url:urlConfig.host + "/operatorMaintenance/addAlgorithm",
                data:JSON.stringify(dataAll),
                type:"POST",
                dataType: "json",
                contentType:"application/json",
                success(data) {
                    $('.noticeList').append(`<li>${getTime()}【算法】保存成功！ </li>`)
                    toastr.info(`【算法】保存成功！` )
                    $("#flex_props1_home").scrollTop($("#flex_props1_home")[0].scrollHeight);
                    if(data.status == 1){
                        $('.noticeList').append(`<li>${getTime()}${data.msg}！ </li>`)
                        toastr.info(`${data.msg}` )
                        $("#flex_props1_home").scrollTop($("#flex_props1_home")[0].scrollHeight);
                        $("#editDic").hide()
                        dictionary();
                        sfWinList();
                        $("#dicDiv").show()
                        dictionaryShow()
                    } else {
                        $('.noticeList').append(`<li>${getTime()}${data.msg}！ </li>`)
                        toastr.info(`${data.msg}` )
                        $("#flex_props1_home").scrollTop($("#flex_props1_home")[0].scrollHeight);
                    }
                }
            })
        } else {
            let jbxx = {
                "algorithmauthor": $("#editAuthor").val(),
                "algorithmfun": "",
                "algorithmgroup":$('#group').val(),
                "algorithmname": name,
                "algorithmtype": 1,
                "des": des,
                "id": $("#editDicYes").attr("editId"),
                "ispublic": 0,
                "moduleid": 0,
                "remark": $('#company').val(),
                "remark2":"",
                "status":$("#zdStatus").val()
            }
            dataAll.tableAlgorithm.id = $("#editDicYes").attr("editId");
            $.ajax({
                url:urlConfig.host + "/operatorMaintenance/modAlgorithmBaseInfoById",
                data:JSON.stringify(jbxx),
                type:"POST",
                dataType: "json",
                contentType:"application/json",
                success(data) {
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
                    if(data.status == 1){
                        sfWinList()
                        $("#dicDiv").show()
                        $('.noticeList').append(`<li>${getTime()}【算法】 ${data.msg} </li>`)
                        toastr.info(`${data.msg}` )
                        $("#flex_props1_home").scrollTop($("#flex_props1_home")[0].scrollHeight);
                        dictionaryShow()
                        freshClick("sfWinTree")
                    } else {
                        $('.noticeList').append(`<li>${getTime()}【算法】 ${data.msg} </li>`)
                        toastr.info(`${data.msg}` )
                        $("#flex_props1_home").scrollTop($("#flex_props1_home")[0].scrollHeight);
                    }
                }
            })
        }

    })
    $('body').on('click','#addZdcs',(e) => {
        $("#zdcsList").append(`
             <div class="zdcsDiv" style="margin-bottom: 15px">
                <i>
                    <span style="color:#fff;">中文名</span>
                    <input class="zdcsCsmc" type="text" value="" maxlength="50"> 
                </i>
                <i>
                    <span style="color:#fff;">英文名</span>
                    <input class="variable" type="text" value="" maxlength="20"> 
                </i>
                <i>
                 <span style="color:#fff;">类型</span>
                    <select class="zdcsSelect" onchange="changeVarType(event)">
                        <option value="2">常量</option>
                        <option value="3">模型</option>
                        <option value="1">基本类型</option>
                    </select>
                </i>  
                <i>
                    <span style="color:#fff;">取值</span>
                    <input type="text" value="" class="zdcsText" maxlength="20">
                </i>  
                <i>
                   <span style="color:#fff;">输入输出</span>
                   <select class="zdcsExport">
                        <option value="0">输入</option>
                        <option value="1">输出</option>
                    </select>
                </i>  
                <button class="deleteZdcs" style="height: 30px;background: #f56c6c;border: none;color: #fff;float: right;margin-right:5px;">删除</button>
            </div>
        `)
    })
    $('body').on('click','.deleteZdcs',(e) => {
        $(e.target).parent().remove()
    })
    //点击删除模型
    $('body').on('click','.lkr-list-del',(e) => {
        window.bigData.delmoduleId = $(e.target).attr('moduleId')
        $('#lkrFrameDel').show()
    })
  
    // 点击删除算法
    $('body').on('click','.lkr-list-delAlgorithm',(e) => {
        window.bigData.delAlgorithmId = $(e.target).data('id')
        $('#lkrAlgorithm').fadeToggle(500)
    })
    $('body').on('click','.inputFields',(e) => {
        window.filed.inputFieldsTarget = $(e.target)
        $('#fields').fadeToggle(500)
    })
     // 点击删除算法
     $('body').on('click','.fieldsList tr',(e) => {
        window.filed.fieldname = $(e.target).parent('tr').children('.fieldname').text();
        $(e.target).parent('tr').addClass("backcolor").siblings("tr").removeClass("backcolor"); 
    })

      // 点击选择算法信息
    $('body').on('click','.otherFormulaList tr',(e) => {
        window.filed.fieldname = $(e.target).parent('tr').children('.algorithmname').text();
        $(e.target).parent('tr').addClass("backcolor").siblings("tr").removeClass("backcolor");
    })
   //点击导入
   $('body').on('click','#Import',(e) => {
        $("#fileupload").show();
    })
    //点击导出
    $('body').on('click','#export',(e) => {
        if($("input[class='ruleCheckbox']:checked").length == 0){
            $('.noticeList').append(`<li>${getTime()}至少勾选一个规则！ </li>`)
            toastr.info(`至少勾选一个规则！` )
            $("#flex_props1_home").scrollTop($("#flex_props1_home")[0].scrollHeight);
            return false;
       }
       let id = $("input[name='exportGz']:checked").val();
       location.href= urlConfig.host+'/algorithmRule/saveAlgorithmRule2File?id=' + id
    })
       // 点击删除规则
    $('body').on('click','.lkr-list-delRule',(e) => {
        window.bigData.delRuleId = $(e.target).data('id')
        $('#lkrRule').fadeToggle(500)
    })

   // 点击编辑规则
    $('body').on('click','.lkr-list-ediRule',(e) => {
        window.bigData.editRuleId = $(e.target).attr('ruleId')
        let ruleid =  $(e.target).attr('ruleId')
        window.bigData.isExportId = ruleid
        window.Topology.Tools = {}
        $("#gzDiv").hide()
        $.ajax({
            url: urlConfig.host + '/algorithmRule/getAlgorithmRuleById',
            type:"get",
            data: {Id:ruleid},
            success(data) {
                if(data){
                    let ruleData = data.tableRole.coordinate
                    $('#ruleName').val(data.tableRole.rolename)
                    $('#ruleRemark').val(data.tableRole.des)
                    $("#currentGzName").text(data.tableRole.rolename);
                    $("#currentGzName").attr("title",data.tableRole.rolename)
                    $("#currentGzDes").text(data.tableRole.des);
                    $("#currentGzDes").attr("title",data.tableRole.des);
                    $("#bzMsg").val(data.tableRole.entrancenote);
                    $("#ruleDes").attr("data",data.tableRole.entrancenote)
                    if(window.canvasNowId == "canvas0"){
                        canvas.open(JSON.parse(ruleData))
                   }else{
                        window.frames[canvasNowId].contentWindow.canvas.open(JSON.parse(ruleData))
                   }
                   
                    window.Topology.isClickAction = []
                    window.Topology.tools = {}
                    window.bigData.ruleType = "edit"
                    window.bigData.editRuleId = data.tableRole.id;
                    responseActionDatas = data.interfaceRoleDataModels
                    if(data.operatorInterfaceDataModels){
                        data.operatorInterfaceDataModels.map(item=>{
                            let obj = {
                                isClick:true,
                                id:item.algorithmID
                            }
                            let saveList ={
                                id :item.algorithmID,
                                uuid:item.id,
                                name:item.interfaceName,
                                children:[]
                            }
                            item.tableInterfaceparametersList.map(index=>{
                                let hx ={
                                    id:index.parameterssources,
                                    uuid:index.id,
                                    algorithmid:item.algorithmID,
                                    varname:index.parametersname,
                                    vartype:"",
                                    valvalue:"",
                                    inorout:index.inorout,
                                    remark:""
                                }
                                saveList.children.push(hx)
                            })
                            window.Topology.isClickAction.push(obj)
                            window.Topology.tools[item.id] = saveList
                        })
                    }
                }
               
            }
        })
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
                $("#editDicTitle").text("算法详情")
                window.bigData.formulaType = "";
                $("#dicDiv").hide()
                $("#editDicYes").hide()
                if(data.tableAlgorithm.algorithmtype == 1){
                    $.ajax({
                        url: urlConfig.host + '/group/findAllGroupMessagesByType',
                        type:"get",
                        data: {type:2},
                        success(resss){
                            $("#group").empty()
                            resss.map(s=>{
                                $("#group").append(`<option value="${s.groupname}">${s.groupname}</option>`)
                            })
                            $("#group").val(data.tableAlgorithm.algorithmgroup)
                            $("#group").attr("disabled",true)
                        }
                    })
                    $("#editAuthor").val("");
                    $("#editDicName").val("");
                    $("#editDicDes").val("");
                    $("#company").val("");
                    $("#editAuthor").val(data.tableAlgorithm.algorithmauthor).attr({"disabled":"disabled"});
                    $("#editDicName").val(data.tableAlgorithm.algorithmname).attr({"disabled":"disabled"});
                    $("#editDicDes").val(data.tableAlgorithm.des).attr({"disabled":"disabled"});
                    $("#company").val(data.tableAlgorithm.remark).attr({"disabled":"disabled"});
                    $("#zdcsList").empty();
                    $("#addZdcs").hide();
                    $("#editDic").show();
                    data.tableFuncs.map(t=>{
                        $("#zdcsList").append(`
                             <div divId="${t.id}" class="zdcsDiv" style="margin-bottom: 15px">
                                <i style="margin-top: 5px">
                                    <span style="color:#fff;">中文名</span>
                                    <input class="zdcsCsmc" disabled type="text" value="${t.parametername}">
                                </i>
                                <i>
                                    <span style="color:#fff;">英文名</span>
                                    <input class="variable" type="text" value="${t.varname}">
                                </i>
                                <i>
                                    <span style="color:#fff;">类型</span>
                                    <select class="zdcsSelect" disabled>
                                        <option value="2">常量</option>
                                        <option value="3">模型</option>
                                        <option value="1">基本类型</option>
                                    </select>
                                </i>
                                <i>
                                    <span style="color:#fff;">取值</span>
                                    <input type="text" value="" class="zdcsText" disabled>
                                </i>
                                <i>
                                    <span style="color:#fff;">输入输出</span>
                                    <select class="zdcsExport" disabled>
                                        <option value="0">输入</option>
                                        <option value="1">输出</option>
                                    </select>
                                </i>
                            </div>
                        `)
                    })
                    for(var i=0;i<data.tableFuncs.length;i++){
                        $("#editDic .zdcsSelect").eq(i).val(data.tableFuncs[i].vartype)
                        $("#editDic .zdcsExport").eq(i).val(data.tableFuncs[i].inorout)
                        if(data.tableFuncs[i].vartype == 2){
                            $("#editDic .zdcsText").eq(i).val(data.tableFuncs[i].valvalue)
                        } else if(data.tableFuncs[i].vartype == 3){
                            $("#editDic .zdcsText").eq(i).val(data.tableFuncs[i].valvalue)
                            $("#editDic .zdcsText").eq(i).prev().text("模型名称")
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
                                <option>number</option>
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
                    $(".gsTitle").text("算法详情")
                    $('.Frame').show()
                    $("#gsName").val(data.tableAlgorithm.algorithmauthor).attr({"disabled":"disabled"});
                    $("#gsDes").val(data.tableAlgorithm.des).attr({"disabled":"disabled"});
                    $('#AlgorithmnameY').val(data.tableAlgorithm.algorithmname).attr({"bleAlgorithmid":data.tableAlgorithm.id,"tableAlmoduleid":data.tableAlgorithm.moduleid,"disabled":"disabled"})
                    $('#MathInput').attr("disabled","disabled");
                    $('#companyGs').attr("disabled","disabled");
                    $('#companyGs').val("");
                    $('#companyGs').val(data.tableAlgorithm.remark);
                    try{
                        ue.setContent('');
                        ue.execCommand('inserthtml', `<img class="kfformula" src=${data.tableAlgorithm.remark2} data-latex=${data.tableAlgorithm.algorithmfun}/>`);
                    }catch (e) {
                        console.log(e);
                    }
                    $.ajax({
                        url: urlConfig.host + '/group/findAllGroupMessagesByType',
                        type:"get",
                        data: {type:2},
                        success(resss){
                            $("#groupGs").empty()
                            resss.map(s=>{
                                $("#groupGs").append(`<option value="${s.groupname}">${s.groupname}</option>`)
                            })
                            $("#groupGs").val(data.tableAlgorithm.algorithmgroup)
                            $("#groupGs").attr("disabled",true)
                        }
                    })
                    $('.closeGsButton').hide();
                    window.bigData.editFormula = data.tableAlgorithm.algorithmfun;
                    window.bigData.formulaType = '';
                    if(data.tableFuncs.length>0){
                        let str =``
                        data.tableFuncs.map((item)=>{
                            str +=`<div class="MathJaxParam" formulaid="${item.id}" formulaModuleId="${item.algorithmid}">
                                        <div class="width-50">
                                            <span>中文名</span>
                                            <input type="text" readonly="readonly" value="${item.parametername}" class="MathJaxInputCs inputButton">
                                        </div>
                                        <div class="width-50">
                                            <span>英文名</span>
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

                }else if(data.tableAlgorithm.algorithmtype == 3){
                    $('.addButton').hide();
                    $(".closeLjButton").hide();
                    window.bigData.formulaType = '';
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

                    $(".Logic").show()
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
        $("#dicDiv").hide();
        let AlgorithmId= $(e.target).attr('algorithmid')
        showMsg(AlgorithmId)
    })
})


