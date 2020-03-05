package deepthinking.fgi.model;

import deepthinking.fgi.domain.TableAlgorithmcondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/24 12:25
 */
@ApiModel(value = "算法规则总模型,包含算子相关信息交互数据模型，算子运行条件")
public class AlgorithmRuleDataModel {
    @ApiModelProperty(value = "ID")
    private Integer id;
    @ApiModelProperty(value = "规则ID")
    private Integer roleId;
    @ApiModelProperty(value = "算子ID")
    private Integer algorithmid;
    @ApiModelProperty(value = "算子信息")
    private AlgorithmModel algorithmModel;
    @ApiModelProperty(value = "前序算子ID")
    private Integer prealgorithmid;
    @ApiModelProperty(value = "前序算子信息")
    private AlgorithmModel preaAlgorithmModel;
    @ApiModelProperty(value = "算子运行条件")
    private TableAlgorithmcondition tableAlgorithmcondition;
    @ApiModelProperty(value = "描述")
    private String des;
    @ApiModelProperty(value = "备注")
    private String remark;

    public AlgorithmRuleDataModel() {
    }

    public AlgorithmRuleDataModel(Integer id, Integer roleId, Integer algorithmid, AlgorithmModel algorithmModel,
                                  Integer prealgorithmid, AlgorithmModel preaAlgorithmModel, TableAlgorithmcondition tableAlgorithmcondition, String des, String remark) {
        this.id = id;
        this.roleId = roleId;
        this.algorithmid = algorithmid;
        this.algorithmModel = algorithmModel;
        this.prealgorithmid = prealgorithmid;
        this.preaAlgorithmModel = preaAlgorithmModel;
        this.tableAlgorithmcondition = tableAlgorithmcondition;
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

    public AlgorithmModel getAlgorithmModel() {
        return algorithmModel;
    }

    public void setAlgorithmModel(AlgorithmModel algorithmModel) {
        this.algorithmModel = algorithmModel;
    }

    public AlgorithmModel getPreaAlgorithmModel() {
        return preaAlgorithmModel;
    }

    public void setPreaAlgorithmModel(AlgorithmModel preaAlgorithmModel) {
        this.preaAlgorithmModel = preaAlgorithmModel;
    }

    public TableAlgorithmcondition getTableAlgorithmcondition() {
        return tableAlgorithmcondition;
    }

    public void setTableAlgorithmcondition(TableAlgorithmcondition tableAlgorithmcondition) {
        this.tableAlgorithmcondition = tableAlgorithmcondition;
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

    public Integer getAlgorithmid() {
        return algorithmid;
    }

    public void setAlgorithmid(Integer algorithmid) {
        this.algorithmid = algorithmid;
    }

    public Integer getPrealgorithmid() {
        return prealgorithmid;
    }

    public void setPrealgorithmid(Integer prealgorithmid) {
        this.prealgorithmid = prealgorithmid;
    }
}
