package lesson07.MyUnit;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MyTest {

    private boolean anotAfterSuite = false;
    private boolean anotBeforeSuite = false;
    private Method mAfterSuite;
    private Method mBeforeSuite;
    ;

    public static void start(Class c) {
        new MyTest().init(c);
    }

    public  void init(Class c) {

        try {


            Object obj =  c.getConstructor().newInstance();


            Method[] methods = c.getDeclaredMethods();
            for (Method m : methods) {
                testAnnotation(m);
            }
            mBeforeSuite.invoke(obj,3);
            List<Method> mTest = new ArrayList<>();
            for (Method m : methods) {

                if(m.isAnnotationPresent(Test.class)){
                    mTest.add(m);
//                    System.out.println(">:"+ m.getAnnotation(Test.class).priority());
//                    m.invoke(obj,1,2);
                }

            }

            mTest.sort(new Comparator<Method>() {
                @Override
                public int compare(Method s1, Method s2) {
                    return s1.getAnnotation(Test.class).priority() - s2.getAnnotation(Test.class).priority() ;
                }
            });

            for (Method m : mTest) {
               String name =  m.getName();
               int num1 = 5;
               int num2 = 2;
               int res = (int) m.invoke(obj,num1,num2);
               int priority =  m.getAnnotation(Test.class).priority();
                System.out.printf("priority = %d  %d %s %d  = %d \n",priority,num1,name,num2,res);
            }

          //  System.out.println(mAfterSuite.getName());
          mAfterSuite.invoke(obj);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void testAnnotation(Method m) {

        Annotation[] annotation = m.getAnnotations();
        for (int i = 0; i < annotation.length; i++) {

            if (annotation[i].annotationType().getCanonicalName().equals("lesson07.MyUnit.AfterSuite")) {

                if (anotAfterSuite) {
                  throw new RuntimeException("Double AfterSuite");
                }
                anotAfterSuite = true;
                mAfterSuite = m;
            }

            if (annotation[i].annotationType().getCanonicalName().equals("lesson07.MyUnit.BeforeSuite")) {


                if (anotBeforeSuite) {
                    throw new RuntimeException("Double BeforeSuite");
                }

                anotBeforeSuite = true;
                mBeforeSuite = m;
            }
        }


    }

    public void start1(Class c) {
        try {
//        if (!c.isAnnotation()) {
//            throw new RuntimeException("Не возможно создать таблицу, не помечено аннотацией @Table");
//        }

            //Method[] methods = c.getMethods();
            Method[] methods = c.getDeclaredMethods();

            List<Method> mTest = new ArrayList<>();
            for (Method m : methods) {
                if (m.isAnnotationPresent(Test.class)) {
                    mTest.add(m);
                    System.out.println("Описание :" + m.getAnnotation(Test.class).priority());
                    //m.invoke(c);

                    System.out.println(m.getName());
                    m.invoke(null);


                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
