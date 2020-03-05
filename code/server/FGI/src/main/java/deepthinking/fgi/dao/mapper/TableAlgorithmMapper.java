package deepthinking.fgi.dao.mapper;

import deepthinking.fgi.domain.TableAlgorithm;
import deepthinking.fgi.domain.TableAlgorithmCriteria;
import java.util.List;

import org.apache.ibatis.annotations.*;
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
        "insert into table_algorithm (ID, ModuleID, ",
        "AlgorithmName, AlgorithmAuthor, ",
        "IsPublic, AlgorithmType, ",
        "AlgorithmFun, Des, ",
        "Remark)",
        "values (#{id,jdbcType=INTEGER}, #{moduleid,jdbcType=INTEGER}, ",
        "#{algorithmname,jdbcType=VARCHAR}, #{algorithmauthor,jdbcType=VARCHAR}, ",
        "#{ispublic,jdbcType=DECIMAL}, #{algorithmtype,jdbcType=DECIMAL}, ",
        "#{algorithmfun,jdbcType=VARCHAR}, #{des,jdbcType=VARCHAR}, ",
        "#{remark,jdbcType=VARCHAR})"
    })
    @SelectKey(statement = "select LAST_INSERT_ID()",
            keyProperty = "id",
            resultType = Integer.class,
            before = false
    )
    int insert(TableAlgorithm record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithm
     *
     * @mbg.generated
     */
    @InsertProvider(type=TableAlgorithmSqlProvider.class, method="insertSelective")
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
        @Result(column="ModuleID", property="moduleid", jdbcType=JdbcType.INTEGER),
        @Result(column="AlgorithmName", property="algorithmname", jdbcType=JdbcType.VARCHAR),
        @Result(column="AlgorithmAuthor", property="algorithmauthor", jdbcType=JdbcType.VARCHAR),
        @Result(column="IsPublic", property="ispublic", jdbcType=JdbcType.DECIMAL),
        @Result(column="AlgorithmType", property="algorithmtype", jdbcType=JdbcType.DECIMAL),
        @Result(column="AlgorithmFun", property="algorithmfun", jdbcType=JdbcType.VARCHAR),
        @Result(column="Des", property="des", jdbcType=JdbcType.VARCHAR),
        @Result(column="Remark", property="remark", jdbcType=JdbcType.VARCHAR)
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
        "ID, ModuleID, AlgorithmName, AlgorithmAuthor, IsPublic, AlgorithmType, AlgorithmFun, ",
        "Des, Remark",
        "from table_algorithm",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ModuleID", property="moduleid", jdbcType=JdbcType.INTEGER),
        @Result(column="AlgorithmName", property="algorithmname", jdbcType=JdbcType.VARCHAR),
        @Result(column="AlgorithmAuthor", property="algorithmauthor", jdbcType=JdbcType.VARCHAR),
        @Result(column="IsPublic", property="ispublic", jdbcType=JdbcType.DECIMAL),
        @Result(column="AlgorithmType", property="algorithmtype", jdbcType=JdbcType.DECIMAL),
        @Result(column="AlgorithmFun", property="algorithmfun", jdbcType=JdbcType.VARCHAR),
        @Result(column="Des", property="des", jdbcType=JdbcType.VARCHAR),
        @Result(column="Remark", property="remark", jdbcType=JdbcType.VARCHAR)
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
        "set ModuleID = #{moduleid,jdbcType=INTEGER},",
          "AlgorithmName = #{algorithmname,jdbcType=VARCHAR},",
          "AlgorithmAuthor = #{algorithmauthor,jdbcType=VARCHAR},",
          "IsPublic = #{ispublic,jdbcType=DECIMAL},",
          "AlgorithmType = #{algorithmtype,jdbcType=DECIMAL},",
          "AlgorithmFun = #{algorithmfun,jdbcType=VARCHAR},",
          "Des = #{des,jdbcType=VARCHAR},",
          "Remark = #{remark,jdbcType=VARCHAR}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TableAlgorithm record);
}