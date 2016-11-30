package com.saltedfish.utils;

import java.io.File;


/**
  * 
  * <P> 图片压缩 </P>
  * 
  * @author lidongfu
  * @return
 */
public class ImageCompress {

	private File file = null; // 文件对象
	private String inputDir; // 输入图路径
	private String outputDir; // 输出图路径
	private String inputFileName; // 输入图文件名
	private String outputFileName; // 输出图文件名
	private int outputWidth = 100; // 默认输出图片宽
	private int outputHeight = 100; // 默认输出图片高
	private boolean proportion = true; // 是否等比缩放标记(默认为等比缩放)

	public ImageCompress() { // 初始化变量
		inputDir = "";
		outputDir = "";
		inputFileName = "";
		outputFileName = "";
		outputWidth = 100;
		outputHeight = 100;
	}

	public void setInputDir(String inputDir) {
		this.inputDir = inputDir;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}

	public void setInputFileName(String inputFileName) {
		this.inputFileName = inputFileName;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	public void setOutputWidth(int outputWidth) {
		this.outputWidth = outputWidth;
	}

	public void setOutputHeight(int outputHeight) {
		this.outputHeight = outputHeight;
	}

	public void setWidthAndHeight(int width, int height) {
		this.outputWidth = width;
		this.outputHeight = height;
	}

	/*
	 * 获得图片大小 传入参数 String path ：图片路径
	 */
	public long getPicSize(String path) {
		file = new File(path);
		return file.length();
	}

	/**
	 * 
	 * <P> 处理压缩 </P>
	 *
	 * @author lidongfu_2005@sina.com
	 * @throws Exception
	 */
	// public void compressPic() throws Exception {
	// try {
	// // 获得源文件
	// file = new File(inputFileName);
	// // System.out.println(inputDir + inputFileName);
	// if (!file.exists()) {
	// throw new Exception("文件不存在");
	// }
	// Image img = ImageIO.read(file);
	// // 判断图片格式是否正确
	// if (img.getWidth(null) == -1) {
	// throw new Exception("文件格式不正确");
	// } else {
	// int newWidth;
	// int newHeight;
	// // 判断是否是等比缩放
	// if (this.proportion == true) {
	// // 为等比缩放计算输出的图片宽度及高度
	// double rate1 = ((double) img.getWidth(null)) / (double) outputWidth + 0.1;
	// double rate2 = ((double) img.getHeight(null)) / (double) outputHeight + 0.1;
	// // 根据缩放比率大的进行缩放控制
	// double rate = rate1 > rate2 ? rate1 : rate2;
	// newWidth = (int) (((double) img.getWidth(null)) / rate);
	// newHeight = (int) (((double) img.getHeight(null)) / rate);
	// } else {
	// newWidth = outputWidth; // 输出的图片宽度
	// newHeight = outputHeight; // 输出的图片高度
	// }
	// BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight,
	// BufferedImage.TYPE_INT_RGB);
	//
	// /*
	// * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
	// */
	// tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH),
	// 0, 0, null);
	// FileOutputStream out = new FileOutputStream(outputFileName);
	// // JPEGImageEncoder可适用于其他图片类型的转换
	// JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	// encoder.encode(tag);
	// out.close();
	// }
	// } catch (IOException ex) {
	// ex.printStackTrace();
	// }
	// }

	/**
	 * 
	 * <P> 图片压缩 </P>
	 *
	 * @author lidongfu_2005@sina.com
	 * @param inputFileName
	 * @param outputFileName
	 * @param width
	 * @param height
	 * @param gp
	 * @throws Exception
	 */
	public void compressPic(String inputFileName, String outputFileName, int width, int height, boolean gp) throws Exception {
		// 输入图路径文件名
		this.inputFileName = inputFileName;
		// 输出图文件路径
		this.outputFileName = outputFileName;
		// 设置图片长宽
		setWidthAndHeight(width, height);
		// 是否是等比缩放 标记
		this.proportion = gp;
		// compressPic();
	}

}
