package cn.xhlcode.klfm.exec;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.*;

import cn.xhlcode.klfm.bean.Album;
import cn.xhlcode.klfm.bean.DataList;
import cn.xhlcode.tool.Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class KaoLaFM {

	ArrayList<Album> albumList = new ArrayList<Album>();
	int pagesize = 20;
	int pagenum = 1;
	int sorttype = 1;

	/**
	 * 
	 * @param originUrl
	 *            专辑网页地址
	 * @param savePath
	 *            保存路径
	 * @param quality
	 *            质量 1:mp3 2:aac 3:m3u8
	 */
	public void download(String originUrl, String savePath, int quality) {
		// 获取网页内容
		InputStream inputStream = Util.getHtmlContext(originUrl);
		String context = Util.InputStreram2String(inputStream);
		// 得到AlbumID
		String albumID = getAlbumID(context);

		String apiUrl = handleUrl(albumID, pagesize, pagenum, sorttype);

		// 获取json 数据
		String json = Util.InputStreram2String(Util.getHtmlContext(apiUrl));

		// 保存json数据
		saveJsonFile(savePath, json);

		// 处理json 数据
		Album album = handleJSON(json);
		albumList.add(album);
		// 如果还有下一页
		while (album.getResult().getHaveNext() == 1) {
			pagenum += 1;
			apiUrl = handleUrl(albumID, pagesize, pagenum, sorttype);
			json = Util.InputStreram2String(Util.getHtmlContext(apiUrl));
			album = handleJSON(json);
			albumList.add(album);
		}
		try {
			downloadAudio(albumList, savePath, quality);
		} catch (Exception ex) {
			System.out.println("下载失败！");
		}

	}

	/**
	 * 保存json文件
	 * 
	 * @param savePath
	 * @param jsonData
	 */
	public void saveJsonFile(String savePath, String jsonData) {

		JSONObject json = JSONObject.fromObject(jsonData);
		json = (JSONObject) json.get("result");
		JSONArray jsonArr = (JSONArray) json.get("dataList");
		json = (JSONObject) jsonArr.get(0);
		// 专辑名
		String albumName = (String) json.get("albumName");
		// 专辑图片地址
		String albumPic = (String) json.get("albumPic");
		// json文件 路径
		File file = new File(savePath + File.separator + albumName + File.separator);

		Util.saveIamgeToLocal(albumPic, file.getAbsolutePath(), albumName);

		if (!file.exists()) {
			file.mkdirs();
		}

		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile() + File.separator + albumName + ".json");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(jsonData);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 下载所有音频
	 * 
	 * @param albumList
	 * @throws IOException
	 */
	public void downloadAudio(ArrayList<Album> albumList, String savePath, int quality) {
		String extension = "";
		String url = "";
		for (int i = 0; i < albumList.size(); i++) {
			Album album = albumList.get(i);
			List<DataList> dataList = album.getResult().getDataList();
			for (int x = 0; x < dataList.size(); x++) {
				String title = dataList.get(x).getOrderNum() + "." + dataList.get(x).getAudioName();
				if (quality == 1) {
					url = dataList.get(x).getMp3PlayUrl();
					extension = "mp3";
				} else if (quality == 2) {
					url = dataList.get(x).getAacPlayUrl();
					extension = "aac";
				} else {
					url = dataList.get(x).getM3u8PlayUrl();
					extension = "m3u8";
				}

				System.out.println(title + " : " + url);

				String path = savePath + dataList.get(x).getAlbumName() + "/";
				File saveDir = new File(path);
				if (!saveDir.exists()) {
					saveDir.mkdirs();
				}
				//保存下载信息
				Util.saveDownloadRecord(path, title, url);
				File file = new File(path + title + "." + extension);

				try {
					if(!file.exists()){
						byte[] getData = Util.readInputStream(Util.getHtmlContext(url));
						FileOutputStream fos = new FileOutputStream(file);
						fos.write(getData);
						if (fos != null) {
							fos.close();
						}						
					}
				} catch (IOException e) {
					try {
						//true表示在文件末尾追加  
						FileOutputStream fos = new FileOutputStream(path+"下载错误.txt",true);
						String msg = title + " : " + url+"\n";
						fos.write(msg.getBytes());
						fos.close();
					} catch (IOException ex) {
						System.err.println("#### 下载文件错误    ####");
						ex.printStackTrace();
					}//true表示在文件末尾追加  
					e.printStackTrace();
				}

			}
		}
		System.out.println("全部下载完毕");

	}

	/**
	 * 将json 转换成 bean
	 * 
	 * @param json
	 */
	public Album handleJSON(String json) {
		Gson gson = new Gson();

		Album album = gson.fromJson(json, Album.class);

		return album;
	}

	/**
	 * 动态生成api请求 例子
	 * http://www.kan8kan.com/webapi/audios/list?id=1100000084119&pagesize=20&
	 * pagenum=1&sorttype=1
	 * 
	 * @param albumID
	 * @param pagesize
	 * @param pagenum
	 * @param sorttype
	 * @return
	 */
	public String handleUrl(String albumID, int pagesize, int pagenum, int sorttype) {
		return "http://www.kan8kan.com/webapi/audios/list?id=" + albumID + "&pagesize=" + pagesize + "&pagenum="
				+ pagenum + "&sorttype=" + sorttype + "";
	}

	/**
	 * 获取AlbumID
	 * 
	 * @param context
	 */
	public String getAlbumID(String context) {
		String regEx = "(?i)(?<=input id=\"albumID\" type=\"hidden\" value=\")((?!a).)*(?=\"\\s/>)";

		Pattern p = Pattern.compile(regEx);

		Matcher m = p.matcher(context);
		String albumID = null;
		while (m.find()) {
			albumID = m.group();
		}
		return albumID;
	}

}
