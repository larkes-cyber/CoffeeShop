//
//  LoginViewModel.swift
//  iosApp
//
//  Created by MacBook on 11.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

class LoginViewModel:ObservableObject{
    
    @Published var login = ""
    @Published var name = ""
    @Published var password = ""
    
    @Published var loginMode = "login"
    @Published var isLoading = false
    @Published var error = ""
    @Published var isSucceed =  false
    
    func switchMode(){
        loginMode = loginMode == "login" ? "auth" : "login"
    }
    
    func onContinue(){
        isLoading = true
        error = ""
        if(loginMode == "login"){
            UseCases().useRegisterUser()
                .execute(user: User(name: name, number: nil, login: login, password: password), completionHandler: {res, err in
                    self.isSucceed = res?.data != nil
                    self.isLoading = false
                    self.error = res?.message ?? ""
            })
        }else{
            UseCases().useAuthUser().execute(login: login, password: password, completionHandler: {res, err in
                self.isSucceed = res?.data != nil
                self.isLoading = false
                self.error = res?.message ?? ""
            })
        }
    }
    
}
