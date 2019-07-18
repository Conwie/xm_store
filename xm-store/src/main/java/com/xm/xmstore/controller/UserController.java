package com.xm.xmstore.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xm.xmstore.controller.ex.FileEmptyException;
import com.xm.xmstore.controller.ex.FileSizeException;
import com.xm.xmstore.controller.ex.FileStateException;
import com.xm.xmstore.controller.ex.FileUploadIOException;
import com.xm.xmstore.controller.ex.GetPhoneCodeFailException;
import com.xm.xmstore.entity.User;
import com.xm.xmstore.service.UserService;
import com.xm.xmstore.service.ex.CodeErrorException;
import com.xm.xmstore.service.ex.ServiceException;
import com.xm.xmstore.util.JsonResult;
import com.xm.xmstore.util.VerificationCode;
import com.xm.xmstore.util.phonecode.PhoneCode;

/**
 * 	处理用户相关请求的控制器 
 *
 */
@RestController
@RequestMapping("users")
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	private VerificationCode vc = new VerificationCode();
	
	/** 处理用户注册请求*/
	@PostMapping("reg")
	public JsonResult<Void> reg(@RequestParam("phone_code")String phoneCode,HttpSession session,User user){
		
		String myCode = session.getAttribute("phoneCode").toString();
		if(!myCode.equals(phoneCode)) {
			throw new CodeErrorException("手机验证码输入错误！");
			
		}
		//执行注册验证
		userService.reg(user);
		return new JsonResult<Void>(SUCCESS);		
	}
	/**判断用户是否存在*/
	@RequestMapping("check_user")
	public JsonResult<Boolean> checkUser(String username){
		Boolean result = userService.checkUser(username);
		return new JsonResult<Boolean>(SUCCESS,result);
	}
	
	/**判断手机号是否被使用*/
	@RequestMapping("check_phone")
	public JsonResult<Boolean> checkPhone(String phone){
		userService.checkPhone(phone);
		return new JsonResult<Boolean>(SUCCESS);
	}
	
	/** 处理用户登录请求*/
	@PostMapping("login")
	public JsonResult<User> login(String username,String password,String code,HttpSession session) throws ServiceException{
//		System.err.println("username=" + username);
//		System.err.println("password=" + password);
		//检查验证码
		checkCode(session, code);
		
		//执行登录验证
		User user = userService.login(username,password,code);
		
		//将uid和username存放到session中
		session.setAttribute("uid", user.getUid());
		session.setAttribute("username", user.getUsername());
		
		return new JsonResult<User>(SUCCESS,user);
	}
	
	
	/** 更改密码 */
	@PostMapping("change_password")
	public JsonResult<User> updatePassowrd(@RequestParam("oldPassword")String oldPassword,@RequestParam("newPassword")String newPassword,HttpSession session) {
		Integer uid = getUidFromSession(session);
		String username=getUsernameFromSession(session);
//		System.err.println("change_password"+username);
		userService.changePassword(uid, oldPassword, newPassword, username);
//		System.err.println("oldPassword"+oldPassword);
//		System.err.println("newPassword"+newPassword);
		return new JsonResult<User>(SUCCESS);
	}
	
	/**修改个人信息*/
	@RequestMapping("change_info")
	public JsonResult<Void> changeInfo(User user, HttpSession session) {
		// 从session中获取uid和username
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		// 执行修改
		userService.changeInfo(uid, username, user);
		// 返回成功
		return new JsonResult<Void>(SUCCESS);
	}
	
	/**根据用户id获取用户个人信息*/
	@GetMapping("get_by_uid")
	public JsonResult<User> getByUid(HttpSession session) {
		// 执行获取数据
		Integer uid = getUidFromSession(session);
		User user = userService.getByUid(uid);
		// 返回成功与数据
		return new JsonResult<User>(SUCCESS,user);
	}
	
	
	/** 处理获取生成验证码的请求*/
	@GetMapping("getImage")
	public void handleGetImage(HttpSession session, HttpServletResponse response) throws ServletException, IOException {
		//获取验证码图片
		BufferedImage image = vc.getImage();
		//获取验证码文本
		String codeText = vc.getText();
		//将文本内容设置到session上
		session.setAttribute("codeText", codeText);

		// 禁止图像缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		// 将图像输出到Servlet输出流中
		ServletOutputStream sos = response.getOutputStream();
		ImageIO.write(image, "jpeg", sos);
		sos.flush();
		sos.close();
	}
	
	@RequestMapping("getPhoneCode")
	public JsonResult<Void> getPhoneCode(String phone,HttpSession session) {
		//生成短信随机验证码
		String phoneCode = PhoneCode.vcode();
//		System.err.println("phoneCode="+phoneCode);
		//将验证码存储到session
		session.setAttribute("phoneCode", phoneCode);
		//将验证码发送到用户手机
		String result = PhoneCode.getPhonemsg(phone, phoneCode);
		if(!result.equals("true")) {
			throw new GetPhoneCodeFailException("获取验证码失败，请稍后重试！");
		}
		return new JsonResult<Void>(SUCCESS);
	}
	
	
	// 检查验证码
	private void checkCode(HttpSession session,String code)throws CodeErrorException{
		Object obj = session.getAttribute("codeText");
		if(obj != null) {
			String codeText = obj.toString();
			if(!codeText.equalsIgnoreCase(code)) {
				throw new CodeErrorException("验证码输入错误！");
			}
		}
	}
	
	/**
	 * 允许上传的头像文件的最大大小
	 */
	public static final long AVATAR_MAX_SIZE = 2 * 1024 * 1024;
	/**
	 * 允许上传的头像文件的类型列表
	 */
	public static final List<String> AVATAR_CONTENT_TYPES = new ArrayList<String>();
	
	static {
		AVATAR_CONTENT_TYPES.add("image/jpeg");
		AVATAR_CONTENT_TYPES.add("image/png");
	}
	
	@PostMapping("change_avatar")
	public JsonResult<String> changeAvatar(
		@RequestParam("file") MultipartFile file, 
		HttpServletRequest request) {
		// 检查文件是否为空
		if (file.isEmpty()) {
			throw new FileEmptyException(
				"上传头像失败！请选择有效的图片文件！");
		}
		
		// 检查文件大小是否超出限制
		if (file.getSize() > AVATAR_MAX_SIZE) {
			throw new FileSizeException(
				"上传头像失败！不允许上传超过" + (AVATAR_MAX_SIZE / 1024) + "KB的图片文件！");
		}
		
		// 检查文件类型是否超出限制
		if (!AVATAR_CONTENT_TYPES.contains(file.getContentType())) {
			throw new FileSizeException(
				"上传头像失败！选择的文件类型超出了限制！\r\r允许使用的文件类型有：" + AVATAR_CONTENT_TYPES);
		}
		
		// 确定文件夹
		String parentPath = request.getServletContext().getRealPath("upload");
		File parent = new File(parentPath);
		if (!parent.exists()) {
			parent.mkdirs();
		}
		
		// 确定文件名
		String filename = UUID.randomUUID().toString();
		String originalFilename = file.getOriginalFilename();
		int beginIndex = originalFilename.lastIndexOf(".");
		String suffix = originalFilename.substring(beginIndex);
		String child = filename + suffix;
		
		// 保存用户上传的文件
		File dest = new File(parent, child);
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			throw new FileStateException(
				"上传文件失败！文件状态有误，请重新尝试！");
		} catch (IOException e) {
			throw new FileUploadIOException(
				"上传文件失败！发生读写错误，请重新尝试！");
		}
		
		// 将文件的路径记录到数据库
		String avatarPath = "/upload/" + child;
		Integer uid = getUidFromSession(request.getSession());
		String username = getUsernameFromSession(request.getSession());
		userService.changeAvatar(uid, avatarPath, username);
		
		// 响应结果
		return new JsonResult<>(SUCCESS, avatarPath);
	}
	

}












