package deepthinking.fgi.service;

import deepthinking.fgi.domain.TableModulefield;

import java.util.List;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/18 16:46
 */
public interface TableModulefieldService extends BaseService<TableModulefield,Integer> {
    /**
     * 对指定的一个模板添加一个字段信息
     * @param column
     * @return
     */
    boolean addModuleColumn(TableModulefield column);

    /**
     * 对指定的一个模板移除指定字段信息
     * @param columnId
     * @return
     */
    boolean removeModuleColumn(String columnId);

    /**
     * 获取指定模板包含的字段信息列表
     * @param moduleId
     * @return
     */
    List<TableModulefield> getModuleColumns(String moduleId);



}
