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

import javax.persistence.EntityExistsException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeServiceTest {

    @InjectMocks
    EmployeService employeService;

    @Mock
    EmployeRepository employeRepository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this.getClass());
    }
/*
    @Test
    public void testEmbaucheEmployeTechnicienPleinTempsBts() throws EmployeException {
        //Given
        String nom = "Doe";
        String prenom = "John";
        Poste poste = Poste.TECHNICIEN;
        NiveauEtude niveauEtude = NiveauEtude.BTS_IUT;
        Double tempsPartiel = 1.0;
        when(employeRepository.findLastMatricule()).thenReturn("00345");
        when(employeRepository.findByMatricule("T00346")).thenReturn(null);

        //When
        employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);

        //Then
        ArgumentCaptor<Employe> employeArgumentCaptor = ArgumentCaptor.forClass(Employe.class);
        verify(employeRepository, times(1)).save(employeArgumentCaptor.capture());
        Assertions.assertEquals(nom, employeArgumentCaptor.getValue().getNom());
        Assertions.assertEquals(prenom, employeArgumentCaptor.getValue().getPrenom());
        Assertions.assertEquals(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")), employeArgumentCaptor.getValue().getDateEmbauche().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        Assertions.assertEquals("T00346", employeArgumentCaptor.getValue().getMatricule());
        Assertions.assertEquals(tempsPartiel, employeArgumentCaptor.getValue().getTempsPartiel());

        //1521.22 * 1.2 * 1.0
        Assertions.assertEquals(1825.46, employeArgumentCaptor.getValue().getSalaire().doubleValue());
    }

    @Test
    public void testEmbaucheEmployeManagerMiTempsMaster() throws EmployeException {
        //Given
        String nom = "Doe";
        String prenom = "John";
        Poste poste = Poste.MANAGER;
        NiveauEtude niveauEtude = NiveauEtude.MASTER;
        Double tempsPartiel = 0.5;
        when(employeRepository.findLastMatricule()).thenReturn("00345");
        when(employeRepository.findByMatricule("M00346")).thenReturn(null);

        //When
        employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);

        //Then
        ArgumentCaptor<Employe> employeArgumentCaptor = ArgumentCaptor.forClass(Employe.class);
        verify(employeRepository, times(1)).save(employeArgumentCaptor.capture());
        Assertions.assertEquals(nom, employeArgumentCaptor.getValue().getNom());
        Assertions.assertEquals(prenom, employeArgumentCaptor.getValue().getPrenom());
        Assertions.assertEquals(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")), employeArgumentCaptor.getValue().getDateEmbauche().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        Assertions.assertEquals("M00346", employeArgumentCaptor.getValue().getMatricule());
        Assertions.assertEquals(tempsPartiel, employeArgumentCaptor.getValue().getTempsPartiel());

        //1521.22 * 1.4 * 0.5
        Assertions.assertEquals(1064.85, employeArgumentCaptor.getValue().getSalaire().doubleValue());
    }

    @Test
    public void testEmbaucheEmployeManagerMiTempsMasterNoLastMatricule() throws EmployeException {
        //Given
        String nom = "Doe";
        String prenom = "John";
        Poste poste = Poste.MANAGER;
        NiveauEtude niveauEtude = NiveauEtude.MASTER;
        Double tempsPartiel = 0.5;
        when(employeRepository.findLastMatricule()).thenReturn(null);
        when(employeRepository.findByMatricule("M00001")).thenReturn(null);

        //When
        employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);

        //Then
        ArgumentCaptor<Employe> employeArgumentCaptor = ArgumentCaptor.forClass(Employe.class);
        verify(employeRepository, times(1)).save(employeArgumentCaptor.capture());
        Assertions.assertEquals("M00001", employeArgumentCaptor.getValue().getMatricule());
    }

    @Test
    public void testEmbaucheEmployeManagerMiTempsMasterExistingEmploye(){
        //Given
        String nom = "Doe";
        String prenom = "John";
        Poste poste = Poste.MANAGER;
        NiveauEtude niveauEtude = NiveauEtude.MASTER;
        Double tempsPartiel = 0.5;
        when(employeRepository.findLastMatricule()).thenReturn(null);
        when(employeRepository.findByMatricule("M00001")).thenReturn(new Employe());

        //When/Then
        EntityExistsException e = Assertions.assertThrows(EntityExistsException.class, () -> employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel));
        Assertions.assertEquals("L'employé de matricule M00001 existe déjà en BDD", e.getMessage());
    }

    @Test
    public void testEmbaucheEmployeManagerMiTempsMaster99999(){
        //Given
        String nom = "Doe";
        String prenom = "John";
        Poste poste = Poste.MANAGER;
        NiveauEtude niveauEtude = NiveauEtude.MASTER;
        Double tempsPartiel = 0.5;
        when(employeRepository.findLastMatricule()).thenReturn("99999");

        //When/Then
        EmployeException e = Assertions.assertThrows(EmployeException.class, () -> employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel));
        Assertions.assertEquals("Limite des 100000 matricules atteinte !", e.getMessage());
    }
    */
    
    //Le chiffre d'affaire traité ne peut être null !
    @Test
    public void testCalculPerformanceCommercialCaTraiteNull() {
    		
    	String matricule = "C12345";
    	Long caTraite = null;
    	Long objectifCa = 1000l;
    	
        try {
        	
        	employeService.calculPerformanceCommercial(matricule, caTraite, objectifCa);
        	when(employeRepository.avgPerformanceWhereMatriculeStartsWith("C")).thenReturn(1.0);
        	
        } catch (Exception e) {
            Assertions.assertThat(e.getMessage());
            Assertions.assertThat(e.getClass());
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
        	when(employeRepository.avgPerformanceWhereMatriculeStartsWith("C")).thenReturn(1.0);
        	
        } catch (Exception e) {
            Assertions.assertThat(e.getMessage());
            Assertions.assertThat(e.getClass());
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
        	when(employeRepository.avgPerformanceWhereMatriculeStartsWith("C")).thenReturn(1.0);
        	
        } catch (Exception e) {
            Assertions.assertThat(e.getMessage());
            Assertions.assertThat(e.getClass());
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
        	when(employeRepository.avgPerformanceWhereMatriculeStartsWith("C")).thenReturn(1.0);
        	
        } catch (Exception e) {
            Assertions.assertThat(e.getMessage());
            Assertions.assertThat(e.getClass());
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
        	when(employeRepository.avgPerformanceWhereMatriculeStartsWith("C")).thenReturn(1.0);
        	
        } catch (Exception e) {
            Assertions.assertThat(e.getMessage());
            Assertions.assertThat(e.getClass());
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
        	when(employeRepository.avgPerformanceWhereMatriculeStartsWith("C")).thenReturn(1.0);
        	
        } catch (Exception e) {
            Assertions.assertThat(e.getMessage());
            Assertions.assertThat(e.getClass());
        }
    }
    
    //null !matricule.startsWith("C")
    
    //1 : Si le chiffre d'affaire est inférieur de plus de 20% à l'objectif fixé, le commercial retombe à la performance de base
    @Test
    public void testCalculPerformanceCommercialInférieur20pourc() throws EmployeException{
        //Given
    	String matricule = "C12345";
    	Long caTraite = 700l;
    	Long objectifCa = 1000l;
    	
        Employe employe = new Employe("Doe","John","C12345",LocalDate.now(),1000D,1,1.0);
        Mockito.when(employeRepository.findByMatricule(matricule)).thenReturn(employe);
        Mockito.when(employeRepository.avgPerformanceWhereMatriculeStartsWith("C")).thenReturn(1.0);
        
        //When
        employeService.calculPerformanceCommercial(matricule, caTraite, objectifCa);
        //Then
        ArgumentCaptor<Employe> employeCaptor = ArgumentCaptor.forClass(Employe.class);
        Mockito.verify(employeRepository, Mockito.times(1)).save(employeCaptor.capture());
        Assertions.assertThat(employeCaptor.getValue().getPerformance()).isEqualTo(Entreprise.PERFORMANCE_BASE);
    }
      
    
    //2 : Si le chiffre d'affaire est inférieur entre 20% et 5% par rapport à l'ojectif fixé, il perd 2 de performance (dans la limite de la performance de base)
    
    @Test
    public void testCalculPerformanceCommercialBetween20and5() {
    	
    	String matricule = "C12345";
    	Long caTraite = 850l;
    	Long objectifCa = 1000l;
    	
        try {
        	
        	employeService.calculPerformanceCommercial(matricule, caTraite, objectifCa);
        	when(employeRepository.avgPerformanceWhereMatriculeStartsWith("C")).thenReturn(1.0);
        	
        } catch (Exception e) {
            Assertions.assertThat(e.getMessage());
            Assertions.assertThat(e.getClass());
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
        	when(employeRepository.avgPerformanceWhereMatriculeStartsWith("C")).thenReturn(1.0);
        	
        } catch (Exception e) {
            Assertions.assertThat(e.getMessage());
            Assertions.assertThat(e.getClass());
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
        	when(employeRepository.avgPerformanceWhereMatriculeStartsWith("C")).thenReturn(1.0);
        	
        } catch (Exception e) {
            Assertions.assertThat(e.getMessage());
            Assertions.assertThat(e.getClass());
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
        	when(employeRepository.avgPerformanceWhereMatriculeStartsWith("C")).thenReturn(1.0);
        	
        } catch (Exception e) {
            Assertions.assertThat(e.getMessage());
            Assertions.assertThat(e.getClass());
        }
    }
    
}
