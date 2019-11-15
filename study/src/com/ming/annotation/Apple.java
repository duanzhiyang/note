package com.ming.annotation;

public class Apple {
    @FruitProvider(id = 1, name = "陕西红富士集团", address = "陕西省西安市延安路")
    private String appleProvider;
    public void setAppleProvider(String appleProvider) {
        this.appleProvider = appleProvider;
    }
    public String getAppleProvider() {
        return appleProvider;
    }

    public static void main(String[] args) {
        FruitInfoUtil.getFruitInfo(Apple.class);
/***********输出结果***************/
// 供应商编号：1 供应商名称：陕西红富士集团 供应商地址：陕西省西安市延
    }
}
