package deepthinking.fgi.service.impl;

import com.github.pagehelper.PageInfo;
import deepthinking.fgi.Enum.InOrOutType;
import deepthinking.fgi.dao.mapper.TableAlgorithmMapper;
import deepthinking.fgi.dao.mapper.TableFuncMapper;
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
    private TableAlgorithmroleMapper tableAlgorithmroleMapper;

    @Override
    public List<AlgorithmBaseInfo> getAllAlgorithm(String username) {
        //username 暂不使用
        List<AlgorithmBaseInfo> result=new ArrayList<>();
        List<Map<String,Object>> tableAlgorithms=tableAlgorithmMapper.selectBaseInfo();
        if(tableAlgorithms.size()>0){
            tableAlgorithms.stream().forEach(data->{
                AlgorithmBaseInfo algorithmBaseInfo=new AlgorithmBaseInfo();
                algorithmBaseInfo.setTableAlgorithm(data);
                int alfgorithmId=Integer.parseInt(data.get("ID").toString());
                List<Map<String,Object>> funcs=tableFuncMapper.selectBaseInfo(alfgorithmId);
                algorithmBaseInfo.setTableFuncs(funcs);
                //查询每个算法的输入输出参数个数
                TableFuncCriteria tableFuncCriteria=new TableFuncCriteria();
                tableFuncCriteria.createCriteria().andAlgorithmidEqualTo(alfgorithmId);
                tableFuncCriteria.createCriteria().andAlgorithmidEqualTo(alfgorithmId).andInoroutEqualTo(InOrOutType.in.getType());
                int count_in= (int) tableFuncMapper.countByExample(tableFuncCriteria);
                algorithmBaseInfo.setInNum(count_in);
                tableFuncCriteria.clear();
                tableFuncCriteria.createCriteria().andAlgorithmidEqualTo(alfgorithmId).andInoroutEqualTo(InOrOutType.out.getType());
                int count_out= (int) tableFuncMapper.countByExample(tableFuncCriteria);
                algorithmBaseInfo.setInNum(count_out);
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
                    .andIdNotEqualTo(algorithmModel.getTableAlgorithm().getId());
            List<TableAlgorithm> tableAlgorithms=tableAlgorithmMapper.selectByExample(tableAlgorithmCriteria);
            if(tableAlgorithms.size()>0){//重名
                return 2;
            }
            insertSelective(algorithmModel.getTableAlgorithm());
            //获取算子ID
            int id=algorithmModel.getTableAlgorithm().getId();
            List<TableFunc> tableFuncs=algorithmModel.getTableFuncs();
            if(tableFuncs.size()>0){
                tableFuncs.stream().forEach(funcs->{
                    funcs.setAlgorithmid(id);//设置算子ID
                    tableFuncMapper.insert(funcs);
                });
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
            boolean flag=cheakAlgorithmrole(algorithmModel.getTableAlgorithm().getId().toString());
            if(flag){
                return 2;
            }
            List<TableFunc> tableFuncs=algorithmModel.getTableFuncs();
            tableFuncs.stream().forEach(fun->tableFuncMapper.updateByPrimaryKeySelective(fun));
        }catch (Exception e){
            logger.error(e.getMessage());
            return 0;
        }
        return 1;
    }

    @Override
    public int modAlgorithmBaseInfoById(TableAlgorithm tableAlgorithm) {
        return updateByPrimaryKeySelective(tableAlgorithm);
    }

    /*
        查询算子是否已用于其他规则中
         */
    private boolean cheakAlgorithmrole(String algthId){
        TableAlgorithmroleCriteria tableAlgorithmroleCriteria=new TableAlgorithmroleCriteria();
        tableAlgorithmroleCriteria.or().andAlgorithmidEqualTo(Integer.parseInt(algthId));
        tableAlgorithmroleCriteria.or().andPrealgorithmidEqualTo(Integer.parseInt(algthId));
        List<TableAlgorithmrole> tableAlgorithmroles=tableAlgorithmroleMapper.selectByExample(tableAlgorithmroleCriteria);
        if(tableAlgorithmroles.size()>0){
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
}
