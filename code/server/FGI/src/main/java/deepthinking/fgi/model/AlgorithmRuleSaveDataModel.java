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
    @ApiModelProperty(value = "规则中接口信息集合")
    private List<OperatorInterfaceDataModel> operatorInterfaceDataModels;
    @ApiModelProperty(value = "接口之间的连线")
    private List<InterfaceRoleDataModel> interfaceRoleDataModels;

    public AlgorithmRuleSaveDataModel() {
    }

    public AlgorithmRuleSaveDataModel(TableRole tableRole, List<OperatorInterfaceDataModel> operatorInterfaceDataModels, List<InterfaceRoleDataModel> interfaceRoleDataModels) {
        this.tableRole = tableRole;
        this.operatorInterfaceDataModels = operatorInterfaceDataModels;
        this.interfaceRoleDataModels = interfaceRoleDataModels;
    }

    public List<InterfaceRoleDataModel> getInterfaceRoleDataModels() {
        return interfaceRoleDataModels;
    }

    public void setInterfaceRoleDataModels(List<InterfaceRoleDataModel> interfaceRoleDataModels) {
        this.interfaceRoleDataModels = interfaceRoleDataModels;
    }

    public TableRole getTableRole() {
        return tableRole;
    }

    public void setTableRole(TableRole tableRole) {
        this.tableRole = tableRole;
    }

    public List<OperatorInterfaceDataModel> getOperatorInterfaceDataModels() {
        return operatorInterfaceDataModels;
    }

    public void setOperatorInterfaceDataModels(List<OperatorInterfaceDataModel> operatorInterfaceDataModels) {
        this.operatorInterfaceDataModels = operatorInterfaceDataModels;
    }
}
