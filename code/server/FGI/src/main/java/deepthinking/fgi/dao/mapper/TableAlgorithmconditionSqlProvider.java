package deepthinking.fgi.dao.mapper;

import deepthinking.fgi.domain.TableAlgorithmcondition;
import deepthinking.fgi.domain.TableAlgorithmconditionCriteria.Criteria;
import deepthinking.fgi.domain.TableAlgorithmconditionCriteria.Criterion;
import deepthinking.fgi.domain.TableAlgorithmconditionCriteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class TableAlgorithmconditionSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    public String countByExample(TableAlgorithmconditionCriteria example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("table_algorithmcondition");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    public String deleteByExample(TableAlgorithmconditionCriteria example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("table_algorithmcondition");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    public String insertSelective(TableAlgorithmcondition record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("table_algorithmcondition");
        
        if (record.getId() != null) {
            sql.VALUES("ID", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getAlgorithmroleid() != null) {
            sql.VALUES("AlgorithmRoleID", "#{algorithmroleid,jdbcType=INTEGER}");
        }
        
        if (record.getFuncid() != null) {
            sql.VALUES("FuncID", "#{funcid,jdbcType=INTEGER}");
        }
        
        if (record.getBehavior() != null) {
            sql.VALUES("Behavior", "#{behavior,jdbcType=VARCHAR}");
        }
        
        if (record.getValuesources() != null) {
            sql.VALUES("ValueSources", "#{valuesources,jdbcType=DECIMAL}");
        }
        
        if (record.getExpression() != null) {
            sql.VALUES("expression", "#{expression,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.VALUES("Remark", "#{remark,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    public String selectByExample(TableAlgorithmconditionCriteria example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("ID");
        } else {
            sql.SELECT("ID");
        }
        sql.SELECT("AlgorithmRoleID");
        sql.SELECT("FuncID");
        sql.SELECT("Behavior");
        sql.SELECT("ValueSources");
        sql.SELECT("expression");
        sql.SELECT("Remark");
        sql.FROM("table_algorithmcondition");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        TableAlgorithmcondition record = (TableAlgorithmcondition) parameter.get("record");
        TableAlgorithmconditionCriteria example = (TableAlgorithmconditionCriteria) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("table_algorithmcondition");
        
        if (record.getId() != null) {
            sql.SET("ID = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getAlgorithmroleid() != null) {
            sql.SET("AlgorithmRoleID = #{record.algorithmroleid,jdbcType=INTEGER}");
        }
        
        if (record.getFuncid() != null) {
            sql.SET("FuncID = #{record.funcid,jdbcType=INTEGER}");
        }
        
        if (record.getBehavior() != null) {
            sql.SET("Behavior = #{record.behavior,jdbcType=VARCHAR}");
        }
        
        if (record.getValuesources() != null) {
            sql.SET("ValueSources = #{record.valuesources,jdbcType=DECIMAL}");
        }
        
        if (record.getExpression() != null) {
            sql.SET("expression = #{record.expression,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("Remark = #{record.remark,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("table_algorithmcondition");
        
        sql.SET("ID = #{record.id,jdbcType=INTEGER}");
        sql.SET("AlgorithmRoleID = #{record.algorithmroleid,jdbcType=INTEGER}");
        sql.SET("FuncID = #{record.funcid,jdbcType=INTEGER}");
        sql.SET("Behavior = #{record.behavior,jdbcType=VARCHAR}");
        sql.SET("ValueSources = #{record.valuesources,jdbcType=DECIMAL}");
        sql.SET("expression = #{record.expression,jdbcType=VARCHAR}");
        sql.SET("Remark = #{record.remark,jdbcType=VARCHAR}");
        
        TableAlgorithmconditionCriteria example = (TableAlgorithmconditionCriteria) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    public String updateByPrimaryKeySelective(TableAlgorithmcondition record) {
        SQL sql = new SQL();
        sql.UPDATE("table_algorithmcondition");
        
        if (record.getAlgorithmroleid() != null) {
            sql.SET("AlgorithmRoleID = #{algorithmroleid,jdbcType=INTEGER}");
        }
        
        if (record.getFuncid() != null) {
            sql.SET("FuncID = #{funcid,jdbcType=INTEGER}");
        }
        
        if (record.getBehavior() != null) {
            sql.SET("Behavior = #{behavior,jdbcType=VARCHAR}");
        }
        
        if (record.getValuesources() != null) {
            sql.SET("ValueSources = #{valuesources,jdbcType=DECIMAL}");
        }
        
        if (record.getExpression() != null) {
            sql.SET("expression = #{expression,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("Remark = #{remark,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("ID = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    protected void applyWhere(SQL sql, TableAlgorithmconditionCriteria example, boolean includeExamplePhrase) {
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