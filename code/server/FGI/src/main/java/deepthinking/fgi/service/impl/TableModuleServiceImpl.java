package deepthinking.fgi.service.impl;

import com.github.pagehelper.PageInfo;
import deepthinking.fgi.dao.mapper.TableModuleMapper;
import deepthinking.fgi.dao.mapper.TableModulefieldMapper;
import deepthinking.fgi.domain.TableModule;
import deepthinking.fgi.domain.TableModuleCriteria;
import deepthinking.fgi.domain.TableModulefield;
import deepthinking.fgi.domain.TableModulefieldCriteria;
import deepthinking.fgi.service.TableModuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/18 16:56
 */
@Service("tableModuleService")
@Transactional
public class TableModuleServiceImpl extends BaseServiceImpl<TableModule,Integer> implements TableModuleService {

    private static Logger logger = LoggerFactory.getLogger(TableModuleServiceImpl.class);

    @Resource
    private TableModuleMapper tableModuleMapper;
    @Resource
    private TableModulefieldMapper tableModulefieldMapper;

    @Override
    public List<TableModule> GetAllModule() {
        TableModuleCriteria tableModuleCriteria=new TableModuleCriteria();
        return tableModuleMapper.selectByExample(tableModuleCriteria);
    }

    @Override
    public List<String> GetModuleGroup() {
        return tableModuleMapper.GetModuleGroup();
    }

    @Override
    public List<TableModule> GetModuleByGroupName(String moduleGroupName) {
        TableModuleCriteria tableModuleCriteria=new TableModuleCriteria();
        tableModuleCriteria.createCriteria().andModulegroupEqualTo(moduleGroupName);
        return tableModuleMapper.selectByExample(tableModuleCriteria);
    }

    @Override
    public TableModule getModuleById(String moduleId) {
        if(moduleId==null||"".equals(moduleId)){
            logger.warn("模型ID不能为空");
            return null;
        }
        TableModule module=selectByPrimaryKey(Integer.parseInt(moduleId));
        TableModulefieldCriteria tableModulefieldCriteria=new TableModulefieldCriteria();
        tableModulefieldCriteria.createCriteria().andModuleidEqualTo(Integer.parseInt(moduleId));
        List<TableModulefield> modulefields=tableModulefieldMapper.selectByExample(tableModulefieldCriteria);
        module.setModulefields(modulefields);
        return module;
    }

    @Override
    public boolean addModule(TableModule module) {
        module.setSqlurl("fgi");
        try {
            //添加模型
            insert(module);
            //获取模型ID
            int id=module.getId();
            //获取关联字段
            List<TableModulefield> list=module.getModulefields();
            if(list!=null&&list.size()>0){
                list.stream().forEach(field->{
                    field.setModuleid(id);
                    tableModulefieldMapper.insert(field);
                });
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean modModuleById(TableModule module) {
        //修改模型
        int i=updateByPrimaryKeySelective(module);
        if(i==1){
            //获取目前传入的模型关联字段
            List<TableModulefield> list=module.getModulefields();
            if(list!=null&&list.size()>0){
                //获取以前表中的字段
                TableModulefieldCriteria tableModulefieldCriteria=new TableModulefieldCriteria();
                tableModulefieldCriteria.createCriteria().andModuleidEqualTo(module.getId());
                List<TableModulefield> old_data=tableModulefieldMapper.selectByExample(tableModulefieldCriteria);
                //新增字段集合
                List<TableModulefield> add_data=new ArrayList<>();
                //删除字段集合
                List<TableModulefield> del_data=new ArrayList<>();
                if(old_data.size()>0){
                    Iterator<TableModulefield> iterator=list.iterator();
                    while (iterator.hasNext()){
                        TableModulefield field=iterator.next();
                        if(field.getId()==null||field.getId()==0){//没有ID，新增
                            add_data.add(field);
                            iterator.remove();
                        }
                    }
                    for(TableModulefield old_field:old_data){
                        boolean falg=true;
                        while (iterator.hasNext()){
                            TableModulefield field=iterator.next();
                            if(old_field.getId()==field.getId()){
                                falg=false;
                                continue;
                            }
                        }
                        if(falg){
                            del_data.add(old_field);
                        }
                    }
                }else{
                    add_data=list;
                }
                if(add_data.size()>0){//新增
                    add_data.stream().forEach(field->tableModulefieldMapper.insert(field));
                }
                if(del_data.size()>0){//删除
                    del_data.stream().forEach(field->tableModulefieldMapper.deleteByPrimaryKey(field.getId()));
                }
            }
        }else{
            logger.warn("修改模型 "+module.toString()+" 失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean delModuleById(String moduleId) {
        if(moduleId==null||"".equals(moduleId)){
            logger.warn("模型ID不能为空");
            return false;
        }
        try {
            int id=Integer.parseInt(moduleId);
            TableModulefieldCriteria tableModulefieldCriteria=new TableModulefieldCriteria();
            tableModulefieldCriteria.createCriteria().andModuleidEqualTo(id);
            tableModulefieldMapper.deleteByExample(tableModulefieldCriteria);
            deleteByPrimaryKey(id);
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public PageInfo<TableModule> pageFind(int pageNum, int pageSize, Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return null;
    }

    @Override
    public List<String> findAllTableFromDB() {
        return tableModuleMapper.findAllTableFromDB();
    }

    @Override
    public List<Map<String,Object>> findAllFiledByTableName(String tableName) {
        return tableModuleMapper.findAllFiledByTableName(tableName);
    }
}
