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
        UseCases().useGetUserData().execute(completionHandler: {res, err in
            
            if(res?.data != nil){
                UseCases().useSyncCoffeeCategories().execute(completionHandler: {_,_ in })
                UseCases().useSyncCoffee().execute(completionHandler: {_,_ in })
                UseCases().useSyncUserData().execute(completionHandler: {_,_ in })
                UseCases().useSyncOrders().execute(completionHandler: {_,_ in })
            }
            self.auth_succeed = res?.data != nil
            self.auth_failure = res?.data == nil
        })
    }
    
}
