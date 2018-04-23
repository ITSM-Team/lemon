package com.mongodb.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.util.DBObjectUtils;
import org.springframework.util.Assert;

import com.mossle.core.query.MatchType;
import com.mossle.core.query.PropertyFilter;

public class MongodbQueryUtil {
	 /**
     * 按属性条件参数创建Criterion,辅助函数.
     * 
     * @param propertyName
     *            String
     * @param propertyValue
     *            Object
     * @param matchType
     *            MatchType
     * @return Criterion
     */
	public static Criteria buildCriterion(String propertyName,
            Object propertyValue, MatchType matchType) {
        Assert.hasText(propertyName, "propertyName不能为空");

        Criteria criterion=new Criteria();
        // 根据MatchType构造criterion
        switch (matchType) {
        case EQ:
           // criterion = Restrictions.eq(propertyName, propertyValue);
        	criterion=criterion.where(propertyName).is(propertyValue);
            break;

        case NOT:
           // criterion = Restrictions.ne(propertyName, propertyValue);
        	criterion=criterion.where(propertyName).ne(propertyName);
            break;

        case LIKE:
//            criterion = Restrictions.like(propertyName, (String) propertyValue,
//                    MatchMode.ANYWHERE);
            criterion=criterion.where(propertyName).regex(propertyValue.toString());
            break;

        case LE:
//            criterion = Restrictions.le(propertyName, propertyValue);
            criterion=criterion.where(propertyName).lte(propertyValue);
            break;

        case LT:
          //  criterion = Restrictions.lt(propertyName, propertyValue);
            criterion=criterion.where(propertyName).lt(propertyValue);
            break;

        case GE:
           // criterion = Restrictions.ge(propertyName, propertyValue);
            criterion =criterion.where(propertyName).gte(propertyValue);
            break;

        case GT:
           // criterion = Restrictions.gt(propertyName, propertyValue);
            criterion=criterion.where(propertyName).gt(propertyValue);
            break;

        case IN:
//            criterion = Restrictions.in(propertyName,
//                    (Collection) propertyValue);
            criterion=criterion.where(propertyName).in( (Collection) propertyValue);
            break;

        case INL:
            //criterion = Restrictions.isNull(propertyName);
            criterion=criterion.where(propertyName).not();
            break;

        case NNL:
            //criterion = Restrictions.isNotNull(propertyName);
            criterion=criterion.where(propertyName).ne("").ne(null);
            break;

        default:
           // criterion = Restrictions.eq(propertyName, propertyValue);
            criterion=criterion.where(propertyName).is(propertyValue);
            break;
        }

        return criterion;
    }
    
    /**
     * 按属性条件列表创建Criterion数组,辅助函数.
     * 
     * @param filters
     *            List
     * @return Criterion[]
     */
    public static Map<String,List<Criteria>> buildCriterion(List<PropertyFilter> filters) {
    	Map<String,List<Criteria>> criterionMap = new HashMap<String,List<Criteria>>();
    	List<Criteria> andlist=new ArrayList<Criteria>();
    	List<Criteria> orlist=new ArrayList<Criteria>();
        for (PropertyFilter filter : filters) {
            // 只有一个属性需要比较的情况.
            if (!filter.hasMultiProperties()) {
            	Criteria criterion = buildCriterion(filter.getPropertyName(),
                        filter.getMatchValue(), filter.getMatchType());
            	andlist.add(criterion);
            } else {
                // 包含多个属性需要比较的情况,进行or处理.                
                for (String param : filter.getPropertyNames()) {
                	Criteria criterion = buildCriterion(param,
                            filter.getMatchValue(), filter.getMatchType());
                	orlist.add(criterion);
                }
            }
        }
        criterionMap.put("and", andlist);
        criterionMap.put("or", orlist);
        return criterionMap;
    }
}
