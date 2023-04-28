package com.example.validaaation.proxy;

import com.example.validaaation.beans.DemandeBeans;
import com.example.validaaation.beans.jourBeans;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@FeignClient(name="microservice-jourFerie", url="http://localhost:9003")
public interface MicroserviceFerieProxy {
    @GetMapping("/jour/all")
    List<jourBeans> find();
    @GetMapping("/jour/debut")
    public List<LocalDate> getDate();

}
