package deepthinking.fgi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "算法规则基本信息")
public class TableRole {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_role.ID
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "ID")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_role.RoleName
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "规则名称")
    private String rolename;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_role.Des
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "规则描述")
    private String des;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_role.EntranceNote
     *
     * @mbg.generated
     */

    @ApiModelProperty(value = "入口备注")
    private String entrancenote;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_role.UuserID
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "用户ID")
    private Integer uuserid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_role.RoleGroup
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "所属组")
    private String rolegroup;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_role.Status
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "状态")
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_role.Remark
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "备注")
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_role.Remark2
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "备注2")
    private String remark2;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_role.Remark3
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "备注3")
    private String remark3;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_role.coordinate
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "坐标")
    private String coordinate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_role.ID
     *
     * @return the value of table_role.ID
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_role.ID
     *
     * @param id the value for table_role.ID
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_role.RoleName
     *
     * @return the value of table_role.RoleName
     *
     * @mbg.generated
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_role.RoleName
     *
     * @param rolename the value for table_role.RoleName
     *
     * @mbg.generated
     */
    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_role.Des
     *
     * @return the value of table_role.Des
     *
     * @mbg.generated
     */
    public String getDes() {
        return des;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_role.Des
     *
     * @param des the value for table_role.Des
     *
     * @mbg.generated
     */
    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_role.EntranceNote
     *
     * @return the value of table_role.EntranceNote
     *
     * @mbg.generated
     */
    public String getEntrancenote() {
        return entrancenote;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_role.EntranceNote
     *
     * @param entrancenote the value for table_role.EntranceNote
     *
     * @mbg.generated
     */
    public void setEntrancenote(String entrancenote) {
        this.entrancenote = entrancenote == null ? null : entrancenote.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_role.UuserID
     *
     * @return the value of table_role.UuserID
     *
     * @mbg.generated
     */
    public Integer getUuserid() {
        return uuserid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_role.UuserID
     *
     * @param uuserid the value for table_role.UuserID
     *
     * @mbg.generated
     */
    public void setUuserid(Integer uuserid) {
        this.uuserid = uuserid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_role.RoleGroup
     *
     * @return the value of table_role.RoleGroup
     *
     * @mbg.generated
     */
    public String getRolegroup() {
        return rolegroup;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_role.RoleGroup
     *
     * @param rolegroup the value for table_role.RoleGroup
     *
     * @mbg.generated
     */
    public void setRolegroup(String rolegroup) {
        this.rolegroup = rolegroup == null ? null : rolegroup.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_role.Status
     *
     * @return the value of table_role.Status
     *
     * @mbg.generated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_role.Status
     *
     * @param status the value for table_role.Status
     *
     * @mbg.generated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_role.Remark
     *
     * @return the value of table_role.Remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_role.Remark
     *
     * @param remark the value for table_role.Remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_role.Remark2
     *
     * @return the value of table_role.Remark2
     *
     * @mbg.generated
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_role.Remark2
     *
     * @param remark2 the value for table_role.Remark2
     *
     * @mbg.generated
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_role.Remark3
     *
     * @return the value of table_role.Remark3
     *
     * @mbg.generated
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_role.Remark3
     *
     * @param remark3 the value for table_role.Remark3
     *
     * @mbg.generated
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_role.coordinate
     *
     * @return the value of table_role.coordinate
     *
     * @mbg.generated
     */
    public String getCoordinate() {
        return coordinate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_role.coordinate
     *
     * @param coordinate the value for table_role.coordinate
     *
     * @mbg.generated
     */
    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate == null ? null : coordinate.trim();
    }
}