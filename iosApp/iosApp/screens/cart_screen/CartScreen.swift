//
//  CartScreen.swift
//  iosApp
//
//  Created by MacBook on 19.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct CartScreen: View {
    
    @StateObject private var viewModel = CartScreenViewModel()
    
    var body: some View {
        VStack{
            NavigationLink(destination:EmptyView(), label: {
             
            })
            .navigationTitle("Your Order")
 
            ZStack{
                Color.white
                ScrollView(.vertical){
                    VStack{
                        VStack(alignment:.leading){
                            TabsRow(titles: ["Deliver", "Pick Up"], selectedTitle: "Deliver", callback: {str in
                                
                            })
                            .padding(.top, 21)
                            .padding(.bottom, 24)
                            Text("Delivery Address")
                                .font(.system(size: 18, weight:.semibold))
                                .foregroundColor(Color(hexStringToUIColor(hex: "2F2D2C")))
                                .padding(.bottom, 16)
                            HStack{
                                MiddleActionBtn(iconName: "edit_icon", title: "Add Address"){
                                    
                                }
                                MiddleActionBtn(iconName: "doc_icon", title: "Add Note"){
                                    
                                }
                            }
                            .padding(.bottom, 34)
                        }
                        .padding(.horizontal, 23)
                        ScrollView(.vertical){
                            VStack(spacing:20){
                                ForEach(0..<viewModel.cartList.count, id:\.self){index in
                                    let item = viewModel.cartList[index]
                                    VStack{
                                        CoffeeCartCard(
                                            coffee: item.coffee,
                                            amount: item.amount,
                                            amountHasChagedCallback: {amount in
                                                viewModel.changeAmount(index: index, amount: amount)
                                            }
                                        )
                                            .padding(.bottom, 13)
                                        Divider()
                                            .frame(maxWidth: .infinity)
                                            .frame(height: 1)
                                            .foregroundColor(Color(hexStringToUIColor(hex: "EAEAEA")))
                                    }
                                }
                            }
                        }
                        .padding(.horizontal, 30)
                        .frame(maxHeight: 350)
                    
                        ZStack{
                            Color(hexStringToUIColor(hex: "F4F4F4"))
                        }
                        .frame(maxWidth: .infinity)
                        .frame(height: 4)
                        
                        VStack(alignment:.leading, spacing: 16){
                            Text("Payment Summary")
                                .font(.system(size: 18, weight: .semibold))
                                .foregroundColor(Color(hexStringToUIColor(hex:"2f2d2c")))
                                .padding(.top, 33)
                                .padding(.bottom, 16)
                            PaymentRow(title: "Price", price: viewModel.price)
                            PaymentRow(title: "Delivery Fee", price: viewModel.fee)
                            Divider()
                                .frame(maxWidth: .infinity)
                                .frame(height: 1)
                                .foregroundColor(Color(hexStringToUIColor(hex: "EAEAEA")))
                            PaymentRow(title: "Total Payment", price: viewModel.fee + viewModel.price)
                            AppPrimaryButton(title: "Order"){
                                
                            }
                            .padding(.top, 16)
                        }
                        .padding(.horizontal, 30)
                        
                    }
                }
            }
        }
        .onAppear{
            viewModel.fetchCoffee()
            viewModel.countPrice()
        }
    }
}

#Preview {
    CartScreen()
}
