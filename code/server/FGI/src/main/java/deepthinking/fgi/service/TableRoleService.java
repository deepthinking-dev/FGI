package deepthinking.fgi.service;

import deepthinking.fgi.domain.TableRole;
import deepthinking.fgi.model.AlgorithmRuleDataModel;
import deepthinking.fgi.model.AlgorithmRuleSaveDataModel;

import java.io.File;
import java.util.List;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/18 16:47
 */
public interface TableRoleService extends BaseService<TableRole,Integer> {

    /**
     * 文件导入
     * @param filePath 文件地址
     * @Author 王若山
     * @return
     */
    List<TableRole> leadByTxt(File file);

    /**
     * 拼装算法导出实体
     * @param id
     * @author 王若山
     * @return
     */
    TableRole GetTableExportData(Integer id);

    /**
     * 根据用户获取所有算法规则 username暂时不用
     * @param username
     * @return
     */
    List<TableRole> GetAllAlgorithmRule(String username );

    /**
     * 根据规则编号获取算法规则(规则具体信息)
     * @param Id
     */
    AlgorithmRuleSaveDataModel getAlgorithmRuleById(String Id);

    /**
     * 新增算法规则（一起新增）
     * @return
     */
    boolean saveAlgorithmRule(AlgorithmRuleSaveDataModel algorithmRuleSaveDataModel);

    /**
     * 新增算法规则（一步一步新增）
     * @param algorithmRuleDataModel
     * @return
     */
    boolean saveAlgorithmRuleOne(AlgorithmRuleDataModel algorithmRuleDataModel);

    /**
     * 只新增规则基本信息
     * @param tableRole
     * @return
     */
    boolean saveAlgorithmRuleBase(TableRole tableRole);

    /**
     * 修改算法规则
     * @return
     */
    boolean modAlgorithmRule(AlgorithmRuleDataModel algorithmRuleDataModel);

    /**
     * 只修改规则基本信息
     * @param tableRole
     * @return
     */
    boolean modAlgorithmRuleBase(TableRole tableRole);

    /**
     * 删除指定规则编号的算法规则
     * @param Id
     * @return
     */
    boolean delAlgorithmRuleById(String Id);

    /**
     * 根据算法关系ID删除一个关系
     * @param algorithmroleId
     * @return
     */
    boolean delTableAlgorithmrole(String algorithmroleId);
}
