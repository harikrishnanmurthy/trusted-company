package com.trustedcompany.fileupload;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	
	private final static Logger LOGGER = Logger.getLogger(FileUploadController.class.getName());

    @RequestMapping(value="/uploadFile", method=RequestMethod.POST)
    @ResponseBody
	public void uploadFileAsync(@RequestParam("file") MultipartFile file) {
    	
    	File tempFile = new File(file.getOriginalFilename());
		Path path = Paths.get(tempFile.getAbsolutePath());
		AsynchronousFileChannel fileChannel = null;
		try {
			fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
		} catch (IOException e) {
		}
		long position = 0;
		try {
			fileChannel.write(ByteBuffer.wrap(file.getBytes()), position, null, new CompletionHandler<Integer, ByteBuffer>() {

			    @Override
			    public void completed(Integer result, ByteBuffer attachment) {
			    	LOGGER.info("############File upload complete############");
			    	LOGGER.info("File Successfully uploaded to "+ tempFile.getAbsolutePath());
			    }

			    @Override
			    public void failed(Throwable exc, ByteBuffer attachment) {
			    	LOGGER.info("############File upload failed############");
			    	LOGGER.severe(exc.getMessage());
			    }
			});
			
			LOGGER.info("############File upload begin############");
		} catch (IOException e) {
			LOGGER.severe(e.getMessage());
		}
	}

}