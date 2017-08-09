/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eveniment.UI.Models;

import eveniment.DataLayer.ProductJpaController;
import eveniment.Entities.Category;
import eveniment.Entities.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author andrei.ababei
 */
public class CategoryComboBoxModel extends AbstractListModel implements ComboBoxModel {

    private final List<Product> _products;

    private Product _selection = null;
    
    
    public CategoryComboBoxModel(Category category, ProductJpaController productRepository) {
        List<Product> products = productRepository.findProductEntities(); 
        
        _products = new ArrayList<Product>();
        
        for(Product product : products)
            if(Objects.equals(product.getCategoryId(), category.getId()))
                _products.add(product);
    }

    @Override
    public int getSize() {
        return _products.size();
    }

    @Override
    public Object getElementAt(int index) {
        return _products.get(index);
    }

    @Override
    public void setSelectedItem(Object item) {
        _selection = (Product)item;
    }

    @Override
    public Object getSelectedItem() {
        return _selection;
    }
}
