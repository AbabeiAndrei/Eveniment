
package eveniment.UI;

import eveniment.Entities.Event;
import eveniment.UI.Models.EventDetailsModel;
import java.text.DecimalFormat;

public class EventOptions extends javax.swing.JFrame {

    private final Event _event;
    private EventDetailsModel _model;
    private final DecimalFormat _formater;
    
    public EventOptions(Event event) {
        initComponents();
        
        _event = event;
        _formater = new DecimalFormat("#.##");
        
        setModel();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnblTotal = new javax.swing.JPanel();
        lblTotal = new javax.swing.JLabel();
        lblTotalValue = new javax.swing.JLabel();
        spData = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnblTotal.setLayout(new java.awt.BorderLayout());

        lblTotal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTotal.setText("Total");
        pnblTotal.add(lblTotal, java.awt.BorderLayout.WEST);

        lblTotalValue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTotalValue.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalValue.setText("0.00 RON");
        pnblTotal.add(lblTotalValue, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnblTotal, java.awt.BorderLayout.PAGE_END);

        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        spData.setViewportView(tblData);

        getContentPane().add(spData, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalValue;
    private javax.swing.JPanel pnblTotal;
    private javax.swing.JScrollPane spData;
    private javax.swing.JTable tblData;
    // End of variables declaration//GEN-END:variables

    private void setModel() {
        _model = new EventDetailsModel(_event);
        tblData.setModel(_model);
        lblTotalValue.setText(_formater.format(_model.calculateToal()) + " LEI");
    }
}
