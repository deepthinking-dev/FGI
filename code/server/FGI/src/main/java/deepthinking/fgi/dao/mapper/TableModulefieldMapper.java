package deepthinking.fgi.dao.mapper;

import deepthinking.fgi.domain.TableModulefield;
import deepthinking.fgi.domain.TableModulefieldCriteria;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface TableModulefieldMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_modulefield
     *
     * @mbg.generated
     */
    @SelectProvider(type=TableModulefieldSqlProvider.class, method="countByExample")
    long countByExample(TableModulefieldCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_modulefield
     *
     * @mbg.generated
     */
    @DeleteProvider(type=TableModulefieldSqlProvider.class, method="deleteByExample")
    int deleteByExample(TableModulefieldCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_modulefield
     *
     * @mbg.generated
     */
    @Delete({
        "delete from table_modulefield",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_modulefield
     *
     * @mbg.generated
     */
    @Insert({
        "insert into table_modulefield (ID, ModuleID, ",
        "FieldName, FieldType, ",
        "TableName, Remark)",
        "values (#{id,jdbcType=INTEGER}, #{moduleid,jdbcType=INTEGER}, ",
        "#{fieldname,jdbcType=VARCHAR}, #{fieldtype,jdbcType=VARCHAR}, ",
        "#{tablename,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(TableModulefield record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_modulefield
     *
     * @mbg.generated
     */
    @InsertProvider(type=TableModulefieldSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(TableModulefield record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_modulefield
     *
     * @mbg.generated
     */
    @SelectProvider(type=TableModulefieldSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ModuleID", property="moduleid", jdbcType=JdbcType.INTEGER),
        @Result(column="FieldName", property="fieldname", jdbcType=JdbcType.VARCHAR),
        @Result(column="FieldType", property="fieldtype", jdbcType=JdbcType.VARCHAR),
        @Result(column="TableName", property="tablename", jdbcType=JdbcType.VARCHAR),
        @Result(column="Remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<TableModulefield> selectByExample(TableModulefieldCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_modulefield
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "ID, ModuleID, FieldName, FieldType, TableName, Remark",
        "from table_modulefield",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ModuleID", property="moduleid", jdbcType=JdbcType.INTEGER),
        @Result(column="FieldName", property="fieldname", jdbcType=JdbcType.VARCHAR),
        @Result(column="FieldType", property="fieldtype", jdbcType=JdbcType.VARCHAR),
        @Result(column="TableName", property="tablename", jdbcType=JdbcType.VARCHAR),
        @Result(column="Remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    TableModulefield selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_modulefield
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TableModulefieldSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TableModulefield record, @Param("example") TableModulefieldCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_modulefield
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TableModulefieldSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TableModulefield record, @Param("example") TableModulefieldCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_modulefield
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TableModulefieldSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TableModulefield record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_modulefield
     *
     * @mbg.generated
     */
    @Update({
        "update table_modulefield",
        "set ModuleID = #{moduleid,jdbcType=INTEGER},",
          "FieldName = #{fieldname,jdbcType=VARCHAR},",
          "FieldType = #{fieldtype,jdbcType=VARCHAR},",
          "TableName = #{tablename,jdbcType=VARCHAR},",
          "Remark = #{remark,jdbcType=VARCHAR}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TableModulefield record);
}