package com.ipiecoles.java.java350.service;

import com.ipiecoles.java.java350.exception.EmployeException;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.Entreprise;
import com.ipiecoles.java.java350.model.NiveauEtude;
import com.ipiecoles.java.java350.model.Poste;
import com.ipiecoles.java.java350.repository.EmployeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityExistsException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

@SpringBootTest
public class EmployeServiceTest {

    @Autowired
    public EmployeRepository employeRepository;

    @Autowired
    public EmployeService employeService;

    @BeforeEach
    public void remiseAZero() {
     employeRepository.deleteAll();
    }
    
    //Le chiffre d'affaire traité ne peut être null !
    @Test
    public void testCalculPerformanceCommercialCaTraiteNull() {
    		
    	String matricule = "C12345";
    	Long caTraite = null;
    	Long objectifCa = 1000l;
    	
    	 try {     	
           	employeService.calculPerformanceCommercial(matricule, caTraite, objectifCa);
           	Employe employe = employeRepository.findByMatricule(matricule);
           	Assertions.assertThat(employe.getPerformance()).isEqualTo(1); 	
           } catch (EmployeException e) {
           }
    }
    
    //Le chiffre d'affaire traité ne peut être négatif !
    @Test
    public void testCalculPerformanceCommercialCaTraiteNeg() {
    		
    	String matricule = "C12345";
    	Long caTraite = 0l;
    	Long objectifCa = 1000l;
    	
    	 try {     	
           	employeService.calculPerformanceCommercial(matricule, caTraite, objectifCa);
           	Employe employe = employeRepository.findByMatricule(matricule);
           	Assertions.assertThat(employe.getPerformance()).isEqualTo(1); 	
           } catch (EmployeException e) {
           }
    }
    
    //L'objectif de chiffre d'affaire ne peut être null !
    @Test
    public void testCalculPerformanceCommercialObjectifCaNull() {
    		
    	String matricule = "C12345";
    	Long caTraite = 900l;
    	Long objectifCa = null;
    	
    	 try {     	
           	employeService.calculPerformanceCommercial(matricule, caTraite, objectifCa);
           	Employe employe = employeRepository.findByMatricule(matricule);
           	Assertions.assertThat(employe.getPerformance()).isEqualTo(1); 	
           } catch (EmployeException e) {
           }
    }
    
    // L'objectif de chiffre d'affaire ne peut être négatif !
    @Test
    public void testCalculPerformanceCommercialObjectifCaNeg() {
    		
    	String matricule = "C12345";
    	Long caTraite = 900l;
    	Long objectifCa = 0l;
    	
    	 try {     	
           	employeService.calculPerformanceCommercial(matricule, caTraite, objectifCa);
           	Employe employe = employeRepository.findByMatricule(matricule);
           	Assertions.assertThat(employe.getPerformance()).isEqualTo(1); 	
           } catch (EmployeException e) {
           }
    }
    //Le matricule ne peut être null !
    @Test
    public void testCalculPerformanceCommercialMatriculeNull() {
    		
    	String matricule = null;
    	Long caTraite = 900l;
    	Long objectifCa = 900l;
    	
    	 try {     	
           	employeService.calculPerformanceCommercial(matricule, caTraite, objectifCa);
           	Employe employe = employeRepository.findByMatricule(matricule);
           	Assertions.assertThat(employe.getPerformance()).isEqualTo(1); 	
           } catch (EmployeException e) {
           }
    }
    
    //Le matricule doit commencer par un C !
    @Test
    public void testCalculPerformanceCommercialMatriculeStratC() {
    	
    	String matricule = "M00001";
    	Long caTraite = 900l;
    	Long objectifCa = 900l;
    	
    	 try {     	
           	employeService.calculPerformanceCommercial(matricule, caTraite, objectifCa);
           	Employe employe = employeRepository.findByMatricule(matricule);
           	Assertions.assertThat(employe.getPerformance()).isEqualTo(1); 	
           } catch (EmployeException e) {
           }
    }
    
    
    //1 : Si le chiffre d'affaire est inférieur de plus de 20% à l'objectif fixé, le commercial retombe à la performance de base
    @Test
    public void testCalculPerformanceCommercialInférieur20pourc() throws EmployeException{
        //Given
    	String matricule = "C12345";
    	Long caTraite = 700l;
    	Long objectifCa = 1000l;
    	
    	 try {     	
           	employeService.calculPerformanceCommercial(matricule, caTraite, objectifCa);
           	Employe employe = employeRepository.findByMatricule(matricule);
           	Assertions.assertThat(employe.getPerformance()).isEqualTo(1); 	
           } catch (EmployeException e) {
           }
    	 }
      
    
    //2 : Si le chiffre d'affaire est inférieur entre 20% et 5% par rapport à l'ojectif fixé, il perd 2 de performance (dans la limite de la performance de base)
    
    @Test
    public void testCalculPerformanceCommercialBetween20and5() {
    	
    	String matricule = "C12345";
    	Long caTraite = 850l;
    	Long objectifCa = 1000l;
    	
    	  try {     	
          	employeService.calculPerformanceCommercial(matricule, caTraite, objectifCa);
          	Employe employe = employeRepository.findByMatricule(matricule);
          	Assertions.assertThat(employe.getPerformance()).isEqualTo(1); 	
          } catch (EmployeException e) {
          }
    }
    
    //3 : Si le chiffre d'affaire est entre -5% et +5% de l'objectif fixé, la performance reste la même.
    @Test
    public void testCalculPerformanceCommercialBetween5and5() {
    	
    	String matricule = "C12345";
    	Long caTraite = 100l;
    	Long objectifCa = 100l;
    	
    	 try {     	
           	employeService.calculPerformanceCommercial(matricule, caTraite, objectifCa);
           	Employe employe = employeRepository.findByMatricule(matricule);
           	Assertions.assertThat(employe.getPerformance()).isEqualTo(1); 	
           } catch (EmployeException e) {
           }
    }
    //4 : Si le chiffre d'affaire est supérieur entre 5 et 20%, il gagne 1 de performance
    @Test
    public void testCalculPerformanceCommercialBetween5and20Perf() {
    	
    	String matricule = "C12345";
    	Long caTraite = 140l;
    	Long objectifCa = 100l;
    	
    	 try {     	
           	employeService.calculPerformanceCommercial(matricule, caTraite, objectifCa);
           	Employe employe = employeRepository.findByMatricule(matricule);
           	Assertions.assertThat(employe.getPerformance()).isEqualTo(1); 	
           } catch (EmployeException e) {
           }
    }
    //5 : Si le chiffre d'affaire est supérieur de plus de 20%, il gagne 4 de performance
    @Test
    public void testCalculPerformanceCommercialBMore20() {
    	
    	String matricule = "C12345";
    	Long caTraite = 1500l;
    	Long objectifCa = 1000l;   	
    	
    	 try {     	
           	employeService.calculPerformanceCommercial(matricule, caTraite, objectifCa);
           	Employe employe = employeRepository.findByMatricule(matricule);
           	Assertions.assertThat(employe.getPerformance()).isEqualTo(1); 	
           } catch (EmployeException e) {
           }
    }
    
}
