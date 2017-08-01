package cn.xhlcode.xmly.exec;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

import cn.xhlcode.tool.Util;
import cn.xhlcode.xmly.bean.Track;

public class Ximalaya {
	
	/**
	 * 下载喜马拉雅音频
	 * @param albumUrl  专辑网页地址
	 * @param quality	执行 1：高质量 2：低质量
	 * @param savePath	保存路径
	 */
	public void download(String albumUrl,String savePath,int quality){
		InputStream inputStream = Util.getHtmlContext(albumUrl);
		String context = Util.InputStreram2String(inputStream);
		//获取音频id
		ArrayList<String> trackJSONUrlList = getTrack(context);
		
		ArrayList<Track> trackList = handleJSON(trackJSONUrlList);
		
		downloadAudio(trackList,quality, savePath);
		
	}
	
	/**
	 * 下载音频
	 * @param trackList
	 * @param quality
	 * @param savePath
	 */
	public void downloadAudio(ArrayList<Track> trackList,int quality,String savePath){
		Track track= new Track();
		String title = "";
		String url = "";
		String path = "";
		for(int i=0;i<trackList.size();i++){
			track = trackList.get(i);
			if(i==0){
				path = savePath+track.getAlbum_title()+"/";
				File saveDir = new File(path);
				if(!saveDir.exists()){
					saveDir.mkdirs();
				}
			}
			title = track.getTitle();
			if(quality == 1){
				url = track.getPlay_path_64();
			}else{
				url = track.getPlay_path_32();
			}
			try{
				System.out.println(track.getAlbum_title()+" - "+title+" : "+url);
				//保存下载信息
				Util.saveDownloadRecord(path, title, url);
				
				File file = new File(path+title+".m4a");
				if(!file.exists()){
					byte[] getData = Util.readInputStream(Util.getHtmlContext(url));
					FileOutputStream fos = new FileOutputStream(file);       
					fos.write(getData);   
					if(fos!=null){  
						fos.close();    
					}					
				}
			}catch(Exception ex){
				System.out.println("下载出错");
			}
			
		}
		System.out.println("全部下载完毕");
	}
	
	
	
	/**
	 * 处理并获取json数据
	 * @param context
	 */
	public ArrayList<String> getTrack(String context){
		ArrayList<String> trackJSONUrlList = new ArrayList<String>();

		String regEx = "(?i)(?<=track_id=\")((?!c).)*(?=\"\\strack_title)";
		Pattern p = Pattern.compile(regEx);
		
		Matcher m = p.matcher(context);
		while(m.find()){
			trackJSONUrlList.add(handleUrl(m.group()));
		}
		return trackJSONUrlList;

	}
	
	/**
	 * 拼接获取json URL
	 * @param trackId
	 * @return
	 */
	public String handleUrl(String trackId){
		return "http://www.ximalaya.com/tracks/"+trackId+".json";
	}
	
	/**
	 * 处理JSON数据
	 * @param trackIdList
	 * @return
	 */
	public ArrayList<Track> handleJSON(ArrayList<String> trackIdList){
		Gson gson = new Gson();
		ArrayList<Track> trackList = new ArrayList<Track>();
		for(int i=0;i<trackIdList.size();i++){
			InputStream inputStream = Util.getHtmlContext(trackIdList.get(i));
			String json = Util.InputStreram2String(inputStream);
			Track track= gson.fromJson(json, Track.class);
			trackList.add(track);
		}
		return trackList;
	}
	
	
	
	
	
	
	
	
	
	
	
}
