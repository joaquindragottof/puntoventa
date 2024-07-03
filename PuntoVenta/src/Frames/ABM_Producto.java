package Frames;

import Database.SQLProducto;
import Entidades.Producto;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ABM_Producto extends javax.swing.JFrame {
    private int modoABM = 1;
    
    public ABM_Producto() {
        initComponents();
        modoCrear();
        mostrarGrillaProductos("", "");
    }
    
    void mostrarGrillaProductos(String filterId, String filterDescripcion){
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        model.addColumn("ID");
        model.addColumn("ALIAS");
        model.addColumn("DESCRIPCION");
        model.addColumn("PRECIO");
        
        grillaProducto.setModel(model);
        
        ArrayList<Producto> productos = SQLProducto.getAll(filterId, filterDescripcion);
        
        for (Producto producto : productos) {
            Object[] fila = 
            {
                producto.getId(), 
                producto.getAlias(), 
                producto.getDescripcion(), 
                producto.getPrecio()
            };
            
            model.addRow(fila);
        }
        
        grillaProducto.setModel(model);
    }
    
    private void limpiarFormulario () {                           
        Id.setValue(null);
        Alias.setText(null);
        Descripcion.setText(null);
        Precio.setValue(null);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Modos ABM">
    private void modoCrear() {
        modoABM = 1;
        
        Helpers.DeshabilitarComponente(Id);
        Helpers.HabilitarComponente(Alias);
        Helpers.HabilitarComponente(Descripcion);
        Helpers.HabilitarComponente(Precio);
        
        Id.setValue(null);
        
        radioButtonCrear.setSelected(true);
    }
    
    private void modoModificar() {
        modoABM = 2;
        
        Helpers.DeshabilitarComponente(Id);
        Helpers.HabilitarComponente(Alias);
        Helpers.HabilitarComponente(Descripcion);
        Helpers.HabilitarComponente(Precio);
        
        radioButtonModificar.setSelected(true);
    }
    
    private void modoEliminar() {
        modoABM = 3;
        
        Helpers.DeshabilitarComponente(Id);
        Helpers.DeshabilitarComponente(Alias);
        Helpers.DeshabilitarComponente(Descripcion);
        Helpers.DeshabilitarComponente(Precio);
        
        radioButtonEliminar.setSelected(true);
    }
    // </editor-fold>
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupABM = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        Alias = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        radioButtonModificar = new javax.swing.JRadioButton();
        radioButtonCrear = new javax.swing.JRadioButton();
        radioButtonEliminar = new javax.swing.JRadioButton();
        Descripcion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Id = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        buttonConfirmar = new javax.swing.JButton();
        Precio = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        grillaProducto = new javax.swing.JTable();
        buttonEliminarSeleccionado = new javax.swing.JButton();
        filtroDescripcion = new javax.swing.JFormattedTextField();
        buttonModificarSeleccionado = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setTitle("Gestion de Productos");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Producto"));

        jLabel4.setText("Alias");

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

        jLabel2.setText("Descripcion");

        Id.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jLabel3.setText("Precio");

        jLabel5.setText("Acción");

        buttonConfirmar.setText("Confirmar");
        buttonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfirmar_onClick(evt);
            }
        });

        Precio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
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
                    .addComponent(Alias, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Precio, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(141, 141, 141))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Alias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioButtonCrear)
                    .addComponent(radioButtonModificar)
                    .addComponent(radioButtonEliminar)
                    .addComponent(jLabel5)
                    .addComponent(buttonConfirmar))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Productos"));

        grillaProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(grillaProducto);

        buttonEliminarSeleccionado.setText("Eliminar");
        buttonEliminarSeleccionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEliminar_onClick(evt);
            }
        });

        filtroDescripcion.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        filtroDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtroDescripcionKeyReleased(evt);
            }
        });

        buttonModificarSeleccionado.setText("Modificar");
        buttonModificarSeleccionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModificar_onClick(evt);
            }
        });

        jLabel7.setText("Filtrar (descripcion)");

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
                .addComponent(filtroDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filtroDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonConfirmar_onClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmar_onClick
        // Obtengo valores del formulario
        var id = Id.getValue();
        var alias = Alias.getText();
        var descripcion = Descripcion.getText();
        var precio = Precio.getValue();
        
        // Obtengo objeto entidad y sus propiedades
        var producto = new Producto();
        
        if (id != null) {
            producto.setId(((Number) id).intValue());
        }
        else{
            if(modoABM != 1){
                JOptionPane.showMessageDialog(null, "Debe seleccionar un ID", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        
        if(alias != null && !alias.equals("")){
            producto.setAlias(alias);
        }
        else{
            JOptionPane.showMessageDialog(null, "Debe ingresar un alias", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if(descripcion != null && !descripcion.equals("")){
            producto.setDescripcion(descripcion);
        }
        else{
            JOptionPane.showMessageDialog(null, "Debe ingresar una descripcion", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if (precio != null) {
            double valor = ((Number) precio).doubleValue();
            producto.setPrecio(valor);
        }
        else{
            JOptionPane.showMessageDialog(null, "Debe ingresar el precio del producto", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Accion segun modo de edicion
        if(modoABM == 1){
            SQLProducto.insert(producto);
            limpiarFormulario();
            modoCrear();
        }
        else if(modoABM == 2){
            SQLProducto.modificar(producto);
            limpiarFormulario();
            modoCrear();
        }
        else if(modoABM == 3){
            try{
                SQLProducto.eliminar(producto.getId());
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Error al eliminar el producto: " + ex.getMessage(), "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
            limpiarFormulario();
            modoCrear();
        }
        
        // Actualizar grilla
        filtroDescripcion.setValue(null);
        mostrarGrillaProductos("", "");
    }//GEN-LAST:event_buttonConfirmar_onClick

    private void buttonEliminar_onClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminar_onClick
        int selectedRow = grillaProducto.getSelectedRow();
        if (selectedRow != -1) {
            Id.setValue((int) grillaProducto.getValueAt(selectedRow, 0));
            Alias.setText((String) grillaProducto.getValueAt(selectedRow, 1));
            Descripcion.setText((String) grillaProducto.getValueAt(selectedRow, 2));
            Precio.setValue((double) grillaProducto.getValueAt(selectedRow, 3));
            
            modoEliminar();
        } else {
            JOptionPane.showMessageDialog(null, "Ningún registro seleccionado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_buttonEliminar_onClick

    private void buttonModificar_onClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificar_onClick
        int selectedRow = grillaProducto.getSelectedRow();
        if (selectedRow != -1) {
            Id.setValue((int) grillaProducto.getValueAt(selectedRow, 0));
            Alias.setText((String) grillaProducto.getValueAt(selectedRow, 1));
            Descripcion.setText((String) grillaProducto.getValueAt(selectedRow, 2));
            Precio.setValue((double) grillaProducto.getValueAt(selectedRow, 3));
            
            modoModificar();
        } else {
            JOptionPane.showMessageDialog(null, "Ningún registro seleccionado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_buttonModificar_onClick

    private void radioButtonCrear_onClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonCrear_onClick
        modoCrear();
    }//GEN-LAST:event_radioButtonCrear_onClick

    private void filtroDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtroDescripcionKeyReleased
        mostrarGrillaProductos("",filtroDescripcion.getText());
    }//GEN-LAST:event_filtroDescripcionKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Alias;
    private javax.swing.JTextField Descripcion;
    private javax.swing.JFormattedTextField Id;
    private javax.swing.JFormattedTextField Precio;
    private javax.swing.JButton buttonConfirmar;
    private javax.swing.JButton buttonEliminarSeleccionado;
    private javax.swing.ButtonGroup buttonGroupABM;
    private javax.swing.JButton buttonModificarSeleccionado;
    private javax.swing.JFormattedTextField filtroDescripcion;
    private javax.swing.JTable grillaProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
