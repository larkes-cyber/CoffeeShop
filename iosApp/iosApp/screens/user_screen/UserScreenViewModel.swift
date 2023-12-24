//
//  UserScreenViewModel.swift
//  iosApp
//
//  Created by MacBook on 24.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

class UserScreenViewModel:ObservableObject{
    
    @Published var user:User? = nil
    @Published var selectedLanguage = "English"
    @Published var nameChangeMode = false
    @Published var nameTextField = ""
    
    
    let languages = ["Russian", "English"]
    
    init() {
        fetchUser()
    }
    
    func switchNameChangeMode(){
        self.nameChangeMode = !self.nameChangeMode
        if(!nameChangeMode){
            user?.name = nameTextField
            UseCases().useEditUser().execute(user: self.user ?? User(name: "", number: "", login: "", password: ""), completionHandler: {res, err in
                
            })
            self.nameTextField = ""
        }
    }
    
    func fetchUser(){
        
        UseCases().useGetUserData().execute(completionHandler: {res, err in
            let user = res?.data
            self.user = user
        })
        
    }
    
}
