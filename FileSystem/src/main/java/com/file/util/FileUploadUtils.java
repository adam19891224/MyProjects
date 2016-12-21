package com.file.util;

import com.file.entity.image.CutImageFile;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileUploadUtils {

	private static Logger logger = Logger.getLogger(FileUploadUtils.class);

	private static final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);

	private static final Integer SIZEX = 150;

	private static final Integer SIZEY = 150;

	private static final String[] CONTACT_ALLOW_TYPES = {"png", "jpg", "jpeg", "bmp", "gif" ,"x-png","x-bmp","x-ms-bmp"};

	private static final String key = "zhouyuhong19891224";

	public static void upload(CutImageFile cutImageFile, String imagePath){

		logger.info("生成图片路径： " + imagePath);

		File target = new File(imagePath);

		{
			File parentDir = target.getParentFile();
			if(!parentDir.isDirectory()){
				logger.info("path为： " + parentDir.getPath() + " 的文件夹不存在， 故创建该文件夹。");
				parentDir.mkdirs();
			}
		}

		if(cutImageFile.getTag()){
			//使用图片裁剪方法
			new FileUploadUtils().copyCutFile(cutImageFile, target);
		}else{
			//使用上传图片方法
			new FileUploadUtils().copyFile(cutImageFile, target);
		}
	}

	private void copyFile(CutImageFile cutImageFile, File target){
		try {
			logger.info("开始创建图片");
//			Thumbnails.of(cutImageFile.getFile().getInputStream()).toFile(target);
			FileUtils.copyInputStreamToFile(cutImageFile.getFile().getInputStream(), target);
			logger.info("图片创建完毕");
		} catch (IOException e) {
			logger.error("生成图片失败", e);
		}
	}

	private void copyCutFile(CutImageFile cutImageFile, File target){
		try{
			logger.info("开始裁剪图片");
			int x = new BigDecimal(cutImageFile.getX()).intValue();
			int y = new BigDecimal(cutImageFile.getY()).intValue();
			int w = new BigDecimal(cutImageFile.getCutWidth()).intValue();
			int h = new BigDecimal(cutImageFile.getCutHeight()).intValue();
			Thumbnails.of(cutImageFile.getFile().getInputStream()).sourceRegion(x, y, w, h).size(SIZEX, SIZEY).toFile(target);
			logger.info("图片裁剪完毕");
		} catch (IOException e) {
			logger.error("裁剪图片失败", e);
		}
	}

	/**
	 * 得到文件名字，以uuid命名
	 */
	private static String getFileName(CutImageFile cutImageFile){
		//TODO 用uuid来为图片命名
		String fileName = UUID.randomUUID().toString();
		//获取图片后缀
		String tempFileName = cutImageFile.getFile().getOriginalFilename();
		String postfix = tempFileName.substring(tempFileName.lastIndexOf("."), tempFileName.length()).toLowerCase();
		return fileName + postfix;
	}

	/**
	 * 得到文件存放路径
	 */
	public static String getSavePath(CutImageFile cutImageFile){
		//生成根目录
		StringBuilder dir = new StringBuilder("/articleImages");
		dir.append(File.separator);
		//得到当前时间, 然后根据年月来创建图片文件夹
		LocalDate nowTime = LocalDate.now();
		//年份
		String dirYear = nowTime.getYear() + "";
		//月份
		String dirMonth = nowTime.getMonthValue() + "";
		dir.append(dirYear);
		dir.append(File.separator);
		dir.append(dirMonth);
		dir.append(File.separator);
		String fileName = getFileName(cutImageFile);
		dir.append(fileName);
		return dir.toString();
	}

	public static boolean isImg(String suffix){
		for(String s : CONTACT_ALLOW_TYPES){
			if(StringUtils.isEqual(s, suffix)){
				return true;
			}
		}
		return false;
	}

	public static ExecutorService getFixedThreadPool(){
		return fixedThreadPool;
	}

	public static void destory(){
		fixedThreadPool.shutdown();
	}

	public static boolean checkFileIsAllow(CutImageFile file){
		String fileName = file.getFile().getOriginalFilename();
		String sign = file.getSign();
		if(StringUtils.isNotNull(fileName) && StringUtils.isNotNull(sign)){
			//同样用文件名 + 私钥生成签名，校验两者是否相等
			String tempSign = MD5Utils.getMD5(fileName + key);
			return sign.equalsIgnoreCase(tempSign);
		}
		return false;
	}
}
