# Spider
喜马拉雅 及 考拉 fm 解析




``` java
//喜马拉雅（不支持收费专辑的解析） 下载
Ximalaya xmly = new Ximalaya();
//例如： 解析下载 《心理罪》 有声小说
xmly.download("http://www.ximalaya.com/6333535/album/2696006","d:/喜马拉雅/",1);  //1：高质量 2：低质量
```

``` java
//考拉FM 下载
KaoLaFM kaolaFM = new KaoLaFM();
//鬼吹灯Ⅰ之1精绝古城
kaolaFM.download("http://www.tingban.cn/zj/EjvAxlyF.html","d:/考拉FM/",1);  //1:mp3 2:aac 3:m3u8
```

