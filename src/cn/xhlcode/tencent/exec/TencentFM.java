package cn.xhlcode.tencent.exec;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import cn.xhlcode.tool.Util;
import net.sf.json.JSONObject;

public class TencentFM {
	
	/**
	 * 取得专辑地址 -> 解析网页源码获取专辑内音频的showIDList -> 根据showID拼接地址获取音频播放页的网页源码 ->
	 * 截取出json格式得到 下载地址/名称/
	 * @param originUrl
	 * @param savePath
	 */
	public static void download(String originUrl,String savePath){
		
		//获取网页内容
		InputStream inputStream = Util.getHtmlContext(originUrl);
		String context = Util.InputStreram2String(inputStream);
		//获取showIDList
		String[] showIDList = getShowIDList(context);
		
		
		String preStr = "https://fm.qq.com/show/";
		String fullUrlStr = "";
		int i=0;
		for(String str:showIDList){
			i++;
			fullUrlStr = preStr+str; //拼接在线播放音频地址
			InputStream inputStreamAudio = Util.getHtmlContext(fullUrlStr);
			String audioContext = Util.InputStreram2String(inputStreamAudio);
			//解析并下载
			
				resolveAndDownload(audioContext,str,savePath);	
			
			
		}
		
		System.out.println("======================================");
		System.out.println("下载完毕");
	}
	
	/**
	 * 获取showIDList
	 * @param context
	 */
	public static String[] getShowIDList(String context){

		String[] dataList = null;
		
		String regEx = "showIdList: \\[\"(?<grp0>.+?)\"\\],";
		
		Pattern p = Pattern.compile(regEx); 
		
		
		Matcher m = p.matcher(context);
		
		String data = new String();
		while(m.find()){
			//正则表达式处理不精确   此处进行二次处理
			data= m.group();
			data = data.substring(14, data.length()-3);
			dataList = data.split("\",\"");
		}
		return dataList;
	}
	
	
	/**
	 * 解析并下载
	 * @param context
	 */
	public static void resolveAndDownload(String context,String str,String savePath){
				
		String regEx = "showData: \\{\"(?<grp0>.+?)[\\s]+\\}\\);";
		
		Pattern p = Pattern.compile(regEx); 
		
		
		Matcher m = p.matcher(context);
		
		String data = new String();
		while(m.find()){
			//正则表达式处理不精确   此处进行二次处理
			data= m.group();
			data = data.substring(10, data.length()-3);
			
			JSONObject json = JSONObject.fromObject(data);
			
			JSONObject showID = (JSONObject) json.get(str);
			
			JSONObject album = (JSONObject) showID.get("album");
			String albumName =  album.getString("name"); //专辑名称
			
			JSONObject show = (JSONObject) showID.get("show");
			JSONObject share = (JSONObject) show.get("share");
			String dataUrl = share.getString("dataUrl");  //下载地址
			String title = share.getString("title");	//音频名称
			System.out.println(albumName+"----"+title+"----"+dataUrl);
//			downLoadAudio(savePath, albumName, dataUrl, title);
			
		}
	} 
	
	/**
	 * 下载
	 * @param savePath  保存路径
	 * @param albumName		专辑名称
	 * @param dataUrl	下载地址
	 * @param title		音频名称
	 */
	public static void downLoadAudio(String savePath,String albumName,String dataUrl,String title){
		savePath = savePath+albumName+"/";
		File saveDir = new File(savePath);
		if(!saveDir.exists()){
			saveDir.mkdirs();
		}
		try {
			byte[] getData = Util.readInputStream(Util.getHtmlContext(dataUrl));
			File file = new File(savePath+title+".m4a");
			FileOutputStream fos = new FileOutputStream(file);       
			fos.write(getData);   
			if(fos!=null){  
				fos.close();    
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("下载出错");
		}
		
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		
	}

}
