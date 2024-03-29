package com.xm.xmstore.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;


/**
 * 	验证码生成工具类
 * @author Administrator
 *
 */
public class VerificationCode  {
	
	private static int IMAGE_WIDTH = 98;  //验证码图片的长         
	private static int IMAGE_HEIGHT = 40; //验证码图片的宽
	
    private String text;  //验证码的文本内容
    private Random rand = new Random();  //获取随机数对象
    
    //字体数组
    private String[] fontNames = {"宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312"};  
    //验证码数组
    private static char[] codes = "0123456789AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz".toCharArray();    

    /**
     * 	1. 获取随机的字符颜色
     */
    private Color randomColor() {
        //这里为什么是150，因为当r，g，b都为255时，即为白色，为了好辨认，需要颜色深一点。
        int r = rand.nextInt(150);
        int g = rand.nextInt(150);
        int b = rand.nextInt(150);
        //返回一个随机颜色
        return new Color(r, g, b);
    }

    /**
     * 	2. 获取随机字体
     */
    private Font randomFont() {
        //获取随机的字体
        int index = rand.nextInt(fontNames.length);
        String fontName = fontNames[index];
        //随机获取字体的样式，0是无样式，1是加粗，2是斜体，3是加粗加斜体
        int style = rand.nextInt(4);
        //随机获取字体的大小
        int size = rand.nextInt(5) + 24;
        //返回一个随机的字体
        return new Font(fontName, style, size);
    }

    /**
     * 	3. 获取随机单个字符
     */
    private char randomChar() {
        int index = rand.nextInt(codes.length);
        return codes[index];
    }

    /**
     * 	4. 画干扰线，验证码干扰线用来防止计算机解析图片
     */
    private void drawLine(BufferedImage image) {
        int num = 155;
        //定义干扰线的数量
        Graphics2D g = (Graphics2D) image.getGraphics();
        for (int i = 0; i < num; i++) {
            int x = rand.nextInt(IMAGE_WIDTH);
            int y = rand.nextInt(IMAGE_HEIGHT);
            int xl = rand.nextInt(IMAGE_WIDTH);
            int yl = rand.nextInt(IMAGE_HEIGHT);
            g.setColor(getRandColor(160, 200));
            g.drawLine(x, y, x + xl, y + yl);
        }
    }

    /**
     * 	5. 创建图片的方法
     */
    private BufferedImage createImage() {
        //创建图片缓冲区
        BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        //获取画笔
        Graphics2D g = (Graphics2D) image.getGraphics();
        // 设定图像背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
        //返回一个图片
        return image;
    }

    /**
     * 	6. 给定范围获得随机的图片颜色
     */
    Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }

        if (bc > 255) {
            bc = 255;
        }

        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
    
    /**
     * 	7. 获取验证码图片的方法
     */
    public BufferedImage getImage() {
        BufferedImage image = createImage();
        //获取画笔
        Graphics2D g = (Graphics2D) image.getGraphics();
        StringBuilder sb = new StringBuilder();
        
        //画干扰线
        drawLine(image);
        
        //画四个字符即可
        for (int i = 0; i < 4; i++) {
            //随机生成字符，因为只有画字符串的方法，没有画字符的方法，所以需要将字符变成字符串再画
            String s = randomChar() + "";
            //添加到StringBuilder里面
            sb.append(s);
            //定义字符的x坐标
            float x = i * 1.0F * IMAGE_WIDTH / 4;
            //设置字体，随机
            g.setFont(randomFont());
            //设置颜色，随机
            g.setColor(randomColor());
            g.drawString(s, x, IMAGE_HEIGHT - 10);
        }
        this.text = sb.toString();
        return image;
    }

    /**
     *	8. 获取验证码文本的方法
     */
    public String getText() {
        return text;
    }
    
    //测试验证码图片的生成
//    public static void main(String[] args) throws Exception {
//    	VerificationCode vc = new VerificationCode();
//    	BufferedImage image = vc.getImage();
//    	System.out.println(vc.getText());
//    	FileOutputStream fos = new FileOutputStream("testImage.jpg");
//    	ImageIO.write(image, "jpg", fos);
//    	System.out.println(image);
//	}
    
}




















