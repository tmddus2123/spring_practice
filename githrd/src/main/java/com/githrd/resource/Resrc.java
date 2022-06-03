package com.githrd.resource;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class Resrc {
	
	@RequestMapping({"/img/*", "/css/*", "/js/*"})
	public String getResource() {
		String view = "";
		// /img/avatar/img_avatar1.png
		// ==> img/avatar/img_avatar1
		return view;
	}
}
