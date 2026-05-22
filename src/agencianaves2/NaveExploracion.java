/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agencianaves2;

import agencianaves2.AgenciaNaves2.tipoMision;

/**
 *
 * @author maite
 */
public class NaveExploracion extends Nave implements Explorable{
    private tipoMision mision;

    public NaveExploracion(tipoMision mision, String nombre, int capacidadTipulacion, int año) {
        super(nombre, capacidadTipulacion, año);
        this.mision = mision;
    }

    @Override
    public String toString() {
        return "NaveExploracion " + super.toString() + " ,mision: " + mision;
    }

    @Override
    public void explorar() {
        System.out.println("La nave " + getNombre() + " inicio la exploracion");
    }
    
    
    
    
}
