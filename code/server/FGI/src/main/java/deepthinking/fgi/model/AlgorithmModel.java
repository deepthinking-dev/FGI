package deepthinking.fgi.model;

import deepthinking.fgi.domain.TableAlgorithm;
import deepthinking.fgi.domain.TableFunc;
import deepthinking.fgi.domain.TableModuleuserrelation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/21 15:33
 */
@ApiModel(value = "算子相关信息交互数据模型")
public class AlgorithmModel {
    @ApiModelProperty(value = "算子基本信息")
    private TableAlgorithm tableAlgorithm;
    @ApiModelProperty(value = "算子公式变量信息")
    private List<TableFunc> tableFuncs;
    @ApiModelProperty(value = "算子用户关系 目前暂不处理")
    private TableModuleuserrelation tableModuleuserrelation;

    public AlgorithmModel() {
    }

    public AlgorithmModel(TableAlgorithm tableAlgorithm, List<TableFunc> tableFuncs, TableModuleuserrelation tableModuleuserrelation) {
        this.tableAlgorithm = tableAlgorithm;
        this.tableFuncs = tableFuncs;
        this.tableModuleuserrelation = tableModuleuserrelation;
    }

    public TableAlgorithm getTableAlgorithm() {
        return tableAlgorithm;
    }

    public void setTableAlgorithm(TableAlgorithm tableAlgorithm) {
        this.tableAlgorithm = tableAlgorithm;
    }

    public List<TableFunc> getTableFuncs() {
        return tableFuncs;
    }

    public void setTableFuncs(List<TableFunc> tableFuncs) {
        this.tableFuncs = tableFuncs;
    }

    public TableModuleuserrelation getTableModuleuserrelation() {
        return tableModuleuserrelation;
    }

    public void setTableModuleuserrelation(TableModuleuserrelation tableModuleuserrelation) {
        this.tableModuleuserrelation = tableModuleuserrelation;
    }
}
