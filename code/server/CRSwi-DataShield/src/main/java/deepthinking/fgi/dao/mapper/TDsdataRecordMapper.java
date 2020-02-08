package deepthinking.fgi.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import deepthinking.fgi.domain.TDsdataRecord;
import deepthinking.fgi.domain.TDsdataRecordCriteria;
import deepthinking.fgi.dto.frontend.RepDataFileMemDto;
import deepthinking.fgi.dto.frontend.ReqDataFileMemDto;

public interface TDsdataRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dsdata_record
     *
     * @mbg.generated
     */
    @SelectProvider(type=TDsdataRecordSqlProvider.class, method="countByExample")
    long countByExample(TDsdataRecordCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dsdata_record
     *
     * @mbg.generated
     */
    @DeleteProvider(type=TDsdataRecordSqlProvider.class, method="deleteByExample")
    int deleteByExample(TDsdataRecordCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dsdata_record
     *
     * @mbg.generated
     */
    @Delete({
        "delete from t_dsdata_record",
        "where datafile_code = #{datafileCode,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer datafileCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dsdata_record
     *
     * @mbg.generated
     */
    @Insert({
        "insert into t_dsdata_record (datafile_code, order_code, ",
        "datafile_name, datafile_time, ",
        "data_consistency, data_url,datafile_size)",
        "values (#{datafileCode,jdbcType=INTEGER}, #{orderCode,jdbcType=INTEGER}, ",
        "#{datafileName,jdbcType=VARCHAR}, #{datafileTime,jdbcType=DATE}, ",
        "#{dataConsistency,jdbcType=INTEGER}, #{dataUrl,jdbcType=VARCHAR}, #{datafileSize,jdbcType=DECIMAL})"
    })
    int insert(TDsdataRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dsdata_record
     *
     * @mbg.generated
     */
    @InsertProvider(type=TDsdataRecordSqlProvider.class, method="insertSelective")
    int insertSelective(TDsdataRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dsdata_record
     *
     * @mbg.generated
     */
    @SelectProvider(type=TDsdataRecordSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="datafile_code", property="datafileCode", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="order_code", property="orderCode", jdbcType=JdbcType.INTEGER),
        @Result(column="datafile_name", property="datafileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="datafile_time", property="datafileTime", jdbcType=JdbcType.DATE),
        @Result(column="data_consistency", property="dataConsistency", jdbcType=JdbcType.INTEGER),
        @Result(column="data_url", property="dataUrl", jdbcType=JdbcType.VARCHAR)
    })
    List<TDsdataRecord> selectByExample(TDsdataRecordCriteria example);

    
    @SelectProvider(type=TDsdataRecordSqlProvider.class, method="selectDataRecords")
    @Results({
        @Result(column="datafile_code", property="datafileCode", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="order_code", property="orderCode", jdbcType=JdbcType.INTEGER),
        @Result(column="datafile_name", property="datafileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="datafile_time", property="datafileTime", jdbcType=JdbcType.DATE),
        @Result(column="data_consistency", property="dataConsistency", jdbcType=JdbcType.INTEGER),
        @Result(column="data_url", property="dataUrl", jdbcType=JdbcType.VARCHAR)
    })
    List<TDsdataRecord> selectDataRecords(Object queryParams);
    
    @SelectProvider(type=TDsdataRecordSqlProvider.class, method="selectDataMemRecords")
    @Results({
        @Result(column="datafile_code", property="datafileCode", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="order_code", property="orderCode", jdbcType=JdbcType.INTEGER),
        @Result(column="datafile_name", property="datafileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="datafile_time", property="datafileTime", jdbcType=JdbcType.DATE),
        @Result(column="data_consistency", property="dataConsistency", jdbcType=JdbcType.INTEGER),
        @Result(column="data_url", property="dataUrl", jdbcType=JdbcType.VARCHAR)
    })
    List<TDsdataRecord> selectDataMemRecords(Object queryParams);
    
    
    @SelectProvider(type=TDsdataRecordSqlProvider.class, method="selectDataFileMemRecords")
    @Results({
        @Result(column="order_code", property="orderCode", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="orgnization_name", property="orgnizationName", jdbcType=JdbcType.VARCHAR),
        @Result(column="staff_name", property="inspectorName", jdbcType=JdbcType.VARCHAR),
        @Result(column="work_date", property="workDate", jdbcType=JdbcType.DATE),
        @Result(column="inspection_order_no", property="inspectionOrderNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="datafile_mem", property="memory", jdbcType=JdbcType.BIGINT)
    })
    List<RepDataFileMemDto> selectDataFileMemRecords(ReqDataFileMemDto dataFileMemDto);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dsdata_record
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "datafile_code, order_code, datafile_name, datafile_time, data_consistency, data_url",
        "from t_dsdata_record",
        "where datafile_code = #{datafileCode,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="datafile_code", property="datafileCode", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="order_code", property="orderCode", jdbcType=JdbcType.INTEGER),
        @Result(column="datafile_name", property="datafileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="datafile_time", property="datafileTime", jdbcType=JdbcType.DATE),
        @Result(column="data_consistency", property="dataConsistency", jdbcType=JdbcType.INTEGER),
        @Result(column="data_url", property="dataUrl", jdbcType=JdbcType.VARCHAR)
    })
    TDsdataRecord selectByPrimaryKey(Integer datafileCode);

    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dsdata_record
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TDsdataRecordSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TDsdataRecord record, @Param("example") TDsdataRecordCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dsdata_record
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TDsdataRecordSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TDsdataRecord record, @Param("example") TDsdataRecordCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dsdata_record
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TDsdataRecordSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TDsdataRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dsdata_record
     *
     * @mbg.generated
     */
    @Update({
        "update t_dsdata_record",
        "set order_code = #{orderCode,jdbcType=INTEGER},",
          "datafile_name = #{datafileName,jdbcType=VARCHAR},",
          "datafile_time = #{datafileTime,jdbcType=DATE},",
          "data_consistency = #{dataConsistency,jdbcType=INTEGER},",
          "data_url = #{dataUrl,jdbcType=VARCHAR}",
        "where datafile_code = #{datafileCode,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TDsdataRecord record);
}