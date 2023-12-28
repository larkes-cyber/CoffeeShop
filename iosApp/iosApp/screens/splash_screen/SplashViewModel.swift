//
//  SplashViewModel.swift
//  iosApp
//
//  Created by MacBook on 13.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class SplashViewModel:ObservableObject{
    
    @Published var auth_succeed = false
    @Published var auth_failure = false
    
    init() {
        UseCases().useGetUserData().execute(completionHandler: {user, err in
            if(user?.data != nil){
                self.auth_succeed = true
            }else{
                self.auth_failure = true
            }
        })
    }
    
}
