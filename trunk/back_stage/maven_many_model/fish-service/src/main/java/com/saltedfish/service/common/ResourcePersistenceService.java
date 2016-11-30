/*******************************************************************************
 * Project   : portal-common
 * Class Name: com.yyq.cloud.portal.common.service.ResourceAsyncService
 * Created By: Jonathan 
 * Created on: 2014年12月9日 下午11:09:05
 * Copyright © 2013-2014 YYQ All rights reserved.
 ******************************************************************************/
package com.saltedfish.service.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.saltedfish.constants.Constants;
import com.saltedfish.constants.ExceptionCode;
import com.saltedfish.constants.FileTypeEnum;
import com.saltedfish.exception.ServiceException;
import com.saltedfish.exception.SystemException;
import com.saltedfish.utils.DateUtils;
import com.saltedfish.utils.ImageCompress;
import com.saltedfish.utils.StringUtil;


/**
 * <P>文件持久化服务类</P>
 * @author lkd
 */
@Service
public class ResourcePersistenceService extends BaseService{


	//保存文件的根路径
	@Value("${app.root.path}")
	private String rootPath;

	@Value("${app.test.relative.path}")
	private String testRelativePath;

	/**
	 * <p>文件上传</p>
	 * @param file 上传的文件
	 * @param fte 上传的目录
	 * @return
	 * @throws IOException
	 * @author wangzh
	 */
	public String uploadFileProcess(MultipartFile file, FileTypeEnum fte) throws IOException {
		String picPath = null;
		if (file != null && file.getSize() > 0) {
			if (file.getSize() > Constants.PROFILE_PHOTO_MAX_SIZE) {
				throw new ServiceException("文件不能超过10M");
			}
			String targetFileName = StringUtil.createFileName();
			String path = getFilePath(fte);
			picPath = path + "/" + DateUtils.formateCurrent(DateUtils.COMPACT_DATE_FORMAT) + "/" + targetFileName;
			asyncSaveFile(picPath, file.getInputStream(), true);
		}
		return picPath;
	}

	/**
	 * <p>异步保存文件</p>
	 * @param fileType
	 * @param fileName
	 * @param inputStream
	 * @return
	 * @author Jonathan
	 */
	@Async
	public void asyncSaveFile(String fileName, InputStream inputStream, Boolean isDelete) {
		logger.debug("开始保存文件, 文件名为:{}", fileName);
		String path = rootPath + fileName;
		File file = new File(path);
		if (file.exists()) {
			logger.info("数据重复:{}", fileName);
			if (isDelete) {
				logger.info("删除数据重复:{}", fileName);
				file.delete();
			} else {
				throw new ServiceException(ExceptionCode.FILE_EXISTING);
			}
		}
		if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
			throw new SystemException(ExceptionCode.DIRECTORY_FAILED_TO_CREATE);
		}
		try {
			// IOUtils.copy(inputStream, new FileOutputStream(file));
			FileOutputStream fs = null;
			try {
				fs = new FileOutputStream(file);
				byte[] buffer = new byte[1024 * 1024];
				int bytesum = 0;
				int byteread = 0;
				while ((byteread = inputStream.read(buffer)) != -1) {
					bytesum += byteread;
					fs.write(buffer, 0, byteread);
					fs.flush();
				}
			} catch (IOException e) {
				logger.error("写入文件出现异常", e);
			} finally {
				try {
					fs.close();
					inputStream.close();
				} catch (IOException e) {
					logger.error("关闭流出现异常", e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(ExceptionCode.FILE_FAILED_TO_SAVE);
		} finally {

		}
	}

	/**
	 *  保存缩略图
	 * <p>TODO</p>
	 * @param fileName
	 * @param inputStream
	 * @param isDelete
	 * @author lkd
	 */
	@Async
	public void saveSmallFile(String fileName, InputStream inputStream, Boolean isDelete, int height, int width) {
		logger.debug("开始保存缩略文件, 文件名为:{}", fileName);
		String originatePath = rootPath + fileName;
		String path = rootPath + fileName + "-" + width + "-" + height; // 这里是原文件的地址
		logger.debug("保存缩略文件地址, 文件名为:{}", fileName);
		File file = new File(path);
		if (file.exists()) {
			logger.info("数据重复:{}", fileName);
			if (isDelete) {
				logger.info("删除数据重复:{}", fileName);
				file.delete();
			} else {
				throw new ServiceException(ExceptionCode.FILE_EXISTING);
			}
		}
		if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
			throw new SystemException(ExceptionCode.DIRECTORY_FAILED_TO_CREATE);
		}
		try {
			// 图片压缩
			ImageCompress imageUtil = new ImageCompress();
			imageUtil.compressPic(originatePath, path, width, height, true);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(ExceptionCode.FILE_FAILED_TO_SAVE);
		} finally {

		}
	}

	public void syncSaveFile(String fileName, InputStream inputStream, Boolean isDelete) {
		logger.debug("开始保存文件, 文件名为:{}", fileName);
		String path = rootPath + fileName;
		File file = new File(path);
		if (file.exists()) {
			logger.info("数据重复:{}", fileName);
			if (isDelete) {
				logger.info("删除数据重复:{}", fileName);
				file.delete();
			} else {
				throw new ServiceException(ExceptionCode.FILE_EXISTING);
			}
		}
		if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
			throw new SystemException(ExceptionCode.DIRECTORY_FAILED_TO_CREATE);
		}
		try {
			// IOUtils.copy(inputStream, new FileOutputStream(file));
			FileOutputStream fs = null;
			try {
				fs = new FileOutputStream(file);
				byte[] buffer = new byte[1024 * 1024];
				int bytesum = 0;
				int byteread = 0;
				while ((byteread = inputStream.read(buffer)) != -1) {
					bytesum += byteread;
					fs.write(buffer, 0, byteread);
					fs.flush();
				}
			} catch (IOException e) {
				logger.error("写入文件出现异常", e);
			} finally {
				try {
					fs.close();
					inputStream.close();
				} catch (IOException e) {
					logger.error("关闭流出现异常", e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(ExceptionCode.FILE_FAILED_TO_SAVE);
		} finally {

		}
	}

	/**
	 * 
	 * <p>保存头像 并进行裁剪</p>
	 * @param fileName
	 * @param inputStream
	 * @author lidongfu
	 */
	@Async
	public void storeAvatarPicture(String fileName, InputStream inputStream) {
		InputStream stream = null;
		String path = rootPath + fileName;
		String sourceFilePath = rootPath + fileName + "_source";
		File file = new File(sourceFilePath);
		if (file.exists()) {
			logger.info("数据重复:{}", fileName);
		}
		if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
			throw new SystemException(ExceptionCode.DIRECTORY_FAILED_TO_CREATE);
		}
		FileOutputStream fs = null;
		try {
			fs = new FileOutputStream(path);
			byte[] buffer = new byte[1024 * 1024];
			int bytesum = 0;
			int byteread = 0;
			while ((byteread = inputStream.read(buffer)) != -1) {
				bytesum += byteread;
				fs.write(buffer, 0, byteread);
				fs.flush();
			}
		} catch (Exception e) {
			logger.error("保存图片出现异常", e);
			throw new SystemException(ExceptionCode.FILE_FAILED_TO_SAVE);
		} finally {
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("关闭流出现异常", e);
			}
		}
	}

	/**
	 * @Title: getFilePath 
	 * @Description: 根据枚举的类别，获取property文件里面配置的文件保存路径
	 * @param fileType
	 * @return
	 * @return: String
	 */
	public String getFilePath(FileTypeEnum fileType) {
		if (FileTypeEnum.TEST.equals(fileType)) {
			return testRelativePath;
		}
		throw new ServiceException(ExceptionCode.DIRECTORY_FAILED_TO_CREATE);
	}

}
