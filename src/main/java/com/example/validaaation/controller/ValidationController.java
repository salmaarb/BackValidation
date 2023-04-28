package com.example.validaaation.controller;

import com.example.validaaation.beans.DemandeBeans;
import com.example.validaaation.beans.jourBeans;
import com.example.validaaation.model.Validation;
import com.example.validaaation.proxy.MicroserviceDemandeProxy;
import com.example.validaaation.proxy.MicroserviceFerieProxy;
import com.example.validaaation.proxy.MicroserviceUserProxy;
import com.example.validaaation.repository.ValidationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:8081"})

@RestController
@RequestMapping("/validation")
public class ValidationController {

    @Autowired
    ValidationRepo validationRepo;
    @Autowired
    MicroserviceDemandeProxy microserviceDemandeProxy;
    @Autowired
    MicroserviceFerieProxy microserviceFerieProxy;
    @Autowired
    MicroserviceUserProxy microserviceUserProxy;
    @PostMapping(value = "/v")
    public ResponseEntity<Validation> etatDemande(@RequestBody Validation val){
        long v=microserviceDemandeProxy.getSolde(val.getIdDemande());

        if(v>=1 && v<=22) {
            System.out.println(val.getIdDemande());
            Date d = microserviceDemandeProxy.getDateDebut(val.getIdDemande());
            Date d1 = microserviceDemandeProxy.getDateFin(val.getIdDemande());
            LocalDate startDate = LocalDate.of(d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear(), d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue(), d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfMonth());
            LocalDate endDate = LocalDate.of(d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear(), d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue(), d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfMonth());
            List<jourBeans> ferieList = microserviceFerieProxy.find();
            //System.out.println(microserviceDemandeProxy.getDateDebut(id).getDay());
            long daysBetween = Math.abs(ChronoUnit.DAYS.between(startDate, endDate));
            int numWeekendDays = 0;
            int numFerieDays = 0;
            for (int i = 0; i <= daysBetween; i++) {
                LocalDate day = startDate.plusDays(i);
                if (day.getDayOfWeek().getValue() >= 6) {
                    numWeekendDays++;
                } else {
                    for (jourBeans ferie : ferieList) {
                        LocalDate ferieStartDate = ferie.getDateDebut();
                        LocalDate ferieEndDate = ferie.getDateFin();
                        if (!day.isBefore(ferieStartDate) && !day.isAfter(ferieEndDate)) {
                            numFerieDays++;
                            break;
                        }
                    }
                }

            }
            long businessDaysBetween = daysBetween - numWeekendDays - numFerieDays;
            v=v-businessDaysBetween;
            System.out.println("Durée de temps ouvrable entre " + startDate + " et " + endDate+ ": " + businessDaysBetween);
            System.out.println("salouma vous reste :) " + v);
            if(v > 0) {
                Validation vall = validationRepo.save(val);
                DemandeBeans demande = microserviceDemandeProxy.recupererUneDemande(vall.getIdDemande());
                System.out.println(demande);

               microserviceDemandeProxy.updateEtat(vall.getIdDemande());
                microserviceDemandeProxy.updateSolde((int) v,val.getIdDemande());
                return new ResponseEntity<Validation>(vall, HttpStatus.CREATED);
            } else {
                microserviceDemandeProxy.updateSolde(0,val.getIdDemande());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);


        }






    }
    @GetMapping("/demandes")
    public List<DemandeBeans> listeDesDemandes(){
        List<DemandeBeans> demandes = microserviceDemandeProxy.listeDesDemandes();
        return demandes;
    }
    @GetMapping("/jourjo")
    public List<LocalDate> getDate(){
        return microserviceFerieProxy.getDate();
    }
    @GetMapping("/jourr")
    public List<jourBeans> find(){
        List<jourBeans> demandes = microserviceFerieProxy.find();
        return demandes;
    }
    @GetMapping("/test/{id}")
    public long  test(@PathVariable int id){
        Date d=microserviceDemandeProxy.getDateDebut(id);
        Date d1=microserviceDemandeProxy.getDateFin(id);
        LocalDate startDate = LocalDate.of(d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear(),d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue(),d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfMonth());
        LocalDate endDate = LocalDate.of(d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear(), d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue(), d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfMonth());
        List<jourBeans> ferieList = microserviceFerieProxy.find();
        //System.out.println(microserviceDemandeProxy.getDateDebut(id).getDay());
        long daysBetween = Math.abs(ChronoUnit.DAYS.between(startDate, endDate));
        int numWeekendDays = 0;
        int numFerieDays = 0;
        for (int i = 0; i <= daysBetween; i++) {
            LocalDate day = startDate.plusDays(i);
            if (day.getDayOfWeek().getValue() >= 6) {
                numWeekendDays++;
            } else {
                for (jourBeans ferie : ferieList) {
                    LocalDate ferieStartDate = ferie.getDateDebut();
                    LocalDate ferieEndDate = ferie.getDateFin();
                    if (!day.isBefore(ferieStartDate) && !day.isAfter(ferieEndDate)) {
                        numFerieDays++;
                        break;
                    }
                }
            }
        }
        long businessDaysBetween = daysBetween - numWeekendDays - numFerieDays;

        System.out.println("Durée de temps ouvrable entre " + startDate + " et " + endDate+ ": " + businessDaysBetween);
        return businessDaysBetween;






    }
    @GetMapping("/v")
        public List<Validation> allValidation(){
        List<Validation> validations = validationRepo.findAll();
        return validations;
        }
    @GetMapping ("/dateDebut/{id}")
    public Date getDateDebut(@PathVariable int id)
    {
Date d=microserviceDemandeProxy.getDateDebut(id);
    LocalDate localDate=    d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


        System.out.println(localDate.getMonthValue());
        return  microserviceDemandeProxy.getDateDebut(id);

    }
    @GetMapping ("/dateFin/{id}")
    public Date getDateFin(@PathVariable int id)
    {

        return  microserviceDemandeProxy.getDateFin(id);

    }
    @GetMapping ("/api/auth/userr")
    public Long getUserId()
    {

        return microserviceUserProxy.getUserId();

    }
}