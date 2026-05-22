/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agencianaves2;

/**
 *
 * @author maite
 */
public class CruceloEstelar extends Nave {
    private int cantPasajeros;

    public CruceloEstelar(int cantPasajeros, String nombre, int capacidadTipulacion, int año) {
        super(nombre, capacidadTipulacion, año);
        this.cantPasajeros = cantPasajeros;
    }

    @Override
    public String toString() {
        return "CruceloEstelar: " + super.toString() + " ,cantPasajeros: " + cantPasajeros;
    }
    
    
}
