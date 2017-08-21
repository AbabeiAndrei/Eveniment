
package eveniment.UI;

import eveniment.DataLayer.EventItemJpaController;
import eveniment.DataLayer.EventJpaController;
import eveniment.DataLayer.PeriodJpaController;
import eveniment.DataLayer.ProgramJpaController;
import eveniment.Entities.Category;
import eveniment.Entities.Enums.PriceRate;
import eveniment.Entities.Enums.RowState;
import eveniment.Entities.Event;
import eveniment.Entities.EventItem;
import eveniment.Entities.Product;
import eveniment.Entities.Program;
import eveniment.Entities.Users;
import eveniment.UI.Filters.IntFilter;
import eveniment.UI.Models.OptionsTableModel;
import eveniment.UI.Models.ProgramTypeModel;
import eveniment.Utils.CalendarUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.text.PlainDocument;

public class EventEditorForm extends javax.swing.JFrame {

    private final Users _user;
    private final EntityManagerFactory _entityManagerFactory;
    private final PeriodJpaController _periodController;
    private final EventJpaController _eventController;
    private final ProgramJpaController _programController;
    private final EventItemJpaController _eventItemController;
    private OptionsTableModel _optionsModel;
    private List<Program> _eventTypes;
    private Program _program;
    private Event _event;

    public EventEditorForm(Event event, EntityManagerFactory entityManagerFactory, Users user) {
        initComponents();
        
        _event = event;
        _user = user;
        _entityManagerFactory = entityManagerFactory;
        _periodController = new PeriodJpaController(entityManagerFactory);
        _eventController = new EventJpaController(entityManagerFactory);
        _programController = new ProgramJpaController(entityManagerFactory);
        _eventItemController = new EventItemJpaController(entityManagerFactory);
        
        setModels();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSelected = new javax.swing.JPanel();
        pnlSelectedMain = new javax.swing.JPanel();
        pnlSelectedDate = new javax.swing.JPanel();
        pnlEventType = new javax.swing.JPanel();
        cbEventType = new javax.swing.JComboBox<>();
        pnlNumberOfPersons = new javax.swing.JPanel();
        lblNumberOfPersons = new javax.swing.JLabel();
        txtNumberOfPersons = new javax.swing.JTextField();
        pnlOptions = new javax.swing.JPanel();
        spOptions = new javax.swing.JScrollPane();
        tblOptions = new javax.swing.JTable();
        pnlTotal = new javax.swing.JPanel();
        lblTextTotal = new javax.swing.JLabel();
        lblSelectedTotal = new javax.swing.JLabel();
        btnDetails = new javax.swing.JButton();
        btnConfirm = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlSelected.setLayout(new java.awt.BorderLayout(20, 20));

        pnlSelectedMain.setLayout(new java.awt.GridLayout(3, 0, 10, 10));

        pnlSelectedDate.setLayout(new java.awt.BorderLayout());
        pnlSelectedMain.add(pnlSelectedDate);

        pnlEventType.setLayout(new java.awt.BorderLayout());

        cbEventType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));
        cbEventType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEventTypeActionPerformed(evt);
            }
        });
        pnlEventType.add(cbEventType, java.awt.BorderLayout.CENTER);

        pnlSelectedMain.add(pnlEventType);

        pnlNumberOfPersons.setLayout(new java.awt.BorderLayout(20, 0));

        lblNumberOfPersons.setText("Numar de persoane");
        pnlNumberOfPersons.add(lblNumberOfPersons, java.awt.BorderLayout.WEST);

        txtNumberOfPersons.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtNumberOfPersons.setText("1");
        pnlNumberOfPersons.add(txtNumberOfPersons, java.awt.BorderLayout.CENTER);

        pnlSelectedMain.add(pnlNumberOfPersons);

        pnlSelected.add(pnlSelectedMain, java.awt.BorderLayout.NORTH);

        pnlOptions.setLayout(new java.awt.BorderLayout());

        tblOptions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Optiuni", "Valori"
            }
        ));
        spOptions.setViewportView(tblOptions);

        pnlOptions.add(spOptions, java.awt.BorderLayout.CENTER);

        pnlSelected.add(pnlOptions, java.awt.BorderLayout.CENTER);

        pnlTotal.setLayout(new java.awt.BorderLayout());

        lblTextTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTextTotal.setText("Total : ");
        pnlTotal.add(lblTextTotal, java.awt.BorderLayout.WEST);

        lblSelectedTotal.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblSelectedTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSelectedTotal.setText("0.00 RON");
        pnlTotal.add(lblSelectedTotal, java.awt.BorderLayout.CENTER);

        btnDetails.setText("Detalii");
        btnDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailsActionPerformed(evt);
            }
        });
        pnlTotal.add(btnDetails, java.awt.BorderLayout.LINE_END);

        btnConfirm.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnConfirm.setText("Confirma");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });
        pnlTotal.add(btnConfirm, java.awt.BorderLayout.PAGE_END);

        pnlSelected.add(pnlTotal, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnlSelected, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void onConfirm(ActionListener listener){
        listener.actionPerformed(new ActionEvent(this, 1, "Confirm"));
    }
    
    private void cbEventTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEventTypeActionPerformed
        int index = cbEventType.getSelectedIndex();

        if(index < 0)
        return;

        setEventDetails(_eventTypes.get(index));
        recalculateTotal();
    }//GEN-LAST:event_cbEventTypeActionPerformed

    private void btnDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailsActionPerformed
        Event event = createEvent();

        if(event == null)
        return;

        EventOptions form = new EventOptions(event);
        form.setVisible(true);
        form.setLocationRelativeTo(this);

        int dayOfWeek = _calendar.get(Calendar.DAY_OF_WEEK);
        String dayOfWeekName = CalendarUtils.getDayName(dayOfWeek).toUpperCase();
        String monthName = CalendarUtils.getMonthName(_month);

        form.setTitle(cbEventType.getSelectedItem().toString() + " - " + dayOfWeekName + " " + _day + " " + monthName + " " + _year);
    }//GEN-LAST:event_btnDetailsActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        Event event = createEvent();

        if(event == null) {
            JOptionPane.showMessageDialog(this, "Nu s-a putut creea evenimentul", getTitle(), JOptionPane.ERROR_MESSAGE);
            return;
        }
        int result = JOptionPane.showConfirmDialog(this, "Esti sigur ca vrei sa creezi evenimentul?", getTitle(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if(result != JOptionPane.YES_OPTION)
        return;

        Collection<EventItem> items = event.getEventItemCollection();

        event.setEventItemCollection(null);

        _eventController.create(event);

        for(EventItem item : items)
        {
            item.setEventId(event);
            _eventItemController.create(item);
        }
        
        JOptionPane.showMessageDialog(this, "Eveniment creeat", getTitle(), JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnConfirmActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnDetails;
    private javax.swing.JComboBox<String> cbEventType;
    private javax.swing.JLabel lblNumberOfPersons;
    private javax.swing.JLabel lblSelectedTotal;
    private javax.swing.JLabel lblTextTotal;
    private javax.swing.JPanel pnlEventType;
    private javax.swing.JPanel pnlNumberOfPersons;
    private javax.swing.JPanel pnlOptions;
    private javax.swing.JPanel pnlSelected;
    private javax.swing.JPanel pnlSelectedDate;
    private javax.swing.JPanel pnlSelectedMain;
    private javax.swing.JPanel pnlTotal;
    private javax.swing.JScrollPane spOptions;
    private javax.swing.JTable tblOptions;
    private javax.swing.JTextField txtNumberOfPersons;
    // End of variables declaration//GEN-END:variables
    
    
    
    private void recalculateTotal() {
        float total = 0f;
        
        total += _periodController.getPrice(_day, _month, _year);
                
        Event event = createEvent();
        
        if(event != null)
            for(EventItem item : event.getEventItemCollection())
                total += item.getPrice().floatValue();
        
        DecimalFormat formater = new DecimalFormat("#.## LEI");
        
        lblSelectedTotal.setText(formater.format(total));
    }

    private void setModels() {
        
        _optionsModel = new OptionsTableModel(_entityManagerFactory, tblOptions){
            @Override
            public void valueChanged(Object sender) {
                recalculateTotal();
            }
        };    
        
        _eventTypes = _programController.findProgramEntities();
        
        ProgramTypeModel model = new ProgramTypeModel(_eventTypes);
        
        cbEventType.setModel(model);
        tblOptions.setModel(_optionsModel);
        tblOptions.setRowHeight(24);
    } 

    private void configureUi() {
        setLocationRelativeTo(null);
        
        PlainDocument doc = (PlainDocument) txtNumberOfPersons.getDocument();
        doc.setDocumentFilter(new IntFilter());
        
        txtNumberOfPersons.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
              recalculateTotal();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
              recalculateTotal();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
              recalculateTotal();
            }
        });
    }

    private void setEventDetails(Program program) {
        _program = program;
        _optionsModel.set(_program);
    }

    private Event createEvent() {
        Event event = new Event();
        
        Integer numberOfPersons;
        
        try
        {
            numberOfPersons = Integer.parseInt(txtNumberOfPersons.getText());
        }
        catch(NumberFormatException ex)
        {
            return null;
        }
            
        if(numberOfPersons <= 0)
            return null;
        
        Object item = cbEventType.getSelectedItem();
        
        if(item == null || !(item instanceof Program))
            return null;
        
        Program program = (Program)item;
        
        event.setProgramId(program);
        
        event.setDate(_calendar.getTime());
        event.setCreatedAt(CalendarUtils.getTime());
        event.setCreatedBy(_user);
        event.setRowState(RowState.Created.toString());
        event.setForUser(_user);
        event.setNumberOfPersons(numberOfPersons);
        event.setEventItemCollection(createEventItems(program, numberOfPersons));
        
        return event;
    }
    
    private Collection<EventItem> createEventItems(Program program, int persons) {
        List<EventItem> items = new ArrayList<>();
        
        if(program.getPrice().floatValue() > 0)
        {
            EventItem item = new EventItem();
            
            item.setName(program.getName());
            item.setPrice(program.getPrice());
            item.setQuantity(1);
            item.setRowState(RowState.Created.toString());
            item.setProductId(null);
            
            items.add(item);
        }
        
        float price = _periodController.getPrice(_day, _month, _year);
        
        if(price > 0)
        {
            EventItem item = new EventItem();
            
            item.setName(_day + "." + _month + "." + _year);
            item.setPrice(new BigDecimal(price));
            item.setQuantity(1);
            item.setRowState(RowState.Created.toString());
            item.setProductId(null);
            
            items.add(item);
        }
        
        int rows = _optionsModel.getRowCount();
        
        for(int i = 0; i < rows ; i++)
        {
            Object obj = _optionsModel.getDataObject(i);
            Product product = null;
            if(obj instanceof Category)
            {
                product = (Product)_optionsModel.getValueAt(i, 1);
            }
            else if(obj instanceof Product){
                if((Boolean)_optionsModel.getValueAt(i, 1))
                    product = (Product)obj;
            }
            else 
                continue;
            
            if(product == null || product.getId() == 0)
                continue;
            
            EventItem item = new EventItem();
            
            String multiplier = "";
            
            if(product.getRate().equals(PriceRate.ByPersons.toString()))
                multiplier = " x " + persons;
            
            item.setName(product.getName() + multiplier);
            item.setDescription(product.getDescription());
            item.setPrice(calculatePrice(product, persons));
            item.setProductId(product);
            item.setQuantity(1);
            item.setRowState(RowState.Created.toString());
            
            items.add(item);
        }
        
        return items;
    }
    
    private BigDecimal calculatePrice(Product product, int persons) {
        float basePrice = product.getPrice().floatValue();
        
        if(PriceRate.ByPersons.toString().equals(product.getRate()))
            basePrice *= persons;
        
        return new BigDecimal(basePrice);
    }
    
    private Boolean dayCanBeSelected(){
        for(Event event : _eventController.findEventEntities())
        {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(event.getDate());
            
            if(CalendarUtils.equals(calendar, _calendar))
                return false;
        }
        return true;
    }
}
