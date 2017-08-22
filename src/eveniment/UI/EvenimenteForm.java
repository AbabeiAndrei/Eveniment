
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
import eveniment.UI.Models.CalendarCellRender;
import eveniment.UI.Models.CalendarTableModel;
import eveniment.UI.Models.ProgramTypeModel;
import eveniment.UI.Models.OptionsTableModel;
import eveniment.Utils.CalendarUtils;
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

public class EvenimenteForm extends javax.swing.JFrame {

    private final PeriodJpaController _periodController;
    private final ProgramJpaController _programController;
    
    private final Calendar _calendar;
    private final CalendarTableModel _calendarModel;
    private final CalendarCellRender _calendarRender;
    private final OptionsTableModel _optionsModel;
    
    private int _day;
    private int _year;
    private int _month;
    private List<Program> _eventTypes;
    private Program _program;
    
    private final Users _user;
    private final EventJpaController _eventController;
    private final EventItemJpaController _eventItemController;

    public EvenimenteForm(EntityManagerFactory entityManagerFactory, Users user) {
        initComponents();
        configureUi();
        
        _user = user;
        _periodController = new PeriodJpaController(entityManagerFactory);
        _eventController = new EventJpaController(entityManagerFactory);
        _programController = new ProgramJpaController(entityManagerFactory);
        _eventItemController = new EventItemJpaController(entityManagerFactory);
        
        //se creaza instanta de calendar ce va fi afisata in UI
        _calendar = Calendar.getInstance();
        _calendar.setFirstDayOfWeek(0);
        
        //secreaza modelul pentru tabel
        _calendarModel = new CalendarTableModel(_calendar);
        //se creaza modelul de randare a celulelor (daca sunt rosii sau normale)
        _calendarRender = new CalendarCellRender(_calendar, _eventController);
        //se creaza modelul pentru tabelul de optiuni (cu produse)
        _optionsModel = new OptionsTableModel(entityManagerFactory, tblOptions){
            @Override
            public void valueChanged(Object sender) {
                recalculateTotal();
            }
        };
        
        _day = _calendar.get(Calendar.DAY_OF_MONTH);
        _month = _calendar.get(Calendar.MONTH);
        _year = _calendar.get(Calendar.YEAR);
        //se selecteaza luna curenta
        selectMonth(_month);
        
        //se asociaza modelele pentru controale
        setModels();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblInfo = new javax.swing.JLabel();
        spMain = new javax.swing.JSplitPane();
        pnlCalendar = new javax.swing.JPanel();
        pnlActions = new javax.swing.JPanel();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        lblCurent = new javax.swing.JLabel();
        pnlDatePicker = new javax.swing.JPanel();
        spDatePicker = new javax.swing.JScrollPane();
        tblDatePicker = new javax.swing.JTable();
        pnlSelected = new javax.swing.JPanel();
        pnlSelectedMain = new javax.swing.JPanel();
        pnlSelectedDate = new javax.swing.JPanel();
        lblSelected = new javax.swing.JLabel();
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

        lblInfo.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblInfo.setText("Alege-ti perioada pentru eveniment");
        getContentPane().add(lblInfo, java.awt.BorderLayout.PAGE_START);

        spMain.setDividerLocation(600);

        pnlCalendar.setMinimumSize(new java.awt.Dimension(200, 20));
        pnlCalendar.setName(""); // NOI18N
        pnlCalendar.setPreferredSize(new java.awt.Dimension(835, 150));
        pnlCalendar.setLayout(new java.awt.BorderLayout());

        pnlActions.setLayout(new java.awt.BorderLayout());

        btnPrev.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnPrev.setText("LUNA PRECEDENTA");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        pnlActions.add(btnPrev, java.awt.BorderLayout.WEST);

        btnNext.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnNext.setText("URMATOAREA LUNA");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        pnlActions.add(btnNext, java.awt.BorderLayout.EAST);

        lblCurent.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblCurent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCurent.setText("MONTH");
        pnlActions.add(lblCurent, java.awt.BorderLayout.CENTER);

        pnlCalendar.add(pnlActions, java.awt.BorderLayout.NORTH);

        pnlDatePicker.setLayout(new java.awt.BorderLayout());

        tblDatePicker.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblDatePicker.setColumnSelectionAllowed(true);
        tblDatePicker.getTableHeader().setReorderingAllowed(false);
        tblDatePicker.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDatePickerMouseClicked(evt);
            }
        });
        spDatePicker.setViewportView(tblDatePicker);
        tblDatePicker.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        pnlDatePicker.add(spDatePicker, java.awt.BorderLayout.CENTER);

        pnlCalendar.add(pnlDatePicker, java.awt.BorderLayout.CENTER);

        spMain.setLeftComponent(pnlCalendar);

        pnlSelected.setLayout(new java.awt.BorderLayout(20, 20));

        pnlSelectedMain.setLayout(new java.awt.GridLayout(3, 0, 10, 10));

        pnlSelectedDate.setLayout(new java.awt.BorderLayout());

        lblSelected.setText("Selectat - LUNI 13 Aprilie 2017");
        pnlSelectedDate.add(lblSelected, java.awt.BorderLayout.PAGE_START);

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

        spMain.setRightComponent(pnlSelected);

        getContentPane().add(spMain, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="Events">   
    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        selectMonth(_month - 1);// se apasa pe butonul LUNA PRECEDENTA
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        selectMonth(_month + 1);// se apasa pe butonul URMATOAREA LUNA
    }//GEN-LAST:event_btnNextActionPerformed

    private void cbEventTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEventTypeActionPerformed
        //cand se schimba programul (Nunta, dans, etc..)
        int index = cbEventType.getSelectedIndex();
        
        if(index < 0)
            return;
        //se seteaza noul program
        setEventDetails(_eventTypes.get(index));
        recalculateTotal();//se recalculeaza totalul pentru programul respectiv
    }//GEN-LAST:event_cbEventTypeActionPerformed

    private void tblDatePickerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDatePickerMouseClicked
        //cand se alege o alta data din calendar
        int row = tblDatePicker.rowAtPoint(evt.getPoint());
        int col = tblDatePicker.columnAtPoint(evt.getPoint());
        if (row >= 0 && col >= 0) {
            Object val = tblDatePicker.getValueAt(row, col);
            if(val == null)
                return;
            
            String valStr = val.toString();
            
            if(valStr.trim().equals(""))
                return;
            
            int day = Integer.parseInt(valStr);
            
            setDay(day);
        }
    }//GEN-LAST:event_tblDatePickerMouseClicked

    private void btnDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailsActionPerformed
        //cand se apasa pe butonul de detalii
        Event event = createEvent();    //se creaza un eveniment pe baza optinilor selectate
        
        if(event == null)
            return;
        
        //se afiseaza un dialog cu optiunile
        EventOptions form = new EventOptions(event);
        form.setVisible(true);
        form.setLocationRelativeTo(this);
                
        int dayOfWeek = _calendar.get(Calendar.DAY_OF_WEEK);
        String dayOfWeekName = CalendarUtils.getDayName(dayOfWeek).toUpperCase();
        String monthName = CalendarUtils.getMonthName(_month);
        
        form.setTitle(cbEventType.getSelectedItem().toString() + " - " + dayOfWeekName + " " + _day + " " + monthName + " " + _year);
    }//GEN-LAST:event_btnDetailsActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        //daca se confirma optiunea
        Event event = createEvent();
        
        if(event == null) {
            JOptionPane.showMessageDialog(this, "Nu s-a putut creea evenimentul", getTitle(), JOptionPane.ERROR_MESSAGE);
            return;
        }        
        int result = JOptionPane.showConfirmDialog(this, "Esti sigur ca vrei sa creezi evenimentul?", getTitle(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if(result != JOptionPane.YES_OPTION)
            return;
        
        //se salveaza produsele
        Collection<EventItem> items = event.getEventItemCollection();
        
        //se detaseaza de eveniment colectia de produse
        event.setEventItemCollection(null);
        
        //se adauga evenimentul in baza de date
        _eventController.create(event);
        
        //se adauga si produsele unul cate unul
        for(EventItem item : items)
        {
            item.setEventId(event);
            _eventItemController.create(item);
        }
        
        _calendarRender.refresh();
        JOptionPane.showMessageDialog(this, "Eveniment creeat", getTitle(), JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnConfirmActionPerformed
    // </editor-fold>   
    
    //<editor-fold defaultstate="collapsed" desc="methods">
    private void selectMonth(int month) {
        //daca luna este mai mica de ianuarie
        if(month <= 0)
        {
            month = 12;//fa luna decembrie
            _year--;//scade anul
        }
        else if(month >= 13)
        {
            month = 1;
            _year++;
        }
        
        int lastMonthInt = month - 1;
        int nextMonthInt = month + 1;
        
        String lastMonth = CalendarUtils.getMonthName(lastMonthInt >= 1 ? lastMonthInt : 12).toUpperCase();
        String currentMonth = CalendarUtils.getMonthName(month).toUpperCase();
        String nextMonth = CalendarUtils.getMonthName(nextMonthInt <= 12 ? nextMonthInt : 1).toUpperCase();
        
        btnPrev.setText(lastMonth);
        
        lblCurent.setText(currentMonth + " " + _year);
        
        btnNext.setText(nextMonth);
        
        _month = month;
        
        setDay(_day);
        
        fillCalendar(month);
    }
    
    private void setDay(int day){
        
        if(day <= 0)
            day = 1;
        
        _calendar.set(_year, _month, 1);
        
        if(_calendar.getActualMaximum(Calendar.DAY_OF_MONTH) < day)
            day = _calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        _calendar.set(_year, _month - 1, day);
        
        int dayOfWeek = _calendar.get(Calendar.DAY_OF_WEEK);
        String dayOfWeekName = CalendarUtils.getDayName(dayOfWeek).toUpperCase();
        String monthName = CalendarUtils.getMonthName(_month);
        
        lblSelected.setText("Selectat - " + dayOfWeekName + " " + day + " " + monthName + " " + _year);
        
        _day = day;
        
        btnConfirm.setEnabled(dayCanBeSelected());
                
        recalculateTotal();
    }
    
    private void fillCalendar(int month) {
        _calendarModel.setDate(_year, month);
    }
    
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
        tblDatePicker.setModel(_calendarModel);
        TableColumnModel tblModel = tblDatePicker.getColumnModel();
        
        for(int i = 0 ; i < tblModel.getColumnCount() ; i++)
            tblModel.getColumn(i).setCellRenderer(_calendarRender);
        
        _eventTypes = _programController.findProgramEntities();
        
        ProgramTypeModel model = new ProgramTypeModel(_eventTypes);
        
        cbEventType.setModel(model);
        tblOptions.setModel(_optionsModel);
        tblOptions.setRowHeight(24);
    } 

    private void configureUi() {
        setLocationRelativeTo(null);
        
        spMain.setResizeWeight(.8d);
        spMain.setDividerLocation(.8d);
        
        tblDatePicker.setRowHeight(50);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER );
        centerRenderer.setVerticalAlignment(JLabel.CENTER );
        tblDatePicker.setDefaultRenderer(String.class, centerRenderer);
        
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
//</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Generated fields">   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnDetails;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JComboBox<String> cbEventType;
    private javax.swing.JLabel lblCurent;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblNumberOfPersons;
    private javax.swing.JLabel lblSelected;
    private javax.swing.JLabel lblSelectedTotal;
    private javax.swing.JLabel lblTextTotal;
    private javax.swing.JPanel pnlActions;
    private javax.swing.JPanel pnlCalendar;
    private javax.swing.JPanel pnlDatePicker;
    private javax.swing.JPanel pnlEventType;
    private javax.swing.JPanel pnlNumberOfPersons;
    private javax.swing.JPanel pnlOptions;
    private javax.swing.JPanel pnlSelected;
    private javax.swing.JPanel pnlSelectedDate;
    private javax.swing.JPanel pnlSelectedMain;
    private javax.swing.JPanel pnlTotal;
    private javax.swing.JScrollPane spDatePicker;
    private javax.swing.JSplitPane spMain;
    private javax.swing.JScrollPane spOptions;
    private javax.swing.JTable tblDatePicker;
    private javax.swing.JTable tblOptions;
    private javax.swing.JTextField txtNumberOfPersons;
    // End of variables declaration//GEN-END:variables


    // </editor-fold>  
}
