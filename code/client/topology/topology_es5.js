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
    tools: [
        {
            group: '基本模板',
            children: []
        },
    ],
    saveNode:[],
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
        toastr.info("需要保存的json：\n" + JSON.stringify(canvas.data));
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
            $("#canvas_menus").css("display", "none");
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
        $("#flex_canvas").bind("contextmenu", function () {
            
            //设置右键菜单
            if (selNodes != null) {
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
            //显示右键菜单
            $("#canvas_menus").css({
                "left": document.body.scrollLeft + event.clientX, "top":
                    document.body.scrollTop + event.clientY
            }).show();
            return false;
        });
        // 保存画布数据
        $(document).on("click", "#canvas_save", function () {
            self.saveKnowledgeMap();
        });
        // -------查询开始-------------
        $(document).on('click', '#search_knowledge_btn', function () {
            console.log("aaa");
            var table = $('#table_knowledge').DataTable();
            table.page('first').draw(false);
            // 点击查询的时候，数据表格数据重新加载。在重新加载的时候，从#query_form小获得查询条件。
            // $('#table').DataTable().ajax.reload();
        });
    },

    addAlgorithm(option){
        if(option.type == "算子"){
            $("#algorithmPage").append(`<div class="left-list" ondragstart="onDragStart(event,${JSON.stringify(option).replace(/\"/g, "'")})" draggable="true">
                <div class="left-list-tilte dbclickAlgorithm" style="height:50px;" AlgorithmId="${option.id}">${option.data.text}</div>
            </div>`);
        }
        if(option.type== "规则"){
            $("#rulePage").append(`<div class="left-list">
                    <input type="checkbox" class="ruleCheckbox" data-id='${option.id}'>
                    <div class="left-list-tilte">${option.data.text}</div>
                    <div class="left-list-event">
                    <div class='lkr-list-ediRule lkr-edit' data-id='${option.id}' data-moduleid='${option.moduleid}'>编辑规则</div>
                    <div class='lkr-list-delRule lkr-del' data-id='${option.id}' data-moduleid='${option.moduleid}'>删除规则</div>
                </div>
                </div>`);
            
        }
       
    },

    addModel(option){
        
        $(".moduleContent").append(`<div class="left-list"  >
                                <div class="left-list-tilte">${option.data.text}</div>
                                <div class="left-list-event">
                                    <div class='lkr-list-edit lkr-edit' data-id='${option.data.id}' >编辑模板</div>
                                    <div class='lkr-list-del lkr-del' data-id='${option.data.id}' >删除模板</div>
                                </div>
                            </div>`);
    },
    // 初始化画布
    initCanvas: function () {
        var self = this;
        console.log("initCanvas")
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
                        type:"算子",
                        data: {
                            id:item.tableAlgorithm.id+"算子",
                            text: item.tableAlgorithm.algorithmname,
                            rect: {
                                width: 200,
                                height: 50
                            },
                            parentId:item.tableAlgorithm.id,
                            font: {
                                fontFamily: 'Arial',
                                color: 'aqua',                           
                                textBaseline: 'top'
                            },
                            children:[],
                            paddingLeft: 10,
                            paddingRight: 10,
                            paddingTop: 10,
                            paddingBottom: 10,
                            borderRadius: 0.1,
                            name: 'rectangle',
                            fillStyle:'rgba(4,44,98,0.58)',
                            strokeStyle: '#4295ec',
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
                    "lineName": "curve",
                    "fromArrowType": "",
                    "toArrowType": "triangleSolid",
                    "scale": 1,
                    "locked": 0
                };
                var canvasOptions = {on: onMessage};
                canvas = new Le5leTopology.Topology('topo_canvas', canvasOptions);
                console.log(canvas)
                
                // 监听画布
                function onMessage(event, data) {
                    console.log(event,data,1111)
                    switch (event) {
                        case 'node':
                            selNodes = [data];
                            selected = {
                                "type": event,
                                "data": data
                            };
                            locked = data.locked;
                            self.initNode();
                            break;
                        case 'line':
                            selected = {
                                "type": event,
                                "data": data
                            };
                            window.currentId = `${data.from.id}_${data.id}_${data.to.id}`;
                            locked = data.locked;
                            self.initLine();
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
                            break;
                        case 'moveNodes':
                            canvas.data.lines.map(item => {
                                if(item.from.id == data[0].id||item.to.id == data[0].id){
                                    $(`#${item.id}`).css({
                                        top:(item.to.y + item.from.y)/2 +"px",
                                        left:(item.to.x + item.from.x)/2+"px"
                                    })
                                }
                            })
                            break    
                        case 'moveOut':
                            this.workspace.nativeElement.scrollLeft += 10;
                            this.workspace.nativeElement.scrollTop += 10;
                         
                            //去掉重复id的node（一个算子在一套规则中只能出现一次）
                            function unique(arr){         
                                for(var i=0; i<arr.length; i++){
                                    for(var j=i+1; j<arr.length; j++){
                                        if(arr[i].id==arr[j].id){         //第一个等同于第二个，splice方法删除第二个
                                            arr.splice(j,1);                                           
                                            j--;
                                            alert('同一个规则算子不能重复！')
                                        }
                                    }
                                }
                                return arr;
                            }
                            unique(canvas.data.nodes)
                            break;
                        case 'addNode':
                            selNodes = [data];
                            selected = {
                                "type": event,
                                "data": data
                            };
                            console.log(data,selected)
                            //存储编辑区数据
                            unique(canvas.data.nodes)
                            self.saveNode = unique(canvas.data.nodes)
                            // let nodeId = data.id
                            // if(nodeId.indexOf('模板') != -1){
                            //     toastr.info('新建算子')
                            //     $("#suanfaType").css('display', "block");
                            //     window.bigData.formulaType = 'add'
                            //     let ModuleId =nodeId.substring(0,nodeId.length-2)
                            //     window.bigData.formulaModuleId = ModuleId
                            // }
                            
                            locked = data.locked;
                            self.initNode();
                            break;
                        case 'addLine':
                            data.strokeStyle = '#4295ec'
                            data.dash = 1
                            // data.name = '"polyline"'      
                            // console.log($("#ligature").show())
                            // data.text = '4545'
                            // console.log(data,canvas)
                            // window.currentId = `${data.from.id}_${data.id}_${data.to.id}`;
                            // $('#topo_canvas div').eq(0).append(`<span id='${data.from.id}_${data.id}_${data.to.id}' ></span>`)
                            //判断连线是否连接成功
                            if(!data.to.id){
                            canvas.data.lines.map((item,i) => {
                                if(item.id == data.id){
                                    canvas.data.lines.splice(i,1)
                                    toastr.info('操作失败！')
                                    
                                    canvas.render();
                                    setTimeout(function () {
                                        selected = null;
                                        selNodes = null;
                                    });
                                }
                            })
                            }else{
                                window.currentId = `${data.from.id}_${data.id}_${data.to.id}`;
                                $('#topo_canvas div').eq(0).append(`<span id='${data.from.id}_${data.id}_${data.to.id}' ></span>`)
                                $('#'+window.currentId).css({
                                    color: '#ffffff',
                                    position: 'absolute',
                                    top:(data.to.y + data.from.y)/2 +"px",
                                    left:(data.to.x + data.from.x)/2+"px"
                                })
                                // 选择关系弹框
                                $(`#selectRela`).css({
                                    top:(data.to.y + data.from.y)/2 +"px",
                                    left:(data.to.x + data.from.x)/2+"px"
                                })
                                selected = {
                                    "type": event,
                                    "data": data
                                };
                                locked = data.locked;
                                self.initLine();
                            }
                            break;
                        case 'delete':
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
                    }

                }
                canvasOptions.on = onMessage;
                canvas.open(data);
                // canvas.updateProps();
                // canvas.random();
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
        $("input[name=from_x]").val(selected.data.from.x);
        $("input[name=from_y]").val(selected.data.from.y);
        $("input[name=to_x]").val(selected.data.to.x);
        $("input[name=to_y]").val(selected.data.to.y);
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
        // console.log($(e).attr("class"))
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
        console.log(selNodes)
        console.log(arrow, index)
        // console.log($(e).attr("class"))
        var sum = 0;
        //显示选择关系
        $("#selectRela").show()
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
        // console.log($(e).attr("class"))
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
        // console.log($(e).attr("class"))
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
        myShape(Node)
        event.dataTransfer.setData('text/plain', JSON.stringify(node.data));
    },
    // 置顶
    onTops: function () {
        console.log(selNodes)
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
    // 删除
    onRender: function () {
        canvas.data.nodes = []
        canvas.data.lines = []
        canvas.render();
        console.log(canvas)
    },
    // 删除
    onDelete: function () {
        canvas.delete();
    },
    // 撤销
    undo: function () {
        canvas.undo();
    },
    // 恢复
    redo: function () {
        canvas.redo();
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
    },
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


