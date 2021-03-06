package deepthinking.fgi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "模板包含字段")
public class TableModulefield {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_modulefield.ID
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "ID")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_modulefield.ModuleID
     * 模板ID
     * @mbg.generated
     */
    @ApiModelProperty(value = "模板ID")
    private Integer moduleid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_modulefield.FieldName
     * 字段名称
     * @mbg.generated
     */
    @ApiModelProperty(value = "字段名称")
    private String fieldname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_modulefield.FieldType
     * 字段类型
     * @mbg.generated
     */
    @ApiModelProperty(value = "字段类型")
    private String fieldtype;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_modulefield.Remark
     * 备注
     * @mbg.generated
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_modulefield.ID
     *
     * @return the value of table_modulefield.ID
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_modulefield.ID
     *
     * @param id the value for table_modulefield.ID
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_modulefield.ModuleID
     *
     * @return the value of table_modulefield.ModuleID
     *
     * @mbg.generated
     */
    public Integer getModuleid() {
        return moduleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_modulefield.ModuleID
     *
     * @param moduleid the value for table_modulefield.ModuleID
     *
     * @mbg.generated
     */
    public void setModuleid(Integer moduleid) {
        this.moduleid = moduleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_modulefield.FieldName
     *
     * @return the value of table_modulefield.FieldName
     *
     * @mbg.generated
     */
    public String getFieldname() {
        return fieldname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_modulefield.FieldName
     *
     * @param fieldname the value for table_modulefield.FieldName
     *
     * @mbg.generated
     */
    public void setFieldname(String fieldname) {
        this.fieldname = fieldname == null ? null : fieldname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_modulefield.FieldType
     *
     * @return the value of table_modulefield.FieldType
     *
     * @mbg.generated
     */
    public String getFieldtype() {
        return fieldtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_modulefield.FieldType
     *
     * @param fieldtype the value for table_modulefield.FieldType
     *
     * @mbg.generated
     */
    public void setFieldtype(String fieldtype) {
        this.fieldtype = fieldtype == null ? null : fieldtype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_modulefield.Remark
     *
     * @return the value of table_modulefield.Remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_modulefield.Remark
     *
     * @param remark the value for table_modulefield.Remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}