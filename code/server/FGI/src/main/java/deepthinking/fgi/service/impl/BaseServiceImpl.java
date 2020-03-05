/********************************************
 * Service层基类的实现
 *
 * @author zwq
 * @create 2018-05-31 22:30
 *********************************************/

package deepthinking.fgi.service.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import deepthinking.fgi.dao.BaseDao;
import deepthinking.fgi.service.BaseService;
import deepthinking.fgi.util.GenericsUtils;


@Service
public abstract class BaseServiceImpl<T,PK extends Serializable> implements BaseService<T,PK> {

    @SuppressWarnings("unchecked")
	public String getNameSpace() {
        @SuppressWarnings("rawtypes")
		Class<T> clazz = (Class) GenericsUtils.getSuperClassGenricType(this.getClass());
        return clazz.getName();
    }
	
    @Autowired
    private BaseDao<T,PK> baseDao;

    public BaseDao<T,PK> getBaseDao() {
        return baseDao;
    }
    public void setBaseDao(BaseDao<T,PK> baseDao) {
        this.baseDao = baseDao;
    }

    public int insert(T entity) {
    	baseDao.setNameSpace(getNameSpace());
        return baseDao.insert(entity);
    }

    public int insertSelective(T record) {
    	baseDao.setNameSpace(getNameSpace());
        return baseDao.insertSelective(record);
    }

    public T selectByPrimaryKey(PK id) {
    	baseDao.setNameSpace(getNameSpace());
        return baseDao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKey(T record) {
    	baseDao.setNameSpace(getNameSpace());
        return baseDao.updateByPrimaryKey(record);
    }

    public int updateByPrimaryKeySelective(T record) {
    	baseDao.setNameSpace(getNameSpace());
        return baseDao.updateByPrimaryKeySelective(record);
    }

    public int deleteByPrimaryKey(PK id) {
    	baseDao.setNameSpace(getNameSpace());
        return baseDao.deleteByPrimaryKey(id);
    }

    public PageInfo<T> pageFind(String statementKey, int pageNum, int pageSize, Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
        return baseDao.pageFind(statementKey,pageNum,pageSize,parameter);
    }
    
    /**************************************
     * 根据输入Object参数，查询数据库，返回List<M>
     * @param statementKey  对应Mapper的接口名称
     * @param parameter     查询条件
     * @return 查询到的数据结构 List<M>
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public <M> List<M> selectAllModelList(String statementKey,Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
        return baseDao.selectAllModel(statementKey,parameter);
    }
    
    public <M> PageInfo<M> pageFindModel(String statementKey, int pageNum, int pageSize, Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
        return baseDao.pageFindModel2(statementKey,pageNum,pageSize,parameter);
    }

}


