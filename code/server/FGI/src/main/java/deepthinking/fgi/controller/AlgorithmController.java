package deepthinking.fgi.controller;

import deepthinking.fgi.common.ResultDto;
import deepthinking.fgi.domain.TableAlgorithm;
import deepthinking.fgi.model.AlgorithmBaseInfo;
import deepthinking.fgi.model.AlgorithmModel;
import deepthinking.fgi.service.TableAlgorithmService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/23 15:43
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/operatorMaintenance")
@Api( description = "03 算法基本维护操作API,算法生成及操作页面相关接口")
public class AlgorithmController {

    @Resource
    TableAlgorithmService tableAlgorithmService;


    @GetMapping("/getAllAlgorithm")
    @ApiOperation(value = "03-01 根据分组获取所有算法,仅返回算法基本信息", notes = "返回算法基本信息", httpMethod = "GET")
    @ApiImplicitParam(name = "groupName", value = "分组名称", dataType = "string", paramType = "query", required = true)
    public List<AlgorithmBaseInfo> getAllAlgorithm(String groupName){
        return tableAlgorithmService.getAllAlgorithm(groupName);
    }

    @PostMapping("/addAlgorithm")
    @ApiOperation(value = "03-02 添加算法信息(包括参数信息)", notes = "返回添加结果", httpMethod = "POST")
    public ResultDto addAlgorithm(@ApiParam @RequestBody AlgorithmModel algorithmModel){
        ResultDto result=new ResultDto();
        int i= tableAlgorithmService.addAlgorithm(algorithmModel);
        result.setStatus(i);
        if(i==1){
            result.setMsg("新增成功");
        }else if(i==2){
            result.setMsg("算法名称重复");
        }else {
            result.setMsg("新增失败");
        }
        return result;
    }

    @GetMapping("/getAlgorithmById")
    @ApiOperation(value = "03-03 根据算法ID获取该算法相关的所有信息，包括参数以及参数关联算法信息", notes = "返回算法详细信息", httpMethod = "GET")
    @ApiImplicitParam(name = "algthId", value = "算法ID", dataType = "string", paramType = "query", required = true)
    public AlgorithmModel getAlgorithmById(String algthId){
        return tableAlgorithmService.getAlgorithmById(algthId);
    }

    @PostMapping("/modAlgorithmBaseInfoById")
    @ApiOperation(value = "03-04 修改算法基本信息", notes = "返回修改结果", httpMethod = "POST")
    public ResultDto modAlgorithmById(@ApiParam @RequestBody TableAlgorithm tableAlgorithm){
        ResultDto result=new ResultDto();
        int i= tableAlgorithmService.modAlgorithmBaseInfoById(tableAlgorithm);
        result.setStatus(i);
        if(i==1){
            result.setMsg("修改成功");
        }else {
            result.setMsg("修改失败");
        }
        return result;
    }
    @PostMapping("/modAlgorithmFuncsById")
    @ApiOperation(value = "03-05 修改算法参数信息", notes = "返回修改结果", httpMethod = "POST")
    public ResultDto modAlgorithmFuncsById(@ApiParam @RequestBody AlgorithmModel algorithmModel){
        ResultDto result=new ResultDto();
        int i= tableAlgorithmService.modAlgorithmFuncsById(algorithmModel);
        result.setStatus(i);
        if(i==1){
            result.setMsg("修改成功");
        }else if(i==2){
            result.setMsg("该算法已经用于某些规则中，无法修改参数信息");
        }else if(i==3){
            result.setMsg("算法名称不能重复");
        }else {
            result.setMsg("修改失败");
        }
        return result;
    }

    @GetMapping("/delAlgorithmById")
    @ApiOperation(value = "03-06 删除算法", notes = "返回删除结果", httpMethod = "GET")
    @ApiImplicitParam(name = "algthId", value = "算法ID", dataType = "string", paramType = "query", required = true)
    public ResultDto delAlgorithmById(String algthId){
        ResultDto result=new ResultDto();
        int i=tableAlgorithmService.delAlgorithmById(algthId);
        result.setStatus(i);
        if(i==0){
            result.setMsg("删除算法信息的算法ID不能为空");
        }else if(i==1){
            result.setMsg("删除成功");
        }else if(i==2){
            result.setMsg("算法与其他算法存在关联关系，无法删除");
        }
        return result;
    }
    @GetMapping("/updataStatus")
    @ApiOperation(value = "03-07 修改算法状态", notes = "返回修改结果", httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(name = "algthId", value = "算法ID", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "status", value = "状态（传入‘发布’与‘取消发布’）", dataType = "string", paramType = "query", required = true)})
    public boolean updataStatus(String algthId,String status) {
        return tableAlgorithmService.updataStatus(algthId,status);
    }

}
