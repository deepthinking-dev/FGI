/**
 * Created by johnnyhhj on 19/11/22.
 */
//画布对象
var canvas = null;
//选中多个节点对象
var selNodes = null;
//选中节点的详情
var selected = null;
//是否锁对象
var locked = false;
//右键菜单对应标记是否可用
var ww = null
var times = 0;
var Topology = {
    data: {},
    load: false,
    lineStyle: {curve: 0, polyline: 1, line: 2},
    startLineStyle: {
        default: 0,
        triangleSolid: 1,
        triangle: 2,
        diamondSolid: 3,
        diamond: 4,
        circleSolid: 5,
        circle: 6,
        line: 7,
        lineUp: 8,
        lineDown: 9
    },
    lineTypeStyle: {curve: 0, polyline: 1, line: 2},
    tools: {},
    saveNode:[],
    addInlist:[],
    addOutlist:[],
    banAdd:true,
    dblclickNode:{},
    isClickAction:[],
    moveNodesList:[],
    // 对象的最初入口
    init: function () {
        var self = this;
        //绑定事件
        self.bindEvent();
        self.initCanvas();
        //初始化颜色选择器
        $('.colorpicker').colorpicker();
    },
    // 保存画布数据
    saveKnowledgeMap: function (id) {
        var self = this;
        ww = JSON.stringify(canvas.data)
        parent.$('.noticeList').append(`<li>${parent.getTime()}"需要保存的json：\n" ${JSON.stringify(canvas.data)}</li>`)
        parent.toastr.info(`需要保存的json：\n" ${JSON.stringify(canvas.data)}` )
        $("#flex_props1_home").scrollTop($("#flex_props1_home")[0].scrollHeight);
    },
    // 绑定事件
    bindEvent: function () {
        var self = this;
        // 监听键盘快捷键
        $(document).keydown(function (e) {
            //撤销 ctrl+z
            if (e.ctrlKey && e.which === 90) {
                canvas.undo();
            }
            //恢复 ctrl+shift+z
            if (e.ctrlKey && e.shiftKey === 90 && e.which === 90) {
                canvas.redo();
            }
            //剪切 ctrl+x
            if (e.ctrlKey && e.which === 88) {
                canvas.cut();
            }
            //复制 ctrl+c
            if (e.ctrlKey && e.which === 67) {
                canvas.copy();
            }
            //粘贴 ctrl+v
            if (e.ctrlKey && e.which === 86) {
                canvas.parse();
            }
        });
        // 隐藏显示
        $("body").click(function (event) {
            if(window.canvasNowId =="canvas0"){
                $("#canvas_menus").css("display", "none");
            }else{
                parent.$("#canvas_menus").css("display", "none");
            }
           
        });
        // 隐藏显示
        $(document).click(function (e) {
            //起始箭头隐藏下拉
            if (!self.compareId(e, "start_line_head")) {
                $("#start_line_dropdown").addClass("hidden");
            }
            //终点箭头隐藏下拉
            if (!self.compareId(e, "end_line_head")) {
                $("#end_line_dropdown").addClass("hidden");
            }
            //连接类型更改隐藏下拉
            if (!self.compareId(e, "line_style_head")) {
                $("#line_style_dropdown").addClass("hidden");
            }
            //连接样式更改隐藏下拉
            if (!self.compareId(e, "line_type_head")) {
                $("#line_type_dropdown").addClass("hidden");
            }
        });
        // 起始箭头显示下拉
        $("#start_line_head").click(function () {
            $("#start_line_dropdown").removeClass("hidden");
        });
        // 终点箭头显示下拉
        $("#end_line_head").click(function () {
            $("#end_line_dropdown").removeClass("hidden");
        });
        // 连接类型更改
        $("#line_style_head").click(function () {
            $("#line_style_dropdown").removeClass("hidden");
        });
        // 连接样式更改
        $("#line_type_head").click(function () {
            $("#line_type_dropdown").removeClass("hidden");
        });
        $(document).on("click", "#down_png", function () {
            self.down_png();
        });
        // 画布右键属性
        $("#flex_canvas").bind("contextmenu", function (e) {
            //设置右键菜单
            if (selNodes != null) {
                $("#showRk").show();
                var selectId = selNodes[0].data.sid;
                if(window.canvasNowId == "canvas0"){
                    window.selectId = selectId;
                }else{
                    parent.$('#'+window.top.canvasNowId)[0].contentWindow.selectId = selectId;
                    parent.$('#'+window.top.canvasNowId)[0].contentWindow.selNodes = selNodes;
                }
               
                //置顶
                $("#menu_top").removeClass("menu-a-disabled");
                $("#menu_top").addClass("menu-a");
                //置底
                $("#menu_bottom").removeClass("menu-a-disabled");
                $("#menu_bottom").addClass("menu-a");
                //组合
                if (selNodes.length > 1 || selNodes[0].name !== "combine") {
                    if (selNodes.length < 2) {
                        $("#menu_combine").addClass("menu-a-disabled");
                        $("#menu_combine").removeClass("menu-a");
                    } else {
                        $("#menu_combine").removeClass("menu-a-disabled");
                        $("#menu_combine").addClass("menu-a");
                    }
                    $("#menu_combine").css("display", "block");
                    $("#menu_unCombine").css("display", "none");
                }
                //取消组合
                if (selNodes.length === 1 && selNodes[0].name == "combine") {
                    $("#menu_unCombine").removeClass("menu-a-disabled");
                    $("#menu_unCombine").addClass("menu-a");
                    $("#menu_combine").css("display", "none");
                    $("#menu_unCombine").css("display", "block");
                }
                //锁定
                if (locked) {
                    $("#menu_lock").html("解锁");
                } else {
                    $("#menu_lock").html("锁定");
                }
                $("#menu_lock").removeClass("menu-a-disabled");
                $("#menu_lock").addClass("menu-a");
            } else {
                $("#showRk").hide();
                //置顶
                $("#menu_top").addClass("menu-a-disabled");
                $("#menu_top").removeClass("menu-a");
                //置底
                $("#menu_bottom").addClass("menu-a-disabled");
                $("#menu_bottom").removeClass("menu-a");
                //组合
                $("#menu_combine").addClass("menu-a-disabled");
                $("#menu_combine").removeClass("menu-a");
                //取消组合
                $("#menu_unCombine").addClass("menu-a-disabled");
                $("#menu_unCombine").removeClass("menu-a");
                //锁定
                $("#menu_lock").addClass("menu-a-disabled");
                $("#menu_lock").removeClass("menu-a");
            }
            window.lineDiv = false;
            //显示右键菜单
            if(window.canvasNowId == "canvas0"){
                $("#canvas_menus").css({
                    "left": document.body.scrollLeft + event.clientX, "top":
                        document.body.scrollTop + event.clientY
                }).show();
            }else{
                parent.$("#canvas_menus").css({
                    "left": document.body.scrollLeft + event.clientX, "top":
                        document.body.scrollTop + event.clientY
                }).show();
            }
           
            return false;
        });
        // 保存画布数据
        $(document).on("click", "#canvas_save", function () {
            self.saveKnowledgeMap();
        });
        // -------查询开始-------------
        $(document).on('click', '#search_knowledge_btn', function () {

            var table = $('#table_knowledge').DataTable();
            table.page('first').draw(false);
            // 点击查询的时候，数据表格数据重新加载。在重新加载的时候，从#query_form小获得查询条件。
            // $('#table').DataTable().ajax.reload();
        });
    },

    addAlgorithm(option){
        if(option.type == "tableAlgorithm"){
            $('#sfGruopListRight').append(`<div class="left-list" ondragstart="onDragStart(event,${JSON.stringify(option).replace(/\"/g, "'")})" draggable="true">
                <div title="${option.data.text}" class="left-list-tilte dbclickAlgorithm" style="height:50px;" AlgorithmId="${option.id}">${option.data.text}</div>
            </div>`);
        }
        if(option.type== "规则"){
            $("#rulePage").append(`<div class="left-list">
                    <div class="left-list-tilte" title=${option.data.text}>${option.data.text}</div>
                </div>
                </div>`);
        }
       
    },

    addModel(option){
        
        $(".moduleContent").append(`<div class="left-list"  >
                                <div class="left-list-tilte" title=${option.data.text}>${option.data.text}</div>
                                <div class="left-list-event">
                                    <div class='lkr-list-edit lkr-edit' data-id='${option.data.id}' >编辑模型</div>
                                    <div class='lkr-list-del lkr-del' data-id='${option.data.id}' >删除模型</div>
                                </div>
                            </div>`);
    },
    // 初始化画布
    initCanvas: function () {
        var self = this;

        // 3. 向引擎注册图形库图形及其相关元素
        $.ajax({
            url:urlConfig.host+'/operatorMaintenance/getAllAlgorithm',
            data:'',
            success: function(data) {
                $(".left-list").remove()
                data.map(item => {
                    window.addAlgorithm({
                        name: 'rectangle',
                        icon: 'icon-rectangle',
                        id:item.tableAlgorithm.id,
                        type:"tableAlgorithm",
                        data: {
                            id:item.tableAlgorithm.id,
                            text: item.tableAlgorithm.algorithmname,
                            rect: {
                                width: 200,
                                height: 100
                            },
                            parentId:item.tableAlgorithm.id,
                            font: {
                                fontFamily: 'Arial',
                                color: 'white',                           
                                textBaseline: 'top',
                                overflow:'hidden',
                                whiteSpace:'nowrap',
                                textOverflow:'ellipsis'
                            },
                            data:{
                                inNum:item.inNum,
                                outNum:item.outNum,
                                sid:item.tableAlgorithm.id
                            },
                            children:[],
                            paddingLeft: 10,
                            paddingRight: 10,
                            paddingTop: 10,
                            paddingBottom: 10,
                            borderRadius: 0.1,
                            name: 'rectangle',
                            fillStyle:'red',
                            strokeStyle: '#4295ec',
                            hideInput:true,
                            bkType:0,
                            hideRotateCP:true,
                            hideSizeCP:true
                        }
                    })
                })
                $('#returnLeft').click(() => {
                    
                    if((document.getElementById('pic_list').scrollLeft - 50) < 0){
                        document.getElementById('pic_list').scrollLeft = 0
                        
                    }else{
                        document.getElementById('pic_list').scrollLeft = document.getElementById('pic_list').scrollLeft - 50
                    }
                })
                $('#returnRight').click(() => {
                    
                    if((document.getElementById('pic_list').scrollLeft + 50) > document.getElementById('pic_list').scrollWidth){
                        document.getElementById('pic_list').scrollLeft = document.getElementById('pic_list').scrollWidth
                        
                    }else{
                        document.getElementById('pic_list').scrollLeft = document.getElementById('pic_list').scrollLeft + 50
                    }
                    
                })
                $("#menu_combine").css('display', "none");
                $("#menu_unCombine").css('display', "block");
                $("#menu_combine").attr('disabled', false);
                $("#menu_unCombine").attr('disabled', true);

                $("#menu_combine").css('display', "block");
                $("#menu_unCombine").css('display', "none");
                $("#menu_combine").attr('disabled', true);
                $("#menu_unCombine").attr('disabled', false);
                // 初始化canvas
                var data = {
                    "nodes": [],
                    "lines": [],
                    "lineName": "line",
                    "fromArrowType": "",
                    "toArrowType": "triangleSolid",
                    "scale": 1,
                    "locked": 0,
                    
                };
               
                var canvasOptions = {
                    on: onMessage
                };
                
                let canvasId = parent.canvasId
                canvas = new Le5leTopology.Topology('topo_canvas',canvasOptions);
                canvas.options.hideRotateCP = true
                canvas.options.hideSizeCP = true
                canvas.options.hideInput = true
                canvas.disableScale =true
                // 监听画布
                function onMessage(event, data) {
                    console.log(event,data)
                    switch (event) {
                        case 'node':
                            selNodes = [data];
                            selected = {
                                "type": event,
                                "data": data
                            };
                            if(data.childStand){
                                parent.$(".menu-a-delete").css("display", "none");
                            }else{
                                parent.$(".menu-a-delete").css("display", "block");
                                parent.$("#showAllmag").show()
                            }

                            self.initNode();
                            parent.$("#setAct").hide()
                            break;
                        case 'line':
                            times++;
                            var out_small = data.from.id.split('---')[0]//输出小矩形uuid
                            var in_small =  data.to.id.split('---')[0]//输入小矩形uuid
                            if(window.top.canvasNowId == "canvas0"){
                                deleteLineDataId = out_small + "AND" + in_small;
                            }else{
                                parent.$('#'+window.top.canvasNowId)[0].contentWindow.deleteLineDataId = out_small + "AND" + in_small;

                            }
                            parent.$("#setAct").show()
                            currentLineData = data;
                            canvas.lockLines([data],true)
                            canvas.render()
                            if(window.canvasNowId != "canvas0"){
                                parent.$('#'+window.top.canvasNowId)[0].contentWindow.selLines =[data]
                            }
                            setTimeout(()=>{
                                times = 0;
                            },300)
                            if(times >=2){
                                parent.$("#actionDivLine").show();
                                parent.$("#actionMsgInLine").val("")
                                parent.$("#actionMsgOutLine").val("")
                                parent.$("#actionDivLineIn").empty();
                                parent.$("#actionDivLineOut").empty();
                                var datas,local,res;
                                if(window.top.canvasNowId == "canvas0"){
                                    datas = canvas.data;
                                    local = globalActionDatas;
                                    res =responseActionDatas
                                }else{
                                    datas = parent.$('#'+window.top.canvasNowId)[0].contentWindow.canvas.data;
                                    local = parent.$('#'+window.top.canvasNowId)[0].contentWindow.globalActionDatas;
                                    res = parent.$('#'+window.top.canvasNowId)[0].contentWindow.responseActionDatas
                                }
                                let id_in;//输入端算法id
                                let id_out;//输出端算法id
                                parent.$("#actionMsgIn").val("");
                                parent.$("#actionMsgOut").val("");
                                parent.$("#actionMsgIn").show();
                                parent.$("#actionMsgOut").hide();
                                parent.$(".menu-a-delete").css("display", "block");
                                parent.$("#addActionButton").attr({
                                    actionRelation:"",
                                    preActionRelation:""
                                })
                                parent.$("#addActionButton").attr("resData","false")
                                datas.nodes.map(s=>{
                                    if(s.id == data.from.id){
                                        id_out = s.childStand.fid
                                    }
                                    if(s.id == data.to.id){
                                        id_in = s.childStand.fid
                                    }
                                })
                                if(window.canvasNowId != "canvas0"){
                                    parent.$('#'+window.top.canvasNowId)[0].contentWindow.selLines =[data]
                                }
                                var bigOutName,smallOutName,bigInName,smallInName,out_big,in_big,fromParmaChinese;
                                var bigList=[];
                                datas.nodes.map(s=>{
                                    if(s.id == data.from.id){
                                        smallOutName = s.childStand.cstext;
                                        out_big = s.childStand.fUUid;
                                    }
                                    if(s.id == data.to.id){
                                        smallInName = s.childStand.cstext;
                                        in_big = s.childStand.fUUid;
                                    }
                                })
                                datas.nodes.map(s=>{
                                    if(s.id == out_big){
                                        bigOutName = s.text;
                                    }
                                    if(s.id == in_big){
                                        bigInName = s.text;
                                    }
                                })
                                datas.nodes.map(s=>{
                                    if(s.id == data.to.id){
                                        fromParmaChinese = s.childStand.cstext
                                    }
                                })
                                $(".actionDivLineInName").text(`${bigInName}的输入参数`)
                                $(".actionDivLineOutName").text(`${bigOutName}的输出参数`)
                                var localData = true;//使用本地缓存数据
                                var responseCurrentData = false;
                                var resBaseOut;
                                var resBaseIn;
                                if(res){//后台返回数据
                                    res.map(t=>{
                                        if((t.preParametersID == out_small) && (t.parametersID == in_small)){
                                            responseCurrentData= t;
                                            resBaseOut = [];
                                            resBaseIn = [];
                                            localData = false;
                                        }
                                    })
                                    if(responseCurrentData){
                                        parent.$("#actionMsgInLine").val(responseCurrentData.actionRelation)
                                        parent.$("#actionMsgOutLine").val(responseCurrentData.preActionRelation)
                                        responseCurrentData.algorithmconditions.map(s=>{
                                            if(responseCurrentData.preParametersID ==  s.interfaceparametersid){//输出
                                                resBaseOut.push(s)
                                            }
                                            if(responseCurrentData.parametersID ==  s.interfaceparametersid){//输入
                                                resBaseIn.push(s)
                                            }
                                        })
                                        var resOutAll = {
                                            "interfaceRoleDataModels":
                                                {
                                                    "algorithmconditions":resBaseOut,
                                                    "des": "",
                                                    "id": responseCurrentData.id,
                                                    "interfaceID": responseCurrentData.interfaceID,
                                                    "parametersID":responseCurrentData.parametersID,
                                                    "preInterfaceID": responseCurrentData.preInterfaceID,
                                                    "preParametersID": responseCurrentData.preParametersID,
                                                    "remark": "",
                                                    "roleid": responseCurrentData.roleid,
                                                }
                                        }
                                        var resInAll = {
                                            "interfaceRoleDataModels":
                                                {
                                                    "algorithmconditions": resBaseIn,
                                                    "des": "",
                                                    "id": responseCurrentData.id,
                                                    "interfaceID": responseCurrentData.interfaceID,
                                                    "parametersID": responseCurrentData.parametersID,
                                                    "preInterfaceID": responseCurrentData.preInterfaceID,
                                                    "preParametersID": responseCurrentData.preParametersID,
                                                    "remark": "",
                                                    "roleid": responseCurrentData.roleid,
                                                }
                                        }
                                        try{
                                            $.ajax({
                                                url:urlConfig.host+'/operatorMaintenance/getAlgorithmById',
                                                data:{algthId:id_in},
                                                success(response) {
                                                    var varname="";
                                                    response.tableFuncs.map(s => {
                                                        if(s.parametername == fromParmaChinese) {
                                                            varname = s.varname
                                                        }
                                                    })
                                                    resBaseIn.map((t,i)=>{
                                                        parent.$("#actionDivLineIn").append(`
                                                                <div style="margin: 10px 0" actionId=${t.id}>
                                                                       <i>${i+1}</i>
                                                                       <span style="width: 80px">行为值来源</span><input value="${varname}" class="xwzly_in_line" disabled>
                                                                       <span style="width: 40px;">行为</span><input class="xwSelect_in_line" value="${t.behavior}" disabled>
                                                                       <span style="width: 60px;">表达式</span><input type="text" value="${t.expression}" class="bds_in_line" disabled>
                                                                    </div>
                                                                `)
                                                    })
                                                }
                                            })
                                            $.ajax({
                                                url:urlConfig.host+'/operatorMaintenance/getAlgorithmById',
                                                data:{algthId:id_out},
                                                success(res) {
                                                    res.tableFuncs.map(s=>{
                                                        resBaseOut.map(x => {
                                                            if (x.valuesources == s.id) {
                                                                x.xwname = s.varname;
                                                            }
                                                        })
                                                    })
                                                    resBaseOut.map((t,i)=>{
                                                        parent.$("#actionDivLineOut").append(`
                                                              <div style="margin: 10px 0" actionId=${t.id}>
                                                                   <i>${i+1}</i>
                                                                   <span style="width: 80px">行为值来源</span><input class="xwzly_out_line" value="${t.xwname}" disabled>
                                                                   <span style="width: 40px">行为</span><input class="xwSelect_out_line" value="${t.behavior}" disabled>
                                                                   <span style="width: 60px">表达式</span><input type="text" value="${t.expression}" class="bds_out_line" disabled>
                                                              </div>
                                                        `)
                                                    })
                                                }
                                            })
                                        } catch (e) {
                                            console.log(e);
                                        }
                                    }
                                }
                                if(localData){//本地缓存数据
                                    local.map(s=>{
                                        if(s.id == out_small + "AND" + in_small){
                                            try{
                                                var lineDatas = s.dataIn.interfaceRoleDataModels.algorithmconditions;
                                                var lineDatasOut = s.dataOut.interfaceRoleDataModels.algorithmconditions;
                                                parent.$("#actionMsgInLine").val(s.dataIn.interfaceRoleDataModels.actionRelation);
                                                parent.$("#actionMsgOutLine").val(s.dataOut.interfaceRoleDataModels.preActionRelation);
                                                $.ajax({
                                                    url:urlConfig.host+'/operatorMaintenance/getAlgorithmById',
                                                    data:{algthId:id_in},
                                                    success(res) {
                                                        var varname="";
                                                        res.tableFuncs.map(s => {
                                                            if(s.parametername == fromParmaChinese) {
                                                                varname = s.varname
                                                            }
                                                        })
                                                        lineDatas.map((t,i)=>{
                                                            parent.$("#actionDivLineIn").append(`
                                                                 <div style="margin: 10px 0">
                                                                       <i>${i+1}</i>
                                                                       <span style="width: 80px">行为值来源</span><input value="${varname}" class="xwzly_in_line" disabled>
                                                                       <span style="width: 40px">行为</span><input class="xwSelect_in_line" value="${t.behavior}" disabled>
                                                                       <span style="width: 60px">表达式</span><input type="text" value="${t.expression}" class="bds_in_line" disabled>
                                                                 </div>
                                                            `)
                                                        })
                                                    }
                                                })
                                                $.ajax({
                                                    url:urlConfig.host+'/operatorMaintenance/getAlgorithmById',
                                                    data:{algthId:id_out},
                                                    success(res) {
                                                        res.tableFuncs.map(s=>{
                                                            lineDatasOut.map(x => {
                                                                if (x.valuesources == s.id) {
                                                                    x.xwname = s.varname;
                                                                }
                                                            })
                                                        })
                                                        lineDatasOut.map((t,i)=>{
                                                            parent.$("#actionDivLineOut").append(`
                                                                 <div style="margin: 10px 0">
                                                                       <i>${i+1}</i>
                                                                       <span style="width: 80px">行为值来源</span><input class="xwzly_out_line" value="${t.xwname}" disabled>
                                                                       <span style="width: 40px">行为</span><input class="xwSelect_out_line" value="${t.behavior}" disabled>
                                                                       <span style="width: 60px">表达式</span><input type="text" value="${t.expression}" class="bds_out_line" disabled>
                                                                 </div>
                                                            `)
                                                        })
                                                    }
                                                })
                                            } catch (e) {
                                                console.log(e);
                                            }
                                        }
                                    })
                                }
                                //-----
                            }
                            break;
                        case 'multi':
                            locked = true;
                            if (data.nodes && data.nodes.length) {
                                selNodes = data.nodes;
                                for (var item in data.nodes) {
                                    if (!item.locked) {
                                        locked = false;
                                        break;
                                    }
                                }
                            }
                            if (locked && data.lines) {
                                for (var item in data.lines) {
                                    if (!item.locked) {
                                        locked = false;
                                        break;
                                    }
                                }
                            }
                            selected = {
                                "type": event,
                                "data": data
                            };
                            break;
                        case 'space':
                            $("#flex_props_home").removeClass("hidden");
                            $("#flex_props_node").addClass("hidden");
                            setTimeout(function () {
                                selected = null;
                                selNodes = null;
                            });
                           parent.$("#setAct").hide();
                           parent.$("#showAllmag").hide()
                          canvas.lock(0)
                            break;
                        case 'moveNodes':
                            data[0].anchors.map((obj,i) => {
                                obj.x = 0;
                                obj.y = 0;
                            })
                            data[0].rotatedAnchors.map((obj,i) => {
                                obj.x = 0;
                                obj.y = 0
                            })
                            if(data[0].childStand) canvas.lockNodes([data[0]], true)
                            let nowList =[]
                            canvas.data.nodes.map(now=>{
                                if(now.childStand && data[0].id ==now.childStand.fUUid){
                                    nowList.push(now)
                                }
                            })
                            let in_num = -1
                            let out_num = -1
                            nowList.map((item,i) => {
                                if(item.childStand){
                                    if( data[0].id ==item.childStand.fUUid){
                                        if(item.childStand.type =="IN"){
                                            in_num++
                                            item.rect.width = 20
                                            item.rect.height = 10 
                                            item.rect.x = data[0].rect.x- item.rect.width
                                            item.rect.y = data[0].rect.y + in_num*20 + 10
                                                                          
                                            item.rect.ex = item.rect.x  + item.rect.width
                                            item.rect.ey = item.rect.y + item.rect.height
                                            item.rect.center.x = item.rect.x+ item.rect.width/2
                                            item.rect.center.y =item.rect.y  + item.rect.height/2

                                            item.textRect.x = item.rect.x - 5
                                            item.textRect.y =  item.rect.y
                                            item.textRect.width = 10
                                            item.textRect.height = 5
                                            item.paddingTopNum = 0
                                            item.paddingTop = 0
                                            item.fullIconRect.height = 4
                                            item.fullTextRect.x = item.rect.ex - item.textRect.height -5
                                            item.fullTextRect.y =  item.rect.y  -item.fullIconRect.height
                                            item.iconRect.x = item.rect.ex - item.textRect.height -5
                                            item.iconRect.y = item.rect.y  -item.fullIconRect.height
                                            item.fullIconRect.x =  item.rect.ex - item.textRect.height -5
                                            item.fullIconRect.y = item.rect.y  -item.fullIconRect.height
                                            item.anchors[0].x = item.rect.x
                                            item.anchors[0].y = item.rect.center.y 
                                            item.anchors[1].x = item.rect.center.x
                                            item.anchors[1].y = item.rect.y   
                                            item.anchors[2].x = item.rect.ex
                                            item.anchors[2].y = item.rect.center.y                                                                                        
                                            item.anchors[3].x = item.rect.center.x
                                            item.anchors[3].y = item.rect.ey  
                                            item.rotatedAnchors[0].x = item.rect.x
                                            item.rotatedAnchors[0].y =item.rect.center.y 
                                            item.rotatedAnchors[1].x = item.rect.center.x
                                            item.rotatedAnchors[1].y = item.rect.y 
                                            item.rotatedAnchors[2].x = item.rect.ex
                                            item.rotatedAnchors[2].y = item.rect.center.y  
                                            item.rotatedAnchors[3].x = item.rect.center.x
                                            item.rotatedAnchors[3].y = item.rect.ey 
                                            item.textMaxLine = 1
                                            item.hideRotateCP=true,
                                            item.hideSizeCP=true
                                        }else{                                         
                                            out_num ++                                            
                                            item.rect.x = data[0].rect.x +data[0].rect.width
                                            item.rect.y = data[0].rect.y + out_num*20 + 10
                                            item.rect.width = 20
                                            item.rect.height = 10                                
                                            item.rect.ex = item.rect.x  + item.rect.width
                                            item.rect.ey = item.rect.y + item.rect.height
                                            item.rect.center.x = item.rect.x+ item.rect.width/2
                                            item.rect.center.y =item.rect.y  + item.rect.height/2


                                            item.textRect.x = item.rect.x -5
                                            item.textRect.y =  item.rect.y
                                            item.textRect.width = 10
                                            item.textRect.height = 5
                                            item.paddingTopNum = 0
                                            item.paddingTop = 0
                                            item.fullIconRect.height = 4
                                            item.fullTextRect.x = item.rect.ex - item.textRect.height -5
                                            item.fullTextRect.y =  item.rect.y  -item.fullIconRect.height
                                            item.iconRect.x = item.rect.ex - item.textRect.height -5 
                                            item.iconRect.y = item.rect.y  -item.fullIconRect.height
                                            item.fullIconRect.x =  item.rect.ex - item.textRect.height -5
                                            item.fullIconRect.y = item.rect.y  -item.fullIconRect.height
                                            item.textMaxLine = 1
                                            item.anchors[0].x = item.rect.x
                                            item.anchors[0].y = item.rect.center.y 
                                            item.anchors[1].x = item.rect.center.x
                                            item.anchors[1].y = item.rect.y   
                                            item.anchors[2].x = item.rect.ex
                                            item.anchors[2].y = item.rect.center.y                                                                                        
                                            item.anchors[3].x = item.rect.center.x
                                            item.anchors[3].y = item.rect.ey  
                                            item.rotatedAnchors[0].x = item.rect.x
                                            item.rotatedAnchors[0].y =item.rect.center.y 
                                            item.rotatedAnchors[1].x = item.rect.center.x
                                            item.rotatedAnchors[1].y = item.rect.y 
                                            item.rotatedAnchors[2].x = item.rect.ex
                                            item.rotatedAnchors[2].y = item.rect.center.y  
                                            item.rotatedAnchors[3].x = item.rect.center.x
                                            item.rotatedAnchors[3].y = item.rect.ey 
                                            item.hideRotateCP=true,
                                            item.hideSizeCP=true
                                        }
                                        canvas.render()                                       
                                    }
                                }
                            })
                            canvas.data.lines.map(item => {
                                let nodesa = canvas.data.nodes.filter(obj => {
                                    if(item.from.id == obj.id) return obj
                                })[0]
                                item.from.x = nodesa.rotatedAnchors[2].x
                                item.from.y = nodesa.rotatedAnchors[2].y

                                let nodesa1 = canvas.data.nodes.filter(obj => {
                                    if(item.to.id == obj.id) return obj
                                })[0]
                                item.to.x = nodesa1.rotatedAnchors[0].x
                                item.to.y = nodesa1.rotatedAnchors[0].y
                            })
                            // canvas.updateProps()
                            break    
                        case 'moveOutNode':
                            if(editGzType == false){
                                editGzType = true;
                            }
                            parent.$("#showCsName").text("").hide()
                            break   
                        case 'moveInNode':   
                            if(data.name == "combine"){
                                $("#menu_unCombine").removeClass("menu-a-disabled");
                                $("#menu_unCombine").addClass("menu-a");
                                $("#menu_combine").css("display", "none");
                                $("#menu_unCombine").css("display", "block");
                                canvas.uncombine(data);
                                canvas.render();
                            }
                            if(data.childStand){
                                parent.$("#showCsName").text(data.childStand.cstext).css({
                                    "position":"absolute",
                                    "top":(data.rect.y+40)+"px",
                                    "left":(data.rect.x)+"px",
                                    "z-index":"999999"
                                })
                                parent.$("#showCsName").show()
                            }
                            canvas.lockNodes([data], false)
                            break    
                        case 'moveOut':
                            parent.$("#setAct").hide()
                            this.workspace.nativeElement.scrollLeft += 10;
                            this.workspace.nativeElement.scrollTop += 10;
                            break;
                        case 'addNode':

                            
                            selNodes = [data];
                            selected = {
                                "type": event,
                                "data": data
                            };
                       
                            self.banAdd = true
                            let saveList ={
                                id :data.id,
                                name:data.text,
                                children:[]
                            }
                            locked = data.locked;
                            self.initNode(); 
                            data.hideRotateCP=true,
                            data.hideSizeCP=true
                            let data1 = JSON.parse(JSON.stringify(data)) 
                            if(self.banAdd){
                                if(data1.childStand){                              
                                    return
                                }else{
                                    parent.$("#showAllmag").show()
                                    self.isClickAction.push({isClick:true,id:data.id})
                                    function guid() {
                                        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
                                            var r = Math.random() * 16 | 0,
                                                v = c == 'x' ? r : (r & 0x3 | 0x8);
                                            return v.toString(16);
                                        });
                                    }
                                    canvas.lockNodes([data],true)
                                    data.id = guid()
                                    saveList.uuid = data.id
                                    data.anchors.map((obj,i) => {
                                        obj.x = 0;
                                        obj.y = 0;
                                    })
                                    data.rotatedAnchors.map((obj,i) => {
                                        obj.x = 0;
                                        obj.y = 0
                                    })
                                }
                                if(data.data.inNum > 0 || data.data.outNum > 0){                                
                                    let data2 = JSON.parse(JSON.stringify(data1)) 
                                    function guid() {
                                        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
                                            var r = Math.random() * 16 | 0,
                                                v = c == 'x' ? r : (r & 0x3 | 0x8);
                                            return v.toString(16);
                                        }); 
                                    }
                                    for(let i= 0;i<data.data.inNum; i++){                                   
                                        self.addInlist.map((item,index) =>{
                                            if(i == index){
                                                
                                                let widths = 20
                                                let heights = 10
                                                let num = {
                                                        x:-widths,
                                                        y:(heights*i) + 10*i+10
                                                    }
                                                let type ="";
                                                if(item.vartype == 1){
                                                    type = item.valvalue
                                                }
                                                if(item.vartype == 2){
                                                    type = "常量"
                                                }
                                                if(item.vartype == 3){
                                                    type ="模型"
                                                }
                                                
                                                switch (type) {
                                                    case '常量':
                                                        fillStyle = '#0eff23';
                                                        break;
                                                    case '模型':
                                                        fillStyle = '#ff00e7';
                                                        break;
                                                    case 'int':
                                                        fillStyle = 'red';
                                                        break;
                                                    case 'byte':
                                                        fillStyle = '#ff7749';
                                                        break;
                                                    case 'long':
                                                        fillStyle = '#a4ff59';
                                                        break;
                                                    case 'short':
                                                        fillStyle = '#fb61ff';
                                                        break;
                                                    case 'float':
                                                        fillStyle = 'blue';
                                                        break;
                                                    case 'double':
                                                        fillStyle = 'green';
                                                        break;
                                                    case 'boolean':
                                                        fillStyle = 'aqua';
                                                        break;
                                                    case 'number':
                                                        fillStyle = 'orange';
                                                        break;
                                                    case 'char':
                                                        fillStyle = '#7cc6ff';
                                                        break;
                                                    case 'date':
                                                        fillStyle = '#ff4286';
                                                        break;
                                                    case 'string':
                                                        fillStyle = '#00c1ff';
                                                        break;
                                                    case 'blob':
                                                        fillStyle = '#6a46ff';
                                                        break;
                                                    case 'array':
                                                        fillStyle = '#ffe964';
                                                        break;
                                                }
                                               
                                                let UUid =  guid()
                                                data2.id = UUid+"---"+type;
                                                data2.rect.width = widths
                                                data2.rect.height = heights
                                                // data2.text = item.parametername;
                                                data2.text = ""   
                                                data2.bkType = 0
                                                data2.fillStyle = fillStyle
                                                data2.rect.ex = data1.rect.x + num.x;
                                                data2.rect.ey = data1.rect.y + num.y;
                                                data2.rect.x = data1.rect.x + num.x;
                                                data2.rect.y = data1.rect.y+ num.y;
                                                data2.textRect.x = data2.rect.x - widths/2;
                                                data2.textRect.y = data2.rect.y -heights*2;
                                                data2.font.color = "white"
                                                data2.textRect.width = 10;
                                                data2.textRect.height = 5;
                                                data2.paddingTopNum = 0
                                                data2.paddingTop = 0
                                                data2.textRect.ex = data2.textRect.x + data2.textRect.width;
                                                data2.textRect.ey = data2.textRect.y +data2.textRect.height;
                                                data2.textMaxLine = 1
                                                data2.hideRotateCP=true,
                                                data2.hideSizeCP=true
                                                data2.childStand = {
                                                    type:"IN",
                                                    wz:num,
                                                    bb:{
                                                        x:data1.rect.x,
                                                        y:data1.rect.y,
                                                        ex:data1.rect.ex,
                                                        ey:data1.rect.ey
                                                    },
                                                    text:item.valvalue,
                                                    fid:data.data.sid,
                                                    fUUid: data.id,
                                                    canshuId:item.id,
                                                    cstext:item.parametername
                                                }
                                              
                                                // data2.anchors[0].x = data2.rect.x
                                                // data2.anchors[0].y =data2.rect.center.y 
                                                // data2.anchors[1].x =0
                                                // data2.anchors[1].y = 0   
                                                // data2.anchors[2].x = 0
                                                // data2.anchors[2].y = 0                                                                                          
                                                // data2.anchors[3].x =0
                                                // data2.anchors[3].y = 0
                                                // data2.rotatedAnchors[0].x = data2.rect.x
                                                // data2.rotatedAnchors[0].y =data2.rect.center.y 
                                                // data2.rotatedAnchors[1].x = 0
                                                // data2.rotatedAnchors[1].y =0
                                                // data2.rotatedAnchors[2].x = 0
                                                // data2.rotatedAnchors[2].y = 0
                                                // data2.rotatedAnchors[3].x =0
                                                // data2.rotatedAnchors[3].y =0
                                                data2.anchors.map((obj,i) => {
                                                    obj.x = data1.anchors[i].x-185 + num.x
                                                    obj.y = data1.anchors[i].y-85 + num.y
                                                })
                                                data2.rotatedAnchors.map((obj,i) => {
                                                    obj.x = data1.rotatedAnchors[i].x-185 + num.x
                                                    obj.y = data1.rotatedAnchors[i].y-85 + num.y
                                                }) 
                                                canvas.addNode(data2)
                                                canvas.lockNodes([data2],true)   
                                                canvas.render()
                                                if(data.data.inNum  > data.data.outNum){
                                                    if( data.rect.height < (heights*(data.data.inNum+1)+10*(data.data.inNum+1))){
                                                        data.rect.ey = data.rect.ey + heights+15
                                                        data.rect.height = data.rect.height + heights+15
                                                    }
                                                }


                                                obj = {
                                                    id:item.id,
                                                    uuid:UUid,
                                                    algorithmid:data.data.sid,
                                                    varname:item.varname,
                                                    vartype:item.vartype,
                                                    valvalue:item.valvalue,
                                                    inorout:item.inorout,
                                                    remark:item.remark,
                                                    parametername:item.parametername
                                                }
                                                saveList.children.push(obj)
                                            }
                                        

                                        })
                                    
                                        // let widths = data1.rect.width/10
                                        // let heights = data1.rect.height/10
                                                                        
                                    }
                                    for(let i= 0;i<data.data.outNum; i++){                                 
                                        self.addOutlist.map((item,index) =>{
                                            if(i == index){
                                                
                                                let widths = 20
                                                let heights = 10
                                                let num = {
                                                        x:data1.rect.width,
                                                        y:(heights*i) + 10*i+10
                                                    }
                                                let type ="";
                                                if(item.vartype == 1){
                                                    type = item.valvalue
                                                }
                                                if(item.vartype == 2){
                                                    type = "常量"
                                                }
                                                if(item.vartype == 3){
                                                    type ="模型"
                                                }
                                                switch (type) {
                                                    case '常量':
                                                        fillStyle = '#0eff23';
                                                        break;
                                                    case '模型':
                                                        fillStyle = '#ff00e7';
                                                        break;
                                                    case 'int':
                                                        fillStyle = 'red';
                                                        break;
                                                    case 'byte':
                                                        fillStyle = '#ff7749';
                                                        break;
                                                    case 'long':
                                                        fillStyle = '#a4ff59';
                                                        break;
                                                    case 'short':
                                                        fillStyle = '#fb61ff';
                                                        break;
                                                    case 'float':
                                                        fillStyle = 'blue';
                                                        break;
                                                    case 'double':
                                                        fillStyle = 'green';
                                                        break;
                                                    case 'boolean':
                                                        fillStyle = 'aqua';
                                                        break;
                                                    case 'number':
                                                        fillStyle = 'orange';
                                                        break;
                                                    case 'char':
                                                        fillStyle = '#7cc6ff';
                                                        break;
                                                    case 'date':
                                                        fillStyle = '#ff4286';
                                                        break;
                                                    case 'string':
                                                        fillStyle = '#00c1ff';
                                                        break;
                                                    case 'blob':
                                                        fillStyle = '#6a46ff';
                                                        break;
                                                    case 'array':
                                                        fillStyle = '#ffe964';
                                                        break;
                                                }
                                                let UUid =  guid()
                                                data2.id = UUid+"---"+type;
                                                data2.rect.width = widths
                                                data2.rect.height = heights
                                                // data2.text = item.parametername;
                                                // data2.text = ""   
                                                data2.bkType = 0
                                                data2.fillStyle = fillStyle
                                                data2.rect.ex = data1.rect.x + num.x;
                                                data2.rect.ey = data1.rect.y + num.y;
                                                data2.rect.x = data1.rect.x + num.x;
                                                data2.rect.y = data1.rect.y+ num.y;
                                                data2.textRect.x = data2.rect.x - widths/2;
                                                data2.textRect.y = data2.rect.y -heights*2;
                                                data2.textRect.width = 10;
                                                data2.textRect.height = 5;
                                                data2.paddingTopNum = 0
                                                data2.paddingTop = 0
                                                data2.font.color = "white"
                                                data2.textRect.ex = data2.textRect.x + data2.textRect.width;
                                                data2.textRect.ey = data2.textRect.y +data2.textRect.height;
                                                data2.textMaxLine = 1
                                                data2.hideRotateCP=true,
                                                data2.hideSizeCP=true
                                                data2.childStand = {
                                                    type:"OUT",
                                                    wz:num,
                                                    bb:{
                                                        x:data1.rect.x,
                                                        y:data1.rect.y,
                                                        ex:data1.rect.ex,
                                                        ey:data1.rect.ey
                                                    },
                                                    text:item.valvalue,
                                                    fid:data.data.sid,
                                                    fUUid: data.id,
                                                    canshuId:item.id,
                                                    cstext:item.parametername
                                                }
                                                // data2.anchors[0].x = 0
                                                // data2.anchors[0].y = 0 
                                                // data2.anchors[1].x =0
                                                // data2.anchors[1].y = 0   
                                                // data2.anchors[2].x = data2.rect.ex
                                                // data2.anchors[2].y = data2.rect.center.y                                                                                           
                                                // data2.anchors[3].x =0
                                                // data2.anchors[3].y = 0
                                                // data2.rotatedAnchors[0].x = 0
                                                // data2.rotatedAnchors[0].y =0
                                                // data2.rotatedAnchors[1].x = 0
                                                // data2.rotatedAnchors[1].y =0
                                                // data2.rotatedAnchors[2].x = data2.rect.ex
                                                // data2.rotatedAnchors[2].y = data2.rect.center.y 
                                                // data2.rotatedAnchors[3].x =0
                                                // data2.rotatedAnchors[3].y =0
                                                data2.anchors.map((obj,i) => {
                                                    obj.x = data1.anchors[i].x-185 + num.x
                                                    obj.y = data1.anchors[i].y-85 + num.y
                                                })
                                                data2.rotatedAnchors.map((obj,i) => {
                                                    obj.x = data1.rotatedAnchors[i].x-185 + num.x
                                                    obj.y = data1.rotatedAnchors[i].y-85 + num.y
                                                }) 
                                                canvas.addNode(data2)
                                                canvas.lockNodes([data2],true)   
                                                canvas.render()
                                                if(data.data.outNum > data.data.inNum){
                                                    if(data.rect.height < (heights*(data.data.outNum+1) +10*(data.data.outNum+1))){
                                                        data.rect.ey = data.rect.ey + heights+15
                                                        data.rect.height = data.rect.height + heights+15
                                                    }
                                                }

                                                obj = {
                                                    id:item.id,
                                                    uuid:UUid,
                                                    algorithmid:data.data.sid,
                                                    varname:item.varname,
                                                    vartype:item.vartype,
                                                    valvalue:item.valvalue,
                                                    inorout:item.inorout,
                                                    remark:item.remark,
                                                    parametername:item.parametername
                                                }
                                                saveList.children.push(obj)
                                            }
                                        

                                        })
                                    
                                        // let widths = data1.rect.width/10
                                        // let heights = data1.rect.height/10
                                                                        
                                    }
                                    self.tools[data.id] = saveList
                                    if(window.canvasNowId == "canvas0"){
                                        self.tools[data.id] = saveList
                                    }else{
                                       parent.$('#'+window.top.canvasNowId)[0].contentWindow.Topology.tools[data.id] = saveList
                                       parent.$('#'+window.top.canvasNowId)[0].contentWindow.selNodes =[data]
                                    }
                                    canvas.render();
                                } 
                            }
                            
                            let ruleType ,editRuleId
                            if(window.canvasNowId == "canvas0"){
                                ruleType = window.bigData.ruleType
                                editRuleId = window.bigData.editRuleId 
                           }else{
                                ruleType = parent.$('#'+window.top.canvasNowId)[0].contentWindow.bigData.ruleType
                                editRuleId =parent.$('#'+window.top.canvasNowId)[0].contentWindow.bigData.editRuleId
                           }  
                           if(ruleType  == "edit"){
                                let childList = saveList
                        
                                let operatorInterfaceDataModel ={
                                    algorithmID:data.data.sid,
                                    id:data.id ,
                                    interfaceName:data.text,
                                    roleID:editRuleId,
                                    tableInterfaceparametersList:[]
                                }
                        
                                childList.children.map(index=>{
                                    let uuid
                                    if(index.uuid.indexOf('---') !=-1){
                                        uuid = index.uuid.substr((index.uuid.indexOf('---')-36),36)
                                    }else{
                                        uuid = index.uuid
                                    }
                                    let CsObj = {
                                        id:uuid,
                                        inorout:index.inorout,
                                        interfaceid:data.id,
                                        parametersname:index.varname,
                                        parameterssources:index.id,
                                        remark:index.remark
                                    }
                                    operatorInterfaceDataModel.tableInterfaceparametersList.push(CsObj)
                                })
                                $.ajax({
                                    type:"post",
                                    dataType: "json",
                                    url:urlConfig.host+'/algorithmRule/modInterfaceRole',
                                    contentType: "application/json;charset=UTF-8",
                                    data:JSON.stringify(operatorInterfaceDataModel),
                                    success: function(data) {
                                        parent.$('.noticeList').append(`<li>${parent.getTime()}【算法参数】修改成功，请暂存规则！ </li>`)
                                        parent.toastr.success(`【算法参数】修改成功，请暂存规则！` )
                                        parent.$("#flex_props1_home").scrollTop(parent.$("#flex_props1_home")[0].scrollHeight);
                                        if(window.canvasNowId == "canvas0"){
                                            window.isRuleNow = false
                                        }else{
                                            parent.$('#'+window.top.canvasNowId)[0].contentWindow.isRuleNow = false
                                        }
                                      
                                    }
                                })
                           }
                            break;
                            
                        case 'resizeNodes':

                            if(!data[0].childStand){
                                data[0].anchors.map((obj,i) => {
                                    obj.x = 0;
                                    obj.y = 0;
                                })
                                data[0].rotatedAnchors.map((obj,i) => {
                                    obj.x = 0;
                                    obj.y = 0
                                })
                                // data[0].rect.width = 200
                                // data[0].rect.height = 100
                            }else{
                                // self.moveNodesList =data
                                // data[0].rect.width = 20
                                // data[0].rect.height = 10
                                // data[0].rect.x = data[0].rect.x
                                // data[0].rect.y = data[0].rect.y
                                // canvas.lockNodes([data[0]], true)
                            }
                            if(data[0].childStand) canvas.lockNodes([data[0]], true)
                            let nowLists =[]
                            canvas.data.nodes.map(now=>{
                                if(now.childStand && data[0].id ==now.childStand.fUUid){
                                    nowLists.push(now)
                                }
                            })
                            let in_nums = -1
                            let out_nums = -1
                            nowLists.map((item,i) => {
                                if(item.childStand){
                                    if( data[0].id ==item.childStand.fUUid){
                                        if(item.childStand.type =="IN"){
                                            in_nums++
                                            item.rect.width = 20
                                            item.rect.height = 10 
                                            item.rect.x = data[0].rect.x- item.rect.width
                                            item.rect.y = data[0].rect.y + in_nums*20 + 10
                                                                          
                                            item.rect.ex = item.rect.x  + item.rect.width
                                            item.rect.ey = item.rect.y + item.rect.height
                                            item.rect.center.x = item.rect.x+ item.rect.width/2
                                            item.rect.center.y =item.rect.y  + item.rect.height/2

                                            item.textRect.x = item.rect.x - 5
                                            item.textRect.y =  item.rect.y
                                            item.textRect.width = 10
                                            item.textRect.height = 5
                                            item.paddingTopNum = 0
                                            item.paddingTop = 0
                                            item.fullIconRect.height = 4
                                            item.fullTextRect.x = item.rect.ex - item.textRect.height -5
                                            item.fullTextRect.y =  item.rect.y  -item.fullIconRect.height
                                            item.iconRect.x = item.rect.ex - item.textRect.height -5
                                            item.iconRect.y = item.rect.y  -item.fullIconRect.height
                                            item.fullIconRect.x =  item.rect.ex - item.textRect.height -5
                                            item.fullIconRect.y = item.rect.y  -item.fullIconRect.height
                                            item.anchors[0].x = item.rect.x
                                            item.anchors[0].y =item.rect.center.y 
                                            item.anchors[1].x =0
                                            item.anchors[1].y = 0   
                                            item.anchors[2].x = 0
                                            item.anchors[2].y = 0                                                                                          
                                            item.anchors[3].x =0
                                            item.anchors[3].y = 0
                                            item.rotatedAnchors[0].x = item.rect.x
                                            item.rotatedAnchors[0].y =item.rect.center.y 
                                            item.rotatedAnchors[1].x = 0
                                            item.rotatedAnchors[1].y =0
                                            item.rotatedAnchors[2].x = 0
                                            item.rotatedAnchors[2].y = 0
                                            item.rotatedAnchors[3].x =0
                                            item.rotatedAnchors[3].y =0
                                            item.textMaxLine = 1
                                            item.hideRotateCP = true,
                                            item.hideSizeCP = true
                                            // item.bkType = 0
                                            // item.fillStyle = "red"
                                        }else{                                         
                                            out_nums ++                                            
                                            item.rect.x = data[0].rect.x +data[0].rect.width
                                            item.rect.y = data[0].rect.y + out_nums*20 + 10
                                            item.rect.width = 20
                                            item.rect.height = 10                                
                                            item.rect.ex = item.rect.x  + item.rect.width
                                            item.rect.ey = item.rect.y + item.rect.height
                                            item.rect.center.x = item.rect.x+ item.rect.width/2
                                            item.rect.center.y =item.rect.y  + item.rect.height/2


                                            item.textRect.x = item.rect.x -5
                                            item.textRect.y =  item.rect.y
                                            item.textRect.width = 10
                                            item.textRect.height = 5
                                            item.paddingTopNum = 0
                                            item.paddingTop = 0
                                            item.fullIconRect.height = 4
                                            item.fullTextRect.x = item.rect.ex - item.textRect.height -5
                                            item.fullTextRect.y =  item.rect.y  -item.fullIconRect.height
                                            item.iconRect.x = item.rect.ex - item.textRect.height -5 
                                            item.iconRect.y = item.rect.y  -item.fullIconRect.height
                                            item.fullIconRect.x =  item.rect.ex - item.textRect.height -5
                                            item.fullIconRect.y = item.rect.y  -item.fullIconRect.height
                                            item.textMaxLine = 1
                                            item.anchors[0].x = 0
                                            item.anchors[0].y =0
                                            item.anchors[1].x =0
                                            item.anchors[1].y = 0   
                                            item.anchors[2].x = item.rect.ex
                                            item.anchors[2].y = item.rect.center.y                                                                                            
                                            item.anchors[3].x =0
                                            item.anchors[3].y = 0
                                            item.rotatedAnchors[0].x = 0
                                            item.rotatedAnchors[0].y =0
                                            item.rotatedAnchors[1].x = 0
                                            item.rotatedAnchors[1].y =0
                                            item.rotatedAnchors[2].x = item.rect.ex
                                            item.rotatedAnchors[2].y = item.rect.center.y
                                            item.rotatedAnchors[3].x =0
                                            item.rotatedAnchors[3].y =0
                                            item.hideRotateCP=true,
                                            item.hideSizeCP=true
                                            // item.bkType = 0
                                            // item.fillStyle = "red"
                                        }
                                        canvas.render()                                       
                                    }
                                }
                            })
                            canvas.data.lines.map(item => {
                                let nodesa = canvas.data.nodes.filter(obj => {
                                    if(item.from.id == obj.id) return obj
                                })[0]
                                item.from.x = nodesa.rotatedAnchors[2].x
                                item.from.y = nodesa.rotatedAnchors[2].y

                                let nodesa1 = canvas.data.nodes.filter(obj => {
                                    if(item.to.id == obj.id) return obj
                                })[0]
                                item.to.x = nodesa1.rotatedAnchors[0].x
                                item.to.y = nodesa1.rotatedAnchors[0].y
                            })
                        break
                        case 'lockNodes':
                           if(data.nodes[0].childStand){
                                canvas.lockNodes([data],true)
                           }
                           
                        break
                        case 'addLine':
                            var strokeStyle;
                            data.dash = 1;
                            data.name = "polyline"
                            data.manualCps =true
                            canvas.lockLines([data],true)
                            canvas.render()
                            data.controlPoints[0].hidden = true;
                            data.controlPoints[1].hidden = true;
                            if(!data.to.id){
                                canvas.data.lines.map((item,i) => {
                                    if(item.id == data.id){
                                        canvas.data.lines.splice(i,1)
                                        parent.$('.noticeList').append(`<li>${parent.getTime()}【算法连线】操作失败！ </li>`)
                                        parent.toastr.info(`【算法连线】操作失败！` )
                                        parent.$("#flex_props1_home").scrollTop(parent.$("#flex_props1_home")[0].scrollHeight);
                                        canvas.render();
                                        setTimeout(function () {
                                            selected = null;
                                            selNodes = null;
                                        });
                                    }
                                })
                            }else{
                                let fromUuidType = data.from.id;
                                let toUuidType = data.to.id;
                                let fromSzId,toSzId,fromOutIn,toOutIn;
                                let uuidOut;//输出大矩形uuid
                                let uuidIn;//输入大矩形uuid
                                let uuidOutSmall = data.from.id.split('---')[0]//输出小矩形uuid
                                let uuidInSmall =  data.to.id.split('---')[0]//输入小矩形uuid
                                let fromType = data.from.id.split('---')[1]//输出小矩形类型
                                let toType = data.to.id.split('---')[1]//输入小矩形类型
                                canvas.data.nodes.map(s=>{
                                    if(s.id == fromUuidType){
                                        fromSzId = s.childStand.fid;
                                        fromOutIn = s.childStand.type;
                                        uuidOut = s.childStand.fUUid;
                                    }
                                    if(s.id == toUuidType){
                                        toSzId = s.childStand.fid;
                                        toOutIn = s.childStand.type
                                        uuidIn = s.childStand.fUUid;
                                    }
                                })
                                if(fromOutIn =="OUT" && toOutIn == "IN"){
                                    let value = ""
                                    if(fromType == "常量"){
                                        canvas.data.nodes.map(item=>{
                                            if(item.id == data.from.id){
                                                value = item.childStand.text
                                            }
                                        })
                                    }
                                    let type = isType(fromType,toType,value)
                                    if(type){
                                        switch (fromType) {
                                            case '常量':
                                                strokeStyle = '#0eff23';
                                                break;
                                            case '模型':
                                                strokeStyle = '#ff00e7';
                                                break;
                                            case 'int':
                                                strokeStyle = 'red';
                                                break;
                                            case 'byte':
                                                strokeStyle = '#ff7749';
                                                break;
                                            case 'long':
                                                strokeStyle = '#a4ff59';
                                                break;
                                            case 'short':
                                                strokeStyle = '#fb61ff';
                                                break;
                                            case 'float':
                                                strokeStyle = 'blue';
                                                break;
                                            case 'double':
                                                strokeStyle = 'green';
                                                break;
                                            case 'boolean':
                                                strokeStyle = 'aqua';
                                                break;
                                            case 'number':
                                                strokeStyle = 'orange';
                                                break;
                                            case 'char':
                                                strokeStyle = '#7cc6ff';
                                                break;
                                            case 'date':
                                                strokeStyle = '#ff4286';
                                                break;
                                            case 'string':
                                                strokeStyle = '#00c1ff';
                                                break;
                                            case 'blob':
                                                strokeStyle = '#6a46ff';
                                                break;
                                            case 'array':
                                                strokeStyle = '#ffe964';
                                                break;
                                        }
                                        data.strokeStyle = strokeStyle;
                                        locked = data.locked;
                                        self.initLine();
                                        var flag = true;
                                        globalActionDatas.map(s=>{//回显线
                                            if(s.id == uuidOutSmall + "AND" + uuidInSmall){
                                                flag = false
                                            }
                                        })


                                        if(flag){//新增线
                                            var dataBaseIn = {
                                                "interfaceRoleDataModels":
                                                    {
                                                        "algorithmconditions": [],
                                                        "des": "",
                                                        "id": 0,
                                                        "interfaceID": uuidIn,
                                                        "parametersID": uuidInSmall,
                                                        "preInterfaceID": uuidOut,
                                                        "preParametersID":  uuidOutSmall,
                                                        "remark": "",
                                                        "roleid": 0,
                                                        "actionRelation":"",
                                                        fromSzId
                                                    }
                                                ,
                                            }
                                            var dataBaseOut = {
                                                "interfaceRoleDataModels":
                                                    {
                                                        "algorithmconditions": [],
                                                        "des": "",
                                                        "id": 0,
                                                        "interfaceID": uuidIn,
                                                        "parametersID": uuidInSmall,
                                                        "preInterfaceID": uuidOut,
                                                        "preParametersID":  uuidOutSmall,
                                                        "remark": "",
                                                        "roleid": 0,
                                                        "preActionRelation":"",
                                                        toSzId
                                                    }
                                                ,
                                            }
                                            globalActionDatas.push({
                                                id:uuidOutSmall + "AND" + uuidInSmall,
                                                dataIn:dataBaseIn,
                                                dataOut:dataBaseOut
                                            })
                                        }

                                    } else {
                                        parent.$('.noticeList').append(`<li>${parent.getTime()}【算法连线】输出输入类型不匹配！ </li>`)
                                        parent.toastr.info(`【算法连线】输出输入类型不匹配！` )
                                        parent.$("#flex_props1_home").scrollTop(parent.$("#flex_props1_home")[0].scrollHeight);
                                        canvas.data.lines.map((item,i) => {
                                            if(item.id == data.id){
                                                canvas.data.lines.splice(i,1)
                                                canvas.render();
                                                setTimeout(function () {
                                                    selected = null;
                                                    selNodes = null;
                                                });
                                            }
                                        })
                                    }
                                } else {
                                    parent.$('.noticeList').append(`<li>${parent.getTime()}【算法连线】只能输出连接输入！ </li>`)
                                    parent.toastr.info(`【算法连线】只能输出连接输入！` )
                                    parent.$("#flex_props1_home").scrollTop(parent.$("#flex_props1_home")[0].scrollHeight);
                                    canvas.data.lines.map((item,i) => {
                                        if(item.id == data.id){
                                            canvas.data.lines.splice(i,1)
                                            canvas.render();
                                            setTimeout(function () {
                                                selected = null;
                                                selNodes = null;
                                            });
                                        }
                                    })
                                }
                            }
                            function isType(fromType,toType,value){
                                let flag =false
                                
                                if(fromType == toType){
                                    flag = true
                                }else if(value){
                                    fromType = typeof(value)
                                }else{
                                    var typeArr1 =["byte","int","long","short","float","double","number"];
                                    var typeArr2 = ["char","String"] 
                                    if(typeArr1.includes(fromType) && typeArr1.includes(toType)){
                                        flag = true
                                    }
                                    if(typeArr2.includes(fromType) && typeArr2.includes(toType)){
                                        flag = true
                                    }
                                    
                                }
                                return flag
                            }
                            let ruleTypes
                            if(window.canvasNowId == "canvas0"){
                                ruleTypes =window.bigData.ruleType
                            }else{
                                ruleTypes =parent.$('#'+window.top.canvasNowId)[0].contentWindow.bigData.ruleType
                            }
                            if(ruleTypes== "edit"){
                                if(window.canvasNowId == "canvas0"){
                                    window.isRuleNow = false
                                }else{
                                    parent.$('#'+window.top.canvasNowId)[0].contentWindow.isRuleNow = false
                                }
                            }
                            break;
                        case 'delete':
                            console.log(data)
                            if(window.canvasNowId == "canvas0"){
                                if(data.nodes.length==0 && data.lines.length == 0){
                                    parent.$('.noticeList').append(`<li>${parent.getTime()} 请选择要删除的节点或者线！ </li>`)
                                    toastr.info(`请选择要删除的节点或者线！` )
                                    $("#flex_props1_home").scrollTop($("#flex_props1_home")[0].scrollHeight);
                                    return
                                }
                            }else{
                                data.nodes = parent.$('#'+window.top.canvasNowId)[0].contentWindow.selNodes
                                data.lines = parent.$('#'+window.top.canvasNowId)[0].contentWindow.selLines 
                                if( data.nodes == null && data.lines == null){
                                    parent.$('.noticeList').append(`<li>${parent.getTime()} 请选择要删除的节点或者线！ </li>`)
                                    toastr.info(`请选择要删除的节点或者线！` )
                                    $("#flex_props1_home").scrollTop($("#flex_props1_home")[0].scrollHeight);
                                    return
                                }
                            }
                            try {
                                if(window.canvasNowId == "canvas0"){
                                    delete  window.Topology.tools[data.nodes[0].id]
                                }else{
                                    delete  parent.$('#'+window.top.canvasNowId)[0].contentWindow.Topology.tools[data.nodes[0].id]
                                   
                                }
                              
                            }catch (e) {
                                console.log(e);
                            }
                           if(window.canvasNowId != "canvas0" && parent.$('#'+window.top.canvasNowId)[0].contentWindow.selLines){
                                parent.$('#'+window.top.canvasNowId)[0].contentWindow.canvas.data.lines.map((item,i)=>{
                                    if(item.id == parent.$('#'+window.top.canvasNowId)[0].contentWindow.selLines[0].id){
                                        debugger
                                        parent.$('#'+window.top.canvasNowId)[0].contentWindow.canvas.data.lines.splice(i,1)
                                        parent.$('#'+window.top.canvasNowId)[0].contentWindow.canvas.render();
                                    }
                                })
                           }
                            if(data.nodes){
                                data.nodes.map(index=>{
                                let length , canvas ;
                                if(window.canvasNowId == "canvas0"){
                                    length=window.canvas.data.nodes.length;
                                    canvas = window.canvas
                                }else{
                                    length=parent.$('#'+window.top.canvasNowId)[0].contentWindow.canvas.data.nodes.length;
                                    canvas = parent.$('#'+window.top.canvasNowId)[0].contentWindow.canvas
                                }
                               
                                let deletedata =[];
                                let deleteLine = []
                                //删除大方块同时删除大方块的子元素（小方块）
                                for(let i =0;i < length; i++){
                                    if(canvas.data.nodes[i].childStand){
                                        if(index.id == canvas.data.nodes[i].childStand.fUUid) {    
                                            deletedata.push(canvas.data.nodes[i].id)  
                                        }
                                    }
                                }
                                  //删除大方块同时删除与大方块的子元素有关的线
                                for(let j=0;j<canvas.data.lines.length;j++){
                                    deletedata.map(lineId=>{
                                        if(canvas.data.lines[j].from.id == lineId || canvas.data.lines[j].to.id == lineId ){
                                            deleteLine.push(canvas.data.lines[j].id)
                                        }
                                    })
                                }
                                deleteLine.map(_lineId=>{
                                    canvas.data.lines.map((lineDelId,k)=>{
                                        if(_lineId == lineDelId.id){
                                            if(window.bigData.ruleType == "edit"){
                                                window.responseActionDatas.map(xian =>{
                                                    let formId = lineDelId.from.id.substr((lineDelId.from.id.indexOf('---')-36),36)
                                                    let toId = lineDelId.to.id.substr((lineDelId.to.id.indexOf('---')-36),36)
                                                    if(xian.parametersID ==toId && xian.preParametersID == formId){                                                      
                                                        $.ajax({
                                                            url: urlConfig.host + '/algorithmRule/delOneInterfaceRole',
                                                            type:"get",
                                                            data: {interfaceRoueId :xian.id},
                                                            success(data) {
                                                                if(data == true){
                                                                    if(window.canvasNowId == "canvas0"){
                                                                        window.isRuleNow = false
                                                                    }else{
                                                                        window.frames[canvasNowId].contentWindow.isRuleNow = false
                                                                    }
                                                                    parent.$('.noticeList').append(`<li>${parent.getTime()}【算法】删除成功，请暂存规则！ </li>`)
                                                                    toastr.success(`【算法】删除成功，请暂存规则！` )
                                                                    $("#flex_props1_home").scrollTop($("#flex_props1_home")[0].scrollHeight);
                                                                    canvas.render();
                                                                }
                                                            }
                                                        })
                                                    }
                                                })
                                            }
                                            canvas.data.lines.splice(k,1); 
                                            canvas.render();
                                        }
                                    })
                                })
                                deletedata.map(index=>{
                                    canvas.data.nodes.map((_iddel,i)=>{
                                        if(index==_iddel.id){
                                            canvas.data.nodes.splice(i,1);
                                            canvas.render();
                                        }
                                    })   
                                })
                                if(window.canvasNowId == "canvas0"){
                                   
                                }else{
                                    canvas.data.nodes.map((_iddel,i)=>{
                                        if(data.nodes[0].id==_iddel.id){
                                            canvas.data.nodes.splice(i,1);
                                            canvas.render();
                                        }
                                    }) 
                                }
                                if(window.bigData.ruleType == "edit"){
                                    //从数据库删除大方块的数据
                                    $.ajax({
                                        type:"get",
                                        dataType: "json",
                                        url:urlConfig.host+'/algorithmRule/delTableOperatorinterface',
                                        contentType: "application/json;charset=UTF-8",
                                        data:{
                                            operatorinterfaceId:index.id
                                        },
                                        success: function(data) {
                                            if(data == true){
                                                if(window.canvasNowId == "canvas0"){
                                                    window.isRuleNow = false
                                                }else{
                                                    window.frames[canvasNowId].contentWindow.isRuleNow = false
                                                }
                                                parent.$('.noticeList').append(`<li>${parent.getTime()}【算法】删除成功，请暂存规则！ </li>`)
                                                parent.toastr.success(`【算法】删除成功，请暂存规则！` )
                                                $("#flex_props1_home").scrollTop($("#flex_props1_home")[0].scrollHeight);
                                                canvas.render();
                                            }
                                        }
                                    })
                                }                          
                            })
                            }
                            
                            
                            $("#flex_props_home").removeClass("hidden");
                            $("#flex_props_node").addClass("hidden");
                            break;
                        // case 'resize':
                        //     if (!this.mouseMoving) {
                        //         this.mouseMoving = true;
                        //         this.workspace.nativeElement.scrollLeft = this.workspace.nativeElement.scrollWidth;
                        //         this.workspace.nativeElement.scrollTop = this.workspace.nativeElement.scrollHeight;
                        //         setTimeout(function() {
                        //             this.mouseMoving = false;
                        //         }, 2000);
                        //     }
                        //     break;
                        // case 'scale':
                        //     Store.set('scale', data);
                        //     break;
                        // case 'locked':
                        //     Store.set('locked', data);
                        //     break;
                        case 'dblclick':
                            $("#topo_canvas textarea").attr({"readonly":"readonly"}).css({
                                "z-index":"-10",
                                "background": "none",
                                "border": "none"
                            })
                            let currId = data.data.sid;
                            data.hideInput = true
                            $.ajax({
                                url:urlConfig.host+'/operatorMaintenance/getAlgorithmById',
                                data:{algthId:currId},
                                success: function(dataAl) {
                                    parent.$(".actionSelected2").empty();
                                    parent.$('.ruleActionZZ').text(dataAl.tableAlgorithm.algorithmauthor)
                                    parent.$('.ruleActionMC').text(dataAl.tableAlgorithm.algorithmname).attr({"title":dataAl.tableAlgorithm.algorithmname})
                                    parent.$('.ruleActionMS').text(dataAl.tableAlgorithm.des).attr({"title":dataAl.tableAlgorithm.des})
                                    let str =`` ;
                                    let ruleType ,editRuleId
                                    if(window.canvasNowId == "canvas0"){
                                        ruleType = window.bigData.ruleType
                                        editRuleId = window.bigData.editRuleId 
                                   }else{
                                        ruleType = parent.$('#'+window.top.canvasNowId)[0].contentWindow.bigData.ruleType
                                        editRuleId =parent.$('#'+window.top.canvasNowId)[0].contentWindow.bigData.editRuleId
                                   }  
                                    if(ruleType  == "edit"){
                                        $.ajax({
                                            url: urlConfig.host + '/algorithmRule/getAlgorithmRuleById',
                                            type:"get",
                                            data: {Id: editRuleId},
                                            success(datagz) {
                                                let isNewSZ = true
                                                datagz.operatorInterfaceDataModels.map(item=>{
                                                    let nowLists = []
                                                    if(window.canvasNowId == "canvas0"){
                                                        if(self.tools[data.id].children.length > dataAl.tableFuncs.length){
                                                            nowLists = self.tools[data.id].children
                                                        }else{
                                                            nowLists = dataAl.tableFuncs
                                                        }
                                                    }else{
                                                        if( parent.$('#'+window.top.canvasNowId)[0].contentWindow.Topology.tools[data.id].children.length > dataAl.tableFuncs.length){
                                                            nowLists = parent.$('#'+window.top.canvasNowId)[0].contentWindow.Topology.tools[data.id].children
                                                        }else{
                                                            nowLists = dataAl.tableFuncs
                                                        }
                                                    }
                                                    
                                                    if(item.id == data.id&&item.tableInterfaceparametersList.length >= nowLists.length){
                                                        isNewSZ =false
                                                        item.tableInterfaceparametersList.map(inter=>{
                                                            dataAl.tableFuncs.map(index =>{ 
                                                                if(inter.parameterssources == index.id){
                                                                    str +=`<div class="actionInfo" data-uuid='${inter.id}' Funcs-id='${index.id}' data-name='${index.varname}' data-title='${inter.remark}' data-parametername='${index.parametername}'>`
                                                                    if(inter.inorout == 1){
                                                                        str+=`<input value="输出" class="actionSelected1" disabled>  `
                                                                    }else{
                                                                        str+= `<input value="输入" class="actionSelected1" disabled>  `
                                                                    }
                                                                        str+=` <input value="${index.parametername}"  class="varNameInput" disabled>`
                                                                    if(index.vartype == 1 || index.vartype == "基本类型"){
                                                                        str+=`<input value="基本类型" class="actionSelected2" disabled>`
                                                                    }else if(index.vartype == 2 || index.vartype == "常量"){
                                                                        str+=`<input value="常量" class="actionSelected2" disabled>`
                                                                    }  else{
                                                                        str+=`<input value="模型" class="actionSelected2" disabled>`
                                                                    } 
                                                                        str+= `<input value="${index.valvalue}" id="varTypeInput" disabled>`
                                                                    if(inter.remark == "xin"){
                                                                        str+=`<button type="button" onclick="reduceButton(event)">x</button> `
                                                                    }      
                                                                                                                           
                                                                        str+=`</div>`     
                                                                    parent.$('.ruleContentDiv').html(str)
                                                                }

                                                                
                                                            })
                                                        })
                                                       
                                                    }
                                                    if(item.id == data.id&&item.tableInterfaceparametersList.length < nowLists.length){ 
                                                        isNewSZ =false
                                                        nowLists.map(index =>{                                                           
                                                            str +=`<div class="actionInfo" data-uuid='${index.uuid}' Funcs-id='${index.id}' data-name='${index.varname}' data-title='${inter.remark}' data-parametername='${index.parametername}'>`
                                                            if(index.inorout == 1){
                                                                str+=`<input value="输出" class="actionSelected1" disabled>  `
                                                            }else{
                                                                str+= `<input value="输入" class="actionSelected1" disabled>  `
                                                            }
                                                                str+=` <input value="${index.parametername}"  class="varNameInput" disabled>`
                                                            if(index.vartype == 1 ||index.vartype == "基本类型" ){
                                                                str+=`<input value="基本类型" class="actionSelected2" disabled>`
                                                            }else if(index.vartype == 2 || index.vartype == "常量" ){
                                                                str+=`<input value="常量" class="actionSelected2" disabled>`
                                                            }  else{
                                                                str+=`<input value="模型" class="actionSelected2" disabled>`
                                                            } 
                                                                str+= `<input value="${index.valvalue}" id="varTypeInput" disabled>`  
                                                                if(index.remark == "xin"){
                                                                    str+=`<button type="button" onclick="reduceButton(event)">x</button>`
                                                                }                                               
                                                                str+=`</div>`                                                           
                                                                parent.$('.ruleContentDiv').html(str)
                                                        }) 
                                                    }
                                                })
                                                if(isNewSZ){
                                                    dataAl.tableFuncs.map(index =>{               
                                                        str +=`<div class="actionInfo" data-uuid='${index.id}' Funcs-id='${index.id}' data-name='${index.varname}' data-title='${index.remark}' data-parametername='${index.parametername}'>`
                                                        if(index.inorout == 1){
                                                            str+=`<input value="输出" class="actionSelected1" disabled>  `
                                                        }else{
                                                            str+= `<input value="输入" class="actionSelected1" disabled>  `
                                                        }
                                                            str+=` <input value="${index.parametername}"  class="varNameInput" disabled>`
                                                        if(index.vartype == 1 || index.vartype == "基本类型"){
                                                            str+=`<input value="基本类型" class="actionSelected2" disabled>`
                                                        }else if(index.vartype == 2 || index.vartype == "常量"){
                                                            str+=`<input value="常量" class="actionSelected2" disabled>`
                                                        }  else{
                                                            str+=`<input value="模型" class="actionSelected2" disabled>`
                                                        } 
                                                            str+= `<input value="${index.valvalue}" id="varTypeInput" disabled>   
                                                               <button type="button" onclick="reduceButton(event)">x</button>                                                    
                                                            </div>`     
                                                        

                                                        parent.$('.ruleContentDiv').html(str)
                                                    })
                                                }

                                                
                                            }
                                        })
                                    }else{  
                                        let NewTools = []  
                                        if(window.canvasNowId == "canvas0"){
                                            NewTools = self.tools[data.id].children
                                        }else{
                                            NewTools =  parent.$('#'+window.top.canvasNowId)[0].contentWindow.Topology.tools[data.id].children
                                        }                                                  
                                        NewTools.map((index,t) =>{
                                        if(index.remark == "xin"){
                                            str +=`<div class="actionInfo" data-uuid='${index.uuid}' Funcs-id='${index.id}' data-name='${index.varname}' data-title='${index.remark}' data-parametername='${index.parametername}'>`
                                                if(index.inorout == 1){
                                                    str+=`<input value="输出" class="actionSelected1" disabled>`
                                                }else{
                                                    str+= `<input value="输入" class="actionSelected1" disabled>`
                                                }
                                                str+=`<select class="varNameInput1"  style="margin-left: 10px;"> </select>`                                       
                                                if(index.vartype == "基本类型" || index.vartype == 1 ){
                                                str+=`<input value="基本类型" class="actionSelected2" disabled>`
                                                }else if(index.vartype == "常量"||  index.vartype == 2){
                                                    str+=`<input value="常量" class="actionSelected2" disabled>`
                                                } else{
                                                    str+=`<input value="模型" class="actionSelected2" disabled>`
                                                } 
                                                str+=`<input value="${index.valvalue}" id="varTypeInput" disabled>   
                                                <button type="button" onclick="reduceButton(event)">x</button>                                               
                                            </div>`  
                                        } else{
                                            str +=`<div class="actionInfo" data-uuid='${index.uuid}' Funcs-id='${index.id}' data-name='${index.varname}' data-title='${index.remark}' data-parametername='${index.parametername}'>`
                                            if(index.inorout == 1){
                                                str+=`<input value="输出" class="actionSelected1" disabled>  `
                                            }else{
                                                str+= `<input value="输入" class="actionSelected1" disabled>  `
                                            }
                                                str+=` <input value="${index.parametername}"  class="varNameInput" disabled>`
                                            if(index.vartype == 1 ||index.vartype == "基本类型" ){
                                                str+=`<input value="基本类型" class="actionSelected2" disabled>`
                                            }else if(index.vartype == 2 || index.vartype == "常量" ){
                                                str+=`<input value="常量" class="actionSelected2" disabled>`
                                            }  else{
                                                str+=`<input value="模型" class="actionSelected2" disabled>`
                                            } 
                                                str+= `<input value="${index.valvalue}" id="varTypeInput" disabled>                                                 
                                                </div>`              
                                        }
                                    })
                                    parent.$('.ruleContentDiv').html(str)
                                    dataAl.tableFuncs.map((s,i)=>{
                                        $('.actionSelected1').eq(i).find("option[value='"+s.inorout+"']").attr("selected",true);
                                        $('.actionSelected2').eq(i).find("option[value='"+s.vartype+"']").attr("selected",true);              
                                    })
                                    $(".actionSelected2").off("change").on("change",()=>{
                                        if($(".actionSelected2").val() == "2"){
                                            $("#varTypeInput").val("常量")
                                        }
                                        if($(".actionSelected2").val() == "3"){
                                            $("#varTypeInput").val("模型")
                                        }
                                        if($(".actionSelected2").val() == "1"){
                                            $("#varTypeInput").val($('.actionSelected2 option:selected').attr('datavalue'))
                                        }
                                    })
                                    let lstr1=`<option>请选择</option>`
                                    dataAl.tableFuncs.map(item => {
                                        lstr1 += `<option value="${item.parametername}">${item.parametername}</option>`
                                    })
                                    NewTools.map((index,t)=>{
                                        if(index.remark == "xin"){
                                            parent.$('.ruleContentDiv .actionInfo').eq(t).find(".varNameInput1").html(lstr1)
                                            setTimeout(function () {
                                                parent.$('.ruleContentDiv .actionInfo').eq(t).find('.actionSelected1').find("option[value='"+index.inorout+"']").attr("selected",true);
                                                parent.$('.ruleContentDiv .actionInfo').eq(t).find('.varNameInput1').find("option[value='"+index.parametername+"']").attr("selected",true);
                                            }, 100);
                                            
                                        }
                                        
                                    })
                                    }
                                }
                            })
                            if(window.canvasNowId == "canvas0"){
                                    $('#ruleAct').show();
                                    self.dblclickNode = data
                            }else{
                                    $('#ruleAct', parent.document).show()
                                    window.Topology.dblclickNode = data
                                    // $('#'+window.canvasNowId)[0].contentWindow.Topology.dblclickNode = data
                                    parent.$('#'+window.top.canvasNowId)[0].contentWindow.Topology.dblclickNode = data
                            }
                            
                         
                        break;
                    }

                }
                canvasOptions.on = onMessage;
            }
        })    

    },
    // 下载图片
    down_png: function () {
        canvas.saveAsImage("PNG图片", "png", null);
    },
    // 初始化node
    initNode: function () {
        var self = this;
        $("#node_line_color").html("边框颜色");
        $("#flex_props_home").addClass("hidden");
        $("#flex_props_node").removeClass("hidden");
        $(".node-show").removeClass('hidden');
        $(".line-show").addClass('hidden');
        //x轴坐标
        $("input[name=node_x]").val(selected.data.rect.x);
        //y轴坐标
        $("input[name=node_y]").val(selected.data.rect.y);
        //高
        $("input[name=node_height]").val(selected.data.rect.height);
        //宽
        $("input[name=node_width]").val(selected.data.rect.width);
        //圆角
        $("input[name=borderRadius]").val(selected.data.borderRadius);
        //旋转
        $("input[name=rotate]").val(selected.data.rotate);
        // $("input[name=rotate]").val(selected.data.dash);
        //边框样式
        var dash = selected.data.dash;
        self.onClickDash(dash, dash);
        //边框颜色
        $("input[name=strokeStyle]").val(selected.data.strokeStyle);
        $("input[name=strokeStyle]").next().children().css("background-color", selected.data.strokeStyle);
        //线宽
        $("input[name=lineWidth]").val(selected.data.lineWidth);
        //背景颜色
        $("input[name=fillStyle]").val(selected.data.fillStyle);
        $("input[name=fillStyle]").next().children().css("background-color", selected.data.fillStyle);
        //背景样式
        // var bkType =  selected.data.bkType;
        // if (bkType == null) {
        //     bkType = 0;
        // }
        // $('#bkType option:contains("' + bkType + '")').attr("selected",true);
        // self.bkTypeChange();
         // 例子：锚点节点只允许in（作为终点）；上面锚点只允许out
    
        $("input[name=fontFamily]").val(selected.data.font.fontFamily);
        //字体颜色
        $("input[name=fontColor]").val(selected.data.font.color);
        $("input[name=fontColor]").next().children().css("background-color", selected.data.font.color);
        //大小
        $("input[name=fontSize]").val(selected.data.font.fontSize);
        //倾斜
        $("select[name=fontStyle] option:contains('" + selected.data.font.fontStyle + "')").attr("selected", true);
        //加粗
        $("select[name=fontWeight] option:contains('" + selected.data.font.fontWeight + "')").attr("selected", true);
        //水平对齐
        $("select[name=textAlign] option:contains('" + selected.data.font.textAlign + "')").attr("selected", true);
        //垂直对齐
        $("select[name=textBaseline] option:contains('" + selected.data.font.textBaseline + "')").attr("selected", true);
        //行高
        $("input[name=lineHeight]").val(selected.data.font.lineHeight);
        //自动匹配高度
        $("input[name=textMaxLine]").val(selected.data.font.textMaxLine);
        //内容
        $("textarea[name=text]").val(selected.data.text);
        //备注
        $("textarea[name=data]").val(selected.data.data == '' ? '' : (selected.data.data.remark == null ? '' :selected.data.data.remark));
        //关联知识
        $("#knowledge_name").html(selected.data.data == '' ? '暂无关联' : (selected.data.data.knowledge == null ? '暂无关联' :selected.data.data.knowledge.title));
        if ($("#knowledge_name").html() == "暂无关联") {
            $("#select_knowledge").removeClass("btn-success");
            $("#select_knowledge").addClass("btn-primary");
            $("#select_knowledge").html("选择知识");
            $("#delete_knowledge").addClass("hidden");
        } else {
            $("#select_knowledge").removeClass("btn-primary");
            $("#select_knowledge").addClass("btn-success");
            $("#select_knowledge").html("更改");
            $("#delete_knowledge").removeClass("hidden");
        }
        //左边距
        $("input[name=paddingLeft]").val(selected.data.paddingLeft);
        //右边距
        $("input[name=paddingRight]").val(selected.data.paddingRight);
        //上边距
        $("input[name=paddingTop]").val(selected.data.paddingTop);
        //下边距
        $("input[name=paddingBottom]").val(selected.data.paddingBottom);
        canvas.overflow()
    },

    // 初始化line
    initLine: function () {
        var self = this;
        $("#node_line_color").html("连线颜色");
        $("#flex_props_home").addClass("hidden");
        $("#flex_props_node").removeClass("hidden");
        $(".node-show").addClass('hidden');
        $(".line-show").removeClass('hidden');
        // $("input[name=from_x]").val(selected.data.from.x);
        // $("input[name=from_y]").val(selected.data.from.y);
        // $("input[name=to_x]").val(selected.data.to.x);
        // $("input[name=to_y]").val(selected.data.to.y);
        //起点箭头
        var fromArrow = selected.data.fromArrow;
        var fromArrow1 = selected.data.fromArrow;
        if (fromArrow == "") {
            fromArrow1 = "default";
        }
        self.onClickFromArrow(fromArrow, self.startLineStyle[fromArrow1]);
        //终点箭头
        var toArrow = selected.data.toArrow;
        var toArrow1 = selected.data.toArrow;
        if (toArrow == "") {
            toArrow1 = "default";
        }
        self.onClickToArrow(toArrow, self.startLineStyle[toArrow1]);
        //连线类型
        var lineType = selected.data.name;
        self.onClickName(lineType, self.lineTypeStyle[lineType], 1);
        //线条颜色
        $("input[name=strokeStyle]").val(selected.data.strokeStyle);
        $("input[name=strokeStyle]").next().children().css("background-color", selected.data.strokeStyle);
        //连线样式
        var dash = selected.data.dash;
        self.onClickDash(dash, dash);
        //线宽
        $("input[name=lineWidth]").val(selected.data.lineWidth);
        //备注
        // $("textarea[name=data]").val(selected.data.data == '' ? '' : selected.data.data.remark);
        canvas.overflow()
    },

    //字体
    // 改变背景颜色
    strokeStyleChange: function (self) {
        selected.data.strokeStyle = $(self).val();
        canvas.render();
    },
    // 改变渐变开始颜色
    gradientFromColorChange: function (self) {
        selected.data.gradientFromColor = $(self).val();
        canvas.render();
    },
    // 改变渐变开始颜色
    fontStyleChange: function (self) {
        selected.data.font.fontStyle = $(self).val();
        canvas.render();
    },
    // 改变渐变开始颜色
    fontStyleChange: function (self) {
        selected.data.font.fontStyle = $(self).val();
        canvas.render();
    },
    // 重置属性
    onChangeProp: function () {
        if (selected.type == "line") {
            //位置起点x
            selected.data.from.x = parseInt($("input[name=from_x]").val());
            //位置起点y
            selected.data.from.y = parseInt($("input[name=from_y]").val());
            //位置终点x
            selected.data.to.x = parseInt($("input[name=to_x]").val());
            //位置终点y
            selected.data.to.y = parseInt($("input[name=to_y]").val());
        }
        if (selected.type == "node") {
            //x轴坐标
            selected.data.rect.x = parseInt($("input[name=node_x]").val());
            //y轴坐标
            selected.data.rect.y = parseInt($("input[name=node_y]").val());
            //高
            selected.data.rect.height = parseInt($("input[name=node_height]").val());
            //宽
            selected.data.rect.width = parseInt($("input[name=node_width]").val());
            //圆角
            selected.data.borderRadius = parseFloat($("input[name=borderRadius]").val());
            //旋转
            selected.data.rotate = parseInt($("input[name=rotate]").val());
            //背景颜色
            selected.data.fillStyle = $("input[name=fillStyle]").val();
            //左边距
            selected.data.paddingLeft = $("input[name=paddingLeft]").val();
            //右边距
            selected.data.paddingRight = $("input[name=paddingRight]").val();
            //上边距
            selected.data.paddingTop = $("input[name=paddingTop]").val();
            //下边距
            selected.data.paddingBottom = $("input[name=paddingBottom]").val();
        }
        //线条颜色
        selected.data.strokeStyle = $("input[name=strokeStyle]").val();
        //线宽
        selected.data.lineWidth = parseInt($("input[name=lineWidth]").val());
        //背景样式
        // var bkType =  selected.data.bkType;
        // if (bkType == null) {
        //     bkType = 0;
        // }
        // $('#bkType option:contains("' + bkType + '")').attr("selected",true);
        // $("input[name=fontFamily]").val('"Hiragino Sans GB", "Microsoft YaHei", "Helvetica Neue", Helvetica, Arial');
        // self.bkTypeChange();
        //字体更改
        selected.data.font.fontFamily = $("input[name=fontFamily]").val();
        //字体颜色
        selected.data.font.color = $("input[name=fontColor]").val();
        //字体大小
        selected.data.font.fontSize = parseInt($("input[name=fontSize]").val());
        //字体倾斜
        selected.data.font.fontStyle = $("select[name=fontStyle]").val();
        //字体加粗
        selected.data.font.fontWeight = $("select[name=fontWeight]").val();
        //水平对齐
        selected.data.font.textAlign = $("select[name=textAlign]").val();
        //垂直对齐
        selected.data.font.textBaseline = $("select[name=textBaseline]").val();
        //行高
        selected.data.font.lineHeight = parseFloat($("input[name=lineHeight]").val());
        //最大行数
        selected.data.font.textMaxLine = parseInt($("input[name=textMaxLine]").val());
        selected.data.text = $("textarea[name=text]").val();
        //备注
        selected.data.data = {};
        selected.data.data.remark = $("textarea[name=data]").val();
        //更新数据（数据不完整，如果是node，需要传node；如果是连线，不用传参数）
        if (selected.type == "node") {
            canvas.updateProps(selected.data);
        } else {
            canvas.updateProps();
        }
        //重绘画布
        canvas.render();
    },
    fillStyleChange: function (self) {
        selected.data.fillStyle = $(self).val();
        canvas.render();
    },
    // 比较id
    compareId: function (dom, id) {
        var domNode = dom.target;
        for (var i = 0; i < 4; i++) {
            if (id == domNode.id) {
                return true;
            }
            if (domNode.parentNode == null) {
                return false;
            }
            domNode = domNode.parentNode;
        }
        return false;
    },
    // 起止箭头更改
    onClickFromArrow: function (arrow, index) {
        var sum = 0;
        //更改选择框显示的箭头
        $("#start_line_head").children().each(function (e) {
            if (index == sum) {
                $(this).removeClass("hidden");
            } else {
                $(this).addClass("hidden");
            }
            sum++;
        })
        //设置对应连线的箭头
        selected.data.fromArrow = arrow;
        //重绘画布
        canvas.render()
    },
    // 箭头终点更改
    onClickToArrow: function (arrow, index) {
        var sum = 0;
        //显示选择关系
        // $("#selectRela").show()
        //更改选择框显示的箭头
        $("#end_line_head").children().each(function (e) {
            if (index == sum) {
                $(this).removeClass("hidden");
            } else {
                $(this).addClass("hidden");
            }
            sum++;
        })
        //设置对应连线的箭头
        selected.data.toArrow = arrow;
        //重绘画布
        canvas.render()
    },
    // 连线类型更改
    onClickName: function (arrow, index, type) {
        var sum = 0;
        //更改选择框显示的箭头
        $("#line_style_head").children().each(function (e) {
            if (index == sum) {
                $(this).removeClass("hidden");
            } else {
                $(this).addClass("hidden");
            }
            sum++;
        });
        if (type == null) {
            //设置对应连线的箭头
            selected.data.name = arrow;
            //更新数据（数据不完整，如果是node，需要传node；如果是连线，不用传参数）
            canvas.updateProps();
            //重绘画布
            canvas.render();
        }
    },
    // 连线样式更改
    onClickDash: function (dash, index) {
        var sum = 0;
        //更改选择框显示的箭头
        $("#line_type_head").children().each(function (e) {
            if (index == sum) {
                $(this).removeClass("hidden");
            } else {
                $(this).addClass("hidden");
            }
            sum++;
        })
        //设置对应连线的箭头
        selected.data.dash = dash;
        //重绘画布
        canvas.render()
    },
    // 拖动node开始时设定该图形的参数
    onDragStart: function (event, node) {

        if(window.canvasNowId =="canvas0"){
            Topology.addInlist = []
            Topology.addOutlist = []
            $.ajax({
                url:urlConfig.host+'/operatorMaintenance/getAlgorithmById',
                data:{algthId:node.id},
                success: function(data) {
                    data.tableFuncs.map((item) =>{
                        if(item.inorout == 0){
                            Topology.addInlist.push(item)
                        }else{
                            Topology.addOutlist.push(item)
                        }
            
                    })
                }
            })
            event.dataTransfer.setData('text/plain', JSON.stringify(node.data));
        }else{
            parent.$('#'+window.canvasNowId)[0].contentWindow.Topology.addInlist =[]
            parent.$('#'+window.canvasNowId)[0].contentWindow.Topology.addOutlist =[]
            $.ajax({
                url:urlConfig.host+'/operatorMaintenance/getAlgorithmById',
                data:{algthId:node.id},
                success: function(data) {
                    data.tableFuncs.map((item) =>{
                        
                        if(item.inorout == 0){
                           
                            parent.$('#'+window.canvasNowId)[0].contentWindow.Topology.addInlist.push(item)
                        }else{
                            parent.$('#'+window.canvasNowId)[0].contentWindow.Topology.addOutlist.push(item)
                        }
            
                    })
                }
            })
            event.dataTransfer.setData('text/plain', JSON.stringify(node.data));
        }
        
    },
    // 置顶
    onTops: function () {
   
    },
    // 置顶
    onTop: function () {
        if (!selNodes) {
            return;
        }
        for (var item in selNodes) {
            canvas.top(item);
        }
        canvas.render();
    },
    // 置底
    onBottom: function () {
        if (!selNodes) {
            return;
        }
        for (var item in c) {
            canvas.bottom(item);
        }
        canvas.render();
    },
    // 组合
    onCombine: function () {
        if (!selNodes || selNodes.length < 2) {
            return;
        }
        canvas.combine(selNodes);
    },
    // 取消组合
    onUncombine: function () {
        if (!selNodes || selNodes.length > 1) {
            return;
        }
        canvas.uncombine(selNodes[0]);
        canvas.render();
    },
    parsew:function(){
        var ss = JSON.parse(ww)
        ss.nodes.map(data => {
            canvas.addNode(data)
        })
        
    },
    // 锁定
    onLock: function () {
        console.log(locked)
        locked = !locked;
        if (selected.type === 'multi') {
            if (selected.data.nodes) {
                for (var item in selected.data.nodes) {
                    item.locked = locked;
                }
            }
            if (selected.data.lines) {
                for (var item in selected.data.lines) {
                    item.locked = locked;
                }
            }
        } else {
            selected.data.locked = locked;
            // readonly = locked;
        }
        canvas.render(true);
    },
    onRender: function () {
        canvas.data.nodes = []
        canvas.data.lines = []
        canvas.render();
    
    },
    // 删除
    onDelete: function (e) {
        canvas.delete();

        if(window.top.canvasNowId == "canvas0"){
            globalActionDatas.map((s,i)=>{
                if(s.id == deleteLineDataId){
                    globalActionDatas.splice(i,1)
                }
            })
        }else{
            parent.$('#'+window.top.canvasNowId)[0].contentWindow.globalActionDatas.map((s,i)=>{
                if(s.id == parent.$('#'+window.top.canvasNowId)[0].contentWindow.deleteLineDataId){
                    parent.$('#'+window.top.canvasNowId)[0].contentWindow.globalActionDatas.splice(i,1)
                }
            })
        }

        if(window.top.canvasNowId == "canvas0"){
            if(responseActionDatas && responseActionDatas.length >0){
                responseActionDatas.map(s=>{
                    if(s.preParametersID + "AND" + s.parametersID == deleteLineDataId){
                        try {
                            $.ajax({
                                url: urlConfig.host + '/algorithmRule/delOneInterfaceRole',
                                type:"get",
                                data: {interfaceRoueId :s.id},
                                success(data) {
                                    if(window.canvasNowId == "canvas0"){
                                        window.isRuleNow = false
                                    }else{
                                        window.frames[canvasNowId].contentWindow.isRuleNow = false
                                    }
                                }
                            })
                        } catch (e) {

                        }
                    }
                })
            }
        }else{
            if( parent.$('#'+window.top.canvasNowId)[0].contentWindow.responseActionDatas &&  parent.$('#'+window.top.canvasNowId)[0].contentWindow.responseActionDatas.length >0){
                parent.$('#'+window.top.canvasNowId)[0].contentWindow.responseActionDatas.map(s=>{
                    if(s.preParametersID + "AND" + s.parametersID ==  parent.$('#'+window.top.canvasNowId)[0].contentWindow.deleteLineDataId){
                        try {
                            $.ajax({
                                url: urlConfig.host + '/algorithmRule/delOneInterfaceRole',
                                type:"get",
                                data: {interfaceRoueId :s.id},
                                success(data) {
                                    if(window.canvasNowId == "canvas0"){
                                        window.isRuleNow = false
                                    }else{
                                        window.frames[canvasNowId].contentWindow.isRuleNow = false
                                    }
                                }
                            })
                        } catch (e) {

                        }
                    }
                })
            }
        }
    },
    // 撤销
    undo: function () {
        // canvas.undo();
        let childList , canvas , cachesLists ,delList;
        if(window.canvasNowId == "canvas0"){
            childList = Object.values(window.Topology.tools)
            canvas = window.canvas
              //获取撤销的数据
            cachesLists =window.canvas.caches.list[window.canvas.caches.list.length-1]
            delList = cachesLists.nodes.slice(window.canvas.caches.index-1,window.canvas.caches.index) 
        }else{
            childList = Object.values(window.frames[canvasNowId].contentWindow.Topology.tools);
            canvas = parent.$('#'+window.top.canvasNowId)[0].contentWindow.canvas
            //获取撤销的数据
            cachesLists =window.frames[canvasNowId].contentWindow.canvas.caches.list[window.frames[canvasNowId].contentWindow.canvas.caches.list.length-1]
            delList = cachesLists.nodes.slice(window.frames[canvasNowId].contentWindow.canvas.caches.index-1,window.frames[canvasNowId].contentWindow.canvas.caches.index) 
        }
        if(delList.length >0){
            childList.map((item,j)=>{
                let delUUid = delList[0].id.substr((delList[0].id.indexOf('---')-36),36)
                if(item.children){
                    item.children.map((child,i)=>{
                        if(child.uuid == delUUid){
                            item.children.splice(i,1)
                        }
                    })
                }else{
                    if(item.uuid == delUUid){
                        childList.splice(j,1)
                    }
                }
            })
        }
      
        canvas.undo();
        canvas.render()

        canvas.data.nodes.map(item=>{
            if(!item.childStand){
                item.anchors.map((obj,i) => {
                    obj.x = 0;
                    obj.y = 0;
                })
                item.rotatedAnchors.map((obj,i) => {
                    obj.x = 0;
                    obj.y = 0
                })
            }
        })
        canvas.render()
    },
    // 恢复
    redo: function () {
        canvas.redo();
        canvas.data.nodes.map(item=>{
            if(!item.childStand){
                item.anchors.map((obj,i) => {
                    obj.x = 0;
                    obj.y = 0;
                })
                item.rotatedAnchors.map((obj,i) => {
                    obj.x = 0;
                    obj.y = 0
                })
            }
        })
    },
    // 剪切
    cut: function () {
        canvas.cut();
    },
    // 复制
    copy: function () {
        canvas.copy();
    },
    // 粘贴
    parse: function () {
        canvas.parse();
    }
};
// window全局，这样别的地方方便调用
window.addAlgorithm = Topology.addAlgorithm;
window.addModel = Topology.addModel;
window.onDragStart = Topology.onDragStart;
window.onTop = Topology.onTop;
window.onTops = Topology.onTops;
window.onBottom = Topology.onBottom;
window.onCombine = Topology.onCombine;
window.onUncombine = Topology.onUncombine;
window.onLock = Topology.onLock;
window.onDelete = Topology.onDelete;
window.undo = Topology.undo;
window.redo = Topology.redo;
window.cut = Topology.cut;
window.copy = Topology.copy;
window.parse = Topology.parse;
window.onClickFromArrow = Topology.onClickFromArrow;
window.onClickToArrow = Topology.onClickToArrow;
window.onClickName = Topology.onClickName;
window.onClickDash = Topology.onClickDash;
window.strokeStyleChange = Topology.strokeStyleChange;
window.fillStyleChange = Topology.fillStyleChange;
window.gradientFromColorChange = Topology.gradientFromColorChange;
window.onChangeProp = Topology.onChangeProp;
window.parsew = Topology.parsew;
window.onRender = Topology.onRender;
// window.bkTypeChange = Topology.bkTypeChange;
// window.rechargeable = user.rechargeable;
// window.rectify = user.rectify;
// 这里是chnannel对象的执行入口。init 是初始化的意思
Topology.init();


