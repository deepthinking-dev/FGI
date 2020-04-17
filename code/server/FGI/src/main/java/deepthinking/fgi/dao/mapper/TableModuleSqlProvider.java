package deepthinking.fgi.dao.mapper;

import java.util.List;
import java.util.Map;

import deepthinking.fgi.domain.TableModule;
import deepthinking.fgi.domain.TableModuleCriteria;
import deepthinking.fgi.domain.TableModuleCriteria.Criterion;
import deepthinking.fgi.domain.TableModuleCriteria.Criteria;
import org.apache.ibatis.jdbc.SQL;

public class TableModuleSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_module
     *
     * @mbg.generated
     */
    public String countByExample(TableModuleCriteria example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("table_module");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_module
     *
     * @mbg.generated
     */
    public String deleteByExample(TableModuleCriteria example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("table_module");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_module
     *
     * @mbg.generated
     */
    public String insertSelective(TableModule record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("table_module");
        
        if (record.getModulename() != null) {
            sql.VALUES("ModuleName", "#{modulename,jdbcType=VARCHAR}");
        }
        
        if (record.getSqlurl() != null) {
            sql.VALUES("SqlUrl", "#{sqlurl,jdbcType=VARCHAR}");
        }
        
        if (record.getModulegroup() != null) {
            sql.VALUES("ModuleGroup", "#{modulegroup,jdbcType=VARCHAR}");
        }
        
        if (record.getDes() != null) {
            sql.VALUES("Des", "#{des,jdbcType=VARCHAR}");
        }
        
        if (record.getUserid() != null) {
            sql.VALUES("UserID", "#{userid,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("Status", "#{status,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.VALUES("Remark", "#{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark2() != null) {
            sql.VALUES("Remark2", "#{remark2,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_module
     *
     * @mbg.generated
     */
    public String selectByExample(TableModuleCriteria example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("ID");
        } else {
            sql.SELECT("ID");
        }
        sql.SELECT("ModuleName");
        sql.SELECT("SqlUrl");
        sql.SELECT("ModuleGroup");
        sql.SELECT("Des");
        sql.SELECT("UserID");
        sql.SELECT("Status");
        sql.SELECT("Remark");
        sql.SELECT("Remark2");
        sql.FROM("table_module");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_module
     *
     * @mbg.generated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        TableModule record = (TableModule) parameter.get("record");
        TableModuleCriteria example = (TableModuleCriteria) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("table_module");
        
        if (record.getId() != null) {
            sql.SET("ID = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getModulename() != null) {
            sql.SET("ModuleName = #{record.modulename,jdbcType=VARCHAR}");
        }
        
        if (record.getSqlurl() != null) {
            sql.SET("SqlUrl = #{record.sqlurl,jdbcType=VARCHAR}");
        }
        
        if (record.getModulegroup() != null) {
            sql.SET("ModuleGroup = #{record.modulegroup,jdbcType=VARCHAR}");
        }
        
        if (record.getDes() != null) {
            sql.SET("Des = #{record.des,jdbcType=VARCHAR}");
        }
        
        if (record.getUserid() != null) {
            sql.SET("UserID = #{record.userid,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("Status = #{record.status,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("Remark = #{record.remark,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark2() != null) {
            sql.SET("Remark2 = #{record.remark2,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_module
     *
     * @mbg.generated
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("table_module");
        
        sql.SET("ID = #{record.id,jdbcType=INTEGER}");
        sql.SET("ModuleName = #{record.modulename,jdbcType=VARCHAR}");
        sql.SET("SqlUrl = #{record.sqlurl,jdbcType=VARCHAR}");
        sql.SET("ModuleGroup = #{record.modulegroup,jdbcType=VARCHAR}");
        sql.SET("Des = #{record.des,jdbcType=VARCHAR}");
        sql.SET("UserID = #{record.userid,jdbcType=INTEGER}");
        sql.SET("Status = #{record.status,jdbcType=VARCHAR}");
        sql.SET("Remark = #{record.remark,jdbcType=VARCHAR}");
        sql.SET("Remark2 = #{record.remark2,jdbcType=VARCHAR}");
        
        TableModuleCriteria example = (TableModuleCriteria) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_module
     *
     * @mbg.generated
     */
    public String updateByPrimaryKeySelective(TableModule record) {
        SQL sql = new SQL();
        sql.UPDATE("table_module");
        
        if (record.getModulename() != null) {
            sql.SET("ModuleName = #{modulename,jdbcType=VARCHAR}");
        }
        
        if (record.getSqlurl() != null) {
            sql.SET("SqlUrl = #{sqlurl,jdbcType=VARCHAR}");
        }
        
        if (record.getModulegroup() != null) {
            sql.SET("ModuleGroup = #{modulegroup,jdbcType=VARCHAR}");
        }
        
        if (record.getDes() != null) {
            sql.SET("Des = #{des,jdbcType=VARCHAR}");
        }
        
        if (record.getUserid() != null) {
            sql.SET("UserID = #{userid,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("Status = #{status,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("Remark = #{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark2() != null) {
            sql.SET("Remark2 = #{remark2,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("ID = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_module
     *
     * @mbg.generated
     */
    protected void applyWhere(SQL sql, TableModuleCriteria example, boolean includeExamplePhrase) {
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