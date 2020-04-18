package deepthinking.fgi.service;

import deepthinking.fgi.domain.TableAlgorithm;
import deepthinking.fgi.model.AlgorithmBaseInfo;
import deepthinking.fgi.model.AlgorithmModel;

import java.util.List;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/18 16:47
 */
public interface TableAlgorithmService extends BaseService<TableAlgorithm,Integer>{
    /**
     * 获取公共的和个人的所有算子,仅返回算子基本信息
     * @param groupName
     * @return
     */
    List<AlgorithmBaseInfo> getAllAlgorithm(String groupName);

    /**
     * 记录算子之间的逻辑关系（当算子有前序算子时，定义前序算子需要满足的逻辑条件，以执行后续算子）
     * @param tableAlgorithmrole
     * @return
     */
//    boolean saveAlgorithmLogicRelation(TableAlgorithmrole tableAlgorithmrole);

    /**
     * 添加算子信息(包括参数信息)
     * @param algorithmModel
     * @return
     */
    int addAlgorithm(AlgorithmModel algorithmModel);

    /**
     * 根据算子ID获取该算子相关的所有信息，包括参数以及参数关联算子信息
     * @param algthId
     * @return
     */
    AlgorithmModel getAlgorithmById(String algthId);

    /**
     * 修改算子参数信息
     * @param algorithmModel
     * @return
     */
    int modAlgorithmFuncsById(AlgorithmModel algorithmModel);

    /**
     * 修改算子基本信息
     * @param tableAlgorithm
     * @return
     */
    int modAlgorithmBaseInfoById(TableAlgorithm tableAlgorithm);

    /**
     * 删除算子
     * @param algthId
     * @return
     */
    int delAlgorithmById(String algthId);

    /**
     *
     * @param algthId
     * @return
     */
    boolean updataStatus(String algthId,String status);
}
