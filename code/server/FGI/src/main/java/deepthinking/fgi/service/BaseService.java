/********************************************
 * Service层基类
 *
 * @author zwq
 * @create 2018-05-31 22:23
 *********************************************/

package deepthinking.fgi.service;


import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.github.pagehelper.PageInfo;

public interface BaseService<T,PK> {

     int insert(T record);
     int insertSelective(T record);
     T selectByPrimaryKey(PK id);
     int updateByPrimaryKey(T record);
     int updateByPrimaryKeySelective(T record);
     int deleteByPrimaryKey(PK id);
     PageInfo<T> pageFind(int pageNum, int pageSize, Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
     <M> List<M> selectAllModelList(String statementKey,Object parameter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
}

