package deepthinking.fgi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "算法接口关系")
public class TableInterfacerole {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_interfacerole.ID
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "ID")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_interfacerole.RoleID
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "规则ID")
    private Integer roleid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_interfacerole.InterfaceID
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "接口ID")
    private String interfaceid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_interfacerole.ParametersID
     *
     * @mbg.generated
     */
      @ApiModelProperty(value = "接口参数ID")
    private String parametersid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_interfacerole.PreInterfaceID
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "前序接口ID")
    private String preinterfaceid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_interfacerole.PreParametersID
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "前序接口参数ID")
    private String preparametersid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_interfacerole.Des
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "描述")
    private String des;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_interfacerole.Remark
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_interfacerole.ID
     *
     * @return the value of table_interfacerole.ID
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_interfacerole.ID
     *
     * @param id the value for table_interfacerole.ID
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_interfacerole.RoleID
     *
     * @return the value of table_interfacerole.RoleID
     *
     * @mbg.generated
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_interfacerole.RoleID
     *
     * @param roleid the value for table_interfacerole.RoleID
     *
     * @mbg.generated
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_interfacerole.InterfaceID
     *
     * @return the value of table_interfacerole.InterfaceID
     *
     * @mbg.generated
     */
    public String getInterfaceid() {
        return interfaceid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_interfacerole.InterfaceID
     *
     * @param interfaceid the value for table_interfacerole.InterfaceID
     *
     * @mbg.generated
     */
    public void setInterfaceid(String interfaceid) {
        this.interfaceid = interfaceid == null ? null : interfaceid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_interfacerole.ParametersID
     *
     * @return the value of table_interfacerole.ParametersID
     *
     * @mbg.generated
     */
    public String getParametersid() {
        return parametersid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_interfacerole.ParametersID
     *
     * @param parametersid the value for table_interfacerole.ParametersID
     *
     * @mbg.generated
     */
    public void setParametersid(String parametersid) {
        this.parametersid = parametersid == null ? null : parametersid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_interfacerole.PreInterfaceID
     *
     * @return the value of table_interfacerole.PreInterfaceID
     *
     * @mbg.generated
     */
    public String getPreinterfaceid() {
        return preinterfaceid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_interfacerole.PreInterfaceID
     *
     * @param preinterfaceid the value for table_interfacerole.PreInterfaceID
     *
     * @mbg.generated
     */
    public void setPreinterfaceid(String preinterfaceid) {
        this.preinterfaceid = preinterfaceid == null ? null : preinterfaceid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_interfacerole.PreParametersID
     *
     * @return the value of table_interfacerole.PreParametersID
     *
     * @mbg.generated
     */
    public String getPreparametersid() {
        return preparametersid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_interfacerole.PreParametersID
     *
     * @param preparametersid the value for table_interfacerole.PreParametersID
     *
     * @mbg.generated
     */
    public void setPreparametersid(String preparametersid) {
        this.preparametersid = preparametersid == null ? null : preparametersid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_interfacerole.Des
     *
     * @return the value of table_interfacerole.Des
     *
     * @mbg.generated
     */
    public String getDes() {
        return des;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_interfacerole.Des
     *
     * @param des the value for table_interfacerole.Des
     *
     * @mbg.generated
     */
    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_interfacerole.Remark
     *
     * @return the value of table_interfacerole.Remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_interfacerole.Remark
     *
     * @param remark the value for table_interfacerole.Remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}