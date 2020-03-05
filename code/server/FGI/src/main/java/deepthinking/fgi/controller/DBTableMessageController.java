package deepthinking.fgi.controller;

import deepthinking.fgi.service.TableModuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/19 20:47
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("module")
@Api(description = "02 获取连接数据库表相关信息API,模板维护及操作页面相关接口")
public class DBTableMessageController {
    @Resource
    private TableModuleService tableModuleService;

    @GetMapping("/findAllTableFromDB")
    @ApiOperation(value = "02-01 获取当前库的所有表（数据表）", notes = "返回数据表表名列表", httpMethod = "GET")
    public List<String> findAllTableFromDB(){
        return tableModuleService.findAllTableFromDB();
    }
    @GetMapping("/findAllFiledByTableName")
    @ApiOperation(value = "02-02 获取指定数据表的字段信息", notes = "返回该数据表所有字段信息", httpMethod = "GET")
    @ApiImplicitParam(name = "tableName", value = "数据表表名", dataType = "string", paramType = "query", required = true)
    public List<Map<String,Object>> findAllFiledByTableName(String tableName){
        return tableModuleService.findAllFiledByTableName(tableName);
    }
}
