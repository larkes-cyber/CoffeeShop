//
//  SplashScreen.swift
//  iosApp
//
//  Created by MacBook on 11.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct StartScreen: View {
    
    @State var hasBeenClicked = false
    
    var body: some View {
        
        VStack{
            
            NavigationLink(destination:LoginScreen(), isActive: $hasBeenClicked){
                  EmptyView()
              }.hidden()
              .navigationBarHidden(true)
            
            ZStack{
                Color(hexStringToUIColor(hex: "000000")).ignoresSafeArea(.all)
                VStack{
                    Image("coffee_background_image")
                        .frame(maxHeight: .infinity)
                    VStack{
                        
                    }
                    .frame(maxHeight: .infinity)
                }
                VStack{
                    LinearGradient(colors: [.black, .black.opacity(0)], startPoint: .bottom, endPoint: .top)
                }
                .frame(maxHeight: 300, alignment: .bottom)
                
                VStack{
                    Spacer()
                    Text("Coffee so good, your taste buds will love it.")
                        .font(.system(size: 34, weight:.semibold))
                        
                        .foregroundColor(.white)
                        .padding(.horizontal, 30)
                        .multilineTextAlignment(.center)
                        .padding(.bottom, 8)
                    Text("The best grain, the finest roast, the powerful flavor.")
                        .font(.system(size: 14, weight:.semibold))
                        .foregroundColor(Color(hexStringToUIColor(hex: "A9A9A9")))
                        .padding(.horizontal, 30)
                        .multilineTextAlignment(.center)
                        .padding(.bottom, 24)
                    
                    AppPrimaryButton(callback: {
                        hasBeenClicked = true
                    }, title: "Get started")
                        .padding(.horizontal, 30)
                        .padding(.bottom, 40)
                    
                }
                .frame(alignment: .bottom)
                    
                
            }.frame(maxWidth: .infinity)
        }
        .ignoresSafeArea()
        
    }
}

#Preview {
    StartScreen()
}
