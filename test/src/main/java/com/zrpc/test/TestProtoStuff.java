package com.zrpc.test;

import com.alibaba.fastjson.JSONObject;
import io.protostuff.Tag;
import protostuff.ProtoStuffUtil;

import java.math.BigDecimal;
import java.util.Arrays;

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
        byte[] data = ProtoStuffUtil.serialize(a);
        System.out.println(Arrays.toString(data));
        B b =ProtoStuffUtil.deserialize(data,B.class);
        System.out.println(JSONObject.toJSONString(b));
        double l = 1d;
        int i = (int) l;
    }





    static class A{
        private Integer id;
        private String name;
        private Integer age;
        private Integer hight;

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
    }

    class B{
        private Integer id;
        private String name;
        private Long agea;
        private Long hight;

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
    }

}
