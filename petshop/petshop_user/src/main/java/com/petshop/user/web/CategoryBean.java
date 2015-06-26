package com.petshop.user.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import com.petshop.model.ejbs.ICategoryService;
import com.petshop.model.entities.Category;
import com.petshop.model.entities.Product;
import com.petshop.user.form.CategoryForm;
import com.petshop.user.form.OrderForm;
import com.petshop.user.form.ProductForm;
import com.petshop.user.utilities.RandomString;
import com.petshop.user.utilities.SessionBean;

/**
 * Managed JSF Bean class to browse through all the categories. This class uses
 * ejb to get the data from the database
 * 
 * @author shivangi
 *
 */
@ManagedBean(name = "categoryBean")
@SessionScoped
public class CategoryBean {

	@EJB
	private ICategoryService categoryService;

	private OrderForm orderForm = new OrderForm();

	/**
	 * @return the orderForm
	 */
	public OrderForm getOrderForm() {
		return orderForm;
	}

	/**
	 * @param orderForm
	 *            the orderForm to set
	 */
	public void setOrderForm(OrderForm orderForm) {
		this.orderForm = orderForm;
	}

	/**
	 * This function loads the categories.
	 */
	public void loadCategories() {

		HttpSession session = SessionBean.getSession();
		int index = 0;

		List<CategoryForm> catFormList = new ArrayList<CategoryForm>();

		if (null != session.getAttribute("loadCat")
				&& !("".equals(session.getAttribute("loadCat")))
				&& ("false".equals(session.getAttribute("loadCat")))) {
			session.setAttribute("loadCat", null);

		} else {

			List<Category> categoryList = categoryService.findAllCategories();

			for (Category catTemp : categoryList) {
				CategoryForm catForm = new CategoryForm();
				catForm.setId(index);
				catForm.setCategoryId(catTemp.getCategoryId());
				catForm.setCategoryName(catTemp.getCategoryName());
				catForm.setCategoryDesc(catTemp.getCategoryDesc());

				List<ProductForm> prodSet = new ArrayList<ProductForm>(0);

				for (Product prodTemp : catTemp.getProductses()) {
					ProductForm prodForm = new ProductForm();

					prodForm.setAmount(prodTemp.getAmount().doubleValue());
					prodForm.setCategoryName(catTemp.getCategoryName());
					prodForm.setProductDesc(prodTemp.getProductDesc());
					prodForm.setProductId(prodTemp.getProductId());
					prodForm.setProductName(prodTemp.getProductName());
					prodForm.setQuantity(0);
					prodForm.setTotal(prodForm.getQuantity()
							* prodForm.getAmount());

					prodSet.add(prodForm);
					prodForm = null;
				}

				catForm.setProdSet(prodSet);

				catFormList.add(catForm);
			}

			this.orderForm.setOrderDate(new Date());
			this.orderForm.setOrderNum(RandomString.randomAlphaNumeric(15));
			this.orderForm.setCatList(catFormList);
		}

	}

	/**
	 * This function creates the customer order.
	 */
	public String placeOrder() {
		List<ProductForm> prodList = new ArrayList<ProductForm>();

		for (CategoryForm catTemp : this.orderForm.getCatList()) {
			for (ProductForm prodTemp : catTemp.getProdSet()) {
				if (prodTemp.getQuantity() > 0) {
					prodTemp.setTotal(prodTemp.getQuantity()
							* prodTemp.getAmount());
					prodList.add(prodTemp);
				}
			}
		}

		this.orderForm.setProdList(prodList);

		return "/protected/confirmOrder";
	}

	/**
	 * This function creates the customer order.
	 */
	public String editOrder() {
		HttpSession session = SessionBean.getSession();
		session.setAttribute("loadCat", "false");

		return "/public/browseProd";
	}

	/**
	 * This function creates the customer order.
	 */
	public void updateAmount() {

		double totalAmt = 0.0;
		this.orderForm.setTotalAmt(0);
		for (CategoryForm catTemp : this.orderForm.getCatList()) {
			for (ProductForm prodTemp : catTemp.getProdSet()) {
				totalAmt = totalAmt
						+ (prodTemp.getQuantity() * prodTemp.getAmount());
			}
		}
		this.orderForm.setTotalAmt(totalAmt);
	}

}
