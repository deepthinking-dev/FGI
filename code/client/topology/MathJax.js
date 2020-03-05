
   
   $('#MathJaxOpen').on('click',function(){
        $(".Frame").attr("style","display:block;");
    })
    $('#LogicOpen').on('click',function(){
        $(".Logic").attr("style","display:block;");
    })
    function FrameClose(){
        $(".Frame").attr("style","display:none;");
    }
    function LogicClose(){
        $(".Logic").attr("style","display:none;");
    }
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
    function gradeChange(){

        console.log(window.bigData.editmoduleId)
        var objS = document.getElementById("selectId").value
        if(objS == "1"){
            $.ajax({
            url:urlConfig.host+'/module/getModuleColumns',
            data:{moduleId:window.bigData.editmoduleId},
            success: function(data) {
               
            }
        })
        }
    }
    //提交算子信息及公式编辑
    function ConfirmFrame(){
        let tableAl ={
            algorithmauthor:"111",
            algorithmfun:$('#MathInput').val(),
            algorithmname:$('#algorithmname').val(),
            algorithmtype:0,
            des:"",
            ispublic:0,
            moduleid:9,
            remark:"",
            tno:0
        }
        let tableF=[]
        let tableModule={
            moduleid:9,
            remark:"",
            username:""
        }
        let MathJaxParamLength = $('.MathJaxParam')
        if(MathJaxParamLength.length > 0){
            for(let i=0;i<MathJaxParamLength.length;i++){
                let obj ={}
                obj.moduleid = 9
                obj.remark = MathJaxParamLength.eq(i).find('.MathJaxInput4').val()
                obj.valvalue = MathJaxParamLength.eq(i).find('.MathJaxInput3').val()
                obj.varname = MathJaxParamLength.eq(i).find('.MathJaxInput1').val()
                obj.vartype = MathJaxParamLength.eq(i).find('#selectId option:selected').text()
                tableF.push(obj)
            }
            
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
               
            }
        }) 
    }
