package deepthinking.fgi.controller;

import deepthinking.fgi.service.ToOtherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@Api(description = "07 给第三发服务用的接口")
public class ToOtherController {
    @Resource
    private ToOtherService toOtherService;

    @GetMapping("/findAlgorithmMessage")
    @ApiOperation(value = "07-01 根据类别和状态获取数据信息", notes = "返回的map信息包含：ID，名称，描述，url等", httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(name = "status", value = "状态值", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "type", value = "类型", dataType = "string", paramType = "query", required = true)})
    public List<Map<String,Object>> findAlgorithmMessage(String status, String type){
        return toOtherService.findAlgorithmMessage(status,type);
    }

    @GetMapping("/updateDataStatus")
    @ApiOperation(value = "07-02 修改数据的审批状态", notes = "修改数据的审批状态", httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "类型", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "id", value = "数据ID", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "status", value = "状态值", dataType = "string", paramType = "query", required = true)})
    boolean updateDataStatus(String type,int id,String status){
        return toOtherService.updateDataStatus(type,id,status);
    }

}
