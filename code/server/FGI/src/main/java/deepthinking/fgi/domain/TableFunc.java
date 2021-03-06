package deepthinking.fgi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 公式变量
 */
@ApiModel(value = "公式变量")
public class TableFunc {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_func.ID
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "ID")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_func.ModuleID
     * 算子ID
     * @mbg.generated
     */
    @ApiModelProperty(value = "算子ID")
    private Integer moduleid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_func.VarName
     * 变量名称
     * @mbg.generated
     */
    @ApiModelProperty(value = "变量名称")
    private String varname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_func.VarType
     * 变量类型：常量
     *             数据项
     *             其他模块计算结果',
     * @mbg.generated
     */
    @ApiModelProperty(value = "变量类型：常量 数据项 其他模块计算结果")
    private String vartype;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_func.ValValue
     * '变量值：
     *             变量类型为常量时，此处为具体数值
     *             类型为数据项时，此处模块中字段名称
     *             类型为其他计算结果时，此处为其他模块的ID。
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "变量值： 变量类型为常量时，此处为具体数值;类型为数据项时，此处模块中字段名称;类型为其他计算结果时，此处为其他模块的ID")
    private String valvalue;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_func.Remark
     * 备注
     * @mbg.generated
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_func.ID
     *
     * @return the value of table_func.ID
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_func.ID
     *
     * @param id the value for table_func.ID
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_func.ModuleID
     *
     * @return the value of table_func.ModuleID
     *
     * @mbg.generated
     */
    public Integer getModuleid() {
        return moduleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_func.ModuleID
     *
     * @param moduleid the value for table_func.ModuleID
     *
     * @mbg.generated
     */
    public void setModuleid(Integer moduleid) {
        this.moduleid = moduleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_func.VarName
     *
     * @return the value of table_func.VarName
     *
     * @mbg.generated
     */
    public String getVarname() {
        return varname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_func.VarName
     *
     * @param varname the value for table_func.VarName
     *
     * @mbg.generated
     */
    public void setVarname(String varname) {
        this.varname = varname == null ? null : varname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_func.VarType
     *
     * @return the value of table_func.VarType
     *
     * @mbg.generated
     */
    public String getVartype() {
        return vartype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_func.VarType
     *
     * @param vartype the value for table_func.VarType
     *
     * @mbg.generated
     */
    public void setVartype(String vartype) {
        this.vartype = vartype == null ? null : vartype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_func.ValValue
     *
     * @return the value of table_func.ValValue
     *
     * @mbg.generated
     */
    public String getValvalue() {
        return valvalue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_func.ValValue
     *
     * @param valvalue the value for table_func.ValValue
     *
     * @mbg.generated
     */
    public void setValvalue(String valvalue) {
        this.valvalue = valvalue == null ? null : valvalue.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_func.Remark
     *
     * @return the value of table_func.Remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_func.Remark
     *
     * @param remark the value for table_func.Remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}