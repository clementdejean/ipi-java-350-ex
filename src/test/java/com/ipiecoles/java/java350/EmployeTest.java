package com.ipiecoles.java.java350;

import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.Entreprise;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

public class EmployeTest {

    //Employé dateEmbauche avec date 2 ans avant aujourd'hui =>
    //2 années d'ancienneté
    @Test
    public void testGetAnneeEmbaucheNmoins2(){
        //Given
        Employe employe =  new Employe();
        employe.setDateEmbauche((LocalDate.now().minusYears(2)));

        //When
        Integer nbAnnees= employe.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(nbAnnees).isEqualTo(2);
    }
    //Date dans le future => 0
    @Test
    public void testGetAnneeEmbaucheNplus2(){
        //Given
        Employe employe =  new Employe();
        employe.setDateEmbauche((LocalDate.now().plusYears(2)));

        //When
        Integer nbAnnees= employe.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(nbAnnees).isEqualTo(0);
    }
    //Date aujourd'hui => 0
    @Test
    public void testAncienneDateEmbaucheNow() {
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now());

        //When
        Integer nbAnnee = employe.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(nbAnnee).isEqualTo(0);
    }
    //Date d'embauche indéfinie => 0
    @Test
    public void testAncienneDateEmbaucheIndefinie() {
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(null);

        //When
        Integer nbAnnee = employe.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(nbAnnee).isEqualTo(0);
    }
    @ParameterizedTest
    @CsvSource({
            "1, 'T12345', 0, 1.0, 1000.0",
            "1, 'M12345', 0, 1.0, 1700.0",
            "2, 'T12345', 0, 1.0, 2300.0"
    })
    public void getPrimeAnnuelle(Integer performance, String matricule, Long nbYearsAnciennete, Double tempsPartiel, Double prime){
        //Given
       Employe employe = new Employe();
       employe.setMatricule(matricule);
       employe.setPerformance(performance);
       employe.setDateEmbauche(LocalDate.now().minusYears(nbYearsAnciennete));
       employe.setTempsPartiel(tempsPartiel);
        //When
        Double primeCalulee = employe.getPrimeAnnuelle();

        //Then
        Assertions.assertThat(primeCalulee).isEqualTo(prime);

    }
    
    @Test
    public void testPourcentageNegatif() {
    	//Given
        Employe employe =  new Employe();
        employe.setSalaire(1000d);

        //When
        employe.augmenterSalaire(-2d);

        //Then
        Assertions.assertThat(employe.getSalaire()).isEqualTo(-1000d);
    }
    
    @Test
    public void testPourcentageIsNull() {
    	//Given
    	Employe employe =  new Employe();
    	
        //When
    	employe.augmenterSalaire(0d);

        //Then
    	Assertions.assertThat(employe.getSalaire()).isEqualTo(1521.22);
    }
    
    @Test
    public void testSalaireIsNull() {
    	//Given
    	Employe employe =  new Employe();
    	employe.setSalaire(0d);
    	
        //When
    	employe.augmenterSalaire(0d);

        //Then
    	Assertions.assertThat(employe.getSalaire()).isEqualTo(0d);
    }
}
