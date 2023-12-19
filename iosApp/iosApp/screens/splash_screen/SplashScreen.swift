//
//  SplashScreen.swift
//  iosApp
//
//  Created by MacBook on 13.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct SplashScreen: View {
    
    @StateObject private var viewModel = SplashViewModel()
    @State var uiTabarController: UITabBarController?

    
    var body: some View {
        
        NavigationView{
            VStack{
                NavigationLink(destination:MainScreen(), isActive: $viewModel.auth_succeed){
                      EmptyView()
                  }.hidden()
                  .navigationBarHidden(true)
                let _ = print(viewModel.auth_succeed)
                let _ = print(viewModel.auth_failure)
                NavigationLink(destination:StartScreen(), isActive: $viewModel.auth_failure){
                      EmptyView()
                  }.hidden()
                  .navigationBarHidden(true)
                ZStack{
                    Color(hexStringToUIColor(hex: "131313"))
                   // CircularProgressBar(size: 75, fontSize: 16)
                    ProgressView()
                }
            }
            .ignoresSafeArea()
            
        }
        .hiddenTabBar()
        .navigationBarHidden(true)

        
    }
}

#Preview {
    SplashScreen()
}
