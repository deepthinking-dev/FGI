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
import deepthinking.fgi.util.XMLUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/18 16:58
 */
@Service("tableRoleService")
@Transactional(rollbackFor = Exception.class)
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
     * @param file 文件地址
     * @Author 王若山
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public RuleXmlModel leadByTxt(File file) {
        RuleXmlModel rule = (RuleXmlModel) XMLUtil.convertXmlFileToObject(RuleXmlModel.class, file);
        //算法规则表Table_Role
        TableRole tableRole = new TableRole();
        tableRole.setRolename(rule.getName() + "-副本" + String.valueOf(System.currentTimeMillis()));
        rule.setName(tableRole.getRolename());
        tableRole.setDes(rule.getDesc());
        tableRole.setRemark(rule.getRemark());
        tableRole.setEntrancenote(rule.getEntrancenote());
        tableRole.setCoordinate(rule.getCoordinate());
        tableRole.setUuserid(rule.getUserid());
        tableRole.setRolegroup(rule.getRoleGroup());
        tableRole.setStatus(rule.getStatus());
        tableRole.setRemark2(rule.getRemark2());
        tableRole.setRemark3(rule.getRemark3());

        roleMapper.insert(tableRole);

        Set<String> interfaceIdSet = new HashSet<String>();   //所有接口ID

        Map map = new HashMap();    //接口名称-接口ID
        Map map2 = new HashMap();
        Map map3 = new HashMap();
        Map map4 = new HashMap();   //接口ID-接口经常

        Map transMap = new HashMap();   //key--老ID value新ID

        Map tmap = new HashMap();   //xmlID-新UUID
        Map vtmap = new HashMap();  //新UUID-老ID
        Map ycmap = new HashMap();  //xmlID-新UUID


        List<TableOperatorinterface> tableOperatorinterfaceList = new ArrayList<TableOperatorinterface>();  //接口表

        List<TableInterfaceparameters> tableInterfaceparametersList = new ArrayList<TableInterfaceparameters>();    //接口参数

        List<TableInterfacerole> tableInterfaceroleList = new ArrayList<TableInterfacerole>();   //接口关系

        List<TableAlgorithmcondition> tableAlgorithmconditionList = new ArrayList<TableAlgorithmcondition>();   //动作
        if(null != rule.getInterfaces()){
            rule.getInterfaces().getInterfa().forEach(interfaceXmlModel -> {
                TableOperatorinterface tableOperatorinterface = new TableOperatorinterface();
                String id = UUID.randomUUID().toString().replace("-", "");
                tmap.put(interfaceXmlModel.getId(), id);
//                transMap.put(interfaceXmlModel.getId(), id);
//                map.put(interfaceXmlModel.getName(), id);
//                map4.put(id, interfaceXmlModel.getName());
                interfaceXmlModel.setId(id);
                tableOperatorinterface.setId(id);
                tableOperatorinterface.setAlgorithmid(interfaceXmlModel.getAlgorithm().getId());
                tableOperatorinterface.setInterfacename(interfaceXmlModel.getName());
                tableOperatorinterface.setRoleid(tableRole.getId());

                tableOperatorinterfaceList.add(tableOperatorinterface);

                SetAllInterfaceId(interfaceXmlModel, tmap, false);   //给所有接口赋值ID

                GetAllInterfaceFromModel(interfaceXmlModel, tableOperatorinterfaceList, tableRole, false);  //收集接口

                SetInterfaceParamId(interfaceXmlModel, ycmap, vtmap);   //给所有接口参数赋值ID

                GetAllInterfaceParamFromModel(interfaceXmlModel, tableInterfaceparametersList, vtmap);   //收集接口参数

                setInterfaceParamRela(interfaceXmlModel,tableInterfaceroleList,tableRole,null, null); //构造接口关系

                cleanRelaList(tableInterfaceroleList);  //除重

                GetAllBehavior(interfaceXmlModel, tableAlgorithmconditionList, ycmap);   //收集动作

//                GetBehaviorFromModel(tableInterfaceroleList, tableAlgorithmconditionList);  //构造动作表

            });
        }


        /*System.out.println();
        Set<String> transKeySet = transMap.keySet();
        String coo = tableRole.getCoordinate();
        for(String key : transKeySet){
            String replageValue = String.valueOf(transMap.get(key));
            coo = coo.replaceAll(key, replageValue);
        }
        tableRole.setCoordinate(coo);
        rule.setCoordinate(coo);
        roleMapper.updateByPrimaryKeySelective(tableRole);*/

        //接口
        tableOperatorinterfaceList.forEach(tableOperatorinterface -> {
            operatorinterfaceMapper.insert(tableOperatorinterface);
        });
        tableInterfaceparametersList.forEach(tableInterfaceparameters -> {
            tableInterfaceparametersMapper.insert(tableInterfaceparameters);
        });
        tableInterfaceroleList.forEach(tableInterfacerole -> {
            interfaceroleMapper.insert(tableInterfacerole);
        });
        GetAllRleBehavior(tableInterfaceroleList, tableAlgorithmconditionList);
//        clearRleBehavior(tableAlgorithmconditionList);
        tableAlgorithmconditionList.forEach(tableAlgorithmcondition -> {
            algorithmconditionMapper.insert(tableAlgorithmcondition);
        });
        return rule;
    }

    private void SetBehaviorIRData(List<TableAlgorithmcondition> tableAlgorithmconditionList,
                                   List<TableInterfacerole> tableInterfaceroleList){
        tableAlgorithmconditionList.forEach(tableAlgorithmcondition -> {
            tableInterfaceroleList.forEach(tableInterfacerole -> {
                if(tableInterfacerole.getParametersid()!=null){
                    if(tableInterfacerole.getParametersid().equals(tableAlgorithmcondition.getInterfaceparametersid())
                            || tableInterfacerole.getPreparametersid().equals(tableAlgorithmcondition.getInterfaceparametersid())){
                        tableAlgorithmcondition.setInterfaceroleid(tableInterfacerole.getId());
                    }
                }
            });
        });
    }


    /**
     * 构造动作表
     * @param tableAlgorithmconditionList
     */
    private void GetBehaviorFromModel(List<TableInterfacerole> tableInterfaceroleList,
                                      List<TableAlgorithmcondition> tableAlgorithmconditionList){
        tableInterfaceroleList.forEach(tableInterfacerole -> {
            tableAlgorithmconditionList.forEach(tableAlgorithmcondition -> {
                if(null == tableAlgorithmcondition.getInterfaceroleid()){
                    if(tableAlgorithmcondition.getInterfaceparametersid().equals(tableInterfacerole.getParametersid())
                            || tableAlgorithmcondition.getInterfaceparametersid().equals(tableInterfacerole.getPreparametersid())){
                        tableAlgorithmcondition.setInterfaceroleid(tableInterfacerole.getId());
                    }
                }
            });
        });
//        if(null != interfaceXmlModel.getParams()){
//            List<InterfaceParamXmlModel> interfaceParamXmlModelList = interfaceXmlModel.getParams().getParam();
//            interfaceParamXmlModelList.forEach(interfaceParamXmlModel -> {
//                interfaceParamXmlModel.getActions().getAction().forEach(action ->{
//                    TableAlgorithmcondition tableAlgorithmcondition = new TableAlgorithmcondition();
//                    tableAlgorithmcondition.setInterfaceparametersid(interfaceParamXmlModel.getId());
//                    tableAlgorithmcondition.setBehavior(action.getBehave());
//                    tableAlgorithmcondition.setValuesources(action.getOrigin());
//                    tableAlgorithmcondition.setExpression(action.getExpression());
//                    tableAlgorithmcondition.setRemark(action.getRemark());
//                    AtomicBoolean flag = new AtomicBoolean(true);
//                    tableAlgorithmconditionList.forEach(tc -> {
//                        if(tc.getInterfaceparametersid().equals(tableAlgorithmcondition.getInterfaceparametersid())
//                                && (tc.getBehavior().equals(tableAlgorithmcondition.getBehavior()))
//                                && (tc.getExpression().equals(tableAlgorithmcondition.getExpression()))
//                                && (tc.getValuesources().equals(tableAlgorithmcondition.getValuesources()))){
//                            flag.set(false);
//                        }
//                    });
//                    if(flag.get()){
//                        tableAlgorithmconditionList.add(tableAlgorithmcondition);
//                    }
//                });
//            });
//            interfaceParamXmlModelList.forEach(interfaceParamXmlModel -> {
//                if(null != interfaceParamXmlModel.getInterfa()){    //有下一层
//                    GetBehaviorFromModel(interfaceParamXmlModel.getInterfa(), tableAlgorithmconditionList);
//                }
//            });
//
//        }

    }

    //设置接口参数ID
    private void SetInterfaceParamId(InterfaceXmlModel interfaceXmlModel, Map tmap, Map vtmap){
        if(null != interfaceXmlModel.getParams()){
            List<InterfaceParamXmlModel> interfaceParamXmlModelList = interfaceXmlModel.getParams().getParam();
            interfaceParamXmlModelList.forEach(interfaceParamXmlModel ->{
                String id = UUID.randomUUID().toString().replace("-", "");
                if(null != tmap.get(interfaceParamXmlModel.getId())){
                    interfaceParamXmlModel.setId(String.valueOf(tmap.get(interfaceParamXmlModel.getId())));
                }else{
                    vtmap.put(id, interfaceParamXmlModel.getId());
                    tmap.put(interfaceParamXmlModel.getId(), id);
                    interfaceParamXmlModel.setId(id);
                }
            });

            interfaceParamXmlModelList.forEach(interfaceParamXmlModel -> {
                if(interfaceParamXmlModel.getInterfaces().getInterfa().size() > 0){
                    interfaceParamXmlModel.getInterfaces().getInterfa().forEach(ixm ->{
                        SetInterfaceParamId(ixm, tmap, vtmap);
                    });
                }
            });
        }
    }

    //去重
    private void cleanRelaList(List<TableInterfacerole> tableInterfaceroleList){
        List<TableInterfacerole> tmpList = new ArrayList<TableInterfacerole>();
        for(int i = 0; i < tableInterfaceroleList.size() - 1; i++){
            TableInterfacerole ti = tableInterfaceroleList.get(i);
            if(!tmpList.contains(ti)){
                for(int j = i + 1; j < tableInterfaceroleList.size(); j++){
                    TableInterfacerole ti2 = tableInterfaceroleList.get(j);
                    if(ti.getInterfaceid().equals(ti2.getInterfaceid()) && ti.getParametersid().equals(ti2.getParametersid()) && ti.getPreinterfaceid().equals(ti2.getPreinterfaceid())
                            && ti.getPreparametersid().equals(ti2.getPreparametersid())){
                        tmpList.add(ti2);
                    }
                }
            }
        }
        tableInterfaceroleList.removeAll(tmpList);
    }

    //构造接口关系
    private void setInterfaceParamRela(InterfaceXmlModel interfaceXmlModel,
                                   List<TableInterfacerole> tableInterfaceroleList,
                                   TableRole tableRole,
                                       InterfaceXmlModel fatherInterfaceXmlModel,
                                       InterfaceParamXmlModel param){

        if(null != fatherInterfaceXmlModel){
            TableInterfacerole tableInterfacerole = new TableInterfacerole();   //接口关系
            tableInterfacerole.setRoleid(tableRole.getId());
//            System.out.println(fatherInterfaceXmlModel.getId() + "/" + fatherInterfaceXmlModel.getId());
            tableInterfacerole.setPreinterfaceid(fatherInterfaceXmlModel.getId());
            tableInterfacerole.setPreparametersid(param.getId());
            tableInterfacerole.setInterfaceid(interfaceXmlModel.getId());
            tableInterfacerole.setParametersid(interfaceXmlModel.getParams().getParam().stream().filter(p -> p.getIotype() == 0).collect(Collectors.toList()).get(0).getId());
            tableInterfaceroleList.add(tableInterfacerole);
        }

        interfaceXmlModel.getParams().getParam().forEach(pa ->{
            if(pa.getInterfaces().getInterfa().size() > 0){
                pa.getInterfaces().getInterfa().forEach(inx ->{
                    setInterfaceParamRela(inx, tableInterfaceroleList, tableRole, interfaceXmlModel, pa);
                });
            }
        });
    }




    /**
     * 给接口参数设置ID,同时构造总的关系条数
     * @param interfaceXmlModel
     * @param map
     */
    private void SetInterfaceParam(InterfaceXmlModel interfaceXmlModel,
                                   List<TableInterfacerole> tableInterfaceroleList,
                                   List<TableAlgorithmcondition> tableAlgorithmconditionList,
                                   Map map, Map map2, TableRole tableRole, Map transMap){
        if(null != interfaceXmlModel.getParams()){
            List<InterfaceParamXmlModel> interfaceParamXmlModelList = interfaceXmlModel.getParams().getParam();
            interfaceParamXmlModelList.forEach(interfaceParamXmlModel ->{
                String id = UUID.randomUUID().toString().replace("-", "");
                if(null != map.get(interfaceParamXmlModel.getId())){
                    interfaceParamXmlModel.setId(String.valueOf(map.get(interfaceXmlModel.getId())));
                }else{

                    transMap.put(interfaceParamXmlModel.getId(), id);
                    map.put(interfaceParamXmlModel.getId(), id);
                    interfaceParamXmlModel.setId(id);
                    map2.put(id, interfaceParamXmlModel.getId());

                    if(interfaceParamXmlModel.getIotype() == 1){    //输出
//                        if(null != interfaceParamXmlModel.getInterfa()){
                            TableInterfacerole tableInterfacerole = new TableInterfacerole();   //接口关系
                            tableInterfacerole.setRoleid(tableRole.getId());
                            tableInterfacerole.setPreinterfaceid(interfaceXmlModel.getId());
                            tableInterfacerole.setPreparametersid(interfaceParamXmlModel.getId());
                            tableInterfacerole.setDes(interfaceParamXmlModel.getDesc());
                            tableInterfacerole.setRemark(interfaceParamXmlModel.getRemark());
                            tableInterfaceroleList.add(tableInterfacerole);
//                        }
                    }
                    interfaceParamXmlModel.getActions().getAction().forEach(action ->{
                        TableAlgorithmcondition tableAlgorithmcondition = new TableAlgorithmcondition();
                        tableAlgorithmcondition.setInterfaceparametersid(id);
                        tableAlgorithmcondition.setBehavior(action.getBehave());
                        tableAlgorithmcondition.setValuesources(action.getOrigin());
                        tableAlgorithmcondition.setExpression(action.getExpression());
                        tableAlgorithmcondition.setRemark(action.getRemark());
                        tableAlgorithmconditionList.add(tableAlgorithmcondition);
                    });
                }
            });
            interfaceParamXmlModelList.forEach(ix -> {
//                if(null != ix.getInterfa()){    //有下一层
//                    SetInterfaceParam(ix.getInterfa(), tableInterfaceroleList, tableAlgorithmconditionList, map, map2, tableRole, transMap);
//                }
            });
        }
    }

    //获取所有接口ID
    private void GetAllInterfaceId(InterfaceXmlModel interfaceXmlModel,
                                   Set<String> interfaceIdSet){
        interfaceIdSet.add(interfaceXmlModel.getId());
        if(null != interfaceXmlModel.getParams()){
            List<InterfaceParamXmlModel> interfaceParamXmlModelList = interfaceXmlModel.getParams().getParam();
            interfaceParamXmlModelList.forEach(interfaceParamXmlModel -> {
                if(interfaceParamXmlModel.getInterfaces().getInterfa().size() > 0){    //有下一层
                    interfaceParamXmlModel.getInterfaces().getInterfa().forEach(ixm ->{
                        GetAllInterfaceId(ixm, interfaceIdSet);
                    });
                }
            });
        }
    }

    /**
     * 给所有接口赋值ID
     * @param interfaceXmlModel
     * @param map
     * @param fg
     */
    private void SetAllInterfaceId(InterfaceXmlModel interfaceXmlModel,
                                   Map tmap,
                                   Boolean fg){
        if(null != interfaceXmlModel.getParams()){
            List<InterfaceParamXmlModel> interfaceParamXmlModelList = interfaceXmlModel.getParams().getParam();
            if(fg){
                String id = UUID.randomUUID().toString().replace("-", "");
                if(tmap.get(interfaceXmlModel.getId()) != null){
                    interfaceXmlModel.setId(String.valueOf(tmap.get(interfaceXmlModel.getId())));
                }else{
                    tmap.put(interfaceXmlModel.getId(), id);
                    interfaceXmlModel.setId(id);
                }
            }

            interfaceParamXmlModelList.forEach(interfaceParamXmlModel -> {
                if(interfaceParamXmlModel.getInterfaces().getInterfa().size() > 0){
                    interfaceParamXmlModel.getInterfaces().getInterfa().forEach(ixm ->{
                        SetAllInterfaceId(ixm, tmap, true);
                    });
                }
            });
        }
    }

    private void clearRleBehavior(List<TableAlgorithmcondition> tableAlgorithmconditionList){
        List<TableAlgorithmcondition> tmpList = new ArrayList<TableAlgorithmcondition>();
        for(int i = 0; i < tableAlgorithmconditionList.size() - 1; i++){
            TableAlgorithmcondition ti = tableAlgorithmconditionList.get(i);
            if(!tmpList.contains(ti)){
                for(int j = i + 1; j < tableAlgorithmconditionList.size(); j++){
                    TableAlgorithmcondition ti2 = tableAlgorithmconditionList.get(j);
                    if(ti.getInterfaceparametersid().equals(ti2.getInterfaceparametersid()) && ti.getInterfaceroleid().intValue() == ti2.getInterfaceroleid().intValue()){
                        tmpList.add(ti2);
                    }
                }
            }
        }
        tableAlgorithmconditionList.removeAll(tmpList);
    }

    private void GetAllRleBehavior(List<TableInterfacerole> tableInterfaceroleList, List<TableAlgorithmcondition> tableAlgorithmconditionList){
        tableInterfaceroleList.forEach(tableInterfacerole -> {
            tableAlgorithmconditionList.forEach(condition ->{
                if(tableInterfacerole.getParametersid().equals(condition.getInterfaceparametersid()) || tableInterfacerole.getPreparametersid().equals(condition.getInterfaceparametersid())){
                    condition.setInterfaceroleid(tableInterfacerole.getId());
                }
            });
        });
    }

    //获取所有动作
    private void GetAllBehavior(InterfaceXmlModel interfaceXmlModel, List<TableAlgorithmcondition> tableAlgorithmconditionList, Map tmap){
        if(null != interfaceXmlModel.getParams()){
            List<InterfaceParamXmlModel> interfaceParamXmlModelList = interfaceXmlModel.getParams().getParam();
            interfaceParamXmlModelList.forEach(param ->{
                param.getActions().getAction().forEach(action ->{
                        TableAlgorithmcondition tableAlgorithmcondition = new TableAlgorithmcondition();
                        tableAlgorithmcondition.setInterfaceparametersid(String.valueOf(tmap.get(action.getParamId())));
                        tableAlgorithmcondition.setBehavior(action.getBehave());
                        tableAlgorithmcondition.setValuesources(action.getOrigin());
                        tableAlgorithmcondition.setExpression(action.getExpression());
                        tableAlgorithmcondition.setRemark(action.getRemark());
                        tableAlgorithmconditionList.add(tableAlgorithmcondition);
                });
            });

            interfaceParamXmlModelList.forEach(interfaceParamXmlModel -> {
                if(interfaceParamXmlModel.getInterfaces().getInterfa().size() > 0){
                    interfaceParamXmlModel.getInterfaces().getInterfa().forEach(ixm ->{
                        GetAllBehavior(ixm, tableAlgorithmconditionList, tmap);
                    });
                }
            });
        }
    }

    /**
     * 构造接口参数表
     * @param interfaceXmlModel
     * @param tableInterfaceparametersList  接口参数表
     */
    private void GetAllInterfaceParamFromModel(InterfaceXmlModel interfaceXmlModel,
                                               List<TableInterfaceparameters> tableInterfaceparametersList,
                                               Map vtmap){
        if(null != interfaceXmlModel.getParams()){
            List<InterfaceParamXmlModel> interfaceParamXmlModelList = interfaceXmlModel.getParams().getParam();
            interfaceParamXmlModelList.forEach(interfaceParamXmlModel ->{
                if(vtmap.get(interfaceParamXmlModel.getId()) != null){
                    //接口参数
                    TableInterfaceparameters tableInterfaceparameters = new TableInterfaceparameters();
                    tableInterfaceparameters.setId(interfaceParamXmlModel.getId());
                    tableInterfaceparameters.setInterfaceid(interfaceXmlModel.getId());
                    tableInterfaceparameters.setParameterssources(interfaceParamXmlModel.getOrigin());
                    tableInterfaceparameters.setInorout(interfaceParamXmlModel.getIotype());
                    tableInterfaceparameters.setParametersname(interfaceParamXmlModel.getName());
                    tableInterfaceparametersList.add(tableInterfaceparameters);
                    vtmap.remove(interfaceParamXmlModel.getId());
                }
            });

            interfaceParamXmlModelList.forEach(interfaceParamXmlModel -> {
                if(interfaceParamXmlModel.getInterfaces().getInterfa().size() > 0){
                    interfaceParamXmlModel.getInterfaces().getInterfa().forEach(ixm ->{
                        GetAllInterfaceParamFromModel(ixm, tableInterfaceparametersList, vtmap);
                    });
                }
            });

        }
    }

    /**
     * 构造接口表信息
     * @param interfaceXmlModel
     * @param tableOperatorinterfaceList
     * @param tableRole
     */
    private void GetAllInterfaceFromModel(InterfaceXmlModel interfaceXmlModel,
                                          List<TableOperatorinterface> tableOperatorinterfaceList,
                                          TableRole tableRole,
                                          boolean fg){
        if(null != interfaceXmlModel.getParams()){
            if(fg){
                AtomicBoolean flag = new AtomicBoolean(true);
                tableOperatorinterfaceList.forEach(tableOperatorinterface -> {
                    if(tableOperatorinterface.getId().equals(interfaceXmlModel.getId())){
                        flag.set(false);
                    }
                });
                if(flag.get()){
                    TableOperatorinterface tableOperatorinterface = new TableOperatorinterface();
                    tableOperatorinterface.setId(interfaceXmlModel.getId());
                    tableOperatorinterface.setAlgorithmid(interfaceXmlModel.getAlgorithm().getId());
                    tableOperatorinterface.setInterfacename(interfaceXmlModel.getName());
                    tableOperatorinterface.setRoleid(tableRole.getId());
                    tableOperatorinterfaceList.add(tableOperatorinterface);
                }
            }

            List<InterfaceParamXmlModel> interfaceParamXmlModelList = interfaceXmlModel.getParams().getParam();
            interfaceParamXmlModelList.forEach(ix -> {
                if(ix.getInterfaces().getInterfa().size() > 0){
                    ix.getInterfaces().getInterfa().forEach(ixm ->{
                        GetAllInterfaceFromModel(ixm, tableOperatorinterfaceList, tableRole, true);
                    });
                }
            });

        }
    }


    /**
     *  构造算法接口关系表
     * @param interfaceXmlModel
     * @param tableInterfaceroleList  接口关系列表
     */
    private void GetAllInterfaceRoleFromModel(InterfaceXmlModel interfaceXmlModel,
                                              List<TableInterfacerole> tableInterfaceroleList){
        tableInterfaceroleList.forEach(tableInterfacerole -> {
            if(tableInterfacerole.getPreinterfaceid().equals(interfaceXmlModel.getId())){
                if((tableInterfacerole.getInterfaceid() == null) &&
                        (tableInterfacerole.getParametersid() == null)){
                    interfaceXmlModel.getParams().getParam().forEach(interfaceParamXmlModel -> {
                        if(tableInterfacerole.getPreparametersid().equals(interfaceParamXmlModel.getId())){
//                            if(null != interfaceParamXmlModel.getInterfa()){
//                                tableInterfacerole.setInterfaceid(interfaceParamXmlModel.getInterfa().getId());
//                                tableInterfacerole.setParametersid(interfaceParamXmlModel.getInterfa().getParams().getParam().stream().filter(inp -> inp.getIotype() == 0).collect(Collectors.toList()).get(0).getId());
//                            }
                        }
                    });
                }
            }
        });
        interfaceXmlModel.getParams().getParam().forEach(ix -> {
//            if(null != ix.getInterfa()){    //有下一层
//                GetAllInterfaceRoleFromModel(ix.getInterfa() ,tableInterfaceroleList);
//            }
        });
        /*if(null != interfaceXmlModel.getParams()){
            List<InterfaceParamXmlModel> interfaceParamXmlModelList = interfaceXmlModel.getParams().getParam();
            interfaceParamXmlModelList.forEach(interfaceParamXmlModel -> {
                if(interfaceParamXmlModel.getIotype() == 1){    //输出
                    TableInterfacerole tableInterfacerole = new TableInterfacerole();   //接口关系
                    tableInterfacerole.setRoleid(tableRole.getId());
                    tableInterfacerole.setPreparametersid(interfaceParamXmlModel.getId());
                    tableInterfacerole.setPreinterfaceid(interfaceXmlModel.getId());
                    tableInterfacerole.setDes(interfaceParamXmlModel.getDesc());
                    tableInterfacerole.setRemark(interfaceParamXmlModel.getRemark());

                    if(null != interfaceParamXmlModel.getInterfa()){    //有下一层
                        GetAllInterfaceRoleFromModel(interfaceParamXmlModel.getInterfa(), tableInterfaceroleList, tableInterfacerole, tableRole, map);
                    }
                }else if(interfaceParamXmlModel.getIotype() == 0){  //输入
                    if(null != transTableInterfacerole){
                        transTableInterfacerole.setInterfaceid(interfaceXmlModel.getId());
                        transTableInterfacerole.setParametersid(interfaceParamXmlModel.getId());
                        AtomicBoolean flag = new AtomicBoolean(true);
                        tableInterfaceroleList.forEach(tableInterfacerole ->{
                            if((tableInterfacerole.getInterfaceid().equals(transTableInterfacerole.getInterfaceid()))
                                    && (tableInterfacerole.getParametersid().equals(transTableInterfacerole.getParametersid()))
                                    && (tableInterfacerole.getPreinterfaceid().equals(transTableInterfacerole.getPreinterfaceid()))
                                    && (tableInterfacerole.getPreparametersid().equals(transTableInterfacerole.getPreparametersid()) )){
                                flag.set(false);
                            }
                        });
                        if(flag.get()){
                            tableInterfaceroleList.add(transTableInterfacerole);
                        }
                    }
                }
            });
        }*/
    }

    //构造算法
    private RuleXmlModel constructRule(TableRole tableRole){
        RuleXmlModel rule = new RuleXmlModel();
        rule.setName(tableRole.getRolename());
        rule.setDesc(tableRole.getDes());
        rule.setRemark(tableRole.getRemark());
        rule.setEntrancenote(tableRole.getEntrancenote());
        rule.setCoordinate(tableRole.getCoordinate());
        rule.setUserid(tableRole.getUuserid());
        rule.setRoleGroup(tableRole.getRolegroup());
        rule.setStatus(tableRole.getStatus());
        rule.setRemark2(tableRole.getRemark2());
        rule.setRemark3(tableRole.getRemark3());
        return rule;
    }

    //规则的所有接口
    private List<TableOperatorinterface> constructAllInterfaceList(TableRole tableRole){
        TableOperatorinterfaceCriteria tableOperatorinterfaceCriteria = new TableOperatorinterfaceCriteria();
        tableOperatorinterfaceCriteria.createCriteria().andRoleidEqualTo(tableRole.getId());
        return operatorinterfaceMapper.selectByExample(tableOperatorinterfaceCriteria);
    }

    //规则所有关系
    private List<TableInterfacerole> constructAllInterfaceroleList(TableRole tableRole){
        TableInterfaceroleCriteria tableInterfaceroleCriteria = new TableInterfaceroleCriteria();
        tableInterfaceroleCriteria.createCriteria().andRoleidEqualTo(tableRole.getId());
        return interfaceroleMapper.selectByExample(tableInterfaceroleCriteria);
    }

    //规则所有算子
    private List<TableAlgorithm> constructAllTableAlgorithmList(List<Integer> allAlgorithmidList){
        TableAlgorithmCriteria tableAlgorithmCriteria = new TableAlgorithmCriteria();
        tableAlgorithmCriteria.createCriteria().andIdIn(allAlgorithmidList);
        return tableAlgorithmMapper.selectByExample(tableAlgorithmCriteria);
    }

    //所有算子参数
    private List<TableFunc> constructAllFuncList(List<Integer> allAlgorithmidList){
        TableFuncCriteria tableFuncCriteria = new TableFuncCriteria();
        tableFuncCriteria.createCriteria().andAlgorithmidIn(allAlgorithmidList);
        return tableFuncMapper.selectByExample(tableFuncCriteria);
    }

    //所有接口参数
    private List<TableInterfaceparameters> constructAllInterfaceParametersList(List<String> allInterfaceidList){
        TableInterfaceparametersCriteria interfaceparametersCriteria = new TableInterfaceparametersCriteria();
        interfaceparametersCriteria.createCriteria().andInterfaceidIn(allInterfaceidList);
        return tableInterfaceparametersMapper.selectByExample(interfaceparametersCriteria);
    }

    //所有参数动作
    private  List<TableAlgorithmcondition> constructAllConditionList(List<String> allInterfaceParamIdList){
        TableAlgorithmconditionCriteria tableAlgorithmconditionCriteria = new TableAlgorithmconditionCriteria();
        tableAlgorithmconditionCriteria.createCriteria().andInterfaceparametersidIn(allInterfaceParamIdList);
        return tableAlgorithmconditionMapper.selectByExample(tableAlgorithmconditionCriteria);
    }

    //首接口ID结合
    private Set<String> constructFirstInterfaceIdSet(List<TableInterfacerole> allInterfaceRelevanceList){
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
        return firstInterfaceIdSet;
    }

    //数据库接口实体->输出接口实体
    private InterfaceXmlModel convertDbInterfaceToOutInterface(TableOperatorinterface tableOperatorinterface){
        InterfaceXmlModel interfaceXmlModel = new InterfaceXmlModel();
        interfaceXmlModel.setName(tableOperatorinterface.getInterfacename());
        interfaceXmlModel.setId(tableOperatorinterface.getId());
        AlgorithmXmlModel algorithm = new AlgorithmXmlModel();
        algorithm.setId(tableOperatorinterface.getAlgorithmid());
        interfaceXmlModel.setAlgorithm(algorithm);
        return interfaceXmlModel;
    }

    //数据库算子参数实体,赋值给输出算子参数
    private ParamXmlModel convertDbFuncToOutParam(TableFunc tableFunc){
        ParamXmlModel paramXmlModel = new ParamXmlModel();
        paramXmlModel.setId(tableFunc.getId());
        paramXmlModel.setName(tableFunc.getVarname());
        paramXmlModel.setType(tableFunc.getVartype());
        paramXmlModel.setValue(tableFunc.getValvalue());
        paramXmlModel.setIotype(tableFunc.getInorout().intValue());
        paramXmlModel.setRemark(tableFunc.getRemark());
        paramXmlModel.setName(tableFunc.getParametername());
        return paramXmlModel;
    }

    //数据库接口参数实体->输出接口参数实体
    private InterfaceParamXmlModel convertDbIparamToOutIpxm(TableInterfaceparameters tableInterfaceparameters){
        InterfaceParamXmlModel interfaceParamXmlModel = new InterfaceParamXmlModel();
        interfaceParamXmlModel.setId(tableInterfaceparameters.getId());
        interfaceParamXmlModel.setName(tableInterfaceparameters.getParametersname());
        interfaceParamXmlModel.setOrigin(tableInterfaceparameters.getParameterssources());
        interfaceParamXmlModel.setIotype(tableInterfaceparameters.getInorout().intValue());
        return interfaceParamXmlModel;
    }

    //数据库参数动作实体->输出参数动作实体
    private BehaviourXmlModel convertDbTConditionToOutBehivor(TableAlgorithmcondition tableAlgorithmcondition,
                                                              InterfaceParamXmlModel interfaceParamXmlModel){
        BehaviourXmlModel behaviourXmlModel = new BehaviourXmlModel();
        behaviourXmlModel.setBehave(tableAlgorithmcondition.getBehavior());
        behaviourXmlModel.setExpression(tableAlgorithmcondition.getExpression());
        behaviourXmlModel.setOrigin(tableAlgorithmcondition.getValuesources());
        behaviourXmlModel.setRemark(tableAlgorithmcondition.getRemark());
        behaviourXmlModel.setParamId(interfaceParamXmlModel.getId());
        return behaviourXmlModel;
    }



    //数据库算子实体,赋值给输出算子实体
    private void configDbAlgorithmData(InterfaceXmlModel interfaceXmlModel,
                                       TableAlgorithm tableAlgorithm){
        interfaceXmlModel.getAlgorithm().setName(tableAlgorithm.getAlgorithmname());
        interfaceXmlModel.getAlgorithm().setAuthor(tableAlgorithm.getAlgorithmauthor());
        interfaceXmlModel.getAlgorithm().setIspub(tableAlgorithm.getIspublic().intValue());
        interfaceXmlModel.getAlgorithm().setType(tableAlgorithm.getAlgorithmtype().intValue());
        interfaceXmlModel.getAlgorithm().setFunc(tableAlgorithm.getAlgorithmfun());
        interfaceXmlModel.getAlgorithm().setDesc(tableAlgorithm.getDes());
        interfaceXmlModel.getAlgorithm().setRemark(tableAlgorithm.getRemark());
        interfaceXmlModel.getAlgorithm().setAlgorithmGroup(tableAlgorithm.getAlgorithmgroup());
        interfaceXmlModel.getAlgorithm().setStatus(tableAlgorithm.getStatus());
        interfaceXmlModel.getAlgorithm().setRemarks2(tableAlgorithm.getRemark2());
    }

    //设置首接口
    private void setFirstIntefa(Set<String> firstInterfaceIdSet,
                                List<TableOperatorinterface> allInterfaceList,
                                RuleXmlModel rule){
        InterfacesXmlModel interfacesXmlModel = new InterfacesXmlModel();
        firstInterfaceIdSet.forEach(fid -> {
            allInterfaceList.forEach(tableOperatorinterface -> {
                if(tableOperatorinterface.getId().equals(fid)){     //锁定首接口
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
    }

    //设置首接口算子以及算子参数
    private void setFirstIntefaAlgorithm(RuleXmlModel rule,
                                         List<TableAlgorithm> allAlgorithmList,
                                         List<TableFunc> allFuncList){
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
                    interfaceXmlModel.getAlgorithm().setAlgorithmGroup(tableAlgorithm.getAlgorithmgroup());
                    interfaceXmlModel.getAlgorithm().setStatus(tableAlgorithm.getStatus());
                    interfaceXmlModel.getAlgorithm().setRemarks2(tableAlgorithm.getRemark2());
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
                            paramXmlModel.setName(tableFunc.getParametername());
                            paramsXmlModel.getParam().add(paramXmlModel);
                        }
                    });
                    interfaceXmlModel.getAlgorithm().setParams(paramsXmlModel);
                }
            });
        });
    }
    //设置首接口的接口参数和行为
    private void setFirstIntefaInteParam(RuleXmlModel rule,
                                         List<TableInterfaceparameters> allInterfaceParametersList,
                                         List<TableAlgorithmcondition> allConditionList){
        rule.getInterfaces().getInterfa().forEach(interfaceXmlModel ->{
            InterfaceParamsXmlModel interfaceParamsXmlModel = new InterfaceParamsXmlModel();
            allInterfaceParametersList.forEach(tableInterfaceparameters -> {
                if(tableInterfaceparameters.getInterfaceid().equals(interfaceXmlModel.getId())){
                    InterfaceParamXmlModel interfaceParamXmlModel = new InterfaceParamXmlModel();
                    interfaceParamXmlModel.setId(tableInterfaceparameters.getId());
                    interfaceParamXmlModel.setName(tableInterfaceparameters.getParametersname());
                    interfaceParamXmlModel.setOrigin(tableInterfaceparameters.getParameterssources());
                    interfaceParamXmlModel.setIotype(tableInterfaceparameters.getInorout().intValue());
                    BehavioursXmlModel behavioursXmlModel = new BehavioursXmlModel();
                    allConditionList.forEach(tableAlgorithmcondition -> {
                        if(tableAlgorithmcondition.getInterfaceparametersid().equals(tableInterfaceparameters.getId())){
                            BehaviourXmlModel behaviourXmlModel = new BehaviourXmlModel();
                            behaviourXmlModel.setBehave(tableAlgorithmcondition.getBehavior());
                            behaviourXmlModel.setExpression(tableAlgorithmcondition.getExpression());
                            behaviourXmlModel.setOrigin(tableAlgorithmcondition.getValuesources());
                            behaviourXmlModel.setRemark(tableAlgorithmcondition.getRemark());
                            behaviourXmlModel.setParamId(interfaceParamXmlModel.getId());
                            behavioursXmlModel.getAction().add(behaviourXmlModel);
                        }
                    });
                    interfaceParamXmlModel.setActions(behavioursXmlModel);
                    interfaceParamsXmlModel.getParam().add(interfaceParamXmlModel);
                }
            });
            interfaceXmlModel.setParams(interfaceParamsXmlModel);
        });
    }

    //子接口判重
    private Boolean judgeRepeatChildInterface(InterfaceParamXmlModel interfaceParamXmlModel,
                                              String interfaceId){
        AtomicReference<Boolean> flag = new AtomicReference<>(true);
        interfaceParamXmlModel.getInterfaces().getInterfa().forEach(interfaceXmlModel -> {
            if(interfaceXmlModel.getId().equals(interfaceId)){
                flag.set(false);
            }
        });
        return flag.get();
    }

    //设置子参数
    private void setChildIntefa(InterfaceXmlModel interfaceXmlModel,
                                List<TableInterfacerole> allInterfaceRelevanceList,
                                List<TableOperatorinterface> allInterfaceList,
                                List<TableAlgorithm> allAlgorithmList,
                                List<TableFunc> allFuncList,
                                List<TableInterfaceparameters> allInterfaceParametersList,
                                List<TableAlgorithmcondition> allConditionList){
        Set<Integer> removeIndex = new HashSet<Integer>();
        final int[] count = {0};
        List<InterfaceXmlModel> nxtImList = new ArrayList<InterfaceXmlModel>();
        interfaceXmlModel.getParams().getParam().forEach(interfaceParamXmlModel -> {    //循环接口参数
            //=============================设置参数子接口===============================//
            if(interfaceParamXmlModel.getIotype() == 1){    //输出
                allInterfaceRelevanceList.forEach(tableInterfacerole -> {   //接口关系
                    if(tableInterfacerole.getPreparametersid().equals(interfaceParamXmlModel.getId())){
                        removeIndex.add(count[0]);
//                        System.out.println("关系ID:" + tableInterfacerole.getId() + ",前参数ID:" + tableInterfacerole.getPreparametersid());
                        //获取子接口
                        allInterfaceList.forEach(tableOperatorinterface -> {
                            if(tableInterfacerole.getInterfaceid().equals(tableOperatorinterface.getId())){
//                                System.out.println("父参数ID:" + interfaceParamXmlModel.getId() + ",子接口ID:" + tableOperatorinterface.getId());
                                if(judgeRepeatChildInterface(interfaceParamXmlModel, tableOperatorinterface.getId())){
                                    InterfaceXmlModel ixm = convertDbInterfaceToOutInterface(tableOperatorinterface);
                                    InterfaceParamsXmlModel interfaceParamsXmlModel = new InterfaceParamsXmlModel();
                                    allInterfaceParametersList.forEach(tableInterfaceparameters -> {
                                        if(tableInterfaceparameters.getInterfaceid().equals(ixm.getId())){
                                            Boolean flag = false;
                                            if(tableInterfaceparameters.getInorout().intValue() == 0){  //输入
                                                if(tableInterfaceparameters.getId().equals(tableInterfacerole.getParametersid())){
                                                    flag = true;
                                                }
                                            }else{
                                                flag = true;
                                            }
                                            if(flag){
                                                InterfaceParamXmlModel ipxm = convertDbIparamToOutIpxm(tableInterfaceparameters);
                                                BehavioursXmlModel behavioursXmlModel = new BehavioursXmlModel();
                                                allConditionList.forEach(tableAlgorithmcondition -> {
                                                    if(tableAlgorithmcondition.getInterfaceparametersid().equals(tableInterfaceparameters.getId())){
                                                        behavioursXmlModel.getAction().add(convertDbTConditionToOutBehivor(tableAlgorithmcondition, ipxm));
                                                    }
                                                });
                                                ipxm.setActions(behavioursXmlModel);
                                                interfaceParamsXmlModel.getParam().add(ipxm);
                                            }

                                        }
                                    });
                                    ixm.setParams(interfaceParamsXmlModel);
                                    interfaceParamXmlModel.getInterfaces().getInterfa().add(ixm);
                                    nxtImList.add(ixm);
                                }
                            }
                        });
                    }
                    count[0]++;
                });
            //=============================设置参数子接口===============================//
            }

            //===============================设置子接口算子/算子参数=====================================//
            interfaceParamXmlModel.getInterfaces().getInterfa().forEach(childInterfaceXmlModel ->{
                allAlgorithmList.forEach(tableAlgorithm -> {
                    if(tableAlgorithm.getId().intValue() == interfaceXmlModel.getAlgorithm().getId().intValue()){
                        configDbAlgorithmData(childInterfaceXmlModel, tableAlgorithm);
                        ParamsXmlModel paramsXmlModel = new ParamsXmlModel();
                        allFuncList.forEach(tableFunc -> {
                            if(tableFunc.getAlgorithmid().intValue() == tableAlgorithm.getId().intValue()){
                                paramsXmlModel.getParam().add(convertDbFuncToOutParam(tableFunc));
                            }
                        });
                        childInterfaceXmlModel.getAlgorithm().setParams(paramsXmlModel);
                    }
                });
            });
            //===============================设置子接口算子/算子参数=====================================//
        });
        removeIndex.forEach(index ->{
            allInterfaceRelevanceList.remove(index);
        });
        if(allInterfaceRelevanceList.size() > 0){
            nxtImList.forEach(ixm -> {
                setChildIntefa(ixm,
                        allInterfaceRelevanceList,
                        allInterfaceList,
                        allAlgorithmList,
                        allFuncList,
                        allInterfaceParametersList,
                        allConditionList);
            });
        }
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
            RuleXmlModel rule = constructRule(tableRole);

            List<TableOperatorinterface> allInterfaceList = constructAllInterfaceList(tableRole);   //所有接口
            List<Integer> allAlgorithmidList = allInterfaceList.stream().map(TableOperatorinterface::getAlgorithmid).collect(Collectors.toList());  //所有算子ID
            List<String> allInterfaceidList = allInterfaceList.stream().map(TableOperatorinterface::getId).collect(Collectors.toList());    //所有接口ID
            List<TableInterfacerole> allInterfaceRelevanceList = constructAllInterfaceroleList(tableRole);   //规则所有关系
            Set<String> firstInterfaceIdSet = constructFirstInterfaceIdSet(allInterfaceRelevanceList);  //首接口ID
            List<TableAlgorithm> allAlgorithmList = constructAllTableAlgorithmList(allAlgorithmidList); //规则包含所有的算子
            List<TableFunc> allFuncList = constructAllFuncList(allAlgorithmidList); //算子参数
            List<TableInterfaceparameters> allInterfaceParametersList = constructAllInterfaceParametersList(allInterfaceidList);    //所有接口参数
            List<String> allInterfaceParamIdList = allInterfaceParametersList.stream().map(TableInterfaceparameters::getId).collect(Collectors.toList());   //所有接口参数ID
            List<TableAlgorithmcondition> allConditionList = constructAllConditionList(allInterfaceParamIdList);    //所有接口参数的动作

            setFirstIntefa(firstInterfaceIdSet, allInterfaceList, rule);   //设置首接口
            setFirstIntefaAlgorithm(rule, allAlgorithmList, allFuncList);  //设置首接口算子以及算子参数
            setFirstIntefaInteParam(rule, allInterfaceParametersList, allConditionList);    //设置首接口的接口参数和行为
            setChildIntefa(rule.getInterfaces().getInterfa().get(0),
                    allInterfaceRelevanceList,
                    allInterfaceList,
                    allAlgorithmList,
                    allFuncList,
                    allInterfaceParametersList,
                    allConditionList);
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
                            //所有接口参数
                            allInterfaceParametersList.forEach(tableInterfaceparameters -> {
                                if(tableInterfaceparameters.getInterfaceid().equals(interfaceXmlModel.getId())){
                                    boolean flag = true;

                                    if(tableInterfaceparameters.getInorout().intValue() == 0){  //输入
                                        if(!tableInterfaceparameters.getId().equals(tableInterfacerole.getParametersid())){
                                            flag = false;
                                        }
                                    }
                                    if(flag){
                                        InterfaceParamXmlModel tmp = new InterfaceParamXmlModel();
                                        tmp.setId(tableInterfaceparameters.getId());
                                        tmp.setName(tableInterfaceparameters.getParametersname());
                                        tmp.setOrigin(tableInterfaceparameters.getParameterssources());
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
                                                if(tmp.getIotype() == 1){    //输出
                                                    allInterfaceRelevanceList.forEach(relevance ->{
                                                        if(relevance.getPreparametersid().equals(tmp.getId())){
                                                            aflag.set(true);
                                                        }
                                                    });
                                                }
                                                if(aflag.get()){

                                                }
                                            }


                                            //list深度拷贝
                                            List<TableInterfacerole> newList = new ArrayList<TableInterfacerole>();
                                            CollectionUtils.addAll(newList, new Object[allInterfaceRelevanceList.size()]);
                                            Collections.copy(newList, allInterfaceRelevanceList);
                                            if(newList.size() > 1){
                                                newList.remove(0);
                                            }

                                            setInterfaceData(newList, tmp, allInterfaceList, allAlgorithmList, allFuncList,
                                                    allInterfaceParametersList, allInterfaceParamIdList, allConditionList);
                                        });
                                        tmp.setActions(behavioursXmlModel);
                                        interfaceParamsXmlModel.getParam().add(tmp);
                                    }
                                }
                            });
                            interfaceXmlModel.setParams(interfaceParamsXmlModel);
//                            interfaceParamXmlModel.setInterfa(interfaceXmlModel);
                        }
                });
            }
        });
    }

    @Transactional(readOnly = true,isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRES_NEW)
    @Override
    public List<TableRole> GetAllAlgorithmRule(String groupName) {
        TableRoleCriteria tableRoleCriteria=new TableRoleCriteria();
        tableRoleCriteria.createCriteria().andRolegroupEqualTo(groupName);
        return roleMapper.selectByExample(tableRoleCriteria);
    }
    @Transactional(readOnly = true,isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRES_NEW)
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
                tableOperatorinterface.setId(operatorInterfaceDataModel.getId());
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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public AlgorithmRuleSaveDataModel saveAlgorithmRule(AlgorithmRuleSaveDataModel algorithmRuleSaveDataModel) {
        try {
            //保存最后算法规则信息
            TableRole tableRole=algorithmRuleSaveDataModel.getTableRole();
            tableRole.setStatus("未发布");
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
                        algorithmconditionMapper.insert(conditions);
                    });
                });
            }
            return algorithmRuleSaveDataModel;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean modAlgorithmRule(InterfaceRoleDataModel interfaceRoleDataModel) {
        try {
            TableInterfacerole interfacerole=interfaceRoleDataModel.getTable_InterfaceRole();
//            interfaceroleMapper.updateByPrimaryKeySelective(interfacerole);//线本身无信息修改
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
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public OperatorInterfaceDataModel modInterfaceRole(OperatorInterfaceDataModel operatorInterfaceDataModel) {
        try {
            TableOperatorinterface tableOperatorinterface = new TableOperatorinterface();
            tableOperatorinterface.setId(operatorInterfaceDataModel.getId());
            tableOperatorinterface.setInterfacename(operatorInterfaceDataModel.getInterfaceName());
            tableOperatorinterface.setAlgorithmid(operatorInterfaceDataModel.getAlgorithmID());
            tableOperatorinterface.setRoleid(operatorInterfaceDataModel.getRoleID());
            List<TableInterfaceparameters> interfaceparameters=operatorInterfaceDataModel.getTableInterfaceparametersList();
            //首先查询是否新增
            TableOperatorinterface tableOperatorinterface_pd=operatorinterfaceMapper.selectByPrimaryKey(tableOperatorinterface.getId());
            if(tableOperatorinterface_pd==null){//新增
                operatorinterfaceMapper.insert(tableOperatorinterface);
                interfaceparameters.forEach(interfaceparameter->interfaceparametersMapper.insert(interfaceparameter));
            }else{
                operatorinterfaceMapper.updateByPrimaryKeySelective(tableOperatorinterface);

                TableInterfaceparametersCriteria tableInterfaceparametersCriteria=new TableInterfaceparametersCriteria();
                tableInterfaceparametersCriteria.createCriteria().andInterfaceidEqualTo(operatorInterfaceDataModel.getId());
                List<TableInterfaceparameters> oldData=interfaceparametersMapper.selectByExample(tableInterfaceparametersCriteria);

                if(oldData.size()>0){
                    List<TableInterfaceparameters> addPara=new ArrayList<>();
                    List<TableInterfaceparameters> updataPara=new ArrayList<>();
                    List<TableInterfaceparameters> deletedataPara=new ArrayList<>();
                    if(interfaceparameters.size()==0){
                        deletedataPara=oldData;
                    }else{
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
                    }
                    //修改
                    updataPara.forEach(data->interfaceparametersMapper.updateByPrimaryKeySelective(data));
                    //删除
                    deletedataPara.forEach(data->{
                        //还需删除该接口参数的连线
                        TableInterfaceroleCriteria tableInterfaceroleCriteria=new TableInterfaceroleCriteria();
                        tableInterfaceroleCriteria.or().andParametersidEqualTo(data.getId());
                        tableInterfaceroleCriteria.or().andParametersidEqualTo(data.getId());
                        List<TableInterfacerole> interfaceroles=interfaceroleMapper.selectByExample(tableInterfaceroleCriteria);
                        if(interfaceroles.size()>0){
                            interfaceroles.forEach(interfacerole->{
                                delOneInterfaceRole(interfacerole.getId().toString());
                            });
                        }
                        interfaceparametersMapper.deleteByPrimaryKey(data.getId());
                    });
                    //新增
                    addPara.forEach(data->interfaceparametersMapper.insert(data));
                }else{
                    interfaceparameters.forEach(interfaceparameter->interfaceparametersMapper.insert(interfaceparameter));
                }
            }
            return operatorInterfaceDataModel;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return null;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean modAlgorithmRuleBase(TableRole tableRole) {
        try{
            return updateByPrimaryKeySelective(tableRole)==1;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return false;
        }
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean delAlgorithmRuleById(String Id) {
        if(Id==null||"".equals(Id)||Id.equals("0")){
            logger.warn("删除规则传入的规则ID不能为空");
            return false;
        }
        try {
            //删除所有算子关系
            TableInterfaceroleCriteria interfaceroleCriteria=new TableInterfaceroleCriteria();
            interfaceroleCriteria.createCriteria().andRoleidEqualTo(Integer.parseInt(Id));
            List<TableInterfacerole> interfaceroles=interfaceroleMapper.selectByExample(interfaceroleCriteria);
            interfaceroles.forEach(interfacerole->{
                delOneInterfaceRole(interfacerole.getId().toString());
            });
//            interfaceroleMapper.deleteByExample(interfaceroleCriteria);

            //删除所有的接口信息
            TableOperatorinterfaceCriteria tableOperatorinterfaceCriteria=new TableOperatorinterfaceCriteria();
            tableOperatorinterfaceCriteria.createCriteria().andRoleidEqualTo(Integer.parseInt(Id));
            List<TableOperatorinterface> operatorinterfaces=operatorinterfaceMapper.selectByExample(tableOperatorinterfaceCriteria);
            operatorinterfaces.forEach(operatorinterface->{
                delTableOperatorinterface(operatorinterface.getId());
            });
//            operatorinterfaceMapper.deleteByExample(tableOperatorinterfaceCriteria);
            //删除规则本身信息
            deleteByPrimaryKey(Integer.parseInt(Id));
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
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
            //查询所有的接口关系信息
            List<TableInterfacerole> interfaceroles=interfaceroleMapper.selectByExample(tableInterfaceroleCriteria);
            if(interfaceroles.size()>0){
                interfaceroles.forEach(interfacerole->{
                    delOneInterfaceRole(interfacerole.getId().toString());
                });
            }
//            interfaceroleMapper.deleteByExample(tableInterfaceroleCriteria);
            //删除接口参数信息
            TableInterfaceparametersCriteria tableInterfaceparametersCriteria=new TableInterfaceparametersCriteria();
            tableInterfaceparametersCriteria.createCriteria().andInterfaceidEqualTo(operatorinterfaceId);
            interfaceparametersMapper.deleteByExample(tableInterfaceparametersCriteria);
            //删除接口本身信息
            operatorinterfaceMapper.deleteByPrimaryKey(operatorinterfaceId);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean delOneInterfaceRole(String interfaceRoueId) {
        try{
            if(interfaceRoueId==null||"".equals(interfaceRoueId)||interfaceRoueId.equals("0")){
                logger.warn("删除算法接口关系传入的接口关系ID不能为空");
                return false;
            }
            //先删除线关联的动作
            TableAlgorithmconditionCriteria tableAlgorithmconditionCriteria=new TableAlgorithmconditionCriteria();
            tableAlgorithmconditionCriteria.createCriteria().andInterfaceroleidEqualTo(Integer.parseInt(interfaceRoueId));
            algorithmconditionMapper.deleteByExample(tableAlgorithmconditionCriteria);
            return interfaceroleMapper.deleteByPrimaryKey(Integer.parseInt(interfaceRoueId))==1;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return false;
        }

    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean saveFunAction(List<TableAlgorithmcondition> algorithmconditions,String interfaceParametersID,int interfaceRoleId,String actionRelation) {
        try {
            //修改动作间关系信息
            TableInterfacerole interfacerole=interfaceroleMapper.selectByPrimaryKey(interfaceRoleId);
            if(interfacerole.getParametersid().equals(interfaceParametersID)){
                interfacerole.setActionrelation(actionRelation);
            }else if(interfacerole.getPreparametersid().equals(interfaceParametersID)){
                interfacerole.setPreactionrelation(actionRelation);
            }
            interfaceroleMapper.updateByPrimaryKeySelective(interfacerole);
            TableAlgorithmconditionCriteria tableAlgorithmconditionCriteria=new TableAlgorithmconditionCriteria();
            tableAlgorithmconditionCriteria.createCriteria().andInterfaceroleidEqualTo(interfaceRoleId).andInterfaceparametersidEqualTo(interfaceParametersID);
            List<TableAlgorithmcondition> old=algorithmconditionMapper.selectByExample(tableAlgorithmconditionCriteria);
            if(old.size()>0){
                List<TableAlgorithmcondition> add=new ArrayList<>();
                List<TableAlgorithmcondition> delete=new ArrayList<>();
                List<TableAlgorithmcondition> uopdate=new ArrayList<>();
                if(algorithmconditions.size()==0){
                    delete=old;
                }else {
                    for(TableAlgorithmcondition _new:algorithmconditions){
                        boolean flag=false;
                        for(TableAlgorithmcondition _old:old){
                            if(_new.getId()==_old.getId()){
                                uopdate.add(_new);
                                flag=true;
                                break;
                            }
                        }
                        if(!flag){
                            add.add(_new);
                        }
                    }
                    for(TableAlgorithmcondition _old:old){
                        boolean flag=false;
                        for(TableAlgorithmcondition _upd:uopdate){
                            if(_old.getId()==_upd.getId()){
                                flag=true;
                                break;
                            }
                        }
                        if(!flag){
                            delete.add(_old);
                        }
                    }
                }
                add.forEach(data->{
                    data.setInterfaceroleid(interfaceRoleId);
                    algorithmconditionMapper.insert(data);
                });
                delete.forEach(data->algorithmconditionMapper.deleteByPrimaryKey(data.getId()));
                uopdate.forEach(data->algorithmconditionMapper.updateByPrimaryKeySelective(data));
            }else{
                algorithmconditions.forEach(data->{
                    data.setInterfaceroleid(interfaceRoleId);
                    algorithmconditionMapper.insert(data);
                });
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean updataStatus(String roleId,String status) {
        if(roleId==null||roleId.equals("0")){
            logger.warn("传入的ID无效");
            return false;
        }else {
            try {
                TableRole tableRole=new TableRole();
                tableRole.setId(Integer.parseInt(roleId));
                if(status.equals("发布")){
                    tableRole.setStatus("发布中");
                }else{
                    tableRole.setStatus("未发布");
                }
                return updateByPrimaryKeySelective(tableRole)==1;
            }catch (Exception e){
                logger.error(e.getMessage());
                return false;
            }
        }
    }
}
