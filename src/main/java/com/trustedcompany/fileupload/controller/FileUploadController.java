package com.trustedcompany.fileupload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.trustedcompany.fileupload.util.FileUploadUtil;

@RestController
public class FileUploadController {

	@Autowired
	FileUploadUtil fileUploadUtil;

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST, produces = "text/html")
	public ResponseEntity<String> uploadFileAsync(@RequestParam("file") MultipartFile file) throws Exception {
		fileUploadUtil.performAsyncFileUpload(file);
		return new ResponseEntity<String>(" Hurrah for the file upload :D \n Please check logs for more details!!",
				HttpStatus.OK);
	}

}