public class Change {
    public static void main(String[] args) {
        // 李四的信息
        int age2 = 18;
        double price2 = 3.14;
        String name2 = "李四";
        char aaa2 = 'a';
        boolean aab2 = true;
        //1111111
        // 张三的信息
        int age1 = 18;
        double price1 = 3.14;
        String name1 = "张三";
        char aaa1 = 'a';
        boolean aab1 = true;

        // 关键：添加打印语句，IDE就不会提示“变量未使用”的警告了
        System.out.println("张三的信息：");
        System.out.println("年龄：" + age1);
        System.out.println("价格：" + price1);
        System.out.println("姓名：" + name1);
        System.out.println("字符：" + aaa1);
        System.out.println("布尔值：" + aab1);

        System.out.println("\n李四的信息：");
        System.out.println("年龄：" + age2);
        System.out.println("价格：" + price2);
        System.out.println("姓名：" + name2);
        System.out.println("字符：" + aaa2);
        System.out.println("布尔值：" + aab2);
    }
}