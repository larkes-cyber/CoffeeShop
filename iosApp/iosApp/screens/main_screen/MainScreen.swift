//
//  MainScreen.swift
//  iosApp
//
//  Created by MacBook on 13.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct MainScreen: View {
    
    @StateObject var viewModel = MainScreenViewModel()
    private let columns = [
           GridItem(.adaptive(minimum: 140))
       ]
    
    var body: some View {
        NavigationLink(destination:CoffeeDetailScreen(id: self.viewModel.selectedCoffeeId ?? ""), isActive: $viewModel.isCoffeeDetailSelected){
            EmptyView()
        }.hidden()
            .navigationBarHidden(true)
        
       
        ScrollView(.vertical){
            ZStack(alignment: .top){
                Color(hexStringToUIColor(hex: "F9F9F9"))
                VStack{
                    ZStack(alignment: .top){
                        LinearGradient(gradient: Gradient(colors: [Color(hexStringToUIColor(hex: "131313")), Color(hexStringToUIColor(hex: "313131"))]), startPoint: .leading, endPoint: .trailing)
                        VStack(spacing: 28){
                            HStack(){
                                VStack(alignment: .leading, spacing: 3){
                                    Text("Welcome")
                                        .font(.system(size: 14, weight:.regular))
                                        .foregroundColor(Color(hexStringToUIColor(hex: "B7B7B7")))
                                    Text(viewModel.user?.name ?? "")
                                        .font(.system(size: 16, weight:.semibold))
                                        .foregroundColor(Color(hexStringToUIColor(hex: "DDDDDD")))
                                }
                                Spacer()
                                Image("coffee_background_image")
                                    .frame(width: 44, height: 44)
                                    .clipShape(RoundedRectangle(cornerRadius: 14.0))
                            }
                            
                            AppSearchBar(callback: {str in
                                viewModel.onFilter(str: str)
                            })
                            
                            Image("promo_photo")
                                .resizable()
                                .frame(height: 140)
                                .clipShape(RoundedRectangle(cornerRadius: 14.0))
                            
                        }
                        .padding(.horizontal, 30)
                        .padding(.top, 63)
                        .padding(.bottom, 24)
                    }
                    .frame(maxHeight: .infinity)

                    
                    VStack{
                        ScrollView(.horizontal, showsIndicators: false){
                            HStack(spacing: 6){
                                ForEach(viewModel.coffeeCategories, id: \.self.id){item in
                                    CategoryItem(category: item.title, callback: {
                                        viewModel.changeCategory(id: item.id)
                                    }, isActive: item.id == viewModel.activeCategory)
                                }
                            }
                        }
                        .statusBarHidden()
                        .padding(.top, 30)
                        .padding(.bottom, 15)
                        
                        if(viewModel.isLoading){
                            ZStack(alignment: .center){
                                ProgressView()
                            }
                        }
                        LazyVGrid(columns: columns, spacing: 20){
                            ForEach(viewModel.coffeeCards, id: \.self.id){ item in
                                CoffeeCard(
                                    coffee: item,
                                    adddedToCartCallback: {hasAdded in
                                        if(hasAdded){
                                            viewModel.addToCart(id: item.id)
                                        }else{
                                            viewModel.removeFromCart(id: item.id)
                                        }
                                    }, callback: {
                                        viewModel.selectCoffee(id: item.id)
                                    }
                                )
                                .padding(.horizontal, 4)
                            }
                        }
                     
                        Spacer()
                            .frame( height: 70)
                    }
                    .padding(.horizontal, 15)
                    .frame(maxHeight: .infinity)
                }
            }
        }
        .showTabBar()
        .refreshable {
            viewModel.syncCoffee()
        }
        .ignoresSafeArea()
       
    }
}


