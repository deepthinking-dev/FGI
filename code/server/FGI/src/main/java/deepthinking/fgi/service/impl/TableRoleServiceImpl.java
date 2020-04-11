package deepthinking.fgi.service.impl;

import com.github.pagehelper.PageInfo;
import deepthinking.fgi.dao.mapper.*;
import deepthinking.fgi.domain.*;
import deepthinking.fgi.model.InterfaceRoleDataModel;
import deepthinking.fgi.model.AlgorithmRuleSaveDataModel;
import deepthinking.fgi.model.OperatorInterfaceDataModel;
import deepthinking.fgi.service.TableAlgorithmService;
import deepthinking.fgi.service.TableRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/18 16:58
 */
@Service("tableRoleService")
@Transactional
public class TableRoleServiceImpl extends BaseServiceImpl<TableRole,Integer> implements TableRoleService {

    private static Logger logger = LoggerFactory.getLogger(TableRoleServiceImpl.class);

    @Resource
    private TableRoleMapper roleMapper;
    @Resource
    private TableAlgorithmconditionMapper algorithmconditionMapper;
    @Resource
    private TableAlgorithmMapper tableAlgorithmMapper;
    @Resource
    private TableFuncMapper funcMapper;
    @Resource
    private TableAlgorithmService tableAlgorithmService;
    @Resource
    private TableOperatorinterfaceMapper operatorinterfaceMapper;
    @Resource
    private TableInterfaceparametersMapper interfaceparametersMapper;
    @Resource
    private TableInterfaceroleMapper interfaceroleMapper;

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
//        List<TableRole> roleList = null;
//        List<TableAlgorithmrole> algorithmroleList = null;
//        List<TableAlgorithm> algorithmList = null;
//        try{
//            String str = FileUtils.readTxtFile(filePath);
//            Map map = (Map) JSON.parse(str);
//            roleList = ((JSONArray) map.get("Role")).toJavaList(TableRole.class);
//            algorithmroleList = ((JSONArray) map.get("AlgorithmRole")).toJavaList(TableAlgorithmrole.class);
//            algorithmList = ((JSONArray) map.get("Algorithm")).toJavaList(TableAlgorithm.class);
//            //算法规则
//            roleList.forEach(role ->{
//                roleMapper.insert(role);
//            });
//            //算子模块
//            algorithmList.forEach(algorithm ->{
//                tableAlgorithmMapper.insert(algorithm);
//            });
//            //算法算子关系
//            List<TableRole> finalRoleList = roleList;
//            List<TableAlgorithm> finalAlgorithmList = algorithmList;
//            List<TableAlgorithmrole> finalAlgorithmroleList = algorithmroleList;
//            algorithmroleList.forEach(algorithmrole ->{
//                finalRoleList.forEach(role ->{
//                    if(algorithmrole.getRoleid().intValue() == role.getTno().intValue()){
//                        algorithmrole.setRoleid(role.getId());
//                    }
//                });
//                finalAlgorithmList.forEach(algorithm ->{
//                    if(algorithmrole.getAlgorithmid().intValue() == algorithm.getTno().intValue()){
//                        algorithmrole.setAlgorithmid(algorithm.getId());
//                    }
//                });
//                Integer id = algorithmroleMapper.selectMaxId();
//                if(null == id){
//                    id = 0;
//                }
//                algorithmrole.setId(++id);
//                algorithmroleMapper.insert(algorithmrole);
//            });
//
//            finalRoleList.forEach(role ->{
//                List<TableAlgorithmrole> childrenList = new ArrayList<TableAlgorithmrole>();
//                finalAlgorithmroleList.forEach(algorithmrole ->{
//                    if(algorithmrole.getRoleid().intValue() == role.getId().intValue()){
//                        childrenList.add(algorithmrole);
//                    }
//                    finalAlgorithmList.forEach(algorithm ->{
//                        if(algorithmrole.getAlgorithmid().intValue() == algorithm.getId().intValue()){
//                            algorithmrole.setTableAlgorithm(algorithm);
//                        }
//                    });
//                });
//                role.setTableAlgorithmroleList(childrenList);
//            });
//            return roleList;
//        }catch (Exception e){
//            //手动回滚
//            roleList.forEach(role ->{
//                roleMapper.deleteByPrimaryKey(role.getId());
//            });
//            //算子模块
//            algorithmList.forEach(algorithm ->{
//                tableAlgorithmMapper.deleteByPrimaryKey(algorithm.getId());
//            });
//            algorithmroleList.forEach(algorithmrole ->{
//                algorithmroleMapper.deleteByPrimaryKey(algorithmrole.getId());
//            });
//            System.out.println(e.getMessage());
            return null;
//        }
    }

    /**
     * 拼装算法导出实体
     * @param id
     * @author 王若山
     * @return
     */
    @Override
    public TableRole GetTableExportData(Integer id) {
//        TableRole tableRole = roleMapper.selectByPrimaryKey(id);
//        if(null != tableRole){
//            TableAlgorithmroleCriteria algorithmroleCriteria = new TableAlgorithmroleCriteria();
//            algorithmroleCriteria.createCriteria().andRoleidEqualTo(tableRole.getId());
//            //算法算子关系
//            List<TableAlgorithmrole> algorithmroleList = algorithmroleMapper.selectByExample(algorithmroleCriteria);
//            tableRole.setTableAlgorithmroleList(algorithmroleList);
//
//            if(algorithmroleList.size() > 0){
//                //算法算子ID集合
//                List<Integer> arIdList = new ArrayList<Integer>();
//                //算子ID集合
//                List<Integer> aIdList = new ArrayList<Integer>();
//                algorithmroleList.forEach(algorithmrole ->{
//                    arIdList.add(algorithmrole.getId());
//                    aIdList.add(algorithmrole.getAlgorithmid());
//                });
//                TableAlgorithmconditionCriteria algorithmconditionCriteria = new TableAlgorithmconditionCriteria();
//                algorithmconditionCriteria.createCriteria().andAlgorithmroleidIn(arIdList);
//                //根据【算法算子关系】的ID查询算子运行条件,并关联起来
//                List<TableAlgorithmcondition> algorithmconditionList = algorithmconditionMapper.selectByExample(algorithmconditionCriteria);
//                if(algorithmconditionList.size() > 0){
//                    algorithmroleList.forEach(algorithmrole ->{
//                        List<TableAlgorithmcondition> childrenList = new ArrayList<TableAlgorithmcondition>();
//                        algorithmconditionList.forEach(algorithmcondition ->{
//                            if(algorithmrole.getId().intValue() == algorithmcondition.getAlgorithmroleid().intValue()){
//                                childrenList.add(algorithmcondition);
//                            }
//                        });
//                        algorithmrole.setTableAlgorithmconditionList(childrenList);
//                    });
//                }
//                //根据算子ID获取结果集,并关联起来
//                TableAlgorithmCriteria algorithmCriteria = new TableAlgorithmCriteria();
//                algorithmCriteria.createCriteria().andIdIn(aIdList);
//                List<TableAlgorithm> algorithmList = tableAlgorithmMapper.selectByExample(algorithmCriteria);
//                //同时获取公式变量列表
//                TableFuncCriteria funcCriteria = new TableFuncCriteria();
//                funcCriteria.createCriteria().andModuleidIn(aIdList);
//                List<TableFunc> funcList = funcMapper.selectByExample(funcCriteria);
//                if(algorithmList.size() > 0){
//                    algorithmroleList.forEach(algorithmrole ->{
//                        algorithmList.forEach(algorithm ->{
//                            if(algorithmrole.getAlgorithmid().intValue() == algorithm.getId().intValue()){
//                                algorithmrole.setTableAlgorithm(algorithm);
//                            }
//                        });
//                    });
//                    algorithmList.forEach(algorithm ->{
//                        List<TableFunc> childrenList = new ArrayList<TableFunc>();
//                        funcList.forEach(func -> {
//                            if(algorithm.getId().intValue() == func.getModuleid().intValue()){
//                                childrenList.add(func);
//                            }
//                        });
//                        algorithm.setTableFuncList(childrenList);
//                    });
//                }
//            }

//            return tableRole;
//        }else{
            return null;
//        }
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
        //查询所有的接口信息
        TableOperatorinterfaceCriteria tableOperatorinterfaceCriteria=new TableOperatorinterfaceCriteria();
        tableOperatorinterfaceCriteria.createCriteria().andRoleidEqualTo(Integer.parseInt(id));
        List<TableOperatorinterface> operatorinterfaces=operatorinterfaceMapper.selectByExample(tableOperatorinterfaceCriteria);
        if(operatorinterfaces.size()>0){
            List<OperatorInterfaceDataModel> operatorInterfaceDataModels=new ArrayList<>();
            //查询参数
            operatorinterfaces.forEach(operatorinterface->{
                OperatorInterfaceDataModel operatorInterfaceDataModel=new OperatorInterfaceDataModel();
                operatorInterfaceDataModel.setId(operatorinterface.getId());
                operatorInterfaceDataModel.setAlgorithmID(operatorinterface.getAlgorithmid());
                operatorInterfaceDataModel.setRoleID(operatorinterface.getRoleid());
                operatorInterfaceDataModel.setInterfaceName(operatorinterface.getInterfacename());
                TableInterfaceparametersCriteria tableInterfaceparametersCriteria=new TableInterfaceparametersCriteria();
                tableInterfaceparametersCriteria.createCriteria().andInterfaceidEqualTo(operatorinterface.getId());
                List<TableInterfaceparameters> interfaceparameters=interfaceparametersMapper.selectByExample(tableInterfaceparametersCriteria);
                operatorInterfaceDataModel.setTableInterfaceparametersList(interfaceparameters);
                operatorInterfaceDataModels.add(operatorInterfaceDataModel);
            });
            algorithmRuleSaveDataModel.setOperatorInterfaceDataModels(operatorInterfaceDataModels);
        }
        //查询所有的线
        List<InterfaceRoleDataModel> data=new ArrayList<>();
        TableInterfaceroleCriteria tableInterfaceroleCriteria=new TableInterfaceroleCriteria();
        tableInterfaceroleCriteria.createCriteria().andRoleidEqualTo(Integer.parseInt(id));
        List<TableInterfacerole> tableInterfaceroles=interfaceroleMapper.selectByExample(tableInterfaceroleCriteria);//所有的线
        if(tableInterfaceroles.size()>0){
            tableInterfaceroles.forEach(interfacerole->{
                InterfaceRoleDataModel interfaceRoleDataModel =new InterfaceRoleDataModel().getInterfaceRoleDataModelFromTableInterfacerole(interfacerole);
                //查询对应的动作
                TableAlgorithmconditionCriteria tableAlgorithmconditionCriteria=new TableAlgorithmconditionCriteria();
                tableAlgorithmconditionCriteria.createCriteria().andInterfaceroleidEqualTo(interfacerole.getId());
                List<TableAlgorithmcondition> tableAlgorithmconditions=algorithmconditionMapper.selectByExample(tableAlgorithmconditionCriteria);
                interfaceRoleDataModel.setAlgorithmconditions(tableAlgorithmconditions);
                data.add(interfaceRoleDataModel);
            });
        }
        algorithmRuleSaveDataModel.setInterfaceRoleDataModels(data);
        return algorithmRuleSaveDataModel;
    }

    private List<OperatorInterfaceDataModel> saveOperatorInterfaceData(List<OperatorInterfaceDataModel> operatorInterfaceDataModels,int rouleID) {
        try {
            operatorInterfaceDataModels.forEach(operatorInterfaceDataModel -> {
                TableOperatorinterface tableOperatorinterface=new TableOperatorinterface();
                tableOperatorinterface.setRoleid(operatorInterfaceDataModel.getRoleID());
                tableOperatorinterface.setAlgorithmid(operatorInterfaceDataModel.getAlgorithmID());
                tableOperatorinterface.setInterfacename(operatorInterfaceDataModel.getInterfaceName());
                tableOperatorinterface.setRoleid(rouleID);
                operatorinterfaceMapper.insert(tableOperatorinterface);
                operatorInterfaceDataModel.setId(tableOperatorinterface.getId());
                //新增参数信息
                List<TableInterfaceparameters> interfaceparameters=operatorInterfaceDataModel.getTableInterfaceparametersList();
                if(interfaceparameters.size()>0){
                    interfaceparameters.forEach(parameter->{
                        interfaceparametersMapper.insert(parameter);
                    });
                }
            });
            return operatorInterfaceDataModels;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public AlgorithmRuleSaveDataModel saveAlgorithmRule(AlgorithmRuleSaveDataModel algorithmRuleSaveDataModel) {
        try {
            //保存最后算法规则信息
            TableRole tableRole=algorithmRuleSaveDataModel.getTableRole();
            insert(tableRole);
            //新增接口信息
            List<OperatorInterfaceDataModel> operatorInterfaceDataModels=algorithmRuleSaveDataModel.getOperatorInterfaceDataModels();
            saveOperatorInterfaceData(operatorInterfaceDataModels,tableRole.getId());
            //新增所有的线
            List<InterfaceRoleDataModel> interfaceRoleDataModels =algorithmRuleSaveDataModel.getInterfaceRoleDataModels();
            if(interfaceRoleDataModels.size()>0){
                interfaceRoleDataModels.forEach(interfaceRoleDataModel -> {
                    TableInterfacerole tableInterfacerole=interfaceRoleDataModel.getTable_InterfaceRole();
                    tableInterfacerole.setRoleid(tableRole.getId());
                    interfaceroleMapper.insert(tableInterfacerole);
                    interfaceRoleDataModel.setId(tableInterfacerole.getId());
                    //保存动作
                    List<TableAlgorithmcondition> algorithmconditions=interfaceRoleDataModel.getAlgorithmconditions();
                    algorithmconditions.forEach(conditions->{
                        conditions.setInterfaceroleid(tableInterfacerole.getId());
                    });
                });
            }
            return algorithmRuleSaveDataModel;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }


    @Override
    public boolean modAlgorithmRule(InterfaceRoleDataModel interfaceRoleDataModel) {
        try {
            TableInterfacerole interfacerole=interfaceRoleDataModel.getTable_InterfaceRole();
            interfaceroleMapper.updateByPrimaryKeySelective(interfacerole);
            List<TableAlgorithmcondition> tableAlgorithmconditions= interfaceRoleDataModel.getAlgorithmconditions();
            //查询以前的动作
            TableAlgorithmconditionCriteria tableAlgorithmconditionCriteria=new TableAlgorithmconditionCriteria();
            tableAlgorithmconditionCriteria.createCriteria().andInterfaceroleidEqualTo(interfacerole.getId());
            List<TableAlgorithmcondition> oldData=algorithmconditionMapper.selectByExample(tableAlgorithmconditionCriteria);
            List<TableAlgorithmcondition> addConditon=new ArrayList<>();
            List<TableAlgorithmcondition> delConditon=new ArrayList<>();
            List<TableAlgorithmcondition> updConditon=new ArrayList<>();
            if(oldData.size()>0){
                for(TableAlgorithmcondition condition:tableAlgorithmconditions){
                    boolean flag=false;
                    for(TableAlgorithmcondition old:oldData){
                        if(old.getId()==condition.getId()){
                            updConditon.add(condition);
                            flag=true;
                            break;
                        }
                    }
                    if(!flag){
                        addConditon.add(condition);
                    }
                }
                for(TableAlgorithmcondition old:oldData){
                    boolean flag=false;
                    for(TableAlgorithmcondition tupdata:updConditon){
                        if(old.getId()==tupdata.getId()){
                            flag=true;
                            break;
                        }
                    }
                    if(!flag){
                        delConditon.add(old);
                    }
                }
                //删除
                delConditon.forEach(data->algorithmconditionMapper.deleteByPrimaryKey(data.getId()));
                //新增
                addConditon.forEach(data->algorithmconditionMapper.insert(data));
                //修改
                updConditon.forEach(data->algorithmconditionMapper.updateByPrimaryKeySelective(data));
            }else{
                tableAlgorithmconditions.forEach(tableAlgorithmcondition->algorithmconditionMapper.insert(tableAlgorithmcondition));
            }



            if(tableAlgorithmconditions.size()>0){

                tableAlgorithmconditions.forEach(tableAlgorithmcondition->{
                    if(tableAlgorithmcondition.getId()!=null&&tableAlgorithmcondition.getId()!=0){
                        algorithmconditionMapper.updateByPrimaryKeySelective(tableAlgorithmcondition);
                    }else {
                        algorithmconditionMapper.insert(tableAlgorithmcondition);
                    }
                });
            }else{
                tableAlgorithmconditions.forEach(tableAlgorithmcondition->algorithmconditionMapper.deleteByPrimaryKey(tableAlgorithmcondition.getId()));
            }
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public OperatorInterfaceDataModel modInterfaceRole(OperatorInterfaceDataModel operatorInterfaceDataModel) {
        try {
            TableOperatorinterface tableOperatorinterface = new TableOperatorinterface();
            tableOperatorinterface.setId(operatorInterfaceDataModel.getId());
            tableOperatorinterface.setInterfacename(operatorInterfaceDataModel.getInterfaceName());
            tableOperatorinterface.setAlgorithmid(operatorInterfaceDataModel.getAlgorithmID());
            tableOperatorinterface.setRoleid(operatorInterfaceDataModel.getRoleID());
            operatorinterfaceMapper.updateByPrimaryKeySelective(tableOperatorinterface);

            TableInterfaceparametersCriteria tableInterfaceparametersCriteria=new TableInterfaceparametersCriteria();
            tableInterfaceparametersCriteria.createCriteria().andInterfaceidEqualTo(operatorInterfaceDataModel.getId());
            List<TableInterfaceparameters> oldData=interfaceparametersMapper.selectByExample(tableInterfaceparametersCriteria);
            List<TableInterfaceparameters> interfaceparameters=operatorInterfaceDataModel.getTableInterfaceparametersList();
            if(oldData.size()>0){
                List<TableInterfaceparameters> addPara=new ArrayList<>();
                List<TableInterfaceparameters> updataPara=new ArrayList<>();
                List<TableInterfaceparameters> deletedataPara=new ArrayList<>();
                for(TableInterfaceparameters para:interfaceparameters){
                    boolean flag=false;
                    for(TableInterfaceparameters old:oldData){
                        if(para.getId().equals(old.getId())){//修改
                            updataPara.add(para);
                            flag=true;
                            break;
                        }
                    }
                    if(!flag){
                        addPara.add(para);
                    }
                }
                for(TableInterfaceparameters old:oldData){
                    boolean flag=false;
                    for(TableInterfaceparameters updte:updataPara){
                        if(old.getId().equals(updte.getId())){
                            flag=true;
                            break;
                        }
                    }
                    if(!flag){
                        deletedataPara.add(old);
                    }
                }
                //修改
                updataPara.forEach(data->interfaceparametersMapper.updateByPrimaryKeySelective(data));
                //删除
                deletedataPara.forEach(data->interfaceparametersMapper.deleteByPrimaryKey(data.getId()));
                //新增
                addPara.forEach(data->interfaceparametersMapper.insert(data));
            }else{
                interfaceparameters.forEach(interfaceparameter->interfaceparametersMapper.insert(interfaceparameter));
            }
            return operatorInterfaceDataModel;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return null;
    }

    @Override
    public boolean modAlgorithmRuleBase(TableRole tableRole) {
        return updateByPrimaryKeySelective(tableRole)==1;
    }

    @Override
    public boolean delAlgorithmRuleById(String Id) {
        if(Id==null||"".equals(Id)||Id.equals("0")){
            logger.warn("删除规则传入的规则ID不能为空");
            return false;
        }
        try {
            //删除所有的接口信息
            TableOperatorinterfaceCriteria tableOperatorinterfaceCriteria=new TableOperatorinterfaceCriteria();
            tableOperatorinterfaceCriteria.createCriteria().andRoleidEqualTo(Integer.parseInt(Id));
            operatorinterfaceMapper.deleteByExample(tableOperatorinterfaceCriteria);
            //删除所有算子关系
            TableInterfaceroleCriteria interfaceroleCriteria=new TableInterfaceroleCriteria();
            interfaceroleCriteria.createCriteria().andRoleidEqualTo(Integer.parseInt(Id));

            interfaceroleMapper.deleteByExample(interfaceroleCriteria);
            //删除规则本身信息
            deleteByPrimaryKey(Integer.parseInt(Id));
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delTableOperatorinterface(String operatorinterfaceId) {
        if(operatorinterfaceId==null||"".equals(operatorinterfaceId)||operatorinterfaceId.equals("0")){
            logger.warn("删除算子关系传入的算法算子关系ID不能为空");
            return false;
        }
        try {
            //接口参数信息和动作信息由外键删除
            //删除接口关系
            TableInterfaceroleCriteria tableInterfaceroleCriteria=new TableInterfaceroleCriteria();
            tableInterfaceroleCriteria.or().andInterfaceidEqualTo(operatorinterfaceId);
            tableInterfaceroleCriteria.or().andPreinterfaceidEqualTo(operatorinterfaceId);
            interfaceroleMapper.deleteByExample(tableInterfaceroleCriteria);
            //删除接口本身信息
            operatorinterfaceMapper.deleteByPrimaryKey(operatorinterfaceId);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delOneInterfaceRole(String interfaceRoueId) {
        if(interfaceRoueId==null||"".equals(interfaceRoueId)||interfaceRoueId.equals("0")){
            logger.warn("删除算法接口关系传入的接口关系ID不能为空");
            return false;
        }
        return interfaceroleMapper.deleteByPrimaryKey(Integer.parseInt(interfaceRoueId))==1;
    }

    @Override
    public boolean saveNewCoordinate(String coordinateinfo, int roleId) {
        try {
            if(coordinateinfo!=null&&!"".equals(coordinateinfo)){
                TableRole tableRole=new TableRole();
                tableRole.setId(roleId);
                tableRole.setCoordinate(coordinateinfo);
                updateByPrimaryKeySelective(tableRole);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean saveFunAction(List<TableAlgorithmcondition> algorithmconditions) {
        try {
            if(algorithmconditions!=null&&algorithmconditions.size()>0){
                //删除以前的
                int interfaceRoleId=algorithmconditions.get(0).getInterfaceroleid();
                String interfaceParametersID=algorithmconditions.get(0).getInterfaceparametersid();
                TableAlgorithmconditionCriteria tableAlgorithmconditionCriteria=new TableAlgorithmconditionCriteria();
                tableAlgorithmconditionCriteria.createCriteria().andInterfaceroleidEqualTo(interfaceRoleId).andInterfaceparametersidEqualTo(interfaceParametersID);
                algorithmconditionMapper.deleteByExample(tableAlgorithmconditionCriteria);
                //新增
                algorithmconditions.forEach(data->algorithmconditionMapper.insert(data));
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }
}
