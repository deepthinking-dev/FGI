package deepthinking.fgi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "算子接口参数信息")
public class TableInterfaceparameters {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_interfaceparameters.ID
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "ID")
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_interfaceparameters.InterfaceID
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "接口ID")
    private String interfaceid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_interfaceparameters.ParametersSources
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "参数来源 算子的参数ID或者常量")
    private String parameterssources;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_interfaceparameters.ParametersName
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "参数名称")
    private String parametersname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_interfaceparameters.inOrOut
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "输入或者输出 0是输入，1是输出")
    private Long inorout;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_interfaceparameters.ID
     *
     * @return the value of table_interfaceparameters.ID
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_interfaceparameters.ID
     *
     * @param id the value for table_interfaceparameters.ID
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_interfaceparameters.InterfaceID
     *
     * @return the value of table_interfaceparameters.InterfaceID
     *
     * @mbg.generated
     */
    public String getInterfaceid() {
        return interfaceid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_interfaceparameters.InterfaceID
     *
     * @param interfaceid the value for table_interfaceparameters.InterfaceID
     *
     * @mbg.generated
     */
    public void setInterfaceid(String interfaceid) {
        this.interfaceid = interfaceid == null ? null : interfaceid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_interfaceparameters.ParametersSources
     *
     * @return the value of table_interfaceparameters.ParametersSources
     *
     * @mbg.generated
     */
    public String getParameterssources() {
        return parameterssources;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_interfaceparameters.ParametersSources
     *
     * @param parameterssources the value for table_interfaceparameters.ParametersSources
     *
     * @mbg.generated
     */
    public void setParameterssources(String parameterssources) {
        this.parameterssources = parameterssources == null ? null : parameterssources.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_interfaceparameters.ParametersName
     *
     * @return the value of table_interfaceparameters.ParametersName
     *
     * @mbg.generated
     */
    public String getParametersname() {
        return parametersname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_interfaceparameters.ParametersName
     *
     * @param parametersname the value for table_interfaceparameters.ParametersName
     *
     * @mbg.generated
     */
    public void setParametersname(String parametersname) {
        this.parametersname = parametersname == null ? null : parametersname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_interfaceparameters.inOrOut
     *
     * @return the value of table_interfaceparameters.inOrOut
     *
     * @mbg.generated
     */
    public Long getInorout() {
        return inorout;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_interfaceparameters.inOrOut
     *
     * @param inorout the value for table_interfaceparameters.inOrOut
     *
     * @mbg.generated
     */
    public void setInorout(Long inorout) {
        this.inorout = inorout;
    }
}