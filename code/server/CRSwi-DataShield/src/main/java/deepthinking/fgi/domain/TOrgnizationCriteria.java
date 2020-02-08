package deepthinking.fgi.domain;

import java.util.ArrayList;
import java.util.List;

public class TOrgnizationCriteria {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_orgnization
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_orgnization
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_orgnization
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_orgnization
     *
     * @mbg.generated
     */
    public TOrgnizationCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_orgnization
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_orgnization
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_orgnization
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_orgnization
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_orgnization
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_orgnization
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_orgnization
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
     * This method corresponds to the database table t_orgnization
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
     * This method corresponds to the database table t_orgnization
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_orgnization
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
     * This class corresponds to the database table t_orgnization
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

        public Criteria andOrgnizationCodeIsNull() {
            addCriterion("orgnization_code is null");
            return (Criteria) this;
        }

        public Criteria andOrgnizationCodeIsNotNull() {
            addCriterion("orgnization_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrgnizationCodeEqualTo(Integer value) {
            addCriterion("orgnization_code =", value, "orgnizationCode");
            return (Criteria) this;
        }

        public Criteria andOrgnizationCodeNotEqualTo(Integer value) {
            addCriterion("orgnization_code <>", value, "orgnizationCode");
            return (Criteria) this;
        }

        public Criteria andOrgnizationCodeGreaterThan(Integer value) {
            addCriterion("orgnization_code >", value, "orgnizationCode");
            return (Criteria) this;
        }

        public Criteria andOrgnizationCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("orgnization_code >=", value, "orgnizationCode");
            return (Criteria) this;
        }

        public Criteria andOrgnizationCodeLessThan(Integer value) {
            addCriterion("orgnization_code <", value, "orgnizationCode");
            return (Criteria) this;
        }

        public Criteria andOrgnizationCodeLessThanOrEqualTo(Integer value) {
            addCriterion("orgnization_code <=", value, "orgnizationCode");
            return (Criteria) this;
        }

        public Criteria andOrgnizationCodeIn(List<Integer> values) {
            addCriterion("orgnization_code in", values, "orgnizationCode");
            return (Criteria) this;
        }

        public Criteria andOrgnizationCodeNotIn(List<Integer> values) {
            addCriterion("orgnization_code not in", values, "orgnizationCode");
            return (Criteria) this;
        }

        public Criteria andOrgnizationCodeBetween(Integer value1, Integer value2) {
            addCriterion("orgnization_code between", value1, value2, "orgnizationCode");
            return (Criteria) this;
        }

        public Criteria andOrgnizationCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("orgnization_code not between", value1, value2, "orgnizationCode");
            return (Criteria) this;
        }

        public Criteria andOrgnizationNameIsNull() {
            addCriterion("orgnization_name is null");
            return (Criteria) this;
        }

        public Criteria andOrgnizationNameIsNotNull() {
            addCriterion("orgnization_name is not null");
            return (Criteria) this;
        }

        public Criteria andOrgnizationNameEqualTo(String value) {
            addCriterion("orgnization_name =", value, "orgnizationName");
            return (Criteria) this;
        }

        public Criteria andOrgnizationNameNotEqualTo(String value) {
            addCriterion("orgnization_name <>", value, "orgnizationName");
            return (Criteria) this;
        }

        public Criteria andOrgnizationNameGreaterThan(String value) {
            addCriterion("orgnization_name >", value, "orgnizationName");
            return (Criteria) this;
        }

        public Criteria andOrgnizationNameGreaterThanOrEqualTo(String value) {
            addCriterion("orgnization_name >=", value, "orgnizationName");
            return (Criteria) this;
        }

        public Criteria andOrgnizationNameLessThan(String value) {
            addCriterion("orgnization_name <", value, "orgnizationName");
            return (Criteria) this;
        }

        public Criteria andOrgnizationNameLessThanOrEqualTo(String value) {
            addCriterion("orgnization_name <=", value, "orgnizationName");
            return (Criteria) this;
        }

        public Criteria andOrgnizationNameLike(String value) {
            addCriterion("orgnization_name like", value, "orgnizationName");
            return (Criteria) this;
        }

        public Criteria andOrgnizationNameNotLike(String value) {
            addCriterion("orgnization_name not like", value, "orgnizationName");
            return (Criteria) this;
        }

        public Criteria andOrgnizationNameIn(List<String> values) {
            addCriterion("orgnization_name in", values, "orgnizationName");
            return (Criteria) this;
        }

        public Criteria andOrgnizationNameNotIn(List<String> values) {
            addCriterion("orgnization_name not in", values, "orgnizationName");
            return (Criteria) this;
        }

        public Criteria andOrgnizationNameBetween(String value1, String value2) {
            addCriterion("orgnization_name between", value1, value2, "orgnizationName");
            return (Criteria) this;
        }

        public Criteria andOrgnizationNameNotBetween(String value1, String value2) {
            addCriterion("orgnization_name not between", value1, value2, "orgnizationName");
            return (Criteria) this;
        }

        public Criteria andOrgnizationDirectorIsNull() {
            addCriterion("orgnization_director is null");
            return (Criteria) this;
        }

        public Criteria andOrgnizationDirectorIsNotNull() {
            addCriterion("orgnization_director is not null");
            return (Criteria) this;
        }

        public Criteria andOrgnizationDirectorEqualTo(String value) {
            addCriterion("orgnization_director =", value, "orgnizationDirector");
            return (Criteria) this;
        }

        public Criteria andOrgnizationDirectorNotEqualTo(String value) {
            addCriterion("orgnization_director <>", value, "orgnizationDirector");
            return (Criteria) this;
        }

        public Criteria andOrgnizationDirectorGreaterThan(String value) {
            addCriterion("orgnization_director >", value, "orgnizationDirector");
            return (Criteria) this;
        }

        public Criteria andOrgnizationDirectorGreaterThanOrEqualTo(String value) {
            addCriterion("orgnization_director >=", value, "orgnizationDirector");
            return (Criteria) this;
        }

        public Criteria andOrgnizationDirectorLessThan(String value) {
            addCriterion("orgnization_director <", value, "orgnizationDirector");
            return (Criteria) this;
        }

        public Criteria andOrgnizationDirectorLessThanOrEqualTo(String value) {
            addCriterion("orgnization_director <=", value, "orgnizationDirector");
            return (Criteria) this;
        }

        public Criteria andOrgnizationDirectorLike(String value) {
            addCriterion("orgnization_director like", value, "orgnizationDirector");
            return (Criteria) this;
        }

        public Criteria andOrgnizationDirectorNotLike(String value) {
            addCriterion("orgnization_director not like", value, "orgnizationDirector");
            return (Criteria) this;
        }

        public Criteria andOrgnizationDirectorIn(List<String> values) {
            addCriterion("orgnization_director in", values, "orgnizationDirector");
            return (Criteria) this;
        }

        public Criteria andOrgnizationDirectorNotIn(List<String> values) {
            addCriterion("orgnization_director not in", values, "orgnizationDirector");
            return (Criteria) this;
        }

        public Criteria andOrgnizationDirectorBetween(String value1, String value2) {
            addCriterion("orgnization_director between", value1, value2, "orgnizationDirector");
            return (Criteria) this;
        }

        public Criteria andOrgnizationDirectorNotBetween(String value1, String value2) {
            addCriterion("orgnization_director not between", value1, value2, "orgnizationDirector");
            return (Criteria) this;
        }

        public Criteria andOrgnizationDegreeIsNull() {
            addCriterion("orgnization_degree is null");
            return (Criteria) this;
        }

        public Criteria andOrgnizationDegreeIsNotNull() {
            addCriterion("orgnization_degree is not null");
            return (Criteria) this;
        }

        public Criteria andOrgnizationDegreeEqualTo(String value) {
            addCriterion("orgnization_degree =", value, "orgnizationDegree");
            return (Criteria) this;
        }

        public Criteria andOrgnizationDegreeNotEqualTo(String value) {
            addCriterion("orgnization_degree <>", value, "orgnizationDegree");
            return (Criteria) this;
        }

        public Criteria andOrgnizationDegreeGreaterThan(String value) {
            addCriterion("orgnization_degree >", value, "orgnizationDegree");
            return (Criteria) this;
        }

        public Criteria andOrgnizationDegreeGreaterThanOrEqualTo(String value) {
            addCriterion("orgnization_degree >=", value, "orgnizationDegree");
            return (Criteria) this;
        }

        public Criteria andOrgnizationDegreeLessThan(String value) {
            addCriterion("orgnization_degree <", value, "orgnizationDegree");
            return (Criteria) this;
        }

        public Criteria andOrgnizationDegreeLessThanOrEqualTo(String value) {
            addCriterion("orgnization_degree <=", value, "orgnizationDegree");
            return (Criteria) this;
        }

        public Criteria andOrgnizationDegreeLike(String value) {
            addCriterion("orgnization_degree like", value, "orgnizationDegree");
            return (Criteria) this;
        }

        public Criteria andOrgnizationDegreeNotLike(String value) {
            addCriterion("orgnization_degree not like", value, "orgnizationDegree");
            return (Criteria) this;
        }

        public Criteria andOrgnizationDegreeIn(List<String> values) {
            addCriterion("orgnization_degree in", values, "orgnizationDegree");
            return (Criteria) this;
        }

        public Criteria andOrgnizationDegreeNotIn(List<String> values) {
            addCriterion("orgnization_degree not in", values, "orgnizationDegree");
            return (Criteria) this;
        }

        public Criteria andOrgnizationDegreeBetween(String value1, String value2) {
            addCriterion("orgnization_degree between", value1, value2, "orgnizationDegree");
            return (Criteria) this;
        }

        public Criteria andOrgnizationDegreeNotBetween(String value1, String value2) {
            addCriterion("orgnization_degree not between", value1, value2, "orgnizationDegree");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_orgnization
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
     * This class corresponds to the database table t_orgnization
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