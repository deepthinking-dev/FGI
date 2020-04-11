package deepthinking.fgi.service.impl;

import com.github.pagehelper.PageInfo;
import deepthinking.fgi.dao.mapper.*;
import deepthinking.fgi.domain.*;
import deepthinking.fgi.model.InterfaceRoleDataModel;
import deepthinking.fgi.model.AlgorithmRuleSaveDataModel;
import deepthinking.fgi.model.OperatorInterfaceDataModel;
import deepthinking.fgi.model.xml.*;
import deepthinking.fgi.service.TableAlgorithmService;
import deepthinking.fgi.service.TableRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

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
    @Resource
    private TableFuncMapper tableFuncMapper;
    @Resource
    private TableInterfaceparametersMapper tableInterfaceparametersMapper;
    @Resource
    private TableAlgorithmconditionMapper tableAlgorithmconditionMapper;

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
    public RuleXmlModel GetTableExportData(Integer id) {
        TableRole tableRole = roleMapper.selectByPrimaryKey(id);
        if(null != tableRole){
            //====================算法========================//
            RuleXmlModel rule = new RuleXmlModel();
            rule.setName(tableRole.getRolename());
            rule.setDesc(tableRole.getDes());
            rule.setRemark(tableRole.getRemark());
            rule.setEntrancenote(tableRole.getEntrancenote());
            rule.setCoordinate(tableRole.getCoordinate());
            rule.setUserid(tableRole.getUuserid());
            //====================算法========================//

            //获取规则的所有接口
            TableOperatorinterfaceCriteria tableOperatorinterfaceCriteria = new TableOperatorinterfaceCriteria();
            tableOperatorinterfaceCriteria.createCriteria().andRoleidEqualTo(tableRole.getId());
            List<TableOperatorinterface> allInterfaceList = operatorinterfaceMapper.selectByExample(tableOperatorinterfaceCriteria);
            //规则的所有算子ID
            List<Integer> allAlgorithmidList = allInterfaceList.stream().map(TableOperatorinterface::getAlgorithmid).collect(Collectors.toList());
            //规则所有接口ID
            List<String> allInterfaceidList = allInterfaceList.stream().map(TableOperatorinterface::getId).collect(Collectors.toList());

            //===================接口=======================//
            //获取规则所有接口的关联关系
            TableInterfaceroleCriteria tableInterfaceroleCriteria = new TableInterfaceroleCriteria();
            tableInterfaceroleCriteria.createCriteria().andRoleidEqualTo(tableRole.getId());
            List<TableInterfacerole> allInterfaceRelevanceList = interfaceroleMapper.selectByExample(tableInterfaceroleCriteria);

            //前接口ID在表中的interfaceID查询不到的为首接口
            Set<String> preInterfaceIdSet = new HashSet<String>();  //收集前置接口ID集
            Set<String> firstInterfaceIdSet = new HashSet<String>();  //收集首位接口ID集

            allInterfaceRelevanceList.forEach(tableInterfacerole -> {
                preInterfaceIdSet.add(tableInterfacerole.getPreinterfaceid());
            });

            for(String pid : preInterfaceIdSet){
                Boolean flag = true;
                for(TableInterfacerole t : allInterfaceRelevanceList){
                    if(t.getInterfaceid().equals(pid)){
                        flag = false;
                    }
                }
                if(flag){
                    firstInterfaceIdSet.add(pid);
                }
            }

            InterfacesXmlModel interfacesXmlModel = new InterfacesXmlModel();
            firstInterfaceIdSet.forEach(fid -> {
                allInterfaceList.forEach(tableOperatorinterface -> {
                    if(tableOperatorinterface.getId().equals(fid)){
                        InterfaceXmlModel interfaceXmlModel = new InterfaceXmlModel();
                        interfaceXmlModel.setName(tableOperatorinterface.getInterfacename());
                        interfaceXmlModel.setId(tableOperatorinterface.getId());
                        AlgorithmXmlModel algorithm = new AlgorithmXmlModel();
                        algorithm.setId(tableOperatorinterface.getAlgorithmid());
                        interfaceXmlModel.setAlgorithm(algorithm);
                        interfacesXmlModel.getInterfa().add(interfaceXmlModel);
                    }
                });
            });
            rule.setInterfaces(interfacesXmlModel);
            //===================接口=======================//

            //===================规则包含算子===============//
            //获取规则包含所有的算子
            TableAlgorithmCriteria tableAlgorithmCriteria = new TableAlgorithmCriteria();
            tableAlgorithmCriteria.createCriteria().andIdIn(allAlgorithmidList);
            List<TableAlgorithm> allAlgorithmList = tableAlgorithmMapper.selectByExample(tableAlgorithmCriteria);

            //获取所有算子的参数
            TableFuncCriteria tableFuncCriteria = new TableFuncCriteria();
            tableFuncCriteria.createCriteria().andAlgorithmidIn(allAlgorithmidList);
            List<TableFunc> allFuncList = tableFuncMapper.selectByExample(tableFuncCriteria);

            rule.getInterfaces().getInterfa().forEach(interfaceXmlModel ->{
                allAlgorithmList.forEach(tableAlgorithm -> {
                    if(tableAlgorithm.getId().intValue() == interfaceXmlModel.getAlgorithm().getId().intValue()){
                        interfaceXmlModel.getAlgorithm().setName(tableAlgorithm.getAlgorithmname());
                        interfaceXmlModel.getAlgorithm().setAuthor(tableAlgorithm.getAlgorithmauthor());
                        interfaceXmlModel.getAlgorithm().setIspub(tableAlgorithm.getIspublic().intValue());
                        interfaceXmlModel.getAlgorithm().setType(tableAlgorithm.getAlgorithmtype().intValue());
                        interfaceXmlModel.getAlgorithm().setFunc(tableAlgorithm.getAlgorithmfun());
                        interfaceXmlModel.getAlgorithm().setDesc(tableAlgorithm.getDes());
                        interfaceXmlModel.getAlgorithm().setRemark(tableAlgorithm.getRemark());
                        ParamsXmlModel paramsXmlModel = new ParamsXmlModel();
                        allFuncList.forEach(tableFunc -> {
                            if(tableFunc.getAlgorithmid().intValue() == tableAlgorithm.getId().intValue()){
                                ParamXmlModel paramXmlModel = new ParamXmlModel();
                                paramXmlModel.setId(tableFunc.getId());
                                paramXmlModel.setName(tableFunc.getVarname());
                                paramXmlModel.setType(tableFunc.getVartype());
                                paramXmlModel.setValue(tableFunc.getValvalue());
                                paramXmlModel.setIotype(tableFunc.getInorout().intValue());
                                paramXmlModel.setRemark(tableFunc.getRemark());
                                paramsXmlModel.getParam().add(paramXmlModel);
                            }
                        });
                        interfaceXmlModel.getAlgorithm().setParams(paramsXmlModel);
                    }
                });
            });
            //===================规则包含算子===============//

            //===================接口参数==================//
            TableInterfaceparametersCriteria interfaceparametersCriteria = new TableInterfaceparametersCriteria();
            interfaceparametersCriteria.createCriteria().andInterfaceidIn(allInterfaceidList);
            //所有接口参数
            List<TableInterfaceparameters> allInterfaceParametersList = tableInterfaceparametersMapper.selectByExample(interfaceparametersCriteria);
            //所有接口参数ID
            List<String> allInterfaceParamIdList = allInterfaceParametersList.stream().map(TableInterfaceparameters::getId).collect(Collectors.toList());

            //所有接口参数的动作
            TableAlgorithmconditionCriteria tableAlgorithmconditionCriteria = new TableAlgorithmconditionCriteria();
            tableAlgorithmconditionCriteria.createCriteria().andInterfaceparametersidIn(allInterfaceParamIdList);
            List<TableAlgorithmcondition> allConditionList = tableAlgorithmconditionMapper.selectByExample(tableAlgorithmconditionCriteria);


            rule.getInterfaces().getInterfa().forEach(interfaceXmlModel ->{
                InterfaceParamsXmlModel interfaceParamsXmlModel = new InterfaceParamsXmlModel();
                allInterfaceParametersList.forEach(tableInterfaceparameters -> {
                    if(tableInterfaceparameters.getInterfaceid().equals(interfaceXmlModel.getId())){
                        InterfaceParamXmlModel interfaceParamXmlModel = new InterfaceParamXmlModel();
                        interfaceParamXmlModel.setId(tableInterfaceparameters.getId());
                        interfaceParamXmlModel.setName(tableInterfaceparameters.getParametersname());
                        interfaceParamXmlModel.setOrigin(Integer.valueOf(tableInterfaceparameters.getParameterssources()));
                        interfaceParamXmlModel.setIotype(tableInterfaceparameters.getInorout().intValue());
                        BehavioursXmlModel behavioursXmlModel = new BehavioursXmlModel();
                        allConditionList.forEach(tableAlgorithmcondition -> {
                            if(tableAlgorithmcondition.getInterfaceparametersid().equals(tableInterfaceparameters.getId())){
                                BehaviourXmlModel behaviourXmlModel = new BehaviourXmlModel();
                                behaviourXmlModel.setBehave(tableAlgorithmcondition.getBehavior());
                                behaviourXmlModel.setExpression(tableAlgorithmcondition.getExpression());
                                behaviourXmlModel.setOrigin(tableAlgorithmcondition.getValuesources());
                                behaviourXmlModel.setRemark(tableAlgorithmcondition.getRemark());
                                behavioursXmlModel.getAction().add(behaviourXmlModel);
                                setInterfaceData(allInterfaceRelevanceList, interfaceParamXmlModel, allInterfaceList,allAlgorithmList, allFuncList,
                                        allInterfaceParametersList, allInterfaceParamIdList, allConditionList);
                            }
                        });
                        interfaceParamXmlModel.setActions(behavioursXmlModel);
                        interfaceParamsXmlModel.getParam().add(interfaceParamXmlModel);
                    }
                });
                interfaceXmlModel.setParams(interfaceParamsXmlModel);
            });
            //===================接口参数==================//
            return rule;
        }else{
            return null;
        }
    }

    /**
     * 设置参数对应的下一个接口相关信息
     * @param allInterfaceRelevanceList 规则关联关系
     * @param interfaceParamXmlModel
     * @param allInterfaceList 所有接口
     * @param allAlgorithmList 所有算子
     * @param allFuncList 所有算子参数
     * @param allInterfaceParametersList 所有接口参数
     * @param allInterfaceParamIdList 所有接口参数ID
     * @param allConditionList 所有接口参数动作
     */
    private void setInterfaceData(List<TableInterfacerole> allInterfaceRelevanceList,
                                  InterfaceParamXmlModel interfaceParamXmlModel,
                                  List<TableOperatorinterface> allInterfaceList,
                                  List<TableAlgorithm> allAlgorithmList,
                                  List<TableFunc> allFuncList,
                                  List<TableInterfaceparameters> allInterfaceParametersList,
                                  List<String> allInterfaceParamIdList,
                                  List<TableAlgorithmcondition> allConditionList){
        allInterfaceRelevanceList.forEach(tableInterfacerole -> {
            if(interfaceParamXmlModel.getId().equals(tableInterfacerole.getPreparametersid())){
                allInterfaceList.forEach(tableOperatorinterface -> {
                    if(tableOperatorinterface.getId().equals(tableInterfacerole.getInterfaceid())){
                        interfaceParamXmlModel.setDesc(tableInterfacerole.getDes());
                        interfaceParamXmlModel.setRemark(tableInterfacerole.getRemark());


                        InterfaceXmlModel interfaceXmlModel = new InterfaceXmlModel();
                        interfaceXmlModel.setName(tableOperatorinterface.getInterfacename());
                        interfaceXmlModel.setId(tableOperatorinterface.getId());
                        //算子
                        AlgorithmXmlModel algorithm = new AlgorithmXmlModel();
                        algorithm.setId(tableOperatorinterface.getAlgorithmid());
                        interfaceXmlModel.setAlgorithm(algorithm);

                        //算子参数
                        allAlgorithmList.forEach(tableAlgorithm -> {
                            if(tableAlgorithm.getId().intValue() == interfaceXmlModel.getAlgorithm().getId().intValue()){
                                interfaceXmlModel.getAlgorithm().setName(tableAlgorithm.getAlgorithmname());
                                interfaceXmlModel.getAlgorithm().setAuthor(tableAlgorithm.getAlgorithmauthor());
                                interfaceXmlModel.getAlgorithm().setIspub(tableAlgorithm.getIspublic().intValue());
                                interfaceXmlModel.getAlgorithm().setType(tableAlgorithm.getAlgorithmtype().intValue());
                                interfaceXmlModel.getAlgorithm().setFunc(tableAlgorithm.getAlgorithmfun());
                                interfaceXmlModel.getAlgorithm().setDesc(tableAlgorithm.getDes());
                                interfaceXmlModel.getAlgorithm().setRemark(tableAlgorithm.getRemark());
                                ParamsXmlModel paramsXmlModel = new ParamsXmlModel();
                                allFuncList.forEach(tableFunc -> {
                                    if(tableFunc.getAlgorithmid().intValue() == tableAlgorithm.getId().intValue()){
                                        ParamXmlModel paramXmlModel = new ParamXmlModel();
                                        paramXmlModel.setId(tableFunc.getId());
                                        paramXmlModel.setName(tableFunc.getVarname());
                                        paramXmlModel.setType(tableFunc.getVartype());
                                        paramXmlModel.setValue(tableFunc.getValvalue());
                                        paramXmlModel.setIotype(tableFunc.getInorout().intValue());
                                        paramXmlModel.setRemark(tableFunc.getRemark());
                                        paramsXmlModel.getParam().add(paramXmlModel);
                                    }
                                });
                                interfaceXmlModel.getAlgorithm().setParams(paramsXmlModel);
                            }
                        });

                        //接口参数+动作
                        InterfaceParamsXmlModel interfaceParamsXmlModel = new InterfaceParamsXmlModel();
                        allInterfaceParametersList.forEach(tableInterfaceparameters -> {
                            if(tableInterfaceparameters.getInterfaceid().equals(interfaceXmlModel.getId())){
                                boolean flag = true;
                                if(tableInterfaceparameters.getInorout().intValue() == 1){  //输入
                                    if(!tableInterfaceparameters.getId().equals(tableInterfacerole.getParametersid())){
                                        flag = false;
                                    }
                                }
                                if(flag){
                                    InterfaceParamXmlModel tmp = new InterfaceParamXmlModel();
                                    tmp.setId(tableInterfaceparameters.getId());
                                    tmp.setName(tableInterfaceparameters.getParametersname());
                                    tmp.setOrigin(Integer.valueOf(tableInterfaceparameters.getParameterssources()));
                                    tmp.setIotype(tableInterfaceparameters.getInorout().intValue());
                                    BehavioursXmlModel behavioursXmlModel = new BehavioursXmlModel();
                                    allConditionList.forEach(tableAlgorithmcondition -> {
                                        if(tableAlgorithmcondition.getInterfaceparametersid().equals(tableInterfaceparameters.getId())){
                                            BehaviourXmlModel behaviourXmlModel = new BehaviourXmlModel();
                                            behaviourXmlModel.setBehave(tableAlgorithmcondition.getBehavior());
                                            behaviourXmlModel.setExpression(tableAlgorithmcondition.getExpression());
                                            behaviourXmlModel.setOrigin(tableAlgorithmcondition.getValuesources());
                                            behaviourXmlModel.setRemark(tableAlgorithmcondition.getRemark());
                                            behavioursXmlModel.getAction().add(behaviourXmlModel);

                                            AtomicBoolean aflag = new AtomicBoolean(false);
                                            if(tmp.getIotype() == 0){    //输出
                                                allInterfaceRelevanceList.forEach(relevance ->{
                                                    if(relevance.getPreparametersid().equals(tmp.getId())){
                                                        aflag.set(true);
                                                    }
                                                });
                                            }
                                            if(aflag.get()){
                                                setInterfaceData(allInterfaceRelevanceList, tmp, allInterfaceList, allAlgorithmList, allFuncList,
                                                        allInterfaceParametersList, allInterfaceParamIdList, allConditionList);
                                            }
                                        }
                                    });
                                    tmp.setActions(behavioursXmlModel);
                                    interfaceParamsXmlModel.getParam().add(tmp);
                                }
                            }
                        });
                        interfaceXmlModel.setParams(interfaceParamsXmlModel);
                        interfaceParamXmlModel.setInterfa(interfaceXmlModel);
                    }
                });
            }
        });
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

    private List<OperatorInterfaceDataModel> saveOperatorInterfaceData(List<OperatorInterfaceDataModel> operatorInterfaceDataModels) {
        try {
            operatorInterfaceDataModels.forEach(operatorInterfaceDataModel -> {
                TableOperatorinterface tableOperatorinterface=new TableOperatorinterface();
                tableOperatorinterface.setRoleid(operatorInterfaceDataModel.getRoleID());
                tableOperatorinterface.setAlgorithmid(operatorInterfaceDataModel.getAlgorithmID());
                tableOperatorinterface.setInterfacename(operatorInterfaceDataModel.getInterfaceName());
                operatorinterfaceMapper.insert(tableOperatorinterface);
                operatorInterfaceDataModel.setId(tableOperatorinterface.getId());
                //新增参数信息
                List<TableInterfaceparameters> interfaceparameters=operatorInterfaceDataModel.getTableInterfaceparametersList();
                if(interfaceparameters.size()>0){
                    interfaceparameters.forEach(parameter->{
//                        parameter.setInterfaceid(tableOperatorinterface.getId());
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
            saveOperatorInterfaceData(operatorInterfaceDataModels);
            //新增所有的线
            List<InterfaceRoleDataModel> interfaceRoleDataModels =algorithmRuleSaveDataModel.getInterfaceRoleDataModels();
            if(interfaceRoleDataModels.size()>0){
                interfaceRoleDataModels.forEach(interfaceRoleDataModel -> {
                    TableInterfacerole tableInterfacerole=interfaceRoleDataModel.getTable_InterfaceRole();
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
