package Frames;

import Database.SQLCliente;
import Database.SQLProducto;
import Database.SQLVendedor;
import Database.SQLVenta;
import Database.SQLVentaProducto;
import Entidades.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ABM_Venta extends javax.swing.JFrame {
    private Venta Venta;
    private int modoABM = 1;
    
    public ABM_Venta() {
        this.Venta = new Venta();
        initComponents();
        
        llenarComboVendedor();
        llenarComboCliente();
        llenarComboProducto();
        
        this.Venta.setVentaProductos(new ArrayList<VentaProducto>());
        modoCrear();
        
        mostrarGrillaVentas("", "");
        mostrarVentaProductos();
    }
    
    // <editor-fold defaultstate="collapsed" desc="ComboBoxes">
    private void llenarComboVendedor(){ 
        Vendedor.addItem(new Item(0, ""));
        
        var vendedores = SQLVendedor.getAllComboBox();
        for (Map.Entry<Integer, String> entry : vendedores.entrySet()) {
            Vendedor.addItem(new Item(entry.getKey(), entry.getValue()));
        }
    }
    
    private void llenarComboCliente(){ 
        Cliente.addItem(new Item(0, ""));
        
        var clientes = SQLCliente.getAllComboBox();
        for (Map.Entry<Integer, String> entry : clientes.entrySet()) {
            Cliente.addItem(new Item(entry.getKey(), entry.getValue()));
        }
    }
    
    private void llenarComboProducto(){
        var productos = SQLProducto.getAllComboBox();
        for (ItemProducto item : productos) {
            Producto.addItem(item);
        }
    }
    // </editor-fold>
    
    private void obtenerVentaProductos(String id){
        this.Venta.setVentaProductos(SQLVentaProducto.getAll(id));
        mostrarVentaProductos();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Mostrar grillas">
    void mostrarGrillaVentas(String filterId, String filterDescripcion){
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        model.addColumn("ID");
        model.addColumn("FECHA");
        model.addColumn("VENDEDORID");
        model.addColumn("VENDEDOR");
        model.addColumn("CLIENTEID");
        model.addColumn("CLIENTE");
        model.addColumn("DESCUENTO");
        model.addColumn("TOTAL");
        
        grillaVentas.setModel(model);
        
        // Ocultar columnas de ID Vendedor y ID Cliente (Mostrar solo los nombres)
        grillaVentas.getColumnModel().getColumn(2).setMinWidth(0);
        grillaVentas.getColumnModel().getColumn(2).setMaxWidth(0);
        grillaVentas.getColumnModel().getColumn(4).setMinWidth(0);
        grillaVentas.getColumnModel().getColumn(4).setMaxWidth(0);
        
        ArrayList<Venta> ventas  = SQLVenta.getAll(filterId);
        
        for (Venta venta : ventas) {
            Object[] fila = 
            {
                venta.getId(), 
                venta.getFecha(), 
                venta.getVendedor().getId(),
                venta.getVendedor().getNombre(), 
                venta.getCliente().getId(),
                venta.getCliente().getNombre(), 
                venta.getDescuento(), 
                venta.getTotal(), 
            };
            
            model.addRow(fila);
        }
        
        grillaVentas.setModel(model);
    }
    
    void mostrarVentaProductos(){
        DefaultTableModel model = new DefaultTableModel();
        
        //model.addColumn("ID");
        model.addColumn("PRODUCTO");
        model.addColumn("CANTIDAD");
        model.addColumn("PRECIO DE VENTA");
        
        for (VentaProducto ventaProducto : this.Venta.getVentaProductos()) {
            var productoDescripcion = SQLProducto.getById(ventaProducto.getProductoId()).getDescripcion();
            
            Object[] fila = 
            {
                productoDescripcion,
                ventaProducto.getCantidad(),
                ventaProducto.getPrecioVenta(),
            };
            
            model.addRow(fila);
        }
        
        grillaVentaProductos.setModel(model);
        
        Total.setValue(this.Venta.CalculaTotal());
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Limpiar">
    private void limpiarFormulario () {                           
        Id.setValue(null);
        Fecha.setDate(null);
        Vendedor.setSelectedIndex(0);
        Cliente.setSelectedIndex(0);
        Descuento.setValue(null);
        Total.setValue(null);
    }
    
    private void limpiarGrillaVentaProductos() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"PRODUCTO", "CANTIDAD", "PRECIO DE VENTA"});
        grillaVentaProductos.setModel(model);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Modos ABM">
    private void modoCrear() {   
        modoABM = 1;
        Helpers.DeshabilitarComponente(Id);
        
        Helpers.HabilitarComponente(Vendedor);
        Helpers.HabilitarComponente(Cliente);
        Helpers.HabilitarComponente(Descuento);
        
        Helpers.HabilitarComponente(grillaVentaProductos);
        
        Id.setValue(null);
        
        this.Venta.setVentaProductos(new ArrayList<VentaProducto>());
        mostrarVentaProductos();
        
        limpiarFormulario();
        
        Fecha.setEnabled(true);
        Fecha.setDate(new Date());
        
        Descuento.setValue(0);
        Cantidad.setValue(1);
        
        radioButtonCrear.setSelected(true);
    }
    
    private void modoModificar(String id) {
        modoABM = 2;
        Helpers.DeshabilitarComponente(Id);
        
        Fecha.setEnabled(true);
        
        Helpers.HabilitarComponente(Vendedor);
        Helpers.HabilitarComponente(Cliente);
        Helpers.HabilitarComponente(Descuento);
        
        Helpers.HabilitarComponente(grillaVentaProductos);
        
        obtenerVentaProductos(id);
        mostrarVentaProductos();
        
        radioButtonModificar.setSelected(true);
    }
    
    private void modoEliminar(String id) {
        modoABM = 3;
        Helpers.DeshabilitarComponente(Id);
        
        Fecha.setEnabled(false);
        
        Helpers.DeshabilitarComponente(Vendedor);
        Helpers.DeshabilitarComponente(Cliente);
        Helpers.DeshabilitarComponente(Descuento);
        
        Helpers.DeshabilitarComponente(grillaVentaProductos);
        
        this.Venta.setVentaProductos(new ArrayList<VentaProducto>());
        
        obtenerVentaProductos(id);
        mostrarVentaProductos();
        
        radioButtonEliminar.setSelected(true);
    }
    // </editor-fold>
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupABM = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        radioButtonModificar = new javax.swing.JRadioButton();
        radioButtonCrear = new javax.swing.JRadioButton();
        radioButtonEliminar = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        buttonConfirmar = new javax.swing.JButton();
        Descuento = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        Total = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        grillaVentaProductos = new javax.swing.JTable();
        buttonEliminarVentaProducto = new javax.swing.JButton();
        buttonAgregarVentaProducto = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        Producto = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        Cantidad = new javax.swing.JFormattedTextField();
        PrecioVenta = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        Vendedor = new javax.swing.JComboBox<>();
        Cliente = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        Fecha = new com.toedter.calendar.JDateChooser();
        Id = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        grillaVentas = new javax.swing.JTable();
        buttonEliminarSeleccionado = new javax.swing.JButton();
        buttonModificarSeleccionado = new javax.swing.JButton();
        filtroVentaId = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();

        setTitle("Gestion de Productos");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Venta"));

        jLabel4.setText("Cliente");

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

        jLabel2.setText("Vendedor");

        jLabel3.setText("Descuento");

        jLabel5.setText("Acción");

        buttonConfirmar.setText("Confirmar");
        buttonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfirmar_onClick(evt);
            }
        });

        Descuento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        jLabel6.setText("Subtotal");

        Total.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        Total.setEnabled(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Productos"));

        grillaVentaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(grillaVentaProductos);

        buttonEliminarVentaProducto.setText("Limpiar");
        buttonEliminarVentaProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEliminarVentaProducto_onClick(evt);
            }
        });

        buttonAgregarVentaProducto.setText("Agregar");
        buttonAgregarVentaProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarVentaProducto(evt);
            }
        });

        jLabel9.setText("Cantidad");

        Producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductoActionPerformed(evt);
            }
        });

        jLabel10.setText("Producto");

        Cantidad.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("0"))));
        Cantidad.setText("1");

        PrecioVenta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("0"))));

        jLabel11.setText("Precio");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(12, 12, 12)
                        .addComponent(Producto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(buttonEliminarVentaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonAgregarVentaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PrecioVenta, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Cantidad))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonEliminarVentaProducto, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(buttonAgregarVentaProducto, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel8.setText("Fecha");

        Id.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Descuento, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(Vendedor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(Id, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Fecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                                    .addComponent(Cliente, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(radioButtonCrear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioButtonModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioButtonEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Descuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonConfirmar)
                            .addComponent(radioButtonEliminar)
                            .addComponent(radioButtonModificar)
                            .addComponent(radioButtonCrear)
                            .addComponent(jLabel5))))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Ventas anteriores"));

        grillaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(grillaVentas);

        buttonEliminarSeleccionado.setText("Eliminar");
        buttonEliminarSeleccionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEliminar_onClick(evt);
            }
        });

        buttonModificarSeleccionado.setText("Ver / Modificar");
        buttonModificarSeleccionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModificar_onClick(evt);
            }
        });

        filtroVentaId.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        filtroVentaId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtroVentaIdKeyReleased(evt);
            }
        });

        jLabel7.setText("Filtrar (Nro. Venta)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1082, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonModificarSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonEliminarSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filtroVentaId, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filtroVentaId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    // <editor-fold defaultstate="collapsed" desc="Botones ABM Venta">
    private void buttonConfirmar_onClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmar_onClick
        var venta = new Venta();
        
        if(validarFormulario(venta) == false){
            return;
        }
        
        venta.setVentaProductos(this.Venta.getVentaProductos());
        
        // Accion segun modo de edicion
        if(modoABM == 1){
            SQLVenta.insert(venta);
            modoCrear();
        }
        else if(modoABM == 2){
            SQLVenta.modificar(venta);
            modoCrear();
        }
        else if(modoABM == 3){
            SQLVenta.eliminar(venta.getId());
            modoCrear();
        }
        
        // Actualizar grilla
        filtroVentaId.setValue(null);
        mostrarGrillaVentas("", "");
    }//GEN-LAST:event_buttonConfirmar_onClick

    private boolean validarFormulario(Venta venta){
        // Obtengo valores del formulario
        var id = Id.getValue();
        var vendedor = (Item) Vendedor.getSelectedItem();
        var cliente = (Item) Cliente.getSelectedItem();
        var fecha = Fecha.getDate();
        var descuento = Descuento.getValue();
        
        // Obtengo objeto entidad y sus propiedades
        
        if (id != null) {
            venta.setId(((Number) Id.getValue()).intValue());
        }
        else{
            if(!radioButtonCrear.isSelected()){
                JOptionPane.showMessageDialog(null, "Debe seleccionar un id para modificar/eliminar", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        
        if (!cliente.getDescripcion().equals("")) {
            var clienteId = cliente.getId();
            venta.setClienteId(clienteId);
        }
        else{
            JOptionPane.showMessageDialog(null, "Debe ingresar un cliente valido", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        if (!vendedor.getDescripcion().equals("")) {
            var vendedorId = vendedor.getId();
            venta.setVendedorId(vendedorId);
        }
        else{
            JOptionPane.showMessageDialog(null, "Debe ingresar un vendedor valido", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        if (fecha != null) {
            venta.setFecha( fecha );
        }
        else{
            JOptionPane.showMessageDialog(null, "Debe ingresar la fecha de la venta", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        if (descuento != null) {
            double valor = ((Number) descuento).doubleValue();
            venta.setDescuento(valor);
        }
        else{
            JOptionPane.showMessageDialog(null, "Debe ingresar descuento", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private void buttonEliminar_onClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminar_onClick
        int selectedRow = grillaVentas.getSelectedRow();
        if (selectedRow != -1) {
            copiarRegistroSeleccionado(selectedRow);
            modoEliminar(grillaVentas.getValueAt(selectedRow, 0).toString());
        } else {
            JOptionPane.showMessageDialog(null, "Ningún registro seleccionado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_buttonEliminar_onClick

    private void buttonModificar_onClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificar_onClick
        int selectedRow = grillaVentas.getSelectedRow();
        if (selectedRow != -1) {
            copiarRegistroSeleccionado(selectedRow);
            modoModificar(grillaVentas.getValueAt(selectedRow, 0).toString());
        } else {
            JOptionPane.showMessageDialog(null, "Ningún registro seleccionado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_buttonModificar_onClick
    // </editor-fold>
    
    private void copiarRegistroSeleccionado(int selectedRow){
        int id = (int) grillaVentas.getValueAt(selectedRow, 0);
            
        Id.setValue((int) grillaVentas.getValueAt(selectedRow, 0));
        Fecha.setDate((Date) grillaVentas.getValueAt(selectedRow, 1));
        
        // Seleccionar ComboBox Vendedor
        var idVendedor = (int) grillaVentas.getValueAt(selectedRow, 2);
        int indexVendedorComboBox = -1;
        for (int i = 0; i < Vendedor.getItemCount(); i++) {
            Item item = Vendedor.getItemAt(i);
            if (item.getId() == idVendedor) {
                indexVendedorComboBox = i;
                break;
            }
        }
        Vendedor.setSelectedIndex(indexVendedorComboBox);
        
        // Seleccionar ComboBox Cliente
        var idCliente = (int) grillaVentas.getValueAt(selectedRow, 4);
        int indexClienteComboBox = -1;
        for (int i = 0; i < Cliente.getItemCount(); i++) {
            Item item = Cliente.getItemAt(i);
            if (item.getId() == idCliente) {
                indexClienteComboBox = i;
                break;
            }
        }
        Cliente.setSelectedIndex(indexClienteComboBox);
        
        
        Descuento.setValue((double) grillaVentas.getValueAt(selectedRow, 6));
    }
    
    private void radioButtonCrear_onClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonCrear_onClick
        modoCrear();
    }//GEN-LAST:event_radioButtonCrear_onClick

    private void filtroVentaIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtroVentaIdKeyReleased
        mostrarGrillaVentas("",filtroVentaId.getText());
    }//GEN-LAST:event_filtroVentaIdKeyReleased

    // <editor-fold defaultstate="collapsed" desc="Botones VentaProducto">
    private void buttonEliminarVentaProducto_onClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminarVentaProducto_onClick
        this.Venta.setVentaProductos( new ArrayList<VentaProducto>());
        mostrarVentaProductos();
    }//GEN-LAST:event_buttonEliminarVentaProducto_onClick

    private void buttonAgregarVentaProducto(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarVentaProducto
        var ventaProducto = new VentaProducto();
        
        if( Id.getValue() != null){
            ventaProducto.setVentaId((int)Id.getValue());
        }
        
        if( Producto.getSelectedItem() != null){
            var idProducto = ((ItemProducto) Producto.getSelectedItem()).getId();
            ventaProducto.setProductoId(idProducto);
        }
        
        ventaProducto.setCantidad(((Number) Cantidad.getValue()).intValue());
        ventaProducto.setPrecioVenta(((Number) PrecioVenta.getValue()).doubleValue());
        
        this.Venta.getVentaProductos().add(ventaProducto);
        mostrarVentaProductos();
    }//GEN-LAST:event_buttonAgregarVentaProducto
    // </editor-fold>
    
    private void ProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductoActionPerformed
        var precioLista = ((ItemProducto) Producto.getSelectedItem()).getPrecio();
        PrecioVenta.setValue(precioLista);
    }//GEN-LAST:event_ProductoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField Cantidad;
    private javax.swing.JComboBox<Item> Cliente;
    private javax.swing.JFormattedTextField Descuento;
    private com.toedter.calendar.JDateChooser Fecha;
    private javax.swing.JFormattedTextField Id;
    private javax.swing.JFormattedTextField PrecioVenta;
    private javax.swing.JComboBox<ItemProducto> Producto;
    private javax.swing.JFormattedTextField Total;
    private javax.swing.JComboBox<Item> Vendedor;
    private javax.swing.JButton buttonAgregarVentaProducto;
    private javax.swing.JButton buttonConfirmar;
    private javax.swing.JButton buttonEliminarSeleccionado;
    private javax.swing.JButton buttonEliminarVentaProducto;
    private javax.swing.ButtonGroup buttonGroupABM;
    private javax.swing.JButton buttonModificarSeleccionado;
    private javax.swing.JFormattedTextField filtroVentaId;
    private javax.swing.JTable grillaVentaProductos;
    private javax.swing.JTable grillaVentas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton radioButtonCrear;
    private javax.swing.JRadioButton radioButtonEliminar;
    private javax.swing.JRadioButton radioButtonModificar;
    // End of variables declaration//GEN-END:variables
}
