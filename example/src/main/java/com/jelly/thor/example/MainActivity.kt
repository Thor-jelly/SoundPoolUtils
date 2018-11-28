package com.jelly.thor.example

import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jelly.thor.soundpoolutils.NumberTurnToHz
import com.jelly.thor.soundpoolutils.SoundPoolUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initSound()

        initEvent()

    }

    private fun initEvent() {
        mTv.setOnClickListener {
            val hz = NumberTurnToHz.builder().setNumber(a17).setUnit("元").build().getHz()
            for (s in hz) {
                Log.d("123===", s)
            }
            GlobalScope.launch {
                playSound(0, hz)
            }
        }

    }

    @Synchronized
    private suspend fun playSound(form: Int, hz: MutableList<String>) {
        if (form == 0) {
            SoundPoolUtils.getInstance().play(SoundKey.ALI, 1F)
            delay(1300)
        } else {
            SoundPoolUtils.getInstance().play(SoundKey.WX, 1F)
            delay(1300)
        }
        for (s in hz) {
            when (s) {
                "0" -> {
                    SoundPoolUtils.getInstance().play(SoundKey.NUM_0, 1.1F)
                    delay(500)
                }
                "1" -> {
                    SoundPoolUtils.getInstance().play(SoundKey.NUM_1, 1.1F)
                    delay(500)
                }
                "2" -> {
                    SoundPoolUtils.getInstance().play(SoundKey.NUM_2, 1.1F)
                    delay(500)
                }
                "3" -> {
                    SoundPoolUtils.getInstance().play(SoundKey.NUM_3, 1.1F)
                    delay(500)
                }
                "4" -> {
                    SoundPoolUtils.getInstance().play(SoundKey.NUM_4, 1.1F)
                    delay(500)
                }
                "5" -> {
                    SoundPoolUtils.getInstance().play(SoundKey.NUM_5, 1.1F)
                    delay(500)
                }
                "6" -> {
                    SoundPoolUtils.getInstance().play(SoundKey.NUM_6, 1.1F)
                    delay(500)
                }
                "7" -> {
                    SoundPoolUtils.getInstance().play(SoundKey.NUM_7, 1.1F)
                    delay(500)
                }
                "8" -> {
                    SoundPoolUtils.getInstance().play(SoundKey.NUM_8, 1.1F)
                    delay(500)
                }
                "9" -> {
                    SoundPoolUtils.getInstance().play(SoundKey.NUM_9, 1.1F)
                    delay(500)
                }
                "拾" -> {
                    SoundPoolUtils.getInstance().play(SoundKey.UNIT_SHI, 1.1F)
                    delay(500)
                }
                "佰" -> {
                    SoundPoolUtils.getInstance().play(SoundKey.UNIT_BAI, 1.1F)
                    delay(500)
                }
                "仟" -> {
                    SoundPoolUtils.getInstance().play(SoundKey.UNIT_QIAN, 1.1F)
                    delay(500)
                }
                "万" ->{
                    SoundPoolUtils.getInstance().play(SoundKey.UNIT_WAN, 0.98F)
                    delay(500)
                }
                "亿" -> {
                    SoundPoolUtils.getInstance().play(SoundKey.UNIT_YI, 1.1F)
                    delay(500)
                }
                "元" -> {
                    SoundPoolUtils.getInstance().play(SoundKey.YUAN, 1.1F)
                    delay(500)
                }
                "." -> {
                    SoundPoolUtils.getInstance().play(SoundKey.POINT, 1.1F)
                    delay(500)
                }
            }
        }
    }

    private fun initSound() {
        SoundPoolUtils.getInstance().initHintSing(this, SoundKey.NUM_0, R.raw.zero)
        SoundPoolUtils.getInstance().initHintSing(this, SoundKey.NUM_1, R.raw.one)
        SoundPoolUtils.getInstance().initHintSing(this, SoundKey.NUM_2, R.raw.two)
        SoundPoolUtils.getInstance().initHintSing(this, SoundKey.NUM_3, R.raw.three)
        SoundPoolUtils.getInstance().initHintSing(this, SoundKey.NUM_4, R.raw.four)
        SoundPoolUtils.getInstance().initHintSing(this, SoundKey.NUM_5, R.raw.five)
        SoundPoolUtils.getInstance().initHintSing(this, SoundKey.NUM_6, R.raw.six)
        SoundPoolUtils.getInstance().initHintSing(this, SoundKey.NUM_7, R.raw.seven)
        SoundPoolUtils.getInstance().initHintSing(this, SoundKey.NUM_8, R.raw.eight)
        SoundPoolUtils.getInstance().initHintSing(this, SoundKey.NUM_9, R.raw.nine)

        SoundPoolUtils.getInstance().initHintSing(this, SoundKey.UNIT_SHI, R.raw.unit_shi)
        SoundPoolUtils.getInstance().initHintSing(this, SoundKey.UNIT_BAI, R.raw.unit_bai)
        SoundPoolUtils.getInstance().initHintSing(this, SoundKey.UNIT_QIAN, R.raw.unit_qian)
        SoundPoolUtils.getInstance().initHintSing(this, SoundKey.UNIT_WAN, R.raw.unit_wan)
        SoundPoolUtils.getInstance().initHintSing(this, SoundKey.UNIT_YI, R.raw.unit_yi)

        SoundPoolUtils.getInstance().initHintSing(this, SoundKey.WX, R.raw.wx_arrival_account)
        SoundPoolUtils.getInstance().initHintSing(this, SoundKey.ALI, R.raw.ali_arrival_account)

        SoundPoolUtils.getInstance().initHintSing(this, SoundKey.YUAN, R.raw.yuan)
        SoundPoolUtils.getInstance().initHintSing(this, SoundKey.POINT, R.raw.point)
    }
}
