package deepthinking.fgi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "算子接口")
public class TableOperatorinterface {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_operatorinterface.ID
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "ID")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_operatorinterface.RoleID
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "规则ID")
    private Integer roleid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_operatorinterface.AlgorithmID
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "算子ID")
    private Integer algorithmid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_operatorinterface.InterfaceName
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "接口名称")
    private String interfacename;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_operatorinterface.ID
     *
     * @return the value of table_operatorinterface.ID
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_operatorinterface.ID
     *
     * @param id the value for table_operatorinterface.ID
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_operatorinterface.RoleID
     *
     * @return the value of table_operatorinterface.RoleID
     *
     * @mbg.generated
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_operatorinterface.RoleID
     *
     * @param roleid the value for table_operatorinterface.RoleID
     *
     * @mbg.generated
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_operatorinterface.AlgorithmID
     *
     * @return the value of table_operatorinterface.AlgorithmID
     *
     * @mbg.generated
     */
    public Integer getAlgorithmid() {
        return algorithmid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_operatorinterface.AlgorithmID
     *
     * @param algorithmid the value for table_operatorinterface.AlgorithmID
     *
     * @mbg.generated
     */
    public void setAlgorithmid(Integer algorithmid) {
        this.algorithmid = algorithmid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_operatorinterface.InterfaceName
     *
     * @return the value of table_operatorinterface.InterfaceName
     *
     * @mbg.generated
     */
    public String getInterfacename() {
        return interfacename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_operatorinterface.InterfaceName
     *
     * @param interfacename the value for table_operatorinterface.InterfaceName
     *
     * @mbg.generated
     */
    public void setInterfacename(String interfacename) {
        this.interfacename = interfacename == null ? null : interfacename.trim();
    }
}