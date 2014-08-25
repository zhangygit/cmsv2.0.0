package com.app.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class ConvertVideo {
	private static Log log = LogFactory.getLog(ConvertVideo.class);

	// 原始文件
	private String videoPath;
	// 目标文件
	private String targetPath;
	// 备份文件
	private String bakPath;
	// 缩略图
	private String thumbnailPath;
	// 声音采样率
	private String audioRate = "22050";
	// 画面品质
	private String picQuality;
	
	private String thumbnail_x;
	private String thumbnail_y;

	private String exePath = "";

	
	private void readFFmpegPath() {
	 if (StringUtils.isBlank(this.exePath))
	      if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") != 0){
	    	  this.exePath = (ConvertPathUtil.getConvertRootPath() + "/ffmpeg/ffmpeg");
	      }else{
	    	  this.exePath = (ConvertPathUtil.getConvertRootPath() + "/ffmpeg/ffmpeg.exe");
	      }
	}
	
	public ConvertVideo() {
		
	}

	public ConvertVideo(String videoPath, String targetPath, String thumbnailPath, String audioRate, String picQuality,String thumbnail_x,String thumbnail_y) {
		this.videoPath = videoPath;
		this.targetPath = targetPath;
		this.thumbnailPath = thumbnailPath;
		this.audioRate = audioRate;
		this.picQuality = picQuality;
		this.thumbnail_x = thumbnail_x;
		this.thumbnail_y = thumbnail_y;
	}
	public ConvertVideo(String videoPath, String targetPath, String thumbnailPath, String audioRate, String picQuality) {
		this.videoPath = videoPath;
		this.targetPath = targetPath;
		this.thumbnailPath = thumbnailPath;
		this.audioRate = audioRate;
		this.picQuality = picQuality;
	}
	public ConvertVideo(String videoPath, String targetPath, String thumbnailPath, String bakPath, String audioRate, String picQuality,String thumbnail_x,String thumbnail_y) {
		this.videoPath = videoPath;
		this.targetPath = targetPath;
		this.thumbnailPath = thumbnailPath;
		this.audioRate = audioRate;
		this.picQuality = picQuality;
		this.bakPath = bakPath;
		this.thumbnail_x = thumbnail_x;
		this.thumbnail_y = thumbnail_y;
	}

	public void process() throws IOException {
		int type = checkContentType();
		readFFmpegPath();
		if (type == 0) {
			this.ffmpegTransVideo();
			if(StringUtils.isNotBlank( bakPath )){
				this.ffmpegTransVideoG();
			}
			if ( true ) {
				this.ffmpegTransImage(thumbnail_x, thumbnail_y);
			}
		} else if (type == 1) {
			this.mencoderTransVideo();
		}
	}

	public int checkContentType() {
		String type = videoPath.substring(videoPath.lastIndexOf(".") + 1,
				videoPath.length()).toLowerCase();
		/*if ( FileUtils.isVideo(videoPath) || FileUtils.isAudio(videoPath) ) {
			return 0;
		}*/
			
		if(true){
			return 0;
		}
		// 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),
		// 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.
		if (type.equals("wmv9")) {
			return 1;
		} else if (type.equals("rm")) {
			return 1;
		} else if (type.equals("rmvb")) {
			return 1;
		}
		return 9;
	}

	public static boolean checkfile(String path) {
		File file = new File(path);
		if (!file.isFile()) {
			return false;
		}
		return true;
	}

	/**
	 * 使用mencoder转码
	 * 
	 * @param videoPath
	 *            源路径 -- 要转换的视频文件
	 * @param targetPath
	 *            目标路径 -- 转换后的视频flv
	 * @return 返回目标路径
	 */
	public String mencoderTransVideo() {
		List<String> commend = new java.util.ArrayList<String>();
		commend.add("d:\\flv\\MediaCoder\\codecs\\mencoder.exe");
		commend.add(videoPath);
		// 音频采用mp3编码
		commend.add("-oac");
		commend.add("mp3lame");
		// 采用高质DivX视频编码，视频码率为112kbps
		commend.add("-ovc");
		commend.add("lavc");
		commend.add("-lavcopts");
		commend.add("vcodec=flv:vbitrate=500:mbd=2:mv0:trell:v4mv:cbp:last_pred=3:dia=-1:cmp=3:vb_strategy=1");
		commend.add("-lameopts");
		commend.add("abr:br=56");
		// 声音采样频率设置，现为22K
		commend.add("-srate");
		commend.add("22050");
		// -sws就是用来设置品质的，默认值为2
		commend.add("-sws");
		commend.add("3");
		// 宽度为208，高度自动调整保持比例；
		// -vf scale=-3:176宽度自动调整保持比例，高度为176；如果想保持原来的大小可以不要这个参数
		commend.add("-vf");
		commend.add("scale=512:-3");
		// 帧速率设置
		commend.add("-ofps");
		commend.add("18");
		/*
		 * mode=3:cbr:br=24单声道 音频码率为24kbps;-lameopts
		 * mode=0:cbr:br=24立体声，音频码率为24kbps; 还可设置音量，-lameopts
		 * mode=3:cbr:br=32:vol=1，设置范置为1~10，但不宜设得太高
		 */
		commend.add("-lameopts");
		commend.add("vbr=3:br=128");
		commend.add("-o");
		commend.add(targetPath);
		// 控制台显示执行的命令
		// System.out.println(commend);
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			builder.start();
			return targetPath;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String ffmpegTransVideo() throws IOException {
		List<String> commend = new java.util.ArrayList<String>();
		if(StringUtils.isBlank(exePath)) readFFmpegPath();
		commend.add(exePath);
		commend.add("-i");
		commend.add(videoPath);
		// 码流控制
		commend.add("-b");
		commend.add("800k");
		commend.add("-minrate");
		commend.add("500k");
		commend.add("-maxrate");
		commend.add("1000k");
		commend.add("-bufsize");
		commend.add("800k");
		// 音质
		commend.add("-ar");
		commend.add(StringUtils.defaultIfEmpty(audioRate, "22050"));
		// 画面大小
		//commend.add("-s");
		//commend.add("720x576");
		if( StringUtils.isNotBlank(picQuality) ) {
			// 清晰度 -qscale 4 为最好可是文件大, -qscale 6就可以了
			commend.add("-qscale");
			commend.add(picQuality);
		}
//		commend.add("-strict experimental");
		commend.add("-y");
		commend.add(targetPath);
//		if( StringUtils.isNotBlank( bakPath ) ) {
//			commend.add(bakPath);
//		}
		log.info(commend);
		System.out.println(commend);
		try {
			CommandExecutor.exec(commend);
			return targetPath;
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException("视频转换失败：" + commend);
		}
	}
	public String ffmpegTransVideoG() throws IOException {
		List<String> commend1 = new java.util.ArrayList<String>();
		commend1.add(exePath);
		commend1.add("-i");
		commend1.add(videoPath);
		// 码流控制01135349m4cr.flv
		// 音质
		commend1.add("-ar");
		commend1.add(StringUtils.defaultIfEmpty(audioRate, "22050"));
		// 画面大小
		commend1.add("-s");
		commend1.add("720x576");
//		if( StringUtils.isNotBlank(picQuality) ) {
//			// 清晰度 -qscale 4 为最好可是文件大, -qscale 6就可以了
//			commend.add("-qscale");
//			commend.add(picQuality);
//		}
		commend1.add("-strict");
		commend1.add("experimental");
		commend1.add("-y");
		commend1.add(bakPath);
		log.info(commend1);
		try {
			String type = videoPath.substring(videoPath.indexOf("."));
			if(type != null && "mp4".equals(type)){
				File srcFile = new File(videoPath);
				File targetFile = new File(bakPath);
				if(targetFile.exists()){
					targetFile.delete();
				}
				/*if(srcFile.exists()){
					VfsUtils.copy(videoPath	, bakPath);
				}*/
			}else{
				CommandExecutor.exec(commend1);
			}
			return targetPath;
		} catch (IOException e) {
			throw new IOException("视频转换失败：" + commend1);
		}
	}

	// 生成图片 参数String newfilename, String newimg
	public boolean ffmpegTransImage(String w, String h) throws IOException {
		log.info("宽："+w+"----------------高："+h);
		
		List<String> commend = new java.util.ArrayList<String>();
		if(StringUtils.isBlank(exePath)) readFFmpegPath();
		commend.add(exePath);
		commend.add("-i");
		commend.add(videoPath);
		commend.add("-ss");
		commend.add("8");
		commend.add("-s");
		commend.add(w + "x" + h);
		commend.add( this.thumbnailPath );
		
		try {
			CommandExecutor.exec(commend);
			if( log.isDebugEnabled() ) {
				log.debug( "视频截图转换成功：" + this.thumbnailPath );
			}
			return true;
		} catch (Exception e) {
			throw new IOException("视频缩略图转换失败：" + commend);
		}
		
	}

}
