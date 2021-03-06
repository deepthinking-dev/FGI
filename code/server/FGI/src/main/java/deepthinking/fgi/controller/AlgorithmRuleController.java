package deepthinking.fgi.controller;

import com.alibaba.fastjson.JSONArray;
import deepthinking.fgi.domain.TableRole;
import deepthinking.fgi.model.AlgorithmRuleDataModel;
import deepthinking.fgi.model.AlgorithmRuleSaveDataModel;
import deepthinking.fgi.service.TableRoleService;
import deepthinking.fgi.util.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/24 11:20
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/algorithmRule")
@Api( description = "04 算法规则编辑和导入导出操作API,算法规则操作页面等相关接口")
public class AlgorithmRuleController {

    @Resource
    TableRoleService tableRoleService;


    @PostMapping("/readAlgorithmRuleFromFile")
    @ApiOperation(value = "04-01 导入算法规则", notes = "导入算法规则", httpMethod = "GET")
    @ApiImplicitParam(name = "file", value = "二进制文件", dataType = "file", paramType = "query", required = true)
    public List<TableRole> readAlgorithmRuleFromFile(@RequestParam("file") MultipartFile file){

        return tableRoleService.leadByTxt(FileUtils.MultipartFileToFile(file));
    }

    @GetMapping("/saveAlgorithmRule2File")
    @ApiOperation(value = "04-02 导出算法规则", notes = "导出算法规则", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "规则编号", dataType = "integer", paramType = "query", required = true)
    public void saveAlgorithmRule2File(Integer id, HttpServletResponse response){
        String filename = UUID.randomUUID().toString();
        String path = FileUtils.getConTextPath() + "/WEB-INF/" + filename;
        try {
            FileUtils.writeFile(path, JSONArray.toJSON(tableRoleService.GetTableExportData(id)).toString().getBytes());
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("算法规则导出.txt", "UTF-8"));
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (Exception ex) {
        } finally {
            try {
                File f = new File(path);
                f.delete();
            } catch (Exception e) {
            }
        }
    }


    @GetMapping("/getAllAlgorithmRule")
    @ApiOperation(value = "04-03 根据用户获取所有算法规则 username暂时不用", notes = "返回算法规则基本信息列表", httpMethod = "GET")
    @ApiImplicitParam(name = "username", value = "用户名（暂时不用传）", dataType = "string", paramType = "query", required = false)
    public List<TableRole> getAllAlgorithmRule(String username ){
        return tableRoleService.GetAllAlgorithmRule(username);
    }

    @GetMapping("/getAlgorithmRuleById")
    @ApiOperation(value = "04-04 根据规则编号获取算法规则(规则具体信息)", notes = "返回规则具体信息", httpMethod = "GET")
    @ApiImplicitParam(name = "Id", value = "规则ID", dataType = "string", paramType = "query", required = true)
    public AlgorithmRuleSaveDataModel getAlgorithmRuleById(String Id){
        return tableRoleService.getAlgorithmRuleById(Id);
    }

    @PostMapping("/saveAlgorithmRule")
    @ApiOperation(value = "04-05 新增算法规则（一起新增）", notes = "返回新增结果", httpMethod = "POST")
    public boolean saveAlgorithmRule(@ApiParam @RequestBody AlgorithmRuleSaveDataModel algorithmRuleSaveDataModel){
        return tableRoleService.saveAlgorithmRule(algorithmRuleSaveDataModel);
    }

    @PostMapping("/saveAlgorithmRuleOne")
    @ApiOperation(value = "04-06 新增算法规则（一步一步新增）", notes = "返回新增结果", httpMethod = "POST")
    public boolean saveAlgorithmRuleOne(@ApiParam @RequestBody AlgorithmRuleDataModel algorithmRuleDataModel){
        return tableRoleService.saveAlgorithmRuleOne(algorithmRuleDataModel);
    }

    @PostMapping("/saveAlgorithmRuleBase")
    @ApiOperation(value = "04-07 只新增规则基本信息", notes = "返回新增结果", httpMethod = "POST")
    public boolean saveAlgorithmRuleBase(@ApiParam @RequestBody TableRole tableRole){
        return tableRoleService.saveAlgorithmRuleBase(tableRole);
    }

    @PostMapping("/modAlgorithmRule")
    @ApiOperation(value = "04-08 修改算法规则(修改一个关系保存一次)", notes = "返回修改结果", httpMethod = "POST")
    public boolean modAlgorithmRule(@ApiParam @RequestBody AlgorithmRuleDataModel algorithmRuleDataModel){
        return tableRoleService.modAlgorithmRule(algorithmRuleDataModel);
    }

    @PostMapping("/modAlgorithmRuleBase")
    @ApiOperation(value = "04-09 只修改规则基本信息", notes = "返回修改结果", httpMethod = "POST")
    public boolean modAlgorithmRuleBase(TableRole tableRole){
        return tableRoleService.modAlgorithmRuleBase(tableRole);
    }

    @GetMapping("/delAlgorithmRuleById")
    @ApiOperation(value = "04-10 删除指定规则编号的算法规则", notes = "返回删除结果", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "规则ID", dataType = "string", paramType = "query", required = true)
    public boolean delAlgorithmRuleById(String id){
        return tableRoleService.delAlgorithmRuleById(id);
    }

    @GetMapping("/delTableAlgorithmrole")
    @ApiOperation(value = "04-11 根据算法关系ID删除一个关系", notes = "返回删除结果", httpMethod = "GET")
    @ApiImplicitParam(name = "algorithmroleId", value = "算法算子关系ID", dataType = "string", paramType = "query", required = true)
    public boolean delTableAlgorithmrole(String algorithmroleId){
        return tableRoleService.delTableAlgorithmrole(algorithmroleId);
    }
}


























