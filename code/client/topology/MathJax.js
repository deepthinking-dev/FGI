
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
        if(objS == "常量"){           
            $(e.target).parent('.width-select').nextAll('.isShow2').attr("style","display:none;");
            $(e.target).parent('.width-select').nextAll('.isShow3').attr("style","display:none;");
            $(e.target).parent('.width-select').nextAll('.isShow1').attr("style","display:block;");
        }
        if(objS == "数据项"){
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
        if(objS == "其他模块计算结果"){

            $(e.target).parent('.width-select').nextAll('.isShow1').attr("style","display:none;");
            $(e.target).parent('.width-select').nextAll('.isShow2').attr("style","display:none;");
            $(e.target).parent('.width-select').nextAll('.isShow3').attr("style","display:block;");
        }
    })
    //提交算子信息及公式编辑
    function ConfirmFrame(){
        
        let tableAl ={
            algorithmauthor:"111",
            algorithmfun:$('#MathInput').val(),
            algorithmname:$('#algorithmname').val(),
            algorithmtype:0,
            des:"",
            ispublic:0,
            moduleid:window.bigData.editmoduleId,
            remark:"",
            tno:0
        }
        let tableF=[]
        let tableModule={
            moduleid:window.bigData.editmoduleId,
            remark:"",
            username:""
        }
        let MathJaxParamLength = $('.MathJaxParam')
        if(MathJaxParamLength.length > 0){
            for(let i=0;i<MathJaxParamLength.length;i++){
                let obj ={}
                if(window.bigData.formulaType = 'edit'){
                    obj.id = $('.MathJaxInput2').attr('id')
                }
                obj.moduleid = window.bigData.editmoduleId
                obj.remark = MathJaxParamLength.eq(i).find('.MathJaxInput4').val()
                obj.valvalue = MathJaxParamLength.eq(i).find('.MathJaxInput3').val()
                obj.varname = MathJaxParamLength.eq(i).find('.MathJaxInput1').val()
                obj.vartype = MathJaxParamLength.eq(i).find('#selectId option:selected').text()
                tableF.push(obj)
            }
            
        }
        if(window.bigData.formulaType = 'edit'){
            tableAl.id =  $('#algorithmname').attr("id")
            tableModule.id=$('#algorithmname').attr("id")
        }
        let param = {
            tableAlgorithm:tableAl,
            tableFuncs:tableF,
            tableModuleuserrelation:tableModule
        }
        console.log(param)
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
    //选择字段信息确定按钮
    function ConfirmFields(){
        $(window.filed.inputFieldsTarget).attr("value",window.filed.fieldname)
        $('#fields').fadeToggle(500)
    }
