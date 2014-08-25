package com.app.cms.action.front;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.cms.entity.main.CmsConfig;
import com.app.cms.entity.main.Content;
import com.app.cms.entity.main.ContentAttachment;
import com.app.cms.manager.main.ContentCountMng;
import com.app.cms.manager.main.ContentMng;
import com.app.cms.web.CmsUtils;
import com.app.common.security.encoder.PwdEncoder;
import com.app.common.util.HttpUtils;
import com.app.common.web.ResponseUtils;

@Controller
public class AttachmentAct {
	private static final Logger log = LoggerFactory
			.getLogger(AttachmentAct.class);

	@RequestMapping(value = "/attachment.jspx", method = RequestMethod.GET)
	public void attachment(Integer cid, Integer i, Long t, String k,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws IOException {
		CmsConfig config = CmsUtils.getSite(request).getConfig();
		String code = config.getDownloadCode();
		int h = config.getDownloadTime() * 60 * 60 * 1000;
		if (pwdEncoder.isPasswordValid(k, cid + ";" + i + ";" + t, code)) {
			long curr = System.currentTimeMillis();
			if (t + h > curr) {
				Content c = contentMng.findById(cid);
				if (c != null) {
					List<ContentAttachment> list = c.getAttachments();
					if (list.size() > i) {
						contentCountMng.downloadCount(c.getId());
						ContentAttachment ca = list.get(i);
						download(request,response,ca.getPath(),ca.getName());
						return;
					} else {
						log.info("download index is out of range: {}", i);
					}
				} else {
					log.info("Content not found: {}", cid);
				}
			} else {
				log.info("download time is expired!");
			}
		} else {
			log.info("download key error!");
		}
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
	/**
	 * 下载WebRoot下附件（自VFSUtil中Copy）
	 * @param request
	 * @param response
	 * @param filePath
	 * @param downloadFileName
	 * @see VFSUtil
	 */
	public static void download(HttpServletRequest request, HttpServletResponse response, String filePath, String downloadFileName) {
    	InputStream reader = null;
		OutputStream out = null;
		try {
			filePath = normalize(filePath);
			reader = request.getServletContext().getResourceAsStream(filePath);
			byte[] buf = new byte[1024];
			int len = 0;
			response.setContentType("application/octet-stream");// 均不提供直接打开
			response.setHeader("Content-Disposition",
					"attachment;filename=" + HttpUtils.convert(request, downloadFileName));
			out = response.getOutputStream();
			while ((len = reader.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			out.flush();
		} catch (IOException e) {
			log.error("读取文件["+filePath+"]报错", e);
		} finally{
			if(reader != null){
				try{
					reader.close();
				}catch(Exception ex){
					log.error("关闭文件流出错", ex);
				}
			}
			if(out != null) {
				try{
					out.close();
				}catch(Exception ex){
					log.error("关闭输出流出错", ex);
				}
			}
		}
    }
	/**
     * 格式化路径分隔符（自VFSUtil中Copy）
     * @param original
     * @return
     */
	private static String normalize(String original) {
        if (log.isDebugEnabled()) {
        	log.debug("normalize前[" + original + "]");
        }
        
        original = original.replace('\\', '/');
        original = eliminateRedundantSlassh(original);
        
        /**
         * in Linux(Unix like) system, must add prefix "/", unckecked,
         * and in WindowNT, if there is no ":", shoulb add one.
         */
        if (original.indexOf(':') == -1) {
            if (!original.startsWith("/")) {
                original = "/" + original;
            }
        } else {
            if (original.startsWith("/")) {
                original = original.substring(1);
            } 
        }

        if (original.endsWith("/")) {
            original = original.substring(0, original.length() - 1);
        }
        
        if (log.isDebugEnabled()) {
        	log.debug("normalize后[" + original + "]");
        }
        
        return original;
    }
	
	/**
	 * （自VFSUtil中Copy）
     * 从路径名称中排除多余的 "/", 如果路径中有"\", 结果我没有测试过; 通常调用这个方法之前
     * 一定要把 "\" 转成 "/"
     * @return  返回排除了多余的"/"的路径
     */
    private static String eliminateRedundantSlassh(String path) {
        if (log.isDebugEnabled()) {
        	log.debug("要排除多余的'/'之前[" + path + "]");
        }
        
        boolean isSlash = false;
        StringBuffer result = new StringBuffer(path.length());
        
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            
            if (c != '/' || !isSlash) {
                result.append(c);
            }
            
            isSlash = (c == '/');
        }

        if (log.isDebugEnabled()) {
        	log.debug("要排除多余的'/'之前[" + path + "]");
        }
        
        return result.toString();
    }
	/**
	 * 获得下载key和time
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/attachment_url.jspx", method = RequestMethod.GET)
	public void url(Integer cid, Integer n, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		if (cid == null || n == null) {
			return;
		}
		CmsConfig config = CmsUtils.getSite(request).getConfig();
		String code = config.getDownloadCode();
		long t = System.currentTimeMillis();
		JSONArray arr = new JSONArray();
		String key;
		for (int i = 0; i < n; i++) {
			key = pwdEncoder.encodePassword(cid + ";" + i + ";" + t, code);
			arr.put("&t=" + t + "&k=" + key);
		}
		ResponseUtils.renderText(response, arr.toString());
	}

	@Autowired
	private ContentMng contentMng;
	@Autowired
	private ContentCountMng contentCountMng;
	@Autowired
	private PwdEncoder pwdEncoder;

}
