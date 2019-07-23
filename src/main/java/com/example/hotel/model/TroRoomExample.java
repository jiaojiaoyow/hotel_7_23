package com.example.hotel.model;

import java.util.ArrayList;
import java.util.List;

public class TroRoomExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TroRoomExample() {
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

        public Criteria andRoomimageIsNull() {
            addCriterion("roomimage is null");
            return (Criteria) this;
        }

        public Criteria andRoomimageIsNotNull() {
            addCriterion("roomimage is not null");
            return (Criteria) this;
        }

        public Criteria andRoomimageEqualTo(String value) {
            addCriterion("roomimage =", value, "roomimage");
            return (Criteria) this;
        }

        public Criteria andRoomimageNotEqualTo(String value) {
            addCriterion("roomimage <>", value, "roomimage");
            return (Criteria) this;
        }

        public Criteria andRoomimageGreaterThan(String value) {
            addCriterion("roomimage >", value, "roomimage");
            return (Criteria) this;
        }

        public Criteria andRoomimageGreaterThanOrEqualTo(String value) {
            addCriterion("roomimage >=", value, "roomimage");
            return (Criteria) this;
        }

        public Criteria andRoomimageLessThan(String value) {
            addCriterion("roomimage <", value, "roomimage");
            return (Criteria) this;
        }

        public Criteria andRoomimageLessThanOrEqualTo(String value) {
            addCriterion("roomimage <=", value, "roomimage");
            return (Criteria) this;
        }

        public Criteria andRoomimageLike(String value) {
            addCriterion("roomimage like", value, "roomimage");
            return (Criteria) this;
        }

        public Criteria andRoomimageNotLike(String value) {
            addCriterion("roomimage not like", value, "roomimage");
            return (Criteria) this;
        }

        public Criteria andRoomimageIn(List<String> values) {
            addCriterion("roomimage in", values, "roomimage");
            return (Criteria) this;
        }

        public Criteria andRoomimageNotIn(List<String> values) {
            addCriterion("roomimage not in", values, "roomimage");
            return (Criteria) this;
        }

        public Criteria andRoomimageBetween(String value1, String value2) {
            addCriterion("roomimage between", value1, value2, "roomimage");
            return (Criteria) this;
        }

        public Criteria andRoomimageNotBetween(String value1, String value2) {
            addCriterion("roomimage not between", value1, value2, "roomimage");
            return (Criteria) this;
        }

        public Criteria andRoomareaIsNull() {
            addCriterion("roomarea is null");
            return (Criteria) this;
        }

        public Criteria andRoomareaIsNotNull() {
            addCriterion("roomarea is not null");
            return (Criteria) this;
        }

        public Criteria andRoomareaEqualTo(String value) {
            addCriterion("roomarea =", value, "roomarea");
            return (Criteria) this;
        }

        public Criteria andRoomareaNotEqualTo(String value) {
            addCriterion("roomarea <>", value, "roomarea");
            return (Criteria) this;
        }

        public Criteria andRoomareaGreaterThan(String value) {
            addCriterion("roomarea >", value, "roomarea");
            return (Criteria) this;
        }

        public Criteria andRoomareaGreaterThanOrEqualTo(String value) {
            addCriterion("roomarea >=", value, "roomarea");
            return (Criteria) this;
        }

        public Criteria andRoomareaLessThan(String value) {
            addCriterion("roomarea <", value, "roomarea");
            return (Criteria) this;
        }

        public Criteria andRoomareaLessThanOrEqualTo(String value) {
            addCriterion("roomarea <=", value, "roomarea");
            return (Criteria) this;
        }

        public Criteria andRoomareaLike(String value) {
            addCriterion("roomarea like", value, "roomarea");
            return (Criteria) this;
        }

        public Criteria andRoomareaNotLike(String value) {
            addCriterion("roomarea not like", value, "roomarea");
            return (Criteria) this;
        }

        public Criteria andRoomareaIn(List<String> values) {
            addCriterion("roomarea in", values, "roomarea");
            return (Criteria) this;
        }

        public Criteria andRoomareaNotIn(List<String> values) {
            addCriterion("roomarea not in", values, "roomarea");
            return (Criteria) this;
        }

        public Criteria andRoomareaBetween(String value1, String value2) {
            addCriterion("roomarea between", value1, value2, "roomarea");
            return (Criteria) this;
        }

        public Criteria andRoomareaNotBetween(String value1, String value2) {
            addCriterion("roomarea not between", value1, value2, "roomarea");
            return (Criteria) this;
        }

        public Criteria andRoomwifiIsNull() {
            addCriterion("roomwifi is null");
            return (Criteria) this;
        }

        public Criteria andRoomwifiIsNotNull() {
            addCriterion("roomwifi is not null");
            return (Criteria) this;
        }

        public Criteria andRoomwifiEqualTo(String value) {
            addCriterion("roomwifi =", value, "roomwifi");
            return (Criteria) this;
        }

        public Criteria andRoomwifiNotEqualTo(String value) {
            addCriterion("roomwifi <>", value, "roomwifi");
            return (Criteria) this;
        }

        public Criteria andRoomwifiGreaterThan(String value) {
            addCriterion("roomwifi >", value, "roomwifi");
            return (Criteria) this;
        }

        public Criteria andRoomwifiGreaterThanOrEqualTo(String value) {
            addCriterion("roomwifi >=", value, "roomwifi");
            return (Criteria) this;
        }

        public Criteria andRoomwifiLessThan(String value) {
            addCriterion("roomwifi <", value, "roomwifi");
            return (Criteria) this;
        }

        public Criteria andRoomwifiLessThanOrEqualTo(String value) {
            addCriterion("roomwifi <=", value, "roomwifi");
            return (Criteria) this;
        }

        public Criteria andRoomwifiLike(String value) {
            addCriterion("roomwifi like", value, "roomwifi");
            return (Criteria) this;
        }

        public Criteria andRoomwifiNotLike(String value) {
            addCriterion("roomwifi not like", value, "roomwifi");
            return (Criteria) this;
        }

        public Criteria andRoomwifiIn(List<String> values) {
            addCriterion("roomwifi in", values, "roomwifi");
            return (Criteria) this;
        }

        public Criteria andRoomwifiNotIn(List<String> values) {
            addCriterion("roomwifi not in", values, "roomwifi");
            return (Criteria) this;
        }

        public Criteria andRoomwifiBetween(String value1, String value2) {
            addCriterion("roomwifi between", value1, value2, "roomwifi");
            return (Criteria) this;
        }

        public Criteria andRoomwifiNotBetween(String value1, String value2) {
            addCriterion("roomwifi not between", value1, value2, "roomwifi");
            return (Criteria) this;
        }

        public Criteria andRoombedIsNull() {
            addCriterion("roombed is null");
            return (Criteria) this;
        }

        public Criteria andRoombedIsNotNull() {
            addCriterion("roombed is not null");
            return (Criteria) this;
        }

        public Criteria andRoombedEqualTo(String value) {
            addCriterion("roombed =", value, "roombed");
            return (Criteria) this;
        }

        public Criteria andRoombedNotEqualTo(String value) {
            addCriterion("roombed <>", value, "roombed");
            return (Criteria) this;
        }

        public Criteria andRoombedGreaterThan(String value) {
            addCriterion("roombed >", value, "roombed");
            return (Criteria) this;
        }

        public Criteria andRoombedGreaterThanOrEqualTo(String value) {
            addCriterion("roombed >=", value, "roombed");
            return (Criteria) this;
        }

        public Criteria andRoombedLessThan(String value) {
            addCriterion("roombed <", value, "roombed");
            return (Criteria) this;
        }

        public Criteria andRoombedLessThanOrEqualTo(String value) {
            addCriterion("roombed <=", value, "roombed");
            return (Criteria) this;
        }

        public Criteria andRoombedLike(String value) {
            addCriterion("roombed like", value, "roombed");
            return (Criteria) this;
        }

        public Criteria andRoombedNotLike(String value) {
            addCriterion("roombed not like", value, "roombed");
            return (Criteria) this;
        }

        public Criteria andRoombedIn(List<String> values) {
            addCriterion("roombed in", values, "roombed");
            return (Criteria) this;
        }

        public Criteria andRoombedNotIn(List<String> values) {
            addCriterion("roombed not in", values, "roombed");
            return (Criteria) this;
        }

        public Criteria andRoombedBetween(String value1, String value2) {
            addCriterion("roombed between", value1, value2, "roombed");
            return (Criteria) this;
        }

        public Criteria andRoombedNotBetween(String value1, String value2) {
            addCriterion("roombed not between", value1, value2, "roombed");
            return (Criteria) this;
        }

        public Criteria andRoombreakfastIsNull() {
            addCriterion("roombreakfast is null");
            return (Criteria) this;
        }

        public Criteria andRoombreakfastIsNotNull() {
            addCriterion("roombreakfast is not null");
            return (Criteria) this;
        }

        public Criteria andRoombreakfastEqualTo(String value) {
            addCriterion("roombreakfast =", value, "roombreakfast");
            return (Criteria) this;
        }

        public Criteria andRoombreakfastNotEqualTo(String value) {
            addCriterion("roombreakfast <>", value, "roombreakfast");
            return (Criteria) this;
        }

        public Criteria andRoombreakfastGreaterThan(String value) {
            addCriterion("roombreakfast >", value, "roombreakfast");
            return (Criteria) this;
        }

        public Criteria andRoombreakfastGreaterThanOrEqualTo(String value) {
            addCriterion("roombreakfast >=", value, "roombreakfast");
            return (Criteria) this;
        }

        public Criteria andRoombreakfastLessThan(String value) {
            addCriterion("roombreakfast <", value, "roombreakfast");
            return (Criteria) this;
        }

        public Criteria andRoombreakfastLessThanOrEqualTo(String value) {
            addCriterion("roombreakfast <=", value, "roombreakfast");
            return (Criteria) this;
        }

        public Criteria andRoombreakfastLike(String value) {
            addCriterion("roombreakfast like", value, "roombreakfast");
            return (Criteria) this;
        }

        public Criteria andRoombreakfastNotLike(String value) {
            addCriterion("roombreakfast not like", value, "roombreakfast");
            return (Criteria) this;
        }

        public Criteria andRoombreakfastIn(List<String> values) {
            addCriterion("roombreakfast in", values, "roombreakfast");
            return (Criteria) this;
        }

        public Criteria andRoombreakfastNotIn(List<String> values) {
            addCriterion("roombreakfast not in", values, "roombreakfast");
            return (Criteria) this;
        }

        public Criteria andRoombreakfastBetween(String value1, String value2) {
            addCriterion("roombreakfast between", value1, value2, "roombreakfast");
            return (Criteria) this;
        }

        public Criteria andRoombreakfastNotBetween(String value1, String value2) {
            addCriterion("roombreakfast not between", value1, value2, "roombreakfast");
            return (Criteria) this;
        }

        public Criteria andRoomnumIsNull() {
            addCriterion("roomnum is null");
            return (Criteria) this;
        }

        public Criteria andRoomnumIsNotNull() {
            addCriterion("roomnum is not null");
            return (Criteria) this;
        }

        public Criteria andRoomnumEqualTo(String value) {
            addCriterion("roomnum =", value, "roomnum");
            return (Criteria) this;
        }

        public Criteria andRoomnumNotEqualTo(String value) {
            addCriterion("roomnum <>", value, "roomnum");
            return (Criteria) this;
        }

        public Criteria andRoomnumGreaterThan(String value) {
            addCriterion("roomnum >", value, "roomnum");
            return (Criteria) this;
        }

        public Criteria andRoomnumGreaterThanOrEqualTo(String value) {
            addCriterion("roomnum >=", value, "roomnum");
            return (Criteria) this;
        }

        public Criteria andRoomnumLessThan(String value) {
            addCriterion("roomnum <", value, "roomnum");
            return (Criteria) this;
        }

        public Criteria andRoomnumLessThanOrEqualTo(String value) {
            addCriterion("roomnum <=", value, "roomnum");
            return (Criteria) this;
        }

        public Criteria andRoomnumLike(String value) {
            addCriterion("roomnum like", value, "roomnum");
            return (Criteria) this;
        }

        public Criteria andRoomnumNotLike(String value) {
            addCriterion("roomnum not like", value, "roomnum");
            return (Criteria) this;
        }

        public Criteria andRoomnumIn(List<String> values) {
            addCriterion("roomnum in", values, "roomnum");
            return (Criteria) this;
        }

        public Criteria andRoomnumNotIn(List<String> values) {
            addCriterion("roomnum not in", values, "roomnum");
            return (Criteria) this;
        }

        public Criteria andRoomnumBetween(String value1, String value2) {
            addCriterion("roomnum between", value1, value2, "roomnum");
            return (Criteria) this;
        }

        public Criteria andRoomnumNotBetween(String value1, String value2) {
            addCriterion("roomnum not between", value1, value2, "roomnum");
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