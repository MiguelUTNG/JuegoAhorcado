/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import vistas.FrmPrinciapal;

/**
 *
 * @author Miguel
 */
public class JuegoAhorcado {
    //private FrmPrinciapal FrmP = new FrmPrinciapal();
    final JPanel panel = new JPanel();
    private int oportunidades = 5;                                  //Número de chances
    private String palabra;                                         //Palabra a adivinar.
    private String catego;                                          //Categoria de palabras.
    private char letra;                                             //Entrada del usuario a verificar
    private String display;                                         //Palabra a vizualizar
    private String[] imagenes = {"a0.jpg", "a1.jpg", "a2.jpg", "a3.jpg", "a4.jpg", "a5.jpg"};
//    private String[] palabras = {"PERRO", "VACA", "MARIPOSA", "DELFIN", "JAGUAR", "LEÓN",
//        "LEOPARDO", "GANSO", "COTORRO", "POLLO", "VENADO", "GALLINA"};
    private String[] palabras;                                      //<----------
    private boolean[] acertadas;
    private int errores;
    private boolean banInicio;                                      //Indica sin el juego ha iniciado.

    public int getOportunidades() {
        return oportunidades;
    }

    public void inicializarDatos() {
        errores = 0;
        oportunidades = 5;
        banInicio=false;
    }

    public void setBanInicio(boolean banInicio) {
        this.banInicio = banInicio;
    }

    public boolean isBanInicio() {
        return banInicio;
    }

    /**
     * Método que carga las palabras desde un archivo determinado.Con el uso de
     * import java.io.BufferedReader, import java.io.File y import
     * java.io.FileReader.
     */
    public void cargarPalabras() {
        //Depende de la categoria seleccionada
        BufferedReader entrada;         //Canal de comunicación de lectura
        File archivo = null;          //Nombre del archivo Fisico
        int num = 0;                  //Numero de palabras
        String linea = null;          //Una linea a la vez
        //Se seleciona el archivo
        switch (catego) {
            case "Animales":
                archivo = new File("C:\\Users\\usuario\\Desktop\\Animales.txt");
                break;
            case "TICs":
                archivo = new File("C:\\Users\\usuario\\Desktop\\Tics.txt");
                break;
            case "Películas":
                archivo = new File("C:\\Users\\usuario\\Desktop\\Películas.txt");
                break;
            case "Frutas":
                archivo = new File("C:\\Users\\usuario\\Desktop\\Frutas.txt");
                break;
            case "Equipos de Futbol":
                archivo = new File("C:\\Users\\usuario\\Desktop\\Equipos de futbol.txt");
        }
        try {
            entrada = new BufferedReader(new FileReader(archivo));
            linea = entrada.readLine();
            while (linea != null) {     //Mientras tenga texto
                num++;
                linea = entrada.readLine();
            }
            entrada.close();
            palabras = new String[num];
            /*Ciclo para agreagar las palabras*/
            entrada = new BufferedReader(new FileReader(archivo));
            for (int i = 0; i < num; i++) {
                palabras[i] = entrada.readLine();
            }
            entrada.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(panel, "Error de lectura de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Se lanzá un random.
     */
    public void seleccionarPalabras() {
        Random r1 = new Random();
        int pos = 0;
        pos = r1.nextInt(palabras.length);
        palabra = palabras[pos];
        acertadas = new boolean[palabra.length()];
    }

    public void generarDisplay() {
        display = "";
        for (int i = 0; i < palabra.length(); i++) {
            if (acertadas[i]) {
                display += palabra.charAt(i) + " ";
            } else {
                display += "_ ";
            }
        }
    }

    public boolean esCorrecta(char letra) {
        this.letra = letra;
        boolean banOk = false;
        for (int i = 0; i < palabra.length(); i++) {
            if (letra == palabra.charAt(i)) {
                acertadas[i] = true;
                banOk = true;
            }
        }
        if (!banOk && banInicio) {
            errores++;
            oportunidades--;
        }
        generarDisplay();
        return banOk;
    }

    public boolean yaGano() {
        for (int i = 0; i < acertadas.length; i++) {
            if (!acertadas[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean yaPerdio() {
        return (errores == imagenes.length - 1);
    }

    public String getDisplay() {
        return display;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setCatego(String catego) {
        this.catego = catego;
    }

    public String getImagen() {
        return imagenes[errores];
    }
}
