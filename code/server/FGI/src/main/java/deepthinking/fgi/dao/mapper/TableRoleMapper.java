package deepthinking.fgi.dao.mapper;

import deepthinking.fgi.domain.TableRole;
import deepthinking.fgi.domain.TableRoleCriteria;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface TableRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_role
     *
     * @mbg.generated
     */
    @SelectProvider(type=TableRoleSqlProvider.class, method="countByExample")
    long countByExample(TableRoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_role
     *
     * @mbg.generated
     */
    @DeleteProvider(type=TableRoleSqlProvider.class, method="deleteByExample")
    int deleteByExample(TableRoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_role
     *
     * @mbg.generated
     */
    @Delete({
        "delete from table_role",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_role
     *
     * @mbg.generated
     */
    @Insert({
        "insert into table_role (ID, RoleName, ",
        "Des, Remark, EntranceNote, ",
        "coordinate)",
        "values (#{id,jdbcType=INTEGER}, #{rolename,jdbcType=VARCHAR}, ",
        "#{des,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, #{entrancenote,jdbcType=VARCHAR}, ",
        "#{coordinate,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(TableRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_role
     *
     * @mbg.generated
     */
    @InsertProvider(type=TableRoleSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(TableRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_role
     *
     * @mbg.generated
     */
    @SelectProvider(type=TableRoleSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="RoleName", property="rolename", jdbcType=JdbcType.VARCHAR),
        @Result(column="Des", property="des", jdbcType=JdbcType.VARCHAR),
        @Result(column="Remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="EntranceNote", property="entrancenote", jdbcType=JdbcType.VARCHAR),
        @Result(column="coordinate", property="coordinate", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<TableRole> selectByExampleWithBLOBs(TableRoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_role
     *
     * @mbg.generated
     */
    @SelectProvider(type=TableRoleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="RoleName", property="rolename", jdbcType=JdbcType.VARCHAR),
        @Result(column="Des", property="des", jdbcType=JdbcType.VARCHAR),
        @Result(column="Remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="EntranceNote", property="entrancenote", jdbcType=JdbcType.VARCHAR)
    })
    List<TableRole> selectByExample(TableRoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_role
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "ID, RoleName, Des, Remark, EntranceNote, coordinate",
        "from table_role",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="RoleName", property="rolename", jdbcType=JdbcType.VARCHAR),
        @Result(column="Des", property="des", jdbcType=JdbcType.VARCHAR),
        @Result(column="Remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="EntranceNote", property="entrancenote", jdbcType=JdbcType.VARCHAR),
        @Result(column="coordinate", property="coordinate", jdbcType=JdbcType.LONGVARCHAR)
    })
    TableRole selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_role
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TableRoleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TableRole record, @Param("example") TableRoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_role
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TableRoleSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") TableRole record, @Param("example") TableRoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_role
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TableRoleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TableRole record, @Param("example") TableRoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_role
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TableRoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TableRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_role
     *
     * @mbg.generated
     */
    @Update({
        "update table_role",
        "set RoleName = #{rolename,jdbcType=VARCHAR},",
          "Des = #{des,jdbcType=VARCHAR},",
          "Remark = #{remark,jdbcType=VARCHAR},",
          "EntranceNote = #{entrancenote,jdbcType=VARCHAR},",
          "coordinate = #{coordinate,jdbcType=LONGVARCHAR}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(TableRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_role
     *
     * @mbg.generated
     */
    @Update({
        "update table_role",
        "set RoleName = #{rolename,jdbcType=VARCHAR},",
          "Des = #{des,jdbcType=VARCHAR},",
          "Remark = #{remark,jdbcType=VARCHAR},",
          "EntranceNote = #{entrancenote,jdbcType=VARCHAR}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TableRole record);
}