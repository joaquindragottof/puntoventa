package Frames;

import Database.SQLVendedor;
import Database.SQLVenta;
import Entidades.VentaResumenModel;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

public class Reporte_Ventas extends javax.swing.JFrame {
    public Reporte_Ventas() {
        initComponents();
        llenarComboVendedor();
        mostrarResumenVentas(null);
    }
    
    private void llenarComboVendedor(){ 
        Vendedor.addItem(new Item(0, "Todos"));
        
        var vendedores = SQLVendedor.getAllComboBox();
        for (Map.Entry<Integer, String> entry : vendedores.entrySet()) {
            Vendedor.addItem(new Item(entry.getKey(), entry.getValue()));
        }
    }
    
    void mostrarResumenVentas(String filterVendedor){
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        model.addColumn("FECHA");
        model.addColumn("VENDEDOR");
        model.addColumn("TOTAL");
        
        grillaResumen.setModel(model);
        
        ArrayList<VentaResumenModel> ventaResumenes = SQLVenta.getResumenVentas(filterVendedor);
        
        for (VentaResumenModel ventaResumen : ventaResumenes) {
            Object[] fila = 
            {
                ventaResumen.getFecha(), 
                ventaResumen.getNombre(), 
                ventaResumen.getTotal()
            };
            
            model.addRow(fila);
        }
        
        grillaResumen.setModel(model);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupABM = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        grillaResumen = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        Vendedor = new javax.swing.JComboBox<>();

        setTitle("Gestion de Productos");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumenes de ventas"));

        grillaResumen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(grillaResumen);

        jLabel7.setText("Filtrar (Vendedor)");

        Vendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VendedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(Vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.getAccessibleContext().setAccessibleName("Resumen de ventas por dia");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void VendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VendedorActionPerformed
        String vendedorNombre = null;
        
        var selectedItemVendedor = (Item)Vendedor.getSelectedItem();
        
        if(selectedItemVendedor != null){
            vendedorNombre = selectedItemVendedor.getDescripcion();
        }
        
        mostrarResumenVentas(vendedorNombre);
    }//GEN-LAST:event_VendedorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Item> Vendedor;
    private javax.swing.ButtonGroup buttonGroupABM;
    private javax.swing.JTable grillaResumen;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
