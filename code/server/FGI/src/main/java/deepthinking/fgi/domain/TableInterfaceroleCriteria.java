package deepthinking.fgi.domain;

import java.util.ArrayList;
import java.util.List;

public class TableInterfaceroleCriteria {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table table_interfacerole
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table table_interfacerole
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table table_interfacerole
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfacerole
     *
     * @mbg.generated
     */
    public TableInterfaceroleCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfacerole
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfacerole
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfacerole
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfacerole
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfacerole
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfacerole
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfacerole
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
     * This method corresponds to the database table table_interfacerole
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
     * This method corresponds to the database table table_interfacerole
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_interfacerole
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
     * This class corresponds to the database table table_interfacerole
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

        public Criteria andRoleidIsNull() {
            addCriterion("RoleID is null");
            return (Criteria) this;
        }

        public Criteria andRoleidIsNotNull() {
            addCriterion("RoleID is not null");
            return (Criteria) this;
        }

        public Criteria andRoleidEqualTo(Integer value) {
            addCriterion("RoleID =", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotEqualTo(Integer value) {
            addCriterion("RoleID <>", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidGreaterThan(Integer value) {
            addCriterion("RoleID >", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidGreaterThanOrEqualTo(Integer value) {
            addCriterion("RoleID >=", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidLessThan(Integer value) {
            addCriterion("RoleID <", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidLessThanOrEqualTo(Integer value) {
            addCriterion("RoleID <=", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidIn(List<Integer> values) {
            addCriterion("RoleID in", values, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotIn(List<Integer> values) {
            addCriterion("RoleID not in", values, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidBetween(Integer value1, Integer value2) {
            addCriterion("RoleID between", value1, value2, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotBetween(Integer value1, Integer value2) {
            addCriterion("RoleID not between", value1, value2, "roleid");
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

        public Criteria andParametersidIsNull() {
            addCriterion("ParametersID is null");
            return (Criteria) this;
        }

        public Criteria andParametersidIsNotNull() {
            addCriterion("ParametersID is not null");
            return (Criteria) this;
        }

        public Criteria andParametersidEqualTo(String value) {
            addCriterion("ParametersID =", value, "parametersid");
            return (Criteria) this;
        }

        public Criteria andParametersidNotEqualTo(String value) {
            addCriterion("ParametersID <>", value, "parametersid");
            return (Criteria) this;
        }

        public Criteria andParametersidGreaterThan(String value) {
            addCriterion("ParametersID >", value, "parametersid");
            return (Criteria) this;
        }

        public Criteria andParametersidGreaterThanOrEqualTo(String value) {
            addCriterion("ParametersID >=", value, "parametersid");
            return (Criteria) this;
        }

        public Criteria andParametersidLessThan(String value) {
            addCriterion("ParametersID <", value, "parametersid");
            return (Criteria) this;
        }

        public Criteria andParametersidLessThanOrEqualTo(String value) {
            addCriterion("ParametersID <=", value, "parametersid");
            return (Criteria) this;
        }

        public Criteria andParametersidLike(String value) {
            addCriterion("ParametersID like", value, "parametersid");
            return (Criteria) this;
        }

        public Criteria andParametersidNotLike(String value) {
            addCriterion("ParametersID not like", value, "parametersid");
            return (Criteria) this;
        }

        public Criteria andParametersidIn(List<String> values) {
            addCriterion("ParametersID in", values, "parametersid");
            return (Criteria) this;
        }

        public Criteria andParametersidNotIn(List<String> values) {
            addCriterion("ParametersID not in", values, "parametersid");
            return (Criteria) this;
        }

        public Criteria andParametersidBetween(String value1, String value2) {
            addCriterion("ParametersID between", value1, value2, "parametersid");
            return (Criteria) this;
        }

        public Criteria andParametersidNotBetween(String value1, String value2) {
            addCriterion("ParametersID not between", value1, value2, "parametersid");
            return (Criteria) this;
        }

        public Criteria andPreinterfaceidIsNull() {
            addCriterion("PreInterfaceID is null");
            return (Criteria) this;
        }

        public Criteria andPreinterfaceidIsNotNull() {
            addCriterion("PreInterfaceID is not null");
            return (Criteria) this;
        }

        public Criteria andPreinterfaceidEqualTo(String value) {
            addCriterion("PreInterfaceID =", value, "preinterfaceid");
            return (Criteria) this;
        }

        public Criteria andPreinterfaceidNotEqualTo(String value) {
            addCriterion("PreInterfaceID <>", value, "preinterfaceid");
            return (Criteria) this;
        }

        public Criteria andPreinterfaceidGreaterThan(String value) {
            addCriterion("PreInterfaceID >", value, "preinterfaceid");
            return (Criteria) this;
        }

        public Criteria andPreinterfaceidGreaterThanOrEqualTo(String value) {
            addCriterion("PreInterfaceID >=", value, "preinterfaceid");
            return (Criteria) this;
        }

        public Criteria andPreinterfaceidLessThan(String value) {
            addCriterion("PreInterfaceID <", value, "preinterfaceid");
            return (Criteria) this;
        }

        public Criteria andPreinterfaceidLessThanOrEqualTo(String value) {
            addCriterion("PreInterfaceID <=", value, "preinterfaceid");
            return (Criteria) this;
        }

        public Criteria andPreinterfaceidLike(String value) {
            addCriterion("PreInterfaceID like", value, "preinterfaceid");
            return (Criteria) this;
        }

        public Criteria andPreinterfaceidNotLike(String value) {
            addCriterion("PreInterfaceID not like", value, "preinterfaceid");
            return (Criteria) this;
        }

        public Criteria andPreinterfaceidIn(List<String> values) {
            addCriterion("PreInterfaceID in", values, "preinterfaceid");
            return (Criteria) this;
        }

        public Criteria andPreinterfaceidNotIn(List<String> values) {
            addCriterion("PreInterfaceID not in", values, "preinterfaceid");
            return (Criteria) this;
        }

        public Criteria andPreinterfaceidBetween(String value1, String value2) {
            addCriterion("PreInterfaceID between", value1, value2, "preinterfaceid");
            return (Criteria) this;
        }

        public Criteria andPreinterfaceidNotBetween(String value1, String value2) {
            addCriterion("PreInterfaceID not between", value1, value2, "preinterfaceid");
            return (Criteria) this;
        }

        public Criteria andPreparametersidIsNull() {
            addCriterion("PreParametersID is null");
            return (Criteria) this;
        }

        public Criteria andPreparametersidIsNotNull() {
            addCriterion("PreParametersID is not null");
            return (Criteria) this;
        }

        public Criteria andPreparametersidEqualTo(String value) {
            addCriterion("PreParametersID =", value, "preparametersid");
            return (Criteria) this;
        }

        public Criteria andPreparametersidNotEqualTo(String value) {
            addCriterion("PreParametersID <>", value, "preparametersid");
            return (Criteria) this;
        }

        public Criteria andPreparametersidGreaterThan(String value) {
            addCriterion("PreParametersID >", value, "preparametersid");
            return (Criteria) this;
        }

        public Criteria andPreparametersidGreaterThanOrEqualTo(String value) {
            addCriterion("PreParametersID >=", value, "preparametersid");
            return (Criteria) this;
        }

        public Criteria andPreparametersidLessThan(String value) {
            addCriterion("PreParametersID <", value, "preparametersid");
            return (Criteria) this;
        }

        public Criteria andPreparametersidLessThanOrEqualTo(String value) {
            addCriterion("PreParametersID <=", value, "preparametersid");
            return (Criteria) this;
        }

        public Criteria andPreparametersidLike(String value) {
            addCriterion("PreParametersID like", value, "preparametersid");
            return (Criteria) this;
        }

        public Criteria andPreparametersidNotLike(String value) {
            addCriterion("PreParametersID not like", value, "preparametersid");
            return (Criteria) this;
        }

        public Criteria andPreparametersidIn(List<String> values) {
            addCriterion("PreParametersID in", values, "preparametersid");
            return (Criteria) this;
        }

        public Criteria andPreparametersidNotIn(List<String> values) {
            addCriterion("PreParametersID not in", values, "preparametersid");
            return (Criteria) this;
        }

        public Criteria andPreparametersidBetween(String value1, String value2) {
            addCriterion("PreParametersID between", value1, value2, "preparametersid");
            return (Criteria) this;
        }

        public Criteria andPreparametersidNotBetween(String value1, String value2) {
            addCriterion("PreParametersID not between", value1, value2, "preparametersid");
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
     * This class corresponds to the database table table_interfacerole
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
     * This class corresponds to the database table table_interfacerole
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