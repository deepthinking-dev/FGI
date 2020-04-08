package deepthinking.fgi.model;

import deepthinking.fgi.domain.TableInterfaceparameters;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "算法规则中算子接口信息")
public class OperatorInterfaceDataModel {
    @ApiModelProperty(value = "ID")
    private int id;
    @ApiModelProperty(value = "规则ID")
    private int RoleID;
    @ApiModelProperty(value = "算子ID")
    private int AlgorithmID;
    @ApiModelProperty(value = "接口名称ID")
    private String InterfaceName;
    @ApiModelProperty(value = "接口参数信息表")
    private List<TableInterfaceparameters> tableInterfaceparametersList;

    public OperatorInterfaceDataModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int roleID) {
        RoleID = roleID;
    }

    public int getAlgorithmID() {
        return AlgorithmID;
    }

    public void setAlgorithmID(int algorithmID) {
        AlgorithmID = algorithmID;
    }

    public String getInterfaceName() {
        return InterfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        InterfaceName = interfaceName;
    }

    public List<TableInterfaceparameters> getTableInterfaceparametersList() {
        return tableInterfaceparametersList;
    }

    public void setTableInterfaceparametersList(List<TableInterfaceparameters> tableInterfaceparametersList) {
        this.tableInterfaceparametersList = tableInterfaceparametersList;
    }
}
