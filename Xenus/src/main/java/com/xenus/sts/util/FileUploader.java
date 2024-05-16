package com.xenus.sts.util;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

//VO
public class FileUploader {
	private List<MultipartFile> files;

	public List<MultipartFile> getFiles() {
		return files;
	}
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
}
