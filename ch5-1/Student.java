public class Student{
    // 书上太多了，麻烦，就写三个。 可根据姓名、年龄查询。
    private static final int STUDENT_NUM = 5;
    private String name;
    int age;
    String gender;
    public Student(){
    }
    public Student(String name,int age,String gender){
        setName(name);
        setAge(age);
        setGender(gender);
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    public void setGender(String gender){
        this.gender=gender;
    }
    public String getGender(){
        return this.gender;
    }
    public void setAge(int age){
        this.age=age;
    }
    public int getAge(){
        return this.age;
    }
    public void printInfo(){
        System.out.println("学生信息  姓名:"+name+" 性别:"+gender+" 年龄:"+age);
    }
    public static void main(String[] args){
        
    }
    private static void (test2){
        Student[] students = new Student[5];
    }
    private static void test1(){
        Student stu1 = new Student();
        stu1.setAge(15);
        stu1.setName("小明");
        stu1.setGender("男");
        stu1.printInfo();
        Student stu2 = new Student("小红",12,"女");
        stu2.printInfo();
        System.out.println("学生1信息  姓名:"+stu1.getName()+" 性别:"+stu1.getGender()+" 年龄:"+stu1.getAge());
    }
}