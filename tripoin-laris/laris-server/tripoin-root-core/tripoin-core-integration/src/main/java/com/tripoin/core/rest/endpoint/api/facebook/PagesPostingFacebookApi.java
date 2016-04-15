package com.tripoin.core.rest.endpoint.api.facebook;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.Page;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;

import com.tripoin.core.pojo.LinkedAccount;
import com.tripoin.core.rest.endpoint.XReturnStatus;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("pagesPostingFacebookApi")
public class PagesPostingFacebookApi extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(PagesPostingFacebookApi.class);
	
	private Facebook facebook;
	
	public List<Page> getPostingPageList(LinkedAccount linkedAccount){
		List<Page> pagedLikeList = null;
		try {
			facebook = new FacebookTemplate(linkedAccount.getToken());
			pagedLikeList = doRecursivePagedList(facebook.likeOperations().getPagesLiked());
		} catch (Exception e) {
			LOGGER.error("Error Posting Page",e);
		}
		return pagedLikeList;
	}
	
	public String getPostingPageString(LinkedAccount linkedAccount){
		String pagedLikeList = null;
		try {
			facebook = new FacebookTemplate(linkedAccount.getToken());
			pagedLikeList = doRecursivePagedString(facebook.likeOperations().getPagesLiked());
		} catch (Exception e) {
			LOGGER.error("Error Posting Page",e);
		}
		return pagedLikeList;
	}
	
	private List<Page> doRecursivePagedList(PagedList<Page> pagedList){
		List<Page> list = new ArrayList<Page>();
		for(Page page : pagedList){
			list.add(page);
		}
		PagedList<Page> pagedNextList = facebook.likeOperations().getPagesLiked(pagedList.getNextPage());
		if(pagedNextList != null && !pagedNextList.isEmpty()){
			if(pagedNextList.size() > 0){
				list.addAll(doRecursivePagedList(pagedNextList));
			}
		}
		return list;				
	}
	
	private String doRecursivePagedString(PagedList<Page> pagedList){
		String pages = "";
		for(int i=0; i<pagedList.size(); i++){
			pages = pages.concat("'").concat(pagedList.get(i).getId()).concat("'");
			if((i+1)!=pagedList.size()){
				pages = pages.concat(",");
			}
		}
		PagedList<Page> pagedNextList = facebook.likeOperations().getPagesLiked(pagedList.getNextPage());
		if(pagedNextList != null && !pagedNextList.isEmpty()){
			if(pagedNextList.size() > 0){
				pages = pages.concat(",").concat(doRecursivePagedString(pagedNextList));
			}
		}
		return pages;				
	}

}
