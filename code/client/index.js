
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
      // 点击编辑算子
    $('body').on('click','.lkr-list-editAlgorithm',(e) => {
        window.bigData.editAlgorithmId = $(e.target).data('id');
        let algorithmtype = $(e.target).attr('data-type');
        window.bigData.formulaType = 'edit'
        window.bigData.formulaModuleId = $(e.target).attr('data-moduleid');
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

    })
    //点击数据项

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
    $('body').on('dblclick','.dbclickAlgorithm',(e) => {
        if($('#dictordySpan').hasClass('addDicClose')) {
            $(".addDicClose").attr("class","editDicClose");
        }
        $("#dataModulePage").hide();
        $("#lkrFrame").hide();
        let AlgorithmId= $(e.target).attr('algorithmid')
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
    })

    $('body').on('click','.lkr-list-ediRule',(e) =>{
        debugger
        var data ={"nodes":[{"id":"af7cfc57","name":"circle","tags":[],"rect":{"x":661,"y":214,"width":100,"height":100,"center":{"x":711,"y":264},"ex":761,"ey":314},"lineWidth":1,"rotate":0,"offsetRotate":0,"globalAlpha":1,"dash":0,"strokeStyle":"#222","fillStyle":"","font":{"color":"#222","fontFamily":"\"Hiragino Sans GB\", \"Microsoft YaHei\", \"Helvetica Neue\", Helvetica, Arial","fontSize":12,"lineHeight":1.5,"fontStyle":"normal","fontWeight":"normal","textAlign":"center","textBaseline":"middle","background":""},"animateStart":0,"animateCycleIndex":0,"lineDashOffset":0,"text":"圆","textMaxLine":1,"textOffsetX":0,"textOffsetY":0,"animateType":"","data":"","zRotate":0,"anchors":[{"x":661,"y":264,"direction":4},{"x":711,"y":214,"direction":1},{"x":761,"y":264,"direction":2},{"x":711,"y":314,"direction":3},{"x":715.357787137383,"y":313.8097349045873,"direction":0,"hidden":true},{"x":719.6824088833465,"y":313.2403876506104,"direction":0,"hidden":true},{"x":723.940952255126,"y":312.2962913144534,"direction":0,"hidden":true},{"x":728.1010071662835,"y":310.98463103929544,"direction":0,"hidden":true},{"x":732.130913087035,"y":309.3153893518325,"direction":0,"hidden":true},{"x":736,"y":307.30127018922195,"direction":0,"hidden":true},{"x":739.6788218175523,"y":304.9576022144496,"direction":0,"hidden":true},{"x":743.1393804843269,"y":302.3022221559489,"direction":0,"hidden":true},{"x":746.3553390593273,"y":299.3553390593274,"direction":0,"hidden":true},{"x":749.3022221559489,"y":296.139380484327,"direction":0,"hidden":true},{"x":751.9576022144496,"y":292.6788218175523,"direction":0,"hidden":true},{"x":754.3012701892219,"y":289,"direction":0,"hidden":true},{"x":756.3153893518324,"y":285.13091308703497,"direction":0,"hidden":true},{"x":757.9846310392954,"y":281.10100716628347,"direction":0,"hidden":true},{"x":759.2962913144534,"y":276.94095225512604,"direction":0,"hidden":true},{"x":760.2403876506104,"y":272.6824088833465,"direction":0,"hidden":true},{"x":760.8097349045872,"y":268.3577871373829,"direction":0,"hidden":true},{"x":760.8097349045872,"y":259.6422128626171,"direction":1,"hidden":true},{"x":760.2403876506104,"y":255.3175911166535,"direction":1,"hidden":true},{"x":759.2962913144534,"y":251.05904774487396,"direction":1,"hidden":true},{"x":757.9846310392954,"y":246.89899283371656,"direction":1,"hidden":true},{"x":756.3153893518326,"y":242.86908691296503,"direction":1,"hidden":true},{"x":754.3012701892219,"y":239,"direction":1,"hidden":true},{"x":751.9576022144496,"y":235.3211781824477,"direction":1,"hidden":true},{"x":749.3022221559489,"y":231.86061951567302,"direction":1,"hidden":true},{"x":746.3553390593274,"y":228.64466094067262,"direction":1,"hidden":true},{"x":743.1393804843269,"y":225.6977778440511,"direction":1,"hidden":true},{"x":739.6788218175523,"y":223.0423977855504,"direction":1,"hidden":true},{"x":736,"y":220.69872981077805,"direction":1,"hidden":true},{"x":732.130913087035,"y":218.6846106481675,"direction":1,"hidden":true},{"x":728.1010071662835,"y":217.0153689607046,"direction":1,"hidden":true},{"x":723.940952255126,"y":215.7037086855466,"direction":1,"hidden":true},{"x":719.6824088833465,"y":214.7596123493896,"direction":1,"hidden":true},{"x":715.357787137383,"y":214.1902650954127,"direction":1,"hidden":true},{"x":706.6422128626172,"y":214.1902650954127,"direction":2,"hidden":true},{"x":702.3175911166535,"y":214.7596123493896,"direction":2,"hidden":true},{"x":698.059047744874,"y":215.7037086855466,"direction":2,"hidden":true},{"x":693.8989928337165,"y":217.01536896070456,"direction":2,"hidden":true},{"x":689.869086912965,"y":218.6846106481675,"direction":2,"hidden":true},{"x":686,"y":220.69872981077808,"direction":2,"hidden":true},{"x":682.3211781824477,"y":223.0423977855504,"direction":2,"hidden":true},{"x":678.8606195156731,"y":225.6977778440511,"direction":2,"hidden":true},{"x":675.6446609406727,"y":228.64466094067262,"direction":2,"hidden":true},{"x":672.6977778440511,"y":231.86061951567302,"direction":2,"hidden":true},{"x":670.0423977855504,"y":235.32117818244768,"direction":2,"hidden":true},{"x":667.6987298107781,"y":238.99999999999997,"direction":2,"hidden":true},{"x":665.6846106481674,"y":242.86908691296503,"direction":2,"hidden":true},{"x":664.0153689607046,"y":246.89899283371653,"direction":2,"hidden":true},{"x":662.7037086855466,"y":251.05904774487396,"direction":2,"hidden":true},{"x":661.7596123493896,"y":255.3175911166535,"direction":2,"hidden":true},{"x":661.1902650954128,"y":259.6422128626171,"direction":2,"hidden":true},{"x":661.1902650954128,"y":268.3577871373829,"direction":3,"hidden":true},{"x":661.7596123493896,"y":272.6824088833465,"direction":3,"hidden":true},{"x":662.7037086855465,"y":276.94095225512604,"direction":3,"hidden":true},{"x":664.0153689607046,"y":281.10100716628347,"direction":3,"hidden":true},{"x":665.6846106481674,"y":285.13091308703497,"direction":3,"hidden":true},{"x":667.6987298107781,"y":289,"direction":3,"hidden":true},{"x":670.0423977855504,"y":292.6788218175523,"direction":3,"hidden":true},{"x":672.6977778440511,"y":296.139380484327,"direction":3,"hidden":true},{"x":675.6446609406726,"y":299.3553390593274,"direction":3,"hidden":true},{"x":678.8606195156731,"y":302.30222215594887,"direction":3,"hidden":true},{"x":682.3211781824476,"y":304.9576022144496,"direction":3,"hidden":true},{"x":686,"y":307.3012701892219,"direction":3,"hidden":true},{"x":689.869086912965,"y":309.3153893518325,"direction":3,"hidden":true},{"x":693.8989928337165,"y":310.98463103929544,"direction":3,"hidden":true},{"x":698.059047744874,"y":312.2962913144534,"direction":3,"hidden":true},{"x":702.3175911166535,"y":313.2403876506104,"direction":3,"hidden":true},{"x":706.642212862617,"y":313.8097349045873,"direction":3,"hidden":true}],"rotatedAnchors":[{"x":661,"y":264,"direction":4},{"x":711,"y":214,"direction":1},{"x":761,"y":264,"direction":2},{"x":711,"y":314,"direction":3},{"x":715.357787137383,"y":313.8097349045873,"direction":0,"hidden":true},{"x":719.6824088833465,"y":313.2403876506104,"direction":0,"hidden":true},{"x":723.940952255126,"y":312.2962913144534,"direction":0,"hidden":true},{"x":728.1010071662835,"y":310.98463103929544,"direction":0,"hidden":true},{"x":732.130913087035,"y":309.3153893518325,"direction":0,"hidden":true},{"x":736,"y":307.30127018922195,"direction":0,"hidden":true},{"x":739.6788218175523,"y":304.9576022144496,"direction":0,"hidden":true},{"x":743.1393804843269,"y":302.3022221559489,"direction":0,"hidden":true},{"x":746.3553390593273,"y":299.3553390593274,"direction":0,"hidden":true},{"x":749.3022221559489,"y":296.139380484327,"direction":0,"hidden":true},{"x":751.9576022144496,"y":292.6788218175523,"direction":0,"hidden":true},{"x":754.3012701892219,"y":289,"direction":0,"hidden":true},{"x":756.3153893518324,"y":285.13091308703497,"direction":0,"hidden":true},{"x":757.9846310392954,"y":281.10100716628347,"direction":0,"hidden":true},{"x":759.2962913144534,"y":276.94095225512604,"direction":0,"hidden":true},{"x":760.2403876506104,"y":272.6824088833465,"direction":0,"hidden":true},{"x":760.8097349045872,"y":268.3577871373829,"direction":0,"hidden":true},{"x":760.8097349045872,"y":259.6422128626171,"direction":1,"hidden":true},{"x":760.2403876506104,"y":255.3175911166535,"direction":1,"hidden":true},{"x":759.2962913144534,"y":251.05904774487396,"direction":1,"hidden":true},{"x":757.9846310392954,"y":246.89899283371656,"direction":1,"hidden":true},{"x":756.3153893518326,"y":242.86908691296503,"direction":1,"hidden":true},{"x":754.3012701892219,"y":239,"direction":1,"hidden":true},{"x":751.9576022144496,"y":235.3211781824477,"direction":1,"hidden":true},{"x":749.3022221559489,"y":231.86061951567302,"direction":1,"hidden":true},{"x":746.3553390593274,"y":228.64466094067262,"direction":1,"hidden":true},{"x":743.1393804843269,"y":225.6977778440511,"direction":1,"hidden":true},{"x":739.6788218175523,"y":223.0423977855504,"direction":1,"hidden":true},{"x":736,"y":220.69872981077805,"direction":1,"hidden":true},{"x":732.130913087035,"y":218.6846106481675,"direction":1,"hidden":true},{"x":728.1010071662835,"y":217.0153689607046,"direction":1,"hidden":true},{"x":723.940952255126,"y":215.7037086855466,"direction":1,"hidden":true},{"x":719.6824088833465,"y":214.7596123493896,"direction":1,"hidden":true},{"x":715.357787137383,"y":214.1902650954127,"direction":1,"hidden":true},{"x":706.6422128626172,"y":214.1902650954127,"direction":2,"hidden":true},{"x":702.3175911166535,"y":214.7596123493896,"direction":2,"hidden":true},{"x":698.059047744874,"y":215.7037086855466,"direction":2,"hidden":true},{"x":693.8989928337165,"y":217.01536896070456,"direction":2,"hidden":true},{"x":689.869086912965,"y":218.6846106481675,"direction":2,"hidden":true},{"x":686,"y":220.69872981077808,"direction":2,"hidden":true},{"x":682.3211781824477,"y":223.0423977855504,"direction":2,"hidden":true},{"x":678.8606195156731,"y":225.6977778440511,"direction":2,"hidden":true},{"x":675.6446609406727,"y":228.64466094067262,"direction":2,"hidden":true},{"x":672.6977778440511,"y":231.86061951567302,"direction":2,"hidden":true},{"x":670.0423977855504,"y":235.32117818244768,"direction":2,"hidden":true},{"x":667.6987298107781,"y":238.99999999999997,"direction":2,"hidden":true},{"x":665.6846106481674,"y":242.86908691296503,"direction":2,"hidden":true},{"x":664.0153689607046,"y":246.89899283371653,"direction":2,"hidden":true},{"x":662.7037086855466,"y":251.05904774487396,"direction":2,"hidden":true},{"x":661.7596123493896,"y":255.3175911166535,"direction":2,"hidden":true},{"x":661.1902650954128,"y":259.6422128626171,"direction":2,"hidden":true},{"x":661.1902650954128,"y":268.3577871373829,"direction":3,"hidden":true},{"x":661.7596123493896,"y":272.6824088833465,"direction":3,"hidden":true},{"x":662.7037086855465,"y":276.94095225512604,"direction":3,"hidden":true},{"x":664.0153689607046,"y":281.10100716628347,"direction":3,"hidden":true},{"x":665.6846106481674,"y":285.13091308703497,"direction":3,"hidden":true},{"x":667.6987298107781,"y":289,"direction":3,"hidden":true},{"x":670.0423977855504,"y":292.6788218175523,"direction":3,"hidden":true},{"x":672.6977778440511,"y":296.139380484327,"direction":3,"hidden":true},{"x":675.6446609406726,"y":299.3553390593274,"direction":3,"hidden":true},{"x":678.8606195156731,"y":302.30222215594887,"direction":3,"hidden":true},{"x":682.3211781824476,"y":304.9576022144496,"direction":3,"hidden":true},{"x":686,"y":307.3012701892219,"direction":3,"hidden":true},{"x":689.869086912965,"y":309.3153893518325,"direction":3,"hidden":true},{"x":693.8989928337165,"y":310.98463103929544,"direction":3,"hidden":true},{"x":698.059047744874,"y":312.2962913144534,"direction":3,"hidden":true},{"x":702.3175911166535,"y":313.2403876506104,"direction":3,"hidden":true},{"x":706.642212862617,"y":313.8097349045873,"direction":3,"hidden":true}],"animateDuration":0,"animateFrames":[],"borderRadius":0,"iconSize":null,"imageAlign":"center","bkType":0,"gradientAngle":0,"gradientRadius":0.01,"paddingTop":0,"paddingBottom":0,"paddingLeft":0,"paddingRight":0,"paddingLeftNum":0,"paddingRightNum":0,"paddingTopNum":0,"paddingBottomNum":0,"textRect":{"x":686,"y":275.6666666666667,"width":50,"height":28.333333333333336,"center":{"x":711,"y":289.83333333333337},"ex":736,"ey":304},"fullTextRect":{"x":675.2857142857143,"y":228.28571428571428,"width":71.42857142857143,"height":71.42857142857143,"center":{"x":711,"y":264},"ex":746.7142857142858,"ey":299.7142857142857},"iconRect":{"x":686,"y":224,"width":50,"height":50,"center":{"x":711,"y":249},"ex":736,"ey":274}},{"id":"3844972a","name":"pentagram","tags":[],"rect":{"x":465,"y":181,"width":100,"height":100,"center":{"x":515,"y":231},"ex":565,"ey":281},"lineWidth":1,"rotate":0,"offsetRotate":0,"globalAlpha":1,"dash":0,"strokeStyle":"#222","fillStyle":"","font":{"color":"#222","fontFamily":"\"Hiragino Sans GB\", \"Microsoft YaHei\", \"Helvetica Neue\", Helvetica, Arial","fontSize":12,"lineHeight":1.5,"fontStyle":"normal","fontWeight":"normal","textAlign":"center","textBaseline":"middle","background":""},"animateStart":0,"animateCycleIndex":0,"lineDashOffset":0,"text":"五角星","textOffsetX":0,"textOffsetY":0,"animateType":"","data":"","zRotate":0,"anchors":[{"x":562.5528258147576,"y":215.54915028125262,"direction":2},{"x":515,"y":181,"direction":1},{"x":467.4471741852423,"y":215.54915028125262,"direction":4},{"x":485.61073738537635,"y":271.45084971874735,"direction":3},{"x":544.3892626146237,"y":271.45084971874735,"direction":3}],"rotatedAnchors":[{"x":562.5528258147576,"y":215.54915028125262,"direction":2},{"x":515,"y":181,"direction":1},{"x":467.4471741852423,"y":215.54915028125262,"direction":4},{"x":485.61073738537635,"y":271.45084971874735,"direction":3},{"x":544.3892626146237,"y":271.45084971874735,"direction":3}],"animateDuration":0,"animateFrames":[],"borderRadius":0,"iconSize":null,"imageAlign":"center","bkType":0,"gradientAngle":0,"gradientRadius":0.01,"paddingTop":0,"paddingBottom":0,"paddingLeft":0,"paddingRight":0,"paddingLeftNum":0,"paddingRightNum":0,"paddingTopNum":0,"paddingBottomNum":0,"fullTextRect":{"x":495,"y":211,"width":40,"height":40,"center":{"x":515,"y":231},"ex":535,"ey":251},"textRect":{"x":495,"y":211,"width":40,"height":40,"center":{"x":515,"y":231},"ex":535,"ey":251},"iconRect":{"x":0,"y":0,"width":0,"height":0,"center":{"x":0,"y":0},"ex":0,"ey":0}}],"lines":[],"lineName":"curve","fromArrowType":"","toArrowType":"triangleSolid","scale":1,"locked":0};
        // var canvas = new Le5leTopology.Topology('topo-canvas', { extDpiRatio: 0 });
        canvas.open(data)
        // $.ajax({
        //     dataType: "json",
        //     url:"./topology/le5le.topology.json",
        //     contentType: "application/json;charset=UTF-8",
        //     success: function(data) {
        //         canvas.open(data);
        //     }
        // })
    })

})


