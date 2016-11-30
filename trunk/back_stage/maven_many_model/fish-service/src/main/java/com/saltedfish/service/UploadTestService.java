/**   
 * Copyright © 2016 公司名. All rights reserved.
 * 
 * @Title: UploadTestService.java 
 * @Prject: fish-service
 * @Package: com.saltedfish.service 
 * @Description: TODO
 * @author: mjy   
 * @date: 2016年11月30日 下午2:40:51 
 * @version: V1.0   
 */
package com.saltedfish.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.saltedfish.constants.Constants;
import com.saltedfish.constants.FileTypeEnum;
import com.saltedfish.exception.ServiceException;
import com.saltedfish.service.common.ResourcePersistenceService;
import com.saltedfish.utils.DateUtils;
import com.saltedfish.utils.StringUtil;

/** 
 * @ClassName: UploadTestService 
 * @Description: 上传文件测试service
 * @author: lkd
 * @date: 2016年11月30日 下午2:40:51  
 */
@Service
public class UploadTestService {

	@Autowired
	private ResourcePersistenceService resourcePersistenceService;
	
	/**
	 * 
	 * <p>上传图片测试</p>
	 * @param file
	 * @return
	 * @throws IOException
	 * @author lkd
	 */
	public String uploadFileProcess(MultipartFile file) throws IOException {
		String picPath = null;
		if (file != null && file.getSize() > 0) {
			if (file.getSize() > Constants.PROFILE_PHOTO_MAX_SIZE) {
				throw new ServiceException("图片文件不能超过10M");
			}
			
			
			//生成文件名称
			String targetFileName = StringUtil.createFileName();
			
			//生成文件保存的路径
			String path = resourcePersistenceService.getFilePath(FileTypeEnum.TEST); 
			picPath = path + "/" + DateUtils.formateCurrent(DateUtils.COMPACT_DATE_FORMAT) + "/" + targetFileName;
			//开始保存文件
			resourcePersistenceService.asyncSaveFile(picPath, file.getInputStream(), true);
		}
		//返回保存文件的路径	
		return picPath;
	}
	
	
	
	
}
