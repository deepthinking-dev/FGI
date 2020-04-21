package deepthinking.fgi.dao.mapper;

import deepthinking.fgi.domain.TableModulefield;
import deepthinking.fgi.domain.TableModulefieldCriteria.Criteria;
import deepthinking.fgi.domain.TableModulefieldCriteria.Criterion;
import deepthinking.fgi.domain.TableModulefieldCriteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class TableModulefieldSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_modulefield
     *
     * @mbg.generated
     */
    public String countByExample(TableModulefieldCriteria example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("table_modulefield");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_modulefield
     *
     * @mbg.generated
     */
    public String deleteByExample(TableModulefieldCriteria example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("table_modulefield");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_modulefield
     *
     * @mbg.generated
     */
    public String insertSelective(TableModulefield record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("table_modulefield");
        
        if (record.getModuleid() != null) {
            sql.VALUES("ModuleID", "#{moduleid,jdbcType=INTEGER}");
        }
        
        if (record.getFieldname() != null) {
            sql.VALUES("FieldName", "#{fieldname,jdbcType=VARCHAR}");
        }
        
        if (record.getEnglishname() != null) {
            sql.VALUES("EnglishName", "#{englishname,jdbcType=VARCHAR}");
        }
        
        if (record.getFieldtype() != null) {
            sql.VALUES("FieldType", "#{fieldtype,jdbcType=VARCHAR}");
        }
        
        if (record.getTablename() != null) {
            sql.VALUES("TableName", "#{tablename,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.VALUES("Remark", "#{remark,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_modulefield
     *
     * @mbg.generated
     */
    public String selectByExample(TableModulefieldCriteria example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("ID");
        } else {
            sql.SELECT("ID");
        }
        sql.SELECT("ModuleID");
        sql.SELECT("FieldName");
        sql.SELECT("EnglishName");
        sql.SELECT("FieldType");
        sql.SELECT("TableName");
        sql.SELECT("Remark");
        sql.FROM("table_modulefield");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_modulefield
     *
     * @mbg.generated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        TableModulefield record = (TableModulefield) parameter.get("record");
        TableModulefieldCriteria example = (TableModulefieldCriteria) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("table_modulefield");
        
        if (record.getId() != null) {
            sql.SET("ID = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getModuleid() != null) {
            sql.SET("ModuleID = #{record.moduleid,jdbcType=INTEGER}");
        }
        
        if (record.getFieldname() != null) {
            sql.SET("FieldName = #{record.fieldname,jdbcType=VARCHAR}");
        }
        
        if (record.getEnglishname() != null) {
            sql.SET("EnglishName = #{record.englishname,jdbcType=VARCHAR}");
        }
        
        if (record.getFieldtype() != null) {
            sql.SET("FieldType = #{record.fieldtype,jdbcType=VARCHAR}");
        }
        
        if (record.getTablename() != null) {
            sql.SET("TableName = #{record.tablename,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("Remark = #{record.remark,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_modulefield
     *
     * @mbg.generated
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("table_modulefield");
        
        sql.SET("ID = #{record.id,jdbcType=INTEGER}");
        sql.SET("ModuleID = #{record.moduleid,jdbcType=INTEGER}");
        sql.SET("FieldName = #{record.fieldname,jdbcType=VARCHAR}");
        sql.SET("EnglishName = #{record.englishname,jdbcType=VARCHAR}");
        sql.SET("FieldType = #{record.fieldtype,jdbcType=VARCHAR}");
        sql.SET("TableName = #{record.tablename,jdbcType=VARCHAR}");
        sql.SET("Remark = #{record.remark,jdbcType=VARCHAR}");
        
        TableModulefieldCriteria example = (TableModulefieldCriteria) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_modulefield
     *
     * @mbg.generated
     */
    public String updateByPrimaryKeySelective(TableModulefield record) {
        SQL sql = new SQL();
        sql.UPDATE("table_modulefield");
        
        if (record.getModuleid() != null) {
            sql.SET("ModuleID = #{moduleid,jdbcType=INTEGER}");
        }
        
        if (record.getFieldname() != null) {
            sql.SET("FieldName = #{fieldname,jdbcType=VARCHAR}");
        }
        
        if (record.getEnglishname() != null) {
            sql.SET("EnglishName = #{englishname,jdbcType=VARCHAR}");
        }
        
        if (record.getFieldtype() != null) {
            sql.SET("FieldType = #{fieldtype,jdbcType=VARCHAR}");
        }
        
        if (record.getTablename() != null) {
            sql.SET("TableName = #{tablename,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("Remark = #{remark,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("ID = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_modulefield
     *
     * @mbg.generated
     */
    protected void applyWhere(SQL sql, TableModulefieldCriteria example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}