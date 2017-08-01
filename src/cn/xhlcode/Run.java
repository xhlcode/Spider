package cn.xhlcode;

import cn.xhlcode.klfm.exec.KaoLaFM;
import cn.xhlcode.tencent.exec.TencentFM;
import cn.xhlcode.xmly.exec.Ximalaya;

public class Run {
	
	public static void main(String[] args) {
//		KaoLaFM kaolaFM = new KaoLaFM();
//		kaolaFM.download("http://www.tingban.cn/zj/EjvAxlyF.html","d:/考拉FM/",1);
		
		//喜马拉雅 下载
		Ximalaya xmly = new Ximalaya();
		xmly.download("http://www.ximalaya.com/6333535/album/2696006","d:/喜马拉雅/",1);
		
		//企鹅FM下载
//		TencentFM tencentFM = new TencentFM();
//		tencentFM.download("https://fm.qq.com/album/rd000hUTzq0uGsQf_39092_39096","d:/企鹅FM/");		
		
	}
	
	
	
	
}
