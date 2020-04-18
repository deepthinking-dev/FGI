package deepthinking.fgi.service.impl;

import com.github.pagehelper.PageInfo;
import deepthinking.fgi.Enum.GroupType;
import deepthinking.fgi.dao.mapper.TableAlgorithmMapper;
import deepthinking.fgi.dao.mapper.TableGroupdataMapper;
import deepthinking.fgi.dao.mapper.TableModuleMapper;
import deepthinking.fgi.dao.mapper.TableRoleMapper;
import deepthinking.fgi.domain.*;
import deepthinking.fgi.service.TableGroupdataService;
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
 * @data 2020/4/16 16:59
 */
@Service("tableGroupdataService")
@Transactional
public class TableGroupdataServiceImpl extends BaseServiceImpl<TableGroupdata,Integer> implements TableGroupdataService {

    private static Logger logger = LoggerFactory.getLogger(TableGroupdataServiceImpl.class);

    @Resource
    private TableGroupdataMapper groupdataMapper;
    @Resource
    private TableModuleMapper tableModuleMapper;
    @Resource
    private TableAlgorithmMapper tableAlgorithmMapper;
    @Resource
    private TableRoleMapper tableRoleMapper;

    @Override
    public List<TableGroupdata> findAllGroupMessagesByType(int type) {
        TableGroupdataCriteria tableGroupdataCriteria=new TableGroupdataCriteria();
        tableGroupdataCriteria.createCriteria().andGrouptypeEqualTo(type);
        return groupdataMapper.selectByExample(tableGroupdataCriteria);
    }

    @Override
    public TableGroupdata updtaTableGroupMessage(TableGroupdata tableGroupdata) {
        try {
            TableGroupdata oldGroupData=selectByPrimaryKey(tableGroupdata.getId());
            groupdataMapper.updateByPrimaryKeySelective(tableGroupdata);
            //将该组下所有的都改为新组名
            String oldName=oldGroupData.getGroupname();
            int type=tableGroupdata.getGrouptype();
            String name=tableGroupdata.getGroupname();
            int id=tableGroupdata.getId();
            switch (type){
                case 1://模型
                    TableModuleCriteria tableModuleCriteria=new TableModuleCriteria();
                    tableModuleCriteria.createCriteria().andModulegroupEqualTo(oldName);
                    TableModule tableModule=new TableModule();
                    tableModule.setModulegroup(name);
                    tableModuleMapper.updateByExampleSelective(tableModule,tableModuleCriteria);
                    break;
                case 2://算法
                    TableAlgorithmCriteria tableAlgorithmCriteria=new TableAlgorithmCriteria();
                    tableAlgorithmCriteria.createCriteria().andAlgorithmgroupEqualTo(oldName);
                    TableAlgorithm tableAlgorithm=new TableAlgorithm();
                    tableAlgorithm.setAlgorithmgroup(name);
                    tableAlgorithmMapper.updateByExampleSelective(tableAlgorithm,tableAlgorithmCriteria);
                    break;
                case 3://规则
                    TableRoleCriteria tableRoleCriteria=new TableRoleCriteria();
                    tableRoleCriteria.createCriteria().andRolegroupEqualTo(oldName);
                    TableRole tableRole=new TableRole();
                    tableRole.setRolegroup(name);
                    tableRoleMapper.updateByExampleSelective(tableRole,tableRoleCriteria);
                    break;
            }
            return tableGroupdata;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public int deleteTableGroupMessage(int id) {
        //删除的时候判断分组下是否有数据
        TableGroupdata tableGroupdata=selectByPrimaryKey(id);
        if(tableGroupdata!=null){
            int type=tableGroupdata.getGrouptype();
            String name=tableGroupdata.getGroupname();
            boolean flag=true;
            switch (type){
                case 1://模型
                    TableModuleCriteria tableModuleCriteria=new TableModuleCriteria();
                    tableModuleCriteria.createCriteria().andModulegroupEqualTo(name);
                    List<TableModule> data=tableModuleMapper.selectByExample(tableModuleCriteria);
                    if(data.size()>0){
                        flag=false;
                    }
                    break;
                case 2://算法
                    TableAlgorithmCriteria tableAlgorithmCriteria=new TableAlgorithmCriteria();
                    tableAlgorithmCriteria.createCriteria().andAlgorithmgroupEqualTo(name);
                    List<TableAlgorithm> dataA=tableAlgorithmMapper.selectByExample(tableAlgorithmCriteria);
                    if(dataA.size()>0){
                        flag=false;
                    }
                    break;
                case 3://规则
                    TableRoleCriteria tableRoleCriteria=new TableRoleCriteria();
                    tableRoleCriteria.createCriteria().andRolegroupEqualTo(name);
                    List<TableRole> dataR=tableRoleMapper.selectByExample(tableRoleCriteria);
                    if(dataR.size()>0){
                        flag=false;
                    }
                    break;
            }
            if(!flag){
                return 2;
            }else{
                return groupdataMapper.deleteByPrimaryKey(id);
            }
        }
        return 0;
    }

    @Override
    public int saveTableGroupMessage(TableGroupdata tableGroupdata) {
        try {
            String name=tableGroupdata.getGroupname();
            int type=tableGroupdata.getGrouptype();
            TableGroupdataCriteria tableGroupdataCriteria=new TableGroupdataCriteria();
            tableGroupdataCriteria.createCriteria().andGrouptypeEqualTo(type).andGroupnameEqualTo(name);
            List<TableGroupdata> list=groupdataMapper.selectByExample(tableGroupdataCriteria);
            if(list.size()>0){
                return 2;
            }else{
                return insert(tableGroupdata);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public PageInfo<TableGroupdata> pageFind(int pageNum, int pageSize, Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return null;
    }
}
