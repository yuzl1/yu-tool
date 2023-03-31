package com.yu1998.yutools;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.yu1998.yutools.bean.menu.Button;
import com.yu1998.yutools.bean.menu.ButtonEnum;
import com.yu1998.yutools.bean.menu.Menu;
import com.yu1998.yutools.bean.message.WxMessage;
import com.yu1998.yutools.bean.message.WxMessageData;
import com.yu1998.yutools.config.WxConfig;
import com.yu1998.yutools.exception.YuToolsException;
import com.yu1998.yutools.utils.WxCommonUtil;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class YuToolsApplicationTests {

	@Resource
	private WxConfig wxConfig;


	@Test
	void contextLoads() throws YuToolsException {
		//-----------模版消息--------------
		WxMessage wxMessage = new WxMessage(wxConfig);
		Map<String, WxMessageData> objMap = new HashMap<>();
		objMap.put("character_string01", WxMessageData.builder().value(String.valueOf(new Date().getTime())).color("#173177").build());
		objMap.put("short_thing01", WxMessageData.builder().value("派单").color("#173177").build());
		objMap.put("time01", WxMessageData.builder().value(DateUtil.format(new Date(),"yyyy年MM月dd日 HH:mm")).color("#173177").build());
		objMap.put("character_string02", WxMessageData.builder().value("紧急").color("#173177").build());
		objMap.put("character_string03", WxMessageData.builder().value("北京301解放军总院").color("#173177").build());
		wxMessage.createJson("okaFC5_h47THVDnN9uAdCoXIkiMY", "NYiXJBn_4oWqdcuVAq0UuvEqlZxqgJvbcHmaJeBRVyc", null, false, null, null, objMap);
		
		WxCommonUtil wxCommonUtil = new WxCommonUtil(wxConfig);
		System.out.println(wxCommonUtil.getAccessToken());
		wxCommonUtil.sendMessage(wxMessage.getMessageBody());
		System.out.println("测试".length());
//      ---------------菜单添加---------------------
		// String uri = "http://301test.raonecloud.com:8026/wx/";
		// Menu menu = new Menu();
		// List<Button> button = new ArrayList<>();
		// List<Button> subButton = new ArrayList<>();
		// Button button1 = ButtonEnum.getButton(ButtonEnum.TYPE_VIEW.getType());
		// button1.setName("零星维修");
		// button1.setUrl(uri+"pages/main/index");
		// subButton.add(button1);
		// Button button2 = ButtonEnum.getButton(ButtonEnum.TYPE_VIEW.getType());
		// button2.setName("满意度管理");
		// button2.setUrl(uri+"pages/mainRepairs/index");
		// subButton.add(button2);
		// Button button3 = ButtonEnum.getButton(ButtonEnum.TYPE_VIEW.getType());
		// button3.setName("运送管理");
		// button3.setUrl(uri+"pages/mainShip/index");
		// subButton.add(button3);
		// Button yiButton = Button.builder().name("服保系统").sub_button(subButton).build();
		// button.add(yiButton);
		// //-----------------------------------------------------

		
		// menu.setButton(button);
		// String s = JSONUtil.toJsonStr(menu);
		// System.out.println(s);
		// WxCommonUtil wxCommonUtil = new WxCommonUtil(wxConfig);
		// wxCommonUtil.getAccessTokenUri();
		// wxCommonUtil.sendMenu(menu);
	}






}
