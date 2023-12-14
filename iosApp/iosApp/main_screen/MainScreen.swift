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
    
    var body: some View {
            NavigationLink(destination:EmptyView(), isActive: $viewModel.isActive){
                  EmptyView()
              }.hidden()
              .navigationBarHidden(true)
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
                            
                        })
                        
                        Image("promo_photo")
                                .resizable()
                                .frame(height: 140)
                                .clipShape(RoundedRectangle(cornerRadius: 14.0))
                    
                    }
                    .padding(.horizontal, 30)
                    .padding(.top, 63)
                    .frame(height: 368)
                }
                
                    
            }
        }
    }
}



