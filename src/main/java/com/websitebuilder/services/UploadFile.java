package com.websitebuilder.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

	@Component
public class UploadFile {

	
	
//	public void uploadLogo(MultipartFile file) throws IOException
//	{
//		final String upload_dir="C:\\xampp\\htdocs\\themes\\logo";
//		Files.copy(file.getInputStream(),Paths.get(upload_dir+File.separator+file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
//	}
//	
//	public void uploadLogoLocal(MultipartFile file) throws IOException
//	{
//		final String upload_dir="D:\\sts project\\BMWebsiteBuilderTool\\src\\main\\resources\\static\\themeLogo";
//		Files.copy(file.getInputStream(),Paths.get(upload_dir+File.separator+file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
//	}
	public void uploadImages(MultipartFile file) throws IOException
	{
		final String upload_dir="C:\\xampp\\htdocs\\themes\\gallery";
		Files.copy(file.getInputStream(),Paths.get(upload_dir+File.separator+file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
	}
	
	public void uploadGallery(MultipartFile file) throws IOException
	{
		final String upload_dir="D:\\sts project\\BMWebsiteBuilderTool\\src\\main\\resources\\static\\themeImages";
		Files.copy(file.getInputStream(),Paths.get(upload_dir+File.separator+file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
	}
	
		
	public void uploadThemeFile(MultipartFile file) throws IOException
	{
		final String upload_dir="D:\\sts project\\BMWebsiteBuilderTool\\src\\main\\resources\\templates\\theme";
		Files.copy(file.getInputStream(),Paths.get(upload_dir+File.separator+file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
	}
	
	public void uploadThemeSS(MultipartFile file) throws IOException
	{
		final String upload_dir="D:\\sts project\\BMWebsiteBuilderTool\\src\\main\\resources\\static\\themeSS";
		Files.copy(file.getInputStream(),Paths.get(upload_dir+File.separator+file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
	}
//	public void uploadClients(MultipartFile file) throws IOException
//	{
//		final String upload_dir="D:\\sts project\\BMWebsiteBuilderTool\\src\\main\\resources\\static\\clients";
//		Files.copy(file.getInputStream(),Paths.get(upload_dir+File.separator+file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
//	}
	
	
}
