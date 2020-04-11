package deepthinking.fgi.dao.mapper;

import deepthinking.fgi.domain.TableAlgorithmcondition;
import deepthinking.fgi.domain.TableAlgorithmconditionCriteria;
import java.util.List;
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

public interface TableAlgorithmconditionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    @SelectProvider(type=TableAlgorithmconditionSqlProvider.class, method="countByExample")
    long countByExample(TableAlgorithmconditionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    @DeleteProvider(type=TableAlgorithmconditionSqlProvider.class, method="deleteByExample")
    int deleteByExample(TableAlgorithmconditionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    @Delete({
        "delete from table_algorithmcondition",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    @Insert({
        "insert into table_algorithmcondition (InterfaceRoleID, InterfaceParametersID, ",
        "Behavior, ValueSources, ",
        "expression, Remark)",
        "values (#{interfaceroleid,jdbcType=INTEGER}, #{interfaceparametersid,jdbcType=VARCHAR}, ",
        "#{behavior,jdbcType=VARCHAR}, #{valuesources,jdbcType=INTEGER}, ",
        "#{expression,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(TableAlgorithmcondition record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    @InsertProvider(type=TableAlgorithmconditionSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(TableAlgorithmcondition record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    @SelectProvider(type=TableAlgorithmconditionSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="InterfaceRoleID", property="interfaceroleid", jdbcType=JdbcType.INTEGER),
        @Result(column="InterfaceParametersID", property="interfaceparametersid", jdbcType=JdbcType.VARCHAR),
        @Result(column="Behavior", property="behavior", jdbcType=JdbcType.VARCHAR),
        @Result(column="ValueSources", property="valuesources", jdbcType=JdbcType.INTEGER),
        @Result(column="expression", property="expression", jdbcType=JdbcType.VARCHAR),
        @Result(column="Remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<TableAlgorithmcondition> selectByExample(TableAlgorithmconditionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "ID, InterfaceRoleID, InterfaceParametersID, Behavior, ValueSources, expression, ",
        "Remark",
        "from table_algorithmcondition",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="InterfaceRoleID", property="interfaceroleid", jdbcType=JdbcType.INTEGER),
        @Result(column="InterfaceParametersID", property="interfaceparametersid", jdbcType=JdbcType.VARCHAR),
        @Result(column="Behavior", property="behavior", jdbcType=JdbcType.VARCHAR),
        @Result(column="ValueSources", property="valuesources", jdbcType=JdbcType.INTEGER),
        @Result(column="expression", property="expression", jdbcType=JdbcType.VARCHAR),
        @Result(column="Remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    TableAlgorithmcondition selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TableAlgorithmconditionSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TableAlgorithmcondition record, @Param("example") TableAlgorithmconditionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TableAlgorithmconditionSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TableAlgorithmcondition record, @Param("example") TableAlgorithmconditionCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TableAlgorithmconditionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TableAlgorithmcondition record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    @Update({
        "update table_algorithmcondition",
        "set InterfaceRoleID = #{interfaceroleid,jdbcType=INTEGER},",
          "InterfaceParametersID = #{interfaceparametersid,jdbcType=VARCHAR},",
          "Behavior = #{behavior,jdbcType=VARCHAR},",
          "ValueSources = #{valuesources,jdbcType=INTEGER},",
          "expression = #{expression,jdbcType=VARCHAR},",
          "Remark = #{remark,jdbcType=VARCHAR}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TableAlgorithmcondition record);
}