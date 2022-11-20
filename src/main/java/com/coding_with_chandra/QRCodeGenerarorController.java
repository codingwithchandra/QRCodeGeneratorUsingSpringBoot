package com.coding_with_chandra;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@RestController
public class QRCodeGenerarorController {
	
	private static final String QR_CODE_PATH = "D:\\sts_workspace\\QRCodeGenerator\\src\\main\\qrcode\\";
	
	@GetMapping("/generate/{code}")
	
	public String generateQRCode(@PathVariable Integer code) {
		
		String msg ="";
		String info =""
				+ "Product Name:Shirt"
				+ "Code:"+code.toString()
				+ "Price=500";
		try {
			generateQRCodeImage(info,250,250,QR_CODE_PATH+code+".png");
			msg ="QR Generated Successfully";
		} catch (WriterException | IOException e) {
			
			msg = e.toString();
			
			e.printStackTrace();
		}
		
		return msg;
		
		
	}
	
	public static void generateQRCodeImage(String text, int width, int height, String filePath)throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
       
    }

}
