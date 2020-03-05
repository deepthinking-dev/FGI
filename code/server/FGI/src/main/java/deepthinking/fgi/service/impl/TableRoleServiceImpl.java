package deepthinking.fgi.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import deepthinking.fgi.dao.mapper.*;
import deepthinking.fgi.domain.*;
import deepthinking.fgi.model.AlgorithmModel;
import deepthinking.fgi.model.AlgorithmRuleDataModel;
import deepthinking.fgi.model.AlgorithmRuleSaveDataModel;
import deepthinking.fgi.service.TableAlgorithmService;
import deepthinking.fgi.service.TableRoleService;
import deepthinking.fgi.util.FileUtils;
import deepthinking.fgi.util.JsonListUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/18 16:58
 */
@Service("tableRoleService")
public class TableRoleServiceImpl extends BaseServiceImpl<TableRole,Integer> implements TableRoleService {

    private static Logger logger = LoggerFactory.getLogger(TableRoleServiceImpl.class);

    @Resource
    private TableRoleMapper roleMapper;
    @Resource
    private TableAlgorithmroleMapper algorithmroleMapper;
    @Resource
    private TableAlgorithmconditionMapper algorithmconditionMapper;
    @Resource
    private TableAlgorithmMapper tableAlgorithmMapper;
    @Resource
    private TableFuncMapper funcMapper;
    @Resource
    private TableAlgorithmService tableAlgorithmService;


    @Override
    public PageInfo<TableRole> pageFind(int pageNum, int pageSize, Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return null;
    }

    /**
     * 文件导入
     * @param filePath 文件地址
     * @Author 王若山
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<TableRole> leadByTxt(String filePath) {
        List<TableRole> roleList = null;
        List<TableAlgorithmrole> algorithmroleList = null;
        List<TableAlgorithm> algorithmList = null;
        try{
            String str = FileUtils.readTxtFile(filePath);
            Map map = (Map) JSON.parse(str);
            roleList = ((JSONArray) map.get("Role")).toJavaList(TableRole.class);
            algorithmroleList = ((JSONArray) map.get("AlgorithmRole")).toJavaList(TableAlgorithmrole.class);
            algorithmList = ((JSONArray) map.get("Algorithm")).toJavaList(TableAlgorithm.class);
            //算法规则
            roleList.forEach(role ->{
                roleMapper.insert(role);
            });
            //算子模块
            algorithmList.forEach(algorithm ->{
                tableAlgorithmMapper.insert(algorithm);
            });
            //算法算子关系
            List<TableRole> finalRoleList = roleList;
            List<TableAlgorithm> finalAlgorithmList = algorithmList;
            List<TableAlgorithmrole> finalAlgorithmroleList = algorithmroleList;
            algorithmroleList.forEach(algorithmrole ->{
                finalRoleList.forEach(role ->{
                    if(algorithmrole.getRoleid().intValue() == role.getTno().intValue()){
                        algorithmrole.setRoleid(role.getId());
                    }
                });
                finalAlgorithmList.forEach(algorithm ->{
                    if(algorithmrole.getAlgorithmid().intValue() == algorithm.getTno().intValue()){
                        algorithmrole.setAlgorithmid(algorithm.getId());
                    }
                });
                Integer id = algorithmroleMapper.selectMaxId();
                if(null == id){
                    id = 0;
                }
                algorithmrole.setId(++id);
                algorithmroleMapper.insert(algorithmrole);
            });

            finalRoleList.forEach(role ->{
                List<TableAlgorithmrole> childrenList = new ArrayList<TableAlgorithmrole>();
                finalAlgorithmroleList.forEach(algorithmrole ->{
                    if(algorithmrole.getRoleid().intValue() == role.getId().intValue()){
                        childrenList.add(algorithmrole);
                    }
                    finalAlgorithmList.forEach(algorithm ->{
                        if(algorithmrole.getAlgorithmid().intValue() == algorithm.getId().intValue()){
                            algorithmrole.setTableAlgorithm(algorithm);
                        }
                    });
                });
                role.setTableAlgorithmroleList(childrenList);
            });
            return roleList;
        }catch (Exception e){
            //手动回滚
            roleList.forEach(role ->{
                roleMapper.deleteByPrimaryKey(role.getId());
            });
            //算子模块
            algorithmList.forEach(algorithm ->{
                tableAlgorithmMapper.deleteByPrimaryKey(algorithm.getId());
            });
            algorithmroleList.forEach(algorithmrole ->{
                algorithmroleMapper.deleteByPrimaryKey(algorithmrole.getId());
            });
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * 拼装算法导出实体
     * @param id
     * @author 王若山
     * @return
     */
    @Override
    public TableRole GetTableExportData(Integer id) {
        TableRole tableRole = roleMapper.selectByPrimaryKey(id);
        if(null != tableRole){
            TableAlgorithmroleCriteria algorithmroleCriteria = new TableAlgorithmroleCriteria();
            algorithmroleCriteria.createCriteria().andRoleidEqualTo(tableRole.getId());
            //算法算子关系
            List<TableAlgorithmrole> algorithmroleList = algorithmroleMapper.selectByExample(algorithmroleCriteria);
            tableRole.setTableAlgorithmroleList(algorithmroleList);

            if(algorithmroleList.size() > 0){
                //算法算子ID集合
                List<Integer> arIdList = new ArrayList<Integer>();
                //算子ID集合
                List<Integer> aIdList = new ArrayList<Integer>();
                algorithmroleList.forEach(algorithmrole ->{
                    arIdList.add(algorithmrole.getId());
                    aIdList.add(algorithmrole.getAlgorithmid());
                });
                TableAlgorithmconditionCriteria algorithmconditionCriteria = new TableAlgorithmconditionCriteria();
                algorithmconditionCriteria.createCriteria().andAlgorithmroleidIn(arIdList);
                //根据【算法算子关系】的ID查询算子运行条件,并关联起来
                List<TableAlgorithmcondition> algorithmconditionList = algorithmconditionMapper.selectByExample(algorithmconditionCriteria);
                if(algorithmconditionList.size() > 0){
                    algorithmroleList.forEach(algorithmrole ->{
                        List<TableAlgorithmcondition> childrenList = new ArrayList<TableAlgorithmcondition>();
                        algorithmconditionList.forEach(algorithmcondition ->{
                            if(algorithmrole.getId().intValue() == algorithmcondition.getAlgorithmroleid().intValue()){
                                childrenList.add(algorithmcondition);
                            }
                        });
                        algorithmrole.setTableAlgorithmconditionList(childrenList);
                    });
                }
                //根据算子ID获取结果集,并关联起来
                TableAlgorithmCriteria algorithmCriteria = new TableAlgorithmCriteria();
                algorithmCriteria.createCriteria().andIdIn(aIdList);
                List<TableAlgorithm> algorithmList = tableAlgorithmMapper.selectByExample(algorithmCriteria);
                //同时获取公式变量列表
                TableFuncCriteria funcCriteria = new TableFuncCriteria();
                funcCriteria.createCriteria().andModuleidIn(aIdList);
                List<TableFunc> funcList = funcMapper.selectByExample(funcCriteria);
                if(algorithmList.size() > 0){
                    algorithmroleList.forEach(algorithmrole ->{
                        algorithmList.forEach(algorithm ->{
                            if(algorithmrole.getAlgorithmid().intValue() == algorithm.getId().intValue()){
                                algorithmrole.setTableAlgorithm(algorithm);
                            }
                        });
                    });
                    algorithmList.forEach(algorithm ->{
                        List<TableFunc> childrenList = new ArrayList<TableFunc>();
                        funcList.forEach(func -> {
                            if(algorithm.getId().intValue() == func.getModuleid().intValue()){
                                childrenList.add(func);
                            }
                        });
                        algorithm.setTableFuncList(childrenList);
                    });
                }
            }

            return tableRole;
        }else{
            return null;
        }
    }


    @Override
    public List<TableRole> GetAllAlgorithmRule(String username) {
        TableRoleCriteria tableRoleCriteria=new TableRoleCriteria();
        return roleMapper.selectByExample(tableRoleCriteria);
    }

    @Override
    public AlgorithmRuleSaveDataModel getAlgorithmRuleById(String id) {
        if(id==null||"".equals(id)){
            logger.warn("查询规则详细信息传入的规则ID不能为空");
            return null;
        }
        AlgorithmRuleSaveDataModel algorithmRuleSaveDataModel=new AlgorithmRuleSaveDataModel();
        //获取规则本身信息
        TableRole tableRole=selectByPrimaryKey(Integer.parseInt(id));
        algorithmRuleSaveDataModel.setTableRole(tableRole);
        List<AlgorithmRuleDataModel> data=new ArrayList<>();
        TableAlgorithmroleCriteria tableAlgorithmroleCriteria=new TableAlgorithmroleCriteria();
        tableAlgorithmroleCriteria.createCriteria().andRoleidEqualTo(Integer.parseInt(id));
        List<TableAlgorithmrole> tableAlgorithmroles=algorithmroleMapper.selectByExample(tableAlgorithmroleCriteria);
        if(tableAlgorithmroles.size()>0){
            tableAlgorithmroles.stream().forEach(algorithmrole->{
                AlgorithmRuleDataModel algorithmRuleDataModel=new AlgorithmRuleDataModel();
                algorithmRuleDataModel.setId(algorithmrole.getId());
                algorithmRuleDataModel.setRoleId(algorithmrole.getRoleid());
                algorithmRuleDataModel.setAlgorithmid(algorithmrole.getAlgorithmid());
                algorithmRuleDataModel.setPrealgorithmid(algorithmrole.getPrealgorithmid());
                algorithmRuleDataModel.setDes(algorithmrole.getDes());
                algorithmRuleDataModel.setRemark(algorithmrole.getRemark());
                //查询对应运行条件
                TableAlgorithmconditionCriteria tableAlgorithmconditionCriteria=new TableAlgorithmconditionCriteria();
                tableAlgorithmconditionCriteria.createCriteria().andAlgorithmroleidEqualTo(algorithmrole.getId());
                TableAlgorithmcondition tableAlgorithmcondition=algorithmconditionMapper.selectByExample(tableAlgorithmconditionCriteria).get(0);
                algorithmRuleDataModel.setTableAlgorithmcondition(tableAlgorithmcondition);
                //查询算子信息
                AlgorithmModel algorithmModel=tableAlgorithmService.getAlgorithmById(algorithmrole.getAlgorithmid().toString());
                AlgorithmModel preAlgorithmModel=tableAlgorithmService.getAlgorithmById(algorithmrole.getPrealgorithmid().toString());
                algorithmRuleDataModel.setAlgorithmModel(algorithmModel);
                algorithmRuleDataModel.setPreaAlgorithmModel(preAlgorithmModel);
                data.add(algorithmRuleDataModel);
            });
        }
        algorithmRuleSaveDataModel.setAlgorithmRuleDataModelList(data);
        return algorithmRuleSaveDataModel;
    }

    @Override
    public boolean saveAlgorithmRule(AlgorithmRuleSaveDataModel algorithmRuleSaveDataModel) {
        try {
            TableRole tableRole=algorithmRuleSaveDataModel.getTableRole();
            if(tableRole!=null){
                insert(tableRole);
                //获取主键
                int id=getTableRolePrimaryKey(tableRole);
                List<AlgorithmRuleDataModel> algorithmRuleDataModels=algorithmRuleSaveDataModel.getAlgorithmRuleDataModelList();
                if(algorithmRuleDataModels.size()>0){
                    algorithmRuleDataModels.stream().forEach(data->{
                        data.setRoleId(id);
                        this.saveAlgorithmRuleOne(data);
                    });
                }
            }
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }
    /*
    获取主键
     */
    private int getTableRolePrimaryKey(TableRole tableRole){
        TableRoleCriteria tableRoleCriteria=new TableRoleCriteria();
        tableRoleCriteria.createCriteria().andRolenameEqualTo(tableRole.getRolename()).andDesEqualTo(tableRole.getDes()).andRemarkEqualTo(tableRole.getRemark());
        tableRole=roleMapper.selectByExample(tableRoleCriteria).get(0);
        return tableRole.getId();
    }
    private int getTableAlgorithmrole(TableAlgorithmrole tableAlgorithmrole){
        TableAlgorithmroleCriteria tableAlgorithmroleCriteria=new TableAlgorithmroleCriteria();
        tableAlgorithmroleCriteria.createCriteria().andRoleidEqualTo(tableAlgorithmrole.getRoleid()).andAlgorithmidEqualTo(tableAlgorithmrole.getAlgorithmid())
                .andPrealgorithmidEqualTo(tableAlgorithmrole.getPrealgorithmid()).andDesEqualTo(tableAlgorithmrole.getDes()).andRemarkEqualTo(tableAlgorithmrole.getRemark());
        tableAlgorithmrole=algorithmroleMapper.selectByExample(tableAlgorithmroleCriteria).get(0);
        return tableAlgorithmrole.getId();
    }

    @Override
    public boolean saveAlgorithmRuleOne(AlgorithmRuleDataModel algorithmRuleDataModel) {
        try {
            TableAlgorithmrole tableAlgorithmrole=fill(algorithmRuleDataModel);
            algorithmroleMapper.insert(tableAlgorithmrole);
            int id=getTableAlgorithmrole(tableAlgorithmrole);
            TableAlgorithmcondition tableAlgorithmcondition=algorithmRuleDataModel.getTableAlgorithmcondition();
            if(tableAlgorithmcondition!=null){
                tableAlgorithmcondition.setAlgorithmroleid(id);
                algorithmconditionMapper.insert(tableAlgorithmcondition);
            }
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean saveAlgorithmRuleBase(TableRole tableRole) {
        return insert(tableRole)==1;
    }

    private TableAlgorithmrole fill(AlgorithmRuleDataModel algorithmRuleDataModel){
        TableAlgorithmrole tableAlgorithmrole=new TableAlgorithmrole();
        tableAlgorithmrole.setId(algorithmRuleDataModel.getId());
        tableAlgorithmrole.setRoleid(algorithmRuleDataModel.getRoleId());
        tableAlgorithmrole.setAlgorithmid(algorithmRuleDataModel.getAlgorithmid());
        tableAlgorithmrole.setPrealgorithmid(algorithmRuleDataModel.getPrealgorithmid());
        return tableAlgorithmrole;
    }

    @Override
    public boolean modAlgorithmRule(AlgorithmRuleDataModel algorithmRuleDataModel) {
        try {
            TableAlgorithmrole tableAlgorithmrole=fill(algorithmRuleDataModel);
            algorithmroleMapper.updateByPrimaryKeySelective(tableAlgorithmrole);
            TableAlgorithmcondition tableAlgorithmcondition=algorithmRuleDataModel.getTableAlgorithmcondition();
            if(tableAlgorithmcondition!=null){
                algorithmconditionMapper.updateByPrimaryKeySelective(tableAlgorithmcondition);
            }
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean modAlgorithmRuleBase(TableRole tableRole) {
        return updateByPrimaryKeySelective(tableRole)==1;
    }

    @Override
    public boolean delAlgorithmRuleById(String Id) {
        if(Id==null||"".equals(Id)){
            logger.warn("删除规则传入的规则ID不能为空");
            return false;
        }
        try {
            //删除所有算子关系
            TableAlgorithmroleCriteria tableAlgorithmroleCriteria=new TableAlgorithmroleCriteria();
            tableAlgorithmroleCriteria.createCriteria().andRoleidEqualTo(Integer.parseInt(Id));
            List<TableAlgorithmrole> tableAlgorithmroles=algorithmroleMapper.selectByExample(tableAlgorithmroleCriteria);
            if(tableAlgorithmroles.size()>0){
                tableAlgorithmroles.stream().forEach(algorithmrole->{
                    //删除运行条件
                    TableAlgorithmconditionCriteria tableAlgorithmconditionCriteria=new TableAlgorithmconditionCriteria();
                    tableAlgorithmconditionCriteria.createCriteria().andAlgorithmroleidEqualTo(algorithmrole.getId());
                    algorithmconditionMapper.deleteByExample(tableAlgorithmconditionCriteria);
                });
            }
            algorithmroleMapper.deleteByExample(tableAlgorithmroleCriteria);
            //删除规则本身信息
            deleteByPrimaryKey(Integer.parseInt(Id));
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delTableAlgorithmrole(String algorithmroleId) {
        if(algorithmroleId==null||"".equals(algorithmroleId)){
            logger.warn("删除算子关系传入的算法算子关系ID不能为空");
            return false;
        }
        try {
            algorithmroleMapper.deleteByPrimaryKey(Integer.parseInt(algorithmroleId));
            //删除运行条件
            TableAlgorithmconditionCriteria tableAlgorithmconditionCriteria=new TableAlgorithmconditionCriteria();
            tableAlgorithmconditionCriteria.createCriteria().andAlgorithmroleidEqualTo(Integer.parseInt(algorithmroleId));
            algorithmconditionMapper.deleteByExample(tableAlgorithmconditionCriteria);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }
}
