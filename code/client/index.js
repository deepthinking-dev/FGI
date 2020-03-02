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
                        id:item[datas.id]+type,
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
})

