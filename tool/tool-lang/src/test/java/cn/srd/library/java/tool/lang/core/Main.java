// package cn.srd.library.tool.lang.core;
//
// /**
//  * generic enum example, copy from <a href="https://github.com/cmoine/generic-enums">generic-enums</a>
//  *
//  * @author wjm
//  * @since 2023-03-23 21:34:12
//  */
// class MyParameterSer {
//     int getInt(Parameters<Integer> param) {
//         // Use you deserialization mechanism...
//     }
//
//     void setInt(Parameters<Integer> param, int value) {
//         // Use you Serialization mechanism...
//     }
//
//     // And so on for all supported types
// }
//
// public class Main {
//     public static void main(String[] args) {
//         MyParameterSer parameterSer = new MyParameterSer();
//         parameterSer.setInt(ParametersExt.INT, 99);
//         parameterSer.setInt(ParametersExt.BOOLEAN, 99); // ends up in a compilation error !
//     }
// }