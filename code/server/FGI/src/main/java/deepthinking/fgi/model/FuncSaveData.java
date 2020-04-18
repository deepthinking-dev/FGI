package deepthinking.fgi.model;

import deepthinking.fgi.domain.TableAlgorithmcondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
@ApiModel(value = "参数动作保存数据模型")
public class FuncSaveData {
    @ApiModelProperty(value = "动作集合")
    private List<TableAlgorithmcondition> algorithmconditions;
    @ApiModelProperty(value = "动作所在参数ID")
    private String interfaceParametersID;
    @ApiModelProperty(value = "动作所在线的ID")
    private int interfaceRoleId;
    @ApiModelProperty(value = "动作间的关系")
    private String actionRelation;

    public FuncSaveData() {
    }

    public FuncSaveData(List<TableAlgorithmcondition> algorithmconditions, String interfaceParametersID, int interfaceRoleId, String actionRelation) {
        this.algorithmconditions = algorithmconditions;
        this.interfaceParametersID = interfaceParametersID;
        this.interfaceRoleId = interfaceRoleId;
        this.actionRelation = actionRelation;
    }

    public List<TableAlgorithmcondition> getAlgorithmconditions() {
        return algorithmconditions;
    }

    public void setAlgorithmconditions(List<TableAlgorithmcondition> algorithmconditions) {
        this.algorithmconditions = algorithmconditions;
    }

    public String getInterfaceParametersID() {
        return interfaceParametersID;
    }

    public void setInterfaceParametersID(String interfaceParametersID) {
        this.interfaceParametersID = interfaceParametersID;
    }

    public int getInterfaceRoleId() {
        return interfaceRoleId;
    }

    public void setInterfaceRoleId(int interfaceRoleId) {
        this.interfaceRoleId = interfaceRoleId;
    }

    public String getActionRelation() {
        return actionRelation;
    }

    public void setActionRelation(String actionRelation) {
        this.actionRelation = actionRelation;
    }
}
