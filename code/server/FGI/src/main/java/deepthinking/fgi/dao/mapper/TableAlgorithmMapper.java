package deepthinking.fgi.dao.mapper;

import java.util.List;
import java.util.Map;

import deepthinking.fgi.domain.TableAlgorithm;
import deepthinking.fgi.domain.TableAlgorithmCriteria;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface TableAlgorithmMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithm
     *
     * @mbg.generated
     */
    @SelectProvider(type=TableAlgorithmSqlProvider.class, method="countByExample")
    long countByExample(TableAlgorithmCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithm
     *
     * @mbg.generated
     */
    @DeleteProvider(type=TableAlgorithmSqlProvider.class, method="deleteByExample")
    int deleteByExample(TableAlgorithmCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithm
     *
     * @mbg.generated
     */
    @Delete({
        "delete from table_algorithm",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithm
     *
     * @mbg.generated
     */
    @Insert({
        "insert into table_algorithm (AlgorithmName, AlgorithmAuthor, ",
        "IsPublic, AlgorithmType, ",
        "AlgorithmFun, Des, ",
        "UserID, AlgorithmGroup, ",
        "Remark, Status, ",
        "Remark2)",
        "values (#{algorithmname,jdbcType=VARCHAR}, #{algorithmauthor,jdbcType=VARCHAR}, ",
        "#{ispublic,jdbcType=DECIMAL}, #{algorithmtype,jdbcType=DECIMAL}, ",
        "#{algorithmfun,jdbcType=VARCHAR}, #{des,jdbcType=VARCHAR}, ",
        "#{userid,jdbcType=INTEGER}, #{algorithmgroup,jdbcType=VARCHAR}, ",
        "#{remark,jdbcType=VARCHAR}, #{status,jdbcType=VARCHAR}, ",
        "#{remark2,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(TableAlgorithm record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithm
     *
     * @mbg.generated
     */
    @InsertProvider(type=TableAlgorithmSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(TableAlgorithm record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithm
     *
     * @mbg.generated
     */
    @SelectProvider(type=TableAlgorithmSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="AlgorithmName", property="algorithmname", jdbcType=JdbcType.VARCHAR),
        @Result(column="AlgorithmAuthor", property="algorithmauthor", jdbcType=JdbcType.VARCHAR),
        @Result(column="IsPublic", property="ispublic", jdbcType=JdbcType.DECIMAL),
        @Result(column="AlgorithmType", property="algorithmtype", jdbcType=JdbcType.DECIMAL),
        @Result(column="AlgorithmFun", property="algorithmfun", jdbcType=JdbcType.VARCHAR),
        @Result(column="Des", property="des", jdbcType=JdbcType.VARCHAR),
        @Result(column="UserID", property="userid", jdbcType=JdbcType.INTEGER),
        @Result(column="AlgorithmGroup", property="algorithmgroup", jdbcType=JdbcType.VARCHAR),
        @Result(column="Remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="Status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="Remark2", property="remark2", jdbcType=JdbcType.VARCHAR)
    })
    List<TableAlgorithm> selectByExample(TableAlgorithmCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithm
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "ID, AlgorithmName, AlgorithmAuthor, IsPublic, AlgorithmType, AlgorithmFun, Des, ",
        "UserID, AlgorithmGroup, Remark, Status, Remark2",
        "from table_algorithm",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="AlgorithmName", property="algorithmname", jdbcType=JdbcType.VARCHAR),
        @Result(column="AlgorithmAuthor", property="algorithmauthor", jdbcType=JdbcType.VARCHAR),
        @Result(column="IsPublic", property="ispublic", jdbcType=JdbcType.DECIMAL),
        @Result(column="AlgorithmType", property="algorithmtype", jdbcType=JdbcType.DECIMAL),
        @Result(column="AlgorithmFun", property="algorithmfun", jdbcType=JdbcType.VARCHAR),
        @Result(column="Des", property="des", jdbcType=JdbcType.VARCHAR),
        @Result(column="UserID", property="userid", jdbcType=JdbcType.INTEGER),
        @Result(column="AlgorithmGroup", property="algorithmgroup", jdbcType=JdbcType.VARCHAR),
        @Result(column="Remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="Status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="Remark2", property="remark2", jdbcType=JdbcType.VARCHAR)
    })
    TableAlgorithm selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithm
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TableAlgorithmSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TableAlgorithm record, @Param("example") TableAlgorithmCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithm
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TableAlgorithmSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TableAlgorithm record, @Param("example") TableAlgorithmCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithm
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TableAlgorithmSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TableAlgorithm record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithm
     *
     * @mbg.generated
     */
    @Update({
        "update table_algorithm",
        "set AlgorithmName = #{algorithmname,jdbcType=VARCHAR},",
          "AlgorithmAuthor = #{algorithmauthor,jdbcType=VARCHAR},",
          "IsPublic = #{ispublic,jdbcType=DECIMAL},",
          "AlgorithmType = #{algorithmtype,jdbcType=DECIMAL},",
          "AlgorithmFun = #{algorithmfun,jdbcType=VARCHAR},",
          "Des = #{des,jdbcType=VARCHAR},",
          "UserID = #{userid,jdbcType=INTEGER},",
          "AlgorithmGroup = #{algorithmgroup,jdbcType=VARCHAR},",
          "Remark = #{remark,jdbcType=VARCHAR},",
          "Status = #{status,jdbcType=VARCHAR},",
          "Remark2 = #{remark2,jdbcType=VARCHAR}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TableAlgorithm record);
    @Select({
                "select",
                "ID, AlgorithmName,Status,AlgorithmAuthor,Des",
                "from table_algorithm where AlgorithmGroup = #{groupName}"
        })
        @Results({
                @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
                @Result(column="AlgorithmName", property="algorithmname", jdbcType=JdbcType.VARCHAR),
                @Result(column="Status", property="status", jdbcType=JdbcType.VARCHAR),
                @Result(column="AlgorithmAuthor", property="algorithmauthor", jdbcType=JdbcType.VARCHAR),
                @Result(column="Des", property="des", jdbcType=JdbcType.VARCHAR),
        })
        List<Map<String,Object>> selectBaseInfo(String groupName);
}