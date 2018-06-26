package com.zrpc.test;

import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.LoadingCache;
import io.protostuff.Tag;
import protostuff.ProtoStuffUtil;

import java.math.BigDecimal;
import java.util.*;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/6/26
 */
public class TestProtoStuff {

    public static void main(String[] args) {
        A a = new A();
        a.setId(1);
        a.setName("zmm");
        a.setAge(18);
        a.setHight(180);
        C c = new C();
        c.setWeight("wer");
        a.setC(c);
        List<C> list = new ArrayList<>();
        list.add(c);
        a.setList(list);
        Map<String,C> map = new HashMap<>();
        map.put("1",c);
        a.setMap(map);
        byte[] data = ProtoStuffUtil.serialize(a);
        System.out.println(Arrays.toString(data));
        B b =ProtoStuffUtil.deserialize(data,B.class);
        System.out.println(JSONObject.toJSONString(b));

    }





    static class A{
        private Integer id;
        private String name;
        private Integer age;
        private Integer hight;

        private List<C> list;

        private Map<String,C> map;

        private C c;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Integer getHight() {
            return hight;
        }

        public void setHight(Integer hight) {
            this.hight = hight;
        }

        public List<C> getList() {
            return list;
        }

        public void setList(List<C> list) {
            this.list = list;
        }

        public Map<String, C> getMap() {
            return map;
        }

        public void setMap(Map<String, C> map) {
            this.map = map;
        }

        public C getC() {
            return c;
        }

        public void setC(C c) {
            this.c = c;
        }
    }

    class B{
        private Integer id;
        private String name;
        private Long agea;
        private Long hight;

        private List<C> list;

        private Map<String,C> map;

        private C c;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getAgea() {
            return agea;
        }

        public void setAgea(Long agea) {
            this.agea = agea;
        }

        public Long getHight() {
            return hight;
        }

        public void setHight(Long hight) {
            this.hight = hight;
        }

        public List<C> getList() {
            return list;
        }

        public void setList(List<C> list) {
            this.list = list;
        }

        public Map<String, C> getMap() {
            return map;
        }

        public void setMap(Map<String, C> map) {
            this.map = map;
        }

        public C getC() {
            return c;
        }

        public void setC(C c) {
            this.c = c;
        }
    }

    static class C{
        private String weight;

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }
    }

}
