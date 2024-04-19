package cn.srd.library.java.orm.mybatis.flex.base.test;

import cn.srd.library.java.orm.contract.model.base.BO;
import cn.srd.library.java.orm.mybatis.flex.base.tool.ColumnNameGetter;

public class GenericClass {

    public static <T extends BO> void someMethod2(ColumnNameGetter<T> list) {

    }

    public static <T extends BO> void someMethod3(ColumnNameGetter<? extends BO>... list) {

    }

    public static void main(String[] args) {
        GenericClass.someMethod2(StudentCourseBO::getName);
        GenericClass.someMethod3((StudentCourseBO scb) -> scb.getName(), (StudentHobbyBO shb) -> shb.getName());
    }

}