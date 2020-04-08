package deepthinking.fgi.dao.mapper;

import java.util.List;

import deepthinking.fgi.domain.TableInterfaceparameters;
import deepthinking.fgi.domain.TableInterfaceparametersCriteria;
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

public interface TableInterfaceparametersMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfaceparameters
     *
     * @mbg.generated
     */
    @SelectProvider(type=TableInterfaceparametersSqlProvider.class, method="countByExample")
    long countByExample(TableInterfaceparametersCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfaceparameters
     *
     * @mbg.generated
     */
    @DeleteProvider(type=TableInterfaceparametersSqlProvider.class, method="deleteByExample")
    int deleteByExample(TableInterfaceparametersCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfaceparameters
     *
     * @mbg.generated
     */
    @Delete({
        "delete from table_interfaceparameters",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfaceparameters
     *
     * @mbg.generated
     */
    @Insert({
        "insert into table_interfaceparameters (InterfaceID, ParametersSources, ",
        "ParametersName, inOrOut)",
        "values (#{interfaceid,jdbcType=INTEGER}, #{parameterssources,jdbcType=VARCHAR}, ",
        "#{parametersname,jdbcType=VARCHAR}, #{inorout,jdbcType=DECIMAL})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(TableInterfaceparameters record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfaceparameters
     *
     * @mbg.generated
     */
    @InsertProvider(type=TableInterfaceparametersSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(TableInterfaceparameters record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfaceparameters
     *
     * @mbg.generated
     */
    @SelectProvider(type=TableInterfaceparametersSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="InterfaceID", property="interfaceid", jdbcType=JdbcType.INTEGER),
        @Result(column="ParametersSources", property="parameterssources", jdbcType=JdbcType.VARCHAR),
        @Result(column="ParametersName", property="parametersname", jdbcType=JdbcType.VARCHAR),
        @Result(column="inOrOut", property="inorout", jdbcType=JdbcType.DECIMAL)
    })
    List<TableInterfaceparameters> selectByExample(TableInterfaceparametersCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfaceparameters
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "ID, InterfaceID, ParametersSources, ParametersName, inOrOut",
        "from table_interfaceparameters",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="InterfaceID", property="interfaceid", jdbcType=JdbcType.INTEGER),
        @Result(column="ParametersSources", property="parameterssources", jdbcType=JdbcType.VARCHAR),
        @Result(column="ParametersName", property="parametersname", jdbcType=JdbcType.VARCHAR),
        @Result(column="inOrOut", property="inorout", jdbcType=JdbcType.DECIMAL)
    })
    TableInterfaceparameters selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfaceparameters
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TableInterfaceparametersSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TableInterfaceparameters record, @Param("example") TableInterfaceparametersCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfaceparameters
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TableInterfaceparametersSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TableInterfaceparameters record, @Param("example") TableInterfaceparametersCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfaceparameters
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TableInterfaceparametersSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TableInterfaceparameters record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfaceparameters
     *
     * @mbg.generated
     */
    @Update({
        "update table_interfaceparameters",
        "set InterfaceID = #{interfaceid,jdbcType=INTEGER},",
          "ParametersSources = #{parameterssources,jdbcType=VARCHAR},",
          "ParametersName = #{parametersname,jdbcType=VARCHAR},",
          "inOrOut = #{inorout,jdbcType=DECIMAL}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TableInterfaceparameters record);
}