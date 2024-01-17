package com.neyser;

import java.sql.*;
import java.util.Scanner;

public class AccesoBaseDatos
{
    // Cadena de conexión
    // Puerto en windows Xampp: JDBC_PORT="3306"
    // Puerto en MacOs Xampp: JDBC_PORT="8889"
    private final String JDBC_PORT = "3306";
    private final String JDBC_SERVER = "localhost";
    private final String JDBC_DATABASE = "bd1";
    private final String JDBC_USER = "root";
    private final String JDBC_PASSWORD = "soporte";
    private final String JDBC_UTC = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String JDBC_URL = "jdbc:mysql://" + JDBC_SERVER + ":" + JDBC_PORT +"/" + JDBC_DATABASE + JDBC_UTC;

    // Objetos SQL
    private Connection con1 = null;
    private Statement stmt1 = null;
    private PreparedStatement pstmt1 = null;
    private ResultSet rs1 = null;
    private String sql1 = null;

    // Variables para solicitud de datos de la insercion
    private Scanner sc1 = new Scanner(System.in);
    private String titulo1 = null;
    private String autor1 = null;
    private String fecha1 = null;
    private String paginas1 = null;
    private String pvp1 = null;

    // Métodos
    // Método para conectar a la base de datos
    public void conetarBD()
    {
        try {
            // Definición de la conexión
            con1 = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            // Establecer la conexión
            stmt1 = con1.createStatement();
        } catch (SQLException e) {
            //e.printStackTrace(System.out);
            System.out.println("SQLException" + e.getMessage());
            System.out.println("SQLEstado" + e.getSQLState());
            System.out.println("SQLCodigoError" + e.getErrorCode());
        }
    }
    // Método para consultar los datos
    public void mostrarDatos()
    {
        try
        {
            conetarBD();
            sql1 = "select * from obr";
            rs1 = stmt1.executeQuery(sql1);

            // Volcado de datos
            while (rs1.next())
            {
                System.out.print(rs1.getString(1) + "|");
                System.out.print(rs1.getString(2) + "|");
                System.out.print(rs1.getString(3) + "|");
                System.out.print(rs1.getString(4) + "|");
                System.out.print(rs1.getString(5) + "|");
                System.out.println(rs1.getString(6));
            }

            // Cierre de rs1, stmt1 y con1
            rs1.close();
            stmt1.close();
            con1.close();
            // Vaciado de stmt1
            stmt1 = null;

        } catch (SQLException e) {
            //e.printStackTrace(System.out);
            System.out.println("SQLException" + e.getMessage());
            System.out.println("SQLEstado" + e.getSQLState());
            System.out.println("SQLCodigoError" + e.getErrorCode());
        }
    }

    // Método para insertar datos
    public void insertarDatos(){
        System.out.println("-- Inserción de Datos");

        System.out.print("Título de la Obra: ");
        titulo1 = sc1.nextLine();

        System.out.print("autor de la Obra: ");
        autor1 = sc1.nextLine();

        System.out.print("Fecha de la Obra: ");
        fecha1 = sc1.nextLine();

        System.out.print("Páginas de la Obra: ");
        paginas1 = sc1.nextLine();

        System.out.print("Precio de la Obra: ");
        pvp1 = sc1.nextLine();

        try
        {
            conetarBD();
            sql1 = "insert into obr (tit_obr, aut_obr, fec_obr, pag_obr, pvp_obr) values (?,?,?,?,?)";
            pstmt1 = con1.prepareStatement(sql1);
            // Definición de parámetros
            pstmt1.setString(1,titulo1);
            pstmt1.setString(2,autor1);
            pstmt1.setString(3,fecha1);
            pstmt1.setInt(4,Integer.parseInt(paginas1));
            pstmt1.setFloat(5,Float.parseFloat(pvp1));

            try
            {
                // Ejecución
                pstmt1.execute();

                // Confirmar los datos como permanentes
                //con1.commit();
                System.out.println("Artículo registrado correctamente");
            } catch (SQLException e) {
                System.out.println("El articulo no se ha podido registrar");
            }

            // Cierre de rs1, stmt1 y con1
            pstmt1.close();
            rs1.close();
            stmt1.close();
            con1.close();
            // Vaciado de stmt1
            pstmt1 = null;
            stmt1 = null;

        } catch (SQLException e) {
            //e.printStackTrace(System.out);
            System.out.println("SQLException" + e.getMessage());
            System.out.println("SQLEstado" + e.getSQLState());
            System.out.println("SQLCodigoError" + e.getErrorCode());
        }



    }

}
