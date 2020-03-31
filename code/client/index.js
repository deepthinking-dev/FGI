
$(function(){
    $("#selectAll").click((e) => {
        $("input:checkbox[name='item']").each((i,v) => {
            $(v).prop('checked',$("#selectAll").prop('checked'))
        })
    })
    $('body').on('click','#flex_tools>.lkr-pic_list .active-taps',(e) => {
        if($(e.target).html() == '模板管理'){
            $("#mouldPage").show()
            $("#algorithmPage").hide()
        }else if($(e.target).html() == '算子管理'){
            $("#algorithmPage").show()
            getAllData('/module/GetAllModule',{id:'id',name:'modulename'},'模板','')
            $("#mouldPage").hide()
        }else if($(e.target).html() == '字典'){

        }
    })
    $('body').on('click','.dicDivClose',(e) => {
        $("#dicDiv").hide();
    })
    $('body').on('click','.editDicClose',(e) => {
        $("#editDic").hide();
    })
    $('body').on('click','#dicYes',(e) => {
        $("#dicDiv").hide();
    })
    $('body').on('click','#addDic',(e) => {
        $("#editDic").show();
        $("#editDicTitle").text("新增字典");
        $("#zdcsList").html(``)
        $("#editDicYes").attr("editId","")
        $("#editDicName").val("");
        $("#editDicDes").val("");
    })
    $('body').on('click','.dicEdit',(e) => {
        $("#editDic").show();
        $("#editDicTitle").text("修改字典")
    })
    $('body').on('click','#editDicYes',(e) => {
        let name = $("#editDicName").val();
        let des =  $("#editDicDes").val();
        let dataAll = {
            "tableAlgorithm": {
                "algorithmauthor": "",
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
        var tables = [];
        $(".zdcsDiv").each((i,s)=>{
            let obj =   {
                "algorithmid": 0,
                "id": 0,
                "remark": ""
            };
            obj.varname = $(s).find('.zdcsCsmc').val() //参数名称
            obj.inorout = $(s).find('.zdcsExport').val()//输入输出
            obj.vartype = $(s).find('.zdcsSelect').val()//变量类型
            if(obj.vartype == "2" || obj.vartype == "3"){
                obj.valvalue = $(s).find('.zdcsText').val()//变量类型值
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
                    dictionary()
                }
            })
        } else {
            let jbxx = {
                "algorithmauthor": "",
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
                    dictionary()
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
                }
            })
        }
    })
    $('body').on('click','#addZdcs',(e) => {
        $("#zdcsList").append(`
             <div class="zdcsDiv" style="margin: 5px 0;border: 1px solid #fff">
                <p style="margin-top: 5px">
                    <span style="color:#fff;margin-left: 30px;;margin-right: 20px;">参数名称</span><input class="zdcsCsmc" type="text" value="">
                </p>
                <p>
                    <span style="color:#fff;margin-left: 30px;;margin-right: 20px">类型</span>
                    <select class="zdcsSelect">
                        <option value="2">常量</option>
                        <option value="3">对象</option>
                        <option value="1">基本类型</option>
                    </select>
                    <input type="text" value="" class="zdcsText">
                </p>
                <p>
                    <span style="color:#fff;margin-left: 30px;;margin-right: 20px">输入输出</span>
                    <select class="zdcsExport">
                        <option value="0">输入</option>
                        <option value="1">输出</option>
                    </select>
                    <input class="deleteZdcs" style="float: right;margin-right: 20px;height: 24px;line-height: 24px;background: #f56c6c;border: none;color: #fff;" type="button" value="删除">
                </p>
            </div>
        `)
    })
    $('body').on('change','.zdcsSelect',(e) => {
        if(e.target.value == "2" || e.target.value == "3"){
            $(e.target).next().remove();
            $(e.target).parent().append(`<input type="text" value=""  class="zdcsText">`)
        } else {
            $(e.target).next().remove();
            $(e.target).parent().append(`
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

    $('body').on('click','#getAllMb',(e) => {
        getAllData('/module/GetAllModule',{id:'id',name:'modulename'},'模板','')
    })
    $('body').on('click','#getAllSz',(e) => {
        getAllData('/operatorMaintenance/getAllAlgorithm',{id:'id',name:'algorithmname'},'算子',{username:null})
    })
    function getAllData(url,datas,type,param){
        $.ajax({
            url:urlConfig.host+url,
            data:param,
            success: function(data) {
                $(".left-list").remove()
                data.map(item => {
                    window.addAlgorithm({
                        name: 'rectangle',
                        icon: 'icon-rectangle',
                        id:item[datas.id],
                        type:type,
                        data: {
                            id:item[datas.id]+type,
                            text: item[datas.name],
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
        })
    }
    $('body').on('click','#mouldPage .active-taps',(e) => {
        $('.active-taps').each((i,v) => {
            $(v).css('color','#ffffff')
        })
        window.bigData.actionTab = $(e.target).data('name')
        $(e.target).css('color','rgb(255, 217, 0)')
        window.onRender()
        $.ajax({
            url:urlConfig.host+'/module/GetModuleByGroupName',
            data:{moduleGroupName:$(e.target).data('name')},
            success: function(data) {
                $(".left-list").remove()
                data.map(item => {
                    window.addModel({
                        name: 'rectangle',
                        icon: 'icon-rectangle',
                        id:item.id,
                        data: {
                            id:item.id,
                            text: item.modulename,
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
        })
    })
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
        window.bigData.formulaType = 'edit'
        $.ajax({
            url:urlConfig.host+'/operatorMaintenance/getAlgorithmById',
            data:{
                algthId:window.bigData.editAlgorithmId
            },
            success: function(data) {
                console.log(data)
                $('#algorithmname').attr({"value":data.tableAlgorithm.algorithmname,"id":data.tableAlgorithm.id});
                if(data.tableFuncs.length>0){
                    window.bigData.editFormula = data.tableAlgorithm.algorithmfun
                    window.changeBds(data.tableAlgorithm.algorithmfun);
                    let str =``
                    data.tableFuncs.map((item)=>{
                        str +=`<div class="MathJaxParam">
                                    <div class="width-30">
                                        <span>变量</span>
                                        <input type="text" readonly="readonly" value="${item.varname}" class="MathJaxInput1">
                                    </div>
                                    <div class="width-30 width-select">
                                        <span>类型</span>
                                        <select id="${item.id}" class="MathJaxInput2">
                                            <option value="">请选择</option>
                                            <option value="常量">常量</option>
                                            <option value="数据项">数据项</option>   
                                            <option value="其他模块计算结果">其他模块计算结果</option>
                                        </select>
                                    </div>
                                    <div class="width-30 isShow1"> 
                                            <span>取值</span >
                                            <input type="text" class="MathJaxInput3" value="${item.valvalue}">
                                    </div>
                                    <div class="width-30 isShow2"> 
                                        <span>数据项</span>
                                        <input type="text" class="MathJaxInput3 inputFields"  onclick="getFilds"  readonly="readonly">
                                    </div>
                                    <div class="width-30 isShow3"> 
                                            <span>其他公式</span>
                                            <input type="text" class="MathJaxInput3"  readonly="readonly">
                                    </div>
                                    <div class="width-100"> 
                                            <span>描述</span>
                                            <input type="text" class="MathJaxInput4" value="${item.remark}">
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
                            }
                            if(vType == "数据项"){
                                $('.MathJaxParam').eq(k).find('.isShow1').attr("style","display:none;");
                                $('.MathJaxParam').eq(k).find('.isShow3').attr("style","display:none;");
                                $('.MathJaxParam').eq(k).find(".isShow2").attr("style","display:block;");
                            }
                            if(vType == "其他模块计算结果"){
                                $('.MathJaxParam').eq(k).find('.isShow1').attr("style","display:none;");
                                $('.MathJaxParam').eq(k).find('.isShow2').attr("style","display:none;");
                                $('.MathJaxParam').eq(k).find(".isShow3").attr("style","display:block;");
                            }
                           }                     
                       }                      
                   }
                }
                
                $('.Frame').fadeToggle(500)
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
 
})


