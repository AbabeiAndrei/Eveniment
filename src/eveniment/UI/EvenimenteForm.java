
package eveniment.UI;

import eveniment.DataLayer.EventJpaController;
import eveniment.DataLayer.PeriodJpaController;
import eveniment.DataLayer.ProgramJpaController;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.PlainDocument;

public class EvenimenteForm extends javax.swing.JFrame {

    private final EntityManagerFactory _entityManagerFactory;
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
    private final EventJpaController _eventController;
    private final Users _user;

    public EvenimenteForm(EntityManagerFactory entityManagerFactory, Users user) {
        initComponents();
        configureUi();
        
        _user = user;
        _entityManagerFactory = entityManagerFactory;
        _periodController = new PeriodJpaController(entityManagerFactory);
        _eventController = new EventJpaController(entityManagerFactory);
        _programController = new ProgramJpaController(entityManagerFactory);
        
        _calendar = Calendar.getInstance();
        _calendar.setFirstDayOfWeek(0);
        
        _calendarModel = new CalendarTableModel(_calendar);
        _calendarRender = new CalendarCellRender(_calendar, _eventController);
        _optionsModel = new OptionsTableModel(entityManagerFactory, tblOptions);
        
        _day = _calendar.get(Calendar.DAY_OF_MONTH);
        _month = _calendar.get(Calendar.MONTH);
        _year = _calendar.get(Calendar.YEAR);
        selectMonth(_month);
        
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
        btnPrev.setText("PREV");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        pnlActions.add(btnPrev, java.awt.BorderLayout.WEST);

        btnNext.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnNext.setText("NEXT");
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

        pnlSelected.add(pnlTotal, java.awt.BorderLayout.SOUTH);

        spMain.setRightComponent(pnlSelected);

        getContentPane().add(spMain, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="Events">   
    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        selectMonth(_month - 1);
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        selectMonth(_month + 1);
    }//GEN-LAST:event_btnNextActionPerformed

    private void cbEventTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEventTypeActionPerformed
        int index = cbEventType.getSelectedIndex();
        
        if(index < 0)
            return;
        
        setEventDetails(_eventTypes.get(index));
        recalculateTotal();
    }//GEN-LAST:event_cbEventTypeActionPerformed

    private void tblDatePickerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDatePickerMouseClicked
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
        Event event = CreateEvent();
    }//GEN-LAST:event_btnDetailsActionPerformed
    // </editor-fold>   
    
    //<editor-fold defaultstate="collapsed" desc="methods">
    private void selectMonth(int month) {
        
        if(month <= 0)
        {
            month = 12;
            _year--;
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
        
        _calendar.set(_year, _month, day);
        
        int dayOfWeek = _calendar.get(Calendar.DAY_OF_WEEK);
        String dayOfWeekName = CalendarUtils.getDayName(dayOfWeek).toUpperCase();
        String monthName = CalendarUtils.getMonthName(_month);
        
        lblSelected.setText("Selectat - " + dayOfWeekName + " " + day + " " + monthName + " " + _year);
        
        _day = day;
                
        recalculateTotal();
    }
    
    private void fillCalendar(int month) {
        _calendarModel.setDate(_year, month);
    }
    
    private void recalculateTotal() {
        float total = 0f;
        
        total += _periodController.getPrice(_day, _month, _year);
        
        if(_program != null)
            total += _program.getPrice().floatValue();
        
        DecimalFormat formater = new DecimalFormat("#.## LEI");
        
        lblSelectedTotal.setText(formater.format(total));
    }

    private void setModels() {
        tblDatePicker.setModel(_calendarModel);
        tblDatePicker.setDefaultRenderer(String.class, _calendarRender);
        
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
    }

    private void setEventDetails(Program program) {
        _program = program;
        _optionsModel.set(_program);
    }

    private Event CreateEvent() {
        Event event = new Event();
        
        Integer numberOfPersons;
        
        try
        {
            numberOfPersons = Integer.parseInt(txtNumberOfPersons.getText());
        }
        catch(Exception ex)
        {
            return null;
        }
            
        if(numberOfPersons <= 0)
            return null;
        
        Program program = (Program)cbEventType.getSelectedItem();
        
        event.setProgramId(program);
        
        event.setDate(_calendar.getTime());
        event.setCreatedAt(CalendarUtils.getTime());
        event.setCreatedBy(_user);
        event.setRowState(RowState.Created.toString());
        event.setForUser(_user);
        event.setNumberOfPersons(numberOfPersons);
        event.setEventItemCollection(createEventItems());
        
        return event;
    }
    
    private Collection<EventItem> createEventItems() {
        List<EventItem> items = new ArrayList<>();
        
        int rows = _optionsModel.getRowCount();
        
        for(int i = 0; i < rows ; i++)
        {
            Product row = (Product)_optionsModel.getValueAt(i, 1);
            
            EventItem item = new EventItem();
            
            item.setName("");
            
            items.add(item);
        }
        
        return items;
    }
//</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Generated fields">   
    // Variables declaration - do not modify//GEN-BEGIN:variables
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
