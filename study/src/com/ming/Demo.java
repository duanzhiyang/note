package com.ming;

public class Demo {
	public static void main(String[] args)
    {
        staticFunction();
    }

    public static void staticFunction(){
        System.out.println("5");
    }

    static Demo book = new Demo();

    static
    {
        System.out.println("4");
    }

    {
        System.out.println("1");
    }
    Demo()
    {
        System.out.println("2");
        System.out.println(price);
        System.out.println(amount);
    }
    int price = 3;
    
    static int amount = 9;

}
