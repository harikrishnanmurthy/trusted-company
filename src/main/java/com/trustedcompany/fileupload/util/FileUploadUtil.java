package com.trustedcompany.fileupload.util;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("fileUploadUtil")
public class FileUploadUtil {

	private final static Logger LOGGER = Logger.getLogger(FileUploadUtil.class.getName());
	
	public void performAsyncFileUpload(MultipartFile file) throws Exception{
		
		File tempFile = new File(file.getOriginalFilename());
		Path path = Paths.get(tempFile.getAbsolutePath());
		AsynchronousFileChannel fileChannel = null;
		long position = 0;
		try {
			fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
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
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			throw e;
		}
	}
}
