package com.example.hotel.model;

import java.util.ArrayList;
import java.util.List;

public class RoomExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RoomExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andRoomnameIsNull() {
            addCriterion("roomname is null");
            return (Criteria) this;
        }

        public Criteria andRoomnameIsNotNull() {
            addCriterion("roomname is not null");
            return (Criteria) this;
        }

        public Criteria andRoomnameEqualTo(String value) {
            addCriterion("roomname =", value, "roomname");
            return (Criteria) this;
        }

        public Criteria andRoomnameNotEqualTo(String value) {
            addCriterion("roomname <>", value, "roomname");
            return (Criteria) this;
        }

        public Criteria andRoomnameGreaterThan(String value) {
            addCriterion("roomname >", value, "roomname");
            return (Criteria) this;
        }

        public Criteria andRoomnameGreaterThanOrEqualTo(String value) {
            addCriterion("roomname >=", value, "roomname");
            return (Criteria) this;
        }

        public Criteria andRoomnameLessThan(String value) {
            addCriterion("roomname <", value, "roomname");
            return (Criteria) this;
        }

        public Criteria andRoomnameLessThanOrEqualTo(String value) {
            addCriterion("roomname <=", value, "roomname");
            return (Criteria) this;
        }

        public Criteria andRoomnameLike(String value) {
            addCriterion("roomname like", value, "roomname");
            return (Criteria) this;
        }

        public Criteria andRoomnameNotLike(String value) {
            addCriterion("roomname not like", value, "roomname");
            return (Criteria) this;
        }

        public Criteria andRoomnameIn(List<String> values) {
            addCriterion("roomname in", values, "roomname");
            return (Criteria) this;
        }

        public Criteria andRoomnameNotIn(List<String> values) {
            addCriterion("roomname not in", values, "roomname");
            return (Criteria) this;
        }

        public Criteria andRoomnameBetween(String value1, String value2) {
            addCriterion("roomname between", value1, value2, "roomname");
            return (Criteria) this;
        }

        public Criteria andRoomnameNotBetween(String value1, String value2) {
            addCriterion("roomname not between", value1, value2, "roomname");
            return (Criteria) this;
        }

        public Criteria andRoomintroduceIsNull() {
            addCriterion("roomintroduce is null");
            return (Criteria) this;
        }

        public Criteria andRoomintroduceIsNotNull() {
            addCriterion("roomintroduce is not null");
            return (Criteria) this;
        }

        public Criteria andRoomintroduceEqualTo(String value) {
            addCriterion("roomintroduce =", value, "roomintroduce");
            return (Criteria) this;
        }

        public Criteria andRoomintroduceNotEqualTo(String value) {
            addCriterion("roomintroduce <>", value, "roomintroduce");
            return (Criteria) this;
        }

        public Criteria andRoomintroduceGreaterThan(String value) {
            addCriterion("roomintroduce >", value, "roomintroduce");
            return (Criteria) this;
        }

        public Criteria andRoomintroduceGreaterThanOrEqualTo(String value) {
            addCriterion("roomintroduce >=", value, "roomintroduce");
            return (Criteria) this;
        }

        public Criteria andRoomintroduceLessThan(String value) {
            addCriterion("roomintroduce <", value, "roomintroduce");
            return (Criteria) this;
        }

        public Criteria andRoomintroduceLessThanOrEqualTo(String value) {
            addCriterion("roomintroduce <=", value, "roomintroduce");
            return (Criteria) this;
        }

        public Criteria andRoomintroduceLike(String value) {
            addCriterion("roomintroduce like", value, "roomintroduce");
            return (Criteria) this;
        }

        public Criteria andRoomintroduceNotLike(String value) {
            addCriterion("roomintroduce not like", value, "roomintroduce");
            return (Criteria) this;
        }

        public Criteria andRoomintroduceIn(List<String> values) {
            addCriterion("roomintroduce in", values, "roomintroduce");
            return (Criteria) this;
        }

        public Criteria andRoomintroduceNotIn(List<String> values) {
            addCriterion("roomintroduce not in", values, "roomintroduce");
            return (Criteria) this;
        }

        public Criteria andRoomintroduceBetween(String value1, String value2) {
            addCriterion("roomintroduce between", value1, value2, "roomintroduce");
            return (Criteria) this;
        }

        public Criteria andRoomintroduceNotBetween(String value1, String value2) {
            addCriterion("roomintroduce not between", value1, value2, "roomintroduce");
            return (Criteria) this;
        }

        public Criteria andRoomnumberIsNull() {
            addCriterion("roomnumber is null");
            return (Criteria) this;
        }

        public Criteria andRoomnumberIsNotNull() {
            addCriterion("roomnumber is not null");
            return (Criteria) this;
        }

        public Criteria andRoomnumberEqualTo(Integer value) {
            addCriterion("roomnumber =", value, "roomnumber");
            return (Criteria) this;
        }

        public Criteria andRoomnumberNotEqualTo(Integer value) {
            addCriterion("roomnumber <>", value, "roomnumber");
            return (Criteria) this;
        }

        public Criteria andRoomnumberGreaterThan(Integer value) {
            addCriterion("roomnumber >", value, "roomnumber");
            return (Criteria) this;
        }

        public Criteria andRoomnumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("roomnumber >=", value, "roomnumber");
            return (Criteria) this;
        }

        public Criteria andRoomnumberLessThan(Integer value) {
            addCriterion("roomnumber <", value, "roomnumber");
            return (Criteria) this;
        }

        public Criteria andRoomnumberLessThanOrEqualTo(Integer value) {
            addCriterion("roomnumber <=", value, "roomnumber");
            return (Criteria) this;
        }

        public Criteria andRoomnumberIn(List<Integer> values) {
            addCriterion("roomnumber in", values, "roomnumber");
            return (Criteria) this;
        }

        public Criteria andRoomnumberNotIn(List<Integer> values) {
            addCriterion("roomnumber not in", values, "roomnumber");
            return (Criteria) this;
        }

        public Criteria andRoomnumberBetween(Integer value1, Integer value2) {
            addCriterion("roomnumber between", value1, value2, "roomnumber");
            return (Criteria) this;
        }

        public Criteria andRoomnumberNotBetween(Integer value1, Integer value2) {
            addCriterion("roomnumber not between", value1, value2, "roomnumber");
            return (Criteria) this;
        }

        public Criteria andRoompriceIsNull() {
            addCriterion("roomprice is null");
            return (Criteria) this;
        }

        public Criteria andRoompriceIsNotNull() {
            addCriterion("roomprice is not null");
            return (Criteria) this;
        }

        public Criteria andRoompriceEqualTo(Double value) {
            addCriterion("roomprice =", value, "roomprice");
            return (Criteria) this;
        }

        public Criteria andRoompriceNotEqualTo(Double value) {
            addCriterion("roomprice <>", value, "roomprice");
            return (Criteria) this;
        }

        public Criteria andRoompriceGreaterThan(Double value) {
            addCriterion("roomprice >", value, "roomprice");
            return (Criteria) this;
        }

        public Criteria andRoompriceGreaterThanOrEqualTo(Double value) {
            addCriterion("roomprice >=", value, "roomprice");
            return (Criteria) this;
        }

        public Criteria andRoompriceLessThan(Double value) {
            addCriterion("roomprice <", value, "roomprice");
            return (Criteria) this;
        }

        public Criteria andRoompriceLessThanOrEqualTo(Double value) {
            addCriterion("roomprice <=", value, "roomprice");
            return (Criteria) this;
        }

        public Criteria andRoompriceIn(List<Double> values) {
            addCriterion("roomprice in", values, "roomprice");
            return (Criteria) this;
        }

        public Criteria andRoompriceNotIn(List<Double> values) {
            addCriterion("roomprice not in", values, "roomprice");
            return (Criteria) this;
        }

        public Criteria andRoompriceBetween(Double value1, Double value2) {
            addCriterion("roomprice between", value1, value2, "roomprice");
            return (Criteria) this;
        }

        public Criteria andRoompriceNotBetween(Double value1, Double value2) {
            addCriterion("roomprice not between", value1, value2, "roomprice");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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