package deepthinking.fgi.controller;

import com.alibaba.fastjson.JSONArray;
import deepthinking.fgi.common.ResultDto;
import deepthinking.fgi.domain.TableAlgorithm;
import deepthinking.fgi.domain.TableRole;
import deepthinking.fgi.model.AlgorithmModel;
import deepthinking.fgi.service.TableAlgorithmService;
import deepthinking.fgi.service.TableRoleService;
import deepthinking.fgi.util.FileUtils;
import deepthinking.fgi.util.JsonListUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    @ApiOperation(value = "03-01 获取公共的和个人的所有算子,仅返回算子基本信息", notes = "返回算子基本信息", httpMethod = "GET")
    @ApiImplicitParam(name = "username", value = "用户名（暂时传入null）", dataType = "string", paramType = "query", required = false)
    public List<TableAlgorithm> getAllAlgorithm(String username){
        return tableAlgorithmService.getAllAlgorithm(username);
    }

    @PostMapping("/addAlgorithm")
    @ApiOperation(value = "03-02 添加算子信息(包括参数信息)", notes = "返回添加结果", httpMethod = "POST")
    public boolean addAlgorithm(@ApiParam @RequestBody AlgorithmModel algorithmModel){
        return tableAlgorithmService.addAlgorithm(algorithmModel);
    }

    @GetMapping("/getAlgorithmById")
    @ApiOperation(value = "03-03 根据算子ID获取该算子相关的所有信息，包括参数以及参数关联算子信息", notes = "返回算法详细信息", httpMethod = "GET")
    @ApiImplicitParam(name = "algthId", value = "算子ID", dataType = "string", paramType = "query", required = true)
    public AlgorithmModel getAlgorithmById(String algthId){
        return tableAlgorithmService.getAlgorithmById(algthId);
    }

    @PostMapping("/modAlgorithmById")
    @ApiOperation(value = "03-04 修改算子信息（包括修改参数）", notes = "返回修改结果", httpMethod = "POST")
    public boolean modAlgorithmById(@ApiParam @RequestBody AlgorithmModel algorithmModel){
        return tableAlgorithmService.modAlgorithmById(algorithmModel);
    }

    @GetMapping("/delAlgorithmById")
    @ApiOperation(value = "03-05 删除算子", notes = "返回删除结果", httpMethod = "GET")
    @ApiImplicitParam(name = "algthId", value = "算子ID", dataType = "string", paramType = "query", required = true)
    public ResultDto delAlgorithmById(String algthId){
        ResultDto result=new ResultDto();
        int i=tableAlgorithmService.delAlgorithmById(algthId);
        result.setStatus(i);
        if(i==0){
            result.setMsg("删除算子信息的算子ID不能为空");
        }else if(i==1){
            result.setMsg("删除成功");
        }else if(i==2){
            result.setMsg("算子作为参数被其他算子关联，无法删除");
        }else if(i==3){
            result.setMsg("算子与其他算子存在关联关系，无法删除");
        }
        return result;
    }

}
