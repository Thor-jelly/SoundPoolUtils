# SoundPoolUtils

短声音源帮助类

[![GitHub release](https://img.shields.io/badge/release-v1.0.2-green.svg)]()

# 全局单例的使用

**具体参数看代码注释很全**

## 初始化声源

```
    SoundPoolUtils.getInstance().initHintSing();
```

## 播放声源

```
    SoundPoolUtils.getInstance().play();
```

## 结束回收资源

```
    SoundPoolUtils.getInstance().onDestroy();
```

# 多个实例使用

只在当前页面需要声源，这样离开当前界面就需要把声源回收了，节省资源

## 获取SoundPoolUtils实例

```
SoundPoolUtils soundPoolUtils = SoundPoolUtils.newInstance();
```

## 获取soundPool实例

```
SoundPool soundPool = soundPoolUtils.getNewInstanceSoundPool();
```

## 初始化声源

```
soundPoolUtils.initHintSing(soundPool, context, key, R.raw.xxx);
```

## 播放声源

```
soundPoolUtils.play(soundPool, key, rate);
```

## 回收声源

```
soundPoolUtils.onDestroy(soundPool);
```