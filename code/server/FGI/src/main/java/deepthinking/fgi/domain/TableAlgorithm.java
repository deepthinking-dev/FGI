package deepthinking.fgi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "算法信息")
public class TableAlgorithm {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_algorithm.ID
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "ID")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_algorithm.AlgorithmName
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "算法名称")
    private String algorithmname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_algorithm.AlgorithmAuthor
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "算法作者")
    private String algorithmauthor;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_algorithm.IsPublic
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "是否公共算法")
    private Long ispublic;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_algorithm.AlgorithmType
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "算法类型(引用；算法公式；逻辑条件)")
    private Long algorithmtype;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_algorithm.AlgorithmFun
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "如果是公式算子，这里保存公式")
    private String algorithmfun;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_algorithm.Des
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "描述")
    private String des;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_algorithm.UserID
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "用户ID")
    private Integer userid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table_algorithm.Remark
     *
     * @mbg.generated
     */
     @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_algorithm.ID
     *
     * @return the value of table_algorithm.ID
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_algorithm.ID
     *
     * @param id the value for table_algorithm.ID
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_algorithm.AlgorithmName
     *
     * @return the value of table_algorithm.AlgorithmName
     *
     * @mbg.generated
     */
    public String getAlgorithmname() {
        return algorithmname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_algorithm.AlgorithmName
     *
     * @param algorithmname the value for table_algorithm.AlgorithmName
     *
     * @mbg.generated
     */
    public void setAlgorithmname(String algorithmname) {
        this.algorithmname = algorithmname == null ? null : algorithmname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_algorithm.AlgorithmAuthor
     *
     * @return the value of table_algorithm.AlgorithmAuthor
     *
     * @mbg.generated
     */
    public String getAlgorithmauthor() {
        return algorithmauthor;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_algorithm.AlgorithmAuthor
     *
     * @param algorithmauthor the value for table_algorithm.AlgorithmAuthor
     *
     * @mbg.generated
     */
    public void setAlgorithmauthor(String algorithmauthor) {
        this.algorithmauthor = algorithmauthor == null ? null : algorithmauthor.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_algorithm.IsPublic
     *
     * @return the value of table_algorithm.IsPublic
     *
     * @mbg.generated
     */
    public Long getIspublic() {
        return ispublic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_algorithm.IsPublic
     *
     * @param ispublic the value for table_algorithm.IsPublic
     *
     * @mbg.generated
     */
    public void setIspublic(Long ispublic) {
        this.ispublic = ispublic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_algorithm.AlgorithmType
     *
     * @return the value of table_algorithm.AlgorithmType
     *
     * @mbg.generated
     */
    public Long getAlgorithmtype() {
        return algorithmtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_algorithm.AlgorithmType
     *
     * @param algorithmtype the value for table_algorithm.AlgorithmType
     *
     * @mbg.generated
     */
    public void setAlgorithmtype(Long algorithmtype) {
        this.algorithmtype = algorithmtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_algorithm.AlgorithmFun
     *
     * @return the value of table_algorithm.AlgorithmFun
     *
     * @mbg.generated
     */
    public String getAlgorithmfun() {
        return algorithmfun;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_algorithm.AlgorithmFun
     *
     * @param algorithmfun the value for table_algorithm.AlgorithmFun
     *
     * @mbg.generated
     */
    public void setAlgorithmfun(String algorithmfun) {
        this.algorithmfun = algorithmfun == null ? null : algorithmfun.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_algorithm.Des
     *
     * @return the value of table_algorithm.Des
     *
     * @mbg.generated
     */
    public String getDes() {
        return des;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_algorithm.Des
     *
     * @param des the value for table_algorithm.Des
     *
     * @mbg.generated
     */
    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_algorithm.UserID
     *
     * @return the value of table_algorithm.UserID
     *
     * @mbg.generated
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_algorithm.UserID
     *
     * @param userid the value for table_algorithm.UserID
     *
     * @mbg.generated
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column table_algorithm.Remark
     *
     * @return the value of table_algorithm.Remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column table_algorithm.Remark
     *
     * @param remark the value for table_algorithm.Remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}