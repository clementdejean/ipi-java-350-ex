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
	public void testCalculPerformanceCommercialCaTraiteNull() throws EmployeException {
		//When
		try {
			employeService.calculPerformanceCommercial("C12345", null, 100L);
			Assertions.fail("Faux");
		} catch (Exception e) {
		//Then
			Assertions.assertThat(e).isInstanceOf(EmployeException.class);
			Assertions.assertThat(e.getMessage()).isEqualTo("Le chiffre d'affaire traité ne peut être négatif ou null !");
		}

	}
    
    //Le chiffre d'affaire traité ne peut être négatif !
    @Test
    public void testCalculPerformanceCommercialCaTraiteNeg() throws EmployeException {
    	//When
		try {
			employeService.calculPerformanceCommercial("C12345", -50L, 100L);
			Assertions.fail("Faux");
		} catch (Exception e) {
		//Then
			Assertions.assertThat(e).isInstanceOf(EmployeException.class);
			Assertions.assertThat(e.getMessage()).isEqualTo("Le chiffre d'affaire traité ne peut être négatif ou null !");
		}
	}
    
    //L'objectif de chiffre d'affaire ne peut être null !
    @Test
    public void testCalculPerformanceCommercialObjectifCaNull() throws EmployeException {
    	try {
			employeService.calculPerformanceCommercial("C12345", 50L, null);
			Assertions.fail("Faux");
		} catch (Exception e) {
		//Then
			Assertions.assertThat(e).isInstanceOf(EmployeException.class);
			Assertions.assertThat(e.getMessage()).isEqualTo("L'objectif de chiffre d'affaire ne peut être négatif ou null !");
		}
    }
    
    // L'objectif de chiffre d'affaire ne peut être négatif !
    @Test
    public void testCalculPerformanceCommercialObjectifCaNeg() throws EmployeException {
    	try {
			employeService.calculPerformanceCommercial("C12345", 50L, -100L);
			Assertions.fail("Faux");
		} catch (Exception e) {
		//Then
			Assertions.assertThat(e).isInstanceOf(EmployeException.class);
			Assertions.assertThat(e.getMessage()).isEqualTo("L'objectif de chiffre d'affaire ne peut être négatif ou null !");
		}
    }

	@Test
	public void testCalculPerformanceCommercialMatriculeNull() throws EmployeException {
		//When
		try {
			employeService.calculPerformanceCommercial(null, 500L, 1000L);
			Assertions.fail("Ca aurait dû planter !");
		} catch (Exception e) {
		//Then
			Assertions.assertThat(e).isInstanceOf(EmployeException.class);
			Assertions.assertThat(e.getMessage()).isEqualTo("Le matricule ne peut être null et doit commencer par un C !");
		}

	}
	
	@Test
	public void testCalculPerformanceCommercialMatriculeStratC() throws EmployeException {
		//When
		try {
			employeService.calculPerformanceCommercial("T12345", 500L, 1000L);
			Assertions.fail("Ca aurait dû planter !");
		} catch (Exception e) {
		//Then
			Assertions.assertThat(e).isInstanceOf(EmployeException.class);
			Assertions.assertThat(e.getMessage()).isEqualTo("Le matricule ne peut être null et doit commencer par un C !");
		}

	}
    
    
    
    
    
    
    
    
    
    //1 : Si le chiffre d'affaire est inférieur de plus de 20% à l'objectif fixé, le commercial retombe à la performance de base
    @Test
    public void testCalculPerformanceCommercialInférieur20pourc() throws EmployeException{
        //Given
    	String matricule = "C12345";
    	Long caTraite = 70l;
    	Long objectifCa = 100l;
    	
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
    	Long caTraite = 85l;
    	Long objectifCa = 100l;
    	
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
    	Long caTraite = 110l;
    	Long objectifCa = 100l;
    	
    	 try {     	
           	employeService.calculPerformanceCommercial(matricule, caTraite, objectifCa);
           	Employe employe = employeRepository.findByMatricule(matricule);
           	Assertions.assertThat(employe.getPerformance()).isEqualTo(3); 	
           } catch (EmployeException e) {
           }
    }
    //5 : Si le chiffre d'affaire est supérieur de plus de 20%, il gagne 4 de performance
    @Test
    public void testCalculPerformanceCommercialBMore20() {
    	
    	String matricule = "C12345";
    	Long caTraite = 140l;
    	Long objectifCa = 100l;   	
    	
    	 try {     	
           	employeService.calculPerformanceCommercial(matricule, caTraite, objectifCa);
           	Employe employe = employeRepository.findByMatricule(matricule);
           	Assertions.assertThat(employe.getPerformance()).isEqualTo(6); 	
           } catch (EmployeException e) {
           }
    }

	
    
}
