package deepthinking.fgi.model;

import deepthinking.fgi.domain.TableRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/24 12:39
 */
@ApiModel(value = "算法规则前后端交互模型")
public class AlgorithmRuleSaveDataModel {
    @ApiModelProperty(value = "规则基本信息")
    private TableRole tableRole;
    @ApiModelProperty(value = "算法算子关系集合")
    private List<AlgorithmRuleDataModel> algorithmRuleDataModelList;
    @ApiModelProperty(value = "算子坐标信息字符串")
    private String coordinateinfo;

    public AlgorithmRuleSaveDataModel() {
    }

    public AlgorithmRuleSaveDataModel(TableRole tableRole, List<AlgorithmRuleDataModel> algorithmRuleDataModelList, String coordinateinfo) {
        this.tableRole = tableRole;
        this.algorithmRuleDataModelList = algorithmRuleDataModelList;
        this.coordinateinfo = coordinateinfo;
    }

    public TableRole getTableRole() {
        return tableRole;
    }

    public void setTableRole(TableRole tableRole) {
        this.tableRole = tableRole;
    }

    public List<AlgorithmRuleDataModel> getAlgorithmRuleDataModelList() {
        return algorithmRuleDataModelList;
    }

    public void setAlgorithmRuleDataModelList(List<AlgorithmRuleDataModel> algorithmRuleDataModelList) {
        this.algorithmRuleDataModelList = algorithmRuleDataModelList;
    }

    public String getCoordinateinfo() {
        return coordinateinfo;
    }

    public void setCoordinateinfo(String coordinateinfo) {
        this.coordinateinfo = coordinateinfo;
    }
}
