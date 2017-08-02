
package eveniment.UI;

import eveniment.DataLayer.PeriodJpaController;
import eveniment.DataLayer.ProgramJpaController;
import eveniment.UI.Models.CalendarTableModel;
import eveniment.Utils.CalendarUtils;
import java.text.DecimalFormat;
import java.util.Calendar;
import javax.persistence.EntityManagerFactory;
import javax.swing.DefaultComboBoxModel;

public class EvenimenteForm extends javax.swing.JFrame {

    private final EntityManagerFactory _entityManagerFactory;
    private final PeriodJpaController _periodController;
    private final ProgramJpaController _programController;
    
    private final Calendar _calendar;
    private final CalendarTableModel _calendarModel;
    
    private int _day;
    private int _year;
    private int _month;

    public EvenimenteForm(EntityManagerFactory entityManagerFactory) {
        initComponents();
        configureUi();
        
        _entityManagerFactory = entityManagerFactory;
        _periodController = new PeriodJpaController(entityManagerFactory);
        _programController = new ProgramJpaController(entityManagerFactory);
        
        _calendarModel = new CalendarTableModel(_year, _month);
        
        _calendar = Calendar.getInstance();
        
        _day = _calendar.get(Calendar.DAY_OF_MONTH);
        _month = _calendar.get(Calendar.MONTH);
        _year = _calendar.get(Calendar.YEAR);
        selectMonth(_month);
        
        setModels();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

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
        
    }//GEN-LAST:event_cbEventTypeActionPerformed
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
        
        FillCalendar(month);
    }
    
    private void setDay(int day){
        
        if(day <= 0)
            day = 1;
        
        _calendar.set(_year, _month + 1, 1);
        
        if(_calendar.getActualMaximum(Calendar.DAY_OF_MONTH) < day)
            day = _calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        _calendar.set(_year, _month + 1, day);
        
        int dayOfWeek = _calendar.get(Calendar.DAY_OF_WEEK);
        String dayOfWeekName = CalendarUtils.getDayName(dayOfWeek).toUpperCase();
        String monthName = CalendarUtils.getMonthName(_month);
        
        lblSelected.setText("Selectat - " + dayOfWeekName + " " + _day + " " + monthName + " " + _year);
        
        RecalculateTotal();
        
        _day = day;
    }
    
    private void FillCalendar(int month) {
        _calendarModel.setDate(_year, month);
    }
    
    private void RecalculateTotal() {
        float total = 0f;
        
        total += _periodController.getPrice(_day, _month, _year);
        
        DecimalFormat formater = new DecimalFormat("#.## LEI");
        
        lblSelectedTotal.setText(formater.format(total));
    }

    private void setModels() {
        tblDatePicker.setModel(_calendarModel);
        
        String[] eventTypes = _programController.getAllProgramNames();
        
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(eventTypes);
        cbEventType.setModel(model);
    } 

    private void configureUi() {
        setLocationRelativeTo(null);
        
        spMain.setResizeWeight(.8d);
        spMain.setDividerLocation(.8d);
    }//</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Generated fields">   
    // Variables declaration - do not modify//GEN-BEGIN:variables
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
