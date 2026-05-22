/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agencianaves2;

import java.util.ArrayList;

/**
 *
 * @author maite
 */
public class GestorExpediciones {
    private ArrayList<Nave> naves = new ArrayList<>();

    public GestorExpediciones() {
        this.naves = naves;
    }
    
    public void mostrarNaves() {
        if (naves.isEmpty()) {
            System.out.println("no hay naves registradas.");
            return; 
        }

        for (Nave nave : naves) {
            System.out.println(nave);
        }
    }
    
    public void agregarNaves(Nave n1) throws NaveDuplicadaException {
        if (naves.contains(n1)) {
            throw new NaveDuplicadaException("Ya existe una nave con esas caracteristicas");
        } 
        naves.add(n1);
        System.out.println("Nave agregada correctamente");
    }
    
    
     public void iniciarExploracion() {
        for (Nave nave: naves) {
            if (nave instanceof Explorable) {
                ((Explorable) nave).explorar();
            } else {
                System.out.println(nave.getNombre() + " es un crucero estelar, por lo que no inicia exploracion");
            }
        }
    }

    public ArrayList<Nave> getNaves() {
        return naves;
    }
     
     
    
}
            
          
            

