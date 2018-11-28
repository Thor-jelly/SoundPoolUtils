package com.jelly.thor.soundpoolutils

import android.text.TextUtils.replace
import java.lang.StringBuilder
import java.util.Collections.replaceAll
import java.util.regex.Pattern

/**
 * 类描述：数字转汉字,builder设计模式<br/>
 * 创建人：吴冬冬<br/>
 * 创建时间：2018/11/22 18:38 <br/>
 */
class NumberTurnToHz private constructor(private val mNumber: String,
                                         private val mUnit: String) {

    companion object {
        const val CHINESE_YUAN = '元'
        const val CHINESE_SHI = '拾'
        const val CHINESE_BAI = '佰'
        const val CHINESE_QIAN = '仟'
        const val CHINESE_WAN = '万'
        const val CHINESE_YI = '亿'
        const val DOT = "."

        private val CHINESE_UNIT by lazy {
            charArrayOf('元', '拾', '佰', '仟', '万', '拾', '佰', '仟', '亿', '拾', '佰', '仟')
        }

        private lateinit var mBuilder: Builder

        @JvmStatic
        fun builder(): Builder {
            mBuilder = Builder()
            return mBuilder
        }
    }

    /**
     * 获取汉字
     */
    fun getHz(): MutableList<String> {
        val resultList = mutableListOf<String>()
        if (mNumber.isNotEmpty()) {
            //获取读到金额
            resultList.addAll(getReadMoney(mNumber))
        }

        if (mUnit.isNotEmpty()) {
            //添加末尾单位
            resultList.add(mUnit)
        }

        return resultList
    }

    /**
     * 获取读到的金额
     */
    private fun getReadMoney(numberStr: String): MutableList<String> {
        val resultList = mutableListOf<String>()
        //1.判断是否为空
        if (numberStr.isEmpty()) {
            return resultList
        }
        //2.判断是否有小数点
        if (numberStr.contains(DOT)) {
            val split = numberStr.split(DOT)
            //整数
            val intStr = split[0]
            //小数
            val decimalStr = split[1]
            //添加整数部分
            resultList.addAll(getIntPart(intStr))
            //添加小数部分
            val decimalStrList = getDecimalPart(decimalStr)
            if (decimalStrList.isNotEmpty()) {
                resultList.add(DOT)
                resultList.addAll(decimalStrList)
            }
        } else {
            //整数
            resultList.addAll(getIntPart(numberStr))
        }
        return resultList
    }

    /**
     * 获取小数部分
     */
    private fun getDecimalPart(decimalStr: String): MutableList<String> {
        val resultList = mutableListOf<String>()
        //移除末尾0
        val regex = "\\d*[^0]+"
        val p = Pattern.compile(regex)
        val m = p.matcher(decimalStr)
        m.takeIf {
            m.find()
        }?.run {
            //decimalStr
            for (c in m.group().toCharArray()) {
                resultList.add(c.toString())
            }
            return resultList
        } ?: return resultList
    }

    /**
     * 获得整数部分
     */
    private fun getIntPart(intStr: String): MutableList<String> {
        val resultList = mutableListOf<String>()

        for (c in getIntPartStr(intStr.toInt()).toCharArray()) {
            resultList.add(c.toString())
        }

        return resultList
    }

    /**
     * 获取整数部分的str拼接
     */
    private fun getIntPartStr(toInt: Int): String {
        //1.判断是否为0
        if (toInt == 0) {
            return "0"
        }

        //2.判断是否是十
        if (toInt == 10) {
            return "拾"
        }

        //3.判断是否是十几
        if (toInt in 11..19) {
            return "拾${toInt % 10}"
        }

        //4.如果都不是开始组合所有数据，从最小位数往最大位数慢慢拆分
        var newResult = ""
        val intStr = toInt.toString().reversed()
        val length = intStr.length

        var resultStr = ""
        var i = 0
        var changeInt = toInt
        while (changeInt > 0) {
            //添加单位
            resultStr = "${CHINESE_UNIT[i++]}$resultStr"
            //添加从低到高的数字
            resultStr = "${(changeInt % 10)}$resultStr"
            changeInt /= 10
        }

        //1万0仟0佰0拾0元
        //5.去除特殊情况的0000->0 和 0百->百
        return resultStr
                .replace(Regex("0[仟佰拾]+"), "0")//千百十为0 转换成""
                .replace((Regex("0{4}[亿万元]")), "")//0000万 -> ""
                .replace((Regex("0+")), "0")//000 -> 0
                .replace(Regex("(\\d[仟佰拾]+)0([亿万元])"), "$1$2")//1仟000万 -> 1仟万
                .replace(Regex("0?元"), "")//0元／元 -> ""
    }


    class Builder {
        private var unit: String = ""
        private var number: String = ""
        /**
         * 设置单位
         */
        fun setUnit(unit: String): Builder {
            this.unit = unit
            return this
        }

        /**
         * 设置数字，最大9位数
         */
        fun setNumber(number: String): Builder {
            this.number = number
            return this
        }

        fun build(): NumberTurnToHz {
            return NumberTurnToHz(number, unit)
        }
    }
}
