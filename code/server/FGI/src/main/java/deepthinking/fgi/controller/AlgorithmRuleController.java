package deepthinking.fgi.controller;

import deepthinking.fgi.domain.TableRole;
import deepthinking.fgi.model.InterfaceRoleDataModel;
import deepthinking.fgi.model.AlgorithmRuleSaveDataModel;
import deepthinking.fgi.model.OperatorInterfaceDataModel;
import deepthinking.fgi.model.FuncSaveData;
import deepthinking.fgi.model.xml.RuleXmlModel;
import deepthinking.fgi.service.TableRoleService;
import deepthinking.fgi.util.FileUploadUtil;
import deepthinking.fgi.util.FileUtils;
import deepthinking.fgi.util.XMLUtil;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    @ApiOperation(value = "04-01 导入算法规则", notes = "导入算法规则", httpMethod = "POST")
    @ApiImplicitParam(name = "file", value = "文件", dataType = "stream", paramType = "query", required = true)
    public RuleXmlModel readAlgorithmRuleFromFile(@RequestParam(value = "file") MultipartFile file) throws Exception {
        return tableRoleService.leadByTxt(FileUploadUtil.multipartFileToFile(file));
    }

    @GetMapping("/saveAlgorithmRule2File")
    @ApiOperation(value = "04-02 导出算法规则", notes = "导出算法规则", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "规则编号", dataType = "integer", paramType = "query", required = true)
    public void saveAlgorithmRule2File(Integer id, HttpServletResponse response){
        String filename = UUID.randomUUID().toString();
        String path = FileUtils.getProjectPath() + "/" + filename;
        System.out.println("path:" + path);
        try {
            XMLUtil.convertToXml(tableRoleService.GetTableExportData(id), path);
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("算法规则导出.xml", "UTF-8"));
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
    @ApiOperation(value = "04-03 根据分组获取该分组下的所有规则信息", notes = "返回算法规则基本信息列表", httpMethod = "GET")
    @ApiImplicitParam(name = "groupName", value = "分组名称", dataType = "string", paramType = "query", required = true)
    public List<TableRole> getAllAlgorithmRule(String groupName, HttpServletRequest request){
        return tableRoleService.GetAllAlgorithmRule(groupName,request);
    }

    @GetMapping("/getAlgorithmRuleById")
    @ApiOperation(value = "04-04 根据规则编号获取算法规则(规则具体信息)", notes = "返回规则具体信息", httpMethod = "GET")
    @ApiImplicitParam(name = "Id", value = "规则ID", dataType = "string", paramType = "query", required = true)
    public AlgorithmRuleSaveDataModel getAlgorithmRuleById(String Id){
        return tableRoleService.getAlgorithmRuleById(Id);
    }

    @PostMapping("/saveAlgorithmRule")
    @ApiOperation(value = "04-05 新增算法规则", notes = "返回新增的规则本身信息", httpMethod = "POST")
    public AlgorithmRuleSaveDataModel saveAlgorithmRule(@ApiParam @RequestBody AlgorithmRuleSaveDataModel algorithmRuleSaveDataModel,HttpServletRequest request){
        return tableRoleService.saveAlgorithmRule(algorithmRuleSaveDataModel,request);
    }

//    @PostMapping("/saveOperatorInterfaceData")
//    @ApiOperation(value = "04-06 新增接口信息(添加一个新增一个)", notes = "返回新增的接口本身信息", httpMethod = "POST")
//    public AlgorithmRuleSaveDataModel saveOperatorInterfaceData(@ApiParam @RequestBody AlgorithmRuleSaveDataModel algorithmRuleSaveDataModel){
//        return tableRoleService.saveOperatorInterfaceData(algorithmRuleSaveDataModel);
//    }

    @PostMapping("/modInterfaceRole")
    @ApiOperation(value = "04-07 修改接口信息", notes = "返回修改后的接口信息", httpMethod = "POST")
    public OperatorInterfaceDataModel modInterfaceRole(@ApiParam @RequestBody OperatorInterfaceDataModel operatorInterfaceDataModel){
        return tableRoleService.modInterfaceRole(operatorInterfaceDataModel);
    }

    @PostMapping("/modAlgorithmRule")
    @ApiOperation(value = "04-08 修改算法规则某条连线的信息", notes = "返回修改结果", httpMethod = "POST")
    public boolean modAlgorithmRule(@ApiParam @RequestBody InterfaceRoleDataModel interfaceRoleDataModel){
        return tableRoleService.modAlgorithmRule(interfaceRoleDataModel);
    }

    @PostMapping("/modAlgorithmRuleBase")
    @ApiOperation(value = "04-09 只修改规则基本信息", notes = "返回修改结果", httpMethod = "POST")
    public TableRole modAlgorithmRuleBase(@ApiParam @RequestBody TableRole tableRole){
        tableRoleService.modAlgorithmRuleBase(tableRole);
        return tableRole;
    }

    @GetMapping("/delAlgorithmRuleById")
    @ApiOperation(value = "04-10 删除指定规则编号的算法规则（整个规则全部删除）", notes = "返回删除结果", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "规则ID", dataType = "string", paramType = "query", required = true)
    public boolean delAlgorithmRuleById(String id){
        return tableRoleService.delAlgorithmRuleById(id);
    }

    @GetMapping("/delOneInterfaceRole")
    @ApiOperation(value = "04-11 根据接口关系ID删除一个关系（一条线）", notes = "返回删除结果", httpMethod = "GET")
    @ApiImplicitParam(name = "interfaceRoueId", value = "算法接口关系ID", dataType = "string", paramType = "query", required = true)
    public boolean delOneInterfaceRole(String interfaceRoueId){
        return tableRoleService.delOneInterfaceRole(interfaceRoueId);
    }

    @PostMapping("/saveNewCoordinate")
    @ApiOperation(value = "04-12 保存最新的规则下算子的坐标信息", notes = "返回保存结果", httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(name = "coordinateinfo", value = "坐标字符串", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "roleId", value = "规则ID", dataType = "string", paramType = "query", required = true)})
    public boolean saveNewCoordinate(String coordinateinfo, String roleId){
        if(roleId==null||roleId.equals("")){
            return false;
        }
        return tableRoleService.saveNewCoordinate(coordinateinfo,Integer.parseInt(roleId));
    }
    @PostMapping("/saveFunAction")
    @ApiOperation(value = "04-13 保存某个参数的动作信息，传入动作对象集合", notes = "返回保存结果", httpMethod = "POST")
    public boolean saveFunAction(@ApiParam @RequestBody FuncSaveData funcSaveData){
        return tableRoleService.saveFunAction(funcSaveData.getAlgorithmconditions(),funcSaveData.getInterfaceParametersID(),
                funcSaveData.getInterfaceRoleId(),funcSaveData.getActionRelation());
    }
    @GetMapping("/delTableOperatorinterface")
    @ApiOperation(value = "04-14 根据接口ID删除一个接口，连同该接口相关的线一起删除", notes = "返回删除结果", httpMethod = "GET")
    @ApiImplicitParam(name = "operatorinterfaceId", value = "接口ID", dataType = "string", paramType = "query", required = true)
    public boolean delTableOperatorinterface(String operatorinterfaceId){
        return tableRoleService.delTableOperatorinterface(operatorinterfaceId);
    }
    @GetMapping("/updataStatus")
    @ApiOperation(value = "04-15 修改规则的状态", notes = "返回修改结果", httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(name = "roleId", value = "规则ID", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "status", value = "状态 传入‘发布’或者‘取消发布’", dataType = "string", paramType = "query", required = true)})
    public boolean updataStatus(String roleId,String status) {
        return tableRoleService.updataStatus(roleId,status);
    }
}


























