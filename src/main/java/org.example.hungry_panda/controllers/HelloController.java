package org.example.hungry_panda.controllers;

import org.example.hungry_panda.html.HtmlTemplate;
import org.example.hungry_panda.kubernetes.K8sHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/v1")
public class HelloController {
	private final String pandaImagePath = "https://cdn.shopify.com/s/files/1/0664/4406/7053/products/1RmM-jvqKCp8BQeIAqMSzocJ8XaYfxQNu.png?v=1666091204&width=533" ;
			// "https://media.istockphoto.com/id/1142411258/photo/how-to-make-coffee-latte-art.jpg?b=1&s=170667a&w=0&k=20&c=Yy1VEmlihscejIQ7o5hd43Jq5elwEpAiF32suyiS2M0=";

	@Value("${greeting.message}")
	private String greeting_message;

	@Value("${greeting.description}")
	private String greeting_desc;

	@GetMapping("/")
	public String index() {
		return HtmlTemplate.htmlLandingPage(
				"Hello from Hungry-Panda!",
				"a simple Java spring-Boot web application</br>",
				"Application pod: '" + K8sHandler.getPodName() + "'</br> Namespace: '" + K8sHandler.getNamespace() + "'"
		);
	}

	@GetMapping("/hello")
	public String hello(){
		return HtmlTemplate.htmlLandingPage(
				greeting_message,
				greeting_desc,
				"",
				pandaImagePath
		);
	}

	@GetMapping("/welcome")
	public String welcome(){
		return HtmlTemplate.htmlLandingPage(
				"Welcome",
				"",
				"",
				pandaImagePath
		);
	}

//
//	@GetMapping("/coffee")
//	public String coffee(){
//		return HtmlTemplate.htmlLandingPage(
//				"Who doesn't like coffee?",
//				"my favorite is Cappuccino!",
//				"",
//				pandaImagePath
//		);
//	}
}