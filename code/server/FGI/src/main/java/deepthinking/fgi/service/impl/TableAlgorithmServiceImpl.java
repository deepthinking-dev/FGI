package deepthinking.fgi.service.impl;

import com.github.pagehelper.PageInfo;
import deepthinking.fgi.Enum.FuncValTypeEnum;
import deepthinking.fgi.dao.mapper.TableAlgorithmMapper;
import deepthinking.fgi.dao.mapper.TableAlgorithmroleMapper;
import deepthinking.fgi.dao.mapper.TableFuncMapper;
import deepthinking.fgi.domain.*;
import deepthinking.fgi.model.AlgorithmModel;
import deepthinking.fgi.service.TableAlgorithmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

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
    private TableAlgorithmroleMapper tableAlgorithmroleMapper;

    @Override
    public List<TableAlgorithm> getAllAlgorithm(String username) {
        //username 暂不使用
        TableAlgorithmCriteria tableAlgorithmCriteria=new TableAlgorithmCriteria();
        return tableAlgorithmMapper.selectByExample(tableAlgorithmCriteria);
    }

    @Override
    public boolean addAlgorithm(AlgorithmModel algorithmModel) {
        try {
            insert(algorithmModel.getTableAlgorithm());
            //获取算子ID
            int id=getAlgorithmId(algorithmModel.getTableAlgorithm());
            List<TableFunc> tableFuncs=algorithmModel.getTableFuncs();
            if(tableFuncs.size()>0){
                tableFuncs.stream().forEach(funcs->{
                    funcs.setModuleid(id);//设置算子ID
                    tableFuncMapper.insert(funcs);
                });
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }
    private int getAlgorithmId(TableAlgorithm tableAlgorithm){
        TableAlgorithmCriteria tableAlgorithmCriteria=new TableAlgorithmCriteria();
        tableAlgorithmCriteria.createCriteria().andModuleidEqualTo(tableAlgorithm.getModuleid()).andAlgorithmnameEqualTo(tableAlgorithm.getAlgorithmname())
                .andAlgorithmfunEqualTo(tableAlgorithm.getAlgorithmfun()).andAlgorithmtypeEqualTo(tableAlgorithm.getAlgorithmtype()).andIspublicEqualTo(tableAlgorithm.getIspublic())
                .andDesEqualTo(tableAlgorithm.getDes()).andRemarkEqualTo(tableAlgorithm.getRemark());
        int id=tableAlgorithmMapper.selectByExample(tableAlgorithmCriteria).get(0).getId();
        return id;
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
        tableFuncCriteria.createCriteria().andModuleidEqualTo(Integer.parseInt(algthId));
        List<TableFunc> tableFuncs=tableFuncMapper.selectByExample(tableFuncCriteria);
        algorithmModel.setTableAlgorithm(tableAlgorithm);
        algorithmModel.setTableFuncs(tableFuncs);
        return algorithmModel;
    }

    @Override
    public boolean modAlgorithmById(AlgorithmModel algorithmModel) {
        try {
            if(null!=algorithmModel.getTableFuncs()&&!"".equals(algorithmModel.getTableFuncs())){
                //获取以前的算子信息，判断公式是否改变
                TableAlgorithm old_tableAlgorithm=selectByPrimaryKey(algorithmModel.getTableAlgorithm().getId());
                String old_algorithmFun=old_tableAlgorithm.getAlgorithmfun();
                List<TableFunc> tableFuncs=algorithmModel.getTableFuncs();
                if(tableFuncs.size()>0){
                    String new_algorithmFun=algorithmModel.getTableAlgorithm().getAlgorithmfun();
                    if(new_algorithmFun.equals(old_algorithmFun)){//一样
                        tableFuncs.stream().forEach(fun->tableFuncMapper.updateByPrimaryKeySelective(fun));
                    }else{
                        TableFuncCriteria tableFuncCriteria=new TableFuncCriteria();
                        tableFuncCriteria.createCriteria().andModuleidEqualTo(algorithmModel.getTableAlgorithm().getId());
                        tableFuncMapper.deleteByExample(tableFuncCriteria);
                        tableFuncs.stream().forEach(fun->tableFuncMapper.insert(fun));
                    }
                }
            }
            updateByPrimaryKey(algorithmModel.getTableAlgorithm());
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public int delAlgorithmById(String algthId) {
        if(algthId==null||"".equals(algthId)){
            logger.warn(algthId+" 删除算子信息的算子ID不能为空");
            return 0;
        }
        // 判断是否有关联算子  1、判断关系表  2、判断关联表
        TableFuncCriteria tableFuncCriteria=new TableFuncCriteria();
        tableFuncCriteria.clear();
        tableFuncCriteria.createCriteria().andVartypeEqualTo(FuncValTypeEnum.getTypeName(3)).andValvalueEqualTo(algthId);
        List<TableFunc> tableFuncs=tableFuncMapper.selectByExample(tableFuncCriteria);
        if(tableFuncs.size()>0){
            logger.info("算子作为参数被其他算子关联，无法删除");
            return 2;
        }
        TableAlgorithmroleCriteria tableAlgorithmroleCriteria=new TableAlgorithmroleCriteria();
        tableAlgorithmroleCriteria.or().andAlgorithmidEqualTo(Integer.parseInt(algthId));
        tableAlgorithmroleCriteria.or().andPrealgorithmidEqualTo(Integer.parseInt(algthId));
        List<TableAlgorithmrole> tableAlgorithmroles=tableAlgorithmroleMapper.selectByExample(tableAlgorithmroleCriteria);
        if(tableAlgorithmroles.size()>0){
            logger.info("算子与其他算子存在关联关系，无法删除");
            return 3;
        }
        //删除参数信息
        tableFuncCriteria.clear();
        tableFuncCriteria.createCriteria().andModuleidEqualTo(Integer.parseInt(algthId));
        tableFuncMapper.deleteByExample(tableFuncCriteria);
        deleteByPrimaryKey(Integer.parseInt(algthId));
        return 1;
    }

    @Override
    public PageInfo<TableAlgorithm> pageFind(int pageNum, int pageSize, Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return null;
    }
}
