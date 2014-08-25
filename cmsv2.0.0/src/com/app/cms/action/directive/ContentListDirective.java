package com.app.cms.action.directive;

import static com.app.cms.Constants.TPL_STYLE_LIST;
import static com.app.cms.Constants.TPL_SUFFIX;
import static com.app.cms.web.FrontUtils.PARAM_STYLE_LIST;
import static com.app.common.web.Constants.UTF8;
import static com.app.common.web.freemarker.DirectiveUtils.OUT_LIST;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.app.cms.action.directive.abs.AbstractContentDirective;
import com.app.cms.entity.main.CmsSite;
import com.app.cms.entity.main.Content;
import com.app.cms.entity.main.ContentExt;
import com.app.cms.web.FrontUtils;
import com.app.common.web.freemarker.DirectiveUtils;
import com.app.common.web.freemarker.ParamsRequiredException;
import com.app.common.web.freemarker.DirectiveUtils.InvokeType;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * 内容列表标签
 */
public class ContentListDirective extends AbstractContentDirective {
	/**
	 * 模板名称
	 */
	public static final String TPL_NAME = "content_list";

	/**
	 * 输入参数，文章ID。允许多个文章ID，用","分开。排斥其他所有筛选参数。
	 */
	public static final String PARAM_IDS = "ids";

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		CmsSite site = FrontUtils.getSite(env);
		List<Content> list = getList(params, env);
		wrapContextPath(list, env);
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		paramWrap.put(OUT_LIST, DEFAULT_WRAPPER.wrap(list));
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		InvokeType type = DirectiveUtils.getInvokeType(params);
		String listStyle = DirectiveUtils.getString(PARAM_STYLE_LIST, params);
		if (InvokeType.sysDefined == type) {
			if (StringUtils.isBlank(listStyle)) {
				throw new ParamsRequiredException(PARAM_STYLE_LIST);
			}
			env.include(TPL_STYLE_LIST + listStyle + TPL_SUFFIX, UTF8, true);
		} else if (InvokeType.userDefined == type) {
			if (StringUtils.isBlank(listStyle)) {
				throw new ParamsRequiredException(PARAM_STYLE_LIST);
			}
			FrontUtils.includeTpl(TPL_STYLE_LIST, site, env);
		} else if (InvokeType.custom == type) {
			FrontUtils.includeTpl(TPL_NAME, site, params, env);
		} else if (InvokeType.body == type) {
			body.render(env.getOut());
		} else {
			throw new RuntimeException("invoke type not handled: " + type);
		}
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}
	
	private void wrapContextPath(List<Content> list, Environment env) {
		if (list != null) {
			String base = "";
			try {
				base = env.getDataModel().get("base").toString();
			} catch (TemplateModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (Content content : list) {
				ContentExt contentExt = content.getContentExt();
				if (StringUtils.isNotBlank(contentExt.getTitleImg())) {
					contentExt.setTitleImg(base + contentExt.getTitleImg());
				}
				if (StringUtils.isNotBlank(contentExt.getTypeImg())) {
					contentExt.setTypeImg(base + contentExt.getTypeImg());
				}
				
			}			
		}
	}
	
	@SuppressWarnings("unchecked")
	protected List<Content> getList(Map<String, TemplateModel> params,
			Environment env) throws TemplateException {
		Integer[] ids = DirectiveUtils.getIntArray(PARAM_IDS, params);
		if (ids != null) {
			return contentMng.getListByIdsForTag(ids, getOrderBy(params));
		} else {
			return (List<Content>) super.getData(params, env);
		}
	}

	@Override
	protected boolean isPage() {
		return false;
	}
}
