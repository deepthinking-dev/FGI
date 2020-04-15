package deepthinking.fgi.model;

import deepthinking.fgi.domain.TableAlgorithmcondition;

import java.util.List;

public class FuncSaveData {
    private List<TableAlgorithmcondition> algorithmconditions;
    private String interfaceParametersID;
    private int interfaceRoleId;

    public FuncSaveData() {
    }

    public FuncSaveData(List<TableAlgorithmcondition> algorithmconditions, String interfaceParametersID, int interfaceRoleId) {
        this.algorithmconditions = algorithmconditions;
        this.interfaceParametersID = interfaceParametersID;
        this.interfaceRoleId = interfaceRoleId;
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
}
