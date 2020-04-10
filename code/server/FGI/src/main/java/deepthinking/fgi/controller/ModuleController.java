package deepthinking.fgi.controller;

import deepthinking.fgi.domain.TableModule;
import deepthinking.fgi.domain.TableModulefield;
import deepthinking.fgi.service.TableModuleService;
import deepthinking.fgi.service.TableModulefieldService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/19 11:29
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("module")
@Api(description = "01 模板操作API,模板维护及操作页面相关接口")
public class ModuleController {
    @Resource
    private TableModuleService tableModuleService;
    @Resource
    private TableModulefieldService tableModulefieldService;

    @GetMapping("/GetAllModule")
    @ApiOperation(value = "01-01 获取所有模板", notes = "返回模板信息列表", httpMethod = "GET")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "pageNum", value = "分页序号", dataType = "int", paramType = "query", required = true),
//            @ApiImplicitParam(name = "pageSize", value = "单页大小", dataType = "int", paramType = "query", required = true)
//    })
    public List<TableModule> GetAllModule(){
        return tableModuleService.GetAllModule();
    }
    @GetMapping("/GetModuleGroup")
    @ApiOperation(value = "01-02 获取所有模板分组", notes = "返回模板分组信息列表", httpMethod = "GET")
    public List<String> GetModuleGroup (){
        return tableModuleService.GetModuleGroup();
    }
    @GetMapping("/GetModuleByGroupName")
    @ApiOperation(value = "01-03 获取指定模板分组下的所有模板", notes = "返回指定模板分组下的所有模板信息", httpMethod = "GET")
    @ApiImplicitParam(name = "moduleGroupName", value = "分组名称", dataType = "string", paramType = "query", required = true)
    public List<TableModule> GetModuleByGroupName(String moduleGroupName){
        return tableModuleService.GetModuleByGroupName(moduleGroupName);
    }
    @GetMapping("/getModuleById")
    @ApiOperation(value = "01-04 获取指定模板的详细信息", notes = "返回获取指定模板的详细信息", httpMethod = "GET")
    @ApiImplicitParam(name = "moduleId", value = "模板ID", dataType = "string", paramType = "query", required = true)
    public TableModule getModuleById(String moduleId){
        return tableModuleService.getModuleById(moduleId);
    }
    @PostMapping("/addModule")
    @ApiOperation(value = "01-05 新建一个全新的模板（可以连同模板关联字段一起新增）", notes = "返回新建结果 true 或者 false ", httpMethod = "POST")
    public boolean addModule (@ApiParam @RequestBody TableModule module){
        return tableModuleService.addModule(module);
    }
    @PostMapping("/modModuleById")
    @ApiOperation(value = "01-06 修改一个全新的模板（可以连同模板关联字段一起修改，系统自动判断需要删除和新增的字段）", notes = "返回修改结果 true 或者 false ", httpMethod = "POST")
    public boolean modModuleById (@ApiParam @RequestBody TableModule module){
        return tableModuleService.modModuleById(module);
    }
    @GetMapping("/delModuleById")
    @ApiOperation(value = "01-07 对指定的一个模板进行删除操作,此接口将删除模板所关联的所有字段信息", notes = "返回删除结果 true 或者 false ", httpMethod = "GET")
    @ApiImplicitParam(name = "moduleId", value = "模板ID", dataType = "string", paramType = "query", required = true)
    public boolean delModuleById (String moduleId){
        return tableModuleService.delModuleById(moduleId);
    }
    @PostMapping("/addModuleColumn")
    @ApiOperation(value = "01-08 对指定的一个模板添加一个字段信息", notes = "返回添加结果 true 或者 false ", httpMethod = "POST")
    public boolean addModuleColumn(@ApiParam @RequestBody TableModulefield column){
        return tableModulefieldService.addModuleColumn(column);
    }
    @GetMapping("/removeModuleColumn")
    @ApiOperation(value = "01-09 对指定的一个模板移除指定字段信息", notes = "返回删除结果 true 或者 false ", httpMethod = "GET")
    @ApiImplicitParam(name = "columnId", value = "字段所在记录ID", dataType = "string", paramType = "query", required = true)
    public boolean removeModuleColumn(String columnId){
        return tableModulefieldService.removeModuleColumn(columnId);
    }
    @GetMapping("/getModuleColumns")
    @ApiOperation(value = "01-10 获取指定模板包含的字段信息列表", notes = "返回指定模板包含的字段信息列表（无模板本身信息） ", httpMethod = "GET")
    @ApiImplicitParam(name = "moduleId", value = "模板ID", dataType = "string", paramType = "query", required = true)
    public List<TableModulefield> getModuleColumns(String moduleId){
        return tableModulefieldService.getModuleColumns(moduleId);
    }
    @GetMapping("/findTableModuleByName")
    @ApiOperation(value = "01-11 根据模型名称查询确定模型，用于赋值操作", notes = "返回确定的模型 ", httpMethod = "GET")
    @ApiImplicitParam(name = "name", value = "模型名称", dataType = "string", paramType = "query", required = true)
    public TableModule findTableModuleByName(String name){
        return tableModuleService.findTableModuleByName(name);
    }
}
