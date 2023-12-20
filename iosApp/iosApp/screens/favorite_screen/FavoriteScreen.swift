//
//  FavoriteScreen.swift
//  iosApp
//
//  Created by MacBook on 17.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct FavoriteScreen: View {
    
    @StateObject private var viewModel = FavoriteScreenViewModel()
    
    var body: some View {
       
    
    VStack{
        NavigationLink(destination:CoffeeDetailScreen(id: viewModel.selectedCoffeeToTranfer), isActive: $viewModel.isTransferToDetailScreenActive){
            EmptyView()
        }.hidden()
            .navigationBarHidden(true)
            ZStack{
                Color(hexStringToUIColor(hex: "F9F9F9"))
                VStack{
                    ZStack{
                        LinearGradient(gradient: Gradient(colors: [Color(hexStringToUIColor(hex: "131313")), Color(hexStringToUIColor(hex: "313131"))]), startPoint: .leading, endPoint: .trailing)
                        VStack(alignment: .leading){
                            Text("Favorite coffee")
                                .font(.system(size: 22, weight:.semibold))
                                .foregroundColor(Color(hexStringToUIColor(hex: "DDDDDD")))
                            Spacer()
                            AppSearchBar(callback: {str in
                                viewModel.onSearch(text: str)
                            })
                        }
                        .padding(.horizontal, 30)
                        .padding(.top, 70)
                        .padding(.bottom, 50)
                    }
                    .frame(maxHeight: 234)
                    ScrollView(.vertical){
                        VStack(spacing: 10){
                            ForEach(viewModel.coffee, id:\.self.id){item in
                                FavoriteCoffeeCard(coffee: item, height: 96){
                                    viewModel.transferToDetail(id: item.id)
                                }
                            }
                        }
                        .padding(.horizontal, 15)
                        .padding(.top, 20)
                    }
                }
            }
            .frame(maxHeight: .infinity)
            .ignoresSafeArea()
            .onAppear{
                viewModel.fetchCoffee()
            }
            .showTabBar()
        }
        
    }
}

#Preview {
    FavoriteScreen()
}
