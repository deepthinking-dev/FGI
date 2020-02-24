package deepthinking.fgi.domain;

import java.util.ArrayList;
import java.util.List;

public class TableAlgorithmconditionCriteria {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    public TableAlgorithmconditionCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmcondition
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
     * This method corresponds to the database table table_algorithmcondition
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
     * This method corresponds to the database table table_algorithmcondition
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_algorithmcondition
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
     * This class corresponds to the database table table_algorithmcondition
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

        public Criteria andAlgorithmroleidIsNull() {
            addCriterion("AlgorithmRoleID is null");
            return (Criteria) this;
        }

        public Criteria andAlgorithmroleidIsNotNull() {
            addCriterion("AlgorithmRoleID is not null");
            return (Criteria) this;
        }

        public Criteria andAlgorithmroleidEqualTo(Integer value) {
            addCriterion("AlgorithmRoleID =", value, "algorithmroleid");
            return (Criteria) this;
        }

        public Criteria andAlgorithmroleidNotEqualTo(Integer value) {
            addCriterion("AlgorithmRoleID <>", value, "algorithmroleid");
            return (Criteria) this;
        }

        public Criteria andAlgorithmroleidGreaterThan(Integer value) {
            addCriterion("AlgorithmRoleID >", value, "algorithmroleid");
            return (Criteria) this;
        }

        public Criteria andAlgorithmroleidGreaterThanOrEqualTo(Integer value) {
            addCriterion("AlgorithmRoleID >=", value, "algorithmroleid");
            return (Criteria) this;
        }

        public Criteria andAlgorithmroleidLessThan(Integer value) {
            addCriterion("AlgorithmRoleID <", value, "algorithmroleid");
            return (Criteria) this;
        }

        public Criteria andAlgorithmroleidLessThanOrEqualTo(Integer value) {
            addCriterion("AlgorithmRoleID <=", value, "algorithmroleid");
            return (Criteria) this;
        }

        public Criteria andAlgorithmroleidIn(List<Integer> values) {
            addCriterion("AlgorithmRoleID in", values, "algorithmroleid");
            return (Criteria) this;
        }

        public Criteria andAlgorithmroleidNotIn(List<Integer> values) {
            addCriterion("AlgorithmRoleID not in", values, "algorithmroleid");
            return (Criteria) this;
        }

        public Criteria andAlgorithmroleidBetween(Integer value1, Integer value2) {
            addCriterion("AlgorithmRoleID between", value1, value2, "algorithmroleid");
            return (Criteria) this;
        }

        public Criteria andAlgorithmroleidNotBetween(Integer value1, Integer value2) {
            addCriterion("AlgorithmRoleID not between", value1, value2, "algorithmroleid");
            return (Criteria) this;
        }

        public Criteria andLogicrelationIsNull() {
            addCriterion("LogicRelation is null");
            return (Criteria) this;
        }

        public Criteria andLogicrelationIsNotNull() {
            addCriterion("LogicRelation is not null");
            return (Criteria) this;
        }

        public Criteria andLogicrelationEqualTo(String value) {
            addCriterion("LogicRelation =", value, "logicrelation");
            return (Criteria) this;
        }

        public Criteria andLogicrelationNotEqualTo(String value) {
            addCriterion("LogicRelation <>", value, "logicrelation");
            return (Criteria) this;
        }

        public Criteria andLogicrelationGreaterThan(String value) {
            addCriterion("LogicRelation >", value, "logicrelation");
            return (Criteria) this;
        }

        public Criteria andLogicrelationGreaterThanOrEqualTo(String value) {
            addCriterion("LogicRelation >=", value, "logicrelation");
            return (Criteria) this;
        }

        public Criteria andLogicrelationLessThan(String value) {
            addCriterion("LogicRelation <", value, "logicrelation");
            return (Criteria) this;
        }

        public Criteria andLogicrelationLessThanOrEqualTo(String value) {
            addCriterion("LogicRelation <=", value, "logicrelation");
            return (Criteria) this;
        }

        public Criteria andLogicrelationLike(String value) {
            addCriterion("LogicRelation like", value, "logicrelation");
            return (Criteria) this;
        }

        public Criteria andLogicrelationNotLike(String value) {
            addCriterion("LogicRelation not like", value, "logicrelation");
            return (Criteria) this;
        }

        public Criteria andLogicrelationIn(List<String> values) {
            addCriterion("LogicRelation in", values, "logicrelation");
            return (Criteria) this;
        }

        public Criteria andLogicrelationNotIn(List<String> values) {
            addCriterion("LogicRelation not in", values, "logicrelation");
            return (Criteria) this;
        }

        public Criteria andLogicrelationBetween(String value1, String value2) {
            addCriterion("LogicRelation between", value1, value2, "logicrelation");
            return (Criteria) this;
        }

        public Criteria andLogicrelationNotBetween(String value1, String value2) {
            addCriterion("LogicRelation not between", value1, value2, "logicrelation");
            return (Criteria) this;
        }

        public Criteria andLogicvalueIsNull() {
            addCriterion("LogicValue is null");
            return (Criteria) this;
        }

        public Criteria andLogicvalueIsNotNull() {
            addCriterion("LogicValue is not null");
            return (Criteria) this;
        }

        public Criteria andLogicvalueEqualTo(Long value) {
            addCriterion("LogicValue =", value, "logicvalue");
            return (Criteria) this;
        }

        public Criteria andLogicvalueNotEqualTo(Long value) {
            addCriterion("LogicValue <>", value, "logicvalue");
            return (Criteria) this;
        }

        public Criteria andLogicvalueGreaterThan(Long value) {
            addCriterion("LogicValue >", value, "logicvalue");
            return (Criteria) this;
        }

        public Criteria andLogicvalueGreaterThanOrEqualTo(Long value) {
            addCriterion("LogicValue >=", value, "logicvalue");
            return (Criteria) this;
        }

        public Criteria andLogicvalueLessThan(Long value) {
            addCriterion("LogicValue <", value, "logicvalue");
            return (Criteria) this;
        }

        public Criteria andLogicvalueLessThanOrEqualTo(Long value) {
            addCriterion("LogicValue <=", value, "logicvalue");
            return (Criteria) this;
        }

        public Criteria andLogicvalueIn(List<Long> values) {
            addCriterion("LogicValue in", values, "logicvalue");
            return (Criteria) this;
        }

        public Criteria andLogicvalueNotIn(List<Long> values) {
            addCriterion("LogicValue not in", values, "logicvalue");
            return (Criteria) this;
        }

        public Criteria andLogicvalueBetween(Long value1, Long value2) {
            addCriterion("LogicValue between", value1, value2, "logicvalue");
            return (Criteria) this;
        }

        public Criteria andLogicvalueNotBetween(Long value1, Long value2) {
            addCriterion("LogicValue not between", value1, value2, "logicvalue");
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
     * This class corresponds to the database table table_algorithmcondition
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
     * This class corresponds to the database table table_algorithmcondition
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