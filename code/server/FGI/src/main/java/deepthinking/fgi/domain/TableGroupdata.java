package deepthinking.fgi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "分组信息")
public class TableGroupdata {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_groupdata.id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "ID")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_groupdata.groupName
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "分组名称")
    private String groupname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_groupdata.groupType
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "分组类型：模型：1；算法：2；规则：3 ")
    private Integer grouptype;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_groupdata.id
     *
     * @return the value of table_groupdata.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_groupdata.id
     *
     * @param id the value for table_groupdata.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_groupdata.groupName
     *
     * @return the value of table_groupdata.groupName
     *
     * @mbg.generated
     */
    public String getGroupname() {
        return groupname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_groupdata.groupName
     *
     * @param groupname the value for table_groupdata.groupName
     *
     * @mbg.generated
     */
    public void setGroupname(String groupname) {
        this.groupname = groupname == null ? null : groupname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_groupdata.groupType
     *
     * @return the value of table_groupdata.groupType
     *
     * @mbg.generated
     */
    public Integer getGrouptype() {
        return grouptype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_groupdata.groupType
     *
     * @param grouptype the value for table_groupdata.groupType
     *
     * @mbg.generated
     */
    public void setGrouptype(Integer grouptype) {
        this.grouptype = grouptype;
    }
}