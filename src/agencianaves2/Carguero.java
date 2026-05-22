/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agencianaves2;

/**
 *
 * @author maite
 */
public class Carguero extends Nave implements Explorable {
    private double capacidadcarga;

    public Carguero(double capacidadcarga, String nombre, int capacidadTipulacion, int año) {
        super(nombre, capacidadTipulacion, año);
        this.capacidadcarga = capacidadcarga;
    }

    @Override
    public String toString() {
        return  "Carguero: " + super.toString() + " ,capacidadcarga: " + capacidadcarga;
    }
    
    @Override
    public void explorar() {
        System.out.println("La nave " + getNombre() + " inicio la exploracion");
    }
}
