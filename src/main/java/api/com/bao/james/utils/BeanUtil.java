package api.com.bao.james.utils;

import com.google.common.collect.Lists;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dozer.DozerBeanMapper;

/**
 * @author James Bao
 * @date
 */
public class BeanUtil {

    private static final DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    public static void copy(Object source, Object target) {

        if (source == null || target == null) {
            return;
        }

        dozerBeanMapper.map(source, target);
    }

    public static <T> T map(Object source, Class<T> clazz) {

        if (source == null || clazz == null) {
            return null;
        }

        return dozerBeanMapper.map(source, clazz);
    }

    public static <T> List<T> copyList(List source, Class<T> target) {

        if (target.isInterface() || source == null || source.isEmpty()) {
            return Lists.newArrayList();
        }

        List<T> targetList = Lists.newArrayList();

        source.forEach(a -> {
            T targetObject = null;
            if (a != null) {
                try {
                    targetObject = target.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                copy(a, targetObject);
            }
            targetList.add(targetObject);
        });

        return targetList;
    }

    public static <T> List<T> mapList(List source, Class<T> target) {

        if (target.isInterface() || source == null || source.isEmpty()) {
            return Lists.newArrayList();
        }

        List<T> targetList = Lists.newArrayList();

        source.forEach(a -> {
            targetList.add(map(a, target));
        });

        return targetList;
    }

    /**
     * compareFields 用法 demo
     *
     *  // 比较s1和s2不同的属性值，其中id忽略比较
     *  Map<String, List<Object>> compareResult = compareFields(p1, p2, new String[]{"projectName"});
     *  System.out.println("s1和s2共有" + compareResult.size() + "个属性值不同（不包括id）");
     *
     *  Set<String> keySet = compareResult.keySet();
     *  for(String key : keySet){
     *      List<Object> list = compareResult.get(key);
     *      System.out.println(">>>  s1的" + key + "为" + list.get(0) + "，s2的" + key + "为" + list.get(1));
     *  }
     */

    /**
     * 比较两个实体属性值，
     * 返回一个 map 以有差异的属性名为 key，
     * value 为一个 list 分别存 obj1,obj2 此属性名的值
     *
     * @param obj1      进行属性比较的对象1
     * @param obj2      进行属性比较的对象2
     * @param ignoreArr 选择忽略比较的属性数组
     * @return 属性差异比较结果map
     */
    public static Map<String, List<Object>> compareFields(Object obj1, Object obj2, String[] ignoreArr) {
        try {
            Map<String, List<Object>> map = new HashMap<>(30);
            List<String> ignoreList = null;
            if (ignoreArr != null && ignoreArr.length > 0) {
                // array转化为list
                ignoreList = Arrays.asList(ignoreArr);
            }
            // 只有两个对象都是同一类型的才有可比性
            if (obj1.getClass() == obj2.getClass()) {
                Class clazz = obj1.getClass();
                // 获取object的属性描述
                PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz,
                        Object.class).getPropertyDescriptors();
                // 这里就是所有的属性了
                for (PropertyDescriptor pd : pds) {
                    // 属性名
                    String name = pd.getName();
                    // 如果当前属性选择忽略比较，跳到下一次循环
                    if (ignoreList != null && ignoreList.contains(name)) {
                        continue;
                    }
                    // get方法
                    Method readMethod = pd.getReadMethod();
                    // 在obj1上调用get方法等同于获得obj1的属性值
                    Object o1 = readMethod.invoke(obj1);
                    // 在obj2上调用get方法等同于获得obj2的属性值
                    Object o2 = readMethod.invoke(obj2);
                    if (o1 instanceof Timestamp) {
                        o1 = new Date(((Timestamp) o1).getTime());
                    }
                    if (o2 instanceof Timestamp) {
                        o2 = new Date(((Timestamp) o2).getTime());
                    }
                    if (o1 == null && o2 == null) {
                        continue;
                    } else if (o1 == null && o2 != null) {
                        List<Object> list = new ArrayList<>();
                        list.add(o1);
                        list.add(o2);
                        map.put(name, list);
                        continue;
                    }
                    // 比较这两个值是否相等,不等就可以放入map了
                    if (!o1.equals(o2)) {
                        List<Object> list = new ArrayList<>();
                        list.add(o1);
                        list.add(o2);
                        map.put(name, list);
                    }
                }
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
