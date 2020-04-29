package deepthinking.fgi.service.impl;

import com.github.pagehelper.PageInfo;
import deepthinking.fgi.Enum.AlgorithmtypeEnum;
import deepthinking.fgi.Enum.InOrOutType;
import deepthinking.fgi.dao.mapper.TableAlgorithmMapper;
import deepthinking.fgi.dao.mapper.TableFuncMapper;
import deepthinking.fgi.dao.mapper.TableOperatorinterfaceMapper;
import deepthinking.fgi.domain.*;
import deepthinking.fgi.model.AlgorithmBaseInfo;
import deepthinking.fgi.model.AlgorithmModel;
import deepthinking.fgi.service.TableAlgorithmService;
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
 * @data 2020/2/18 16:53
 */
@Service("tableAlgorithmService")
@Transactional
public class TableAlgorithmServiceImpl extends BaseServiceImpl<TableAlgorithm,Integer> implements TableAlgorithmService {

    private static Logger logger = LoggerFactory.getLogger(TableAlgorithmServiceImpl.class);
    @Resource
    private TableAlgorithmMapper tableAlgorithmMapper;
    @Resource
    private TableFuncMapper tableFuncMapper;
    @Resource
    private TableOperatorinterfaceMapper tableOperatorinterfaceMapper;

    @Override
    public List<AlgorithmBaseInfo> getAllAlgorithm(String groupName) {
        List<AlgorithmBaseInfo> result=new ArrayList<>();
        List<Map<String,Object>> tableAlgorithms=tableAlgorithmMapper.selectBaseInfo(groupName);
        if(tableAlgorithms.size()>0){
            tableAlgorithms.forEach(data->{
                AlgorithmBaseInfo algorithmBaseInfo=new AlgorithmBaseInfo();
                algorithmBaseInfo.setTableAlgorithm(data);
                int alfgorithmId=Integer.parseInt(data.get("id").toString());
                List<Map<String,Object>> funcs=tableFuncMapper.selectBaseInfo(alfgorithmId);
                algorithmBaseInfo.setTableFuncs(funcs);
                //查询每个算法的输入输出参数个数
                TableFuncCriteria tableFuncCriteria=new TableFuncCriteria();
                tableFuncCriteria.createCriteria().andAlgorithmidEqualTo(alfgorithmId).andInoroutEqualTo(InOrOutType.in.getType());
                int count_in= (int) tableFuncMapper.countByExample(tableFuncCriteria);
                algorithmBaseInfo.setInNum(count_in);
                tableFuncCriteria.clear();
                tableFuncCriteria.createCriteria().andAlgorithmidEqualTo(alfgorithmId).andInoroutEqualTo(InOrOutType.out.getType());
                int count_out= (int) tableFuncMapper.countByExample(tableFuncCriteria);
                algorithmBaseInfo.setOutNum(count_out);
                result.add(algorithmBaseInfo);
            });
        }
        return result;
    }

    @Override
    public int addAlgorithm(AlgorithmModel algorithmModel) {
        try {
            //算子名称不能重复
            TableAlgorithmCriteria tableAlgorithmCriteria=new TableAlgorithmCriteria();
            tableAlgorithmCriteria.createCriteria().andAlgorithmnameEqualTo(algorithmModel.getTableAlgorithm().getAlgorithmname())
                    .andAlgorithmgroupEqualTo(algorithmModel.getTableAlgorithm().getAlgorithmgroup());
            List<TableAlgorithm> tableAlgorithms=tableAlgorithmMapper.selectByExample(tableAlgorithmCriteria);
            if(tableAlgorithms.size()>0){//重名
                return 2;
            }
            //设置状态
            algorithmModel.getTableAlgorithm().setStatus("未发布");
            insertSelective(algorithmModel.getTableAlgorithm());
            //获取算子ID
            int id=algorithmModel.getTableAlgorithm().getId();
            if(algorithmModel.getTableAlgorithm().getAlgorithmtype()!= AlgorithmtypeEnum.logical.getAlgorithmtype()){
                List<TableFunc> tableFuncs=algorithmModel.getTableFuncs();
                if(tableFuncs.size()>0){
                    if(algorithmModel.getTableAlgorithm().getAlgorithmtype()== AlgorithmtypeEnum.formula.getAlgorithmtype()){
                        TableFunc tableFunc=new TableFunc();
                        tableFunc.setInorout(InOrOutType.out.getType());
                        tableFunc.setParametername("公式计算结果");
                        tableFunc.setVarname("result");
                        tableFunc.setVartype(tableFuncs.get(0).getVartype());
                        tableFunc.setValvalue(tableFuncs.get(0).getValvalue());
                        tableFunc.setRemark("");
                        tableFuncs.add(tableFunc);
                    }
                    tableFuncs.stream().forEach(funcs->{
                        funcs.setAlgorithmid(id);//设置算子ID
                        tableFuncMapper.insert(funcs);
                    });
                }
            }

        }catch (Exception e){
            logger.error(e.getMessage());
            return 0;
        }
        return 1;
    }

    @Override
    public AlgorithmModel getAlgorithmById(String algthId) {
        if(algthId==null||"".equals(algthId)){
            logger.warn(algthId+" 获取算子信息的算子ID不能为空");
            return null;
        }
        AlgorithmModel algorithmModel=new AlgorithmModel();
        TableAlgorithm tableAlgorithm=selectByPrimaryKey(Integer.parseInt(algthId));
        TableFuncCriteria tableFuncCriteria=new TableFuncCriteria();
        tableFuncCriteria.createCriteria().andAlgorithmidEqualTo(Integer.parseInt(algthId));
        List<TableFunc> tableFuncs=tableFuncMapper.selectByExample(tableFuncCriteria);
        algorithmModel.setTableAlgorithm(tableAlgorithm);
        algorithmModel.setTableFuncs(tableFuncs);
        return algorithmModel;
    }

    @Override
    public int modAlgorithmFuncsById(AlgorithmModel algorithmModel) {
        try {
            //算子名称不能重复
            TableAlgorithmCriteria tableAlgorithmCriteria=new TableAlgorithmCriteria();
            tableAlgorithmCriteria.createCriteria().andAlgorithmnameEqualTo(algorithmModel.getTableAlgorithm().getAlgorithmname())
                    .andIdNotEqualTo(algorithmModel.getTableAlgorithm().getId()).andAlgorithmgroupEqualTo(algorithmModel.getTableAlgorithm().getAlgorithmgroup());
            List<TableAlgorithm> tableAlgorithms=tableAlgorithmMapper.selectByExample(tableAlgorithmCriteria);
            if(tableAlgorithms.size()>0){//重名
                return 3;
            }
            boolean flag=cheakAlgorithmrole(algorithmModel.getTableAlgorithm().getId().toString());
            if(flag){
                return 2;
            }
            List<TableFunc> tableFuncs=algorithmModel.getTableFuncs();
            //查询以前所有的参数
            TableFuncCriteria tableFuncCriteria=new TableFuncCriteria();
            tableFuncCriteria.createCriteria().andAlgorithmidEqualTo(algorithmModel.getTableAlgorithm().getId());
            List<TableFunc> oldData=tableFuncMapper.selectByExample(tableFuncCriteria);
            List<TableFunc> addData=new ArrayList<>();
            List<TableFunc> delData=new ArrayList<>();
            List<TableFunc> updData=new ArrayList<>();
            if(oldData.size()>0){
                for(TableFunc func:tableFuncs){
                    boolean fg=false;
                    for(TableFunc old:oldData){
                        if(func.getId()==old.getId()){
                            updData.add(func);
                            fg=true;
                            break;
                        }
                    }
                    if(!fg){
                        addData.add(func);
                    }
                }
                for(TableFunc old:oldData){
                    boolean fg=false;
                    for(TableFunc upd:updData){
                        if(old.getId()==upd.getId()){
                            fg=true;
                            break;
                        }
                    }
                    if(!fg){
                        delData.add(old);
                    }
                }
                //删除
                delData.forEach(data->tableFuncMapper.deleteByPrimaryKey(data.getId()));
                //修改
                updData.forEach(data->tableFuncMapper.updateByPrimaryKeySelective(data));
                //新增
                addData.forEach(data->tableFuncMapper.insert(data));
            }else {
                tableFuncs.stream().forEach(fun->tableFuncMapper.insert(fun));
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            return 0;
        }
        return 1;
    }

    @Override
    public int modAlgorithmBaseInfoById(TableAlgorithm tableAlgorithm) {
        boolean flag=cheakAlgorithmrole(tableAlgorithm.getId().toString());
        if(flag){
            return 2;
        }
        TableAlgorithmCriteria tableAlgorithmCriteria=new TableAlgorithmCriteria();
        tableAlgorithmCriteria.createCriteria().andAlgorithmnameEqualTo(tableAlgorithm.getAlgorithmname())
                .andIdNotEqualTo(tableAlgorithm.getId()).andAlgorithmgroupEqualTo(tableAlgorithm.getAlgorithmgroup());
        List<TableAlgorithm> tableAlgorithms=tableAlgorithmMapper.selectByExample(tableAlgorithmCriteria);
        if(tableAlgorithms.size()>0){//重名
            return 3;
        }
        return updateByPrimaryKeySelective(tableAlgorithm);
    }

    /*
        查询算子是否已用于其他规则中
         */
    private boolean cheakAlgorithmrole(String algthId){
        TableOperatorinterfaceCriteria tableOperatorinterfaceCriteria=new TableOperatorinterfaceCriteria();
        tableOperatorinterfaceCriteria.createCriteria().andAlgorithmidEqualTo(Integer.parseInt(algthId));
        List<TableOperatorinterface> tableOperatorinterfaces=tableOperatorinterfaceMapper.selectByExample(tableOperatorinterfaceCriteria);
        if(tableOperatorinterfaces.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public int delAlgorithmById(String algthId) {
        if(algthId==null||"".equals(algthId)){
            logger.warn(algthId+" 删除算子信息的算子ID不能为空");
            return 0;
        }
        boolean flag=cheakAlgorithmrole(algthId);
        if(flag){
            logger.info("算子与其他算子存在关联关系，无法删除");
            return 2;
        }
        //删除参数信息
        TableFuncCriteria tableFuncCriteria=new TableFuncCriteria();
        tableFuncCriteria.createCriteria().andAlgorithmidEqualTo(Integer.parseInt(algthId));
        tableFuncMapper.deleteByExample(tableFuncCriteria);
        deleteByPrimaryKey(Integer.parseInt(algthId));
        return 1;
    }

    @Override
    public PageInfo<TableAlgorithm> pageFind(int pageNum, int pageSize, Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return null;
    }

    @Override
    public boolean updataStatus(String algthId,String status) {
        if(algthId==null||algthId.equals("0")){
            logger.warn("传入的ID无效");
            return false;
        }else {
            try {
                TableAlgorithm tableAlgorithm=new TableAlgorithm();
                tableAlgorithm.setId(Integer.parseInt(algthId));
                if(status.equals("发布")){
                    tableAlgorithm.setStatus("发布中");
                }else{
                    tableAlgorithm.setStatus("未发布");
                }
                return updateByPrimaryKeySelective(tableAlgorithm)==1;
            }catch (Exception e){
                logger.error(e.getMessage());
                return false;
            }
        }
    }
}
