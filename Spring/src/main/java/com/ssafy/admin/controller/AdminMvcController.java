package com.ssafy.admin.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.user.model.dto.UserDto;
import com.ssafy.user.model.service.UserService;
import com.ssafy.util.PageNavigation;

@Controller
@RequestMapping("/admin")
public class AdminMvcController {

	private Logger logger = LoggerFactory.getLogger(AdminMvcController.class);
	
	private UserService userService;
	
	@Autowired
	public AdminMvcController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/user/list")
	public ModelAndView list(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		try {
			List<UserDto> list = userService.getUserList(map);
			PageNavigation pageNavigation = userService.makePageNavigation(map);
			
			mav.setViewName("user/admin-user-list");
			mav.addObject("pgno", map.get("pgno"));
			mav.addObject("key", map.get("key"));
			mav.addObject("word", map.get("word"));
			mav.addObject("navigation", pageNavigation);
			mav.addObject("userList", list);
			
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			
			mav.setViewName("error/error");
			mav.addObject("msg", "유저 목록 얻기 중 에러발생");
			
			return mav;
		}
	}

	@PostMapping("/user/modify")
	public String modify(UserDto userDto, @RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes) throws SQLException {

		userService.modify(userDto);
		
		redirectAttributes.addAttribute("pgno", map.get("pgno"));
		redirectAttributes.addAttribute("key", map.get("pgno"));
		redirectAttributes.addAttribute("word", map.get("word"));
		
		return "redirect:/admin/user/list";

	}
	
	@GetMapping("/user/remove")
	public String remove(UserDto userDto, @RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes) throws SQLException {

		userService.remove(userDto.getUserId());
		
		redirectAttributes.addAttribute("pgno", map.get("pgno"));
		redirectAttributes.addAttribute("key", map.get("pgno"));
		redirectAttributes.addAttribute("word", map.get("word"));
		
		return "redirect:/admin/user/list";

	}
	
	@PostMapping("/user/add")
	public String add(UserDto userDto, @RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes) throws Exception {
		userService.join(userDto);
		
		redirectAttributes.addAttribute("pgno", map.get("pgno"));
		redirectAttributes.addAttribute("key", map.get("pgno"));
		redirectAttributes.addAttribute("word", map.get("word"));
		
		return "redirect:/admin/user/list";
	}
}
