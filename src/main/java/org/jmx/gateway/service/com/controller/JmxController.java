package org.jmx.gateway.service.com.controller;

import org.jmx.gateway.service.com.entity.CpuInfo;
import org.jmx.gateway.service.com.entity.MemoryInfo;
import org.jmx.gateway.service.com.service.JmxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jmx")
public class JmxController {

    private final JmxService jmxService;

    @Autowired
    public JmxController(JmxService jmxService) {
        this.jmxService = jmxService;
    }

    @GetMapping("/memory")
    public MemoryInfo getMemoryMetric() throws Exception {
        return jmxService.getMemoryMetric();
    }

    @GetMapping("/cpu")
    public CpuInfo getCpuMetric() throws Exception {
        return jmxService.getCpuMetric();
    }
}
