package com.jelly.thor.soundpoolutils

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.util.SparseIntArray

/**
 * 类描述：声音播放工具<br/>
 * 创建人：吴冬冬<br/>
 * 创建时间：2018/11/22 14:51 <br/>
 */
class SoundPoolUtils {
    //    private constructor(){
//        throw UnsupportedOperationException("该类是单例模式")
//    }

    companion object {
        private val mSA = SparseIntArray()
        private var sSoundPool: SoundPool? = null
        private var INSTANCE: SoundPoolUtils? = null

        @Synchronized
        @JvmStatic
        fun getInstance(): SoundPoolUtils {
            return when (INSTANCE) {
                null -> {
                    INSTANCE = SoundPoolUtils()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        //设置描述音频流信息的属性
                        val abs = AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .build()
                        sSoundPool = SoundPool.Builder()
                            .setMaxStreams(1)//同时播放的流的最大数量
                            .setAudioAttributes(abs)
                            .build()
                    } else {
                        /*
                               1.maxStreams :允许同时播放的流的最大值
                               2.streamType ：音频流的类型描述，在Audiomanager中有种类型声明，游戏应用通常会使用流媒体音乐。
                               3.srcQuality：采样率转化质量,通过方法说明可以知道这个参数没有用
                        */
                        sSoundPool = SoundPool(1, AudioManager.STREAM_MUSIC, 0)
                    }
                    INSTANCE!!
                }
                else -> INSTANCE!!
            }
        }
    }

    /**
     * 初始化提示音
     *
     * @param key 设置声源对应的key，播放时用到
     * @param resId 例：放到raw下，可以通过R.raw.xxx调用
     */
    fun initHintSing(context: Context, key: Int, resId: Int) {
        val load = sSoundPool?.load(context, resId, 1) ?: throw NullPointerException("SoundPoolUntil 已经被回收")
        mSA.put(key, load)
    }

    /**
     * 播放声音
     *
     * @param key 设置声源的时候设置的key
     * @param rate 播放速度，0.5-2.0之间。1.0为正常速度
     */
    @JvmOverloads
    fun play(key: Int, rate: Float = 1F) {
        mSA.indexOfKey(key).takeIf {
            it >= 0
        }?.let {
            // soundID 		就是load方法返回的音频ID,
            // leftVolume 	是左声道 取值 0.0~1.0
            // rightVolume	是右声道 取值 0.0~1.0
            // priority 	优先级，0最低
            // loop 		0代表不循环,-1代表无限循环
            // rate 		播放速度，0.5-2.0之间。1.0为正常速度
            // 如果播放成功返回一个非0的streamID,否则回返回0
            sSoundPool?.play(mSA.get(key), 1f, 1f, 1, 0, rate) ?: throw NullPointerException("SoundPoolUntil 已经被回收")
        } ?: throw IllegalArgumentException("请输入正确的key，或者请检查是否调用initHintSing方法")
    }

    /**
     * 回收资源
     */
    fun onDestroy() {
        sSoundPool?.release()
        sSoundPool = null
        INSTANCE = null
    }
}