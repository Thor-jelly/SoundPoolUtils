package com.jelly.thor.soundpoolutils

import org.junit.Test

import org.junit.Assert.*

/**
 * 类描述：//TODO:(这里用一句话描述这个方法的作用)    <br></br>
 * 创建人：吴冬冬<br></br>
 * 创建时间：2018/11/28 16:06 <br></br>
 */
class NumberTurnToHzTest {

    @Test
    fun getHz(){
        val a1 = "0"
        val a2 = "10"
        val a3 = "13"
        val a4 = "20"
        val a5 = "100"
        val a6 = "101"
        val a7 = "1000.01"
        val a8 = "1001"
        val a9 = "1010"
        val a10 = "10000"
        val a11 = "10001"//1万01元
        val a18 = "10301"//1万03佰01元
        val a12 = "100010001"//1亿01万01元
        val a13 = "100000001"//1亿01元
        val a14 = "10000000"//1仟万元
        val a15 = "10000001"//1仟万01元
        val a16 = "10010001"//1仟01万01元
        val a17 = "11000001"//1仟1佰万01元

        val b = NumberTurnToHz.builder()
            .setNumber(a7)
            .setUnit("元")
            .build()

        for (s in b.getHz()) {
            System.out.print(s)
        }

    }
}