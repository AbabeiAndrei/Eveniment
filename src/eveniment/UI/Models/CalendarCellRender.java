/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eveniment.UI.Models;

import eveniment.DataLayer.EventJpaController;
import eveniment.DataLayer.PeriodJpaController;
import eveniment.Entities.Event;
import eveniment.Entities.Period;
import java.awt.Color;
import java.awt.Component;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CalendarCellRender extends DefaultTableCellRenderer {

    private final EventJpaController _eventRepository;
    private final Calendar _calendar;

    public CalendarCellRender(Calendar calendar, EventJpaController eventRepository) {
        _calendar = calendar;
        _eventRepository = eventRepository;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
        
        for(Event event : _eventRepository.findEventEntities())
        {
            Date date = event.getDate();
            if(date.getYear()== _calendar.get(Calendar.YEAR) && date.getMonth() == _calendar.get(Calendar.MONTH) && Integer.toString(date.getDay()).equals(value.toString()))
            {
                comp.setBackground(Color.red);
            }
        }
        
        return comp;
    }
    
    
}
