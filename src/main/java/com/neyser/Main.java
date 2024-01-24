package com.neyser;

public class Main {
    public static void main(String[] args) {
        AccesoBaseDatos accesoBaseDatos = new AccesoBaseDatos();
        accesoBaseDatos.mostrarDatos();
        //accesoBaseDatos.insertarDatos();
        //accesoBaseDatos.modificarDatos();
        accesoBaseDatos.eliminarDatos();
        accesoBaseDatos.mostrarDatos();
    }
}