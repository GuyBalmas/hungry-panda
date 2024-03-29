package org.example.hungry_panda.controllers;

import java.util.Map;
import org.example.hungry_panda.html.HtmlTemplate;
import org.example.hungry_panda.kubernetes.K8sHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/k8s")
public class KubernetesController {
    
    final static Logger logger = LoggerFactory.getLogger(KubernetesController.class);

    
	private String podName = K8sHandler.getPodName();

	private String namespace = K8sHandler.getNamespace();

    private Map<String,String> envVars = K8sHandler.getEnvVars();

    @GetMapping("/")
	public String k8s_env(){
		return HtmlTemplate.htmlLandingPage(
                "Pod Name: " + podName,
                "Namespace: " + namespace,
				"Kubernetes Environment Variables: " + envVars.toString()
		);
	}

    @GetMapping("/pod")
	public String podInfo(){
        podName = K8sHandler.getPodName();
        namespace = K8sHandler.getNamespace();
		return HtmlTemplate.htmlLandingPage(
				"Pod Name: " + podName,
				"Namespace: " + namespace
		);
	}
}
