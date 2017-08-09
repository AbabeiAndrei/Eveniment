
package eveniment.UI.Models;

import eveniment.DataLayer.CategoryJpaController;
import eveniment.DataLayer.ProductJpaController;
import eveniment.DataLayer.ProgramCategoriesJpaController;
import eveniment.DataLayer.ProgramProductsJpaController;
import eveniment.Entities.Category;
import eveniment.Entities.Product;
import eveniment.Entities.Program;
import eveniment.Entities.ProgramCategories;
import eveniment.Entities.ProgramProducts;
import java.awt.Component;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class OptionsCellEditorModel extends AbstractCellEditor implements TableCellEditor {
    
    private TableCellEditor editor;
    private Program _program;
    private final EntityManagerFactory _entityManagerFactory;
    private final JTable _table;
    private final ProgramProductsJpaController _programProductsRepository;
    private final ProgramCategoriesJpaController _programCategoriesRepository;
    private final CategoryJpaController _categoriesRepository;
    private final ProductJpaController _productsRepository;
    private ArrayList<Category> _categories;
    private ArrayList<Product> _products;
        
    OptionsCellEditorModel(EntityManagerFactory entityManagerFactory, JTable table) {
        _table = table;
        _entityManagerFactory = entityManagerFactory;
        _programProductsRepository = new ProgramProductsJpaController(entityManagerFactory);
        _programCategoriesRepository = new ProgramCategoriesJpaController(entityManagerFactory);
        _categoriesRepository = new CategoryJpaController(entityManagerFactory);
        _productsRepository = new ProductJpaController(entityManagerFactory);
    }

    @Override
    public Object getCellEditorValue() {
        if(editor != null)
            return editor.getCellEditorValue();
        
        return null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if(column == 0)
            return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
        
        return getComponent(table, value, isSelected, row, column);
    }

    void set(Program program) {
        _program = program;
        List<ProgramCategories> programCategories = new ArrayList<>();
        for(ProgramCategories pcat : _programCategoriesRepository.findProgramCategoriesEntities())
            if(pcat.getProgramCategoriesPK().getProgramId() == _program.getId())
                programCategories.add(pcat);
        
        List<ProgramProducts> programProducts = new ArrayList<>();
        for(ProgramProducts pprod : _programProductsRepository.findProgramProductsEntities())
            if(pprod.getProgramProductsPK().getProgramId() == _program.getId())
                programProducts.add(pprod);
        
        _categories = new ArrayList<>();
        _products = new ArrayList<>();
        
        for(ProgramCategories pcat : programCategories)
            _categories.add(_categoriesRepository.findCategory(pcat.getProgramCategoriesPK().getCategoryId()));
        
        for(ProgramProducts pprod : programProducts)
            _products.add(pprod.getProduct());
        
    }

    private Component getComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if(editor == null)
            if(row < _categories.size())
            {
                Category category = _categories.get(row);
                JComboBox cb = new JComboBox(new CategoryComboBoxModel(category, _productsRepository));
                editor = new DefaultCellEditor(cb);
            }
            else if(row < _categories.size() + _products.size()){
                Product product = _products.get(row - _categories.size());
                JCheckBox check = new JCheckBox(product.getName());
                editor = new DefaultCellEditor(check);
            }
        
        return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
    }    
    
    public Object getTextAt(int row){
        if(row < _categories.size())
        {
            return _categories.get(row).getName();
        }
        else if(row < _categories.size() + _products.size()){
            return _products.get(row - _categories.size()).getName();
        }
        
        return null;
    }

    public Object getValueAt(int rowIndex) {        
        return getCellEditorValue();
    }

    @Override
    public boolean isCellEditable(EventObject e) {
        return true;
    }
    
    
}
