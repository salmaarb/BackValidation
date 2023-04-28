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

@FeignClient(name="microservice-demande", url="http://localhost:9001")

public interface MicroserviceDemandeProxy {
    @GetMapping(value = "/demande/demandes")
    List<DemandeBeans> listeDesDemandes();
    @GetMapping(value = "/demande/jour")
    List<DemandeBeans> listeDesDemandesjour();
    @GetMapping( value = "/demande/demande/{id}")
    DemandeBeans recupererUneDemande(@PathVariable("id") int id);
    @GetMapping ("/demande/dateDebut/{id}")
     Date getDateDebut(@PathVariable("id") int id);
    @GetMapping ("/demande/dateFin/{id}")
     Date getDateFin(@PathVariable("id") int id);
     @GetMapping("/demande/test/{id}")
     long  test(@PathVariable("id") int id);
    @GetMapping ("/demande/solde/{id}")
   long getSolde(@PathVariable int id);
    @PutMapping("/demande/solde/{n}/{id}")
    void updateSolde(@PathVariable int n,@PathVariable int id);
    @PutMapping ("/demande/etat/{id}")
    void updateEtat(@PathVariable int id);
}
