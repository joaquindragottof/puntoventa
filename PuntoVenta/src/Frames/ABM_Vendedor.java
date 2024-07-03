package Frames;

import Database.SQLVendedor;
import Entidades.Vendedor;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ABM_Vendedor extends javax.swing.JFrame {
    private int modoABM = 1;
    
    public ABM_Vendedor() {
        initComponents();
        modoCrear();
        mostrarGrillaVendedores("", "");
    }
    
    void mostrarGrillaVendedores(String filterId, String filterDescripcion){
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        model.addColumn("ID");
        model.addColumn("NOMBRE");
        model.addColumn("SUELDO");
        
        grillaVendedor.setModel(model);
        
        ArrayList<Vendedor> vendedores = SQLVendedor.getAll(filterDescripcion);
        
        for (Vendedor vendedor : vendedores) {
            Object[] fila = 
            {
                vendedor.getId(), 
                vendedor.getNombre(), 
                vendedor.getSueldo()
            };
            
            model.addRow(fila);
        }
        
        grillaVendedor.setModel(model);
    }
    
    private void limpiarFormulario () {                           
        Id.setValue(null);
        Nombre.setText(null);
        Sueldo.setValue(null);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Modos ABM">
    private void modoCrear() {
        modoABM = 1;
        
        Helpers.DeshabilitarComponente(Id);
        Helpers.HabilitarComponente(Nombre);
        Helpers.HabilitarComponente(Sueldo);
        
        Id.setValue(null);
        
        radioButtonCrear.setSelected(true);
    }
    
    private void modoModificar() {
        modoABM = 2;
        
        Helpers.DeshabilitarComponente(Id);
        Helpers.HabilitarComponente(Nombre);
        Helpers.HabilitarComponente(Sueldo);
        
        radioButtonModificar.setSelected(true);
    }
    
    private void modoEliminar() {
        modoABM = 3;
        
        Helpers.DeshabilitarComponente(Id);
        Helpers.DeshabilitarComponente(Nombre);
        Helpers.DeshabilitarComponente(Sueldo);
        
        radioButtonEliminar.setSelected(true);
    }
    // </editor-fold>
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupABM = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        radioButtonModificar = new javax.swing.JRadioButton();
        radioButtonCrear = new javax.swing.JRadioButton();
        radioButtonEliminar = new javax.swing.JRadioButton();
        Nombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Id = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        buttonConfirmar = new javax.swing.JButton();
        Sueldo = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        grillaVendedor = new javax.swing.JTable();
        buttonEliminarSeleccionado = new javax.swing.JButton();
        filtroNombre = new javax.swing.JFormattedTextField();
        buttonModificarSeleccionado = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setTitle("Gestion de Productos");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Vendedor"));

        buttonGroupABM.add(radioButtonModificar);
        radioButtonModificar.setText("Modificar");
        radioButtonModificar.setEnabled(false);

        buttonGroupABM.add(radioButtonCrear);
        radioButtonCrear.setText("Crear");
        radioButtonCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonCrear_onClick(evt);
            }
        });

        buttonGroupABM.add(radioButtonEliminar);
        radioButtonEliminar.setText("Eliminar");
        radioButtonEliminar.setEnabled(false);

        jLabel1.setText("ID");

        jLabel2.setText("Nombre");

        Id.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jLabel3.setText("Sueldo");

        jLabel5.setText("Acción");

        buttonConfirmar.setText("Confirmar");
        buttonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfirmar_onClick(evt);
            }
        });

        Sueldo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(radioButtonCrear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioButtonModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioButtonEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sueldo, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(141, 141, 141))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Sueldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioButtonCrear)
                    .addComponent(radioButtonModificar)
                    .addComponent(radioButtonEliminar)
                    .addComponent(jLabel5)
                    .addComponent(buttonConfirmar)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Vendedores"));

        grillaVendedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(grillaVendedor);

        buttonEliminarSeleccionado.setText("Eliminar");
        buttonEliminarSeleccionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEliminar_onClick(evt);
            }
        });

        filtroNombre.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        filtroNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtroNombreKeyReleased(evt);
            }
        });

        buttonModificarSeleccionado.setText("Modificar");
        buttonModificarSeleccionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModificar_onClick(evt);
            }
        });

        jLabel7.setText("Filtrar (Nombre)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonModificarSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonEliminarSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(10, 10, 10)
                .addComponent(filtroNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filtroNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonEliminarSeleccionado)
                    .addComponent(buttonModificarSeleccionado))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonConfirmar_onClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmar_onClick
        
        // Obtengo objeto entidad y sus propiedades
        var vendedor = new Vendedor();
        if (Id.getValue() != null) {
            vendedor.setId(((Number) Id.getValue()).intValue());
        }
        
        vendedor.setNombre(Nombre.getText());
        
        if (Sueldo.getValue() != null) {
            double sueldo = ((Number) Sueldo.getValue()).doubleValue();
            vendedor.setSueldo(sueldo);
        }
        
        // Accion segun modo de edicion
        if(modoABM == 1){
            SQLVendedor.insert(vendedor);
            limpiarFormulario();
            modoCrear();
        }
        else if(modoABM == 2){
            SQLVendedor.modificar(vendedor);
            limpiarFormulario();
            modoCrear();
        }
        else if(modoABM == 3){
            try{
                SQLVendedor.eliminar(vendedor.getId());
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Error al eliminar el Vendedor: " + ex.getMessage(), "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
            limpiarFormulario();
            modoCrear();
        }
        
        // Actualizar grilla
        filtroNombre.setValue(null);
        mostrarGrillaVendedores("", "");
    }//GEN-LAST:event_buttonConfirmar_onClick

    private void buttonEliminar_onClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminar_onClick
        int selectedRow = grillaVendedor.getSelectedRow();
        if (selectedRow != -1) {
            Id.setValue((int) grillaVendedor.getValueAt(selectedRow, 0));
            Nombre.setText((String) grillaVendedor.getValueAt(selectedRow, 1));
            Sueldo.setValue((double) grillaVendedor.getValueAt(selectedRow, 2));
            
            modoEliminar();
        } else {
            JOptionPane.showMessageDialog(null, "Ningún registro seleccionado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_buttonEliminar_onClick

    private void buttonModificar_onClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificar_onClick
        int selectedRow = grillaVendedor.getSelectedRow();
        if (selectedRow != -1) {
            Id.setValue((int) grillaVendedor.getValueAt(selectedRow, 0));
            Nombre.setText((String) grillaVendedor.getValueAt(selectedRow, 1));
            Sueldo.setValue((double) grillaVendedor.getValueAt(selectedRow, 2));
            
            modoModificar();
        } else {
            JOptionPane.showMessageDialog(null, "Ningún registro seleccionado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_buttonModificar_onClick

    private void radioButtonCrear_onClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonCrear_onClick
        modoCrear();
    }//GEN-LAST:event_radioButtonCrear_onClick

    private void filtroNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtroNombreKeyReleased
        mostrarGrillaVendedores("",filtroNombre.getText());
    }//GEN-LAST:event_filtroNombreKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField Id;
    private javax.swing.JTextField Nombre;
    private javax.swing.JFormattedTextField Sueldo;
    private javax.swing.JButton buttonConfirmar;
    private javax.swing.JButton buttonEliminarSeleccionado;
    private javax.swing.ButtonGroup buttonGroupABM;
    private javax.swing.JButton buttonModificarSeleccionado;
    private javax.swing.JFormattedTextField filtroNombre;
    private javax.swing.JTable grillaVendedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton radioButtonCrear;
    private javax.swing.JRadioButton radioButtonEliminar;
    private javax.swing.JRadioButton radioButtonModificar;
    // End of variables declaration//GEN-END:variables
}
