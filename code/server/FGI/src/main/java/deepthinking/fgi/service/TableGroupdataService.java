package deepthinking.fgi.service;

import deepthinking.fgi.domain.TableGroupdata;

import java.util.List;

/**
 * 分组信息的接口
 */
public interface TableGroupdataService extends BaseService<TableGroupdata,Integer>  {
    /**
     * 根据类型查询该类型下的所有分组信息
     * @return
     */
    List<TableGroupdata> findAllGroupMessagesByType(int type);

    /**
     * 修改分组信息，返回修改后的信息
     * @param tableGroupdata
     * @return
     */
    TableGroupdata updtaTableGroupMessage(TableGroupdata tableGroupdata);

    /**
     * 根据ID删除分组消息
     * @param id
     * @return
     */
    int deleteTableGroupMessage(int id);

    /**
     * 新增分组信息
     * @param tableGroupdata
     * @return
     */
    int saveTableGroupMessage(TableGroupdata tableGroupdata);

    /**
     * 根据类型和名称查询分组
     * @param name
     * @param type
     * @return
     */
    List<TableGroupdata> findTableGroupdataByTypeAndName(String name,int type);
}
