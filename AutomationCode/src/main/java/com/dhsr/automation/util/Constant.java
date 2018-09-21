package com.dhsr.automation.util;

public class Constant {
	public static final String WEB_URL = PropertiesUtil.getStringByKey("weixin4j.user.redirect.uri", "weixin4j.properties");
	public static final String TEMPLETID_VISIT = PropertiesUtil.getStringByKey("templetId_Visit", "weixin4j.properties");
	public static final String TEMPLETID_VISITBACK = PropertiesUtil.getStringByKey("templetId_VisitBack", "weixin4j.properties");
	public static final String TEMPLETID_VISITURL = PropertiesUtil.getStringByKey("templetId_VisitUrl", "weixin4j.properties");
	}
