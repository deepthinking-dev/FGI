package deepthinking.fgi.domain;

import java.util.ArrayList;
import java.util.List;

public class TableInterfaceparametersCriteria {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table table_interfaceparameters
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table table_interfaceparameters
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table table_interfaceparameters
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfaceparameters
     *
     * @mbg.generated
     */
    public TableInterfaceparametersCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfaceparameters
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfaceparameters
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfaceparameters
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfaceparameters
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfaceparameters
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfaceparameters
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfaceparameters
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
     * This method corresponds to the database table table_interfaceparameters
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
     * This method corresponds to the database table table_interfaceparameters
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfaceparameters
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
     * This class corresponds to the database table table_interfaceparameters
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andInterfaceidIsNull() {
            addCriterion("InterfaceID is null");
            return (Criteria) this;
        }

        public Criteria andInterfaceidIsNotNull() {
            addCriterion("InterfaceID is not null");
            return (Criteria) this;
        }

        public Criteria andInterfaceidEqualTo(String value) {
            addCriterion("InterfaceID =", value, "interfaceid");
            return (Criteria) this;
        }

        public Criteria andInterfaceidNotEqualTo(String value) {
            addCriterion("InterfaceID <>", value, "interfaceid");
            return (Criteria) this;
        }

        public Criteria andInterfaceidGreaterThan(String value) {
            addCriterion("InterfaceID >", value, "interfaceid");
            return (Criteria) this;
        }

        public Criteria andInterfaceidGreaterThanOrEqualTo(String value) {
            addCriterion("InterfaceID >=", value, "interfaceid");
            return (Criteria) this;
        }

        public Criteria andInterfaceidLessThan(String value) {
            addCriterion("InterfaceID <", value, "interfaceid");
            return (Criteria) this;
        }

        public Criteria andInterfaceidLessThanOrEqualTo(String value) {
            addCriterion("InterfaceID <=", value, "interfaceid");
            return (Criteria) this;
        }

        public Criteria andInterfaceidLike(String value) {
            addCriterion("InterfaceID like", value, "interfaceid");
            return (Criteria) this;
        }

        public Criteria andInterfaceidNotLike(String value) {
            addCriterion("InterfaceID not like", value, "interfaceid");
            return (Criteria) this;
        }

        public Criteria andInterfaceidIn(List<String> values) {
            addCriterion("InterfaceID in", values, "interfaceid");
            return (Criteria) this;
        }

        public Criteria andInterfaceidNotIn(List<String> values) {
            addCriterion("InterfaceID not in", values, "interfaceid");
            return (Criteria) this;
        }

        public Criteria andInterfaceidBetween(String value1, String value2) {
            addCriterion("InterfaceID between", value1, value2, "interfaceid");
            return (Criteria) this;
        }

        public Criteria andInterfaceidNotBetween(String value1, String value2) {
            addCriterion("InterfaceID not between", value1, value2, "interfaceid");
            return (Criteria) this;
        }

        public Criteria andParameterssourcesIsNull() {
            addCriterion("ParametersSources is null");
            return (Criteria) this;
        }

        public Criteria andParameterssourcesIsNotNull() {
            addCriterion("ParametersSources is not null");
            return (Criteria) this;
        }

        public Criteria andParameterssourcesEqualTo(String value) {
            addCriterion("ParametersSources =", value, "parameterssources");
            return (Criteria) this;
        }

        public Criteria andParameterssourcesNotEqualTo(String value) {
            addCriterion("ParametersSources <>", value, "parameterssources");
            return (Criteria) this;
        }

        public Criteria andParameterssourcesGreaterThan(String value) {
            addCriterion("ParametersSources >", value, "parameterssources");
            return (Criteria) this;
        }

        public Criteria andParameterssourcesGreaterThanOrEqualTo(String value) {
            addCriterion("ParametersSources >=", value, "parameterssources");
            return (Criteria) this;
        }

        public Criteria andParameterssourcesLessThan(String value) {
            addCriterion("ParametersSources <", value, "parameterssources");
            return (Criteria) this;
        }

        public Criteria andParameterssourcesLessThanOrEqualTo(String value) {
            addCriterion("ParametersSources <=", value, "parameterssources");
            return (Criteria) this;
        }

        public Criteria andParameterssourcesLike(String value) {
            addCriterion("ParametersSources like", value, "parameterssources");
            return (Criteria) this;
        }

        public Criteria andParameterssourcesNotLike(String value) {
            addCriterion("ParametersSources not like", value, "parameterssources");
            return (Criteria) this;
        }

        public Criteria andParameterssourcesIn(List<String> values) {
            addCriterion("ParametersSources in", values, "parameterssources");
            return (Criteria) this;
        }

        public Criteria andParameterssourcesNotIn(List<String> values) {
            addCriterion("ParametersSources not in", values, "parameterssources");
            return (Criteria) this;
        }

        public Criteria andParameterssourcesBetween(String value1, String value2) {
            addCriterion("ParametersSources between", value1, value2, "parameterssources");
            return (Criteria) this;
        }

        public Criteria andParameterssourcesNotBetween(String value1, String value2) {
            addCriterion("ParametersSources not between", value1, value2, "parameterssources");
            return (Criteria) this;
        }

        public Criteria andParametersnameIsNull() {
            addCriterion("ParametersName is null");
            return (Criteria) this;
        }

        public Criteria andParametersnameIsNotNull() {
            addCriterion("ParametersName is not null");
            return (Criteria) this;
        }

        public Criteria andParametersnameEqualTo(String value) {
            addCriterion("ParametersName =", value, "parametersname");
            return (Criteria) this;
        }

        public Criteria andParametersnameNotEqualTo(String value) {
            addCriterion("ParametersName <>", value, "parametersname");
            return (Criteria) this;
        }

        public Criteria andParametersnameGreaterThan(String value) {
            addCriterion("ParametersName >", value, "parametersname");
            return (Criteria) this;
        }

        public Criteria andParametersnameGreaterThanOrEqualTo(String value) {
            addCriterion("ParametersName >=", value, "parametersname");
            return (Criteria) this;
        }

        public Criteria andParametersnameLessThan(String value) {
            addCriterion("ParametersName <", value, "parametersname");
            return (Criteria) this;
        }

        public Criteria andParametersnameLessThanOrEqualTo(String value) {
            addCriterion("ParametersName <=", value, "parametersname");
            return (Criteria) this;
        }

        public Criteria andParametersnameLike(String value) {
            addCriterion("ParametersName like", value, "parametersname");
            return (Criteria) this;
        }

        public Criteria andParametersnameNotLike(String value) {
            addCriterion("ParametersName not like", value, "parametersname");
            return (Criteria) this;
        }

        public Criteria andParametersnameIn(List<String> values) {
            addCriterion("ParametersName in", values, "parametersname");
            return (Criteria) this;
        }

        public Criteria andParametersnameNotIn(List<String> values) {
            addCriterion("ParametersName not in", values, "parametersname");
            return (Criteria) this;
        }

        public Criteria andParametersnameBetween(String value1, String value2) {
            addCriterion("ParametersName between", value1, value2, "parametersname");
            return (Criteria) this;
        }

        public Criteria andParametersnameNotBetween(String value1, String value2) {
            addCriterion("ParametersName not between", value1, value2, "parametersname");
            return (Criteria) this;
        }

        public Criteria andInoroutIsNull() {
            addCriterion("inOrOut is null");
            return (Criteria) this;
        }

        public Criteria andInoroutIsNotNull() {
            addCriterion("inOrOut is not null");
            return (Criteria) this;
        }

        public Criteria andInoroutEqualTo(Long value) {
            addCriterion("inOrOut =", value, "inorout");
            return (Criteria) this;
        }

        public Criteria andInoroutNotEqualTo(Long value) {
            addCriterion("inOrOut <>", value, "inorout");
            return (Criteria) this;
        }

        public Criteria andInoroutGreaterThan(Long value) {
            addCriterion("inOrOut >", value, "inorout");
            return (Criteria) this;
        }

        public Criteria andInoroutGreaterThanOrEqualTo(Long value) {
            addCriterion("inOrOut >=", value, "inorout");
            return (Criteria) this;
        }

        public Criteria andInoroutLessThan(Long value) {
            addCriterion("inOrOut <", value, "inorout");
            return (Criteria) this;
        }

        public Criteria andInoroutLessThanOrEqualTo(Long value) {
            addCriterion("inOrOut <=", value, "inorout");
            return (Criteria) this;
        }

        public Criteria andInoroutIn(List<Long> values) {
            addCriterion("inOrOut in", values, "inorout");
            return (Criteria) this;
        }

        public Criteria andInoroutNotIn(List<Long> values) {
            addCriterion("inOrOut not in", values, "inorout");
            return (Criteria) this;
        }

        public Criteria andInoroutBetween(Long value1, Long value2) {
            addCriterion("inOrOut between", value1, value2, "inorout");
            return (Criteria) this;
        }

        public Criteria andInoroutNotBetween(Long value1, Long value2) {
            addCriterion("inOrOut not between", value1, value2, "inorout");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table table_interfaceparameters
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
     * This class corresponds to the database table table_interfaceparameters
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