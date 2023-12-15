//
//  LoginScreen.swift
//  iosApp
//
//  Created by MacBook on 11.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct LoginScreen: View {
    
    @StateObject private var loginViewModel = LoginViewModel()
    
    var body: some View {
        VStack{
            NavigationLink(destination:SplashScreen(), isActive: $loginViewModel.isSucceed){
                  EmptyView()
              }.hidden()
              .navigationBarHidden(true)
            ZStack{
                Color(hexStringToUIColor(hex: "131313"))
                VStack{
                    VStack{
                        Text(loginViewModel.loginMode == "login" ? "Registration" : "Welcome back!")
                            .font(.system(size: 34, weight:.semibold))
                            .foregroundColor(.white)
                            .frame(maxWidth:.infinity, alignment: .leading)
                        
                        if(loginViewModel.loginMode != "login"){
                            Text("login in your account")
                                .font(.system(size: 18, weight:.regular))
                                .foregroundColor(Color(hexStringToUIColor(hex: "A9A9A9")))
                                .frame(maxWidth:.infinity, alignment: .leading)
                                .padding(.top, 5)
                        }
                    }
                    .padding(.bottom, 45)
                    let _ = print(loginViewModel.error)
                    VStack{
                        VStack(alignment: .leading){
                            VStack(spacing: 18){
                                if(loginViewModel.loginMode == "login"){
                                    LoginTextField(text: $loginViewModel.name, placeholder: "Enter your name")
                                }
                                LoginTextField(text: $loginViewModel.login, placeholder: "Enter your email")
                                PasswordTextField(text: $loginViewModel.password, placeholder: "Enter your password")
                            }
                            if(!loginViewModel.error.isEmpty){
                                Text(loginViewModel.error)
                                    .font(.system(size: 14, weight:.semibold))
                                    .foregroundColor(.red)
                                    .frame(alignment: .leading)
                            }
                        }
                        if(loginViewModel.isLoading){
                            HStack(alignment: .center){
                                ZStack{
                                    CircularProgressBar(
                                        size: 50, fontSize: 14
                                    )
                                }
                                .padding(.top, 10)
                            }
                        }
                    }
                    .padding(.bottom, 70)
                    AppPrimaryButton(callback: {
                        loginViewModel.onContinue()
                    }, title: "Continue")
                    
                    HStack{
                        Text(loginViewModel.loginMode == "login" ? "Already have an account?" : "Don’t have an account?")
                            .font(.system(size: 14, weight:.regular))
                            .foregroundColor(Color(hexStringToUIColor(hex: "A9A9A9")))
                        Button(action: {
                            loginViewModel.switchMode()
                        }, label: {
                            Text(loginViewModel.loginMode == "login" ? "Sign in" : "Sign up")
                                .font(.system(size: 14, weight:.regular))
                                .foregroundColor(Color(hexStringToUIColor(hex: "C67C4E")))
                        })
                    }
                }
                .padding(.horizontal, 30)
            }
        }
        .ignoresSafeArea()
    }
}


