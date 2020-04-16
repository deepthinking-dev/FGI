package deepthinking.fgi.controller;

import deepthinking.fgi.domain.TableGroupdata;
import deepthinking.fgi.service.TableGroupdataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/4/16 17:14
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("group")
@Api(description = "06 分组维护API，对分组信息的维护")
public class TableGroupdataController {
    @Resource
    private TableGroupdataService tableGroupdataService;

    @GetMapping("/findAllGroupMessagesByType")
    @ApiOperation(value = "06-01 根据类型查询该类型下的所有分组信息", notes = "返回指定类型下的所有分组信息", httpMethod = "GET")
    @ApiImplicitParam(name = "type", value = "类型（模型 1，算法 2，规则 3）", dataType = "int", paramType = "query", required = true)
    public List<TableGroupdata> findAllGroupMessagesByType(int type){
        return tableGroupdataService.findAllGroupMessagesByType(type);
    }

    @GetMapping("/updtaTableGroupMessage")
    @ApiOperation(value = "06-02 修改分组信息", notes = "返回修改后的信息,返回null表示修改失败", httpMethod = "POST")
    public TableGroupdata updtaTableGroupMessage(@ApiParam @RequestBody TableGroupdata tableGroupdata){
        return tableGroupdataService.updtaTableGroupMessage(tableGroupdata);
    }
    @GetMapping("/saveTableGroupMessage")
    @ApiOperation(value = "06-04 新增分组信息", notes = "返回新增结果", httpMethod = "POST")
    public String saveTableGroupMessage(@ApiParam @RequestBody TableGroupdata tableGroupdata){
        int i=tableGroupdataService.saveTableGroupMessage(tableGroupdata);
        if(i==2){
            return "分组名称重复";
        }else if(i==1){
            return "新增成功";
        }else {
            return "新增失败";
        }
    }

    @GetMapping("/deleteTableGroupMessage")
    @ApiOperation(value = "06-03 根据ID删除分组消息", notes = "返回删除结果", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "分组ID", dataType = "int", paramType = "query", required = true)
    public String deleteTableGroupMessage(int id){
        int i=tableGroupdataService.deleteTableGroupMessage(id);
        if(i==2){
            return "该分组下有数据，无法删除";
        }else if(i==1){
            return "删除成功";
        }else {
            return "传入的ID无效";
        }
    }
}
