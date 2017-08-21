
package eveniment.UI;

import eveniment.DataLayer.EventItemJpaController;
import eveniment.DataLayer.EventJpaController;
import eveniment.DataLayer.PeriodJpaController;
import eveniment.DataLayer.ProgramJpaController;
import eveniment.DataLayer.exceptions.NonexistentEntityException;
import eveniment.Entities.Enums.RowState;
import eveniment.Entities.Event;
import eveniment.Entities.Users;
import eveniment.UI.CustomControls.EditEventCellActions;
import eveniment.UI.Models.ListEventsTableModel;
import eveniment.UI.Models.Renders.ButtonEditor;
import eveniment.UI.Models.Renders.ButtonRenderer;
import eveniment.UI.Models.Renders.EditEventCellRender;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;

public class ListEventsForm extends javax.swing.JFrame {

    private final Users _user;
    private final EntityManagerFactory _entityManagerFactory;
    private final PeriodJpaController _periodController;
    private final EventJpaController _eventController;
    private final ProgramJpaController _programController;
    private final EventItemJpaController _eventItemController;
    private ListEventsTableModel _eventModel;

    public ListEventsForm(EntityManagerFactory entityManagerFactory, Users user) {
        initComponents();
        
        _user = user;
        _entityManagerFactory = entityManagerFactory;
        _periodController = new PeriodJpaController(entityManagerFactory);
        _eventController = new EventJpaController(entityManagerFactory);
        _programController = new ProgramJpaController(entityManagerFactory);
        _eventItemController = new EventItemJpaController(entityManagerFactory);
        
        setModels();
        setLocationRelativeTo(null);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        spEvents = new javax.swing.JScrollPane();
        tblEvents = new javax.swing.JTable();
        pnlActionsBase = new javax.swing.JPanel();
        pnlActions = new javax.swing.JPanel();
        btnEdit = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        pnlActionDelete = new javax.swing.JPanel();
        btnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Eveniment - Lista evenimente");

        lblTitle.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblTitle.setText("Lista evenimente");
        getContentPane().add(lblTitle, java.awt.BorderLayout.PAGE_START);

        tblEvents.setModel(new javax.swing.table.DefaultTableModel(
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
        spEvents.setViewportView(tblEvents);

        getContentPane().add(spEvents, java.awt.BorderLayout.CENTER);

        pnlActionsBase.setLayout(new java.awt.BorderLayout());

        pnlActions.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnEdit.setText("Adauga eveniment");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        pnlActions.add(btnEdit);

        btnAdd.setText("Modifica eveniment");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        pnlActions.add(btnAdd);

        pnlActionsBase.add(pnlActions, java.awt.BorderLayout.CENTER);

        btnDelete.setText("Sterge eveniment");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        pnlActionDelete.add(btnDelete);

        pnlActionsBase.add(pnlActionDelete, java.awt.BorderLayout.WEST);

        getContentPane().add(pnlActionsBase, java.awt.BorderLayout.SOUTH);

        setSize(new java.awt.Dimension(1028, 613));
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        showEditWindow(null);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
       Event event = getSelectedEvent();
        
        if(event == null)
            return;
        
        showEditWindow(event);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        Event event = getSelectedEvent();
        
        if(event == null)
            return;
        
        int result = JOptionPane.showConfirmDialog(this, "Esti sigur ca vrei sa stergi evenimentul selectat?", getTitle(), JOptionPane.YES_NO_OPTION);
        
        if(result != JOptionPane.YES_OPTION)
            return;
        
        event.setRowState(RowState.Deleted.toString());
        try {
            _eventController.edit(event);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Evenimentul nu a putut fi sters", getTitle(), JOptionPane.ERROR_MESSAGE);
            return;
        }
        _eventModel.setEvents(getEvents());
        
    }//GEN-LAST:event_btnDeleteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlActionDelete;
    private javax.swing.JPanel pnlActions;
    private javax.swing.JPanel pnlActionsBase;
    private javax.swing.JScrollPane spEvents;
    private javax.swing.JTable tblEvents;
    // End of variables declaration//GEN-END:variables

    private void setModels() {
        _eventModel = new ListEventsTableModel(getEvents());
        
        tblEvents.setModel(_eventModel);
        
//        TableColumn column = tblEvents.getColumnModel().getColumn(4);
//        column.setPreferredWidth(180);
//        column.setMinWidth(180);
//        column.setMaxWidth(180);
        
        tblEvents.setRowHeight(35);
    }

    private List<Event> getEvents() {
        List<Event> events = new ArrayList<>();
        for(Event event : _eventController.findEventEntities())
            if(!event.getRowState().equals(RowState.Deleted.toString()))
                events.add(event);
        
        return events;
    }

    private void showEditWindow(Event event) {
        EventEditorForm editor = new EventEditorForm(event, _entityManagerFactory, _user);
        editor.onConfirm(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _eventModel.setEvents(getEvents());
            }
        });
        editor.setVisible(true);
    }

    private Event getSelectedEvent() {
        int row = tblEvents.getSelectedRow();
        
        if(row < 0){
            JOptionPane.showMessageDialog(this, "Selectati un eveniment", getTitle(), JOptionPane.WARNING_MESSAGE);
            return null;
        }
        
        return _eventModel.getItem(row);
    }
}

