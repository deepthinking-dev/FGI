package deepthinking.fgi.service;

import deepthinking.fgi.domain.TableModule;

import java.util.List;
import java.util.Map;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/18 16:47
 */
public interface TableModuleService extends BaseService<TableModule,Integer> {
    /**
     * 获取所有的模板信息列表
     * @return
     */
    List<TableModule> GetAllModule();

    /**
     * 获取所有模板分组
     * @return
     */
    List<String> GetModuleGroup ();

    /**
     * 获取指定模板分组下的所有模板
     * @param moduleGroupName
     * @return
     */
    List<TableModule> GetModuleByGroupName(String moduleGroupName);

    /**
     * 获取指定模板的详细信息
     * @param moduleId
     * @return
     */
    TableModule getModuleById(String moduleId);

    /**
     * 新建一个全新的模板
     * @param module
     * @return
     */
    boolean addModule (TableModule module);

    /**
     * 对指定的一个模板进行修改保存
     * @param module
     * @return
     */
    boolean modModuleById (TableModule module);

    /**
     * 对指定的一个模板进行删除操作
     * @param moduleId
     * @return
     */
    boolean delModuleById (String moduleId);

    /**
     * 获取当前库的所有表 表名
     * @return
     */
    List<String> findAllTableFromDB();

    /**
     * 根据表名获取该表的字段信息
     * @param tableName
     * @return
     */
    List<Map<String,Object>> findAllFiledByTableName(String tableName);

    /**
     * 根据模型名称查询确定模型，用于赋值操作
     * @param name
     * @return
     */
    TableModule findTableModuleByName(String name);

    /**
     * 修改模型的状态信息
     * @param modelId
     * @param status
     * @return
     */
    public boolean updataStatus(String modelId,String status);
}
