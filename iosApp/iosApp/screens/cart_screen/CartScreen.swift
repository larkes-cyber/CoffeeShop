//
//  CartScreen.swift
//  iosApp
//
//  Created by MacBook on 19.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import MapKit

struct CartScreen: View {
    
    @StateObject private var viewModel = CartScreenViewModel()
    
    @State private var mapRegion = MKCoordinateRegion(center: CLLocationCoordinate2D(latitude: 51.5, longitude: -0.12), span: MKCoordinateSpan(latitudeDelta: 0.2, longitudeDelta: 0.2))

    
    var body: some View {
        VStack{
            NavigationLink(destination:EmptyView(), label: {
                HStack(alignment: .center){
                    Text(viewModel.showMap ? "Select location" :"Your Order")
                        .font(.system(size: 22, weight: .semibold))
                        .foregroundColor(Color(hexStringToUIColor(hex: "2F2D2C")))
                    Spacer()
                    if viewModel.showMap{
                        Button(action: {
                            viewModel.switchShowingMap()
                        }, label: {
                            Text("Done")
                        })
                    }
                }
                .padding(.horizontal, 30)
                .padding(.top, 10)
            })
        
            ZStack{
                Color.white
                ScrollView(.vertical){
                    VStack{
                        VStack(alignment:.leading){
                            TabsRow(titles: ["Pick Up", "Deliver"], selectedTitle: viewModel.deliveryMode, callback: {str in
                                viewModel.onDeliveryModeChange(mode: str)
                            })
                            .padding(.top, 21)
                            .padding(.bottom, 24)
                            if(viewModel.deliveryMode == "Deliver"){
                                Text("Delivery Address")
                                    .font(.system(size: 18, weight:.semibold))
                                    .foregroundColor(Color(hexStringToUIColor(hex: "2F2D2C")))
                                    .padding(.bottom, 16)
                                HStack{
                                    MiddleActionBtn(iconName: "edit_icon", title: "Add Address"){
                                        viewModel.switchShowingMap()
                                    }
                                    MiddleActionBtn(iconName: "doc_icon", title: "Add Note"){
                                        
                                    }
                                }
                            }else{
                                if(viewModel.selectedLocation != nil){
                                    Text("Sected pick up location: \(viewModel.selectedLocation?.name ?? "")")
                                        .font(.system(size: 18, weight: .medium))
                                }
                                
                                AppMap(coords: viewModel.coords, callback: {coords in
                                    viewModel.selectLocation(location: coords)
                                })
                                .frame(minHeight: 300)
                                .cornerRadius(14)
                            }
                        }
                        .padding(.bottom, 34)
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
                            if(viewModel.showingAlert){
                                SmallSpanAlert(title: "Sorry, on this moment payment is not available", callback: {
                                    viewModel.switchAlertActivity()
                                })
                                .padding(.top, 16)
                                .padding(.bottom, 30)
                            }else{
                                AppPrimaryButton(title: "Order"){
                                    viewModel.switchAlertActivity()
                                }
                                .padding(.top, 16)
                                .padding(.bottom, 30)
                            }
                        }
                        .padding(.horizontal, 30)
                        
                    }
                }
                if(viewModel.showMap){
                    AppMap(coords: viewModel.coords, callback: {coords in
                        viewModel.selectLocation(location: coords)
                    })
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
