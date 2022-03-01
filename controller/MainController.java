package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.ProductModel;
import service.ProductDetails;
import dao.ProductDao;

public class MainController {
	public static void main(String[] args) throws ClassNotFoundException {
	        ProductDetails prdt = new ProductDetails();
		    ProductDao prdDao = new ProductDao();

		System.out.println("==================================================");
		System.out.println("                 Online Shop                        ");
		System.out.println("==================================================");
		System.out.println("Are you an Admin or User. Type your option below");
		System.out.println();
		boolean temp = true;
		while(temp) {
			System.out.println("1) Admin \n2) User\n3) Exit");
			System.out.println();
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter choice : ");
			int option = Integer.parseInt(sc.nextLine());
			System.out.println();
			switch(option) {
				case 1: {
						boolean temp1 = true;
						while(temp1) {
							System.out.println("Choose any one of an operation that you wish to do");
							System.out.println("==================================================");
							System.out.println("1) Add Item \n2) View Product details\n3) Update Product Details\n4) Delete Product\n5) Exit");
							System.out.println("==================================================");
							boolean flag1 = true;
							int adminOption = 0;
							while(flag1) {
								try {
									adminOption = Integer.parseInt(sc.nextLine());
									flag1 = false;
								}catch(Exception e) {
									System.out.println("Input given type is wrong. Give the right option in number type");
									flag1 =true;
								}
							}
							
							switch(adminOption) {
								case 1:{
									System.out.println("********************************************************************");
									System.out.print("How many Product details that you need to add : ");
									int ItemCount = Integer.parseInt(sc.nextLine());
									
									int pid;
									String productName;
								    double price;
									int stock;
									
									for(int i=0; i<ItemCount; i++) {
										  System.out.print("Enter the product id : ");
									      pid              = sc.nextInt();
									      sc.nextLine();
									      
									      System.out.print("Enter the product name :");
									      productName  = sc.nextLine();
									      
									      System.out.print("Enter the price of the product : ");
									      price        = sc.nextDouble();
									      sc.nextLine();
									      
									      System.out.print("Enter the stocks available for the product : ");
									      stock           = sc.nextInt();
									      System.out.println("********************************************************************");
									      
									      
									      ProductModel pd = new ProductModel(pid,productName,price,stock);
									      prdt.addProductToList(pd);
									      
								     }
								     break;
								}
								case 2:{
									System.out.println("********************************************************************");
									prdDao.viewProducts();
									System.out.println("********************************************************************");
					             	break;
								    }
								case 3:{
									System.out.println("********************************************************************");
									System.out.print("Enter the id to update : ");
									int id = sc.nextInt();
								    sc.nextLine();
								    System.out.print("Enter the price to update : ");
									double price = sc.nextDouble();
									sc.nextLine();
									System.out.print("Enter the updated stocks : ");
								    int stock_rem = sc.nextInt();
								    sc.nextLine();
								    prdDao.update(stock_rem, id,price);
									break;
								    }
								case 4:{
									System.out.print("Enter the product id to delete the record : ");
								    int pid = sc.nextInt();
								    prdDao.delete(pid);
								    System.out.println("********************************************************************");
								    break;
								    }
								case 5:{
									temp1=false;
									break;
								    }
							 }
						}					
					break;
				}
				
				case 2:{
					    boolean temp2 = true;
					    while(temp2) {
					
				          	System.out.println("Choose any one of an operation that you wish to do");
				        	System.out.println("==================================================");
				          	System.out.println("1)View the products\n"
							           +"2)Add to cart\n"
							           +"3)Remove from cart\n"
							           +"4)View the adcart\n"
							           +"5)TotalAmount\n"
							           +"6)Exit");
				        	System.out.println("==================================================");
					        int choice = Integer.parseInt(sc.nextLine());
				    
					        switch(choice) {
					            case 1:{
					            	  System.out.println("********************************************************************");
						              prdDao.viewProducts();
						              System.out.println("********************************************************************");
						              break;
					                  }
					            case 2:{
					            	  System.out.println("********************************************************************");
					    	          System.out.println("Enter the product id for add to cart : ");
					    	          int searchId = Integer.parseInt(sc.nextLine());
					                  prdDao.adcart(searchId);
					                  System.out.println("********************************************************************");
					    	          break;
					                  }
					           case 3:{
					        	      System.out.println("********************************************************************");
					    	          System.out.println("Enter the product id to delete from cart : ");
					    	          int delid = Integer.parseInt(sc.nextLine());
					    	          prdDao.delete_cart(delid);
					    	          prdDao.stockupdate(delid);
					    	          System.out.println("********************************************************************");
					    	          break;
					                  }
					           case 4:{
					        	      System.out.println("********************************************************************");
					    	          prdDao.viewcart();
					    	          System.out.println("********************************************************************");
					    	          break;
					                  }
					           case 5:{
					        	   System.out.println("********************************************************************");
					        	   prdDao.totalAmount();
					        	   System.out.println("********************************************************************");
					        	   break;
					           }
					           case 6:{
					    	          temp2 = false;
					    	          break;
					                  }
					          }
				       }
				       break;
				}
				
				case 3:{
					temp = false;
				}
			}
		}
	}
}
