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
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CalendarCellRender extends DefaultTableCellRenderer {

    private final EventJpaController _eventRepository;
    private final Calendar _calendar;
    private List<Event> _entries;

    public CalendarCellRender(Calendar calendar, EventJpaController eventRepository) {
        _calendar = calendar;
        _eventRepository = eventRepository;
        refresh();
    }
    
    public void refresh(){
        _entries = _eventRepository.findEventEntities();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
        
        if(value == null)
            return comp;
        
        for(Event event : _entries)
        {
            Date date = event.getDate();
            Calendar cal = new GregorianCalendar();
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            if(year == _calendar.get(Calendar.YEAR))
            {
                int month = cal.get(Calendar.MONTH);
                if(month == _calendar.get(Calendar.MONTH))
                {
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    if(Integer.toString(day).equals(value.toString()))
                        comp.setBackground(Color.red);
                    else
                        comp.setBackground(Color.white);
                }
            }
        }
        
        return comp;
    }
    
    
}
