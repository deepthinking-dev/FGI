package deepthinking.fgi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/3/26 19:06
 */
@ApiModel(value = "算法简略信息数据模型")
public class AlgorithmBaseInfo {
    private Map<String,Object> tableAlgorithm;
    @ApiModelProperty(value = "输出个数")
    private int outNum;
    @ApiModelProperty(value = "输入个数")
    private int inNum;
    @ApiModelProperty(value = "参数基本信息")
    private List<Map<String,Object>> tableFuncs;

    public AlgorithmBaseInfo(Map<String, Object> tableAlgorithm, int outNum, int inNum, List<Map<String, Object>> tableFuncs) {
        this.tableAlgorithm = tableAlgorithm;
        this.outNum = outNum;
        this.inNum = inNum;
        this.tableFuncs = tableFuncs;
    }

    public AlgorithmBaseInfo() {
    }

    public Map<String, Object> getTableAlgorithm() {
        return tableAlgorithm;
    }

    public void setTableAlgorithm(Map<String, Object> tableAlgorithm) {
        this.tableAlgorithm = tableAlgorithm;
    }

    public int getOutNum() {
        return outNum;
    }

    public void setOutNum(int outNum) {
        this.outNum = outNum;
    }

    public int getInNum() {
        return inNum;
    }

    public void setInNum(int inNum) {
        this.inNum = inNum;
    }

    public List<Map<String, Object>> getTableFuncs() {
        return tableFuncs;
    }

    public void setTableFuncs(List<Map<String, Object>> tableFuncs) {
        this.tableFuncs = tableFuncs;
    }
}
