package deepthinking.fgi.domain;

import java.util.ArrayList;
import java.util.List;

public class TableAlgorithmCriteria {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table table_algorithm
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table table_algorithm
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table table_algorithm
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithm
     *
     * @mbg.generated
     */
    public TableAlgorithmCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithm
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithm
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithm
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithm
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithm
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithm
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithm
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithm
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithm
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithm
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table table_algorithm
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andModuleidIsNull() {
            addCriterion("ModuleID is null");
            return (Criteria) this;
        }

        public Criteria andModuleidIsNotNull() {
            addCriterion("ModuleID is not null");
            return (Criteria) this;
        }

        public Criteria andModuleidEqualTo(Integer value) {
            addCriterion("ModuleID =", value, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidNotEqualTo(Integer value) {
            addCriterion("ModuleID <>", value, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidGreaterThan(Integer value) {
            addCriterion("ModuleID >", value, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidGreaterThanOrEqualTo(Integer value) {
            addCriterion("ModuleID >=", value, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidLessThan(Integer value) {
            addCriterion("ModuleID <", value, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidLessThanOrEqualTo(Integer value) {
            addCriterion("ModuleID <=", value, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidIn(List<Integer> values) {
            addCriterion("ModuleID in", values, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidNotIn(List<Integer> values) {
            addCriterion("ModuleID not in", values, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidBetween(Integer value1, Integer value2) {
            addCriterion("ModuleID between", value1, value2, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidNotBetween(Integer value1, Integer value2) {
            addCriterion("ModuleID not between", value1, value2, "moduleid");
            return (Criteria) this;
        }

        public Criteria andAlgorithmnameIsNull() {
            addCriterion("AlgorithmName is null");
            return (Criteria) this;
        }

        public Criteria andAlgorithmnameIsNotNull() {
            addCriterion("AlgorithmName is not null");
            return (Criteria) this;
        }

        public Criteria andAlgorithmnameEqualTo(String value) {
            addCriterion("AlgorithmName =", value, "algorithmname");
            return (Criteria) this;
        }

        public Criteria andAlgorithmnameNotEqualTo(String value) {
            addCriterion("AlgorithmName <>", value, "algorithmname");
            return (Criteria) this;
        }

        public Criteria andAlgorithmnameGreaterThan(String value) {
            addCriterion("AlgorithmName >", value, "algorithmname");
            return (Criteria) this;
        }

        public Criteria andAlgorithmnameGreaterThanOrEqualTo(String value) {
            addCriterion("AlgorithmName >=", value, "algorithmname");
            return (Criteria) this;
        }

        public Criteria andAlgorithmnameLessThan(String value) {
            addCriterion("AlgorithmName <", value, "algorithmname");
            return (Criteria) this;
        }

        public Criteria andAlgorithmnameLessThanOrEqualTo(String value) {
            addCriterion("AlgorithmName <=", value, "algorithmname");
            return (Criteria) this;
        }

        public Criteria andAlgorithmnameLike(String value) {
            addCriterion("AlgorithmName like", value, "algorithmname");
            return (Criteria) this;
        }

        public Criteria andAlgorithmnameNotLike(String value) {
            addCriterion("AlgorithmName not like", value, "algorithmname");
            return (Criteria) this;
        }

        public Criteria andAlgorithmnameIn(List<String> values) {
            addCriterion("AlgorithmName in", values, "algorithmname");
            return (Criteria) this;
        }

        public Criteria andAlgorithmnameNotIn(List<String> values) {
            addCriterion("AlgorithmName not in", values, "algorithmname");
            return (Criteria) this;
        }

        public Criteria andAlgorithmnameBetween(String value1, String value2) {
            addCriterion("AlgorithmName between", value1, value2, "algorithmname");
            return (Criteria) this;
        }

        public Criteria andAlgorithmnameNotBetween(String value1, String value2) {
            addCriterion("AlgorithmName not between", value1, value2, "algorithmname");
            return (Criteria) this;
        }

        public Criteria andAlgorithmauthorIsNull() {
            addCriterion("AlgorithmAuthor is null");
            return (Criteria) this;
        }

        public Criteria andAlgorithmauthorIsNotNull() {
            addCriterion("AlgorithmAuthor is not null");
            return (Criteria) this;
        }

        public Criteria andAlgorithmauthorEqualTo(String value) {
            addCriterion("AlgorithmAuthor =", value, "algorithmauthor");
            return (Criteria) this;
        }

        public Criteria andAlgorithmauthorNotEqualTo(String value) {
            addCriterion("AlgorithmAuthor <>", value, "algorithmauthor");
            return (Criteria) this;
        }

        public Criteria andAlgorithmauthorGreaterThan(String value) {
            addCriterion("AlgorithmAuthor >", value, "algorithmauthor");
            return (Criteria) this;
        }

        public Criteria andAlgorithmauthorGreaterThanOrEqualTo(String value) {
            addCriterion("AlgorithmAuthor >=", value, "algorithmauthor");
            return (Criteria) this;
        }

        public Criteria andAlgorithmauthorLessThan(String value) {
            addCriterion("AlgorithmAuthor <", value, "algorithmauthor");
            return (Criteria) this;
        }

        public Criteria andAlgorithmauthorLessThanOrEqualTo(String value) {
            addCriterion("AlgorithmAuthor <=", value, "algorithmauthor");
            return (Criteria) this;
        }

        public Criteria andAlgorithmauthorLike(String value) {
            addCriterion("AlgorithmAuthor like", value, "algorithmauthor");
            return (Criteria) this;
        }

        public Criteria andAlgorithmauthorNotLike(String value) {
            addCriterion("AlgorithmAuthor not like", value, "algorithmauthor");
            return (Criteria) this;
        }

        public Criteria andAlgorithmauthorIn(List<String> values) {
            addCriterion("AlgorithmAuthor in", values, "algorithmauthor");
            return (Criteria) this;
        }

        public Criteria andAlgorithmauthorNotIn(List<String> values) {
            addCriterion("AlgorithmAuthor not in", values, "algorithmauthor");
            return (Criteria) this;
        }

        public Criteria andAlgorithmauthorBetween(String value1, String value2) {
            addCriterion("AlgorithmAuthor between", value1, value2, "algorithmauthor");
            return (Criteria) this;
        }

        public Criteria andAlgorithmauthorNotBetween(String value1, String value2) {
            addCriterion("AlgorithmAuthor not between", value1, value2, "algorithmauthor");
            return (Criteria) this;
        }

        public Criteria andIspublicIsNull() {
            addCriterion("IsPublic is null");
            return (Criteria) this;
        }

        public Criteria andIspublicIsNotNull() {
            addCriterion("IsPublic is not null");
            return (Criteria) this;
        }

        public Criteria andIspublicEqualTo(Long value) {
            addCriterion("IsPublic =", value, "ispublic");
            return (Criteria) this;
        }

        public Criteria andIspublicNotEqualTo(Long value) {
            addCriterion("IsPublic <>", value, "ispublic");
            return (Criteria) this;
        }

        public Criteria andIspublicGreaterThan(Long value) {
            addCriterion("IsPublic >", value, "ispublic");
            return (Criteria) this;
        }

        public Criteria andIspublicGreaterThanOrEqualTo(Long value) {
            addCriterion("IsPublic >=", value, "ispublic");
            return (Criteria) this;
        }

        public Criteria andIspublicLessThan(Long value) {
            addCriterion("IsPublic <", value, "ispublic");
            return (Criteria) this;
        }

        public Criteria andIspublicLessThanOrEqualTo(Long value) {
            addCriterion("IsPublic <=", value, "ispublic");
            return (Criteria) this;
        }

        public Criteria andIspublicIn(List<Long> values) {
            addCriterion("IsPublic in", values, "ispublic");
            return (Criteria) this;
        }

        public Criteria andIspublicNotIn(List<Long> values) {
            addCriterion("IsPublic not in", values, "ispublic");
            return (Criteria) this;
        }

        public Criteria andIspublicBetween(Long value1, Long value2) {
            addCriterion("IsPublic between", value1, value2, "ispublic");
            return (Criteria) this;
        }

        public Criteria andIspublicNotBetween(Long value1, Long value2) {
            addCriterion("IsPublic not between", value1, value2, "ispublic");
            return (Criteria) this;
        }

        public Criteria andAlgorithmtypeIsNull() {
            addCriterion("AlgorithmType is null");
            return (Criteria) this;
        }

        public Criteria andAlgorithmtypeIsNotNull() {
            addCriterion("AlgorithmType is not null");
            return (Criteria) this;
        }

        public Criteria andAlgorithmtypeEqualTo(Long value) {
            addCriterion("AlgorithmType =", value, "algorithmtype");
            return (Criteria) this;
        }

        public Criteria andAlgorithmtypeNotEqualTo(Long value) {
            addCriterion("AlgorithmType <>", value, "algorithmtype");
            return (Criteria) this;
        }

        public Criteria andAlgorithmtypeGreaterThan(Long value) {
            addCriterion("AlgorithmType >", value, "algorithmtype");
            return (Criteria) this;
        }

        public Criteria andAlgorithmtypeGreaterThanOrEqualTo(Long value) {
            addCriterion("AlgorithmType >=", value, "algorithmtype");
            return (Criteria) this;
        }

        public Criteria andAlgorithmtypeLessThan(Long value) {
            addCriterion("AlgorithmType <", value, "algorithmtype");
            return (Criteria) this;
        }

        public Criteria andAlgorithmtypeLessThanOrEqualTo(Long value) {
            addCriterion("AlgorithmType <=", value, "algorithmtype");
            return (Criteria) this;
        }

        public Criteria andAlgorithmtypeIn(List<Long> values) {
            addCriterion("AlgorithmType in", values, "algorithmtype");
            return (Criteria) this;
        }

        public Criteria andAlgorithmtypeNotIn(List<Long> values) {
            addCriterion("AlgorithmType not in", values, "algorithmtype");
            return (Criteria) this;
        }

        public Criteria andAlgorithmtypeBetween(Long value1, Long value2) {
            addCriterion("AlgorithmType between", value1, value2, "algorithmtype");
            return (Criteria) this;
        }

        public Criteria andAlgorithmtypeNotBetween(Long value1, Long value2) {
            addCriterion("AlgorithmType not between", value1, value2, "algorithmtype");
            return (Criteria) this;
        }

        public Criteria andAlgorithmfunIsNull() {
            addCriterion("AlgorithmFun is null");
            return (Criteria) this;
        }

        public Criteria andAlgorithmfunIsNotNull() {
            addCriterion("AlgorithmFun is not null");
            return (Criteria) this;
        }

        public Criteria andAlgorithmfunEqualTo(String value) {
            addCriterion("AlgorithmFun =", value, "algorithmfun");
            return (Criteria) this;
        }

        public Criteria andAlgorithmfunNotEqualTo(String value) {
            addCriterion("AlgorithmFun <>", value, "algorithmfun");
            return (Criteria) this;
        }

        public Criteria andAlgorithmfunGreaterThan(String value) {
            addCriterion("AlgorithmFun >", value, "algorithmfun");
            return (Criteria) this;
        }

        public Criteria andAlgorithmfunGreaterThanOrEqualTo(String value) {
            addCriterion("AlgorithmFun >=", value, "algorithmfun");
            return (Criteria) this;
        }

        public Criteria andAlgorithmfunLessThan(String value) {
            addCriterion("AlgorithmFun <", value, "algorithmfun");
            return (Criteria) this;
        }

        public Criteria andAlgorithmfunLessThanOrEqualTo(String value) {
            addCriterion("AlgorithmFun <=", value, "algorithmfun");
            return (Criteria) this;
        }

        public Criteria andAlgorithmfunLike(String value) {
            addCriterion("AlgorithmFun like", value, "algorithmfun");
            return (Criteria) this;
        }

        public Criteria andAlgorithmfunNotLike(String value) {
            addCriterion("AlgorithmFun not like", value, "algorithmfun");
            return (Criteria) this;
        }

        public Criteria andAlgorithmfunIn(List<String> values) {
            addCriterion("AlgorithmFun in", values, "algorithmfun");
            return (Criteria) this;
        }

        public Criteria andAlgorithmfunNotIn(List<String> values) {
            addCriterion("AlgorithmFun not in", values, "algorithmfun");
            return (Criteria) this;
        }

        public Criteria andAlgorithmfunBetween(String value1, String value2) {
            addCriterion("AlgorithmFun between", value1, value2, "algorithmfun");
            return (Criteria) this;
        }

        public Criteria andAlgorithmfunNotBetween(String value1, String value2) {
            addCriterion("AlgorithmFun not between", value1, value2, "algorithmfun");
            return (Criteria) this;
        }

        public Criteria andDesIsNull() {
            addCriterion("Des is null");
            return (Criteria) this;
        }

        public Criteria andDesIsNotNull() {
            addCriterion("Des is not null");
            return (Criteria) this;
        }

        public Criteria andDesEqualTo(String value) {
            addCriterion("Des =", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesNotEqualTo(String value) {
            addCriterion("Des <>", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesGreaterThan(String value) {
            addCriterion("Des >", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesGreaterThanOrEqualTo(String value) {
            addCriterion("Des >=", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesLessThan(String value) {
            addCriterion("Des <", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesLessThanOrEqualTo(String value) {
            addCriterion("Des <=", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesLike(String value) {
            addCriterion("Des like", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesNotLike(String value) {
            addCriterion("Des not like", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesIn(List<String> values) {
            addCriterion("Des in", values, "des");
            return (Criteria) this;
        }

        public Criteria andDesNotIn(List<String> values) {
            addCriterion("Des not in", values, "des");
            return (Criteria) this;
        }

        public Criteria andDesBetween(String value1, String value2) {
            addCriterion("Des between", value1, value2, "des");
            return (Criteria) this;
        }

        public Criteria andDesNotBetween(String value1, String value2) {
            addCriterion("Des not between", value1, value2, "des");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("Remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("Remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("Remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("Remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("Remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("Remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("Remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("Remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("Remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("Remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("Remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("Remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("Remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("Remark not between", value1, value2, "remark");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table table_algorithm
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table table_algorithm
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}