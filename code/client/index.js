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
        }
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
        debugger
        window.bigData.delAlgorithmId = $(e.target).data('id')
        $('#lkrAlgorithm').fadeToggle(500)
    })
      // 点击编辑算子
    $('body').on('click','.lkr-list-editAlgorithm',(e) => {
        debugger
        window.bigData.editAlgorithmId = $(e.target).data('id')
        $.ajax({
            url:urlConfig.host+'/operatorMaintenance/getAlgorithmById',
            data:{
                algthId:window.bigData.editAlgorithmId
            },
            success: function(data) {
                console.log(data)
                let str =``
                if(data.tableFuncs.length>0){
                 
                    data.tableFuncs.map(item=>{
                        str += `<div class="MathJaxParam">
                                    <div class="width-30">
                                        <span>变量</span>
                                        <input type="text" :disabled="true" value="${item.varname}" class="MathJaxInput1">
                                    </div>
                                    <div class="width-30 width-select">
                                        <span>类型</span>
                                        <select id="selectId" value="${item.vartype}" onchange="gradeChange()" class="MathJaxInput2">
                                            <option value="">请选择</option>
                                            <option value="0">常量</option>
                                            <option value="1">数据项</option>
                                            <option value="2">其他模块计算结果</option>
                                        </select>
                                    </div>
                                    <div class="width-30"> 
                                            <span>取值</span>
                                            <input type="text" class="MathJaxInput3" value="${item.valvalue}">
                                    </div>
                                    <div class="width-100"> 
                                            <span>描述</span>
                                            <input type="text" class="MathJaxInput4" value="${item.remark}">
                                    </div>
                                </div>`
                    })
                }
                $('.Frame').append(`
                <div class="form-group">
                        <div>
                            <span class="form-span">算子名称</span>
                            <input class="form-input" type="text" id="algorithmname" value="${data.tableAlgorithm.algorithmname}">
                        </div>
                    <div>
                        <span class="form-span">公式编辑</span>
                        <input id="MathInput" onblur="Preview.Update()" type="text" value="${data.tableAlgorithm.algorithmfun}">
                    </div>
                        <div>
                            <span  class="form-span">公式预览</span>
                            <div id="MathPreview" ></div>
                            <div id="MathBuffer" style="font-size: 20px;visibility:hidden; position:absolute; top:0; left: 0;margin: 0 auto;"></div>
                        </div>
        
                </div>
                <div class="form-group MathJaxEdit">
                    ${str}
                </div>     
                
            </div>`)
                $('.Frame').fadeToggle(500)
            }
        })
       
    })
})

