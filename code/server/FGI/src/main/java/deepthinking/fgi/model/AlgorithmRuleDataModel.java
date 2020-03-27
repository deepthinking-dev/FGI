package deepthinking.fgi.model;

import deepthinking.fgi.domain.TableAlgorithmcondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/24 12:25
 */
@ApiModel(value = "算法规则中一条连线代表的数据模型")
public class AlgorithmRuleDataModel {
    @ApiModelProperty(value = "ID")
    private Integer id;
    @ApiModelProperty(value = "规则ID")
    private Integer roleId;
    @ApiModelProperty(value = "算子ID")
    private Integer algorithmid;
    @ApiModelProperty(value = "参数ID")
    private Integer FuncID;
//    @ApiModelProperty(value = "算子信息")
//    private AlgorithmModel algorithmModel;
    @ApiModelProperty(value = "前序算子ID")
    private Integer prealgorithmid;
    @ApiModelProperty(value = "前序算子参数ID")
    private Integer PreFuncID;
//    @ApiModelProperty(value = "前序算子信息")
//    private AlgorithmModel preaAlgorithmModel;
    @ApiModelProperty(value = "算法参数动作集合")
    private List<TableAlgorithmcondition> algorithmconditions;
    @ApiModelProperty(value = "描述")
    private String des;
    @ApiModelProperty(value = "备注")
    private String remark;

    public AlgorithmRuleDataModel() {
    }

    public AlgorithmRuleDataModel(Integer id, Integer roleId, Integer algorithmid, Integer funcID,
                                  Integer prealgorithmid, Integer preFuncID, List<TableAlgorithmcondition> algorithmconditions, String des, String remark) {
        this.id = id;
        this.roleId = roleId;
        this.algorithmid = algorithmid;
        FuncID = funcID;
        this.prealgorithmid = prealgorithmid;
        PreFuncID = preFuncID;
        this.algorithmconditions = algorithmconditions;
        this.des = des;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getAlgorithmid() {
        return algorithmid;
    }

    public void setAlgorithmid(Integer algorithmid) {
        this.algorithmid = algorithmid;
    }

    public Integer getFuncID() {
        return FuncID;
    }

    public void setFuncID(Integer funcID) {
        FuncID = funcID;
    }

    public Integer getPrealgorithmid() {
        return prealgorithmid;
    }

    public void setPrealgorithmid(Integer prealgorithmid) {
        this.prealgorithmid = prealgorithmid;
    }

    public Integer getPreFuncID() {
        return PreFuncID;
    }

    public void setPreFuncID(Integer preFuncID) {
        PreFuncID = preFuncID;
    }

    public List<TableAlgorithmcondition> getAlgorithmconditions() {
        return algorithmconditions;
    }

    public void setAlgorithmconditions(List<TableAlgorithmcondition> algorithmconditions) {
        this.algorithmconditions = algorithmconditions;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
