
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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;

public class OptionsTableModel extends AbstractTableModel {

    private final EntityManagerFactory _entityManagerFactory;
    private final JTable _table;
    
    private Program _program;
    
    private static final String[] _columnNames = new String[]{
        "Serviciu", "Valoare"
    };
    private EachRowEditor _rowEditor;
    private final ProgramProductsJpaController _programProductsRepository;
    private final ProgramCategoriesJpaController _programCategoriesRepository;
    private final CategoryJpaController _categoriesRepository;
    private final ProductJpaController _productsRepository;
    private ArrayList<Category> _categories;
    private ArrayList<Product> _products;
    
    public OptionsTableModel(EntityManagerFactory entityManagerFactory, JTable table) {
        _entityManagerFactory = entityManagerFactory;        
        _table = table;
        
        
        _programProductsRepository = new ProgramProductsJpaController(entityManagerFactory);
        _programCategoriesRepository = new ProgramCategoriesJpaController(entityManagerFactory);
        _categoriesRepository = new CategoryJpaController(entityManagerFactory);
        _productsRepository = new ProductJpaController(entityManagerFactory);
    }

    @Override
    public int getRowCount() {
        if(_program == null)
            return 0;
        
        int a = _program.getProgramCategoriesCollection().size() + _program.getProgramProductsCollection().size();
        
        return a;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0)
            return getTextAt(rowIndex);
        
        return _rowEditor.getRowEditorValue(rowIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1;
    }

    @Override
    public String getColumnName(int column) {
        return _columnNames[column];
    }
    
    public void set(Program program){
        setData(program);
        
        _rowEditor = new EachRowEditor(_table);
        for(int i = 0 ; i < getRowCount() ; i++)
        {
            TableCellEditor editor = getCellEditor(i);
            if(editor != null)
                _rowEditor.setEditorAt(i, editor);
        }
        _table.getColumnModel().getColumn(1).setCellEditor(_rowEditor);
        fireTableDataChanged();
    }

    public TableCellEditor getCellEditor(int row) {
        if(row < _categories.size())
        {
            Category category = _categories.get(row);
            JComboBox cb = new JComboBox(new CategoryComboBoxModel(category, _productsRepository));
            return new DefaultCellEditor(cb);
        }
        else if(row < _categories.size() + _products.size()){
            Product product = _products.get(row - _categories.size());
            JCheckBox check = new JCheckBox(product.getName());
            check.setModel(new ButtonProductModel(product));
            return new DefaultCellEditor(check);
        }
        
        return new DefaultCellEditor(new JTextField());
    }

    private Object getTextAt(int row) {
        if(row < _categories.size())
        {
            return _categories.get(row).getName();
        }
        else if(row < _categories.size() + _products.size()){
            return _products.get(row - _categories.size()).getName();
        }
        
        return null;
    }

    private void setData(Program program) {
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
    
    
}
