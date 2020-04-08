package deepthinking.fgi.model;

import deepthinking.fgi.domain.TableAlgorithmcondition;
import deepthinking.fgi.domain.TableInterfacerole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/24 12:25
 */
@ApiModel(value = "算法规则中一条连线代表的数据模型")
public class InterfaceRoleDataModel {
    @ApiModelProperty(value = "ID")
    private Integer id;
    @ApiModelProperty(value = "规则ID")
    private Integer roleid;
    @ApiModelProperty(value = "接口ID")
    private Integer InterfaceID;
    @ApiModelProperty(value = "接口参数ID")
    private Integer ParametersID;
    @ApiModelProperty(value = "前序接口ID")
    private Integer PreInterfaceID;
    @ApiModelProperty(value = "前序接口参数ID")
    private Integer PreParametersID;
    @ApiModelProperty(value = "接口参数动作集合")
    private List<TableAlgorithmcondition> algorithmconditions;
    @ApiModelProperty(value = "描述")
    private String des;
    @ApiModelProperty(value = "备注")
    private String remark;

    public TableInterfacerole getTable_InterfaceRole(){
        TableInterfacerole tableInterfacerole=new TableInterfacerole();
        tableInterfacerole.setId(id);
        tableInterfacerole.setInterfaceid(InterfaceID);
        tableInterfacerole.setParametersid(ParametersID);
        tableInterfacerole.setPreinterfaceid(PreInterfaceID);
        tableInterfacerole.setPreparametersid(PreParametersID);
        tableInterfacerole.setDes(des);
        tableInterfacerole.setRemark("");
        tableInterfacerole.setRoleid(roleid);
        return tableInterfacerole;
    }

    public InterfaceRoleDataModel getInterfaceRoleDataModelFromTableInterfacerole(TableInterfacerole tableInterfacerole){
        return new InterfaceRoleDataModel(tableInterfacerole.getId(),tableInterfacerole.getRoleid(),tableInterfacerole.getInterfaceid(),tableInterfacerole.getParametersid(),tableInterfacerole.getPreinterfaceid(),
                tableInterfacerole.getPreparametersid(),null,tableInterfacerole.getDes(),tableInterfacerole.getRemark());
    }

    public InterfaceRoleDataModel() {
    }

    public InterfaceRoleDataModel(Integer id, Integer roleid, Integer interfaceID, Integer parametersID, Integer preInterfaceID,
                                  Integer preParametersID, List<TableAlgorithmcondition> algorithmconditions, String des, String remark) {
        this.id = id;
        this.roleid = roleid;
        InterfaceID = interfaceID;
        ParametersID = parametersID;
        PreInterfaceID = preInterfaceID;
        PreParametersID = preParametersID;
        this.algorithmconditions = algorithmconditions;
        this.des = des;
        this.remark = remark;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInterfaceID() {
        return InterfaceID;
    }

    public void setInterfaceID(Integer interfaceID) {
        InterfaceID = interfaceID;
    }

    public Integer getParametersID() {
        return ParametersID;
    }

    public void setParametersID(Integer parametersID) {
        ParametersID = parametersID;
    }

    public Integer getPreInterfaceID() {
        return PreInterfaceID;
    }

    public void setPreInterfaceID(Integer preInterfaceID) {
        PreInterfaceID = preInterfaceID;
    }

    public Integer getPreParametersID() {
        return PreParametersID;
    }

    public void setPreParametersID(Integer preParametersID) {
        PreParametersID = preParametersID;
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
