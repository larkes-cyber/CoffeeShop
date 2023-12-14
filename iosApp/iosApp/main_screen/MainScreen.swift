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
        ZStack{
            Color(hexStringToUIColor(hex: "F9F9F9"))
            VStack{
                
                ZStack{
                    LinearGradient(gradient: Gradient(colors: [Color(hexStringToUIColor(hex: "131313")), Color(hexStringToUIColor(hex: "313131"))]), startPoint: .leading, endPoint: .trailing)
                    VStack{
                        
                        HStack(){
                            VStack{
                                Text("Welcome")
                                    .font(.system(size: 12, weight:.regular))
                                    .foregroundColor(Color(hexStringToUIColor(hex: "B7B7B7")))
                                Text(viewModel.user?.name ?? "")
                                    .font(.system(size: 14, weight:.semibold))
                                    .foregroundColor(Color(hexStringToUIColor(hex: "DDDDDD")))
                            }
                            Image("coffee_background_image")
                                .frame(width: 44, height: 44)
                                .clipShape(Rectangle())
                        }
                        .frame(width: .infinity)
                        
                        
                        
                    }
                }
                
                
            }
            
        }
        .frame(minWidth: .infinity, minHeight: .infinity)
    }
}

#Preview {
    MainScreen()
}
