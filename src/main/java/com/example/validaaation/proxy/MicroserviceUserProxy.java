package com.example.validaaation.proxy;
import com.example.validaaation.beans.DemandeBeans;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Date;
import java.util.List;

@FeignClient(name="microservice-user", url="http://localhost:8081")

public interface MicroserviceUserProxy {

    @GetMapping ("/api/auth/userr")
    long getUserId();

}
