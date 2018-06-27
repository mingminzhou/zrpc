package com.zrpc.test;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/6/26
 */
public class TestClient {

    public static void main(String[] args) {
        System.out.println(TestClient.class.getCanonicalName());
        System.out.println(TestClient.class.getName());
        System.out.println(TestClient.class.getSimpleName());


        System.out.println("=========================");


        System.out.println(A.class.getCanonicalName());
        System.out.println(A.class.getName());
        System.out.println(A.class.getSimpleName());
    }

    class A {

    }
}
