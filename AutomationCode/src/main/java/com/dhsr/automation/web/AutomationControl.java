package com.dhsr.automation.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.dhsr.automation.service.AutomationService;



/**
 * 控制层
 * @author liuxiaogang
 *
 */
@Controller
public class AutomationControl {

	@Resource
	private AutomationService automationService;
}
