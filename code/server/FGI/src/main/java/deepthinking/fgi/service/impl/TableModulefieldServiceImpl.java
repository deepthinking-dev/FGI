package deepthinking.fgi.service.impl;

import com.github.pagehelper.PageInfo;
import deepthinking.fgi.dao.mapper.TableModulefieldMapper;
import deepthinking.fgi.domain.TableModulefield;
import deepthinking.fgi.domain.TableModulefieldCriteria;
import deepthinking.fgi.service.TableModulefieldService;
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
 * @data 2020/2/18 16:55
 */
@Service("tableModulefieldService")
@Transactional
public class TableModulefieldServiceImpl extends BaseServiceImpl<TableModulefield,Integer> implements TableModulefieldService {
    private static Logger logger = LoggerFactory.getLogger(TableModuleServiceImpl.class);

    @Resource
    private TableModulefieldMapper tableModulefieldMapper;

    @Override
    public boolean addModuleColumn(TableModulefield column) {
        return insert(column)>=1?true:false;
    }

    @Override
    public boolean removeModuleColumn(String columnId) {
        if(columnId==null||columnId.equals("")){
            logger.warn("传入columnId不能为空");
            return  false;
        }
        return deleteByPrimaryKey(Integer.parseInt(columnId))==1?true:false;
    }

    @Override
    public List<TableModulefield> getModuleColumns(String moduleId) {
        if(moduleId==null||moduleId.equals("")){
            logger.warn("传入moduleId不能为空");
            return  null;
        }
        TableModulefieldCriteria tableModulefieldCriteria=new TableModulefieldCriteria();
        tableModulefieldCriteria.createCriteria().andModuleidEqualTo(Integer.parseInt(moduleId));
        return tableModulefieldMapper.selectByExample(tableModulefieldCriteria);
    }

    @Override
    public PageInfo<TableModulefield> pageFind(int pageNum, int pageSize, Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return null;
    }

}
