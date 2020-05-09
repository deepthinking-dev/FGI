package deepthinking.fgi.controller;

import deepthinking.fgi.domain.TableModule;
import deepthinking.fgi.domain.TableModulefield;
import deepthinking.fgi.service.TableModuleService;
import deepthinking.fgi.service.TableModulefieldService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/19 11:29
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("module")
@Api(description = "01 模型操作API,模型维护及操作页面相关接口")
public class ModuleController {
    @Resource
    private TableModuleService tableModuleService;
    @Resource
    private TableModulefieldService tableModulefieldService;

    @GetMapping("/GetAllModule")
    @ApiOperation(value = "01-01 获取所有模型", notes = "返回模型信息列表", httpMethod = "GET")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "pageNum", value = "分页序号", dataType = "int", paramType = "query", required = true),
//            @ApiImplicitParam(name = "pageSize", value = "单页大小", dataType = "int", paramType = "query", required = true)
//    })
    public List<TableModule> GetAllModule(HttpServletRequest request){
        return tableModuleService.GetAllModule(request);
    }
    @GetMapping("/GetModuleGroup")
    @ApiOperation(value = "01-02 获取所有模型分组", notes = "返回模型分组信息列表", httpMethod = "GET")
    public List<String> GetModuleGroup (){
        return tableModuleService.GetModuleGroup();
    }
    @GetMapping("/GetModuleByGroupName")
    @ApiOperation(value = "01-03 获取指定分组下的所有模模型", notes = "返回指定模型分组下的所有模型信息", httpMethod = "GET")
    @ApiImplicitParam(name = "moduleGroupName", value = "分组名称", dataType = "string", paramType = "query", required = true)
    public List<TableModule> GetModuleByGroupName(String moduleGroupName,HttpServletRequest request){
        return tableModuleService.GetModuleByGroupName(moduleGroupName,request);
    }
    @GetMapping("/getModuleById")
    @ApiOperation(value = "01-04 获取指定模型的详细信息", notes = "返回获取指定模型的详细信息", httpMethod = "GET")
    @ApiImplicitParam(name = "moduleId", value = "模型ID", dataType = "string", paramType = "query", required = true)
    public TableModule getModuleById(String moduleId){
        return tableModuleService.getModuleById(moduleId);
    }
    @PostMapping("/addModule")
    @ApiOperation(value = "01-05 新建一个全新的模型（可以连同模型关联字段一起新增）", notes = "返回新建结果 true 或者 false ", httpMethod = "POST")
    public boolean addModule (@ApiParam @RequestBody TableModule module,HttpServletRequest request){
        return tableModuleService.addModule(module,request);
    }
    @PostMapping("/modModuleById")
    @ApiOperation(value = "01-06 修改一个全新的模型（可以连同模型关联字段一起修改，系统自动判断需要删除和新增的字段）", notes = "返回修改结果 true 或者 false ", httpMethod = "POST")
    public boolean modModuleById (@ApiParam @RequestBody TableModule module){
        return tableModuleService.modModuleById(module);
    }
    @GetMapping("/delModuleById")
    @ApiOperation(value = "01-07 对指定的一个模型进行删除操作,此接口将删除模型所关联的所有字段信息", notes = "返回删除结果 true 或者 false ", httpMethod = "GET")
    @ApiImplicitParam(name = "moduleId", value = "模型ID", dataType = "string", paramType = "query", required = true)
    public boolean delModuleById (String moduleId){
        return tableModuleService.delModuleById(moduleId);
    }
    @PostMapping("/addModuleColumn")
    @ApiOperation(value = "01-08 对指定的一个模型添加一个字段信息", notes = "返回添加结果 true 或者 false ", httpMethod = "POST")
    public boolean addModuleColumn(@ApiParam @RequestBody TableModulefield column){
        return tableModulefieldService.addModuleColumn(column);
    }
    @GetMapping("/removeModuleColumn")
    @ApiOperation(value = "01-09 对指定的一个模型移除指定字段信息", notes = "返回删除结果 true 或者 false ", httpMethod = "GET")
    @ApiImplicitParam(name = "columnId", value = "字段所在记录ID", dataType = "string", paramType = "query", required = true)
    public boolean removeModuleColumn(String columnId){
        return tableModulefieldService.removeModuleColumn(columnId);
    }
    @GetMapping("/getModuleColumns")
    @ApiOperation(value = "01-10 获取指定模型包含的字段信息列表", notes = "返回指定模型包含的字段信息列表（无模型本身信息） ", httpMethod = "GET")
    @ApiImplicitParam(name = "moduleId", value = "模型ID", dataType = "string", paramType = "query", required = true)
    public List<TableModulefield> getModuleColumns(String moduleId){
        return tableModulefieldService.getModuleColumns(moduleId);
    }
    @GetMapping("/findTableModuleByName")
    @ApiOperation(value = "01-11 根据模型名称查询确定模型，用于赋值操作", notes = "返回确定的模型 ", httpMethod = "GET")
    @ApiImplicitParam(name = "name", value = "模型名称", dataType = "string", paramType = "query", required = true)
    public TableModule findTableModuleByName(String name){
        return tableModuleService.findTableModuleByName(name);
    }

    @GetMapping("/updataStatus")
    @ApiOperation(value = "01-12 修改模型的状态", notes = "返回修改结果", httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(name = "modelId", value = "模型ID", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "status", value = "状态 传入‘发布’或者‘取消发布’", dataType = "string", paramType = "query", required = true)})
    public boolean updataStatus(String modelId,String status) {
        return tableModuleService.updataStatus(modelId,status);
    }
}
