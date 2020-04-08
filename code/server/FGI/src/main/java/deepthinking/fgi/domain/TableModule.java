package deepthinking.fgi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "模型")
public class TableModule {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_module.ID
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "ID")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_module.ModuleName
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "模型名称")
    private String modulename;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_module.SqlUrl
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "数据库连接")
    private String sqlurl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_module.ModuleGroup
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "模型组")
    private String modulegroup;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_module.Des
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "模型描述")
    private String des;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_module.UserID
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "用户ID")
    private Integer userid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_module.Remark
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "模板所含字段")
        private List<TableModulefield> modulefields;

        public List<TableModulefield> getModulefields() {
            return modulefields;
        }

        public void setModulefields(List<TableModulefield> modulefields) {
            this.modulefields = modulefields;
        }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_module.ID
     *
     * @return the value of table_module.ID
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_module.ID
     *
     * @param id the value for table_module.ID
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_module.ModuleName
     *
     * @return the value of table_module.ModuleName
     *
     * @mbg.generated
     */
    public String getModulename() {
        return modulename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_module.ModuleName
     *
     * @param modulename the value for table_module.ModuleName
     *
     * @mbg.generated
     */
    public void setModulename(String modulename) {
        this.modulename = modulename == null ? null : modulename.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_module.SqlUrl
     *
     * @return the value of table_module.SqlUrl
     *
     * @mbg.generated
     */
    public String getSqlurl() {
        return sqlurl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_module.SqlUrl
     *
     * @param sqlurl the value for table_module.SqlUrl
     *
     * @mbg.generated
     */
    public void setSqlurl(String sqlurl) {
        this.sqlurl = sqlurl == null ? null : sqlurl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_module.ModuleGroup
     *
     * @return the value of table_module.ModuleGroup
     *
     * @mbg.generated
     */
    public String getModulegroup() {
        return modulegroup;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_module.ModuleGroup
     *
     * @param modulegroup the value for table_module.ModuleGroup
     *
     * @mbg.generated
     */
    public void setModulegroup(String modulegroup) {
        this.modulegroup = modulegroup == null ? null : modulegroup.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_module.Des
     *
     * @return the value of table_module.Des
     *
     * @mbg.generated
     */
    public String getDes() {
        return des;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_module.Des
     *
     * @param des the value for table_module.Des
     *
     * @mbg.generated
     */
    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_module.UserID
     *
     * @return the value of table_module.UserID
     *
     * @mbg.generated
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_module.UserID
     *
     * @param userid the value for table_module.UserID
     *
     * @mbg.generated
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_module.Remark
     *
     * @return the value of table_module.Remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_module.Remark
     *
     * @param remark the value for table_module.Remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}