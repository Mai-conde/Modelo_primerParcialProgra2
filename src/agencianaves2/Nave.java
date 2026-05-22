/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agencianaves2;

/**
 *
 * @author maite
 */
public abstract class Nave implements Comparable<Nave> {
    protected String nombre;
    protected int capacidadTripulacion;
    protected int año;

    public Nave(String nombre, int capacidadTripulacion, int año) {
        this.nombre = nombre;
        this.capacidadTripulacion = capacidadTripulacion;
        this.año = año;
    }
    
    /*public boolean sonIguales(Nave n1) {
        if (n1 instanceof Nave) {
            Nave n2 = (Nave) n1;
            return (this.año == n2.año && this.nombre.equals(n2.nombre));
        }
        return false;
    }
*/
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Nave otraNave = (Nave) obj;
        return this.año == otraNave.año && this.nombre.equalsIgnoreCase(otraNave.nombre);
    }
    
    @Override
    public int compareTo(Nave n) {
        int resultadoAño = Integer.compare(n.año, this.año);

        // Si los años son distintos (ej: 2026 vs 2020), ya sabemos quién gana, devolvemos el resultado.
        if (resultadoAño != 0) {
            return resultadoAño;
        }

        // 2. Si los años empatan (resultadoAño == 0), desempatamos por Tripulación (Mayor a menor)
        return Integer.compare(n.capacidadTripulacion, this.capacidadTripulacion);
    }

    @Override
    public String toString() {
        return "Nave " + "nombre: " + nombre + ", capacidadTipulacion: " + capacidadTripulacion + " ,año: " + año;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidadTripulacion() {
        return capacidadTripulacion;
    }

    public int getAño() {
        return año;
    }
    
    
    
    
    
}
