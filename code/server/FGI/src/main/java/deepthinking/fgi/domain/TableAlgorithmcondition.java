package deepthinking.fgi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 算子运行条件
 */
@ApiModel(value = "算子运行条件")
public class TableAlgorithmcondition {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_algorithmcondition.ID
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "ID")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_algorithmcondition.AlgorithmRoleID
     * 算法算子ID
     * @mbg.generated
     */
    @ApiModelProperty(value = "算法算子ID")
    private Integer algorithmroleid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_algorithmcondition.LogicRelation
     * 逻辑关系
     * @mbg.generated
     */
    @ApiModelProperty(value = "逻辑关系")
    private String logicrelation;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_algorithmcondition.LogicValue
     * 逻辑值
     * @mbg.generated
     */
    @ApiModelProperty(value = "逻辑值")
    private Long logicvalue;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_algorithmcondition.Remark
     * 备注
     * @mbg.generated
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_algorithmcondition.ID
     *
     * @return the value of table_algorithmcondition.ID
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_algorithmcondition.ID
     *
     * @param id the value for table_algorithmcondition.ID
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_algorithmcondition.AlgorithmRoleID
     *
     * @return the value of table_algorithmcondition.AlgorithmRoleID
     *
     * @mbg.generated
     */
    public Integer getAlgorithmroleid() {
        return algorithmroleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_algorithmcondition.AlgorithmRoleID
     *
     * @param algorithmroleid the value for table_algorithmcondition.AlgorithmRoleID
     *
     * @mbg.generated
     */
    public void setAlgorithmroleid(Integer algorithmroleid) {
        this.algorithmroleid = algorithmroleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_algorithmcondition.LogicRelation
     *
     * @return the value of table_algorithmcondition.LogicRelation
     *
     * @mbg.generated
     */
    public String getLogicrelation() {
        return logicrelation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_algorithmcondition.LogicRelation
     *
     * @param logicrelation the value for table_algorithmcondition.LogicRelation
     *
     * @mbg.generated
     */
    public void setLogicrelation(String logicrelation) {
        this.logicrelation = logicrelation == null ? null : logicrelation.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_algorithmcondition.LogicValue
     *
     * @return the value of table_algorithmcondition.LogicValue
     *
     * @mbg.generated
     */
    public Long getLogicvalue() {
        return logicvalue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_algorithmcondition.LogicValue
     *
     * @param logicvalue the value for table_algorithmcondition.LogicValue
     *
     * @mbg.generated
     */
    public void setLogicvalue(Long logicvalue) {
        this.logicvalue = logicvalue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_algorithmcondition.Remark
     *
     * @return the value of table_algorithmcondition.Remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_algorithmcondition.Remark
     *
     * @param remark the value for table_algorithmcondition.Remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}