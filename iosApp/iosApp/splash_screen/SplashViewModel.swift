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
                UseCases().useSyncCoffeeCategories().execute(completionHandler: {_,_ in
                    UseCases().useSyncCoffee().execute(completionHandler: {_,_ in
                        UseCases().useSyncUserData().execute(completionHandler: {_,_ in
                            UseCases().useSyncOrders().execute(completionHandler: {_,_ in
                                self.auth_succeed = true
                            })
                        })

                    })

                })
            }else{
                self.auth_failure = true
            }
        })
    }
    
}
