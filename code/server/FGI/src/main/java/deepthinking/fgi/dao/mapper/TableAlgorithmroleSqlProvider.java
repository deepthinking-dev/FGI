package deepthinking.fgi.dao.mapper;

import deepthinking.fgi.domain.TableAlgorithmrole;
import deepthinking.fgi.domain.TableAlgorithmroleCriteria.Criteria;
import deepthinking.fgi.domain.TableAlgorithmroleCriteria.Criterion;
import deepthinking.fgi.domain.TableAlgorithmroleCriteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class TableAlgorithmroleSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmrole
     *
     * @mbg.generated
     */
    public String countByExample(TableAlgorithmroleCriteria example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("table_algorithmrole");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmrole
     *
     * @mbg.generated
     */
    public String deleteByExample(TableAlgorithmroleCriteria example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("table_algorithmrole");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmrole
     *
     * @mbg.generated
     */
    public String insertSelective(TableAlgorithmrole record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("table_algorithmrole");
        
        if (record.getId() != null) {
            sql.VALUES("ID", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getRoleid() != null) {
            sql.VALUES("RoleID", "#{roleid,jdbcType=INTEGER}");
        }
        
        if (record.getAlgorithmid() != null) {
            sql.VALUES("AlgorithmID", "#{algorithmid,jdbcType=INTEGER}");
        }
        
        if (record.getPrealgorithmid() != null) {
            sql.VALUES("PreAlgorithmID", "#{prealgorithmid,jdbcType=INTEGER}");
        }
        
        if (record.getDes() != null) {
            sql.VALUES("Des", "#{des,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.VALUES("Remark", "#{remark,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmrole
     *
     * @mbg.generated
     */
    public String selectByExample(TableAlgorithmroleCriteria example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("ID");
        } else {
            sql.SELECT("ID");
        }
        sql.SELECT("RoleID");
        sql.SELECT("AlgorithmID");
        sql.SELECT("PreAlgorithmID");
        sql.SELECT("Des");
        sql.SELECT("Remark");
        sql.FROM("table_algorithmrole");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmrole
     *
     * @mbg.generated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        TableAlgorithmrole record = (TableAlgorithmrole) parameter.get("record");
        TableAlgorithmroleCriteria example = (TableAlgorithmroleCriteria) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("table_algorithmrole");
        
        if (record.getId() != null) {
            sql.SET("ID = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getRoleid() != null) {
            sql.SET("RoleID = #{record.roleid,jdbcType=INTEGER}");
        }
        
        if (record.getAlgorithmid() != null) {
            sql.SET("AlgorithmID = #{record.algorithmid,jdbcType=INTEGER}");
        }
        
        if (record.getPrealgorithmid() != null) {
            sql.SET("PreAlgorithmID = #{record.prealgorithmid,jdbcType=INTEGER}");
        }
        
        if (record.getDes() != null) {
            sql.SET("Des = #{record.des,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("Remark = #{record.remark,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmrole
     *
     * @mbg.generated
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("table_algorithmrole");
        
        sql.SET("ID = #{record.id,jdbcType=INTEGER}");
        sql.SET("RoleID = #{record.roleid,jdbcType=INTEGER}");
        sql.SET("AlgorithmID = #{record.algorithmid,jdbcType=INTEGER}");
        sql.SET("PreAlgorithmID = #{record.prealgorithmid,jdbcType=INTEGER}");
        sql.SET("Des = #{record.des,jdbcType=VARCHAR}");
        sql.SET("Remark = #{record.remark,jdbcType=VARCHAR}");
        
        TableAlgorithmroleCriteria example = (TableAlgorithmroleCriteria) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmrole
     *
     * @mbg.generated
     */
    public String updateByPrimaryKeySelective(TableAlgorithmrole record) {
        SQL sql = new SQL();
        sql.UPDATE("table_algorithmrole");
        
        if (record.getRoleid() != null) {
            sql.SET("RoleID = #{roleid,jdbcType=INTEGER}");
        }
        
        if (record.getAlgorithmid() != null) {
            sql.SET("AlgorithmID = #{algorithmid,jdbcType=INTEGER}");
        }
        
        if (record.getPrealgorithmid() != null) {
            sql.SET("PreAlgorithmID = #{prealgorithmid,jdbcType=INTEGER}");
        }
        
        if (record.getDes() != null) {
            sql.SET("Des = #{des,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("Remark = #{remark,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("ID = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmrole
     *
     * @mbg.generated
     */
    protected void applyWhere(SQL sql, TableAlgorithmroleCriteria example, boolean includeExamplePhrase) {
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