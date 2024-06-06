 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.casinoreptefinal;

/**
 *
 * @author TimOliver
 */
public class Joc {
    private String nom;
    private String tipus;
    
    public Joc() {};

    public Joc(String nom, String tipus) {
        this.nom = nom;
        this.tipus = tipus;
    }

    // Getters i Setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getTipus() { return tipus; }
    public void setTipus(String tipus) { this.tipus = tipus; }
}
