package deepthinking.fgi.service;

import deepthinking.fgi.domain.TableAlgorithmcondition;
import deepthinking.fgi.domain.TableRole;
import deepthinking.fgi.model.InterfaceRoleDataModel;
import deepthinking.fgi.model.AlgorithmRuleSaveDataModel;
import deepthinking.fgi.model.OperatorInterfaceDataModel;

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
    List<TableRole> leadByTxt(String filePath);

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
     * 新增接口信息
     * @param algorithmRuleSaveDataModel
     * @return
     */
    AlgorithmRuleSaveDataModel saveOperatorInterfaceData(AlgorithmRuleSaveDataModel algorithmRuleSaveDataModel);

    /**
     * 新增算法规则--连线信息
     * @return
     */
    AlgorithmRuleSaveDataModel saveAlgorithmRule(AlgorithmRuleSaveDataModel algorithmRuleSaveDataModel);

    /**
     * 修改接口信息
     * @param operatorInterfaceDataModel
     * @return
     */

    OperatorInterfaceDataModel modInterfaceRole(OperatorInterfaceDataModel operatorInterfaceDataModel);

    /**
     * 修改算法规则某条连线的信息
     * @return
     */
    boolean modAlgorithmRule(InterfaceRoleDataModel interfaceRoleDataModel);

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
     * 根据接口ID删除一个接口，连同该接口相关的线一起删除
     * @param operatorinterfaceId
     * @return
     */
    boolean delTableOperatorinterface(String operatorinterfaceId);

    /**
     * 根据算法接口关系Id删除一条线
     * @param interfaceRoueId
     * @return
     */
    boolean delOneInterfaceRole(String interfaceRoueId);

    /**
     * 保存最新的规则下算子的坐标
     * @param coordinate
     * @return
     */
    boolean saveNewCoordinate(String coordinate,int roleId);

    /**
     * 修改某个参数的动作
     * @return
     */
    boolean saveFunAction(List<TableAlgorithmcondition> algorithmconditions);
}
