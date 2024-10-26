package com.mycompany.proyecto_turing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Esta clase representa la interfaz gráfica para cargar y validar las
 * transiciones de una máquina de Turing.
 */
public class Validador extends javax.swing.JFrame {

    public Validador() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 102));
        jButton1.setText("Cargar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 280, -1, -1));

        jTable1.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Estado Actual", "Simbolo Leido", "Nuevo Estado", "Simbolo Escrito", "Movimiento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 550, 200));

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(255, 255, 102));
        jTextField2.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 70, -1));

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 102));
        jButton2.setText("Validar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 280, -1, -1));

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 102));
        jButton3.setText("Regresar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 255, 102));
        jTextField1.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 80, -1));

        jLabel1.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel1.setText("Estado Final");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel3.setText("Estado Inical");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 330));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String filePath = "archivo.txt"; //Ruta para cargar el archivo.txt
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Limpia la tabla antes de cargar nuevos datos

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;

            // Lee el archivo línea por línea
            while ((line = br.readLine()) != null) {
                lineNumber++;

                if (lineNumber == 1) {
                    // Inserta el contenido de la primera línea en el JTextField1(Estado inicial)
                    jTextField1.setText(line);
                    continue;
                } else if (lineNumber == 2) {
                    // Inserta el contenido de la segunda línea en el JTextField2(Estado Final)
                    jTextField2.setText(line);
                    continue;
                }

                // Separa cada línea por comas y agrega a la tabla a partir de la línea 3 del archivo.txt
                String[] data = line.split(",");
                if (data.length == 5) {
                    model.addRow(data);  // Agrega la fila a la tabla
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  // Muestra un mensaje de error en caso de fallo algo 
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Acción del botón "Regresar" que cierra la ventana actual y abre la del menu
        menu ver = new menu();
        ver.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Obtener el estado inicial y final desde los campos de texto 
        String estadoInicial = jTextField1.getText();
        String estadoFinal = jTextField2.getText();

        // Crear una instancia de la máquina de Turing
        MaquinaTuring maquina = new MaquinaTuring(estadoInicial, estadoFinal);

        // Obtener las transiciones desde la tabla
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            // Verificar que haya suficientes columnas y si no mostrar mensaje de columnas insuficientes
            if (jTable1.getColumnCount() < 5) {
                JOptionPane.showMessageDialog(this, "La tabla debe tener al menos 5 columnas.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener cada valor de la tabla y verificar si el índice está dentro de límites
            String estadoActual = (String) jTable1.getValueAt(i, 0);
            String simboloLeidoStr = (String) jTable1.getValueAt(i, 1);
            String nuevoEstado = (String) jTable1.getValueAt(i, 2);
            String simboloEscribirStr = (String) jTable1.getValueAt(i, 3);
            String movimientoStr = (String) jTable1.getValueAt(i, 4);

            // Verificar si algún valor es null o si el índice de la columna es válido
            if (estadoActual == null || simboloLeidoStr == null || nuevoEstado == null || simboloEscribirStr == null || movimientoStr == null) {
                JOptionPane.showMessageDialog(this, "Por favor, completa todas las transiciones en la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Convertir los símbolos de escritura y lectura a caracteres
            char simboloLeido = simboloLeidoStr.charAt(0);
            char simboloEscribir = simboloEscribirStr.charAt(0);

            // Validar el movimiento
            if (!movimientoStr.equals("R") && !movimientoStr.equals("L")) {
                JOptionPane.showMessageDialog(this, "El movimiento debe ser 'R' (Derecha) o 'L' (Izquierda).", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Agregar la transición a la máquina de Turing
            maquina.agregarTransicion(estadoActual, simboloLeido, nuevoEstado, simboloEscribir, movimientoStr.charAt(0));
        }

        // Obtener la cadena de entrada desde el campo de texto
        String cadenaEntradaTexto = JOptionPane.showInputDialog(this, "Ingrese la cadena para validar:", "Entrada de cadena", JOptionPane.PLAIN_MESSAGE);

        // Verificar si el usuario ha presionado "Cancelar" o no ha ingresado ninguna cadena
        if (cadenaEntradaTexto == null || cadenaEntradaTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La operación ha sido cancelada o no se ingresó ninguna cadena.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Establecer la cadena de entrada en la máquina de Turing
        maquina.configurarEntrada(cadenaEntradaTexto);

        // Ejecutar la máquina de Turing y registrar los pasos
        StringBuilder registroPasos = new StringBuilder();
        boolean esAceptada = maquina.ejecutar(registroPasos, null);

        // Mostrar el resultado y los pasos
        JOptionPane.showMessageDialog(this, registroPasos.toString());
        if (esAceptada) {
            JOptionPane.showMessageDialog(this, "La cadena ha sido aceptada.");
        } else {
            JOptionPane.showMessageDialog(this, "La cadena ha sido rechazada.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Validador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Validador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Validador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Validador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Validador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
